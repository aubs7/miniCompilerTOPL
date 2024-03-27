public class Parser {

    public static boolean parse(String [] fileContent){

        String[] syntax = {"<data_type>", "<identifier>", "<assignment_operator>", "<value>", "<delimiter>"};

        for (int x = 0; x < fileContent.length; x++){

            while(x<fileContent.length){
                if((syntax[x])==(fileContent[x])){
                    //System.out.println(syntax[x] + " " + fileContent[x]); for checking
                    x++;
                } else {
                    //System.out.println(syntax[x] + " " + fileContent[x]); for checking
                    return false;
                }
            }
        }
        return true;
    }

}
