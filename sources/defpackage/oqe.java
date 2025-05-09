package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqe extends SportLevelByVo2Max {
    public oqe(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 21;
            this.mPoorHigherLimit = 25;
            this.mFairHigherLimit = 29;
            this.mAverageHigherLimit = 33;
            this.mGoodHigherLimit = 37;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 25;
            this.mPoorHigherLimit = 31;
            this.mFairHigherLimit = 35;
            this.mAverageHigherLimit = 41;
            this.mGoodHigherLimit = 46;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
