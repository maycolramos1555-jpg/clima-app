# Usamos un servidor web ligero (Nginx) para servir archivos estáticos
FROM nginx:alpine

# Copiamos el index.html de nuestra carpeta frontend a la carpeta que Nginx expone por defecto
COPY index.html /usr/share/nginx/html/index.html

# Exponemos el puerto 80 (el puerto HTTP estándar)
EXPOSE 80
