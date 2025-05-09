package com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.model.SportLevel;

/* loaded from: classes9.dex */
public abstract class SportLevelByVo2Max {
    private static final String TAG = "Track_SportLevelByVo2Max";
    protected int mAverageHigherLimit;
    protected int mFairHigherLimit;
    protected int mGoodHigherLimit;
    protected boolean mIsInit = false;
    protected int mPoorHigherLimit;
    protected int mVeryPoorLimit;

    public SportLevel calSportLevel(int i) {
        if (!this.mIsInit) {
            LogUtil.h(TAG, "never init");
            return new SportLevel(-1, 0);
        }
        SportLevel sportLevel = new SportLevel();
        sportLevel.saveReason(5);
        sportLevel.saveValue(i);
        if (i <= this.mVeryPoorLimit) {
            sportLevel.saveLevel(0);
        } else if (i <= this.mPoorHigherLimit) {
            sportLevel.saveLevel(1);
        } else if (i <= this.mFairHigherLimit) {
            sportLevel.saveLevel(2);
        } else if (i <= this.mAverageHigherLimit) {
            sportLevel.saveLevel(3);
        } else if (i <= this.mGoodHigherLimit) {
            sportLevel.saveLevel(4);
        } else {
            sportLevel.saveLevel(5);
        }
        return sportLevel;
    }
}
