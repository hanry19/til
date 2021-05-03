# 3장. Thymeleaf를 이용한 view단 작성

> **추후계획**

**여기까지하면 간단한 CRUD는 마무리되고 앞으론 첨부파일 저장 및 다운로드** 

**그리고 페이징 까지구현해보겠다.**

이전 장까지 초기설정과 Controller, Service, Mapper에 대해서 작성을 하였다. 

지금까지 요약으론 

1. 클라이언트가 Controller로 요청하고
2. Controller는 요청받은 정보를 처리할 수 있는 메서드를 꺼내집어내서
3. 복잡한 로직은 Service단으로 넘겨서 Mapper를 통해 DB로부터 값을 받아
4. Model에 담아서 논리적 주소 혹은 redirect로 url을 반환한다. 

1. **Resources 패키지와 HTML 파일위치** 

    Thymeleaf는 jsp와 다르게 html파일에 코드를 입력하는 방식을 사용한다. 

    우선 java 클래스 파일 이외에는 주요 자원들은 resources 패키지를 이용하는데, 여기서도 폴더가 나뉜다.  

    1. mapper 패키지 

        이 임의로 만들어 넣었고, xml파일에서 위치에 따라 변경을 해줘야 한다. 

    2. static 패키지 

        말그대로 정적인 데이터를 넣는다. 주로 자바스크립트,CSS 파일을 넣는다.

    3. templates 패키지

        이 패키지가 클라이언트에게 나타낼 html파일을 저장하는 곳이다. 

        Thymeleaf는 스스로 해당 폴더에서 html을 찾는다. 

        ![3%E1%84%8C%E1%85%A1%E1%86%BC%20Thymeleaf%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB%20view%E1%84%83%E1%85%A1%E1%86%AB%20%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%201e9fdafb88bb42e291665fe2088d20ce/Untitled.png](3%E1%84%8C%E1%85%A1%E1%86%BC%20Thymeleaf%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB%20view%E1%84%83%E1%85%A1%E1%86%AB%20%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%201e9fdafb88bb42e291665fe2088d20ce/Untitled.png)

2. Thymeleaf사용 방법

     html에서 Thymeleaf사용을 선언을 해야만 관련 코드 사용이 가능하다.

```java
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
```

![3%E1%84%8C%E1%85%A1%E1%86%BC%20Thymeleaf%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB%20view%E1%84%83%E1%85%A1%E1%86%AB%20%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%201e9fdafb88bb42e291665fe2088d20ce/Untitled%201.png](3%E1%84%8C%E1%85%A1%E1%86%BC%20Thymeleaf%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB%20view%E1%84%83%E1%85%A1%E1%86%AB%20%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%201e9fdafb88bb42e291665fe2088d20ce/Untitled%201.png)

Thymeleaf 선언 예시

Thymeleaf 사용을 선언했으면, 이제 기존의 html 태그에 **"th:____"** 를 붙여 사용한다. 

관련코드는 Thymeleaf 공식 홈페이지를 통해 자세히 알 수 있으며, 여기서 사용 되는 코드들에 대해서는 각 html 내에 주석으로 설명을 달았다. 

또한 이 코드들을 정리하여 추후 따로 정리하여 올릴 수 있도록 하겠다!

1. **list.html**
    - 펼쳐보기

        ```html
        <!DOCTYPE html>

        <!--우선 view단에서 타임리프를 사용하겠다는 강력한 의지를 보여준다! 이 의지가 없으면, 타임리프 문법을 사용할 수 없다.-->
        <html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
        <head>
          <meta charset="UTF-8">
          <title>파일게시판-리스트페이</title>

          <style type="text/css">
            li {
              list-style: none;
              float: left;
            }
          </style>

        </head>

        <body>
        <div>

          <div style="padding: 10px; text-align: center;">
            <h1><a th:href="@{/}" style="text-decoration: none;">파일자료실</a></h1>
          </div>

          <!--/* 타임리프 관련 태그에 내용이 없어도 무적권 에러ㅇㅅㅇ */-->

          <div>
            <table>
              <thead style="text-align: center;">
              <tr>
                <th style="text-align: center;">순서</th>
                <th style="text-align: center;">작성자</th>
                <th style="text-align: center;">제목</th>
                <th style="text-align: center;">작성일</th>
              </tr>
              </thead>
              <tbody>
              <!-- th:each 는 반복출력을 지원하다.
                 th:each="data : ${boardDtos}" 이렇게 하면, 모델에 포함된 boardDtos 컬렌션 데이터가
                 data 변수에 하나 씩 포함되고, 반복문 안에서 date를 이용하여 값을 깨낼 수 있다.
                 컬렉션의 수 만큼  [ <tr> </tr> ] 이 태그를 포함하여 생성된다.
                 -->
              <!-- 리터럴 대체 [ |...| ]

                                                                                get method-->
              <tr th:each="data : ${boardDtos}" th:onclick="|location.href='@{/detail/{bno}(bno=${data.b_no})}'|">
                <td th:text="${data.b_no}" style="cursor: pointer; text-align: center;"></td>
                <td th:text="${data.writer}" style="cursor: pointer; text-align: center;"></td>
                <td th:text="${data.title}" style="cursor: pointer; text-align: center;"></td>
                <td th:text="${data.reg_date}" style="cursor: pointer; text-align: center;"></td>
              </tr>
              </tbody>
            </table>
          </div>

          <div style="text-align: right;">
            <button onclick="location.href='/write'">글작성</button>
          </div>

          <br/><br/><br/>
        </div>
        </body>
        </html>
        ```

2. **insert.html**
    - 펼쳐보기

        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

          <title>파일게시판 - 글작성</title>
        </head>

        <body>
        <div>
          <div style="padding: 10px; text-align: center;">
            <h1><a th:href="@{/}" style="text-decoration: none;">게시글작성</a></h1>
          </div>

          <div>
            <form role="form" th:object=${boardDto} th:action
                  method="post" enctype="multipart/form-data">
              <div>
                <label for="title" style="font-weight: bolder;">제목</label>
                <input type="text" th:field="*{title}" id="title" name="title"
                       placeholder="제목을 입력하세요" required="required">
              </div>
              <div>
                <label for="writer" style="font-weight: bolder;">작성자</label>

        <!--        th:field ="*{필드이름}" 을 설정하면 html필드와 폼 객체에 포함된 필드를 연결할 수 있고ㅡ
                    html 필드 값이 폼 객체의 햄당 필드로 설정된다. 반대로 폼 객체의 필드값이 Model에서 Html 필드 값을 설정된다.-->
                <input type="text" th:field="*{writer}" id="writer" name="writer"
                       placeholder="작성자를 입력하세요" required="required">
              </div>
              <div style="font-weight: bolder;">
                <label for="content">내용</label><br/>
                <textarea th:field="*{content}" id="content" name="content" rows="15" cols="50"
                          placeholder="내용을 입력하세요" required="required"></textarea>
              </div>
              <div>
                <input type="file" name="files">
              </div>
              <div style="text-align: right;">
                <input type="submit" th:value="목록" th:formaction="@{/}">
                <input type="submit" th:value="작성" th:formaction="@{/writeSub}" >
                <input type="reset" th:value="취소" th:formaction="@{/writeSub}">
              </div>
            </form>
          </div>
          <br/><br/><br/>
        </div>
        </body>
        </html>
        ```

3. **update.html**
    - 펼쳐보기

        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>파일게시판 - 내용수정</title>
        </head>

        <body>
        <div>
            <div style="padding: 10px; text-align: center;">
                <h1><a th:href="@{/}" style="text-decoration: none;">게시글 수정</a></h1>
            </div>
            <div>
                <form role="form" th:object="${boardDto}"
                      th:action method="post">
                    <div>
                        <label for="title" style="font-weight: bolder;">제목</label>
                        <input type="text" id="title" name="title"
                               th:value="${detail.title}">
                    </div>
                    <div>
                        <label for="writer" style="font-weight: bolder;">작성자</label>
                        <input type="text" id="writer" name="writer"
                               th:value="${detail.writer}" disabled="disabled">
                    </div>
                    <div>
                        <!--  [[${detail.content}]] 의 의미는 태그를 벗어나 인라인 에서 표현을 하는 방법이다.
                        기타 문자와 결합하여 사용할 수 있다.-->
                        <label for="content" style="font-weight: bolder;">내용</label><br>
                        <textarea id="content" name="content" rows="15" cols="50">
        아래는 이전에 작성한 내용 입니다.
        [[${detail.content}]]
          </textarea>
                    </div>
                    <div style="text-align: right;">

                        <input type="hidden" name="b_no" th:value="${detail.b_no}">
        <!--                이건 html 문법인지 모르곘는데,form 전체에 action 값을 지정해주니, 그거로 바로 연결이 된다? -->
                        <input type="submit" th:value="목록" th:formaction="@{/}">
                        <input type="submit" th:value="저장" th:formaction="@{/update}">
        <!--              앞서 이야기 한 것 처럼 formaction은 버튼을 눌렸을 때 전달할 URL을 입력한다. -->
                        <input type="submit" th:value="삭제" th:formaction="@{/delete/{bno}(bno=${detail.b_no})}">

                    </div>
                </form>
            </div>
            <br/><br/><br/>
        </div>
        </body>
        </html>
        ```

4. **detail.html**
    - 펼쳐보기

        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>파일게시판 - 상세페이지</title>
        </head>

        <body>
        <div>
            <div style="padding: 10px; text-align: center;">
                    <!--   th:href="@{/fileBoard/list}" 이는 타임리프의 URL 링크 표현 방식이다.    -->
                <h1><a th:href="@{/}" style="text-decoration: none;">CrazyAcade - 자료실</a></h1>
            </div>

            <div>
                <div>
                    <h4 style="font-weight: bolder;">상세내용</h4>
                    <br/>

        <!--  form  문 시작   -->
                    <!--  th:object=${boardDto}해당 form문 에  사용될 객체를 지정한다. 객체로 받아진다.
                          th:action="@{/}
                          -->       <!--="@{/fileBoard/list}"-->
                    <form role="form" th:object=${boardDto}
                          th:action  method="post">
                        <div>
                            <label style="font-weight: bolder;">제목</label>

        <!--  th:text =내용 변경. 내용의값을 변경한다. 태그안에 들어가는 텍스트 값이다.
             그러나 th:field 는 각 필드 매핑하는 역할. th:object에 설정해준 객체의 내부와 매칭  -->
                            <p th:text="${detail.title}"></p>
                            <input type="hidden" th:field="${detail.title}">
                        </div>
                        <div>
                            <label style="font-weight: bolder;">작성자</label>
                            <p th:text="${detail.writer}"></p>
                            <input type="hidden" th:field="${detail.writer}">
                        </div>
                        <div>
                            <label style="font-weight: bolder;">작성일자</label>
                            <p th:text="${detail.reg_date}"></p>
                        </div>
                        <div>
                            <label style="font-weight: bolder;">첨부파일</label>
                            <p>
                                <a th:if="${file}" th:href="@{/fileBoard/fileDown/{bno}(bno=${file.b_no})}">
                                    [[${file.fileoriginname}]]
                                </a>
                            </p>
                        </div>
                        <div>
                            <label for="" style="font-weight: bolder;">내용</label>
                            <p th:text="${detail.content}"></p>
                            <input type="hidden" th:field="${detail.content}">
                        </div>

                        <!--  th:value="${item.id}" == 모델에 있는 정보를 획득하고 프로펄티 접근법으로 출력한다.
                                           value 속성을  th:value속성으로 변경
                              th:formaction  = 버튼 태그의 formation 속성은 form data가 서버로 제출 될 때 도착할 URL 의미
                                                type 속성이 submit인 경우에만 사용 가능.
                                           -->
                        <div style="text-align: right;">
                            <input type="submit" th:value="목록" th:formaction="@{/}">
                            <input type="submit" th:value="수정" th:formaction="@{/edit/{bno}(bno=${detail.b_no})}">
                            <input type="submit" th:value="삭제" th:formaction="@{/delete/{bno}(bno=${detail.b_no})}">
                        </div>
                    </form>

                </div>

            </div>

            <br/><br/><br/>

        </div>
        </body>
        </html>
        ```