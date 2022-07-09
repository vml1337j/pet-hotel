# Pet hotel backend

## Для чего?

Предоставляет базовую функциональность приложения

---

## API

**Возможности для неавторизованных пользователей**

1) Проверка доступности логина:
 ```http request
GET /validation?username=qwerty
```

2) Регистрация:
 ```http request
POST /registration
```

Тело запроса:
```json
{
  "username": "qwerty",
  "password": "123"
}
```

3) Авторизация:
```http request
POST /auth
```

Тело запроса:
```json
{
  "username": "qwerty",
  "password": "123"
}
```

---

**Для работы с животными необходимо быть авторизованным** 

1) Получение всех своих животных:
```http request
GET /pets
```

2) Получение свое животное по id:
```http request
GET /pets/{pet_id}
```

3) Создать животное:
```http request
POST /pets
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
PUT /pets/{pet_id}
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
DELETE /pets/{pet_id}
```

---

И так же logout
```http request
POST /logout
```

---