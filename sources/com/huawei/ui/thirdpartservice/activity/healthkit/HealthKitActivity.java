package com.huawei.ui.thirdpartservice.activity.healthkit;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cei;
import defpackage.nrh;
import defpackage.sdt;
import defpackage.shx;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class HealthKitActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10543a;
    private Context b;
    private HealthTextView c;
    private b d = new b(this);
    private Intent e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private HealthButton k;
    private HealthButton o;

    static class a implements IBaseResponseCallback {
        private WeakReference<HealthKitActivity> c;

        a(HealthKitActivity healthKitActivity) {
            this.c = new WeakReference<>(healthKitActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            HealthKitActivity healthKitActivity = this.c.get();
            if (healthKitActivity == null) {
                LogUtil.h("HealthKitActivity", "healthKitActivity = null");
            } else if (i == 0) {
                LogUtil.a("HealthKitActivity", "onResponse setUserPrivacy success ");
                healthKitActivity.d.sendEmptyMessage(1);
            } else {
                sdt.e(i);
            }
        }
    }

    static class b extends BaseHandler<HealthKitActivity> {
        b(HealthKitActivity healthKitActivity) {
            super(healthKitActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dWR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthKitActivity healthKitActivity, Message message) {
            if (message.what != 1) {
                return;
            }
            if (!healthKitActivity.j) {
                healthKitActivity.c();
            } else {
                healthKitActivity.setResult(-1);
                healthKitActivity.finish();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        setContentView(R.layout.activity_health_kit);
        this.f10543a = (HealthTextView) findViewById(R.id.health_kit_welcome_rela);
        this.c = (HealthTextView) findViewById(R.id.health_kit_welcome_guide_describe);
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_kit_link_button);
        this.k = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.health_kit_unlink_button);
        this.o = healthButton2;
        healthButton2.setOnClickListener(this);
        Intent intent = getIntent();
        this.e = intent;
        if (intent != null) {
            this.f = intent.getBooleanExtra("key_health_kit_authorization_to_bind_device", false);
            this.j = this.e.getBooleanExtra("key_health_kit_authorization_to_thread_device", false);
            this.g = this.e.getBooleanExtra("device_list_to_health_kit_authorization", false);
            this.h = this.e.getBooleanExtra("key_start_to_measure_to_health_kit_authorization", false);
        }
        if (this.j) {
            this.k.setVisibility(0);
            this.o.setVisibility(8);
            this.f10543a.setText(R.string._2130844975_res_0x7f021d2f);
            this.c.setText(R.string._2130844976_res_0x7f021d30);
            return;
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        boolean b2 = new shx().b(402);
        this.i = b2;
        LogUtil.a("HealthKitActivity", "checklinkedStatus, mIsLinked = ", Boolean.valueOf(b2));
        if (this.i) {
            b();
            return;
        }
        this.k.setVisibility(0);
        this.o.setVisibility(8);
        this.f10543a.setText(R.string._2130844975_res_0x7f021d2f);
        this.c.setText(R.string._2130844976_res_0x7f021d30);
    }

    private void b() {
        if (this.f) {
            dWQ_(this);
            return;
        }
        if (this.g) {
            e();
            return;
        }
        if (this.h) {
            a();
            return;
        }
        this.k.setVisibility(8);
        this.o.setVisibility(0);
        this.f10543a.setText(R.string._2130844977_res_0x7f021d31);
        this.c.setText(R.string._2130844978_res_0x7f021d32);
    }

    private void a() {
        String stringExtra = this.e.getStringExtra("productId");
        LogUtil.c("HealthKitActivity", "productId = ", stringExtra);
        String stringExtra2 = this.e.getStringExtra("uniqueId");
        LogUtil.c("HealthKitActivity", "uniqueId = ", stringExtra2);
        cei.b().setBeginMeasureBiAnalytics(stringExtra);
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setClassName(this.b, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", cei.b().getH5Path(stringExtra) + "#/type=2/sn=" + stringExtra2);
        intent.putExtra("productId", stringExtra);
        intent.putExtra("commonDeviceInfo", (ContentValues) this.e.getParcelableExtra("commonDeviceInfo"));
        startActivity(intent);
        finish();
    }

    private void e() {
        LogUtil.a("HealthKitActivity", "startWebViewActivity enter");
        String stringExtra = this.e.getStringExtra("productId");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.b("HealthKitActivity", "startWebViewActivity productId is null");
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setClassName(this.b, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", cei.b().getH5Path(stringExtra) + "#/type=4/uuidpre=" + cei.b().getSubProductId(stringExtra));
        intent.putExtra("productId", stringExtra);
        ContentValues contentValues = (ContentValues) this.e.getParcelableExtra("commonDeviceInfo");
        if (contentValues == null) {
            LogUtil.b("HealthKitActivity", "startWebViewActivity contentValues is null");
            finish();
        } else {
            intent.putExtra("commonDeviceInfo", contentValues);
            LogUtil.a("HealthKitActivity", "sannuo_type=4_device list page to H5 interface,Productid=", stringExtra);
            startActivity(intent);
            finish();
        }
    }

    private void dWQ_(Activity activity) {
        cei.b().downloadBloodSugarDeviceResource(activity);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        cei.b().destroyResourceDownloadTool();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("HealthKitActivity", "onClick view = null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.health_kit_link_button) {
            if (!CommonUtil.aa(this.b)) {
                nrh.b(this.b, R.string._2130841393_res_0x7f020f31);
                LogUtil.h("HealthKitActivity", "isNetworkConnected false");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            d(true);
            if (this.j) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (!this.f && !this.g && !this.h) {
                finish();
            }
        } else if (id == R.id.health_kit_unlink_button) {
            if (!CommonUtil.aa(this.b)) {
                nrh.b(this.b, R.string._2130841393_res_0x7f020f31);
                LogUtil.h("HealthKitActivity", "isNetworkConnected false");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            d(false);
            finish();
        } else {
            LogUtil.h("HealthKitActivity", "not effect click!");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(boolean z) {
        if (z) {
            new shx().b(402, new a(this), true);
        } else {
            new shx().b(402, new a(this), false);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
