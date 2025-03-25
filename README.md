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
![Image](https://github.com/user-attachments/assets/4a5ac365-29cd-4904-b87b-2b9a616223e8)

### 📝 테이블 생성에 필요한 query
```js
use schedule;

crate table schedule(
    id         bigint auto_increment
        primary key,
    todo       varchar(100) not null,
    username   varchar(20)  not null,
    password   varchar(10)  null,
    createtime datetime     null,
    updatetime datetime     null
);

```
<br>

## 📌 기능 요구사항
### Lv 1. 일정 생성 및 조회 필수
#### 일정 생성 (일정 작성하기)
- 포함 데이터:
- 할일, 작성자명, 비밀번호, 작성/수정일
- 작성/수정일: 날짜와 시간을 모두 포함한 형태
- 기능:
- 일정의 고유 식별자(ID)를 자동 생성하여 관리
- 최초 입력 시, 수정일은 작성일과 동일
#### 전체 일정 조회 (등록된 일정 불러오기)
- 조건:
- 수정일 (형식: YYYY-MM-DD)
- 작성자명
- 기능:
- 조건 중 하나 또는 둘 다 충족할 수 있음
- 수정일 기준 내림차순 정렬하여 조회
#### 선택 일정 조회 (선택한 일정 정보 불러오기)
- 기능:
- 일정의 고유 식별자(ID)를 사용하여 단건 조회
### Lv 2. 일정 수정 및 삭제 필수
#### 선택한 일정 수정
- 수정 가능한 항목: 할일, 작성자명
- 기능:
- 서버에 일정 수정 시 비밀번호를 함께 전달
- 작성일은 변경 불가, 수정일은 수정 시점으로 업데이트
#### 선택한 일정 삭제
- 기능:
- 서버에 일정 삭제 요청 시 비밀번호를 함께 전달
