package com.huawei.hms.support.account.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hwid.ao;
import com.huawei.hms.hwid.as;
import com.huawei.hms.hwid.d;
import com.huawei.hms.hwid.f;
import com.huawei.hms.hwid.h;
import com.huawei.hms.hwid.i;
import com.huawei.hms.hwid.j;
import com.huawei.hms.hwid.l;
import com.huawei.hms.hwid.m;
import com.huawei.hms.hwid.n;
import com.huawei.hms.hwid.o;
import com.huawei.hms.hwid.p;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.result.AccountIcon;
import com.huawei.hms.support.account.result.AssistTokenResult;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.api.account.AccountGetTokenOptions;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.account.AccountLogoutRequest;
import com.huawei.hms.support.api.entity.account.AccountNaming;
import com.huawei.hms.support.api.entity.account.AccountSignInRequest;
import com.huawei.hms.support.api.entity.account.AccountSignOutReq;
import com.huawei.hms.support.api.entity.account.AccountStartAssistLoginRequest;
import com.huawei.hms.support.api.entity.account.GetAssistTokenRequest;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.tencent.open.SocialConstants;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountAuthServiceImpl extends HuaweiApi<AccountAuthParams> implements AccountAuthService {
    private static final Api<AccountAuthParams> ACCOUNT_AUTH_API = new Api<>("AuthAccount.API");
    private static final Long ONE_WEEK_MILLISECOND = 604800000L;
    protected static final String TAG = "[ACCOUNT]AccountAuthServiceImpl";
    private String mAccountName;
    private boolean mFromRequestToken;

    public AccountAuthServiceImpl(Activity activity, AccountAuthParams accountAuthParams, int i) {
        super(activity, ACCOUNT_AUTH_API, accountAuthParams, (AbstractClientBuilder) new d(), i);
    }

    public AccountAuthServiceImpl(Context context, AccountAuthParams accountAuthParams, int i) {
        super(context, ACCOUNT_AUTH_API, accountAuthParams, new d(), i);
    }

    public AccountAuthServiceImpl(Context context, AccountAuthParams accountAuthParams, String str, int i) {
        super(context, ACCOUNT_AUTH_API, accountAuthParams, new d(), i);
        this.mAccountName = str;
        this.mFromRequestToken = true;
    }

    public AccountAuthServiceImpl(Activity activity, AccountAuthParams accountAuthParams, String str, int i) {
        super(activity, ACCOUNT_AUTH_API, accountAuthParams, (AbstractClientBuilder) new d(), i);
        this.mAccountName = str;
        this.mFromRequestToken = true;
    }

    @Override // com.huawei.hms.support.feature.service.AuthService
    public Intent getSignInIntent() {
        as.b(TAG, "getSignInIntent", true);
        return f.a(getContext(), getOption(), getSubAppID());
    }

    @Override // com.huawei.hms.support.feature.service.AuthService
    public Task<Void> signOut() {
        as.b(TAG, "signOut", true);
        f.a();
        AccountSignOutReq accountSignOutReq = new AccountSignOutReq();
        return doWrite(new n(AccountNaming.signoutAccount, accountSignOutReq.toJson(), HiAnalyticsClient.reportEntry(getContext(), "hwid.signout", AuthInternalPickerConstant.HMS_SDK_VERSION)));
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Task<AuthAccount> silentSignIn() {
        as.b(TAG, "silentSignIn", true);
        AccountSignInRequest accountSignInRequest = new AccountSignInRequest();
        AccountAuthParams option = getOption();
        accountSignInRequest.setAccountAuthParams(option);
        boolean z = this.mFromRequestToken;
        if (z) {
            accountSignInRequest.setAccountGetTokenOptions(new AccountGetTokenOptions(this.mAccountName, z));
        }
        if (!checkIdTokenSignAlg(option)) {
            as.d(TAG, "idTokenSignAlg is invalid", true);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2003, "idTokenSignAlg is invalid")));
            return taskCompletionSource.getTask();
        }
        try {
            String json = accountSignInRequest.toJson();
            String reportEntry = HiAnalyticsClient.reportEntry(getContext(), AccountNaming.silentSignInAccount, AuthInternalPickerConstant.HMS_SDK_VERSION);
            if (this.mFromRequestToken) {
                return doWrite(new l(AccountNaming.silentSignInAccount, json, reportEntry));
            }
            return doWrite(new m(AccountNaming.silentSignInAccount, json, reportEntry));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException, errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            taskCompletionSource2.setException(new ApiException(new Status(2015)));
            return taskCompletionSource2.getTask();
        }
    }

    private boolean checkIdTokenSignAlg(AccountAuthParams accountAuthParams) {
        as.b(TAG, "checkIdTokenSignAlg", true);
        if (accountAuthParams == null) {
            as.d(TAG, "accountAuthParams is null", true);
            return false;
        }
        try {
            int i = new JSONObject(accountAuthParams.getSignInParams()).getInt("idTokenSignAlg");
            return i == 1 || i == 2;
        } catch (JSONException e) {
            as.d(TAG, "JSONException:" + e.getClass().getSimpleName(), true);
            return false;
        }
    }

    @Override // com.huawei.hms.support.feature.service.AuthService
    public Task<Void> cancelAuthorization() {
        as.b(TAG, "cancelAuthorization", true);
        AccountSignInRequest accountSignInRequest = new AccountSignInRequest();
        accountSignInRequest.setAccountAuthParams(getOption());
        try {
            return doWrite(new h(AccountNaming.revokeAccessAccount, accountSignInRequest.toJson(), HiAnalyticsClient.reportEntry(getContext(), AccountNaming.revokeAccessAccount, AuthInternalPickerConstant.HMS_SDK_VERSION)));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException, errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2015)));
            return taskCompletionSource.getTask();
        }
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Task<Void> startAssistLogin(String str) {
        as.b(TAG, "startAssistLogin", true);
        try {
            return doWrite(new o(AccountNaming.startAssistLogin, new AccountStartAssistLoginRequest(str).toJson(), HiAnalyticsClient.reportEntry(getContext(), AccountNaming.startAssistLogin, AuthInternalPickerConstant.HMS_SDK_VERSION)));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException, errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2015)));
            return taskCompletionSource.getTask();
        }
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Task<AccountIcon> getChannel() {
        as.b(TAG, "getChannel", true);
        AccountSignInRequest accountSignInRequest = new AccountSignInRequest();
        accountSignInRequest.setAccountAuthParams(getOption());
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("ACCOUNT_CHANNEL_CACHE", 0);
        String string = sharedPreferences.getString("icon", "");
        String string2 = sharedPreferences.getString(SocialConstants.PARAM_APP_DESC, "");
        long j = sharedPreferences.getLong("cache_time", 0L);
        long a2 = ao.a();
        if (j != 0 && !TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && a2 - j < ONE_WEEK_MILLISECOND.longValue()) {
            AccountIcon accountIcon = new AccountIcon(string2, stringToBitmap(string));
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setResult(accountIcon);
            taskCompletionSource.setException(new ApiException(new Status(0)));
            return taskCompletionSource.getTask();
        }
        try {
            return doWrite(new i(AccountNaming.getChannelAccount, accountSignInRequest.toJson(), HiAnalyticsClient.reportEntry(getContext(), AccountNaming.getChannelAccount, AuthInternalPickerConstant.HMS_SDK_VERSION), context));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException, errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            taskCompletionSource2.setException(new ApiException(new Status(2015)));
            return taskCompletionSource2.getTask();
        }
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Intent getIndependentSignInIntent(String str) {
        as.b(TAG, "getIndependentSignInIntent", true);
        return f.a(getContext(), getOption(), getSubAppID(), str);
    }

    private Bitmap stringToBitmap(String str) {
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            as.d(TAG, "stringToBitmap Exception is " + e.getClass().getSimpleName(), true);
            return null;
        } catch (OutOfMemoryError unused) {
            as.d(TAG, "out of memory error ", true);
            return null;
        }
    }

    @Override // com.huawei.hms.common.HuaweiApi
    public int getApiLevel() {
        List<PermissionInfo> permissionInfos;
        AccountAuthParams option = getOption();
        if (option != null && (permissionInfos = option.getPermissionInfos()) != null && permissionInfos.size() != 0) {
            Iterator<PermissionInfo> it = permissionInfos.iterator();
            while (it.hasNext()) {
                String permission = it.next().getPermission();
                if (permission != null && permission.equals(CommonConstant.LocalPermission.CARRIER_ID)) {
                    as.b(TAG, "permissioninfos contain carrierId and silentSignIn setApiLevel is 13", true);
                    return 13;
                }
            }
        }
        return 1;
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Task<Void> logout() {
        as.b(TAG, "logout", true);
        AccountLogoutRequest accountLogoutRequest = new AccountLogoutRequest();
        accountLogoutRequest.setAccountAuthParams(getOption());
        try {
            return doWrite(new j(AccountNaming.logout, accountLogoutRequest.toJson(), HiAnalyticsClient.reportEntry(getContext(), AccountNaming.logout, AuthInternalPickerConstant.HMS_SDK_VERSION)));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException, errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2015)));
            return taskCompletionSource.getTask();
        }
    }

    @Override // com.huawei.hms.support.account.service.AccountAuthService
    public Task<AssistTokenResult> getAssistToken(GetAssistTokenRequest getAssistTokenRequest) {
        as.b(TAG, "getAssistToken", true);
        return doWriteGetAssistToken(getAssistTokenRequest);
    }

    private Task<AssistTokenResult> doWriteGetAssistToken(GetAssistTokenRequest getAssistTokenRequest) {
        try {
            return doWrite(new p(AccountNaming.getAssistToken, getAssistTokenRequest.toJson(), HiAnalyticsClient.reportEntry(getContext(), AccountNaming.getAssistToken, AuthInternalPickerConstant.HMS_SDK_VERSION)));
        } catch (JSONException unused) {
            as.d(TAG, "JSONException， errorcode is:2015", true);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2015)));
            return taskCompletionSource.getTask();
        }
    }
}
