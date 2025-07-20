package org.example.java17Features;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
class Vehicle {                     // Class 'Vehicle' can't be public, (if public - should be declared in a file named 'Vehicle.java)
    private String brand;
    private double price;
    private String color;
}

/*@Data
@AllArgsConstructor
class Animal {
    private String type;
    private String name;
}*/

record Animal(String type, String name) {
}     //  JAVA 17 - Records (Immutable Data Classes)


sealed class A permits B,C {
      public String display() {
          return "From A";
      }
}

final class B extends A {
    public String display() {
         return "From B";
    }
}

sealed class C extends A {
    public String display() {
        return "From C";
    }
}

non-sealed class D extends  C{
    public String display() {
        return "From D";
    }
}

public class Java17FeaturesMain {
    public static void main(String[] args) {

        Vehicle v = new Vehicle();
        v.setBrand("TATA");
        v.setColor("Red");
        System.out.println(v);

        Animal ani1 = new Animal("Wild", "Tiger");
        Animal ani2 = null;
        System.out.println(ani1);

        // JAVA 17 - Sealed Classes Define fixed class hierarchies (good for modeling DSLs, rules, etc.)
        A a = new A();
        A b = new B();
        A c = new C();
        A d = new D();

        System.out.println("a = "+a.display() +" b =  "+b.display()+" c = "+c.display()+" d = "+d.display());

        // JAVA 17 - Improved Null Handling with Optional (Java 8+ but still relevant in Java 17)
        Optional.ofNullable(ani1).map(Animal::type).ifPresent(System.out::println);
        Optional.ofNullable(ani2).map(Animal::type).ifPresent(System.out::println);

        // JAVA 17 - Compact if-else using switch inside lambdas
        List<String> listOfAnimals = List.of("lion", "dog", "cat", "tiger", "Cow", "elephant", "rabbits");

        listOfAnimals.forEach(animal -> {
            String type = switch (animal) {
                case "lion", "tiger", "bear", "elephant" -> "Wild Animal";
                case "cat", "cow", "horse", "dog" -> "Domestic Animal";
                default -> "Unknown Animal";
            };
            System.out.println(animal + " is a : " + type);
        });


        //JAVA 17 - Text Blocks (for Multiline Strings)

        String jsonOld = "{\n" +
                "  \"name\": \"Arpi...\",\n" +
                "  \"age\": 28\n" +
                "}";
        System.out.println(jsonOld);

        String multilineStringJsonNew = """
                {
                  "name": "Sachin...",
                  "age": 32
                }
                """;
        System.out.println(multilineStringJsonNew);
    }
}
