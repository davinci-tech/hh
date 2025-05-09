package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import java.util.List;

/* loaded from: classes3.dex */
public class TextLinkTemplate extends BaseTemplate {

    @SerializedName("gridContents")
    private List<SingleTextContent> gridContents;

    @SerializedName("name")
    private String name;

    @SerializedName("nameVisibility")
    private boolean nameVisibility;

    public List<SingleTextContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleTextContent> list) {
        this.gridContents = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isNameVisible() {
        return this.nameVisibility;
    }

    public void setNameVisible(boolean z) {
        this.nameVisibility = z;
    }
}
