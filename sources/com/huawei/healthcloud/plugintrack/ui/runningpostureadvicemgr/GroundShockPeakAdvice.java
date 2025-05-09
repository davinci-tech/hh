package com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class GroundShockPeakAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<GroundShockPeakAdvice> CREATOR = new Parcelable.Creator<GroundShockPeakAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundShockPeakAdvice.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bhZ_, reason: merged with bridge method [inline-methods] */
        public GroundShockPeakAdvice createFromParcel(Parcel parcel) {
            return new GroundShockPeakAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public GroundShockPeakAdvice[] newArray(int i) {
            return new GroundShockPeakAdvice[i];
        }
    };
    public static final float GROUND_SHOCK_PEAK_LEVEL_EXCELLENT = 2.0f;
    public static final float GROUND_SHOCK_PEAK_LEVEL_NORMAL = 3.0f;
    private static final String RUNNING_POSTURE_ACTIVE_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.ActivePeakFragment";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    protected int getColor(int i) {
        return i != 1 ? i != 3 ? RunningPostureAdviceBase.COLOR_SUBOPTIMAL : RunningPostureAdviceBase.COLOR_EXCELLENT : RunningPostureAdviceBase.COLOR_NORMAL;
    }

    public GroundShockPeakAdvice(float f) {
        this.mPostureId = 6;
        initData(f);
    }

    protected GroundShockPeakAdvice(Parcel parcel) {
        if (parcel != null) {
            this.mValue = parcel.readFloat();
            this.mLevel = parcel.readInt();
            this.mLevelShortTip = parcel.readInt();
            this.mLevelLongTip = parcel.readInt();
            this.mAdvice = parcel.readInt();
            this.mDescription = parcel.readInt();
            this.mActionList = parcel.createStringArrayList();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int judgeLevel(float f) {
        if (f < 2.0f) {
            return 3;
        }
        return Float.compare(f, 3.0f) <= 0 ? 1 : 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initActionList() {
        this.mActionList = new ArrayList(3);
        this.mActionList.add("018B");
        this.mActionList.add("001B");
        this.mActionList.add("030B");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130845176_res_0x7f021df8);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void judgeAdvices(int i) {
        super.judgeAdvices(i);
        this.mAdvice = R.string._2130845302_res_0x7f021e76;
        this.mLevelLongTip = R.string._2130845455_res_0x7f021f0f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeFloat(this.mValue);
        parcel.writeInt(this.mLevel);
        parcel.writeInt(this.mLevelShortTip);
        parcel.writeInt(this.mLevelLongTip);
        parcel.writeInt(this.mAdvice);
        parcel.writeInt(this.mDescription);
        parcel.writeStringList(this.mActionList);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public float getValue() {
        return this.mValue;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getRunningPostureFragment() {
        return RUNNING_POSTURE_ACTIVE_FRAGMENT;
    }
}
