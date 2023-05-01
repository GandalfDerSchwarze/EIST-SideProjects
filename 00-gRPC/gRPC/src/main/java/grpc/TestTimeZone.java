package grpc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestTimeZone {
    private static final String DATE_FORMAT = "MM/dd/yyyy - HH:mm:ss z";

    public static void main(String[] args){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String out = zdt.format(dtf);

        System.out.println(out);
    }
}