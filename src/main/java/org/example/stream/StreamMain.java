package org.example.stream;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {

        System.out.println("JAVA 8 Features");

        List<Integer> nums = List.of(1, 6, 4, 5);
        Integer[] intarr = new Integer[]{1, 2, 3};

        List<Integer> intList = Arrays.asList(3, 5, 8, 2, 1, 12, 15, 7, 11);
        List<String> fruitsList = Arrays.asList("apple", "kiwi", "banana", "cherry", "mango", "date", "kiwi", "avocado", "apple", "banana", "apple", "pineapple", "mango", "apple", "kiwi");

        int int1 = IntStream.range(1,5).sum();
        System.out.println("IntStream - "+ int1);

        fruitsList.stream().filter(x -> x.startsWith("a")).forEach(System.out::println);  // filter
        //##############################################################################################

        String newstr = fruitsList.stream().reduce("",(s, d) -> s+"--"+d);  // reduce
        System.out.println("reduce op on strings- " +newstr);

        int result1 = intList.stream()
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

        Map<String, Integer> map = new HashMap<>();
        map.put("JAVA", 10);
        map.put("KOTLIN", 10);
        map.put("AWS", 8);
        map.forEach((k, v) ->
                System.out.println("iterate with forEach: " + k + " - " + v)
        );

        for (Map.Entry<String, Integer> ent : map.entrySet()) {
            System.out.println("iterate with Map.Entry<String,Integer> ent : map.entrySet(): " + ent.getKey() + " - " + ent.getValue());
        }

        //JAVA 9 - MAP
        Map<String, String> emptyMap = Map.of();
        Map<String, String> singletonMap = Map.of("key1", "value");
        Map<String, String> mp = Map.of("key1", "value1", "key2", "value2", "key3", "value3");
        mp.forEach((k, v) -> System.out.println(k + " - " + v));
        //Note that this method supports only a maximum of 10 key-value pairs.

        Map<String, String> doubleBraceMap = new HashMap<String, String>() {{
            put("k1", "v1");
            put("k2", "v2");
        }};
        doubleBraceMap.forEach((k, v) -> System.out.println(k + " - " + v));
        //We must try to avoid this initialization technique because it creates an anonymous extra class at every usage, holds hidden references to the enclosing object, and might cause memory leak issues.

        //  Count frequency of elements in a list
        fruitsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).forEach((k, v) -> System.out.println(k + " - " + v));
        //  O/P like - apple - apple-apple, kiwi - kiwi-kiwi-kiwi
        fruitsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.joining("-"))).forEach((k, v) -> System.out.println(k + " - " + v));
        // Find elements whose count > 1 from a list
        fruitsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(k -> k.getValue() > 1)
                .forEach(e -> System.out.println("Fruit count > 1: "+e.getKey()));

        // Odd Even
        intList.stream().collect(Collectors.groupingBy(s -> s % 2 == 0 ? "EVEN" : "ODD"))
                .forEach((k, v) -> System.out.println(k + " - " + v));


        List<List<String>> nested = List.of(List.of("a", "b"), List.of("c", "d"), List.of("c", "a"));

        // convert a list of list to - a single list like [ a b c d c a ]
        String newlist = nested.stream().flatMap(s -> s.stream()).reduce("", (a, b) -> a + " " + b);
        System.out.println(newlist);


    }
}
