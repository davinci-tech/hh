package defpackage;

import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.functionsetcard.manager.ICardManager;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class oii implements CardShowStrategy {
    protected ICardManager c;
    private boolean b = false;
    private Observer d = new Observer() { // from class: oii.5
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (str == null) {
                return;
            }
            str.hashCode();
            if (str.equals("NOTIFY_STEP_CARD_CHANGED")) {
                LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "notify step card status changed");
                if (objArr == null || objArr.length <= 0) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof String) {
                    oii.this.d((String) obj);
                }
            }
        }
    };

    public oii(ICardManager iCardManager) {
        this.c = iCardManager;
        a();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void doStrategy() {
        e();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void release() {
        c();
    }

    private void a() {
        Observer observer;
        if (this.b || (observer = this.d) == null) {
            return;
        }
        ObserverManagerUtil.d(observer, "NOTIFY_STEP_CARD_CHANGED");
        this.b = true;
    }

    private void c() {
        if (this.b) {
            ObserverManagerUtil.e(this.d, "NOTIFY_STEP_CARD_CHANGED");
            this.b = false;
        }
    }

    private void e() {
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_ThreeCircleCardStrategy", "addThreeCircleToCurrentConfig mCardImpl is null");
            return;
        }
        ohz currentConfigs = iCardManager.getCurrentConfigs();
        ohz defaultConfigs = this.c.getDefaultConfigs();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(currentConfigs.d());
        List<String> b = oib.b(arrayList);
        String name = CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName();
        if (b.contains(name)) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "cardConfigList already have three circle");
            return;
        }
        LinkedList<CardConfig> linkedList = new LinkedList<>();
        Iterator<CardConfig> it = currentConfigs.d().iterator();
        while (it.hasNext()) {
            CardConfig next = it.next();
            if (next != null && next.getCardId() != null && !next.getCardId().equals(CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName())) {
                linkedList.add(next);
            }
        }
        CardConfig cardConfig = new CardConfig();
        Iterator<CardConfig> it2 = defaultConfigs.d().iterator();
        while (it2.hasNext()) {
            CardConfig next2 = it2.next();
            if (next2 != null && next2.getCardId() != null && next2.getCardId().equals(CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName())) {
                next2.setShowFlag(2);
                cardConfig = next2;
            }
        }
        linkedList.add(cardConfig);
        currentConfigs.d(linkedList);
        List<ohx> a2 = currentConfigs.a();
        if (a2.size() != 4) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "default cardConfig group type is not 4, please check");
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(a2.get(3).c());
        if (arrayList2.contains(name)) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "marketingGroup already have three circle");
            return;
        }
        arrayList2.add(name);
        a2.get(3).c(arrayList2);
        currentConfigs.b(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_ThreeCircleCardStrategy", "queryThreeCircleSwitch mCardImpl is null");
            return;
        }
        ohz currentConfigs = iCardManager.getCurrentConfigs();
        List<CardConfig> cardConfigList = this.c.getCardConfigList(true);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(currentConfigs.d());
        cardConfigList.addAll(this.c.getCardConfigList(true));
        List<String> b = oib.b(cardConfigList);
        if (CollectionUtils.d(arrayList)) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "queryThreeCircleSwitch cardConfigList == null");
            return;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "three_circle_edited_hide_or_show");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "health_life_edited_hide_or_show");
        if (c(b2.concat(b3))) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "user edit three circle show, health life show");
        } else if (str.equals("threeCircleCard")) {
            b(b, b2, b3);
        } else if (str.equals("threeLeafCard")) {
            c(b, b2, b3);
        }
    }

    private boolean c(String str) {
        ArrayList arrayList = new ArrayList();
        String num = Integer.toString(1);
        String num2 = Integer.toString(2);
        arrayList.add(num.concat(num2));
        arrayList.add(num.concat(num));
        arrayList.add(num2.concat(num));
        arrayList.add(num2.concat(num));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void b(List<String> list, String str, String str2) {
        LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "showThreeCircleCard current cardIdList", list);
        String name = CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName();
        if (oib.c(str, str2) && list.contains("HEALTH_MODEL_CARD_KEY_NEW") && !list.contains(name)) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "situation one, switch");
            b("HEALTH_MODEL_CARD_KEY_NEW", name);
            return;
        }
        if (str2.equals(Integer.toString(2)) && !str.equals(Integer.toString(1)) && !str.equals(Integer.toString(2)) && !list.contains(name)) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "situation two, add");
            e(1, name);
        }
        LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "showThreeCircleCard new cardIdList", list);
    }

    private void c(List<String> list, String str, String str2) {
        LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "showHealthModelCard current cardIdList", list);
        String name = CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName();
        if (oib.c(str, str2) && list.contains(name) && !list.contains("HEALTH_MODEL_CARD_KEY_NEW")) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "situation one, switch");
            b(name, "HEALTH_MODEL_CARD_KEY_NEW");
            return;
        }
        if (str.equals(Integer.toString(2)) && !str2.equals(Integer.toString(2)) && !str2.equals(Integer.toString(1)) && !list.contains("HEALTH_MODEL_CARD_KEY_NEW")) {
            LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "situation two, add");
            e(1, "HEALTH_MODEL_CARD_KEY_NEW");
        }
        LogUtil.a("FunctionCard_ThreeCircleCardStrategy", "showHealthModelCard new cardIdList", list);
    }

    private void b(String str, String str2) {
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_ThreeCircleCardStrategy", "replaceTwoModelCard mCardImpl is null");
        } else {
            iCardManager.replaceTwoModelCard(str, str2);
        }
    }

    private void e(int i, String str) {
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_ThreeCircleCardStrategy", "addTwoModelCard mCardImpl is null");
        } else {
            iCardManager.addTwoModelCard(i, str);
        }
    }
}
