import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;

class Guard {
    private LocalDateTime lastActivityTime = null;
    private int guardId;
    private LinkedList<int[]> minutes;

    Guard(Record record) {
        this.guardId = record.getGuardId();
        minutes = new LinkedList<>();
        addDay(record);
    }

    int getTotalMinutesAsleep() {
        return minutes.stream().mapToInt(minArr -> Arrays.stream(minArr).sum()).sum();
    }

    void addDay(Record record) {
        int[] minutesArray = new int[60];
        minutes.add(minutesArray);
        lastActivityTime = record.getDate();
    }

    public void fallAsleep(LocalDateTime date) {
        int[] minuteArray = minutes.getLast();
        minuteArray[date.getMinute()] = 1;
        lastActivityTime = date;
    }

    public void wakeUp(LocalDateTime date) {
        int[] minuteArray = minutes.getLast();
        for(int i=lastActivityTime.getMinute()+1; i<date.getMinute(); i++)
        {
            minuteArray[i] = 1;
        }
        lastActivityTime = date;
    }

    public int getId() {
        return guardId;
    }

    public int getMostFrequentlyAsleepMinute() {
        int[] aggregatedMinutes = new int[60];
        for(int[] minArr:minutes){
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
}
