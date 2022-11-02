# faktura
[![Build Status](http://kozinaki.net:27080/api/badges/kozinaki/faktura/status.svg)](http://kozinaki.net:27080/kozinaki/faktura)

App for accounting orders and customers

Roadmap
- [x] Customers
  - [x] Create
  - [x] Delete
  - [ ] Update
  - [ ] Pagination
- [ ] Orders
  - [ ] Create
  - [ ] Delete
  - [ ] Update
  - [ ] Pagination
- [ ] Payments
  - [ ] Create
  - [ ] Delete
  - [ ] Update
  - [ ] Pagination
- [ ] Diagrams
- [ ] Auth

Get all customers
```bash
curl -X GET \
localhost:8080/api/v1/customers
```

Create customer
```bash
curl -X POST \
  localhost:8080/api/v1/customers \
  -d '{"name":"Nevermore, the Shadow Fiend","phone":"+79012345678","address":"Shadowraze, Necromastery, Presence of the Dark Lord, Requiem of Souls","email":"nevermore@dota2.com","inn":"5432106789","description":"So, you are curious where I come from? There is one easy way to find out for yourself"}' \
  -H "Content-Type: application/json"
```

Delete customer
```bash
curl -X DELETE \
localhost:8080/api/v1/customers \
-d '{"id":1}' \ 
-H "Content-Type: application/json"
```
