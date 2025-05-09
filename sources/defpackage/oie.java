package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes6.dex */
public class oie extends CardUpgradeStrategy {
    public oie(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardPosition() {
        this.mCardPosition = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), CardFlowInteractors.CardNameConstants.PHYSIOLOGICAL_CYCLE_CARD.getName());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public void setCardShowOptions() {
        this.mShowCard = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_physiological_cycle_card");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public int getShowFlag(List<Boolean> list) {
        if (list == null || list.size() < 9) {
            return 0;
        }
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_physiological_cycle_card");
        if (TextUtils.isEmpty(b)) {
            b = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_physiological_cycle_card_for_personal_SWITCH");
            if (TextUtils.isEmpty(b)) {
                b = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "show_physiological_cycle_card_for_personal_information");
            }
        }
        return list.get(5).booleanValue() ? (!"".equals(b) && TRUE.equals(b)) ? 1 : 2 : (list.get(6).booleanValue() || list.get(7).booleanValue() || list.get(8).booleanValue()) ? 2 : 0;
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public String getCardId() {
        return CardFlowInteractors.CardNameConstants.PHYSIOLOGICAL_CYCLE_CARD.getName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0076, code lost:
    
        if (r9.get(8).booleanValue() == false) goto L25;
     */
    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isEditedPosition(java.util.List<java.lang.Boolean> r9) {
        /*
            r8 = this;
            r0 = 0
            java.lang.Object r1 = r9.get(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r3 = r1.booleanValue()
            r1 = 2
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r5 = r1.booleanValue()
            r1 = 1
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r1.booleanValue()
            r1 = 3
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r6 = r1.booleanValue()
            r1 = 4
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r7 = r1.booleanValue()
            r1 = 5
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L50
            boolean r9 = r8.isEmptyPosition()
            if (r9 == 0) goto L4b
            return r0
        L4b:
            r2 = r8
            r2.a(r3, r4, r5, r6, r7)
            goto L79
        L50:
            r1 = 6
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L9a
            r1 = 7
            java.lang.Object r1 = r9.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L9a
            r1 = 8
            java.lang.Object r9 = r9.get(r1)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L79
            goto L9a
        L79:
            boolean r9 = r8.isEmptyPosition()     // Catch: java.lang.NumberFormatException -> L8f
            if (r9 != 0) goto L8a
            java.lang.String r9 = r8.mCardPosition     // Catch: java.lang.NumberFormatException -> L8f
            int r9 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L8f
            r1 = 1000(0x3e8, float:1.401E-42)
            if (r9 < r1) goto L8a
            return r0
        L8a:
            boolean r9 = r8.judgeDefaultPosition()
            return r9
        L8f:
            java.lang.String r9 = "isEditedPosition error"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r1 = "PhysiologicalCycleCardStrategy"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
        L9a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.oie.isEditedPosition(java.util.List):boolean");
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeStrategy
    public boolean isEditedShowStatus() {
        return !"".equals(this.mShowCard);
    }

    private void a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!z) {
            if (z4 && z5) {
                this.mDefaultCardPosition = "6";
                return;
            } else if (z4 || z5) {
                this.mDefaultCardPosition = "7";
                return;
            } else {
                this.mDefaultCardPosition = "8";
                return;
            }
        }
        if (z2 && z3) {
            this.mDefaultCardPosition = "6";
            return;
        }
        if (z2) {
            this.mDefaultCardPosition = "5";
        } else if (z3) {
            this.mDefaultCardPosition = "4";
        } else {
            this.mDefaultCardPosition = "3";
        }
    }
}
