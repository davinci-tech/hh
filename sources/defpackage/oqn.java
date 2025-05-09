package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqn extends SportLevelByVo2Max {
    public oqn(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 20;
            this.mPoorHigherLimit = 23;
            this.mFairHigherLimit = 27;
            this.mAverageHigherLimit = 31;
            this.mGoodHigherLimit = 35;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 24;
            this.mPoorHigherLimit = 29;
            this.mFairHigherLimit = 34;
            this.mAverageHigherLimit = 39;
            this.mGoodHigherLimit = 43;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
