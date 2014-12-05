package ro.brox.model;

import play.Logger;
import play.libs.Json;

import java.util.Arrays;
import java.util.Objects;

/**
 * Food,Measure,Weight (g),kCal,Fat (g),Carbo(g),Protein (g)
 *
 * @author Roxana
 * @version 1.0
 */
public class Food {

    private String name;
    private String measure;
    private double wheight;
    private int kCal;
    private int fat;
    private int carbo;
    private int protein;

    public Food(String s) {
        Objects.requireNonNull(s);
        String[] split = s.split(",");
        if(split.length >= 6) {
            this.name = String.join(" ", Arrays.asList(split).subList(0, split.length - 6));
            this.measure = split[split.length-6];
            this.wheight = Double.parseDouble(split[split.length-5]);
            this.kCal = Integer.parseInt(split[split.length-4]);
            this.fat = Integer.parseInt(split[split.length-3]);
            this.carbo = Integer.parseInt(split[split.length-2]);
            this.protein = Integer.parseInt(split[split.length-1]);
        } else {
            this.name = "Error Parsing " + s;
            Logger.error(this.name);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getWheight() {
        return wheight;
    }

    public void setWheight(double wheight) {
        this.wheight = wheight;
    }

    public int getkCal() {
        return kCal;
    }

    public void setkCal(int kCal) {
        this.kCal = kCal;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbo() {
        return carbo;
    }

    public void setCarbo(int carbo) {
        this.carbo = carbo;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;

        Food food = (Food) o;

        if (carbo != food.carbo) return false;
        if (fat != food.fat) return false;
        if (kCal != food.kCal) return false;
        if (protein != food.protein) return false;
        if (Double.compare(food.wheight, wheight) != 0) return false;
        if (measure != null ? !measure.equals(food.measure) : food.measure != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        temp = Double.doubleToLongBits(wheight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + kCal;
        result = 31 * result + fat;
        result = 31 * result + carbo;
        result = 31 * result + protein;
        return result;
    }

    @Override
    public String toString() {
        return Json.stringify(Json.toJson(this));
    }
}
