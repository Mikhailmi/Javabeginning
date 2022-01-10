package java3lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class TestApp {

    private static Object obj;

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        TestClass testClass = new TestClass();
        TestApp.start(testClass.getClass());

    }


    public static void start(Class testClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (!areBeforeAfterAnnotationsCorrect(testClass)) {
            throw new RuntimeException();
        }

            obj = testClass.newInstance();

        Method beforeM = null;
        Method afterM = null;
        ArrayList<Method> testMethods = new ArrayList<>();
        Method[] objMethods = testClass.getDeclaredMethods();

        for (Method method : objMethods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeM = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterM = method;
            } else if (method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeM != null) {
            testMethods.add(0, beforeM);
        }

        if (afterM != null) {
            testMethods.add(afterM);
        }

            for (Method testMethod : testMethods) {
                if (Modifier.isPrivate(testMethod.getModifiers())) {
                    testMethod.setAccessible(true);
                }
                testMethod.invoke(obj);
            }

    }

    private static boolean areBeforeAfterAnnotationsCorrect(Class testClass) {
        int beforeAnnotationCount = 0;
        int afterAnnotationCount = 0;

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeAnnotationCount++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterAnnotationCount++;
            }
        }

        return (beforeAnnotationCount < 2) && (afterAnnotationCount < 2);
    }

}
