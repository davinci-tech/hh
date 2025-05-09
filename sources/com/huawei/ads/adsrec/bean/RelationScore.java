package com.huawei.ads.adsrec.bean;

import com.huawei.ads.fund.anno.DataKeep;

@DataKeep
/* loaded from: classes2.dex */
public class RelationScore {
    private String contentId;
    private double intentScore;
    private double mergedScore;
    private double score;

    public double d() {
        return this.score;
    }

    public double a() {
        return this.mergedScore;
    }

    public double b() {
        return this.intentScore;
    }

    public String c() {
        return this.contentId;
    }

    public RelationScore(RelationScore relationScore) {
        this.contentId = relationScore.c();
        this.score = relationScore.d();
        this.intentScore = relationScore.b();
        this.mergedScore = relationScore.a();
    }

    public RelationScore() {
    }
}
