find src/ | grep .java | xargs javac -d ./bin/ -cp .:lib/*
cd ./bin;java -cp .:../lib/* src.GSMain;cd ..
exec bash
