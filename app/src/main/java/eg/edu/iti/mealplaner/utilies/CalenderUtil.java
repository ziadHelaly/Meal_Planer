package eg.edu.iti.mealplaner.utilies;

import java.util.Calendar;

public class CalenderUtil {
    public static String getFormattedDate(int dayOfMonth, int month,int year){
        return dayOfMonth + "/" + (month + 1) + "/" + year;
    }
    public static String getToday(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return getFormattedDate(day,month,year);
    }
}
