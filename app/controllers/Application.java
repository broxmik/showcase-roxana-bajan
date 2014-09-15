package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import utils.Variables;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Main controller for the application
 * @author Roxana Bajan
 */
public class Application extends Controller {
	
	public static Result index() {
		return ok("Work in progress");
	}
	
	/**
	 * Serves the interface in a development environment
	 * @param resource name of the resource 
	 * @return dev interface
	 */
    public static Result dev(String resource) {
    	
    	// Only available in DEV
    	if(play.api.Play.isDev(play.api.Play.current())) {
    		
            String fileName;
            try {
                fileName = URLDecoder.decode(resource, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                fileName = resource;
            }
            File file = new File(Variables.PATH_TO_DEV_UI + fileName);
            try {
    			return ok(file, true);
    		} catch(Throwable t) {
    			return notFound();
    		}
    		
    	} 
    	
    	// No dev 
    	return notFound();
    }
}
