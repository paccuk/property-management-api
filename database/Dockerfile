FROM postgres:15-alpine

COPY init-scripts /docker-entrypoint-initdb.d/

ADD entrypoint.sh /
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]