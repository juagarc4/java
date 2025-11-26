package advancedconcepts;

import java.util.*;

public class AdvancedConcepts {

    static void main(String[] args){
        List<String> myList = new ArrayList<>();
        myList.add("Monday");
        myList.add("Tuesday");
        myList.add("Wednesday");
        myList.add("Thursday");
        myList.add("Friday");
        myList.add("Saturday");
        myList.add("Sunday");
        System.out.println("Print List elements without using lambda function");
        for(String element: myList) {
            System.out.println("Day of week: " + element);
        }

        // Using Lambda function
        System.out.println("\nPrint List elements using lambda function");
        myList.forEach(element -> {
            System.out.println("Day of week: " + element);
        });

        // Using reference methods: Using this way, we simplify the code,
        // but we are not abel anymore to detail what we are doing.
        System.out.println("\nPrint List elements reference methods");
        myList.forEach(System.out::println);

        // Generating the lists in this way, we can add the elements directly without using .add()
        List<String> names = Arrays.asList("Peter", "Ivonne", "Noemi");
        System.out.println("\nPrint List elements generated using asList method");
        names.forEach(System.out::println);

        // Sets
        Set<String> set = new TreeSet<>();

        // The duplicated element will only be printed once;
        set.add("Charlie");
        set.add("Charlie");
        set.add("Karla");
        set.add("Victoria");

        System.out.println("\nPrint Set elements");
        set.forEach(System.out::println);

        // Removing one element
        set.remove("Charlie");
        System.out.println("\nPrint Set elements after removing one element");
        set.forEach(System.out::println);

        // Maps

        Map<String, String> person = new HashMap<>();

        person.put("First name", "Diego");
        person.put("Last name", "Flores");
        person.put("age", "33");
        person.put("age", "33"); // Duplicated are ignored
        System.out.println("\nPrint Map values");
        person.entrySet().forEach(System.out::println);
        System.out.println("\nPrint Map values with a changed value");
        person.put("age", "30"); // Change value
        person.entrySet().forEach(System.out::println);
        System.out.println("\nPrint Map values after removing one pairs key=value");
        person.remove("age"); // Remove age
        person.entrySet().forEach(System.out::println);
        System.out.println("\nIterate over the single elements: (key, value)");
        person.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}