package com.huawei.ui.homehealth.functionsetcard.manager.strategy;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes6.dex */
public class SportsCardStrategy extends CardUpgradeStrategy {
    public SportsCardStrategy(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardPosition() {
        this.mCardPosition = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), CardFlowInteractors.CardNameConstants.SPORTS_CARD.getName());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardShowOptions() {
        this.mShowCard = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_sport_record_card");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public String getCardId() {
        return CardFlowInteractors.CardNameConstants.SPORTS_CARD.getName();
    }

    public boolean a() {
        return !"".equals(SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "SPORT_HISTORY"));
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    protected boolean isEditedPosition(List<Boolean> list) {
        if (isEmptyShowCard() || isEmptyPosition()) {
            return false;
        }
        this.mDefaultCardPosition = "0";
        return judgeDefaultPosition();
    }
}
