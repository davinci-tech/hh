package com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class MenstrualSwitchStatus implements Parcelable {
    public static final Parcelable.Creator<MenstrualSwitchStatus> CREATOR = new Parcelable.Creator<MenstrualSwitchStatus>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bNe_, reason: merged with bridge method [inline-methods] */
        public MenstrualSwitchStatus createFromParcel(Parcel parcel) {
            return new MenstrualSwitchStatus(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MenstrualSwitchStatus[] newArray(int i) {
            return new MenstrualSwitchStatus[i];
        }
    };
    private int mEasyPregnancyEndSwitch;
    private int mEasyPregnancyStartSwitch;
    private int mMasterSwitch;
    private int mMenstruationEndRemindSwitch;
    private int mMenstruationStartRemindSwitch;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MenstrualSwitchStatus() {
    }

    protected MenstrualSwitchStatus(Parcel parcel) {
        this.mMasterSwitch = parcel.readInt();
        this.mMenstruationStartRemindSwitch = parcel.readInt();
        this.mMenstruationEndRemindSwitch = parcel.readInt();
        this.mEasyPregnancyStartSwitch = parcel.readInt();
        this.mEasyPregnancyEndSwitch = parcel.readInt();
    }

    public int getMasterSwitch() {
        return this.mMasterSwitch;
    }

    public void setMasterSwitch(int i) {
        this.mMasterSwitch = i;
    }

    public int getMenstruationStartRemindSwitch() {
        return this.mMenstruationStartRemindSwitch;
    }

    public void setMenstruationStartRemindSwitch(int i) {
        this.mMenstruationStartRemindSwitch = i;
    }

    public int getMenstruationEndRemindSwitch() {
        return this.mMenstruationEndRemindSwitch;
    }

    public void setMenstruationEndRemindSwitch(int i) {
        this.mMenstruationEndRemindSwitch = i;
    }

    public int getEasyPregnancyStartSwitch() {
        return this.mEasyPregnancyStartSwitch;
    }

    public void setEasyPregnancyStartSwitch(int i) {
        this.mEasyPregnancyStartSwitch = i;
    }

    public int getEasyPregnancyEndSwitch() {
        return this.mEasyPregnancyEndSwitch;
    }

    public void setEasyPregnancyEndSwitch(int i) {
        this.mEasyPregnancyEndSwitch = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMasterSwitch);
        parcel.writeInt(this.mMenstruationStartRemindSwitch);
        parcel.writeInt(this.mMenstruationEndRemindSwitch);
        parcel.writeInt(this.mEasyPregnancyStartSwitch);
        parcel.writeInt(this.mEasyPregnancyEndSwitch);
    }

    public String toString() {
        return "MenstrualSwitchStatus:" + this.mMasterSwitch + this.mMenstruationStartRemindSwitch + this.mMenstruationEndRemindSwitch + this.mEasyPregnancyStartSwitch + this.mEasyPregnancyEndSwitch;
    }
}
