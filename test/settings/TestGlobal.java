package settings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.ApplicationModule;
import guice.TestModule;
import play.GlobalSettings;
import play.Play;

/**
 * @author Roxana
 * @version 1.0
 */
public class TestGlobal extends GlobalSettings {

    private static final Injector injector;
    static {
        injector = Guice.createInjector(new TestModule());
    }

    @Override
    public <T> T getControllerInstance(Class<T> aClass) throws Exception {
        return injector.getInstance(aClass);
    }

}
