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
public class AverageHangTimeAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<AverageHangTimeAdvice> CREATOR = new Parcelable.Creator<AverageHangTimeAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.AverageHangTimeAdvice.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bhT_, reason: merged with bridge method [inline-methods] */
        public AverageHangTimeAdvice createFromParcel(Parcel parcel) {
            return new AverageHangTimeAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public AverageHangTimeAdvice[] newArray(int i) {
            return new AverageHangTimeAdvice[i];
        }
    };
    private static final int HANG_TIME_HIGH_THRESHOLD = 125;
    private static final int HANG_TIME_SHORT_THRESHOLD = 100;
    private static final String RUNNING_POSTURE_HANGTIME_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.HangTimeFragment";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    protected int getColor(int i) {
        return i != 1 ? i != 3 ? RunningPostureAdviceBase.COLOR_SUBOPTIMAL : RunningPostureAdviceBase.COLOR_EXCELLENT : RunningPostureAdviceBase.COLOR_NORMAL;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int judgeLevel(float f) {
        if (f < 100.0f) {
            return -1;
        }
        return f <= 125.0f ? 1 : 3;
    }

    public AverageHangTimeAdvice(int i, int i2, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        initData(i, i2, list, sportDetailChartDataType);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initData(float f, int i, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        this.mPostureId = 1;
        this.mValue = f;
        this.mLevel = hjt.e(f, i, list, sportDetailChartDataType);
        initActionList();
        judgeAdvicesNewVersion(this.mLevel);
    }

    protected AverageHangTimeAdvice(Parcel parcel) {
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
        this.mLevelLongTip = R.string._2130846036_res_0x7f022154;
        this.mAdvice = R.string._2130843731_res_0x7f021853;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initActionList() {
        this.mActionList = new ArrayList(2);
        this.mActionList.add("018B");
        this.mActionList.add("007B");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130843148_res_0x7f02160c);
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
        return RUNNING_POSTURE_HANGTIME_FRAGMENT;
    }
}
