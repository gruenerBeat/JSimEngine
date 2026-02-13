mkdir build
mkdir build/src
javac -sourcepath source/src -d build/src source/src/engine/engine.java
cp -r source/resource build/resource
cd build
jar cvfe game.jar engine.engine -C src .
rm -rf src