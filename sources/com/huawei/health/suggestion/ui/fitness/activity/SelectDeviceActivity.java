package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.dialog.CustomDeviceSelectDialog;
import com.huawei.health.device.ui.dialog.DeviceCheckAdapter;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SelectDeviceActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.fhw;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class SelectDeviceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CustomDeviceSelectDialog f3099a;
    private int b;
    private HealthDevice.HealthDeviceKind d;
    private ArrayList<ContentValues> e;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        LogUtil.a("Suggestion_SelectDeviceActivity", "come in SelectDeviceActivity");
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_SelectDeviceActivity", "getIntent is null");
            return;
        }
        this.b = intent.getIntExtra("SPORT_TYPE", 264);
        this.d = (HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(this.b));
        this.e = ceo.d().d(this.d);
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.activity_fitness_device_connect);
        b();
    }

    private void b() {
        if (this.d == null) {
            LogUtil.h("Suggestion_SelectDeviceActivity", "showDeviceSelectDialog mCurrentDeviceKind is null");
            return;
        }
        CustomDeviceSelectDialog e = new CustomDeviceSelectDialog.Builder(this).b(R.string.IDS_plugin_device_select).e(this.d).d(R.string._2130842267_res_0x7f02129b, new DeviceCheckAdapter.OnItemClickListener() { // from class: flp
            @Override // com.huawei.health.device.ui.dialog.DeviceCheckAdapter.OnItemClickListener
            public final void onItemClick(cjv cjvVar) {
                SelectDeviceActivity.this.e(cjvVar);
            }
        }).Hx_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.SelectDeviceActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectDeviceActivity.this.f3099a != null) {
                    SelectDeviceActivity.this.f3099a.dismiss();
                    SelectDeviceActivity.this.f3099a = null;
                    ObserverManagerUtil.c("DEVICE_ASSOCIATION", 152);
                } else {
                    LogUtil.h("Suggestion_SelectDeviceActivity", "showDeviceSelectDialog setNegativeButton mUnbindDialog is null");
                }
                SelectDeviceActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.f3099a = e;
        e.setCancelable(false);
        this.f3099a.show();
    }

    public /* synthetic */ void e(cjv cjvVar) {
        CustomDeviceSelectDialog customDeviceSelectDialog = this.f3099a;
        if (customDeviceSelectDialog != null) {
            customDeviceSelectDialog.dismiss();
            this.f3099a = null;
            ObserverManagerUtil.c("DEVICE_ASSOCIATION", cjvVar.FT_().getAsString("uniqueId"), fhw.f12519a.get(Integer.valueOf(this.b)));
        } else {
            LogUtil.h("Suggestion_SelectDeviceActivity", "showDeviceSelectDialog setPositiveButton mUnbindDialog is null");
        }
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        LogUtil.a("Suggestion_SelectDeviceActivity", "finish()");
        super.finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
