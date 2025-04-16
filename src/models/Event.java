package models;

public class Event {
    private int event_id;
    private String name;
    private String location;
    private String date;
    private String category;

    public Event(int event_id, String name, String location, String date, String category) {
        this.event_id = event_id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.category = category;
    }

    public int getEventId() { return event_id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getCategory() { return category; }
}
