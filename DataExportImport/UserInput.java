package DataExportImport;

import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¬ведите кол-во необходимых вопросов: ");
        int x = scanner.nextInt();
        System.out.println("¬ведено число " + x);
    }

}
