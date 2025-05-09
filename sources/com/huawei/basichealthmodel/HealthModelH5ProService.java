package com.huawei.basichealthmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.HealthModelH5ProService;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.basichealthmodel.bean.ConfigDetailBean;
import com.huawei.basichealthmodel.callback.HealthModelH5ProCallback;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import defpackage.auz;
import defpackage.awq;
import defpackage.azi;
import defpackage.azw;
import defpackage.bao;
import defpackage.bbb;
import defpackage.bcc;
import defpackage.bcl;
import defpackage.bcm;
import defpackage.bdh;
import defpackage.drx;
import defpackage.dsb;
import defpackage.dsl;
import defpackage.gnm;
import defpackage.grz;
import defpackage.jdl;
import defpackage.koq;
import defpackage.niv;
import defpackage.nix;
import defpackage.njj;
import defpackage.nrh;
import defpackage.nsg;
import defpackage.quh;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "HealthModel", users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class HealthModelH5ProService {
    private static final String TAG = "HealthLife_HealthModelH5ProService";

    private HealthModelH5ProService() {
    }

    private static Context getContext() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ != null) {
            return wa_;
        }
        LogUtil.h(TAG, "getContext activity is null");
        return BaseApplication.e();
    }

    @H5ProMethod(name = "jumpH5ProApp")
    public static void jumpH5ProApp(String str, String str2) {
        LogUtil.a(TAG, "jumpH5ProApp packageName ", str, " path ", str2);
        azi.b(getContext(), str, str2);
    }

    @H5ProMethod(name = "jumpUrl")
    public static void jumpUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "jumpUrl url is ", str);
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.h(TAG, "jumpUrl api is null url ", str);
        } else {
            marketRouterApi.router(str);
            LogUtil.a(TAG, "jumpUrl url ", str);
        }
    }

    @H5ProMethod(name = "jumpMindfulness")
    public static void jumpMindfulness() {
        ConfigDetailBean detailEntity = azi.d(awq.e(), 9).getDetailEntity();
        if (detailEntity == null) {
            LogUtil.h(TAG, "jumpMindfulness healthTaskDetailEntity is null");
            return;
        }
        String uri = detailEntity.getUri();
        if (TextUtils.isEmpty(uri)) {
            jumpUrl(detailEntity.getUrl());
        } else {
            gnm.aPB_(getContext(), new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(uri)));
        }
        LogUtil.a(TAG, "jumpMindfulness");
    }

    @H5ProMethod(name = "jumpSleep")
    public static void jumpSleep(boolean z) {
        AppRouter.b("/Main/KnitSleepDetailActivity").c("from", 9).c(getContext());
        LogUtil.a(TAG, "jumpSleep isDetail ", Boolean.valueOf(z));
    }

    @H5ProMethod(name = "jumpGoalManager")
    public static void jumpGoalManager() {
        Activity activity;
        String str;
        Context context = getContext();
        if (context instanceof Activity) {
            activity = (Activity) context;
            str = activity.getClass().getSimpleName();
        } else {
            activity = null;
            str = "";
        }
        Activity activity2 = activity;
        String str2 = str;
        if (activity2 != null && "HealthModelActivity".equals(str2)) {
            AppRouter.b("/PluginHealthModel/HealthModelGoalManagerActivity").zD_(activity2, 2000);
        } else {
            AppRouter.b("/PluginHealthModel/HealthModelGoalManagerActivity").c(context);
        }
        LogUtil.a(TAG, "jumpGoalManager context ", context, " activity ", activity2, " simpleName ", str2);
    }

    @H5ProMethod(name = "jumpGuide")
    public static void jumpGuide(String str, String str2, String str3, String str4) {
        LogUtil.a(TAG, "jumpGuide idListText=", str, ",basicIdText=", str2, ",goalIdListText=", str3, ",goalIdText=", str4);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return;
        }
        try {
            ArrayList<String> arrayList = (ArrayList) HiJsonUtil.b(str, new TypeToken<ArrayList<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.4
            }.getType());
            ArrayList<String> arrayList2 = (ArrayList) HiJsonUtil.b(str2, new TypeToken<ArrayList<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.15
            }.getType());
            ArrayList<String> arrayList3 = (ArrayList) HiJsonUtil.b(str3, new TypeToken<ArrayList<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.12
            }.getType());
            ArrayList arrayList4 = (ArrayList) HiJsonUtil.b(str4, new TypeToken<ArrayList<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.13
            }.getType());
            if (!koq.b(arrayList) && !koq.b(arrayList2) && !koq.b(arrayList3) && !koq.b(arrayList4)) {
                Bundle bundle = new Bundle();
                bundle.putInt("goalId", CommonUtil.h((String) arrayList4.get(0)));
                bundle.putStringArrayList("idList", arrayList);
                bundle.putStringArrayList("basicIdList", arrayList2);
                bundle.putStringArrayList("goalIdList", arrayList3);
                AppRouter.b("/PluginHealthModel/HealthModelGuideActivity").zF_(bundle).c(getContext());
                return;
            }
            LogUtil.h(TAG, "jumpGuide taskId convert error");
        } catch (JsonParseException e) {
            LogUtil.b(TAG, "jumpGuide exception=", LogAnonymous.b((Throwable) e));
        }
    }

    @H5ProMethod(name = "jumpInputWeight")
    public static void jumpInputWeight() {
        AppRouter.b("/Main/InputWeightActivity").c(getContext());
        LogUtil.a(TAG, "jumpInputWeight");
    }

    @H5ProMethod(name = "getJoinTime")
    public static String getJoinTime() {
        int t = azi.t();
        LogUtil.a(TAG, "getJoinTime joinTime ", Integer.valueOf(t));
        return t <= 0 ? "" : String.valueOf(t);
    }

    @H5ProMethod(name = "getUpgradeTime")
    public static String getUpgradeTime() {
        int q = azi.q();
        LogUtil.a(TAG, "getUpgradeTime upgradeTime ", Integer.valueOf(q));
        return q <= 0 ? "" : String.valueOf(q);
    }

    @H5ProMethod(name = "getChallengeId")
    public static String getChallengeId() {
        String e = bao.e("health_model_challenge_id");
        LogUtil.a(TAG, "getChallengeId challengeId ", e);
        return (TextUtils.isEmpty(e) || e.equals(String.valueOf(AwarenessConstants.ERROR_NO_PERMISSION_CODE))) ? "" : e;
    }

    @H5ProMethod(name = "getChallengeIdList")
    public static String getChallengeIdList() {
        int challengeId;
        List<ChallengeConfigBean> c = bcm.c(awq.e().a().getChallengeList());
        LogUtil.a(TAG, "getChallengeIdList list ", c);
        if (koq.b(c)) {
            return "";
        }
        ArrayList<Integer> j = dsl.j();
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = j.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue > 1 && intValue != 11) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        HashMap hashMap = new HashMap();
        for (ChallengeConfigBean challengeConfigBean : c) {
            if (challengeConfigBean != null && (challengeId = challengeConfigBean.getChallengeId()) != 200001) {
                hashMap.put(Integer.valueOf(challengeId), arrayList);
            }
        }
        String e = HiJsonUtil.e(hashMap);
        LogUtil.a(TAG, "getChallengeIdList json ", e);
        return e;
    }

    @H5ProMethod(name = "getTaskIdList")
    public static String getTaskIdList() {
        ArrayList<Integer> j = dsl.j();
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = j.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue > 1 && intValue != 11) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        String e = HiJsonUtil.e(arrayList);
        LogUtil.a(TAG, "getTaskIdList taskIdList ", j, " json ", e);
        return e;
    }

    @H5ProMethod(name = "isBloodPressurePlan")
    public static boolean isBloodPressurePlan() {
        boolean y = azi.y();
        LogUtil.a(TAG, "isBloodPressurePlan isBloodPressurePlan ", Boolean.valueOf(y));
        return y;
    }

    @H5ProMethod(name = "isIntensityRestDoubleToday")
    public static boolean isIntensityRestDoubleToday() {
        boolean d = bdh.d();
        LogUtil.a(TAG, "isIntensityRestDoubleToday isIntensityRestDoubleToday ", Boolean.valueOf(d));
        return d;
    }

    private static String getBenefitOrSuggestion(String str, String str2, boolean z) {
        String str3;
        LogUtil.a(TAG, "getBenefitOrSuggestion taskIdText ", str, " challengeIdText ", str2, " isBenefit ", Boolean.valueOf(z));
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            return "";
        }
        if (z) {
            str3 = bcl.e(h, h2, false);
        } else {
            ArrayList<String> d = bcc.d(bcc.a(h, h2).a(), (HashMap<String, String>) null);
            if (koq.b(d)) {
                LogUtil.h(TAG, "getBenefitOrSuggestion stringList ", d);
                return "";
            }
            str3 = d.get(nsg.b(d.size()));
        }
        LogUtil.a(TAG, "getBenefitOrSuggestion text ", str3);
        return str3;
    }

    @H5ProMethod(name = "getBenefit")
    public static String getBenefit(String str, String str2) {
        return getBenefitOrSuggestion(str, str2, true);
    }

    @H5ProMethod(name = "getSuggestion")
    public static String getSuggestion(String str, String str2) {
        return getBenefitOrSuggestion(str, str2, false);
    }

    /* renamed from: com.huawei.basichealthmodel.HealthModelH5ProService$11, reason: invalid class name */
    /* loaded from: classes8.dex */
    public class AnonymousClass11 extends HandlerThread {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HealthModelH5ProCallback f1895a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass11(String str, HealthModelH5ProCallback healthModelH5ProCallback) {
            super(str);
            this.f1895a = healthModelH5ProCallback;
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            if (this.f1895a == null) {
                LogUtil.h(HealthModelH5ProService.TAG, "getTaskTip callback is null");
                return;
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final StringBuilder sb = new StringBuilder();
            HealthModelH5ProService.getTextForTaskTip(new ResponseCallback() { // from class: aua
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.AnonymousClass11.d(sb, countDownLatch, i, (String) obj);
                }
            });
            CountDownTimer countDownTimer = new CountDownTimer(2000L, 2000L) { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.11.3
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    LogUtil.a(HealthModelH5ProService.TAG, "getTaskTip onFinish");
                    cancel();
                    countDownLatch.countDown();
                }
            };
            countDownTimer.start();
            azi.d(countDownLatch, "getTaskTip");
            countDownTimer.cancel();
            String sb2 = sb.toString();
            HealthModelH5ProCallback healthModelH5ProCallback = this.f1895a;
            if (TextUtils.isEmpty(sb2)) {
                sb2 = BaseApplication.e().getResources().getString(R$string.IDS_task_tip);
            }
            healthModelH5ProCallback.onSuccess(sb2);
        }

        public static /* synthetic */ void d(StringBuilder sb, CountDownLatch countDownLatch, int i, String str) {
            LogUtil.a(HealthModelH5ProService.TAG, "getTaskTip result ", Integer.valueOf(i), " text ", str);
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
            }
            countDownLatch.countDown();
        }
    }

    @H5ProMethod(name = "getTaskTip")
    public static void getTaskTip(HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        new AnonymousClass11("getTaskTip", healthModelH5ProCallback).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getTextForTaskTip(final ResponseCallback<String> responseCallback) {
        awq.e().b(DateFormatUtil.b(System.currentTimeMillis()), new TaskDayDataListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.14
            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onDataChange(int i, HealthLifeBean healthLifeBean) {
            }

            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onAllDataChange(int i, List<HealthLifeBean> list) {
                LogUtil.a(HealthModelH5ProService.TAG, "getTextForTaskTip result ", Integer.valueOf(i), " healthTaskBeanList ", list);
                ArrayList idListForTaskTip = HealthModelH5ProService.getIdListForTaskTip(list);
                if (koq.b(idListForTaskTip)) {
                    ResponseCallback.this.onResponse(-1, "");
                    return;
                }
                ArrayList<String> d = bcc.d(bcc.d("", ((Integer) idListForTaskTip.get(nsg.b(idListForTaskTip.size()))).intValue()).a(), (HashMap<String, String>) null);
                if (koq.b(d)) {
                    LogUtil.h(HealthModelH5ProService.TAG, "getTextForTaskTip tipList ", d);
                    ResponseCallback.this.onResponse(-1, "");
                    return;
                }
                String str = d.get(nsg.b(d.size()));
                LogUtil.a(HealthModelH5ProService.TAG, "getTextForTaskTip text ", str);
                if (TextUtils.isEmpty(str)) {
                    ResponseCallback.this.onResponse(-1, "");
                } else {
                    ResponseCallback.this.onResponse(0, str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Integer> getIdListForTaskTip(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            return new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                if (azi.c(id) && id > 1 && id < 11) {
                    arrayList.add(Integer.valueOf(id));
                    if (healthLifeBean.getComplete() <= 0) {
                        arrayList2.add(Integer.valueOf(id));
                    }
                }
            }
        }
        if (koq.b(arrayList)) {
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList3 = new ArrayList<>();
        if (koq.c(arrayList2)) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (!koq.b(bcc.d("", intValue).a())) {
                    arrayList3.add(Integer.valueOf(intValue));
                }
            }
        }
        if (koq.c(arrayList3)) {
            return arrayList3;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int intValue2 = ((Integer) it2.next()).intValue();
            if (!koq.b(bcc.d("", intValue2).a())) {
                arrayList3.add(Integer.valueOf(intValue2));
            }
        }
        return arrayList3;
    }

    @H5ProMethod(name = "getRewardsComplete")
    public static void getRewardsComplete(final String str, final String str2, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: att
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getRewardsComplete(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getRewardsComplete callback is null");
            return;
        }
        LogUtil.a(TAG, "getRewardsComplete ", str, str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        List<HealthLifeBean> d = auz.d(h, h2, azi.p());
        if (koq.b(d)) {
            LogUtil.h(TAG, "getRewardsComplete list ", d);
            healthModelH5ProCallback.onFailure(-1, "getRewardsComplete list is empty");
        } else {
            int c = azi.c(d);
            LogUtil.a(TAG, "getRewardsComplete rewardsCompleted ", Integer.valueOf(c));
            healthModelH5ProCallback.onSuccess(Integer.valueOf(c));
        }
    }

    @H5ProMethod(name = "getCompleteForTask")
    public static void getCompleteForTask(final String str, final String str2, final String str3, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atk
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getCompleteForTask(str, str2, str3, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getCompleteForTask callback is null");
            return;
        }
        LogUtil.a(TAG, "getCompleteForTask ", str, str2, " taskIdText ", str3);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        int h3 = CommonUtils.h(str3);
        if (h <= 0 || h2 <= 0 || !azi.c(h3)) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        int a2 = auz.a(h, h2, h3);
        LogUtil.a(TAG, "getCompleteForTask completedTask ", Integer.valueOf(a2));
        healthModelH5ProCallback.onSuccess(Integer.valueOf(a2));
    }

    @H5ProMethod(name = "getCompleteArray")
    public static void getCompleteArray(final String str, final String str2, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        int a2;
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atw
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getCompleteArray(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getCompleteArray callback is null");
            return;
        }
        LogUtil.a(TAG, "getCompleteArray ", str, str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        ArrayList<Integer> j = dsl.j();
        HashMap hashMap = new HashMap();
        Iterator<Integer> it = j.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (azi.c(intValue) && intValue > 1 && intValue != 11 && (a2 = auz.a(h, h2, intValue)) > 0) {
                hashMap.put(Integer.valueOf(intValue), Integer.valueOf(a2));
            }
        }
        String e = HiJsonUtil.e(hashMap);
        LogUtil.a(TAG, "getCompleteArray json ", e);
        healthModelH5ProCallback.onSuccess(e);
    }

    @H5ProMethod(name = "getComplete")
    public static void getComplete(final String str, final String str2, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atq
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getComplete(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getComplete callback is null");
            return;
        }
        LogUtil.a(TAG, "getComplete ", str, str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        int i = azi.lJ_(h, h2).get(0);
        LogUtil.a(TAG, "getComplete completeNumber ", Integer.valueOf(i));
        healthModelH5ProCallback.onSuccess(Integer.valueOf(i));
    }

    @H5ProMethod(name = "getClover")
    public static void getClover(final String str, final String str2, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atp
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getClover(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getClover callback is null");
            return;
        }
        LogUtil.a(TAG, "getClover ", str, str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        int i = azi.lJ_(h, h2).get(1);
        LogUtil.a(TAG, "getClover completeNumber ", Integer.valueOf(i));
        healthModelH5ProCallback.onSuccess(Integer.valueOf(i));
    }

    @H5ProMethod(name = "getTaskSubscriptionList")
    public static void getTaskSubscriptionList(final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atf
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getTaskSubscriptionList(HealthModelH5ProCallback.this);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getTaskSubscriptionList callback is null");
            return;
        }
        SparseArray<HealthLifeBean> kE_ = awq.e().kE_(azi.p(), DateFormatUtil.b(jdl.y(System.currentTimeMillis())), false);
        int size = kE_.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            int keyAt = kE_.keyAt(i);
            if (azi.c(keyAt) && keyAt > 1) {
                arrayList.add(kE_.get(keyAt));
            }
        }
        String d = HiJsonUtil.d(arrayList, new TypeToken<ArrayList<HealthLifeBean>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.18
        }.getType());
        LogUtil.a(TAG, "getTaskSubscriptionList json ", d);
        healthModelH5ProCallback.onSuccess(d);
    }

    @H5ProMethod(name = "updateTaskSubscriptionList")
    public static void updateTaskSubscriptionList(final String str, final String str2, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atg
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.updateTaskSubscriptionList(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "updateTaskSubscriptionList callback is null");
            return;
        }
        LogUtil.a(TAG, "updateTaskSubscriptionList taskIdText ", str, " targetText ", str2);
        final int h = CommonUtils.h(str);
        final int h2 = CommonUtils.h(str2);
        if (!azi.c(h) || h2 < 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            if (!CommonUtil.aa(BaseApplication.e())) {
                String string = BaseApplication.e().getResources().getString(com.huawei.health.servicesui.R$string.IDS_hw_show_set_about_privacy_connectting_error);
                nrh.d(getContext(), string);
                healthModelH5ProCallback.onFailure(-1, string);
                return;
            }
            awq.e().e(getUpdateList(awq.e().kE_(azi.p(), DateFormatUtil.b(jdl.y(System.currentTimeMillis())), false), h, h2), new ResponseCallback() { // from class: ate
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.lambda$updateTaskSubscriptionList$7(h, h2, healthModelH5ProCallback, i, (SparseArray) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$updateTaskSubscriptionList$7(int i, int i2, HealthModelH5ProCallback healthModelH5ProCallback, int i3, SparseArray sparseArray) {
        LogUtil.a(TAG, "updateTaskSubscriptionList resultCode ", Integer.valueOf(i3), " taskId ", Integer.valueOf(i), " target ", Integer.valueOf(i2));
        processTaskResult(i3, i, healthModelH5ProCallback);
    }

    private static void processTaskResult(int i, int i2, HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (i == 0) {
            bbb.a(i2);
            healthModelH5ProCallback.onSuccess(0);
            return;
        }
        if (i != 1003) {
            if (i == 12030008) {
                bbb.b();
                healthModelH5ProCallback.onFailure(i, "");
                return;
            } else if (i != 12030010) {
                bbb.e();
                healthModelH5ProCallback.onFailure(i, "");
                return;
            }
        }
        bbb.a();
        healthModelH5ProCallback.onFailure(i, "");
    }

    private static ArrayList<HealthLifeBean> getUpdateList(SparseArray<HealthLifeBean> sparseArray, int i, int i2) {
        LogUtil.a(TAG, "getUpdateList sparseArray ", sparseArray, " taskId ", Integer.valueOf(i), " target ", Integer.valueOf(i2));
        if (sparseArray == null) {
            return new ArrayList<>();
        }
        int size = sparseArray.size();
        ArrayList<HealthLifeBean> arrayList = new ArrayList<>();
        int b = DateFormatUtil.b(jdl.y(System.currentTimeMillis()));
        for (int i3 = 0; i3 < size; i3++) {
            HealthLifeBean healthLifeBean = sparseArray.get(sparseArray.keyAt(i3));
            if (healthLifeBean != null) {
                healthLifeBean.setRecordDay(b);
                int id = healthLifeBean.getId();
                if (id == 1) {
                    healthLifeBean.setLastTarget(healthLifeBean.getTarget());
                    arrayList.add(healthLifeBean);
                } else {
                    if (id == i && ((i == 2 || i == 3) && i2 > 0)) {
                        healthLifeBean.setLastTarget(healthLifeBean.getTarget());
                        healthLifeBean.setTarget(String.valueOf(i2));
                        arrayList.add(healthLifeBean);
                    }
                    if (!arrayList.contains(healthLifeBean)) {
                        healthLifeBean.setLastTarget(healthLifeBean.getTarget());
                        arrayList.add(healthLifeBean);
                    }
                }
            }
        }
        return arrayList;
    }

    @H5ProMethod(name = "updateHealthLifeChallenge")
    public static void updateHealthLifeChallenge(final String str, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atm
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.updateHealthLifeChallenge(str, healthModelH5ProCallback);
                }
            });
        } else if (healthModelH5ProCallback == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "updateHealthLifeChallenge callback ", healthModelH5ProCallback, " challengeIdText ", str);
        } else {
            LogUtil.a(TAG, "updateHealthLifeChallenge challengeIdText ", str);
            azw.b().a(CommonUtil.h(str), new ResponseCallback() { // from class: atn
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.lambda$updateHealthLifeChallenge$9(HealthModelH5ProCallback.this, i, (String) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$updateHealthLifeChallenge$9(HealthModelH5ProCallback healthModelH5ProCallback, int i, String str) {
        LogUtil.a(TAG, "updateHealthLifeChallenge errorCode ", Integer.valueOf(i), " object ", str);
        if (i == 0) {
            healthModelH5ProCallback.onSuccess(0);
            return;
        }
        if (str == null) {
            str = "";
        }
        healthModelH5ProCallback.onFailure(i, str);
    }

    @H5ProMethod(name = "updateSubscriptionList")
    public static void updateSubscriptionList(final String str, final String str2, final String str3, final String str4, final HealthModelH5ProCallback<Integer> healthModelH5ProCallback) {
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "updateSubscriptionList callback is null");
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: ata
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.updateSubscriptionList(str, str2, str3, str4, healthModelH5ProCallback);
                }
            });
            return;
        }
        LogUtil.a(TAG, "updateSubscriptionList subscriptionIdListText ", str, " basicIdListText ", str2, " challengeIdListText ", str3, " challengeIdText ", str4);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        try {
            List<String> list = (List) HiJsonUtil.b(str, new TypeToken<List<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.17
            }.getType());
            List<String> list2 = (List) HiJsonUtil.b(str2, new TypeToken<List<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.19
            }.getType());
            List<String> list3 = (List) HiJsonUtil.b(str3, new TypeToken<List<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.3
            }.getType());
            List list4 = (List) HiJsonUtil.b(str4, new TypeToken<List<String>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.2
            }.getType());
            if (!koq.b(list) && !koq.b(list2) && !koq.b(list3) && !koq.b(list4)) {
                azw.b().c(list, list2, list3, CommonUtil.h((String) list4.get(0)), new ResponseCallback() { // from class: ath
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i, Object obj) {
                        HealthModelH5ProService.lambda$updateSubscriptionList$11(HealthModelH5ProCallback.this, i, (String) obj);
                    }
                });
                return;
            }
            LogUtil.h(TAG, "updateSubscriptionList taskId convert error");
            healthModelH5ProCallback.onFailure(-1, "");
        } catch (JsonParseException e) {
            LogUtil.b(TAG, "updateSubscriptionList exception ", LogAnonymous.b((Throwable) e));
            healthModelH5ProCallback.onFailure(-1, "");
        }
    }

    public static /* synthetic */ void lambda$updateSubscriptionList$11(HealthModelH5ProCallback healthModelH5ProCallback, int i, String str) {
        LogUtil.a(TAG, "updateSubscriptionList errorCode ", Integer.valueOf(i), " object ", str);
        if (i == 0 || i == 12030009) {
            healthModelH5ProCallback.onSuccess(0);
        } else {
            healthModelH5ProCallback.onFailure(i, str);
        }
    }

    @H5ProMethod(name = "getTaskList")
    public static void getTaskList(final String str, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: ati
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getTaskList(str, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getTaskList callback is null");
            return;
        }
        LogUtil.a(TAG, "getTaskList dateText ", str);
        int h = CommonUtils.h(str);
        if (h <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            awq.e().b(h, new TaskDayDataListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.1
                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onDataChange(int i, HealthLifeBean healthLifeBean) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onAllDataChange(int i, List<HealthLifeBean> list) {
                    String e = HiJsonUtil.e(list);
                    LogUtil.a(HealthModelH5ProService.TAG, "getTaskList json ", e);
                    if (koq.b(list)) {
                        HealthModelH5ProCallback.this.onFailure(-1, e);
                    } else {
                        HealthModelH5ProCallback.this.onSuccess(e);
                    }
                }
            });
        }
    }

    @H5ProMethod(name = "getWeekRecordList")
    public static void getWeekRecordList(final String str, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atl
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getWeekRecordList(str, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getWeekRecordList callback is null");
            return;
        }
        LogUtil.a(TAG, "getWeekRecordList dateText ", str);
        int h = CommonUtils.h(str);
        if (h <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            awq.e().a(azi.p(), h, new TaskDataListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.5
                @Override // com.huawei.basichealthmodel.listener.TaskDataListener
                public void onDataChange(int i, SparseArray<HealthLifeBean> sparseArray) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDataListener
                public void onAllDataChange(int i, SparseArray<List<HealthLifeBean>> sparseArray) {
                    if (sparseArray == null) {
                        LogUtil.h(HealthModelH5ProService.TAG, "getWeekRecordList listSparseArray is null");
                        HealthModelH5ProCallback.this.onFailure(-1, "");
                        return;
                    }
                    int size = sparseArray.size();
                    if (size <= 0) {
                        LogUtil.h(HealthModelH5ProService.TAG, "getWeekRecordList size ", Integer.valueOf(size));
                        HealthModelH5ProCallback.this.onFailure(-1, "");
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    for (int i2 = 0; i2 < size; i2++) {
                        int keyAt = sparseArray.keyAt(i2);
                        hashMap.put(Integer.valueOf(keyAt), sparseArray.get(keyAt));
                    }
                    String e = HiJsonUtil.e(hashMap);
                    LogUtil.a(HealthModelH5ProService.TAG, "getWeekRecordList json ", e);
                    HealthModelH5ProCallback.this.onSuccess(e);
                }
            });
        }
    }

    @H5ProMethod(name = "getIntensityStatusList")
    public static void getIntensityStatusList(final String str, final String str2, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atd
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getIntensityStatusList(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getIntensityStatusList callback is null");
            return;
        }
        LogUtil.a(TAG, "getIntensityStatusList startDateText ", str, " endDateText ", str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            azi.a(str, str2, new HiAggregateListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.8
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (koq.b(list)) {
                        HealthModelH5ProCallback.this.onSuccess("");
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData != null) {
                            hashMap.put(Integer.valueOf(azi.b(hiHealthData)), Integer.valueOf(azi.c(hiHealthData)));
                        }
                    }
                    String e = HiJsonUtil.e(hashMap);
                    LogUtil.a(HealthModelH5ProService.TAG, "getIntensityStatusList json ", e);
                    HealthModelH5ProCallback.this.onSuccess(e);
                }
            });
        }
    }

    @H5ProMethod(name = "getDietRecord")
    public static void getDietRecord(final String str, final String str2, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: aub
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getDietRecord(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getDietRecord callback is null");
            return;
        }
        LogUtil.a(TAG, "getDietRecord startDateText ", str, " endDateText ", str2);
        int h = CommonUtils.h(str);
        int h2 = CommonUtils.h(str2);
        if (h <= 0 || h2 <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            grz.b(h, h2, new ResponseCallback() { // from class: aty
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.lambda$getDietRecord$16(HealthModelH5ProCallback.this, i, (List) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getDietRecord$16(HealthModelH5ProCallback healthModelH5ProCallback, int i, List list) {
        String e = HiJsonUtil.e(list);
        List<quh> list2 = (List) HiJsonUtil.b(e, new TypeToken<List<quh>>() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.10
        }.getType());
        if (koq.b(list2)) {
            ReleaseLogUtil.d(TAG, "getDietRecord errorCode ", Integer.valueOf(i), " list ", list, " json ", e, " resultList ", list2);
            healthModelH5ProCallback.onSuccess(e);
            return;
        }
        for (quh quhVar : list2) {
            if (quhVar != null) {
                quhVar.e(jdl.b(DateFormatUtil.c(quhVar.c()), TimeZone.getDefault()));
                quhVar.h();
            }
        }
        String e2 = HiJsonUtil.e(list2);
        LogUtil.a(TAG, "getDietRecord errorCode ", Integer.valueOf(i), " list ", list, " json ", e, "resultJson ", e2, " resultList ", list2);
        healthModelH5ProCallback.onSuccess(e2);
    }

    @H5ProMethod(name = "queryWeekHealthReport")
    public static void queryWeekHealthReport(final String str, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: ats
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.queryWeekHealthReport(str, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "queryWeekHealthReport callback is null");
            return;
        }
        LogUtil.a(TAG, "queryWeekHealthReport startDateText ", str);
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h(TAG, "queryWeekHealthReport isNetworkConnected false");
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        int h = CommonUtils.h(str);
        if (h <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            azi.b(h, (ResponseCallback<dsb>) new ResponseCallback() { // from class: ato
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.lambda$queryWeekHealthReport$18(HealthModelH5ProCallback.this, i, (dsb) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$queryWeekHealthReport$18(HealthModelH5ProCallback healthModelH5ProCallback, int i, dsb dsbVar) {
        if (dsbVar == null) {
            healthModelH5ProCallback.onFailure(i, "");
            return;
        }
        String e = HiJsonUtil.e(dsbVar);
        LogUtil.a(TAG, "queryWeekHealthReport json ", e);
        healthModelH5ProCallback.onSuccess(e);
    }

    @H5ProMethod(name = "queryBenefitInfo")
    public static void queryBenefitInfo(final String str, final String str2, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atv
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.queryBenefitInfo(str, str2, healthModelH5ProCallback);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "queryBenefitInfo callback is null");
            return;
        }
        LogUtil.a(TAG, "queryBenefitInfo resourceTypeText ", str, " resourceId ", str2);
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h(TAG, "queryBenefitInfo isNetworkConnected false");
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        int h = CommonUtils.h(str);
        if (h <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
            return;
        }
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(TAG, "queryBenefitInfo payApi is null");
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            payApi.queryBenefitInfo(h, str2, new IBaseResponseCallback() { // from class: atu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HealthModelH5ProService.lambda$queryBenefitInfo$20(HealthModelH5ProCallback.this, i, obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$queryBenefitInfo$20(HealthModelH5ProCallback healthModelH5ProCallback, int i, Object obj) {
        String e = obj == null ? "" : HiJsonUtil.e(obj);
        LogUtil.a(TAG, "queryBenefitInfo errorCode ", Integer.valueOf(i), " object ", obj, " json ", e);
        if (i == 0) {
            healthModelH5ProCallback.onSuccess(e);
        } else {
            healthModelH5ProCallback.onFailure(-1, e);
        }
    }

    @H5ProMethod(name = "isThreeCircle")
    public static boolean isThreeCircle() {
        boolean e = niv.e(BaseApplication.e());
        LogUtil.a(TAG, "isThreeCircle isThreeCircleCard ", Boolean.valueOf(e));
        return e;
    }

    @H5ProMethod(name = "getThreeCircleProgress")
    public static void getThreeCircleProgress(final long j, final long j2, final int i, final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        LogUtil.a(TAG, "getThreeCircleProgress startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2), " type:", Integer.valueOf(i));
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atj
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getThreeCircleProgress(j, j2, i, healthModelH5ProCallback);
                }
            });
        } else if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getThreeCircleProgress callback is null");
        } else {
            nix.c().c(jdl.t(j), jdl.e(j2), new ResponseCallback() { // from class: atx
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    HealthModelH5ProService.lambda$getThreeCircleProgress$22(HealthModelH5ProCallback.this, i, i2, (List) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getThreeCircleProgress$22(HealthModelH5ProCallback healthModelH5ProCallback, int i, int i2, List list) {
        if (koq.b(list)) {
            LogUtil.h(TAG, "getThreeCircleProgress dataList isEmpty!");
            healthModelH5ProCallback.onFailure(-1, "task list is empty!");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HiHealthData hiHealthData = (HiHealthData) it.next();
            if (hiHealthData != null) {
                HashMap hashMap = new HashMap(16);
                putThreeCircleData(hiHealthData, hashMap, i);
                arrayList.add(hashMap);
            }
        }
        String e = HiJsonUtil.e(arrayList);
        LogUtil.a(TAG, "getThreeCircleProgress json: ", e);
        healthModelH5ProCallback.onSuccess(e);
    }

    private static void putThreeCircleData(HiHealthData hiHealthData, Map<String, Object> map, int i) {
        int i2 = hiHealthData.getInt("calorieGoalValue");
        int i3 = hiHealthData.getInt("calorieUserValue");
        int i4 = i3 >= i2 ? 1 : 0;
        int i5 = hiHealthData.getInt("durationGoalValue");
        int i6 = hiHealthData.getInt("durationUserValue");
        if (i6 >= i5) {
            i4++;
        }
        int i7 = hiHealthData.getInt("activeGoalValue");
        int i8 = hiHealthData.getInt("activeUserValue");
        if (i8 >= i7) {
            i4++;
        }
        int i9 = hiHealthData.getInt("stepGoalValue");
        int i10 = hiHealthData.getInt("stepUserValue");
        if (i == 0) {
            map.put("totalTaskNum", 3);
            map.put("completedTaskNum", Integer.valueOf(i4));
            return;
        }
        map.put("day", Long.valueOf(hiHealthData.getDay()));
        map.put("startTime", Long.valueOf(hiHealthData.getStartTime()));
        map.put("calorieGoalValue", Integer.valueOf(i2));
        map.put("calorieUserValue", Integer.valueOf(i3));
        map.put("durationGoalValue", Integer.valueOf(i5));
        map.put("durationUserValue", Integer.valueOf(i6));
        map.put("activeGoalValue", Integer.valueOf(i7));
        map.put("activeUserValue", Integer.valueOf(i8));
        map.put("stepGoalValue", Integer.valueOf(i9));
        map.put("stepUserValue", Integer.valueOf(i10));
    }

    @H5ProMethod(name = "showClover")
    public static void showClover() {
        Context e = BaseApplication.e();
        boolean a2 = niv.a(e);
        boolean b = niv.b(e);
        LogUtil.a(TAG, "showClover isStepCard ", Boolean.valueOf(a2), " isThreeLeafCard ", Boolean.valueOf(b));
        if (a2 || b) {
            return;
        }
        ObserverManagerUtil.c("cloverLife", new Object[0]);
    }

    @H5ProMethod(name = "getTodayTaskStatus")
    public static void getTodayTaskStatus(final HealthModelH5ProCallback<String> healthModelH5ProCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: atb
                @Override // java.lang.Runnable
                public final void run() {
                    HealthModelH5ProService.getTodayTaskStatus(HealthModelH5ProCallback.this);
                }
            });
            return;
        }
        if (healthModelH5ProCallback == null) {
            LogUtil.h(TAG, "getTodayTaskStatus callback is null");
            return;
        }
        int b = DateFormatUtil.b(System.currentTimeMillis());
        LogUtil.a(TAG, "getTodayTaskStatus -> " + b);
        if (b <= 0) {
            healthModelH5ProCallback.onFailure(-1, "");
        } else {
            awq.e().b(b, new TaskDayDataListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.7
                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onDataChange(int i, HealthLifeBean healthLifeBean) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onAllDataChange(int i, List<HealthLifeBean> list) {
                    LogUtil.a(HealthModelH5ProService.TAG, "getTodayTaskStatus: onAllDataChange -> " + i);
                    if (!koq.b(list)) {
                        HealthModelH5ProCallback.this.onSuccess(HiJsonUtil.e(HealthModelH5ProService.getTaskStatus(list)));
                    } else {
                        HealthModelH5ProCallback.this.onFailure(i, "task list is empty");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getTaskStatus(List<HealthLifeBean> list) {
        Iterator<HealthLifeBean> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getComplete() > 0) {
                i++;
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("totalTaskNum", list.size());
            jSONObject.put("completedTaskNum", i);
        } catch (JSONException e) {
            LogUtil.b(TAG, "getTaskStatus: exception -> " + e.getMessage());
        }
        return jSONObject;
    }

    @H5ProMethod(name = "getSwitchState")
    public static void getSwitchState(final String str, final HealthModelH5ProCallback healthModelH5ProCallback) {
        final boolean z;
        String str2;
        final String str3;
        ReleaseLogUtil.e(TAG, "getSwitchState buttonKey:", str);
        if ("healthLifeThreeLeafState".equals(str)) {
            str2 = "900300020";
            str3 = "healthLifeThreeLeafSwitch";
            z = true;
        } else if ("healthLifeWeekReportState".equals(str)) {
            z = !TextUtils.isEmpty(bao.e("week_report_remind_switch"));
            str2 = "900300021";
            str3 = "healthLifeWeekReportSwitch";
        } else {
            ReleaseLogUtil.d(TAG, "no such keys");
            healthModelH5ProCallback.onFailure(-1, "no such keys");
            return;
        }
        LogUtil.a(TAG, "state is ", Boolean.valueOf(z));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str2);
        njj.d("9003", arrayList, new HiDataReadResultListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.6
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(HealthModelH5ProService.TAG, "onResult errorCode: ", Integer.valueOf(i), " buttonKey:", str);
                LogUtil.a(HealthModelH5ProService.TAG, "onResult errorCode: ", Integer.valueOf(i), " buttonKey:", str, " data: ", obj);
                if (koq.e(obj, HiSampleConfig.class)) {
                    List list = (List) obj;
                    if (!koq.b(list)) {
                        HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                        healthModelH5ProCallback.onSuccess(Boolean.valueOf("1".equals(dsl.c(hiSampleConfig.getConfigData(), str3))));
                        LogUtil.a(HealthModelH5ProService.TAG, "key :", str, " value:", hiSampleConfig.getConfigData());
                        return;
                    }
                }
                LogUtil.h(HealthModelH5ProService.TAG, "isListTypeMatch false or list is empty");
                healthModelH5ProCallback.onSuccess(Boolean.valueOf(z));
            }
        });
    }

    @H5ProMethod(name = "updateSwitchState")
    public static void updateSwitchState(String str, boolean z, final HealthModelH5ProCallback healthModelH5ProCallback) {
        String str2;
        String str3;
        ReleaseLogUtil.e(TAG, "updateSwitchState buttonKey:", str);
        if ("healthLifeThreeLeafState".equals(str)) {
            str2 = "900300020";
            str3 = "healthLifeThreeLeafSwitch";
        } else if (!"healthLifeWeekReportState".equals(str)) {
            ReleaseLogUtil.d(TAG, "no such keys");
            healthModelH5ProCallback.onSuccess(false);
            return;
        } else {
            str2 = "900300021";
            str3 = "healthLifeWeekReportSwitch";
        }
        njj.a("9003", str2, dsl.e(str3, z ? "1" : "0"), new HiDataOperateListener() { // from class: com.huawei.basichealthmodel.HealthModelH5ProService.9
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e(HealthModelH5ProService.TAG, "setSampleConfig errorCode: ", Integer.valueOf(i));
                if (i == 0) {
                    HealthModelH5ProCallback.this.onSuccess(true);
                } else {
                    HealthModelH5ProCallback.this.onSuccess(false);
                }
            }
        }, System.currentTimeMillis());
    }

    @H5ProMethod(name = "getHealthLifeChallenge")
    public static void getHealthLifeChallenge(final HealthModelH5ProCallback healthModelH5ProCallback) {
        ReleaseLogUtil.e(TAG, "getHealthLifeChallenge ");
        if (healthModelH5ProCallback != null) {
            dsl.b(TAG, (ResponseCallback<drx>) new ResponseCallback() { // from class: atr
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    HealthModelH5ProService.lambda$getHealthLifeChallenge$24(HealthModelH5ProCallback.this, i, (drx) obj);
                }
            });
        } else {
            ReleaseLogUtil.d(TAG, "getHealthLifeChallenge callback is null");
        }
    }

    public static /* synthetic */ void lambda$getHealthLifeChallenge$24(HealthModelH5ProCallback healthModelH5ProCallback, int i, drx drxVar) {
        Object[] objArr = new Object[4];
        objArr[0] = "getHealthLifeChallenge resultCode ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " data is null:";
        objArr[3] = Boolean.valueOf(drxVar == null);
        ReleaseLogUtil.e(TAG, objArr);
        if (drxVar != null) {
            healthModelH5ProCallback.onSuccess(HiJsonUtil.e(drxVar));
        } else {
            healthModelH5ProCallback.onSuccess("");
        }
    }
}
