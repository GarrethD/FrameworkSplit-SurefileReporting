package Utilities.DateManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeCollector {

    public static String getDate(int amount)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: "+sdf.format(cal.getTime()));
        //Adding 1 Day to the current date
        cal.add(Calendar.DAY_OF_MONTH, amount);
        //Date after adding one day to the current date
        String newDate = sdf.format(cal.getTime());
        //Displaying the new Date after addition of 1 Day
        System.out.println("Incremnted current date by one: "+newDate);

        return newDate;
    }

}
