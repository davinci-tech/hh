package com.huawei.pluginhealthzone.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.efb;
import defpackage.ixx;
import defpackage.iyk;
import defpackage.mqr;
import defpackage.mqt;
import defpackage.nsf;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes.dex */
public class FamilyHealthTempActivity extends BaseActivity {
    private String b;
    private String c;
    private String d;
    private String e;
    private String g;
    private String h;

    /* renamed from: a, reason: collision with root package name */
    private String f8479a = null;
    private String f = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
        getWindow().setBackgroundDrawableResource(R$color.common_white_0alpha);
        cmT_(getIntent());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("FamilyHealthTempActivity", "onNewIntent");
        setIntent(intent);
        cmT_(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("FamilyHealthTempActivity", "onResume");
        Intent intent = getIntent();
        cmV_(intent);
        if (TextUtils.isEmpty(this.h)) {
            LogUtil.h("FamilyHealthTempActivity", "verifyCode is empty");
        }
        cmU_(intent);
        if (efb.l()) {
            a();
        }
        finish();
    }

    private void cmT_(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        this.f8479a = extras.getString("pushId");
        String string = extras.getString("serviceId");
        this.f = string;
        LogUtil.a("FamilyHealthTempActivity", "mPushId = ", this.f8479a, "mServiceId =", string);
    }

    private void cmU_(Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.hasExtra("pushType")) {
            String stringExtra = intent.getStringExtra("pushType");
            this.g = stringExtra;
            LogUtil.a("FamilyHealthTempActivity", "mPushType = ", stringExtra);
        }
        if (intent.hasExtra(HealthZonePushReceiver.MEMBER_HUID)) {
            String stringExtra2 = intent.getStringExtra(HealthZonePushReceiver.MEMBER_HUID);
            this.e = stringExtra2;
            LogUtil.a("FamilyHealthTempActivity", "memberhuid = ", stringExtra2);
        }
        if (intent.hasExtra(HealthZonePushReceiver.DETAIL_TYPE)) {
            this.e = intent.getStringExtra(HealthZonePushReceiver.DETAIL_TYPE);
            LogUtil.a("FamilyHealthTempActivity", "detailType = ", this.d);
        }
        if (intent.hasExtra(HealthZonePushReceiver.IS_HASH_HUID)) {
            String stringExtra3 = intent.getStringExtra(HealthZonePushReceiver.IS_HASH_HUID);
            this.c = stringExtra3;
            LogUtil.a("FamilyHealthTempActivity", "isHashHuid = ", stringExtra3);
        }
    }

    private void cmV_(Intent intent) {
        if (intent == null) {
            return;
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        LogUtil.a("FamilyHealthTempActivity", "uri: ", zs_);
        if (zs_ != null) {
            String queryParameter = zs_.getQueryParameter(HealthZonePushReceiver.MEMBER_HUID);
            this.e = queryParameter;
            LogUtil.a("FamilyHealthTempActivity", "uri get memberhuid = ", queryParameter);
            String queryParameter2 = zs_.getQueryParameter("pushType");
            this.g = queryParameter2;
            LogUtil.a("FamilyHealthTempActivity", "uri get pushType = ", queryParameter2);
            String queryParameter3 = zs_.getQueryParameter(HealthZonePushReceiver.DETAIL_TYPE);
            this.d = queryParameter3;
            LogUtil.a("FamilyHealthTempActivity", "uri get detailType = ", queryParameter3);
            String queryParameter4 = zs_.getQueryParameter(HealthZonePushReceiver.IS_HASH_HUID);
            this.c = queryParameter4;
            LogUtil.a("FamilyHealthTempActivity", "uri get isHashHuid = ", queryParameter4);
            String queryParameter5 = zs_.getQueryParameter("from");
            this.b = queryParameter5;
            LogUtil.a("FamilyHealthTempActivity", "uri get from = ", queryParameter5);
            String[] split = zs_.toString().split("verificationcode=");
            if (split.length >= 2) {
                this.h = split[1];
                return;
            }
        }
        if (intent.hasExtra("verifyCode")) {
            this.h = intent.getStringExtra("verifyCode");
        }
    }

    private void a() {
        LogUtil.a("FamilyHealthTempActivity", "startH5 enter");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(CapabilityStatus.AWA_CAP_CODE_WIFI), "sIsFirstHealth");
        mqt mqtVar = new mqt();
        if ("1".equals(b)) {
            mqtVar.c("false");
        } else {
            mqtVar.c("true");
        }
        if (!TextUtils.isEmpty(this.h)) {
            mqtVar.d("2");
            mqtVar.h(this.h);
        } else if (!TextUtils.isEmpty(this.g)) {
            d(this.g, mqtVar, this.e, this.d, this.c);
        } else if (!TextUtils.isEmpty(this.b)) {
            mqtVar.d(this.b);
            mqtVar.e(this.e);
        } else {
            mqtVar.d("0");
        }
        mqr.c(this, mqtVar);
    }

    private void d(String str, mqt mqtVar, String str2, String str3, String str4) {
        if (str.equals("4")) {
            mqtVar.d("4");
        } else {
            mqtVar.d("1");
            ixx.d().c(new iyk.e().a(1).d(nsf.h(R.string.IDS_hwh_family_health_zone)).a((String) null).e(this.f8479a).c(this.f).b((String) null).b());
        }
        mqtVar.g(str);
        mqtVar.e(str2);
        mqtVar.a(str3);
        mqtVar.b(str4);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
