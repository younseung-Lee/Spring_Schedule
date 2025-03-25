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
[[![onealog](/assets/readme/easyme.png)  ](https://github.com/younseung-Lee/Spring_Schedule/issues/1#issue-2945894319) ](https://private-user-images.githubusercontent.com/95355975/426459300-4a5ac365-29cd-4904-b87b-2b9a616223e8.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDI4OTU2OTEsIm5iZiI6MTc0Mjg5NTM5MSwicGF0aCI6Ii85NTM1NTk3NS80MjY0NTkzMDAtNGE1YWMzNjUtMjljZC00OTA0LWI4N2ItMmI5YTYxNjIyM2U4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTAzMjUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMzI1VDA5MzYzMVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTg5NTIxMDFkOTdkYWM5NjBiMzUyOTMyZWNhNDM1MThkZjJhNDA0Y2MzMzg0MzVkNmMyYjFiZjJiYTJhNzAyNDMmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.3DQpkPo0C665o6sIEdl8t_ZhwizXVW5upKwxoXilcvA)
