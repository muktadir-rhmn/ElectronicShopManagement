
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author MUKTADIR
 */


public class HtmlCreator {
    private String textClass = "form-control";
    private String buttonClass = "btn btn-success btn-block";
    private static final String indent = "    ";
    
   
    public void setTextClass(String className) {
        buttonClass = className;
    }
   
    public String text(String name) {
        return text(name, capitalize(name));
    }
   
    public String text(String name, String label) {
       String code = 
            "<tr>\n" +
            indent + "<td>\n" +
            indent + indent + "<label for='"+ name + "'>" + label+"</label>\n" +
            indent + "</td>\n" +
            indent + "<td>\n" +
            indent + indent + "<input type='text' name='" +name+ "' id='"+ name+ "' class='"+ textClass +"'/>\n" +
            indent + "</td>\n" +
            "</tr>\n" ;
        return code;
    }

   
    public String submit(String label) {
        String code =  "<tr>\n" +
            indent + "<td>\n" +
            indent + "</td>\n" +
            indent + "<td>\n" +
            indent +indent +"<input type='submit' class='" +buttonClass + "' value='" +label +"' />" +
            indent + "<td>\n" +                 
            "</tr>\n" ;
        
      return code;
    } 

   
    public void setButtonClass(String className) {
        this.buttonClass = className;
    }
    
    String capitalize(String s){
        String s1 = s.substring(0, 1).toUpperCase();
        return s1 + s.substring(1);
    }
    
    public static void copyToClipBoard(String s){
        StringSelection stringSelection = new StringSelection(s);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    public static String getWarning(String warning){
        return "<div class='alert-warning'>" + warning + "</div>";
    }
}
