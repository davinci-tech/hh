package defpackage;

import com.huawei.ui.homehealth.runcard.operation.recommendalgo.sportlevelmapping.SportLevelByVo2Max;

/* loaded from: classes9.dex */
public class oqk extends SportLevelByVo2Max {
    public oqk(int i) {
        if (i == 1) {
            this.mVeryPoorLimit = 18;
            this.mPoorHigherLimit = 22;
            this.mFairHigherLimit = 25;
            this.mAverageHigherLimit = 29;
            this.mGoodHigherLimit = 32;
            this.mIsInit = true;
            return;
        }
        if (i == 0) {
            this.mVeryPoorLimit = 23;
            this.mPoorHigherLimit = 27;
            this.mFairHigherLimit = 32;
            this.mAverageHigherLimit = 36;
            this.mGoodHigherLimit = 41;
            this.mIsInit = true;
            return;
        }
        this.mIsInit = false;
    }
}
