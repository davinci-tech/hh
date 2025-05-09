package com.huawei.ui.homehealth.knit;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver;
import com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity;
import defpackage.ixx;
import defpackage.koq;
import defpackage.oia;
import defpackage.owm;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class FunctionSetCardProvider extends BaseKnitDataProvider implements FunctionSetCardObserver {

    /* renamed from: a, reason: collision with root package name */
    private Context f9472a;
    private oia b;
    private SectionBean h;
    private final Object f = new Object();
    private Handler d = new Handler(Looper.getMainLooper());
    private List<FunctionSetSubCardData> g = new ArrayList(10);
    private HashMap<String, Object> j = new HashMap<>();
    private boolean e = true;
    private View.OnClickListener i = new View.OnClickListener() { // from class: com.huawei.ui.homehealth.knit.FunctionSetCardProvider.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("FunctionSetCardProvider", "go to Card Management activity");
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            if (FunctionSetCardProvider.this.f9472a != null) {
                ixx.d().d(FunctionSetCardProvider.this.f9472a.getApplicationContext(), AnalyticsValue.HEALTH_HOME_MANAGERMENT_CARD_2010032.value(), hashMap, 0);
                try {
                    FunctionSetCardProvider.this.f9472a.startActivity(new Intent(FunctionSetCardProvider.this.f9472a, (Class<?>) FunctionSetCardManagementActivity.class));
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("FunctionSetCardProvider", " onclick exception" + e.getMessage());
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.knit.FunctionSetCardProvider.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getIntExtra("refresh_type", -1) == 0) {
                FunctionSetCardProvider.this.d();
            }
        }
    };

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardInsert(String str) {
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardOrderChanged(String str) {
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardReplace(String str, String str2) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("FunctionSetCardProvider", "loadData");
        this.f9472a = context;
        this.h = sectionBean;
        if (this.e) {
            oia c = oia.c();
            this.b = c;
            c.a(this);
            e();
            this.e = false;
        }
        c();
        this.j.put("REFRESH_NO_CARD_LAYOUT_SHOW_STATUS", true);
        this.h.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("FunctionSetCardProvider", "resume");
        synchronized (this.f) {
            if (koq.b(this.g)) {
                return;
            }
            for (FunctionSetSubCardData functionSetSubCardData : this.g) {
                if (functionSetSubCardData != null) {
                    functionSetSubCardData.onResume();
                }
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("FunctionSetCardProvider", "parseParams");
        hashMap.putAll(this.j);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        oia.e();
        synchronized (this.f) {
            for (FunctionSetSubCardData functionSetSubCardData : this.g) {
                if (functionSetSubCardData != null) {
                    functionSetSubCardData.onDestroy();
                }
            }
        }
        this.b.e(this);
        a();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.d = null;
        }
        synchronized (this.f) {
            this.g.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.j.clear();
        this.j.put("ADD_CARD_TEXT", this.f9472a.getString(R.string._2130837877_res_0x7f020175));
        this.j.put("EMPTY_CARD_PROMPT_TEXT", this.f9472a.getString(R.string._2130837996_res_0x7f0201ec));
        this.j.put("BTN_MODIFY_CARDS", this.f9472a.getString(R.string._2130837879_res_0x7f020177));
        this.j.put("FUNCTION_SET_NO_CARD_CLICK_EVENT", this.i);
    }

    private void c() {
        b();
        List<CardConstructor> a2 = this.b.a();
        if (koq.b(a2)) {
            LogUtil.h("FunctionSetCardProvider", "initCard mViewAdapter or cardConstructors is null");
            return;
        }
        synchronized (this.f) {
            this.g.clear();
            this.g.addAll(owm.c(this.f9472a, a2, null));
            this.j.put("DATA_LIST", this.g);
        }
        this.j.put("NOTIFY_DATA_SET_CHANGED", true);
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        LocalBroadcastManager.getInstance(this.f9472a.getApplicationContext()).registerReceiver(this.c, intentFilter);
        LogUtil.a("FunctionSetCardProvider", "mHiBroadcastReceiver register success", this);
    }

    private void a() {
        if (this.c != null) {
            try {
                LocalBroadcastManager.getInstance(this.f9472a.getApplicationContext()).unregisterReceiver(this.c);
                LogUtil.a("FunctionSetCardProvider", "mHiBroadcastReceiver unregister", this);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("FunctionSetCardProvider", "IllegalArgumentException mHiBroadcastReceiver unregister");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("FunctionSetCardProvider", "refreshAllCard");
        synchronized (this.f) {
            for (FunctionSetSubCardData functionSetSubCardData : this.g) {
                if (functionSetSubCardData != null) {
                    functionSetSubCardData.readCardData();
                }
            }
            b();
            this.j.put("DATA_LIST", this.g);
        }
        this.h.e(new Object());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyAllCardChanged() {
        LogUtil.a("FunctionSetCardProvider", "notifyAllCardChanged");
        Handler handler = this.d;
        if (handler == null) {
            LogUtil.h("FunctionSetCardProvider", "notifyAllCardChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.knit.FunctionSetCardProvider.5
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (FunctionSetCardProvider.this.f) {
                        List<CardConstructor> a2 = FunctionSetCardProvider.this.b.a();
                        FunctionSetCardProvider.this.g.clear();
                        FunctionSetCardProvider.this.g.addAll(owm.a(FunctionSetCardProvider.this.f9472a, FunctionSetCardProvider.this.g, a2, null));
                        FunctionSetCardProvider.this.b();
                        FunctionSetCardProvider.this.j.put("DATA_LIST", FunctionSetCardProvider.this.g);
                    }
                    FunctionSetCardProvider.this.j.put("SET_ADAPTER", true);
                    FunctionSetCardProvider.this.j.put("REFRESH_NO_CARD_LAYOUT_SHOW_STATUS", true);
                    FunctionSetCardProvider.this.h.e(new Object());
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(final String str, final boolean z) {
        LogUtil.a("FunctionSetCardProvider", "notifyCardShowStatusChanged cardId");
        Handler handler = this.d;
        if (handler == null) {
            LogUtil.h("FunctionSetCardProvider", "notifyCardShowStatusChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.knit.FunctionSetCardProvider.4
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardProvider.this.b();
                    FunctionSetCardProvider.this.b(str, z);
                    FunctionSetCardProvider.this.j.put("REFRESH_NO_CARD_LAYOUT_SHOW_STATUS", true);
                    synchronized (FunctionSetCardProvider.this.f) {
                        FunctionSetCardProvider.this.j.put("DATA_LIST", FunctionSetCardProvider.this.g);
                    }
                    FunctionSetCardProvider.this.h.e(new Object());
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(final List<String> list, final boolean z) {
        LogUtil.a("FunctionSetCardProvider", "notifyCardShowStatusChanged cardIds");
        Handler handler = this.d;
        if (handler == null) {
            LogUtil.h("FunctionSetCardProvider", "notifyCardShowStatusChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.knit.FunctionSetCardProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardProvider.this.b();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        FunctionSetCardProvider.this.b((String) it.next(), z);
                    }
                    FunctionSetCardProvider.this.j.put("REFRESH_NO_CARD_LAYOUT_SHOW_STATUS", true);
                    synchronized (FunctionSetCardProvider.this.f) {
                        FunctionSetCardProvider.this.j.put("DATA_LIST", FunctionSetCardProvider.this.g);
                    }
                    FunctionSetCardProvider.this.h.e(new Object());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        int i;
        LogUtil.a("FunctionSetCardProvider", "changeCardShowStatus cardId = ", str, " isShow = ", Boolean.valueOf(z));
        if (StringUtils.g(str)) {
            LogUtil.h("FunctionSetCardProvider", "changeCardShowStatus cardId or mViewAdapter is null");
            return;
        }
        synchronized (this.f) {
            i = 0;
            while (true) {
                if (i >= this.g.size()) {
                    i = -1;
                    break;
                } else if (str.equals(this.g.get(i).getCardId())) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (z) {
            LogUtil.a("FunctionSetCardProvider", "changeCardShowStatus add card,  position = ", Integer.valueOf(i));
            a(str, i);
            return;
        }
        if (i >= 0) {
            LogUtil.a("FunctionSetCardProvider", "changeCardShowStatus remove card,  position = ", Integer.valueOf(i));
            synchronized (this.f) {
                FunctionSetSubCardData functionSetSubCardData = this.g.get(i);
                this.g.remove(i);
                this.j.put("NOTIFY_ITEM_REMOVED", Integer.valueOf(i));
                if (i != this.g.size()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i));
                    arrayList.add(Integer.valueOf(this.g.size() - i));
                    this.j.put("NOTIFY_ITEM_RANGE_CHANGED", arrayList);
                }
                if (functionSetSubCardData != null) {
                    functionSetSubCardData.onDestroy();
                }
            }
        }
    }

    private void a(String str, int i) {
        List<CardConstructor> a2 = this.b.a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            CardConstructor cardConstructor = a2.get(i2);
            if (str.equals(cardConstructor.getCardId())) {
                if (i != i2) {
                    LogUtil.a("FunctionSetCardProvider", "addNewCardByCardId fromPosition = ", Integer.valueOf(i), " toPosition = ", Integer.valueOf(i2));
                    d(cardConstructor, i, i2);
                    return;
                }
                return;
            }
        }
    }

    private void d(CardConstructor cardConstructor, int i, int i2) {
        synchronized (this.f) {
            if (i2 < this.g.size()) {
                if (i >= 0) {
                    Collections.swap(this.g, i, i2);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i));
                    arrayList.add(Integer.valueOf(i2));
                    this.j.put("NOTIFY_ITEM_MOVED", arrayList);
                    LogUtil.a("FunctionSetCardProvider", "swap card, fromPosition = ", Integer.valueOf(i), " toPosition = ", Integer.valueOf(i2));
                } else {
                    LogUtil.h("FunctionSetCardProvider", "add new card to position, toPosition = ", Integer.valueOf(i2));
                }
            } else if (i < 0) {
                this.g.add(cardConstructor.createCardReader(this.f9472a));
                this.j.put("NOTIFY_ITEM_INSERTED", Integer.valueOf(this.g.size() - 1));
                LogUtil.a("FunctionSetCardProvider", "add new card to end");
            } else {
                LogUtil.h("FunctionSetCardProvider", "swap card fromPosition = ", Integer.valueOf(i));
            }
        }
    }
}
