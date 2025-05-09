package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleEntryContent;
import java.util.List;

/* loaded from: classes3.dex */
public class QuickEntryTemplate extends BaseTemplate {
    private List<SingleEntryContent> gridContents;

    public void setGridContents(List<SingleEntryContent> list) {
        this.gridContents = list;
    }

    public List<SingleEntryContent> getGridContents() {
        return this.gridContents;
    }
}
