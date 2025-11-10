# Sistema de Selección de Idioma - JW Unofficial

## 📋 Descripción General

Se ha implementado un sistema completo de selección de idioma que permite a los usuarios elegir su idioma preferido para navegar en jw.org. El idioma seleccionado se mantiene persistente hasta que se borren los datos de la aplicación.

## ✨ Características Implementadas

### 1. **Selección de Idioma al Primer Lanzamiento**
- Al abrir la app por primera vez, se muestra un diálogo elegante para seleccionar el idioma
- Incluye 32 idiomas soportados nativamente por jw.org
- El diálogo no se puede cerrar hasta que se seleccione un idioma (primer lanzamiento)

### 2. **Persistencia del Idioma**
- El idioma seleccionado se guarda en SharedPreferences
- Se mantiene entre sesiones de la app
- Solo se borra cuando el usuario elimina los datos de la aplicación

### 3. **URLs Dinámicas Basadas en Idioma**
- **Inglés (predeterminado)**: `https://www.jw.org/`
- **Español**: `https://www.jw.org/es/`
- **Portugués**: `https://www.jw.org/pt/`
- Y así sucesivamente para todos los idiomas soportados

### 4. **Detección Automática de Cambio de Idioma**
- Si el usuario cambia el idioma desde la propia página de jw.org
- La app detecta automáticamente el cambio en la URL
- Actualiza las preferencias guardadas
- Las siguientes navegaciones usarán el nuevo idioma

### 5. **Opción de Cambiar Idioma Manual**
- Menú de opciones (⋮) en la parte superior derecha
- Opción "Cambiar idioma" disponible en cualquier momento
- Muestra el mismo diálogo con el idioma actual pre-seleccionado
- Al confirmar, recarga la página con el nuevo idioma

## 🌍 Idiomas Soportados

La aplicación soporta 32 idiomas principales:

- **Inglés** (English) - Por defecto
- **Español** (Español)
- **Portugués** (Português)
- **Francés** (Français)
- **Alemán** (Deutsch)
- **Italiano** (Italiano)
- **Ruso** (Русский)
- **Japonés** (日本語)
- **Coreano** (한국어)
- **Chino Simplificado** (中文简体)
- **Chino Tradicional** (中文繁體)
- **Polaco** (Polski)
- **Rumano** (Română)
- **Ucraniano** (Українська)
- **Holandés** (Nederlands)
- **Sueco** (Svenska)
- **Noruego** (Norsk)
- **Danés** (Dansk)
- **Finlandés** (Suomi)
- **Checo** (Čeština)
- **Húngaro** (Magyar)
- **Griego** (Ελληνικά)
- **Turco** (Türkçe)
- **Árabe** (العربية)
- **Hebreo** (עברית)
- **Hindi** (हिन्दी)
- **Tailandés** (ไทย)
- **Vietnamita** (Tiếng Việt)
- **Indonesio** (Bahasa Indonesia)
- **Malayo** (Bahasa Melayu)
- **Tagalo** (Tagalog)
- **Suajili** (Kiswahili)

## 🔧 Archivos Creados/Modificados

### Archivos Nuevos:
1. `LanguagePreferences.kt` - Gestión de preferencias de idioma
2. `SupportedLanguages.kt` - Lista de idiomas soportados
3. `dialog_language_selection.xml` - UI del diálogo de selección
4. `main_menu.xml` - Menú de opciones
5. `strings.xml` - Strings de recursos

### Archivos Modificados:
1. `MainActivity.kt` - Lógica principal de selección y navegación

## 📱 Flujo de Usuario

### Primera Vez:
1. Usuario abre la app
2. Se muestra diálogo de selección de idioma
3. Usuario selecciona su idioma preferido
4. Se carga jw.org en el idioma seleccionado
5. El idioma se guarda permanentemente

### Uso Normal:
1. Usuario abre la app
2. Se carga automáticamente en el idioma guardado
3. Navega normalmente

### Cambio de Idioma:
**Opción 1 - Desde la app:**
1. Usuario toca menú (⋮)
2. Selecciona "Cambiar idioma"
3. Elige nuevo idioma
4. Página se recarga con el nuevo idioma

**Opción 2 - Desde jw.org:**
1. Usuario cambia idioma en la página web
2. La app detecta el cambio automáticamente
3. Guarda el nuevo idioma
4. Futuras navegaciones usan el nuevo idioma

## 💾 Persistencia de Datos

Los datos se almacenan en `SharedPreferences`:
- **Clave**: `language_prefs`
- **Datos guardados**:
  - `language_code`: Código del idioma (ej: "es", "pt", "fr")
  - `language_name`: Nombre del idioma (ej: "Español", "Português")
  - `first_launch`: Bandera de primer lanzamiento

## 🗑️ Borrar Datos

Para resetear el idioma y volver al diálogo inicial:
1. Ir a Ajustes del dispositivo
2. Aplicaciones → JW Unofficial
3. Almacenamiento → Borrar datos
4. Al abrir la app nuevamente, aparecerá el diálogo de selección

## 🎨 Diseño del Diálogo

- **Estilo**: Material Design
- **Componentes**: 
  - Título explicativo
  - Spinner con lista de idiomas
  - Botón "Confirmar"
  - Botón "Cancelar" (solo después del primer lanzamiento)
- **Comportamiento**: 
  - No cancelable en primer lanzamiento
  - Cancelable en cambios posteriores

## 🔄 Integración con BottomNavigationView

El BottomNavigationView ahora navega usando URLs dinámicas:
- **Botón Inicio**: Carga `jw.org/[código-idioma]/`
- **Botón Noticias**: Carga `jw.org/[código-idioma]/news/`

## ✅ Ventajas de la Implementación

1. ✨ **Experiencia de Usuario Mejorada**: Idioma nativo desde el inicio
2. 💾 **Persistencia Total**: No se pierde la configuración
3. 🔄 **Detección Automática**: Sincronización con cambios en la web
4. 🎯 **Flexibilidad**: Cambio de idioma en cualquier momento
5. 🌍 **Amplia Cobertura**: 32 idiomas principales
6. 📱 **Integración Completa**: Funciona con toda la navegación de la app

## 🚀 Compilación Exitosa

El proyecto ha sido compilado exitosamente con todas las nuevas características implementadas.

