# 토비의 스프링 3.1 학습 코드
토비의 스프링 3.1의 소스를 개발 및 개선하는 일련의 과정을 경험하기 위한 코드입니다.

보다 자세한 내용은 "토비의 스프링 3.1" 책을 읽어보는 것을 추천합니다.

---

# 설정 방법
프로젝트 설정에 필요한 정보입니다.

## DB 설정
- DB는 mariadb를 사용합니다.
- `/libs/mariadb-java-client-2.7.3.jar` jdbc를 사용합니다.
- `/sql/create_db.sql` 을 참고해서, DB를 `spring` 으로 생성합니다.
- `/sql/create_users.sql` 을 참고해서, 테이블을 `users` 로 생성합니다. 