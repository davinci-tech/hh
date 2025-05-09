package defpackage;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes6.dex */
public class oif extends CardUpgradeStrategy {
    public oif(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardPosition() {
        this.mCardPosition = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), CardFlowInteractors.CardNameConstants.BLOODOXYGEN_CARD.getName());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardShowOptions() {
        this.mShowCard = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_blood_oxygen_card");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public String getCardId() {
        return CardFlowInteractors.CardNameConstants.BLOODOXYGEN_CARD.getName();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public boolean isEditedPosition(List<Boolean> list) {
        boolean booleanValue = list.get(0).booleanValue();
        boolean booleanValue2 = list.get(2).booleanValue();
        if (list.get(5).booleanValue()) {
            if (booleanValue && isEmptyPosition()) {
                return false;
            }
            if (!booleanValue) {
                this.mDefaultCardPosition = "5";
            } else if (booleanValue2) {
                this.mDefaultCardPosition = "5";
            } else {
                this.mDefaultCardPosition = "4";
            }
        } else if (list.get(6).booleanValue() || list.get(7).booleanValue()) {
            if (isEmptyPosition() || booleanValue) {
                return false;
            }
            this.mDefaultCardPosition = "4";
        } else if (list.get(8).booleanValue()) {
            return false;
        }
        return judgeDefaultPosition();
    }
}
