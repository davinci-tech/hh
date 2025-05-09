package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleGridContent;

/* loaded from: classes3.dex */
public class SingleFunctionGridContent extends SingleGridContent {
    private String linkValueButton;
    private String subTheme;
    private boolean subThemeVisibility;

    public SingleFunctionGridContent(SingleGridContent.Builder builder) {
        super(builder);
    }

    public String getSubTheme() {
        return this.subTheme;
    }

    public void setSubTheme(String str) {
        this.subTheme = str;
    }

    public boolean isSubThemeVisibility() {
        return this.subThemeVisibility;
    }

    public void setSubThemeVisibility(boolean z) {
        this.subThemeVisibility = z;
    }

    public String getLinkValueButton() {
        return this.linkValueButton;
    }

    public void setLinkValueButton(String str) {
        this.linkValueButton = str;
    }
}
