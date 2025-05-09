package com.huawei.ui.main.stories.fitness.activity.coresleep.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class SingleTab implements Parcelable {
    public static final Parcelable.Creator<SingleTab> CREATOR = new Parcelable.Creator<SingleTab>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.model.SingleTab.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: drN_, reason: merged with bridge method [inline-methods] */
        public SingleTab createFromParcel(Parcel parcel) {
            return new SingleTab(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SingleTab[] newArray(int i) {
            return new SingleTab[i];
        }
    };

    @SerializedName("tabLink")
    private String tabLink;

    @SerializedName("tabName")
    private String tabName;

    @SerializedName("tabPriority")
    private int tabPriority;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SingleTab(Parcel parcel) {
        this.tabPriority = parcel.readInt();
        this.tabName = parcel.readString();
        this.tabLink = parcel.readString();
    }

    public int getTabPriority() {
        return this.tabPriority;
    }

    public void setTabPriority(int i) {
        this.tabPriority = i;
    }

    public String getTabName() {
        return this.tabName;
    }

    public void setTabName(String str) {
        this.tabName = str;
    }

    public String getTabLink() {
        return this.tabLink;
    }

    public void setTabLink(String str) {
        this.tabLink = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.tabPriority);
        parcel.writeString(this.tabName);
        parcel.writeString(this.tabLink);
    }

    public String toString() {
        return "SingleTab{tabPriority=" + this.tabPriority + ", tabName='" + this.tabName + "', tabLink='" + this.tabLink + "'}";
    }
}
