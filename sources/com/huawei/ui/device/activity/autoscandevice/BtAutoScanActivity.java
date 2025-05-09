package com.huawei.ui.device.activity.autoscandevice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.datatype.GoogleDeviceCache;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.device.activity.autoscandevice.GalleryAdapter;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import defpackage.bgb;
import defpackage.bkw;
import defpackage.bmw;
import defpackage.cve;
import defpackage.ixx;
import defpackage.izy;
import defpackage.jdi;
import defpackage.jeg;
import defpackage.jfu;
import defpackage.jjb;
import defpackage.koq;
import defpackage.nrj;
import defpackage.nsn;
import defpackage.nue;
import defpackage.oae;
import defpackage.oaf;
import defpackage.obb;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class BtAutoScanActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f9060a = {"android.permission.READ_PHONE_STATE"};
    private HealthCheckBox b;
    private CustomAlertDialog d;
    private CustomAlertDialog.Builder e;
    private ActivityResultLauncher<IntentSenderRequest> g;
    private oae i;
    private GalleryAdapter j;
    private GuideInteractors k;
    private int l;
    private String m;
    private HealthRecycleView n;
    private int o = 0;
    private ArrayList<BluetoothDevice> c = new ArrayList<>(16);
    private HashMap<String, Integer> p = new HashMap<>(0);
    private int s = 0;
    private String f = null;
    private String h = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("BtAutoScanActivity", "onCreate");
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        this.i = oae.c(this);
        this.k = new GuideInteractors(getApplicationContext());
        b();
        a();
        overridePendingTransition(R.anim._2130772071_res_0x7f010067, 0);
    }

    private void b() {
        this.g = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                BtAutoScanActivity.this.d((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void d(ActivityResult activityResult) {
        bmw.e(100112, this.h, String.valueOf(activityResult.getResultCode()), String.valueOf(100007));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            d(this.l, this.m);
        } else {
            obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == -1) {
                        String str = BtAutoScanActivity.this.f;
                        BtAutoScanActivity btAutoScanActivity = BtAutoScanActivity.this;
                        obb.c(str, btAutoScanActivity, btAutoScanActivity.g, 2);
                    } else {
                        BtAutoScanActivity btAutoScanActivity2 = BtAutoScanActivity.this;
                        btAutoScanActivity2.d(btAutoScanActivity2.l, BtAutoScanActivity.this.m);
                    }
                }
            }, this);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        LogUtil.a("BtAutoScanActivity", "initViewTahiti");
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.o = intent.getIntExtra(TemplateStyleRecord.STYLE, 0);
            if (intent.getSerializableExtra("DEVICE_SCAN_LIST") instanceof ArrayList) {
                this.c = (ArrayList) intent.getSerializableExtra("DEVICE_SCAN_LIST");
            }
            try {
                this.c = intent.getParcelableArrayListExtra("bluetooth_list");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("BtAutoScanActivity", "initView ArrayIndexOutOfBoundsException is ", e.getMessage());
            } catch (IndexOutOfBoundsException e2) {
                LogUtil.b("BtAutoScanActivity", "initView IndexOutOfBoundsException message is ", e2.getMessage());
            }
            if (intent.getSerializableExtra("device_rssi_map") instanceof HashMap) {
                this.p = (HashMap) intent.getSerializableExtra("device_rssi_map");
            }
            LogUtil.a("BtAutoScanActivity", "style: ", Integer.valueOf(this.o), " content: ", Integer.valueOf(intent.getIntExtra(TemplateStyleRecord.STYLE, 0)));
            if (this.o == 1) {
                d();
            } else {
                LogUtil.h("BtAutoScanActivity", "initView default");
            }
            setFinishOnTouchOutside(false);
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = defaultDisplay.getWidth();
            getWindow().setAttributes(attributes);
            return;
        }
        LogUtil.h("BtAutoScanActivity", "initView intent is null.");
    }

    private void d() {
        LogUtil.a("BtAutoScanActivity", "mBtDeviceList is ", this.c);
        LayoutInflater layoutInflater = getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) getSystemService("layout_inflater") : null;
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.dialog_auto_listview, (ViewGroup) null);
            this.e = new CustomAlertDialog.Builder(this);
            if (inflate == null) {
                LogUtil.h("BtAutoScanActivity", "initDeviceListDialog view is null.");
                return;
            }
            this.n = (HealthRecycleView) inflate.findViewById(R.id.device_list);
            this.b = (HealthCheckBox) inflate.findViewById(R.id.agree_checkbox);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(0);
            this.n.setLayoutManager(linearLayoutManager);
            this.n.a(false);
            this.n.d(false);
            this.b.setChecked(this.k.b());
            GalleryAdapter galleryAdapter = new GalleryAdapter(BaseApplication.getContext(), this.c);
            this.j = galleryAdapter;
            this.n.setAdapter(galleryAdapter);
            this.j.c(new GalleryAdapter.OnItemClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.5
                @Override // com.huawei.ui.device.activity.autoscandevice.GalleryAdapter.OnItemClickListener
                public void onClick(int i) {
                    BtAutoScanActivity.this.s = i;
                }
            });
            this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        BtAutoScanActivity.this.k.c(true);
                    } else {
                        BtAutoScanActivity.this.k.c(false);
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            cPl_(inflate);
        }
    }

    private void cPl_(View view) {
        this.e.cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BtAutoScanActivity.this.finish();
                BtAutoScanActivity.this.overridePendingTransition(0, R.anim._2130772072_res_0x7f010068);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.e.cyo_(R.string._2130841388_res_0x7f020f2c, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (BtAutoScanActivity.this.s < BtAutoScanActivity.this.c.size()) {
                    oaf.b(BaseApplication.getContext()).h(((BluetoothDevice) BtAutoScanActivity.this.c.get(BtAutoScanActivity.this.s)).getAddress());
                    LogUtil.a("BtAutoScanActivity", "start Pair mSelectPosition is ", Integer.valueOf(BtAutoScanActivity.this.s));
                    BtAutoScanActivity btAutoScanActivity = BtAutoScanActivity.this;
                    btAutoScanActivity.b(btAutoScanActivity.s);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.e.cyp_(view);
        CustomAlertDialog c = this.e.c();
        this.d = c;
        if (c != null) {
            c.setCancelable(false);
            this.d.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i < 0 || this.c.size() <= 0) {
            return;
        }
        try {
            this.f = this.c.get(i).getAddress();
            String name = this.c.get(i).getName();
            this.h = name;
            if (name == null) {
                LogUtil.h("BtAutoScanActivity", "startConnectDevice mDeviceName is null.");
                finish();
                return;
            }
            if (!NotificationContentProviderUtil.e()) {
                LogUtil.a("BtAutoScanActivity", "CommonConstants.NOTIFICATION_INITIALIZE");
                if (jjb.b().c()) {
                    NotificationContentProviderUtil.a(1);
                } else {
                    NotificationContentProviderUtil.a(0);
                }
            }
            this.l = oae.c(this.h);
            String b = this.i.b(oae.c(this.h));
            this.m = b;
            e(this.l, b, i);
        } catch (SecurityException e) {
            LogUtil.b("BtAutoScanActivity", "startConnectDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void e(int i, String str, int i2) {
        LogUtil.a("BtAutoScanActivity", "preEnterDevicePairActivity deviceType: ", Integer.valueOf(i), "deviceName: ", str);
        if (!b(i, str, i2)) {
            LogUtil.h("BtAutoScanActivity", "preEnterDevicePairActivity permission has not.");
        } else {
            d(i, str, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, int i2) {
        int i3;
        LogUtil.a("BtAutoScanActivity", "enterDevicePairGuide deviceType: ", Integer.valueOf(i), "deviceName: ", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010049.value(), new HashMap(16), 0);
        try {
            if (jfu.f(i)) {
                GoogleDeviceCache.QrBleCache qrBleCache = new GoogleDeviceCache.QrBleCache();
                if (i2 >= 0 && i2 < this.c.size()) {
                    BluetoothDevice bluetoothDevice = this.c.get(i2);
                    HashMap<String, Integer> hashMap = this.p;
                    if (hashMap != null) {
                        LogUtil.a("BtAutoScanActivity", "enterDevicePairGuide mRssiMap:", hashMap);
                        i3 = this.p.get(bluetoothDevice.getName()).intValue();
                    } else {
                        i3 = -1;
                    }
                    qrBleCache.setBluetoothDevice(bluetoothDevice);
                    qrBleCache.setRssi(i3);
                    qrBleCache.setTime(System.currentTimeMillis());
                    GoogleDeviceCache.getInstance().saveCache(qrBleCache);
                    if (nrj.b()) {
                        nrj.cKa_(this);
                        return;
                    } else {
                        nrj.cKb_(this, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.6
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                LogUtil.a("BtAutoScanActivity", "uninstallShowDialog negative.");
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.8
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                LogUtil.a("BtAutoScanActivity", "uninstallShowDialog positive");
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        });
                        return;
                    }
                }
                return;
            }
            g();
        } catch (SecurityException e) {
            LogUtil.b("BtAutoScanActivity", "enterDevicePairGuide SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void g() {
        if (obb.a(this, 2) && this.l != 75) {
            bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.f));
            obb.c(this.f, this, this.g, 2);
            return;
        }
        d(this.l, this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        LogUtil.a("BtAutoScanActivity", "jumpToPairPage mDeviceName: ", this.h);
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(this.h);
        if (koq.b(deviceInfoByBluetooth) || deviceInfoByBluetooth.get(0) == null) {
            LogUtil.h("BtAutoScanActivity", "jumpToPairPage infoBeanList is empty");
            finish();
            return;
        }
        List<String> ac = deviceInfoByBluetooth.get(0).ac();
        String r = deviceInfoByBluetooth.get(0).r();
        if (bgb.d().isSupportH5Pair(this.h)) {
            bgb.d().startPair(this, StartPairOption.builder().c(this.f).e(ac).d(this.h).b("wear_watch").a(false).c());
            finish();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) DevicePairGuideActivity.class);
        if (ac instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) ac);
        }
        intent.putExtra("kind_id", r);
        intent.putExtra("pairGuideProductType", i);
        intent.putExtra("pairGuideProductName", str);
        intent.putExtra("pairGuideFromScanList", true);
        intent.putExtra("pairGuideSelectName", this.h);
        intent.putExtra("pairGuideSelectAddress", this.f);
        intent.putExtra("isHeartRateDevice", 0);
        intent.putExtra("DOWNLOAD_RESOURCE", true);
        intent.putExtra("pairGuideDeviceMode", 100007);
        startActivityForResult(intent, 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        int i3;
        LogUtil.a("BtAutoScanActivity", "onActivityResult requestCode ", Integer.valueOf(i), " resultCode: ", Integer.valueOf(i2));
        super.onActivityResult(i, i2, intent);
        String str = this.h;
        if (str != null) {
            i3 = oae.c(str);
            LogUtil.a("BtAutoScanActivity", "mDeviceName is ", this.h, " deviceType is ", Integer.valueOf(i3));
        } else {
            i3 = -1;
        }
        if (i == 1) {
            if (i2 == 2) {
                setResult(2);
                if (intent != null) {
                    nue.cNU_(intent, this, nue.e(i2, true, i3, true));
                }
            }
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.o == 1) {
            izy.b(this).c();
        }
        super.onDestroy();
        CommonUtil.a(BaseApplication.getContext());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim._2130772072_res_0x7f010068);
        super.onBackPressed();
    }

    private boolean b(int i, String str, int i2) {
        Context context = BaseApplication.getContext();
        String[] strArr = f9060a;
        if (jdi.c(context, strArr)) {
            return true;
        }
        c(strArr, i, str, i2);
        return false;
    }

    private void c(String[] strArr, final int i, final String str, final int i2) {
        boolean c = jdi.c(BaseApplication.getContext(), strArr);
        LogUtil.a("BtAutoScanActivity", "requestPermissions() hasPermissionNeeded is ", Boolean.valueOf(c));
        if (!c) {
            jdi.bFM_(this, strArr, new PermissionsResultAction() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.9
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("BtAutoScanActivity", "requestPermissions() permission onGranted()");
                    BtAutoScanActivity.this.d(i, str, i2);
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str2) {
                    LogUtil.a("BtAutoScanActivity", "requestPermissions() permission onDenied()");
                }
            }, 1000);
        } else {
            LogUtil.a("BtAutoScanActivity", "requestPermissions() permission if (!hasPermissionNeeded) else.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        LogUtil.a("BtAutoScanActivity", "requestCode: ", Integer.valueOf(i));
        jeg.d().d(strArr, iArr);
        b(i, strArr, iArr);
    }

    private void b(int i, String[] strArr, int[] iArr) {
        if (i == 1000) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (f9060a[0].equals(strArr[i2]) && iArr[i2] != 0) {
                    c();
                } else {
                    LogUtil.a("BtAutoScanActivity", "no show dialog");
                }
            }
        }
    }

    private void c() {
        LogUtil.a("BtAutoScanActivity", "telephone permision reject");
        if (isFinishing() || isDestroyed()) {
            LogUtil.a("BtAutoScanActivity", "mainActivityContext finish");
            return;
        }
        LogUtil.a("BtAutoScanActivity", "ready show dialog.");
        e();
        LogUtil.a("BtAutoScanActivity", "show success.");
    }

    private void e() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(getString(R$string.IDS_hwh_home_other_permissions_title)).e(getString(R.string._2130837673_res_0x7f0200a9)).cyV_(getString(R.string._2130841425_res_0x7f020f51).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nsn.ak(BtAutoScanActivity.this);
                if (!BtAutoScanActivity.this.isFinishing()) {
                    BtAutoScanActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(getString(R.string._2130839505_res_0x7f0207d1).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("BtAutoScanActivity", "handlePermission negative.");
                if (!BtAutoScanActivity.this.isFinishing()) {
                    BtAutoScanActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
