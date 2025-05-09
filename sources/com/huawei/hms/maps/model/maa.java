package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes4.dex */
public class maa implements Parcelable {
    public static final Parcelable.Creator<maa> CREATOR = new Parcelable.Creator<maa>() { // from class: com.huawei.hms.maps.model.maa.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public maa[] newArray(int i) {
            return new maa[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public maa createFromParcel(Parcel parcel) {
            return new maa(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f5030a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;
    private String h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeString(2, this.f5030a, false);
        parcelWrite.writeString(3, this.b, false);
        parcelWrite.writeString(4, this.c, false);
        parcelWrite.writeString(5, this.d, false);
        parcelWrite.writeString(6, this.e, false);
        parcelWrite.writeLong(7, this.f);
        parcelWrite.writeString(8, this.g, false);
        parcelWrite.writeString(9, this.h, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public maa g(String str) {
        this.h = str;
        return this;
    }

    public maa f(String str) {
        this.e = str;
        return this;
    }

    public maa e(String str) {
        this.d = str;
        return this;
    }

    public maa d(String str) {
        this.c = str;
        return this;
    }

    public maa c(String str) {
        this.b = str;
        return this;
    }

    public maa b(String str) {
        this.f5030a = str;
        return this;
    }

    public maa a(String str) {
        this.g = str;
        return this;
    }

    public maa a(long j) {
        this.f = j;
        return this;
    }

    protected maa(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f5030a = parcelReader.createString(2, "");
        this.b = parcelReader.createString(3, "");
        this.c = parcelReader.createString(4, "");
        this.d = parcelReader.createString(5, "");
        this.e = parcelReader.createString(6, "");
        this.f = parcelReader.readLong(7, 0L);
        this.g = parcelReader.createString(8, "");
        this.h = parcelReader.createString(9, "");
    }

    public maa() {
    }
}
