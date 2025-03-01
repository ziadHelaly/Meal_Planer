package eg.edu.iti.mealplaner.utilies;

public class CalenderUtil {
    public static String getFormattedDate(int dayOfMonth, int month,int year){
        return dayOfMonth + "/" + (month + 1) + "/" + year;
    }
}
