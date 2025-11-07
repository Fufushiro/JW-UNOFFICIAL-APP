#!/bin/bash

# Script de instalación rápida para JW UNOFFICIAL

echo "🚀 JW UNOFFICIAL - Script de Instalación"
echo "========================================"
echo ""

# Verificar que estamos en el directorio correcto
if [ ! -f "settings.gradle.kts" ]; then
    echo "❌ Error: Ejecuta este script desde la raíz del proyecto"
    exit 1
fi

echo "📦 Paso 1: Limpiando proyecto..."
./gradlew clean

echo ""
echo "🔨 Paso 2: Compilando APK debug..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ ¡Build exitoso!"
    echo ""
    echo "📱 APK generado en:"
    echo "   app/build/outputs/apk/debug/app-debug.apk"
    echo ""

    # Verificar si hay un dispositivo conectado
    if command -v adb &> /dev/null; then
        DEVICES=$(adb devices | grep -w "device" | wc -l)

        if [ $DEVICES -gt 0 ]; then
            echo "📲 Dispositivo Android detectado"
            read -p "¿Deseas instalar la app ahora? (s/n): " -n 1 -r
            echo ""
            if [[ $REPLY =~ ^[Ss]$ ]]; then
                echo "⏳ Instalando..."
                ./gradlew installDebug

                if [ $? -eq 0 ]; then
                    echo "✅ ¡App instalada exitosamente!"
                    echo ""
                    echo "🎉 Puedes abrir la app 'JW UNOFFICIAL' en tu dispositivo"
                else
                    echo "❌ Error al instalar"
                fi
            fi
        else
            echo "ℹ️  No hay dispositivos Android conectados"
            echo "   Conecta un dispositivo o inicia un emulador e instala con:"
            echo "   ./gradlew installDebug"
        fi
    fi

    echo ""
    echo "📚 Características implementadas:"
    echo "   ✅ WebView optimizado para jw.org"
    echo "   ✅ Pull-to-refresh con doble swipe"
    echo "   ✅ Cache agresivo para carga rápida"
    echo "   ✅ Funcionamiento parcial offline"
    echo "   ✅ UI idéntica a la web original"
    echo "   ✅ Compatible con PWA"
    echo ""
    echo "📖 Lee README.md para más información"

else
    echo ""
    echo "❌ Error en la compilación"
    echo "   Revisa los logs arriba para más detalles"
    exit 1
fi

