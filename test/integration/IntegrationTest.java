package integration;

import org.junit.Test;
import play.libs.F.Callback;
import play.test.TestBrowser;
import settings.TestGlobal;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.*;

/**
 * @author Roxana
 * @version 1.0
 */
public class IntegrationTest {

    @Test
    public void testBrowser(){
        running(testServer(3333, fakeApplication(inMemoryDatabase(), new TestGlobal())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/api/wip");
                assertTrue(browser.pageSource().contains("Work in progress"));
            }
        });
    }
}
