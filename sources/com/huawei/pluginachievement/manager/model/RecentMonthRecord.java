package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class RecentMonthRecord extends mcz {
    private String comments1;
    private String comments2;
    private double distance;
    private double distanceRanking;
    private long endDate;
    private long endUtcDate;
    private long firstDate;
    private int kakaNum;
    private String medals;
    private int minReportNo;
    private int price;
    private int reportNo;
    private double steps;
    private double stepsRanking;

    public RecentMonthRecord() {
        super(4);
        this.comments1 = "";
        this.comments2 = "";
        this.medals = "";
    }

    public double acquireSteps() {
        return this.steps;
    }

    public void setSteps(double d) {
        this.steps = d;
    }

    public double acquireDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public int acquireReportNo() {
        return this.reportNo;
    }

    public void setReportNo(int i) {
        this.reportNo = i;
    }

    public double acquireDistanceRanking() {
        return this.distanceRanking;
    }

    public void setDistanceRanking(double d) {
        this.distanceRanking = d;
    }

    public double acquireStepsRanking() {
        return this.stepsRanking;
    }

    public void setStepsRanking(double d) {
        this.stepsRanking = d;
    }

    public int getKakaNum() {
        return this.kakaNum;
    }

    public void setKakaNum(int i) {
        this.kakaNum = i;
    }

    public int acquirePrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getComments1() {
        return this.comments1;
    }

    public void setComments1(String str) {
        this.comments1 = str;
    }

    public String getComments2() {
        return this.comments2;
    }

    public void setComments2(String str) {
        this.comments2 = str;
    }

    public String acquireMedals() {
        return this.medals;
    }

    public void setMedals(String str) {
        this.medals = str;
    }

    public long acquireFirstDate() {
        return this.firstDate;
    }

    public void saveFirstDate(long j) {
        this.firstDate = j;
    }

    public long acquireEndDate() {
        return this.endDate;
    }

    public void saveEndDate(long j) {
        this.endDate = j;
    }

    public long getEndUtcDate() {
        return this.endUtcDate;
    }

    public void setEndUtcDate(long j) {
        this.endUtcDate = j;
    }

    public int acquireMinReportNo() {
        return this.minReportNo;
    }

    public void setMinReportNo(int i) {
        this.minReportNo = i;
    }

    public String toString() {
        return "RecentMonthRecord{huid='" + super.getHuid() + "', firstDate=" + this.firstDate + ", endDate=" + this.endDate + ", steps=" + this.steps + ", distance=" + this.distance + ", reportNo=" + this.reportNo + ", distanceRanking=" + this.distanceRanking + ", stepsRanking=" + this.stepsRanking + ", kakaNum=" + this.kakaNum + ", price=" + this.price + ", comments1='" + this.comments1 + "', comments2='" + this.comments2 + "', medals='" + this.medals + "', endUtcDate=" + this.endUtcDate + '}';
    }
}
