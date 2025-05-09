package com.huawei.health.marketing.datatype.templates;

import java.util.List;

/* loaded from: classes3.dex */
public class FamousTeacherSeriesRecTemplate extends MarketBaseTemplate {
    private List<SingleFamousTeacherStandardContent> gridContents;

    public List<SingleFamousTeacherStandardContent> getGridContents() {
        return this.gridContents;
    }

    @Override // com.huawei.health.marketing.datatype.templates.MarketBaseTemplate
    public String toString() {
        return "FamousTeacherSeriesRecTemplate{" + super.toString() + ", gridContents=" + this.gridContents + '}';
    }
}
