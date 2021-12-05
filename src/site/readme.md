1、nginx配置
http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    tcp_nodelay         on;
    keepalive_timeout   65;
    types_hash_max_size 4096;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

    # Load modular configuration files from the /etc/nginx/conf.d directory.
    # See http://nginx.org/en/docs/ngx_core_module.html#include
    # for more information.
    include /etc/nginx/conf.d/*.conf;

     map $http_upgrade $connection_upgrade {
        default upgrade;
        '' close;
    }
 
    upstream websocket {
       server 192.168.33.1:18082;
    }
 
    upstream config {
       server 192.168.33.1:8080;
    }

    upstream html {
       server 192.168.33.1:1234;
    }

    server {
        listen 8020;
        location / {
            proxy_pass http://html;
        }

        location /cmdb {
            proxy_pass http://websocket;
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection $connection_upgrade;
            proxy_set_header Host $host;
        }

        location /config {
            proxy_pass http://config;
            proxy_cookie_path /config /;
        }



    }
    
2、部署位置
负载均衡134.64.116.90:8777转发到134.95.25.119:8777（通过老安全平台134.95.25.24下的SSH25119),程序部署在/home/cmdb/SafeShareDataService目录下
