package com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class StrikePatternAdvice extends RunningPostureAdviceBase {
    public static final Parcelable.Creator<StrikePatternAdvice> CREATOR = new Parcelable.Creator<StrikePatternAdvice>() { // from class: com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.StrikePatternAdvice.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bid_, reason: merged with bridge method [inline-methods] */
        public StrikePatternAdvice createFromParcel(Parcel parcel) {
            return new StrikePatternAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public StrikePatternAdvice[] newArray(int i) {
            return new StrikePatternAdvice[i];
        }
    };
    private static final String RUNNING_POSTURE_FOOT_FRAGMENT = "com.huawei.health.suggestion.ui.fragment.FootStrikePatternFragment";
    private int mForeFoot;
    private int mHindFoot;
    private int mWholeFoot;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int judgeLevel(float f) {
        return 1;
    }

    public StrikePatternAdvice(int i, int i2, int i3) {
        initData(i, i2, i3);
    }

    protected StrikePatternAdvice(Parcel parcel) {
        if (parcel == null) {
            throw new RuntimeException("Invaild input");
        }
        this.mValue = parcel.readFloat();
        this.mLevel = parcel.readInt();
        this.mLevelShortTip = parcel.readInt();
        this.mLevelLongTip = parcel.readInt();
        this.mAdvice = parcel.readInt();
        this.mActionList = parcel.createStringArrayList();
        this.mForeFoot = parcel.readInt();
        this.mWholeFoot = parcel.readInt();
        this.mHindFoot = parcel.readInt();
    }

    private void initData(int i, int i2, int i3) {
        this.mPostureId = 10;
        this.mForeFoot = i;
        this.mWholeFoot = i2;
        this.mHindFoot = i3;
        if (i >= i2 && i >= i3) {
            this.mLevel = 0;
        } else if (i2 >= i && i2 >= i3) {
            this.mLevel = 1;
        } else {
            this.mLevel = 3;
        }
        initActionList();
        judgeAdvices(this.mLevel);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void initActionList() {
        this.mActionList = new ArrayList(2);
        if (this.mLevel == 0) {
            this.mActionList.add("112B");
            this.mActionList.add("113B");
            this.mActionList.add("051B");
            this.mActionList.add("052B");
            return;
        }
        if (this.mLevel == 1) {
            this.mActionList.add("109B");
            this.mActionList.add("001B");
        } else {
            this.mActionList.add("001B");
            this.mActionList.add("018B");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getText() {
        return BaseApplication.getContext().getResources().getString(R.string._2130845674_res_0x7f021fea);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public void judgeAdvices(int i) {
        super.judgeAdvices(i);
        this.mLevelLongTip = R.string._2130842756_res_0x7f021484;
        if (i == 0) {
            this.mAdvice = R.string._2130842921_res_0x7f021529;
        } else if (i == 1) {
            this.mAdvice = R.string._2130842922_res_0x7f02152a;
        } else {
            this.mAdvice = R.string._2130842923_res_0x7f02152b;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public int[] acquireValueList() {
        return new int[]{this.mForeFoot, this.mWholeFoot, this.mHindFoot};
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
        parcel.writeInt(this.mForeFoot);
        parcel.writeInt(this.mWholeFoot);
        parcel.writeInt(this.mHindFoot);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase
    public String getRunningPostureFragment() {
        return RUNNING_POSTURE_FOOT_FRAGMENT;
    }
}
