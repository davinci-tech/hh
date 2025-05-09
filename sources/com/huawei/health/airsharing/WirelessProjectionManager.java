package com.huawei.health.airsharing;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.airsharing.WirelessProjectionManager;
import com.huawei.health.airsharing.helper.AirSharingHelper;
import com.huawei.health.airsharing.ui.DeviceListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cub;
import defpackage.ixx;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes3.dex */
public class WirelessProjectionManager {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2187a;
    private HealthTextView b;
    private DeviceListAdapter c;
    private Context d;
    private AirSharingHelper e;
    private CustomViewDialog f;
    private CustomTextAlertDialog g;
    private CustomViewDialog h;
    private CustomViewDialog i;
    private CustomTextAlertDialog j;
    private CustomTextAlertDialog k;
    private AnimationDrawable n;
    private Handler o;
    private LinearLayout s;
    private ListView t;
    private int p = 0;
    private boolean m = false;
    private boolean l = false;

    static /* synthetic */ int b(WirelessProjectionManager wirelessProjectionManager, int i) {
        int i2 = wirelessProjectionManager.p + i;
        wirelessProjectionManager.p = i2;
        return i2;
    }

    public WirelessProjectionManager(Context context) {
        this.d = context;
        AirSharingHelper e2 = AirSharingHelper.e();
        this.e = e2;
        e2.e(BaseApplication.e());
        this.e.e(new a());
        this.o = new e(Looper.getMainLooper(), this);
    }

    public void b() {
        Context context = this.d;
        if (context == null) {
            LogUtil.h("Distribute_WirelessProjectionManager", "WirelessProjectionManager not init.");
            return;
        }
        if (cub.h(context)) {
            String b = SharedPreferenceManager.b(this.d, Integer.toString(1019), "projection_last_device_id");
            LogUtil.a("Distribute_WirelessProjectionManager", "lastDeviceId:", b);
            if (TextUtils.isEmpty(b)) {
                return;
            }
            this.o.sendMessageDelayed(this.o.obtainMessage(1006, b), 1000L);
        }
    }

    public int f() {
        LogUtil.a("Distribute_WirelessProjectionManager", "startWirelessProjection.");
        this.l = true;
        Context context = this.d;
        if (context == null) {
            LogUtil.h("Distribute_WirelessProjectionManager", "WirelessProjectionManager not init.");
            return 4;
        }
        if (!cub.h(context)) {
            CustomTextAlertDialog customTextAlertDialog = this.k;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                return 3;
            }
            a();
            return 3;
        }
        if (this.e.g()) {
            c(this.e.f());
            return 1;
        }
        return g();
    }

    public void e() {
        LogUtil.a("Distribute_WirelessProjectionManager", "destroy.");
        AirSharingHelper airSharingHelper = this.e;
        if (airSharingHelper != null) {
            airSharingHelper.d();
        }
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.o = null;
        }
        this.d = null;
    }

    public void a(boolean z) {
        AirSharingHelper airSharingHelper = this.e;
        if (airSharingHelper != null) {
            airSharingHelper.a(z);
        }
    }

    public void d(boolean z) {
        LogUtil.a("Distribute_WirelessProjectionManager", "setIsCanPreSearch:", Boolean.valueOf(z));
        this.e.d(z);
    }

    public void b(boolean z) {
        this.m = z;
    }

    static class e extends BaseHandler<WirelessProjectionManager> {
        private e(Looper looper, WirelessProjectionManager wirelessProjectionManager) {
            super(looper, wirelessProjectionManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: Co_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WirelessProjectionManager wirelessProjectionManager, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 1006:
                        WirelessProjectionManager.Cm_(wirelessProjectionManager, message);
                        break;
                    case 1007:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_HIDE_SCAN_DEVICES_DIALOG");
                        if (wirelessProjectionManager.h != null && wirelessProjectionManager.h.isShowing()) {
                            wirelessProjectionManager.h.dismiss();
                            wirelessProjectionManager.j();
                            break;
                        }
                        break;
                    case 1008:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_SHOW_DISPLAY_DEVICES_DIALOG");
                        wirelessProjectionManager.d();
                        break;
                    case 1009:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_ADD_DISPLAY_DEVICE_DIALOG");
                        wirelessProjectionManager.Ck_(message);
                        break;
                    case 1010:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_REMOVE_DISPLAY_DEVICE_DIALOG");
                        wirelessProjectionManager.Cn_(message);
                        break;
                    case 1011:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_SHOW_CONNECT_DEVICE_DIALOG");
                        wirelessProjectionManager.Cl_(message);
                        break;
                    case 1012:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_HIDE_CONNECT_DEVICE_DIALOG");
                        if (wirelessProjectionManager.i != null && wirelessProjectionManager.i.isShowing()) {
                            wirelessProjectionManager.n.stop();
                            wirelessProjectionManager.i.dismiss();
                            wirelessProjectionManager.j();
                            break;
                        }
                        break;
                    case 1013:
                        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_SHOW_CONNECT_DEVICE_FAILED_DIALOG");
                        if (wirelessProjectionManager.j == null || !wirelessProjectionManager.j.isShowing()) {
                            wirelessProjectionManager.c();
                            break;
                        }
                        break;
                }
            }
            LogUtil.h("Distribute_WirelessProjectionManager", "in createInstanceOfHandler, no msg is received.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Cm_(WirelessProjectionManager wirelessProjectionManager, Message message) {
        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, case is MSG_START_PROJECTION_DEVICE_PRE_SEARCH");
        String str = (String) message.obj;
        wirelessProjectionManager.e.d(true);
        wirelessProjectionManager.e.e(str);
    }

    private int g() {
        if (this.e.h()) {
            LogUtil.a("Distribute_WirelessProjectionManager", "mIsLastDeviceExist is true, connectToDevice");
            CustomTextAlertDialog customTextAlertDialog = this.g;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                return 2;
            }
            b(this.e.b().getDeviceName());
            return 2;
        }
        CustomViewDialog customViewDialog = this.h;
        if (customViewDialog == null || !customViewDialog.isShowing()) {
            if (this.e.j()) {
                this.e.o();
            }
            this.e.m();
            return 0;
        }
        LogUtil.a("Distribute_WirelessProjectionManager", "in handleMessage, mDialogStartScanDevices.isShowing() is ", Boolean.valueOf(this.h.isShowing()));
        return 4;
    }

    protected void a() {
        LogUtil.a("Distribute_WirelessProjectionManager", "showProjectionPreDeviceDialog...");
        if (this.d == null) {
            return;
        }
        if (this.k == null) {
            this.k = new CustomTextAlertDialog.Builder(this.d).b(this.d.getResources().getString(R.string._2130837518_res_0x7f02000e)).e(this.d.getResources().getString(R.string._2130837519_res_0x7f02000f)).c(true).cyV_(this.d.getResources().getString(R.string._2130837520_res_0x7f020010), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.i();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyS_(this.d.getResources().getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
        }
        this.k.setCancelable(false);
        this.k.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        cub.a(BaseApplication.e(), true);
        this.o.postDelayed(new Runnable() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.4
            @Override // java.lang.Runnable
            public void run() {
                WirelessProjectionManager.b(WirelessProjectionManager.this, 200);
                if (cub.h(BaseApplication.e()) || WirelessProjectionManager.this.p >= 1600) {
                    LogUtil.a("Distribute_WirelessProjectionManager", "mTotalUpdateWifiStateDelayMillis delay ", Integer.valueOf(WirelessProjectionManager.this.p), "ms");
                    WirelessProjectionManager.this.e.m();
                    WirelessProjectionManager.this.p = 0;
                    WirelessProjectionManager.this.o.removeCallbacks(this);
                    return;
                }
                LogUtil.a("Distribute_WirelessProjectionManager", "mTotalUpdateWifiStateDelayMillis postDelayed delay   ", Integer.valueOf(WirelessProjectionManager.this.p), "ms");
                WirelessProjectionManager.this.o.postDelayed(this, 200L);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("Distribute_WirelessProjectionManager", "showStartScanDevicesDialog...");
        if (this.d == null) {
            return;
        }
        if (!this.l) {
            LogUtil.a("Distribute_WirelessProjectionManager", "showStartScanDevicesDialog mIsClickStartScan false");
            return;
        }
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: bza
                @Override // java.lang.Runnable
                public final void run() {
                    WirelessProjectionManager.this.h();
                }
            });
            return;
        }
        if (this.h == null) {
            View inflate = View.inflate(this.d, R.layout.scan_miracast_devices_dialog, null);
            if (inflate != null) {
                this.s = (LinearLayout) inflate.findViewById(R.id.scan_device_linearlayout);
                this.t = (ListView) inflate.findViewById(R.id.miracast_device_list);
            }
            this.h = new CustomViewDialog.Builder(this.d).a(this.d.getResources().getString(R.string._2130837506_res_0x7f020002)).d(true).czg_(inflate).czf_(this.d.getResources().getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.e.l();
                    WirelessProjectionManager.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        DeviceListAdapter deviceListAdapter = this.c;
        if (deviceListAdapter != null) {
            deviceListAdapter.a();
        }
        LinearLayout linearLayout = this.s;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        ListView listView = this.t;
        if (listView != null) {
            listView.setVisibility(8);
        }
        this.h.setCancelable(false);
        if (this.h.isShowing()) {
            return;
        }
        this.h.show();
    }

    protected void d() {
        LogUtil.a("Distribute_WirelessProjectionManager", "showDisplayDevicesDialog...");
        if (this.h == null || this.t == null) {
            return;
        }
        LinearLayout linearLayout = this.s;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        this.t.setVisibility(0);
        if (this.c == null) {
            this.c = new DeviceListAdapter(this.d);
        }
        this.t.setAdapter((ListAdapter) this.c);
        this.t.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.10
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ProjectionDevice projectionDevice;
                WirelessProjectionManager.this.d(1007);
                if (WirelessProjectionManager.this.c.getItem(i) instanceof ProjectionDevice) {
                    projectionDevice = (ProjectionDevice) WirelessProjectionManager.this.c.getItem(i);
                    WirelessProjectionManager.this.a(1011, projectionDevice);
                } else {
                    projectionDevice = null;
                }
                WirelessProjectionManager.this.e.b(projectionDevice);
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private void c(String str) {
        LogUtil.a("Distribute_WirelessProjectionManager", "showCloseProjectionDialog...");
        Context context = this.d;
        if (context == null) {
            return;
        }
        if (this.f == null) {
            View inflate = View.inflate(context, R.layout.close_projection_connection_dialog, null);
            this.b = (HealthTextView) inflate.findViewById(R.id.projection_device_name);
            this.f = new CustomViewDialog.Builder(this.d).a(this.d.getResources().getString(R.string._2130837506_res_0x7f020002)).czg_(inflate).czf_(this.d.getResources().getString(R.string._2130837523_res_0x7f020013), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.e.c();
                    WirelessProjectionManager.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czd_(this.d.getResources().getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.j();
                    LogUtil.a("Distribute_WirelessProjectionManager", "setNegativeButton in showCloseProjectionDialog!");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.b.setText(str);
        this.f.setCancelable(false);
        this.f.show();
    }

    protected void b(String str) {
        LogUtil.a("Distribute_WirelessProjectionManager", "showProjectionPreDeviceDialog...");
        if (this.d == null) {
            return;
        }
        if (this.g == null) {
            final HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            this.g = new CustomTextAlertDialog.Builder(this.d).b(this.d.getResources().getString(R.string._2130837506_res_0x7f020002)).e(String.format(Locale.ENGLISH, this.d.getResources().getString(R.string.ie_projection_pre_device), str)).c(true).cyV_(this.d.getResources().getString(R.string._2130837521_res_0x7f020011), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ProjectionDevice b = WirelessProjectionManager.this.e.b();
                    WirelessProjectionManager.this.a(b.getDeviceName());
                    WirelessProjectionManager.this.e.b(b);
                    WirelessProjectionManager.this.e.n();
                    hashMap.put("action", 0);
                    ixx.d().d(WirelessProjectionManager.this.d, AnalyticsValue.DISTRIBUTE_CONNECT_LAST_DEVICE_11130050.value(), hashMap, 0);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyS_(this.d.getResources().getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.e.o();
                    WirelessProjectionManager.this.e.m();
                    hashMap.put("action", 1);
                    ixx.d().d(WirelessProjectionManager.this.d, AnalyticsValue.DISTRIBUTE_CONNECT_LAST_DEVICE_11130050.value(), hashMap, 0);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
        }
        this.g.setCancelable(false);
        this.g.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogUtil.a("Distribute_WirelessProjectionManager", "showConnectDeviceDialog...");
        Context context = this.d;
        if (context == null) {
            return;
        }
        if (this.i == null) {
            View inflate = View.inflate(context, R.layout.connect_miracast_device_dialog, null);
            this.f2187a = (HealthTextView) inflate.findViewById(R.id.connect_miracast_device);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.connect_miracast_device_progress_anim);
            if (imageView.getDrawable() instanceof AnimationDrawable) {
                this.n = (AnimationDrawable) imageView.getDrawable();
            }
            this.i = new CustomViewDialog.Builder(this.d).a(this.d.getResources().getString(R.string._2130837506_res_0x7f020002)).czg_(inflate).czf_(this.d.getResources().getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Distribute_WirelessProjectionManager", "Cancel the connection to the device.");
                    WirelessProjectionManager.this.e.a();
                    WirelessProjectionManager.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.f2187a.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R.string._2130837517_res_0x7f02000d), str));
        this.i.setCancelable(false);
        this.i.show();
        this.n.start();
    }

    protected void c() {
        LogUtil.a("Distribute_WirelessProjectionManager", "showConnectDeviceFailedDialog...");
        if (this.d == null) {
            return;
        }
        if (this.j == null) {
            this.j = new CustomTextAlertDialog.Builder(this.d).b(this.d.getResources().getString(R.string._2130837514_res_0x7f02000a)).e(this.d.getResources().getString(R.string._2130837515_res_0x7f02000b)).c(true).cyV_(this.d.getResources().getString(R.string._2130837516_res_0x7f02000c), new View.OnClickListener() { // from class: com.huawei.health.airsharing.WirelessProjectionManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WirelessProjectionManager.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
        }
        this.j.setCancelable(false);
        this.j.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Ck_(Message message) {
        if (message == null || this.c == null || !(message.obj instanceof ProjectionDevice)) {
            return;
        }
        this.c.a((ProjectionDevice) message.obj);
    }

    protected void Cn_(Message message) {
        if (message == null || this.c == null || !(message.obj instanceof ProjectionDevice)) {
            return;
        }
        this.c.d((ProjectionDevice) message.obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Cl_(Message message) {
        CustomViewDialog customViewDialog = this.i;
        if ((customViewDialog == null || !customViewDialog.isShowing()) && (message.obj instanceof ProjectionDevice)) {
            a(((ProjectionDevice) message.obj).getDeviceName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i == 3501) {
            h();
        } else if (i == 3503) {
            h();
        }
    }

    public class a implements AirSharingHelper.EventCallback {
        public a() {
        }

        @Override // com.huawei.health.airsharing.helper.AirSharingHelper.EventCallback
        public boolean onEvent(int i, String str) {
            if (i == 3005) {
                LogUtil.a("Distribute_ProjectionEventCallback", "Successful disconnection");
            }
            if (i == 3006) {
                LogUtil.a("Distribute_ProjectionEventCallback", "Stop scanning");
            }
            ReleaseLogUtil.e("Distribute_ProjectionEventCallback", RewardMethods.ON_EVENT, Integer.valueOf(i));
            WirelessProjectionManager.this.e(i);
            return false;
        }

        @Override // com.huawei.health.airsharing.helper.AirSharingHelper.EventCallback
        public void onDisplayUpdate(int i, String str, String str2, int i2) {
            LogUtil.a("Distribute_ProjectionEventCallback", "deviceId:", Integer.valueOf(i), "firstDevice:", str, "secondDevice:", str2, "secondDeviceId:", Integer.valueOf(i2));
        }

        @Override // com.huawei.health.airsharing.helper.AirSharingHelper.EventCallback
        public void onMirrorUpdate(int i, String str, String str2, int i2, boolean z) {
            LogUtil.a("Distribute_ProjectionEventCallback", "deviceId:", Integer.valueOf(i), "firstDevice:", str, "secondDevice:", str2, "secondDeviceId:", Integer.valueOf(i2));
        }

        @Override // com.huawei.health.airsharing.helper.AirSharingHelper.EventCallback
        public void onProjectionDeviceUpdate(int i, ProjectionDevice projectionDevice) {
            switch (i) {
                case 3001:
                    LogUtil.a("Distribute_ProjectionEventCallback", "Device add");
                    if (projectionDevice != null && (projectionDevice.getCapability() & 1) == 1) {
                        if (WirelessProjectionManager.this.h != null && WirelessProjectionManager.this.h.isShowing()) {
                            WirelessProjectionManager.this.d(1008);
                        }
                        WirelessProjectionManager.this.a(1009, projectionDevice);
                        HashMap hashMap = new HashMap(2);
                        hashMap.put("click", 1);
                        hashMap.put("deviceName", projectionDevice.getDeviceName());
                        ixx.d().d(WirelessProjectionManager.this.d, AnalyticsValue.DISTRIBUTE_GET_PROJECTION_DEVICE_1130049.value(), hashMap, 0);
                        break;
                    }
                    break;
                case 3002:
                    LogUtil.a("Distribute_ProjectionEventCallback", "Device remove");
                    WirelessProjectionManager.this.a(1010, projectionDevice);
                    break;
                case 3003:
                    LogUtil.a("Distribute_ProjectionEventCallback", "Device connect succeed");
                    WirelessProjectionManager.this.d(1012);
                    break;
                case IEventListener.EVENT_ID_DEVICE_CONN_FAIL /* 3004 */:
                    LogUtil.h("Distribute_ProjectionEventCallback", "Device connect failed");
                    WirelessProjectionManager.this.d(1012);
                    WirelessProjectionManager.this.d(1013);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        Handler handler = this.o;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj) {
        Handler handler = this.o;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = obj;
            this.o.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("Distribute_WirelessProjectionManager", "hideSystemNavBar", Boolean.valueOf(this.m), Boolean.valueOf(this.d instanceof Activity));
        Context context = this.d;
        if ((context instanceof Activity) && this.m) {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }
}
