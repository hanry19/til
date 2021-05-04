# 4장. 페이징 구현 Part.1

## 1. Critaria.java생성

페이징 처리를 위해 DTO 패키지에 Criteria.jave를 생성한다.

```java
@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;   // 보여줄 페이지 번호 = 현재 페이지
    private int amount;   // 페이지당 보여줄 게시글의 개수

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    // loop를 돌리 기 위해 배열이 필요하다.
    public String[] getTypeArr() {
        // string 배열이 null이 안나오게 하려고 빈 배열을 준다.
        return  type == null? new String[] {} : type.split("");
    }
}
```

## 2. BoardMapper.java 인터페이스 메서드 추가

파라미터를 받아서 sql을 처리해야 함으로 List를 만들어 Criteria 파라미터를 받는다. 

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
    int fileBoardDelete(int b_no);

    //페이징
    List<BoardDto> getListWithPaging(Criteria cri);

    // 테이블 카운트
    int getTotalCount(Criteria cri);
```

## 3. FileBoaradMapper.xml 추가

<![CDATA[ ] ]>  : C DATA라고 부른다.

xml은 태그로 이루어져 있다. 하지만 SQL문 중간에 부등호 (<,> )등이 들어가면 xml태그와 헤깔리기 때문에 CDATA 로 감싸 줌으로 써 하난의 수식임을 말한다

```java
<select id="getListWithPaging" resultType="com.example.boardPrac2.dto.BoardDto">
        <![CDATA[
    select * from (
          select /* index_desc(file_board pk_board)   */ rownum rn, b_no, title, WRITER, reg_date
          from FILE_BOARD
          where 
          B_NO > 0 and ROWNUM <= (2 * 10)
      )
    where rn > (2-1) * 10
        ]]>
    </select>
```

## 4.Test 진행

지금까지 작성한 **기본 틀**이 잘 , 제대로 동작하는지 테스트를 한다. 

결과를 보면 10개가 제대로 출력되고 있는걸 볼 수 있다.

```java
@Test
@DisplayName("pagingTest")
    public void testPaging() {
		// 1page에 10개
        Criteria cri = new Criteria();
        List<BoardDto> list = boardMapper.getListWithPaging(cri);
				
    }
```

결과가 제대로 나오니 다시 xml파일을 수정한한다.

```java
<select id="getListWithPaging" resultType="com.example.boardPrac2.dto.BoardDto">
        <![CDATA[
    select * from (
          select /* index_asr(file_board pk_board)   */ rownum rn, b_no, title, WRITER, reg_date
          from FILE_BOARD
          where  B_NO > 0 and ROWNUM <= (#{pageNum} * #{amount})
      )
    where rn > (#{pageNum}-1) * #{amount}
        ]]>
    </select>
```

## 5.  service interface & java

위와 같이 테스트가 끝났으니까, Controller와 service에 적용을 해야한다.

- BoardService.java

    ```java
    List<BoardDto> getFileBoardList(Criteria cri);
    ```

- BoardSericeImple.java

    ```java
    @Override
        public List<BoardDto> getFileBoardList(Criteria cri) {
            return boardMapper.getListWithPaging(cri);
        }
    ```

## 6. BoardController.java

```java
@RequestMapping("/")
    private String BoardList(Criteria cri, Model model) {
        List<BoardDto> boardDtos = new ArrayList<>();

        boardDtos = boardService.getFileBoardList(cri);

        model.addAttribute("boardDtos", boardDtos);
       

        return "fileBoard/list";
    }
```