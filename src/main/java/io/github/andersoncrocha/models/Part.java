package io.github.andersoncrocha.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Part {

    private final Integer value;
    private List<Part> neighbors = new ArrayList<>();
    private int index;

    public Part() {
        this(null);
    }

    public Part(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public List<Part> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Part> neighbors) {
        this.neighbors = neighbors;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isBlank() {
        return Objects.isNull(value);
    }

    public boolean isNotBlank() {
        return !isBlank();
    }

    public boolean valuesMatch(Integer value) {
        return Objects.equals(this.value, value);
    }

    @Override
    public String toString() {
        return String.format("%3s ", Optional.ofNullable(value).map(String::valueOf).orElse(""));
    }

}
