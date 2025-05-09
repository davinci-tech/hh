package com.huawei.health;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.HuaweiLoginActivity;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ScopeManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.rvq;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class HuaweiLoginActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f2170a;
    private int c;
    private int d = 0;
    private boolean b = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "onCreate");
        Window window = getWindow();
        window.setFlags(AppRouterExtras.COLDSTART, AppRouterExtras.COLDSTART);
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        this.f2170a = this;
        b();
    }

    private void b() {
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "initView()");
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.c = intent.getIntExtra("callbackIndex", 0);
        int intExtra = intent.getIntExtra("requestCode", 0);
        int intExtra2 = intent.getIntExtra(CommonConstant.REALNAMERESULT.KEY_VERIFY_TYPE, 0);
        this.d = intExtra2;
        if (intExtra <= 0) {
            finish();
            return;
        }
        if (intExtra == 6009) {
            e();
            return;
        }
        if (intExtra == 6012) {
            Ar_(intent);
            return;
        }
        if (intExtra != 6011) {
            ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "initView requestCode == ", Integer.valueOf(intExtra));
        } else if (intExtra2 > 0) {
            c();
        } else {
            d();
        }
    }

    private void e() {
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "startAccountLogin()");
        String[] scopeList = ScopeManager.getScopeList();
        ArrayList arrayList = new ArrayList(64);
        for (String str : scopeList) {
            arrayList.add(new Scope(str));
        }
        startActivityForResult(AccountAuthManager.getService((Activity) this, new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setScopeList(arrayList).setAuthorizationCode().setAccessToken().setUid().setMobileNumber().setProfile().createParams()).getSignInIntent(), 6009);
        this.b = true;
    }

    private void c() {
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "enter kidAccountProtocolDialog.");
        if (TextUtils.isEmpty(SharedPreferenceUtil.getInstance(this.f2170a).getGuardianAccount())) {
            ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "kidAccountProtocolDialog is adult.");
            setResult(-1);
            finish();
        } else {
            View inflate = View.inflate(this.f2170a, R.layout.kid_account_protocol_dialog, null);
            Ao_(inflate);
            CustomAlertDialog c = new CustomAlertDialog.Builder(this.f2170a).a(this.f2170a.getString(R.string._2130846662_res_0x7f0223c6)).cyp_(inflate).cyn_(R.string._2130837555_res_0x7f020033, new DialogInterface.OnClickListener() { // from class: bxr
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    HuaweiLoginActivity.this.As_(dialogInterface, i);
                }
            }).cyo_(R.string._2130842480_res_0x7f021370, new DialogInterface.OnClickListener() { // from class: bxq
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    HuaweiLoginActivity.this.At_(dialogInterface, i);
                }
            }).c();
            c.setCancelable(false);
            c.show();
        }
    }

    public /* synthetic */ void As_(DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "finish MainAcitivity for cause: don't authorize kid account.");
        MainInteractors.d();
        finish();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void At_(DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "authorize kid account.");
        d();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void Ao_(View view) {
        if (view == null) {
            ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "initKidProtocolView view is null");
            return;
        }
        String h = nsf.h(R.string._2130847426_res_0x7f0226c2);
        String h2 = nsf.h(R.string._2130847430_res_0x7f0226c6);
        SpannableString spannableString = new SpannableString(nsf.b(R.string._2130847429_res_0x7f0226c5, h, h2));
        Aq_(spannableString, h);
        Aq_(spannableString, h2);
        ((HealthTextView) view.findViewById(R.id.kid_agreement_three)).setText(spannableString);
        String h3 = nsf.h(R.string.IDS_hwh_family_health_zone);
        String h4 = nsf.h(R.string._2130847427_res_0x7f0226c3);
        SpannableString spannableString2 = new SpannableString(nsf.b(R.string._2130847424_res_0x7f0226c0, h3, h4));
        Aq_(spannableString2, h3);
        Aq_(spannableString2, h4);
        ((HealthTextView) view.findViewById(R.id.kid_agreement_four)).setText(spannableString2);
        String h5 = nsf.h(R.string._2130841274_res_0x7f020eba);
        String h6 = nsf.h(R.string._2130841275_res_0x7f020ebb);
        SpannableString spannableString3 = new SpannableString(nsf.b(R.string._2130847425_res_0x7f0226c1, h6, h5));
        rvq rvqVar = new rvq(this.f2170a, "HealthUserAgreement", false);
        rvq rvqVar2 = new rvq(this.f2170a, "HealthPrivacy", false);
        Ap_(spannableString3, h6, rvqVar);
        Ap_(spannableString3, h5, rvqVar2);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.kid_agreement_five);
        healthTextView.setText(spannableString3);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void d() {
        if (CommonUtil.z(this.f2170a)) {
            a();
        } else {
            j();
        }
    }

    private void a() {
        ThirdPartyLoginManager.getInstance().checkUserPassword(this, "1", new IBaseResponseCallback() { // from class: com.huawei.health.HuaweiLoginActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "judgeIsKidAndNeedAuth checkUserPassword success");
                    HuaweiLoginActivity.this.setResult(-1);
                } else {
                    ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "checkUserPassword user close", Integer.valueOf(i));
                    HuaweiLoginActivity.this.setResult(0);
                    if (HuaweiLoginActivity.this.d > 0) {
                        MainInteractors.d();
                    }
                }
                HuaweiLoginActivity.this.finish();
            }
        });
    }

    private void j() {
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "enter verifyPassWordByHms().");
        String hMSPackageName = HMSPackageManager.getInstance(this.f2170a).getHMSPackageName();
        if (TextUtils.isEmpty(hMSPackageName)) {
            ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "checkPassWordByHms: not install hms.");
            return;
        }
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this.f2170a);
        String accountType = sharedPreferenceUtil.getAccountType();
        int i = "1".equals(accountType) ? 3 : 0;
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwid://com.huawei.hwid/VerifyPassword"));
            intent.setPackage(hMSPackageName);
            intent.putExtra("VERIFY_PASSWORD_TYPE", i);
            intent.putExtra(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, accountType);
            if (i == 3) {
                Context context = this.f2170a;
                int m = CommonUtil.m(context, LoginInit.getInstance(context).getAccountInfo(1009));
                String guardianAccount = sharedPreferenceUtil.getGuardianAccount();
                String guardianUid = sharedPreferenceUtil.getGuardianUid();
                intent.putExtra("VERIFY_PASSWORD_TYPE", "granted");
                intent.putExtra(HwPayConstant.KEY_USER_NAME, guardianAccount);
                intent.putExtra(JsbMapKeyNames.H5_USER_ID, guardianUid);
                intent.putExtra("siteId", m);
            }
            startActivityForResult(intent, SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
        } catch (ActivityNotFoundException unused) {
            ReleaseLogUtil.c("PLGLOGIN_HuaweiLoginActivity", "verifyPassWordByHms error.");
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "onActivityResult requestCode=", Integer.valueOf(i), "resultCode=", Integer.valueOf(i2));
        if (i == 6009) {
            ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "onActivityResult for account sdk login.");
            this.b = false;
            HuaweiLoginManager.getInstance().handleLoginRequest(intent, this.c);
            finish();
            return;
        }
        if (i == 6011) {
            ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "onActivityResult for verify passWord.");
            setResult(i2);
            if (this.d > 0 && i2 == 0) {
                MainInteractors.d();
            }
            finish();
        }
    }

    private void Ar_(Intent intent) {
        String stringExtra = intent.getStringExtra("requestParam");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "qrCodeAuthorize tempCode is empty");
        } else {
            ThirdPartyLoginManager.getInstance().qrCodeAuthorize(this, stringExtra, new IBaseResponseCallback() { // from class: com.huawei.health.HuaweiLoginActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "qrCodeAuthorize response ");
                    HuaweiLoginActivity.this.finish();
                }
            });
        }
    }

    private void Aq_(SpannableString spannableString, String str) {
        nsi.cKL_(spannableString, str, R.string._2130851581_res_0x7f0236fd);
        nsi.cKI_(spannableString, str, R.color._2131299236_res_0x7f090ba4);
    }

    private void Ap_(SpannableString spannableString, String str, rvq rvqVar) {
        nsi.cKL_(spannableString, str, R.string._2130851581_res_0x7f0236fd);
        nsi.cKH_(spannableString, str, rvqVar);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("PLGLOGIN_HuaweiLoginActivity", "onDestroy");
        if (this.b) {
            ReleaseLogUtil.d("PLGLOGIN_HuaweiLoginActivity", "onDestroy login not finished");
            HuaweiLoginManager.getInstance().handleLoginRequest(null, this.c);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
