package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.picker.AccountPickerManager;
import com.huawei.hms.support.picker.request.AccountPickerParams;
import com.huawei.hms.support.picker.request.AccountPickerParamsHelper;
import com.huawei.hms.support.picker.service.AccountPickerService;
import com.huawei.picture.security.account.AccountAgentActivity;
import com.huawei.picture.security.account.bean.AccountInitParams;
import com.huawei.picture.security.account.bean.AuthErrorBean;
import com.huawei.picture.security.account.bean.ErrorStatus;
import com.huawei.picture.security.account.bean.SignInRequest;
import com.huawei.picture.security.account.bean.SignInResponse;
import com.huawei.picture.security.account.bean.UserAccountInfo;
import com.huawei.picture.security.account.bean.UserInfoBean;
import com.huawei.picture.security.account.bean.UserTokenBean;
import com.huawei.picture.security.account.handler.CloudAccountHandler;
import com.huawei.secure.android.common.encrypt.rsa.RSASign;
import com.huawei.secure.android.common.util.SafeBase64;
import defpackage.mca;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class mbs {
    private static volatile mbs b;
    private volatile long c = 0;
    public Map<String, CloudAccountHandler> d = new ConcurrentHashMap();
    private AccountInitParams e;

    private mbs() {
    }

    public static mbs d() {
        if (b == null) {
            synchronized (mbs.class) {
                if (b == null) {
                    b = new mbs();
                }
            }
        }
        return b;
    }

    public void e(AccountInitParams accountInitParams) {
        this.e = accountInitParams;
        mcc.d().c(new mca.e().cfI_(this.e.getApplication()).e("picture_security_sdk_preferences").d("account_work_key").c("picture_security_sdk_root_key").d());
        mbv.d().d(this.e.getAuthErrorParamList());
        mci.c(new Runnable() { // from class: mbw
            @Override // java.lang.Runnable
            public final void run() {
                mcd.c().a();
            }
        });
    }

    public AccountAuthService d(Context context) {
        return AccountAuthManager.getService(context, new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setScopeList(this.e.getScopes()).setAuthorizationCode().setUid().createParams());
    }

    public AccountPickerService cfG_(Activity activity) {
        return AccountPickerManager.getService(activity, new AccountPickerParamsHelper(AccountPickerParams.DEFAULT_AUTH_REQUEST_PARAM).setScopeList(this.e.getScopes()).setHostAccessToken(this.e.getAccessToken()).setRedirecturl("hms://redirect_uri").setDeviceInfo(kqj.d(null, null, null, this.e.getDeviceId())).setAccessToken().setAuthorizationCode().setUid().setGrsAppName(this.e.getGrsAppName()).createParams(), 1);
    }

    public void d(CloudAccountHandler cloudAccountHandler) {
        if (!mcl.b(this.e.getApplication())) {
            cloudAccountHandler.onRequestError(new ErrorStatus(-100, ""));
            mcj.b("CloudAccountManager", "startAccountSDKSilentSignIn isNetworkAvailable false");
            return;
        }
        try {
            if (!cloudAccountHandler.isLoginAccount()) {
                mcj.c("CloudAccountManager", "getAccountInfo startAccountSDKActivity.");
                e(cloudAccountHandler);
            } else if (this.e.isMcp()) {
                mcj.c("CloudAccountManager", "getAccountInfo trySignIn.");
                c(cloudAccountHandler);
            } else {
                mcj.c("CloudAccountManager", "getAccountInfo startAccountSDKSilentSignIn.");
                f(cloudAccountHandler);
            }
        } catch (Exception e) {
            mcj.d("CloudAccountManager", "getAccountInfo failed. Exception: ", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (b(r8) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.huawei.picture.security.account.handler.CloudAccountHandler r8, com.huawei.picture.security.account.bean.HitopRespInfo r9) {
        /*
            r7 = this;
            java.lang.String r0 = "11001"
            java.lang.String r1 = r9.getResultCode()
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 != 0) goto L1e
            mbv r0 = defpackage.mbv.d()
            java.lang.String r1 = r9.getUrl()
            java.lang.String r9 = r9.getResultInfo()
            boolean r9 = r0.a(r1, r9)
            if (r9 == 0) goto L44
        L1e:
            java.lang.String r9 = "generateJsonData auth failed."
            java.lang.String r0 = "CloudAccountManager"
            defpackage.mcj.c(r0, r9)
            long r1 = java.lang.System.currentTimeMillis()
            long r3 = r7.c
            long r3 = r1 - r3
            r5 = 10000(0x2710, double:4.9407E-320)
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 <= 0) goto L46
            boolean r8 = r7.b(r8)     // Catch: java.lang.Exception -> L3a
            if (r8 == 0) goto L40
            goto L46
        L3a:
            r8 = move-exception
            java.lang.String r9 = "getAccountsByType refreshUserInfo failed: "
            defpackage.mcj.d(r0, r9, r8)
        L40:
            r8 = 0
            r7.c = r8
        L44:
            r8 = 0
            return r8
        L46:
            r7.c = r1
            java.lang.String r8 = "generateJsonData refresh token success."
            defpackage.mcj.c(r0, r8)
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mbs.a(com.huawei.picture.security.account.handler.CloudAccountHandler, com.huawei.picture.security.account.bean.HitopRespInfo):boolean");
    }

    public void c(List<AuthErrorBean> list) {
        mbv.d().d(list);
    }

    public void c(final String str, final String str2, final CloudAccountHandler cloudAccountHandler) {
        mcj.c("CloudAccountManager", "signInForUserInfo");
        mci.c(new Runnable() { // from class: mbu
            @Override // java.lang.Runnable
            public final void run() {
                mbs.this.c(str, cloudAccountHandler, str2);
            }
        });
    }

    /* synthetic */ void c(String str, CloudAccountHandler cloudAccountHandler, String str2) {
        Map<String, String> c = mcd.c().c(true);
        String str3 = c.get("publicKey");
        String str4 = c.get("privateKey");
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setAuthCode(str);
        signInRequest.setPublicKey(str3);
        signInRequest.setAppId(this.e.getAppIds());
        signInRequest.setRedirectUri("hms://redirect_uri");
        SignInResponse requestSignIn = cloudAccountHandler.requestSignIn(signInRequest, str2);
        if (requestSignIn != null) {
            if (TextUtils.equals("00000", requestSignIn.getResultcode())) {
                UserAccountInfo d = d(requestSignIn);
                mco.b(this.e.getApplication(), "userRefreshToken", mcc.d().b(d.getUserId(), "account_work_key") + "@" + requestSignIn.getUserRefreshToken(), "picture_security_sdk_preferences");
                mco.b(this.e.getApplication(), "authPrivateKey", mcc.d().b(str4, "account_work_key"), "picture_security_sdk_preferences");
                cloudAccountHandler.onRequestFinish(d);
                mcj.c("CloudAccountManager", "signInForUserInfo getAccountInfo success!");
                return;
            }
            mco.b(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
            cloudAccountHandler.onRequestError(new ErrorStatus(mcm.e(requestSignIn.getResultcode(), 0), requestSignIn.getResultinfo()));
            mcj.c("CloudAccountManager", "signInForUserInfo getAccountInfo failed!");
            return;
        }
        mco.b(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
        cloudAccountHandler.onRequestError(new ErrorStatus(-98, ""));
        mcj.c("CloudAccountManager", "signInForUserInfo response is null!");
    }

    private boolean b(CloudAccountHandler cloudAccountHandler) {
        mcj.c("CloudAccountManager", "refreshUserInfo");
        String a2 = a();
        String c = mco.c(this.e.getApplication(), "authPrivateKey", "", "picture_security_sdk_preferences");
        String c2 = mcc.d().c(c, "account_work_key");
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(c2) || TextUtils.equals(c2, c)) {
            mcj.c("CloudAccountManager", "reFreshTokenRequest refreshUserInfo failed: " + TextUtils.isEmpty(a2) + " " + TextUtils.isEmpty(c2));
            d(cloudAccountHandler);
            return false;
        }
        String str = System.currentTimeMillis() + "";
        String encodeToString = SafeBase64.encodeToString(RSASign.sign(str.getBytes(Charset.defaultCharset()), mcd.e(c2), true), 2);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserRefreshToken(a2);
        signInRequest.setAppId(this.e.getAppIds());
        signInRequest.setTimestamp(str);
        signInRequest.setSign(encodeToString);
        SignInResponse requestRefresh = cloudAccountHandler.requestRefresh(signInRequest);
        if (requestRefresh != null) {
            String resultcode = requestRefresh.getResultcode();
            if (TextUtils.equals("00000", resultcode)) {
                UserAccountInfo d = d(requestRefresh);
                mco.b(this.e.getApplication(), "userRefreshToken", mcc.d().b(d.getUserId(), "account_work_key") + "@" + requestRefresh.getUserRefreshToken(), "picture_security_sdk_preferences");
                cloudAccountHandler.onRequestFinish(d);
                mcj.c("CloudAccountManager", "refreshUserInfo getAccountInfo success!");
                return true;
            }
            mco.b(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
            cloudAccountHandler.onRequestError(new ErrorStatus(mcm.e(resultcode, 0), requestRefresh.getResultinfo()));
            mcj.c("CloudAccountManager", "refreshUserInfo getAccountInfo failed!");
        } else {
            mco.b(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
            cloudAccountHandler.onRequestError(new ErrorStatus(-98, ""));
            mcj.c("CloudAccountManager", "refreshUserInfo response is null!");
        }
        return false;
    }

    private void e(CloudAccountHandler cloudAccountHandler) {
        Intent intent = new Intent(this.e.getApplication(), (Class<?>) AccountAgentActivity.class);
        String e = mcr.e(16);
        this.d.put(e, cloudAccountHandler);
        intent.putExtra("handlerId", e);
        intent.setFlags(268435456);
        mcf.cfJ_(this.e.getApplication(), intent);
    }

    private void c(CloudAccountHandler cloudAccountHandler) {
        AccountInitParams accountInitParams = this.e;
        if (accountInitParams == null) {
            mcj.b("CloudAccountManager", "trySignIn mParams is null");
        } else if (accountInitParams.hasHmsCore()) {
            cloudAccountHandler.logFromSdk("trySilentSignIn has hms core");
            i(cloudAccountHandler);
        } else {
            cloudAccountHandler.logFromSdk("trySilentSignIn not has hms core");
            g(cloudAccountHandler);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void i(final com.huawei.picture.security.account.handler.CloudAccountHandler r4) {
        /*
            r3 = this;
            java.lang.String r0 = "CloudAccountManager"
            mbs r1 = d()     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            com.huawei.picture.security.account.bean.AccountInitParams r2 = r3.e     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            android.app.Application r2 = r2.getApplication()     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            com.huawei.hms.support.account.service.AccountAuthService r1 = r1.d(r2)     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            com.huawei.picture.security.account.bean.AccountInitParams r2 = r3.e     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            java.lang.String r2 = r2.getSubAppId()     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            r1.setSubAppId(r2)     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            com.huawei.hmf.tasks.Task r0 = r1.silentSignIn()     // Catch: java.lang.Exception -> L1e com.huawei.hms.common.ApiException -> L24
            goto L2a
        L1e:
            java.lang.String r1 = "trySilentSignIn setSubAppId Exception!"
            defpackage.mcj.b(r0, r1)
            goto L29
        L24:
            java.lang.String r1 = "trySilentSignIn setSubAppId ApiException!"
            defpackage.mcj.b(r0, r1)
        L29:
            r0 = 0
        L2a:
            if (r0 != 0) goto L35
            java.lang.String r0 = "trySilentSignIn task is null"
            r4.logFromSdk(r0)
            r3.g(r4)
            return
        L35:
            mbs$3 r1 = new mbs$3
            r1.<init>()
            r0.addOnSuccessListener(r1)
            mbs$4 r1 = new mbs$4
            r1.<init>()
            r0.addOnFailureListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mbs.i(com.huawei.picture.security.account.handler.CloudAccountHandler):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(CloudAccountHandler cloudAccountHandler) {
        Intent intent = new Intent(this.e.getApplication(), (Class<?>) AccountAgentActivity.class);
        intent.putExtra("isMcp", true);
        intent.putExtra("hasMms", this.e.hasHmsCore());
        intent.putExtra("subAppId", this.e.getSubAppId());
        String e = mcr.e(16);
        this.d.put(e, cloudAccountHandler);
        intent.putExtra("handlerId", e);
        intent.setFlags(268435456);
        mcf.cfJ_(this.e.getApplication(), intent);
    }

    private void f(final CloudAccountHandler cloudAccountHandler) {
        Task<AuthAccount> silentSignIn = d(this.e.getApplication()).silentSignIn();
        silentSignIn.addOnSuccessListener(new OnSuccessListener() { // from class: mbt
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                mbs.this.c(cloudAccountHandler, (AuthAccount) obj);
            }
        });
        silentSignIn.addOnFailureListener(new OnFailureListener() { // from class: mby
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                mbs.d(CloudAccountHandler.this, exc);
            }
        });
    }

    /* synthetic */ void c(final CloudAccountHandler cloudAccountHandler, AuthAccount authAccount) {
        String a2 = a();
        String c = mco.c(this.e.getApplication(), "authPrivateKey", "", "picture_security_sdk_preferences");
        String c2 = mcc.d().c(c, "account_work_key");
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(c2) || TextUtils.equals(c2, c)) {
            mcj.b("CloudAccountManager", "startAccountSDKSilentSignIn refreshToken empty: " + TextUtils.isEmpty(a()) + " ;privateKey empty: " + TextUtils.isEmpty(c2));
            c(authAccount.getAuthorizationCode(), authAccount.getCountryCode(), cloudAccountHandler);
            return;
        }
        String e = e();
        if (TextUtils.isEmpty(e) || !TextUtils.equals(e, authAccount.getUid())) {
            mcj.b("CloudAccountManager", "startAccountSDKSilentSignIn userId empty: " + TextUtils.isEmpty(e));
            c(authAccount.getAuthorizationCode(), authAccount.getCountryCode(), cloudAccountHandler);
            return;
        }
        mci.c(new Runnable() { // from class: mbx
            @Override // java.lang.Runnable
            public final void run() {
                mbs.this.a(cloudAccountHandler);
            }
        });
    }

    /* synthetic */ void a(CloudAccountHandler cloudAccountHandler) {
        try {
            b(cloudAccountHandler);
        } catch (Exception e) {
            mcj.d("CloudAccountManager", "getAccountsByType refreshUserInfo failed: ", e);
        }
    }

    static /* synthetic */ void d(CloudAccountHandler cloudAccountHandler, Exception exc) {
        mcj.d("CloudAccountManager", "startAccountSDKSilentSignIn onFailure: ", exc);
        cloudAccountHandler.onRequestError(new ErrorStatus(-99, ""));
    }

    private UserAccountInfo d(SignInResponse signInResponse) {
        UserAccountInfo userAccountInfo = new UserAccountInfo();
        if (signInResponse != null) {
            UserInfoBean userInfo = signInResponse.getUserInfo();
            List<UserTokenBean> userToken = signInResponse.getUserToken();
            if (userInfo != null) {
                userAccountInfo.setUserId(userInfo.getUserId());
                userAccountInfo.setAccountName(userInfo.getAccountName());
                userAccountInfo.setCountryCode(userInfo.getCountryCode());
                userAccountInfo.setServiceCountryCode(userInfo.getServiceCountryCode());
                userAccountInfo.setLoginUserName(userInfo.getLoginUserName());
                userAccountInfo.setAgeGroupFlag(userInfo.getAgeGroupFlag());
                userAccountInfo.setHeadPictureURL(userInfo.getHeadPictureURL());
                userAccountInfo.setSiteId(0);
                userAccountInfo.setUserTokens(userToken);
            }
        }
        return userAccountInfo;
    }

    private String e() {
        String c = mco.c(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        String[] split = c.split("@");
        if (split.length < 2) {
            return null;
        }
        return mcc.d().c(split[0], "account_work_key");
    }

    private String a() {
        String c = mco.c(this.e.getApplication(), "userRefreshToken", "", "picture_security_sdk_preferences");
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        String[] split = c.split("@");
        if (split.length < 2) {
            return null;
        }
        return split[1];
    }
}
