# 2장 Controller /Service / Mapper 작성

## 1장 요약

1. DTO 객체 생성
2. Mapper 인터페이스와 Mapper xml파일 생성
3. test코드 진행

## 1. Controller.java 작성

Controller에는 클라이언트로부터 요청(Request) 를 받아 처리반(Service)으로 전달하고 그 결과를 응답(response)하는 역할을 한다. 

여기서 '@Autowired 처리반' 을 이용하여 service 의존성을 주입한다. 

앞서 말한거 처럼 @Autowired 를 사용하므로써 service객체를 싱글톤으로 사용이 가능해 진다. 

코드를 보면 @GetMapping, @PostMapping, @RequestMapping 이렇게 세가지를 볼 수 있다. 자세한 설명은 나중에 REST API를 다루며 다시 작성하겠다. 

간단하게 말하면 

@GetMapping은 클라이언트가 정보를 가지고 있지 않는 상태에서 서버에게 값을 요청할 때 사용된다.

@PostMapping은 클라이언트가 값을 가지고 (작성하고) 서버에 전달할 때 사용

@RequestMapping은 위 두가지 기능을 다 지니고 있다. 

또한 return값을 보게되면 'redirect : / ' 와 'fileBoard/list'을 볼 수 있다.

redirect에는 URL 값이 들어가고 (redirect: URL) 클라이언트가 특정 URL을 요청하면 서버에서 다시 해당 서버로 요청을 보내는 것

"fileBoard/list" 는 논리적 주소 이다. 

1. **controller.java**

    ```java
    @Controller
    public class BoardController {

        // 스프링 빈 컨테테이너에 BoardService 클래스를 주입하여 하나의 객체를 공유하는 형식으로 사용한다 => single tone 방식
        // 스프링 프레임워크는 기존의 싱글톤 방식의 단점을 보완하여 사용할 수 있고, 그로인해 불필요한 객체 생성을 방지한다.
        @Autowired
        BoardService boardService;

        @RequestMapping("/")
        private String BoardList(Model model, HttpServletRequest request) {
            List<BoardDto> boardDtos = new ArrayList<>();

            // boardDtos(ArrayList) 는 서비스인터페이스의 fileBoardList를 불러온다.
            // boardService 는 인터페이스로 해당 인터페이스를 구현하는 클래스의 메서드의 getFileBoaradList를 실행시킨다.
            // 실행한 메서드는 BoardMapper 인터페이스의 getFileBoardList를 반환하며
            // 이 mapper는 mapper.xml과 매핑 되어 잇어, sql문을 실행시켜 값을 가져온다.

                /*     @Override
                          public List<BoardDto> getFileBoardList() {
                          return boardMapper.getFileBoardList();
                     }*/

            // 그러니까 ArrayList인 boardDtos는 db에서
            // 'getFileBoardList()"라는 ID를 가진 녀석의 쿼리문을 실행시킨 값이다!
            boardDtos = boardService.getFileBoardList();

            // 그리고 db에서 가져온 값을 model의 속성에 "boardDtos" 라는 이름으로 추가
            // 이 모델은 view단으로 연결이 된다!
            model.addAttribute("boardDtos", boardDtos);

            // 그리고 논리적 주소를 반환한다.
            // 이 의미는 "/" 로 호출이 오면 "fileBoarad/List"로 연결해주는 것이다.
            return "fileBoard/list";
        }

    // 클라이언트가 게시판에서 디테일한 정보를 요청할 때 전달되는 url은 "/detail/b_no" 이다.
    //    여기서 b_no라는 것은 쿼리문으로 게시글 번호마다 다르게 측정될 것이다
    //    그리고 getMapping을 하는 이유 역시 서버에 데이터를 요청하는 것이기 때문이다.
    //    get /post 같은 rest API도 추후 정리하여 올리겠다.

        @GetMapping("/detail/{b_no}")

    //    @PathVariable은 경로 변수이다.
    //    @RequsrtMapping은 URL 경로를 템플릿 화 할 수 있는데, @PathVariable 을 사용하면
    //    매칭되는 부분을 편리하게 조회할 수 있다. 또한 이름과 파라미터 이름이 같으면 생략이 가능하다. (다중 적용가능)

        private String boardDetail(@PathVariable("b_no") int b_no, Model model) {

            // 클라이언트가 선택한 url의 쿼리문 중 "b_no" 에 해당하는 값의 이름을 파라미터와 동일하게 하여 가져온다.
            // 그리고 이전 list페이지에서 가지고 있는 model을 받아 "detail" 이란 객체이름으로 한 속성을 넣는다.
            // 이 역시 로직을 위해 service의 detail메서드로 전달하고,
            // 데이터베이스로부터 쿼리문을 실행시킨 값을 받아 리턴 받는다.
            // 그리고 "fileBoard/detail" 이라는 논리적 주소를 리턴한다.

            model.addAttribute("detail", boardService.fileBoardDetail(b_no));

            return "fileBoard/detail";

        }

        // 여기서 postMapping인 이유는 detail 페이지에서 수정 버튼을 눌리면 해당 페이지의 정보를 가져온 뒤
        // 수정을 진행하기 때문이다. get은 내가 정보가 없어도 받을 수 있고, post 는 가지고 잇는 정보를 주고, 값을 받는 형식임을 명심하자.4
        // 그래서 detail페이지 중 "수정" 버튼에 달아놓은 링크에 따라 아래 메서드가 호출된다. 이 역시 @PathVariable(경로변수)
        // 템플릿화 된 URL 에서 일치하는 부분의 값을 가져올 수 있다.
        @PostMapping("/edit/{b_no}")
        private String boardEdit(@PathVariable("b_no") int b_no, Model model) {
            model.addAttribute("detail", boardService.fileBoardDetail(b_no));
            return "fileBoard/update";
        }

        // 기존의 내용을 수정하는 거니까 post mapping이다. 게다가 model attribute까지 가져오니 빼박!
        @PostMapping("/update")
        private String boardEditDone(BoardDto boardDto) {
    //        @ModelAttibute는 이름을 붙혀서 사용할 수  있는데 생략도 가능하다.
    //        private String boardEditDone(@ModelAttribute BoardDto boardDto) ==> 생략 ㄴㄴ
    //        생략 시 모델에 저정될 떄 클래스 명을 사용한다.. 이때 클래스의 첫 글자만 소문자로 변경해서 등록한다 .

            boardService.fileBoardUpdate(boardDto);

            int bno = boardDto.getB_no();
            String b_no = Integer.toString(bno);

            // 리다이렉트 할 때 논리적 주소를 적는게 아니라  URL을 적는 것이다.
            return "redirect:/detail/" + b_no;
        }

        @PostMapping("/delete/{b_no}")
        private String fileDelete(@PathVariable("b_no") int b_no) {
            boardService.fileBoardDelete(b_no);

            return "redirect:/";
        }

        @GetMapping("/write")
        private String fileWrite(@ModelAttribute BoardDto boardDto) {
            return "fileBoard/insert";
        }

        @PostMapping("/writeSub")
        private String fileWriteSub(@ModelAttribute BoardDto boardDto) {
            boardService.fileBoardInsert(boardDto);
            return "redirect:/";
        }
    }
    ```

## 2. Service 작성

1. **BoardService.java 인터페이스 작성**

    ```java
    public interface BoardService {

        List<BoardDto> getFileBoardList(); //게시글 리스트 출력
        BoardDto fileBoardDetail(int b_no); //게시글 세부내용 보기
        int fileBoardInsert(BoardDto boardDto); //게시글 생성
        int fileBoardUpdate(BoardDto boardDto); //게시글 수정
        int fileBoardDelete(int b_no); //게시글 삭제

    }
    ```

2. **BoardServiceImpl.java 작성**

    클라이언트로부터 요청을 받으면  처리반인 service단으로 전달된다.

    전달된 각 파라미터 들은 메서드에 맞게끔 들어가고, 이 Service는 주입받고 있는 BoardMapper 인터페이스에 있는 메서드로부터 받는 값을 넘긴다.

    그럼 BoardMapper는 db에게 접근 가능한 Mapper.xml파일에게 값을 요청하고 sql문을 실행시켜 값을 받고, 그 값을 반환하여 conroller에게 넘긴다. 

    ```java
    @Service
    public class BoardServiceImpl implements BoardService {

        @Autowired
        BoardMapper boardMapper;

        @Override
        public List<BoardDto> getFileBoardList() {
            return boardMapper.getFileBoardList();
        }

        @Override
        public BoardDto fileBoardDetail(int b_no) {
            return boardMapper.fileBoardDetail(b_no);
        }

        @Override
        public int fileBoardInsert(BoardDto boardDto) {
            return boardMapper.fileBoardInsert(boardDto);
        }

        @Override
        public int fileBoardUpdate(BoardDto boardDto) {
            return boardMapper.fileBoardUpdate(boardDto);
        }

        @Override
        public int fileBoardDelete(int b_no) {
            return boardMapper.fileBoardDelete(b_no);
        }
    }
    ```

3. **Mapper.xml**

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
    </mapper>
    ```

여기까지하면 view단을 제외한 구성들은 다 작성하게 된다. 

다음번엔 view단 작성을 해보겠다