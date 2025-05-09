package com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr;

import android.os.Parcelable;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class RunningPostureAdviceBase implements Parcelable {
    public static final int COLOR_EXCELLENT = -10575361;
    public static final int COLOR_GOOD = -14445916;
    public static final int COLOR_NORMAL = -10175652;
    public static final int COLOR_POORER = -2539198;
    public static final int COLOR_SUBOPTIMAL = -26624;
    public static final int LEVEL_EXCELLENT = 3;
    public static final int LEVEL_GOOD = 2;
    public static final int LEVEL_NORMAL = 1;
    public static final int LEVEL_POORER = -1;
    public static final int LEVEL_SUBOPTIMAL = 0;
    protected List<String> mActionList;
    protected int mAdvice;
    protected int mDescription;
    protected int mLevel;
    protected int mLevelLongTip;
    protected int mLevelShortTip;
    protected int mPostureId;
    protected float mValue;

    protected int getColor(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? COLOR_POORER : COLOR_EXCELLENT : COLOR_GOOD : COLOR_NORMAL : COLOR_SUBOPTIMAL;
    }

    public abstract String getRunningPostureFragment();

    public abstract String getText();

    public abstract void initActionList();

    protected void initData(float f, int i, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
    }

    public int judgeLevel(float f) {
        return 0;
    }

    protected void initData(float f) {
        this.mValue = f;
        this.mLevel = judgeLevel(f);
        initActionList();
        judgeAdvices(this.mLevel);
    }

    public int acquireLevel() {
        return this.mLevel;
    }

    public int acquireLevelShortTip() {
        return this.mLevelShortTip;
    }

    public int acquireLevelLongTip() {
        return this.mLevelLongTip;
    }

    public int acquireDescription() {
        return this.mDescription;
    }

    public int acquireAdvice() {
        return this.mAdvice;
    }

    public List<String> acquireActionList() {
        return this.mActionList;
    }

    public int[] acquireValueList() {
        return new int[]{(int) this.mValue};
    }

    public void judgeAdvices(int i) {
        if (i == -1) {
            this.mLevelShortTip = R.string._2130846025_res_0x7f022149;
            return;
        }
        if (i == 0) {
            this.mLevelShortTip = R.string._2130846026_res_0x7f02214a;
            return;
        }
        if (i == 1) {
            this.mLevelShortTip = R.string._2130846027_res_0x7f02214b;
            return;
        }
        if (i == 2) {
            this.mLevelShortTip = R.string._2130846028_res_0x7f02214c;
        } else if (i == 3) {
            this.mLevelShortTip = R.string._2130846029_res_0x7f02214d;
        } else {
            this.mLevelShortTip = R.string._2130846027_res_0x7f02214b;
        }
    }

    public void judgeAdvicesNewVersion(int i) {
        if (i == -1) {
            this.mLevelShortTip = R.string._2130846344_res_0x7f022288;
            return;
        }
        if (i == 0) {
            this.mLevelShortTip = R.string._2130845263_res_0x7f021e4f;
            return;
        }
        if (i == 1) {
            this.mLevelShortTip = R.string._2130846028_res_0x7f02214c;
        } else if (i == 2) {
            this.mLevelShortTip = R.string._2130846029_res_0x7f02214d;
        } else {
            this.mLevelShortTip = R.string._2130846343_res_0x7f022287;
        }
    }

    public float getValue() {
        return this.mValue;
    }

    public int getPostureId() {
        return this.mPostureId;
    }
}
