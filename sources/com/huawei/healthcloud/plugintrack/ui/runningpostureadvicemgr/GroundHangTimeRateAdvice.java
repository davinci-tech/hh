package com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.hjt;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GroundHangTimeRateAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<GroundHangTimeRateAdvice> CREATOR = new Parcelable.Creator<GroundHangTimeRateAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundHangTimeRateAdvice.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bhW_, reason: merged with bridge method [inline-methods] */
        public GroundHangTimeRateAdvice createFromParcel(Parcel parcel) {
            return new GroundHangTimeRateAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public GroundHangTimeRateAdvice[] newArray(int i) {
            return new GroundHangTimeRateAdvice[i];
        }
    };
    private static final float HANG_TIME_LARGE_THRESHOLD = 250.0f;
    private static final int HANG_TIME_SMALL_THRESHOLD = 150;
    private static final String RUNNING_POSTURE_TIMERATE_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.GroundHangTimeRateFragment";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    protected int getColor(int i) {
        return i != 1 ? i != 2 ? RunningPostureAdviceBase.COLOR_SUBOPTIMAL : RunningPostureAdviceBase.COLOR_GOOD : RunningPostureAdviceBase.COLOR_NORMAL;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int judgeLevel(float f) {
        if (f < 150.0f) {
            return 2;
        }
        return f <= HANG_TIME_LARGE_THRESHOLD ? 1 : 0;
    }

    public GroundHangTimeRateAdvice(int i, int i2, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        initData(i, i2, list, sportDetailChartDataType);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initData(float f, int i, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        this.mPostureId = 2;
        this.mValue = f;
        this.mLevel = hjt.e(f / 100.0f, i, list, sportDetailChartDataType);
        initActionList();
        judgeAdvicesNewVersion(this.mLevel);
    }

    protected GroundHangTimeRateAdvice(Parcel parcel) {
        if (parcel == null) {
            throw new RuntimeException("Invaild input");
        }
        this.mValue = parcel.readFloat();
        this.mLevel = parcel.readInt();
        this.mLevelShortTip = parcel.readInt();
        this.mLevelLongTip = parcel.readInt();
        this.mAdvice = parcel.readInt();
        this.mActionList = parcel.createStringArrayList();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void judgeAdvicesNewVersion(int i) {
        super.judgeAdvicesNewVersion(i);
        this.mLevelLongTip = R.string._2130846037_res_0x7f022155;
        this.mAdvice = R.string._2130843736_res_0x7f021858;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initActionList() {
        this.mActionList = new ArrayList(2);
        this.mActionList.add("030B");
        this.mActionList.add("023B");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130843723_res_0x7f02184b);
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
        parcel.writeStringList(this.mActionList);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getRunningPostureFragment() {
        return RUNNING_POSTURE_TIMERATE_FRAGMENT;
    }
}
