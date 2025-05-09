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
public class GroundContactTimeAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<GroundContactTimeAdvice> CREATOR = new Parcelable.Creator<GroundContactTimeAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundContactTimeAdvice.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: bhV_, reason: merged with bridge method [inline-methods] */
        public GroundContactTimeAdvice createFromParcel(Parcel parcel) {
            return new GroundContactTimeAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public GroundContactTimeAdvice[] newArray(int i) {
            return new GroundContactTimeAdvice[i];
        }
    };
    private static final int GROUND_CONTACT_TIME_THRESH = 300;
    private static final String RUNNING_POSTURE_TIME_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.GroundContactTimeFragment";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    protected int getColor(int i) {
        return i != 1 ? RunningPostureAdviceBase.COLOR_SUBOPTIMAL : RunningPostureAdviceBase.COLOR_NORMAL;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int judgeLevel(float f) {
        return f <= 300.0f ? 1 : -1;
    }

    public GroundContactTimeAdvice(int i, int i2, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        initData(i, i2, list, sportDetailChartDataType);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initData(float f, int i, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        this.mPostureId = 0;
        this.mValue = f;
        this.mLevel = hjt.e(f, i, list, sportDetailChartDataType);
        initActionList();
        judgeAdvicesNewVersion(this.mLevel);
    }

    protected GroundContactTimeAdvice(Parcel parcel) {
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
    public void initActionList() {
        this.mActionList = new ArrayList(2);
        this.mActionList.add("002B");
        this.mActionList.add("004B");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130842710_res_0x7f021456);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void judgeAdvicesNewVersion(int i) {
        super.judgeAdvicesNewVersion(i);
        this.mLevelLongTip = R.string._2130846035_res_0x7f022153;
        if (i == 1) {
            this.mAdvice = R.string._2130842911_res_0x7f02151f;
        } else {
            this.mAdvice = R.string._2130842912_res_0x7f021520;
        }
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
        return RUNNING_POSTURE_TIME_FRAGMENT;
    }
}
