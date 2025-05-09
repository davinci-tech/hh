package com.huawei.hms.support.hwid.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.api.OptionalPendingResult;
import com.huawei.hms.common.api.internal.OptionalPendingResultImpl;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.hwid.as;
import com.huawei.hms.hwid.x;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.core.ConnectService;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonNaming;
import com.huawei.hms.support.api.entity.core.JosGetNoticeResp;
import com.huawei.hms.support.api.entity.hwid.RevokeAccessResp;
import com.huawei.hms.support.api.entity.hwid.SignInReq;
import com.huawei.hms.support.api.entity.hwid.SignInResp;
import com.huawei.hms.support.api.entity.hwid.SignOutReq;
import com.huawei.hms.support.api.entity.hwid.SignOutResp;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hms.ui.SafeBundle;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class HuaweiIdAuthAPIServiceImpl implements HuaweiIdAuthAPIService {

    /* renamed from: a, reason: collision with root package name */
    private HuaweiApiClient f6014a = null;
    private WeakReference<Activity> b = null;

    static List<Scope> a(HuaweiApiClient huaweiApiClient) {
        return ((HuaweiApiClientImpl) huaweiApiClient).getScopes();
    }

    static List<PermissionInfo> b(HuaweiApiClient huaweiApiClient) {
        return ((HuaweiApiClientImpl) huaweiApiClient).getPermissionInfos();
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public PendingResult<SignInResult> signIn(Activity activity, HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter signIn", true);
        Checker.checkNonNull(activity, "Activity must not be null.");
        Checker.checkNonNull(huaweiApiClient, "HuaweiApiClient must not be null.");
        List<Scope> a2 = a(huaweiApiClient);
        List<PermissionInfo> b2 = b(huaweiApiClient);
        HashSet hashSet = new HashSet();
        Iterator<Scope> it = a2.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getScopeUri());
        }
        HashSet hashSet2 = new HashSet();
        Iterator<PermissionInfo> it2 = b2.iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().getPermission());
        }
        SignInReq signInReq = new SignInReq(hashSet, hashSet2);
        this.f6014a = huaweiApiClient;
        this.b = new WeakReference<>(activity);
        return new PendingResultImpl<SignInResult, SignInResp>(huaweiApiClient, CommonNaming.signin, signInReq) { // from class: com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIServiceImpl.1
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SignInResult onComplete(SignInResp signInResp) {
                as.b("HuaweiIdAuthAPIServiceImpl", "signIn onComplete:" + signInResp.getRetCode(), true);
                HuaweiIdAuthAPIServiceImpl.this.a(signInResp.getRetCode());
                Intent data = signInResp.getData();
                data.putExtra(CommonConstant.RETKEY.RETCODE, signInResp.getRetCode());
                return HuaweiIdAuthAPIServiceImpl.this.getHwIdSignInResultFromIntent(data);
            }
        };
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public PendingResult<SignInResult> signInBackend(HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter signInBackend", true);
        Checker.checkNonNull(huaweiApiClient, "HuaweiApiClient must not be null.");
        this.f6014a = huaweiApiClient;
        List<Scope> a2 = a(huaweiApiClient);
        List<PermissionInfo> b2 = b(huaweiApiClient);
        HashSet hashSet = new HashSet();
        Iterator<Scope> it = a2.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getScopeUri());
        }
        HashSet hashSet2 = new HashSet();
        Iterator<PermissionInfo> it2 = b2.iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().getPermission());
        }
        return new PendingResultImpl<SignInResult, SignInResp>(huaweiApiClient, CommonNaming.singinbackend, new SignInReq(hashSet, hashSet2)) { // from class: com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIServiceImpl.2
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SignInResult onComplete(SignInResp signInResp) {
                as.b("HuaweiIdAuthAPIServiceImpl", "signInBackend onComplete:" + signInResp.getRetCode(), true);
                Intent data = signInResp.getData();
                data.putExtra(CommonConstant.RETKEY.RETCODE, signInResp.getRetCode());
                return HuaweiIdAuthAPIServiceImpl.this.getHwIdSignInResultFromIntent(data);
            }
        };
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public PendingResult<Status> signOut(HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter signOut", true);
        if (!huaweiApiClient.isConnected()) {
            return d(huaweiApiClient);
        }
        return new c(huaweiApiClient, "hwid.signout", new SignOutReq());
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public SignInResult getHwIdSignInResultFromIntent(Intent intent) {
        PendingIntent activity;
        if (intent != null && intent.getExtras() != null) {
            SafeBundle safeBundle = new SafeBundle(intent.getExtras());
            int i = safeBundle.getInt(CommonConstant.RETKEY.RETCODE, 0);
            if (i == 0) {
                String string = safeBundle.getString(CommonConstant.RETKEY.GENDER);
                String string2 = safeBundle.getString(CommonConstant.RETKEY.USERID, "");
                String string3 = safeBundle.getString(CommonConstant.RETKEY.STATUS);
                String string4 = safeBundle.getString(CommonConstant.RETKEY.SCOPE);
                String string5 = safeBundle.getString(CommonConstant.RETKEY.ID_TOKEN);
                String string6 = safeBundle.getString(CommonConstant.RETKEY.EMAIL);
                String string7 = safeBundle.getString(CommonConstant.RETKEY.FAMILY_NAME);
                String string8 = safeBundle.getString(CommonConstant.RETKEY.GIVEN_NAME);
                String string9 = safeBundle.getString(CommonConstant.RETKEY.AGE_RANGE);
                String string10 = safeBundle.getString(CommonConstant.RETKEY.HOME_ZONE);
                AuthHuaweiId build = AuthHuaweiId.build(safeBundle.getString(CommonConstant.RETKEY.OPENID, ""), string2, safeBundle.getString(CommonConstant.RETKEY.DISPLAYNAME, ""), safeBundle.getString(CommonConstant.RETKEY.PHOTOURL), safeBundle.getString(CommonConstant.RETKEY.ACCESS_TOKEN, ""), safeBundle.getString(CommonConstant.RETKEY.SERVICECOUNTRYCODE, ""), conversionStatus(string3), conversionGender(string), conversionScopes(string4), safeBundle.getString(CommonConstant.RETKEY.SERVICEAUTHCODE, ""), safeBundle.getString(CommonConstant.RETKEY.UNIONID, ""), safeBundle.getString(CommonConstant.RETKEY.COUNTRYCODE, ""));
                build.setIdToken(string5);
                build.setEmail(string6);
                build.setFamilyName(string7);
                build.setGivenName(string8);
                build.setAgeRange(string9);
                build.setHomeZone(conversionHomeZone(string10));
                Status status = new Status(i);
                SignInResult signInResult = new SignInResult(status);
                signInResult.setStatus(status);
                signInResult.setAuthHuaweiId(build);
                as.b("HuaweiIdAuthAPIServiceImpl", "getSignInResult success", true);
                return signInResult;
            }
            if (i == 2002 || i == 2004) {
                Random random = new Random();
                as.b("HuaweiIdAuthAPIServiceImpl", "SDK version >= 23", true);
                activity = PendingIntent.getActivity(this.f6014a.getContext(), random.nextInt(), intent, AppRouterExtras.COLDSTART);
            } else {
                activity = null;
            }
            Status status2 = new Status(i, (String) null, activity);
            SignInResult signInResult2 = new SignInResult(status2);
            signInResult2.setStatus(status2);
            signInResult2.setData(intent);
            as.b("HuaweiIdAuthAPIServiceImpl", "getSignInResult fail", true);
            return signInResult2;
        }
        SignInResult signInResult3 = new SignInResult(new Status(2003));
        as.d("HuaweiIdAuthAPIServiceImpl", "getSignInResult error", true);
        return signInResult3;
    }

    public HuaweiIdAuthResult getHuaweiIdSignInResultFromIntent(Intent intent) {
        Status status;
        HuaweiIdAuthResult huaweiIdAuthResult = new HuaweiIdAuthResult();
        if (intent != null && intent.getExtras() != null) {
            SafeBundle safeBundle = new SafeBundle(intent.getExtras());
            int i = safeBundle.getInt(CommonConstant.RETKEY.RETCODE, 0);
            status = new Status(i);
            if (i == 0) {
                huaweiIdAuthResult.setAuthHuaweiId(a(safeBundle));
                as.b("HuaweiIdAuthAPIServiceImpl", "getSignInResult success", true);
            } else {
                as.b("HuaweiIdAuthAPIServiceImpl", "getSignInResult fail", true);
            }
        } else {
            status = new Status(2003);
            as.d("HuaweiIdAuthAPIServiceImpl", "getSignInResult error", true);
        }
        huaweiIdAuthResult.setStatus(status);
        return huaweiIdAuthResult;
    }

    private AuthHuaweiId a(SafeBundle safeBundle) {
        int i;
        if (safeBundle == null) {
            as.b("HuaweiIdAuthAPIServiceImpl", "bd is null", true);
            return new AuthHuaweiId();
        }
        String string = safeBundle.getString(CommonConstant.RETKEY.ACCESS_TOKEN, "");
        String string2 = safeBundle.getString(CommonConstant.RETKEY.SERVICEAUTHCODE, "");
        String string3 = safeBundle.getString(CommonConstant.RETKEY.DISPLAYNAME, "");
        String string4 = safeBundle.getString(CommonConstant.RETKEY.USERID, "");
        String string5 = safeBundle.getString(CommonConstant.RETKEY.OPENID, "");
        String string6 = safeBundle.getString(CommonConstant.RETKEY.PHOTOURL);
        String string7 = safeBundle.getString(CommonConstant.RETKEY.SERVICECOUNTRYCODE, "");
        String string8 = safeBundle.getString(CommonConstant.RETKEY.COUNTRYCODE, "");
        String string9 = safeBundle.getString(CommonConstant.RETKEY.UNIONID, "");
        int a2 = a(safeBundle.getString(CommonConstant.RETKEY.GENDER));
        int b2 = b(safeBundle.getString(CommonConstant.RETKEY.STATUS));
        int c2 = c(safeBundle.getString(CommonConstant.RETKEY.HOME_ZONE));
        String string10 = safeBundle.getString(CommonConstant.RETKEY.SCOPE);
        HashSet hashSet = new HashSet();
        if (string10 != null) {
            String[] split = string10.split(" ");
            int length = split.length;
            i = c2;
            int i2 = 0;
            while (i2 < length) {
                hashSet.add(new Scope(split[i2]));
                i2++;
                length = length;
                split = split;
            }
        } else {
            i = c2;
        }
        AuthHuaweiId build = AuthHuaweiId.build(string5, string4, string3, string6, string, string7, b2, a2, hashSet, string2, string9, string8);
        build.setIdToken(safeBundle.getString(CommonConstant.RETKEY.ID_TOKEN));
        build.setEmail(safeBundle.getString(CommonConstant.RETKEY.EMAIL));
        build.setFamilyName(safeBundle.getString(CommonConstant.RETKEY.FAMILY_NAME));
        build.setGivenName(safeBundle.getString(CommonConstant.RETKEY.GIVEN_NAME));
        build.setAgeRange(safeBundle.getString(CommonConstant.RETKEY.AGE_RANGE));
        build.setHomeZone(i);
        return build;
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-1";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private int c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            as.d("HuaweiIdAuthAPIServiceImpl", "parse homeZone error.", true);
            return 0;
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public HuaweiIdAuthResult parseHuaweiIdFromIntent(Intent intent) {
        return x.a(intent);
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public Intent getSignInIntent(HuaweiApiClient huaweiApiClient) {
        Context context = huaweiApiClient.getContext();
        HuaweiIdAuthParams signInOption = getSignInOption(huaweiApiClient);
        if (signInOption == null) {
            return null;
        }
        return x.a(context, signInOption, huaweiApiClient.getSubAppInfo() != null ? huaweiApiClient.getSubAppInfo().getSubAppID() : null);
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public PendingResult<Status> cancelAuthorization(HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter cancelAuthorization", true);
        this.f6014a = huaweiApiClient;
        return new b(huaweiApiClient, "hwid.revokeAccess", c(huaweiApiClient));
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService
    public OptionalPendingResult<HuaweiIdAuthResult> silentSignIn(HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter silentSignIn", true);
        Checker.checkNonNull(huaweiApiClient, "HuaweiApiClient must not be null.");
        this.f6014a = huaweiApiClient;
        return new OptionalPendingResultImpl(new PendingResultImpl<HuaweiIdAuthResult, SignInResp>(huaweiApiClient, CommonNaming.singinbackend, c(huaweiApiClient)) { // from class: com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIServiceImpl.3
            @Override // com.huawei.hms.support.api.PendingResultImpl
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HuaweiIdAuthResult onComplete(SignInResp signInResp) {
                as.b("HuaweiIdAuthAPIServiceImpl", "signInBackend onComplete:" + signInResp.getRetCode(), true);
                Intent data = signInResp.getData();
                data.putExtra(CommonConstant.RETKEY.RETCODE, signInResp.getRetCode());
                return HuaweiIdAuthAPIServiceImpl.this.getHuaweiIdSignInResultFromIntent(data);
            }
        });
    }

    private SignInReq c(HuaweiApiClient huaweiApiClient) {
        List<Scope> a2 = a(huaweiApiClient);
        List<PermissionInfo> b2 = b(huaweiApiClient);
        HashSet hashSet = new HashSet();
        Iterator<Scope> it = a2.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getScopeUri());
        }
        HashSet hashSet2 = new HashSet();
        Iterator<PermissionInfo> it2 = b2.iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().getPermission());
        }
        return new SignInReq(hashSet, hashSet2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0 && HuaweiApiAvailability.getInstance().isHuaweiMobileNoticeAvailable(this.f6014a.getContext()) == 0) {
            new Timer().schedule(new TimerTask() { // from class: com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIServiceImpl.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ConnectService.getNotice(HuaweiIdAuthAPIServiceImpl.this.f6014a, 1, "6.12.0.300").setResultCallback(new a());
                }
            }, 200L);
        }
    }

    /* loaded from: classes9.dex */
    static class c extends PendingResultImpl<Status, SignOutResp> {
        public c(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Status onComplete(SignOutResp signOutResp) {
            Status status = new Status(0);
            as.b("HuaweiIdAuthAPIServiceImpl", "signOut onComplete", true);
            return status;
        }
    }

    /* loaded from: classes9.dex */
    static class b extends PendingResultImpl<Status, RevokeAccessResp> {
        public b(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Status onComplete(RevokeAccessResp revokeAccessResp) {
            as.b("HuaweiIdAuthAPIServiceImpl", "RevokeAccessPendingResult onComplete", true);
            return new Status(revokeAccessResp.getRetCode());
        }
    }

    /* loaded from: classes9.dex */
    class a implements ResultCallback<ResolveResult<JosGetNoticeResp>> {
        private a() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(ResolveResult<JosGetNoticeResp> resolveResult) {
            JosGetNoticeResp value;
            Intent noticeIntent;
            if (resolveResult == null || !resolveResult.getStatus().isSuccess() || (noticeIntent = (value = resolveResult.getValue()).getNoticeIntent()) == null || value.getStatusCode() != 0) {
                return;
            }
            as.a("HuaweiIdAuthAPIServiceImpl", "get notice has intent.", true);
            Activity validActivity = Util.getValidActivity((Activity) HuaweiIdAuthAPIServiceImpl.this.b.get(), HuaweiIdAuthAPIServiceImpl.this.f6014a.getTopActivity());
            if (validActivity == null) {
                as.d("HuaweiIdAuthAPIServiceImpl", "showNotice no valid activity!", true);
                return;
            }
            try {
                validActivity.startActivity(noticeIntent);
            } catch (Exception e) {
                as.d("HuaweiIdAuthAPIServiceImpl", "startActivity exception" + e.getClass().getSimpleName(), true);
            }
        }
    }

    protected HuaweiIdAuthParams getSignInOption(HuaweiApiClient huaweiApiClient) {
        Api.ApiOptions apiOptions;
        Map<Api<?>, Api.ApiOptions> apiMap = huaweiApiClient.getApiMap();
        if (apiMap == null || (apiOptions = apiMap.get(HuaweiIdAuthAPIManager.HUAWEI_OAUTH_API)) == null || !(apiOptions instanceof HuaweiIdAuthParams)) {
            return null;
        }
        return (HuaweiIdAuthParams) apiOptions;
    }

    private PendingResult<Status> d(HuaweiApiClient huaweiApiClient) {
        as.b("HuaweiIdAuthAPIServiceImpl", "Enter signOutNoConnected", true);
        Task<Void> signOut = HuaweiIdAuthManager.getService(huaweiApiClient.getContext(), getSignInOption(huaweiApiClient)).signOut();
        SignOutNoConnectPendingResult signOutNoConnectPendingResult = new SignOutNoConnectPendingResult(huaweiApiClient, "hwid.signout", new SignOutReq());
        signOut.addOnCompleteListener(new SignOutOnCompleteListener(signOutNoConnectPendingResult));
        return signOutNoConnectPendingResult;
    }

    /* loaded from: classes9.dex */
    protected static class SignOutNoConnectPendingResult extends PendingResultImpl<Status, SignOutResp> {

        /* renamed from: a, reason: collision with root package name */
        private ResultCallback f6019a;

        public SignOutNoConnectPendingResult(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        public Status onComplete(SignOutResp signOutResp) {
            Status status = new Status(0);
            as.b("HuaweiIdAuthAPIServiceImpl", "signOut onComplete", true);
            return status;
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl, com.huawei.hms.support.api.client.PendingResult
        public void setResultCallback(ResultCallback<Status> resultCallback) {
            this.f6019a = resultCallback;
        }

        public ResultCallback getCallback() {
            return this.f6019a;
        }
    }

    /* loaded from: classes9.dex */
    protected static class SignOutOnCompleteListener implements OnCompleteListener<Void> {

        /* renamed from: a, reason: collision with root package name */
        private final SignOutNoConnectPendingResult f6020a;

        public SignOutOnCompleteListener(SignOutNoConnectPendingResult signOutNoConnectPendingResult) {
            this.f6020a = signOutNoConnectPendingResult;
        }

        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<Void> task) {
            as.b("HuaweiIdAuthAPIServiceImpl", "SignOutOnCompleteListener onComplete", true);
            if (this.f6020a == null) {
                as.d("HuaweiIdAuthAPIServiceImpl", "signOutResultPendingResult is null", true);
                return;
            }
            Status status = Status.FAILURE;
            if (task.isSuccessful()) {
                status = Status.SUCCESS;
            } else {
                Exception exception = task.getException();
                if (exception == null || !(exception instanceof ApiException)) {
                    as.d("HuaweiIdAuthAPIServiceImpl", "wrong exception while failed", true);
                } else {
                    status = new Status(((ApiException) exception).getStatusCode());
                }
            }
            ResultCallback callback = this.f6020a.getCallback();
            if (callback == null) {
                as.d("HuaweiIdAuthAPIServiceImpl", "set Result callback after signOut completed", true);
            } else {
                callback.onResult(status);
            }
        }
    }

    protected static int conversionGender(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-1";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    protected static int conversionHomeZone(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    protected static int conversionStatus(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static HashSet<Scope> conversionScopes(String str) {
        HashSet<Scope> hashSet = new HashSet<>();
        if (str != null) {
            for (String str2 : str.split(" ")) {
                hashSet.add(new Scope(str2));
            }
        }
        return hashSet;
    }
}
