
docker ps 
doccker ps -a

docker run   --name webserver -i -t  atppath/nodejs  /bin/bash
docker run -p 3000:3000  --name webserver -t  atppath/nodejs  nodejs nodejsapp/server.js
docker commit 0379cb4126bf7776206c1d829edc00620c2a9c9318b8892737ca269234c77068 atppath/nodejs
docker rm webserver
