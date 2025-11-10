package ia.ankherth.jwunofficial

data class Language(
    val code: String,
    val name: String
)

object SupportedLanguages {
    val languages = listOf(
        Language("", "English"),
        Language("es", "Español"),
        Language("pt", "Português"),
        Language("fr", "Français"),
        Language("de", "Deutsch"),
        Language("it", "Italiano"),
        Language("ru", "Русский"),
        Language("ja", "日本語"),
        Language("ko", "한국어"),
        Language("zh-hans", "中文简体"),
        Language("zh-hant", "中文繁體"),
        Language("pl", "Polski"),
        Language("ro", "Română"),
        Language("uk", "Українська"),
        Language("nl", "Nederlands"),
        Language("sv", "Svenska"),
        Language("no", "Norsk"),
        Language("da", "Dansk"),
        Language("fi", "Suomi"),
        Language("cs", "Čeština"),
        Language("hu", "Magyar"),
        Language("el", "Ελληνικά"),
        Language("tr", "Türkçe"),
        Language("ar", "العربية"),
        Language("he", "עברית"),
        Language("hi", "हिन्दी"),
        Language("th", "ไทย"),
        Language("vi", "Tiếng Việt"),
        Language("id", "Bahasa Indonesia"),
        Language("ms", "Bahasa Melayu"),
        Language("tl", "Tagalog"),
        Language("sw", "Kiswahili")
    )

    fun getLanguageByCode(code: String?): Language? {
        return languages.find { it.code == code }
    }
}

