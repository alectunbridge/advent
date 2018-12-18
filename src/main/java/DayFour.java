import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DayFour {
    private Map<Integer,Guard> guards = new HashMap<>();
    private int guardOnDuty;

    Record parseRecord(String input) {
        Record record = new Record();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //"[1518-09-22 23:50] Guard #2309 begins shift"
        Pattern shiftBeginsPattern = Pattern.compile("^\\[(.*)] Guard #(\\d+).*$");
        Matcher shiftBeginsMatcher = shiftBeginsPattern.matcher(input);

//        "[1518-10-09 00:43] wakes up"
        Pattern wakeUpPattern = Pattern.compile("^\\[(.*)] wakes up$");
        Matcher wakeUpMatcher = wakeUpPattern.matcher(input);

        Pattern fallAsleepPattern = Pattern.compile("^\\[(.*)] falls asleep$");
        Matcher fallAsleepMatcher = fallAsleepPattern.matcher(input);

        if (shiftBeginsMatcher.matches()) {
            record.setDate(LocalDateTime.parse(shiftBeginsMatcher.group(1), dateTimeFormatter));
            record.setEventType(EventType.SHIFT_BEGINS);
            record.setGuardId(Integer.parseInt(shiftBeginsMatcher.group(2)));
            Guard guard = guards.putIfAbsent(record.getGuardId(),new Guard(record));
            if(guard != null){
                guard.addDay(record);
            }
            guardOnDuty = record.getGuardId();
        } else if(wakeUpMatcher.matches()){
            record.setDate(LocalDateTime.parse(wakeUpMatcher.group(1), dateTimeFormatter));
            record.setEventType(EventType.WAKE_UP);
            guards.get(guardOnDuty).wakeUp(record.getDate());
        } else if(fallAsleepMatcher.matches()){
            record.setDate(LocalDateTime.parse(fallAsleepMatcher.group(1), dateTimeFormatter));
            record.setEventType(EventType.FALL_ASLEEP);
            guards.get(guardOnDuty).fallAsleep(record.getDate());
        } else {
            throw new RuntimeException("Cannot parse record");
        }
        return record;
    }

    public List<Record> parseRecords(String... input) {
        List<Record> records = new ArrayList<>();
        for (String string : input) {
            records.add(parseRecord(string));
        }
        records.sort(Comparator.comparing(Record::getDate));
        return records;
    }

    public Map<Integer,Guard> getGuards() {
        return guards;
    }

    public int getSolution() {
        Guard laziestGuard = null;
        int maxTotalMinutes = 0;
        for(Guard guard:guards.values()){
            int totalMinutesAsleep = guard.getTotalMinutesAsleep();
            if(totalMinutesAsleep > maxTotalMinutes){
                maxTotalMinutes = totalMinutesAsleep;
                laziestGuard = guard;
            }
        };
        System.out.println(laziestGuard.getId());
        return laziestGuard.getId()*laziestGuard.getMostFrequentlyAsleepMinute();
    }

}
