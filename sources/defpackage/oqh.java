package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqh extends SportLevelByVo2Max {
    public oqh(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 23;
            this.mPoorHigherLimit = 27;
            this.mFairHigherLimit = 31;
            this.mAverageHigherLimit = 35;
            this.mGoodHigherLimit = 40;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 27;
            this.mPoorHigherLimit = 32;
            this.mFairHigherLimit = 38;
            this.mAverageHigherLimit = 43;
            this.mGoodHigherLimit = 48;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
