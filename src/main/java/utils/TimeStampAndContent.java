package utils;

/**
 * Created by Fresher on 17/09/2017.
 */
public class TimeStampAndContent implements Comparable<TimeStampAndContent> {


    private long timeStamp;

    private String content;


    public TimeStampAndContent(long timeStamp, String content) {
        this.timeStamp = timeStamp;
        this.content = content;
    }


    public long getTimeStamp() {
        return timeStamp;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(TimeStampAndContent o) {
        return (int) (this.getTimeStamp() - o.getTimeStamp());
    }
}
