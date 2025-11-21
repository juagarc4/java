package varargs;

import java.util.Arrays;

public class VariableArguments {
    static void  main(String[] args){
        printNumbers(1,2,3,4,5);
        System.out.println();
        printNumbers(1,2,5);
        System.out.println();
        differentParameters("Karla", 1, 20, 30);
    }

    private static void differentParameters(String name, int... numbers) {
        System.out.println("PRINTING VARIABLE AND NON-VARIABLE PARAMETERS");
        System.out.println("Name:" + name);
        printNumbers(numbers);
    }

    static void printNumbers(int... numbers) {
        System.out.println("PRINTING VARIABLE PARAMETERS FROM SAME TYPE");
        for (int i=0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

}