package AlgorithmTagging;

public class Converter {
    public static void main(String[] args) {
        System.out.println(ConvertDecimalToHex(10));
        System.out.println(ConvertDecimalToHex(58));
    }
    static String ConvertDecimalToHex(int number){
        if (number < 10) { return Integer.toString(number); };
        String[] abcdef = new String[]{"A","B","C","D","E","F"};

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(number / 16)
                .append(abcdef[(number % 16) - 10])
                .append("h");
        return stringBuilder.toString();
    }

    static String ConvertHexToDecimal(String hex){
        /*if (number < 9) { return Integer.toString(number); };
        String[] abcdef = new String[]{"A","B","C","D","E","F"};

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(number / 16)
                .append(abcdef[(number % 16) - 10])
                .append("h");
        return stringBuilder.toString();*/
        return null;
    }

}
