import java.util.Scanner;
public class AddFromKbd {
    public static void readAndAdd(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the first num: ");
        int num1 = scanner.nextInt();

        System.out.println("Please input the second num: ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        System.out.println("The sum is: " + sum );

    }

    public static void main(String[] args) {
        readAndAdd();
    }

}
