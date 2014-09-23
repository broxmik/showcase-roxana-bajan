import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.ApplicationModule;
import play.GlobalSettings;

/**
 * @author Roxana
 * @version 1.0
 */
public class Global extends GlobalSettings {

    private static Injector injector = Guice.createInjector(new ApplicationModule());

    @Override
    public <T> T getControllerInstance(Class<T> aClass) throws Exception {
        return injector.getInstance(aClass);
    }

}
