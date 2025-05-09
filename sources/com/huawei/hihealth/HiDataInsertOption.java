package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiDataInsertOption implements Parcelable {
    public static final Parcelable.Creator<HiDataInsertOption> CREATOR = new Parcelable.Creator<HiDataInsertOption>() { // from class: com.huawei.hihealth.HiDataInsertOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buw_, reason: merged with bridge method [inline-methods] */
        public HiDataInsertOption createFromParcel(Parcel parcel) {
            return new HiDataInsertOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiDataInsertOption[] newArray(int i) {
            return new HiDataInsertOption[i];
        }
    };
    private List<HiHealthData> datas;
    private ContentValues valueHolder;
    private int writeStatType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiDataInsertOption() {
        this.datas = new ArrayList();
    }

    public HiDataInsertOption(List<HiHealthData> list) {
        this.datas = list;
    }

    private HiDataInsertOption(Parcel parcel) {
        this.writeStatType = parcel.readInt();
        this.datas = parcel.createTypedArrayList(HiHealthData.CREATOR);
        this.valueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public void addData(HiHealthData hiHealthData) {
        this.datas.add(hiHealthData);
    }

    public List<HiHealthData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<HiHealthData> list) {
        this.datas = (List) ResultUtils.a(list);
    }

    public int getWriteStatType() {
        return this.writeStatType;
    }

    public void setWriteStatType(int i) {
        this.writeStatType = i;
    }

    public String getPackageName() {
        ContentValues contentValues = this.valueHolder;
        if (contentValues != null) {
            return contentValues.getAsString("package_name");
        }
        return null;
    }

    public void setPackageName(String str) {
        getValueHolder().put("package_name", str);
    }

    private ContentValues getValueHolder() {
        if (this.valueHolder == null) {
            this.valueHolder = new ContentValues();
        }
        return this.valueHolder;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.writeStatType);
        parcel.writeTypedList(this.datas);
        parcel.writeParcelable(this.valueHolder, i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDataInsertOption{writeStatType=");
        stringBuffer.append(this.writeStatType);
        stringBuffer.append(", datas=").append(this.datas);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
