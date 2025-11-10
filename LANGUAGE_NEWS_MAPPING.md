# Sistema de Mapeo de Idiomas para Noticias

## Descripción
La aplicación JW Unofficial implementa un sistema inteligente de cambio de idioma que modifica automáticamente las URLs de jw.org según el idioma seleccionado por el usuario.

## Funcionamiento

### Página de Inicio
Cuando el usuario selecciona un idioma, la URL de inicio cambia:
- **Inglés**: `https://www.jw.org/`
- **Español**: `https://www.jw.org/es/`
- **Português**: `https://www.jw.org/pt/`
- **Français**: `https://www.jw.org/fr/`
- Y así sucesivamente...

### Sección de Noticias
La sección de noticias utiliza traducciones específicas del segmento "news" según el idioma:

| Idioma | Código | Segmento de URL | URL Completa |
|--------|--------|----------------|--------------|
| English | `` (vacío) | news | `jw.org/en/news/` |
| Español | `es` | noticias | `jw.org/es/noticias/` |
| Português | `pt` | noticias | `jw.org/pt/noticias/` |
| Français | `fr` | actualites | `jw.org/fr/actualites/` |
| Deutsch | `de` | nachrichten | `jw.org/de/nachrichten/` |
| Italiano | `it` | notizie | `jw.org/it/notizie/` |
| Русский | `ru` | novosti | `jw.org/ru/novosti/` |
| 日本語 | `ja` | news | `jw.org/ja/news/` |
| 한국어 | `ko` | news | `jw.org/ko/news/` |
| 中文简体 | `zh-hans` | news | `jw.org/zh-hans/news/` |
| 中文繁體 | `zh-hant` | news | `jw.org/zh-hant/news/` |
| Polski | `pl` | aktualnosci | `jw.org/pl/aktualnosci/` |
| Română | `ro` | stiri | `jw.org/ro/stiri/` |
| Українська | `uk` | novyny | `jw.org/uk/novyny/` |
| Nederlands | `nl` | nieuws | `jw.org/nl/nieuws/` |
| Svenska | `sv` | nyheter | `jw.org/sv/nyheter/` |
| Norsk | `no` | nyheter | `jw.org/no/nyheter/` |
| Dansk | `da` | nyheder | `jw.org/da/nyheder/` |
| Suomi | `fi` | uutiset | `jw.org/fi/uutiset/` |
| Čeština | `cs` | zpravy | `jw.org/cs/zpravy/` |
| Magyar | `hu` | hirek | `jw.org/hu/hirek/` |
| Ελληνικά | `el` | nea | `jw.org/el/nea/` |
| Türkçe | `tr` | haberler | `jw.org/tr/haberler/` |
| العربية | `ar` | news | `jw.org/ar/news/` |
| עברית | `he` | news | `jw.org/he/news/` |
| हिन्दी | `hi` | news | `jw.org/hi/news/` |
| ไทย | `th` | news | `jw.org/th/news/` |
| Tiếng Việt | `vi` | tin-tuc | `jw.org/vi/tin-tuc/` |
| Bahasa Indonesia | `id` | berita | `jw.org/id/berita/` |
| Bahasa Melayu | `ms` | berita | `jw.org/ms/berita/` |
| Tagalog | `tl` | balita | `jw.org/tl/balita/` |
| Kiswahili | `sw` | habari | `jw.org/sw/habari/` |

## Persistencia del Idioma
El idioma seleccionado se guarda en `SharedPreferences` y permanece configurado hasta que:
1. El usuario cambie manualmente el idioma desde el menú de la aplicación
2. Se borren los datos de la aplicación desde la configuración del sistema

## Selección Inicial de Idioma
Al instalar la aplicación por primera vez, se muestra un diálogo que permite al usuario seleccionar su idioma preferido. Esta selección determinará:
- El idioma de la página principal de jw.org
- Las URLs específicas para cada sección (inicio, noticias, etc.)
- La interfaz de usuario permanecerá en el idioma del sistema

## Implementación Técnica
La función `getNewsSegmentForLanguage()` en `MainActivity.kt` gestiona el mapeo entre códigos de idioma y las traducciones correspondientes del segmento "news". Si un idioma no tiene una traducción específica disponible en jw.org, se usa "news" como valor por defecto.

