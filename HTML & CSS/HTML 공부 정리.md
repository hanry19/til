# HTMl  강의 : 드림코딩 엘리

secction head 를 통해 구분

- header
- nav
- aside
- main : 여러가지 section으로 나눌 수 있다.
    - section
        - articel : 여러가지 item들을 재사용가능하게 묶어 놓은 것들
- footer

![Untitled](https://user-images.githubusercontent.com/63430211/118659503-56d60000-b828-11eb-91f9-3d0b11e7d921.png)


![Untitled 1](https://user-images.githubusercontent.com/63430211/118659554-63f2ef00-b828-11eb-8482-4007922a6d29.png)

태그는 박스가 되는 애들과 사용자에게 보여지는 애들이 있다. 

사용자에게 보여지는 것은 Block 과 Inline 두가지로 나눌 수 있다. 

- Block 레벨

    한줄에 하나씩 들어가는 것

- Inline

    공간이 허용하는 한에서  다른 태그 옆에 작성 가능

![Untitled 2](https://user-images.githubusercontent.com/63430211/118659586-6a816680-b828-11eb-88bf-debe41767373.png)

- Tag & Element

    tag는 <> 로시작해서 </>로 끝난다. 

    그리고 tag 안에 있는 애들을 Content라고 부르고 

    이 모든걸 **elemetn , node**라고 한다.

![Untitled 3](https://user-images.githubusercontent.com/63430211/118659607-6ead8400-b828-11eb-8189-e09026503b85.png)

- 같은 페이지 안에 같은 태그라도 안에 attribute 에 따라서 다르게 정의할 수 있다.

![Untitled 4](https://user-images.githubusercontent.com/63430211/118659624-72410b00-b828-11eb-8ff7-5ba01046e8fc.png)

# 연습

```html
<!-- Box vs Item -->
 
   <!-- Box -->
  <header></header>
  <footer></footer>
  <section></section>
  <div> </div>
  <span></span>
  
  <!-- Item -->
  <h1></h1>
  <button></button>
```

- <a>

    <a> 태그에 관련된 정보는 MDN 웹 사이트에서 자세히 알 수 있다.

    [https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/target](https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/target)

    ```html
        <a href="http://www.google.com" target=_blank >Click</a>
    ```

![Untitled 5](https://user-images.githubusercontent.com/63430211/118659649-7705bf00-b828-11eb-97b1-6399e88503fc.png)

   ![Untitled 6](https://user-images.githubusercontent.com/63430211/118659666-7bca7300-b828-11eb-95ef-b80b739a6515.png)
- <p> 태그 : Paragraph

    문단을 나눌 때 사용한다. 

```html
<p>this is a sentence. <b>That</b> is...</P>

<p> this is a sentence. <span>That</span>is...</p>

<p> this is a sentence. <div>That</div>is...</p>
```

```html
this is a sentence. **That** is...

this is a sentence. Thatis...

this is a sentence.

That
is...
```

- **ol, ul , li 태그 : List 만들기**

    MDN 페이지에서 해당 태그에 대해서 찾아보면 속성값이 어떤 것들이 있는지 알 수 있다. 그걸 보고 참고하자. 

    ```html
    간단하게 만드는 단축키 **ol>li*3**
    ```

    ![Untitled 7](https://user-images.githubusercontent.com/63430211/118659681-808f2700-b828-11eb-9e32-2e0376fcc4f9.png)

```html
<ol type="i" reversed>
    <li>1</li>
    <li>2</li>
    <li>3</li>
  </ol>

<ul>
    <li></li>
    <li></li>
    <li></li>
  </ul>
```

```html
1.1
2.2
3.3

*
*
* 
```

- **Input 태그**

    원하는 데이터를 사용자에게 요청하고 값을 받을 수 있다. 

    그리고 label을 태그를 같이 사용한다. 이를 통해 사용자에게 어떠한 정보를 원하는지 정확하게 얻을 수 있다. 예를들어 "name" 값을 얻고 싶다면 아래와 같이 작성

    ```html
    <label for="">Name :  </label>
      <input id="input_name" type="text">
    ```

    <label>과 <input>은 inline 속성이라 한줄에 같이 표현된다.

    그리고 input은 구분을 위해 고유한 식별자를 **id**를 통해 지정한다. 

    type 또한 수가 엄청 다양하기에 MDN 페이지를 참고하여 사용가능하다. 

    [: The Input (Form Input) element - HTML: HyperText Markup Language | MDN](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input)


> 출처

[드림코딩 by 엘리](https://www.youtube.com/c/%EB%93%9C%EB%A6%BC%EC%BD%94%EB%94%A9by%EC%97%98%EB%A6%AC)