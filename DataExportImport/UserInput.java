package DataExportImport;

import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ���-�� ����������� ��������: ");
        int x = scanner.nextInt();
        System.out.println("������� ����� " + x);
    }

}
