package Printer;

public class Printer {
    private String context;

    public Printer(String _context) {
        this.context = _context;
    }

    public void PrintLn() {
        System.out.println(context);
    }

    public void PrintFile() {
        String[] lines = context.split("\n");
        for (String line : lines) {
            System.out.println(line);
        }
    }

}
