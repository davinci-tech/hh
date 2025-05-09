package com.huawei.ui.homehealth.functionsetcard;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.functionsetcard.FunctionSetViewAdapter;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.health.utils.functionsetcard.ICardChangedCallback;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.marketing.datatype.templates.FunctionCardTemplate;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder;
import com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver;
import com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementActivity;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.dnx;
import defpackage.dpw;
import defpackage.efb;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nhj;
import defpackage.nsn;
import defpackage.ohy;
import defpackage.oia;
import defpackage.ojd;
import defpackage.omz;
import defpackage.owi;
import defpackage.owm;
import defpackage.owp;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionSetCardViewHolder extends CardViewHolder implements FunctionSetCardObserver {

    /* renamed from: a, reason: collision with root package name */
    private final int f9422a;
    private ICardChangedCallback b;
    private oia c;
    private HealthTextView d;
    private final int e;
    private ojd f;
    private Handler g;
    private BroadcastReceiver h;
    private HealthColumnSystem i;
    private RelativeLayout j;
    private LinearLayout k;
    private List<FunctionSetSubCardData> l;
    private long m;
    private boolean n;
    private long o;
    private NoTitleCustomAlertDialog p;
    private HealthRecycleView q;
    private HealthButton r;
    private Observer s;
    private c t;
    private View.OnClickListener u;
    private volatile FunctionSetViewAdapter x;

    /* loaded from: classes9.dex */
    public interface IRequestCallback {
        void onSuccess(FunctionCardTemplate functionCardTemplate);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardOrderChanged(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("FunctionSetCardViewHolder", "go to Card Management activity");
        if (getContext() != null) {
            try {
                getContext().startActivity(new Intent(getContext(), (Class<?>) FunctionSetCardManagementActivity.class));
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("FunctionSetCardViewHolder", "ActivityNotFoundException", e2.getMessage());
            }
        }
        this.x.a(false);
    }

    FunctionSetCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.e = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362889_res_0x7f0a0449);
        this.f9422a = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362948_res_0x7f0a0484);
        this.o = 0L;
        this.l = new CopyOnWriteArrayList();
        this.g = new Handler(Looper.getMainLooper());
        this.h = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    return;
                }
                int intExtra = intent.getIntExtra("refresh_type", -1);
                if (intExtra == 0) {
                    ohy.c().e();
                    FunctionSetCardViewHolder.this.n();
                }
                if (intExtra == 9) {
                    for (FunctionSetSubCardData functionSetSubCardData : FunctionSetCardViewHolder.this.l) {
                        if (functionSetSubCardData instanceof ojd) {
                            functionSetSubCardData.readCardData();
                        }
                    }
                }
            }
        };
        this.u = new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FunctionSetCardViewHolder.this.h();
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                hashMap.put("from", 0);
                hashMap.put("smartRecommend", Boolean.valueOf(FunctionSetCardViewHolder.this.c.h()));
                ixx.d().d(FunctionSetCardViewHolder.this.getContext().getApplicationContext(), AnalyticsValue.HEALTH_HOME_MANAGERMENT_CARD_2010032.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view2);
            }
        };
        ReleaseLogUtil.d("R_FunctionSetCardViewHolder", "FunctionSetCardViewHolder called");
        this.n = nsn.ag(BaseApplication.getActivity());
        this.b = new e(this);
        l();
        k();
    }

    private void k() {
        List<String> d2 = oia.c().d();
        ReleaseLogUtil.e("R_FunctionSetCardViewHolder", "initSummary cardList ", d2);
        if (koq.b(d2)) {
            return;
        }
        List<String> e2 = e(d2);
        nhj.b(e2, d2.size());
        ReleaseLogUtil.e("R_FunctionSetCardViewHolder", "initSummary list ", e2, " mLastRequestSummaryTime ", Long.valueOf(this.m));
        this.m = System.currentTimeMillis();
    }

    private List<String> e(List<String> list) {
        ArrayList arrayList = new ArrayList(3);
        if (list.contains(CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName())) {
            arrayList.add("activityRings");
        }
        if (list.contains(CardFlowInteractors.CardNameConstants.BLOODPRESSURE_CARD.getName())) {
            arrayList.add(MessageConstant.BLOOD_PRESSURE_TYPE);
        }
        if (list.contains(CardFlowInteractors.CardNameConstants.STRESS_CARD.getName())) {
            arrayList.add("emotionalHealth");
        }
        return arrayList;
    }

    public void i() {
        HealthColumnSystem healthColumnSystem = this.i;
        if (healthColumnSystem != null) {
            healthColumnSystem.e(getContext());
            c cVar = this.t;
            if (cVar != null) {
                cVar.setSpanCount(j());
            }
            boolean ag = nsn.ag(BaseApplication.getActivity());
            if (ag != this.n && !efb.e(getContext())) {
                LogUtil.a("FunctionSetCardViewHolder", "Screen changed, notify all card, isWidescreen:", Boolean.valueOf(ag));
                this.x.notifyDataSetChanged();
            }
            this.n = ag;
        }
    }

    private void l() {
        oia c2 = oia.c();
        this.c = c2;
        c2.a(this);
        this.i = new HealthColumnSystem(getContext(), 1);
        this.t = new c(j(), 1);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.itemView.findViewById(R.id.function_set_view);
        this.q = healthRecycleView;
        healthRecycleView.setFocusableInTouchMode(false);
        this.q.setIsScroll(false);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.q.getLayoutParams();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int a2 = dpw.a(getContext());
        int e2 = dpw.e(getContext());
        int d2 = dpw.d(getContext()) / 2;
        layoutParams.setMarginStart((a2 - d2) + ((Integer) safeRegionWidth.first).intValue());
        layoutParams.setMarginEnd((e2 - d2) + ((Integer) safeRegionWidth.first).intValue());
        f();
        this.x = new FunctionSetViewAdapter(this.l, getContext());
        this.x.setHasStableIds(true);
        dnx.d().c(this.x);
        this.q.setLayoutManager(this.t);
        this.q.setItemAnimator(null);
        this.q.setAdapter(this.x);
        this.j = (RelativeLayout) this.itemView.findViewById(R.id.function_set_no_card);
        this.k = (LinearLayout) this.itemView.findViewById(R.id.modify_cards_layout);
        HealthTextView healthTextView = (HealthTextView) this.itemView.findViewById(R.id.add_card);
        this.d = healthTextView;
        healthTextView.setText(getContext().getString(R.string._2130837877_res_0x7f020175));
        HealthButton healthButton = (HealthButton) this.itemView.findViewById(R.id.btn_modify_cards);
        this.r = healthButton;
        healthButton.setText(getContext().getString(R.string._2130837879_res_0x7f020177));
        this.r.setOnClickListener(this.u);
        g();
        m();
        q();
        d();
        o();
    }

    private int j() {
        int f = this.i.f() / 2;
        LogUtil.a("FunctionSetCardViewHolder", "getCardColumNum CARD_WIDTH_MIN:", Integer.valueOf(this.e), ", CARD_WIDTH_MAX", Integer.valueOf(this.f9422a), ", columNum", Integer.valueOf(f), ", SingleColumnWidth:", Float.valueOf(this.i.g()), ", getGutter:", Integer.valueOf(this.i.a()));
        if (this.i.e()) {
            if (this.f9422a < (this.i.g() * 2.0f) + this.i.a()) {
                return f + 1;
            }
            LogUtil.a("FunctionSetCardViewHolder", "getCardColumNum isPhoneScreen columNum:", Integer.valueOf(f));
            return f;
        }
        if (this.e > (this.i.g() * 2.0f) + this.i.a()) {
            return f - 1;
        }
        LogUtil.a("FunctionSetCardViewHolder", "getCardColumNum is not PhoneScreen columNum:", Integer.valueOf(f));
        return f;
    }

    private void g() {
        HealthTextView healthTextView = (HealthTextView) this.itemView.findViewById(R.id.empty_card_prompt_text);
        healthTextView.setText(getContext().getString(R.string._2130837996_res_0x7f0201ec));
        if (LanguageUtil.r(getContext().getApplicationContext())) {
            healthTextView.setTextSize(1, 9.0f);
        } else {
            healthTextView.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6));
        }
    }

    public static class e implements ICardChangedCallback {
        private WeakReference<FunctionSetCardViewHolder> e;

        @Override // com.huawei.health.health.utils.functionsetcard.ICardChangedCallback
        public void notifyCardDelete(FunctionSetSubCardData functionSetSubCardData) {
        }

        e(FunctionSetCardViewHolder functionSetCardViewHolder) {
            this.e = new WeakReference<>(functionSetCardViewHolder);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.ICardChangedCallback
        public void notifyCardChanged(final FunctionSetSubCardData functionSetSubCardData) {
            if (!HandlerExecutor.b()) {
                HandlerExecutor.a(new Runnable() { // from class: ohw
                    @Override // java.lang.Runnable
                    public final void run() {
                        FunctionSetCardViewHolder.e.this.a(functionSetSubCardData);
                    }
                });
            } else {
                a(functionSetSubCardData);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void a(FunctionSetSubCardData functionSetSubCardData) {
            FunctionSetCardViewHolder functionSetCardViewHolder = this.e.get();
            if (functionSetCardViewHolder != null) {
                functionSetCardViewHolder.d(functionSetSubCardData);
            } else {
                LogUtil.h("FunctionSetCardViewHolder", "CardChangedCallback holder is null");
            }
        }

        @Override // com.huawei.health.health.utils.functionsetcard.ICardChangedCallback
        public void notifyCardScrolled(FunctionSetSubCardData functionSetSubCardData) {
            FunctionSetCardViewHolder functionSetCardViewHolder = this.e.get();
            if (functionSetCardViewHolder != null) {
                if (functionSetCardViewHolder.x != null) {
                    int a2 = functionSetCardViewHolder.x.a(functionSetSubCardData);
                    if (functionSetCardViewHolder.x.e(functionSetCardViewHolder.l, a2)) {
                        functionSetCardViewHolder.x.e(a2);
                        return;
                    }
                    LogUtil.a("FunctionSetCardViewHolder", "out of bounds: " + a2 + ", mList size: " + functionSetCardViewHolder.l.size());
                    return;
                }
                LogUtil.a("FunctionSetCardViewHolder", "mViewAdapter is null");
                return;
            }
            LogUtil.h("FunctionSetCardViewHolder", "CardChangedCallback holder is null");
        }

        @Override // com.huawei.health.health.utils.functionsetcard.ICardChangedCallback
        public View loadMarketingView(String str, int i) {
            FunctionSetCardViewHolder functionSetCardViewHolder = this.e.get();
            if (functionSetCardViewHolder != null) {
                return functionSetCardViewHolder.c.daO_(str, functionSetCardViewHolder.x.d(i));
            }
            LogUtil.a("FunctionSetCardViewHolder", "CardChangedCallback holder is null");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(FunctionSetSubCardData functionSetSubCardData) {
        LogUtil.a("FunctionSetCardViewHolder", "notify recevied");
        if (this.x == null) {
            LogUtil.a("FunctionSetCardViewHolder", "mViewAdapter is null");
            return;
        }
        int a2 = this.x.a(functionSetSubCardData);
        LogUtil.a("FunctionSetCardViewHolder", "data changed, position: " + a2);
        if (!this.x.e(this.l, a2)) {
            LogUtil.a("FunctionSetCardViewHolder", "out of bounds: " + a2 + ", mList size: " + this.l.size());
            return;
        }
        if (this.o == 0 || SystemClock.elapsedRealtime() - this.o > 2000) {
            if (functionSetSubCardData.hasCardData() && !functionSetSubCardData.hasCardDataLastTime()) {
                owi.a(functionSetSubCardData, 1, this.c.h());
            }
            this.o = SystemClock.elapsedRealtime();
        }
        a(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        HealthRecycleView healthRecycleView = this.q;
        if (healthRecycleView == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyItemChanged mRecyclerView is null");
        } else if (healthRecycleView.isComputingLayout()) {
            LogUtil.a("FunctionSetCardViewHolder", "mRecyclerView isComputingLayout");
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.8
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.a(i);
                }
            }, 50L);
        } else {
            LogUtil.a("FunctionSetCardViewHolder", "mViewAdapter notifyItemChanged");
            this.x.notifyItemChanged(i);
        }
    }

    private void f() {
        List<CardConstructor> a2 = this.c.a();
        if (koq.b(a2)) {
            LogUtil.h("FunctionSetCardViewHolder", "initCard mViewAdapter or cardConstructors is null");
        } else {
            this.l.clear();
            this.l.addAll(owm.c(getContext(), a2, this.b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (koq.b(this.l)) {
            this.j.setVisibility(0);
            this.j.setOnClickListener(this.u);
            this.r.setVisibility(8);
            d(R.dimen._2131362565_res_0x7f0a0305);
            return;
        }
        this.j.setVisibility(8);
        this.r.setVisibility(0);
        this.r.setText(R.string._2130837879_res_0x7f020177);
        d(R.dimen._2131362566_res_0x7f0a0306);
    }

    private void d(int i) {
        if (this.k.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.k.getLayoutParams();
            layoutParams.bottomMargin = BaseApplication.getContext().getResources().getDimensionPixelOffset(i);
            this.k.setLayoutParams(layoutParams);
        }
    }

    private void q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        LocalBroadcastManager.getInstance(getContext().getApplicationContext()).registerReceiver(this.h, intentFilter);
        LogUtil.a("FunctionSetCardViewHolder", "mHiBroadcastReceiver register success", this);
    }

    private void r() {
        if (this.h != null) {
            try {
                LocalBroadcastManager.getInstance(getContext().getApplicationContext()).unregisterReceiver(this.h);
                LogUtil.a("FunctionSetCardViewHolder", "mHiBroadcastReceiver unregister", this);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("FunctionSetCardViewHolder", "IllegalArgumentException mHiBroadcastReceiver unregister");
            }
        }
    }

    public void b() {
        ReleaseLogUtil.e("R_FunctionSetCardViewHolder", "onResume mLastRequestSummaryTime ", Long.valueOf(this.m));
        if (Math.abs(System.currentTimeMillis() - this.m) > 600000 || !jdl.ac(this.m)) {
            k();
        }
        for (FunctionSetSubCardData functionSetSubCardData : this.l) {
            LogUtil.a("FunctionSetCardViewHolder", "reader: ", functionSetSubCardData);
            if (functionSetSubCardData != null) {
                functionSetSubCardData.onResume();
            }
        }
        this.c.l();
    }

    public void a() {
        owi.c(getContext(), this.l, this.c.h());
        for (FunctionSetSubCardData functionSetSubCardData : this.l) {
            if (functionSetSubCardData != null) {
                functionSetSubCardData.onDestroy();
            }
        }
        this.c.e(this);
        r();
        e();
        ObserverManagerUtil.e(this.s, "PRESS_HODLE_DELETE_CARD");
    }

    public void c() {
        r();
        this.c.e(this);
    }

    public void e() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        HealthRecycleView healthRecycleView = this.q;
        if (healthRecycleView != null) {
            healthRecycleView.setOnScrollListener(null);
            this.q = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (this.x != null) {
            this.x.c();
        }
        List<FunctionSetSubCardData> list = this.l;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("FunctionSetCardViewHolder", "refreshAllCard");
        for (FunctionSetSubCardData functionSetSubCardData : this.l) {
            LogUtil.a("FunctionSetCardViewHolder", "refreshAllCard, reader: ", functionSetSubCardData);
            if (functionSetSubCardData != null) {
                if (functionSetSubCardData instanceof FunctionSetBeanReader) {
                    ((FunctionSetBeanReader) functionSetSubCardData).setRefreshReason(0);
                }
                functionSetSubCardData.readCardData();
            }
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyAllCardChanged() {
        if (this.g == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyAllCardChanged mHandler is null");
            return;
        }
        LogUtil.a("FunctionSetCardViewHolder", "notifyAllCardChanged");
        final List<CardConstructor> a2 = this.c.a();
        this.g.post(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.13
            @Override // java.lang.Runnable
            public void run() {
                FunctionSetCardViewHolder.this.b((List<CardConstructor>) a2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<CardConstructor> list) {
        this.x.e(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.11
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(10);
                if (koq.c(FunctionSetCardViewHolder.this.l)) {
                    arrayList.addAll(FunctionSetCardViewHolder.this.l);
                }
                FunctionSetCardViewHolder.this.l.clear();
                FunctionSetCardViewHolder.this.l.addAll(owm.a(FunctionSetCardViewHolder.this.getContext(), arrayList, list, FunctionSetCardViewHolder.this.b));
                FunctionSetCardViewHolder.this.x.notifyDataSetChanged();
                FunctionSetCardViewHolder.this.m();
                FunctionSetCardViewHolder.this.p();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.14
            @Override // java.lang.Runnable
            public void run() {
                FunctionSetCardViewHolder.this.t();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        CardConfig cardConfig;
        for (FunctionSetSubCardData functionSetSubCardData : this.l) {
            if (functionSetSubCardData != null && (cardConfig = functionSetSubCardData.getCardConfig()) != null && "PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(cardConfig.getCardId())) {
                SharedPreferenceManager.e(getContext(), Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY", "true", new StorageParams());
                return;
            }
        }
        SharedPreferenceManager.e(getContext(), Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY", "false", new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z) {
        if ("PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(str)) {
            SharedPreferenceManager.e(getContext(), Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY", z ? "true" : "false", new StorageParams());
        }
    }

    private void o() {
        HandlerExecutor.d(new a(this), PreConnectManager.CONNECT_INTERNAL);
    }

    static class a implements Runnable {
        private WeakReference<FunctionSetCardViewHolder> e;

        a(FunctionSetCardViewHolder functionSetCardViewHolder) {
            this.e = new WeakReference<>(functionSetCardViewHolder);
        }

        @Override // java.lang.Runnable
        public void run() {
            FunctionSetCardViewHolder functionSetCardViewHolder = this.e.get();
            if (functionSetCardViewHolder == null) {
                LogUtil.a("FunctionSetCardViewHolder", "holder ref is null");
                return;
            }
            String b = SharedPreferenceManager.b(functionSetCardViewHolder.getContext(), Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY");
            LogUtil.a("FunctionSetCardViewHolder", "initPhyscialCycleShowStatusSp value: ", b);
            if (TextUtils.isEmpty(b)) {
                functionSetCardViewHolder.t();
            }
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(final String str, final boolean z) {
        LogUtil.a("FunctionSetCardViewHolder", "notifyCardShowStatusChanged cardId");
        Handler handler = this.g;
        if (handler == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyCardShowStatusChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.15
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.d(str, z, false);
                    FunctionSetCardViewHolder.this.d(str, z);
                    FunctionSetCardViewHolder.this.m();
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(final List<String> list, final boolean z) {
        LogUtil.a("FunctionSetCardViewHolder", "notifyCardShowStatusChanged cardIds");
        Handler handler = this.g;
        if (handler == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyCardShowStatusChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.12
                @Override // java.lang.Runnable
                public void run() {
                    for (String str : list) {
                        FunctionSetCardViewHolder.this.d(str, z, false);
                        FunctionSetCardViewHolder.this.d(str, z);
                    }
                    FunctionSetCardViewHolder.this.m();
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardInsert(final String str) {
        LogUtil.a("FunctionSetCardViewHolder", "notifyCardInsert cardId: ", str);
        Handler handler = this.g;
        if (handler == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyCardShowStatusChanged mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.18
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.b(str, -1);
                    FunctionSetCardViewHolder.this.m();
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardReplace(final String str, final String str2) {
        LogUtil.a("FunctionSetCardViewHolder", "notifyCardReplace hideCardId: ", str, " showCardId", str2);
        Handler handler = this.g;
        if (handler == null) {
            LogUtil.h("FunctionSetCardViewHolder", "notifyCardReplace mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.2
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.e(str, str2);
                    FunctionSetCardViewHolder.this.m();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z, boolean z2) {
        LogUtil.a("FunctionSetCardViewHolder", "changeCardShowStatus cardId = ", str, " isShow = ", Boolean.valueOf(z));
        if (StringUtils.g(str) || this.x == null) {
            LogUtil.h("FunctionSetCardViewHolder", "changeCardShowStatus cardId or mViewAdapter is null");
            return;
        }
        if (!z2 && "PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(str) && this.c.f()) {
            LogUtil.a("FunctionSetCardViewHolder", "IsUpdateBuUser, PHYSIOLOGICAL no changed by gender");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.l.size()) {
                i = -1;
                break;
            } else if (str.equals(this.l.get(i).getCardId())) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            LogUtil.a("FunctionSetCardViewHolder", "changeCardShowStatus add card,  position = ", Integer.valueOf(i));
            b(str, i);
            return;
        }
        if (i >= 0) {
            LogUtil.a("FunctionSetCardViewHolder", "changeCardShowStatus remove card,  position = ", Integer.valueOf(i));
            FunctionSetSubCardData functionSetSubCardData = this.l.get(i);
            this.l.remove(i);
            this.x.notifyItemRemoved(i);
            if (i != this.l.size()) {
                this.x.notifyItemRangeChanged(i, this.l.size() - i);
            }
            if (functionSetSubCardData != null) {
                functionSetSubCardData.onDestroy();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        LogUtil.a("FunctionSetCardViewHolder", "replaceCard showCardId = ", str2, " hideCardId = ", str);
        if (StringUtils.g(str2) || this.x == null || StringUtils.g(str)) {
            LogUtil.h("FunctionSetCardViewHolder", "changeCardShowStatus cardId or mViewAdapter is null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.l.size()) {
                i = -1;
                break;
            } else if (str.equals(this.l.get(i).getCardId())) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            LogUtil.a("FunctionSetCardViewHolder", "changeCardShowStatus remove card,  position = ", Integer.valueOf(i));
            FunctionSetSubCardData functionSetSubCardData = this.l.get(i);
            this.l.remove(i);
            this.x.notifyItemRemoved(this.x.b(i));
            if (functionSetSubCardData != null) {
                functionSetSubCardData.onDestroy();
            }
            c(str2, -1);
        }
    }

    private void c(String str, int i) {
        List<CardConstructor> a2 = this.c.a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            CardConstructor cardConstructor = a2.get(i2);
            if (str.equals(cardConstructor.getCardId())) {
                LogUtil.a("FunctionSetCardViewHolder", "insertNewCardByCardId fromPosition = ", Integer.valueOf(i), " toPosition = ", Integer.valueOf(i2));
                owi.b(getContext(), d(cardConstructor, i, i2), this.c.h(), 2);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        List<CardConstructor> a2 = this.c.a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            CardConstructor cardConstructor = a2.get(i2);
            if (str.equals(cardConstructor.getCardId())) {
                if (i != i2) {
                    LogUtil.a("FunctionSetCardViewHolder", "addNewCardByCardId fromPosition = ", Integer.valueOf(i), " toPosition = ", Integer.valueOf(i2));
                    owi.b(getContext(), d(cardConstructor, i, i2), this.c.h(), 2);
                    return;
                }
                return;
            }
        }
    }

    private FunctionSetSubCardData d(CardConstructor cardConstructor, int i, int i2) {
        if (i2 >= this.l.size()) {
            if (i < 0) {
                FunctionSetSubCardData createCardReader = cardConstructor.createCardReader(getContext());
                createCardReader.setNotifyCardChangedCallback(this.b);
                createCardReader.setCardConstructor(cardConstructor);
                this.l.add(createCardReader);
                this.x.notifyItemInserted(this.l.size() - 1);
                LogUtil.a("FunctionSetCardViewHolder", "add new card to end");
                return createCardReader;
            }
            LogUtil.h("FunctionSetCardViewHolder", "swap card fromPosition = ", Integer.valueOf(i));
            return null;
        }
        if (i >= 0) {
            Collections.swap(this.l, i, i2);
            this.x.notifyItemMoved(i, i2);
            LogUtil.a("FunctionSetCardViewHolder", "swap card, fromPosition = ", Integer.valueOf(i), " toPosition = ", Integer.valueOf(i2));
            return null;
        }
        LogUtil.h("FunctionSetCardViewHolder", "add new card to position, toPosition = ", Integer.valueOf(i2));
        FunctionSetSubCardData createCardReader2 = cardConstructor.createCardReader(getContext());
        createCardReader2.setNotifyCardChangedCallback(this.b);
        createCardReader2.setCardConstructor(cardConstructor);
        this.l.add(i2, createCardReader2);
        this.x.notifyItemInserted(this.x.b(i2));
        return createCardReader2;
    }

    static class c extends StaggeredGridLayoutManager {
        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                ReleaseLogUtil.c("R_FunctionSetCardViewHolder", "functionSetCardViewHolder RecyclerView onLayoutChildren exception. message = ", e.getMessage());
            }
        }
    }

    static class d implements Observer {
        private WeakReference<FunctionSetCardViewHolder> b;

        d(FunctionSetCardViewHolder functionSetCardViewHolder) {
            this.b = new WeakReference<>(functionSetCardViewHolder);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (objArr == null) {
                LogUtil.h("FunctionSetCardViewHolder", "args is null");
                return;
            }
            if (objArr.length >= 2 && (objArr[1] instanceof String)) {
                FunctionSetCardViewHolder functionSetCardViewHolder = this.b.get();
                if (functionSetCardViewHolder != null) {
                    functionSetCardViewHolder.c((String) objArr[1], (FunctionSetSubCardData) objArr[0]);
                } else {
                    LogUtil.h("FunctionSetCardViewHolder", "DeleteCardObserver holder is null");
                }
            }
        }
    }

    public void d() {
        d dVar = new d(this);
        this.s = dVar;
        ObserverManagerUtil.d(dVar, "PRESS_HODLE_DELETE_CARD");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, FunctionSetSubCardData functionSetSubCardData) {
        CardConfig cardConfig;
        if ("1".equals(str)) {
            if (functionSetSubCardData == null || (cardConfig = functionSetSubCardData.getCardConfig()) == null) {
                return;
            }
            b(cardConfig, functionSetSubCardData);
            return;
        }
        if ("2".equals(str)) {
            h();
            owi.b(getContext(), this.c.h(), 2);
            owi.a(getContext(), this.c.h(), 1, functionSetSubCardData);
        } else {
            if ("3".equals(str)) {
                owi.c(getContext(), functionSetSubCardData);
                return;
            }
            if ("4".equals(str)) {
                owi.b(getContext(), this.c.h(), 0);
            } else if ("5".equals(str)) {
                owi.b(getContext(), this.c.h(), 3);
            } else if ("6".equals(str)) {
                owi.e(getContext(), functionSetSubCardData);
            }
        }
    }

    private void b(CardConfig cardConfig, FunctionSetSubCardData functionSetSubCardData) {
        if ("PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(cardConfig.getCardId())) {
            a(cardConfig, functionSetSubCardData);
        } else {
            e(cardConfig, functionSetSubCardData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final CardConfig cardConfig, final FunctionSetSubCardData functionSetSubCardData) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.4
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData);
                }
            });
            return;
        }
        d(cardConfig.getCardId(), false);
        owi.d(getContext(), 0, functionSetSubCardData, this.l, this.c.h(), 0);
        d(cardConfig.getCardId(), false, true);
        this.c.c(cardConfig);
        LogUtil.a("FunctionSetCardViewHolder", "hide card cardId: " + cardConfig.getCardId());
        owi.b(getContext(), this.c.h(), 1);
        owi.a(functionSetSubCardData, 3, oia.c().h());
    }

    private void a(final CardConfig cardConfig, final FunctionSetSubCardData functionSetSubCardData) {
        LogUtil.a("FunctionSetCardViewHolder", "processPhysiologicalCycleCardDelete");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.3
            @Override // java.lang.Runnable
            public void run() {
                String g = owp.g(FunctionSetCardViewHolder.this.getContext());
                if (g == null) {
                    LogUtil.b("FunctionSetCardViewHolder", "getMenstrualSwitch is null.");
                    FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData);
                } else {
                    if ("".equals(g)) {
                        FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData, g);
                        return;
                    }
                    try {
                        if (new JSONObject(g).getInt("masterSwitch") == 1) {
                            FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData, g);
                            return;
                        }
                    } catch (JSONException unused) {
                        LogUtil.b("FunctionSetCardViewHolder", "setPhysicalCycleSwitch JSONException.");
                    }
                    FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final CardConfig cardConfig, final FunctionSetSubCardData functionSetSubCardData, final String str) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.5
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData, str);
                }
            });
            return;
        }
        if (this.p == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getContext());
            builder.e(getContext().getResources().getString(R.string._2130838114_res_0x7f020262)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FunctionSetCardViewHolder.this.c(str);
                    FunctionSetCardViewHolder.this.s();
                    FunctionSetCardViewHolder.this.e(cardConfig, functionSetSubCardData);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.p = builder.e();
        }
        if (this.p.isShowing()) {
            return;
        }
        this.p.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.FunctionSetCardViewHolder.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList(5);
                    arrayList.add("masterSwitch");
                    arrayList.add("menstrualStartSwitch");
                    arrayList.add("menstrualEndSwitch");
                    arrayList.add("easyPregnancyStartSwitch");
                    arrayList.add("easyPregnancyEndSwitch");
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        jSONObject.put((String) it.next(), 0);
                    }
                    HiUserPreference userPreference = HiHealthManager.d(FunctionSetCardViewHolder.this.getContext()).getUserPreference("com.huawei.health.mc");
                    if (userPreference == null) {
                        ReleaseLogUtil.d("FunctionSetCardViewHolder", "setPhysicalCycleSwitch userPreference is null");
                        return;
                    }
                    userPreference.setValue(jSONObject.toString());
                    userPreference.setSyncStatus(0);
                    HiHealthManager.d(FunctionSetCardViewHolder.this.getContext()).setUserPreference(userPreference);
                } catch (JSONException unused) {
                    LogUtil.b("FunctionSetCardViewHolder", "setPhysicalCycleSwitch JSONException.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        MenstrualSwitchStatus menstrualSwitchStatus = new MenstrualSwitchStatus();
        menstrualSwitchStatus.setMasterSwitch(0);
        menstrualSwitchStatus.setMenstruationStartRemindSwitch(0);
        menstrualSwitchStatus.setMenstruationEndRemindSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyStartSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyEndSwitch(0);
        omz.a().b(menstrualSwitchStatus);
    }
}
