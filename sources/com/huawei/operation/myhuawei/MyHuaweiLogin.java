package com.huawei.operation.myhuawei;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.gmz;
import defpackage.jdl;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nru;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class MyHuaweiLogin {
    public static final String IS_START_LOGIN = "isStartLogin";
    private static final String MYHUAWEI_LOGIN_URL = "/achievement/threeRingTask/myHuawei/loginMyHuawei";
    public static final String RELOAD_URL = "reloadUrl";
    private static final String TAG = "PluginOperation_MyHuaweiLogin";

    public static void myHuaweiLoginCloud(final UiCallback<String> uiCallback) {
        MyHuaweiLoginRequest myHuaweiLoginRequest = new MyHuaweiLoginRequest();
        myHuaweiLoginRequest.setTimeZone(jdl.q(System.currentTimeMillis()));
        myHuaweiLoginRequest.setAccessToken(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        final HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        final String b = lql.b(healthDataCloudFactory.getBody(myHuaweiLoginRequest));
        LogUtil.d(TAG, "myHuaweiLoginCloud body: ", b);
        GRSManager.a(BaseApplication.getContext()).e("achievementUrl", new GrsQueryCallback() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                lqi.d().b(str + MyHuaweiLogin.MYHUAWEI_LOGIN_URL, HealthDataCloudFactory.this.getHeaders(), b, MyHuaweiLoginResponse.class, new ResultCallback<MyHuaweiLoginResponse>() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin.1.1
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(MyHuaweiLoginResponse myHuaweiLoginResponse) {
                        LogUtil.d(MyHuaweiLogin.TAG, "myHuaweiLoginCloud onSuccess: ", myHuaweiLoginResponse);
                        if (myHuaweiLoginResponse.getResultCode() == 0) {
                            uiCallback.onSuccess(HandlerExecutor.yF_(), myHuaweiLoginResponse.getCookie());
                        } else {
                            uiCallback.onFailure(HandlerExecutor.yF_(), myHuaweiLoginResponse.getResultCode(), "");
                        }
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        Object[] objArr = new Object[2];
                        objArr[0] = "myHuaweiLoginCloud with ";
                        objArr[1] = th == null ? null : th.getMessage();
                        ReleaseLogUtil.c(MyHuaweiLogin.TAG, objArr);
                        uiCallback.onFailure(HandlerExecutor.yF_(), -1, "");
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                ReleaseLogUtil.c(MyHuaweiLogin.TAG, "get GRS failed ");
                uiCallback.onFailure(HandlerExecutor.yF_(), -1, "");
            }
        });
    }

    public static void handleMyHuaweiLogin(UiCallback<String> uiCallback, Activity activity) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin$$ExternalSyntheticLambda2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ReleaseLogUtil.b(MyHuaweiLogin.TAG, "browsingToLogin with ", Integer.valueOf(i));
                }
            }, "");
            activity.finish();
            return;
        }
        if (String.valueOf(true).equals(gmz.d().c(405))) {
            myHuaweiLoginCloud(uiCallback);
        } else {
            buildMyHuaweiDialog(uiCallback, activity);
        }
    }

    public static void buildMyHuaweiDialog(final UiCallback<String> uiCallback, final Activity activity) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                MyHuaweiLogin.lambda$buildMyHuaweiDialog$3(activity, uiCallback);
            }
        });
    }

    static /* synthetic */ void lambda$buildMyHuaweiDialog$3(final Activity activity, final UiCallback uiCallback) {
        if (activity == null) {
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string._2130846781_res_0x7f02243d).d(R.string._2130846783_res_0x7f02243f).cyU_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyHuaweiLogin.lambda$buildMyHuaweiDialog$1(UiCallback.this, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyHuaweiLogin.lambda$buildMyHuaweiDialog$2(activity, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void lambda$buildMyHuaweiDialog$1(UiCallback uiCallback, View view) {
        gmz.d().c(405, true, (String) null, (IBaseResponseCallback) null);
        myHuaweiLoginCloud(uiCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$buildMyHuaweiDialog$2(Activity activity, View view) {
        gmz.d().c(405, false, (String) null, (IBaseResponseCallback) null);
        activity.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static Map<String, Object> getMyHuaweiLogin(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(IS_START_LOGIN, false);
        hashMap.put(RELOAD_URL, str);
        if (!isSupportMyHuaweiLogin()) {
            return hashMap;
        }
        final HashMap hashMap2 = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.myhuawei.MyHuaweiLogin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MyHuaweiLogin.lambda$getMyHuaweiLogin$4(hashMap2, countDownLatch);
            }
        });
        try {
            if (!countDownLatch.await(2L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.d(TAG, "getGrsUrl wait over time");
                return hashMap;
            }
            if (z && isMyHuaweiUrl(str, hashMap2)) {
                hashMap.put(IS_START_LOGIN, true);
                return hashMap;
            }
            if (isMyHuaweiUpLogin(str, str2, hashMap2)) {
                hashMap.put(IS_START_LOGIN, true);
                hashMap.put(RELOAD_URL, str2);
            }
            return hashMap;
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c(TAG, "getGrsUrl InterruptedException");
            return hashMap;
        }
    }

    static /* synthetic */ void lambda$getMyHuaweiLogin$4(HashMap hashMap, CountDownLatch countDownLatch) {
        Context context = BaseApplication.getContext();
        hashMap.put("domainMyHuawei", GRSManager.a(context).getUrl("domainMyHuawei"));
        hashMap.put("domainMyHuaweiUpLogin", GRSManager.a(context).getUrl("domainMyHuaweiUpLogin"));
        hashMap.put("domainMyHuaweiOauthLogin", GRSManager.a(context).getUrl("domainMyHuaweiOauthLogin"));
        countDownLatch.countDown();
    }

    private static boolean isMyHuaweiUpLogin(String str, String str2, HashMap<String, String> hashMap) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String b = nru.b(hashMap, "domainMyHuaweiUpLogin", "");
        String b2 = nru.b(hashMap, "domainMyHuaweiOauthLogin", "");
        return ((!TextUtils.isEmpty(b) && str.contains(b)) || (!TextUtils.isEmpty(b2) && str.contains(b2))) && isMyHuaweiUrl(str2, hashMap);
    }

    private static boolean isMyHuaweiUrl(String str, HashMap<String, String> hashMap) {
        String[] supportCountry;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String b = nru.b(hashMap, "domainMyHuawei", "");
        if (TextUtils.isEmpty(b) || (supportCountry = getSupportCountry()) == null) {
            return false;
        }
        for (String str2 : supportCountry) {
            if (str.contains(b + "/" + ("GB".equals(str2) ? "uk" : str2.toLowerCase(Locale.ENGLISH)).toLowerCase(Locale.ENGLISH))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupportMyHuaweiLogin() {
        String[] supportCountry;
        if (!Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || (supportCountry = getSupportCountry()) == null) {
            return false;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        for (String str : supportCountry) {
            if (TextUtils.equals(str, accountInfo)) {
                return true;
            }
        }
        return false;
    }

    private static String[] getSupportCountry() {
        try {
            return BaseApplication.getContext().getResources().getStringArray(R.array._2130968685_res_0x7f04006d);
        } catch (Resources.NotFoundException e) {
            LogUtil.e(TAG, "isSupportMyHuaweiLogin meet exception: ", e.getMessage());
            return new String[0];
        }
    }
}
