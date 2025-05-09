package defpackage;

import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.functionsetcard.manager.ICardManager;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class oij implements CardShowStrategy {
    protected ICardManager c;

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void release() {
    }

    public oij(ICardManager iCardManager) {
        this.c = iCardManager;
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardShowStrategy
    public void doStrategy() {
        b();
    }

    public void a() {
        if (efb.e(BaseApplication.getContext())) {
            c();
        } else {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: oij.2
                @Override // java.lang.Runnable
                public void run() {
                    oij.this.b();
                }
            });
            return;
        }
        final Map<String, List<Integer>> d = d();
        HashSet hashSet = new HashSet();
        for (List<Integer> list : d.values()) {
            if (koq.c(list)) {
                hashSet.addAll(list);
            }
        }
        ReleaseLogUtil.e("FunctionCard_HasDataStrategy", "doDataStatusStrategy() types size:", Integer.valueOf(hashSet.size()));
        if (koq.b(hashSet)) {
            return;
        }
        oib.d(BaseApplication.getContext(), oib.c(hashSet), new IBaseResponseCallback() { // from class: oij.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List a2 = oij.this.a(obj, d);
                if (koq.c(a2)) {
                    oij.this.c(a2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> a(Object obj, Map<String, List<Integer>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        if (!koq.e(obj, Integer.class)) {
            return arrayList;
        }
        List list = (List) obj;
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            Iterator<Integer> it = entry.getValue().iterator();
            while (it.hasNext()) {
                if (list.contains(it.next()) && !arrayList.contains(entry.getKey())) {
                    arrayList.add(entry.getKey());
                }
            }
        }
        return arrayList;
    }

    private Map<String, List<Integer>> d() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_HasDataStrategy", "getAllUninitializedCardTypes mCardImpl is null");
            return linkedHashMap;
        }
        for (CardConfig cardConfig : iCardManager.getUninitializedCardsFromCache()) {
            linkedHashMap.put(cardConfig.getCardId(), cardConfig.getDataTypes());
        }
        return linkedHashMap;
    }

    private void c() {
        a(Boolean.valueOf(oib.d()));
    }

    private void e() {
        ICardManager iCardManager = this.c;
        if (iCardManager == null) {
            LogUtil.h("FunctionCard_HasDataStrategy", "initCardOrder mCardImpl is null");
        } else {
            this.c.onShowStatusChanged(iCardManager.getCardIdsByGroupId(4), true, -1);
        }
    }

    private void a(Object obj) {
        List<String> cardIdsByGroupId;
        LogUtil.a("FunctionCard_HasDataStrategy", "setInitNoDataByGenderSetDefaultConfig isFemale:", obj);
        if (obj instanceof Boolean) {
            if (this.c == null) {
                LogUtil.h("FunctionCard_HasDataStrategy", "mCardImpl is null");
                return;
            }
            if (((Boolean) obj).booleanValue()) {
                cardIdsByGroupId = this.c.getCardIdsByGroupId((CommonUtil.bf() && CommonUtil.z(BaseApplication.getContext())) ? 1 : 2);
            } else {
                cardIdsByGroupId = this.c.getCardIdsByGroupId(1);
            }
            this.c.onShowStatusChanged(cardIdsByGroupId, true, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        ReleaseLogUtil.e("FunctionCard_HasDataStrategy", "setMemoryByCurrentSetHasDataConfig has data ");
        if (koq.e(obj, String.class)) {
            ICardManager iCardManager = this.c;
            if (iCardManager == null) {
                LogUtil.h("FunctionCard_HasDataStrategy", "setMemoryByCurrentSetHasDataConfig mCardImpl is null");
            } else {
                iCardManager.onShowStatusChanged((List) obj, true, -1);
            }
        }
    }
}
