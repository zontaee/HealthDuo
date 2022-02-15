# HealthDuo

02-15

![image](https://user-images.githubusercontent.com/90680271/154027428-206423c1-c0ab-489c-985c-bb818463d260.png)

JpaRepository를 extends 받아서 save기능을 사용하고 있는데 회원가입씩 동일한 아이디가 입력될경우 에러를 터트리고 싶었다. 하지만 오류는 발생하지 않았고 로그를 보니 insert 대신 update문이

나가는걸 확인 했다. JPA JpaRepository를 save로직이 중복되는 데이터가 들어오면 merge(병합) 로직이 실행되는 코드를 본거같다. 그냥 update대신 insert만 가능하게 할 수 있는지 알아보고 만약 안된다

면 회원가입시 중복 아이디 체크를 받아서 controller에 중복되는 아이디값이 들어가지 않게 해줘야겠다.
