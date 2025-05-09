package defpackage;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes6.dex */
public class oig extends CardUpgradeStrategy {
    public oig(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardPosition() {
        this.mCardPosition = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), CardFlowInteractors.CardNameConstants.BLOODSUGAR_CARD.getName());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardShowOptions() {
        this.mShowCard = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_blood_sugar_card");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public String getCardId() {
        return CardFlowInteractors.CardNameConstants.BLOODSUGAR_CARD.getName();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public boolean isEditedPosition(List<Boolean> list) {
        boolean booleanValue = list.get(0).booleanValue();
        if (list.get(5).booleanValue()) {
            if (booleanValue) {
                return false;
            }
            this.mDefaultCardPosition = "6";
        } else if (list.get(6).booleanValue()) {
            if (booleanValue) {
                return false;
            }
            this.mDefaultCardPosition = "6";
        } else if (list.get(7).booleanValue()) {
            if (booleanValue) {
                return false;
            }
            this.mDefaultCardPosition = "5";
        } else if (list.get(8).booleanValue()) {
            if (booleanValue) {
                return false;
            }
            this.mDefaultCardPosition = "6";
        }
        try {
            if (!isEmptyPosition()) {
                if (Integer.parseInt(this.mCardPosition) >= 1000) {
                    return false;
                }
            }
            return judgeDefaultPosition();
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarCardStrategy", "isEditedPosition error");
            return false;
        }
    }
}
