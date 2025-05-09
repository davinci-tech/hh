package com.huawei.hwcommonmodel.datatypes;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class CoreSleepDataInfo implements Parcelable {
    public static final Parcelable.Creator<CoreSleepDataInfo> CREATOR = new Parcelable.Creator<CoreSleepDataInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.CoreSleepDataInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFf_, reason: merged with bridge method [inline-methods] */
        public CoreSleepDataInfo createFromParcel(Parcel parcel) {
            byte[] createByteArray = parcel.createByteArray();
            CoreSleepDataInfo coreSleepDataInfo = new CoreSleepDataInfo();
            coreSleepDataInfo.setInfos(createByteArray);
            return coreSleepDataInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CoreSleepDataInfo[] newArray(int i) {
            return new CoreSleepDataInfo[i];
        }
    };
    private byte[] mDataInfos;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte[] getInfo() {
        return (byte[]) this.mDataInfos.clone();
    }

    public void setInfos(byte[] bArr) {
        this.mDataInfos = (byte[]) bArr.clone();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.mDataInfos);
    }
}
