package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class Tab implements Parcelable {
    public static final Parcelable.Creator<Tab> CREATOR = new Parcelable.Creator<Tab>() { // from class: com.huawei.health.marketing.request.Tab.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tab createFromParcel(Parcel parcel) {
            return new Tab(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tab[] newArray(int i) {
            return new Tab[i];
        }
    };
    private long id;
    private String tabName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected Tab(Parcel parcel) {
        this.id = parcel.readLong();
        this.tabName = parcel.readString();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.tabName;
    }

    public void setName(String str) {
        this.tabName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.tabName);
    }
}
