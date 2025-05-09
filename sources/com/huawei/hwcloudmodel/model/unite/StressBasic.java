package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class StressBasic {
    private Integer lastScore;
    private Integer maxScore;
    private Integer meanScore;
    private Integer measureCount;
    private Integer minScore;

    public Integer fetchMeasureCount() {
        return this.measureCount;
    }

    public void configMeasureCount(Integer num) {
        this.measureCount = num;
    }

    public Integer fetchMaxScore() {
        return this.maxScore;
    }

    public void configMaxScore(Integer num) {
        this.maxScore = num;
    }

    public Integer fetchMinScore() {
        return this.minScore;
    }

    public void configMinScore(Integer num) {
        this.minScore = num;
    }

    public Integer fetchMeanScore() {
        return this.meanScore;
    }

    public void configMeanScore(Integer num) {
        this.meanScore = num;
    }

    public Integer fetchLastScore() {
        return this.lastScore;
    }

    public void configLastScore(Integer num) {
        this.lastScore = num;
    }
}
