package com.huawei.health.marketing.datatype;

import java.util.List;

/* loaded from: classes7.dex */
public class MonthlyReportTemplate {
    private String extend;
    private List<SingleMonth> gridContents;

    public MonthlyReportTemplate(String str, List<SingleMonth> list) {
        this.extend = str;
        this.gridContents = list;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public List<SingleMonth> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleMonth> list) {
        this.gridContents = list;
    }
}
