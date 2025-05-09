package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.CommonParam;
import java.util.Objects;

/* loaded from: classes.dex */
public class ReceiverFilter extends CommonParam {
    public static final Parcelable.Creator<ReceiverFilter> CREATOR = new Parcelable.Creator<ReceiverFilter>() { // from class: com.huawei.hihealth.model.ReceiverFilter.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwy_, reason: merged with bridge method [inline-methods] */
        public ReceiverFilter createFromParcel(Parcel parcel) {
            return new ReceiverFilter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ReceiverFilter[] newArray(int i) {
            return new ReceiverFilter[i];
        }
    };
    private String action;
    private String pkgName;

    public ReceiverFilter(String str, String str2) {
        this.pkgName = str;
        this.action = str2;
    }

    protected ReceiverFilter(Parcel parcel) {
        super(parcel);
        this.pkgName = parcel.readString();
        this.action = parcel.readString();
    }

    @Override // com.huawei.hihealth.CommonParam, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.pkgName);
        parcel.writeString(this.action);
    }

    public int hashCode() {
        return Objects.hash(this.pkgName, this.action);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReceiverFilter)) {
            return false;
        }
        ReceiverFilter receiverFilter = (ReceiverFilter) obj;
        return Objects.equals(this.pkgName, receiverFilter.getPkgName()) && Objects.equals(this.action, receiverFilter.getAction());
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }
}
