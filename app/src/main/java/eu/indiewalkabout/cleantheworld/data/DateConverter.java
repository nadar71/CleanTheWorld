package eu.indiewalkabout.cleantheworld.data;

<<<<<<< HEAD
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);

    }
    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();

    }
=======
public class DateConverter {
>>>>>>> bf9558a5a8336c716856ee8a13f20b70ccde2398
}
