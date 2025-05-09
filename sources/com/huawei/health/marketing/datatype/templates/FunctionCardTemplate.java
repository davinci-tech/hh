package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.FunctionCardContent;
import java.util.List;

/* loaded from: classes8.dex */
public class FunctionCardTemplate {
    private String extend;
    private List<FunctionCardContent> gridContents;
    private String name;
    private Boolean nameVisibility;

    public FunctionCardTemplate(String str, Boolean bool, String str2, List<FunctionCardContent> list) {
        this.name = str;
        this.nameVisibility = bool;
        this.extend = str2;
        this.gridContents = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Boolean getNameVisibility() {
        return this.nameVisibility;
    }

    public void setNameVisibility(Boolean bool) {
        this.nameVisibility = bool;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public List<FunctionCardContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<FunctionCardContent> list) {
        this.gridContents = list;
    }
}
