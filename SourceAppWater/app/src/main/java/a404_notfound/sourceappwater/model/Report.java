package a404_notfound.sourceappwater.model;

/**
 * Created by Michelle on 2/27/2017.
 */

public abstract class Report {
    //someone please double check these types
    private int[] date;
    private int[] time;
    private static int reportNumber;
    private String reporter;
    private int[] coordinates;
    public Report (String reporter, int[] coordinates) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        //add date and time from fire base as default.
        reportNumber++;
    }
}
