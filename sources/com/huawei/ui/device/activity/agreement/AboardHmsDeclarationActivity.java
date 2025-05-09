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
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.jfq;
import defpackage.nsy;
import defpackage.nuj;
import defpackage.nyu;
import defpackage.nyv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AboardHmsDeclarationActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private DeclarationAdapter f9039a;
    private List<nyu> b;
    private String c;
    private String d;
    private Context e;
    private String i;
    private String j;
    private nyu k;
    private nyu l;
    private nyu o;
    private int m = -1;
    private Handler g = new a(this);
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.agreement.AboardHmsDeclarationActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                LogUtil.a("AboardHmsDeclarationActivity", "onReceive broadcast_receiver_user_setting");
                AboardHmsDeclarationActivity.this.g.removeMessages(1);
                AboardHmsDeclarationActivity.this.finish();
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("AboardHmsDeclarationActivity", "the intent type is not DeviceInfo");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (TextUtils.isEmpty(AboardHmsDeclarationActivity.this.d) || !AboardHmsDeclarationActivity.this.d.equals(deviceInfo.getDeviceIdentify())) {
                    LogUtil.h("AboardHmsDeclarationActivity", "This broadcast is not sent by the current device");
                    return;
                } else {
                    if (deviceInfo.getDeviceConnectState() == 2) {
                        LogUtil.a("AboardHmsDeclarationActivity", "onReceive connected");
                        AboardHmsDeclarationActivity.this.finish();
                        return;
                    }
                    return;
                }
            }
            LogUtil.a("AboardHmsDeclarationActivity", "onReceive else branch");
        }
    };
    private EventBus.ICallback h = new EventBus.ICallback() { // from class: com.huawei.ui.device.activity.agreement.AboardHmsDeclarationActivity.3
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar == null) {
                LogUtil.h("AboardHmsDeclarationActivity", "event is null");
                return;
            }
            Bundle Kl_ = bVar.Kl_();
            if (Kl_ == null) {
                LogUtil.h("AboardHmsDeclarationActivity", "bundle is null");
                return;
            }
            if ("device_pair_aboard_switch_status".equals(bVar.e())) {
                int i = Kl_.getInt("hms_auto_update", -1);
                if (i != -1) {
                    boolean z = i == 1;
                    AboardHmsDeclarationActivity.this.o.e(z);
                    AboardHmsDeclarationActivity.this.k.e(z);
                    AboardHmsDeclarationActivity.this.f9039a.notifyDataSetChanged();
                }
                LogUtil.a("AboardHmsDeclarationActivity", "action", bVar.e());
            }
        }
    };

    static class a extends Handler {
        WeakReference<AboardHmsDeclarationActivity> e;

        a(AboardHmsDeclarationActivity aboardHmsDeclarationActivity) {
            this.e = new WeakReference<>(aboardHmsDeclarationActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AboardHmsDeclarationActivity aboardHmsDeclarationActivity = this.e.get();
            if (aboardHmsDeclarationActivity == null) {
                LogUtil.h("AboardHmsDeclarationActivity", "activity is null");
            } else if (message.what == 1) {
                nuj.a(aboardHmsDeclarationActivity.i, 2, 6);
                aboardHmsDeclarationActivity.j();
                LogUtil.a("AboardHmsDeclarationActivity", "handleMessage pair time out");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("AboardHmsDeclarationActivity", "AboardHmsDeclarationActivity onCreate() ");
        this.e = this;
        i();
        f();
        IntentFilter intentFilter = new IntentFilter("broadcast_receiver_user_setting");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.e, this.f, intentFilter, LocalBroadcast.c, null);
        EventBus.d(this.h, 1, "device_pair_aboard_switch_status");
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
        BroadcastReceiver broadcastReceiver = this.f;
        if (broadcastReceiver != null) {
            BroadcastManagerUtil.bFK_(this.e, broadcastReceiver);
        }
        EventBus.ICallback iCallback = this.h;
        if (iCallback != null) {
            EventBus.a(iCallback, "device_pair_aboard_switch_status");
        }
    }

    private void i() {
        if (getIntent() == null) {
            LogUtil.h("AboardHmsDeclarationActivity", "initData intent is null");
            finish();
            return;
        }
        Serializable serializableExtra = getIntent().getSerializableExtra("declarationBeans");
        if (serializableExtra instanceof List) {
            List list = (List) serializableExtra;
            this.b = new ArrayList(list.size());
            for (Object obj : list) {
                if (obj instanceof nyu) {
                    this.b.add((nyu) obj);
                }
            }
        }
        this.d = getIntent().getStringExtra("pairGuideSelectAddress");
        this.c = getIntent().getStringExtra("device_country_code");
        this.j = getIntent().getStringExtra("device_emui_version");
        this.i = getIntent().getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        this.m = getIntent().getIntExtra("device_version_type", -1);
        h();
    }

    private void h() {
        this.l = a("hms_statement_head");
        nyu a2 = a("hms_statement_body");
        this.o = a2;
        a2.d("huawei_mobile_service_statement");
        this.k = a("hms_auto_update");
        Map<String, DeviceCapability> a3 = jfq.c().a(3, "", "AboardHmsDeclarationActivity");
        if (a3 == null) {
            LogUtil.h("AboardHmsDeclarationActivity", "filter deviceCapabilityMap is null");
            return;
        }
        DeviceCapability deviceCapability = a3.get(this.d);
        if (deviceCapability != null) {
            this.o.e(deviceCapability.isHmsAutoUpdate());
            this.k.e(deviceCapability.isHmsAutoUpdateWifi());
            LogUtil.a("AboardHmsDeclarationActivity", Boolean.valueOf(deviceCapability.isHmsAutoUpdate()), Boolean.valueOf(deviceCapability.isHmsAutoUpdateWifi()));
            return;
        }
        LogUtil.a("AboardHmsDeclarationActivity", "deviceCapability is null");
    }

    private nyu a(String str) {
        return new nyv().c(nuj.e(str, this.j));
    }

    private void f() {
        setContentView(R.layout.activity_aboard_hms_declaration);
        View cMc_ = nsy.cMc_(this, R.id.aboard_declaration_text_view_back);
        if (cMc_ != null) {
            cMc_.setOnClickListener(this);
        }
        View cMc_2 = nsy.cMc_(this, R.id.aboard_declaration_text_view_next);
        if (cMc_2 != null) {
            cMc_2.setOnClickListener(this);
        }
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(this.l);
        arrayList.add(this.o);
        c(arrayList);
        ListView listView = (ListView) nsy.cMc_(this, R.id.list_view_content);
        DeclarationAdapter declarationAdapter = new DeclarationAdapter(this, arrayList, this.m);
        this.f9039a = declarationAdapter;
        if (listView != null) {
            listView.setAdapter((ListAdapter) declarationAdapter);
        }
    }

    private void c(List<nyu> list) {
        nyu nyuVar = new nyu();
        nyuVar.d(0);
        nyuVar.f(this.e.getResources().getString(R.string._2130843945_res_0x7f021929));
        list.add(0, nyuVar);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.aboard_declaration_text_view_back) {
            finish();
        } else if (view.getId() == R.id.aboard_declaration_text_view_next) {
            g();
        } else {
            LogUtil.a("AboardHmsDeclarationActivity", "onClick() the else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        ArrayList arrayList = new ArrayList(this.b.size() + 3);
        arrayList.addAll(this.b);
        arrayList.add(this.o);
        arrayList.add(this.k);
        Intent intent = new Intent(this.e, (Class<?>) EnhancementImprovementActivity.class);
        intent.putExtra("declarationBeans", arrayList);
        intent.putExtra("pairGuideSelectAddress", this.d);
        intent.putExtra("device_country_code", this.c);
        intent.putExtra("device_emui_version", this.j);
        intent.putExtra("device_version_type", this.m);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("AboardHmsDeclarationActivity", "enter notify Pair Fail");
        Intent intent = new Intent();
        intent.setAction("broadcast_receiver_user_setting");
        intent.putExtra("error_code", -1);
        intent.putExtra("pairGuideSelectAddress", this.d);
        BroadcastManagerUtil.bFH_(this.e, intent, LocalBroadcast.c, true);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("AboardHmsDeclarationActivity", "dispatchTouchEvent event is null");
            return false;
        }
        if (motionEvent.getAction() == 2) {
            this.g.removeMessages(1);
            this.g.sendEmptyMessageDelayed(1, 60000L);
            LogUtil.a("AboardHmsDeclarationActivity", "timeout is reset");
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public nyu d() {
        return this.k;
    }

    public String b() {
        return this.d;
    }

    public String a() {
        return this.c;
    }

    public String e() {
        return this.j;
    }

    public String c() {
        return this.i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
