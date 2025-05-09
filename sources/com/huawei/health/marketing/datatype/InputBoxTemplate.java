package com.huawei.health.marketing.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class InputBoxTemplate implements Parcelable {
    public static final Parcelable.Creator<InputBoxTemplate> CREATOR = new Parcelable.Creator<InputBoxTemplate>() { // from class: com.huawei.health.marketing.datatype.InputBoxTemplate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputBoxTemplate createFromParcel(Parcel parcel) {
            return new InputBoxTemplate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputBoxTemplate[] newArray(int i) {
            return new InputBoxTemplate[i];
        }
    };
    public static final String NONE_TYPE = "none";
    public static final String NORMAL_TYPE = "normal";
    public static final String SEARCH_TYPE = "search";
    private String extend;
    private String lessonSubCategory;
    private String linkType;
    private String linkValue;
    private String theme;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.theme);
        parcel.writeString(this.linkType);
        parcel.writeString(this.linkValue);
        parcel.writeString(this.extend);
        parcel.writeString(this.lessonSubCategory);
    }

    public InputBoxTemplate() {
    }

    protected InputBoxTemplate(Parcel parcel) {
        this.theme = parcel.readString();
        this.linkType = parcel.readString();
        this.linkValue = parcel.readString();
        this.extend = parcel.readString();
        this.lessonSubCategory = parcel.readString();
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public String getLinkType() {
        return this.linkType;
    }

    public void setLinkType(String str) {
        this.linkType = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public String getLessonSubCategory() {
        return this.lessonSubCategory;
    }

    public void setLessonSubCategory(String str) {
        this.lessonSubCategory = str;
    }
}
