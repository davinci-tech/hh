package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import defpackage.ary;
import defpackage.ase;
import defpackage.bzs;
import defpackage.dqj;
import defpackage.ffy;
import defpackage.fit;
import defpackage.fiu;
import defpackage.fiy;
import defpackage.fjf;
import defpackage.fys;
import defpackage.fyw;
import defpackage.gea;
import defpackage.gge;
import defpackage.gib;
import defpackage.gpn;
import defpackage.grz;
import defpackage.gsc;
import defpackage.jdx;
import defpackage.koq;
import defpackage.moj;
import defpackage.qts;
import defpackage.quh;
import defpackage.qul;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes8.dex */
public class MemberPlanProvider extends BaseKnitDataProvider<gea> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3387a;
    private boolean b;
    private IntPlan c;
    private quh d;
    private String g;
    private final Observer h;
    private qts j;
    private fiy l;
    private fiy m;
    private List<Integer> n;
    private WeakReference<SectionBean> f = new WeakReference<>(null);
    private OnFitnessStatusChangeCallback i = new OnFitnessStatusChangeCallback() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider.3
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public void onUpdate() {
            if (MemberPlanProvider.this.f3387a != null && MemberPlanProvider.this.f.get() != null) {
                MemberPlanProvider memberPlanProvider = MemberPlanProvider.this;
                memberPlanProvider.e(memberPlanProvider.f3387a, (SectionBean) MemberPlanProvider.this.f.get());
                return;
            }
            Object[] objArr = new Object[3];
            objArr[0] = "context null.";
            objArr[1] = Boolean.valueOf(MemberPlanProvider.this.f3387a == null);
            objArr[2] = Boolean.valueOf(MemberPlanProvider.this.f.get() == null);
            LogUtil.h("Suggestion_MemberPlanProvider", objArr);
        }
    };
    private Map<String, Object> o = new HashMap();
    private boolean e = true;

    private boolean e(int i, int i2, int i3) {
        return i < i3 || (i == i3 && i2 == 0);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    public /* synthetic */ void e(String str, Object[] objArr) {
        IntPlan intPlan;
        if ("MEMBER_PAGE_IS_VISIBLE_TO_USER".equals(str)) {
            Object obj = objArr[0];
            if (!(obj instanceof Boolean)) {
                LogUtil.h("Suggestion_MemberPlanProvider", "mObserver object ", obj);
                return;
            }
            boolean booleanValue = ((Boolean) obj).booleanValue();
            this.e = booleanValue;
            LogUtil.a("Suggestion_MemberPlanProvider", "mIsVisibleToUser:", Boolean.valueOf(booleanValue), " size:", Integer.valueOf(this.o.size()));
            if (this.e && this.o.size() != 0) {
                gge.e(0, this.o);
            }
            if (!this.e || (intPlan = this.c) == null) {
                return;
            }
            b(intPlan);
            d(this.c);
        }
    }

    public MemberPlanProvider() {
        Observer observer = new Observer() { // from class: geo
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                MemberPlanProvider.this.e(str, objArr);
            }
        };
        this.h = observer;
        LogUtil.a("Suggestion_MemberPlanProvider", "MemberPlanProvider create.");
        ArrayList arrayList = new ArrayList();
        arrayList.add(40003);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value()));
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new c());
        ary.a().e(this.i, "PLAN_DELETE");
        ary.a().e(this.i, "PLAN_UPDATE");
        ObserverManagerUtil.d(observer, "MEMBER_PAGE_IS_VISIBLE_TO_USER");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Suggestion_MemberPlanProvider", "on destroy.");
        if (koq.c(this.n)) {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.n, new HiUnSubscribeListener() { // from class: geq
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    ReleaseLogUtil.b("Suggestion_MemberPlanProvider", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
        ary.a().c(this.i, "PLAN_DELETE");
        ary.a().c(this.i, "PLAN_UPDATE");
        ObserverManagerUtil.c(this.h);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(final Context context, final SectionBean sectionBean) {
        super.e(context, sectionBean);
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider.4
                @Override // java.lang.Runnable
                public void run() {
                    MemberPlanProvider.this.e(context, sectionBean);
                }
            });
            return;
        }
        if (this.f3387a == null) {
            this.f3387a = context;
        }
        if (this.f.get() == null) {
            this.f = new WeakReference<>(sectionBean);
        }
        e(new gea(), sectionBean, new CountDownLatch(4));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, gea geaVar) {
        super.parseParams(context, hashMap, geaVar);
        hashMap.put("DOING_PLAN_FLAG", Boolean.valueOf(geaVar.o()));
        hashMap.put("INT_PLAN_TYPE", Integer.valueOf(geaVar.h()));
        hashMap.put("PLAN_NAME", geaVar.g());
        hashMap.put("REPORT_PLAN_TEXT", geaVar.n());
        hashMap.put("IS_SHOW_RED_DOT", Boolean.valueOf(geaVar.m()));
        hashMap.put("DIET_CALORIE_OVERVIEW", geaVar.d());
        hashMap.put("BEGIN_DATE", Long.valueOf(geaVar.c()));
        hashMap.put("TOTAL_DAY", Integer.valueOf(geaVar.l()));
        hashMap.put("FINISH_RATE", Float.valueOf(geaVar.j()));
        hashMap.put("IS_VIP_EXPIRED", Boolean.valueOf(geaVar.k()));
        hashMap.put("GO_TO_SPORT_TIP", geaVar.f());
        hashMap.put("DIET_TIP", geaVar.e());
        hashMap.put("RECIPE_TIP", geaVar.i());
        hashMap.put("FASTING_LITE_TYPE", Integer.valueOf(geaVar.a()));
        hashMap.put("FASTING_LITE_VALUE", Long.valueOf(geaVar.b()));
        d(context, hashMap, geaVar);
        if (this.e) {
            gge.e(0, this.o);
            IntPlan intPlan = this.c;
            if (intPlan != null) {
                b(intPlan);
                d(this.c);
            }
        }
    }

    private void d(final Context context, HashMap<String, Object> hashMap, final gea geaVar) {
        hashMap.put("GO_TO_PLAN_CLICK", new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.aLL_(context, view);
            }
        });
        hashMap.put("GO_TO_REPORT_CLICK", new View.OnClickListener() { // from class: gep
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.this.aLO_(geaVar, view);
            }
        });
        hashMap.put("CREATE_AI_FITNESS_CLICK", new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.aLM_(context, view);
            }
        });
        hashMap.put("GO_TO_DIETARY_INSTRUCT", new View.OnClickListener() { // from class: gen
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.aLN_(view);
            }
        });
        c(context, hashMap, geaVar);
    }

    static /* synthetic */ void aLL_(Context context, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "go to plan click.");
        JumpUtil.c(context);
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aLO_(gea geaVar, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "go to report click.");
        JumpUtil.a(1, this.f3387a, geaVar.h(), this.g);
        gge.d(1, geaVar.g(), 1, (int) geaVar.j());
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aLM_(Context context, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "create ai fitness plan click.");
        JumpUtil.d(IntPlan.PlanType.AI_FITNESS_PLAN.getType(), "JZ001", "", context);
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aLN_(View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "setClickListener GO_TO_DIETARY_INSTRUCT");
        JumpUtil.e();
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(final Context context, HashMap<String, Object> hashMap, final gea geaVar) {
        hashMap.put("GO_TO_SPORT_CLICK", new View.OnClickListener() { // from class: geu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.this.aLP_(geaVar, context, view);
            }
        });
        hashMap.put("DIET_CLICK", new View.OnClickListener() { // from class: get
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.this.aLQ_(geaVar, view);
            }
        });
        hashMap.put("RECIPE_CLICK", new View.OnClickListener() { // from class: gek
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.this.aLR_(geaVar, context, view);
            }
        });
        hashMap.put("FASTING_LITE_CLICK", new View.OnClickListener() { // from class: gem
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberPlanProvider.this.aLS_(geaVar, view);
            }
        });
    }

    public /* synthetic */ void aLP_(gea geaVar, Context context, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "Go to sport click.");
        if (geaVar.k() && geaVar.h() == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            a();
            gge.d(3, geaVar.g(), 3, (int) geaVar.j());
        } else {
            JumpUtil.c(context);
            gge.d(1, geaVar.g(), 2, (int) geaVar.j());
        }
        gge.e(1, this.o);
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aLQ_(gea geaVar, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "goToDietDiaryFastRecord.");
        grz.f("Suggestion_MemberPlanProvider");
        gge.d(1, geaVar.g(), 4, (int) geaVar.j());
        gge.e(1, this.o);
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aLR_(gea geaVar, Context context, View view) {
        if (geaVar.k()) {
            a();
            gge.d(3, geaVar.g(), 6, (int) geaVar.j());
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("Suggestion_MemberPlanProvider", "Go to recipe click.");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, "opening_page_plan");
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.custom-recipe", builder);
        gge.d(1, geaVar.g(), 5, (int) geaVar.j());
        gge.e(1, this.o);
        gge.b(1);
        dqj.o();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aLS_(gea geaVar, View view) {
        LogUtil.a("Suggestion_MemberPlanProvider", "Go to fasting lite click.");
        dqj.o();
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.a("Suggestion_MemberPlanProvider", "marketRouterApi == null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            marketRouterApi.router("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.fasting-lite?healthType=2&pageType=10&pullFrom=4009&h5pro=true&urlType=4&pName=com.huawei.health&path=plan_setting");
            gge.d(1, geaVar.g(), 7, (int) geaVar.j());
            gge.e(1, this.o);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a() {
        LogUtil.a("Suggestion_MemberPlanProvider", "jumpToMemberSelect.");
        AppRouter.b("/OperationBundle/MemberTypeSelectActivity").b(R.anim._2130772060_res_0x7f01005c, R.anim._2130771981_res_0x7f01000d).j();
    }

    private void e(gea geaVar, SectionBean sectionBean, CountDownLatch countDownLatch) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "getCurrentPlan intPlanApi is null.");
        } else {
            planApi.b(new AnonymousClass1(geaVar, sectionBean, countDownLatch));
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider$1, reason: invalid class name */
    public class AnonymousClass1 extends UiCallback<IntPlan> {
        final /* synthetic */ CountDownLatch b;
        final /* synthetic */ gea d;
        final /* synthetic */ SectionBean e;

        AnonymousClass1(gea geaVar, SectionBean sectionBean, CountDownLatch countDownLatch) {
            this.d = geaVar;
            this.e = sectionBean;
            this.b = countDownLatch;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_MemberPlanProvider", "getCurrentPlan onFailure", Integer.valueOf(i), str);
            this.d.c(false);
            SectionBean sectionBean = this.e;
            if (sectionBean == null) {
                LogUtil.h("Suggestion_MemberPlanProvider", "getCurrentPlan onFailure sectionBean == null");
            } else {
                sectionBean.e(this.d);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            LogUtil.a("Suggestion_MemberPlanProvider", "getCurrentIntPlan onSuccess.");
            if (intPlan != null) {
                MemberPlanProvider.this.c = intPlan;
                MemberPlanProvider.this.g = intPlan.getPlanId();
                this.d.c(true);
                this.d.e(intPlan.getPlanType().getType());
                if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                    MemberPlanProvider.this.d(this.d, intPlan);
                }
                MemberPlanProvider.this.c(this.d, intPlan);
                ThreadPoolManager d = ThreadPoolManager.d();
                final gea geaVar = this.d;
                final SectionBean sectionBean = this.e;
                final CountDownLatch countDownLatch = this.b;
                d.execute(new Runnable() { // from class: ger
                    @Override // java.lang.Runnable
                    public final void run() {
                        MemberPlanProvider.AnonymousClass1.this.c(geaVar, intPlan, sectionBean, countDownLatch);
                    }
                });
                return;
            }
            LogUtil.b("Suggestion_MemberPlanProvider", "plan data is null.");
            this.d.c(false);
            SectionBean sectionBean2 = this.e;
            if (sectionBean2 == null) {
                LogUtil.b("Suggestion_MemberPlanProvider", "plan null sectionBean == null.");
            } else {
                sectionBean2.e(this.d);
            }
        }

        public /* synthetic */ void c(gea geaVar, IntPlan intPlan, SectionBean sectionBean, CountDownLatch countDownLatch) {
            MemberPlanProvider.this.b(geaVar, intPlan, sectionBean, countDownLatch);
            MemberPlanProvider.this.c(geaVar, intPlan, sectionBean, countDownLatch);
        }
    }

    private void b(IntPlan intPlan) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            ase.d(intPlan, "7", 7, 2, 1);
        } else {
            ase.d(intPlan, "7", new int[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(gea geaVar, IntPlan intPlan) {
        LogUtil.a("Suggestion_MemberPlanProvider", "isOverdue:", Boolean.valueOf(fyw.x(intPlan)), " isVipExpired:", Boolean.valueOf(fyw.t(intPlan)));
        if (fyw.x(intPlan) || fyw.t(intPlan)) {
            geaVar.b("");
            geaVar.d(false);
        } else if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            b(geaVar, intPlan);
        } else if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            a(geaVar, intPlan);
        } else {
            geaVar.b("");
            geaVar.d(false);
        }
    }

    private void a(gea geaVar, IntPlan intPlan) {
        if (fyw.u(intPlan)) {
            geaVar.b(ffy.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.string._2130848640_res_0x7f022b80, new Object[0]));
            if (fyw.e()) {
                geaVar.d(true);
                return;
            } else {
                geaVar.d(false);
                return;
            }
        }
        b(geaVar, intPlan);
    }

    private void b(gea geaVar, IntPlan intPlan) {
        if (fyw.v(intPlan)) {
            geaVar.b(ffy.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.string._2130848639_res_0x7f022b7f, new Object[0]));
            if (fyw.b()) {
                geaVar.d(true);
                return;
            } else {
                geaVar.d(false);
                return;
            }
        }
        geaVar.b("");
        geaVar.d(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(gea geaVar, IntPlan intPlan) {
        if (intPlan.getMetaInfo() == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "setCompletedDaysAndRate meta info is null.");
            return;
        }
        geaVar.c(intPlan.getMetaInfo().getName());
        if (intPlan.getStat("progress") != null) {
            geaVar.e(((Float) intPlan.getStat("progress").getValue()).floatValue());
        } else {
            geaVar.e(0.0f);
        }
        geaVar.d(intPlan.getPlanTimeInfo().getBeginDate());
        geaVar.c(intPlan.getMetaInfo().getDayCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final gea geaVar, final IntPlan intPlan, final SectionBean sectionBean, final CountDownLatch countDownLatch) {
        LogUtil.a("Suggestion_MemberPlanProvider", "checkIsVipExpired default:", Integer.valueOf(intPlan.getTransactionStatus()));
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("Suggestion_MemberPlanProvider", "refreshVipStateData VipApi is null");
            geaVar.b(fyw.t(intPlan));
            e(geaVar, intPlan, sectionBean, countDownLatch);
            return;
        }
        vipApi.getVipInfo(new VipCallback() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider.5
            @Override // com.huawei.health.vip.api.VipCallback
            public void onSuccess(Object obj) {
                LogUtil.a("Suggestion_MemberPlanProvider", "getVipInfo success.");
                if (obj == null) {
                    LogUtil.a("Suggestion_MemberPlanProvider", "getVipInfo onSuccess result is null");
                    geaVar.b(fyw.t(intPlan));
                    MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
                    return;
                }
                UserMemberInfo userMemberInfo = obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null;
                if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1 || gpn.d(userMemberInfo)) {
                    LogUtil.h("Suggestion_MemberPlanProvider", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                    geaVar.b(true);
                } else {
                    geaVar.b(false);
                }
                MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
            }

            @Override // com.huawei.health.vip.api.VipCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_MemberPlanProvider", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(gea geaVar, IntPlan intPlan, SectionBean sectionBean, CountDownLatch countDownLatch) {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        int b = DateFormatUtil.b(timeInMillis);
        calendar.add(6, 1);
        long timeInMillis2 = calendar.getTimeInMillis();
        ReleaseLogUtil.b("Suggestion_MemberPlanProvider", "getTodayDietRecord dayFormat ", Integer.valueOf(b));
        grz.b(b, b, new e(this, geaVar, intPlan, sectionBean, timeInMillis, timeInMillis2, countDownLatch));
    }

    static class e implements ResponseCallback<List<quh>> {

        /* renamed from: a, reason: collision with root package name */
        private final long f3392a;
        private final SectionBean b;
        private final IntPlan c;
        private final gea d;
        private final CountDownLatch e;
        private final WeakReference<MemberPlanProvider> f;
        private final long j;

        e(MemberPlanProvider memberPlanProvider, gea geaVar, IntPlan intPlan, SectionBean sectionBean, long j, long j2, CountDownLatch countDownLatch) {
            this.f = new WeakReference<>(memberPlanProvider);
            this.d = geaVar;
            this.c = intPlan;
            this.b = sectionBean;
            this.f3392a = j;
            this.j = j2;
            this.e = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            MemberPlanProvider memberPlanProvider = this.f.get();
            if (memberPlanProvider == null) {
                ReleaseLogUtil.a("Suggestion_MemberPlanProvider", "InnerDietDiaryCbk provider is null list ", list);
                return;
            }
            LogUtil.a("Suggestion_MemberPlanProvider", "InnerDietDiaryCbk errorCode ", Integer.valueOf(i), " list ", list);
            if (koq.b(list)) {
                memberPlanProvider.d(this.d, this.c, this.b, this.f3392a, this.j, this.e);
                memberPlanProvider.e(this.d, this.c, this.b, this.e);
                return;
            }
            quh quhVar = list.get(0);
            if (quhVar == null) {
                memberPlanProvider.d(this.d, this.c, this.b, this.f3392a, this.j, this.e);
                memberPlanProvider.e(this.d, this.c, this.b, this.e);
                return;
            }
            memberPlanProvider.d = quhVar;
            memberPlanProvider.j = quhVar.d();
            memberPlanProvider.d(this.d, this.c, this.b, this.f3392a, this.j, this.e);
            gea geaVar = this.d;
            if (geaVar != null) {
                geaVar.b(memberPlanProvider.j);
            }
            memberPlanProvider.e(this.d, this.c, this.b, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(gea geaVar, IntPlan intPlan, SectionBean sectionBean, long j, long j2, CountDownLatch countDownLatch) {
        LogUtil.a("Suggestion_MemberPlanProvider", "getTwoDayRecipe:", Long.valueOf(j), " ", Long.valueOf(j2));
        c(geaVar, intPlan, j, false, sectionBean, countDownLatch);
        c(geaVar, intPlan, j2, true, sectionBean, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final gea geaVar, final IntPlan intPlan, final SectionBean sectionBean, final CountDownLatch countDownLatch) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: geh
                @Override // java.lang.Runnable
                public final void run() {
                    MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
                }
            });
            return;
        }
        countDownLatch.countDown();
        if (countDownLatch.getCount() > 0) {
            LogUtil.h("Suggestion_MemberPlanProvider", "not finish request.", Long.valueOf(countDownLatch.getCount()));
            return;
        }
        LogUtil.a("Suggestion_MemberPlanProvider", "setTodaySuggestion:", Long.valueOf(countDownLatch.getCount()));
        this.o.put("planName", intPlan.getMetaInfo().getName());
        e(geaVar, intPlan);
        b(geaVar);
        a(geaVar);
        e(geaVar);
        if (sectionBean == null) {
            ReleaseLogUtil.a("Suggestion_MemberPlanProvider", "setTodaySuggestion sectionBean is null");
        } else {
            sectionBean.e(geaVar);
        }
    }

    private void e(gea geaVar, IntPlan intPlan) {
        int i;
        int i2 = 0;
        if (geaVar.h() == IntPlan.PlanType.AI_FITNESS_PLAN.getType() && geaVar.k()) {
            geaVar.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847736_res_0x7f0227f8));
            this.o.put("fitnessStatus", 0);
            return;
        }
        List<IntAction> b = fys.b(intPlan);
        if (koq.c(b)) {
            Iterator<IntAction> it = b.iterator();
            while (it.hasNext()) {
                if (it.next().getPunchFlag() == 0) {
                    i2++;
                }
            }
            i = i2;
            i2 = 1;
        } else {
            i = 0;
        }
        if (i2 == 0) {
            geaVar.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R.string._2130846124_res_0x7f0221ac));
            this.o.put("fitnessStatus", 4);
        } else if (i == 0) {
            geaVar.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847737_res_0x7f0227f9));
            this.o.put("fitnessStatus", 3);
        } else if (i < b.size()) {
            geaVar.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903456_res_0x7f0301a0, i, Integer.valueOf(i)));
            this.o.put("fitnessStatus", 2);
        } else {
            geaVar.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903455_res_0x7f03019f, i, Integer.valueOf(i)));
            this.o.put("fitnessStatus", 1);
        }
    }

    private void d(IntPlan intPlan) {
        final List<IntAction> b = fys.b(intPlan);
        int g = ase.g(intPlan);
        int d = gib.d(Calendar.getInstance().get(7));
        b.addAll(fys.e(intPlan, g, d));
        Iterator<IntAction> it = b.iterator();
        final int i = 0;
        while (it.hasNext()) {
            if (it.next().getPunchFlag() == 1) {
                i++;
            }
        }
        ase.e(this.c, g, d, new IBaseResponseCallback() { // from class: gev
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                MemberPlanProvider.this.d(b, i, i2, obj);
            }
        });
    }

    public /* synthetic */ void d(List list, int i, int i2, Object obj) {
        gge.b(list.size(), i, c((List<RecordData>) obj), this.b);
    }

    private List<Integer> c(List<RecordData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.c(list)) {
            for (RecordData recordData : list) {
                if (recordData != null && recordData.getSportType() > 0 && recordData.getIsInPlan() != 1) {
                    arrayList.add(Integer.valueOf(recordData.getSportType()));
                }
            }
        }
        return arrayList;
    }

    private void b(gea geaVar) {
        qts qtsVar = this.j;
        if (qtsVar == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "overview is null.");
            geaVar.d("");
            return;
        }
        int round = Math.round(qtsVar.f());
        int round2 = Math.round(this.j.c());
        int round3 = Math.round(this.j.e());
        int i = Calendar.getInstance().get(11);
        if (round == 0) {
            geaVar.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847733_res_0x7f0227f5, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130846278_res_0x7f022246, String.valueOf(round), String.valueOf(round2))));
            this.o.put("dietStatus", 0);
        } else if (i >= 18) {
            geaVar.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847738_res_0x7f0227fa));
            this.o.put("dietStatus", 3);
        } else if (round <= (round3 + round2) * 1.1f) {
            geaVar.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847733_res_0x7f0227f5, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130846278_res_0x7f022246, String.valueOf(round), String.valueOf(round2))));
            this.o.put("dietStatus", 1);
        } else {
            geaVar.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847739_res_0x7f0227fb, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130846278_res_0x7f022246, String.valueOf(round), String.valueOf(round2))));
            this.o.put("dietStatus", 2);
        }
    }

    private void e(gea geaVar) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "setTodayFastLiteTip weightApi == null.");
            return;
        }
        String fastingLiteStatus = weightApi.getFastingLiteStatus();
        if (fastingLiteStatus.equals("0") || fastingLiteStatus.equals("1")) {
            LogUtil.a("Suggestion_MemberPlanProvider", "getFastingLiteStatus:", fastingLiteStatus);
            this.o.put("fastingStatus", 0);
            return;
        }
        String fastingLiteViewData = weightApi.getFastingLiteViewData();
        if (TextUtils.isEmpty(fastingLiteViewData)) {
            LogUtil.h("Suggestion_MemberPlanProvider", "getFastingLiteViewData null.");
            this.o.put("fastingStatus", 0);
        } else {
            LogUtil.a("Suggestion_MemberPlanProvider", "FastingLiteViewData:", fastingLiteViewData);
            b(geaVar, fastingLiteViewData);
        }
    }

    private void b(gea geaVar, String str) {
        gsc gscVar = (gsc) HiJsonUtil.e(str, gsc.class);
        if (gscVar != null) {
            int c2 = gscVar.c();
            if (c2 == 0) {
                geaVar.d(R.string._2130847735_res_0x7f0227f7);
                geaVar.e(a(gscVar));
                this.o.put("fastingStatus", 1);
            } else if (c2 == 1) {
                geaVar.d(R.string._2130847734_res_0x7f0227f6);
                geaVar.e(a(gscVar));
                this.o.put("fastingStatus", 2);
            } else {
                if (c2 == 2) {
                    geaVar.d(R.string._2130847753_res_0x7f022809);
                    geaVar.e(0L);
                    this.o.put("fastingStatus", 0);
                    return;
                }
                LogUtil.b("Suggestion_MemberPlanProvider", "un know type.");
            }
        }
    }

    private long a(gsc gscVar) {
        long e2 = gscVar.e() - System.currentTimeMillis();
        if (e2 < 0) {
            return 0L;
        }
        return e2;
    }

    private void a(gea geaVar) {
        if (geaVar.k()) {
            geaVar.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847736_res_0x7f0227f8));
            this.o.put("recipeStatus", 0);
        } else if (c().equals("0") && geaVar.h() != IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            geaVar.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847740_res_0x7f0227fc));
            this.o.put("recipeStatus", 1);
        } else if (this.d == null || this.l == null) {
            geaVar.a("");
        } else {
            c(geaVar);
        }
    }

    private void c(gea geaVar) {
        int i = Calendar.getInstance().get(11);
        int i2 = Calendar.getInstance().get(12);
        if (e(i, i2, 10)) {
            if (c(10)) {
                geaVar.a(b(20, this.l.c()));
                this.o.put("recipeStatus", 3);
                return;
            } else {
                geaVar.a(b(10, this.l.e()));
                this.o.put("recipeStatus", 2);
                return;
            }
        }
        if (e(i, i2, 15)) {
            if (c(20)) {
                geaVar.a(b(30, this.l.a()));
                this.o.put("recipeStatus", 4);
                return;
            } else {
                geaVar.a(b(20, this.l.c()));
                this.o.put("recipeStatus", 3);
                return;
            }
        }
        if (c(30)) {
            int e2 = e(this.m);
            geaVar.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130847741_res_0x7f0227fd, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903474_res_0x7f0301b2, e2, Integer.valueOf(e2))));
            this.o.put("recipeStatus", 5);
        } else {
            geaVar.a(b(30, this.l.a()));
            this.o.put("recipeStatus", 4);
        }
    }

    private void c(final gea geaVar, final IntPlan intPlan, long j, final boolean z, final SectionBean sectionBean, final CountDownLatch countDownLatch) {
        fit fitVar = new fit();
        String num = Integer.toString(1000);
        qts qtsVar = this.j;
        if (qtsVar != null) {
            num = Integer.toString(Math.min(Math.max(1000, Math.round(qtsVar.c())), 10000));
        }
        String str = num;
        fitVar.e(str);
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            fitVar.c("1");
        } else {
            fitVar.c(c());
        }
        final String j2 = gib.j(j);
        LogUtil.a("Suggestion_MemberPlanProvider", "energy:", str, " date:", j2, " isTomorrow:", Boolean.valueOf(z));
        new fjf().getFoodRecommendDetail(intPlan.getPlanId(), j2, fitVar, new UiCallback<fiy>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.MemberPlanProvider.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.b("Suggestion_MemberPlanProvider", "errorCode:", Integer.valueOf(i), MyLocationStyle.ERROR_INFO, str2);
                MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(fiy fiyVar) {
                LogUtil.a("Suggestion_MemberPlanProvider", "getFoodRecommendDetail success.", j2);
                if (z) {
                    MemberPlanProvider.this.m = fiyVar;
                } else {
                    MemberPlanProvider.this.l = fiyVar;
                }
                MemberPlanProvider.this.e(geaVar, intPlan, sectionBean, countDownLatch);
            }
        });
    }

    private String c() {
        HiUserPreference userPreference = HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getUserPreference("custom.custom_recipes");
        if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
            LogUtil.h("Suggestion_MemberPlanProvider", "getUserPreferenceValue is null or value valid fail", "0");
            return "0";
        }
        String value = userPreference.getValue();
        LogUtil.a("Suggestion_MemberPlanProvider", "getUserPreferenceValue ", value);
        Map<String, String> a2 = moj.a(value);
        if (a2 == null || a2.get("nutrient_goal") == null) {
            return value;
        }
        LogUtil.a("Suggestion_MemberPlanProvider", "nutrient_goal:", a2.get("nutrient_goal"));
        return a2.get("nutrient_goal");
    }

    private boolean c(int i) {
        qul qulVar;
        quh quhVar = this.d;
        if (quhVar == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "mDietRecord == null || foods == null.");
            return false;
        }
        Iterator<qul> it = quhVar.a().iterator();
        while (true) {
            if (!it.hasNext()) {
                qulVar = null;
                break;
            }
            qulVar = it.next();
            if (qulVar.h() == i) {
                break;
            }
        }
        if (qulVar == null) {
            return false;
        }
        LogUtil.a("Suggestion_MemberPlanProvider", "mealType:", Integer.valueOf(i), " kcal:", Float.valueOf(qulVar.b()));
        return qulVar.b() > 0.0f;
    }

    private String b(int i, List<fiu> list) {
        if (koq.b(list)) {
            return "";
        }
        Iterator<fiu> it = list.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().g();
        }
        if (i == 10) {
            int i2 = (int) f;
            return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130848649_res_0x7f022b89, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903474_res_0x7f0301b2, i2, Integer.valueOf(i2)));
        }
        if (i == 20) {
            int i3 = (int) f;
            return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130848650_res_0x7f022b8a, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903474_res_0x7f0301b2, i3, Integer.valueOf(i3)));
        }
        if (i != 30) {
            return "";
        }
        int i4 = (int) f;
        return com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130848651_res_0x7f022b8b, com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903474_res_0x7f0301b2, i4, Integer.valueOf(i4)));
    }

    private int e(fiy fiyVar) {
        int i = 0;
        if (fiyVar == null) {
            LogUtil.b("Suggestion_MemberPlanProvider", "getTotalRecipeHeat recipes null.");
            return 0;
        }
        if (fiyVar.e() != null) {
            Iterator<fiu> it = fiyVar.e().iterator();
            while (it.hasNext()) {
                i = (int) (i + it.next().g());
            }
        }
        if (fiyVar.c() != null) {
            Iterator<fiu> it2 = fiyVar.c().iterator();
            while (it2.hasNext()) {
                i = (int) (i + it2.next().g());
            }
        }
        if (fiyVar.a() != null) {
            Iterator<fiu> it3 = fiyVar.a().iterator();
            while (it3.hasNext()) {
                i = (int) (i + it3.next().g());
            }
        }
        return i;
    }

    static class c implements HiSubscribeListener {
        private final WeakReference<MemberPlanProvider> b;

        private c(MemberPlanProvider memberPlanProvider) {
            this.b = new WeakReference<>(memberPlanProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            MemberPlanProvider memberPlanProvider = this.b.get();
            if (memberPlanProvider != null) {
                memberPlanProvider.n = list;
            } else {
                ReleaseLogUtil.a("Suggestion_MemberPlanProvider", "InnerHiSubscribeListener onResult provider is null");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ReleaseLogUtil.b("Suggestion_MemberPlanProvider", "InnerHiSubscribeListener onChange type ", Integer.valueOf(i), " changeKey ", str, " time ", Long.valueOf(j));
            MemberPlanProvider memberPlanProvider = this.b.get();
            if (memberPlanProvider != null) {
                SectionBean sectionBean = (SectionBean) memberPlanProvider.f.get();
                if (sectionBean != null) {
                    memberPlanProvider.e(memberPlanProvider.f3387a, sectionBean);
                    return;
                } else {
                    ReleaseLogUtil.a("Suggestion_MemberPlanProvider", "InnerHiSubscribeListener onChange sectionBean is null");
                    return;
                }
            }
            ReleaseLogUtil.a("Suggestion_MemberPlanProvider", "InnerHiSubscribeListener onChange provider is null");
        }
    }
}
