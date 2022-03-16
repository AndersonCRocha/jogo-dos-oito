package io.github.andersoncrocha.utils;

import java.util.List;
import java.util.Random;

public class CollectionUtils {

    private static final Random random = new Random();

    public static <T> T getRandomValue(List<T> collection) {
        int randomIndex = random.nextInt(collection.size());
        return collection.get(randomIndex);
    }

}
