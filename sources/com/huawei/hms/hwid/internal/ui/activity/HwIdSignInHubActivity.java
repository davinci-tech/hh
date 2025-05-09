package com.huawei.hms.hwid.internal.ui.activity;

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
import com.huawei.hms.hwid.ao;
import com.huawei.hms.hwid.ar;
import com.huawei.hms.hwid.as;
import com.huawei.hms.hwid.f;
import com.huawei.hms.hwid.q;
import com.huawei.hms.hwid.s;
import com.huawei.hms.hwid.y;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.CommonNaming;
import com.huawei.hms.support.api.entity.hwid.HuaweiIdSignInRequest;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hms.support.picker.activity.AccountPickerSignInHubActivity;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.hms.utils.HMSPackageManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HwIdSignInHubActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private String f4649a = null;
    private boolean b = false;
    private HuaweiIdSignInRequest c = null;
    private HuaweiIdAuthResult d = null;
    private s e;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "onCreate", true);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (a(intent)) {
            a(0, "invalid intent");
            return;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        boolean booleanExtra = safeIntent.getBooleanExtra("intent.extra.isfullscreen", false);
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "isActivityFullScreen is :" + booleanExtra, true);
        if (booleanExtra) {
            f.a(getWindow());
            getWindow().setFlags(1024, 1024);
        } else {
            f.a(this);
        }
        String action = safeIntent.getAction();
        if (!TextUtils.equals("com.huawei.hms.jos.signIn", action)) {
            String str = "unknow Action:";
            if (action != null) {
                str = "unknow Action:" + action;
            }
            a(0, str);
            return;
        }
        String stringExtra = safeIntent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO);
        try {
            this.c = HuaweiIdSignInRequest.fromJson(safeIntent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST));
        } catch (JSONException unused) {
            this.c = null;
            as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "onCreate: JsonException", true);
        }
        if (TextUtils.isEmpty(stringExtra)) {
            a(0, "Activity started with invalid cp client info");
            return;
        }
        try {
            this.e = s.a(stringExtra);
        } catch (JSONException unused2) {
            this.e = null;
            as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "onCreate: JsonException", true);
        }
        if (this.c == null || this.e == null) {
            a(0, "Activity started with invalid sign in request info");
            return;
        }
        ao.a(this);
        if (bundle != null) {
            restoreInstanceState(bundle);
        } else {
            this.f4649a = HiAnalyticsClient.reportEntry(this, CommonNaming.signinIntent, AuthInternalPickerConstant.HMS_SDK_VERSION);
            a();
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
        s sVar;
        super.onActivityResult(i, i2, intent);
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "onActivityResult: requestCode：" + i + " , resultCode：" + i2, true);
        SafeIntent safeIntent = intent != null ? new SafeIntent(intent) : null;
        if (this.b || i != 16587) {
            return;
        }
        int i3 = 8;
        if (safeIntent != null && (stringExtra = intent.getStringExtra("HUAWEIID_SIGNIN_RESULT")) != null) {
            try {
                this.d = new HuaweiIdAuthResult().fromJson(stringExtra);
                y.a().a(this.d.getHuaweiId(), this.c.getHuaweiIdAuthParams());
                i3 = this.d.getStatus().getStatusCode();
            } catch (JSONException unused) {
                this.d = null;
                as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "onActivityResult: JsonException", true);
            }
            int i4 = i3;
            String str = this.f4649a;
            if (str != null && (sVar = this.e) != null) {
                HiAnalyticsClient.reportExit(this, CommonNaming.signinIntent, str, sVar.a(), ar.a(i4), i4, (int) this.e.b());
                as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "report: api=hwid.signinintentversion=" + this.e.b(), true);
            }
            setResult(i2, safeIntent);
            finish();
            return;
        }
        if (i2 <= 0) {
            i2 = 8;
        }
        a(i2);
    }

    protected void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.f4649a = bundle.getString("HiAnalyticsTransId");
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String str = this.f4649a;
        if (str == null || bundle == null) {
            return;
        }
        bundle.putString("HiAnalyticsTransId", str);
    }

    private void a(int i, String str) {
        as.d("[HUAWEIIDSDK]HwIdSignInClientHub", str, true);
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        s sVar;
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "onSignInAccountFailed: retCode：" + i, true);
        String str = this.f4649a;
        if (str != null && (sVar = this.e) != null) {
            HiAnalyticsClient.reportExit(this, CommonNaming.signinIntent, str, sVar.a(), ar.a(i), i, (int) this.e.b());
            as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "report: api=hwid.signinintentversion=" + this.e.b(), true);
        }
        Status status = new Status(i);
        HuaweiIdAuthResult huaweiIdAuthResult = new HuaweiIdAuthResult();
        huaweiIdAuthResult.setStatus(status);
        Intent intent = new Intent();
        try {
            intent.putExtra("HUAWEIID_SIGNIN_RESULT", huaweiIdAuthResult.toJson());
        } catch (JSONException unused) {
            as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "convert result to json failed", true);
        }
        setResult(0, intent);
        finish();
    }

    private void a() {
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "checkMinVersion start.", true);
        q.a(this, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.hwid.internal.ui.activity.HwIdSignInHubActivity.1
            @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
            public void onComplete(int i) {
                if (i == 0) {
                    as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "version check ok", true);
                    HwIdSignInHubActivity.this.b();
                } else {
                    as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "version check failed", true);
                    HwIdSignInHubActivity.this.a(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "startSignInActivity", true);
        try {
            startActivityForResult(c(), AccountPickerSignInHubActivity.IDENTIFIER);
        } catch (ActivityNotFoundException e) {
            this.b = true;
            as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "Launch sign in Intent failed. hms is probably being updated：" + e.getClass().getSimpleName(), true);
            a(17);
        }
    }

    private Intent c() {
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "getJosSignInIntent", true);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO);
        String stringExtra2 = intent.getStringExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST);
        String stringExtra3 = intent.getStringExtra("exParams");
        as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "getJosSignInIntent. exParams is null " + TextUtils.isEmpty(stringExtra3), true);
        if (!TextUtils.isEmpty(stringExtra3)) {
            try {
                JSONObject jSONObject = new JSONObject(stringExtra2);
                jSONObject.put("exParams", stringExtra3);
                stringExtra2 = jSONObject.toString();
            } catch (JSONException e) {
                as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "getJosSignInIntent Exception " + e.getClass().getSimpleName(), true);
            }
        }
        String action = intent.getAction();
        try {
            s a2 = s.a(stringExtra);
            ForegroundIntentBuilder kitSdkVersion = new ForegroundIntentBuilder(this).setAction(action).setRequestBody(stringExtra2).setKitSdkVersion(AuthInternalPickerConstant.HMS_SDK_VERSION);
            if (a2.c() != null) {
                kitSdkVersion.setSubAppId(a2.c());
            }
            Intent build = kitSdkVersion.build();
            as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "get package name of hms is " + HMSPackageManager.getInstance(this).getHMSPackageName(), true);
            as.b("[HUAWEIIDSDK]HwIdSignInClientHub", "Current package name is " + getPackageName(), true);
            build.setPackage(getPackageName());
            return build;
        } catch (Exception e2) {
            as.d("[HUAWEIIDSDK]HwIdSignInClientHub", "getSignInIntent failed because:" + e2.getClass().getSimpleName(), true);
            return new Intent();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
