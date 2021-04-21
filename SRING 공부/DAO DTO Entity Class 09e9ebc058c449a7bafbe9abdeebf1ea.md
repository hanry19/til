# DAO / DTO / Entity Class

## 1. DAO (Data Access Object) - repository package

- **실제 DB에 접근하는 객체**
    - DB를 사용해 데이터 조회 , 조작 하는 기능을 담당하는 것들!
    - Persistence Layer (DB에 data를 CRUD하는 계층)

- **Service와 DB를 연결하는 고리 역할**
    - 작성한 SQL문을 사용하여 DB에 접근한 후 적잘한 CRUD API를 제공한다
    - Domain Logic(비즈니스 로직이나 DB와 관련없는 코드들)을 persistence mechanism과 분리하기 위함
    - 분리하는 이유

        HTTP Request를  Web Application이 받으면 Thread를 생성한다. 하지만 비즈니스 로직이 DB로부터 데이터를 얻어오기 위해 매번 Driver를 로드하고 Connection 객체를 생성하면 엄청 많은 커넥션이 일어나므로 **DAO를 하나 만들어 DB전용 객체로만 쓰는것 ⇒ 부담감소**

- **Spring에서 DAO는?**
    - 우선 spring은 싱글톤 패턴을 권장한다.
    - spring에서 관리되는 싱글톤 패턴 =  bean. 그러니까 **Spring에서 DAO는 @Repository annottion으로 정의 한다.** 그리고 이 class 선언 시 바로 @Repository annotation을 사용해도 되지만 메서드 header 만 정의한 **interface를 정의하고 이것을 구현한 class에 annotation을 붙여 사용한다! ⇒**  확장성과 유연성이 높아지기 때문

    ```java
    UserDao.java

    		public interface UserDao {
    		    /**
    		     * user 테이블에서 모든 유저의 정보를 가져온다.
    		     * 
    		     * @return 모든 유저의 정보
    		     */
    		    public List<User> getUsers();
    		}

    UserDaoImple.java

    		@Repository("userDao")
    		public class UserDaoImpl implements UserDao {
    		    @Override
    		    public List<User> getUsers()
    		    {
    		        // 리스트 생성
    		        List<User> result = new ArrayList<User>();
    		
    		        // 데이터베이스에서 유저 목록을 가져온다.
    		        result.add(...);
    		        ...
    		
    		        return result;
    		    }
    		}
    ```

## 2. DTO (Data Transfer Object) - dto package

- **계층간 데이터 교환을 위한 객체(Java Bean)**
    - DB에서 데이터를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체
    - **DB의 데이터가 Presentation Logic Tier**로 넘어오게 될 떄는 DTO의 모습으로 바껴서 오고 간다.
    - **로직이 없는 순수한 데이터 객체이며, Getter / Setter만 있다.**
    - 하지만 DB에서 꺼낸 값을 임의로 변경할 필요가 없기에 DTO 클래스에는 Setter가 없다.  (대신 생성자에서 값을 할당한다.)
    - ***DTO는 DB에서 Data를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체. 위 코드에서도 DAO가 DB로부터 Data를 얻어 List에 담아서 보내고 있다.
    그리고 DTO는 아래와 같이 쓰인다.***

    ```java
    public class User {
        private String name;
        private int age;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void getAge() {
            return this.age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }

        @Override
        public String toString() {
            return "name='" + name + "', age=" + age;
        }
    }
    ```

- **Request 와 Response용 DTO는 view 를 위한 클래스**
    - 자주 변경이 필요한 클래스
    - Presentation Model
    - toEntity() 메서드를 통해 DTO에서 필요한 부분을 이용하여 Entity로 만든다.
    - 또한 Controller Layer에서 Response DTO 형태로 Client에 전달

- **VO(Value Object) VS DTO**
    - VO는 DTO와 동일한 개념이지만 ***Read Only*** 속성을 갖는다.
    - **VO는 특정한 비즈니스 값을 담는 객체**이고, **DTO는 Layer 간의 통신 용도**로 오고가는 객체이다.

## 3.Entity Class - domain package

- **실제 DB의 테이블과 매칭 될 클래스**
    - 테이블과 링크될 클래스 임을 나타낸다.
    - Entity 클래스 또는 가장 Core한 클래스라고 부른다.
    - @Entity , @Column, @Id 등을 이용한다

- **Entity Class와 DTO 클래스 분리하는 이유**
    - **View Layer**와 **DB Layer의 역할을 철저하게 분리**하기 위함
    - 테이블과 매핑되는 **Entity Class**가 변경되면 여러 클래스에 영향을 끼침
    - View와 통신하는 **DTO Class**(Request , Response 클래스)는 **자주 변경**되므로 **분리!**

    - Domain Model을 아무리 잘 설계하여도 각 View 내에서 Domain Model의 getter만을 이용해서 원하는 정보를 표시하기가 어렵다. 이런경우 Domain Model내에 Presentation을 위한 필드나 로직을 추가하게 되는데, 이러한 방식이 모델링의 순수성을 깨고 Domain Model 객체를 망가뜨리게 된다.
    - 또한 Domain Model을 복잡하게 조합한 형태의 Presentation요구사항들이 있기 때문에 Domain Model을 직접 사용하는 것은 어렵다.
    - DTO는 Domain Model을 복사한 형태로, 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서만 사용한다.

# 전체 구조!! (package 기준)

![DAO%20DTO%20Entity%20Class%2009e9ebc058c449a7bafbe9abdeebf1ea/Untitled.png](DAO%20DTO%20Entity%20Class%2009e9ebc058c449a7bafbe9abdeebf1ea/Untitled.png)

- **Controller (web)**
    - **기능**
        1. 해당 요청 url에 따라 적절한 view와 mapping 처리
        2. @Autowired Service를 통해 Service의 method 이용
        3. 적절한 **Response Entity(DTO)를 body에 담아 Client에 반환**
        4. **@Controller**
            1. API와 VIEW를 동시에 사용하는 경우에 사용
            2. 대신 API 서비스로 사용하는 경우는 @Responsebody를 사용하여 객체 반환
        5. @RestController
            1. view 가 필요없는 **API 만 지원하는 서비스**에 사용
            2. @RequestMapping메서드가 기본적으로 @ResponseBody 의미를 가정
            3. Data(json, xml 등) return이 주 목적 : return **ResponseEntity**
            4. 즉, @RestController = @Controller + @ResponseBody

- **Service**
    - **기능**
        1. @Autowired Repository 를 통해 repository의 method를 이용
        2. 적절한 **Business Logic을 처리**한다. 
        3. DAO로 DB에 접근하고 DTO로 데이터를 전달받은 다음, 비지니스 로직을 처리해 적절한 데이터를 반환
        4. Controller가 Request를 받음연 적절한 Service에 전달하고, 전달받은 Service는 비즈니스 로직을 처리한다.
        5. DAO로 DB에 접근하고, DTO로 데이터를 전달 받은 다음, 적절한 처리르 해반환!

        ```java
        public interface UserService {
            /**
             * 유저 정보를 텍스트 파일로 저장한다.
             * 
             * @param path 저장할 파일의 경로
             * @return 저장한 유저의 개수
             */
            public int saveUsersAsTextFile(String path);
        }
        ```

        ```java
        @Service("userService")
        public class UserServiceImpl implements UserService {
          private static final Logger LOGGER = Logger.getLogger("UserServiceImpl");

          @Autowired
          private UserDao userDao;

          @Override
          public int saveUsersAsTextFile(String path) {
              List<User> users = userDao.getUsers();

              // 비즈니스 로직
              try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                  StringBuilder result = new StringBuilder();
                  for(User user : users) {
                      result.append(user);
                      result.append('\n');
                  }

                  fileOutputStream.write(result.toString().getBytes());
              } catch (IOException exception) {
                  LOGGER.log(Level.SEVERE, "파일을 쓸 수 없습니다.");
                  throw new IllegalStateException(String.format("Can't write a file. path: %s", path));
              }

              return users.size();
          }
        }
        ```

        지금까지 DAO로부터 DTO 리스트를 받고, DTO의 리스트를 파일로 저장한 코드

        @Autowired 로 userDao Bean을 찾아서 연결한 것을 볼 수 있다.

        Controller에서 서비스 호출은  아래와 같다

        ```java
        @Controller
        public class MainController {
            @Autowired
            private UserService userService;

            @RequestMapping(value = "/save/users", method = RequestMethod.GET)
            public ModelAndView saveUsers(ModelAndView mv) {
                // 유저를 얻어와서 텍스트 파일로 저장한다.
                int saveCount = userService.saveUsersAsTextFile("users.txt");

                // 뷰에서 결과를 보여주기 위해 저장한 개수를 뷰에 넘긴다.
                mv.addObject("saveCount", saveCount);
                mv.setViewName("saveUsersResultView");

                return mv;
            }
        }
        ```

- **Repository(DAO)**
    - **기능**
        1. 실제 DB에 접근하는 객체!!
        2. Service와 DB를 연결하는 고리 역할!!
        3. SQL를 사용하여 DB에 접근한 후 적절한 CRUD API 제공