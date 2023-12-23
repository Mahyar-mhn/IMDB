package com.example.testjavafx;

public class Report {
    private static int nextId = 1; // for generating unique report IDs
    private int id;
    private Member reporter;
    private Review reportedReview;
    private String reason;

    public Report(Member reporter, Review reportedReview, String reason) {
        this.id = nextId++;
        this.reporter = reporter;
        this.reportedReview = reportedReview;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public Member getReporter() {
        return reporter;
    }

    public Review getReportedReview() {
        return reportedReview;
    }

    public String getReason() {
        return reason;
    }
}

