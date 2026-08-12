import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) throws ParseException {
        showDate();
        showCalendar();
        showLegacyFormatting();
    }

    private static void showDate() {
        /* Date represents an instant as milliseconds counted from the Unix epoch */
        Date currentDate = new Date();
        Date epoch = new Date(0);

        /* getTime returns the milliseconds stored by the Date object */
        long currentMilliseconds = currentDate.getTime();

        /* before and after compare two instants */
        boolean currentDateIsAfterEpoch = currentDate.after(epoch);

        /* Date is mutable because setTime replaces its stored instant */
        Date copiedDate = new Date(currentDate.getTime());
        copiedDate.setTime(copiedDate.getTime() + 60_000);

        /* most Date methods for individual fields are deprecated */
        /* Calendar was later introduced to manipulate date and time fields */
        System.out.println("Date: milliseconds = " + currentMilliseconds
                + ", after epoch = " + currentDateIsAfterEpoch
                + ", copied date is later = " + copiedDate.after(currentDate));
    }

    private static void showCalendar() {
        /* Calendar combines an instant with a time zone and calendar rules */
        Calendar calendar = Calendar.getInstance(
                TimeZone.getTimeZone("America/Sao_Paulo"), Locale.US);

        /* clear removes the current fields before setting a specific date */
        calendar.clear();

        /* Calendar months start at zero, so constants are safer than numeric months */
        calendar.set(2026, Calendar.AUGUST, 12, 10, 30, 0);

        /* get reads an individual field from the calendar */
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        /* add performs calendar-aware arithmetic and adjusts larger fields */
        calendar.add(Calendar.DAY_OF_MONTH, 5);

        /* set changes one field directly while add changes it relatively */
        calendar.set(Calendar.HOUR_OF_DAY, 14);

        /* getTime converts the Calendar state into a Date */
        Date resultingDate = calendar.getTime();

        System.out.println("Calendar: original fields = " + year + "-" + month + "-" + day
                + ", date after changes = " + resultingDate);
    }

    private static void showLegacyFormatting() throws ParseException {
        Date date = new Date();

        /* DateFormat provides predefined locale-dependent date and time styles */
        DateFormat shortBrazilianFormat = DateFormat.getDateInstance(
                DateFormat.SHORT, Locale.forLanguageTag("pt-BR"));
        String localizedDate = shortBrazilianFormat.format(date);

        /* SimpleDateFormat uses a custom pattern to convert Date into text */
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        String formattedDate = formatter.format(date);

        /* parse performs the reverse conversion from text into Date */
        formatter.setLenient(false);
        Date parsedDate = formatter.parse("12/08/2026 14:30:00");

        /* lenient false rejects invalid dates instead of automatically adjusting them */
        boolean invalidDateWasRejected = false;
        try {
            formatter.parse("40/15/2026 14:30:00");
        } catch (ParseException exception) {
            invalidDateWasRejected = true;
        }

        /* pattern letters are case-sensitive because MM is month and mm is minute */
        /* SimpleDateFormat is mutable and not thread-safe, so instances should not be shared */
        /* modern code should prefer java.time and DateTimeFormatter */
        System.out.println("Formatting: localized = " + localizedDate
                + ", custom = " + formattedDate
                + ", parsed = " + formatter.format(parsedDate)
                + ", invalid rejected = " + invalidDateWasRejected);
    }
}
