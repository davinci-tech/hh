package com.huawei.ui.homehealth.threecirclecard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.healthmodel.HealthModelApi;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeStatistic;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ucd.cloveranimation.AddFrameListener;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.Clover;
import com.huawei.ui.homehealth.stepscard.AchieveTaskHandler;
import com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder;
import com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardData;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.watchface.api.HwWatchFaceApi;
import defpackage.bzs;
import defpackage.dsa;
import defpackage.dsl;
import defpackage.dsm;
import defpackage.eyw;
import defpackage.gnm;
import defpackage.guw;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.jfa;
import defpackage.koq;
import defpackage.njj;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrv;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsg;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.oun;
import defpackage.owc;
import defpackage.pit;
import defpackage.qmf;
import defpackage.sdh;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class ThreeLeafCardData extends TwoModelBaseCard {
    private boolean aa;
    private boolean ab;
    private float ac;
    private float ad;
    private boolean ae;
    private int af;
    private List<Integer> ag;
    private int ah;
    private String ai;
    private String aj;
    private c ak;
    private int al;
    private int am;
    private ThreeLeafCardViewHolder an;
    private boolean f;
    private AchieveTaskHandler h;
    private final Context i;
    private boolean j;
    private String k;
    private int l;
    private int m;
    private int n;
    private Context o;
    private int p;
    private List<HealthLifeTaskBean> q;
    private String r;
    private final BroadcastReceiver s;
    private int t;
    private final boolean u;
    private boolean v;
    private boolean w;
    private final boolean x;
    private final AtomicBoolean y;
    private float z;

    private boolean a(float f, float f2, float f3) {
        return f >= 1.0f && f3 >= 1.0f && f2 >= 1.0f;
    }

    public void c(String str) {
        if ("900200006".equals(str) || "900200008".equals(str)) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(str);
            dsl.e(arrayList, (ResponseCallback<List<HealthLifeBean>>) new ResponseCallback() { // from class: our
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "threeCircleAndStepGoalUpdate onResponse status ", Integer.valueOf(i), " list ", (List) obj);
                }
            });
        }
    }

    static class d implements HiSubscribeListener {
        private WeakReference<ThreeLeafCardData> b;

        d(ThreeLeafCardData threeLeafCardData) {
            this.b = new WeakReference<>(threeLeafCardData);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (list != null && list2 != null && this.b.get() != null) {
                this.b.get().ag = list;
                LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onResult:", Integer.valueOf(list.size()), " failList:", Integer.valueOf(list2.size()));
            } else {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "onResult list null.");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ThreeLeafCardData threeLeafCardData = this.b.get();
            if (threeLeafCardData == null) {
                return;
            }
            if (i == 103) {
                LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "switch state changed,", str);
                if ("900300020".equals(str)) {
                    threeLeafCardData.x();
                    return;
                }
                return;
            }
            if (ArkUIXConstants.INSERT.equals(str) || "latestDataDownload".equals(str)) {
                threeLeafCardData.k = str;
                if (i == DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value() || i == 2 || i == 3 || i == DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value() || i == HiSubscribeType.f4119a || i == 14 || i == 200 || i == HiSubscribeType.c) {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onChange type:", Integer.valueOf(i), " time:", Long.valueOf(j), " changeKey:", str);
                    if (threeLeafCardData.a(i)) {
                        threeLeafCardData.j(1);
                        return;
                    }
                    return;
                }
                LogUtil.c("HealthLife_SCUI_ThreeLeafCardData", "observerType other type");
                return;
            }
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "sync from cloud.", Integer.valueOf(i), " changeKey:", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getAnimationSwitch");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900300020");
        njj.d("9003", arrayList, new HiDataReadResultListener() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardData.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (!koq.e(obj, HiSampleConfig.class)) {
                    LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "list getAnimationSwitch isListTypeMatch false ");
                    dsl.e("healthLifeThreeLeafSwitch", true);
                    return;
                }
                List list = (List) obj;
                if (koq.b(list) || list.size() != 1) {
                    LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "list getAnimationSwitch list is empty ");
                    dsl.e("healthLifeThreeLeafSwitch", true);
                } else {
                    HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "animation switch is ", hiSampleConfig.getConfigData());
                    dsl.e("healthLifeThreeLeafSwitch", "1".equals(dsl.c(hiSampleConfig.getConfigData(), "healthLifeThreeLeafSwitch")));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        boolean z = i == HiSubscribeType.f4119a;
        boolean z2 = i == HiSubscribeType.c;
        if (koq.b(this.q) || !(z || z2)) {
            return true;
        }
        for (HealthLifeTaskBean healthLifeTaskBean : this.q) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getComplete() <= 0) {
                int id = healthLifeTaskBean.getId();
                if (z && id == 12) {
                    return true;
                }
                if (z2 && id == 14) {
                    return true;
                }
            }
        }
        return false;
    }

    static class c extends BroadcastReceiver {
        private WeakReference<ThreeLeafCardData> d;

        c(ThreeLeafCardData threeLeafCardData) {
            this.d = new WeakReference<>(threeLeafCardData);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "SyncCloudDataReceiver onReceive intent is null or context is null");
                return;
            }
            ThreeLeafCardData threeLeafCardData = this.d.get();
            if (threeLeafCardData == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "SyncCloudDataReceiver onReceive cardData is null");
                return;
            }
            String stringExtra = intent.getStringExtra("sync_cloud_data_status");
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "Sync status:", stringExtra, " process:", Double.valueOf(intent.getDoubleExtra("sync_cloud_data_process", 0.0d)));
            threeLeafCardData.y.set("ongoing_sync_cloud_data".equals(stringExtra));
            if ("sync_cloud_data_backstage".equals(stringExtra)) {
                threeLeafCardData.x();
            }
            if ("sync_cloud_data_finish".equals(stringExtra) || "sync_cloud_data_fail".equals(stringExtra) || "sync_cloud_data_setting_flag".equals(stringExtra)) {
                threeLeafCardData.j(1);
                threeLeafCardData.x();
            }
        }
    }

    public ThreeLeafCardData(Context context) {
        super(context);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardData.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null || !HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                    LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "mForegroundStatusChangeReceiver onReceive intent ", intent);
                    return;
                }
                if ("threeLeafCard".equals(oun.a())) {
                    boolean z = false;
                    boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "mForegroundStatusChangeReceiver onReceive isForeground ", Boolean.valueOf(booleanExtra));
                    if (booleanExtra) {
                        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "mForegroundStatusChangeReceiver onReceive getTopActivity ", BaseApplication.wa_());
                        ThreeLeafCardData threeLeafCardData = ThreeLeafCardData.this;
                        if (CommonUtil.h(threeLeafCardData.i, "com.huawei.health.MainActivity") && ThreeLeafCardData.this.v) {
                            z = true;
                        }
                        threeLeafCardData.aa = z;
                    }
                }
            }
        };
        this.s = broadcastReceiver;
        this.y = new AtomicBoolean();
        Context e2 = BaseApplication.e();
        this.i = e2;
        this.f = true;
        this.j = false;
        this.p = -1;
        this.n = -1;
        this.l = 2;
        CommonUtil.a("HealthLife_SCUI_ThreeLeafCardData", "ThreeLeafCardData");
        this.o = context == null ? e2 : context;
        this.x = LanguageUtil.bc(e2);
        this.u = LanguageUtil.h(e2);
        this.aa = "threeLeafCard".equals(oun.a());
        this.w = UnitUtil.h();
        d(new d(this));
        aa();
        z();
        BroadcastManager.wj_(broadcastReceiver);
        this.am = DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault());
        guw.c();
    }

    public ThreeLeafCardData(Context context, AchieveTaskHandler achieveTaskHandler) {
        this(context);
        this.h = achieveTaskHandler;
    }

    private void z() {
        ObserverManagerUtil.d(new b(this), "HEALTH_MODEL_CARD_KEY_NEW");
    }

    static class b implements Observer {
        private WeakReference<ThreeLeafCardData> b;

        b(ThreeLeafCardData threeLeafCardData) {
            this.b = new WeakReference<>(threeLeafCardData);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ThreeLeafCardData threeLeafCardData = this.b.get();
            if (threeLeafCardData == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "card is null");
            } else {
                LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "three leaf small card has shown");
                threeLeafCardData.j(1);
            }
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        ThreeLeafCardViewHolder threeLeafCardViewHolder = new ThreeLeafCardViewHolder(viewGroup, this.o, false);
        this.an = threeLeafCardViewHolder;
        dir_(threeLeafCardViewHolder.getHomeStepCardLayout());
        f();
        d("threeLeafCard");
        a();
        ad();
        refreshCardData();
        return this.an;
    }

    protected void f() {
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "initThreeCircleIcon mThreeLeafCardViewHolder is null");
            return;
        }
        ImageView threeLeafIcon = threeLeafCardViewHolder.getThreeLeafIcon();
        if (threeLeafIcon != null) {
            threeLeafIcon.setBackground(ContextCompat.getDrawable(this.i, R.drawable._2131430650_res_0x7f0b0cfa));
            jcf.bEz_(this.an.die_(), nsf.h(R.string.IDS_three_circle_title));
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshCardData");
        super.refreshCardData();
        j(1);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (this.f) {
            this.f = false;
        } else {
            this.j = true;
            refreshCardData();
        }
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void a(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onBindViewHolder");
        if (this.f || !this.j || this.an == null) {
            return;
        }
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "try to refreshStepStrenActivityWidthPressure");
        this.an.i();
        this.j = false;
    }

    private void aa() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sync_cloud_data_action");
        c cVar = new c(this);
        this.ak = cVar;
        BroadcastManagerUtil.bFz_(this.i, cVar, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final int i) {
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "mThreeLeafCardViewHolder == null");
            return;
        }
        final Clover l = threeLeafCardViewHolder.l();
        if (l == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "clover not init.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: oux
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeLeafCardData.this.b(i, l);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, Clover clover) {
        dsl.a(DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault()), new e(this, i, clover));
    }

    static class e implements ResponseCallback<List<HealthLifeTaskBean>> {
        private int c;
        private WeakReference<Clover> d;
        private WeakReference<ThreeLeafCardData> e;

        e(ThreeLeafCardData threeLeafCardData, int i, Clover clover) {
            this.e = new WeakReference<>(threeLeafCardData);
            this.c = i;
            this.d = new WeakReference<>(clover);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HealthLifeTaskBean> list) {
            WeakReference<ThreeLeafCardData> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "GetHealthLifeRecordsCallback onResponse mCardDataReference is null");
                return;
            }
            ThreeLeafCardData threeLeafCardData = weakReference.get();
            if (threeLeafCardData != null) {
                threeLeafCardData.q = list;
                WeakReference<Clover> weakReference2 = this.d;
                if (weakReference2 != null) {
                    threeLeafCardData.a(this.c, weakReference2.get(), threeLeafCardData.h(list));
                    return;
                } else {
                    LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "GetHealthLifeRecordsCallback onResponse mCloverReference is null");
                    return;
                }
            }
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "GetHealthLifeRecordsCallback onResponse cardData is null");
        }
    }

    public static class a implements ResponseCallback<dsa> {
        private WeakReference<ThreeLeafCardData> b;
        private List<HealthLifeTaskBean> e;

        a(ThreeLeafCardData threeLeafCardData, List<HealthLifeTaskBean> list) {
            this.b = new WeakReference<>(threeLeafCardData);
            this.e = list;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, final dsa dsaVar) {
            final ThreeLeafCardData threeLeafCardData = this.b.get();
            if (threeLeafCardData == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "threeLeafCardData is null");
            } else {
                HandlerExecutor.a(new Runnable() { // from class: ovh
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThreeLeafCardData.a.this.b(threeLeafCardData, dsaVar);
                    }
                });
            }
        }

        public /* synthetic */ void b(ThreeLeafCardData threeLeafCardData, dsa dsaVar) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "update message");
            threeLeafCardData.c(this.e, dsaVar);
            dsl.e((List<HealthLifeBean>) threeLeafCardData.h(this.e));
            ObserverManagerUtil.c("HEALTH_LIFE_DAY_RECORD", this.e);
        }
    }

    private void m(List<HealthLifeTaskBean> list) {
        if (!this.y.get() || "latestDataDownload".equals(this.k)) {
            i(list);
            boolean j = BaseApplication.j();
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "sendDayRecord mIsHomeFragmentVisible:", Boolean.valueOf(this.v), " isRunningForeground:", Boolean.valueOf(j));
            if ("threeLeafCard".equals(oun.a()) && j && this.v) {
                if (koq.c(c(list))) {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "current day has complete tasks");
                    l(list);
                    return;
                } else {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "current day do not have complete tasks");
                    c(list, (dsa) null);
                    dsl.e(h(list));
                    ObserverManagerUtil.c("HEALTH_LIFE_DAY_RECORD", list);
                    return;
                }
            }
            ObserverManagerUtil.c("HEALTH_LIFE_DAY_RECORD", list);
        }
    }

    private void l(final List<HealthLifeTaskBean> list) {
        if (!HandlerExecutor.b()) {
            dsl.c(this.am, new a(this, list));
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ovb
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeLeafCardData.this.e(list);
                }
            });
        }
    }

    public /* synthetic */ void e(List list) {
        dsl.c(this.am, new a(this, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HealthLifeTaskBean> list, dsa dsaVar) {
        final ArrayList arrayList = new ArrayList();
        owc a2 = a(list, dsaVar);
        if (a2 != null) {
            arrayList.add(a2);
        } else {
            owc b2 = b(list);
            if (b2 != null) {
                arrayList.add(b2);
            }
        }
        owc g = g(list);
        if (g != null) {
            arrayList.add(g);
        }
        if (koq.c(arrayList)) {
            HandlerExecutor.d(new Runnable() { // from class: out
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeLeafCardData.this.d(arrayList);
                }
            }, this.f ? 2000L : 0L);
        }
    }

    public /* synthetic */ void d(List list) {
        this.an.b((List<owc>) list);
    }

    private owc a(List<HealthLifeTaskBean> list, dsa dsaVar) {
        if (dsaVar == null || koq.b(dsaVar.e()) || koq.b(list)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "there is no cons info");
            return null;
        }
        dsl.a(list, dsaVar);
        b(dsaVar, list);
        List<HealthLifeTaskBean> f = f(list);
        if (koq.b(f)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "there is no just-complete tasks");
            return null;
        }
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getConsCompleteMessage, diffList is ", f, " data is ", dsaVar.toString());
        return d(f, dsaVar);
    }

    private void b(dsa dsaVar, List<HealthLifeTaskBean> list) {
        List<HealthLifeStatistic> e2 = dsaVar.e();
        List<HealthLifeTaskBean> b2 = b(c(list), e2);
        if (koq.b(b2)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "small card no difflist");
            return;
        }
        HealthLifeTaskBean healthLifeTaskBean = b2.get(nsg.b(b2.size()));
        if (healthLifeTaskBean == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getCompleteMessage bean is null");
            return;
        }
        healthLifeTaskBean.getHealthLifeBean().setContinueDays(d(healthLifeTaskBean, e2));
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "notifySmallCardConsMessage, bean is ", healthLifeTaskBean.toString());
        Object[] objArr = new Object[4];
        objArr[0] = healthLifeTaskBean;
        objArr[1] = dsl.d(b2);
        objArr[2] = Integer.valueOf(dsaVar.c());
        objArr[3] = d(healthLifeTaskBean.getId(), 1, b2.size() == 1 ? 1 : 2, healthLifeTaskBean, 2);
        ObserverManagerUtil.c("HEALTH_LIFE_CONS_INFO", objArr);
    }

    private List<HealthLifeTaskBean> b(List<HealthLifeTaskBean> list, List<HealthLifeStatistic> list2) {
        List<Integer> c2 = dsl.c(this.am);
        ArrayList arrayList = new ArrayList();
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "shown ids ", c2);
        if (!koq.b(list)) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "completeList is ", list.toString(), " consInfo is ", list2.toString());
            for (HealthLifeTaskBean healthLifeTaskBean : list) {
                if (!c2.contains(Integer.valueOf(healthLifeTaskBean.getId()))) {
                    for (HealthLifeStatistic healthLifeStatistic : list2) {
                        if (healthLifeStatistic.getId() == healthLifeTaskBean.getId() && healthLifeStatistic.getConDays() >= 2) {
                            arrayList.add(healthLifeTaskBean);
                        }
                    }
                }
            }
            return arrayList;
        }
        LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "completeList is empty");
        return arrayList;
    }

    private List<HealthLifeTaskBean> e(List<HealthLifeTaskBean> list, dsa dsaVar) {
        if (koq.b(list) || dsaVar == null || koq.b(dsaVar.e())) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "nothing to fliter for big three leaf card");
            return null;
        }
        List<HealthLifeStatistic> e2 = dsaVar.e();
        ArrayList arrayList = new ArrayList();
        for (HealthLifeStatistic healthLifeStatistic : e2) {
            if (healthLifeStatistic.getConDays() >= 2) {
                Iterator<HealthLifeTaskBean> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        HealthLifeTaskBean next = it.next();
                        if (next.getId() == healthLifeStatistic.getId()) {
                            arrayList.add(next);
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private owc d(List<HealthLifeTaskBean> list, dsa dsaVar) {
        List<HealthLifeTaskBean> e2 = e(list, dsaVar);
        owc owcVar = null;
        if (koq.b(e2)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "no diffCons");
            return null;
        }
        int size = e2.size();
        HealthLifeTaskBean healthLifeTaskBean = e2.get(nsg.b(size));
        if (healthLifeTaskBean == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getConsCompleteMessage bean is null");
            return null;
        }
        int size2 = list.size();
        List<HealthLifeStatistic> e3 = dsaVar.e();
        String name = healthLifeTaskBean.getName();
        if (healthLifeTaskBean.getId() == 12) {
            name = nsf.h(R.string._2130846568_res_0x7f022368);
        }
        healthLifeTaskBean.getHealthLifeBean().setContinueDays(d(healthLifeTaskBean, e3));
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "current bean is ", healthLifeTaskBean.toString());
        if (size2 == 1) {
            if (size == 1) {
                owcVar = d(e3, healthLifeTaskBean, name, dsaVar.c());
            }
        } else if (size == size2) {
            owcVar = a(healthLifeTaskBean, size, name);
        } else if (size != 0) {
            if (!this.ae) {
                LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "already in three leaf card");
                owcVar = a(healthLifeTaskBean, size, name);
            } else {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "just from three circle card");
            }
        }
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getConsCompleteMessage diffCompleteCount ", Integer.valueOf(size2), " id  ", Integer.valueOf(healthLifeTaskBean.getId()), " message is :", owcVar);
        return owcVar;
    }

    private owc a(HealthLifeTaskBean healthLifeTaskBean, int i, String str) {
        owc b2 = b(healthLifeTaskBean, 2);
        if (LanguageUtil.h(this.i)) {
            c(b2, nsf.a(R.plurals._2130903387_res_0x7f03015b, i, UnitUtil.e(i, 1, 0)));
            a(b2, str, str, healthLifeTaskBean.getColorResourcesId());
        } else {
            String a2 = nsf.a(R.plurals._2130903392_res_0x7f030160, i, UnitUtil.e(i, 1, 0));
            a(b2, a2, a2, R.color._2131299236_res_0x7f090ba4);
        }
        return b2;
    }

    private owc d(List<HealthLifeStatistic> list, HealthLifeTaskBean healthLifeTaskBean, String str, int i) {
        owc b2 = b(healthLifeTaskBean, 1);
        int d2 = d(healthLifeTaskBean, list);
        if (d2 > i) {
            c(b2, nsf.a(R.plurals._2130903391_res_0x7f03015f, i, UnitUtil.e(d2, 1, 0)));
        } else {
            c(b2, nsf.a(R.plurals._2130903388_res_0x7f03015c, d2, UnitUtil.e(d2, 1, 0)));
        }
        a(b2, str, str, healthLifeTaskBean.getColorResourcesId());
        return b2;
    }

    private int d(HealthLifeTaskBean healthLifeTaskBean, List<HealthLifeStatistic> list) {
        for (HealthLifeStatistic healthLifeStatistic : list) {
            if (healthLifeTaskBean.getId() == healthLifeStatistic.getId()) {
                return healthLifeStatistic.getConDays();
            }
        }
        return 0;
    }

    private owc b(final HealthLifeTaskBean healthLifeTaskBean, final int i) {
        owc owcVar = new owc();
        owcVar.c(healthLifeTaskBean.getBackgroundColorResourcesId());
        owcVar.d(R.dimen._2131365062_res_0x7f0a0cc6);
        Drawable drawable = ContextCompat.getDrawable(this.i, healthLifeTaskBean.getIconId());
        if (this.x) {
            drawable = nrz.cKm_(this.i, drawable);
        }
        owcVar.diH_(drawable);
        owcVar.a(AnalyticsValue.HEALTH_LIFE_TASK_COMPLETE_2119097.value());
        final int id = healthLifeTaskBean.getId();
        owcVar.c(d(id, 0, i, healthLifeTaskBean, 1));
        owcVar.diG_(new View.OnClickListener() { // from class: ova
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeLeafCardData.this.dik_(id, i, healthLifeTaskBean, view);
            }
        });
        return owcVar;
    }

    public /* synthetic */ void dik_(int i, int i2, HealthLifeTaskBean healthLifeTaskBean, View view) {
        this.aa = false;
        ixx.d().d(this.i, AnalyticsValue.HEALTH_LIFE_TASK_COMPLETE_2119097.value(), d(i, 1, i2, healthLifeTaskBean, 1), 0);
        dsl.ZK_(this.o, Uri.parse("").buildUpon().appendQueryParameter("from", "cloverLifeTaskComplete").build());
        ViewClickInstrumentation.clickOnView(view);
    }

    private owc b(List<HealthLifeTaskBean> list) {
        owc owcVar;
        List<HealthLifeTaskBean> f = f(list);
        if (koq.b(f)) {
            return null;
        }
        int size = f.size();
        HealthLifeTaskBean healthLifeTaskBean = f.get(nsg.b(size));
        if (healthLifeTaskBean == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getCompleteMessage bean is null");
            return null;
        }
        new owc();
        String name = healthLifeTaskBean.getName();
        if (size == 1) {
            owcVar = b(healthLifeTaskBean, 1);
            c(owcVar, nsf.h(R.string._2130846538_res_0x7f02234a));
            a(owcVar, name, name, healthLifeTaskBean.getColorResourcesId());
        } else {
            owc b2 = b(healthLifeTaskBean, 2);
            String e2 = UnitUtil.e(size, 1, 0);
            if (this.u) {
                a(b2, nsf.a(R.plurals._2130903457_res_0x7f0301a1, size, name, e2), name, healthLifeTaskBean.getColorResourcesId());
            } else {
                SpannableString spannableString = new SpannableString(nsf.a(R.plurals._2130903384_res_0x7f030158, size, e2));
                nsi.cKI_(spannableString, spannableString.toString(), R.color._2131299236_res_0x7f090ba4);
                nsi.cKL_(spannableString, spannableString.toString(), R.string._2130851582_res_0x7f0236fe);
                b2.d(spannableString);
            }
            owcVar = b2;
        }
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getCompleteMessage diffCompleteCount ", Integer.valueOf(f.size()), " task  ", owcVar.c());
        return owcVar;
    }

    private void c(owc owcVar, String str) {
        SpannableString spannableString = new SpannableString(str);
        nsi.cKI_(spannableString, spannableString.toString(), R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, spannableString.toString(), R.string._2130851582_res_0x7f0236fe);
        owcVar.a(spannableString);
    }

    private void a(owc owcVar, String str, String str2, int i) {
        SpannableString spannableString = new SpannableString(str);
        nsi.cKI_(spannableString, spannableString.toString(), R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, spannableString.toString(), R.string._2130851582_res_0x7f0236fe);
        nsi.cKI_(spannableString, str2, i);
        nsi.cKL_(spannableString, str2, R.string._2130851581_res_0x7f0236fd);
        owcVar.d(spannableString);
    }

    private List<HealthLifeTaskBean> f(List<HealthLifeTaskBean> list) {
        List<HealthLifeTaskBean> c2 = c(list);
        if (koq.b(c2)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "completeList is empty");
            return null;
        }
        List<HealthLifeTaskBean> c3 = c(dsl.f());
        int size = c2.size();
        int size2 = c3.size();
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getCompleteMessage completeCount ", Integer.valueOf(size), " lastCompleteCount ", Integer.valueOf(size2));
        if (size <= size2) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (HealthLifeTaskBean healthLifeTaskBean : c3) {
            if (healthLifeTaskBean != null) {
                hashMap.put(Integer.valueOf(healthLifeTaskBean.getId()), healthLifeTaskBean);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (HealthLifeTaskBean healthLifeTaskBean2 : c2) {
            if (healthLifeTaskBean2 != null && !hashMap.containsKey(Integer.valueOf(healthLifeTaskBean2.getId()))) {
                arrayList.add(healthLifeTaskBean2);
            }
        }
        return arrayList;
    }

    private owc g(List<HealthLifeTaskBean> list) {
        HealthLifeTaskBean healthLifeTaskBean;
        if (koq.b(list) || !this.aa) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getReminderMessage list ", list, " mIsSupportShowReminder ", Boolean.valueOf(this.aa));
            return null;
        }
        this.aa = false;
        Pair<Integer, String> b2 = dsl.b(h(j(list)));
        if (b2 == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getReminderMessage pair is null");
            return null;
        }
        final int intValue = b2.first.intValue();
        String str = b2.second;
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "getReminderMessage id ", Integer.valueOf(intValue), " tip ", str);
        if (intValue <= 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<HealthLifeTaskBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                healthLifeTaskBean = null;
                break;
            }
            healthLifeTaskBean = it.next();
            if (healthLifeTaskBean != null && healthLifeTaskBean.getId() == intValue) {
                break;
            }
        }
        if (healthLifeTaskBean == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getReminderMessage bean is null");
            return null;
        }
        owc owcVar = new owc();
        owcVar.c(healthLifeTaskBean.getBackgroundColorResourcesId());
        owcVar.d(R.dimen._2131365062_res_0x7f0a0cc6);
        Drawable drawable = ContextCompat.getDrawable(this.i, healthLifeTaskBean.getIconId());
        if (this.x) {
            drawable = nrz.cKm_(this.i, drawable);
        }
        owcVar.diH_(drawable);
        SpannableString spannableString = new SpannableString(str);
        nsi.cKI_(spannableString, spannableString.toString(), R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, spannableString.toString(), R.string._2130851582_res_0x7f0236fe);
        owcVar.d(spannableString);
        Drawable drawable2 = ContextCompat.getDrawable(this.i, R.drawable._2131427554_res_0x7f0b00e2);
        if (drawable2 != null) {
            drawable2 = nrf.cJH_(drawable2, ContextCompat.getColor(this.i, R.color._2131296685_res_0x7f0901ad));
        }
        if (this.x) {
            drawable2 = nrz.cKm_(this.i, drawable2);
        }
        owcVar.diI_(drawable2);
        owcVar.a(AnalyticsValue.HEALTH_LIFE_TASK_REMINDER_2119096.value());
        owcVar.c(a(intValue, 0));
        owcVar.diG_(new View.OnClickListener() { // from class: ouy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeLeafCardData.this.dil_(intValue, view);
            }
        });
        return owcVar;
    }

    public /* synthetic */ void dil_(int i, View view) {
        ixx.d().d(this.i, AnalyticsValue.HEALTH_LIFE_TASK_REMINDER_2119096.value(), a(i, 1), 0);
        if (i == 5) {
            q();
            ViewClickInstrumentation.clickOnView(view);
        } else if (i == 6) {
            r();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.aa = false;
            dsl.ZK_(this.o, Uri.parse("").buildUpon().appendQueryParameter("id", String.valueOf(i)).appendQueryParameter("from", "cloverLifeTaskReminder").build());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private List<HealthLifeTaskBean> c(List<HealthLifeTaskBean> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (HealthLifeTaskBean healthLifeTaskBean : list) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getComplete() > 0) {
                arrayList.add(healthLifeTaskBean);
            }
        }
        return arrayList;
    }

    private List<HealthLifeTaskBean> j(List<HealthLifeTaskBean> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (HealthLifeTaskBean healthLifeTaskBean : list) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getComplete() <= 0) {
                arrayList.add(healthLifeTaskBean);
            }
        }
        return arrayList;
    }

    private void i(final List<HealthLifeTaskBean> list) {
        if (this.an == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "refreshBottomData mThreeLeafCardViewHolder null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "refreshBottomData list ", list);
            return;
        }
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: ovd
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeLeafCardData.this.a(list);
                }
            });
            return;
        }
        int size = c(list).size();
        int size2 = list.size();
        this.an.updateBottomData(size, size2);
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshBottomData mTotalCount ", Integer.valueOf(this.al), " totalCount ", Integer.valueOf(size2), " mCompleteCount ", Integer.valueOf(this.m), " completeCount ", Integer.valueOf(size));
        if (this.al != size2 || this.m != size) {
            dsl.t();
        }
        this.al = size2;
        this.m = size;
    }

    public /* synthetic */ void a(List list) {
        i((List<HealthLifeTaskBean>) list);
    }

    public void a(final int i, final Clover clover, final List<HealthLifeBean> list) {
        if (clover == null || list == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "params is null");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: ouu
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeLeafCardData.this.e(list, i, clover);
                }
            });
        }
    }

    public /* synthetic */ void e(List list, int i, Clover clover) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refresh data size:", Integer.valueOf(list.size()));
        int b2 = dsl.b((List<HealthLifeBean>) list, 1);
        if (this.l != b2) {
            this.l = b2;
            e(this.af, this.n, this.p, this.t);
            this.an.refreshDistanceCalFloorMargin(this.r, this.ai, this.aj, this.l == 2);
        }
        b(this.l);
        this.an.refreshStepStrenActivityWidth();
        b(i, clover, list);
    }

    private void b(int i, Clover clover, List<HealthLifeBean> list) {
        float d2 = dsl.d(list, 1);
        float d3 = dsl.d(list, 2);
        float d4 = dsl.d(list, 3);
        boolean d5 = dsl.d(list, d2, d4, d3);
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "isSame top:", Float.valueOf(d2), " right:", Float.valueOf(d3), " left:", Float.valueOf(d4), " isPerfect:", Boolean.valueOf(d5));
        m(this.q);
        if (c(d2, d3, d4, d5)) {
            return;
        }
        this.ab = d5;
        this.ac = d2;
        this.ad = d3;
        this.z = d4;
        jfa.b(dsm.c, "perfect_three_leaf", d5 ? System.currentTimeMillis() : 0L);
        if (i == 2) {
            c(clover, d2, d4, d3, d5);
        } else {
            e(clover, d2, d4, d3, d5);
        }
        e(a(d2, d3, d4), d5);
    }

    private void e(Clover clover, List<HealthLifeTaskBean> list) {
        if (clover == null || koq.b(list)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "params is null");
            return;
        }
        List<HealthLifeBean> h = h(list);
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshUiWithAnimation size:", Integer.valueOf(h.size()));
        float d2 = dsl.d(h, 1);
        float d3 = dsl.d(h, 2);
        float d4 = dsl.d(h, 3);
        boolean d5 = dsl.d(h, d2, d4, d3);
        boolean a2 = a(d2, d3, d4);
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "refreshUiWithAnimation isSame top:", Float.valueOf(d2), " right:", Float.valueOf(d3), " left:", Float.valueOf(d4), " isPerfect:", Boolean.valueOf(d5), " isVibrancy:", Boolean.valueOf(a2));
        this.ab = d5;
        this.ac = d2;
        this.ad = d3;
        this.z = d4;
        if (d5) {
            jfa.b(dsm.c, "perfect_three_leaf", System.currentTimeMillis());
        }
        e(a2, d5);
    }

    private boolean c(float f, float f2, float f3, boolean z) {
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "mLastTop:", Float.valueOf(this.ac), " mLastRight:", Float.valueOf(this.ad), " mLastLeft:", Float.valueOf(this.z), " mLastIsPerfect:", Boolean.valueOf(this.ab));
        return Float.compare(this.ac, f) == 0 && Float.compare(this.ad, f2) == 0 && Float.compare(this.z, f3) == 0 && this.ab == z;
    }

    private void e(boolean z, boolean z2) {
        Context context = this.o;
        if (context == null || context != BaseApplication.wa_()) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "checkShowFinishTargetAnimation mainActivity is not show");
            return;
        }
        if (!dsl.c("healthLifeThreeLeafSwitch", true)) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "animation switch is closed");
        } else if (z2) {
            this.h.dhA_(AchieveTaskHandler.AchieveAniType.THREE_LEAF_PERFECT, new Bundle());
        } else if (z) {
            this.h.dhA_(AchieveTaskHandler.AchieveAniType.THREE_LEAF_ACTIVE, new Bundle());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HealthLifeBean> h(List<HealthLifeTaskBean> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        Iterator<HealthLifeTaskBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getHealthLifeBean());
        }
        return arrayList;
    }

    private void ad() {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "initThreeLeafView.");
        Clover l = this.an.l();
        if (l == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "clover not init.");
            return;
        }
        l.setMaxOutsideScale(1.02f);
        l.setCloverWithIcon(0.0f, 0.0f, 0.0f);
        List<HealthLifeTaskBean> e2 = dsl.e();
        this.q = e2;
        List<HealthLifeBean> h = h(e2);
        if (koq.c(h)) {
            HealthLifeBean healthLifeBean = h.get(0);
            int c2 = DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault());
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "recordDay:", Integer.valueOf(healthLifeBean.getRecordDay()), " currentDay:", Integer.valueOf(c2));
            if (healthLifeBean.getRecordDay() == c2) {
                i(this.q);
            } else {
                this.q = new ArrayList();
                h = new ArrayList<>();
            }
        }
        this.ac = dsl.d(h, 1);
        this.ad = dsl.d(h, 2);
        float d2 = dsl.d(h, 3);
        this.z = d2;
        boolean d3 = dsl.d(h, this.ac, d2, this.ad);
        this.ab = d3;
        if (d3) {
            jfa.b(dsm.c, "perfect_three_leaf", System.currentTimeMillis());
        }
        b(l, this.ac, this.z, this.ad, this.ab);
        int b2 = dsl.b(h, 1);
        this.l = b2;
        b(b2);
    }

    private void b(Clover clover, float f, float f2, float f3, boolean z) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "showCloverWithoutAnimation top:", Float.valueOf(f), " left:", Float.valueOf(f2), " right:", Float.valueOf(f3), " isPerfect:", Boolean.valueOf(z));
        e(clover, z);
        clover.setCloverWithIcon(f, f2, f3);
    }

    private void e(final Clover clover, final float f, final float f2, final float f3, boolean z) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "growOneClover top:", Float.valueOf(f), " left:", Float.valueOf(f2), " right:", Float.valueOf(f3), " isPerfect:", Boolean.valueOf(z));
        e(clover, z);
        if (!"threeLeafCard".equals(oun.a())) {
            clover.setCloverWithIcon(f, f2, f3);
            return;
        }
        if (Float.compare(f, 1.0f) == 0 && Float.compare(f2, 1.0f) == 0 && Float.compare(f3, 1.0f) == 0) {
            clover.setCloverRunAnimator(0.999f, f2, f3);
        } else {
            clover.setCloverRunAnimator(f, f2, f3);
        }
        clover.postDelayed(new Runnable() { // from class: ouv
            @Override // java.lang.Runnable
            public final void run() {
                Clover.this.setCloverWithIcon(f, f2, f3);
            }
        }, 550L);
    }

    private void c(Clover clover, float f, float f2, float f3, boolean z) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "growAllClover top:", Float.valueOf(f), " left:", Float.valueOf(f2), " right:", Float.valueOf(f3), " isPerfect:", Boolean.valueOf(z));
        e(clover, z);
        if (!"threeLeafCard".equals(oun.a())) {
            clover.setCloverWithIcon(f, f2, f3);
        } else {
            clover.setCloverRunGrowAnimator(f, f2, f3);
            clover.setAnimEndListener(new AddFrameListener() { // from class: ovc
                @Override // com.huawei.ucd.cloveranimation.AddFrameListener
                public final void onFinished() {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "growAllClover finished.");
                }
            });
        }
    }

    private void e(Clover clover, boolean z) {
        if (clover.getVisibility() != 0) {
            clover.setVisibility(0);
        }
        clover.d();
        clover.setPlayAllFrameAnimation(true);
        clover.setAllFull(z);
    }

    private void b(int i) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "bindCardData.");
        if (this.an == null) {
            return;
        }
        this.an.c((dsl.n() || koq.c(this.q)) ? 0 : 8);
        ai();
        if (i == 2) {
            this.an.d(R.string.IDS_settings_steps, this.af, R.plurals._2130903359_res_0x7f03013f);
        } else {
            this.an.setFitnessDataOriginIcon2Visible(8);
            this.an.d(R.string._2130845495_res_0x7f021f37, this.t, R.plurals.IDS_single_circle_intensity_target_desc);
        }
        ac();
    }

    private void ai() {
        HealthLifeTaskBean c2 = c(6);
        if (c2 == null || c2.getHealthLifeBean() == null) {
            this.an.c(R.string.IDS_hw_show_main_home_page_sleep, R.string._2130837597_res_0x7f02005d, true);
            return;
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "wake up:", healthLifeBean.getResult(), " ", Integer.valueOf(healthLifeBean.getRecordDay()), " complete:", Integer.valueOf(healthLifeBean.getComplete()));
        String d2 = dsl.d(healthLifeBean.getResult());
        if (healthLifeBean.getComplete() == 3) {
            this.an.c(R.string.IDS_hw_show_main_home_page_sleep, R.string._2130848720_res_0x7f022bd0, false);
        } else if (TextUtils.isEmpty(d2)) {
            this.an.c(R.string.IDS_hw_show_main_home_page_sleep, R.string._2130837597_res_0x7f02005d, true);
        } else {
            this.an.e(R.string.IDS_hw_show_main_home_page_sleep, UnitUtil.c(dsl.c(d2), 129), R.string._2130847197_res_0x7f0225dd);
        }
    }

    private void ac() {
        HealthLifeTaskBean c2 = c(5);
        if (c2 == null || c2.getHealthLifeBean() == null) {
            this.an.a(R.string._2130845995_res_0x7f02212b);
            this.an.b(R.string._2130845665_res_0x7f021fe1);
            return;
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        if (dsl.b(healthLifeBean)) {
            eyw eywVar = (eyw) HiJsonUtil.e(healthLifeBean.getExtendInfo(), eyw.class);
            int a2 = eywVar.a();
            this.an.d(R.string._2130845995_res_0x7f02212b, R.string.IDS_settings_one_level_menu_settings_item_text_id14, UnitUtil.e(a2, 1, 0), sdh.e(a2), dsl.b(eywVar.e()));
            return;
        }
        if (CommonUtil.h(healthLifeBean.getResult()) == 0) {
            this.an.a(R.string._2130845995_res_0x7f02212b);
            this.an.b(R.string._2130845665_res_0x7f021fe1);
        } else {
            this.an.a(R.string._2130845995_res_0x7f02212b);
            int h = CommonUtil.h(healthLifeBean.getResult());
            this.an.d(h, nsf.a(R.plurals._2130903361_res_0x7f030141, h, ""));
        }
    }

    private void i(int i) {
        this.af = i;
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "updateStepText null.");
        } else if (this.l == 2) {
            threeLeafCardViewHolder.d(R.string.IDS_settings_steps, i, R.plurals._2130903359_res_0x7f03013f);
        } else {
            threeLeafCardViewHolder.bindThreeCircleFloorLayout(2, i);
            this.aj = UnitUtil.e(this.af, 1, 0);
        }
    }

    private void h(int i) {
        this.t = i;
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "updateIntensityText null.");
        } else if (this.l == 2) {
            threeLeafCardViewHolder.bindThreeCircleFloorLayout(3, i);
            this.aj = UnitUtil.e(this.t, 1, 0);
        } else {
            threeLeafCardViewHolder.d(R.string._2130845495_res_0x7f021f37, i, R.plurals.IDS_single_circle_intensity_target_desc);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void g_() {
        super.g_();
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            ReleaseLogUtil.d("HealthLife_SCUI_ThreeLeafCardData", "handleMessageWhenReferenceNotNull viewHolder is null");
        } else {
            threeLeafCardViewHolder.setFitnessDataOriginIcon2Visible(this.l == 2 ? 0 : 8);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void a(int i, int[] iArr, int[] iArr2) {
        super.a(i, iArr, iArr2);
        b(iArr);
    }

    private void b(int[] iArr) {
        boolean z;
        int c2 = DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault());
        if (this.am != c2) {
            this.am = c2;
            j(2);
            z = true;
        } else {
            z = false;
        }
        int i = iArr[3];
        int i2 = iArr[0];
        int i3 = iArr[4];
        int i4 = iArr[1];
        String str = this.af + "," + this.n + "," + this.p + "," + this.t;
        String str2 = i + "," + i2 + "," + i3 + "," + i4;
        boolean z2 = this.w != UnitUtil.h();
        if (str2.equals(str) && !z2) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "newData:", str2, " no need refresh.");
            return;
        }
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "newData:", str2, " lastData:", str, " todayDate:", Integer.valueOf(this.am));
        this.w = UnitUtil.h();
        e(i, i2, i3, i4);
        if (str2.length() != str.length() || z2) {
            ab();
        }
        if (z) {
            return;
        }
        ae();
    }

    private void e(int i, int i2, int i3, int i4) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "updateRealSportData tempStep:", Integer.valueOf(i), " tempCalories:", Integer.valueOf(i2), " tempDistance:", Integer.valueOf(i3), " tempIntensity:", Integer.valueOf(i4));
        if (i < 0) {
            ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
            if (threeLeafCardViewHolder != null) {
                threeLeafCardViewHolder.setFitnessDataOriginIcon2Visible(8);
            }
            i = 0;
        }
        i(i);
        if (i4 < 0) {
            i4 = 0;
        }
        h(i4);
        if (i2 >= 0) {
            this.n = i2;
            this.ai = UnitUtil.e(Math.round(i2 * 0.001f), 1, 0);
        }
        if (i3 >= 0) {
            this.p = i3;
        }
    }

    private void ab() {
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder != null) {
            threeLeafCardViewHolder.refreshStepStrenActivityWidth();
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshLayoutMargin mFirst:", this.r, " mSecond:", this.ai, " mThird:", this.aj);
            this.an.refreshDistanceCalFloorMargin(this.r, this.ai, this.aj, this.l == 2);
        }
    }

    private void ae() {
        HealthLifeTaskBean c2 = c(this.l);
        if (c2 == null || c2.getHealthLifeBean() == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "updateSportTask null.");
            return;
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        if (a(healthLifeBean)) {
            jfa.f(dsm.c, "step_intensity_bean", nrv.a(healthLifeBean));
            ObserverManagerUtil.c("HOME_PAGE_SPORT_TASK_UPDATE", healthLifeBean);
            ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
            if (threeLeafCardViewHolder == null || threeLeafCardViewHolder.l() == null) {
                LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "not init finish.");
            } else {
                b(1, this.an.l(), h(this.q));
            }
        }
    }

    private boolean a(HealthLifeBean healthLifeBean) {
        int i = this.l == 2 ? this.af : this.t;
        int complete = healthLifeBean.getComplete();
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshSportTask target:", healthLifeBean.getTarget(), " curValue:", Integer.valueOf(i), " complete:", Integer.valueOf(complete), " mStepInThreeLeaf:", Integer.valueOf(this.ah));
        if (complete <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(1);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            healthLifeBean.setSyncStatus(0);
        } else if (i != CommonUtil.h(healthLifeBean.getResult())) {
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setSyncStatus(0);
        } else {
            LogUtil.c("HealthLife_SCUI_ThreeLeafCardData", "no need refreshSportTask");
        }
        int h = CommonUtil.h(healthLifeBean.getResult());
        healthLifeBean.setResult(Integer.toString(i));
        return e(healthLifeBean, h, complete);
    }

    private boolean e(HealthLifeBean healthLifeBean, int i, int i2) {
        boolean z = true;
        if (this.l == 2) {
            if (this.af - this.ah <= 50 && ((i2 > 0 || healthLifeBean.getComplete() <= 0) && (i != 0 || this.af <= 0))) {
                z = false;
            }
            if (Math.abs(this.af - this.ah) > 50) {
                this.ah = this.af;
            }
        }
        return z;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        HealthModelApi c2 = dsl.c();
        if (view == null || c2 == null) {
            LogUtil.b("HealthLife_SCUI_ThreeLeafCardData", "onClick view == null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "onClick view ", view);
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.stepLayout) {
            r();
        } else if (id == R.id.time_strength_layout) {
            w();
        } else if (id == R.id.activityLayout) {
            q();
        } else if (id == R.id.three_circle_layout_container || id == R.id.three_leaf_default_layout || id == R.id.three_leaf_default_layout_inflated) {
            b("cloverLife");
        } else if (id == R.id.bottom_step_layout || id == R.id.bottom_step_layout_2) {
            b("HOME_HEALTH_LIFE_COMPLETE_TODAY");
        } else if (id == R.id.hw_health_fitness_data_origin_icon2_layout && this.an.getFitnessDataOriginIcon() != null) {
            o();
        } else {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "click others.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(String str) {
        this.aa = false;
        dsl.ZK_(this.o, Uri.parse("").buildUpon().appendQueryParameter("from", str).build());
    }

    private void r() {
        HealthLifeTaskBean c2 = c(6);
        if (c2 == null) {
            c(3, false, false);
            dsl.d(this.o, (HealthLifeTaskBean) null, "inputSleepDialog", (ResponseCallback<HealthLifeTaskBean>) null);
            return;
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        if (healthLifeBean == null) {
            c(3, false, false);
            dsl.d(this.o, c2, "inputSleepDialog", (ResponseCallback<HealthLifeTaskBean>) null);
            return;
        }
        c(3, healthLifeBean.getComplete() > 0, false);
        if (TextUtils.isEmpty(dsl.d(healthLifeBean.getResult()))) {
            dsl.d(this.o, c2, "inputSleepDialog", (ResponseCallback<HealthLifeTaskBean>) null);
            return;
        }
        Intent intent = new Intent(this.i, (Class<?>) KnitSleepDetailActivity.class);
        intent.putExtra("from", 2);
        gnm.aPB_(this.o, intent);
    }

    private void w() {
        if (this.l == 2) {
            t();
            return;
        }
        HealthLifeTaskBean c2 = c(3);
        if (c2 == null) {
            ReleaseLogUtil.d("R_HealthLife_SCUI_ThreeLeafCardData", "clickStepOrIntensityArea intensityTask is null mBaseSportTaskId ", Integer.valueOf(this.l));
        } else if (c2.getComplete() > 0) {
            s();
        } else {
            dsl.d(this.o, c2, "cloverLife", (ResponseCallback<HealthLifeTaskBean>) new ResponseCallback() { // from class: ouw
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    ThreeLeafCardData.this.d(i, (HealthLifeTaskBean) obj);
                }
            });
        }
    }

    public /* synthetic */ void d(int i, HealthLifeTaskBean healthLifeTaskBean) {
        if (i == 3) {
            j(1);
        }
    }

    private void q() {
        HealthLifeTaskBean c2 = c(5);
        if (c2 == null) {
            c(2, false, false);
            bzs.e().loadH5ProApp(this.o, "com.huawei.health.h5.breath-training", null);
            return;
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        boolean b2 = dsl.b(healthLifeBean);
        c(2, c2.getComplete() > 0, b2);
        if (!b2 && CommonUtils.h(healthLifeBean.getResult()) <= 0) {
            bzs.e().loadH5ProApp(this.o, "com.huawei.health.h5.breath-training", null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type", 2);
        qmf.dFD_(this.o, bundle);
    }

    private HealthLifeTaskBean c(int i) {
        List<HealthLifeTaskBean> list = this.q;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "getHealthLifeTaskById list ", list);
            return null;
        }
        for (HealthLifeTaskBean healthLifeTaskBean : list) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getHealthLifeBean() != null && healthLifeTaskBean.getId() == i) {
                return healthLifeTaskBean;
            }
        }
        return null;
    }

    private HealthLifeBean d(int i) {
        HealthLifeTaskBean c2 = c(dsl.b(h(this.q), i));
        if (c2 == null) {
            return new HealthLifeBean();
        }
        HealthLifeBean healthLifeBean = c2.getHealthLifeBean();
        return healthLifeBean == null ? new HealthLifeBean() : healthLifeBean;
    }

    private void c(int i, boolean z, boolean z2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        if (i == 2) {
            hashMap.put("type", "moods");
            hashMap.put("hasStressData", Boolean.valueOf(z2));
        } else if (i == 3) {
            hashMap.put("type", "sleep");
        } else {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "setBiEventForClick type ", Integer.valueOf(i));
        }
        hashMap.put("cloverCardStatus", Integer.valueOf(!z ? 1 : 0));
        ixx.d().d(this.i, AnalyticsValue.HEALTH_CLOVER_LIFE_2010119.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void e() {
        if ("threeLeafCard".equals(oun.a())) {
            u();
            v();
            y();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0043, code lost:
    
        if (r1 > 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0039, code lost:
    
        if (r1 > 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0047, code lost:
    
        r0 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void u() {
        /*
            r6 = this;
            r0 = 1
            com.huawei.health.healthmodel.bean.HealthLifeBean r1 = r6.d(r0)
            int r2 = r1.getId()
            java.lang.String r3 = r1.getTarget()
            int r3 = health.compact.a.CommonUtils.h(r3)
            if (r2 <= 0) goto L6c
            if (r3 > 0) goto L16
            goto L6c
        L16:
            java.util.HashMap r4 = new java.util.HashMap
            r5 = 3
            r4.<init>(r5)
            java.lang.String r5 = "click"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4.put(r5, r0)
            java.lang.String r0 = r1.getResult()
            int r0 = health.compact.a.CommonUtils.h(r0)
            r1 = 2
            java.lang.String r5 = "taskType"
            if (r2 != r1) goto L3c
            java.lang.String r1 = "walk"
            r4.put(r5, r1)
            int r1 = r6.af
            if (r1 <= 0) goto L47
            goto L45
        L3c:
            java.lang.String r1 = "MVPA"
            r4.put(r5, r1)
            int r1 = r6.t
            if (r1 <= 0) goto L47
        L45:
            float r0 = (float) r1
            goto L48
        L47:
            float r0 = (float) r0
        L48:
            float r1 = (float) r3
            float r0 = r0 / r1
            r1 = 1120403456(0x42c80000, float:100.0)
            float r0 = r0 * r1
            double r0 = (double) r0
            r2 = 0
            double r0 = health.compact.a.UnitUtil.a(r0, r2)
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            java.lang.String r1 = "taskFinishRate"
            r4.put(r1, r0)
            ixx r0 = defpackage.ixx.d()
            android.content.Context r1 = r6.i
            com.huawei.hwcommonmodel.constants.AnalyticsValue r3 = com.huawei.hwcommonmodel.constants.AnalyticsValue.HEALTH_EXIT_APP_MODE_COMPLETE_2010117
            java.lang.String r3 = r3.value()
            r0.d(r1, r3, r4, r2)
            return
        L6c:
            java.lang.String r0 = "exitBiForSport sport "
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "HealthLife_SCUI_ThreeLeafCardData"
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardData.u():void");
    }

    private void v() {
        HealthLifeBean d2 = d(2);
        if (d2.getId() <= 0) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "exitBiForMood mood ", d2);
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(ParsedFieldTag.TASK_TYPE, "moods");
        hashMap.put("taskFinishRate", Integer.valueOf(d2.getComplete() <= 0 ? 0 : 100));
        ixx.d().d(this.i, AnalyticsValue.HEALTH_EXIT_APP_MODE_COMPLETE_2010117.value(), hashMap, 0);
    }

    private void y() {
        HealthLifeBean d2 = d(3);
        if (d2.getId() <= 0) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "exitBiForSlumber slumber ", d2);
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(ParsedFieldTag.TASK_TYPE, "sleep");
        hashMap.put("taskFinishRate", Integer.valueOf(d2.getComplete() <= 0 ? 0 : 100));
        ixx.d().d(this.i, AnalyticsValue.HEALTH_EXIT_APP_MODE_COMPLETE_2010117.value(), hashMap, 0);
    }

    private void c(Map<String, Object> map, int i, int i2) {
        map.put("click", 1);
        map.put("planType", Integer.valueOf(dsl.h()));
        map.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(i));
        map.put("event", Integer.valueOf(i2));
    }

    private Map<String, Object> a(int i, int i2) {
        HashMap hashMap = new HashMap(4);
        c(hashMap, i, i2);
        return hashMap;
    }

    private Map<String, Object> d(int i, int i2, int i3, HealthLifeTaskBean healthLifeTaskBean, int i4) {
        HashMap hashMap = new HashMap(5);
        c(hashMap, i, i2);
        hashMap.put("reminderType", Integer.valueOf(i3));
        hashMap.put("from", Integer.valueOf(i4));
        hashMap.put("continuumDays", Integer.valueOf(healthLifeTaskBean.getHealthLifeBean().getContinueDays()));
        return hashMap;
    }

    protected void a() {
        Context context = this.o;
        View.OnClickListener cwZ_ = context instanceof BaseActivity ? nkx.cwZ_(this, (BaseActivity) context, true, "") : this;
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "BaseCardViewHolder is null");
            return;
        }
        RelativeLayout activityLayout = threeLeafCardViewHolder.getActivityLayout();
        if (activityLayout != null) {
            activityLayout.setOnClickListener(cwZ_);
        }
        ViewGroup stepLayout = this.an.getStepLayout();
        if (stepLayout != null) {
            stepLayout.setOnClickListener(cwZ_);
        }
        ViewGroup timeStrengthLayout = this.an.getTimeStrengthLayout();
        if (timeStrengthLayout != null) {
            timeStrengthLayout.setOnClickListener(cwZ_);
        }
        LinearLayout threeCycleLayoutContainer = this.an.getThreeCycleLayoutContainer();
        if (threeCycleLayoutContainer != null) {
            threeCycleLayoutContainer.setOnClickListener(cwZ_);
        }
        LinearLayout dim_ = this.an.dim_();
        if (dim_ != null) {
            dim_.setOnClickListener(cwZ_);
        }
        this.an.setBottomClickListener(cwZ_);
        m();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "oonConfigurationChanged");
        this.q = new ArrayList();
        this.p = -1;
        this.n = -1;
        this.af = 0;
        this.t = 0;
        super.onConfigurationChanged(configuration);
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onConfigurationChanged holder is null");
        } else {
            threeLeafCardViewHolder.updateLayout();
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public StepsBaseCardViewHolder c() {
        return this.an;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onDestroy");
        super.onDestroy();
        if (koq.c(this.ag)) {
            HiHealthNativeApi.a(this.i).unSubscribeHiHealthData(this.ag, new HiUnSubscribeListener() { // from class: ouz
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
        pit.a().b();
        BroadcastManagerUtil.bFJ_(this.i, this.ak);
        BroadcastManager.wx_(this.s);
        ObserverManagerUtil.e("HEALTH_MODEL_CARD_KEY_NEW");
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder != null) {
            threeLeafCardViewHolder.m();
        }
    }

    public void g() {
        this.an.l().setCloverCutValuesWithAnim(0.0f, 0.0f, 0.0f);
    }

    public void h() {
        int b2 = dsl.b(h(this.q), 1);
        this.l = b2;
        b(b2);
        e(this.af, this.n, this.p, this.t);
        ab();
        if (koq.b(this.q)) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "refreshUiForVisible mHealthLifeTaskList ", this.q);
            return;
        }
        List<HealthLifeBean> h = h(this.q);
        LogUtil.a("HealthLife_SCUI_ThreeLeafCardData", "refreshUiForVisible list ", h);
        float d2 = dsl.d(h, 1);
        float d3 = dsl.d(h, 2);
        float d4 = dsl.d(h, 3);
        boolean d5 = dsl.d(h, d2, d4, d3);
        ReleaseLogUtil.e("HealthLife_SCUI_ThreeLeafCardData", "refreshUiForVisible top:", Float.valueOf(d2), " right:", Float.valueOf(d3), " left:", Float.valueOf(d4), " isPerfect:", Boolean.valueOf(d5));
        this.ab = d5;
        this.ac = d2;
        this.ad = d3;
        this.z = d4;
        b(this.an.l(), d2, d4, d3, d5);
    }

    public void n() {
        e(this.an.l(), this.q);
    }

    public void j() {
        g();
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    protected void b() {
        this.v = true;
        this.an.d(false);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void b(String str, int i) {
        if ("threeLeafCard".equals(str) && this.g != null) {
            this.g.b(str, i);
            this.ae = false;
        }
        ThreeLeafCardViewHolder threeLeafCardViewHolder = this.an;
        if (threeLeafCardViewHolder == null) {
            LogUtil.h("HealthLife_SCUI_ThreeLeafCardData", "afterStepCardChange mThreeLeafCardViewHolder is null");
            return;
        }
        HealthTextView healthTextView = threeLeafCardViewHolder.u;
        if (healthTextView != null && healthTextView.getVisibility() == 0) {
            Context context = this.o;
            int b2 = DateFormatUtil.b(System.currentTimeMillis());
            SharedPreferenceManager.e(context, String.valueOf(10000), "SP_PERFECT_RED_POINT", String.valueOf(b2), new StorageParams());
        }
        if ("threeCircleCard".equals(str)) {
            this.ae = true;
            this.an.d(true);
        }
        this.an.updateMsgRedDotVisibility(8);
    }

    private void d(HiSubscribeListener hiSubscribeListener) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(200);
        arrayList.add(14);
        arrayList.add(2);
        arrayList.add(Integer.valueOf(HiSubscribeType.c));
        arrayList.add(3);
        arrayList.add(Integer.valueOf(HiSubscribeType.f4119a));
        arrayList.add(103);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()));
        HiHealthNativeApi.a(this.i).subscribeHiHealthData(arrayList, hiSubscribeListener);
    }

    public void d(boolean z) {
        if (c() instanceof ThreeLeafCardViewHolder) {
            this.v = z;
            c().updateLeafCardShowStatus(BaseApplication.j() && z);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "HealthLife_SCUI_ThreeLeafCardData";
    }
}
