/**
 * Created by MUKTADIR on 12/14/2016.
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String code =
                generateModelPropertyInt("scheduleId") +
                        generateModelPropertyString("date")+
                        generateModelPropertyInt("amount")+
                        generateModelPropertyInt("invoiceId")+
                        generateModelPropertyString("customerName")+
                        generateModelPropertyString("customerMobile")
                ;

        HtmlCreator.copyToClipBoard(code);
    }
    public static String generateReqParam(String name){
        return String.format("String %s = req.getParameter(\"%s\");\n", name, name);
    }

    public static String generateModelPropertyString(String name){
        return "private String " + name + " = null;\n";
    }
    public static String generateModelPropertyInt(String name){
        return "private int " + name + " = -1;\n";
    }
}
