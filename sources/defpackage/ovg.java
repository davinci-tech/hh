package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.ui.homehealth.stepscard.AchieveTaskHandler;
import com.huawei.ui.homehealth.stepscard.EmptyStepsCardData;
import com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData;
import com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardData;
import com.huawei.ui.homehealth.threecirclecard.TwoModelCardViewHolder;
import com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import defpackage.ovg;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class ovg extends EmptyStepsCardData {
    private static long e;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f15973a;
    private TwoModelCardViewHolder ad;
    private final int[] b;
    private int c;
    protected AchieveTaskHandler d;
    private Observer<HashMap<String, Integer>> f;
    private ExecutorService g;
    private final int[] h;
    private Handler i;
    private StepsViewModel j;
    private int k;
    private boolean l;
    private HealthOpenSDK m;
    private ouh n;
    private boolean o;
    private int p;
    private CommonSingleCallback<Boolean> q;
    private final com.huawei.haf.design.pattern.Observer r;
    private oun s;
    private b t;
    private Observer<Bundle> u;
    private oui v;
    private ThreeCircleCardData w;
    private String x;
    private com.huawei.haf.design.pattern.Observer y;
    private ThreeLeafCardData z;

    public static class d extends BaseHandler<ovg> {
        public d(ovg ovgVar) {
            super(ovgVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: diA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ovg ovgVar, Message message) {
            if (message == null) {
                ReleaseLogUtil.a("SCUI_TwoModelCardData", "msg == null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                if (ovgVar.w != null) {
                    ovgVar.w.a(ovgVar.c, ovgVar.b, ovgVar.h);
                }
                if (ovgVar.z != null) {
                    ovgVar.z.a(ovgVar.c, ovgVar.b, ovgVar.h);
                    return;
                }
                return;
            }
            if (i == 10) {
                ovgVar.j();
            } else {
                if (i != 11) {
                    return;
                }
                ovgVar.t();
            }
        }
    }

    /* synthetic */ void c(String str, final Object[] objArr) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 59925378) {
            if (str.equals("NOTIFY_STEP_CARD_CHANGED")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1178094076) {
            if (hashCode == 1991442356 && str.equals("HEALTH_LIFE_DAY_RECORD")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("HOME_FRAGMENT_IS_VISIBLE_TO_USER")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            LogUtil.a("SCUI_TwoModelCardData", "switch card", str);
            HandlerExecutor.e(new Runnable() { // from class: ovp
                @Override // java.lang.Runnable
                public final void run() {
                    ovg.this.b(objArr);
                }
            });
            return;
        }
        if (c != 1) {
            if (c == 2) {
                a(objArr);
                return;
            } else {
                LogUtil.h("SCUI_TwoModelCardData", "mObserver label ", str, " objects ", objArr);
                return;
            }
        }
        if (objArr == null || objArr.length <= 0) {
            ReleaseLogUtil.a("R_SCUI_TwoModelCardData", "mObserver objects ", objArr);
            return;
        }
        Object obj = objArr[0];
        if (!(obj instanceof Boolean)) {
            ReleaseLogUtil.a("R_SCUI_TwoModelCardData", "mObserver object ", obj);
            return;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        this.f15973a = booleanValue;
        LogUtil.a("SCUI_TwoModelCardData", "mObserver isHomeFragmentVisible = ", Boolean.valueOf(booleanValue));
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.d(this.f15973a);
        }
    }

    /* synthetic */ void b(Object[] objArr) {
        onResume();
        if (objArr == null || objArr.length <= 0) {
            LogUtil.h("SCUI_TwoModelCardData", "switch card objects is empty");
            this.p = 0;
            return;
        }
        Object obj = objArr[0];
        if (obj instanceof String) {
            String str = (String) obj;
            ThreeCircleCardData threeCircleCardData = this.w;
            if (threeCircleCardData != null) {
                threeCircleCardData.b(str, this.p);
            }
            ThreeLeafCardData threeLeafCardData = this.z;
            if (threeLeafCardData != null) {
                threeLeafCardData.b(str, this.p);
            }
        }
        this.p = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        HandlerExecutor.e(new Runnable() { // from class: ovt
            @Override // java.lang.Runnable
            public final void run() {
                ovg.this.i();
            }
        });
    }

    /* synthetic */ void i() {
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.a(UnitUtil.e(1.0d, 1, 0));
            this.z.e(0);
        }
    }

    private void a(final Object[] objArr) {
        HandlerExecutor.e(new Runnable() { // from class: ovq
            @Override // java.lang.Runnable
            public final void run() {
                ovg.this.c(objArr);
            }
        });
    }

    /* synthetic */ void c(Object[] objArr) {
        if (koq.b(objArr, 0)) {
            LogUtil.h("SCUI_TwoModelCardData", "observerForHealthLife objects ", objArr);
            return;
        }
        if (this.w == null) {
            LogUtil.h("SCUI_TwoModelCardData", "mThreeCircleCardData is null");
            return;
        }
        Object obj = objArr[0];
        int b2 = koq.e(obj, HealthLifeTaskBean.class) ? b((List<HealthLifeTaskBean>) obj) : 0;
        int b3 = b(dsl.f());
        LogUtil.a("SCUI_TwoModelCardData", "observerForHealthLife completeCount:", Integer.valueOf(b2), " lastCompleteCount:", Integer.valueOf(b3));
        if (b2 > b3) {
            int i = b2 - b3;
            this.p = i;
            ThreeCircleCardData threeCircleCardData = this.w;
            if (threeCircleCardData != null) {
                threeCircleCardData.a(UnitUtil.e(i, 1, 0));
                this.w.e(0);
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("reminderNum", Integer.valueOf(this.p));
            ixx.d().d(this.mContext, AnalyticsValue.HEALTH_LIFE_TASK_COMPLETE_2119095.value(), hashMap, 0);
            return;
        }
        this.p = 0;
        ThreeCircleCardData threeCircleCardData2 = this.w;
        if (threeCircleCardData2 != null) {
            threeCircleCardData2.e(8);
        }
    }

    private int b(List<HealthLifeTaskBean> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        for (HealthLifeTaskBean healthLifeTaskBean : list) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getComplete() > 0) {
                i++;
            }
        }
        return i;
    }

    public ovg(Context context, String str, oun ounVar) {
        super(context);
        this.d = new AchieveTaskHandler();
        this.u = new Observer() { // from class: com.huawei.ui.homehealth.threecirclecard.TwoModelCardData$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ovg.this.dit_((Bundle) obj);
            }
        };
        this.f = new Observer() { // from class: com.huawei.ui.homehealth.threecirclecard.TwoModelCardData$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ovg.this.c((HashMap<String, Integer>) obj);
            }
        };
        this.t = new b(this);
        this.n = null;
        this.o = true;
        this.l = false;
        this.k = 0;
        int[] iArr = {270, 25, 12, 10000};
        this.h = iArr;
        int[] iArr2 = {0, 0, 0, 0, 0, 0};
        this.b = iArr2;
        this.m = null;
        this.y = new com.huawei.haf.design.pattern.Observer() { // from class: ovg.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str2, Object... objArr) {
                LogUtil.a("SCUI_TwoModelCardData", "mSwitchModelObserver notify");
                if ("cloverLife".equals(str2)) {
                    if ("threeLeafCard".equals(oun.a())) {
                        LogUtil.a("SCUI_TwoModelCardData", "show three leaf already");
                    } else if ("threeCircleCard".equals(oun.a())) {
                        ovg.this.s.e("threeCircleCard", false);
                    }
                }
            }
        };
        com.huawei.haf.design.pattern.Observer observer = new com.huawei.haf.design.pattern.Observer() { // from class: ovv
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str2, Object[] objArr) {
                ovg.this.c(str2, objArr);
            }
        };
        this.r = observer;
        this.i = new d(this);
        owg.c(iArr2);
        owg.a(iArr);
        r();
        p();
        ThreeCircleCardData threeCircleCardData = new ThreeCircleCardData(context);
        this.w = threeCircleCardData;
        threeCircleCardData.e(ounVar);
        this.w.e(this.m);
        ThreeLeafCardData threeLeafCardData = new ThreeLeafCardData(context, this.d);
        this.z = threeLeafCardData;
        threeLeafCardData.e(ounVar);
        this.z.e(this.m);
        this.x = str;
        this.s = ounVar;
        ObserverManagerUtil.d(this.y, "cloverLife");
        e();
        ObserverManagerUtil.d(this.t, "showFitnessDataOriginDialog");
        ObserverManagerUtil.d(observer, "NOTIFY_STEP_CARD_CHANGED");
        ObserverManagerUtil.d(observer, "HOME_FRAGMENT_IS_VISIBLE_TO_USER");
        ObserverManagerUtil.d(observer, "HEALTH_LIFE_DAY_RECORD");
        ThreadPoolManager.d().execute(new Runnable() { // from class: ovw
            @Override // java.lang.Runnable
            public final void run() {
                ovg.this.b();
            }
        });
        this.d.c(this);
        HiBroadcastUtil.i(this.mContext);
        a();
    }

    /* synthetic */ void b() {
        ouf.e(this.mContext);
        ouf.a(this.mContext);
    }

    private void r() {
        if (this.j == null) {
            this.j = (StepsViewModel) new ViewModelProvider((ViewModelStoreOwner) this.mContext).get(StepsViewModel.class);
        }
        this.m = dss.c(this.mContext).d();
        this.j.e((LifecycleOwner) this.mContext, this.u);
        this.j.d((LifecycleOwner) this.mContext, this.f);
        this.j.e(this.m);
    }

    @Override // com.huawei.ui.homehealth.stepscard.EmptyStepsCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            LogUtil.b("SCUI_TwoModelCardData", "getCardViewHolder layoutInflater == null");
            layoutInflater = LayoutInflater.from(BaseApplication.getContext());
        }
        LayoutInflater layoutInflater2 = layoutInflater;
        LogUtil.a("SCUI_TwoModelCardData", "getCardViewHolder start");
        this.ad = new TwoModelCardViewHolder(layoutInflater2.inflate(R.layout.layout_two_model_card_container, viewGroup, false), this.mContext, false, this.x, this.w, this.z, layoutInflater2);
        refreshCardData();
        LogUtil.a("SCUI_TwoModelCardData", "getCardViewHolder end");
        return this.ad;
    }

    protected void a() {
        StepCounterSupportUtil.c(new StepCounterSupportUtil.StepCounterClassCallback() { // from class: ovg.1
            @Override // com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.StepCounterClassCallback
            public void getDeviceClass(int i) {
                ovg.this.refreshCardData();
            }
        });
    }

    @Override // com.huawei.ui.homehealth.stepscard.EmptyStepsCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ovr
            @Override // java.lang.Runnable
            public final void run() {
                ovg.this.h();
            }
        });
    }

    /* synthetic */ void h() {
        v();
        o();
    }

    private void v() {
        long b2 = LogUtil.b(2000, e);
        if (b2 != -1) {
            ReleaseLogUtil.b("SCUI_TwoModelCardData", "onResume updateStepsAndCalories steps=", Integer.valueOf(this.b[3]), " floor=", Integer.valueOf(this.b[5]), " calories=", Integer.valueOf(this.b[0]), " distance=", Integer.valueOf(this.b[4]), " intensity ", Integer.valueOf(this.b[1]), " activityHour ", Integer.valueOf(this.b[2]));
            e = b2;
        }
        boolean j = com.huawei.haf.application.BaseApplication.j();
        if ((this.o && j) || this.b[3] == 0) {
            this.i.sendMessage(this.i.obtainMessage(1));
        }
    }

    protected void o() {
        LogUtil.a("SCUI_TwoModelCardData", "updateFitnessDataOrigin start ");
        if (this.q == null) {
            this.q = new CommonSingleCallback() { // from class: ovi
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    ovg.this.a((Boolean) obj);
                }
            };
            pwj.e().d(this.q);
        }
        pwj.e().c();
    }

    /* synthetic */ void a(Boolean bool) {
        LogUtil.a("SCUI_TwoModelCardData", "updateFitnessDataOrigin result ");
        this.i.sendMessage(this.i.obtainMessage(10));
    }

    protected void j() {
        ThreeCircleCardData threeCircleCardData = this.w;
        if (threeCircleCardData != null) {
            threeCircleCardData.g_();
        }
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.g_();
        }
    }

    private void p() {
        oui ouiVar;
        oui ouiVar2 = new oui();
        this.v = ouiVar2;
        ouiVar2.a(new IBaseResponseCallback() { // from class: ovx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ovg.this.d(i, obj);
            }
        });
        if (!Utils.o() || (ouiVar = this.v) == null) {
            return;
        }
        ouiVar.d();
    }

    /* synthetic */ void e(Object obj) {
        this.z.c((String) obj);
    }

    /* synthetic */ void d(int i, final Object obj) {
        if (i == 0 || i == 3) {
            m();
            if (i != 3 || this.z == null) {
                return;
            }
            LogUtil.a("SCUI_TwoModelCardData", "initSyncHelper EVENT_GOAL_UPDATE");
            ThreadPoolManager.d().execute(new Runnable() { // from class: ovl
                @Override // java.lang.Runnable
                public final void run() {
                    ovg.this.e(obj);
                }
            });
            return;
        }
        if (i != 1) {
            if (i == 2) {
                this.l = false;
                return;
            }
            return;
        }
        int i2 = this.k;
        int i3 = this.b[3];
        if (i2 == i3) {
            LogUtil.a("SCUI_TwoModelCardData", "processOriginListData mLastShowTipsStep == mSteps");
        } else {
            this.k = i3;
            owg.a(this.mContext, this.b[3]);
        }
    }

    private void m() {
        LogUtil.a("SCUI_TwoModelCardData", "acquireGoalSteps");
        StepsViewModel stepsViewModel = this.j;
        if (stepsViewModel != null) {
            stepsViewModel.e();
        }
    }

    protected void e() {
        if (!Utils.o() || OperationUtils.isOperation(LoginInit.getInstance(this.mContext).getCountryCode(null))) {
            if (this.n == null) {
                this.n = ouh.d(this.mContext);
            }
            if (this.g == null) {
                this.g = Executors.newSingleThreadExecutor();
            }
            this.n.e(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dit_(Bundle bundle) {
        if (bundle != null) {
            int i = bundle.getInt("carior", -1);
            int i2 = bundle.getInt("intensityTime", -1);
            int i3 = bundle.getInt("activeCount", -1);
            final int i4 = bundle.getInt("step", -1);
            int i5 = bundle.getInt("distance", -1);
            int i6 = bundle.getInt("floor", -1);
            this.c = i;
            this.b[0] = (int) UnitUtil.a(i / 1000.0d, 0);
            int[] iArr = this.b;
            iArr[1] = i2;
            iArr[2] = i3;
            iArr[3] = i4;
            iArr[4] = i5;
            iArr[5] = i6;
            x();
            owg.b(this.b);
            boolean isRunningForeground = BaseApplication.isRunningForeground();
            LogUtil.a("SCUI_TwoModelCardData", "report step=", Integer.valueOf(i4), " calories=", Integer.valueOf(i), " floor=", Integer.valueOf(i6), " distance=", Integer.valueOf(i5), " intensity=", Integer.valueOf(i2), " mActiveHours=", Integer.valueOf(i3), "  app isRunningForeground=", Boolean.valueOf(isRunningForeground));
            if ((this.o && isRunningForeground) || i4 == 0) {
                ThreeCircleCardData threeCircleCardData = this.w;
                if (threeCircleCardData != null) {
                    threeCircleCardData.a(this.c, this.b, this.h);
                }
                ThreeLeafCardData threeLeafCardData = this.z;
                if (threeLeafCardData != null) {
                    threeLeafCardData.a(this.c, this.b, this.h);
                }
                d(i, i2, i3, i4);
                ThreadPoolManager.d().execute(new Runnable() { // from class: ovs
                    @Override // java.lang.Runnable
                    public final void run() {
                        PluginAchieveAdapterImpl.a(i4);
                    }
                });
                otg.d(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HashMap<String, Integer> hashMap) {
        LogUtil.a("SCUI_TwoModelCardData", "processGoalData goal value is ", hashMap);
        a(hashMap, "900200007", this.h[0] * 1000, 0);
        a(hashMap, "900200008", this.h[1], 1);
        a(hashMap, "900200009", this.h[2], 2);
        a(hashMap, "900200006", this.h[3], 3);
        if (!s()) {
            LogUtil.a("SCUI_TwoModelCardData", "processGoalData mGoalValue is ", Arrays.toString(this.h));
            ThreeCircleCardData threeCircleCardData = this.w;
            if (threeCircleCardData != null) {
                threeCircleCardData.a(this.c, this.b, this.h);
            }
            ThreeLeafCardData threeLeafCardData = this.z;
            if (threeLeafCardData != null) {
                threeLeafCardData.a(this.c, this.b, this.h);
            }
            owg.d(this.h);
            this.l = true;
            int i = this.c;
            int[] iArr = this.b;
            d(i, iArr[1], iArr[2], iArr[3]);
            x();
            return;
        }
        LogUtil.h("SCUI_TwoModelCardData", "updateAchieveProgress goal value is error");
    }

    private boolean s() {
        for (int i : this.h) {
            if (i == 0) {
                return true;
            }
        }
        return false;
    }

    private void a(HashMap<String, Integer> hashMap, String str, int i, int i2) {
        int e2 = nip.e(hashMap, str, i);
        if (i2 == 0) {
            e2 /= 1000;
        }
        int i3 = this.h[i2];
        if (e2 != i3) {
            LogUtil.a("SCUI_TwoModelCardData", "insert updateGoalValueByOne spKey is ", str, " mGoalValue ", Integer.valueOf(i3), " goal ", Integer.valueOf(e2));
            this.h[i2] = e2;
        }
    }

    private void d(int i, int i2, int i3, int i4) {
        if (!this.l) {
            LogUtil.h("SCUI_TwoModelCardData", "updateAchieveAnimation not get goal success");
            return;
        }
        if (this.mContext == null || this.mContext != com.huawei.haf.application.BaseApplication.wa_()) {
            LogUtil.h("SCUI_TwoModelCardData", "updateAchieveAnimation mainActivity is not show");
            return;
        }
        int[] iArr = this.h;
        if (iArr[0] <= 0 || iArr[1] <= 0 || iArr[2] <= 0) {
            return;
        }
        c(i, i2, i3, i4);
    }

    private void c(int i, int i2, int i3, int i4) {
        Handler handler;
        if (!this.o) {
            ReleaseLogUtil.a("SCUI_TwoModelCardData", " updateAchieveDialogAnimation failed. current activity is on pause.");
            return;
        }
        int[] iArr = this.h;
        boolean z = i >= iArr[0] * 1000;
        boolean z2 = i2 >= iArr[1];
        boolean z3 = i3 >= iArr[2];
        LogUtil.a("SCUI_TwoModelCardData", "isHeatAchieved=", Boolean.valueOf(z), " isIntensityAchieved=", Boolean.valueOf(z2), " isActiveAchieved=", Boolean.valueOf(z3), " isStepAchieved=", Boolean.valueOf(i4 >= iArr[3]));
        if (z3 && z2 && z) {
            if ("threeCircleCard".equals(oun.a())) {
                ThreeLeafCardData threeLeafCardData = this.z;
                if (threeLeafCardData != null) {
                    threeLeafCardData.e(8);
                    SharedPreferenceManager.e(this.mContext, String.valueOf(10000), "SP_PERFECT_RED_POINT", String.valueOf(DateFormatUtil.b(System.currentTimeMillis())), new StorageParams());
                    return;
                }
                return;
            }
            String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "SP_PERFECT_RED_POINT");
            if ((TextUtils.isEmpty(b2) || !b2.equals(String.valueOf(DateFormatUtil.b(System.currentTimeMillis())))) && (handler = this.i) != null) {
                this.i.sendMessage(handler.obtainMessage(11));
                return;
            }
            return;
        }
        ThreeLeafCardData threeLeafCardData2 = this.z;
        if (threeLeafCardData2 != null) {
            threeLeafCardData2.e(8);
            SharedPreferenceManager.e(this.mContext, String.valueOf(10000), "SP_PERFECT_RED_POINT", "", new StorageParams());
        }
    }

    private void x() {
        if (this.l) {
            int[] iArr = this.b;
            int i = iArr[0];
            int[] iArr2 = this.h;
            PluginAchieveAdapterImpl.c(i, iArr2[0], iArr[1], iArr2[1]);
        }
    }

    public TwoModelCardViewHolder c() {
        return this.ad;
    }

    public ThreeCircleCardData d() {
        return this.w;
    }

    public void d(final String str, final boolean z) {
        if (!HandlerExecutor.b()) {
            LogUtil.a("SCUI_TwoModelCardData", "notifyModelChanged, not in ui thread");
            HandlerExecutor.a(new Runnable() { // from class: ovg.5
                @Override // java.lang.Runnable
                public void run() {
                    ovg.this.d(str, z);
                }
            });
            return;
        }
        LogUtil.a("SCUI_TwoModelCardData", "notifyModelChanged, targetType: ", str);
        this.x = str;
        if (z) {
            w();
        }
        TwoModelCardViewHolder twoModelCardViewHolder = this.ad;
        if (twoModelCardViewHolder != null) {
            twoModelCardViewHolder.b(str);
        }
        if ("threeLeafCard".equals(str)) {
            this.s.a("threeLeafCard");
        } else {
            this.s.a("threeCircleCard");
        }
        y();
    }

    private void w() {
        if ("threeCircleCard".equals(this.x)) {
            this.w.h();
        } else {
            this.z.j();
        }
    }

    private void y() {
        LogUtil.a("SCUI_TwoModelCardData", "refreshUiForVisible");
        if ("threeCircleCard".equals(this.x)) {
            this.w.j();
        } else {
            this.z.h();
            LogUtil.a("SCUI_TwoModelCardData", "refreshUiForVisible end");
        }
    }

    public void k() {
        LogUtil.a("SCUI_TwoModelCardData", "startAnimator");
        if ("threeLeafCard".equals(this.x)) {
            this.z.n();
        }
        c().e(this.x);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TwoModelCardViewHolder twoModelCardViewHolder = this.ad;
        if (twoModelCardViewHolder != null) {
            twoModelCardViewHolder.e();
        }
        ThreeCircleCardData threeCircleCardData = this.w;
        if (threeCircleCardData != null) {
            threeCircleCardData.onConfigurationChanged(configuration);
        }
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.onConfigurationChanged(configuration);
        }
        this.d.dhB_(configuration);
    }

    public void g() {
        this.o = false;
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.d(this.f15973a);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.o = true;
        ThreeCircleCardData threeCircleCardData = this.w;
        if (threeCircleCardData != null) {
            threeCircleCardData.onResume();
        }
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.d(this.f15973a);
            this.z.onResume();
        }
        m();
        n();
        refreshCardData();
        ReleaseLogUtil.b("SCUI_TwoModelCardData", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void n() {
        Intent intent = new Intent();
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction("com.huawei.health.check_sensor_state");
        this.mContext.sendBroadcast(intent, SecurityConstant.d);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        ThreeCircleCardData threeCircleCardData = this.w;
        if (threeCircleCardData != null) {
            threeCircleCardData.onDestroy();
        }
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.onDestroy();
        }
        ObserverManagerUtil.c(this.y);
        ObserverManagerUtil.c(this.r);
        TwoModelCardViewHolder twoModelCardViewHolder = this.ad;
        if (twoModelCardViewHolder != null) {
            twoModelCardViewHolder.c();
        }
        ExecutorService executorService = this.g;
        if (executorService != null && ouh.b(executorService)) {
            this.g = null;
        }
        if (this.n != null) {
            ReleaseLogUtil.b("SCUI_TwoModelCardData", "mNotificationInteractors closeMessageObserver");
            this.n.d();
        }
        if (this.q != null) {
            pwj.e().b(this.q);
            this.q = null;
        }
        oui ouiVar = this.v;
        if (ouiVar != null) {
            ouiVar.c();
        }
    }

    public void d(RecyclerView.ViewHolder viewHolder, int i) {
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.a(viewHolder, i);
        }
    }

    protected void f() {
        LogUtil.a("SCUI_TwoModelCardData", "showFitnessDataOriginDialog()");
        if (c() == null) {
            LogUtil.b("SCUI_TwoModelCardData", "showFitnessDataOriginDialog mStepsCardViewHolder == null");
            return;
        }
        if ("".equals(SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "data_origin_icon_red_dot"))) {
            SharedPreferenceManager.e(this.mContext, String.valueOf(10000), "data_origin_icon_red_dot", "false", new StorageParams());
        }
        ThreeCircleCardData threeCircleCardData = this.w;
        if (threeCircleCardData != null) {
            threeCircleCardData.k();
        }
        ThreeLeafCardData threeLeafCardData = this.z;
        if (threeLeafCardData != null) {
            threeLeafCardData.k();
        }
        pwj.e().a(this.mContext, q());
    }

    private int q() {
        if ("threeCircleCard".equals(oun.a())) {
            return 1;
        }
        return "threeLeafCard".equals(oun.a()) ? 2 : 0;
    }

    public View.OnClickListener diu_(AchieveTaskHandler.AchieveAniType achieveAniType) {
        if (AchieveTaskHandler.AchieveAniType.SINGLE_CIRCLE_ACTIVE.equals(achieveAniType)) {
            return new View.OnClickListener() { // from class: ovn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ovg.this.div_(view);
                }
            };
        }
        if (AchieveTaskHandler.AchieveAniType.SINGLE_CIRCLE_WALK.equals(achieveAniType)) {
            return new View.OnClickListener() { // from class: ovj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ovg.this.diw_(view);
                }
            };
        }
        if (AchieveTaskHandler.AchieveAniType.SINGLE_CIRCLE_CALORIE.equals(achieveAniType)) {
            return new View.OnClickListener() { // from class: ovk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ovg.this.dix_(view);
                }
            };
        }
        if (AchieveTaskHandler.AchieveAniType.SINGLE_CIRCLE_INTENSITY.equals(achieveAniType)) {
            return new View.OnClickListener() { // from class: ovm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ovg.this.diy_(view);
                }
            };
        }
        if (AchieveTaskHandler.AchieveAniType.THREE_CIRCLE.equals(achieveAniType)) {
            return new View.OnClickListener() { // from class: ovo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ovg.this.diz_(view);
                }
            };
        }
        return null;
    }

    /* synthetic */ void div_(View view) {
        owg.a(this.mContext);
        pxp.d(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void diw_(View view) {
        pxp.a(1);
        HealthOpenSDK healthOpenSDK = this.m;
        Context context = this.mContext;
        int[] iArr = this.b;
        owg.b(healthOpenSDK, context, iArr[3], iArr[4]);
        pxp.d(5);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dix_(View view) {
        owg.a(this.m, this.mContext, this.b[0]);
        pxp.d(3);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void diy_(View view) {
        l();
        pxp.d(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void diz_(View view) {
        owg.b(this.mContext, this.c, this.b, this.h, "7");
        pxp.d(4);
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void l() {
        HealthOpenSDK healthOpenSDK = this.m;
        if (healthOpenSDK != null) {
            healthOpenSDK.a((IFlushResult) null);
        }
        owg.b(this.m, this.mContext, this.b[1]);
    }

    public static class b implements com.huawei.haf.design.pattern.Observer {
        private WeakReference<ovg> d;

        b(ovg ovgVar) {
            this.d = new WeakReference<>(ovgVar);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ovg ovgVar;
            if (!"showFitnessDataOriginDialog".equals(str) || (ovgVar = this.d.get()) == null) {
                return;
            }
            ovgVar.f();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "SCUI_TwoModelCardData";
    }
}
