package com.memorygame.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sarah on 7/15/18.
 */

public class Utils {

    public static Set<Integer> generateRandomNumbers(int halfBoardSize, int max) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();
        while (numbers.size() < halfBoardSize) {
            numbers.add(random.nextInt(max) + 1);
        }

        return numbers;
    }

    public static String timerFormat(long millis) {

        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}
