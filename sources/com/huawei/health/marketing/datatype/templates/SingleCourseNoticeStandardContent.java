package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class SingleCourseNoticeStandardContent {

    @SerializedName("subContents")
    private List<SingleCourseNoticeContent> subContents;

    @SerializedName("tabName")
    private String tabName;

    @SerializedName("tabPriority")
    private int tabPriority;

    @SerializedName("theme")
    private String theme;

    public int getTabPriority() {
        return this.tabPriority;
    }

    public String getTabName() {
        return this.tabName;
    }

    public String getTheme() {
        return this.theme;
    }

    public List<SingleCourseNoticeContent> getSubContents() {
        return this.subContents;
    }
}
