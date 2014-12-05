package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import ro.brox.model.Food;

import java.io.IOException;
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
        try (Stream<String> lines = Files.lines(Paths.get("data", "calories.csv"), StandardCharsets.ISO_8859_1)) {
            foodList = lines.skip(2)
                    .parallel()
                    .filter(s -> s != null && !s.trim().isEmpty())
                    .map(Food::new)
                    .sorted(Comparator.comparingInt(Food::getCarbo).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
            return internalServerError(e.getMessage());
        }

        return ok(foodList.toString());
    }
}
