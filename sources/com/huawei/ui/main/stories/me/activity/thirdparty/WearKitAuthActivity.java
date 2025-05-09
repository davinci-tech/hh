package com.huawei.ui.main.stories.me.activity.thirdparty;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class WearKitAuthActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSwitchButton f10360a;
    private HealthSwitchButton b;
    private HealthSwitchButton c;
    private HiAppInfo e;
    private List<HealthSwitchButton> f;
    private HealthSwitchButton h;
    private CompoundButton.OnCheckedChangeListener j;
    private boolean g = false;
    private Handler d = new d(this);

    static class d extends Handler {
        private final WeakReference<WearKitAuthActivity> b;

        d(WearKitAuthActivity wearKitAuthActivity) {
            this.b = new WeakReference<>(wearKitAuthActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WearKitAuthActivity wearKitAuthActivity = this.b.get();
            if (wearKitAuthActivity == null) {
                return;
            }
            LogUtil.a("WearKitAuthActivity", "mHandler msg.what = ", Integer.valueOf(message.what));
            if (message.what == 1000) {
                for (WearKitPermission wearKitPermission : (List) message.obj) {
                    if (wearKitPermission != null) {
                        boolean z = wearKitPermission.getAllow() == 1;
                        int scopeId = wearKitPermission.getScopeId();
                        if (scopeId == 1) {
                            wearKitAuthActivity.c.setChecked(z);
                        } else if (scopeId == 2) {
                            wearKitAuthActivity.b.setChecked(z);
                        } else if (scopeId == 3) {
                            wearKitAuthActivity.h.setChecked(z);
                        }
                    }
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wear_kit_auth);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        try {
            this.e = (HiAppInfo) intent.getParcelableExtra(MapKeyNames.APP_INFO);
            this.g = intent.getBooleanExtra("logo", false);
        } catch (Exception e) {
            LogUtil.b("WearKitAuthActivity", "Exception: ", e.getClass().getSimpleName());
            this.e = null;
        }
        if (this.e == null) {
            LogUtil.h("WearKitAuthActivity", "mHiAppInfo == null");
            finish();
        } else {
            e();
        }
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_wear_kit_logo);
        if (this.g) {
            linearLayout.setVisibility(0);
        }
        h();
        b();
        this.j = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WearKitAuthActivity.this.c.setChecked(z);
                WearKitAuthActivity.this.b.setChecked(z);
                WearKitAuthActivity.this.h.setChecked(z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        };
        i();
        d();
    }

    private void h() {
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.hw_wear_kit_device_list_allow);
        String format = String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_hw_wear_kit_auth), this.e.getAppName());
        LogUtil.c("WearKitAuthActivity", "text = ", format);
        healthSubHeader.setHeadTitleText(format);
    }

    private void b() {
        this.f10360a = (HealthSwitchButton) findViewById(R.id.hw_wear_kit_switch_all);
        this.c = (HealthSwitchButton) findViewById(R.id.hw_wear_kit_device_list);
        this.b = (HealthSwitchButton) findViewById(R.id.hw_wear_kit_info_switch);
        this.h = (HealthSwitchButton) findViewById(R.id.hw_wear_kit_message_switch);
        c();
    }

    private void c() {
        ArrayList arrayList = new ArrayList(10);
        this.f = arrayList;
        arrayList.add(this.c);
        this.f.add(this.b);
        this.f.add(this.h);
    }

    private void i() {
        this.f10360a.setOnCheckedChangeListener(this.j);
        e(this.c, 1);
        e(this.b, 2);
        e(this.h, 3);
    }

    private void e(HealthSwitchButton healthSwitchButton, final int i) {
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WearKitAuthActivity wearKitAuthActivity = WearKitAuthActivity.this;
                wearKitAuthActivity.d(wearKitAuthActivity.e, i, z);
                WearKitAuthActivity.this.d(z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (!z && this.f10360a.isChecked()) {
            this.f10360a.setOnCheckedChangeListener(null);
            this.f10360a.setChecked(false);
            this.f10360a.setOnCheckedChangeListener(this.j);
        }
        if (z && !this.f10360a.isChecked() && a()) {
            this.f10360a.setChecked(true);
        }
    }

    private boolean a() {
        Iterator<HealthSwitchButton> it = this.f.iterator();
        while (it.hasNext()) {
            if (!it.next().isChecked()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiAppInfo hiAppInfo, int i, boolean z) {
        if (hiAppInfo == null) {
            LogUtil.h("WearKitAuthActivity", "changeAuth hiAppInfo null");
        } else {
            HiHealthNativeApi.a(this).updateWearKitPermission(hiAppInfo.getAppId(), i, z, new HiDataOperateListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity.3
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    if (i2 == 0) {
                        LogUtil.a("WearKitAuthActivity", "changeAuth success");
                    } else {
                        LogUtil.h("WearKitAuthActivity", "changeAuth fail");
                    }
                }
            });
        }
    }

    private void d() {
        HiHealthNativeApi.a(this).queryWearKitPermission(this.e.getAppId(), new HiDataOperateListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("WearKitAuthActivity", "checkStatus obj not list");
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("WearKitAuthActivity", "checkStatus list empty");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 1000;
                obtain.obj = list;
                WearKitAuthActivity.this.d.sendMessage(obtain);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
