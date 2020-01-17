import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
    private static String numbers[] = {
        "0", "1", "2", "3",
        "4", "5", "6", "7",
        "8", "9", "A", "B",
        "C", "D", "E", "F"
    };
    private static String simbols = "0123456789ABCDEF";

    public static int factorial(int fact){
        if(fact <= 1)
            return 1;
        return fact * factorial(fact - 1);
    }

    public static int fibonacci(int n){
        if(n <= 2)
            return 1;
        return fibonacci(n-1) + fibonacci(n - 2);
    }

    public static int mcd(int input){
        return mcd(input, 2);
    }

    private static int mcd(int input, int div){
        if(div >= input)
            return 1;
        if(input % div == 0)
            return div;
        return mcd(input, div + 1);

    }

    public static int sumArray(int arr[], int index){
        if(index >= arr.length)
            return 0;
        return arr[index] + sumArray(arr, index + 1);
    }

    public static void printArr(int arr[], int index){
        if(index >= arr.length)
             return;
        System.out.println(arr[index]);
        printArr(arr, index + 1);
    }
    //    Sumar elementos de una lista (genérica para números)
    //    Convertir decimal a (binario, octal, hexadecimal)
    public static String decimalToBase(int input, int base){
        if(input == 0)
            return "";
        return decimalToBase(input / base, base) + numbers[input % base];
    }
    //    Convertir cualquier base a decimal
    public static int baseToDecimal(String input, int base, int index){
        if(input.length() == 0 )
            return 0;
        char c = input.charAt(input.length() - 1);
        int val = simbols.indexOf(c);
        input = input.substring(0, input.length() - 1);
        return (int)Math.pow(val, index) * base + baseToDecimal(input, base, index + 1);
    }
    //    Código Gray
    //    Elevar a una potencia un numero
    public static int pow(int n, int m){
        if(m == 0)
            return 1;
        return n * pow(n, m -1);
    }
    //    Búsqueda binaria en arreglos (genérica)
    public static int search(int[] arr, int left, int right, int ele){
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == ele)
                return mid;
            if (arr[mid] > ele)
                return search(arr, left, mid - 1, ele);
            return search(arr, mid + 1, right, ele);
        }
        return -1;
    }
    //    Permutaciones de cadenas de n letras (ejercicio 17, 18)
    //    Distancia Hamming (ejercicio 21)
    public static int hamming(String input1, String input2){
        if(input1.length() == 0 && input2.length() == 0)
            return 0;
        if(input1.length() == 0 && input2.length() != 0)
            return input2.length();
        if(input1.length() != 0 && input2.length() == 0)
            return input1.length();
        int val = input1.charAt(0) == input2.charAt(0) ? 0 : 1;
        return val + hamming(input1.substring(1, input1.length()), input2.substring(1, input2.length()) );
    }
}
