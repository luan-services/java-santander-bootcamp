import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class App {
    public static void main(String[] args) {
        showLocalDate();
        showLocalDateTime();
        showOffsetTime();
        showOffsetDateTime();
    }

    private static void showLocalDate() {
        /* LocalDate represents a date without a time or time zone */
        LocalDate courseStart = LocalDate.of(2026, 8, 12);

        /* parse creates a date from ISO text in the yyyy-MM-dd format */
        LocalDate courseEnd = LocalDate.parse("2026-12-20");

        /* java.time objects are immutable, so operations return new objects */
        LocalDate nextWeek = courseStart.plusWeeks(1);
        LocalDate previousDay = courseStart.minusDays(1);

        /* getters provide individual date fields and useful enum values */
        int year = courseStart.getYear();
        int month = courseStart.getMonthValue();
        int day = courseStart.getDayOfMonth();
        DayOfWeek dayOfWeek = courseStart.getDayOfWeek();

        /* Period represents a difference in years, months, and days */
        Period coursePeriod = Period.between(courseStart, courseEnd);

        /* ChronoUnit calculates a difference using one specific unit */
        long totalDays = ChronoUnit.DAYS.between(courseStart, courseEnd);

        /* DateTimeFormatter formats immutable java.time objects safely */
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = courseStart.format(dateFormatter);

        System.out.println("LocalDate: date = " + formattedDate
                + ", next week = " + nextWeek
                + ", previous day = " + previousDay
                + ", fields = " + year + "-" + month + "-" + day
                + ", day of week = " + dayOfWeek
                + ", period = " + coursePeriod
                + ", total days = " + totalDays);
    }

    private static void showLocalDateTime() {
        /* LocalDateTime combines a date and time without a time zone or offset */
        LocalDateTime classStart = LocalDateTime.of(2026, 8, 12, 19, 30);

        /* a LocalDate and LocalTime can be combined into a LocalDateTime */
        LocalDate date = LocalDate.of(2026, 8, 13);
        LocalTime time = LocalTime.of(19, 30);
        LocalDateTime nextClass = LocalDateTime.of(date, time);

        /* plus and minus return new values and leave the original unchanged */
        LocalDateTime classEnd = classStart.plusHours(2).plusMinutes(15);

        /* Duration represents an exact time-based amount in hours, minutes, or seconds */
        Duration classDuration = Duration.between(classStart, classEnd);

        /* isBefore and isAfter compare local date-time values */
        boolean nextClassIsLater = nextClass.isAfter(classStart);

        /* parsing uses a formatter when the text is not in the default ISO format */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse("15/08/2026 10:00", formatter);
        String formattedDateTime = classStart.format(formatter);

        /* use LocalDateTime when the zone is irrelevant or intentionally unknown */
        System.out.println("LocalDateTime: start = " + formattedDateTime
                + ", end = " + classEnd.format(formatter)
                + ", duration in minutes = " + classDuration.toMinutes()
                + ", next class is later = " + nextClassIsLater
                + ", parsed = " + parsedDateTime.format(formatter));
    }

    private static void showOffsetTime() {
        /* OffsetTime stores a time of day together with a fixed UTC offset */
        OffsetTime brazilTime = OffsetTime.of(14, 30, 0, 0, ZoneOffset.ofHours(-3));

        /* withOffsetSameInstant changes the offset while preserving the same instant */
        OffsetTime utcTime = brazilTime.withOffsetSameInstant(ZoneOffset.UTC);

        /* withOffsetSameLocal changes only the offset and preserves the local clock time */
        OffsetTime sameClockDifferentOffset = brazilTime.withOffsetSameLocal(ZoneOffset.ofHours(2));

        /* plus methods perform immutable time arithmetic */
        OffsetTime oneHourLater = brazilTime.plusHours(1);

        /* OffsetTime is useful for recurring times where a fixed offset matters */
        System.out.println("OffsetTime: Brazil = " + brazilTime
                + ", same instant in UTC = " + utcTime
                + ", same clock with another offset = " + sameClockDifferentOffset
                + ", one hour later = " + oneHourLater);
    }

    private static void showOffsetDateTime() {
        /* OffsetDateTime stores a date, time, and fixed offset from UTC */
        OffsetDateTime brazilDateTime = OffsetDateTime.of(
                2026, 8, 12, 14, 30, 0, 0, ZoneOffset.ofHours(-3));

        /* parse reads the standard ISO format including its offset */
        OffsetDateTime parsedDateTime = OffsetDateTime.parse("2026-08-12T17:30:00Z");

        /* withOffsetSameInstant represents the same instant using another offset */
        OffsetDateTime utcDateTime = brazilDateTime.withOffsetSameInstant(ZoneOffset.UTC);

        /* toInstant removes the local representation and keeps the universal instant */
        boolean sameInstant = brazilDateTime.toInstant().equals(parsedDateTime.toInstant());

        /* plusDays and other operations return a new OffsetDateTime */
        OffsetDateTime nextDay = brazilDateTime.plusDays(1);

        /* the offset is fixed and does not contain regional daylight-saving rules */
        /* use ZonedDateTime when rules for a region such as America/Sao_Paulo matter */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm xxx");
        System.out.println("OffsetDateTime: Brazil = " + brazilDateTime.format(formatter)
                + ", UTC = " + utcDateTime.format(formatter)
                + ", same instant = " + sameInstant
                + ", next day = " + nextDay.format(formatter));
    }
}
