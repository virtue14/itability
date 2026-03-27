# itAbility Backend

IT 개발자를 위한 커리어 성장 및 커뮤니티 플랫폼

## 프로젝트 구조

```
itability-be/
├── eureka-server/       # 서비스 디스커버리 (8761)
├── config-server/       # 설정 중앙 관리 (8888)
├── gateway-server/      # API 게이트웨이 (8000)
├── board-service/       # 게시판, 모집, 크롤링, 검색 (8081)
├── member-service/      # 회원, OAuth2, 마이페이지 (8082)
├── report-service/      # 신고, 블랙리스트 (8083)
├── config-repo/         # 서비스별 설정 파일
├── docker-compose.yml   # 인프라 (MariaDB, Elasticsearch)
└── .env                 # Docker/Config Server용 환경변수 (Git 미포함)
```

## 시작하기

### 1. .env 설정

`itability-be/`에 `.env` 파일 생성 후 `.env`의 `ENCRYPT_KEY` 사용

```env
DB_PORT=3306
DB_NAME=itability
DB_PASSWORD=<DB 비밀번호>
ELASTICSEARCH_PORT=9200
ENCRYPT_KEY=<암호화 키>
```

### 2. 인프라 실행

```bash
docker compose up -d        # MariaDB + Elasticsearch 실행
docker compose ps           # 상태 확인
docker compose down         # 중지
docker compose down -v      # 중지 + 데이터 삭제
```

### 3. 서비스 실행 (순서 필수)

```
1) Eureka Server    → 서비스 등록/발견
2) Config Server    → 설정 제공 (ENCRYPT_KEY 필요)
3) 나머지 서비스     → Config Server에서 설정을 받아 기동
```

IntelliJ: Config Server의 Run Configuration > Environment Variables에 `ENCRYPT_KEY=<키값>` 추가

터미널:
```bash
cd eureka-server && ./gradlew bootRun
cd config-server && ENCRYPT_KEY=<키값> ./gradlew bootRun
cd gateway-server && ./gradlew bootRun
cd board-service && ./gradlew bootRun
cd member-service && ./gradlew bootRun
cd report-service && ./gradlew bootRun
```

### 4. 실행 확인

- Eureka 대시보드: http://localhost:8761
- Config 설정 조회: `curl http://localhost:8888/gateway-server/default`
- Gateway 라우팅: `http://localhost:8000/{서비스명}/**`

---

## Config Server 암/복호화

> Config Server는 Swagger를 지원하지 않음 (Config Server의 경로 매핑이 Swagger 경로를 가로채기 때문)

`config-repo/`의 `'{cipher}...'` 값은 암호화된 비밀값이며, Config Server가 `ENCRYPT_KEY`로 복호화 후 각 서비스에 전달

### 암호화 (Config Server 실행 중)

```bash
curl -X POST http://localhost:8888/encrypt -d "암호화할_값"
# 출력: a1b2c3d4e5f6...

# config-repo yml에 적용
# password: '{cipher}a1b2c3d4e5f6...'
```

### 복호화 확인

```bash
# 서비스별 설정 전체 조회 (복호화된 상태로 반환)
curl http://localhost:8888/itability-member-service/default
```

### 주의

- **URL, 경로, 포트**는 암호화 시 값 손상 → 평문 유지
- **비밀번호, API Key, Secret**만 암호화 대상
- 팀 전원 **동일한 ENCRYPT_KEY** 사용 필수
