# 🚀 COMANDOS ÚTILES - REFERENCIA RÁPIDA

## Compilación y Build

```bash
# Limpiar proyecto
./gradlew clean

# Compilar APK Debug
./gradlew assembleDebug

# Compilar APK Release
./gradlew assembleRelease

# Build completo (debug + release + tests)
./gradlew build

# Build más rápido (sin tests)
./gradlew assembleDebug --parallel
```

## Instalación

```bash
# Instalar APK debug en dispositivo conectado
./gradlew installDebug

# Desinstalar app del dispositivo
./gradlew uninstallDebug

# Desinstalar e instalar de nuevo
./gradlew uninstallDebug installDebug

# Script automático de instalación
./install.sh
```

## ADB (Android Debug Bridge)

```bash
# Ver dispositivos conectados
adb devices

# Instalar APK manualmente
adb install app/build/outputs/apk/debug/app-debug.apk

# Reinstalar (sobreescribir)
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Desinstalar app
adb uninstall ia.ankherth.jwunofficial

# Abrir app
adb shell am start -n ia.ankherth.jwunofficial/.MainActivity

# Forzar cierre de app
adb shell am force-stop ia.ankherth.jwunofficial

# Limpiar datos de la app
adb shell pm clear ia.ankherth.jwunofficial
```

## Logs y Debugging

```bash
# Ver todos los logs
adb logcat

# Limpiar logs y ver nuevos
adb logcat -c && adb logcat

# Filtrar por app
adb logcat | grep "ia.ankherth.jwunofficial"

# Ver solo errores
adb logcat *:E

# Ver logs del WebView
adb logcat | grep -E "(WebView|chromium)"

# Guardar logs a archivo
adb logcat > logs.txt
```

## Cache y Almacenamiento

```bash
# Ver tamaño de cache de la app
adb shell du -sh /data/data/ia.ankherth.jwunofficial/cache

# Ver tamaño total de la app
adb shell du -sh /data/data/ia.ankherth.jwunofficial

# Limpiar cache (sin borrar datos)
adb shell pm clear ia.ankherth.jwunofficial

# Ver archivos de cache
adb shell ls -lh /data/data/ia.ankherth.jwunofficial/cache
```

## Información de la App

```bash
# Ver información del paquete
adb shell dumpsys package ia.ankherth.jwunofficial | head -50

# Ver permisos otorgados
adb shell dumpsys package ia.ankherth.jwunofficial | grep permission

# Ver si la app está corriendo
adb shell ps | grep ia.ankherth.jwunofficial

# Ver uso de memoria
adb shell dumpsys meminfo ia.ankherth.jwunofficial
```

## APK Management

```bash
# Ver ubicación del APK
ls -lh app/build/outputs/apk/debug/

# Copiar APK a escritorio
cp app/build/outputs/apk/debug/app-debug.apk ~/Desktop/

# Obtener información del APK
aapt dump badging app/build/outputs/apk/debug/app-debug.apk

# Extraer APK instalado del dispositivo
adb pull $(adb shell pm path ia.ankherth.jwunofficial | cut -d':' -f2) app-from-device.apk
```

## Gradle Útiles

```bash
# Ver dependencias del proyecto
./gradlew app:dependencies

# Ver tareas disponibles
./gradlew tasks

# Build con más información
./gradlew assembleDebug --info

# Build con debugging completo
./gradlew assembleDebug --debug

# Ver tiempo de cada tarea
./gradlew assembleDebug --profile

# Sincronizar Gradle
./gradlew --refresh-dependencies
```

## Testing

```bash
# Ejecutar tests unitarios
./gradlew test

# Ejecutar tests instrumentados (requiere dispositivo)
./gradlew connectedAndroidTest

# Ver reporte de tests
open app/build/reports/tests/testDebugUnitTest/index.html
```

## Release Build

```bash
# Generar APK release (sin firma)
./gradlew assembleRelease

# Ubicación del APK release
ls -lh app/build/outputs/apk/release/
```

## Emulador

```bash
# Listar emuladores disponibles
emulator -list-avds

# Iniciar emulador específico
emulator -avd Pixel_5_API_33 &

# Iniciar emulador con borrado de datos
emulator -avd Pixel_5_API_33 -wipe-data &
```

## Troubleshooting

```bash
# Matar servidor ADB y reiniciar
adb kill-server && adb start-server

# Reiniciar dispositivo
adb reboot

# Verificar estado de Gradle daemon
./gradlew --status

# Detener todos los daemons de Gradle
./gradlew --stop

# Limpiar cache de Gradle
rm -rf ~/.gradle/caches/

# Limpiar completamente y rebuild
./gradlew clean cleanBuildCache && ./gradlew assembleDebug
```

## Permisos (si se agregan en el futuro)

```bash
# Otorgar permiso manualmente
adb shell pm grant ia.ankherth.jwunofficial android.permission.PERMISSION_NAME

# Revocar permiso
adb shell pm revoke ia.ankherth.jwunofficial android.permission.PERMISSION_NAME

# Ver permisos de internet (ya otorgado)
adb shell dumpsys package ia.ankherth.jwunofficial | grep "android.permission.INTERNET"
```

## Network y Conectividad

```bash
# Deshabilitar WiFi en el dispositivo
adb shell svc wifi disable

# Habilitar WiFi
adb shell svc wifi enable

# Deshabilitar datos móviles
adb shell svc data disable

# Habilitar datos móviles
adb shell svc data enable

# Ver estado de conectividad
adb shell dumpsys connectivity
```

## Screenshots y Video

```bash
# Tomar screenshot
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png

# Grabar video (CTRL+C para detener)
adb shell screenrecord /sdcard/video.mp4

# Grabar video con límite de tiempo (30 segundos)
adb shell screenrecord --time-limit 30 /sdcard/video.mp4

# Descargar video
adb pull /sdcard/video.mp4
```

## Comandos One-Liner Útiles

```bash
# Clean, build e install en un comando
./gradlew clean assembleDebug installDebug

# Build y abrir app automáticamente
./gradlew installDebug && adb shell am start -n ia.ankherth.jwunofficial/.MainActivity

# Verificar build y mostrar tamaño del APK
./gradlew assembleDebug && ls -lh app/build/outputs/apk/debug/app-debug.apk

# Logs en tiempo real solo de la app
adb logcat | grep --line-buffered "ia.ankherth.jwunofficial"

# Reinstalar y ver logs
./gradlew uninstallDebug installDebug && adb shell am start -n ia.ankherth.jwunofficial/.MainActivity && adb logcat | grep "ia.ankherth.jwunofficial"
```

## Archivos del Proyecto

```bash
# Ver estructura del proyecto
tree -L 3 -I 'build|.gradle'

# Buscar archivos Kotlin
find . -name "*.kt" -type f

# Contar líneas de código
find app/src/main/java -name "*.kt" | xargs wc -l

# Ver tamaño del proyecto
du -sh .
```

## Git (para control de versiones)

```bash
# Inicializar Git (si no está)
git init

# Agregar archivos
git add .

# Commit
git commit -m "Initial commit: JW UNOFFICIAL app"

# Ver cambios
git status

# Ver historial
git log --oneline
```

---

## 🔥 Comandos Más Usados (Top 10)

1. `./gradlew assembleDebug` - Compilar APK
2. `./gradlew installDebug` - Instalar en dispositivo
3. `adb logcat | grep jwun` - Ver logs
4. `./gradlew clean` - Limpiar proyecto
5. `adb devices` - Ver dispositivos conectados
6. `adb install -r app/build/outputs/apk/debug/app-debug.apk` - Reinstalar APK
7. `./gradlew uninstallDebug installDebug` - Reinstalar
8. `adb shell pm clear ia.ankherth.jwunofficial` - Limpiar datos
9. `./install.sh` - Instalación automática
10. `adb shell am start -n ia.ankherth.jwunofficial/.MainActivity` - Abrir app

---

**💡 Tip**: Guarda este archivo como referencia rápida para desarrollo!

