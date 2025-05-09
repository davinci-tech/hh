package defpackage;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes6.dex */
public class oih extends CardUpgradeStrategy {
    public oih(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardPosition() {
        this.mCardPosition = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), CardFlowInteractors.CardNameConstants.STRESS_CARD.getName());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardShowOptions() {
        this.mShowCard = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_stress_card");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public String getCardId() {
        return CardFlowInteractors.CardNameConstants.STRESS_CARD.getName();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public boolean isEditedPosition(List<Boolean> list) {
        boolean booleanValue = list.get(0).booleanValue();
        boolean booleanValue2 = list.get(1).booleanValue();
        boolean booleanValue3 = list.get(2).booleanValue();
        if (list.get(5).booleanValue()) {
            if (booleanValue && isEmptyPosition()) {
                return false;
            }
            if (!booleanValue) {
                this.mDefaultCardPosition = "4";
            } else if (booleanValue3) {
                this.mDefaultCardPosition = "4";
            } else {
                this.mDefaultCardPosition = "3";
            }
        } else if (list.get(6).booleanValue() || list.get(7).booleanValue()) {
            if (!booleanValue) {
                this.mDefaultCardPosition = "2";
            } else {
                if (!booleanValue2) {
                    return false;
                }
                this.mDefaultCardPosition = "2";
            }
        } else if (list.get(8).booleanValue()) {
            if (!booleanValue) {
                this.mDefaultCardPosition = "3";
            } else {
                if (!booleanValue2) {
                    return false;
                }
                this.mDefaultCardPosition = "3";
            }
        }
        return judgeDefaultPosition();
    }
}
