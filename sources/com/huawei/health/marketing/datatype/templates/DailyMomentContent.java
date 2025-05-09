package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import java.util.List;

/* loaded from: classes3.dex */
public class DailyMomentContent {
    private Integer displayRule;
    private Integer displayUpperLimit;
    private List<SingleDailyMomentContent> momentContents;
    private Integer rank;

    public DailyMomentContent(int i, int i2, int i3, List<SingleDailyMomentContent> list) {
        this.rank = Integer.valueOf(i);
        this.displayRule = Integer.valueOf(i2);
        this.displayUpperLimit = Integer.valueOf(i3);
        this.momentContents = list;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer num) {
        this.rank = num;
    }

    public Integer getDisplayRule() {
        return this.displayRule;
    }

    public void setDisplayRule(Integer num) {
        this.displayRule = num;
    }

    public Integer getDisplayUpperLimit() {
        return this.displayUpperLimit;
    }

    public void setDisplayUpperLimit(Integer num) {
        this.displayUpperLimit = num;
    }

    public List<SingleDailyMomentContent> getMomentContents() {
        return this.momentContents;
    }

    public void setMomentContents(List<SingleDailyMomentContent> list) {
        this.momentContents = list;
    }
}
