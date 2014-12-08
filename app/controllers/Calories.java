package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import ro.brox.model.Food;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Roxana
 * @version 1.0
 */
public class Calories extends Controller {

    public Result getCalories() {
        List<Food> foodList;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("public/data/calories.csv");
        if(resource == null){
            String message = "Could not find calories.csv";
            Logger.error(message);
            return internalServerError(message);
        }
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()), StandardCharsets.ISO_8859_1)) {
            foodList = lines.skip(2)
                    .parallel()
                    .filter(s -> s != null && !s.trim().isEmpty())
                    .map(Food::new)
                    .sorted(Comparator.comparingInt(Food::getkCal).reversed())
                    .limit(12)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
            return internalServerError(e.getMessage());
        } catch (URISyntaxException e) {
            Logger.error(e.getMessage(), e);
            return internalServerError(e.getMessage());
        }

        return ok(foodList.toString());
    }
}
