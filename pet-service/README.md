# Сервис для работы с животными

## Для чего?

Сервис для работы с животными

---

## API

Api предоставляет CRUD  
1) Получить всех своих животных:
```http request
GET /api/v1/pet-service/owners/{owner_name}/pets
```

2) Получить свое животное по id:
```http request
GET /api/v1/pet-service/owners/{owner_name}/pets/{pet_id}
```

3) Создать животное:
```http request
POST /api/v1/pet-service/owners/{owner_name}/pets
```

Тело запроса:
```json
{
  "name": "myrka",
  "type": "CAT",
  "gender": "FEMALE",
  "birthday": "2002-08-25"
}
```

4) Изменить животное по id:
```http request
PUT /api/v1/pet-service/owners/{owner_name}/pets/{pet_id}
```

Тело запроса:
```json
{
  "name": "Myrka",
  "type": "CAT",
  "gender": "FEMALE",
  "birthday": "2002-08-25"
}
```

5) Удалить животное по id:
```http request
DELETE /api/v1/pet-service/owners/{owner_name}/pets/{pet_id}
```

---