# httpd


## 브렌치별 설명

### STEP_00 

- 비어있는 maven 프로젝트

### STEP_01

- main메소드를 가지고 있는 App클래스
- main메소드에서는 HttpServer객체를 생성하고, 해당 객체가 가지고 있는 run()메소드를 호출한다.
- run()메소드를 가지고 있는 HttpServer 클래스

![클래스 다이어그램](1.PNG)

- 점선은 의존관계를 표현한다. 메소드에서 해당 객체를 사용한다는 것을 의미한다.
- 실선은 연관관계를 표현한다. 클래스가 해당 클래스를 가진다는 것을 의미한다.

### STEP_02

- HttpServer클래스가 8080포트로 부터 Client접속을 대기하도록 한다. run()메소드에서 구현한다.


###  STEP_03

- RequestHandler클래스는 Client Socket을 파라미터로 받아 Request객체를 반환하는 handle메소드를 가지고 있다.
- Request객체는 필드로 httpVersion, headers, method, requestTarget, bodyInput을 가지고 있다.
- handle메소드는 socket의 inputStream을 이용하여 정보를 읽어들여 httpVersion, headers, method, requestTarget의 값을 초기화 한다.


![클래스 다이어그램](2.PNG)