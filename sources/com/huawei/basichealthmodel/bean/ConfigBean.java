package com.huawei.basichealthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class ConfigBean implements Parcelable {
    public static final Parcelable.Creator<ConfigBean> CREATOR = new Parcelable.Creator<ConfigBean>() { // from class: com.huawei.basichealthmodel.bean.ConfigBean.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: jN_, reason: merged with bridge method [inline-methods] */
        public ConfigBean createFromParcel(Parcel parcel) {
            ConfigBean configBean = new ConfigBean();
            if (parcel == null) {
                return configBean;
            }
            configBean.mModuleName = parcel.readString();
            configBean.mModuleVer = parcel.readString();
            configBean.mSupportAppMinVersion = parcel.readString();
            configBean.mSupportAppMaxVersion = parcel.readString();
            configBean.mTaskList = parcel.createTypedArrayList(TaskConfigBean.CREATOR);
            configBean.mChallengeList = parcel.createTypedArrayList(ChallengeConfigBean.CREATOR);
            configBean.mChallengeMotivationImage = parcel.readString();
            return configBean;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ConfigBean[] newArray(int i) {
            return new ConfigBean[i];
        }
    };

    @SerializedName("challengeList")
    private List<ChallengeConfigBean> mChallengeList;

    @SerializedName("challengeMotivationImage")
    private String mChallengeMotivationImage;
    private String mModuleName;
    private String mModuleVer;
    private String mSupportAppMaxVersion;
    private String mSupportAppMinVersion;
    private List<TaskConfigBean> mTaskList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mModuleName);
        parcel.writeString(this.mModuleVer);
        parcel.writeString(this.mSupportAppMinVersion);
        parcel.writeString(this.mSupportAppMaxVersion);
        parcel.writeTypedList(this.mTaskList);
        parcel.writeTypedList(this.mChallengeList);
        parcel.writeString(this.mChallengeMotivationImage);
    }

    public String getSupportAppMaxVersion() {
        return this.mSupportAppMaxVersion;
    }

    public List<TaskConfigBean> getTaskList() {
        return this.mTaskList;
    }

    public void setChallengeList(List<ChallengeConfigBean> list) {
        this.mChallengeList = list;
    }

    public List<ChallengeConfigBean> getChallengeList() {
        return this.mChallengeList;
    }

    public void setChallengeMotivationImage(String str) {
        this.mChallengeMotivationImage = str;
    }

    public String getChallengeMotivationImage() {
        return this.mChallengeMotivationImage;
    }
}
