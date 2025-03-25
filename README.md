# 📅 일정 관리 API

일정(캘린더)을 관리할 수 있는 RESTful API입니다.  
일정을 생성, 조회, 수정, 삭제할 수 있습니다.

---

## 📌 API 명세서

### 📝 일정 관리 API

| 기능 | 메서드 | URL | 요청 바디 | Response | 응답 (실패) |
|------|--------|------------|------------|------------|------------|
| **일정 생성** | `POST` | `/schedule` | `{ "todo ": string, "username": string, "password": string }` | `201 Created + { "id": Long, "todo": string, "username": string, "createtime": string, "updatetime": string }` | `400 Bad Request` |
| **전체 일정 조회** | `GET` | `/schedule` | 없음 | `200 OK + [ { "id": Long, "todo": string, "username": string, "createtime": string, "updatetime": string } ] (없으면 [])`) | 없음 |
| **선택 일정 조회** | `GET` | `/schedule/{id}` | 없음 | `200 OK + { "id": Long, "todo": string, "username": string, "createtime": string, "updatetime": string }` | `404 Not Found` |
| **선택 일정 수정** | `PATCH` | `/schedule/{id}` | `200 OK + {"todo ": string,"username": string,"password": string}` | `200 OK + { "id": Long, "todo": string, "username": string, "createtime": string, "updatetime": string }` | `401 Unauthorized` | 
| **선택 일정 삭제** | `DELETE` | `/schedules/{id}?password={password}` | 없음 | `200 OK` | `404 Not Found` |

### 📝 ERD
![onealog](/assets/readme/easyme.png)   
