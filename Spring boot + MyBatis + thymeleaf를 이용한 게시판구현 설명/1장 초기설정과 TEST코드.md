# 1장. 초기설정과 TEST코드

## 사용할 프레임 워크 및 라이브러리

1. Spring Boot
2. Oracle DataBase
3. Thymeleaf
4. Mybatis

## 전체적인 흐름

1. MyBatis + Spring Boot + Oracle 연동 및 테스트
2. **대략적인 흐름**

    고객이 웹 페이지에서 요청을 할 시 컨트롤러로 URL이 전달되어 해당 하는 URL과 일치하는 메서드가 있을 시 Mapper를 구현하고 있는 Service로 request정보를 넘긴다.

    전달받은 Service는 Mapper에 있는 메서드 헤더를 구현하여 XML의 SQL문을 통해 영속성을 갖는 데이터를 저장, 수정 및 읽어올 수 있다.

![Untitled](https://user-images.githubusercontent.com/63430211/116891184-5f93d700-ac69-11eb-9b7d-d686b0a49b83.png)



# 1. Spring Initializr 접속 및 시작파일 다운

## 1-1 **Dependencies**

- Spring Web
- Lombok
- MyBatis Framework
- Oracle Driver
- Thymeleaf

![Untitled 1](https://user-images.githubusercontent.com/63430211/116890972-29eeee00-ac69-11eb-86d4-2398b22122d6.png)


## 1-2 applictation.yml 설정

요즘엔 applicaton.propertise 보단 .yml을 더 많이 쓴다고 하여 여기선 yml로 설정을 한다. 

(applicaton.propertise로 된 파일이름을 바꿔준다.)

Maven에서 쓰이는  pom.xml과 applicaton.propertise와 .yml  의 공통점은 초기 설정을 해준다는 것이고, 차이점은 가독성이 향상됐다고 보면 될 것 같다. 

```java
server:
  port: 8080
  
spring:
  datasource:
    driver-class-name: oracle.jdbc.driver.OracleDriver
    url: jdbc:oracle:thin:@localhost:1521:orcl
    data-username: 각자 아이디
    data-password: 각자 비번
  
mybatis:
  mapper-locations: classpath:/mapper/*.xml      # mapper.xml 파일 위치
#type-aliases-package: board.mybatis.dto.**
```

## 1-3 초기 폴더 및 클래스 설정

1. 객체를 담을 DTO 클래스
2. 결합도는 낮추고 응집도를  높이는 Service인터페이스 (메서드 이름만 가지고 있다.)
3. Service 인터페이스를 구현하여 비즈니스 로직을 구현하는  ServiceImpl.java
4. DB로부터 값을 가져오는 Mapper인터페이스
5. 데이터베이스의 SQL문을 실행시키는 Mapper.xml 파일
6. 클라이언트로 요청받은 로직수행의 값을 모델에 담아 전달하면 xxx.html이 호출되어 화면에 보여진다.


![Untitled 2](https://user-images.githubusercontent.com/63430211/116891013-3410ec80-ac69-11eb-9285-ca3d926c7b81.png)


## 1-4 CRUD TEST

1. **Oracle table 생성**

    inteliJ를 사용할 경우 Oracle Developer를 별도로 실행하지 않고 이용이 가능하다.


![Untitled 3](https://user-images.githubusercontent.com/63430211/116891034-396e3700-ac69-11eb-86ae-52c4222d2626.png)

![Untitled 4](https://user-images.githubusercontent.com/63430211/116891061-3f641800-ac69-11eb-977a-e26cf66d0d50.png)


    ```java
    create table file_boards
    (
        b_no     int not null primary key,
        title    varchar2(255),
        content  varchar2(255),
        writer   varchar2(255),
        reg_date Date
    );
    ```

2.  **BoardDTO.java**

    게시판에 들어갈 내용들의 객체들을 생성한다.

    객체의 값들은 DB상의 이름과 같으면 좋다. 그렇지 않을 경우 이름을  Alias로 별칭을 줘야한다.

     해당 객체들을 다른 레이어에서 사용할 수 있도록 Getter / Setter 메서드 및 생성자를 생성해준다. 

    여기서는 Lombok에서 지원하는 annotation을 사용하여 코드를 간결하게 하였다 

    또한 @ToString 의 경우 다음에 진행할 Test 코드 실행 시 출력되는 값을 깔끔하게 볼 수 있게끔 도와준다.

    ```java
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(of = {"b_no","title","content","writer","reg_date"})
    public class BoardDto {

        private int b_no;
        private String title;
        private String content;
        private String writer;
        private Date reg_date;
    }
    ```

3. **BoardMapper.java와 FileBoardMapper.xml 작성**

    데이터베이스와 연결과 SQL문 연산이 잘 이우어 지는지 확인을 하기 위해 데이터베이스의 SQL 쿼리문을 실행시키는 xml파일과 이 파일과 mapping 되는 BoardMapper인터페이스를 작성해야한다. 

    BoardMapper 인터페이스에는 기능들만 가지고 있으며, 구현은 하지 않는다. 

    여기서 @Mapper annotation은 해당 클래스가 mapper라고 선언하고, 이 선언을 통해 

    DAO 인터페이스와 구현체의 생성을 생략할 수 있다. 게다가 이 annotation이 있음으로 써 SQL 쿼리문을 실행시킬 수 있게 된다.

    **BoardMapper.java**

    ```java
    @Mapper
    public interface BoardMapper {

        // 읽기
        List<BoardDto> getFileBoardList(); 
        // 자세히 읽기
        BoardDto fileBoardDetail(int b_no);
        // 삽입하기
        int fileBoardInsert(BoardDto boardDto);
        // 수정하기
        int fileBoardUpdate(BoardDto boardDto);
        //삭제하기
        int fileBoardDelete(int bno);
    }
    ```

    **FileBoardMapper.xml**

    ```java
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

    <mapper namespace="com.example.boardPrac2.mapper.BoardMapper">

        <select id="getFileBoardList" resultType="com.example.boardPrac2.dto.BoardDto">
            SELECT * FROM file_board
            ORDER BY b_no
        </select>

        <select id="fileBoardDetail" parameterType="int" resultType="com.example.boardPrac2.dto.BoardDto">
            SELECT * FROM file_board
            WHERE b_no=#{b_no}
        </select>

        <insert id="fileBoardInsert" parameterType="com.example.boardPrac2.dto.BoardDto" keyProperty="b_no">
            INSERT INTO file_board (b_no, title, content, writer, reg_date)
            VALUES(AUTO_INCREASE.nextval,#{title}, #{content}, #{writer},default)

        </insert>

        <update id="fileBoardUpdate" parameterType="com.example.boardPrac2.dto.BoardDto">

            update FILE_BOARD set
            title=<if test="title != null">#{title}</if>,
            content=<if test="content != null">#{content}</if>
            where b_no=#{b_no}

        </update>
        <delete id="fileBoardDelete" parameterType="int">
            DELETE FROM file_board WHERE b_no=#{b_no}
        </delete>
    ```

4. **CRUD TEST 시작**

    TEST 코드의 중요성은 점점 부각되어져가고, 그중 가장 간단한 테스트를 진행해보려고한다. 아직은 TEST코드에 대해서 자세힌 모르지만, 추후 공부하여 업로드 하겠다. 

    **TEST class 생성**
    
    
![Untitled 5](https://user-images.githubusercontent.com/63430211/116891081-4559f900-ac69-11eb-87c8-82a42da3fa6d.png)


    ```java
    @SpringBootTest
    @Transactional
    @Rollback(value = false)
    class BoardMapperTest {

        @Autowired
        BoardMapper boardMapper;

        @Test
        @DisplayName("R test")
        void selectTest() {
            List<BoardDto> boardDto = boardMapper.getFileBoardList();
            for (BoardDto boardDtos : boardDto) {
                System.out.println("boardDto = " + boardDtos);
            }

        }

        @Test
        @DisplayName("C test")
        void insertTest() {
            BoardDto boardDto = new BoardDto();

            boardDto.setContent("test");
            boardDto.setWriter("tes");
            boardDto.setTitle("test");

            boardMapper.fileBoardInsert(boardDto);
        }

        @Test
        @DisplayName("u Test")
        void editTest() {
            BoardDto boardDto = new BoardDto();

            boardDto.setTitle("happy");
            boardDto.setWriter("dog");
            boardDto.setContent("메롱");

            boardMapper.fileBoardUpdate(boardDto);
        }

        @Test
        @DisplayName("d Tesdt")
        void deleteTest() {
            BoardDto boardDto = new BoardDto();

            boardMapper.fileBoardDetail(125);

        }

    }
    ```

    테스트를 실행 시켰을 때 아래 사진과 같이 뜬다면 대 성공!


![Untitled 6](https://user-images.githubusercontent.com/63430211/116891112-4e4aca80-ac69-11eb-8b25-1c642b03692d.png)
