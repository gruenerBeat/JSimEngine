./clean.sh
mkdir build
mkdir build/src
javac -sourcepath source/src -d build/src source/src/engine/logic/Main.java
cp -r source/resource build/resource
cd build
jar cvfe game.jar engine.logic.Main -C src .
echo "java -ea -jar game.jar" > run.sh
chmod +x run.sh
rm -rf src