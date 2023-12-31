#build frontend and docker image
pushd frontend/app
ng build
popd
pushd frontend/nginx
docker build -t nginx-with-angular .
popd
#build backend
pushd backend/
batch mvnw.cmd clean install -DskipTests
docker build -t spring-backend .
popd

