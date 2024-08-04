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

- **Header**

| Header           | Type    | Description                         |
|:-----------------|:--------|:------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_ADMIN |

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

- **Header**

| Header           | Type    | Description                         |
|:-----------------|:--------|:------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_ADMIN |

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

### Get By NPSN
- **Endpoint**
```http
  GET /api/v1/sekolah/{npsn}
```

- **Path Variable**

| Parameter | Type     | Description                   |
|:----------| :------- |:------------------------------|
| `npsn`    | `Integer` | **Required**. NPSN of sekolah |

- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |


- **Response**
```json
{
    "statusCode": 202,
    "message": "You have successfully logged in",
    "data": {
        "username": "0101010101",
        "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJlZHV3YXZlIiwiaWF0IjoxNzIyNzAzMzMwLCJleHAiOjE3MjI5NjI1MzAsInN1YiI6IjRiMTU4NDc0LTIyYWUtNDNkOC1iZDgyLTI0MmI3YmQ4YWFlYyIsInJvbGVzIjpbIlJPTEVfU0VLT0xBSCJdfQ.THMtJ_v_7biX_Kwzell62s7oPx381hxzRIRqFDIX360vSQTn-6o6tG4O5AChu9Ek1b16aJyIWpzwRYq0lXsGjA",
        "roles": [
            "ROLE_SEKOLAH"
        ]
    }
}
```

### Update Sekolah
- **Endpoint**
```http
  PUT /api/v1/sekolah
```

- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_ADMIN |


- **Request** \
  Content-Type : multipart/form-data;

| Parameter         | Type   | Description                          |
|:------------------|:-------|:-------------------------------------|
| `sekolah_request` | `text` | **Required**. Json to create sekolah |
| `logo`            | `file` | image for sekolah      |

```json
{
  "sekolah_request":{
      "id": "ba2098ba-dcfe-4dce-981d-699388f854a7",
      "sekolah": "SMP 23 Jakarta",
      "email": "sekolahabc@example.com",
      "noHp": "08123456789",
      "npsn": "1237821555",
      "password": "your_password"
    }
}
```

- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully update data",
    "data": {
        "id": "a6c00ca1-ea92-43b6-95c0-614677836193",
        "sekolah": "SMP 23 Jakarta",
        "email": "sekolahabc@example.com",
        "npsn": "0101010101",
        "logo": "https://ik.imagekit.io/soeauotwj/031a49d9-b44a-472a-b6ff-ffa39970d7c9_chicken_k7fWhH904.png",
        "no_hp": "08123456789",
        "created_by": "admin"
    }
}
```

### Delete By id Sekolah

- **Endpoint**
```http
  DELETE /api/v1/sekolah/{id}
```

- **Path Variable**

| Parameter | Type     | Description                 |
|:----------|:---------|:----------------------------|
| `id`      | `String` | **Required**. id of sekolah |


- **Header**

| Header           | Type    | Description                         |
|:-----------------|:--------|:------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_ADMIN |


- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully delete data sekolah",
    "data": null
}
```

## Golongan


### Create Golongan
- **Endpoint**
```http
  POST /api/v1/golongan
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Request**
```json
{
    "golongan": "golongan sedang",
    "spp": 20000000
}
```

- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
        "golongan": "golongan sedang",
        "spp": 20000000,
        "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
        "createdAt": "2024-08-03T16:58:52.118+00:00",
        "updateAt": "2024-08-03T16:58:52.118+00:00"
    }
}
```

### Get All Golongan

- **Endpoint**
```http
  GET /api/v1/golongan
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Response**
```json
{
    "statusCode": 200,
    "message": "successfully get data",
    "data": [
        {
            "id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
            "golongan": "golongan sedang",
            "spp": 20000000,
            "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
            "createdAt": "2024-08-03",
            "updateAt": "2024-08-03"
        },
        {
            "id": "304d427e-37a7-42ad-a0e8-99b0d7749e81",
            "golongan": "golongan rendah",
            "spp": 10000000,
            "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
            "createdAt": "2024-08-04",
            "updateAt": "2024-08-04"
        }
    ],
    "paging": null
}
```

### Get By id Golongan
- **Endpoint**
```http
  GET /api/v1/golongan/{id}
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Path Variable**

| Parameter | Type     | Description                  |
|:----------|:---------|:-----------------------------|
| `id`      | `String` | **Required**. id of Golongan |

- **Response**
```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
        "golongan": "golongan sedang",
        "spp": 20000000,
        "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
        "createdAt": "2024-08-03",
        "updateAt": "2024-08-03"
    }
}
```

### Update Golongan
- **Endpoint**
```http
  PUT /api/v1/golongan
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Request**
```json
{
    "id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
    "golongan": "golongan sedang",
    "spp": 20000000
}
```
- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully update data",
    "data": {
        "id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
        "golongan": "golongan sedang",
        "spp": 20000000,
        "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
        "createdAt": "2024-08-03",
        "updateAt": "2024-08-04"
    }
}
```

### Delete By id Golongan

- **Endpoint**
```http
  DELETE /api/v1/golongan/{id}
```

- **Path Variable**

| Parameter | Type     | Description                  |
|:----------|:---------|:-----------------------------|
| `id`      | `String` | **Required**. id of golongan |


- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |


- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully delete data golongan",
    "data": null
}
```




## Siswa

### Create Siswa

- **Endpoint**
```http
  POST /api/v1/siswa
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Request**

```json
{
  "nama": "anthoni",
  "nis": "12341234",
  "email": "anthoni@duck.com",
  "noHp": "0812345678",
  "noHpOrtu": "0887654321",
  "alamat": "Jl. H. Ahmad Dahlan",
  "golonganId": "ac30f23f-4add-49af-bfb4-cb6a1b84f314"
}
```


- **Response**
```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
        "nama": "anthoni",
        "email": "anthoni@duck.com",
        "alamat": "Jl. H. Ahmad Dahlan",
        "status": "LUNAS",
        "tagihan": 0,
        "NIS": "12341234",
        "no_hp": "0812345678",
        "no_hp_ortu": "0887654321",
        "is_active": true,
        "golongan": "golongan Bangsawan",
        "golongan_id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
        "spp": 20000000,
        "created_at": "2024-08-04T06:57:34.554+00:00",
        "updated_at": "2024-08-04T06:57:34.554+00:00"
    }
}
```

### Get All Siswa
- **Endpoint**
```http
  GET /api/v1/siswa
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |


- **Query Params**


  | Parameter   | Type      | Description                                          |
    |:------------|:----------|:-----------------------------------------------------|
  | `golongan`  | `String`  | Search by golongan id                                |
  | `nama`      | `String`  | Search by siswa name                                 |
  | `nis`       | `Integer` | Search by siswa nis                                  |
  | `tagihan`   | `Integer` | Search by siswa tagihan                              |
  | `isActive`  | `Boolean` | Search by siswa isActive true or false               |
  | `status`    | `String`  | Search by siswa status tagihan LUNAS or BELUM_LUNAS  |
  | `page`      | `Integer` | number of page data sekolah                          |
  | `size`      | `Integer` | Limit of row data sekolah                            |
  | `sortBy`    | `String`  | Sorting data by their key, example "sekolah", "npsn" |
  | `direction` | `String`  | Sorting by ASC or DESC                               |

- **Response**

```json
{
    "statusCode": 200,
    "message": "Successfully get all data siswa",
    "data": [
        {
            "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
            "nama": "anthoni",
            "email": "anthoni@duck.com",
            "alamat": "Jl. H. Ahmad Dahlan",
            "status": "LUNAS",
            "tagihan": 0,
            "NIS": "12341234",
            "no_hp": "0812345678",
            "no_hp_ortu": "0887654321",
            "is_active": true,
            "golongan": "golongan Bangsawan",
            "golongan_id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
            "spp": 20000000,
            "created_at": "2024-08-04",
            "updated_at": "2024-08-04"
        }
    ],
    "paging": {
        "totalPages": 1,
        "totalElements": 1,
        "page": 0,
        "size": 10,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

### Get By id Siswa


- **Endpoint**
```http
  GET /api/v1/siswa/{id}
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Path Variable**

| Parameter | Type     | Description                 |
|:----------|:---------|:----------------------------|
| `id`      | `String` | **Required**. id of Sekolah |

- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
        "nama": "anthoni",
        "email": "anthoni@duck.com",
        "alamat": "Jl. H. Ahmad Dahlan",
        "status": "LUNAS",
        "tagihan": 0,
        "NIS": "12341234",
        "no_hp": "0812345678",
        "no_hp_ortu": "0887654321",
        "is_active": true,
        "golongan": "golongan Bangsawan",
        "golongan_id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
        "spp": 20000000,
        "created_at": "2024-08-04",
        "updated_at": "2024-08-04"
    }
}
```

### Update Siswa


- **Endpoint**
```http
  PUT /api/v1/siswa
```
- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Request**
```json
{
  "id" : "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
  "nama": "Rifky",
  "nis": "13131313",
  "email": "rifky@duck.com",
  "noHp": "0812345678",
  "noHpOrtu": "0887654321",
  "alamat": "Jl. H. Melati",
  "golonganId": "09d29fa6-f8ad-465d-a929-dc4dd0592f48"
}
```
- **Response**

```json
{
  "statusCode": 200,
  "message": "successfully update data siswa",
  "data": {
    "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
    "nama": "Rifky",
    "email": "rifky@duck.com",
    "alamat": "Jl. H. Melati",
    "status": "LUNAS",
    "tagihan": 0,
    "NIS": "13131313",
    "no_hp": "0812345678",
    "no_hp_ortu": "0887654321",
    "is_active": true,
    "golongan": "golongan Bangsawan",
    "golongan_id": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
    "spp": 20000000,
    "created_at": "2024-08-04",
    "updated_at": "2024-08-04T07:10:48.008+00:00"
  }
}
```

### Delete Siswa


- **Endpoint**
```http
  DELETE /api/v1/siswa/{id}
```

- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |

- **Path Variable**

| Parameter | Type     | Description               |
|:----------|:---------|:--------------------------|
| `id`      | `String` | **Required**. id of Siswa |


- **Response**
```json
{
    "statusCode": 200,
    "message": "Student Active that was successfully changed",
    "data": "Students with ID F08DC8AE-7A7D-4912-9AE1-3C63E0439956 was changed"
}
```

### Reset Tagihan
- **Endpoint**
```http
  POST /api/v1/siswa/reset
```

- **Header**

| Header           | Type    | Description                           |
|:-----------------|:--------|:--------------------------------------|
| `Authorization`  | `Token` | **Required**. TOKEN with ROLE_SEKOLAH |


- **Request**
```json
{
    "siswaId" : [
        "3e983609-9a7a-4588-8cdc-e3e1a64ad799",
        "f08dc8ae-7a7d-4912-9ae1-3c63e0439956"
    ]
}
```

- **Response**
```json
{
    "statusCode": 200,
    "message": "successfully update status",
    "data": null,
    "paging": null
}
```

### Login Siswa
- **Endpoint**
```http
  POST /api/v1/siswa/login
```

- **Request**
```json
{
  "nis": "13131313",
  "email": "rifky@duck.com"
}
```

- **Response**

```json
{
    "statusCode": 200,
    "message": "successfully add data",
    "data": {
        "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
        "nama": "Rifky",
        "email": "rifky@duck.com",
        "alamat": "Jl. H. Melati",
        "status": "BELUM_LUNAS",
        "tagihan": 20000000,
        "sekolah": "SMP 23 Jakarta",
        "urlLogo": "https://ik.imagekit.io/soeauotwj/031a49d9-b44a-472a-b6ff-ffa39970d7c9_chicken_k7fWhH904.png",
        "NIS": "13131313",
        "no_hp": "0812345678",
        "no_hp_ortu": "0887654321",
        "is_active": false,
        "golongan": "golongan Bangsawan",
        "spp": 20000000,
        "created_at": "2024-08-04",
        "updated_at": "2024-08-04"
    }
}
```


## Transaksi
### Create Transaksi

- **Endpoint**
```http
  POST /api/v1/transaksi
```

- **Request**
```json
{
    "sekolahId": "a6c00ca1-ea92-43b6-95c0-614677836193",
    "nis": "13131313",
    "jumlahBayar": 20000000
}
```

- **Response**
```json
{
    "statusCode": 200,
    "message": "Successfully create transaction",
    "data": {
        "id": "431d7f8a-09ac-4774-87ef-14736e3732b9",
        "transDate": "2024-08-04T07:32:18.089+00:00",
        "siswa": {
            "id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
            "nama": "Rifky",
            "email": "rifky@duck.com",
            "alamat": "Jl. H. Melati",
            "status": "BELUM_LUNAS",
            "tagihan": 20000000,
            "NIS": "13131313",
            "no_hp": "0812345678",
            "no_hp_ortu": "0887654321",
            "is_active": true,
            "golongan": "09d29fa6-f8ad-465d-a929-dc4dd0592f48",
            "golongan_id": null,
            "spp": null,
            "created_at": "2024-08-04",
            "updated_at": "2024-08-04"
        },
        "jumlahBayar": 20000000,
        "pembayaran": {
            "id": "41a5a54c-6cfa-491e-a85f-6f7c61ae6378",
            "token": "664b54b9-9dee-4671-af9a-2c2159bcb65a",
            "redirectUrl": "https://app.sandbox.midtrans.com/snap/v4/redirection/664b54b9-9dee-4671-af9a-2c2159bcb65a",
            "transactionStatus": "pending"
        }
    }
}
```

### Get All Transaksi By siswaId
- **Endpoint**
```http
  POST /api/v1/transaksi/siswa/{id}
```


- **Path Variable**

| Parameter | Type     | Description               |
|:----------|:---------|:--------------------------|
| `id`      | `String` | **Required**. id of Siswa |

- **Response**

```json
{
    "statusCode": 200,
    "message": "Successfully get all transaction siswa",
    "data": [
        {
            "id": "431d7f8a-09ac-4774-87ef-14736e3732b9",
            "nis": "13131313",
            "golongan": "golongan Bangsawan",
            "siswa_id": "f08dc8ae-7a7d-4912-9ae1-3c63e0439956",
            "nama_siswa": "Rifky",
            "jumlah_pembayaran": 20000000,
            "status_pembayaran": "pending",
            "tanggal_transaksi": "2024-08-04T07:32:18.089+00:00"
        }
    ]
}
```



### Get transaksi By id

- **Endpoint**
```http
  POST /api/v1/transaksi/{id}
```


- **Path Variable**

| Parameter | Type     | Description                     |
|:----------|:---------|:--------------------------------|
| `id`      | `String` | **Required**. id of Transaction |

- **Response**

```json
{
    "statusCode": 200,
    "message": "Successfully get data transaction",
    "data": {
        "id": "431d7f8a-09ac-4774-87ef-14736e3732b9",
        "transDate": "2024-08-04T07:32:18.089+00:00",
        "nama": "Rifky",
        "email": "rifky@duck.com",
        "golongan": "golongan Bangsawan",
        "jumlahBayar": 20000000,
        "transactionStatus": "pending",
        "NIS": "13131313"
    }
}
```
