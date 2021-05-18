# CSS 입문 : 드림코딩 엘리

# 1. CSS : Cascding Style Sheets

세부적인 정의가있다면 그걸 쓰고 없다면 다음으로 넘어간다

웹사이트 스타일링할 때 세가지로 나누어 생각할 수 있다. 

1. Author style 

    우리가 style 하는 것 ⇒ CSS

2. User style

    사용자의 취향에 따라 브라우저에서 styling 을 바꾸는 것 

3. Browser

    브라우저에서 기본적으로 지정된 스타일

우리가 정의한 스타일을 최우선으로하고 사용자가 정의한 스타일 , 브라우저 스타일로 간다. 

이 연결을 끊어내는 것

 **! important  ⇒ " 내가 제일 중요해" ⇒ 좋지 않다.**

html배울 때 커다란 body안에 sesstion으로 나눠서 해야한다 ⇒ labling이 쉽기 때문이다.

labling이 잘되어 있으면 css꾸밀 때 어떤 녀석을 골라서 꾸밀지 쉬워진다.

## 1-1 **선택자 : selectors**

- 어떤 태그들을 고를건지 선택하는 것이고 아래와 같다.

**Universal      *          : 전체범위**

**type             Tag      :  Tag에 적용**

**Id                 #id       :  선택된 id에 적용**

**Class            .class    : class에 적용**

**State            :**

**Attribute     [ ]**

## 1-2 Display : block , inline. inline-block

기본적으로 div와 span은 block과 inline 속성을 가지고 있는데 이를 display 설정을 통해 임의로 변경할 수 있다. 

block level로 지정할 경우 contents의 내용이 없어도 "박스" 이기 때문에 표현이 되지만, inline level로 지정된 경우 박스 보다는 박스 안 "물건"에 초점을 맞춘다

1.  **<div> :** display : block 
2. **<span> :** display : inline

- **inline = 물건**

    공간이 되는 한 한줄에 여러개의 물건을 담는다.

- **block = 상자**

    한줄에 상자 하나

- **inline-block**

    한줄에 상자를 공간이 되는 한 담는다.

## 1-3 Position

position은 기본값으로static을 가지고 있고, html상의 순서대로 브라우저에 보여주는 것

article은 body안에 담겨져 있기데 페이지 상에 제일 위쪽에 보여진다.

그럼 container의 위치를 바꾸기 위해서는 position을 relative로 바꿔준다. 

```html
.box {
  background : blue;
  left : 20px;
  top:20px;
  **position: absolute;**
}
```

**absolute** 

- item과 가장 가까이 있는 박스 안에서 위치 변경이 일어 나는 것 (상자 내 절대적)

**relateive**

- 현 위치에서 상대적으로 움직임 (상대적)

**fixed** 

- 상자안에서 완전히 벗어나 윈도우에서 움직이는 것!  (윈도우 절대적)

**sticky** 

- 원래 있어야할 자리에 그대로 잇으며, 스크롤링 하여도 그 자리 그대로 붙어 있는 것

항상 호환성 여부를 확인해야 한다. 

# 2. FLEX BOX

행 열을 자유자제로 바꿀수 있는 **FLEX BOX**  ⇒ **CSS의 꽃**

박스가 작아지면 어떻게 작아지고 커지면 어떻게 커질건지 자유롭게 배치 가능하다. 

이전엔 모든 브라우저에서 호환 가능한 LAYOUT을 만들기 위해 **Position, float, table**을 사용 했으며, 구조가 복잡했었다. 그리고 박스(div) 안 item들을 수직정렬 및 동일한 높이, 사이즈로 배치하는 것들이 많이 까다로웠다. 

**이를 보완하기 위해 나온게  FlexBox이다.** 

![Untitled 7](https://user-images.githubusercontent.com/63430211/118659681-808f2700-b828-11eb-9e32-2e0376fcc4f9.png)
![Uploading Untitled.png…]()


- **Flex Box 기본 HTML 구조**

    ```html
    <div class="container">
    	<div class="item">helloflex</div>
    	<div class="item">abc</div>
    	<div class="item">helloflex</div>
    </div>
    ```

이전에 float을 설명하면 이 것의 원래 목적은 이미지와 text들을 어떻게 배치할지 정의하기 위해 나타난 것 그래서 float:left 하면 이미지가 왼쪽에 배치 되었다. 

![Untitled 1](https://user-images.githubusercontent.com/63430211/118659797-9866ab00-b828-11eb-8ad5-25980472085e.png)


예전에는 css에 layout을 할 수 있는 기능이 없었기에 float을 써서 정렬을 하였다. 하지만 이는 float의 원래 목적에 맞지 않는다. 

FlexBox는 두가지만 이해하면 큰 그림을 이해할 수 있다. 

## 1. 중심축과 반대축

수직과 수평선이 있다고 할 때 수직을 중심으로 지정하면 수평은 반대축이 된다. 

1. 왼쪽에서 오른쪽으로 정렬 된다 ⇒ **수평 축 : 메인축** **(main axis)**

    수직 방향 ⇒ **반대축 (cross axis)**

![Untitled 2](https://user-images.githubusercontent.com/63430211/118659837-a0bee600-b828-11eb-9968-1d2c94f8f4ec.png)

2. 반대로 위에서 아래로 정렬된다. ⇒ **수직축 : 메인축** **(main axis)**

    수평방향 ⇒ **메인축**

   ![Untitled 3](https://user-images.githubusercontent.com/63430211/118659873-a74d5d80-b828-11eb-93ac-8e4183ecbb8b.png)

### 2. container와 item들의 속성값

1. **Container 속성값**
    - **display**

        contatiner에게  flexBox를 적용 시킨다.

        적용시킨 아이템들은 아래와 같이 배치되고 컬럼의 높이가 자동으로 동일해진다.

        ```html
        .container {
        	display: flex;
        	/* display: inline-flex; */
        ```

      ![Untitled 4](https://user-images.githubusercontent.com/63430211/118659903-acaaa800-b828-11eb-83ef-7b376f938aa3.png)
    - **flex-direction**

        기본값은 row이다 

        ```html
        flex-direction: row; 왼쪽에서 오른쪽
        flex-direction: row-reverse;  오른쪽에서 왼쪽
        flex-direction: column;    위에서 아래로
        flex-direction: column-rever 아래에서 위로
        ```

    - **flex-wrap**

        기본값 nowrap일 때 아이템들이 많아지거나 좁아지더라도 item들이 한줄에 붙어 있는다. 하지만 wrap으로 바꿔줄 경우 자동으로 item들의 줄 바꿈을 볼 수 있다.

        ![Untitled 5](https://user-images.githubusercontent.com/63430211/118659925-b03e2f00-b828-11eb-82f1-3360d38e8deb.png)
       ![Untitled 6](https://user-images.githubusercontent.com/63430211/118659948-b502e300-b828-11eb-9d2a-84e0aefb08cd.png)

    - **flex-flow**

        위 두가지 방법(flex-direction , flex-wrap)을 **한줄로 작성하여 사용하는 것**이다. 

        ```html
        flex-flow : column nowrap;
        ```

    - **justisfy-content**

        **중심축(main axis)**에서 cotainer 내의 item들을 어떻게 배치할 것인지 결정 하는 것

        1. **flex-start (기본값)** 

            중심축 = 수평 일 경우 : 왼쪽 ⇒ 오른쪽

            중시축 = 수직 일 경우 : 위 ⇒ 아래

        2. **flex-end** 

            중심축이 수평일 경우 :  오른쪽 쪽 정렬

            중심축이 수직일 경우 : 아래 정렬 

        3. **center**

            아이템들을 center로 맞춰준다.

        4. **space-around** 

            박스를 둘러싸 공간을 만들어주는 것 . 그래서 제일 왼쪽과 오른쪽은 박스가 하나임으로 박스 사이에 있는 공간들의 절반크기

          ![Untitled 7](https://user-images.githubusercontent.com/63430211/118659973-ba602d80-b828-11eb-8e76-a6e6d691ee71.png)
        5. **sapce-evenly**

            아이템들 사이에 똑같은 간격을 넣어주는 것.

          ![Untitled 8](https://user-images.githubusercontent.com/63430211/118659991-be8c4b00-b828-11eb-88e2-dfda74e81739.png)

        6. **space-between** 

            왼쪽과 오른쪽을 벽에 붙이고 공간을 나누는 것

           ![Untitled 9](https://user-images.githubusercontent.com/63430211/118660007-c3e99580-b828-11eb-9594-640bf977e04d.png)

           ![Untitled 10](https://user-images.githubusercontent.com/63430211/118660063-cf3cc100-b828-11eb-8104-cc793937ecbc.png)


    - **align-items**

        **justisfy-content가 중심축(수평)에서 item 들을 어떻게 배치**할 것인지 결정하는 것이면 **align-items는 반대축(수직)에서 item들을 배치하는 것**

        1. **center** 

            상자들을 수직적으로 중심에 두는 것.

        2. **baseline**

            만약 item1에 **padding이 들어가 text위치가 바꼇을 때 text가 균등하게 만들어 준다**.

            ```html
            aligh-items: baseline;
            ```

            ![Untitled 10](https://user-images.githubusercontent.com/63430211/118660063-cf3cc100-b828-11eb-8104-cc793937ecbc.png)
![Untitled 11](https://user-images.githubusercontent.com/63430211/118660086-d49a0b80-b828-11eb-895c-f54cfcfea9c3.png)


    - **align-content**

        **justisfy-content은 중심축(수평)에서 item을 배치**한다면 **align-content은 반대축(수평)에서 item을 배치**한다. 

        ```html
        align-content : space-between
        ```

2. **item 속성값**
    - **order**

        item 들 순서를 지정해준다. 현업에서 사용 안함 

        ```html
        .item1{
          background: #ef1a9a;
          order: 2;
        }
        .item2{
          background: red;
          order:1;
        }
        .item3{
          background: blue;
          order:3;
        }
        ```

  ![Untitled 12](https://user-images.githubusercontent.com/63430211/118660098-d82d9280-b828-11eb-9966-7b3a61704e57.png)

    - **flex-grow**

        flex-grow를 쓰지않으면 자신의 고유한 크기를 유지하려고 한다. 

        하지만 이걸 쓰면 contatiner를 가득 채우려고 아이템들이 늘어나게 된다

        기본값은 0 여서 가득채우려는 노력을 하지 않는다.  

        item 1,2,3 에 각각 flex-grow를 1로 지정해주면 1:1 비율로 커지지만

        2,1,1 로 지정할 경우 item 1은 두배의 크기로 커지게된다. 

        ```html
        .item1{
          background: #ef1a9a;
          flex-grow: 1;
        }
        .item2{
          background: red;
          flex-grow: 1;
        }
        .item3{
          background: blue;
          flex-grow: 1;
        }
        ```

    - **flex-shrink**

        container가 작아질 때 어떻게 행동할지 지정하는 것이다. 

        기본값은 0이고 container의 사이즈가 변할 때 어떠한 비율로 작이질지 지정해준다. 

    - **flex-basis**

        item들이 공간을 얼마나 차지해야하는지 세부적으로 명시하게끔 도와준다.

        기본값은 auto이다. auto로 할 경우 grow나 shrink에 지정된 값으로 변형된다. 

        하지만 grow, shrink를 쓰지않고 %를 지정해 놓으면 커지나거 작아질 때 지정한 %로 적용이 된다.

        ```html
        .item1{
          background: #ef1a9a;
          flex-basis : 60%
        }
        .item2{
          background: red;
          flex-basis : 30%
        }
        .item3{
          background: blue;
          flex-basis : 10%
        }
        ```

    - **align-self**

        container level에서는 justisfy-content, align-items, align-contents로  아이템들을 골고루 배치할 수 있다면 align-self는 item 별로 item 들을 정렬할 수 있다. item 하나만 center에 맞출 수 있다. item 하나만 가운데 정렬을 하고 싶을 때 사용할 수 있다. 

        ```html
        .item1{
          background: #ef1a9a;
          flex-basis : 60%;
          **align-self : center;**   
        }
        ```

         

> 출처

[이번에야말로 CSS Flex를 익혀보자](https://studiomeal.com/archives/197)

[드림코딩 by 엘리](https://www.youtube.com/c/%EB%93%9C%EB%A6%BC%EC%BD%94%EB%94%A9by%EC%97%98%EB%A6%AC)