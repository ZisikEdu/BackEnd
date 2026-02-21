# DOKHU API 명세서

## 기본 정보
- **Base URL**: `/api`
- **인증**: Spring Security Session 기반 (OAuth2 / Local Login)
- **Content-Type**: `application/json`

---

## 1. 책 API (Book) - 로컬 DB

### 1.1 로컬 DB 책 검색
```
GET /api/books/search
```

로컬 데이터베이스에서 책을 검색합니다. (제목, 저자, ISBN)

**Query Parameters**
| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| query | String | ✅ | - | 검색어 (제목, 저자, ISBN) |
| page | Integer | ❌ | 1 | 페이지 번호 |
| size | Integer | ❌ | 10 | 페이지 크기 |

**Response**
```json
{
  "content": [
    {
      "id": 1,
      "isbn": "9781234567890",
      "title": "책 제목",
      "author": "저자명",
      "category": "소설/문학",
      "genre": null,
      "imageUrl": "https://image.url/cover.jpg",
      "description": "책 설명...",
      "publisher": "출판사",
      "publishedDate": "2024-01-15T00:00:00",
      "totalPages": 320,
      "rating": 4.0,
      "aladinItemId": "123456",
      "isManualEntry": false,
      "createdAt": "2024-01-20T10:30:00"
    }
  ],
  "pageable": { ... },
  "totalElements": 50,
  "totalPages": 5
}
```

---

### 1.2 로컬 DB ISBN으로 책 조회
```
GET /api/books/isbn/{isbn}
```

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| isbn | String | ISBN 또는 ISBN13 |

**Response**: BookResponse

---

### 1.3 로컬 DB ID로 책 조회
```
GET /api/books/{id}
```

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 책 ID |

**Response**
```json
{
  "id": 1,
  "isbn": "9781234567890",
  "title": "책 제목",
  "author": "저자명",
  "category": "소설/문학",
  "genre": null,
  "imageUrl": "https://image.url/cover.jpg",
  "description": "책 설명...",
  "publisher": "출판사",
  "publishedDate": "2024-01-15T00:00:00",
  "totalPages": 320,
  "rating": 4.0,
  "aladinItemId": "123456",
  "isManualEntry": false,
  "createdAt": "2024-01-20T10:30:00"
}
```

---

### 1.4 직접 책 등록
```
POST /api/books/manual
```

**Request Body**
```json
{
  "title": "책 제목",
  "author": "저자명",
  "isbn": "9781234567890",
  "category": "카테고리",
  "genre": "장르",
  "imageUrl": "https://image.url/cover.jpg",
  "description": "책 설명",
  "publisher": "출판사",
  "totalPages": 300
}
```

**Response**: `201 Created`
```json
{
  "id": 1,
  "isbn": "9781234567890",
  "title": "책 제목",
  "author": "저자명",
  "category": "카테고리",
  "genre": "장르",
  "imageUrl": "https://image.url/cover.jpg",
  "description": "책 설명",
  "publisher": "출판사",
  "publishedDate": null,
  "totalPages": 300,
  "rating": null,
  "aladinItemId": null,
  "isManualEntry": true,
  "createdAt": "2024-01-20T10:30:00"
}
```

---

## 2. 알라딘 API (Aladin)

### 2.1 알라딘 책 검색 (조회만)
```
GET /api/books/aladin/search
```

알라딘 API에서 책을 검색합니다. 로컬 DB에 저장하지 않습니다.

**Query Parameters**
| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| query | String | ✅ | - | 검색어 |
| page | Integer | ❌ | 1 | 페이지 번호 |
| size | Integer | ❌ | 10 | 페이지 크기 |

**Response**
```json
{
  "totalResults": 150,
  "page": 1,
  "size": 10,
  "items": [
    {
      "itemId": "123456",
      "title": "책 제목",
      "author": "저자명",
      "publisher": "출판사",
      "pubDate": "2024-01-15",
      "isbn": "1234567890",
      "isbn13": "9781234567890",
      "cover": "https://image.url/cover.jpg",
      "description": "책 설명...",
      "categoryName": "소설/문학",
      "priceStandard": 15000,
      "customerReviewRank": 8,
      "itemPage": 320
    }
  ]
}
```

---

### 2.2 알라딘 ISBN 조회 (조회만)
```
GET /api/books/aladin/isbn/{isbn}
```

알라딘 API에서 ISBN으로 책을 조회합니다. 로컬 DB에 저장하지 않습니다.

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| isbn | String | ISBN 또는 ISBN13 |

**Response**: BookSearchResponse (2.1과 동일)

---

### 2.3 알라딘에서 ISBN으로 책 가져오기 (로컬 DB 저장)
```
POST /api/books/aladin/import/isbn/{isbn}
```

알라딘 API에서 ISBN으로 책을 조회하여 로컬 DB에 저장합니다.
이미 존재하는 책은 기존 데이터를 반환합니다.

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| isbn | String | ISBN 또는 ISBN13 |

**Response**: `201 Created`
```json
{
  "id": 1,
  "isbn": "9781234567890",
  "title": "책 제목",
  "author": "저자명",
  "category": "소설/문학",
  "genre": null,
  "imageUrl": "https://image.url/cover.jpg",
  "description": "책 설명...",
  "publisher": "출판사",
  "publishedDate": "2024-01-15T00:00:00",
  "totalPages": 320,
  "rating": 4.0,
  "aladinItemId": "123456",
  "isManualEntry": false,
  "createdAt": "2024-01-20T10:30:00"
}
```

---

### 2.4 알라딘에서 ItemId로 책 가져오기 (로컬 DB 저장)
```
POST /api/books/aladin/import/item/{itemId}
```

알라딘 API에서 ItemId로 책을 조회하여 로컬 DB에 저장합니다.

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| itemId | String | 알라딘 Item ID |

**Response**: `201 Created` - BookResponse

---

### 2.5 알라딘 검색 결과 일괄 저장
```
POST /api/books/aladin/import/search
```

알라딘 API 검색 결과를 로컬 DB에 일괄 저장합니다.
이미 존재하는 책은 기존 데이터를 반환합니다.

**Query Parameters**
| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| query | String | ✅ | - | 검색어 |
| page | Integer | ❌ | 1 | 페이지 번호 |
| size | Integer | ❌ | 10 | 페이지 크기 |

**Response**: `201 Created`
```json
[
  {
    "id": 1,
    "isbn": "9781234567890",
    "title": "책 제목 1",
    ...
  },
  {
    "id": 2,
    "isbn": "9781234567891",
    "title": "책 제목 2",
    ...
  }
]
```

---

## 3. 서재 API (Library)

### 2.1 서재에 책 추가
```
POST /api/library/books
```

**Request Body**
```json
{
  "bookId": 1,
  "status": "PLAN_TO_READ"
}
```

| Field | Type | Required | Default | Description |
|-------|------|----------|---------|-------------|
| bookId | Long | ✅ | - | 책 ID |
| status | String | ❌ | PLAN_TO_READ | 읽기 상태 |

**Status 값**
- `PLAN_TO_READ`: 읽을 예정
- `READING`: 읽는 중
- `COMPLETE`: 완독

**Response**: `201 Created`
```json
{
  "id": 1,
  "bookId": 1,
  "bookTitle": "책 제목",
  "bookAuthor": "저자명",
  "bookImageUrl": "https://image.url/cover.jpg",
  "totalPages": 320,
  "status": "PLAN_TO_READ",
  "currentPage": 0,
  "userRating": null,
  "progressPercent": 0,
  "registeredAt": "2024-01-20T10:30:00",
  "startedAt": null,
  "completedAt": null,
  "rereadCount": 0
}
```

---

### 2.2 서재 목록 조회
```
GET /api/library/books
```

**Query Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| status | String | ❌ | 상태 필터 (PLAN_TO_READ, READING, COMPLETE) |
| page | Integer | ❌ | 페이지 번호 |
| size | Integer | ❌ | 페이지 크기 |

**Response**
```json
{
  "content": [
    {
      "id": 1,
      "bookId": 1,
      "bookTitle": "책 제목",
      "bookAuthor": "저자명",
      "bookImageUrl": "https://image.url/cover.jpg",
      "totalPages": 320,
      "status": "READING",
      "currentPage": 150,
      "userRating": null,
      "progressPercent": 47,
      "registeredAt": "2024-01-20T10:30:00",
      "startedAt": "2024-01-21T09:00:00",
      "completedAt": null,
      "rereadCount": 0
    }
  ],
  "pageable": { ... },
  "totalElements": 10,
  "totalPages": 1
}
```

---

### 2.3 서재 책 상세 조회
```
GET /api/library/books/{id}
```

**Path Parameters**
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 서재 책 ID (UserLibrary ID) |

**Response**: UserLibraryResponse (2.1과 동일)

---

### 2.4 서재 책 수정
```
PATCH /api/library/books/{id}
```

**Request Body**
```json
{
  "status": "READING",
  "currentPage": 150,
  "userRating": 4
}
```

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| status | String | ❌ | 읽기 상태 |
| currentPage | Integer | ❌ | 현재 페이지 (0 이상) |
| userRating | Integer | ❌ | 별점 (1-5) |

**Response**: UserLibraryResponse

---

### 2.5 서재에서 책 제거
```
DELETE /api/library/books/{id}
```

**Response**: `204 No Content`

---

### 2.6 재독 시작
```
POST /api/library/books/{id}/reread
```

**Response**: UserLibraryResponse
- `rereadCount` 증가
- `currentPage` 0으로 초기화
- `status` READING으로 변경

---

## 4. 집중 모드 API (Flow)

### 3.1 세션 시작
```
POST /api/flow/sessions/start
```

**Request Body**
```json
{
  "userLibraryId": 1,
  "startPage": 100,
  "videoUrl": "https://youtube.com/watch?v=xxx"
}
```

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| userLibraryId | Long | ✅ | 서재 책 ID |
| startPage | Integer | ❌ | 시작 페이지 (미입력시 현재 페이지) |
| videoUrl | String | ❌ | 영상 모드 URL |

**Response**: `201 Created`
```json
{
  "id": 1,
  "userLibraryId": 1,
  "startTime": "2024-01-20T10:30:00",
  "endTime": null,
  "duration": null,
  "startPage": 100,
  "endPage": null,
  "pagesRead": 0,
  "videoUrl": null,
  "sessionDate": "2024-01-20",
  "isActive": true
}
```

---

### 3.2 세션 종료
```
POST /api/flow/sessions/{id}/end
```

**Request Body**
```json
{
  "endPage": 130
}
```

**Response**: FlowSessionResponse
```json
{
  "id": 1,
  "userLibraryId": 1,
  "startTime": "2024-01-20T10:30:00",
  "endTime": "2024-01-20T11:30:00",
  "duration": 3600,
  "startPage": 100,
  "endPage": 130,
  "pagesRead": 30,
  "videoUrl": null,
  "sessionDate": "2024-01-20",
  "isActive": false
}
```

---

### 3.3 메모 작성
```
POST /api/flow/sessions/{id}/memos
```

**Request Body**
```json
{
  "content": "메모 내용입니다.",
  "pageNumber": 115
}
```

**Response**: `201 Created`
```json
{
  "id": 1,
  "flowSessionId": 1,
  "content": "메모 내용입니다.",
  "pageNumber": 115,
  "createdAt": "2024-01-20T10:45:00"
}
```

---

### 3.4 메모 목록 조회
```
GET /api/flow/sessions/{id}/memos
```

**Response**
```json
[
  {
    "id": 1,
    "flowSessionId": 1,
    "content": "메모 내용입니다.",
    "pageNumber": 115,
    "createdAt": "2024-01-20T10:45:00"
  }
]
```

---

### 3.5 활성 세션 조회
```
GET /api/flow/sessions/active
```

**Response**: FlowSessionResponse 또는 `null`

---

### 3.6 세션 상세 조회
```
GET /api/flow/sessions/{id}
```

**Response**: FlowSessionResponse

---

## 5. 독서 노트 API (Book Note)

### 4.1 독서 노트 조회
```
GET /api/library/books/{id}/note
```

**Response**
```json
{
  "id": 1,
  "userLibraryId": 1,
  "noteType": "FREE",
  "content": "노트 내용...",
  "createdAt": "2024-01-20T10:30:00",
  "updatedAt": "2024-01-21T15:00:00",
  "flowMemos": [
    {
      "id": 1,
      "flowSessionId": 1,
      "content": "집중 모드 메모",
      "pageNumber": 115,
      "createdAt": "2024-01-20T10:45:00"
    }
  ]
}
```

---

### 4.2 독서 노트 생성
```
POST /api/library/books/{id}/note
```

**Request Body**
```json
{
  "noteType": "FREE",
  "content": "노트 내용..."
}
```

| Field | Type | Required | Default | Description |
|-------|------|----------|---------|-------------|
| noteType | String | ❌ | FREE | 노트 타입 (FREE, TEMPLATE) |
| content | String | ❌ | - | 노트 내용 |

**Response**: `201 Created` - BookNoteResponse

---

### 4.3 독서 노트 수정
```
PATCH /api/library/books/{id}/note
```

**Request Body**
```json
{
  "noteType": "TEMPLATE",
  "content": "수정된 노트 내용..."
}
```

**Response**: BookNoteResponse

---

## 6. 통계 API (Statistics)

### 5.1 통계 요약
```
GET /api/statistics/summary
```

**Response**
```json
{
  "completedBooks": 15,
  "totalFocusSeconds": 86400,
  "totalFocusTimeFormatted": "24시간 0분",
  "currentStreak": 7,
  "maxStreak": 14
}
```

---

### 5.2 월별 통계
```
GET /api/statistics/monthly
```

**Query Parameters**
| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| year | Integer | ❌ | 현재 년도 | 조회 년도 |

**Response**
```json
{
  "year": 2024,
  "months": [
    { "month": 1, "completedBooks": 2 },
    { "month": 2, "completedBooks": 3 },
    { "month": 3, "completedBooks": 1 },
    ...
  ],
  "totalBooksYear": 15
}
```

---

### 5.3 독서 캘린더
```
GET /api/statistics/calendar
```

**Query Parameters**
| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| year | Integer | ❌ | 현재 년도 | 조회 년도 |
| month | Integer | ❌ | 현재 월 | 조회 월 |

**Response**
```json
{
  "year": 2024,
  "month": 1,
  "readingDates": [
    "2024-01-05",
    "2024-01-06",
    "2024-01-07",
    "2024-01-10",
    "2024-01-15"
  ],
  "totalReadingDays": 5
}
```

---

## 7. 홈 화면 API (Home)

### 6.1 프로필 카드
```
GET /api/home/profile-card
```

**Response**
```json
{
  "nickname": "독서왕",
  "profileImageUrl": "https://image.url/profile.jpg",
  "completedBooks": 15,
  "totalFocusTime": "24시간 0분",
  "currentStreak": 7
}
```

---

### 6.2 오늘의 문학 카드
```
GET /api/home/literary-card
```

**Response**
```json
{
  "id": 1,
  "type": "DAILY",
  "content": "책은 마음의 양식이다.",
  "author": "키케로",
  "source": "투스쿨룸 논쟁",
  "displayDate": "2024-01-20"
}
```

---

### 6.3 현재 읽는 책 카드
```
GET /api/home/reading-card
```

**Response**
```json
{
  "currentlyReading": [
    {
      "id": 1,
      "bookId": 1,
      "bookTitle": "책 제목",
      "bookAuthor": "저자명",
      "bookImageUrl": "https://image.url/cover.jpg",
      "totalPages": 320,
      "status": "READING",
      "currentPage": 150,
      "progressPercent": 47,
      ...
    }
  ],
  "totalCount": 1
}
```

---

## 8. 우편함 API (Mailbox)

### 7.1 공지 목록
```
GET /api/mailbox/notices
```

**Query Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| page | Integer | ❌ | 페이지 번호 |
| size | Integer | ❌ | 페이지 크기 |

**Response**
```json
{
  "content": [
    {
      "id": 1,
      "type": "NOTICE",
      "title": "서비스 업데이트 안내",
      "content": "새로운 기능이 추가되었습니다...",
      "isRead": false,
      "createdAt": "2024-01-20T10:00:00"
    }
  ],
  "pageable": { ... },
  "totalElements": 5
}
```

---

### 7.2 개인 우편 목록
```
GET /api/mailbox/mail
```

**Response**: Page<MailResponse> (7.1과 동일 구조)

---

### 7.3 읽음 처리
```
PATCH /api/mailbox/{id}/read
```

**Response**: `204 No Content`

---

### 7.4 피드백 보내기
```
POST /api/mailbox/feedback
```

**Request Body**
```json
{
  "subject": "기능 제안",
  "content": "이런 기능이 있었으면 좋겠습니다..."
}
```

**Response**: `201 Created`
```json
{
  "id": 1,
  "subject": "기능 제안",
  "content": "이런 기능이 있었으면 좋겠습니다...",
  "status": "PENDING",
  "createdAt": "2024-01-20T10:30:00"
}
```

**Feedback Status 값**
- `PENDING`: 대기 중
- `REVIEWED`: 검토됨
- `RESOLVED`: 해결됨

---

## 9. 프로필 API (Profile)

### 8.1 프로필 조회
```
GET /api/profile
```

**Response**
```json
{
  "id": 1,
  "email": "user@example.com",
  "username": "홍길동",
  "nickname": "독서왕",
  "profileImageUrl": "https://image.url/profile.jpg",
  "bio": "책을 사랑하는 사람입니다.",
  "streakDays": 7,
  "maxStreakDays": 14,
  "lastReadingDate": "2024-01-20",
  "updatedAt": "2024-01-20T10:30:00"
}
```

---

### 8.2 프로필 수정
```
PATCH /api/profile
```

**Request Body**
```json
{
  "nickname": "새 닉네임",
  "bio": "새 소개글",
  "profileImageUrl": "https://image.url/new-profile.jpg"
}
```

모든 필드는 선택 사항입니다.

**Response**: ProfileResponse (8.1과 동일)

---

## 에러 응답

### 에러 형식
```json
{
  "code": "404",
  "message": "존재하지 않는 책입니다.",
  "validation": {}
}
```

### 에러 코드
| HTTP Status | Exception | Message |
|-------------|-----------|---------|
| 400 | FlowSessionAlreadyEnded | 이미 종료된 집중 세션입니다. |
| 400 | InvalidRequest | 잘못된 요청입니다. |
| 404 | BookNotFound | 존재하지 않는 책입니다. |
| 404 | LibraryBookNotFound | 서재에 등록된 책을 찾을 수 없습니다. |
| 404 | FlowSessionNotFound | 집중 세션을 찾을 수 없습니다. |
| 404 | BookNoteNotFound | 독서 노트를 찾을 수 없습니다. |
| 404 | UserNotFound | 사용자를 찾을 수 없습니다. |
| 409 | AlreadyInLibrary | 이미 서재에 등록된 책입니다. |
| 502 | AladinApiException | 알라딘 API 호출 중 오류가 발생했습니다. |

---

## 인증

모든 API (책 검색 제외)는 인증이 필요합니다.

### 지원하는 인증 방식
1. **Session 기반 인증**: Spring Security Session
2. **OAuth2**: Google, Naver

### 인증 실패 시
```json
{
  "code": "401",
  "message": "Unauthorized"
}
```
