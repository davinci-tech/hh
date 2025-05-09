package com.huawei.ui.homehealth.healthheadlinecard;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData;
import defpackage.dpg;
import defpackage.enq;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nrv;
import defpackage.ojw;
import defpackage.oko;
import defpackage.oli;
import defpackage.olu;
import defpackage.oly;
import defpackage.oma;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthHeadLinesCardData extends AbstractBaseCardData {

    /* renamed from: a, reason: collision with root package name */
    private View f9454a;
    private long b;
    private final WeakReference<Context> e;
    private HealthHeadLinesCardViewHolder f;
    private boolean c = true;
    private final Observer g = new AnonymousClass1();
    private final Observer d = new AnonymousClass4();

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    public HealthHeadLinesCardData(Context context) {
        this.e = new WeakReference<>(context == null ? BaseApplication.getContext() : context);
        LogUtil.a("HealthHeadLinesCardData", "HealthHeadLinesCardData create");
    }

    /* renamed from: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData$1, reason: invalid class name */
    public class AnonymousClass1 implements Observer {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            oli a2 = oli.a();
            enq f = a2.f();
            String h = f.h();
            LogUtil.a("HealthHeadLinesCardData", "handle show view, currentMediaId = ", h, ", title = ", f.n());
            if (!TextUtils.isEmpty(h)) {
                if (HealthHeadLinesCardData.this.f != null) {
                    HealthHeadLinesCardData.this.f.d(f, a2.n());
                }
                HealthHeadLinesCardData.this.g();
                return;
            }
            HealthHeadLinesCardData.this.c();
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!koq.e(objArr, 0)) {
                LogUtil.b("HealthHeadLinesCardData", "null args!");
                return;
            }
            LogUtil.a("HealthHeadLinesCardData", "show headlines observer args = ", objArr);
            Object obj = objArr[0];
            if (obj instanceof Boolean) {
                HandlerExecutor.e(((Boolean) obj).booleanValue() ? new Runnable() { // from class: oke
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthHeadLinesCardData.AnonymousClass1.this.a();
                    }
                } : new Runnable() { // from class: oka
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthHeadLinesCardData.AnonymousClass1.this.c();
                    }
                });
            }
        }

        public /* synthetic */ void c() {
            HealthHeadLinesCardData.this.a();
        }
    }

    /* renamed from: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData$4, reason: invalid class name */
    public class AnonymousClass4 implements Observer {
        AnonymousClass4() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(final String str, final Object... objArr) {
            HandlerExecutor.e(new Runnable() { // from class: okd
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesCardData.AnonymousClass4.this.c(str, objArr);
                }
            });
        }

        public /* synthetic */ void c(String str, Object[] objArr) {
            LogUtil.a("HealthHeadLinesCardData", "login status changed label = ", str, ", args = ", objArr);
            if (str == null) {
                return;
            }
            if ("com.huawei.plugin.account.logout".equals(str)) {
                oli.a().i(2);
                SharedPreferenceManager.c(Integer.toString(10000), "ShowHealthHeadLinesData", "");
                HealthHeadLinesCardData.this.a();
            } else if ("com.huawei.plugin.account.login".equals(str)) {
                HealthHeadLinesCardData.this.j();
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.f9454a = layoutInflater.inflate(R.layout.home_item_health_headlines, viewGroup, false);
        this.f = new HealthHeadLinesCardViewHolder(this, this.f9454a, b(), false);
        a();
        i();
        ojw.d();
        oli.a().b();
        LogUtil.a("HealthHeadLinesCardData", "getCardViewHolder");
        j();
        return this.f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        LogUtil.a("HealthHeadLinesCardData", "onResume lastShowTime:", Long.valueOf(this.b));
        enq f = oli.a().f();
        String h = f == null ? "" : f.h();
        if (jdl.ac(this.b) || !TextUtils.isEmpty(h)) {
            return;
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        View view = this.f9454a;
        if (view == null) {
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.height = 0;
        layoutParams.bottomMargin = 0;
        this.f9454a.setLayoutParams(layoutParams);
        this.f9454a.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        View view = this.f9454a;
        if (view == null) {
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.bottomMargin = nrr.e(b(), 12.0f);
        this.f9454a.setLayoutParams(layoutParams);
        this.f9454a.setVisibility(0);
        LogUtil.a("HealthHeadLinesCardData", "showView");
    }

    private void i() {
        if (this.c) {
            ObserverManagerUtil.d(this.g, "HOME_RECYCLE_VIEW_MOVE");
            ObserverManagerUtil.d(this.d, "com.huawei.plugin.account.logout");
            ObserverManagerUtil.d(this.d, "com.huawei.plugin.account.login");
            this.c = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.b = System.currentTimeMillis();
        String e = SharedPreferenceManager.e(Integer.toString(10000), "ShowHealthHeadLinesData", "");
        if (!TextUtils.isEmpty(e)) {
            b("true".equals(e));
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okb
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesCardData.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        b(oko.d());
    }

    private void b(boolean z) {
        LogUtil.a("HealthHeadLinesCardData", "isShow ", Boolean.valueOf(z));
        oko.e(z);
        LoginInit loginInit = LoginInit.getInstance(b());
        if (!loginInit.isKidAccount() && z && loginInit.getIsLogined()) {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(final enq enqVar, final int i) {
        LogUtil.a("HealthHeadLinesCardData", "initShowViewHolder start");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: okc
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesCardData.this.e(enqVar, i);
                }
            });
            return;
        }
        if (enqVar == null) {
            LogUtil.h("HealthHeadLinesCardData", "initShowViewHolder audioItem is null");
            return;
        }
        LogUtil.a("HealthHeadLinesCardData", "initShowViewHolder");
        HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder = this.f;
        if (healthHeadLinesCardViewHolder != null) {
            healthHeadLinesCardViewHolder.d(enqVar, i);
        }
        g();
        e();
    }

    public void c() {
        final enq enqVar;
        ReleaseLogUtil.e("R_HealthHeadLinesCardData", "initHealthHeadlinesListHistoryData");
        final oli a2 = oli.a();
        final olu p = a2.p();
        if (p == null || !ojw.j(p.d())) {
            enqVar = null;
        } else {
            enqVar = a2.n() != 3 ? p.c().get(0) : a2.f();
            LogUtil.a("HealthHeadLinesCardData", "initHealthHeadlinesListHistoryData has today list, curShowAudio:", enqVar);
            e(enqVar, a2.n());
        }
        HealthHeadLinesInfoManager.d().d(new UiCallback<List<oma>>() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("R_HealthHeadLinesCardData", "getTodayWorkoutListHistory failed, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                HealthHeadLinesCardData.this.b(p);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<oma> list) {
                if (!koq.b(list)) {
                    HealthHeadLinesCardData.this.d(list);
                    List<oly> a3 = ojw.a(list);
                    List<enq> e = ojw.e(list);
                    if (koq.b(a3) || koq.b(e)) {
                        ReleaseLogUtil.c("R_HealthHeadLinesCardData", "audioItemList or detailList is empty. init by backup plan.");
                        HealthHeadLinesCardData.this.b(p);
                        return;
                    }
                    if (enqVar == null) {
                        oli.a().v();
                    }
                    enq enqVar2 = e.get(e.size() - 1);
                    oly olyVar = a3.get(a3.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(enqVar2);
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(olyVar);
                    a2.c(arrayList, arrayList2);
                    ReleaseLogUtil.e("R_HealthHeadLinesCardData", "accept, current play state = ", Integer.valueOf(a2.n()));
                    LogUtil.a("HealthHeadLinesCardData", "initHealthHeadlinesListHistoryData audioItems today item = ", enqVar2);
                    if (a2.n() == 3) {
                        enqVar2 = a2.f();
                    }
                    enq enqVar3 = enqVar;
                    if (enqVar3 == null || !nrv.a(enqVar3).equals(nrv.a(enqVar2))) {
                        HealthHeadLinesCardData.this.e(enqVar2, a2.n());
                    }
                    ReleaseLogUtil.e("R_HealthHeadLinesCardData", "dealWithWorkoutDetailAndInitHolder end.");
                    return;
                }
                ReleaseLogUtil.c("R_HealthHeadLinesCardData", "data is empty. init by backup plan.");
                HealthHeadLinesCardData.this.b(p);
            }
        }, e(p), jdl.e(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<oma> list) {
        LogUtil.a("HealthHeadLinesCardData", "sortAudioWorkoutDetailData before data：", list.toString());
        Collections.sort(list, new Comparator() { // from class: ojx
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HealthHeadLinesCardData.b((oma) obj, (oma) obj2);
            }
        });
    }

    public static /* synthetic */ int b(oma omaVar, oma omaVar2) {
        if (omaVar == null || omaVar2 == null) {
            return 0;
        }
        String b = omaVar.b();
        String b2 = omaVar2.b();
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(b2)) {
            return 0;
        }
        try {
            return Long.compare(DateFormatUtil.d(b, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD).getTime(), DateFormatUtil.d(b2, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD).getTime());
        } catch (ParseException unused) {
            LogUtil.b("HealthHeadLinesCardData", "Covert Time Format Exception");
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(olu oluVar) {
        if (oluVar == null || koq.b(oluVar.c())) {
            ReleaseLogUtil.c("R_HealthHeadLinesCardData", "mediaInfo is null. setHolder fail.");
        } else {
            e(oluVar.c().get(0), oli.a().n());
        }
    }

    private long e(olu oluVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (oluVar == null || koq.b(oluVar.c())) {
            ReleaseLogUtil.e("R_HealthHeadLinesCardData", "getPlayList is null. setHolder fail. try to get one month detailInfo.");
            return dpg.d(ojw.a(currentTimeMillis, -29));
        }
        long d = ojw.d(oluVar.d().get(0));
        LogUtil.a("R_HealthHeadLinesCardData", "cacheWorkoutDetailDate = ", Long.valueOf(d), "; todayTime = ", Long.valueOf(currentTimeMillis));
        return jdl.t(Math.min(d, currentTimeMillis));
    }

    private void f() {
        ObserverManagerUtil.e(this.g, "HOME_RECYCLE_VIEW_MOVE");
        ObserverManagerUtil.e(this.d, "com.huawei.plugin.account.logout");
        ObserverManagerUtil.e(this.d, "com.huawei.plugin.account.login");
    }

    private Context b() {
        Context context = this.e.get();
        return context != null ? context : BaseApplication.getContext();
    }

    private void e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(ParamConstants.CallbackMethod.ON_SHOW, 1);
        LogUtil.a("HealthHeadLinesCardData", "HEALTH_HEADLINES_SHOW_1100053 healthHeadLinesMap = ", hashMap);
        ixx.d().d(b(), AnalyticsValue.HEALTH_HEADLINES_SHOW_1100053.value(), hashMap, 0);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        ReleaseLogUtil.e("R_HealthHeadLinesCardData", "onDestroy");
        HealthHeadLinesCardViewHolder healthHeadLinesCardViewHolder = this.f;
        if (healthHeadLinesCardViewHolder != null) {
            healthHeadLinesCardViewHolder.b();
        }
        super.onDestroy();
        LogUtil.a("HealthHeadLinesCardData", "onDestroy");
        f();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "HealthHeadLinesCardData";
    }
}
