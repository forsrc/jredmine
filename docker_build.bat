docker build -t forsrc/jredmine:ui jredmine-ui/
docker build -t forsrc/jredmine:server jredmine-server/


docker push forsrc/jredmine:ui 
docker push forsrc/jredmine:server 