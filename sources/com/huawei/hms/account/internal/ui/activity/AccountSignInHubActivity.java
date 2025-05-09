package com.huawei.hms.account.internal.ui.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hms.activity.ForegroundIntentBuilder;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hwid.a;
import com.huawei.hms.hwid.ao;
import com.huawei.hms.hwid.ap;
import com.huawei.hms.hwid.ar;
import com.huawei.hms.hwid.as;
import com.huawei.hms.hwid.b;
import com.huawei.hms.hwid.f;
import com.huawei.hms.hwid.g;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.result.AccountAuthResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.account.AccountSignInRequest;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.picker.activity.AccountPickerSignInHubActivity;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.hms.utils.HMSPackageManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountSignInHubActivity extends Activity {
    private b e;
    private int f;

    /* renamed from: a, reason: collision with root package name */
    private String f4252a = null;
    private boolean b = false;
    private AccountSignInRequest c = null;
    private AccountAuthResult d = null;
    private String g = CommonNaming.signinIntent;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "onCreate", true);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (a(intent)) {
            a(0, "invalid intent");
            return;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        a(safeIntent.getBooleanExtra("intent.extra.isfullscreen", false));
        String action = safeIntent.getAction();
        if (!TextUtils.equals("com.huawei.hms.jos.signIn", action) && !TextUtils.equals("com.huawei.hms.account.signIn", action) && !TextUtils.equals(AuthInternalPickerConstant.IntentAction.ACTION_SIGN_IN_HUB, action)) {
            String str = "unknow Action:";
            if (action != null) {
                str = "unknow Action:" + action;
            }
            a(0, str);
            return;
        }
        String stringExtra = safeIntent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO);
        try {
            this.c = AccountSignInRequest.fromJson(safeIntent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST));
        } catch (JSONException unused) {
            this.c = null;
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "onCreate: JsonException", true);
        }
        if (TextUtils.isEmpty(stringExtra)) {
            a(0, "Activity started with invalid cp client info");
            return;
        }
        try {
            this.e = b.a(stringExtra);
        } catch (JSONException unused2) {
            this.e = null;
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "onCreate: JsonException", true);
        }
        if (this.c == null || this.e == null) {
            a(0, "Activity started with invalid sign in request info");
            return;
        }
        if (!a()) {
            a(2003);
            return;
        }
        int intExtra = safeIntent.getIntExtra("INDEPENDENT_SIGN_IN_FLAG", 0);
        this.f = intExtra;
        if (intExtra == 1 && !a(safeIntent).booleanValue()) {
            a(2003);
            return;
        }
        ao.a(this);
        if (bundle != null) {
            restoreInstanceState(bundle);
        } else {
            this.f4252a = HiAnalyticsClient.reportEntry(this, this.g, AuthInternalPickerConstant.HMS_SDK_VERSION);
            b();
        }
    }

    private boolean a() {
        AccountAuthParams accountAuthParams = this.c.getAccountAuthParams();
        if (accountAuthParams == null) {
            return false;
        }
        try {
            int optInt = new JSONObject(accountAuthParams.getSignInParams()).optInt("idTokenSignAlg", 2);
            return optInt == 1 || optInt == 2;
        } catch (JSONException e) {
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "JSONException:" + e.getClass().getSimpleName(), true);
            return false;
        }
    }

    private boolean a(Intent intent) {
        if (intent != null) {
            try {
                intent.getStringExtra("ANYTHING");
                return false;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        int i3;
        b bVar;
        super.onActivityResult(i, i2, intent);
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "onActivityResult: requestCode：" + i + " , resultCode：" + i2, true);
        SafeIntent safeIntent = intent != null ? new SafeIntent(intent) : null;
        if (this.b) {
            return;
        }
        if (i == 16587 || i == 16588) {
            if (safeIntent != null && (stringExtra = intent.getStringExtra("HUAWEIID_SIGNIN_RESULT")) != null) {
                try {
                    this.d = new AccountAuthResult().fromJson(stringExtra);
                    if (i == 16587) {
                        g.a().a(this.d.getAccount(), this.c.getAccountAuthParams());
                    }
                    i3 = this.d.getStatus().getStatusCode();
                } catch (JSONException unused) {
                    this.d = null;
                    as.d("[ACCOUNTSDK]AccountSignInHubActivity", "onActivityResult: JsonException", true);
                    i3 = 8;
                }
                String str = this.f4252a;
                if (str != null && (bVar = this.e) != null) {
                    HiAnalyticsClient.reportExit(this, this.g, str, bVar.a(), ar.a(i3), i3, (int) this.e.b());
                }
                setResult(i2, safeIntent);
                finish();
                return;
            }
            a(i2 > 0 ? i2 : 8);
        }
    }

    protected void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.f4252a = bundle.getString("HiAnalyticsTransId");
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "onSaveInstanceState start", true);
        String str = this.f4252a;
        if (str == null || bundle == null) {
            return;
        }
        bundle.putString("HiAnalyticsTransId", str);
    }

    private void a(int i, String str) {
        as.d("[ACCOUNTSDK]AccountSignInHubActivity", "errMessage is: " + str, true);
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        b bVar;
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "onSignInAccountFailed: retCode：" + i, true);
        String str = this.f4252a;
        if (str != null && (bVar = this.e) != null) {
            HiAnalyticsClient.reportExit(this, this.g, str, bVar.a(), ar.a(i), i, (int) this.e.b());
        }
        Status status = new Status(i);
        AccountAuthResult accountAuthResult = new AccountAuthResult();
        accountAuthResult.setStatus(status);
        Intent intent = new Intent();
        try {
            intent.putExtra("HUAWEIID_SIGNIN_RESULT", accountAuthResult.toJson());
        } catch (JSONException unused) {
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "convert result to json failed", true);
        }
        setResult(0, intent);
        finish();
    }

    private void b() {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "checkMinVersion start.", true);
        a.a(this, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.account.internal.ui.activity.AccountSignInHubActivity.1
            @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
            public void onComplete(int i) {
                if (i == 0) {
                    as.b("[ACCOUNTSDK]AccountSignInHubActivity", "version check ok", true);
                    AccountSignInHubActivity.this.c();
                } else {
                    as.b("[ACCOUNTSDK]AccountSignInHubActivity", "version check failed", true);
                    AccountSignInHubActivity.this.a(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "startSignInActivity", true);
        try {
            startActivityForResult(d(), this.f == 1 ? AccountPickerSignInHubActivity.IDENTIFIER_H5 : AccountPickerSignInHubActivity.IDENTIFIER);
        } catch (ActivityNotFoundException e) {
            this.b = true;
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "Launch sign in Intent failed. hms is probably being updated：" + e.getClass().getSimpleName(), true);
            a(17);
        }
    }

    private Intent d() {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "getJosSignInIntent", true);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO);
        String stringExtra2 = intent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST);
        String stringExtra3 = intent.getStringExtra("exParams");
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "getJosSignInIntent. exParams is null " + TextUtils.isEmpty(stringExtra3), true);
        if (!TextUtils.isEmpty(stringExtra3)) {
            try {
                JSONObject jSONObject = new JSONObject(stringExtra2);
                jSONObject.put("exParams", stringExtra3);
                stringExtra2 = jSONObject.toString();
            } catch (JSONException e) {
                as.b("[ACCOUNTSDK]AccountSignInHubActivity", "getJosSignInIntent Exception " + e.getClass().getSimpleName(), true);
            }
        }
        String action = intent.getAction();
        try {
            b a2 = b.a(stringExtra);
            ForegroundIntentBuilder kitSdkVersion = new ForegroundIntentBuilder(this).setAction(action).setRequestBody(stringExtra2).setKitSdkVersion(AuthInternalPickerConstant.HMS_SDK_VERSION);
            if (a2.c() != null) {
                kitSdkVersion.setSubAppId(a2.c());
            }
            if (this.f == 1) {
                as.b("[ACCOUNTSDK]AccountSignInHubActivity", "setApiLevel is 11", true);
                kitSdkVersion.setApiLevel(11);
            }
            if (e().booleanValue()) {
                as.b("[ACCOUNTSDK]AccountSignInHubActivity", "setApiLevel is 13", true);
                kitSdkVersion.setApiLevel(13);
            }
            if (f()) {
                as.b("[ACCOUNTSDK]AccountSignInHubActivity", "setApiLevel is 22", true);
                kitSdkVersion.setApiLevel(22);
            }
            Intent build = kitSdkVersion.build();
            as.b("[ACCOUNTSDK]AccountSignInHubActivity", "get package name of hms is " + HMSPackageManager.getInstance(this).getHMSPackageName(), true);
            as.b("[ACCOUNTSDK]AccountSignInHubActivity", "current package name is " + getPackageName(), true);
            build.setPackage(getPackageName());
            return build;
        } catch (Exception e2) {
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "getSignInIntent failed because:" + e2.getClass().getSimpleName(), true);
            return new Intent();
        }
    }

    private Boolean a(SafeIntent safeIntent) {
        String stringExtra = safeIntent.getStringExtra("ACCESS_TOKEN");
        AccountAuthParams accountAuthParams = this.c.getAccountAuthParams();
        if (accountAuthParams == null) {
            a("accountAuthParams is null");
            return false;
        }
        List<Scope> requestScopeList = accountAuthParams.getRequestScopeList();
        if (TextUtils.isEmpty(stringExtra) || ap.a(requestScopeList).booleanValue() || requestScopeList.size() != 1) {
            a("independent sign in params error");
            return false;
        }
        this.g = CommonNaming.independentsigninIntent;
        return true;
    }

    private void a(String str) {
        as.d("[ACCOUNTSDK]AccountSignInHubActivity", "onIndependentSignInFailed errMessage:" + str, true);
        Status status = new Status(2003, str);
        AccountAuthResult accountAuthResult = new AccountAuthResult();
        accountAuthResult.setStatus(status);
        Intent intent = new Intent();
        try {
            intent.putExtra("HUAWEIID_SIGNIN_RESULT", accountAuthResult.toJson());
        } catch (JSONException unused) {
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "convert result to json failed", true);
        }
        setResult(0, intent);
        finish();
    }

    private Boolean e() {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "checkCarrierIdPermission begin", true);
        AccountAuthParams accountAuthParams = this.c.getAccountAuthParams();
        if (accountAuthParams == null) {
            return false;
        }
        List<PermissionInfo> permissionInfos = accountAuthParams.getPermissionInfos();
        if (ap.b(permissionInfos).booleanValue()) {
            Iterator<PermissionInfo> it = permissionInfos.iterator();
            while (it.hasNext()) {
                String permission = it.next().getPermission();
                if (permission != null && permission.equals(CommonConstant.LocalPermission.CARRIER_ID)) {
                    as.b("[ACCOUNTSDK]AccountSignInHubActivity", "permissioninfos contain carrierId", true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean f() {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "checkNeedConsent begin", true);
        AccountAuthParams accountAuthParams = this.c.getAccountAuthParams();
        if (accountAuthParams == null) {
            return false;
        }
        try {
            return new JSONObject(accountAuthParams.getSignInParams()).optBoolean(CommonConstant.RequestParams.KEY_ID_NEED_CONSENT, false);
        } catch (JSONException e) {
            as.d("[ACCOUNTSDK]AccountSignInHubActivity", "JSONException:" + e.getClass().getSimpleName(), true);
            return false;
        }
    }

    private void a(boolean z) {
        as.b("[ACCOUNTSDK]AccountSignInHubActivity", "isActivityFullScreen is :" + z, true);
        if (z) {
            f.a(getWindow());
            getWindow().setFlags(1024, 1024);
        } else {
            f.a(this);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
