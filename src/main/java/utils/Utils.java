package utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Utils {
    public static Integer[] getUniqueRandomIntAr(int upperBound, int start, int end){
        if ((upperBound - (start + end - 1)) == 0 || (upperBound - (start + end + 1)) == 0) {
            return IntStream.rangeClosed(start, end).boxed().toArray(n -> new Integer[upperBound]);
        }

        Random rnd = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < upperBound) {
            set.add(rnd.nextInt(start, end));

        }
        return set.toArray(new Integer[set.size() - 1]);
    }

    public static String getRandomCharsString(int length){
        Random random = new Random();
        return IntStream.rangeClosed(1, length).mapToObj(
                i -> {
                    int d = random.nextInt(26);
                    return String.valueOf((char)(d + 'a')); // or (i % 2 == 0 ? d + 'a' : d +  'A') for every even - upperCase chars
                }).collect(Collectors.joining(""));
    }
}
