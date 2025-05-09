package com.huawei.hms.support.picker.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.a.a.b.b;
import com.huawei.hms.a.a.d.a;
import com.huawei.hms.a.a.d.c;
import com.huawei.hms.a.a.d.d;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.common.utils.PickerHiAnalyticsUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.api.entity.common.PickerCommonNaming;
import com.huawei.hms.support.api.entity.hwid.AccountPickerSignInRequest;
import com.huawei.hms.support.api.entity.hwid.AccountPickerSignOutReq;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.picker.common.AccountPickerUtil;
import com.huawei.hms.support.picker.request.AccountPickerParams;
import defpackage.kqk;
import defpackage.ksq;
import defpackage.ksy;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountPickerServiceImpl extends HuaweiApi<AccountPickerParams> implements AccountPickerService {
    private static final Api<AccountPickerParams> HUAWEI_ID_AUTH_API = new Api<>(HuaweiApiAvailability.HMS_API_NAME_ID);
    protected static final String TAG = "[HUAWEIIDSDK]AccountPickerServiceImpl";
    private AccountPickerParams accountPickerParams;
    private int mKitSdkVersion;
    private int mPickerType;
    private String transId;

    @Override // com.huawei.hms.common.HuaweiApi
    public int getApiLevel() {
        return 9;
    }

    public AccountPickerServiceImpl(Activity activity, AccountPickerParams accountPickerParams, int i) {
        super(activity, HUAWEI_ID_AUTH_API, accountPickerParams, (AbstractClientBuilder) new b(), i);
        this.transId = null;
        this.mPickerType = Integer.MIN_VALUE;
        this.accountPickerParams = accountPickerParams;
        this.mKitSdkVersion = i;
    }

    public AccountPickerServiceImpl(Context context, AccountPickerParams accountPickerParams, int i) {
        super(context, HUAWEI_ID_AUTH_API, accountPickerParams, new b(), i);
        this.transId = null;
        this.mPickerType = Integer.MIN_VALUE;
        this.mKitSdkVersion = i;
        this.accountPickerParams = accountPickerParams;
    }

    public AccountPickerServiceImpl(Activity activity, AccountPickerParams accountPickerParams, int i, int i2) {
        super(activity, HUAWEI_ID_AUTH_API, accountPickerParams, (AbstractClientBuilder) new b(), i);
        this.transId = null;
        this.accountPickerParams = accountPickerParams;
        this.mKitSdkVersion = i;
        this.mPickerType = i2;
    }

    public AccountPickerServiceImpl(Context context, AccountPickerParams accountPickerParams, int i, int i2) {
        super(context, HUAWEI_ID_AUTH_API, accountPickerParams, new b(), i);
        this.transId = null;
        this.accountPickerParams = accountPickerParams;
        this.mKitSdkVersion = i;
        this.mPickerType = i2;
    }

    @Override // com.huawei.hms.support.picker.service.AccountPickerService
    public Intent signIn() {
        boolean z = true;
        ksy.b(TAG, "accountPicker signIn", true);
        if (this.mPickerType == Integer.MIN_VALUE) {
            ksy.b(TAG, "pickerType is null", true);
            this.transId = HiAnalyticsClient.reportEntry(getContext(), PickerCommonNaming.signinIntent, AuthInternalPickerConstant.HMS_SDK_VERSION);
            z = false;
        } else {
            ksy.b(TAG, "pickerType is " + this.mPickerType, true);
            this.transId = HiAnalyticsClient.reportEntry(getContext(), PickerCommonNaming.signinAccountPickerType, AuthInternalPickerConstant.HMS_SDK_VERSION);
        }
        return AccountPickerUtil.getSignInIntent(getContext(), this.accountPickerParams, getSubAppID(), this.transId, this.mPickerType, z);
    }

    @Override // com.huawei.hms.support.picker.service.AccountPickerService
    public Intent signInByMcp() {
        boolean z = true;
        ksy.b(TAG, "picker signInByMcp.", true);
        Context context = getContext();
        if (this.mPickerType == Integer.MIN_VALUE) {
            ksy.b(TAG, "pickerType is null", true);
            this.transId = HiAnalyticsClient.reportEntry(context, PickerCommonNaming.signinIntent, AuthInternalPickerConstant.HMS_SDK_VERSION);
            z = false;
        } else {
            ksy.b(TAG, "pickerType is " + this.mPickerType, true);
            this.transId = HiAnalyticsClient.reportEntry(context, PickerCommonNaming.signinAccountPickerType, AuthInternalPickerConstant.HMS_SDK_VERSION);
        }
        return AccountPickerUtil.getSignInByMcpIntent(context, this.accountPickerParams, getSubAppID(), this.transId, this.mPickerType, z);
    }

    @Override // com.huawei.hms.support.picker.service.AccountPickerService
    public Task<Void> signOut() {
        ksy.b(TAG, "signOut", true);
        AccountPickerUtil.clearSignInAccountCache();
        AccountPickerSignOutReq accountPickerSignOutReq = new AccountPickerSignOutReq();
        Context context = getContext();
        this.transId = HiAnalyticsClient.reportEntry(context, PickerCommonNaming.AccountPickerSignout, AuthInternalPickerConstant.HMS_SDK_VERSION);
        int isHuaweiMobileServicesAvailable = new AvailableAdapter(AuthInternalPickerConstant.HMS_APK_VERSION_MIN).isHuaweiMobileServicesAvailable(context);
        ksy.b(TAG, "signOut isHuaweiMobileServicesAvailableCode:" + isHuaweiMobileServicesAvailable, true);
        c cVar = new c(context, this.transId);
        ksy.b(TAG, "check HMS service begin.", true);
        try {
            if (isHuaweiMobileServicesAvailable == 1 || isHuaweiMobileServicesAvailable == 21 || isHuaweiMobileServicesAvailable == 3) {
                ksy.b(TAG, "it has not HMS service.", true);
                ksq.c(context, false);
                AccountLiteSdkServiceImpl.h5SignOut(context, cVar);
                return cVar.a().getTask();
            }
            if (isHuaweiMobileServicesAvailable == 0 || isHuaweiMobileServicesAvailable == 2) {
                AccountLiteSdkServiceImpl.h5SignOut(context, cVar);
                return doWrite(new d("hwid.signout", accountPickerSignOutReq.toJson(), this.transId));
            }
            return signOutFailure(2015, AuthInternalPickerConstant.UNKOWN_ERROR, this.transId);
        } catch (kqk e) {
            ksy.c(TAG, "ParmaInvalidException:" + e.getClass().getSimpleName(), true);
            return signOutFailure(2003, AuthInternalPickerConstant.PARAM_ERROR, this.transId);
        }
    }

    private Task<Void> signOutFailure(int i, String str, String str2) {
        ksy.b(TAG, "signOutFailure start. statusCode is " + i, true);
        HiAnalyticsClient.reportExit(getContext(), PickerCommonNaming.AccountPickerSignout, str2, PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(new ApiException(new Status(i, str)));
        return taskCompletionSource.getTask();
    }

    @Override // com.huawei.hms.support.picker.service.AccountPickerService
    public Task<Void> cancelAuthorization(String str) {
        try {
            ksy.b(TAG, "cancelAuthorization", true);
            if (TextUtils.isEmpty(str)) {
                ksy.c(TAG, "input accessToken is null", true);
                return cancelAuthorizationFailure(2003, AuthInternalPickerConstant.PARAM_ERROR, this.transId);
            }
            AccountPickerSignInRequest accountPickerSignInRequest = new AccountPickerSignInRequest();
            JSONObject jSONObject = new JSONObject(this.accountPickerParams.getSignInParams());
            jSONObject.put("accessToken", str);
            this.accountPickerParams.setSignInParams(jSONObject.toString());
            String optString = jSONObject.optString(CommonPickerConstant.RequestParams.KEY_GRS_APP_NAME);
            accountPickerSignInRequest.setAccountPickerParams(getOption());
            String json = accountPickerSignInRequest.toJson();
            Context context = getContext();
            this.transId = HiAnalyticsClient.reportEntry(context, PickerCommonNaming.AccountPickerRevokeAccess, AuthInternalPickerConstant.HMS_SDK_VERSION);
            AvailableAdapter availableAdapter = new AvailableAdapter(AuthInternalPickerConstant.HMS_APK_VERSION_MIN);
            ksy.b(TAG, "check HMS service begin.", true);
            int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(context);
            ksy.b(TAG, "cancelAuthorization isHuaweiMobileServicesAvailableCode:" + isHuaweiMobileServicesAvailable, true);
            if (isHuaweiMobileServicesAvailable != 1 && isHuaweiMobileServicesAvailable != 21 && isHuaweiMobileServicesAvailable != 3) {
                if (isHuaweiMobileServicesAvailable != 0 && isHuaweiMobileServicesAvailable != 2) {
                    return cancelAuthorizationFailure(2015, AuthInternalPickerConstant.UNKOWN_ERROR, this.transId);
                }
                ksy.b(TAG, "it has HMS service.", true);
                return doWrite(new a("hwid.revokeAccess", json, this.transId));
            }
            ksy.b(TAG, "it has not HMS service.", true);
            ksq.c(context, false);
            com.huawei.hms.a.a.d.b bVar = new com.huawei.hms.a.a.d.b(context, this.transId);
            AccountLiteSdkServiceImpl.revoke(context, optString, str, bVar);
            return bVar.a().getTask();
        } catch (kqk e) {
            ksy.c(TAG, "ParmaInvalidException:" + e.getClass().getSimpleName(), true);
            return cancelAuthorizationFailure(2015, e.getMessage(), this.transId);
        } catch (JSONException e2) {
            ksy.c(TAG, "JSONException:" + e2.getClass().getSimpleName(), true);
            return cancelAuthorizationFailure(2015, e2.getMessage(), this.transId);
        }
    }

    private Task<Void> cancelAuthorizationFailure(int i, String str, String str2) {
        ksy.b(TAG, "cancelAuthorizationFailure start. statusCode is " + i, true);
        HiAnalyticsClient.reportExit(getContext(), PickerCommonNaming.AccountPickerRevokeAccess, str2, PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(new ApiException(new Status(i, str)));
        return taskCompletionSource.getTask();
    }
}
