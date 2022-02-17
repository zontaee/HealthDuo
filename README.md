# HealthDuo

02-15

![image](https://user-images.githubusercontent.com/90680271/154027428-206423c1-c0ab-489c-985c-bb818463d260.png)

JpaRepository를 extends 받아서 save기능을 사용하고 있는데 회원가입씩 동일한 아이디가 입력될경우 에러를 터트리고 싶었다. 하지만 오류는 발생하지 않았고 로그를 보니 insert 대신 update문이

나가는걸 확인 했다. JPA JpaRepository의 save로직은 중복되는 데이터가 들어오면 merge(병합) 로직이 실행되는 걸로 알고있다. 그냥 update대신 insert만 가능하게 할 수 있는지 알아보고 만약 안된다

면 회원가입시 중복 아이디 체크를 받아서 controller에 중복되는 아이디값이 들어가지 않게 해줘야겠다. 


-> 2-16 사용자 정의 리포지토리 사용자 지정 쿼리로 해결(JPA JpaRepository를 save로직은 예상한대로 중복되는 데이터가 들어오면 update가 나와 새로 만들어 줘야 했다)


2-17


![image](https://user-images.githubusercontent.com/90680271/154434991-d5a04c6c-9357-4f7e-9e5e-5882eb47acb2.png)


페이징을 하고 정렬기능을 추가하려던중 properties를 인식하지 못하는 오류가 발생하여 _(언더스코어) 가 들어간 앤티티 네이밍 때문인걸 알게되었다


따라서  변형된 낙타 표기법 ex (memberId) 로 변경하였다.

