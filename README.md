
1. Clonar o abrir el proyecto en Android Studio
2. Sincronizar Gradle
3. Conectar un dispositivo o emulador Android
4. Ejecutar la aplicación

### Build desde Terminal

```bash
# Build Debug APK
./gradlew assembleDebug

# El APK se genera en:
# app/build/outputs/apk/debug/app-debug.apk

# Instalar en dispositivo conectado
./gradlew installDebug
```

## Uso

1. **Primera carga**: La app descargará y cacheará el contenido de jw.org
2. **Aperturas posteriores**: Carga instantánea desde cache con actualización en segundo plano
3. **Refrescar**: Desliza hacia abajo 2 veces consecutivas para forzar recarga completa
4. **Navegación**: Usa el botón atrás para navegar por el historial
5. **Offline**: El contenido previamente cargado seguirá disponible

## Notas Técnicas

### Cache Mode
- Por defecto: `LOAD_CACHE_ELSE_NETWORK`
- Durante refresh forzado: `LOAD_NO_CACHE` (temporalmente)
- La cache se restaura automáticamente después de 1 segundo

### Lifecycle
- `onPause()`: Pausa el WebView para ahorrar recursos
- `onResume()`: Reanuda el WebView
- `onDestroy()`: Limpia el WebView correctamente

### Seguridad
- SSL/TLS habilitado
- Safe Browsing activado
- Cleartext traffic deshabilitado
- Solo navegación dentro de dominios jw.org

## Mejoras Futuras Posibles

- [ ] Modo oscuro automático
- [ ] Descarga de contenido para offline completo
- [ ] Notificaciones push
- [ ] Widget de pantalla de inicio
- [ ] Compartir contenido
- [ ] Favoritos locales

## Licencia

Este proyecto es no oficial y no está afiliado con jw.org

## Desarrollador

Creado para facilitar el acceso móvil optimizado a jw.org
# JW UNOFFICIAL - WebApp para JW.ORG

Una aplicación Android optimizada que funciona como Progressive Web App (PWA) para acceder a https://www.jw.org/

## Características

### ✨ Funcionalidades Principales

- **WebView Optimizado**: Carga y muestra el contenido de jw.org de manera nativa
- **Pull-to-Refresh Inteligente**: Requiere 2 swipes hacia abajo consecutivos (dentro de 2 segundos) para refrescar completamente la página
- **Cache Agresivo**: Sistema de caché mejorado que almacena contenido localmente para:
  - Carga instantánea de la UI
  - Reducción del consumo de datos
  - Funcionamiento parcial offline
  - Solo se actualizan los datos nuevos del servidor

### 🚀 Optimizaciones de Rendimiento

- **Carga Asíncrona**: La UI se carga instantáneamente desde la cache mientras los datos se actualizan en segundo plano
- **Service Workers**: Soporte para PWA con service workers (Android 7.0+)
- **DOM Storage**: Almacenamiento local y de sesión habilitado
- **Hardware Acceleration**: Aceleración por hardware activada para mejor rendimiento

### 🎨 Interfaz de Usuario

- **Sin Elementos Extra**: No hay barras de navegación, toolbars ni elementos UI adicionales
- **Apariencia Original**: La página se muestra exactamente como en el navegador
- **StatusBar Transparente**: Barra de estado transparente para experiencia inmersiva
- **Sin ActionBar**: Tema NoActionBar para mantener la UI limpia

### 📱 Compatibilidad

- **Android Mínimo**: Android 10 (API 29)
- **Android Target**: Android 14 (API 36)
- **Navegación WebView**: Botón atrás navega por el historial del WebView
- **Orientación**: Maneja cambios de orientación sin recargar

## Cómo Funciona

### Sistema de Cache

La aplicación utiliza `LOAD_CACHE_ELSE_NETWORK` que:
1. Primero intenta cargar desde la cache local
2. Si no hay cache o está desactualizada, carga desde la red
3. Los recursos se guardan automáticamente en la cache del dispositivo

### Pull-to-Refresh

- **Primer swipe**: No hace nada, solo cuenta
- **Segundo swipe (dentro de 2 segundos)**: Fuerza una recarga completa ignorando la cache
- Después de la recarga, el sistema vuelve al modo de cache normal

### Offline

- La app puede mostrar contenido previamente cargado cuando no hay conexión
- Los recursos estáticos (CSS, JS, imágenes) se almacenan localmente
- La navegación por páginas ya visitadas funciona offline

## Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/ia/ankherth/jwunofficial/
│   │   └── MainActivity.kt          # Activity principal con WebView
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml    # Layout con SwipeRefreshLayout + WebView
│   │   └── values/
│   │       └── themes.xml           # Tema sin ActionBar
│   └── AndroidManifest.xml          # Configuración de permisos y activity
```

## Configuración Técnica

### Permisos
- `INTERNET`: Para acceder a jw.org
- `ACCESS_NETWORK_STATE`: Para detectar conectividad

### WebView Settings
- JavaScript habilitado
- DOM Storage habilitado
- Mixed Content Mode: Compatibility
- Wide Viewport habilitado
- Zoom deshabilitado
- File Access habilitado
- Safe Browsing habilitado (Android 7.0+)

## Instalación

### Requisitos
- Android Studio Hedgehog o superior
- JDK 11 o superior
- SDK de Android con API 29-36

### Pasos

