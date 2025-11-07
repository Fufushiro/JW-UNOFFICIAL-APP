# 📱 GUÍA DE USO - JW UNOFFICIAL

## Instalación Rápida

### Opción 1: Usar el script automático
```bash
./install.sh
```

### Opción 2: Manual
```bash
# Compilar
./gradlew assembleDebug

# Instalar (con dispositivo conectado)
./gradlew installDebug
```

## 🎯 Cómo Usar la App

### Primera vez
1. Abre la app "JW UNOFFICIAL"
2. Espera a que cargue https://www.jw.org/
3. La página se cacheará automáticamente
4. ¡Listo! Las siguientes aperturas serán instantáneas

### Navegación
- **Toca cualquier enlace**: Navega normalmente por jw.org
- **Botón Atrás**: Vuelve a la página anterior del historial
- **Botón Home**: Sale de la app (mantiene cache)

### Refrescar Contenido (2 swipes)
Para forzar una recarga completa y obtener contenido actualizado:

1. **Primer swipe**: Desliza el dedo hacia abajo desde la parte superior
   - No pasará nada, pero la app registra el intento
   
2. **Segundo swipe**: Dentro de 2 segundos, desliza hacia abajo nuevamente
   - Verás el indicador de carga giratorio
   - La página se recargará completamente sin usar cache
   - Se descargarán todas las actualizaciones del servidor

💡 **Tip**: Si solo haces 1 swipe, no se recarga nada. Necesitas 2 swipes rápidos consecutivos.

### Uso Offline
- Abre la app sin conexión a internet
- Todo el contenido previamente visitado estará disponible
- Podrás navegar por las páginas que ya hayas visto
- Los enlaces a páginas no visitadas no funcionarán

### Uso Normal (con conexión)
- La app carga la UI instantáneamente desde cache
- En segundo plano verifica si hay actualizaciones
- Si hay contenido nuevo, lo descarga sin recargar la pantalla
- ¡Experiencia súper rápida! ⚡

## 🔍 Resolución de Problemas

### La app no carga la página
- Verifica que tengas conexión a internet (la primera vez lo requiere)
- Cierra y abre la app nuevamente
- Si persiste, borra datos de la app y vuelve a abrirla

### El refresh no funciona
- Asegúrate de estar en la parte superior de la página
- Haz 2 swipes consecutivos dentro de 2 segundos
- Verifica que el primer swipe no sea muy lento

### La app va lenta
- Esto es raro, la app debería ser muy rápida
- Intenta hacer un refresh forzado (2 swipes)
- Verifica que tengas espacio de almacenamiento disponible

### Contenido desactualizado
- Haz 2 swipes hacia abajo para forzar recarga completa
- Esto descargará todo el contenido nuevo del servidor

## 📊 Funcionamiento Técnico

### Cache Inteligente
```
Primera apertura: Descarga → Cache → Muestra
Aperturas normales: Cache → Muestra → Actualiza en segundo plano
Sin conexión: Cache → Muestra (sin actualizar)
Con 2 swipes: Ignora cache → Descarga todo → Cache nuevo → Muestra
```

### Uso de Datos
- **Primera carga**: ~2-5 MB (depende del contenido de jw.org)
- **Aperturas posteriores**: ~50-200 KB (solo actualizaciones)
- **Modo offline**: 0 bytes (todo desde cache)
- **Refresh forzado**: ~2-5 MB (recarga completa)

### Almacenamiento
- Cache de WebView: ~20-50 MB (se limpia automáticamente si es necesario)
- Database local: ~5-10 MB (datos de DOM Storage)
- Total estimado: ~30-60 MB

## 🎨 Características de la UI

- **Sin barra superior**: La página se muestra a pantalla completa
- **Sin barra inferior**: No hay navegación adicional
- **Idéntica a la web**: Apariencia 100% igual a jw.org en navegador
- **StatusBar transparente**: Se integra con el contenido
- **Sin distracciones**: Solo ves el contenido de jw.org

## 🚀 Ventajas sobre usar el navegador

✅ **Más rápida**: Cache agresivo, carga instantánea
✅ **Funciona offline**: Contenido disponible sin conexión
✅ **Menos datos**: Solo descarga lo nuevo
✅ **App dedicada**: No se mezcla con otras pestañas del navegador
✅ **Icono en pantalla**: Acceso directo desde el launcher
✅ **Mejor experiencia**: Optimizada específicamente para jw.org

## 📋 Compatibilidad

- **Android mínimo**: Android 10 (API 29)
- **Android recomendado**: Android 11+ para mejor rendimiento
- **Espacio requerido**: ~100 MB (app + cache)
- **Conexión**: Requerida solo para primera carga y actualizaciones

## 💡 Tips y Trucos

1. **Primera carga completa**: La primera vez que abras la app, navega por varias secciones de jw.org para cachear más contenido

2. **Uso offline**: Si sabes que no tendrás conexión, explora primero el contenido que necesitarás offline

3. **Ahorro de datos**: La app automáticamente usa cache agresivo, ideal para planes de datos limitados

4. **Refresh periódico**: Haz refresh (2 swipes) una vez al día para mantener contenido actualizado

5. **Navegación rápida**: Usa el botón atrás del dispositivo en lugar de los enlaces "Atrás" de la página para navegación más rápida

## 🆘 Soporte

Para problemas o sugerencias:
- Revisa el archivo README.md
- Revisa IMPLEMENTATION_SUMMARY.md para detalles técnicos
- Verifica que tu versión de Android sea compatible (10+)

---

**¡Disfruta de una experiencia optimizada en jw.org! 🎉**

