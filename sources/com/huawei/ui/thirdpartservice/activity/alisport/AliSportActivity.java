package com.huawei.ui.thirdpartservice.activity.alisport;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.main.api.MainCommonApi;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.ThirdAuthTokenI;
import com.huawei.hwcloudmodel.model.ThirdAuthTokenO;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.thirdpartservice.activity.alisport.AliSportActivity;
import defpackage.ixx;
import defpackage.jbs;
import defpackage.jdx;
import defpackage.sec;
import defpackage.sha;
import defpackage.shc;
import defpackage.shg;
import defpackage.shr;
import defpackage.shs;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class AliSportActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10540a;
    private HealthButton b;
    private HealthButton c;
    private LinearLayout d;
    private c e;
    private HealthTextView g;
    private HealthTextView h;
    private jbs j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("AliSportActivity", "onCreate");
        super.onCreate(bundle);
        this.f10540a = this;
        this.e = new c(this);
        this.j = jbs.a(this.f10540a);
        setContentView(R.layout.activity_ali_sport);
        Intent intent = getIntent();
        if (intent != null) {
            b(intent.getBooleanExtra("AUTH_STATUS", false));
        }
    }

    private void b(boolean z) {
        this.g = (HealthTextView) findViewById(R.id.ali_sport_connect_tip);
        HealthButton healthButton = (HealthButton) findViewById(R.id.aliSport_button_connect);
        this.c = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sfe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AliSportActivity.this.dWN_(view);
            }
        });
        this.b = (HealthButton) findViewById(R.id.aliSport_button_cancel_connect);
        this.d = (LinearLayout) findViewById(R.id.aliSport_button_cancel_linear_layout);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: sfg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AliSportActivity.this.dWO_(view);
            }
        });
        this.h = (HealthTextView) findViewById(R.id.ali_sport_connect_show_content);
        a(z);
    }

    public /* synthetic */ void dWN_(View view) {
        if ("false".equals(((MainCommonApi) Services.c("Main", MainCommonApi.class)).getPersonalPrivacySettingValue(3))) {
            ((WeChatApi) Services.c("Main", WeChatApi.class)).showSportPrivacySettingDialog(this.f10540a, R.string._2130842768_res_0x7f021490);
        } else {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dWO_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.g.setVisibility(z ? 8 : 0);
        this.c.setVisibility(z ? 8 : 0);
        this.d.setVisibility(z ? 0 : 8);
        this.h.setText(z ? R.string._2130842771_res_0x7f021493 : R.string._2130842770_res_0x7f021492);
        if (z) {
            this.h.setGravity(17);
        } else {
            this.h.setGravity(GravityCompat.START);
        }
        KeyValDbManager.b(BaseApplication.getContext()).d("third_part_service_ali_sport_status", String.valueOf(z), null);
    }

    private void a() {
        if (sec.b(this.f10540a, "com.eg.android.AlipayGphone")) {
            jdx.b(new Runnable() { // from class: sfh
                @Override // java.lang.Runnable
                public final void run() {
                    AliSportActivity.this.b();
                }
            });
            return;
        }
        LogUtil.h("AliSportActivity", "startAliAuth System is not install Ali pay App.");
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.eg.android.AlipayGphone"));
        intent.addFlags(268435456);
        if (intent.resolveActivity(this.f10540a.getPackageManager()) == null) {
            sec.d(this.f10540a, R.string._2130841794_res_0x7f0210c2, R.string._2130842392_res_0x7f021318);
        } else {
            dWL_(intent);
        }
    }

    private void a(int i) {
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = i;
        this.e.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!CommonUtil.aa(this.f10540a)) {
            a(6);
            return;
        }
        shr dXP_ = shs.dXP_(this, sha.b(1, 24, 1024, 2024));
        if (dXP_ == null) {
            a(2);
            return;
        }
        String b = dXP_.b();
        if (!TextUtils.equals(b, "9000") || !TextUtils.equals(dXP_.e(), "200")) {
            LogUtil.a("AliSportActivity", "doAliAuth failed ! ali auth error , result is ", b);
            if (TextUtils.equals(b, "6001")) {
                a(5);
                return;
            } else {
                a(2);
                return;
            }
        }
        ThirdAuthTokenI b2 = b(dXP_.a(), dXP_.c());
        if (b2 == null) {
            LogUtil.a("AliSportActivity", "doAliAuth failed ! prepare thirdAuthToken error , userID is null");
            a(2);
            return;
        }
        c(b2);
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("status", 0);
        String value = AnalyticsValue.HEALTH_MINE_SHARE_DATA_ALI_SYNC_2140011.value();
        ixx.d().d(this.f10540a, value, hashMap, 0);
        LogUtil.a("AliSportActivity", "BI save notification click event finish, value = ", value);
    }

    private void c(ThirdAuthTokenI thirdAuthTokenI) {
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAuthToken", thirdAuthTokenI);
        new shc(ThirdAuthTokenO.class, "/dataOpen/third/thirdAuthorization").c(hashMap, new ICloudOperationResult() { // from class: sfb
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                AliSportActivity.this.c((ThirdAuthTokenO) obj, str, z);
            }
        });
    }

    public /* synthetic */ void c(ThirdAuthTokenO thirdAuthTokenO, String str, boolean z) {
        LogUtil.a("AliSportActivity", "thirdAuthorization isSuccess is ", Boolean.valueOf(z));
        if (z && thirdAuthTokenO.getThirdAuthToken() != null) {
            a(1);
        } else {
            a(2);
        }
    }

    private ThirdAuthTokenI b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ThirdAuthTokenI thirdAuthTokenI = new ThirdAuthTokenI();
        thirdAuthTokenI.setThirdAccountType(24);
        thirdAuthTokenI.setOpenId(str);
        thirdAuthTokenI.setThirdTokenType(3);
        thirdAuthTokenI.setThirdToken(str2);
        return thirdAuthTokenI;
    }

    private void e() {
        if (!CommonUtil.aa(this.f10540a)) {
            a(6);
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f10540a).e(R.string._2130842409_res_0x7f021329).czC_(R.string._2130841740_res_0x7f02108c, new View.OnClickListener() { // from class: sff
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AliSportActivity.this.dWM_(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: sfc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AliSportActivity.dWK_(view);
            }
        }).e();
        e.setCancelable(false);
        if (isDestroyed() || isFinishing()) {
            return;
        }
        e.show();
    }

    public /* synthetic */ void dWM_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dWK_(View view) {
        LogUtil.c("AliSportActivity", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAccountType", 24);
        new shg(Object.class, "/dataOpen/third/cancelThirdAuthorization").d(hashMap, new ICloudOperationResult() { // from class: sfd
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                AliSportActivity.this.b(obj, str, z);
            }
        });
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("click", "1");
        hashMap2.put("status", 1);
        String value = AnalyticsValue.HEALTH_MINE_SHARE_DATA_ALI_SYNC_2140011.value();
        ixx.d().d(this.f10540a, value, hashMap2, 0);
        LogUtil.a("AliSportActivity", "BI save notification click event finish, value = ", value);
    }

    public /* synthetic */ void b(Object obj, String str, boolean z) {
        LogUtil.a("AliSportActivity", "thirdAuthorization isSuccess is ", Boolean.valueOf(z));
        if (z) {
            a(3);
        } else {
            a(4);
        }
    }

    private void dWL_(Intent intent) {
        PackageManager packageManager = getPackageManager();
        if (packageManager == null) {
            LogUtil.b("AliSportActivity", "packageManager is null");
            return;
        }
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            LogUtil.b("AliSportActivity", "resolveInfo is null");
            return;
        }
        try {
            if (TextUtils.isEmpty(resolveActivity.activityInfo.name) || TextUtils.isEmpty(resolveActivity.activityInfo.packageName)) {
                throw new ActivityNotFoundException();
            }
            LogUtil.a("AliSportActivity", "packageName:", resolveActivity.activityInfo.packageName);
            LogUtil.a("AliSportActivity", "name:", resolveActivity.activityInfo.name);
            if (!resolveActivity.activityInfo.name.startsWith(resolveActivity.activityInfo.packageName)) {
                LogUtil.b("AliSportActivity", "ResolverActivity Not Found");
                throw new ActivityNotFoundException();
            }
            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
            startActivity(intent);
        } catch (ActivityNotFoundException | SecurityException unused) {
            LogUtil.b("AliSportActivity", "SecurityException: Permission Denial: starting Intent");
            sec.d(this.f10540a, R.string._2130841794_res_0x7f0210c2, R.string._2130842392_res_0x7f021318);
        }
    }

    static class c extends BaseHandler<AliSportActivity> {
        c(AliSportActivity aliSportActivity) {
            super(aliSportActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dWP_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AliSportActivity aliSportActivity, Message message) {
            LogUtil.c("AliSportActivity", "handleMessage msg is ", Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    aliSportActivity.a(true);
                    break;
                case 2:
                    aliSportActivity.a(false);
                    Toast.makeText(aliSportActivity, R.string._2130842410_res_0x7f02132a, 0).show();
                    break;
                case 3:
                case 5:
                    aliSportActivity.a(false);
                    break;
                case 4:
                    Toast.makeText(aliSportActivity, R.string._2130842411_res_0x7f02132b, 0).show();
                    break;
                case 6:
                    Toast.makeText(aliSportActivity, R.string._2130841392_res_0x7f020f30, 0).show();
                    break;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
