package com.huawei.watchface;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.picture.security.account.bean.AccountInitParams;
import com.huawei.picture.security.account.bean.AuthErrorBean;
import com.huawei.picture.security.account.bean.ErrorStatus;
import com.huawei.picture.security.account.bean.HitopRespInfo;
import com.huawei.picture.security.account.bean.SignInRequest;
import com.huawei.picture.security.account.bean.SignInResponse;
import com.huawei.picture.security.account.bean.UserAccountInfo;
import com.huawei.picture.security.account.bean.UserTokenBean;
import com.huawei.picture.security.account.handler.CloudAccountHandler;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import defpackage.mbs;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class n {
    private static volatile n c;
    private static UserAccountInfo d;
    private static CloudAccountHandler e;

    /* renamed from: a, reason: collision with root package name */
    private Context f11150a;
    private final Runnable b;
    private mbs f;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private long m;
    private String n;
    private long o;
    private OperateWatchFaceCallback s;
    private List<AuthErrorBean> g = new CopyOnWriteArrayList();
    private boolean p = false;
    private boolean q = false;
    private long r = 0;

    public static n a(Context context) {
        if (c == null) {
            synchronized (n.class) {
                if (c == null) {
                    c = new n(context.getApplicationContext());
                }
            }
        }
        return c;
    }

    n(Context context) {
        this.f11150a = context;
        m();
        this.m = a("REFRESH_EXPIRE_PREF");
        this.o = a("EXPIRE_TIME_PREF");
        this.i = u();
        String v = v();
        this.k = v;
        this.l = TextUtils.isEmpty(v) ? this.h : this.k;
        FlavorConfig.safeHwLog("HwUserTokenServiceManager", "mToken init:" + this.l);
        B();
        this.b = new Runnable() { // from class: com.huawei.watchface.n$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                n.this.C();
            }
        };
        e = new CloudAccountHandler() { // from class: com.huawei.watchface.n.1
            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public void onRequestFinish(UserAccountInfo userAccountInfo) {
                HwLog.i("HwUserTokenServiceManager", "onRequestFinish enter");
                if (userAccountInfo != null) {
                    UserAccountInfo unused = n.d = userAccountInfo;
                    n.this.i = userAccountInfo.getUserId();
                    UserTokenBean s = n.this.s();
                    if (s == null) {
                        HwLog.e("HwUserTokenServiceManager", "onRequestFinish accounts.getUserTokens() is null!");
                        return;
                    }
                    n.this.j = s.getExpire();
                    n.this.k = s.getToken();
                    n nVar = n.this;
                    nVar.l = nVar.k;
                    n.this.t();
                    if (n.this.s != null) {
                        n.this.s.transmitUserTokenUpdate();
                        return;
                    }
                    return;
                }
                HwLog.i("HwUserTokenServiceManager", "onRequestFinish accounts is null!");
            }

            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public void onRequestError(ErrorStatus errorStatus) {
                HwLog.e("HwUserTokenServiceManager", "onRequestError: ErrorCode:" + errorStatus.getErrorCode() + "ErrorReason:" + errorStatus.getErrorReason());
                n nVar = n.this;
                nVar.l = nVar.h;
                n.this.a(-1);
            }

            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public SignInResponse requestSignIn(SignInRequest signInRequest, String str) {
                HwLog.i("HwUserTokenServiceManager", "requestSignIn enter");
                n.this.q = false;
                if (signInRequest == null) {
                    HwLog.e("HwUserTokenServiceManager", "signInRequest is null");
                    return null;
                }
                SafeBundle safeBundle = new SafeBundle();
                safeBundle.putString("authCode", signInRequest.getAuthCode());
                safeBundle.putString("countryCode", str);
                safeBundle.putString("appId", "10003");
                safeBundle.putString("redirectUri", "hms://redirect_uri");
                safeBundle.putString("publicKey", signInRequest.getPublicKey());
                ca caVar = new ca(safeBundle.getBundle());
                SignInResponse c2 = caVar.c(caVar.c());
                if (c2 != null && c2.getUserInfo() != null) {
                    n.this.n = String.valueOf(c2.getUserInfo().getAgeGroupFlag());
                    HwLog.d("HwUserTokenServiceManager", "mAccountType:" + n.this.n + ",sp mAccountType:" + n.this.x());
                    dp.b(n.this.f11150a, true);
                    q.a().a(HwWatchFaceApi.getInstance(n.this.f11150a).getDeviceIdentify());
                    n.this.a(c2.getUserInfo().getAgeGroupFlag());
                } else {
                    HwLog.d("HwUserTokenServiceManager", "response is null or getUserInfo is null");
                    n.this.a(-1);
                }
                return c2;
            }

            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public SignInResponse requestRefresh(SignInRequest signInRequest) {
                HwLog.i("HwUserTokenServiceManager", "requestRefresh enter");
                if (signInRequest == null) {
                    HwLog.e("HwUserTokenServiceManager", "signInRequest is null");
                    return null;
                }
                SafeBundle safeBundle = new SafeBundle();
                safeBundle.putString("appId", "10003");
                safeBundle.putString("timestamp", signInRequest.getTimestamp());
                safeBundle.putString(HwPayConstant.KEY_SIGN, signInRequest.getSign());
                safeBundle.putString("userRefreshToken", signInRequest.getUserRefreshToken());
                bu buVar = new bu(safeBundle.getBundle());
                return buVar.c(buVar.c());
            }

            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public void logFromSdk(String str) {
                HwLog.i("HwUserTokenServiceManager", "logFromSdk:" + str);
            }

            @Override // com.huawei.picture.security.account.handler.CloudAccountHandler
            public boolean isLoginAccount() {
                HwLog.i("HwUserTokenServiceManager", "isLoginAccount enter");
                return HwWatchFaceApi.getInstance(n.this.f11150a).isLogin();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        try {
            HwLog.i("HwUserTokenServiceManager", "updateToken on runnable");
            n();
        } catch (Exception e2) {
            HwLog.i("HwUserTokenServiceManager", "updateToken exception: " + HwLog.printException(e2));
        }
    }

    private void m() {
        synchronized (this) {
            HwLog.i("HwUserTokenServiceManager", "getAccessToken enter ");
            if (TextUtils.isEmpty(this.h)) {
                this.h = HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
                FlavorConfig.safeHwLog("HwUserTokenServiceManager", "getAccessToken mRefreshAccessToken:" + this.h);
            }
            if (TextUtils.isEmpty(this.h)) {
                n();
            } else {
                q();
            }
        }
    }

    private void n() {
        synchronized (this) {
            HwLog.i("HwUserTokenServiceManager", "refreshAccessToken enter ");
            HwWatchFaceApi.getInstance(this.f11150a).refreshAccessToken(new HwWatchFaceApi.RefreshAccessTokenListener() { // from class: com.huawei.watchface.n.2
                @Override // com.huawei.watchface.api.HwWatchFaceApi.RefreshAccessTokenListener
                public void onLoginSuccess(Object obj) {
                    if (obj instanceof String) {
                        n.this.h = (String) obj;
                        n.this.q();
                    }
                }

                @Override // com.huawei.watchface.api.HwWatchFaceApi.RefreshAccessTokenListener
                public void onLoginFailed(Object obj) {
                    HwLog.e("HwUserTokenServiceManager", "onLoginFailed enter object: " + GsonHelper.toJson(obj));
                }
            });
        }
    }

    private List<Scope> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.ACCOUNT_BASE_PROFILE)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_COUNTRY)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_REALNAME_ANONYMOUS)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_AGE_RANGE)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_CLOUD_POSITION)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_GENDER)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_UPDATEGENDER)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_BIRTHDAY)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_LIST)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_ACCOUNT_FLAGS)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_HEALTHKIT_SPORT)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_HEALTHKIT_REALTIMEHEARTRATE)));
        arrayList.add(new Scope(DensityUtil.getStringById(R$string.SCOPE_HEALTHKIT_HEARTRATE)));
        return arrayList;
    }

    private void p() {
        synchronized (this) {
            if (this.b != null) {
                HwLog.i("HwUserTokenServiceManager", "stopPolling: removeTaskFromWorker");
                BackgroundTaskUtils.b(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        synchronized (this) {
            p();
            HwLog.i("HwUserTokenServiceManager", "startPolling");
            Runnable runnable = this.b;
            if (runnable != null) {
                BackgroundTaskUtils.a(runnable, 3600000);
            }
        }
    }

    private void r() {
        HwLog.i("HwUserTokenServiceManager", "getAccountInfo -- enter!");
        if (e == null) {
            HwLog.e("HwUserTokenServiceManager", "getAccountInfo -- mCloudAccountHandler can not be null!");
            return;
        }
        if (this.f == null) {
            HwLog.e("HwUserTokenServiceManager", "getAccountInfo -- cloudAccountManager can not be null!");
        } else if (CommonUtils.t()) {
            HwLog.e("HwUserTokenServiceManager", "getAccountInfo -- cloudAccountManager is backGround!");
            this.q = true;
        } else {
            this.f.d(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UserTokenBean s() {
        HwLog.i("HwUserTokenServiceManager", "getCurrentUserTokenBean enter");
        UserAccountInfo userAccountInfo = d;
        if (userAccountInfo == null || userAccountInfo.getUserTokens() == null) {
            HwLog.e("HwUserTokenServiceManager", "mUserAccountInfo token is empty");
            return null;
        }
        for (UserTokenBean userTokenBean : d.getUserTokens()) {
            if (!TextUtils.isEmpty(userTokenBean.getAppId()) && userTokenBean.getAppId().equals("10003")) {
                return userTokenBean;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void t() {
        /*
            r10 = this;
            android.app.Application r0 = com.huawei.watchface.environment.Environment.getApplicationContext()
            java.lang.String r1 = "USER_TOKEN_FILE_PREF"
            android.content.SharedPreferences r0 = com.huawei.watchface.dp.a(r0, r1)
            java.lang.String r1 = "HwUserTokenServiceManager"
            if (r0 != 0) goto L14
            java.lang.String r0 = "preferences is null"
            com.huawei.watchface.utils.HwLog.i(r1, r0)
            return
        L14:
            java.lang.String r2 = r10.j
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L8a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "mExpireInterval vaule ="
            r2.<init>(r3)
            java.lang.String r3 = r10.j
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.huawei.watchface.utils.HwLog.i(r1, r2)
            r2 = 0
            java.lang.String r4 = r10.j     // Catch: java.lang.Exception -> L4b
            long r4 = com.huawei.watchface.dh.a(r4, r2)     // Catch: java.lang.Exception -> L4b
            long r4 = r10.a(r4)     // Catch: java.lang.Exception -> L4b
            java.lang.String r6 = r10.j     // Catch: java.lang.Exception -> L49
            long r6 = com.huawei.watchface.dh.a(r6, r2)     // Catch: java.lang.Exception -> L49
            r8 = 2
            long r6 = r6 / r8
            long r6 = r10.a(r6)     // Catch: java.lang.Exception -> L49
            goto L64
        L49:
            r6 = move-exception
            goto L4e
        L4b:
            r4 = move-exception
            r6 = r4
            r4 = r2
        L4e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "saveAccountInfo Exception:"
            r7.<init>(r8)
            java.lang.String r6 = com.huawei.watchface.utils.HwLog.printException(r6)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.huawei.watchface.utils.HwLog.i(r1, r6)
            r6 = r2
        L64:
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L77
            r10.o = r4
            android.content.SharedPreferences$Editor r1 = r0.edit()
            java.lang.String r8 = "EXPIRE_TIME_PREF"
            android.content.SharedPreferences$Editor r1 = r1.putLong(r8, r4)
            r1.apply()
        L77:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 == 0) goto L8a
            r10.m = r6
            android.content.SharedPreferences$Editor r1 = r0.edit()
            java.lang.String r2 = "REFRESH_EXPIRE_PREF"
            android.content.SharedPreferences$Editor r1 = r1.putLong(r2, r6)
            r1.apply()
        L8a:
            java.lang.String r1 = r10.k
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r2 = "storagePw"
            if (r1 != 0) goto La7
            java.lang.String r1 = r10.k
            java.lang.String r1 = com.huawei.watchface.ao.a(r1, r2)
            android.content.SharedPreferences$Editor r3 = r0.edit()
            java.lang.String r4 = "USER_TOKEN_VALUE_PREF"
            android.content.SharedPreferences$Editor r1 = r3.putString(r4, r1)
            r1.apply()
        La7:
            java.lang.String r1 = r10.i
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto Lc2
            java.lang.String r1 = r10.i
            java.lang.String r1 = com.huawei.watchface.ao.a(r1, r2)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.lang.String r2 = "USER_ID_PREF"
            android.content.SharedPreferences$Editor r0 = r0.putString(r2, r1)
            r0.apply()
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.n.t():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        HwLog.d("HwUserTokenServiceManager", "saveChildInfo enter accountType:" + i);
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "USER_TOKEN_FILE_PREF");
        if (a2 == null) {
            HwLog.i("HwUserTokenServiceManager", "preferences is null");
        } else {
            a2.edit().putString("USER_ACCOUNT_TYPE", String.valueOf(i)).apply();
        }
    }

    private long a(long j) {
        int minutes = (int) TimeUnit.SECONDS.toMinutes(j);
        if (minutes <= 0) {
            return System.currentTimeMillis();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(12, minutes);
        return calendar.getTimeInMillis();
    }

    private String u() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "USER_TOKEN_FILE_PREF");
        if (a2 == null) {
            HwLog.i("HwUserTokenServiceManager", "sharedPreferences is null");
            return null;
        }
        String string = a2.getString("USER_ID_PREF", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return ao.b(string, "storagePw");
    }

    private String v() {
        if (y()) {
            return null;
        }
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "USER_TOKEN_FILE_PREF");
        if (a2 == null) {
            HwLog.i("HwUserTokenServiceManager", "sharedPreferences is null");
            return null;
        }
        String string = a2.getString("USER_TOKEN_VALUE_PREF", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return ao.b(string, "storagePw");
    }

    private long a(String str) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "USER_TOKEN_FILE_PREF");
        if (a2 == null || TextUtils.isEmpty(str)) {
            HwLog.i("HwUserTokenServiceManager", "sharedPreferences is null or key is empty");
            return 0L;
        }
        return a2.getLong(str, 0L);
    }

    private boolean w() {
        String userId = HwWatchFaceApi.getInstance(this.f11150a).getUserId();
        if (TextUtils.isEmpty(userId)) {
            return false;
        }
        if (TextUtils.isEmpty(this.i)) {
            return true;
        }
        boolean equals = this.i.equals(userId);
        HwLog.i("HwUserTokenServiceManager", "isCurrentUser:" + equals);
        return equals;
    }

    public boolean a() {
        try {
            if (this.n == null) {
                this.n = x();
            }
            String str = this.n;
            r1 = str != null ? Integer.parseInt(str) : -1;
            HwLog.d("HwUserTokenServiceManager", "mAccountType:" + this.n);
        } catch (NumberFormatException e2) {
            HwLog.d("HwUserTokenServiceManager", "isChildAccount NumberFormatException:" + HwLog.printException((Exception) e2));
        } catch (Exception e3) {
            HwLog.d("HwUserTokenServiceManager", "isChildAccount Exception:" + HwLog.printException(e3));
        }
        return r1 == 2 || r1 == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String x() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "USER_TOKEN_FILE_PREF");
        if (a2 == null) {
            HwLog.i("HwUserTokenServiceManager", "sharedPreferences is null");
            return null;
        }
        return a2.getString("USER_ACCOUNT_TYPE", null);
    }

    private boolean y() {
        boolean w = w();
        HwLog.i("HwUserTokenServiceManager", "isUserTokenTimeExpired isCurrentUser:" + w);
        if (w) {
            return System.currentTimeMillis() > this.o;
        }
        HwLog.i("HwUserTokenServiceManager", "isUserTokenTimeExpired isCurrentUser is not valid:");
        return true;
    }

    private boolean z() {
        boolean w = w();
        HwLog.i("HwUserTokenServiceManager", "isRefreshTimeExpired isCurrentUser:" + w);
        if (w) {
            return System.currentTimeMillis() > this.m;
        }
        HwLog.i("HwUserTokenServiceManager", "isRefreshTimeExpired isCurrentUser is not valid:");
        return true;
    }

    private String b(String str) {
        HwLog.i("HwUserTokenServiceManager", "encodeToken -- enter");
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            HwLog.i("HwUserTokenServiceManager", HwLog.printException((Exception) e2));
            return str;
        }
    }

    private boolean A() {
        if (cu.d()) {
            if (CommonUtils.m(this.f11150a)) {
                return true;
            }
            HwLog.w("HwUserTokenServiceManager", "hasHms() isNewHonor.");
            return false;
        }
        if (!HwWatchFaceApi.getInstance(this.f11150a).isHmsLiteEnable()) {
            return true;
        }
        HwLog.w("HwUserTokenServiceManager", "hasHms() isHmsLiteEnable.");
        return false;
    }

    public void a(List<String> list) {
        List<AuthErrorBean> list2 = this.g;
        if (list2 == null) {
            return;
        }
        list2.clear();
        try {
            if (!ArrayUtils.isEmpty(list)) {
                HwLog.i("HwUserTokenServiceManager", "updateAuthErrorList authErrorStrList " + list.size());
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    AuthErrorBean authErrorBean = (AuthErrorBean) GsonHelper.fromJson(it.next(), AuthErrorBean.class);
                    if (authErrorBean != null) {
                        this.g.add(authErrorBean);
                    }
                }
            }
        } catch (Exception e2) {
            HwLog.e("HwUserTokenServiceManager", "updateAuthErrorList Exception:" + HwLog.printException(e2));
        }
        if (ArrayUtils.isEmpty(this.g)) {
            HwLog.i("HwUserTokenServiceManager", "updateAuthErrorList authErrorParamList is empty, add default AuthErrorList.");
            B();
        }
        mbs mbsVar = this.f;
        if (mbsVar == null) {
            HwLog.i("HwUserTokenServiceManager", "mCloudAccountManager cannot be null");
        } else {
            mbsVar.c(this.g);
        }
    }

    private void B() {
        this.g.add(new AuthErrorBean("/servicesupport/market/getResourceByCode.do", "resultCode", OpAnalyticsConstants.WATCH_FAIL_CODE));
        this.g.add(new AuthErrorBean("/servicesupport/theme/tis/service/v2/downloadinfo/queryfree", "resultcode", "-1"));
        this.g.add(new AuthErrorBean("/servicesupport/theme/downloadFeedback.do", "resultcode", "2"));
        this.g.add(new AuthErrorBean("/servicesupport/ugc/service/v1/getBiUserSearchList.do", "resultCode", "90200001"));
        this.g.add(new AuthErrorBean("/servicesupport/ugc/service/v1/getUserinfo.do", "resultcode", "90200001"));
        this.g.add(new AuthErrorBean("/servicesupport/ugc/service/v1/posts/newcreate.do", "resultcode", "90100005"));
        this.g.add(new AuthErrorBean("/servicesupport/ugc/service/v1/posts/newcreate.do", "resultcode", "90200001"));
        this.g.add(new AuthErrorBean("/servicesupport/ugc/service/passThrough.do", "resultcode", "90200001"));
        this.g.add(new AuthErrorBean("/servicesupport/theme/tis/service/v2/commentList/query", "resultcode", "6"));
    }

    public void a(boolean z) {
        synchronized (this) {
            String x = x();
            this.n = x;
            if (x == null) {
                b(true);
            } else {
                b(z);
            }
        }
    }

    public void b(final boolean z) {
        synchronized (this) {
            HwLog.i("HwUserTokenServiceManager", "init -- enter isLoginBroadcastReceived" + z);
            if (Environment.getApplicationContext() == null) {
                HwLog.e("HwUserTokenServiceManager", "init -- application context can not be null!");
                return;
            }
            if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
                HwLog.e("HwUserTokenServiceManager", "init -- is test");
                return;
            }
            if (!z && this.f != null && w()) {
                HwLog.e("HwUserTokenServiceManager", "init -- mCloudAccountManager is already initialized!");
                return;
            }
            mbs d2 = mbs.d();
            this.f = d2;
            if (d2 == null) {
                HwLog.e("HwUserTokenServiceManager", "init -- cloudAccountManager can not be null!");
                return;
            }
            if (Math.abs(System.currentTimeMillis() - this.r) < 5000) {
                HwLog.e("HwUserTokenServiceManager", "init interval is less 5s");
                return;
            }
            this.r = System.currentTimeMillis();
            if (z) {
                this.h = HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
                HwWatchFaceApi.getInstance(this.f11150a).refreshAccessToken(new HwWatchFaceApi.RefreshAccessTokenListener() { // from class: com.huawei.watchface.n.3
                    @Override // com.huawei.watchface.api.HwWatchFaceApi.RefreshAccessTokenListener
                    public void onLoginSuccess(Object obj) {
                        if (obj instanceof String) {
                            n.this.h = (String) obj;
                            n.this.d(z);
                        }
                    }

                    @Override // com.huawei.watchface.api.HwWatchFaceApi.RefreshAccessTokenListener
                    public void onLoginFailed(Object obj) {
                        n.this.d(z);
                        HwLog.i("HwUserTokenServiceManager", "init -- onLoginFailed enter object: " + GsonHelper.toJson(obj));
                    }
                });
            } else if (TextUtils.isEmpty(this.h)) {
                HwLog.i("HwUserTokenServiceManager", "init -- mRefreshAccessToken can not be null");
            } else {
                e(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        HwLog.i("HwUserTokenServiceManager", "getAccountInfoIfTokenIsValid -- enter" + z);
        if (TextUtils.isEmpty(this.h)) {
            this.h = HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
        }
        if (TextUtils.isEmpty(this.h)) {
            HwLog.i("HwUserTokenServiceManager", "getAccountInfoIfTokenIsValid -- mRefreshAccessToken can not be empty or null: ");
        } else {
            e(z);
        }
    }

    private void e(boolean z) {
        try {
            HwLog.i("HwUserTokenServiceManager", "getAccountInfoWithValidToken -- enter" + z);
            dp.b(this.f11150a, true);
            List<Scope> o = o();
            String l = l();
            AccountInitParams build = new AccountInitParams.Builder().setApplication(Environment.getApplicationContext()).setMcp(true).setAppIds("10003").setAuthErrorParamList(this.g).setScopes(o).setAccessToken(this.h).setSubAppId(l).setDeviceId(HwWatchFaceApi.getInstance(this.f11150a).getDeviceId()).setGrsAppName("healthcloud").hasHmsCore(A()).build();
            if (build == null) {
                HwLog.i("HwUserTokenServiceManager", "init -- mcpParams can not be null!");
                return;
            }
            this.f.e(build);
            if (z || z()) {
                r();
            }
        } catch (Exception e2) {
            HwLog.i("HwUserTokenServiceManager", "getAccountInfoWithValidToken Exception：" + HwLog.printException(e2));
        }
    }

    public void a(StringBuffer stringBuffer) {
        HwLog.i("HwUserTokenServiceManager", "setUserTokenToParameter enter");
        if (stringBuffer == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.k) && !y()) {
            stringBuffer.append("&userToken=").append(b(this.k));
            stringBuffer.append("&authtype=").append("ST");
        } else {
            String b = b(HwWatchFaceApi.getInstance(this.f11150a).getAccessToken());
            stringBuffer.append("&authtype=").append(2 == HwWatchFaceApi.getInstance(this.f11150a).getAccessTokenType() ? "AT" : "ST");
            stringBuffer.append("&userToken=").append(b);
        }
    }

    public void a(LinkedHashMap<String, String> linkedHashMap, boolean z) {
        HwLog.i("HwUserTokenServiceManager", "setUserTokenToHeader enter:" + TextUtils.isEmpty(this.k));
        if (linkedHashMap == null) {
            return;
        }
        if (z && !TextUtils.isEmpty(this.k) && !y()) {
            linkedHashMap.put("usertoken", this.k);
            linkedHashMap.put("authtype", "ST");
        } else {
            String accessToken = HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
            linkedHashMap.put("authtype", 2 == HwWatchFaceApi.getInstance(this.f11150a).getAccessTokenType() ? "AT" : "ST");
            linkedHashMap.put("usertoken", accessToken);
        }
    }

    public String b() {
        return this.n;
    }

    public String c() {
        if (TextUtils.isEmpty(this.l) || y()) {
            return HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
        }
        return this.l;
    }

    public String d() {
        if (TextUtils.isEmpty(this.k) || y()) {
            return HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
        }
        return this.k;
    }

    public void e() {
        if (w() || TextUtils.isEmpty(this.i)) {
            return;
        }
        a(true);
    }

    public String f() {
        return this.k;
    }

    public boolean a(HitopRespInfo hitopRespInfo) {
        mbs mbsVar = this.f;
        if (mbsVar == null) {
            HwLog.i("HwUserTokenServiceManager", "refreshIfAuthFail -- cloudAccountManager can not be null!");
            return false;
        }
        return mbsVar.a(e, hitopRespInfo);
    }

    public String g() {
        StringBuffer stringBuffer = new StringBuffer(2);
        String deviceType = HwWatchFaceApi.getInstance(this.f11150a).getDeviceType();
        if (TextUtils.isEmpty(this.k) || y()) {
            stringBuffer.append(HwWatchFaceApi.getInstance(this.f11150a).getAccessToken()).append("_").append(deviceType);
            if (2 == HwWatchFaceApi.getInstance(this.f11150a).getAccessTokenType()) {
                stringBuffer.append("_AT");
            } else {
                stringBuffer.append("_ST");
            }
        } else {
            stringBuffer.append(this.k).append("_").append(deviceType).append("_ST");
        }
        return stringBuffer.toString();
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.s = operateWatchFaceCallback;
    }

    public String[] h() {
        String[] strArr = new String[2];
        if (TextUtils.isEmpty(this.k) || y()) {
            strArr[0] = HwWatchFaceApi.getInstance(this.f11150a).getAccessToken();
            if (2 == HwWatchFaceApi.getInstance(this.f11150a).getAccessTokenType()) {
                strArr[1] = "AT";
            } else {
                strArr[1] = "ST";
            }
        } else {
            strArr[0] = this.k;
            strArr[1] = "ST";
        }
        return strArr;
    }

    public boolean i() {
        return this.p;
    }

    public void c(boolean z) {
        this.p = z;
    }

    public void j() {
        synchronized (this) {
            HwLog.i("HwUserTokenServiceManager", "destroy");
            p();
            e = null;
            d = null;
            this.f = null;
            c = null;
        }
    }

    public void k() {
        if (this.q) {
            HwLog.i("HwUserTokenServiceManager", "refreshAccountInfo -- enter!");
            CloudAccountHandler cloudAccountHandler = e;
            if (cloudAccountHandler == null) {
                HwLog.e("HwUserTokenServiceManager", "refreshAccountInfo -- mCloudAccountHandler can not be null!");
                return;
            }
            mbs mbsVar = this.f;
            if (mbsVar == null) {
                HwLog.e("HwUserTokenServiceManager", "refreshAccountInfo -- cloudAccountManager can not be null!");
            } else {
                mbsVar.d(cloudAccountHandler);
                this.q = false;
            }
        }
    }

    private String l() {
        return FlavorConfig.MCP_APP_ID;
    }
}
