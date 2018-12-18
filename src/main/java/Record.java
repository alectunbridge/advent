import java.time.LocalDateTime;
import java.util.Objects;

class Record {
    private LocalDateTime date;
    private EventType eventType;
    private int guardId;

    LocalDateTime getDate() {
        return date;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return guardId == record.guardId &&
                Objects.equals(date, record.date) &&
                eventType == record.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, eventType, guardId);
    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", eventType=" + eventType +
                ", guardId=" + guardId +
                '}';
    }
}
