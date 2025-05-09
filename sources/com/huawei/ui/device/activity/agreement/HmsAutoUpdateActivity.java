package com.huawei.ui.device.activity.agreement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nsy;
import defpackage.nuj;
import defpackage.nyu;
import defpackage.nyv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class HmsAutoUpdateActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f9048a;
    private String b;
    private String c;
    private Context d;
    private String f;
    private nyu j;
    private Handler g = new a(this);
    private int h = -1;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.agreement.HmsAutoUpdateActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                LogUtil.a("AutoUpdateOverWifiActivity", "onReceive broadcast_receiver_user_setting");
                HmsAutoUpdateActivity.this.g.removeMessages(1);
                HmsAutoUpdateActivity.this.finish();
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("AutoUpdateOverWifiActivity", "the intent type is not DeviceInfo");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (HmsAutoUpdateActivity.this.b == null || !HmsAutoUpdateActivity.this.b.equals(deviceInfo.getmDeviceId())) {
                    LogUtil.h("AutoUpdateOverWifiActivity", "This device does not have the correspond capability.");
                    return;
                }
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 4 || deviceConnectState == 3) {
                    LogUtil.a("AutoUpdateOverWifiActivity", "onReceive connected");
                    HmsAutoUpdateActivity.this.finish();
                    return;
                }
                return;
            }
            LogUtil.a("AutoUpdateOverWifiActivity", "onReceive else branch");
        }
    };

    static class a extends Handler {
        WeakReference<HmsAutoUpdateActivity> d;

        a(HmsAutoUpdateActivity hmsAutoUpdateActivity) {
            this.d = new WeakReference<>(hmsAutoUpdateActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            HmsAutoUpdateActivity hmsAutoUpdateActivity = this.d.get();
            if (hmsAutoUpdateActivity == null) {
                LogUtil.h("AutoUpdateOverWifiActivity", "activity is null");
            } else if (message.what == 1) {
                nuj.a(hmsAutoUpdateActivity.c, 2, 7);
                hmsAutoUpdateActivity.d();
                LogUtil.a("AutoUpdateOverWifiActivity", "handleMessage pair time out");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("AutoUpdateOverWifiActivity", "HmsAutoUpdateActivity onCreate() ");
        this.d = this;
        setContentView(R.layout.wifi_update_switch_activity);
        e();
        c();
        IntentFilter intentFilter = new IntentFilter("broadcast_receiver_user_setting");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.d, this.e, intentFilter, LocalBroadcast.c, null);
    }

    private nyu e(String str) {
        return new nyv().c(nuj.e(str, this.f));
    }

    private void e() {
        if (getIntent() == null) {
            LogUtil.h("AutoUpdateOverWifiActivity", "initDeclarationDataWifi intent is null");
            return;
        }
        this.f9048a = getIntent().getStringExtra("device_country_code");
        this.f = getIntent().getStringExtra("device_emui_version");
        this.c = getIntent().getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        this.j = e("hms_auto_update");
        this.h = getIntent().getIntExtra("device_version_type", -1);
        Serializable serializableExtra = getIntent().getSerializableExtra("hms_auto_update");
        this.b = getIntent().getStringExtra("pairGuideSelectAddress");
        if (serializableExtra instanceof nyu) {
            this.j.e(((nyu) serializableExtra).g());
        }
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.wear_home_open_service_statement_title);
        if (customTitleBar != null) {
            customTitleBar.setTitleText(BaseApplication.getContext().getString(R.string._2130843947_res_0x7f02192b));
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(this.j);
        ListView listView = (ListView) nsy.cMc_(this, R.id.dev_switch_list_view);
        DeclarationAdapter declarationAdapter = new DeclarationAdapter(this.d, arrayList, this.h);
        if (listView != null) {
            listView.setAdapter((ListAdapter) declarationAdapter);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("AutoUpdateOverWifiActivity", "dispatchTouchEvent event is null");
            return false;
        }
        if (motionEvent.getAction() == 2) {
            this.g.removeMessages(1);
            this.g.sendEmptyMessageDelayed(1, 60000L);
            LogUtil.a("AutoUpdateOverWifiActivity", "timeout is reset");
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Handler handler = this.g;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 60000L);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.e;
        if (broadcastReceiver != null) {
            BroadcastManagerUtil.bFK_(this.d, broadcastReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("AutoUpdateOverWifiActivity", "enter notify Pair Fail");
        Intent intent = new Intent();
        intent.setAction("broadcast_receiver_user_setting");
        intent.putExtra("error_code", -1);
        intent.putExtra("pairGuideSelectAddress", this.b);
        BroadcastManagerUtil.bFH_(this.d, intent, LocalBroadcast.c, true);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
