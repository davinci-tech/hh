package com.huawei.hms.support.picker.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hms.a.a.c.a;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.picker.activity.AccountPickerSignInHubContract;
import com.huawei.hms.support.picker.common.AccountPickerUtil;
import com.huawei.hms.support.picker.result.AccountPickerResult;
import defpackage.kst;
import defpackage.ksy;
import defpackage.kte;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountPickerSignInHubActivity extends Activity implements AccountPickerSignInHubContract.View {
    public static final int IDENTIFIER = 16587;
    public static final int IDENTIFIER_H5 = 16588;
    private static final String TAG = "AccountPickerSignInHubActivity";
    private AccountPickerSignInHubPresenter mPresenter;
    private boolean signInNotFoundActivity;
    private AccountPickerResult signInResult = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        ksy.b(TAG, "onCreate", true);
        super.onCreate(bundle);
        setStatusBar();
        AccountPickerSignInHubPresenter accountPickerSignInHubPresenter = new AccountPickerSignInHubPresenter(this, this);
        this.mPresenter = accountPickerSignInHubPresenter;
        if (accountPickerSignInHubPresenter.initIntent(getIntent())) {
            if (this.mPresenter.mIsActivityFullScreen) {
                AccountPickerUtil.setFullScreenAdaptCutout(getWindow());
            } else {
                AccountPickerUtil.setStatusBarColor(this);
            }
            AccountPickerUtil.setEMUI10StatusBarColor(this);
            if (bundle != null) {
                this.mPresenter.restoreInstanceState(bundle);
            } else {
                this.mPresenter.checkMinVersion();
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ksy.b(TAG, "onActivityResult: requestCode = " + i + "; resultCode = " + i2, true);
        if (!this.signInNotFoundActivity && i == 16587 && intent != null) {
            ksy.b(TAG, "data is not null", true);
            String stringExtra = intent.getStringExtra("HUAWEIID_SIGNIN_RESULT");
            if (stringExtra != null) {
                ksy.b(TAG, "jsonSignInResult is not null", true);
                dealApkSignInResult(stringExtra, intent, 2015, i2);
                return;
            }
        }
        if (i == 16588) {
            handleH5LoginResult(i2, intent, 2015);
            return;
        }
        if (i2 <= 0) {
            i2 = 2015;
        }
        this.mPresenter.onSignInFailed(i2);
    }

    private void handleH5LoginResult(int i, Intent intent, int i2) {
        ksy.b(TAG, "handleH5LoginResult start.", true);
        if (intent != null) {
            ksy.b(TAG, "data is not null", true);
            String stringExtra = intent.getStringExtra("HUAWEIID_SIGNIN_RESULT");
            ksy.b(TAG, "handleH5LoginResult: jsonSignInResult = " + stringExtra, false);
            if (stringExtra != null) {
                ksy.b(TAG, "jsonSignInResult is not null", true);
                int saveDataByApkOrH5 = saveDataByApkOrH5(i2, stringExtra);
                if (saveDataByApkOrH5 != 0 || this.signInResult.getAuthAccountPicker() == null) {
                    ksy.b(TAG, "handleH5LoginResult: status code is invalid", true);
                    this.mPresenter.onSignInFailed(saveDataByApkOrH5);
                    return;
                }
                ksy.b(TAG, "onActivityResultFromH5 SUCCESS", true);
                try {
                    addAccountAttr(stringExtra, intent, i, "1");
                    return;
                } catch (JSONException unused) {
                    ksy.c(TAG, "onActivityResultFromH5: JsonException", true);
                    this.mPresenter.onSignInFailed(i2);
                    return;
                }
            }
            ksy.b(TAG, "handleH5LoginResult: jsonSignInResult is null", true);
            this.mPresenter.onSignInFailed(i2);
            return;
        }
        ksy.c(TAG, "data is null.", true);
        this.mPresenter.onSignInFailed(i2);
    }

    protected int saveDataByApkOrH5(int i, String str) {
        ksy.b(TAG, "saveDataByApkOrH5 start.", true);
        try {
            this.signInResult = new AccountPickerResult().fromJson(str);
            a.a().a(this.signInResult.getAuthAccountPicker(), this.mPresenter.getSignInRequest().getAccountPickerParams());
            i = this.signInResult.getStatus().getStatusCode();
        } catch (JSONException unused) {
            this.signInResult = null;
            ksy.c(TAG, "saveDataByApkOrH5: JsonException", true);
        }
        this.mPresenter.report(i);
        return i;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mPresenter.onSaveInstanceState(bundle);
    }

    @Override // com.huawei.hms.support.picker.activity.AccountPickerSignInHubContract.View
    public void startSignInActivity(Intent intent, int i) {
        ksy.b(TAG, "startSignInActivity:requestCode = " + i, true);
        if (intent == null) {
            ksy.c(TAG, "startSignInActivity:intent is null.", true);
            this.mPresenter.onSignInFailed(17);
            return;
        }
        try {
            startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e) {
            this.signInNotFoundActivity = true;
            ksy.c(TAG, "Launch sign in Intent failed. hms is probably being updated：" + e.getClass().getSimpleName(), true);
            this.mPresenter.onSignInFailed(17);
        }
    }

    private void addAccountAttr(String str, Intent intent, int i, String str2) throws JSONException {
        ksy.b(TAG, "addAccountAttr start.", true);
        JSONObject jSONObject = new JSONObject(str);
        JSONObject optJSONObject = jSONObject.optJSONObject(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID);
        if (optJSONObject != null) {
            optJSONObject.put("accountAttr", str2);
            ksy.b(TAG, "addAccountAttr: accountPickerJson = " + optJSONObject.toString(), false);
            String optString = optJSONObject.optString("serverAuthCode");
            ksy.b(TAG, "addAccountAttr: jsonObject = " + jSONObject.toString(), false);
            boolean isNeedRequestToken = this.mPresenter.isNeedRequestToken(str2);
            if (!TextUtils.isEmpty(optString) && isNeedRequestToken) {
                this.mPresenter.doTokenRequest(optString);
            }
            if (isNeedRequestToken) {
                return;
            }
        }
        intent.putExtra("HUAWEIID_SIGNIN_RESULT", jSONObject.toString());
        setResult(i, intent);
        finish();
    }

    private void dealApkSignInResult(String str, Intent intent, int i, int i2) {
        ksy.b(TAG, "dealApkSignInResult", true);
        int saveDataByApkOrH5 = saveDataByApkOrH5(i, str);
        ksy.b(TAG, "statusCode is " + saveDataByApkOrH5, true);
        if (saveDataByApkOrH5 != 0) {
            if (20051 == saveDataByApkOrH5) {
                ksy.b(TAG, "logIn By H5", true);
                this.mPresenter.startWebViewActivity();
                return;
            } else {
                ksy.b(TAG, "logIn By APK failed", true);
                this.mPresenter.onSignInFailed(saveDataByApkOrH5);
                return;
            }
        }
        if (this.signInResult.getAuthAccountPicker() != null) {
            ksy.b(TAG, "onActivityResultFromApk SUCCESS", true);
            try {
                addAccountAttr(str, intent, i2, getAccountAttr(str));
            } catch (JSONException unused) {
                ksy.c(TAG, "onActivityResultFromApk: JsonException", true);
                this.mPresenter.onSignInFailed(i);
            }
        }
    }

    private String getAccountAttr(String str) {
        ksy.b(TAG, "getAccountAttr start.", true);
        try {
            if (!AccountPickerUtil.isPcSimulator(str)) {
                return "0";
            }
            JSONObject optJSONObject = new JSONObject(str).optJSONObject(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID);
            return optJSONObject != null ? optJSONObject.optString("accountAttr", "-1") : "-1";
        } catch (Exception e) {
            ksy.c(TAG, "getAccountAttr: Exception = " + e.getClass().getSimpleName(), true);
            return "-1";
        }
    }

    @Override // com.huawei.hms.support.picker.activity.AccountPickerSignInHubContract.View
    public void exit(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }

    private void setStatusBar() {
        if (!kst.j() || kte.c(this)) {
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(9216);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
