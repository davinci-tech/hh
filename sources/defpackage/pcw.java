package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.holder.WearHomeStatusHolder;
import defpackage.pcw;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class pcw extends WearHomeBaseCard {

    /* renamed from: a, reason: collision with root package name */
    private Context f16067a;
    private WearHomeStatusHolder n = null;
    private NoTitleCustomAlertDialog i = null;
    private CustomTextAlertDialog c = null;
    private boolean f = false;
    private CustomAlertDialog j = null;
    private String e = "";
    private BtSwitchStateCallback d = null;
    private final BroadcastReceiver h = new BroadcastReceiver() { // from class: pcw.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            if (intent == null || !"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || (extras = intent.getExtras()) == null) {
                return;
            }
            int i = extras.getInt("BATTERY_LEVEL");
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "battery refresh is received，refreshBattery is", Integer.valueOf(i));
            int b = jpy.b(pcw.this.mActivity.g);
            String string = extras.getString("DEVICE_MAC");
            pcw.this.b(string);
            if (b != i && pcw.this.mActivity.g.equals(string)) {
                jpy.b(pcw.this.mActivity.g, i);
            }
            pcw pcwVar = pcw.this;
            pcwVar.d(jpy.b(pcwVar.mActivity.g));
        }
    };
    private final View.OnClickListener b = new View.OnClickListener() { // from class: pdi
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            pcw.this.dlP_(view);
        }
    };
    private Runnable g = new Runnable() { // from class: pdg
        @Override // java.lang.Runnable
        public final void run() {
            pcw.this.f();
        }
    };

    /* synthetic */ void dlP_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.mActivity.b == null) {
            LogUtil.a("WearHomeStatusCard", "mActivity.mCurrentDeviceInfo == null");
            this.mActivity.b = oxa.a().b(this.mActivity.g);
        }
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a() {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.mContext) { // from class: pcw.4
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WearHomeStatusCard", "nearby permission granted");
                    if (gnm.aPA_(pcw.this.mActivity)) {
                        pcw.this.l();
                    } else {
                        LogUtil.h("WearHomeStatusCard", "checkBluetoothPermission not ActivityAlive");
                    }
                }
            });
        } else if (this.mActivity.b != null && this.mActivity.b.getDeviceBluetoothType() == 2) {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(this.mContext) { // from class: pcw.5
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WearHomeStatusCard", "location permission ok.");
                    if (!oad.d(pcw.this.mContext)) {
                        pcw.this.x();
                    } else {
                        pcw.this.l();
                    }
                }
            });
        } else {
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (jkj.d().j()) {
            LogUtil.a("WearHomeStatusCard", "other wear device is Upgrading");
            v();
        } else {
            p();
        }
    }

    /* synthetic */ void f() {
        e(0);
    }

    public pcw(Context context, WearHomeActivity wearHomeActivity) {
        this.mContext = context;
        this.mActivity = wearHomeActivity;
        this.f16067a = BaseApplication.getContext();
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        WearHomeStatusHolder wearHomeStatusHolder = new WearHomeStatusHolder(layoutInflater.inflate(R.layout.wear_home_status_layout, viewGroup, false));
        this.n = wearHomeStatusHolder;
        wearHomeStatusHolder.k().setOnClickListener(this.b);
        s();
        LogUtil.a("WearHomeStatusCard", "getCardViewHolder", this.n);
        return this.n;
    }

    private void s() {
        pep.dmT_(this.mContext, this.h, "com.huawei.bone.action.BATTERY_LEVEL");
        o();
        j(0);
    }

    private void v() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: pdd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pcw.dlL_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dlL_(View view) {
        LogUtil.a("WearHomeStatusCard", "showTipDialog,click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        if (this.mActivity == null || this.n == null) {
            return;
        }
        this.mActivity.djG_().removeCallbacks(this.g);
        g(i);
        j(200);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        j(200);
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        pep.dmY_(this.mContext, this.h);
        oyf.c(0);
        if (this.d != null) {
            iyl.d().e(this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pdf
            @Override // java.lang.Runnable
            public final void run() {
                pcw.this.j();
            }
        });
    }

    /* synthetic */ void j() {
        LogUtil.a("WearHomeStatusCard", "getHarmonyStatus getValue");
        String e = jah.c().e("scale_share_harmony_tips");
        DevicePairGuideUtil.e(e);
        LogUtil.a("WearHomeStatusCard", "getHarmonyStatus scale_share_harmony_tips: ", e);
        this.mActivity.runOnUiThread(new Runnable() { // from class: pde
            @Override // java.lang.Runnable
            public final void run() {
                pcw.this.g();
            }
        });
    }

    /* synthetic */ void g() {
        String string;
        if ("on".equals(DevicePairGuideUtil.d())) {
            string = BaseApplication.getContext().getString(R.string._2130843755_res_0x7f02186b);
        } else {
            string = BaseApplication.getContext().getString(R.string._2130843258_res_0x7f02167a);
        }
        pep.d(this.mContext, string);
    }

    private void j(int i) {
        if (this.n == null) {
            LogUtil.h("WearHomeStatusCard", "refreshStatusCard mWearHomeStatusHolder == null");
            return;
        }
        e(i);
        d(jpy.b(this.mActivity.g));
        if (jpy.b(this.mActivity.g) != -1) {
            d(jpy.b(this.mActivity.g));
            LogUtil.a("WearHomeStatusCard", "DeviceBatteryInfo.battery is not -1 , battery is ", Integer.valueOf(jpy.b(this.mActivity.g)));
        }
        d(this.mActivity.b);
    }

    private void p() {
        this.mActivity.djG_().removeMessages(24);
        this.mActivity.djG_().sendEmptyMessage(24);
    }

    public void i() {
        if (this.n == null) {
            ReleaseLogUtil.d("R_WearHomeStatusCard", "reconnectByClickBluetoothImage mWearHomeStatusHolder == null");
            return;
        }
        if (this.mActivity.b == null) {
            this.mActivity.b = oxa.a().b(this.mActivity.g);
        }
        if (this.mActivity.b != null) {
            this.mActivity.m = this.mActivity.b.getSecurityUuid();
            int deviceConnectState = this.mActivity.b.getDeviceConnectState();
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "bluetooth status:", Integer.valueOf(deviceConnectState));
            if (deviceConnectState != 2 && deviceConnectState != 1) {
                LogUtil.a("MainUI", 0, "WearHomeStatusCard", "begin connect in ui , name:", this.mActivity.b.getDeviceName());
                if (!a(this.mActivity.b)) {
                    LogUtil.a("WearHomeStatusCard", "onclickReconnect false");
                    return;
                }
                if (this.mActivity.djG_() == null) {
                    ReleaseLogUtil.d("R_WearHomeStatusCard", "reconnectByClickBluetoothImage messageHandler is null");
                    return;
                }
                this.mActivity.djG_().removeCallbacks(this.g);
                this.mActivity.djG_().postDelayed(this.g, 20000L);
                final List<DeviceInfo> i = oxa.a().i();
                a(i);
                ThreadPoolManager.d().execute(new Runnable() { // from class: pcw.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String str;
                        jfv.a(i, pcw.this.mActivity.b.getDeviceIdentify());
                        if (pcw.this.mActivity.b == null) {
                            LogUtil.h("WearHomeStatusCard", "reconnectByClickBluetoothImage mCurrentDeviceInfo is null");
                            return;
                        }
                        if (!TextUtils.isEmpty(pcw.this.mActivity.b.getDeviceUdid())) {
                            str = pcw.this.mActivity.b.getDeviceUdid();
                        } else {
                            str = pcw.this.mActivity.b.getSecurityUuid() + "#ANDROID21";
                        }
                        new obq().d(null, str, false, pcw.this.mActivity.b.getDeviceIdentify(), pcw.this.mContext);
                    }
                });
                return;
            }
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "connected or connecting");
            return;
        }
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "no device info");
    }

    private void a(List<DeviceInfo> list) {
        if (list == null) {
            LogUtil.a("WearHomeStatusCard", "handleDeviceState deviceInfoLists is null");
            return;
        }
        if (cvt.c(this.mActivity.b.getProductType())) {
            for (DeviceInfo deviceInfo : list) {
                if (this.mActivity.b.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    deviceInfo.setDeviceActiveState(1);
                    deviceInfo.setDeviceConnectState(1);
                } else if (cvt.c(deviceInfo.getProductType())) {
                    deviceInfo.setDeviceActiveState(0);
                    deviceInfo.setDeviceConnectState(3);
                } else {
                    LogUtil.h("WearHomeStatusCard", "handleDeviceState default");
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo2 : list) {
            if (this.mActivity.b.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                deviceInfo2.setDeviceActiveState(1);
                deviceInfo2.setDeviceConnectState(1);
            }
            if (!this.mActivity.b.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getAutoDetectSwitchStatus() != 1 && !obb.e(this.mActivity.b.getProductType()) && deviceInfo2.getDeviceActiveState() == 1) {
                deviceInfo2.setDeviceActiveState(0);
                deviceInfo2.setDeviceConnectState(3);
            }
        }
        cpl.c().f();
    }

    private boolean a(DeviceInfo deviceInfo) {
        LogUtil.a("MainUI", 0, "Enter onclickReconnect");
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("MainUI", 0, "onclickReconnect BT switch is false!");
            r();
            return false;
        }
        LogUtil.a("MainUI", 0, "onclickReconnect close BT Dialog!");
        m();
        if (cpl.c().b(deviceInfo.getDeviceIdentify()) || cpl.c().e().isEmpty()) {
            h(deviceInfo);
            return true;
        }
        b(deviceInfo);
        h(deviceInfo);
        return true;
    }

    private void b(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeStatusCard", "handleWorkMode set device enable");
        deviceInfo.setDeviceActiveState(1);
        deviceInfo.setDeviceConnectState(1);
    }

    private void e(int i) {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter refreshState:", Integer.valueOf(i));
        this.mActivity.djG_().removeMessages(1001);
        this.mActivity.djG_().sendEmptyMessageDelayed(1001, i);
    }

    public void d() {
        this.mActivity.b = oxa.a().b(this.mActivity.g);
        if (this.mActivity.b != null) {
            List<DeviceInfo> i = oxa.a().i();
            if (i == null || i.size() == 0) {
                LogUtil.h("WearHomeStatusCard", "(deviceList == null) || (deviceList.size() == 0)");
                return;
            }
            for (DeviceInfo deviceInfo : i) {
                if (this.mActivity.b.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    LogUtil.a("MainUI", 0, "WearHomeStatusCard", "dealWithRefreshBlueStatus status ", Integer.valueOf(deviceConnectState));
                    i(this.mActivity.b);
                    g(deviceConnectState);
                    return;
                }
            }
            return;
        }
        g(3);
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "deviceInfo is null");
    }

    private void h(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeStatusCard", "startReconnect reconnectCount : ", Integer.valueOf(oyf.a()));
        if (deviceInfo != null) {
            SharedPreferenceManager.c(String.valueOf(10), "app_big_data_device_name", deviceInfo.getDeviceName());
        }
        if (oyf.a() < 2) {
            this.f = true;
            g(1);
            oyf.c(oyf.a() + 1);
            LogUtil.a("WearHomeStatusCard", "startReconnect : ", Integer.valueOf(oyf.a()));
            return;
        }
        this.f = false;
        dlI_(cpl.c().Kj_(deviceInfo.getProductType()));
        oyf.c(0);
    }

    private void i(DeviceInfo deviceInfo) {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter updateIdImage ProductType:", Integer.valueOf(deviceInfo.getProductType()), " DeviceModel:", deviceInfo.getDeviceModel(), " HiLinkDeviceId:", deviceInfo.getHiLinkDeviceId());
        if (deviceInfo.getProductType() == 10 && (TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN") || TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter updateIdImage : baoshijie!");
            this.n.dmD_().setImageResource(R.mipmap._2131821454_res_0x7f11038e);
        } else if (!TextUtils.isEmpty(deviceInfo.getDeviceName()) && deviceInfo.getProductType() == 11 && deviceInfo.getDeviceName().contains("HUAWEI CM-R1P")) {
            this.n.dmD_().setImageResource(R.mipmap._2131821240_res_0x7f1102b8);
        } else if (jfu.m(deviceInfo.getProductType()) || !TextUtils.isEmpty(jfu.d(deviceInfo.getProductType()))) {
            j(deviceInfo);
        } else {
            this.n.dmD_().setImageResource(oxa.a().d(deviceInfo.getProductType()));
        }
    }

    private void j(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("R_WearHomeStatusCard", "enter setSupportDownloadImage");
        ThreadPoolManager.d().execute(new AnonymousClass3(deviceInfo));
    }

    /* renamed from: pcw$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ DeviceInfo c;

        AnonymousClass3(DeviceInfo deviceInfo) {
            this.c = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            ReleaseLogUtil.e("R_WearHomeStatusCard", "setSupportDownloadImage subThread");
            String j = jfu.j(this.c.getProductType());
            if (TextUtils.isEmpty(j)) {
                j = jfu.d(this.c.getProductType());
            }
            final cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
            if (TextUtils.isEmpty(j) || pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
                pcw.this.c(this.c);
                return;
            }
            LogUtil.a("WearHomeStatusCard", "is plugin download uuid:", j);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
            LogUtil.a("WearHomeStatusCard", "is plugin download isPluginAvailable:", Boolean.valueOf(isResourcesAvailable));
            if (!isResourcesAvailable) {
                pcw.this.c(this.c);
                return;
            }
            WearHomeActivity wearHomeActivity = pcw.this.mActivity;
            final DeviceInfo deviceInfo = this.c;
            wearHomeActivity.runOnUiThread(new Runnable() { // from class: pdm
                @Override // java.lang.Runnable
                public final void run() {
                    pcw.AnonymousClass3.this.b(deviceInfo, pluginInfoByUuid);
                }
            });
        }

        /* synthetic */ void b(DeviceInfo deviceInfo, cvc cvcVar) {
            ReleaseLogUtil.e("R_WearHomeStatusCard", "setSupportDownloadImage MainThread");
            pcw.this.e(deviceInfo, cvcVar);
            pcw.this.d(deviceInfo, cvcVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo, cvc cvcVar) {
        String a2 = cwf.a(cvcVar, nsn.v(this.mContext) ? "dark" : "light", deviceInfo);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("WearHomeStatusCard", "deviceBackground imageName is null");
            this.n.dmE_().setVisibility(8);
            return;
        }
        LogUtil.a("WearHomeStatusCard", "deviceBackground imageName:", a2);
        String str = msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + a2 + ".png";
        if (!new File(str).exists()) {
            LogUtil.h("WearHomeStatusCard", "deviceBackground image file not exists");
            this.n.dmE_().setVisibility(8);
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inInputShareable = true;
        LogUtil.a("WearHomeStatusCard", "deviceBackground have bitmap fetchPluginUuid:", cvcVar.e());
        this.n.dmE_().setImageBitmap(BitmapFactory.decodeFile(str, options));
        this.n.dmE_().setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, cvc cvcVar) {
        String a2 = cwf.a(cvcVar, 2, deviceInfo);
        LogUtil.a("WearHomeStatusCard", "HomeImgNew name:", a2);
        if (new File(msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + a2 + ".png").exists()) {
            Bitmap loadImageByImageName = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(cvcVar, a2);
            if (loadImageByImageName == null) {
                LogUtil.h("WearHomeStatusCard", "deviceBitmapTemp is null");
                return;
            }
            int width = loadImageByImageName.getWidth();
            int height = loadImageByImageName.getHeight();
            LogUtil.a("WearHomeStatusCard", "width:", Integer.valueOf(width), ",height:", Integer.valueOf(height));
            LogUtil.a("WearHomeStatusCard", "background width:", Integer.valueOf(this.n.dmD_().getWidth()), ", background height:", Integer.valueOf(this.n.dmD_().getHeight()));
            Matrix matrix = new Matrix();
            matrix.postScale(this.n.dmD_().getWidth() / width, this.n.dmD_().getHeight() / height);
            matrix.reset();
            this.n.dmD_().setImageBitmap(Bitmap.createBitmap(loadImageByImageName, 0, 0, width, height, matrix, true));
            return;
        }
        c(deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final DeviceInfo deviceInfo) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: pdk
            @Override // java.lang.Runnable
            public final void run() {
                pcw.this.e(deviceInfo);
            }
        });
    }

    /* synthetic */ void e(DeviceInfo deviceInfo) {
        if (jfu.h(deviceInfo.getProductType())) {
            this.n.dmD_().setImageResource(R.mipmap._2131820664_res_0x7f110078);
        } else {
            this.n.dmD_().setImageResource(R.mipmap._2131820674_res_0x7f110082);
        }
    }

    private void o() {
        LogUtil.a("WearHomeStatusCard", "getConnectTip");
        if (this.mActivity.b == null) {
            LogUtil.h("WearHomeStatusCard", "getConnectTip mActivity.mCurrentDeviceInfo == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pcw.7
                @Override // java.lang.Runnable
                public void run() {
                    if (pcw.this.mActivity == null || pcw.this.mActivity.b == null) {
                        LogUtil.h("WearHomeStatusCard", "getConnectTip mActivity or mCurrentDeviceInfo is null");
                        return;
                    }
                    cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(pcw.this.mActivity.b.getProductType()));
                    if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
                        return;
                    }
                    pcw.this.e = pluginInfoByUuid.f().c();
                    LogUtil.a("WearHomeStatusCard", "mAwakenDevice = ", pcw.this.e);
                }
            });
        }
    }

    private void g(int i) {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter updateBluetoothState isConnect:", Integer.valueOf(i));
        if (i == 2) {
            ad();
        } else if (i == 1) {
            k();
        } else if (i == 9 || i == 13) {
            u();
        } else if (i == 10) {
            if (this.mActivity.b == null) {
                LogUtil.h("MainUI", 0, "WearHomeStatusCard", "current info is empty.");
                return;
            } else if (this.f) {
                oxa.a().c(this.mActivity.b.getDeviceIdentify());
                this.mActivity.finish();
            }
        } else if (i == 5) {
            ab();
        } else {
            this.f = false;
            this.n.dmC_().setVisibility(0);
            this.n.k().setVisibility(0);
            this.n.dmy_().setVisibility(8);
            this.n.dmF_().setVisibility(8);
            this.n.dmB_().setVisibility(8);
            this.n.dmA_().setVisibility(8);
            if (!TextUtils.isEmpty(this.e)) {
                this.n.i().setVisibility(0);
                this.n.i().setText(this.e);
            } else {
                this.n.i().setVisibility(8);
            }
            this.n.q().setVisibility(8);
            this.n.j().setText(this.f16067a.getResources().getString(R.string._2130841443_res_0x7f020f63));
            if (this.mActivity.djG_() != null) {
                this.mActivity.djG_().removeMessages(1006);
            }
        }
        h(i);
    }

    private void u() {
        this.n.k().setVisibility(0);
        this.n.dmy_().setVisibility(8);
        this.n.dmF_().setVisibility(8);
        this.n.dmB_().setVisibility(8);
        this.n.dmA_().setVisibility(8);
        if (!TextUtils.isEmpty(this.e)) {
            this.n.i().setVisibility(0);
            this.n.i().setText(this.e);
        } else {
            this.n.i().setVisibility(8);
        }
        this.n.q().setVisibility(8);
        this.n.j().setText(this.f16067a.getResources().getString(R.string._2130841443_res_0x7f020f63));
        if (this.f) {
            h();
        }
    }

    private void ab() {
        this.f = false;
        this.n.k().setVisibility(0);
        this.n.dmy_().setVisibility(8);
        this.n.dmF_().setVisibility(8);
        this.n.dmB_().setVisibility(8);
        this.n.dmA_().setVisibility(8);
        if (!TextUtils.isEmpty(this.e)) {
            this.n.i().setVisibility(0);
            this.n.i().setText(this.e);
        } else {
            this.n.i().setVisibility(8);
        }
        this.n.q().setVisibility(8);
        this.n.j().setText(this.f16067a.getResources().getString(R.string._2130841443_res_0x7f020f63));
        q();
    }

    private void ad() {
        CustomAlertDialog customAlertDialog = this.j;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        this.f = false;
        this.mActivity.b = oxa.a().a(this.mActivity.g);
        if (this.mActivity.b != null && cvt.c(this.mActivity.b.getProductType())) {
            if (jkj.d().c(this.mActivity.g) == 6) {
                this.n.k().setVisibility(8);
                this.n.dmy_().setVisibility(8);
                this.n.dmF_().setVisibility(8);
                this.n.dmB_().setVisibility(8);
                this.n.dmA_().setVisibility(0);
                this.n.j().setText(this.f16067a.getResources().getString(R.string._2130841463_res_0x7f020f77));
                return;
            }
            this.n.k().setVisibility(8);
            this.n.dmA_().setVisibility(8);
            this.n.dmy_().setVisibility(0);
            this.n.j().setText(this.f16067a.getResources().getString(R.string.IDS_wearhome_connected));
            oyf.c(0);
            this.mActivity.djG_().sendEmptyMessage(1002);
            return;
        }
        w();
    }

    private void h(int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        if (this.n.dmB_().getVisibility() == 0) {
            this.n.dmC_().setVisibility(8);
            this.n.dmB_().setVisibility(0);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 2.0f);
            layoutParams2.setMarginStart(nsn.c(this.mContext, 24.0f));
            this.n.dmz_().setLayoutParams(layoutParams2);
            return;
        }
        if (this.n.dmF_().getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams3.setMarginStart(nsn.c(this.mContext, 24.0f));
            this.n.dmz_().setLayoutParams(layoutParams3);
            layoutParams4.setMarginEnd(nsn.c(this.mContext, 24.0f));
            this.n.dmC_().setLayoutParams(layoutParams4);
            layoutParams.setMarginStart(nsn.c(this.mContext, 8.0f));
            layoutParams.setMarginEnd(nsn.c(this.mContext, 8.0f));
            this.n.dmF_().setLayoutParams(layoutParams);
            int d = jgq.d().d(this.mActivity.g);
            this.n.b().setText(jgq.d().d(d));
            this.n.c().setText(this.f16067a.getResources().getString(R.string._2130845101_res_0x7f021dad));
            if (LanguageUtil.p(this.mContext) || LanguageUtil.h(this.mContext)) {
                this.n.b().setTextSize(2, 16.0f);
                this.n.c().setTextSize(2, 14.0f);
                return;
            } else if (d == 0 && LanguageUtil.ae(this.mContext)) {
                this.n.b().setTextSize(2, 11.0f);
                this.n.b().setLines(1);
                return;
            } else {
                this.n.b().setTextSize(2, 14.0f);
                this.n.c().setTextSize(2, 12.0f);
                return;
            }
        }
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(nsn.c(this.mContext, 24.0f));
        this.n.dmz_().setLayoutParams(layoutParams);
        layoutParams5.setMarginEnd(nsn.c(this.mContext, 24.0f));
        this.n.dmC_().setLayoutParams(layoutParams5);
    }

    private void h() {
        if (this.c != null) {
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(this.mContext.getResources().getString(R.string._2130843841_res_0x7f0218c1)).e(this.mContext.getResources().getString(R.string._2130843842_res_0x7f0218c2)).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: pdc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pcw.this.dlO_(view);
            }
        }).a();
        this.c = a2;
        a2.setCancelable(false);
        if (this.c.isShowing()) {
            return;
        }
        this.c.show();
    }

    /* synthetic */ void dlO_(View view) {
        LogUtil.a("WearHomeStatusCard", "The user indicates know.");
        this.c.dismiss();
        this.c = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void q() {
        LogUtil.c("WearHomeStatusCard", "showBandUnavailableDialog");
        boolean h = CommonUtil.h(this.mContext, "com.huawei.ui.homewear21.home.WearHomeActivity");
        LogUtil.c("WearHomeStatusCard", "isForeground : ", Boolean.valueOf(h));
        if (h) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R.string.IDS_service_area_notice_title).e(this.mActivity.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: pdj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pcw.dlK_(view);
                }
            }).a();
            a2.setCancelable(false);
            if (a2.isShowing()) {
                return;
            }
            a2.show();
        }
    }

    static /* synthetic */ void dlK_(View view) {
        LogUtil.c("WearHomeStatusCard", "onClick showBandUnavailableDialog");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter GET_BATTETY_LEVEL");
        String deviceIdentify = (this.mActivity == null || this.mActivity.b == null) ? "" : this.mActivity.b.getDeviceIdentify();
        if (deviceIdentify.isEmpty()) {
            oxa.a().d();
        } else {
            oxa.a().d(deviceIdentify);
        }
    }

    public void c() {
        LogUtil.a("WearHomeStatusCard", "Enter GET_WEAR_PLACE_CODE");
        if (this.mActivity != null) {
            if (this.mActivity.b != null) {
                jgi.e().c(this.mActivity.b, new a(this.mActivity), "WearHomeStatusCard");
            } else {
                jgi.e().b(new a(this.mActivity), "WearHomeStatusCard");
            }
            this.mActivity.djG_().sendEmptyMessageDelayed(1006, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter refreshBattery value:", Integer.valueOf(i));
        if (!CommonUtil.x(this.mContext) && this.mActivity.b != null && this.mActivity.b.getDeviceConnectState() == 2) {
            this.mActivity.i = true;
        }
        if (this.mActivity.i) {
            if (jkj.d().c(this.mActivity.g) == 6) {
                LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter refreshBattery device is OTA");
                return;
            }
            Message obtainMessage = this.mActivity.djG_().obtainMessage();
            obtainMessage.what = 1003;
            obtainMessage.arg1 = i;
            this.mActivity.djG_().removeMessages(1003);
            this.mActivity.djG_().sendMessage(obtainMessage);
            return;
        }
        LogUtil.h("MainUI", 0, "WearHomeStatusCard", "updateBluetoothState isConnect is false");
    }

    public void dlM_(Message message) {
        if (this.n == null) {
            LogUtil.h("WearHomeStatusCard", "handlerRefreshBatteryState mWearHomeStatusHolder is null");
            return;
        }
        c(message.arg1);
        this.n.j().setText(this.mContext.getResources().getString(R.string.IDS_wearhome_connected));
        if (this.n.k().getVisibility() == 0) {
            this.n.k().setVisibility(8);
        }
    }

    private void c(int i) {
        LogUtil.a("WearHomeStatusCard", "Enter refreshBatteryState");
        if (cwi.c(this.mActivity.b, 112)) {
            b(i);
            return;
        }
        if (i == -1) {
            LogUtil.h("WearHomeStatusCard", "refreshBatteryState BATTERY_INVALID");
            this.n.dmy_().setVisibility(8);
            return;
        }
        if (this.n.dmy_().getVisibility() != 0) {
            this.n.dmy_().setVisibility(0);
        }
        if (LanguageUtil.bc(this.mContext)) {
            this.n.dmx_().setImageDrawable(nrz.cKm_(this.mContext, nsn.cLd_(i)));
        } else {
            this.n.dmx_().setImageDrawable(nsn.cLd_(i));
        }
        this.n.e().setText(UnitUtil.e(i, 2, 0));
    }

    private void b(int i) {
        if (this.n.dmB_().getVisibility() != 0) {
            this.n.dmB_().setVisibility(0);
        }
        if (i != -1) {
            this.n.g().setText(UnitUtil.e(i, 2, 0));
        } else {
            this.n.g().setText("--");
        }
        jpw e = jpy.e(this.mActivity.g);
        int b = e.b();
        int e2 = e.e();
        if (b != -1) {
            this.n.o().setText(UnitUtil.e(b, 2, 0));
        } else {
            this.n.o().setText("--");
        }
        if (e2 != -1) {
            this.n.l().setText(UnitUtil.e(e2, 2, 0));
        } else {
            this.n.l().setText("--");
        }
    }

    public void dlN_(Message message) {
        if (this.n == null) {
            LogUtil.h("WearHomeStatusCard", "handlerRefreshWearPlace mWearHomeStatusHolder is null");
            return;
        }
        int i = message.arg1;
        if (this.mActivity.l) {
            this.n.dmF_().setVisibility(0);
            this.n.b().setText(jgq.d().d(i));
            this.n.c().setText(this.f16067a.getResources().getString(R.string._2130845101_res_0x7f021dad));
            if (i == 240) {
                t();
                return;
            }
            return;
        }
        this.n.dmF_().setVisibility(8);
    }

    private void t() {
        LogUtil.a("WearHomeStatusCard", "showBoltLowTemperatureAndBatteryDialog. ");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
        builder.e(String.format(Locale.ROOT, this.mContext.getString(R.string._2130846708_res_0x7f0223f4), pep.b(this.mContext, this.mActivity.b.getDeviceIdentify(), true))).czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: pcw.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WearHomeStatusCard", "showBoltLowTemperatureAndBatteryDialog click.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public void e() {
        this.mActivity.djG_().removeMessages(1006);
        if (this.n == null) {
            LogUtil.h("WearHomeStatusCard", "handlerTimeOutWearPlace mWearHomeStatusHolder is null");
        } else {
            if (this.mActivity.l) {
                this.n.dmF_().setVisibility(0);
                this.n.b().setText(this.f16067a.getResources().getString(R.string._2130845616_res_0x7f021fb0));
                this.n.c().setText(this.f16067a.getResources().getString(R.string._2130845101_res_0x7f021dad));
                return;
            }
            this.n.dmF_().setVisibility(8);
        }
    }

    private void w() {
        LogUtil.a("MainUI", "isDeviceOTA :", Integer.valueOf(jkj.d().c(this.mActivity.g)));
        if (jkj.d().c(this.mActivity.g) == 6) {
            n();
            return;
        }
        this.n.k().setVisibility(8);
        if (cwi.c(this.mActivity.b, 112)) {
            this.n.dmB_().setVisibility(0);
        } else {
            this.n.dmy_().setVisibility(0);
        }
        if (this.mActivity.l) {
            this.n.dmF_().setVisibility(0);
        }
        this.n.dmA_().setVisibility(8);
        this.n.j().setText(this.f16067a.getResources().getString(R.string.IDS_wearhome_connected));
        if (this.mActivity.b != null) {
            boolean z = this.mActivity.b.getPowerSaveModel() == 1;
            boolean z2 = jpo.c(this.mActivity.b.getDeviceIdentify()) == 2;
            if (z) {
                if (pep.d(this.mActivity.b.getProductType())) {
                    this.n.i().setText(this.f16067a.getResources().getString(R.string.IDS_device_power_saving));
                    this.n.q().setText(this.f16067a.getResources().getString(R.string._2130844086_res_0x7f0219b6));
                } else {
                    this.n.i().setText(this.f16067a.getResources().getString(R.string._2130846511_res_0x7f02232f));
                    this.n.q().setText(this.f16067a.getResources().getString(R.string._2130846512_res_0x7f022330));
                }
                this.n.i().setVisibility(0);
                this.n.q().setVisibility(0);
            } else if (z2) {
                this.n.i().setText(this.f16067a.getResources().getString(R.string.IDS_device_family_code));
                this.n.i().setVisibility(0);
                this.n.q().setVisibility(8);
            } else {
                this.n.i().setVisibility(8);
                this.n.q().setVisibility(8);
            }
        }
        oyf.c(0);
        this.mActivity.djG_().sendEmptyMessage(1002);
    }

    private void n() {
        this.n.k().setVisibility(8);
        this.n.dmy_().setVisibility(8);
        this.n.dmF_().setVisibility(8);
        this.n.dmB_().setVisibility(8);
        this.n.dmA_().setVisibility(0);
        this.n.j().setText(this.f16067a.getResources().getString(R.string._2130841463_res_0x7f020f77));
        this.n.dmA_().setVisibility(0);
    }

    private void k() {
        this.n.k().setVisibility(8);
        this.n.dmy_().setVisibility(8);
        this.n.dmF_().setVisibility(8);
        this.n.dmB_().setVisibility(8);
        this.n.dmA_().setVisibility(0);
        if (!TextUtils.isEmpty(this.e)) {
            this.n.i().setVisibility(0);
            this.n.i().setText(this.e);
        } else {
            this.n.i().setVisibility(8);
        }
        this.n.q().setVisibility(8);
        this.n.j().setText(this.f16067a.getResources().getString(R.string.IDS_device_connecting_21));
    }

    private void dlI_(View view) {
        if (this.mActivity == null || this.mActivity.isDestroyed() || this.mActivity.isFinishing()) {
            return;
        }
        LogUtil.a("WearHomeStatusCard", "enter createAlertDialog");
        if (this.j == null) {
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.mContext);
            builder.a(this.f16067a.getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).cyp_(view).cyo_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: pdb
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    pcw.dlJ_(dialogInterface, i);
                }
            });
            CustomAlertDialog c = builder.c();
            this.j = c;
            c.setCancelable(true);
        }
        this.j.show();
    }

    static /* synthetic */ void dlJ_(DialogInterface dialogInterface, int i) {
        LogUtil.a("WearHomeStatusCard", "showAlertDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("MainUI", 0, "Enter processConnectedStateChange ");
        if (deviceInfo != null && BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("MainUI", 0, "processConnectedStateChange BT switch is false!");
            r();
        } else {
            LogUtil.a("MainUI", 0, "processConnectedStateChange close BT Dialog!");
            m();
        }
    }

    private void r() {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.mContext) { // from class: pcw.9
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WearHomeStatusCard", "preShowOpenSystemBluetoothDialog nearby permission granted");
                    pcw.this.y();
                }
            });
        } else {
            y();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter showOpenSystemBluetoothDialog");
        if (oxa.a() == null || oxa.a().g()) {
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "showOpenSystemBluetoothDialog return");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.i;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                return;
            }
            this.i.show();
            return;
        }
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "showOpenSystemBluetoothDialog app");
        String string = this.mActivity.getResources().getString(R.string.IDS_app_name_health);
        String format = String.format(Locale.ENGLISH, this.mActivity.getResources().getString(R.string.IDS_device_bt_open_request_info), string);
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(format).a(this.mActivity.getResources().getString(R.string.IDS_device_bt_open_info_tip)).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: pdh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pcw.this.dlR_(view);
            }
        }).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: pdn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pcw.this.dlQ_(view);
            }
        }).e();
        this.i = e;
        e.setCancelable(false);
        this.i.show();
    }

    /* synthetic */ void dlR_(View view) {
        if (oxa.a() != null && oxa.a().g()) {
            oxa.a().c(false);
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.i;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.i = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlQ_(View view) {
        LogUtil.a("MainUI", 0, "WearHomeStatusCard", "showOpenSystemBluetoothDialog positive.");
        if (oxa.a() != null && !oxa.a().g()) {
            this.d = new BtSwitchStateCallback() { // from class: pcz
                @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
                public final void onBtSwitchStateCallback(int i) {
                    pcw.this.a(i);
                }
            };
            iyl.d().d(this.d);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void a(int i) {
        LogUtil.a("WearHomeStatusCard", "showOpenSystemBluetoothDialog switchState :", Integer.valueOf(i));
        if (i == 3) {
            iyl.d().e(this.d);
            i();
        }
    }

    private void m() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.i;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.mActivity == null) {
            LogUtil.h("MainUI", 0, "WearHomeStatusCard", "received，mActivity is empty");
        } else if (this.mActivity.g == null) {
            LogUtil.h("MainUI", 0, "WearHomeStatusCard", "received，mActivity.mDeviceMac is empty");
        } else {
            if (this.mActivity.g.equals(str)) {
                return;
            }
            LogUtil.a("MainUI", 0, "WearHomeStatusCard", "received，deviceMac:", iyl.d().e(str), "mActivity.mDeviceMac is:", iyl.d().e(this.mActivity.g));
        }
    }

    static class a implements WearPlaceCallback {
        private WeakReference<WearHomeActivity> e;

        a(WearHomeActivity wearHomeActivity) {
            this.e = new WeakReference<>(wearHomeActivity);
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback
        public void onResponse(DeviceInfo deviceInfo, int i) {
            WearHomeActivity wearHomeActivity = this.e.get();
            if (deviceInfo == null || TextUtils.isEmpty(wearHomeActivity.g)) {
                LogUtil.h("WearHomeStatusCard", "WearHomePlaceCallback enter deviceInfo = null or mDeviceMac = null");
                return;
            }
            if (jkj.d().c(wearHomeActivity.g) == 6) {
                LogUtil.a("MainUI", 0, "WearHomeStatusCard", "Enter refreshWearPlace device is OTA");
                return;
            }
            if (TextUtils.equals(deviceInfo.getDeviceIdentify(), wearHomeActivity.g)) {
                if (wearHomeActivity.djG_() == null) {
                    LogUtil.h("WearHomeStatusCard", "activity.getMessageHandler() == null");
                    return;
                }
                LogUtil.a("WearHomeStatusCard", "WearHomePlaceCallback onResponse");
                Message obtainMessage = wearHomeActivity.djG_().obtainMessage();
                obtainMessage.what = 1005;
                obtainMessage.arg1 = i;
                wearHomeActivity.djG_().removeMessages(1005);
                wearHomeActivity.djG_().removeMessages(1006);
                wearHomeActivity.djG_().sendMessage(obtainMessage);
            }
        }
    }
}
