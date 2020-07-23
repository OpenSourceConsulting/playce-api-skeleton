# playce-api-skeleton
playce-api-skeleton는 SpringBoot + JPA + Apache Derby로 구성된 REST API입니다.

#### 실행 방법
1. 초기 데이터베이스가 설정이 되어 있지 않으므로 application.properties의 사용할 데이터베이스 정보 입력
```
사용할 데이터베이스 정보 기입
spring.datasource.platform=derby
spring.datasource.url=jdbc:derby://localhost:1527/playce;create=true
spring.datasource.username=playce
spring.datasource.password=playce

spring.jpa.hibernate.ddl-auto=true // 초기 테이블 생성 유무
```
2. SampleApplication 기동 -> 애플리케이션 기동 시 테이블 생성 및 샘플 데이터 insert
3. 초기 테이블 생성 후, spring.jpa.hibernate.ddl-auto 'none'으로 설정(생성된 기존 테이블 사용)

#### Swagger APIs
##### URL : http://localhost:8080/playce/swagger-ui.html

#### API spec
##### Admin
|Method|URL|설명|
|------|----|---|
|**[GET]**|*/api/admin/user*|사용자 목록 조회
|**[POST]**|*/api/admin/user*|사용자 생성
|**[GET]**|*/api/admin/user/{id}*|사용자 상세 조회
|**[PUT]**|*/api/admin/user/{id}*|사용자 수정
|**[DELETE]**|*/api/admin/user/{id}*|사용자 삭제

##### Domain
|Method|URL|설명|
|------|----|---|
|**[GET]**|*/api/domain*|도메인 목록 조회
|**[POST]**|*/api/domain*|도메인 생성
|**[GET]**|*/api/domain/{id}*|도메인 상세 조회
|**[PUT]**|*/api/domain/{id}*|도메인 수정
|**[DELETE]**|*/api/domain/{id}*|도메인 삭제

##### Monitoring
|Method|URL|설명|
|------|----|---|
|**[GET]**|*/api/monitoring/host*|호스트 모니터링 정보 조회

##### Resources
|Method|URL|설명|
|------|----|---|
|**[GET]**|*/api/resources/cluster*|클러스터 목록 조회
|**[POST]**|*/api/resources/cluster*|클러스터 생성
|**[GET]**|*/api/resources/{id}*|클러스터 상세 조회
|**[PUT]**|*/api/resources/{id}*|클러스터 수정
|**[DELETE]**|*/api/resources/{id}*|클러스터 삭제

##### Servers
|Method|URL|설명|
|------|----|---|
|**[GET]**|*/api/servers/host*|호스트 목록 조회
|**[GET]**|*/api/servers/host/{id}*|호스트 상세정보 조회
