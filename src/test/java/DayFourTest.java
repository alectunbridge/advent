import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFourTest {

    private DayFour dayFour;

    @Before
    public void setUp() {
        dayFour = new DayFour();
    }

    @Test
    public void parseShiftBeginsRecord() {
        Record record = dayFour.parseRecord("[1518-09-22 23:50] Guard #2309 begins shift");

        assertThat(record.getDate()).isEqualTo(LocalDateTime.of(1518, 9, 22, 23, 50));
        assertThat(record.getEventType()).isEqualTo(EventType.SHIFT_BEGINS);
        assertThat(record.getGuardId()).isEqualTo(2309);
    }

    @Test
    public void parseWakeUpRecord() {
        dayFour.parseRecord("[1518-10-09 00:42] Guard #2309 begins shift");
        Record record = dayFour.parseRecord("[1518-10-09 00:43] wakes up");

        assertThat(record.getDate()).isEqualTo(LocalDateTime.of(1518, 10, 9, 0, 43));
        assertThat(record.getEventType()).isEqualTo(EventType.WAKE_UP);
    }

    @Test
    public void parseFallAsleepRecord() {
        dayFour.parseRecord("[1518-10-12 00:35] Guard #2309 begins shift");
        Record record = dayFour.parseRecord("[1518-10-12 00:35] falls asleep");

        assertThat(record.getDate()).isEqualTo(LocalDateTime.of(1518, 10, 12, 0, 35));
    }

    @Test
    public void sortRecords() {
        List<Record> records = dayFour.parseRecords("[1518-10-12 00:35] Guard #2309 begins shift", "[1518-10-09 00:43] wakes up");
        assertThat(records).containsExactly(
                dayFour.parseRecord("[1518-10-09 00:43] wakes up"),
                dayFour.parseRecord("[1518-10-12 00:35] Guard #2309 begins shift"));
    }

    @Test
    public void getTotalMinutesAsleep() {
        dayFour.parseRecords(
                "[1518-09-22 23:50] Guard #2309 begins shift",
                "[1518-09-22 23:51] falls asleep");
        assertThat(dayFour.getGuards().get(2309).getTotalMinutesAsleep()).isEqualTo(1);
    }

    @Test
    public void sample() {
        dayFour.parseRecords(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up",
                "[1518-11-01 23:58] Guard #99 begins shift",
                "[1518-11-02 00:40] falls asleep",
                "[1518-11-02 00:50] wakes up",
                "[1518-11-03 00:05] Guard #10 begins shift",
                "[1518-11-03 00:24] falls asleep",
                "[1518-11-03 00:29] wakes up",
                "[1518-11-04 00:02] Guard #99 begins shift",
                "[1518-11-04 00:36] falls asleep",
                "[1518-11-04 00:46] wakes up",
                "[1518-11-05 00:03] Guard #99 begins shift",
                "[1518-11-05 00:45] falls asleep",
                "[1518-11-05 00:55] wakes up");

        assertThat(dayFour.getGuards().get(10).getTotalMinutesAsleep()).isEqualTo(50);
        assertThat(dayFour.getGuards().get(99).getTotalMinutesAsleep()).isEqualTo(30);
        assertThat(dayFour.getGuards().get(99).getTotalMinutesAsleep()).isEqualTo(30);

        assertThat(dayFour.getSolution()).isEqualTo(240);
    }

    @Test
    public void firstSolution() {
        List<Record> records = dayFour.parseRecords(Input4.STRINGS);
        records.forEach(s->System.out.println(s));
        assertThat(dayFour.getSolution()).isEqualTo(0);
    }
}
