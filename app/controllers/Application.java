package controllers;

import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import services.ResourceService;
import utils.Variables;

import java.io.File;

/**
 * Main controller for the application
 * @author Roxana Bajan
 */
public class Application extends Controller {


    private ResourceService resourceService;

    @Inject
    public Application(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    public Result index() {
        return ok("Work in progress");
    }

    /**
     * Serves the interface in a development environment
     * @param resource name of the resource
     * @return dev interface
     */
    public Result dev(String resource) {

        // Only available in DEV
        if(play.api.Play.isDev(play.api.Play.current())) {
            File file = resourceService.getResource(resource);
            try {
                return ok(file, true);
            } catch(Throwable t) {
                return notFound(resourceService.getNotFoundFile(), true);
            }
        }

        // No dev
        return notFound(resourceService.getNotFoundFile(), true);
    }
}
