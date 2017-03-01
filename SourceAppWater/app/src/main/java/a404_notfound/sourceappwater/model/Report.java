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
    private String coordinates;

    public Report (String reporter, String coordinates) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        //add date and time from fire base as default.
        reportNumber++;
    }

    public String getReporter() {
        return this.reporter;
    }

    public String getCoordinates() {
        return this.coordinates;
    }

    public int getReportNumber() {
        return this.reportNumber;
    }
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
