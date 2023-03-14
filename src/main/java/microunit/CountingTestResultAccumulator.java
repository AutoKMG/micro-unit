package microunit;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Accumulates the results of test method executions by counting them.
 */
public class CountingTestResultAccumulator implements TestResultAccumulator{

    private AtomicInteger success = new AtomicInteger(0);
    private AtomicInteger failure = new AtomicInteger(0);
    private AtomicInteger error = new AtomicInteger(0);
    @Override
    public void onSuccess(Method method) {
        success.incrementAndGet();
    }

    @Override
    public void onFailure(Method method) {
        failure.incrementAndGet();
    }

    @Override
    public void onError(Method method) {
        error.incrementAndGet();
    }

    public String toString(){
        return String.format(
                """ 
                Success: %s
                Failure: %d
                Error: %s
                """,success,failure.get(),error);
    }
}
