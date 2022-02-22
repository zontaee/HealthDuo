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

2-18 


삭제 , 수정 추가 (기본 CRUD)


2-19 

아이디 저장하기(쿠키사용), 로그인 유지기능 sesstion ,게시판 작성자 추가(modelmapper(앤티티 DTO 변환 라이브러리))공부 후 적용예정


2-21


![image](https://user-images.githubusercontent.com/90680271/154938248-db007cd3-6fe8-4158-9bb8-5d266d5a2e66.png)


나는 기본키를 따로 지정해주었는데 위 사진과 같이 기본키 시퀀스가 같이 상승한다... 해결책을 찾는중이다. (->@SequenceGenerator 사용 해결)


2-22

![2-22 join](https://user-images.githubusercontent.com/90680271/155128585-56035ed7-80bb-42d0-88e1-ee119f12e77d.JPG)관계


@ManyToOne(fetch = FetchType.LAZY) 연관관계 설정에서 패치 타임을 LAZY 해서 발생하는 문제다. LAZY 를 사용하게 되면 proxy 객채로 생성되어서 fetch API 에서 데이터를 가져올때 오류가 생긴다.

따라서 해결방법은 Eager로 타입을 바꿔주거나 패치조인을 하거나 DTO로 반환해주는 방법이있는데 여기서는 DTO 로 변환하여 해결하였다.
