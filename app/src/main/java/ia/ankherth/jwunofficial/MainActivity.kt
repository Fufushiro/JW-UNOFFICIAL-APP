package ia.ankherth.jwunofficial

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import android.webkit.WebSettings.LOAD_CACHE_ONLY
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var bottomNavigationView: BottomNavigationView
    private var lastScrollY = 0
    private var currentLanguageCode: String = ""

    companion object {
        private const val BASE_URL = "https://www.jw.org"
    }

    private fun getHomeUrl(): String {
        return if (currentLanguageCode.isEmpty()) {
            "$BASE_URL/"
        } else {
            "$BASE_URL/$currentLanguageCode/"
        }
    }

    private fun getNewsUrl(): String {
        // Obtener la traducción de "news" según el idioma
        val newsSegment = getNewsSegmentForLanguage(currentLanguageCode)

        return if (currentLanguageCode.isEmpty()) {
            "$BASE_URL/en/news/"
        } else {
            "$BASE_URL/$currentLanguageCode/$newsSegment/"
        }
    }

    private fun getNewsSegmentForLanguage(languageCode: String): String {
        return when (languageCode) {
            "" -> "news"  // English
            "es" -> "noticias"  // Español
            "pt" -> "noticias"  // Português
            "fr" -> "actualites"  // Français
            "de" -> "nachrichten"  // Deutsch
            "it" -> "notizie"  // Italiano
            "ru" -> "novosti"  // Русский
            "ja" -> "news"  // 日本語
            "ko" -> "news"  // 한국어
            "zh-hans" -> "news"  // 中文简体
            "zh-hant" -> "news"  // 中文繁體
            "pl" -> "aktualnosci"  // Polski
            "ro" -> "stiri"  // Română
            "uk" -> "novyny"  // Українська
            "nl" -> "nieuws"  // Nederlands
            "sv" -> "nyheter"  // Svenska
            "no" -> "nyheter"  // Norsk
            "da" -> "nyheder"  // Dansk
            "fi" -> "uutiset"  // Suomi
            "cs" -> "zpravy"  // Čeština
            "hu" -> "hirek"  // Magyar
            "el" -> "nea"  // Ελληνικά
            "tr" -> "haberler"  // Türkçe
            "ar" -> "news"  // العربية
            "he" -> "news"  // עברית
            "hi" -> "news"  // हिन्दी
            "th" -> "news"  // ไทย
            "vi" -> "tin-tuc"  // Tiếng Việt
            "id" -> "berita"  // Bahasa Indonesia
            "ms" -> "berita"  // Bahasa Melayu
            "tl" -> "balita"  // Tagalog
            "sw" -> "habari"  // Kiswahili
            else -> "news"
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        setupWebView()
        setupBackPressHandler()
        setupBottomNavigation()
        setupScrollListener()

        // Verificar si es el primer lanzamiento
        if (LanguagePreferences.isFirstLaunch(this)) {
            showLanguageSelectionDialog()
        } else {
            // Cargar el idioma guardado
            currentLanguageCode = LanguagePreferences.getLanguageCode(this) ?: ""
            // Cargar la página con el idioma seleccionado
            webView.loadUrl(getHomeUrl())
        }
    }

    private fun showLanguageSelectionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_language_selection, null)
        val spinner = dialogView.findViewById<Spinner>(R.id.languageSpinner)

        // Configurar el adapter del spinner
        val languageNames = SupportedLanguages.languages.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languageNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Pre-seleccionar el idioma actual
        val currentLanguagePosition = SupportedLanguages.languages.indexOfFirst { it.code == currentLanguageCode }
        if (currentLanguagePosition != -1) {
            spinner.setSelection(currentLanguagePosition)
        }

        val isFirstLaunch = LanguagePreferences.isFirstLaunch(this)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(!isFirstLaunch) // Solo cancelable si no es el primer lanzamiento
            .create()

        dialogView.findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            val selectedLanguage = SupportedLanguages.languages[selectedPosition]

            // Guardar el idioma seleccionado
            val languageChanged = selectedLanguage.code != currentLanguageCode
            currentLanguageCode = selectedLanguage.code
            LanguagePreferences.saveLanguage(this, selectedLanguage.code, selectedLanguage.name)
            LanguagePreferences.setFirstLaunchComplete(this)

            // Recargar la página con el idioma seleccionado si cambió
            if (languageChanged || isFirstLaunch) {
                webView.loadUrl(getHomeUrl())
            }

            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            if (isFirstLaunch) {
                // Si cancela en el primer lanzamiento, usar inglés por defecto
                currentLanguageCode = ""
                LanguagePreferences.saveLanguage(this, "", "English")
                LanguagePreferences.setFirstLaunchComplete(this)
                webView.loadUrl(getHomeUrl())
            }
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setupScrollListener() {
        webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && scrollY > 0) {
                // Scrolling hacia abajo - ocultar bottom navigation
                hideBottomNavigation()
            } else if (scrollY < oldScrollY) {
                // Scrolling hacia arriba - mostrar bottom navigation
                showBottomNavigation()
            }
            lastScrollY = scrollY
        }
    }

    private fun hideBottomNavigation() {
        bottomNavigationView.animate()
            .translationY(bottomNavigationView.height.toFloat() + 100f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(300)
            .start()
    }

    private fun showBottomNavigation() {
        bottomNavigationView.animate()
            .translationY(0f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(300)
            .start()
    }

    private fun detectLanguageChange(url: String) {
        // Extraer el código de idioma de la URL
        // Formato: https://www.jw.org/CODE/ o https://www.jw.org/CODE/...
        if (url.contains("jw.org")) {
            val regex = Regex("jw\\.org/([a-z]{2}(?:-[a-z]+)?)/")
            val matchResult = regex.find(url)

            val newLanguageCode = if (matchResult != null) {
                matchResult.groupValues[1]
            } else {
                // Si no hay código en la URL, es inglés (URL sin código)
                if (url.matches(Regex("https?://www\\.jw\\.org/?$")) ||
                    url.matches(Regex("https?://www\\.jw\\.org/en/.*"))) {
                    ""
                } else {
                    return // No es una URL válida para detectar idioma
                }
            }

            // Si el idioma cambió, actualizar las preferencias
            if (newLanguageCode != currentLanguageCode) {
                currentLanguageCode = newLanguageCode
                val language = SupportedLanguages.getLanguageByCode(newLanguageCode)
                if (language != null) {
                    LanguagePreferences.saveLanguage(this, language.code, language.name)
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    webView.loadUrl(getHomeUrl())
                    true
                }
                R.id.nav_news -> {
                    webView.loadUrl(getNewsUrl())
                    true
                }
                else -> false
            }
        }

        // Establecer el item de inicio como seleccionado
        bottomNavigationView.selectedItemId = R.id.nav_home
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                // Habilitar JavaScript para que la página funcione correctamente
                javaScriptEnabled = true
                domStorageEnabled = true

                // Configuración de cache agresiva basada en conectividad
                cacheMode = if (NetworkUtils.isNetworkAvailable(this@MainActivity)) {
                    LOAD_CACHE_ELSE_NETWORK
                } else {
                    LOAD_CACHE_ONLY
                }
                cacheMode = LOAD_CACHE_ELSE_NETWORK

                // Configuración para mejorar el rendimiento
                loadsImagesAutomatically = true
                mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE

                // Configuración PWA
                mediaPlaybackRequiresUserGesture = false
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)

                // Configuración adicional para mejor experiencia
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = false
                displayZoomControls = false

                // Habilitar almacenamiento local y session storage
                allowFileAccess = true
                allowContentAccess = true

                // Service Worker para PWA (requiere API 24+)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    safeBrowsingEnabled = true
                }
            }

            // WebViewClient personalizado para manejar la carga
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val url = request?.url?.toString() ?: return false

                    // Permitir navegación dentro del dominio jw.org
                    if (url.contains("jw.org")) {
                        view?.loadUrl(url)
                        return true
                    }

                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    // Detectar cambio de idioma en la URL
                    url?.let { detectLanguageChange(it) }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Página terminó de cargar
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    // Manejar error si es necesario
                }

                // Cargar recursos desde cache cuando sea posible
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    return super.shouldInterceptRequest(view, request)
                }
            }

            // WebChromeClient para soporte PWA completo
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    // No mostramos barra de progreso para mantener la UI limpia
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    // Actualizar título si es necesario
                }

                // Soporte para notificaciones y permisos PWA
                override fun onPermissionRequest(request: PermissionRequest?) {
                    request?.grant(request.resources)
                }
            }
        }
    }

    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        // Pausar el WebView para ahorrar recursos
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        // Reanudar el WebView
        webView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Limpiar el WebView
        webView.destroy()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_change_language -> {
                showLanguageSelectionDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

