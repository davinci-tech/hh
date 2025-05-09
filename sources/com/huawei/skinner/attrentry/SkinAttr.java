package com.huawei.skinner.attrentry;

import android.view.View;

/* loaded from: classes6.dex */
public abstract class SkinAttr {
    public String attrName;
    public int attrValueRefId;
    public String attrValueRefName;
    public String attrValueTypeName;
    public volatile boolean isCancled = false;
    protected int priority;
    protected String requestResObjectType;

    public abstract void apply(View view, boolean z);

    public String toString() {
        return "SkinAttr \n[\nattrName=" + this.attrName + ", \nattrValueRefId=" + this.attrValueRefId + ", \nattrValueRefName=" + this.attrValueRefName + ", \nattrValueTypeName=" + this.attrValueTypeName + "\n]";
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SkinAttr) {
            return ((SkinAttr) obj).attrName.equals(this.attrName);
        }
        return false;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public void setAttrName(String str) {
        this.attrName = str;
    }

    public int getAttrValueRefId() {
        return this.attrValueRefId;
    }

    public void setAttrValueRefId(int i) {
        this.attrValueRefId = i;
    }

    public String getAttrValueRefName() {
        return this.attrValueRefName;
    }

    public void setAttrValueRefName(String str) {
        this.attrValueRefName = str;
    }

    public String getAttrValueTypeName() {
        return this.attrValueTypeName;
    }

    public void setAttrValueTypeName(String str) {
        this.attrValueTypeName = str;
    }

    public String getRequestResObjectType() {
        return this.requestResObjectType;
    }

    public void setRequestResObjectType(String str) {
        this.requestResObjectType = str;
    }

    public boolean isCancled() {
        return this.isCancled;
    }

    public void setCancled(boolean z) {
        this.isCancled = z;
    }
}
