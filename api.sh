curl -X GET \
	localhost:8080/api/v1/customers

curl -X POST \
  localhost:8080/api/v1/customers \
  -d '{"name":"Nevermore, the Shadow Fiend","phone":"+79012345678","address":"Shadowraze, Necromastery, Presence of the Dark Lord, Requiem of Souls","email":"nevermore@dota2.com","inn":"5432106789","description":"So, you are curious where I come from? There is one easy way to find out for yourself"}' \
  -H "Content-Type: application/json"

curl -X PUT \
  localhost:8080/api/v1/customers \
  -d '{"id":"4", "name":"Nevermore, the Shadow Fiend","phone":"+79012345678","address":"Shadowraze, Necromastery, Presence of the Dark Lord, Requiem of Souls","email":"nevermore@dota2.com","inn":"5432106789","description":"So, you are curious where I come from? There is one easy way to find out for yourself"}' \
  -H "Content-Type: application/json"

curl -X DELETE \
	localhost:8080/api/v1/customers \
	-d '{"id":1}' -H "Content-Type: application/json"
