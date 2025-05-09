package com.huawei.ui.device.activity.pairing;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.device.activity.pairing.DeviceListAdapter;
import defpackage.iyl;
import defpackage.jah;
import defpackage.jeg;
import defpackage.jfu;
import defpackage.nrh;
import defpackage.nxe;
import defpackage.nxf;
import defpackage.oae;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class HwBtDialogActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CustomViewDialog.Builder f9195a;
    private iyl b;
    private Context d;
    private DialogAidlCallback e;
    private NoTitleCustomAlertDialog f;
    private CustomViewDialog g;
    private DeviceListAdapter h;
    private String j;
    private TextView k;
    private nxf l;
    private int n;
    private int o;
    private nxe p;
    private ListView q;
    private HealthProgressBar r;
    private DeviceParameter i = null;
    private Handler m = new b(this);
    private a c = new a(this);

    class a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<HwBtDialogActivity> f9197a;

        a(HwBtDialogActivity hwBtDialogActivity) {
            this.f9197a = new WeakReference<>(hwBtDialogActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HwBtDialogActivity hwBtDialogActivity = this.f9197a.get();
            if (hwBtDialogActivity == null) {
                LogUtil.h("HwBtDialogActivity", "bluetooth switch onReceive activity is null.");
                return;
            }
            if (intent == null) {
                LogUtil.h("HwBtDialogActivity", "bluetooth switch onReceive intent is null.");
                return;
            }
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter == null) {
                    LogUtil.h("HwBtDialogActivity", "bluetooth switch onReceive mAdapter is null.");
                    return;
                }
                int state = defaultAdapter.getState();
                LogUtil.a("HwBtDialogActivity", "bluetooth switch onReceive btSwitchState: ", Integer.valueOf(state));
                if (state == 12) {
                    HwBtDialogActivity.this.l.f();
                    if (hwBtDialogActivity.c != null) {
                        try {
                            LogUtil.a("HwBtDialogActivity", "bluetooth switch onReceive unregisterReceiver.");
                            context.unregisterReceiver(hwBtDialogActivity.c);
                            return;
                        } catch (IllegalArgumentException unused) {
                            LogUtil.b("HwBtDialogActivity", "bluetooth switch unregisterReceiver IllegalArgumentException.");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("HwBtDialogActivity", "onReceive btSwitchState default.");
            }
        }
    }

    public static class DialogAidlCallback extends IBluetoothDialogAidlCallback.Stub {
        private final WeakReference<HwBtDialogActivity> e;

        private DialogAidlCallback(HwBtDialogActivity hwBtDialogActivity) {
            this.e = new WeakReference<>(hwBtDialogActivity);
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onSetList(List<BluetoothDeviceNode> list, boolean z, int i) {
            LogUtil.a("HwBtDialogActivity", "Enter onSetList.");
            HwBtDialogActivity hwBtDialogActivity = this.e.get();
            if (hwBtDialogActivity == null || hwBtDialogActivity.h == null || hwBtDialogActivity.m == null) {
                LogUtil.h("HwBtDialogActivity", "onSetList activity is null.");
                return;
            }
            if (!z) {
                Message obtain = Message.obtain();
                obtain.what = 8;
                obtain.obj = list;
                obtain.arg1 = 0;
                hwBtDialogActivity.m.sendMessage(obtain);
                return;
            }
            Message obtain2 = Message.obtain();
            obtain2.what = 8;
            obtain2.obj = list;
            obtain2.arg1 = 1;
            obtain2.arg2 = i;
            hwBtDialogActivity.m.sendMessage(obtain2);
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onScanFinished() {
            LogUtil.a("HwBtDialogActivity", "Enter onScanFinished.");
            HwBtDialogActivity hwBtDialogActivity = this.e.get();
            if (hwBtDialogActivity != null && hwBtDialogActivity.m != null) {
                hwBtDialogActivity.m.sendEmptyMessage(10);
            } else {
                LogUtil.h("HwBtDialogActivity", "onScanFinished activity is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onSetNameFilter(List list) {
            LogUtil.a("HwBtDialogActivity", "Enter onSetNameFilter.");
            HwBtDialogActivity hwBtDialogActivity = this.e.get();
            if (hwBtDialogActivity != null && hwBtDialogActivity.h != null && list != null) {
                LogUtil.a("HwBtDialogActivity", "onSetNameFilter nameFilter: ", list.toString());
                hwBtDialogActivity.h.a((List<String>) list);
            } else {
                LogUtil.h("HwBtDialogActivity", "onSetNameFilter activity is null.");
            }
        }
    }

    class b extends Handler {
        private WeakReference<HwBtDialogActivity> c;

        b(HwBtDialogActivity hwBtDialogActivity) {
            this.c = new WeakReference<>(hwBtDialogActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            HwBtDialogActivity hwBtDialogActivity = this.c.get();
            if (hwBtDialogActivity == null) {
                LogUtil.h("HwBtDialogActivity", "handleMessage activity is null.");
                return;
            }
            if (message == null) {
                LogUtil.h("HwBtDialogActivity", "handleMessage msg is null.");
                return;
            }
            LogUtil.a("HwBtDialogActivity", "handleMessage receive msg: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 8) {
                if (i == 10) {
                    if (hwBtDialogActivity.isFinishing()) {
                        return;
                    }
                    hwBtDialogActivity.r.setVisibility(8);
                    if (hwBtDialogActivity.k != null) {
                        hwBtDialogActivity.k.setText(R.string.IDS_device_mgr_device_scan_completed_title);
                        return;
                    }
                    return;
                }
                LogUtil.h("HwBtDialogActivity", "MyHandler handleMessage is error.");
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            if (message.arg1 == 0 && (message.obj instanceof List)) {
                arrayList.addAll((List) message.obj);
                HwBtDialogActivity.this.h.b(arrayList);
            } else if (message.arg1 == 1 && (message.obj instanceof List)) {
                arrayList.addAll((List) message.obj);
                HwBtDialogActivity.this.h.a(arrayList, message.arg2);
            } else {
                LogUtil.h("HwBtDialogActivity", "enter set list branch");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("HwBtDialogActivity", "onCreate");
        this.d = BaseApplication.getContext();
        this.p = new nxe();
        iyl d = iyl.d();
        this.b = d;
        d.c(this.d);
        this.e = new DialogAidlCallback();
        nxf c = nxf.c(this.d);
        this.l = c;
        c.e(this.b);
        this.l.a(this.e);
        Window window = getWindow();
        window.setGravity(80);
        window.clearFlags(2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        f();
    }

    private void f() {
        if (DevicePairGuideUtil.d() != null) {
            k();
        } else {
            ThreadPoolManager.d().d("HwBtDialogActivity", new Runnable() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("HwBtDialogActivity", "SCALE_SHARE_HARMONY_TIPS getValue");
                    String e = jah.c().e("scale_share_harmony_tips");
                    DevicePairGuideUtil.e(e);
                    LogUtil.a("HwBtDialogActivity", "SCALE_SHARE_HARMONY_TIPS: ", e);
                    HwBtDialogActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            HwBtDialogActivity.this.k();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Intent intent = getIntent();
        if (intent != null) {
            this.o = intent.getIntExtra(TemplateStyleRecord.STYLE, 0);
            if (intent.getParcelableExtra("device_parameter") instanceof DeviceParameter) {
                this.i = (DeviceParameter) intent.getParcelableExtra("device_parameter");
            }
            this.j = intent.getStringExtra("hichain_late_intent_key");
            LogUtil.a("HwBtDialogActivity", "initView style:", Integer.valueOf(this.o), ";content:", Integer.valueOf(intent.getIntExtra("content", 0)));
            this.l.d(this.i);
            int i = this.o;
            if (i == 1) {
                this.n = intent.getIntExtra("content", 0);
                i();
            } else if (i == 3) {
                d();
                g();
            }
            setFinishOnTouchOutside(false);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = defaultDisplay.getWidth();
            getWindow().setAttributes(attributes);
            return;
        }
        LogUtil.h("HwBtDialogActivity", "initView intent is null.");
    }

    private void d() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            LogUtil.h("HwBtDialogActivity", "initView mAdapter is null.");
            return;
        }
        LogUtil.a("HwBtDialogActivity", "initView mAdapter is not null.");
        if (defaultAdapter.isEnabled()) {
            return;
        }
        LogUtil.a("HwBtDialogActivity", "initView mAdapter closed.");
        BroadcastManagerUtil.bFB_(this.d, this.c, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    private void i() {
        String string = getString(android.R.string.ok);
        String string2 = getString(android.R.string.cancel);
        int i = this.n;
        if (i == 1) {
            string = getString(R.string._2130842176_res_0x7f021240);
        } else if (i == 2) {
            string = getString(R.string._2130842176_res_0x7f021240);
        } else if (i == 4) {
            string2 = getString(R.string._2130843255_res_0x7f021677);
            string = getString(R.string._2130843256_res_0x7f021678);
        } else if (i == 5) {
            string = getString(R.string._2130843256_res_0x7f021678);
        } else if (i == 6) {
            string = getString(R.string._2130841131_res_0x7f020e2b);
        } else if (i == 7) {
            string = getString(R.string._2130843855_res_0x7f0218cf);
            string2 = null;
        } else {
            LogUtil.h("HwBtDialogActivity", "initConfirmDialog mDialogContent is unknown.");
        }
        String j = j();
        e();
        d(string, string2, j);
    }

    private String j() {
        int i = this.n;
        if (i == 1) {
            if ("on".equals(DevicePairGuideUtil.d())) {
                return getString(R.string._2130843754_res_0x7f02186a);
            }
            return getString(R.string._2130843257_res_0x7f021679);
        }
        if (i == 2) {
            if ("on".equals(DevicePairGuideUtil.d())) {
                return getString(R.string._2130843755_res_0x7f02186b);
            }
            return getString(R.string._2130843258_res_0x7f02167a);
        }
        if (i == 3) {
            return getString(R.string._2130843262_res_0x7f02167e);
        }
        if (i == 4) {
            return String.format(Locale.ROOT, getResources().getString(R.string._2130843259_res_0x7f02167b), nxf.c(this.d).a());
        }
        if (i == 6) {
            return getString(R.string._2130843261_res_0x7f02167d);
        }
        if (i == 7) {
            return getString(R.string._2130843854_res_0x7f0218ce);
        }
        LogUtil.h("HwBtDialogActivity", "getTipText mDialogContent is unknown.");
        return "";
    }

    private void e() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.f;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        this.f.dismiss();
    }

    private void d(String str, String str2, String str3) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(str3).czE_(str, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwBtDialogActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(str2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwBtDialogActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.f = e;
        e.setCanceledOnTouchOutside(false);
        this.f.setCancelable(false);
        this.f.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("HwBtDialogActivity", "Enter confirmButtonFunction mDialogContent: ", Integer.valueOf(this.n));
        int i = this.n;
        if (i == 1 || i == 2) {
            startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
            return;
        }
        if (i == 3) {
            nxf.c(this.d).b(true);
            finish();
            return;
        }
        if (i == 4) {
            nxf.c(this.d).d();
            this.p.d(true);
            finish();
        } else if (i == 6) {
            LogUtil.a("HwBtDialogActivity", "confirmButtonFunction go to application set");
            startActivityForResult(new Intent("android.settings.SETTINGS"), 2);
        } else if (i == 7) {
            b(this.j);
        } else {
            LogUtil.h("HwBtDialogActivity", "confirmButtonFunction mDialogContent is unknown.");
        }
    }

    private void b(String str) {
        int i;
        LogUtil.a("HwBtDialogActivity", "Enter unbindDevice.");
        if (str == null) {
            LogUtil.h("HwBtDialogActivity", "unbindDevice deviceIdentify is null.");
            return;
        }
        List<DeviceInfo> j = oae.c(this.d).j();
        if (j == null || j.size() == 0) {
            LogUtil.h("HwBtDialogActivity", "unbindDevice list is empty.");
            return;
        }
        Iterator<DeviceInfo> it = j.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceIdentify().equals(str)) {
                i = j.indexOf(next);
                break;
            }
        }
        if (i == -1) {
            LogUtil.h("HwBtDialogActivity", "unbindDevice index is invalid.");
            return;
        }
        j.remove(i);
        LogUtil.a("HwBtDialogActivity", "deleteDevice");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(this.d).e(arrayList, true);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int i = this.n;
        if (i != 1) {
            if (i == 2) {
                h();
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    c(false);
                    finish();
                    return;
                } else if (i == 6) {
                    h();
                    return;
                } else {
                    LogUtil.h("HwBtDialogActivity", "cancelButtonFunction mDialogContent is unknown.");
                    return;
                }
            }
        }
        nxf.c(this.d).b(false);
        finish();
    }

    private void c(boolean z) {
        if (this.i == null) {
            LogUtil.h("HwBtDialogActivity", "showDeviceList mDeviceParameter is null.");
            return;
        }
        LogUtil.a("HwBtDialogActivity", "showDeviceList isBrTip: ", Boolean.valueOf(z));
        if (this.i.getBluetoothType() == 1 && z && this.b.d(this.i.getNameFilter())) {
            LogUtil.a("HwBtDialogActivity", "showDeviceList has wanted BR device.");
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.HwBtDialogActivity"));
                intent.setFlags(268435456);
                Bundle bundle = new Bundle();
                bundle.putInt(TemplateStyleRecord.STYLE, 1);
                bundle.putInt("content", 4);
                bundle.putParcelable("device_parameter", this.i);
                intent.putExtras(bundle);
                this.d.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                LogUtil.b("HwBtDialogActivity", "ActivityNotFoundException e:", e.getMessage());
                return;
            }
        }
        LogUtil.a("HwBtDialogActivity", "Clear device scan list.");
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.HwBtDialogActivity"));
        intent2.setFlags(268435456);
        Bundle bundle2 = new Bundle();
        bundle2.putInt(TemplateStyleRecord.STYLE, 3);
        bundle2.putParcelable("device_parameter", this.i);
        intent2.putExtras(bundle2);
        try {
            this.d.startActivity(intent2);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwBtDialogActivity", "start ActivityNotFoundException.");
        } catch (SecurityException unused2) {
            LogUtil.b("HwBtDialogActivity", "start BtDialogActivitySecurityException.");
        }
    }

    private void g() {
        View inflate = this.d.getSystemService("layout_inflater") instanceof LayoutInflater ? ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.dialog_listview, (ViewGroup) null) : null;
        this.f9195a = new CustomViewDialog.Builder(this);
        if (inflate != null) {
            this.q = (ListView) inflate.findViewById(R.id.device_list);
            this.k = (TextView) inflate.findViewById(R.id.title_listview_tv);
            this.h = new DeviceListAdapter(BaseApplication.getContext());
            HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.dialog_listview_loading);
            this.r = healthProgressBar;
            healthProgressBar.setLayerType(1, null);
            this.r.setVisibility(0);
            this.q.setAdapter((ListAdapter) this.h);
            this.q.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    HwBtDialogActivity.this.cRO_(view, j);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            });
            this.f9195a.czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HwBtDialogActivity.this.h();
                    HwBtDialogActivity.this.b.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f9195a.czh_(inflate, 0, 0);
            this.f9195a.c(false);
        }
        if (this.l.b(this)) {
            CustomViewDialog e = this.f9195a.e();
            this.g = e;
            if (e != null) {
                e.setCancelable(false);
                this.g.show();
            }
            this.l.f();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (iArr == null || strArr == null) {
            LogUtil.h("HwBtDialogActivity", "grant result grantResults or permissions is null.");
            return;
        }
        jeg.d().d(strArr, iArr);
        if (i != 1) {
            if (i == 20192) {
                if (this.l.e() && this.l.j()) {
                    LogUtil.a("HwBtDialogActivity", "The location permission already exists and can continue scan.");
                    c();
                    return;
                } else {
                    finish();
                    return;
                }
            }
            LogUtil.h("HwBtDialogActivity", "onRequestPermissionsResult error.");
            return;
        }
        if (iArr.length > 0) {
            boolean z = iArr[0] == 0;
            LogUtil.a("HwBtDialogActivity", "grant isLocationEnable: ", Boolean.valueOf(z));
            if (z) {
                o();
                return;
            } else {
                finish();
                m();
                return;
            }
        }
        LogUtil.h("HwBtDialogActivity", "grantResults is null or length is incorrect.");
    }

    private void m() {
        LogUtil.a("HwBtDialogActivity", "Enter showPermissionDialog.");
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.HwBtDialogActivity"));
            intent.setFlags(268435456);
            Bundle bundle = new Bundle();
            bundle.putInt(TemplateStyleRecord.STYLE, 1);
            bundle.putInt("content", 6);
            bundle.putParcelable("device_parameter", this.i);
            intent.putExtras(bundle);
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("HwBtDialogActivity", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private void c() {
        LogUtil.a("HwBtDialogActivity", "Enter continueScanDevices.");
        this.l.a(false);
        CustomViewDialog e = this.f9195a.e();
        this.g = e;
        if (e != null) {
            e.setCancelable(false);
            this.g.show();
        }
        this.l.f();
    }

    private void o() {
        CustomViewDialog.Builder builder;
        if (this.l.i()) {
            CustomViewDialog customViewDialog = this.g;
            if (customViewDialog == null) {
                LogUtil.h("HwBtDialogActivity", "setLocationEnable mCustomViewDialog is null");
            } else {
                LogUtil.a("HwBtDialogActivity", "setLocationEnable mCustomViewDialog isShowing: ", Boolean.valueOf(customViewDialog.isShowing()));
            }
            if (this.g == null && (builder = this.f9195a) != null) {
                this.g = builder.e();
            }
            CustomViewDialog customViewDialog2 = this.g;
            if (customViewDialog2 != null && !customViewDialog2.isShowing()) {
                this.g.setCancelable(false);
                this.g.show();
            }
            this.l.f();
            return;
        }
        finish();
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.l.b();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRO_(View view, long j) {
        int b2 = this.p.b();
        LogUtil.a("HwBtDialogActivity", "connectUserClickDevice userSelectProduct: ", Integer.valueOf(b2));
        if (b2 == 11) {
            e(j, b2, -1);
            return;
        }
        String str = null;
        DeviceListAdapter.d dVar = view.getTag() instanceof DeviceListAdapter.d ? (DeviceListAdapter.d) view.getTag() : null;
        if (dVar != null && (dVar.d.getText() instanceof String)) {
            str = (String) dVar.d.getText();
        }
        int c = jfu.c(str);
        LogUtil.a("HwBtDialogActivity", "connectUserClickDevice currentPairDeviceProductType: ", Integer.valueOf(c));
        if (str != null) {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            if ((upperCase.contains("HUAWEI") || upperCase.contains("HONOR") || upperCase.contains("PORSCHE")) && ((c == -1 || !jfu.d(c, str)) && !CommonUtil.bu())) {
                n();
                return;
            }
        }
        this.p.c(str);
        e(j, b2, c);
    }

    private void n() {
        CustomViewDialog customViewDialog = this.g;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.g.dismiss();
        }
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("HwBtDialogActivity", "showNoSupportDevicePairDialog activity is destroy.");
            return;
        }
        View inflate = View.inflate(this, R.layout.commonui_no_support_device_pair_dialog, null);
        ((TextView) inflate.findViewById(R.id.dialog_message_title)).setText(getResources().getString(R.string._2130844063_res_0x7f02199f));
        ((TextView) inflate.findViewById(R.id.dialog_message_one)).setText(BaseApplication.getContext().getString(R.string._2130844064_res_0x7f0219a0, 1));
        ((TextView) inflate.findViewById(R.id.dialog_message_two)).setText(BaseApplication.getContext().getString(R.string._2130844065_res_0x7f0219a1, 2));
        CustomViewDialog e = new CustomViewDialog.Builder(this).a(getResources().getString(R.string._2130843086_res_0x7f0215ce)).czg_(inflate).czf_(getResources().getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.HwBtDialogActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwBtDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private void e(long j, int i, int i2) {
        LogUtil.a("HwBtDialogActivity", "Enter startConnectClickDevice.");
        if (i != i2 && i2 != -1) {
            this.p.b(i2);
        }
        if (this.h.getCount() - 1 == j) {
            nrh.e(BaseApplication.getContext(), R.string.IDS_device_mgr_device_pair_guide_tips);
        } else {
            this.p.a(j, i, i2);
        }
        h();
        this.b.b();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("HwBtDialogActivity", "BT_GPS onActivityResult requestCode:", Integer.valueOf(i), "resultCode:", Integer.valueOf(i2));
        if (i == 1) {
            LogUtil.a("HwBtDialogActivity", "BT_GPS showDeviceList 3");
            if (this.l.h()) {
                finish();
                LogUtil.a("HwBtDialogActivity", "BT_GPS open");
                nxf.c(this.d).d("");
                return;
            } else {
                finish();
                this.l.b(false);
                return;
            }
        }
        if (i == 2) {
            if (checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                finish();
                LogUtil.a("HwBtDialogActivity", "BT_GPS showDeviceList 4");
                c(true);
                return;
            } else {
                LogUtil.h("HwBtDialogActivity", "no permission.");
                finish();
                return;
            }
        }
        LogUtil.h("HwBtDialogActivity", "requestCode is other.");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("HwBtDialogActivity", "onDestroy");
        this.m.removeCallbacksAndMessages(null);
        CustomViewDialog customViewDialog = this.g;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.g.dismiss();
        }
        CommonUtil.a(BaseApplication.getContext());
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.f;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.f.dismiss();
        }
        if (this.c != null) {
            try {
                LogUtil.a("HwBtDialogActivity", "onReceive unregisterReceiver.");
                unregisterReceiver(this.c);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("HwBtDialogActivity", "unregisterReceiver occur IllegalArgumentException.");
            }
        }
        if (this.e != null) {
            this.e = null;
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.n == 3) {
            nxf.c(this.d).b(false);
            finish();
        } else {
            h();
        }
        if (this.o == 3) {
            LogUtil.a("HwBtDialogActivity", "back cancel.");
            this.b.b();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
