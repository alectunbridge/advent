import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class Guard {
    private LocalDateTime lastActivityTime = null;
    private int guardId;
    private Map<LocalDate,int[]> minutes;

    Guard(Record record) {
        this.guardId = record.getGuardId();
        minutes = new LinkedHashMap<>();
        addDay(record);
    }

    int getTotalMinutesAsleep() {
        int total = 0;
        for (int[] minArr : minutes.values()) {
            total += Arrays.stream(minArr).sum();
        }
        return total;
    }

    void addDay(Record record) {
        int[] minutesArray = new int[60];
        minutes.put(record.getDate().toLocalDate(),minutesArray);
        lastActivityTime = record.getDate();
    }

    public void fallAsleep(LocalDateTime date) {
        int[] minuteArray = minutes.get(date.toLocalDate());
        if(minuteArray == null){
            minuteArray = new int[60];
            minutes.put(date.toLocalDate(), minuteArray);
        }
        minuteArray[date.getMinute()] = 1;
        lastActivityTime = date;
    }

    public void wakeUp(LocalDateTime date) {
        int[] minuteArray = minutes.get(date.toLocalDate());
        if(minuteArray == null){
            minuteArray = new int[60];
            minutes.put(date.toLocalDate(), minuteArray);
        }

        //set sleep time
        int[] minutesToSetToSleep = minutes.get(lastActivityTime.toLocalDate());
        if(lastActivityTime.toLocalDate().equals(date.toLocalDate())){
            for(int i=lastActivityTime.getMinute()+1; i<date.getMinute(); i++) {
                minutesToSetToSleep[i] = 1;
            }
        } else {
            for(int i=lastActivityTime.getMinute()+1; i<60; i++) {
                minutesToSetToSleep[i] = 1;
            }
            minutesToSetToSleep = minutes.get(date.toLocalDate());
            for(int j=0; j<date.getMinute(); j++){
                minutesToSetToSleep[j] = 1;
            }
        }
        lastActivityTime = date;
    }

    public int getId() {
        return guardId;
    }

    public int getMostFrequentlyAsleepMinute() {
        int[] aggregatedMinutes = new int[60];
        for(int[] minArr: minutes.values()){
            for(int i=0; i<60; i++){
                aggregatedMinutes[i] += minArr[i];
            }
        }
        int result = -1;
        int max = 0;
        for(int j=0; j<60; j++){
            if(aggregatedMinutes[j]>max){
                max=aggregatedMinutes[j];
                result = j;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        List<Map.Entry<LocalDate, int[]>> collect = minutes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());

        collect.forEach((e) -> {
            result.append(e.getKey());
            result.append(" ");
            result.append(Arrays.stream(e.getValue()).mapToObj( i->String.format("%2d",i)).collect(Collectors.joining(" ")));
            result.append("\n");
        });
        return result.toString();
    }
}
