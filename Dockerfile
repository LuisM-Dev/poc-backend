FROM open-liberty:webProfile8
EXPOSE 9443
COPY /src/main/conf/lib/* /config/lib/
COPY /src/main/conf/resources/security/ /config/resources/security/
COPY /src/main/conf/mpopenapi/ /config/mpopenapi/
COPY /src/main/conf/server.xml /config/
COPY /target/or-persist-api.war /config/apps/
COPY /target/newrelic/newrelic.jar /config/apps/
COPY /newrelic/newrelic.yml /config/apps/