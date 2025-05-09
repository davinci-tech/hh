package com.huawei.health.marketing.datatype;

import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import java.util.List;

/* loaded from: classes3.dex */
public class MultiTabHorizontalTemplate extends BaseTemplate {
    private List<SingleGrid4Tab> gridContents;
    private List<SingleTab> tabs;

    public List<SingleTab> getTabs() {
        return this.tabs;
    }

    public void setTabs(List<SingleTab> list) {
        this.tabs = list;
    }

    public List<SingleGrid4Tab> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleGrid4Tab> list) {
        this.gridContents = list;
    }
}
