package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqi extends SportLevelByVo2Max {
    public oqi(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 26;
            this.mPoorHigherLimit = 31;
            this.mFairHigherLimit = 36;
            this.mAverageHigherLimit = 41;
            this.mGoodHigherLimit = 46;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 31;
            this.mPoorHigherLimit = 37;
            this.mFairHigherLimit = 43;
            this.mAverageHigherLimit = 50;
            this.mGoodHigherLimit = 56;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
