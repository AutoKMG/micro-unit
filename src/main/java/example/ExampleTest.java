package example;

import static microunit.Assert.*;
import microunit.Test;

// import microunit.ExpectedExceptionHandlingTestRunner;
import microunit.Test;
// import microunit.BasicTestRunner;

import java.io.IOException;

// CHECKSTYLE:OFF
public class ExampleTest {

    public ExampleTest() {
    }

    @Test
    public void a() {
        assertTrue(1 + 1 == 2, "This should always be true");
    } // success

    @Test
    public void b() {
        assertTrue(1 + 1 == 0, "This should never be true");
    } // failure

    @Test
    public void c() {
        Integer.parseInt("Hello, World!"); // throws NumberFormatException
    } // error

    // @Test(expected = IOException.class)
    public void d() throws Exception {
    } // failure

    // @Test(expected = IOException.class)
    public void e() throws Exception {
        throw new IOException("An I/O exception occurred");
    } // success

    // @Test(expected = IOException.class)
    public void f() {
        throw new RuntimeException("Oops!");
    } // error

    public void g() {
    }

    public static void main(String[] args) throws Exception {
        // new BasicTestRunner(ExampleTest.class).runTestMethods();
    }

}
