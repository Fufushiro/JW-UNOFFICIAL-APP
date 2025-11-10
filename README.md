# JW UNOFFICIAL

**Aplicación Android nativa** para acceder a jw.org con funcionalidades optimizadas.

![Version](https://img.shields.io/badge/version-1.0.2-blue.svg)
![Android](https://img.shields.io/badge/Android-10%2B-green.svg)
![License](https://img.shields.io/badge/license-MIT-orange.svg)

---

## 🚀 Características Principales

### 🌍 Sistema Multi-Idioma
- **32 idiomas soportados** con selector intuitivo al primer uso
- **Persistencia automática** del idioma seleccionado
- **Cambio manual** desde el menú en cualquier momento
- **Detección automática** cuando cambias idioma desde la web
- URLs dinámicas según idioma (ej: `/es/`, `/pt/`, `/fr/`)

### 📱 Navegación
- **Barra inferior** con acceso rápido a:
  - 🏠 **Inicio**: Página principal de jw.org
  - 📰 **Noticias**: Últimas noticias de jw.org
- **Botón Atrás** funcional para historial de navegación
- **UI limpia** sin elementos extra, idéntica a la web

### ⚡ Caché Inteligente
- **Carga instantánea** desde caché en aperturas posteriores
- **Funcionamiento offline** con contenido visitado previamente
- **Actualización en segundo plano** sin recargar la UI
- **Detección automática de red** para optimizar estrategia de caché
- **Sistema de doble swipe** para forzar actualización completa

### 🔧 PWA y Optimizaciones
- **Service Workers** habilitados para funcionalidad PWA
- **Hardware acceleration** para mejor rendimiento
- **Safe Browsing** habilitado (Android 7.0+)
- **Gestión óptima de memoria** y ciclo de vida
- **Sin barra de estado superpuesta** - interfaz perfectamente alineada

---

## 📥 Instalación

### Requisitos
- **Android 10** (API 29) o superior
- Conexión a Internet (solo primera vez)

### Instalación Automática
```bash
./install.sh
```

### Instalación Manual
```bash
# Compilar
./gradlew assembleDebug

# Instalar (con dispositivo conectado)
./gradlew installDebug
```

---

## 💡 Uso

### Primera Vez
1. Abre la aplicación
2. Selecciona tu idioma preferido del diálogo
3. Navega normalmente - todo se cacheará automáticamente

### Navegación Diaria
- Usa la **barra inferior** para cambiar entre Inicio y Noticias
- Navega por enlaces normalmente dentro de jw.org
- **Botón Atrás** para volver a páginas anteriores

### Actualizar Contenido
**Doble swipe** (desliza 2 veces hacia abajo en menos de 2 segundos):
- Fuerza recarga completa sin caché
- Descarga contenido actualizado del servidor

### Cambiar Idioma
1. Toca **⋮** (menú superior derecho)
2. Selecciona **"Cambiar idioma"**
3. Elige el nuevo idioma
4. La página se recargará automáticamente

### Modo Offline
- Todo el contenido visitado está disponible sin conexión
- Navega por páginas ya cacheadas
- Enlaces a contenido no visitado no funcionarán

---

## 🛠️ Tecnologías

- **Lenguaje**: Kotlin
- **Min SDK**: Android 10 (API 29)
- **Target SDK**: Android 14 (API 36)
- **Componentes principales**:
  - AndroidX Core KTX
  - Material Design Components
  - WebKit 1.11.0
  - ViewBinding

### Permisos
- `INTERNET` - Acceso a contenido web
- `ACCESS_NETWORK_STATE` - Detección de conectividad

---

## 📚 Documentación Adicional

- [CHANGELOG.md](CHANGELOG.md) - Historial detallado de versiones
- [USER_GUIDE.md](USER_GUIDE.md) - Guía completa de uso
- [LANGUAGE_SYSTEM.md](LANGUAGE_SYSTEM.md) - Sistema de idiomas
- [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - Detalles técnicos

---

## 📝 Versión Actual: 1.0.2

### Mejoras Recientes
- ✅ Sistema multi-idioma completo (32 idiomas)
- ✅ Barra de navegación inferior
- ✅ Caché inteligente optimizado
- ✅ Corrección de superposición de UI
- ✅ Detección automática de cambios de idioma
- ✅ Menú de opciones con cambio manual de idioma

---

## 📄 Licencia

Este proyecto es de código abierto bajo licencia MIT.

## ⚠️ Disclaimer

Esta es una aplicación **no oficial** y no está afiliada, asociada, autorizada, respaldada o de ninguna manera oficialmente conectada con jw.org o cualquiera de sus subsidiarias o afiliados.

---

**Desarrollado con ❤️ para la comunidad**

