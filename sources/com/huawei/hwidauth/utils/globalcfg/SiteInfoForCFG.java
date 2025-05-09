package com.huawei.hwidauth.utils.globalcfg;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class SiteInfoForCFG implements Parcelable {
    public static final Parcelable.Creator<SiteInfoForCFG> CREATOR = new Parcelable.Creator<SiteInfoForCFG>() { // from class: com.huawei.hwidauth.utils.globalcfg.SiteInfoForCFG.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bQG_, reason: merged with bridge method [inline-methods] */
        public SiteInfoForCFG createFromParcel(Parcel parcel) {
            return new SiteInfoForCFG(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SiteInfoForCFG[] newArray(int i) {
            return new SiteInfoForCFG[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f6384a;
    private String b;
    private String c;
    private String d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SiteInfoForCFG() {
    }

    protected SiteInfoForCFG(Parcel parcel) {
        this.f6384a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6384a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    public String a() {
        return this.f6384a;
    }

    public void a(String str) {
        this.f6384a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public String b() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }
}
