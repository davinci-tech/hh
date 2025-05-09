package com.huawei.pluginachievement.jsmodule;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import defpackage.bzs;
import defpackage.koq;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdn;
import defpackage.mee;
import defpackage.meh;
import defpackage.mlc;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class AchieveUtil extends JsBaseModule {
    private static final String CLIENT_TYPE_HONOR = "2";
    private static final String CLIENT_TYPE_HUAWEI = "1";
    private static final String CLIENT_TYPE_PAD = "5";
    private static final String CLIENT_TYPE_THIRD = "3";
    private static final String KAKA_TASK_RULE_ID = "taskRuleId";
    private static final int RESULT_OK = 200;

    @JavascriptInterface
    public void openEditAddressPage(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("awardRecordId", str);
        bundle.putString("activityId", str2);
        AppRouter.b("/HWUserProfileMgr/WriteDeliveryInfoActivity").zF_(bundle).c(BaseApplication.getContext());
    }

    @JavascriptInterface
    public void openVmallDetail(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        Intent createWebViewIntent = bzs.e().createWebViewIntent(this.mContext, bundle, 268435456);
        if (createWebViewIntent != null) {
            this.mContext.startActivity(createWebViewIntent);
        }
    }

    @JavascriptInterface
    public String getNickName() {
        String a2 = mcx.a(this.mContext.getApplicationContext());
        return !TextUtils.isEmpty(a2) ? a2 : LoginInit.getInstance(this.mContext).getAccountInfo(1002);
    }

    @JavascriptInterface
    public int getiVersion(int i) {
        return meh.c(BaseApplication.getContext()).c(i);
    }

    @JavascriptInterface
    public void openMyAwardPage() {
        AppRouter.b("/HWUserProfileMgr/MyAwardActivity").c(this.mContext);
    }

    @JavascriptInterface
    public void openMyAwardPage(long j, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("from", str);
        AppRouter.b("/HWUserProfileMgr/MyAwardActivity").zF_(bundle).c(this.mContext);
        onSuccessCallback(j, true);
    }

    @JavascriptInterface
    public static String getClientType() {
        return SystemInfo.a() ? "1" : (SystemInfo.d() || SystemInfo.h()) ? "2" : nsn.ae(BaseApplication.getContext()) ? "5" : "3";
    }

    @JavascriptInterface
    public void finishKakaTask(String str, String str2) {
        LogUtil.c(this.TAG, "finishKakaTask taskRuleId = ", str, " params =", str2);
        HashMap hashMap = new HashMap(2);
        if (!TextUtils.isEmpty(str2)) {
            mdn.e(str2, hashMap);
        }
        mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), str, hashMap);
    }

    @JavascriptInterface
    public void finishKakaTaskV2(String str) {
        ReleaseLogUtil.e(this.TAG, "BuyVip finishKakaTaskV2 params =", str);
        HashMap hashMap = new HashMap(2);
        mdn.e(str, hashMap);
        if (hashMap.containsKey(KAKA_TASK_RULE_ID) && (hashMap.get(KAKA_TASK_RULE_ID) instanceof String)) {
            String str2 = (String) hashMap.get(KAKA_TASK_RULE_ID);
            LogUtil.a(this.TAG, "BuyVip finishKakaTaskV2 taskRuleId = ", str2);
            mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), str2, hashMap);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0050 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void isFinishAllTasks(long r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = defpackage.mle.d()
            r1 = 0
            java.util.Map r0 = defpackage.mle.a(r0, r1)
            java.lang.String r2 = "task_size"
            boolean r3 = r0.containsKey(r2)
            if (r3 == 0) goto L20
            java.lang.Object r2 = r0.get(r2)
            boolean r3 = r2 instanceof java.lang.Integer
            if (r3 == 0) goto L20
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L21
        L20:
            r2 = r1
        L21:
            java.lang.String r3 = "finish_count"
            boolean r4 = r0.containsKey(r3)
            if (r4 == 0) goto L38
            java.lang.Object r0 = r0.get(r3)
            boolean r3 = r0 instanceof java.lang.Integer
            if (r3 == 0) goto L38
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            goto L39
        L38:
            r0 = r1
        L39:
            java.lang.String r3 = r8.TAG
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.String r5 = " finishCount "
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            java.lang.String r7 = "isFinishAllTasks taskSize "
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r4, r5, r6}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r4)
            if (r2 == 0) goto L53
            if (r0 != r2) goto L53
            r1 = 1
        L53:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r8.onSuccessCallback(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginachievement.jsmodule.AchieveUtil.isFinishAllTasks(long):void");
    }

    @JavascriptInterface
    public void scrollToTop(long j) {
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(27);
        userAchieveWrapper.setResultCode("0");
        mee.b(this.mContext).c(200, userAchieveWrapper);
        onSuccessCallback(j, true);
    }

    @JavascriptInterface
    public void showTotalKakaNum(long j, int i) {
        LogUtil.a(this.TAG, "showTotalKakaNum:", Integer.valueOf(i));
        if (i < 0) {
            return;
        }
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(28);
        userAchieveWrapper.setResultCode("0");
        mee.b(this.mContext).c(i, userAchieveWrapper);
        onSuccessCallback(j, true);
    }

    @JavascriptInterface
    public void getMedalsImage(final long j, final String str) {
        LogUtil.a(this.TAG, "getMedalsImage medals= ", str);
        if (TextUtils.isEmpty(str)) {
            onSuccessCallback(j, false);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.jsmodule.AchieveUtil.4
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList<String> c = mlc.c(str);
                    LogUtil.a(AchieveUtil.this.TAG, "getMedalsImage medalList= ", c.toString());
                    AchieveUtil.this.getMedalsImageUrl(j, c);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMedalsImageUrl(long j, List<String> list) {
        if (koq.b(list)) {
            onSuccessCallback(j, false);
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        List<mcz> b = meh.c(BaseApplication.getContext()).b(9, new HashMap(2));
        for (String str : list) {
            Iterator<mcz> it = b.iterator();
            while (true) {
                if (it.hasNext()) {
                    mcz next = it.next();
                    if (next instanceof MedalConfigInfo) {
                        MedalConfigInfo medalConfigInfo = (MedalConfigInfo) next;
                        if (str.equals(medalConfigInfo.acquireMedalID())) {
                            arrayList.add(medalConfigInfo.acquireLightListStyle());
                            break;
                        }
                    }
                }
            }
        }
        LogUtil.a(this.TAG, "getMedalsImage medalUrls ", arrayList.toString());
        onSuccessCallback(j, arrayList);
    }
}
