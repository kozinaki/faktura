curl -X GET \
	localhost:8080/api/v1/customers

curl -X POST \
	localhost:8080/api/v1/customers \
	-d '{"id":2,"name":"Frodo","phone":"+79523566533","address":"Russia, Dagestan, Makhachkala, Kalinina d. 22, kv. 42","email":"lakec3.14@yandex.ru","inn":"1234567890","description":"Hello World!"}' -H "Content-Type: application/json"

curl -X DELETE \
	localhost:8080/api/v1/customers \
	-d '{"id":1}' -H "Content-Type: application/json"
