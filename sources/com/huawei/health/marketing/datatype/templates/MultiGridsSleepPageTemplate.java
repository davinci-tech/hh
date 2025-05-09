package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.TimePeriod;
import java.util.List;

/* loaded from: classes3.dex */
public class MultiGridsSleepPageTemplate extends BaseTemplate {
    private List<SingleGridContent> gridContents;
    private String linkValue;
    private String moreMenuTitle;
    private String name;
    private boolean nameVisibility;
    private List<TimePeriod> timePeriods;

    public List<TimePeriod> getTimePeriods() {
        return this.timePeriods;
    }

    public void setTimePeriods(List<TimePeriod> list) {
        this.timePeriods = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public void setNameVisibility(boolean z) {
        this.nameVisibility = z;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getMoreMenuTitle() {
        return this.moreMenuTitle;
    }

    public void setMoreMenuTitle(String str) {
        this.moreMenuTitle = str;
    }

    public List<SingleGridContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleGridContent> list) {
        this.gridContents = list;
    }
}
