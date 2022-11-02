rm ./frontend/src/main/resources/static/built/*.js || true
rm ./src/main/resources/static/built/bundle.js || true
cd ./frontend
npm install && npx webpack build
cd ..
cp ./frontend/src/main/resources/static/built/*.js ./src/main/resources/static/built/bundle.js
