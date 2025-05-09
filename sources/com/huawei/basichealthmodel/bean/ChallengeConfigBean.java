package com.huawei.basichealthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthmodel.bean.TextBean;
import java.util.List;

/* loaded from: classes.dex */
public class ChallengeConfigBean implements Parcelable {
    public static final Parcelable.Creator<ChallengeConfigBean> CREATOR = new Parcelable.Creator<ChallengeConfigBean>() { // from class: com.huawei.basichealthmodel.bean.ChallengeConfigBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: jM_, reason: merged with bridge method [inline-methods] */
        public ChallengeConfigBean createFromParcel(Parcel parcel) {
            ChallengeConfigBean challengeConfigBean = new ChallengeConfigBean();
            if (parcel == null) {
                return challengeConfigBean;
            }
            challengeConfigBean.mChallengeId = parcel.readInt();
            challengeConfigBean.mChallengeName = parcel.readString();
            challengeConfigBean.mChallengeGuide = parcel.readString();
            challengeConfigBean.mChallengeIntro = parcel.readString();
            challengeConfigBean.mChallengeTitle = (TextBean) parcel.readParcelable(TextBean.class.getClassLoader());
            challengeConfigBean.mDetailEntity = (ConfigDetailBean) parcel.readParcelable(ConfigDetailBean.class.getClassLoader());
            challengeConfigBean.mChallengeImageFileName = parcel.readString();
            challengeConfigBean.mChallengeShareFileName = parcel.readString();
            challengeConfigBean.mChallengeShareBenefit = parcel.readString();
            challengeConfigBean.mChallengeTaskId = parcel.createStringArrayList();
            challengeConfigBean.mVersionRange = parcel.readString();
            challengeConfigBean.mVersionRangeBeta = parcel.readString();
            return challengeConfigBean;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ChallengeConfigBean[] newArray(int i) {
            return new ChallengeConfigBean[i];
        }
    };

    @SerializedName("challengeGuide")
    private String mChallengeGuide;

    @SerializedName("challengeId")
    private int mChallengeId;

    @SerializedName("challengeImageFileName")
    private String mChallengeImageFileName;

    @SerializedName("challengeIntro")
    private String mChallengeIntro;

    @SerializedName("challengeName")
    private String mChallengeName;

    @SerializedName("challengeShareBenefit")
    private String mChallengeShareBenefit;

    @SerializedName("challengeShareFileName")
    private String mChallengeShareFileName;

    @SerializedName("challengeTaskId")
    private List<String> mChallengeTaskId;

    @SerializedName("challengeTitle")
    private TextBean mChallengeTitle;

    @SerializedName("detailEntity")
    private ConfigDetailBean mDetailEntity;

    @SerializedName("versionRange")
    private String mVersionRange;

    @SerializedName("versionRangeBeta")
    private String mVersionRangeBeta;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getChallengeId() {
        return this.mChallengeId;
    }

    public List<String> getChallengeTaskId() {
        return this.mChallengeTaskId;
    }

    public String getVersionRange() {
        return this.mVersionRange;
    }

    public String getVersionRangeBeta() {
        return this.mVersionRangeBeta;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mChallengeId);
        parcel.writeString(this.mChallengeName);
        parcel.writeString(this.mChallengeGuide);
        parcel.writeString(this.mChallengeIntro);
        parcel.writeParcelable(this.mChallengeTitle, i);
        parcel.writeParcelable(this.mDetailEntity, i);
        parcel.writeString(this.mChallengeImageFileName);
        parcel.writeString(this.mChallengeShareFileName);
        parcel.writeString(this.mChallengeShareBenefit);
        parcel.writeStringList(this.mChallengeTaskId);
        parcel.writeString(this.mVersionRange);
        parcel.writeString(this.mVersionRangeBeta);
    }
}
