package com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class GroundVerticalAmplitudeRatioAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<GroundVerticalAmplitudeRatioAdvice> CREATOR = new Parcelable.Creator<GroundVerticalAmplitudeRatioAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundVerticalAmplitudeRatioAdvice.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bic_, reason: merged with bridge method [inline-methods] */
        public GroundVerticalAmplitudeRatioAdvice createFromParcel(Parcel parcel) {
            return new GroundVerticalAmplitudeRatioAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public GroundVerticalAmplitudeRatioAdvice[] newArray(int i) {
            return new GroundVerticalAmplitudeRatioAdvice[i];
        }
    };
    public static final int GROUND_VERTICAL_AMPLITUDE_RATIO_LEVEL_EXCELLENT = 6;
    public static final float GROUND_VERTICAL_AMPLITUDE_RATIO_LEVEL_NORMAL = 8.5f;
    public static final int GROUND_VERTICAL_AMPLITUDE_RATIO_LEVEL_POORER = 13;
    public static final int GROUND_VERTICAL_AMPLITUDE_RATIO_LEVEL_SUBOPTIMAL = 11;
    private static final String RUNNING_POSTURE_VERTICALRAT_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.VerticalRatioFragment";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GroundVerticalAmplitudeRatioAdvice(float f) {
        this.mPostureId = 5;
        initData(f);
    }

    protected GroundVerticalAmplitudeRatioAdvice(Parcel parcel) {
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
        if (Float.compare(f, 6.0f) <= 0) {
            return 3;
        }
        if (Float.compare(f, 8.5f) <= 0) {
            return 2;
        }
        if (Float.compare(f, 11.0f) <= 0) {
            return 1;
        }
        return Float.compare(f, 13.0f) <= 0 ? 0 : -1;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initActionList() {
        this.mActionList = new ArrayList(2);
        this.mActionList.add("002B");
        this.mActionList.add("023B");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130845218_res_0x7f021e22);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void judgeAdvices(int i) {
        super.judgeAdvices(i);
        this.mLevelLongTip = R.string._2130845451_res_0x7f021f0b;
        this.mAdvice = R.string._2130845308_res_0x7f021e7c;
        this.mDescription = R.string._2130845309_res_0x7f021e7d;
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
        return RUNNING_POSTURE_VERTICALRAT_FRAGMENT;
    }
}
