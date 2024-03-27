public class SemanticAnalysis {
    public static boolean analyzer(String input) {
        String[] inputSplit = input.split("\\s+");
        String[] syntax = new String[inputSplit.length + 1];
        Boolean isSemanticCorrect = false;
        for (int x = 0; x < inputSplit.length; x++) {
            String token = inputSplit[x];
            if (isDataType(token)) {
                syntax[x] = token;
            } else if (token.equals("=")) {
                syntax[x] = "assignment_operator";
            } else if (isStringStart(token)) {
                while (isStringEnd(token) == false) {
                    x++;
                    token = inputSplit[x];
                }
                syntax[x] = "String";
                x++;
                syntax[x] = "delimiter";
                isSemanticCorrect = semanticChecker(syntax[x - 1], syntax[0]);
            } else if (isChar(token)) {
                syntax[x] = "char";
                x++;
                syntax[x] = "delimiter";
                isSemanticCorrect = semanticChecker(syntax[x - 1], syntax[0]);
            } else if (isDouble(token)) {
                syntax[x] = "double";
                x++;
                syntax[x] = "delimiter";
                isSemanticCorrect = semanticChecker(syntax[x - 1], syntax[0]);
            } else if (isInteger(token)) {
                syntax[x] = "int";
                x++;
                syntax[x] = "delimiter";
                isSemanticCorrect = semanticChecker(syntax[x - 1], syntax[0]);
            } else {
                if (token.endsWith(";")) {
                    syntax[x] = "identifier";
                    x++;
                    syntax[x] = "delimiter";
                } else {
                    syntax[x] = "identifier";
                }
            }
        }

        if (isSemanticCorrect == true) {
            return true;
        } else {
            return false;
        }

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
    public static boolean semanticChecker(String x, String y){
        return x.matches(y);
    }
}
