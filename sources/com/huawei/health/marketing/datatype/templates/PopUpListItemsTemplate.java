package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.SingleGridContent;
import java.util.List;

/* loaded from: classes3.dex */
public class PopUpListItemsTemplate {

    @SerializedName("description")
    private String description;

    @SerializedName("gridContents")
    private List<SingleGridContent> gridContents;

    @SerializedName("theme")
    private String theme;

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<SingleGridContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleGridContent> list) {
        this.gridContents = list;
    }
}
