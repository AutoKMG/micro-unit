package microunit;

import java.lang.reflect.Method;

/**
 * This interface is used to accumulate the results of test method executions.
 */
public interface TestResultAccumulator {
    /**
     * Record that a test method was executed successfully.
     * @param method the test method executed
     */
    void onSuccess(Method method);

    /**
     * Records that the execution of a test method resulted in a failure.
     * @param method the test method executed
     */
    void onFailure(Method method);

    /**
     * Records that the execution of a test method resulted in an error.
     * @param method the test method executed
     */
    void onError(Method method);
}
