package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes9.dex */
public class MapAuthInfo implements Parcelable {
    public static final Parcelable.Creator<MapAuthInfo> CREATOR = new Parcelable.Creator<MapAuthInfo>() { // from class: com.huawei.hms.maps.model.MapAuthInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MapAuthInfo[] newArray(int i) {
            return new MapAuthInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MapAuthInfo createFromParcel(Parcel parcel) {
            return new MapAuthInfo(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private long f5000a;
    private String b;
    private String c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private String j;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeString(2, this.b, false);
        parcelWrite.writeString(3, this.c, false);
        parcelWrite.writeInt(4, this.d);
        parcelWrite.writeInt(5, this.e);
        parcelWrite.writeLong(6, this.f5000a);
        parcelWrite.writeString(7, this.f, false);
        parcelWrite.writeString(8, this.g, false);
        parcelWrite.writeString(9, this.h, false);
        parcelWrite.writeBoolean(10, this.i);
        parcelWrite.writeString(11, this.j, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public MapAuthInfo tomApikey(String str) {
        this.c = str;
        return this;
    }

    public MapAuthInfo startAuthTime(Long l) {
        this.f5000a = l.longValue();
        return this;
    }

    public MapAuthInfo sdkVersion(String str) {
        this.h = str;
        return this;
    }

    public MapAuthInfo requestId(String str) {
        this.g = str;
        return this;
    }

    public MapAuthInfo providerType(String str) {
        this.j = str;
        return this;
    }

    public MapAuthInfo politicalView(String str) {
        this.f = str;
        return this;
    }

    public MapAuthInfo packageFingerprint(int i) {
        this.e = i;
        return this;
    }

    public MapAuthInfo mapApiUsageWaitDuration(int i) {
        this.e = i;
        return this;
    }

    public MapAuthInfo mapApiUsageBatchNum(int i) {
        this.d = i;
        return this;
    }

    public MapAuthInfo isSyncAuth(boolean z) {
        this.i = z;
        return this;
    }

    public MapAuthInfo identifyResult(String str) {
        this.b = str;
        return this;
    }

    public String getTomApikey() {
        return this.c;
    }

    public long getStartAuthTime() {
        return this.f5000a;
    }

    public String getSdkVersion() {
        return this.h;
    }

    public String getRequestId() {
        return this.g;
    }

    public String getProviderType() {
        return this.j;
    }

    public String getPoliticalView() {
        return this.f;
    }

    public boolean getIsSyncAuth() {
        return this.i;
    }

    public String getIidentifyResult() {
        return this.b;
    }

    public int getApiUsageWaitDuration() {
        return this.e;
    }

    public int getApiUsageBatchNum() {
        return this.d;
    }

    protected MapAuthInfo(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.b = parcelReader.createString(2, "");
        this.c = parcelReader.createString(3, "");
        this.d = parcelReader.readInt(4, 10);
        this.e = parcelReader.readInt(5, 60);
        this.f5000a = parcelReader.readLong(6, 0L);
        this.f = parcelReader.createString(7, "");
        this.g = parcelReader.createString(8, "");
        this.h = parcelReader.createString(9, "");
        this.i = parcelReader.readBoolean(10, true);
        this.j = parcelReader.createString(11, "");
    }

    public MapAuthInfo() {
    }
}
