package exemplo.model;
import java.util.Calendar;

public class Teste_Date{
 
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
//         
        System.out.println("Data/Hora atual: "+c.getTime());
        System.out.println("Ano: "+c.get(Calendar.YEAR));
        System.out.println("M�s: "+c.get(Calendar.MONTH));
        System.out.println("Dia do M�s: "+c.get(Calendar.DAY_OF_MONTH));
        System.out.println(c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH)+ "/" + c.get(Calendar.YEAR));
    }
}