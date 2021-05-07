# 4장. 페이징 구현 Part.2

## 1. 페이지 처리시 필요한 정보

- 현재페이지 번호(page)
- "이전", "다음 페이지" 링크 (prev, next)
- 화면에 보여지는 페이지의 시작 번호와 끝 번호 ( Start Page, endPage)

현재 페이지 번호가 3 일때 페이지는 1이고, 끝 번호는 10 .

현재 페이지 번호가 10일때 페이지는 1이고, 끝 번호는 10 .

현재 페이지 번호가 11 일때 페이지는 11이고, 끝 번호는 10 .

이러한 계산이 필요. =⇒ 끝페이지 부터 계산을 하면 간단하다.

계산방법

 3/10.0 = 0.3 ⇒ 올림 하면 1 

그리고 무조건 10단위로 처리하면 *10  ⇒ 10

11/10.0 = 1.1 ⇒ 올림 하면 2 *10 =⇒ 20

- 시작 페이지 = 마지막 페이지 번호 - 9
- 마지막 페이지  =   전체 데이터 수에 대한 작업이 필요하다.

## 2. PageDTO.java

- **필수 데이터**
    1. 현재 페이지 번호 
    2. 전체 데이터 수량

```java
@ToString
@Getter
public class PageDTO {

    private int startPage, endPage;
    private boolean prev, next;

    private int total;

// 한페이지에 무조건 10개 씩 보여준다는 확신이 없으니 
    private Criteria cri;

    public PageDTO(Criteria cri, int total) {
        this.cri = cri; 
        this.total = total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

        this.startPage = endPage - 9;
				

				//stargPage가 1보다 크다는건 이전 페이지가 있다는것.
        this.prev = this.startPage > 1;

				// total(게시물 총수량)이 120일 경우 1.0 
				//		(120 * 1.0) / 10(한페이지 게시물 수) = 12
				//		==> 12 페이지에서 끝난다. 근데 이 위에 까지 짠 코드는
			  //  마지막 페이지가 20페이지 까지 나온다
				// 그래서 if(realEnd < endPage) endPage = realEnd 해줘야 한다
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        this.endPage = realEnd <= endPage ? realEnd : endPage;
				
				// 다음 페이지로 갈 필요 있는지?
        this.next = this.endPage < realEnd;

    }
}
```

## 2. Test 진행

- **TEST CODE  ver.1**

```java
@Test
    public void testPageDTO() {
        Criteria cri = new Criteria();
        cri.setPageNum(11); //시작 페이지
																			  // TOTAL
        PageDTO pageDTO = new PageDTO(cri, 250);
        System.out.println("pageDTO = " + pageDTO);

    }

//결과

/*
현재 페이지 위치 = 11page
현재 페이지의 마지막 페이지 = 20page
이전 / 다음 링크 = true
*/

pageDTO = PageDTO(startPage=11, endPage=20, prev=true, next=true, 
total=250, cri=Criteria(pageNum=11, amount=10))

```

- **TEST CODE  ver.2**

```java
@Test
    public void testPageDTO() {
        Criteria cri = new Criteria();
        cri.setPageNum(25); //시작 페이지
																			  // TOTAL
        PageDTO pageDTO = new PageDTO(cri, 250);
        System.out.println("pageDTO = " + pageDTO);

    }

//결과

/*
현재 페이지 위치 = 25page
현재 페이지의 마지막 페이지 = **21page**
이전 링크 = true
다음 링크 = **false**
*/

pageDTO = PageDTO(startPage=21, endPage=26, prev=true, next=false, 
total=251, cri=Criteria(pageNum=25, amount=10, type=null, keyword=null))
```

## 3. Controller.java 수정

Controller.java 수정 : 결과를 화면에 전달

```java
@RequestMapping("/")
    private String BoardList(Criteria cri, Model model) {
        List<BoardDto> boardDtos = new ArrayList<>();

        boardDtos = boardService.getFileBoardList(cri);

        model.addAttribute("boardDtos", boardDtos);
        model.addAttribute("pageMaker", new PageDTO(cri, 123/*임의의 total수*/));
		
			// 아직 total수량 메서드를 구현 안했기 때문에 임의로 숫자를 준다. 

        return "fileBoard/list";
    }
```

## 4. list.html 에 페이징 추가

맨 처음 pageMaker를 (Contoller를 보면 pageMaker 라는 이름으로 속성들을 넣었다.) 꺼집어 내 보면 내용이 제대로 출력되는지 알 수 있다. 

```java
<!--      <li th:text="${pageMaker}"></li>-->
```

출력이 잘 된다면 페이징 버튼 부분을 구현해준다. 

```java
<div>
    <ul th:each="obj :${pageMaker}">
<!--      <li th:text="${pageMaker}"></li>-->
      <li th:if="${obj.prev} == true">
        <a th:href="@{/(pageNum=${obj.startPage}-1,amount=${obj.cri.amount})}"> &laquo;&laquo;</a>
      </li>

      <li th:each="idx,iterStat : ${#numbers.sequence(obj.startPage,obj.endPage)}"  th:classappend="${obj.cri.pageNum} == ${idx} ? active : null">
        <a th:href="@{/(pageNum=${idx},amount=${obj.cri.amount})}" th:text="${idx}"></a>
      </li>

      <li th:if="${obj.next} == true and ${obj.endPage > 0}">
        <a th:href="@{/(pageNum=${obj.endPage}+1,amount=${obj.cri.amount})}">&raquo;&raquo;</a>
      </li>

    </ul>

  </div>
```