package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class District implements Parcelable {
    public static final Parcelable.Creator<District> CREATOR = new Parcelable.Creator<District>() { // from class: com.amap.api.services.route.District.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ District[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ District createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static District a(Parcel parcel) {
            return new District(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1532a;
    private String b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDistrictName() {
        return this.f1532a;
    }

    public void setDistrictName(String str) {
        this.f1532a = str;
    }

    public String getDistrictAdcode() {
        return this.b;
    }

    public void setDistrictAdcode(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1532a);
        parcel.writeString(this.b);
    }

    public District(Parcel parcel) {
        this.f1532a = parcel.readString();
        this.b = parcel.readString();
    }

    public District() {
    }
}
