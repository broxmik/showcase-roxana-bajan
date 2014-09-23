package unit;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controllers.Application;
import guice.TestModule;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;
import play.mvc.Results;
import services.ResourceService;

import static org.fest.assertions.Assertions.*;

/**
 * @author Roxana
 * @version 1.0
 */
public class BaseTest {

    protected Injector injector;

    @Before
    public void before(){
        injector = Guice.createInjector(new TestModule());
    }

}
