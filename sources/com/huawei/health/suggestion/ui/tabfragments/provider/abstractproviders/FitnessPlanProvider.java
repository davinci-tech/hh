package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.suggestion.util.ResourceJudgeUtil;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import defpackage.ary;
import defpackage.ase;
import defpackage.ffy;
import defpackage.fyw;
import defpackage.gge;
import defpackage.gib;
import defpackage.jed;
import defpackage.koq;
import defpackage.qtm;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class FitnessPlanProvider extends FitnessEntranceProvider<List<Object>> {
    private static final double DEFAULT_BFR = 20.0d;
    private static final double DEFAULT_WEIGHT = 65.0d;
    protected static final int MSG_UPDATE_MY_PLAN = 0;
    protected static final int MSG_UPDATE_RECOMMENDED_PLAN = 1;
    private static final int START_TEXT_DEFAULT_COLOR = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296937_res_0x7f0902a9);
    private static final int START_TEXT_FINISH_COLOR = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296995_res_0x7f0902e3);
    private static final String TAG = "Suggestion_FitnessPlanProvider";
    public Context mContext;
    private float mFinishRate;
    private String mPlanComplete;
    private final OnFitnessStatusChangeCallback mPlanRefreshCallback;
    private String mPlanStatus;
    private String mPlanSummary;
    private String mStartText;
    private String mTodayTask;
    private String mVipExpiredContent;
    protected WeakReference<SectionBean> mSectionBeanRef = new WeakReference<>(null);
    protected boolean mIsJump2VipView = false;
    protected double mLastWeight = DEFAULT_WEIGHT;
    protected double mLastBodyFat = DEFAULT_BFR;
    protected boolean hasWeight = false;
    protected boolean vipStop = false;
    public Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            SectionBean sectionBean = FitnessPlanProvider.this.mSectionBeanRef.get();
            if (sectionBean == null) {
                ReleaseLogUtil.c(FitnessPlanProvider.TAG, "handle message failed, cause sectionBean is null!");
                return;
            }
            if (message.what == 0) {
                sectionBean.e(message.obj);
                FitnessPlanProvider.this.setFitnessPlansData((IntPlan) ((List) message.obj).get(0));
            } else if (message.what == 1) {
                sectionBean.e(message.obj);
            } else {
                LogUtil.h(FitnessPlanProvider.this.getLogTag(), "msg not handle.", Integer.valueOf(message.what));
            }
        }
    };
    private int mStartTextColor = START_TEXT_DEFAULT_COLOR;

    public abstract void getRecommendedPlanList();

    public abstract void onClickTitleEvent(Context context);

    public abstract void setMyPlanClickListener(Context context, List<IntPlan> list, Map<String, Object> map);

    public abstract void updateMyPlans(IntPlan intPlan);

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (List<Object>) obj);
    }

    public FitnessPlanProvider() {
        e eVar = new e(this);
        this.mPlanRefreshCallback = eVar;
        ary.a().e(eVar, "PLAN_DELETE");
        ary.a().e(eVar, "PLAN_CREATE");
        ary.a().e(eVar, "PLAN_UPDATE");
        ary.a().e(eVar, "PLAN_SWITCH");
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return !CommonUtil.bu();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData");
        this.mContext = context;
        this.mSectionBeanRef = new WeakReference<>(sectionBean);
        getPlanInfo();
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, List<Object> list) {
        setData(context, hashMap, list);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        ary.a().c(this.mPlanRefreshCallback, "PLAN_DELETE");
        ary.a().c(this.mPlanRefreshCallback, "PLAN_CREATE");
        ary.a().c(this.mPlanRefreshCallback, "PLAN_UPDATE");
        ary.a().c(this.mPlanRefreshCallback, "PLAN_SWITCH");
    }

    private void setData(Context context, HashMap<String, Object> hashMap, List<Object> list) {
        hashMap.put("TITLE", getSubViewTitle());
        if (koq.e((Object) list, PlanInfo.class)) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", true);
            addRecommendPlanToMap(list, hashMap);
            setRecommendPlanClickListener(context, list, hashMap);
        } else {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
            addMyPlanToMap(list, hashMap);
            setMyPlanClickListener(context, list, hashMap);
        }
    }

    private void addRecommendPlanToMap(List<FitnessPackageInfo> list, Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (FitnessPackageInfo fitnessPackageInfo : list) {
            if (fitnessPackageInfo != null) {
                arrayList.add(fitnessPackageInfo.acquireCardPicture());
                arrayList2.add(fitnessPackageInfo.acquireName());
                if (TextUtils.isEmpty(fitnessPackageInfo.acquireDescription())) {
                    LogUtil.a(getLogTag(), "workoutPlanInfo:", fitnessPackageInfo.acquireName(), fitnessPackageInfo.acquirePlanTempId());
                } else {
                    arrayList3.add(fitnessPackageInfo.acquireDescription().trim());
                }
                arrayList4.add(fitnessPackageInfo.getPriceTagBeanList());
            }
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put("DESCRIPTION", arrayList3);
        map.put("CORNER_VIEW", arrayList4);
    }

    private void addMyPlanToMap(List<IntPlan> list, Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        Iterator<IntPlan> it = list.iterator();
        while (it.hasNext()) {
            IntPlan next = it.next();
            if (next.getIntroduction() != null && !TextUtils.isEmpty(next.getIntroduction().acquirePreviewImgUrl())) {
                arrayList.add(next.getIntroduction().acquirePreviewImgUrl());
            } else if (!TextUtils.isEmpty(next.getMetaInfo().getCardImage())) {
                arrayList.add(next.getMetaInfo().getCardImage());
            } else {
                arrayList.add(next.getMetaInfo().getPicture());
            }
            if (TextUtils.isEmpty(this.mVipExpiredContent)) {
                arrayList2.add(next.getMetaInfo().getName());
            } else {
                arrayList2.add(this.mVipExpiredContent);
            }
            arrayList3.add(this.mPlanComplete);
            arrayList4.add(this.mTodayTask);
            Iterator<IntPlan> it2 = it;
            ArrayList arrayList11 = arrayList4;
            arrayList5.add(ffy.d(BaseApplication.getContext(), R.string._2130848449_res_0x7f022ac1, jed.b(this.mFinishRate, 2, 1)));
            arrayList8.add(Integer.valueOf((int) this.mFinishRate));
            String str = this.mPlanSummary;
            if (str != null) {
                arrayList9.add(str);
            }
            String str2 = this.mStartText;
            if (str2 != null) {
                arrayList6.add(str2);
                arrayList7.add(Integer.valueOf(this.mStartTextColor));
            }
            String str3 = this.mPlanStatus;
            if (str3 != null) {
                arrayList10.add(str3);
            }
            it = it2;
            arrayList4 = arrayList11;
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put("COMPLETE_SITUATION", arrayList3);
        map.put("TODAY_TASK", arrayList4);
        map.put("FINISH_RATE", arrayList5);
        map.put("SUMMARY_TEXT", arrayList9);
        map.put(CommonConstant.RETKEY.STATUS, arrayList10);
        map.put("BUTTON_TEXT", arrayList6);
        map.put("BUTTON_TEXT_COLOR", arrayList7);
        map.put("PROGRESS_VALUE", arrayList8);
    }

    private void setRecommendPlanClickListener(final Context context, final List<FitnessPackageInfo> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("MORE_CLICK_EVENT".equals(str)) {
                    FitnessPlanProvider.this.onClickTitleEvent(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b(FitnessPlanProvider.this.getLogTag(), "position is out of bounds");
                    return;
                }
                FitnessPackageInfo fitnessPackageInfo = (FitnessPackageInfo) list.get(i);
                if (fitnessPackageInfo == null) {
                    LogUtil.b(FitnessPlanProvider.this.getLogTag(), "workoutPlanInfo is null");
                    return;
                }
                IntPlan.PlanType planType = IntPlan.PlanType.getPlanType(fitnessPackageInfo.getPlanType());
                String acquirePlanTempId = fitnessPackageInfo.acquirePlanTempId();
                int acquireDisplayStyle = fitnessPackageInfo.acquireDisplayStyle();
                if (planType != null) {
                    if (planType.equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                        FitnessPlanProvider.this.goToCreateAiFitnessPlan(planType, acquirePlanTempId);
                    } else if (planType.equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                        ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
                    } else {
                        JumpUtil.d(planType.getType(), acquirePlanTempId, acquireDisplayStyle, FitnessPlanProvider.this.mContext);
                        FitnessPlanProvider.this.reportGeneralPlanBi(fitnessPackageInfo);
                    }
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportGeneralPlanBi(FitnessPackageInfo fitnessPackageInfo) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("type", 3);
        hashMap.put("planType", Integer.valueOf(fitnessPackageInfo.getPlanType()));
        hashMap.put("planName", fitnessPackageInfo.acquireName());
        if (ase.d(fitnessPackageInfo)) {
            hashMap.put("enter", "0");
        } else {
            hashMap.put("enter", "1");
        }
        gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToCreateAiFitnessPlan(IntPlan.PlanType planType, String str) {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new d(planType.getType(), str, this), "");
    }

    static class d implements IBaseResponseCallback {
        private int b;
        private WeakReference<FitnessPlanProvider> d;
        private String e;

        d(int i, String str, FitnessPlanProvider fitnessPlanProvider) {
            this.b = i;
            this.e = str;
            this.d = new WeakReference<>(fitnessPlanProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            FitnessPlanProvider fitnessPlanProvider = this.d.get();
            if (fitnessPlanProvider == null) {
                LogUtil.b(FitnessPlanProvider.TAG, "fitnessPlanProvider is null");
            } else if (i == 0) {
                JumpUtil.d(this.b, this.e, 0, "fitness", "", fitnessPlanProvider.mContext);
            } else {
                LogUtil.a(fitnessPlanProvider.getLogTag(), "LoginInit is not success", Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setFitnessPlansData(com.huawei.basefitnessadvice.model.intplan.IntPlan r8) {
        /*
            r7 = this;
            java.util.List r0 = defpackage.fys.b(r8)
            boolean r1 = defpackage.koq.c(r0)
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L3a
            java.util.Iterator r1 = r0.iterator()
            r4 = r2
        L11:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L2c
            java.lang.Object r5 = r1.next()
            com.huawei.basefitnessadvice.model.intplan.IntAction r5 = (com.huawei.basefitnessadvice.model.intplan.IntAction) r5
            if (r5 != 0) goto L20
            goto L11
        L20:
            int r6 = r5.getPunchFlag()
            if (r6 != 0) goto L11
            if (r3 != 0) goto L29
            r3 = r5
        L29:
            int r4 = r4 + 1
            goto L11
        L2c:
            int r1 = r0.size()
            r5 = 1
            if (r1 <= r5) goto L3b
            int r0 = r0.size()
            if (r4 >= r0) goto L3b
            r2 = r5
        L3a:
            r5 = r2
        L3b:
            r7.setHolderPlanStart(r8)
            boolean r0 = defpackage.fyw.l(r8)
            if (r0 != 0) goto L48
            r7.showVipExpiredView(r8)
            return
        L48:
            int r0 = defpackage.ase.g(r8)
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            r4 = 7
            int r1 = r1.get(r4)
            int r1 = defpackage.gib.d(r1)
            boolean r0 = defpackage.ase.b(r8, r0, r1)
            if (r5 != 0) goto L63
            r7.showRestAndCompletionDayLayout(r8, r0)
            goto L83
        L63:
            if (r3 != 0) goto L80
            android.content.Context r0 = r7.mContext
            r1 = 2130844995(0x7f021d43, float:1.7295157E38)
            java.lang.String r0 = r0.getString(r1)
            r7.mTodayTask = r0
            android.content.Context r0 = r7.mContext
            r1 = 2130844734(0x7f021c3e, float:1.7294628E38)
            java.lang.String r0 = r0.getString(r1)
            r7.mStartText = r0
            int r0 = com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider.START_TEXT_FINISH_COLOR
            r7.mStartTextColor = r0
            goto L83
        L80:
            r7.clickStartIntPlanCourse(r3, r2)
        L83:
            java.lang.ref.WeakReference<com.huawei.health.knit.section.model.SectionBean> r0 = r7.mSectionBeanRef
            java.lang.Object r0 = r0.get()
            com.huawei.health.knit.section.model.SectionBean r0 = (com.huawei.health.knit.section.model.SectionBean) r0
            if (r0 != 0) goto L99
            java.lang.String r8 = "setFitnessPlansData failed, cause sectionBean is null!"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r0 = "Suggestion_FitnessPlanProvider"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r8)
            return
        L99:
            java.lang.Object r1 = r0.e()
            r0.e(r1)
            r7.showVipExpiredView(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider.setFitnessPlansData(com.huawei.basefitnessadvice.model.intplan.IntPlan):void");
    }

    private void showRestAndCompletionDayLayout(IntPlan intPlan, boolean z) {
        if (z) {
            this.mTodayTask = this.mContext.getString(R.string._2130848765_res_0x7f022bfd);
        } else {
            this.mTodayTask = this.mContext.getString(R.string._2130848447_res_0x7f022abf);
        }
        this.mStartText = null;
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN && ase.g(intPlan) > 1) {
            this.mStartText = this.mContext.getString(R.string._2130844994_res_0x7f021d42);
        }
        this.mStartTextColor = START_TEXT_DEFAULT_COLOR;
        if (intPlan.getPlanType() != IntPlan.PlanType.AI_FITNESS_PLAN || this.vipStop) {
            return;
        }
        initRestDayWeight();
    }

    private void setHolderPlanStart(IntPlan intPlan) {
        if (intPlan.getStat("progress") != null) {
            this.mFinishRate = ((Float) intPlan.getStat("progress").getValue()).floatValue();
        }
        int e2 = fyw.e(intPlan);
        if (e2 > intPlan.getMetaInfo().getDayCount()) {
            this.mPlanStatus = BaseApplication.getContext().getString(R.string._2130846125_res_0x7f0221ad);
        } else if (e2 <= 0) {
            this.mPlanStatus = this.mContext.getResources().getString(R.string._2130846123_res_0x7f0221ab, DateUtils.formatDateTime(this.mContext, intPlan.getPlanTimeInfo().getBeginDate() * 1000, 24));
        } else {
            this.mPlanComplete = ffy.c(this.mContext, e2, intPlan.getMetaInfo().getDayCount(), 0);
            this.mPlanStatus = null;
        }
        SectionBean sectionBean = this.mSectionBeanRef.get();
        if (sectionBean == null) {
            ReleaseLogUtil.c(TAG, "setHolderPlanStart failed, cause sectionBean is null!");
        } else {
            sectionBean.e(sectionBean.e());
        }
    }

    private void clickStartIntPlanCourse(IntAction intAction, boolean z) {
        if (z) {
            this.mStartText = BaseApplication.getContext().getString(R.string._2130844993_res_0x7f021d41);
        } else {
            this.mStartText = BaseApplication.getContext().getString(R.string._2130848439_res_0x7f022ab7);
        }
        this.mStartTextColor = START_TEXT_DEFAULT_COLOR;
        if (intAction.getActionInfo() != null) {
            this.mTodayTask = intAction.getActionInfo().d();
            SectionBean sectionBean = this.mSectionBeanRef.get();
            if (sectionBean == null) {
                ReleaseLogUtil.c(TAG, "clickStartIntPlanCourse failed, cause sectionBean is null!");
            } else {
                sectionBean.e(sectionBean.e());
            }
        }
    }

    private void showVipExpiredView(IntPlan intPlan) {
        int i;
        this.mVipExpiredContent = "";
        this.mIsJump2VipView = false;
        if (intPlan == null) {
            return;
        }
        String planTempId = intPlan.getPlanTempId();
        if (intPlan.getPlanType().getType() == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
            planTempId = String.valueOf(intPlan.getPlanType());
            i = 5;
        } else {
            i = 2;
        }
        ResourceJudgeUtil.c(i, planTempId, intPlan.getTransactionStatus(), new JudgeCallback() { // from class: gfj
            @Override // com.huawei.health.suggestion.callback.JudgeCallback
            public final void onJudgeCallback(Object obj) {
                FitnessPlanProvider.this.m519xa84cbf20((Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showVipExpiredView$0$com-huawei-health-suggestion-ui-tabfragments-provider-abstractproviders-FitnessPlanProvider, reason: not valid java name */
    public /* synthetic */ void m519xa84cbf20(Integer num) {
        if (num.equals(2) || num.equals(3)) {
            this.vipStop = true;
            setVipExpiredData();
        } else {
            this.vipStop = false;
        }
        SectionBean sectionBean = this.mSectionBeanRef.get();
        if (sectionBean == null) {
            ReleaseLogUtil.c(TAG, "showVipExpiredView failed, cause sectionBean is null!");
        } else {
            sectionBean.e(sectionBean.e());
        }
    }

    private void setVipExpiredData() {
        this.mIsJump2VipView = true;
        this.mTodayTask = BaseApplication.getContext().getResources().getString(R.string._2130848620_res_0x7f022b6c);
        this.mVipExpiredContent = BaseApplication.getContext().getResources().getString(R.string._2130848621_res_0x7f022b6d);
        this.mStartText = BaseApplication.getContext().getResources().getString(R.string._2130844867_res_0x7f021cc3);
        this.mStartTextColor = START_TEXT_DEFAULT_COLOR;
    }

    private void initRestDayWeight() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(gib.b(System.currentTimeMillis()), System.currentTimeMillis());
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            weightApi.getWeightData(hiAggregateOption, new c(this));
        } else {
            LogUtil.h(getLogTag(), "weightApi is null.");
        }
        SectionBean sectionBean = this.mSectionBeanRef.get();
        if (sectionBean == null) {
            ReleaseLogUtil.c(TAG, "initRestDayWeight failed, cause sectionBean is null!");
        } else {
            sectionBean.e(sectionBean.e());
        }
    }

    public static class c implements WeightCallback {
        private WeakReference<FitnessPlanProvider> e;

        c(FitnessPlanProvider fitnessPlanProvider) {
            this.e = new WeakReference<>(fitnessPlanProvider);
        }

        @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
        public void getWeight(final ArrayList<qtm> arrayList) {
            WeakReference<FitnessPlanProvider> weakReference = this.e;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.h(FitnessPlanProvider.TAG, "mProviderReference is destroyed.");
            } else {
                final FitnessPlanProvider fitnessPlanProvider = this.e.get();
                HandlerExecutor.e(new Runnable() { // from class: gfg
                    @Override // java.lang.Runnable
                    public final void run() {
                        FitnessPlanProvider.this.processLatestWeight(arrayList);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processLatestWeight(ArrayList<qtm> arrayList) {
        if (arrayList.size() == 0) {
            LogUtil.a(getLogTag(), "processLatestWeight has no record");
            this.mStartText = this.mContext.getString(R.string._2130848647_res_0x7f022b87);
        } else {
            qtm qtmVar = arrayList.get(0);
            LogUtil.a(getLogTag(), "processLatestWeight has record ", Double.valueOf(qtmVar.e()));
            if (qtmVar.e() > 0.0d) {
                this.mPlanSummary = ffy.d(this.mContext, R.string._2130849775_res_0x7f022fef, jed.b(qtmVar.e(), 1, 1));
                this.hasWeight = true;
                this.mLastWeight = qtmVar.e();
                this.mLastBodyFat = qtmVar.b();
            }
        }
        SectionBean sectionBean = this.mSectionBeanRef.get();
        if (sectionBean == null) {
            ReleaseLogUtil.c(TAG, "processLatestWeight failed, cause sectionBean is null!");
        } else {
            sectionBean.e(sectionBean.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPlanInfo() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider.3
            @Override // java.lang.Runnable
            public void run() {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.h(FitnessPlanProvider.this.getLogTag(), "getMyPlanInfo : planApi is null.");
                } else {
                    planApi.getCurrentIntPlan(new b(FitnessPlanProvider.this));
                }
            }
        });
    }

    static class b extends UiCallback<IntPlan> {
        WeakReference<FitnessPlanProvider> d;

        b(FitnessPlanProvider fitnessPlanProvider) {
            this.d = new WeakReference<>(fitnessPlanProvider);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<FitnessPlanProvider> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h(FitnessPlanProvider.TAG, "GetMyPlanCallback onFailure mReference is null.");
                return;
            }
            FitnessPlanProvider fitnessPlanProvider = weakReference.get();
            if (fitnessPlanProvider != null) {
                LogUtil.h(fitnessPlanProvider.getLogTag(), "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                fitnessPlanProvider.getRecommendedPlanList();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            WeakReference<FitnessPlanProvider> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h(FitnessPlanProvider.TAG, "GetMyPlanCallback onSuccess mReference is null.");
                return;
            }
            FitnessPlanProvider fitnessPlanProvider = weakReference.get();
            if (fitnessPlanProvider != null) {
                fitnessPlanProvider.updateMyPlans(intPlan);
            }
        }
    }

    static class e implements OnFitnessStatusChangeCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<FitnessPlanProvider> f3406a;

        e(FitnessPlanProvider fitnessPlanProvider) {
            this.f3406a = new WeakReference<>(fitnessPlanProvider);
        }

        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public void onUpdate() {
            FitnessPlanProvider fitnessPlanProvider = this.f3406a.get();
            if (fitnessPlanProvider != null) {
                fitnessPlanProvider.getPlanInfo();
            } else {
                LogUtil.h(FitnessPlanProvider.TAG, "OnFitnessStatusChangeCallback provider == null");
            }
        }
    }
}
