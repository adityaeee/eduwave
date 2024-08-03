# EduWave

Develope a school fee payment system that facilitates quick, secure, and efficient transactions for students, parents, and school administrators. The system aims to reduce administrative workload, enhance payment tracking, improve user satisfaction, and reduce time on payment process.


## Technologies Used
- **Programming Language:** Java 17
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Image Storage Service:** ImageKit (REST client)
- **Payment Gateway:** Midtrans
# API Reference


## Auth
### Login 

- **Endpoint**
```http
  POST /api/v1/auth/login
```

- **Request**
```json
{
    "username": "your_username",
    "password": "your_password"
}
```

- **Response**
```json
{
    "statusCode": 202,
    "message": "You have successfully logged in",
    "data": {
        "username": "your_username",
        "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJlZHV3YXZlIiwiaWF0IjoxNzIyNjg1MjE2LCJleHAiOjE3MjI5NDQ0MTYsInN1YiI6IjEyYjM5MjI1LTFmNTYtNDYzYi04NzE3LTNlNjg3MWFlZDM2OSIsInJvbGVzIjpbIlJPTEVfU0VLT0xBSCJdfQ.2smXvcwZF4wm3bKzZXARx8BVNzX45NaetU__IJdaz5OCi_mCvoIRuMO0x7-nefImuBxJ7IrXU6V--m37CJ87Sg",
        "roles": [
            "ROLE_SEKOLAH"
        ]
    }
}
```



## Sekolah

### Create Sekolah
- **Endpoint**
```http
  POST /api/v1/sekolah
```

- **Request** \
Content-Type : multipart/form-data;

| Parameter         | Type   | Description                          |
|:------------------|:-------|:-------------------------------------|
| `sekolah_request` | `text` | **Required**. Json to create sekolah |
| `logo`            | `file` | image for sekolah      |

```json
{
  "sekolah_request": {
    "sekolah": "Smp 4 Jakarta",
    "email": "smpn4@example.com",
    "noHp": "08123456789",
    "npsn": "5551",
    "password": "5551"
  }
}
```
- **Response**
```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "02659e05-c06d-49a8-a38c-d7393c53c0d9",
        "sekolah": "Smp 4 Jakarta",
        "email": "smpn4@example.com",
        "npsn": "5551",
        "logo": "https://ik.imagekit.io/soeauotwj/70769b66-07ef-4050-8e65-73b39f2d40c8_138006l_CU7-2fxtW.jpg",
        "no_hp": "08123456789",
        "created_by": "admin"
    }
}
```
### Get All Sekolah
- **Endpoint**
```http
  GET /api/v1/sekolah
```

- **Query Params**

  | Parameter   | Type      | Description                                          |
  |:------------|:----------|:-----------------------------------------------------|
  | `sekolah`   | `String`  | Search By name sekolah                               |
  | `npsn`      | `String`  | Search By NPSN of sekolah                            |
  | `page`      | `Integer` | number of page data sekolah                          |
  | `size`      | `Integer` | Limit of row data sekolah                            |
  | `sortBy`    | `String`  | Sorting data by their key, example "sekolah", "npsn" |
  | `direction` | `String`  | Sorting by ASC or DESC                               |


- **Response**

```json
{
    "statusCode": 200,
    "message": "Successfully get data sekolah",
    "data": [
        {
            "id": "94153e1a-00fb-4543-ade1-7792a4a270fa",
            "sekolah": "SMK 10 Jakarta",
            "email": "smkn10@example.com",
            "npsn": "1234",
            "logo": "https://ik.imagekit.io/soeauotwj/9daf1948-66a7-4ea8-bb3d-8b345f805d24_138006l_8cCI9fd3q.jpg",
            "no_hp": "08123456789",
            "created_by": "admin"
        },
        {
            "id": "02659e05-c06d-49a8-a38c-d7393c53c0d9",
            "sekolah": "Smp 4 Jakarta",
            "email": "smpn4@example.com",
            "npsn": "5551",
            "logo": "https://ik.imagekit.io/soeauotwj/70769b66-07ef-4050-8e65-73b39f2d40c8_138006l_CU7-2fxtW.jpg",
            "no_hp": "08123456789",
            "created_by": "admin"
        }
    ],
    "paging": {
        "totalPages": 1,
        "totalElements": 2,
        "page": 1,
        "size": 20,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

### Get By Id
### Update Sekolah
### Delete Sekolah