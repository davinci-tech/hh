package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class HiSampleConfigProcessOption implements Parcelable {
    public static final Parcelable.Creator<HiSampleConfigProcessOption> CREATOR = new Parcelable.Creator<HiSampleConfigProcessOption>() { // from class: com.huawei.hihealth.HiSampleConfigProcessOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buT_, reason: merged with bridge method [inline-methods] */
        public HiSampleConfigProcessOption createFromParcel(Parcel parcel) {
            return new HiSampleConfigProcessOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiSampleConfigProcessOption[] newArray(int i) {
            return new HiSampleConfigProcessOption[i];
        }
    };
    private String mDeviceUniqueId;
    private String mPackageName;
    private int mProcessType;
    private List<HiSampleConfigKey> mSampleConfigKeyList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<HiSampleConfigKey> getSampleConfigKeyList() {
        return this.mSampleConfigKeyList;
    }

    public void setSampleConfigKeyList(List<HiSampleConfigKey> list) {
        this.mSampleConfigKeyList = list;
    }

    public int getProcessType() {
        return this.mProcessType;
    }

    public void setProcessType(int i) {
        this.mProcessType = i;
    }

    public String getDeviceUniqueId() {
        return this.mDeviceUniqueId;
    }

    public void setDeviceUniqueId(String str) {
        this.mDeviceUniqueId = str;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public HiSampleConfigProcessOption() {
    }

    protected HiSampleConfigProcessOption(Parcel parcel) {
        this.mSampleConfigKeyList = parcel.createTypedArrayList(HiSampleConfigKey.CREATOR);
        this.mProcessType = parcel.readInt();
        this.mDeviceUniqueId = parcel.readString();
        this.mPackageName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mSampleConfigKeyList);
        parcel.writeInt(this.mProcessType);
        parcel.writeString(this.mDeviceUniqueId);
        parcel.writeString(this.mPackageName);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiSplCfgProcOp{splCfgKeyList=");
        stringBuffer.append(this.mSampleConfigKeyList);
        stringBuffer.append(",ProcTp=").append(this.mProcessType);
        stringBuffer.append(",devUid=").append(this.mDeviceUniqueId);
        stringBuffer.append(",pkgNm=").append(this.mPackageName);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
