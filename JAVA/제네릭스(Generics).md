# [남궁성] 제네릭스(Generics)

- **제네릭스 (Generics) 란?**
    - 컴파일 시 타입을 체크해 주는 기능(compile-time type check)

        그간 타입을 체크하는데 한계가 있었다.

        ArrayList는 object 배열을 가지고 있기에 모든 종류의 객체가 저장 가능.

        그런데 내가 TV만 저장 하고 싶다면?

        제네릭스 이전에는 다른게들어와도 잡아 낼 수 없었다. 

        제네릭스 이후에는 TV만 들어오게 한다.

    ![Untitled](https://user-images.githubusercontent.com/63430211/119673395-48b26000-be76-11eb-9fac-582016d5110e.png)

    - 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄여 준다.

        원래 꺼낼 때 object 타입을 반환하니까 형변환이 필요했었는데, 지금은 들어 있는걸 알기에 형변환 안해도 된다.

        **그렇기에 코드가 간결해 진다 !!**

     ![Untitled 1](https://user-images.githubusercontent.com/63430211/119673404-4a7c2380-be76-11eb-85fc-e81964e27252.png)

        - 제레닉스의 장점
            1. 타입 안정성 제공
            2. 타입체크와 형 변환을 생략할 수 있어 코드가 간결

        ```java
        public class Main {

            public static void main(String[] args) {
        	// write your code here
                ArrayList list = new ArrayList();
                list.add(10);
                list.add(20);
                list.add("30"); // string 추가

        //        그럼 꺼낼 떄

        //        Integer i = list.get(2); ==> 이대론 컴파일 에러가 있어서 형변환을 한다.
                Integer i = (Integer) list.get(2);
              
          // list.get(2)를하면 object 타입을 반환한다.그리고 Integer로 변환하는건 괜찮다라고 인식한다.
          
         //ClassCastException ==> 형변환 에러 ( 실행 시 발생하는 에러)
        // ==> 이걸 컴파일러의 한계 라고 한다.

                // 그럼 실행 시 발생하는 에러를 어떻게 하면 컴파일 시 알 수 있을까 ? 해서 나온게 제네릭스

                ArrayList<Integer> list1 = new ArrayList();
                 // 그래서 여기에  "나는 integer 만 저장할래" 라고 말함
        				// 만약 이전 처럼 모든 걸 다 받고 싶으면 <Object>로 해주면 된다.

                list1.add(10);               // 컴파일러에게 더 많은 정보는 주는 것이다.
                list1.add(20);               // 얘네들은 auto boxing 이 되므로 괜찮다.
        //        list1.add("30");            // 에러
                list1.add(30);            // 타입 체크가 강화 되었다.

                Integer ii = list1.get(2);     // 꺼낼 때도 integer로 형변환 해야하지만
                                              // integer 인걸 이미 알기에 생략 가능

                System.out.println("list = " + list);

            }
        }
        ```

- **타입 변수**
    - 타입 변수 란, 제네릭 를 작성할 때 Object 타입 대신 타입 변수(E)를 선언해서 사용

        ![Untitled 2](https://user-images.githubusercontent.com/63430211/119673413-4bad5080-be76-11eb-9cfa-8f82c9c773fb.png)

        ArrayList안에 있는 object들을 ㅏ앨와 같이  바꾸는것 =⇒ 타입 변수 

        타입변수 E 를 선언하고 obejct를 전부다 E 로 바꿈 =⇒ 제네릭 클래스

        오브젝트를 포함한 클래스가 

         

    - 타입 변수에 대입하기

        객체 생성 시, 타입변수 (E) 대신 실제 타입(TV)를 지정 대입

        ```java
        ArrayList<Tv> tvList = new ArrayList<Tv>();
        (참조변수)                         (생성자) 는 타입 이 일치해야 한다.

        // 이렇게 되면 아래 코드의  E 에 Tv가 대입되어 들어간다
        // 타입변수에 따라 계속 변한다.

        public class ArrayList<E> extends AbstractList<E> {
        		private trasient E[] elementData;
        		public boolean add(E o) { asdasdasd}
        		public E get(int index) { asdasdad }
        ...
        } 
        ```

        타입 변수 대신 실제 타입이 지정되면 **형변환 생략 가능**!!

- **제네릭 타입과 다형성**
    - 참조 변수와 생성자의 대입된 타입은 일치해야한다.

        ```java
        List<Tv> list = new ArrayList<Tv>();    // 다형성 ㅇㅋ!!
        List<Tv> list = new LinkedList<Tv>();   // 다형성 ㅇㅋ!!

        ```

    - 매개변수의 다형성도 성립

        product의 자손들도 ㅇㅋ 이다 

        왜냐면  E에 PRODUCT가 대입되면 Product와 그의 자손이 들어갈 수 있다.

       ![Untitled 3](https://user-images.githubusercontent.com/63430211/119673423-4cde7d80-be76-11eb-899c-1d14c63cda60.png)

- **Iterator<E>**
    - 클래스를 작성할 때 object 타입 대신 T와 같은 타입 변수를 사용

        일반 클래스일 떄 제네릭 클래스로 바뀌면서 타입 변수로 선업하고 오브젝트가 타입변수 e로 바꼈다

     ![Untitled 4](https://user-images.githubusercontent.com/63430211/119673427-4d771400-be76-11eb-86cc-81324bc9218f.png)

        ```java
        class Student{
            String name = "";
            int ban;
            int no;

            public Student(String name, int ban, int no) {
                this.name = name;
                this.ban = ban;
                this.no = no;
            }
        }
        public class Main {

        		public static void main(String[] args) {
        		        ArrayList<Student> list = new ArrayList<Student>();
        		        list.add(new Student("ㅁㄴㅇ", 1, 1));
        		        list.add(new Student("지코바", 2, 3));
        		        list.add(new Student("먹고시다", 3, 4));
        		
        		//        Iterator it = list.iterator();
        		        Iterator<Student> it = list.iterator(); 
        						 // 제네릭을 사용하므로 형변환 생략

        		        while (it.hasNext()) {
        		//            Student s = it.next();  // 에러, 형변환 필요

        		/*            Student s = (Student) it.next();
        		            System.out.println("s.name = " + s.name); */

        		            // 제네릭을 사용하므로 아래와 같이 코드 간결화도 가능
        		            System.out.println(it.next().name);
        		        }
        		    }
        }
        ```

- **HashMap <K, V>**
    - 여러 개의 타입 변수가 필요한 경우 , 콤마()를 구분자로 사용

        key 와 value 값이 다를 수 있다! key의 타입이 string이고 value가 student 타입!

      ![Untitled 5](https://user-images.githubusercontent.com/63430211/119673436-4ea84100-be76-11eb-9796-bc82a3daccaf.png)

        그래서 K 대신 STRING V 대신 STUDENT 넣으면 아래와 같다. 

    ![Untitled 6](https://user-images.githubusercontent.com/63430211/119673444-50720480-be76-11eb-9958-72dc44631ba2.png)

        ```java
        class Student{
            String name = "";
            int ban;
            int no;
            int kor;
            int eng;
            int math;

            public Student(String name, int ban, int no, int kor, int eng, int math) {
                this.name = name;
                this.ban = ban;
                this.no = no;
                this.kor = kor;
                this.eng = eng;
                this.math = math;
            }
        }

        public class Main {

            public static void main(String[] args) {
                HashMap<String, Student> map = new HashMap<String, Student>();
                map.put("자바왕", new Student("자바왕", 1, 1, 1123, 123, 123));
        //                K = string   V = Student

        //        꺼낼 때
                Student s = map.get("자바왕");
                System.out.println(map);
            }
        }
        ```