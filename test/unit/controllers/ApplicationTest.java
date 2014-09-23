package unit.controllers;

import controllers.Application;
import org.junit.Test;
import play.mvc.Result;
import unit.BaseTest;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

public class ApplicationTest extends BaseTest{

    @Test
    public void testIndex() throws Exception {
        Application application = injector.getInstance(Application.class);
        Result index = application.index();
        assertThat(status(index)).isEqualTo(OK);
        assertThat(contentAsString(index)).contains("Work in progress");
    }

}
