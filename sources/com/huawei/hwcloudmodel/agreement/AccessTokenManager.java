package com.huawei.hwcloudmodel.agreement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.GRSManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class AccessTokenManager {
    private static final String TAG = "Login_AccessTokenManager";
    private Activity mActivity;
    private HuaweiApiClient mClient;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    public AccessTokenManager() {
    }

    public AccessTokenManager(Activity activity) {
        this.mActivity = activity;
    }

    public void initSingleThreadExecutor() {
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public void initHandler(Handler handler) {
        this.mHandler = handler;
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public void hmsConnect(Context context, HuaweiApiClient.ConnectionCallbacks connectionCallbacks, HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        HuaweiIdAuthParams createParams = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).createParams();
        if (context == null) {
            LogUtil.a(TAG, "hmsConnect context == null");
            return;
        }
        HuaweiApiClient build = new HuaweiApiClient.Builder(context.getApplicationContext()).addApi(HuaweiIdAuthAPIManager.HUAWEI_OAUTH_API, createParams).addScope(HuaweiIdAuthAPIManager.HUAWEIID_BASE_SCOPE).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(onConnectionFailedListener).build();
        this.mClient = build;
        build.connect(this.mActivity);
    }

    public HuaweiApiClient getClient() {
        return this.mClient;
    }

    public void retryConnect() {
        HuaweiApiClient huaweiApiClient = this.mClient;
        if (huaweiApiClient == null || huaweiApiClient.isConnected()) {
            return;
        }
        this.mClient.connect(this.mActivity);
    }

    public String signIn(final Context context) {
        final HuaweiApiClient huaweiApiClient = this.mClient;
        if (huaweiApiClient == null || !huaweiApiClient.isConnected() || context == null) {
            LogUtil.a(TAG, "signInHuaweiId_signIn is not connected");
            return "";
        }
        final Handler handler = this.mHandler;
        if (handler == null) {
            return "";
        }
        ExecutorService executorService = this.mExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
        }
        this.mExecutorService.execute(new Runnable() { // from class: com.huawei.hwcloudmodel.agreement.AccessTokenManager.1
            @Override // java.lang.Runnable
            public void run() {
                String signBackendTest = AccessTokenManager.signBackendTest(context, huaweiApiClient, 1);
                LogUtil.c(AccessTokenManager.TAG, "signIn.accessToken = ", signBackendTest);
                String url = GRSManager.a(context).getUrl("agreementservice");
                Message obtainMessage = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("agrUrl", url);
                bundle.putString("token", signBackendTest);
                obtainMessage.setData(bundle);
                obtainMessage.what = 10002;
                handler.sendMessage(obtainMessage);
                AccessTokenManager.this.saveAccessToken(context, signBackendTest);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAccessToken(Context context, String str) {
        LogUtil.a(TAG, "enter to saveATToken():");
        if (!TextUtils.isEmpty(str)) {
            LoginInit.getInstance(context).setAccountInfo(1007, str, null);
        } else {
            LogUtil.a(TAG, "accessToken is null");
        }
    }

    public String signInToGetAt(final Context context) {
        LogUtil.a(TAG, "enter signInToGetAt():");
        final HuaweiApiClient huaweiApiClient = this.mClient;
        if (huaweiApiClient == null || !huaweiApiClient.isConnected() || context == null) {
            LogUtil.a(TAG, "signInToGetAt is not connected");
            return "";
        }
        ExecutorService executorService = this.mExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
        }
        this.mExecutorService.execute(new Runnable() { // from class: com.huawei.hwcloudmodel.agreement.AccessTokenManager.2
            @Override // java.lang.Runnable
            public void run() {
                String signBackendTest = AccessTokenManager.signBackendTest(context, huaweiApiClient, 1);
                LogUtil.c(AccessTokenManager.TAG, "signInToGetAt get accessToken = ", signBackendTest);
                AccessTokenManager.this.saveAccessToken(context, signBackendTest);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String signBackendTest(Context context, HuaweiApiClient huaweiApiClient, int i) {
        LogUtil.a(TAG, "signBackendTest retryTimes ", Integer.valueOf(i));
        if (i < 0) {
            return null;
        }
        SignInResult await = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.signInBackend(huaweiApiClient).await(10L, TimeUnit.SECONDS);
        if (await != null) {
            Status status = await.getStatus();
            if (status != null) {
                return judgeConnectState(context, await, huaweiApiClient, i, status);
            }
            LogUtil.a(TAG, "signIn status is null");
        } else {
            LogUtil.a(TAG, "signIn result is null");
        }
        return null;
    }

    private static String judgeConnectState(Context context, SignInResult signInResult, HuaweiApiClient huaweiApiClient, int i, Status status) {
        if (status.getStatusCode() == 907135004) {
            LogUtil.a(TAG, "authHuaweiId StatusCode() ", Integer.valueOf(status.getStatusCode()));
            return signBackendTest(context, huaweiApiClient, i - 1);
        }
        if (status.getStatusCode() == 0) {
            AuthHuaweiId authHuaweiId = signInResult.getAuthHuaweiId();
            if (authHuaweiId != null) {
                return authHuaweiId.getAccessToken();
            }
            LogUtil.a(TAG, "authHuaweiId is null");
            return null;
        }
        if (signInResult.getStatus().getStatusCode() == 2002) {
            startSignInActivity(context, signInResult);
            return null;
        }
        LogUtil.a(TAG, "signIn error:", Integer.valueOf(status.getStatusCode()));
        return null;
    }

    public void shutDownThread() {
        ExecutorService executorService = this.mExecutorService;
        if (executorService != null) {
            executorService.shutdown();
        }
        HuaweiApiClient huaweiApiClient = this.mClient;
        if (huaweiApiClient != null) {
            huaweiApiClient.disconnect();
            this.mClient = null;
        }
        this.mActivity = null;
    }

    private static void startSignInActivity(Context context, SignInResult signInResult) {
        Intent data = signInResult.getData();
        LogUtil.a(TAG, "HuaweiIdAuthResult not auth");
        if (data != null) {
            data.setFlags(268435456);
            if (context != null) {
                context.startActivity(data);
            }
        }
    }
}
