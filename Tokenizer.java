public class Tokenizer {

    public static String [] tokenize(String fileContent) {

        String[] inputSplit = fileContent.split("\\s+");

        String[] syntax = new String[inputSplit.length + 1];

        for (int x = 0; x < inputSplit.length; x++) {

            String token = inputSplit[x];

            if (isDataType(token)) {
                syntax[x] = "<data_type>";
            }
            else if (token.equals("=")) {
                syntax[x] = "<assignment_operator>";
            }
            else if (isStringStart(token)) {
                while (isStringEnd(token) == false) {
                    x++;
                    token = inputSplit[x];
                }
                syntax[x] = "<value>";
                x++;
                syntax[x] = "<delimiter>";
            }
            else if (isChar(token)) {
                syntax[x] = "<value>";
                x++;
                syntax[x] = "<delimiter>";
            }
            else if (isDouble(token)) {
                syntax[x] = "<value>";
                x++;
                syntax[x] = "<delimiter>";
            }
            else if (isInteger(token)) {
                syntax[x] = "<value>";
                x++;
                syntax[x] = "<delimiter>";
            }
            else {
                if (token.endsWith(";")) {
                    syntax[x] = "<identifier>";
                    x++;
                    syntax[x] = "<delimiter>";
                } else {
                    syntax[x] = "<identifier>";
                }
            }
        }

        return syntax;
    }
    public static boolean isDataType(String token) {
        return token.matches("(int|String|char|double)");
    }

    public static boolean isStringStart(String token) {
        return token.matches("\\\".*");
    }

    public static boolean isStringEnd(String token) {
        return token.matches(".*\\;$");
    }

    public static boolean isChar(String token) {
        return token.matches("\\'.*");
    }

    public static boolean isInteger(String token) {
        return token.matches(".*\\d\\;$");
    }
    public static boolean isDouble(String token) {
        return token.matches(".*\\d\\.?\\d\\;$");
    }
}


