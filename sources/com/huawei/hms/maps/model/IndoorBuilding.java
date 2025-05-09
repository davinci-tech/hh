package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes9.dex */
public class IndoorBuilding implements Parcelable {
    public static final Parcelable.Creator<IndoorBuilding> CREATOR = new Parcelable.Creator<IndoorBuilding>() { // from class: com.huawei.hms.maps.model.IndoorBuilding.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IndoorBuilding[] newArray(int i) {
            return new IndoorBuilding[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IndoorBuilding createFromParcel(Parcel parcel) {
            return new IndoorBuilding(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    String f4998a;
    String[] b;
    int[] c;
    String d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeString(2, this.f4998a, false);
        parcelWrite.writeStringArray(3, this.b, false);
        parcelWrite.writeIntArray(4, this.c, false);
        parcelWrite.writeString(5, this.d, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public void setFloorOrder(int[] iArr) {
        this.c = iArr;
    }

    public void setFloorNames(String[] strArr) {
        this.b = strArr;
    }

    public void setCurrFloorName(String str) {
        this.d = str;
    }

    public void setBuildingId(String str) {
        this.f4998a = str;
    }

    public int[] getFloorOrder() {
        return this.c;
    }

    public String[] getFloorNames() {
        return this.b;
    }

    public String getCurrFloorName() {
        return this.d;
    }

    public String getBuildingId() {
        return this.f4998a;
    }

    public IndoorBuilding(String str, String str2, String str3, String str4) {
        this.f4998a = str;
        this.b = str2.split("\\|");
        String[] split = str3.split("\\|");
        this.c = new int[split.length];
        try {
            int i = 0;
            for (String str5 : split) {
                this.c[i] = Integer.parseInt(str5);
                i++;
            }
        } catch (NumberFormatException unused) {
            LogM.e("IndoorBuilding", "error number type");
        }
        this.d = str4;
    }

    protected IndoorBuilding(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f4998a = parcelReader.createString(2, "");
        this.b = parcelReader.createStringArray(3, new String[0]);
        this.c = parcelReader.createIntArray(4, new int[0]);
        this.d = parcelReader.createString(5, "");
    }
}
