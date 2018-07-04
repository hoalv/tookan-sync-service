package shippo.sync.tookan.global;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static boolean eq(Object obj1, Object obj2){
        if ((obj1 == null && obj2 != null) || (obj1 != null && !obj1.equals(obj2))) {
            return false;
        }
        return true;
    }

    public static String readFileToString(String fileName)
            throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader fin = new BufferedReader(reader);
        StringBuilder resultStringBuilder = new StringBuilder();
        String s;
        while ((s=fin.readLine()) != null) {
            resultStringBuilder.append("\n");
            resultStringBuilder.append(s);
        }
        fin.close();
        return resultStringBuilder.toString();
    }

    public static void main(String[] args) {
//        Timestamp timestamp = Timestamp.valueOf("2017-10-14 14:43:59.22");
//        System.out.println(timespampToPostgreTimestampString(timestamp));
//        System.out.println(postgreTimestampStringToTimestamp("2017-10-14T07:43:59.220Z"));
        System.out.println();
    }
}
