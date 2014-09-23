package guice;

import com.google.inject.AbstractModule;
import services.ResourceService;
import services.impl.ResourceServiceImpl;

/**
 * @author Roxana
 * @version 1.0
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceService.class).to(ResourceServiceImpl.class);
    }
}
