package com.huawei.healthcloud.plugintrack.golf.bean;

/* loaded from: classes8.dex */
public class GolfHoleScore {
    private int fairwayHits;
    private boolean gir;
    private GolfHistoryHittingTrack golfHistoryHittingTrack;
    private int handicap;
    private long id;
    private int par;
    private int penalty;
    private int putts;
    private int score;
    private int sequence;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int i) {
        this.sequence = i;
    }

    public int getPar() {
        return this.par;
    }

    public void setPar(int i) {
        this.par = i;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getHandicap() {
        return this.handicap;
    }

    public void setHandicap(int i) {
        this.handicap = i;
    }

    public int getPutts() {
        return this.putts;
    }

    public void setPutts(int i) {
        this.putts = i;
    }

    public int getPenalty() {
        return this.penalty;
    }

    public void setPenalty(int i) {
        this.penalty = i;
    }

    public int getFairwayHits() {
        return this.fairwayHits;
    }

    public void setFairwayHits(int i) {
        this.fairwayHits = i;
    }

    public boolean isGir() {
        return this.gir;
    }

    public void setGir(boolean z) {
        this.gir = z;
    }

    public GolfHistoryHittingTrack getGolfHistoryHittingTrack() {
        return this.golfHistoryHittingTrack;
    }

    public void setGolfHistoryHittingTrack(GolfHistoryHittingTrack golfHistoryHittingTrack) {
        this.golfHistoryHittingTrack = golfHistoryHittingTrack;
    }
}
