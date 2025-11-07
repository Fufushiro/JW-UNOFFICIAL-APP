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

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        private const val HOME_URL = "https://www.jw.org/"
        private const val NEWS_URL = "https://www.jw.org/en/news/"
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

        // Cargar la pgina
        webView.loadUrl(HOME_URL)
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    webView.loadUrl(HOME_URL)
                    true
                }
                R.id.nav_news -> {
                    webView.loadUrl(NEWS_URL)
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
                    // Página iniciando carga
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
}

