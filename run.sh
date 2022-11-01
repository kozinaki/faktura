rm ./frontend/src/main/resources/static/built/*.js
rm ./src/main/resources/static/built/bundle.js
cd ./frontend
npm install && npx webpack build
cd ..
cp ./frontend/src/main/resources/static/built/*.js ./src/main/resources/static/built/bundle.js
