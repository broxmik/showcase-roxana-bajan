package controllers;

import play.Logger;
import play.api.Play;
import play.mvc.Controller;
import play.mvc.Result;
import ro.brox.model.Food;
import scala.Option;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Option<File> existingFile = Play.getExistingFile("conf/data/calories.csv", Play.current());
        if(existingFile.nonEmpty()) {
            Path path = existingFile.get().toPath();
            try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1)) {
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
            }
            return ok(foodList.toString());
        }
        return internalServerError("Could not find file calories.csv");
    }
}
