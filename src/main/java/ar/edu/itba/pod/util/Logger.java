package ar.edu.itba.pod.util;

import java.io.IOException;
import java.io.OutputStream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat
            .forPattern("dd/mm/yyyy  hh:mm:ss:SSS");

    private final OutputStream os;

    public Logger() {
        this.os = System.out;
    }

    public Logger(OutputStream os) {
        this.os = os;
    }

    public void logTimestamp(String header, DateTime dateTime)
            throws IOException {
        String timestampString = header + ": "
                + dateTimeFormatter.print(dateTime) + "\n";
        os.write(timestampString.getBytes());
    }
}
