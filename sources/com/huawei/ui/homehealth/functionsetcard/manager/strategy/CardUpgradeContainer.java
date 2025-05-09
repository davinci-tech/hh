package com.huawei.ui.homehealth.functionsetcard.manager.strategy;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.ohz;
import defpackage.oic;
import defpackage.oid;
import defpackage.oie;
import defpackage.oif;
import defpackage.oig;
import defpackage.oih;
import defpackage.oil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class CardUpgradeContainer {

    /* renamed from: a, reason: collision with root package name */
    private int f9432a = 0;
    private boolean b;
    private List<CardUpgradeStrategy> c;
    private boolean d;
    private boolean e;
    private String f;
    private List<Boolean> g;
    private SparseArray<CardUpgradeStrategy> h;

    public CardUpgradeContainer(Context context) {
        d(context);
        c(context);
        k();
        m();
    }

    private void d(Context context) {
        this.f = SharedPreferenceManager.b(context, String.valueOf(10000), CardFlowInteractors.CardNameConstants.MANAGER_CARD.getName());
    }

    private void c(Context context) {
        SparseArray<CardUpgradeStrategy> sparseArray = new SparseArray<>(9);
        this.h = sparseArray;
        sparseArray.put(0, new SportsCardStrategy(context));
        this.h.put(1, new SleepCardStrategy(context));
        this.h.put(2, new oil(context));
        this.h.put(3, new oih(context));
        this.h.put(4, new oic(context));
        this.h.put(5, new oif(context));
        this.h.put(6, new oid(context));
        this.h.put(7, new oig(context));
        this.h.put(8, new oie(context));
    }

    private void k() {
        ArrayList arrayList = new ArrayList(9);
        this.g = arrayList;
        arrayList.add(Boolean.valueOf(j()));
        this.g.add(Boolean.valueOf(h()));
        this.g.add(Boolean.valueOf(f()));
        this.g.add(Boolean.valueOf(i()));
        this.g.add(Boolean.valueOf(g()));
        this.g.add(Boolean.valueOf(c()));
        this.g.add(Boolean.valueOf(d()));
        this.g.add(Boolean.valueOf(e()));
        this.g.add(Boolean.valueOf(a()));
    }

    private boolean j() {
        return this.h.get(0).isEmptyShowCard() ? ("6".equals(this.f) || "7".equals(this.f)) ? false : true : ("8".equals(this.f) || "9".equals(this.f)) ? false : true;
    }

    private boolean h() {
        return !this.h.get(3).isEmptyPosition();
    }

    private boolean f() {
        return !this.h.get(0).isEmptyPosition();
    }

    private boolean i() {
        oig oigVar = (oig) this.h.get(7);
        try {
            if (!oigVar.isEmptyPosition() && Integer.parseInt(oigVar.getCardPosition()) >= 1000) {
                if (oigVar.isEmptyShowCard()) {
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b("CardUpgradeContainer", "isPreShowCardPressure error");
        }
        return false;
    }

    private boolean g() {
        oid oidVar = (oid) this.h.get(6);
        try {
            if (!oidVar.isEmptyPosition() && Integer.parseInt(oidVar.getCardPosition()) >= 1000) {
                if (oidVar.isEmptyShowCard()) {
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b("CardUpgradeContainer", "isPreShowCardPressure error");
        }
        return false;
    }

    private boolean c() {
        boolean z = !this.h.get(0).isEmptyShowCard();
        LogUtil.c("CardUpgradeContainer", "isOneScenario=", Boolean.valueOf(z));
        return z;
    }

    private boolean d() {
        boolean z = false;
        boolean isEmptyShowCard = this.h.get(0).isEmptyShowCard();
        boolean isEmptyPosition = this.h.get(5).isEmptyPosition();
        if (isEmptyShowCard && (!isEmptyPosition)) {
            z = true;
        }
        LogUtil.c("CardUpgradeContainer", "isTwoScenario=", Boolean.valueOf(z));
        return z;
    }

    private boolean e() {
        boolean z = false;
        boolean isEmptyShowCard = this.h.get(0).isEmptyShowCard();
        boolean isEmptyPosition = this.h.get(1).isEmptyPosition();
        boolean isEmptyPosition2 = this.h.get(5).isEmptyPosition();
        if (isEmptyShowCard && (!isEmptyPosition) && isEmptyPosition2) {
            z = true;
        }
        LogUtil.c("CardUpgradeContainer", "isThreeScenario=", Boolean.valueOf(z));
        return z;
    }

    private boolean a() {
        boolean z = false;
        SportsCardStrategy sportsCardStrategy = (SportsCardStrategy) this.h.get(0);
        boolean isEmptyShowCard = sportsCardStrategy.isEmptyShowCard();
        boolean a2 = sportsCardStrategy.a();
        if (isEmptyShowCard && a2) {
            z = true;
        }
        LogUtil.c("CardUpgradeContainer", "isFourScenario=", Boolean.valueOf(z));
        return z;
    }

    private void m() {
        boolean z = false;
        if (this.h == null) {
            this.b = false;
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.h.size()) {
                break;
            }
            CardUpgradeStrategy cardUpgradeStrategy = this.h.get(this.h.keyAt(i));
            if (cardUpgradeStrategy.isEditedCard(this.g)) {
                LogUtil.c("CardUpgradeContainer", cardUpgradeStrategy.getCardId(), " isEditedCard=true");
                z = true;
                break;
            }
            i++;
        }
        this.b = z;
    }

    private void b() {
        if (this.c == null) {
            this.c = new ArrayList();
            this.d = false;
            this.e = false;
            for (int i = 0; i < this.h.size(); i++) {
                CardUpgradeStrategy cardUpgradeStrategy = this.h.get(this.h.keyAt(i));
                ReleaseLogUtil.e("CardUpgradeContainer", cardUpgradeStrategy.getCardId(), " showCard=", cardUpgradeStrategy.getShowCard(), " position=", cardUpgradeStrategy.getCardPosition());
                if (!cardUpgradeStrategy.isEmptyPosition()) {
                    if (cardUpgradeStrategy instanceof SportsCardStrategy) {
                        this.e = true;
                    }
                    if (cardUpgradeStrategy.getShowFlag(this.g) == 1) {
                        this.d = true;
                    }
                    this.c.add(cardUpgradeStrategy);
                }
            }
            Collections.sort(this.c, new Comparator<CardUpgradeStrategy>() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeContainer.5
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(CardUpgradeStrategy cardUpgradeStrategy2, CardUpgradeStrategy cardUpgradeStrategy3) {
                    try {
                        return Integer.parseInt(cardUpgradeStrategy2.getCardPosition()) - Integer.parseInt(cardUpgradeStrategy3.getCardPosition());
                    } catch (NumberFormatException unused) {
                        LogUtil.b("CardUpgradeContainer", "getEditCardPositionSortList error");
                        return 0;
                    }
                }
            });
        }
    }

    public ohz c(ohz ohzVar) {
        if (ohzVar != null && this.b) {
            LinkedList<CardConfig> d = ohzVar.d();
            ohzVar.e(this.b);
            if (d == null) {
                return ohzVar;
            }
            this.f9432a = 0;
            b();
            boolean z = this.e;
            if (z && this.d) {
                d(d);
            } else if (z) {
                a(d);
            } else {
                e(d);
            }
            ohzVar.d(d);
        }
        return ohzVar;
    }

    private void d(LinkedList<CardConfig> linkedList) {
        a(linkedList, this.c);
        this.f9432a = 1;
        b(linkedList, "HEALTH_MODEL_CARD_KEY_NEW");
    }

    private void e(LinkedList<CardConfig> linkedList) {
        b(linkedList, this.h.get(0).getCardId());
        b(linkedList, "HEALTH_MODEL_CARD_KEY_NEW");
        a(linkedList, this.c);
    }

    private void a(LinkedList<CardConfig> linkedList) {
        b(linkedList, "HEALTH_MODEL_CARD_KEY_NEW");
        a(linkedList, this.c);
    }

    private void a(LinkedList<CardConfig> linkedList, List<CardUpgradeStrategy> list) {
        for (int i = 0; i < list.size(); i++) {
            c(linkedList, list.get(i));
        }
    }

    private void b(LinkedList<CardConfig> linkedList, String str) {
        for (int i = 0; i < linkedList.size(); i++) {
            CardConfig cardConfig = linkedList.get(i);
            if (str != null && cardConfig != null && str.equals(cardConfig.getCardId())) {
                SportsCardStrategy sportsCardStrategy = (SportsCardStrategy) this.h.get(0);
                if (cardConfig.getCardId() != null && sportsCardStrategy != null && ("HEALTH_MODEL_CARD_KEY_NEW".equals(cardConfig.getCardId()) || cardConfig.getCardId().equals(sportsCardStrategy.getCardId()))) {
                    cardConfig.setShowFlag(1);
                }
                c(linkedList, i, cardConfig);
                return;
            }
        }
    }

    private void c(LinkedList<CardConfig> linkedList, CardUpgradeStrategy cardUpgradeStrategy) {
        for (int i = 0; i < linkedList.size(); i++) {
            CardConfig cardConfig = linkedList.get(i);
            if (cardUpgradeStrategy != null && cardUpgradeStrategy.getCardId() != null && cardConfig != null && cardUpgradeStrategy.getCardId().equals(cardConfig.getCardId())) {
                boolean isEditedCard = cardUpgradeStrategy.isEditedCard(this.g);
                int showFlag = cardUpgradeStrategy.getShowFlag(this.g);
                cardConfig.setEditFlag(isEditedCard ? 1 : 0);
                cardConfig.setShowFlag(showFlag);
                c(linkedList, i, cardConfig);
                return;
            }
        }
    }

    private void c(LinkedList<CardConfig> linkedList, int i, CardConfig cardConfig) {
        if (koq.d(linkedList, this.f9432a)) {
            linkedList.remove(i);
            linkedList.add(this.f9432a, cardConfig);
            this.f9432a++;
        }
    }
}
