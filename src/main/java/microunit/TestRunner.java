package microunit;

import example.ExampleTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Engine for executing unit test.
 */
public class TestRunner {
    private Class<?> testClass;

    /**
     * Creates a {@code TestRunner} object to execute the test method of the test class specified
     * @param testClass a {@link Class} object representing a unit test class.
     */
    public TestRunner (Class<?> testClass){
        this.testClass = testClass;
    }

    /**
     * Returns the method of the test class that are annotated with the annotation specified.
     * @param annotation a {@link Class} object representing an annotation interface.
     * @return the list of methods on which the annotation is present.
     */
    protected List<Method> getMethodsAnnotatedWith(Class<? extends Annotation> annotation){
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toList();
    }

    /**
     * Executes a test method on the test instance specified.
     * @param instance the instance of the test class on which the test method is invoked
     * @param method the test method to be invoked
     * @param accumulator the accumulator to record the outcome of the execution
     * @throws IllegalAccessException if the test method can't be invoked ,e.g. it's not public.
     */
    protected void executeTestMethod(Object instance, Method method, TestResultAccumulator accumulator)
    throws IllegalAccessException {
        try {
            method.invoke(instance);
            accumulator.onSuccess(method);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if(cause instanceof AssertionError){
                // this is a failure
                accumulator.onFailure(method);
            } else {
                // this is an error
                accumulator.onError(method);
            }
        }
    }

    /**
     * Executes all test methods of the test class
     */
    public void runTestMethods() {
        try {
            TestResultAccumulator accumulator = new CountingTestResultAccumulator();
            for (Method method : getMethodsAnnotatedWith(Test.class)){
                System.out.println(method);
                Object instance = testClass.getConstructor().newInstance();
                System.out.printf("New instance: %s%n", instance);
                executeTestMethod(instance,method,accumulator);
            }
            System.out.println(accumulator);
        } catch (ReflectiveOperationException e){
            throw new InvalidTestClassException(e);
        }
    }
    public static void main(String[] args) throws Exception{
        if (args.length != 1){
            System.out.printf("Usage: %s <test-class>&n",TestRunner.class);
            System.exit(1);
        }
        Class<?> testClas = Class.forName(args[0]);
        TestRunner testRunner = new TestRunner(testClas);
        testRunner.runTestMethods();
    }
}
