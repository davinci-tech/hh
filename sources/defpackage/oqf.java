package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqf extends SportLevelByVo2Max {
    public oqf(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 24;
            this.mPoorHigherLimit = 29;
            this.mFairHigherLimit = 33;
            this.mAverageHigherLimit = 37;
            this.mGoodHigherLimit = 42;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 28;
            this.mPoorHigherLimit = 34;
            this.mFairHigherLimit = 40;
            this.mAverageHigherLimit = 45;
            this.mGoodHigherLimit = 51;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
