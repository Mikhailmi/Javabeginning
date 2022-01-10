package java3lesson7;



public class TestClass {


   @BeforeSuite
   void beforeSuite() {
    System.out.println("Метод с аннотацией BeforeSuite запущен");

    }

    @Test (priority = 5)
    void test1 () {
     System.out.println("Метод test1 запущен");
    }

    @Test (priority = 4)
    void test2 () {
     System.out.println("Метод test2 запущен");
    }


    @AfterSuite
    void afterSuite () {
     System.out.println("Метод с аннотацией AfterSuite запущен");
    }

}
