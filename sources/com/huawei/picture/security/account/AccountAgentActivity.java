package com.huawei.picture.security.account;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.picker.service.AccountPickerService;
import com.huawei.picture.security.account.bean.ErrorStatus;
import com.huawei.picture.security.account.handler.CloudAccountHandler;
import com.huawei.secure.android.common.intent.SafeIntent;
import defpackage.mbs;
import defpackage.mcf;
import defpackage.mci;
import defpackage.mcj;

/* loaded from: classes6.dex */
public class AccountAgentActivity extends BaseAgentActivity {

    /* renamed from: a, reason: collision with root package name */
    private final Runnable f8368a = new Runnable() { // from class: com.huawei.picture.security.account.AccountAgentActivity.4
        @Override // java.lang.Runnable
        public void run() {
            if (AccountAgentActivity.this.c != null) {
                AccountAgentActivity.this.c.logFromSdk("finishTask scheduledShutdownActivity finish");
            }
            AccountAgentActivity.this.finish();
        }
    };
    private CloudAccountHandler c;

    @Override // com.huawei.picture.security.account.BaseAgentActivity, com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent signInIntent;
        super.onCreate(bundle);
        SafeIntent safeIntent = new SafeIntent(getIntent());
        boolean booleanExtra = safeIntent.getBooleanExtra("isMcp", false);
        boolean booleanExtra2 = safeIntent.getBooleanExtra("hasMms", true);
        String stringExtra = safeIntent.getStringExtra("subAppId");
        String stringExtra2 = safeIntent.getStringExtra("handlerId");
        if (stringExtra2 == null) {
            finish();
            return;
        }
        CloudAccountHandler cloudAccountHandler = mbs.d().d.get(stringExtra2);
        this.c = cloudAccountHandler;
        if (cloudAccountHandler == null) {
            finish();
            return;
        }
        if (!booleanExtra) {
            signInIntent = mbs.d().d(this).getSignInIntent();
        } else if (booleanExtra2) {
            try {
                AccountAuthService d = mbs.d().d(this);
                d.setSubAppId(stringExtra);
                signInIntent = d.getSignInIntent();
            } catch (ApiException e) {
                mcj.d("AccountAgentActivity", "AccountAuthService ApiException: ", e);
                signInIntent = null;
                mcf.cfK_(this, signInIntent, 8888);
                d();
            }
        } else {
            try {
                AccountPickerService cfG_ = mbs.d().cfG_(this);
                cfG_.setSubAppId(stringExtra);
                signInIntent = cfG_.signInByMcp();
            } catch (ApiException e2) {
                mcj.d("AccountAgentActivity", "AccountPickerService ApiException: ", e2);
                signInIntent = null;
                mcf.cfK_(this, signInIntent, 8888);
                d();
            }
        }
        mcf.cfK_(this, signInIntent, 8888);
        d();
    }

    private void d() {
        mci.c(this.f8368a, 3000L);
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        mci.a(this.f8368a);
    }

    @Override // com.huawei.picture.security.account.BaseAgentActivity, com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            mci.a(this.f8368a);
            if (!(intent instanceof SafeIntent)) {
                intent = new SafeIntent(intent);
            }
            if (i == 8888) {
                mcj.c("AccountAgentActivity", "onActivityResult requestCode : " + i);
                Task<AuthAccount> parseAuthResultFromIntent = AccountAuthManager.parseAuthResultFromIntent(intent);
                this.c.logFromSdk("onActivityResult isSuccessful:" + parseAuthResultFromIntent.isSuccessful());
                if (parseAuthResultFromIntent.isSuccessful()) {
                    AuthAccount result = parseAuthResultFromIntent.getResult();
                    mbs.d().c(result.getAuthorizationCode(), result.getServiceCountryCode(), this.c);
                } else {
                    ApiException apiException = (ApiException) parseAuthResultFromIntent.getException();
                    mcj.b("AccountAgentActivity", "onActivityResult signIn failed : " + apiException.getStatusCode());
                    this.c.onRequestError(new ErrorStatus(apiException.getStatusCode(), apiException.getMessage()));
                }
                finish();
            }
        } catch (Exception e) {
            mcj.d("AccountAgentActivity", "onActivityResult exception : ", e);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
