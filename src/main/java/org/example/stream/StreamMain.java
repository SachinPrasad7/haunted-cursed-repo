package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {

        System.out.println("JAVA 8 Features");

        List<Integer> nums = List.of(1, 6, 4, 5);
        Integer[] intarr = new Integer[]{1, 2, 3};
        List<Integer> list = Arrays.asList(3, 5, 8, 2, 1, 12, 15);
        int int1 = IntStream.range(1,5).sum();
        System.out.println("IntStream - "+ int1);
        List<String> str = Arrays.asList("apple", "banana", "cherry", "date", "avocado", "pineapple", "mango", "kiwi");

        str.stream().filter(x -> x.startsWith("a")).forEach(System.out::println);  // filter
        //##############################################################################################

        String newstr = str.stream().reduce("",(s,d) -> s+"--"+d);  // reduce
        System.out.println("reduce op on strings- " +newstr);

        int result1 = list.stream()
                .reduce(0,                          // identity - which serves as the starting value
                        (a, b) -> a + b,    // accumulator - A function that takes two arguments: the running result and the current element of the stream. It performs the actual reduction logic (e.g., summing, multiplying, concatenating, etc.). Signature: (partialResult, element) -> newPartialResult
                        (a, b) -> a + b);   // combiner - Only used in parallel streams. (partialResult1, partialResult2) -> combinedResult

        System.out.println("reduce result1 - " +result1);

        int result2 = Arrays.asList(1, 2, 3, 4, 7, 9).parallelStream()
                .reduce(2,
                        (a, b) -> {
                           // System.out.println("Accumulator: " + a + " + " + b);
                            return a + b;
                        },
                        (a, b) -> {
                           // System.out.println("Combiner: " + a + " + " + b);
                            return a + b;
                        });
        System.out.println("reduce result2 - " +result2);

        Optional<Integer> result3 = nums.stream().reduce((e1, e2) -> e1*e2);
        System.out.println("reduce result3 - " +result3.get());
        int result4 = nums.stream().reduce(100000,(e1, e2) -> e1/e2);
        System.out.println("reduce result4 - " +result4);
        int result5 = nums.stream().reduce(1, Integer::max);
        System.out.println("reduce result5 - " +result5);
        //##############################################################################################

        Stream<Integer> stt = Stream.of(1,2,3,5);
                stt.forEach(s -> System.out.println("Stream.of test- "+ s));
               // stt.forEach(x -> System.out.println("Stream.of test- "+ x));  // Stream has already been linked or consumed



    }
}
