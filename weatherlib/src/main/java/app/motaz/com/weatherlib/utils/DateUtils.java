package app.motaz.com.weatherlib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by motaz on 3/8/17.
 */

public class DateUtils {
    public static String getFormatedDate(String inputDate) {
        String inputPattern = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputPattern);
        try {
            Date date = simpleDateFormat.parse(inputDate);
            SimpleDateFormat outputSimpleDateFormat = new SimpleDateFormat("EEE, MMM dd");
            return outputSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
