package microunit;

/*
* Provides assertions methods for writing unit tests.
*
*/
public class Assert {
    /**
     * Fails a test by throwing an {@link AssertionError} with the given
     * message.
     * @param message the message that describes the reason of the failure,
     *                may be{@code null}
     */
    public static void fail(String message){
        if(message == null){
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }

    /**
     * Fails a test by throwing an {@link AssertionError} with no detail
     * message.
     */
    public static void fail(){
        fail(null);
    }

    /**
     * Assets that the condition specified is {@code true}, otherwise an
     * {@link AssertionError} with the given detail message is thrown.
     * @param condition the condition that must be {@code true}
     * @param message the message that describe the reason of the failure
     */
    public static void assertTrue(boolean condition,String message){
        if(! condition){
            fail(message);
        }
    }

    /**
     * Asserts that the condition specified is {@code true}, otherwise an
     * {@link AssertionError} with no detail message is thrown.
     * @param condition the condition that must be {@code true}
     */
    public static void assertTrue(boolean condition){
        assertTrue(condition, null);
    }
}
