package com.huawei.health.breathe.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class BreatheBean implements Parcelable {
    public static final Parcelable.Creator<BreatheBean> CREATOR = new Parcelable.Creator<BreatheBean>() { // from class: com.huawei.health.breathe.bean.BreatheBean.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: Cy_, reason: merged with bridge method [inline-methods] */
        public BreatheBean createFromParcel(Parcel parcel) {
            return new BreatheBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BreatheBean[] newArray(int i) {
            return new BreatheBean[i];
        }
    };

    @SerializedName("beginBpm")
    private double mBeginBpm;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("endBpm")
    private double mEndBpm;

    @SerializedName("rhythm")
    private int mRhythm;

    @SerializedName("totalGrade")
    private float mTotalGrade;

    @SerializedName(ParsedFieldTag.NPES_TOTAL_SCORE)
    private float mTotalScore;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BreatheBean() {
    }

    protected BreatheBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mDuration = parcel.readInt();
        this.mRhythm = parcel.readInt();
        this.mTotalScore = parcel.readFloat();
        this.mTotalGrade = parcel.readFloat();
        this.mBeginBpm = parcel.readDouble();
        this.mEndBpm = parcel.readDouble();
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setRhythm(int i) {
        this.mRhythm = i;
    }

    public void setTotalScore(float f) {
        this.mTotalScore = f;
    }

    public void setTotalGrade(float f) {
        this.mTotalGrade = f;
    }

    public void setBeginBpm(double d) {
        this.mBeginBpm = d;
    }

    public void setEndBpm(double d) {
        this.mEndBpm = d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mRhythm);
        parcel.writeFloat(this.mTotalScore);
        parcel.writeFloat(this.mTotalGrade);
        parcel.writeDouble(this.mBeginBpm);
        parcel.writeDouble(this.mEndBpm);
    }
}
