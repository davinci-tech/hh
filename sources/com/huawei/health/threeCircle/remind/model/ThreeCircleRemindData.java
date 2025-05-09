package com.huawei.health.threeCircle.remind.model;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.moj;

/* loaded from: classes4.dex */
public class ThreeCircleRemindData implements Parcelable {
    public static final Parcelable.Creator<ThreeCircleRemindData> CREATOR = new Parcelable.Creator<ThreeCircleRemindData>() { // from class: com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: aNn_, reason: merged with bridge method [inline-methods] */
        public ThreeCircleRemindData createFromParcel(Parcel parcel) {
            return new ThreeCircleRemindData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ThreeCircleRemindData[] newArray(int i) {
            return new ThreeCircleRemindData[i];
        }
    };
    private DeviceEventData mDeviceEventData;
    private int mPriority;
    private String mRemindText;
    private String mRemindType;
    private String mSubRemindType;
    private long mTimeMill;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ThreeCircleRemindData(Parcel parcel) {
        this.mRemindType = parcel.readString();
        this.mSubRemindType = parcel.readString();
        this.mPriority = parcel.readInt();
        this.mRemindText = parcel.readString();
        this.mDeviceEventData = (DeviceEventData) parcel.readParcelable(DeviceEventData.class.getClassLoader());
        this.mTimeMill = parcel.readLong();
    }

    private ThreeCircleRemindData(c cVar) {
        this.mRemindType = cVar.b;
        this.mPriority = cVar.d;
        this.mSubRemindType = cVar.e;
        this.mRemindText = cVar.f3446a;
        this.mDeviceEventData = cVar.c;
        this.mTimeMill = cVar.i;
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f3446a;
        private String b;
        private DeviceEventData c;
        private int d;
        private String e;
        private long i;

        public ThreeCircleRemindData d() {
            return new ThreeCircleRemindData(this);
        }

        public c b(String str) {
            this.b = str;
            return this;
        }

        public c e(int i) {
            this.d = i;
            return this;
        }

        public c c(String str) {
            this.e = str;
            return this;
        }

        public c a(String str) {
            this.f3446a = str;
            return this;
        }

        public c a(DeviceEventData deviceEventData) {
            this.c = deviceEventData;
            return this;
        }

        public c c(long j) {
            this.i = j;
            return this;
        }
    }

    public int getPriority() {
        return this.mPriority;
    }

    public String getRemindType() {
        return this.mRemindType;
    }

    public String getRemindText() {
        return this.mRemindText;
    }

    public DeviceEventData getDeviceEventData() {
        return this.mDeviceEventData;
    }

    public String getSubRemindType() {
        return this.mSubRemindType;
    }

    public String toString() {
        return moj.e(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRemindType);
        parcel.writeString(this.mSubRemindType);
        parcel.writeInt(this.mPriority);
        parcel.writeString(this.mRemindText);
        parcel.writeParcelable(this.mDeviceEventData, i);
        parcel.writeLong(this.mTimeMill);
    }
}
