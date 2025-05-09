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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jfq;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nug;
import defpackage.nuj;
import defpackage.nyu;
import defpackage.nyv;
import defpackage.nzf;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class EnhancementImprovementActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9046a;
    private DeclarationAdapter b;
    private String c;
    private String[] d;
    private List<nyu> e;
    private String g;
    private List<nzf> h;
    private String i;
    private List<nyu> j;
    private String o;
    private boolean n = false;
    private boolean l = true;
    private boolean k = false;
    private Handler m = new b(this);
    private int q = -1;
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.agreement.EnhancementImprovementActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            LogUtil.a("EnhancementImprovement", "onReceive mDevicePairReceiver:", intent.getAction());
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                LogUtil.a("EnhancementImprovement", "onReceive broadcast_receiver_user_setting");
                EnhancementImprovementActivity.this.m.removeMessages(1);
                EnhancementImprovementActivity.this.finish();
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("EnhancementImprovement", "the intent type is not DeviceInfo");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (TextUtils.isEmpty(EnhancementImprovementActivity.this.i) || !EnhancementImprovementActivity.this.i.equals(deviceInfo.getDeviceIdentify())) {
                    LogUtil.h("EnhancementImprovement", "This broadcast is not sent by the current device");
                    return;
                } else {
                    if (deviceInfo.getDeviceConnectState() == 2) {
                        LogUtil.a("EnhancementImprovement", "onReceive connected");
                        EnhancementImprovementActivity.this.finish();
                        return;
                    }
                    return;
                }
            }
            LogUtil.a("EnhancementImprovement", "onReceive else branch");
        }
    };

    static class b extends Handler {
        WeakReference<EnhancementImprovementActivity> c;

        b(EnhancementImprovementActivity enhancementImprovementActivity) {
            this.c = new WeakReference<>(enhancementImprovementActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            EnhancementImprovementActivity enhancementImprovementActivity = this.c.get();
            if (enhancementImprovementActivity == null) {
                LogUtil.h("EnhancementImprovement", "handleMessage activity is null");
            } else if (message.what == 1) {
                nuj.a(enhancementImprovementActivity.g, 2, 3);
                enhancementImprovementActivity.f();
                LogUtil.a("EnhancementImprovement", "handleMessage pair time out");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EnhancementImprovement", "EnhancementImprovementActivity onCreate()");
        this.f9046a = this;
        d();
        a();
        IntentFilter intentFilter = new IntentFilter("broadcast_receiver_user_setting");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.f9046a, this.f, intentFilter, LocalBroadcast.c, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.f;
        if (broadcastReceiver != null) {
            BroadcastManagerUtil.bFK_(this.f9046a, broadcastReceiver);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Handler handler = this.m;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 60000L);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void d() {
        if (getIntent() == null) {
            LogUtil.h("EnhancementImprovement", "initData intent is null");
            return;
        }
        Serializable serializableExtra = getIntent().getSerializableExtra("declarationBeans");
        if (serializableExtra instanceof List) {
            List list = (List) serializableExtra;
            this.e = new ArrayList(list.size());
            for (Object obj : list) {
                if (obj instanceof nyu) {
                    this.e.add((nyu) obj);
                }
            }
        }
        this.i = getIntent().getStringExtra("pairGuideSelectAddress");
        this.c = getIntent().getStringExtra("device_country_code");
        this.o = getIntent().getStringExtra("device_emui_version");
        this.g = getIntent().getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        this.q = getIntent().getIntExtra("device_version_type", -1);
        b();
    }

    private void h() {
        List<nyu> list = this.j;
        if (list == null || list.isEmpty()) {
            LogUtil.h("EnhancementImprovement", "setCheckedStatus mDeclarationBeans is empty");
            return;
        }
        Iterator<nyu> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().e(this.n);
        }
    }

    private void b() {
        LogUtil.a("EnhancementImprovement", "enhanceSize:", Integer.valueOf(nuj.e().length));
        String[] e = nuj.e();
        this.d = e;
        int length = e.length + 1;
        this.h = new ArrayList(length);
        this.j = new ArrayList(length);
        List<String> e2 = e();
        d(e2);
        Iterator<String> it = e2.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private List<String> e() {
        ArrayList arrayList = new ArrayList(this.d.length);
        Collections.addAll(arrayList, this.d);
        return arrayList;
    }

    private void d(List<String> list) {
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "EnhancementImprovement");
        if (a2 == null) {
            LogUtil.h("EnhancementImprovement", "filter deviceCapabilityMap is null");
            return;
        }
        DeviceCapability deviceCapability = a2.get(this.i);
        if (deviceCapability != null) {
            int smartWatchVersionTypeValue = deviceCapability.getSmartWatchVersionTypeValue();
            if (smartWatchVersionTypeValue == 0 || smartWatchVersionTypeValue == 1) {
                this.n = false;
                LogUtil.a("EnhancementImprovement", "in china");
                return;
            } else {
                if (smartWatchVersionTypeValue == 2 || smartWatchVersionTypeValue == 3) {
                    list.remove("smart_capabilities");
                    LogUtil.a("EnhancementImprovement", "in abroad");
                    this.n = false;
                    return;
                }
                LogUtil.a("EnhancementImprovement", "in default");
                return;
            }
        }
        LogUtil.a("EnhancementImprovement", "deviceCapability is null");
    }

    private void e(String str) {
        nzf e = nuj.e(str, this.o);
        if (!TextUtils.isEmpty(e.a())) {
            this.h.add(e);
        } else {
            LogUtil.h("EnhancementImprovement", "addDeclarationList declaration getFeatureId is empty");
        }
    }

    private void a() {
        setContentView(R.layout.activity_enhancement_improvement);
        View cMc_ = nsy.cMc_(this, R.id.agreement_declaration_text_view_back);
        if (cMc_ != null) {
            cMc_.setOnClickListener(this);
        }
        View cMc_2 = nsy.cMc_(this, R.id.agreement_declaration_text_view_next);
        if (cMc_2 != null) {
            cMc_2.setOnClickListener(this);
        }
        this.j = new nyv().c(this.h);
        h();
        e(this.j);
        this.b = new DeclarationAdapter(this, this.j, this.q);
        ListView listView = (ListView) nsy.cMc_(this, R.id.list_view_content);
        if (listView != null) {
            listView.setAdapter((ListAdapter) this.b);
        }
    }

    private void e(List<nyu> list) {
        nyu nyuVar = new nyu();
        nyuVar.d(0);
        nyuVar.f(this.f9046a.getResources().getString(R.string._2130843890_res_0x7f0218f2));
        list.add(0, nyuVar);
    }

    private void j() {
        ArrayList arrayList = new ArrayList(this.j.size() + this.e.size());
        arrayList.addAll(this.e);
        arrayList.addAll(this.j);
        LogUtil.a("EnhancementImprovement", "next:", Integer.valueOf(arrayList.size()));
        nug.e(this.i, arrayList);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.agreement_declaration_text_view_next);
        if (healthTextView != null) {
            healthTextView.setTextColor(this.f9046a.getResources().getColor(R.color._2131296687_res_0x7f0901af));
            healthTextView.setEnabled(false);
        }
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMc_(this, R.id.agreement_declaration_text_view_back);
        if (healthTextView2 != null) {
            healthTextView2.setTextColor(this.f9046a.getResources().getColor(R.color._2131296687_res_0x7f0901af));
            healthTextView2.setEnabled(false);
        }
        this.l = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.agreement_declaration_text_view_back) {
            if (!this.l) {
                LogUtil.h("EnhancementImprovement", "waiting can not back");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            finish();
        } else if (view.getId() == R.id.agreement_declaration_text_view_next) {
            if (nsn.o()) {
                LogUtil.a("EnhancementImprovement", "onClick() isFastClick");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (c()) {
                j();
            } else if (this.k) {
                j();
            } else {
                g();
            }
        } else {
            LogUtil.a("EnhancementImprovement", "onClick() the else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean c() {
        while (true) {
            boolean z = true;
            for (nyu nyuVar : this.j) {
                if (!TextUtils.isEmpty(nyuVar.d())) {
                    if (!z || !nyuVar.g()) {
                        z = false;
                    }
                }
            }
            return z;
        }
    }

    private void g() {
        View inflate = View.inflate(this.f9046a, R.layout.dialog_layout_single_choice, null);
        HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.cbx_single_choice);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.agreement.EnhancementImprovementActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Iterator it = EnhancementImprovementActivity.this.j.iterator();
                while (it.hasNext()) {
                    ((nyu) it.next()).e(z);
                }
                EnhancementImprovementActivity.this.b.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f9046a);
        builder.czg_(inflate).czf_(this.f9046a.getString(R.string._2130844420_res_0x7f021b04).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.EnhancementImprovementActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czd_(this.f9046a.getString(R.string._2130844419_res_0x7f021b03).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.EnhancementImprovementActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
        this.k = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("EnhancementImprovement", "enter notify Pair Fail");
        Intent intent = new Intent();
        intent.setAction("broadcast_receiver_user_setting");
        intent.putExtra("error_code", -1);
        intent.putExtra("pairGuideSelectAddress", this.i);
        BroadcastManagerUtil.bFH_(this.f9046a, intent, LocalBroadcast.c, true);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("EnhancementImprovement", "dispatchTouchEvent event is null");
            return false;
        }
        if (motionEvent.getAction() == 2) {
            this.m.removeMessages(1);
            this.m.sendEmptyMessageDelayed(1, 60000L);
            LogUtil.a("EnhancementImprovement", "timeout is reset");
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent == null) {
            LogUtil.h("EnhancementImprovement", "onKeyDown event is null");
            return false;
        }
        if (keyEvent.getKeyCode() != 4 || this.l) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
