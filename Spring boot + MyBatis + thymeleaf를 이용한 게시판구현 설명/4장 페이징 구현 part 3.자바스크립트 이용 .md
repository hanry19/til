# 4장 페이징 두가지방법 (업데이트)

## 1. 이벤트 처리방법

- **이벤트 미처리( a 태그에 있는 href 값에 주목하자)**

```java
<div aria-label="Page navigation example ">
  <ul class="pagination" th:each="obj :${pageMaker}" >
      <li class="page-item">
          <a class="page-link" th:href="@{/?pageNum=1}" aria-label="Previous">
              <span aria-hidden="true">처음</span>
          </a>
      </li>
      <li class="page-item" th:if="${obj.cri.pageNum} != 1">
          <a class="page-link" th:href="@{/(pageNum=${obj.cri.pageNum}-1, amount=${obj.cri.amount})}" aria-label="Previous">
              <span aria-hidden="true">previous</span>
          </a>
      </li>

          <li class="page-item" th:each="idx,iterStat : ${#numbers.sequence(obj.startPage, obj.endPage)}"  th:classappend="${obj.cri.pageNum} == ${idx} ? active : null">
              <a class="page-link" th:href="@{/(pageNum=${idx}, amount=${obj.cri.amount})}" th:text=${idx}></a>
          </li>

      <li class="page-item" th:if="${obj.cri.pageNum} != ${obj.endPage}">
          <a class="page-link" th:href="@{/(pageNum=${obj.cri.pageNum}+1, amount=${obj.cri.amount})}" aria-label="Next">
              <span aria-hidden="true">Next</span>
          </a>
      </li>
      <li class="page-item">
          <a class="page-link" th:href='@{/?pageNum={endPage}(endPage=${obj.endPage})}' aria-label="Previous">
              <span aria-hidden="true">끝</span>
          </a>
      </li>
  </ul>
</div>
```

화면에 보이는 버튼의 링크를 적용하는 것은 아래 코드와 같이 복잡하다.

![Untitled](https://user-images.githubusercontent.com/63430211/118505638-912b9880-b767-11eb-8bc3-4fcbe6f447c8.png)

```java
// 이벤트 처리방법 미적용한 href

th:href="@{/(pageNum=${obj.cri.pageNum}-1, amount=${obj.cri.amount})}"
```

그래서 javaScript를 통해서 처리하는 방법을 사용한다.

아래와 같이 **href가 값만 받을 수 있게** 설정을 한뒤 **임의의 form 태그를 작성**한다.

```java
// 이벤트 처리방법 적용한 href

<a class="page-link" th:href="${obj.cri.pageNum -1}" aria-label="Previous">
                        <span aria-hidden="true">previous</span>
</a>
```

임의 form태그는 자바스크립트에 의해 페이징 버튼을 클릭 할 경우 값을 전달받아 전송을 한다.  

```java
//임의의 form  태그
//페이징 코드 아래에 삽입

<form id="actionForm" action="/mypage" method="get">
            <input type="hidden" name="pageNum" th:value="${pageMaker.cri.pageNum}">
            <input type="hidden" name="amount" th:value="${pageMaker.cri.amount}">
</form>
```

아래는 자바스크립트 코드이다.

- **actionForm**

     form 태그의 class 명

- **.page-link**

     <a> 의 class 이름

- **preventDefault( )**

    <a> 의 기본 동작인  href를 통한 이동을 못하게 막는 역할을 한다.

- .**attr("href")**

    <a>에 있는 href의 속성의 값

- **this**.**attr("href")**

    <a> 클릭 시 href를 통해 이동을 해야하지만 preventDefault로인해 그 역할을 못하으로 href가 전달받은 값이 this가 된다. **(= targetPage)**

- **console.log(~~)**

    콘솔에 값 찍어 보는거

- **actionForm.find("input[name ='pageNum']").val(targetPage);**

    actionForm(form태그 클래스 명) 에서 ***<input name=pageNum>***의 value값을 **targetPage**로 설정한다
    (아래 자바스트립트 코드 참고)

- **actionForm.submit();**

    form 전송!

아래 코드를 주석처리하고 페이징 번호를 클릭하면 콘솔에 찍히는걸 볼 수 있다.

```java
actionForm.submit();
```

![Untitled 1](https://user-images.githubusercontent.com/63430211/118505681-9ab50080-b767-11eb-8d71-36e5e7867e60.png)


- **자바스크립트 코드**

```java
<script>
  
  var actionForm = $("#actionForm");

  $(".page-link").on("click", function (e) {
    e.preventDefault();

    var **targetPage** = $(this).attr("href");
    console.log(targetPage);

    actionForm.find("input[name ='pageNum']").val(targetPage);
    actionForm.submit();

  });
</script>
```

- **이벤트 적용 한 페이징**

```java
	<!--        페이징 시작 -->
	
	<div aria-label="Page navigation example ">
	  <ul class="pagination" th:each="obj :${pageMaker}" >
	      <li class="page-item">
	          <a class="page-link" th:href="${1}" aria-label="Previous">
	              <span aria-hidden="true">처음</span>
	          </a>
	      </li>
	      <li class="page-item" th:if="${obj.cri.pageNum} != 1">
	
	          <a class="page-link" th:href="${obj.cri.pageNum -1}" aria-label="Previous">
	              <span aria-hidden="true">previous</span>
	          </a>
	      </li>
	
	          <li class="page-item" th:each="idx,iterStat : ${#numbers.sequence(obj.startPage, obj.endPage)}"  th:classappend="${obj.cri.pageNum} == ${idx} ? active : null">
	              <a class="page-link" th:href="${idx}" th:text=${idx}></a>
	          </li>
	
	      <li class="page-item" th:if="${obj.cri.pageNum} != ${obj.endPage}">
	          <a class="page-link" th:href="${obj.cri.pageNum +1}" aria-label="Next">
	              <span aria-hidden="true">Next</span>
	          </a>
	      </li>
	      <li class="page-item">
	          <a class="page-link" th:href="${obj.endPage}" aria-label="Previous">
	              <span aria-hidden="true">끝</span>
	          </a>
	      </li>
	  </ul>
	</div>
	
	<!--        페이지번호를 클릭하면 링크를 통한게 아니라 폼태그을 서브밋 날려서 하려고 한다.
	pagenum , amount를 수정해야한다.
	
	-->
	<form id="actionForm" action="/mypage" method="get">
	  <input type="hidden" name="pageNum" th:value="${pageMaker.cri.pageNum}">
	  <input type="hidden" name="amount" th:value="${pageMaker.cri.amount}">
	</form>
	
	
	<!--페이징 끝-->
```