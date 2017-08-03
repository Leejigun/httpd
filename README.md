# httpd


## 브렌치별 설명

### STEP_00 

- 비어있는 maven 프로젝트

### STEP_01

- main메소드를 가지고 있는 App클래스
- main메소드에서는 HttpServer객체를 생성하고, 해당 객체가 가지고 있는 run()메소드를 호출한다.
- run()메소드를 가지고 있는 HttpServer 클래스

### STEP_02

- HttpServer클래스가 8080포트로 부터 Client접속을 대기하도록 한다. run()메소드에서 구현한다.


###  STEP_03

- RequestHandler클래스는 Client Socket을 파라미터로 받아 Request객체를 반환하는 handle메소드를 가지고 있다.

