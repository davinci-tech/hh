package com.huawei.ui.thirdpartservice.activity.wechat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jdx;
import health.compact.a.Services;
import java.util.Locale;

/* loaded from: classes9.dex */
public class WeChatHealthActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10555a;
    private WeChatApi b;
    private HealthButton c;
    private HealthTextView d;
    private Context e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wechat_health);
        this.e = this;
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        jdx.b(new Runnable() { // from class: com.huawei.ui.thirdpartservice.activity.wechat.WeChatHealthActivity.2
            @Override // java.lang.Runnable
            public void run() {
                WeChatHealthActivity.this.b = (WeChatApi) Services.c("Main", WeChatApi.class);
                if (WeChatHealthActivity.this.b.isUserBind(WeChatHealthActivity.this.e)) {
                    return;
                }
                WeChatHealthActivity.this.startActivity(new Intent(WeChatHealthActivity.this.e, (Class<?>) WeChatConnectActivity.class));
                WeChatHealthActivity.this.finish();
            }
        });
    }

    private void d() {
        this.f10555a = (HealthTextView) findViewById(R.id.weChat_unbind_guide1);
        this.d = (HealthTextView) findViewById(R.id.weChat_unbind_guide2);
        this.c = (HealthButton) findViewById(R.id.weChat_rebind);
        this.f10555a.setText(String.format(Locale.ENGLISH, getString(R.string._2130843440_res_0x7f021730), 1));
        this.d.setText(String.format(Locale.ENGLISH, getString(R.string._2130843441_res_0x7f021731), 2));
        this.c.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("WeChatHealthActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.weChat_rebind) {
                startActivity(new Intent(this.e, (Class<?>) WeChatConnectActivity.class));
                finish();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
