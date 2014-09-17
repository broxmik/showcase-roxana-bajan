package services;

import utils.Variables;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Roxana
 * @version 1.0
 */
public class ResourceServiceImpl implements ResourceService{

    @Override
    public File getResource(String resource) {
        String fileName;
        try {
            fileName = URLDecoder.decode(resource, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            fileName = resource;
        }
        return new File(Variables.PATH_TO_DEV_UI + fileName);
    }

    @Override
    public File getNotFoundFile() {
        return new File(Variables.PATH_TO_DEV_UI + "404.html");
    }

    @Override
    public File getServerErrorFile() {
        return new File(Variables.PATH_TO_DEV_UI + "500.html");
    }
}
