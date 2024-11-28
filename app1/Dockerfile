FROM nginx:alpine
COPY index.html /usr/share/nginx/html/
# Copy the Nginx configuration file
COPY ./nginx.conf /etc/nginx/nginx.conf
#COPY nginx.conf /etc/nginx/nginx.conf

#RUN mkdir -p /etc/nginx/certs && \
#    openssl req -x509 -newkey rsa:4096 -keyout /etc/nginx/certs/key.pem -out /etc/nginx/certs/cert.pem \
#    -days 364 -nodes -subj "/CN=serverurl"

# Copy existing certificates into the container
#COPY ./certs/* /etc/nginx/certs/

