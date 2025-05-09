package com.huawei.ui.device.activity.pairing;

import android.R;
import android.app.AppOpsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.DeviceListAdapter;
import defpackage.iyg;
import defpackage.jah;
import defpackage.jeg;
import defpackage.jez;
import defpackage.jfu;
import defpackage.nrh;
import defpackage.nxe;
import defpackage.oae;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class BtDialogActivity extends BaseActivity {
    private NoTitleCustomAlertDialog c;
    private CustomViewDialog d;
    private CustomViewDialog.Builder e;
    private DeviceListAdapter j;
    private ListView k;
    private HealthProgressBar l;
    private boolean n;
    private HealthTextView o;
    private nxe p;
    private String q;
    private e i = null;
    private int f = 0;
    private int g = 0;
    private Handler h = new b(this);

    /* renamed from: a, reason: collision with root package name */
    private Context f9173a = null;
    private boolean m = false;
    private d b = new d(this);

    static class d extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<BtDialogActivity> f9177a;

        d(BtDialogActivity btDialogActivity) {
            this.f9177a = new WeakReference<>(btDialogActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BtDialogActivity btDialogActivity = this.f9177a.get();
            if (btDialogActivity == null) {
                LogUtil.h("BtDialogActivity", "onReceive activity is null.");
                return;
            }
            if (intent == null) {
                LogUtil.h("BtDialogActivity", "onReceive intent is null.");
                return;
            }
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter == null) {
                    LogUtil.h("BtDialogActivity", "onReceive mAdapter is null.");
                    return;
                }
                int state = defaultAdapter.getState();
                LogUtil.a("BtDialogActivity", "onReceive btSwitchState: ", Integer.valueOf(state));
                if (state == 12) {
                    btDialogActivity.ac();
                    if (btDialogActivity.b != null) {
                        try {
                            LogUtil.a("BtDialogActivity", "onReceive unregisterReceiver.");
                            context.unregisterReceiver(btDialogActivity.b);
                            return;
                        } catch (IllegalArgumentException unused) {
                            LogUtil.b("BtDialogActivity", "onReceive unregisterReceiver IllegalArgumentException.");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("BtDialogActivity", "onReceive btSwitchState default.");
            }
        }
    }

    static class e extends IBluetoothDialogAidlCallback.Stub {
        private final WeakReference<BtDialogActivity> c;

        private e(BtDialogActivity btDialogActivity) {
            this.c = new WeakReference<>(btDialogActivity);
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onSetList(List<BluetoothDeviceNode> list, boolean z, int i) {
            LogUtil.a("BtDialogActivity", "enter onSetList.");
            BtDialogActivity btDialogActivity = this.c.get();
            if (btDialogActivity == null || btDialogActivity.j == null || btDialogActivity.h == null) {
                LogUtil.h("BtDialogActivity", "onSetList activity is null.");
                return;
            }
            if (!z) {
                Message obtain = Message.obtain();
                obtain.what = 8;
                obtain.obj = list;
                obtain.arg1 = 0;
                btDialogActivity.h.sendMessage(obtain);
                return;
            }
            Message obtain2 = Message.obtain();
            obtain2.what = 8;
            obtain2.obj = list;
            obtain2.arg1 = 1;
            obtain2.arg2 = i;
            btDialogActivity.h.sendMessage(obtain2);
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onScanFinished() {
            LogUtil.a("BtDialogActivity", "enter onScanFinished.");
            BtDialogActivity btDialogActivity = this.c.get();
            if (btDialogActivity != null && btDialogActivity.h != null) {
                btDialogActivity.h.sendEmptyMessage(10);
            } else {
                LogUtil.h("BtDialogActivity", "onScanFinished activity is null.");
            }
        }

        @Override // com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback
        public void onSetNameFilter(List list) {
            LogUtil.a("BtDialogActivity", "enter onSetNameFilter.");
            BtDialogActivity btDialogActivity = this.c.get();
            if (btDialogActivity != null && btDialogActivity.j != null && list != null) {
                LogUtil.a("BtDialogActivity", "onSetNameFilter nameFilter: ", list.toString());
                btDialogActivity.j.a((List<String>) list);
            } else {
                LogUtil.h("BtDialogActivity", "onSetNameFilter activity is null.");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "onCreate");
        this.f9173a = BaseApplication.getContext();
        this.p = new nxe();
        this.i = new e();
        Window window = getWindow();
        window.setGravity(80);
        window.clearFlags(2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        k();
    }

    private void k() {
        if (DevicePairGuideUtil.d() != null) {
            q();
        } else {
            ThreadPoolManager.d().d("BtDialogActivity", new Runnable() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("BtDialogActivity", "SCALE_SHARE_HARMONY_TIPS getValue.");
                    String e2 = jah.c().e("scale_share_harmony_tips");
                    DevicePairGuideUtil.e(e2);
                    LogUtil.a("BtDialogActivity", "SCALE_SHARE_HARMONY_TIPS: ", e2);
                    BtDialogActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BtDialogActivity.this.q();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra(TemplateStyleRecord.STYLE, 0);
            this.f = intExtra;
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "initView style: ", Integer.valueOf(intExtra), ";content: ", Integer.valueOf(intent.getIntExtra("content", 0)));
            this.q = intent.getStringExtra("hichain_late_intent_key");
            int i = this.f;
            if (i == 1) {
                this.g = intent.getIntExtra("content", 0);
                r();
            } else if (i == 3) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter == null) {
                    LogUtil.h("BtDialogActivity", "initView mAdapter is null.");
                } else {
                    LogUtil.a("BtDialogActivity", "initView mAdapter is not null.");
                    if (!defaultAdapter.isEnabled()) {
                        LogUtil.a("BtDialogActivity", "initView mAdapter closed.");
                        BroadcastManagerUtil.bFB_(this.f9173a, this.b, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                    }
                }
                c(intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -1));
            } else {
                LogUtil.h("BtDialogActivity", "initView mDialogStyle is unknown.");
            }
            setFinishOnTouchOutside(false);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = defaultDisplay.getWidth();
            getWindow().setAttributes(attributes);
            return;
        }
        LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "initView intent is null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("BtDialogActivity", "confirmButtonFunction mDialogContent: ", Integer.valueOf(this.g));
        switch (this.g) {
            case 1:
                startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                break;
            case 2:
                startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                break;
            case 3:
                e(true);
                finish();
                break;
            case 4:
                h();
                this.p.d(true);
                finish();
                break;
            case 5:
                a(false);
                finish();
                break;
            case 6:
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "confirmButtonFunction go to application set.");
                startActivityForResult(new Intent("android.settings.SETTINGS"), 2);
                break;
            case 7:
                c(this.q);
                break;
            default:
                LogUtil.h("BtDialogActivity", "confirmButtonFunction mDialogContent is unknown.");
                break;
        }
    }

    private void c(String str) {
        int i;
        LogUtil.a("BtDialogActivity", 0, "BtDialogActivity", "Enter unbindDevice.");
        if (str == null) {
            LogUtil.h("BtDialogActivity", "unbindDevice bindMacAddress is empty.");
            return;
        }
        List<DeviceInfo> j = oae.c(this.f9173a).j();
        if (j == null || j.size() == 0) {
            LogUtil.h("BtDialogActivity", "unbindDevice list is empty.");
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
            LogUtil.h("BtDialogActivity", "unbindDevice index is invalid.");
            return;
        }
        j.remove(i);
        LogUtil.a("BtDialogActivity", "unbindDevice deleteDevice.");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(this.f9173a).e(arrayList, true);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                LogUtil.h("BtDialogActivity", "enableBluetoothSwitch adapter is null.");
            } else if (!defaultAdapter.isEnabled()) {
                LogUtil.a("BtDialogActivity", "enableBluetoothSwitch bluetooth switch is close.");
                LogUtil.a("BtDialogActivity", "enableBluetoothSwitch bluetooth switch isEnable: ", Boolean.valueOf(defaultAdapter.enable()));
            }
        } catch (SecurityException e2) {
            LogUtil.b("BtDialogActivity", "enableBluetoothSwitch SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        switch (this.g) {
            case 1:
            case 3:
                e(false);
                finish();
                break;
            case 2:
                m();
                break;
            case 4:
                a(false);
                finish();
                break;
            case 5:
                m();
                break;
            case 6:
                m();
                break;
            default:
                LogUtil.h("BtDialogActivity", "cancelButtonFunction mDialogContent is unknown.");
                break;
        }
    }

    private void r() {
        String string = getString(R.string.ok);
        String string2 = getString(R.string.cancel);
        int i = this.g;
        if (i == 1) {
            string = getString(R$string.IDS_common_enable_button);
        } else if (i == 2) {
            string = getString(R$string.IDS_common_enable_button);
        } else if (i == 4) {
            string2 = getString(R$string.IDS_btsdk_confirm_repair);
            string = getString(R$string.IDS_btsdk_confirm_connect);
        } else if (i == 5) {
            string = getString(R$string.IDS_btsdk_confirm_connect);
        } else if (i == 6) {
            string = getString(R$string.IDS_settings_button_ok);
        } else if (i == 7) {
            string = getString(R$string.IDS_kn_lost_ok);
            string2 = null;
        } else {
            LogUtil.h("BtDialogActivity", "initConfirmDialog mDialogContent is unknown.");
        }
        String t = t();
        i();
        e(string, string2, t);
    }

    private String t() {
        switch (this.g) {
            case 1:
                if ("on".equals(DevicePairGuideUtil.d())) {
                    return getString(R$string.IDS_btsdk_turn_on_location_BT_harmony);
                }
                return getString(R$string.IDS_btsdk_turn_on_location_BT);
            case 2:
                if ("on".equals(DevicePairGuideUtil.d())) {
                    return getString(R$string.IDS_btsdk_turn_on_location_harmony);
                }
                return getString(R$string.IDS_btsdk_turn_on_location);
            case 3:
                return getString(R$string.IDS_btsdk_turn_on_BT);
            case 4:
                return String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_btsdk_confirm_connected_content), n());
            case 5:
                String o = o();
                return String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_btsdk_confirm_reconnect_content), o, o);
            case 6:
                return getString(R$string.IDS_btsdk_get_loacation_permiassion_health);
            case 7:
                return getString(R$string.IDS_kn_lost_content);
            default:
                LogUtil.h("BtDialogActivity", "getTipText mDialogContent is unknown.");
                return "";
        }
    }

    private void e(String str, String str2, String str3) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(str3).czE_(str, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BtDialogActivity.this.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(str2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BtDialogActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.c = e2;
        e2.setCanceledOnTouchOutside(false);
        this.c.setCancelable(false);
        this.c.show();
    }

    private void c(int i) {
        LayoutInflater layoutInflater = this.f9173a.getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) this.f9173a.getSystemService("layout_inflater") : null;
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(com.huawei.health.R.layout.dialog_listview, (ViewGroup) null);
            this.e = new CustomViewDialog.Builder(this);
            if (inflate != null) {
                this.k = (ListView) inflate.findViewById(com.huawei.health.R.id.device_list);
                this.o = (HealthTextView) inflate.findViewById(com.huawei.health.R.id.title_listview_tv);
                this.j = new DeviceListAdapter(BaseApplication.getContext());
                HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(com.huawei.health.R.id.dialog_listview_loading);
                this.l = healthProgressBar;
                healthProgressBar.setLayerType(1, null);
                this.l.setVisibility(0);
                this.k.setAdapter((ListAdapter) this.j);
                d(this.i);
                this.k.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.4
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                        BtDialogActivity.this.cRr_(view, j);
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                    }
                });
                this.e.czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BtDialogActivity.this.m();
                        BtDialogActivity.this.a();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                this.e.czh_(inflate, 0, 0);
                this.e.c(false);
            }
            if (b(i)) {
                CustomViewDialog e2 = this.e.e();
                this.d = e2;
                if (e2 != null) {
                    e2.setCancelable(false);
                    this.d.show();
                }
                ac();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRr_(View view, long j) {
        int b2 = this.p.b();
        LogUtil.a("BtDialogActivity", "connectUserClickDevice userSelectProduct :", Integer.valueOf(b2));
        if (b2 == 11) {
            d(j, b2, -1);
            return;
        }
        DeviceListAdapter.d dVar = view.getTag() instanceof DeviceListAdapter.d ? (DeviceListAdapter.d) view.getTag() : null;
        if (dVar != null) {
            String obj = dVar.d.getText().toString();
            int c = jfu.c(obj);
            LogUtil.a("BtDialogActivity", "connectUserClickDevice currentPairDeviceProductType :", Integer.valueOf(c));
            String upperCase = obj.toUpperCase(Locale.ENGLISH);
            if ((upperCase.contains("HUAWEI") || upperCase.contains("HONOR") || upperCase.contains("PORSCHE")) && ((c == -1 || !jfu.d(c, obj)) && !CommonUtil.bu())) {
                y();
            } else {
                this.p.c(obj);
                d(j, b2, c);
            }
        }
    }

    private void d(long j, int i, int i2) {
        LogUtil.a("BtDialogActivity", "Enter startConnectClickDevice.");
        if (i != i2 && i2 != -1) {
            this.p.b(i2);
        }
        if (this.j.getCount() - 1 == j) {
            nrh.e(BaseApplication.getContext(), R$string.IDS_device_mgr_device_pair_guide_tips);
        } else {
            this.p.a(j, i, i2);
        }
        m();
        a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "onActivityResult requestCode: ", Integer.valueOf(i), " ,resultCode: ", Integer.valueOf(i2));
        if (i == 1) {
            if (w() || CommonUtil.bh()) {
                finish();
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "onActivityResult showDeviceList 3");
                e(true);
            } else {
                finish();
                e(false);
            }
        } else if (i == 2) {
            if (checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                finish();
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "onActivityResult showDeviceList 4");
                a(true);
            } else {
                LogUtil.h("BtDialogActivity", "onActivityResult no permission.");
                finish();
            }
        } else {
            LogUtil.h("BtDialogActivity", "onActivityResult requestCode is other.");
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("BtDialogActivity", "Enter onDestroy.");
        this.h.removeCallbacksAndMessages(null);
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.d.dismiss();
        }
        CommonUtil.a(BaseApplication.getContext());
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.c.dismiss();
        }
        if (this.b != null) {
            try {
                LogUtil.a("BtDialogActivity", "onDestroy onReceive unregisterReceiver");
                unregisterReceiver(this.b);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("BtDialogActivity", "onDestroy unregisterReceiver occur IllegalArgumentException.");
            }
        }
        if (this.i != null) {
            this.i = null;
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.g == 3) {
            e(false);
            finish();
        } else {
            m();
        }
        if (this.f == 3) {
            LogUtil.a("BtDialogActivity", "onBackPressed back cancel.");
            c();
        }
    }

    class b extends Handler {
        private WeakReference<BtDialogActivity> b;

        b(BtDialogActivity btDialogActivity) {
            this.b = new WeakReference<>(btDialogActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            BtDialogActivity btDialogActivity = this.b.get();
            if (btDialogActivity == null) {
                LogUtil.h("BtDialogActivity", "handleMessage activity is null.");
            }
            if (message == null) {
                LogUtil.h("BtDialogActivity", "handleMessage msg is null.");
                return;
            }
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "handleMessage receive msg : ", Integer.valueOf(message.what));
            switch (message.what) {
                case 8:
                    if (message.arg1 == 0 && (message.obj instanceof List)) {
                        BtDialogActivity.this.j.b((List<BluetoothDeviceNode>) message.obj);
                        break;
                    } else if (message.arg1 == 1 && (message.obj instanceof List)) {
                        BtDialogActivity.this.j.a((List) message.obj, message.arg2);
                        break;
                    } else {
                        LogUtil.a("BtDialogActivity", "handleMessage set list branch");
                        break;
                    }
                    break;
                case 9:
                    BtDialogActivity.l();
                    break;
                case 10:
                    if (!btDialogActivity.isFinishing()) {
                        btDialogActivity.l.setVisibility(8);
                        if (btDialogActivity.o != null) {
                            btDialogActivity.o.setText(R$string.IDS_device_mgr_device_scan_completed_title);
                            break;
                        }
                    }
                    break;
                default:
                    LogUtil.h("BtDialogActivity", "MyHandler handleMessage is error.");
                    break;
            }
        }
    }

    private boolean b(int i) {
        this.n = true;
        if (i != 2 && i != 1 && i != 5) {
            return false;
        }
        if (!p()) {
            if (i == 1 && CommonUtil.bh() && !PermissionUtil.c()) {
                return true;
            }
            s();
        }
        return this.n;
    }

    private void s() {
        e();
        this.m = true;
        this.n = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (isFinishing()) {
            return;
        }
        PermissionUtil.b(this, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(this) { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.7
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("BtDialogActivity", "applyLocationPermission onGranted permission allowed.");
                BtDialogActivity.this.n = true;
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                super.onDenied(str);
                BtDialogActivity.this.e();
                BtDialogActivity.this.n = false;
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                if (!BtDialogActivity.this.isFinishing()) {
                    super.onForeverDenied(permissionType, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.7.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            BtDialogActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.7.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            BtDialogActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }
                BtDialogActivity.this.n = false;
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (iArr == null || strArr == null) {
            LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "grant result grantResults or permissions is null");
            return;
        }
        jeg.d().d(strArr, iArr);
        if (i != 1) {
            if (i == 20192) {
                if (p() && this.m) {
                    LogUtil.a("BtDialogActivity", "The location permission already exists and can continue scan.");
                    g();
                    return;
                } else {
                    finish();
                    return;
                }
            }
            LogUtil.h("BtDialogActivity", "onRequestPermissionsResult error.");
            return;
        }
        if (iArr.length > 0) {
            boolean z = iArr[0] == 0;
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "grant result: ", Boolean.valueOf(z));
            if (z) {
                u();
                return;
            } else {
                finish();
                v();
                return;
            }
        }
        LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "grantResults is null or length is incorrect.");
    }

    private void g() {
        LogUtil.a("BtDialogActivity", "Enter continueScanDevices.");
        this.m = false;
        CustomViewDialog e2 = this.e.e();
        this.d = e2;
        if (e2 != null) {
            e2.setCancelable(false);
            this.d.show();
        }
        ac();
    }

    private void u() {
        CustomViewDialog.Builder builder;
        if (x()) {
            CustomViewDialog customViewDialog = this.d;
            if (customViewDialog == null) {
                LogUtil.h("BtDialogActivity", "setLocationEnable mCustomViewDialog is null");
            } else {
                LogUtil.a("BtDialogActivity", "setLocationEnable mCustomViewDialog isShowing: ", Boolean.valueOf(customViewDialog.isShowing()));
            }
            if (this.d == null && (builder = this.e) != null) {
                this.d = builder.e();
            }
            CustomViewDialog customViewDialog2 = this.d;
            if (customViewDialog2 != null && !customViewDialog2.isShowing()) {
                this.d.setCancelable(false);
                this.d.show();
            }
            ac();
            return;
        }
        finish();
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        j();
        finish();
    }

    private boolean x() {
        int checkOp = ((AppOpsManager) getSystemService("appops")).checkOp("android:coarse_location", Process.myUid(), getPackageName());
        LogUtil.a("BtDialogActivity", "isLocationEnable checkOp: ", Integer.valueOf(checkOp));
        boolean z = checkOp == 0;
        LogUtil.a("BtDialogActivity", "isLocationEnable res:", Boolean.valueOf(z), "0：allowed other：no permission.");
        return z;
    }

    private boolean p() {
        return PermissionUtil.e(this.f9173a, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
    }

    private boolean w() {
        boolean z;
        boolean z2;
        LocationManager locationManager = getSystemService("location") instanceof LocationManager ? (LocationManager) getSystemService("location") : null;
        if (locationManager != null) {
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "isGpsLocationEnable isGpsEnable: ", Boolean.valueOf(z2));
            if (iyg.e() || iyg.b()) {
                return z2;
            }
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDialogActivity", "isGpsLocationEnable isNetWorkEnable: ", Boolean.valueOf(z));
        } else {
            z = true;
            z2 = true;
        }
        return z2 || z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("BtDialogActivity", "Enter cancelScanDevice.");
        c();
    }

    private void i() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        this.c.dismiss();
    }

    private void c() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10003);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "cancelBluetoothDeviceDiscovery RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "cancelBluetoothDeviceDiscovery binder is null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10004);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "startScanDevices RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "startScanDevices binder is null.");
    }

    private void e(boolean z) {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10006);
                deviceDialogMessage.setIsStatusFlag(z);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "dialogEnableBluetoothSwitch RemoteException");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "dialogEnableBluetoothSwitch binder is null.");
    }

    private void h() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10001);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "connectHfpConnectedDevice RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "connectHfpConnectedDevice binder is null.");
    }

    private void a(boolean z) {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10002);
                deviceDialogMessage.setIsStatusFlag(z);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "showDeviceList RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "showDeviceList binder is null.");
    }

    private String n() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10007);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                return e2.dialogMessage(deviceDialogMessage, null);
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "getHfpConnectedDeviceName RemoteException.");
            }
        } else {
            LogUtil.h("BtDialogActivity", "getHfpConnectedDeviceName binder is null.");
        }
        return "";
    }

    private String o() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10008);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                return e2.dialogMessage(deviceDialogMessage, null);
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "getAddDeviceName RemoteException.");
            }
        } else {
            LogUtil.h("BtDialogActivity", "getAddDeviceName binder is null.");
        }
        return "";
    }

    private void v() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10015);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "showPermissionDialog RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "showPermissionDialog binder is null.");
    }

    private void j() {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10016);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(null);
                e2.dialogMessage(deviceDialogMessage, null);
                return;
            } catch (RemoteException unused) {
                LogUtil.b("BtDialogActivity", "dialogEnableScan RemoteException.");
                return;
            }
        }
        LogUtil.h("BtDialogActivity", "dialogEnableScan binder is null.");
    }

    private void d(e eVar) {
        if (eVar instanceof IBluetoothDialogAidlCallback) {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                try {
                    DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                    deviceDialogMessage.setMethod(PrebakedEffectId.RT_DRAWING_ARROW);
                    deviceDialogMessage.setIsStatusFlag(false);
                    deviceDialogMessage.setSelectId(0L);
                    deviceDialogMessage.setDeviceName(null);
                    e2.dialogMessage(deviceDialogMessage, eVar);
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("BtDialogActivity", "registerCallback RemoteException.");
                    return;
                }
            }
            LogUtil.h("BtDialogActivity", "registerCallback binder is null.");
        }
    }

    private void y() {
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.d.dismiss();
        }
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("BtDialogActivity", "showNoSupportDevicePairDialog activity is destroy.");
            return;
        }
        View inflate = View.inflate(this, com.huawei.health.R.layout.commonui_no_support_device_pair_dialog, null);
        ((HealthTextView) inflate.findViewById(com.huawei.health.R.id.dialog_message_title)).setText(getResources().getString(R$string.IDS_common_failed_content));
        ((HealthTextView) inflate.findViewById(com.huawei.health.R.id.dialog_message_one)).setText(BaseApplication.getContext().getString(R$string.IDS_common_failed_content_one, 1));
        ((HealthTextView) inflate.findViewById(com.huawei.health.R.id.dialog_message_two)).setText(BaseApplication.getContext().getString(R$string.IDS_common_failed_content_two, 2));
        CustomViewDialog e2 = new CustomViewDialog.Builder(this).a(getResources().getString(R$string.IDS_watchface_note)).czg_(inflate).czf_(getResources().getString(R$string.IDS_common_notification_know_tips), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.BtDialogActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BtDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
