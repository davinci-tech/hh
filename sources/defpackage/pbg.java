package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.ui.privacy.HonorDevicePrivacyActivity;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.UnbindDeviceRequest;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomStateDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageResult;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class pbg implements IBaseResponseCallback {
    private Context c;
    private WearHomeActivity d;
    private CustomAlertDialog e;
    private AnimationDrawable f;
    private CustomAlertDialog g;
    private AnimationDrawable h;
    private CustomAlertDialog j;
    private NoTitleCustomAlertDialog k;
    private CustomTextAlertDialog l;
    private CustomTextAlertDialog m;
    private CommonDialog21 n;
    private NoTitleCustomAlertDialog b = null;

    /* renamed from: a, reason: collision with root package name */
    private final BtSwitchStateCallback f16040a = new BtSwitchStateCallback() { // from class: pbg.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            if (pbg.this.d == null || pbg.this.d.c == null) {
                return;
            }
            pbg.this.d.c.c(pbg.this.f16040a);
            LogUtil.a("WearHomeMainAction", "onBtSwitchStateCallback bluetoothSwitchState:", Integer.valueOf(i));
            if (i == 3) {
                pbg.this.e();
            }
        }
    };
    private EventBus.ICallback i = new EventBus.ICallback() { // from class: pbg.15
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar == null) {
                LogUtil.h("WearHomeMainAction", "event is null");
                return;
            }
            Bundle Kl_ = bVar.Kl_();
            if (Kl_ == null) {
                LogUtil.h("WearHomeMainAction", "bundle is null");
                return;
            }
            if ("sign_account_hms_login_state_action".equals(bVar.e())) {
                pbg.this.c();
                if (pbg.this.d.djG_() != null) {
                    pbg.this.d.djG_().removeMessages(300005);
                    pbg.this.d.djG_().removeMessages(300006);
                }
                String string = Kl_.getString("sign_account_hms_login_state_key");
                if (string == null) {
                    LogUtil.h("WearHomeMainAction", "state is null");
                    return;
                }
                if ("-1".equals(string)) {
                    LogUtil.h("WearHomeMainAction", "state is default");
                    pbg.this.d(2);
                    return;
                }
                if (!"1".equals(string) && pbg.this.d.djG_() != null) {
                    pbg.this.d.djG_().sendEmptyMessageDelayed(300005, 2000L);
                }
                pbg.this.d.d(jds.c(string, 10));
                LogUtil.a("WearHomeMainAction", "action  state is :", bVar.e(), string);
            }
        }
    };
    private jqi o = jqi.a();

    static class a implements ServiceConnectCallback {
        private String d;

        a(String str) {
            this.d = str;
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            LogUtil.a("WearHomeMainAction", "profile connected");
            ProfileAgent.PROFILE_AGENT.setConnected(true);
            ThreadPoolManager.d().execute(new Runnable() { // from class: pbg.a.3
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.d != null) {
                        new jqf().a(a.this.d);
                        ProfileAgent.PROFILE_AGENT.disconnectProfile();
                    } else {
                        LogUtil.h("WearHomeMainAction", "deviceSecUuid is null");
                    }
                }
            });
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            ProfileAgent.PROFILE_AGENT.setConnected(false);
            LogUtil.a("WearHomeMainAction", "profile disconnected");
        }
    }

    public pbg(Context context, WearHomeActivity wearHomeActivity) {
        this.d = wearHomeActivity;
        this.c = context;
        jgf.b().d(this);
    }

    public void c(String str, String str2, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.e("R_GRS_WearHomeMainAction", "openLegalActivity deviceInfo is null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(this.c, WearHomeLegalInformationActivity.class);
            intent.putExtra("device_id", str);
            intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str2);
            intent.putExtra("product_type", deviceInfo.getProductType());
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeMainAction", "ActivityNotFoundException WearHomeLegalInformationActivity");
        }
    }

    public void d() {
        this.d.startActivityForResult(new Intent(this.c, (Class<?>) HonorDevicePrivacyActivity.class), 10008);
    }

    public void d(String str) {
        int i;
        LogUtil.a("WearHomeMainAction", "enter WearHomeMainAction startDetection");
        if ("LOCAL_TYPE".equals(str)) {
            i = 1;
        } else if ("REMOTE_TYPE".equals(str)) {
            i = 2;
        } else {
            LogUtil.c("WearHomeMainAction", "startConnectDetection else");
            i = -1;
        }
        jge.b().c(this.d.b, i);
    }

    public void c(boolean z) {
        ResolveInfo resolveActivity;
        try {
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010046.value(), new HashMap(16), 0);
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(KakaConstants.SCHEME_TEL + (z ? "95030" : BaseApplication.getContext().getString(R.string._2130846302_res_0x7f02225e))));
            PackageManager packageManager = this.d.getPackageManager();
            if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
                return;
            }
            ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
            intent.setComponent(componentName);
            String packageName = componentName.getPackageName();
            WearHomeActivity wearHomeActivity = this.d;
            nsn.cLM_(intent, packageName, wearHomeActivity, wearHomeActivity.getString(R.string._2130841230_res_0x7f020e8e));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeMainAction", "callHotline() ActivityNotFoundException");
        }
    }

    public void a() {
        p();
        this.d.x.execute(new Runnable() { // from class: pbg.20
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("WearHomeMainAction", "openHuaFenClubActivity enter");
                final String str = GRSManager.a(BaseApplication.getContext()).getUrl("domainClubHuawei") + "/forum.php?mod=forumdisplay&fid=4301";
                DeviceInfo b = oxa.a().b(pbg.this.d.g);
                if (b != null) {
                    int productType = b.getProductType();
                    LogUtil.a("WearHomeMainAction", "openHuaFenClubActivity() productType ", Integer.valueOf(productType));
                    if (!cvt.c(productType)) {
                        str = pew.d().e(b);
                    }
                }
                LogUtil.a("WearHomeMainAction", "startWebViewActivity() url is ", str, ", jumpModeKey is ", 1);
                pbg.this.d.runOnUiThread(new Runnable() { // from class: pbg.20.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("WearHomeMainAction", "Handler");
                        pbg.this.g(str);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        try {
            Intent intent = new Intent(this.c, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", str);
            intent.putExtra(Constants.JUMP_MODE_KEY, 1);
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeMainAction", "ActivityNotFoundException WebViewActivity");
        }
    }

    private void p() {
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010042.value(), new HashMap(16), 0);
    }

    public void b() {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (Build.VERSION.SDK_INT > 30) {
                PermissionUtil.b(this.d, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.d) { // from class: pbg.16
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.a("WearHomeMainAction", "checkBluetoothState nearby permission granted");
                        pbg.this.w();
                    }
                });
                return;
            } else {
                w();
                return;
            }
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.c.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: pbh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.this.dlc_(view);
            }
        }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: pbd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.this.dld_(view);
            }
        }).e();
        this.k = e;
        e.setCancelable(false);
        this.k.show();
    }

    /* synthetic */ void dlc_(View view) {
        LogUtil.a("WearHomeMainAction", "user choose open bluetooth");
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity == null || wearHomeActivity.c == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.d.c.d(this.f16040a);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void dld_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        noTitleCustomAlertDialog.dismiss();
        this.k = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e() {
        LogUtil.a("WearHomeMainAction", 0, "WearHomeMainAction", "Enter unbindListener");
        LogUtil.a("WearHomeMainAction", "Enter unbind ota status:", Integer.valueOf(jkj.d().c(this.d.g)));
        if (jgf.b().a(this.d.g)) {
            v();
        } else {
            q();
        }
    }

    private void q() {
        this.o.getSwitchSetting("intelligent_home_linkage", this.d.g, new IBaseResponseCallback() { // from class: pbg.19
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str;
                boolean z = false;
                if (i == 0 && (obj instanceof String)) {
                    String str2 = (String) obj;
                    if (str2.contains("&&")) {
                        String[] split = str2.split("&&");
                        LogUtil.a("WearHomeMainAction", "getIntelligentStatus splits length is ", Integer.valueOf(split.length));
                        if (split.length == 5) {
                            str = split[0];
                            z = true;
                            LogUtil.a("WearHomeMainAction", "getIntelligentStatus isEnable = ", Boolean.valueOf(z));
                            pbg.this.e(z, str);
                        }
                    }
                }
                str = "";
                LogUtil.a("WearHomeMainAction", "getIntelligentStatus isEnable = ", Boolean.valueOf(z));
                pbg.this.e(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z, final String str) {
        this.d.runOnUiThread(new Runnable() { // from class: pbg.18
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("WearHomeMainAction", "updateIntelligentStatus enter");
                if (z) {
                    pbg.this.j(str);
                } else {
                    pbg.this.x();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final String str) {
        LogUtil.a("WearHomeMainAction", 1, "WearHomeMainAction", "enter showUnbindIntelligentHomeDialog()");
        if (this.l == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.c).b(R.string.IDS_device_wear_home_delete_device).e(String.format(Locale.ENGLISH, this.c.getString(R.string.IDS_device_to_intelligent_home_linkage_remove_pairing), this.c.getString(R.string.IDS_app_name_health), this.c.getString(R.string.IDS_device_intelligent_home))).cyU_(R.string._2130841438_res_0x7f020f5e, new View.OnClickListener() { // from class: pbl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pbg.this.dlf_(str, view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: pbi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pbg.this.dlg_(view);
                }
            }).a();
            this.l = a2;
            a2.setCancelable(false);
            this.l.show();
        }
    }

    /* synthetic */ void dlf_(String str, View view) {
        b(str);
        this.l.dismiss();
        this.l = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlg_(View view) {
        LogUtil.c("WearHomeMainAction", "showLoginFail cancel click");
        this.l.dismiss();
        this.l = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(String str) {
        ac();
        LogUtil.c("WearHomeMainAction", "showUnbindDialog ok click");
        jgy.e(BaseApplication.getContext()).a(str, new FitnessCloudCallBack() { // from class: pbg.17
            @Override // com.huawei.callback.FitnessCloudCallBack
            public void onResponce(Object obj) {
                if (obj instanceof ReleaseDeviceLinkageResponse) {
                    ReleaseDeviceLinkageResponse releaseDeviceLinkageResponse = (ReleaseDeviceLinkageResponse) obj;
                    if (releaseDeviceLinkageResponse.getResultCode().intValue() == 0) {
                        pbg.this.o.setSwitchSetting("intelligent_home_linkage", null, pbg.this.d.g, null);
                        LogUtil.a("WearHomeMainAction", "releaseDeviceLinkage is success ", releaseDeviceLinkageResponse.getResultCode(), com.huawei.openalliance.ad.constant.Constants.LINK, releaseDeviceLinkageResponse.getResultDesc());
                        return;
                    } else {
                        LogUtil.h("WearHomeMainAction", "releaseDeviceLinkage is fail ", releaseDeviceLinkageResponse.getResultCode() + com.huawei.openalliance.ad.constant.Constants.LINK, releaseDeviceLinkageResponse.getResultDesc());
                        return;
                    }
                }
                LogUtil.h("WearHomeMainAction", "releaseDeviceLinkage is fail");
            }
        });
    }

    public void j() {
        LogUtil.a("WearHomeMainAction", "showFirstEcgServiceDialog enter");
        if (this.g == null) {
            View inflate = View.inflate(this.c, R.layout.dialog_first_ecg_service_tip, null);
            ((HealthTextView) mfm.cgM_(inflate, R.id.ecg_collect_text)).setText(this.c.getResources().getString(R.string._2130838365_res_0x7f02035d, "599"));
            CustomAlertDialog c = new CustomAlertDialog.Builder(this.c).cyp_(inflate).cyn_(R.string._2130838404_res_0x7f020384, new DialogInterface.OnClickListener() { // from class: pbg.22
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    pep.a(pbg.this.c, 3);
                    LogUtil.a("WearHomeMainAction", "showFirstEcgServiceDialog, user click cancel");
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).cyo_(R.string._2130838367_res_0x7f02035f, new DialogInterface.OnClickListener() { // from class: pbg.21
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    pep.a(pbg.this.c, 2);
                    pbg.this.ad();
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).c();
            this.g = c;
            HealthButton healthButton = (HealthButton) c.findViewById(R.id.dialog_btn_positive);
            healthButton.setTextColor(this.c.getResources().getColor(R.color._2131298273_res_0x7f0907e1, null));
            healthButton.setBackgroundResource(R.drawable.button_background_emphasize);
            ((HealthButtonBarLayout) this.g.findViewById(R.id.button_bar)).setDividerDrawable(this.c.getResources().getDrawable(R.drawable._2131427926_res_0x7f0b0256, null));
            this.g.setCancelable(false);
        }
        if (this.g.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.g.show();
        jfq.c().setSharedPreference("KEY_SHOW_ECG_SERVICE_ACTIVATION_TIP_FLAG", String.valueOf(System.currentTimeMillis()), null);
        pep.a(this.c, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        boolean z = false;
        boolean isSupportEcgAuth = this.d.f9644a != null ? this.d.f9644a.isSupportEcgAuth() : false;
        if (cwi.c(this.d.b, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce")) {
            z = true;
        }
        boolean o = Utils.o();
        LogUtil.a("WearHomeMainAction", "startEcgForParamsByCapability isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isOversea:", Boolean.valueOf(o));
        if (z) {
            LogUtil.a("WearHomeMainAction", "startEcgForParamsByCapability start ecg analysis");
            mxv.b(BaseApplication.getContext(), "com.huawei.health.h5.ecgce", 1, "interpretation");
        } else {
            if (!isSupportEcgAuth || o) {
                return;
            }
            mxv.b(BaseApplication.getContext(), "com.huawei.health.ecg.collection", 1, "interpretation");
        }
    }

    public void e(String str) {
        DeviceInfo a2 = oxa.a().a(str);
        if (a2 != null) {
            if (cvt.d(a2.getProductType())) {
                CustomAlertDialog customAlertDialog = this.e;
                if (customAlertDialog != null && customAlertDialog.isShowing()) {
                    LogUtil.c("WearHomeMainAction", "showWearShoesDialog Already show!");
                    this.d.e(WearHomeActivity.EnumDialog.RATE);
                    return;
                } else {
                    aa();
                    return;
                }
            }
            if (cvt.a(a2.getProductType())) {
                s();
                return;
            } else {
                a(a2);
                return;
            }
        }
        LogUtil.h("WearHomeMainAction", "showDialogRateReminder current device info is null");
    }

    private void a(DeviceInfo deviceInfo) {
        final boolean z;
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            this.d.e(WearHomeActivity.EnumDialog.RATE);
            return;
        }
        final View inflate = LayoutInflater.from(this.c).inflate(R.layout.fragment_set_rate_reminder_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.fragment_set_rate_reminder_dialog_image);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.fragment_set_rate_reminder_dialog_text);
        if (deviceInfo == null) {
            LogUtil.a("WearHomeMainAction", "refresh dialog Support deviceInfo is null , return");
            this.d.e(WearHomeActivity.EnumDialog.RATE);
            return;
        }
        if (deviceInfo.getProductType() == 3 || deviceInfo.getProductType() == 10 || deviceInfo.getProductType() == 8) {
            imageView.setImageResource(R.drawable._2131428622_res_0x7f0b050e);
            healthTextView.setText(R.string._2130842213_res_0x7f021265);
        } else if (d(deviceInfo)) {
            imageView.setImageResource(R.drawable._2131428621_res_0x7f0b050d);
            healthTextView.setText(R.string._2130842212_res_0x7f021264);
        } else {
            if (cvt.b(deviceInfo.getProductType())) {
                imageView.setImageResource(R.mipmap._2131821448_res_0x7f110388);
                healthTextView.setText(R.string._2130842791_res_0x7f0214a7);
                z = false;
                ThreadPoolManager.d().execute(new Runnable() { // from class: pbg.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z && pbg.this.d.b != null) {
                            if (cwf.i(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(pbg.this.d.b.getProductType())))) {
                                LogUtil.a("WearHomeMainAction", "exist show guide page config");
                                return;
                            }
                        }
                        pbg.this.d.runOnUiThread(new Runnable() { // from class: pbg.4.2
                            @Override // java.lang.Runnable
                            public void run() {
                                pbg.this.dkZ_(inflate);
                            }
                        });
                    }
                });
            }
            if (!dkT_(deviceInfo.getProductType(), imageView, healthTextView)) {
                this.d.e(WearHomeActivity.EnumDialog.RATE);
                return;
            }
        }
        z = true;
        ThreadPoolManager.d().execute(new Runnable() { // from class: pbg.4
            @Override // java.lang.Runnable
            public void run() {
                if (z && pbg.this.d.b != null) {
                    if (cwf.i(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(pbg.this.d.b.getProductType())))) {
                        LogUtil.a("WearHomeMainAction", "exist show guide page config");
                        return;
                    }
                }
                pbg.this.d.runOnUiThread(new Runnable() { // from class: pbg.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        pbg.this.dkZ_(inflate);
                    }
                });
            }
        });
    }

    private boolean d(DeviceInfo deviceInfo) {
        return deviceInfo.getProductType() == 13 || deviceInfo.getProductType() == 15 || deviceInfo.getProductType() == 16 || deviceInfo.getProductType() == 12;
    }

    public void h() {
        if (this.d.b == null) {
            LogUtil.h("WearHomeMainAction", "showUserGuideDialog, currentDeviceInfo is null");
        } else {
            final String j = jfu.j(this.d.b.getProductType());
            ThreadPoolManager.d().execute(new Runnable() { // from class: pbg.5
                @Override // java.lang.Runnable
                public void run() {
                    cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
                    if (pluginInfoByUuid == null) {
                        LogUtil.h("WearHomeMainAction", "ezPluginInfo is null");
                        return;
                    }
                    boolean d = ocp.b(pbg.this.d.b, false).d();
                    ArrayList arrayList = new ArrayList();
                    List<List<String>> e = cwf.e(pluginInfoByUuid);
                    if (d) {
                        for (List<String> list : e) {
                            if (list.size() >= 2) {
                                arrayList.add(list.get(1));
                            }
                        }
                    } else {
                        arrayList.addAll(cwf.c(pluginInfoByUuid));
                    }
                    pbg.this.b(j, pluginInfoByUuid, arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, cvc cvcVar, final ArrayList<String> arrayList) {
        final List<List<String>> d = cwf.d(cvcVar);
        final ArrayList<String> a2 = cwf.a(cvcVar);
        WearHomeActivity wearHomeActivity = this.d;
        if (wearHomeActivity != null && wearHomeActivity.b != null) {
            cwf.e(cvcVar, this.d.b.getHiLinkDeviceId(), a2, arrayList, d);
        }
        if (arrayList == null || arrayList.isEmpty() || arrayList.size() != d.size()) {
            LogUtil.h("WearHomeMainAction", "guide description is null, is do not support guide dialog");
        } else {
            final int b = cwf.b(cvcVar);
            this.d.runOnUiThread(new Runnable() { // from class: pbg.2
                @Override // java.lang.Runnable
                public void run() {
                    CustomStateDialog.Builder builder = new CustomStateDialog.Builder(pbg.this.c);
                    builder.e(str).a(a2).b(arrayList).d(d).c(b);
                    CustomStateDialog a3 = builder.a();
                    a3.setCanceledOnTouchOutside(false);
                    if (a3.isShowing() || pbg.this.d.isFinishing()) {
                        return;
                    }
                    a3.show();
                    new GuideInteractors(pbg.this.c).b(dis.b(pbg.this.d.b.getDeviceIdentify()));
                }
            });
        }
    }

    public void i() {
        CustomAlertDialog customAlertDialog = this.j;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.h("WearHomeMainAction", "mEarCustomDialog isShowing");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pbg.3
                @Override // java.lang.Runnable
                public void run() {
                    View inflate = LayoutInflater.from(pbg.this.c).inflate(R.layout.ear_muff_dialog_content_layout, (ViewGroup) null);
                    ((HealthTextView) inflate.findViewById(R.id.ear_muff_title)).setText(R.string._2130844902_res_0x7f021ce6);
                    ((HealthTextView) inflate.findViewById(R.id.ear_muff_content)).setText(R.string._2130844903_res_0x7f021ce7);
                    View inflate2 = LayoutInflater.from(pbg.this.c).inflate(R.layout.ear_muff_dialog_content_layout, (ViewGroup) null);
                    ((HealthTextView) inflate2.findViewById(R.id.ear_muff_title)).setText(R.string._2130844904_res_0x7f021ce8);
                    ((HealthTextView) inflate2.findViewById(R.id.ear_muff_content)).setText(R.string._2130844905_res_0x7f021ce9);
                    pbg.this.dkS_(inflate, inflate2);
                    final View inflate3 = LayoutInflater.from(pbg.this.c).inflate(R.layout.ear_muff_viewpager, (ViewGroup) null);
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(inflate);
                    arrayList.add(inflate2);
                    HealthViewPager healthViewPager = (HealthViewPager) inflate3.findViewById(R.id.viewpager);
                    healthViewPager.setAdapter(new pbp(arrayList));
                    healthViewPager.setCurrentItem(0);
                    healthViewPager.setIsAutoHeight(true);
                    HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate3.findViewById(R.id.indicator);
                    healthDotsPageIndicator.setRtlEnable(true);
                    healthDotsPageIndicator.setViewPager(healthViewPager);
                    pbg.this.c(healthViewPager, (ArrayList<View>) arrayList);
                    if (pbg.this.d != null) {
                        pbg.this.d.runOnUiThread(new Runnable() { // from class: pbg.3.5
                            @Override // java.lang.Runnable
                            public void run() {
                                pbg.this.dkY_(inflate3);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dkS_(View view, View view2) {
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(this.d.b.getProductType()));
        if (pluginInfoByUuid == null) {
            LogUtil.h("WearHomeMainAction", "ezPluginInfo is null");
            return;
        }
        ArrayList<String> c = cwf.c(pluginInfoByUuid, 6);
        if (c == null) {
            LogUtil.h("WearHomeMainAction", "removalList is null");
            return;
        }
        this.h = new AnimationDrawable();
        LogUtil.a("WearHomeMainAction", "removalList size is:", Integer.valueOf(c.size()));
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            Bitmap dkR_ = dkR_(pluginInfoByUuid, it.next());
            if (dkR_ != null) {
                this.h.addFrame(new BitmapDrawable(dkR_), 100);
            }
        }
        ((ImageView) view.findViewById(R.id.ear_muff_image)).setImageDrawable(this.h);
        this.h.setOneShot(false);
        this.h.start();
        ArrayList<String> c2 = cwf.c(pluginInfoByUuid, 5);
        if (c2 == null) {
            LogUtil.h("WearHomeMainAction", "installList is null");
            return;
        }
        this.f = new AnimationDrawable();
        LogUtil.a("WearHomeMainAction", "installList size is:", Integer.valueOf(c2.size()));
        Iterator<String> it2 = c2.iterator();
        while (it2.hasNext()) {
            Bitmap dkR_2 = dkR_(pluginInfoByUuid, it2.next());
            if (dkR_2 != null) {
                this.f.addFrame(new BitmapDrawable(dkR_2), 100);
            }
        }
        ((ImageView) view2.findViewById(R.id.ear_muff_image)).setImageDrawable(this.f);
        this.f.setOneShot(false);
        this.f.start();
    }

    private Bitmap dkR_(cvc cvcVar, String str) {
        return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + str + ".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthViewPager healthViewPager, final ArrayList<View> arrayList) {
        healthViewPager.setOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: pbg.10
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                LogUtil.c("WearHomeMainAction", "onPageScrolled position ", Integer.valueOf(i));
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i < 0 || i >= arrayList.size()) {
                    LogUtil.h("WearHomeMainAction", "onPageSelected position error:", Integer.valueOf(i));
                    return;
                }
                LogUtil.a("WearHomeMainAction", "onPageSelected position ", Integer.valueOf(i));
                if (i == 0) {
                    if (pbg.this.f != null) {
                        pbg.this.f.stop();
                    }
                    if (pbg.this.h != null) {
                        if (pbg.this.h.isRunning()) {
                            pbg.this.h.stop();
                        }
                        pbg.this.h.start();
                        return;
                    }
                    return;
                }
                if (pbg.this.h != null) {
                    pbg.this.h.stop();
                }
                if (pbg.this.f != null) {
                    if (pbg.this.f.isRunning()) {
                        pbg.this.f.stop();
                    }
                    pbg.this.f.start();
                }
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                LogUtil.c("WearHomeMainAction", "in onPageScrollStateChanged", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dkY_(View view) {
        LogUtil.a("WearHomeMainAction", "showDialogEarMuffDialog");
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.c).cyn_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: pbg.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (pbg.this.j != null) {
                    pbg.this.j.dismiss();
                    pbg.this.j = null;
                }
                if (pbg.this.f != null) {
                    pbg.this.f.stop();
                    pbg.this.f = null;
                }
                if (pbg.this.h != null) {
                    pbg.this.h.stop();
                    pbg.this.h = null;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.j = cyn_.c();
        cyn_.cyp_(view);
        this.j.setCancelable(false);
        if (this.j.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.j.show();
    }

    private boolean dkT_(int i, ImageView imageView, HealthTextView healthTextView) {
        if (imageView == null || healthTextView == null) {
            return false;
        }
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        int ah = (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null) ? -1 : pluginInfoByDeviceType.f().ah();
        if (ah == 1) {
            imageView.setImageResource(R.drawable._2131428621_res_0x7f0b050d);
            healthTextView.setText(R.string._2130842212_res_0x7f021264);
            return true;
        }
        if (ah != 2) {
            return false;
        }
        imageView.setImageResource(R.drawable._2131428622_res_0x7f0b050e);
        healthTextView.setText(R.string._2130842213_res_0x7f021265);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dkZ_(View view) {
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.c).cyn_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: pbg.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (pbg.this.e != null) {
                    pbg.this.e.dismiss();
                    pbg.this.e = null;
                }
                pbg.this.d.e(WearHomeActivity.EnumDialog.RATE);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.e = cyn_.c();
        cyn_.cyp_(view);
        this.e.setCancelable(false);
        if (this.e.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.e.show();
    }

    private void s() {
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.c("WearHomeMainAction", "showAw70ProWearWristsDialog Already show!");
            this.d.e(WearHomeActivity.EnumDialog.RATE);
        } else {
            dkX_(new CustomAlertDialog.Builder(this.c).cyn_(R.string._2130841128_res_0x7f020e28, new DialogInterface.OnClickListener() { // from class: pbg.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    pbg.this.aa();
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }), dkW_(1));
        }
    }

    private void dkX_(CustomAlertDialog.Builder builder, View view) {
        this.e = builder.c();
        builder.cyp_(view);
        this.e.setCancelable(false);
        if (this.e.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        dkX_(new CustomAlertDialog.Builder(this.c).cyn_(R.string._2130841128_res_0x7f020e28, new DialogInterface.OnClickListener() { // from class: pbg.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (pbg.this.e != null) {
                    pbg.this.e.dismiss();
                    pbg.this.e = null;
                }
                pbg.this.z();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }), dkW_(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        dkX_(new CustomAlertDialog.Builder(this.c).cyn_(R.string._2130841554_res_0x7f020fd2, new DialogInterface.OnClickListener() { // from class: pbg.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (pbg.this.e != null) {
                    pbg.this.e.dismiss();
                    pbg.this.e = null;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }), dkW_(3));
    }

    private View dkW_(int i) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.fragment_aw70_pro_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.aw70_pro_example_start_run_img);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.aw70_pro_prompt_img);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.aw70_pro_prompt_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.aw70_pro_prompt_first_content);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.aw70_pro_prompt_second_content);
        HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.aw70_pro_prompt_third_content);
        if (i == 1) {
            imageView.setImageResource(R.mipmap._2131821433_res_0x7f110379);
            imageView2.setVisibility(8);
            healthTextView.setText(R.string._2130843375_res_0x7f0216ef);
            healthTextView2.setText(this.d.getString(R.string._2130843377_res_0x7f0216f1, new Object[]{1}));
            healthTextView3.setText(this.d.getString(R.string._2130843378_res_0x7f0216f2, new Object[]{2}));
            healthTextView4.setVisibility(8);
        } else if (i == 2) {
            imageView2.setImageResource(R.mipmap._2131821432_res_0x7f110378);
            imageView.setVisibility(8);
            healthTextView.setText(R.string._2130843376_res_0x7f0216f0);
            healthTextView2.setText(this.d.getString(R.string._2130843379_res_0x7f0216f3, new Object[]{1}));
            healthTextView3.setText(this.d.getString(R.string._2130843380_res_0x7f0216f4, new Object[]{2}));
            healthTextView4.setVisibility(8);
        } else if (i == 3) {
            imageView.setImageResource(R.mipmap._2131821455_res_0x7f11038f);
            imageView2.setVisibility(8);
            healthTextView.setText(R.string._2130842267_res_0x7f02129b);
            healthTextView2.setVisibility(8);
            healthTextView3.setVisibility(8);
            healthTextView4.setVisibility(0);
            healthTextView4.setText(R.string._2130843381_res_0x7f0216f5);
        } else {
            LogUtil.c("WearHomeMainAction", "loadAw70ProDialogView is not exist");
        }
        return inflate;
    }

    public void m() {
        ac();
    }

    private void ac() {
        LogUtil.a("WearHomeMainAction", 0, "WearHomeMainAction", "Enter unbindDevice");
        List<DeviceInfo> i = oxa.a().i();
        this.d.b = oxa.a().a(this.d.g);
        DeviceInfo deviceInfo = null;
        if (this.d.b != null && i != null && i.size() > 0) {
            oaf.b(BaseApplication.getContext()).a(this.d.b);
            oaf.b(BaseApplication.getContext()).h(this.d.g);
            int i2 = -1;
            for (DeviceInfo deviceInfo2 : i) {
                if (deviceInfo2.getDeviceIdentify().equals(this.d.b.getDeviceIdentify())) {
                    i2 = i.indexOf(deviceInfo2);
                }
            }
            LogUtil.a("WearHomeMainAction", 0, "WearHomeMainAction", "deleteUseDeviceList() id is ", Integer.valueOf(i2));
            if (i2 != -1) {
                DeviceInfo remove = i.remove(i2);
                o();
                deviceInfo = remove;
            }
            r();
            if (this.d.f9644a != null && this.d.f9644a.isSupportSyncWifi()) {
                KeyValDbManager.b(BaseApplication.getContext()).e("saved_support_sync_wifi", "0");
            }
            oau.c(this.d.b);
            pep.c(this.d.b.getProductType(), this.d.g);
        }
        if (this.d.b == null && i != null && i.size() > 0) {
            int d = d(i);
            LogUtil.a("WearHomeMainAction", 0, "WearHomeMainAction", "deleteUseDeviceList() id is ", Integer.valueOf(d));
            if (d != -1) {
                DeviceInfo remove2 = i.remove(d);
                o();
                deviceInfo = remove2;
            }
            r();
        }
        i(deviceInfo);
        if (deviceInfo != null) {
            e(deviceInfo);
        }
        l();
        jfq.c().d("deleteDevice", deviceInfo, 0, "");
    }

    private int d(List<DeviceInfo> list) {
        int i = -1;
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo.getDeviceIdentify().equals(this.d.g)) {
                i = list.indexOf(deviceInfo);
            }
        }
        return i;
    }

    private void o() {
        LogUtil.a("WearHomeMainAction", "deleteDevice");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.d.g);
        oxa.a().c(arrayList, true);
    }

    private void e(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeMainAction", "removeCloudDevice removedDeviceInfo is ", deviceInfo.toString());
        String deviceModel = deviceInfo.getDeviceModel();
        if (deviceModel == null || !deviceModel.toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
            LogUtil.h("WearHomeMainAction", "removeCloudDevice getDeviceModel not contains HECTOR");
        } else {
            final String deviceName = deviceInfo.getDeviceName();
            this.d.x.execute(new Runnable() { // from class: pbg.13
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("WearHomeMainAction", "removeCloudDevice run() getAllBindDeviceSync");
                    GetBindDeviceRsp d = jbs.a(pbg.this.c).d(new GetBindDeviceReq());
                    if (!pbg.this.e(d)) {
                        LogUtil.b("WearHomeMainAction", "removeCloudDevice getBindDeviceSync error");
                        return;
                    }
                    List<com.huawei.hwcloudmodel.model.userprofile.DeviceInfo> deviceInfos = d.getDeviceInfos();
                    if (deviceInfos == null || deviceInfos.isEmpty()) {
                        LogUtil.h("WearHomeMainAction", "getBindDeviceSync deviceInfos is null");
                        return;
                    }
                    for (com.huawei.hwcloudmodel.model.userprofile.DeviceInfo deviceInfo2 : deviceInfos) {
                        if (deviceName == null || deviceInfo2 == null || TextUtils.isEmpty(deviceInfo2.getName())) {
                            LogUtil.h("WearHomeMainAction", "getBindDeviceSync info data error");
                        } else if (deviceInfo2.getName().equals(deviceName)) {
                            LogUtil.a("WearHomeMainAction", "cloud DeviceInfo is ", deviceInfo2.toString());
                            pbg.this.c(deviceInfo2.getDeviceCode().longValue());
                            return;
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(GetBindDeviceRsp getBindDeviceRsp) {
        if (getBindDeviceRsp == null) {
            LogUtil.h("WearHomeMainAction", "checkCloudResponse response is null");
            return false;
        }
        int intValue = getBindDeviceRsp.getResultCode().intValue();
        LogUtil.a("WearHomeMainAction", "checkCloudResponse response resultCode is,", Integer.valueOf(intValue));
        return intValue == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j) {
        LogUtil.a("WearHomeMainAction", "unBindDevice enter");
        UnbindDeviceRequest unbindDeviceRequest = new UnbindDeviceRequest();
        unbindDeviceRequest.setDeviceCode(Long.valueOf(j));
        CloudCommonReponse d = jbs.a(this.c).d(unbindDeviceRequest);
        if (d != null) {
            LogUtil.a("WearHomeMainAction", "unBindDevice response is ", d.toString());
        } else {
            LogUtil.h("WearHomeMainAction", "unBindDevice response is null");
        }
    }

    private void l() {
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        List<MessageObject> messages = messageCenterApi.getMessages(String.valueOf(19), "device_type_connected");
        LogUtil.a("WearHomeMainAction", "deleteDeviceInfo, delete messageList, messageList.size():", Integer.valueOf(messages.size()));
        int size = messages.size();
        for (int i = 0; i < size; i++) {
            try {
                messageCenterApi.deleteMessage(messages.get(i).getMsgId());
                jfq.c().setSharedPreference("kStorage_DeviceCfgMgr_Identify", "", null);
                LogUtil.c("WearHomeMainAction", "deleteDeviceInfo delete success");
            } catch (NumberFormatException unused) {
                LogUtil.b("WearHomeMainAction", "deleteDeviceInfo error");
                return;
            }
        }
    }

    private void i(DeviceInfo deviceInfo) {
        c(deviceInfo);
        if (!Utils.o() && deviceInfo != null) {
            ProfileAgent.PROFILE_AGENT.connectProfile(new a(deviceInfo.getSecurityUuid() + "#ANDROID21"));
        }
        joj.a().a(deviceInfo);
        this.d.n = false;
        this.d.finish();
    }

    private void c(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.a("WearHomeMainAction", "get device info");
            String b = dis.b(deviceInfo.getDeviceIdentify());
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), b);
            LogUtil.a("WearHomeMainAction", "sharedPreferenceResult is ", b2);
            if (!TextUtils.isEmpty(b2) && b2.equals("0")) {
                SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), b);
            }
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10005), "keyUserGuidePage" + b);
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10008), "keyToastShowTime" + b);
            KeyValDbManager.b(BaseApplication.getContext()).c("downloadHiAiPlugin");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.a("WearHomeMainAction", 1, "WearHomeMainAction", "enter showRestoreFactoryDialog()");
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.c).b(R.string.IDS_device_wear_home_delete_device).e(this.c.getResources().getString(R.string.IDS_unbind_device_wear_home)).cyU_(R.string.IDS_unbind_device_wear_home_unpair, new View.OnClickListener() { // from class: pbj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.this.dle_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: pbm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.dkV_(view);
            }
        });
        if (this.d.isFinishing()) {
            return;
        }
        CustomTextAlertDialog a2 = cyR_.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* synthetic */ void dle_(View view) {
        LogUtil.a("WearHomeMainAction", "showUnbindDialog ok click");
        oau.d(3);
        ac();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dkV_(View view) {
        LogUtil.a("WearHomeMainAction", "showLoginFail cancel click");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void v() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(this.c.getResources().getString(R.string.IDS_device_ota_later_note)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: pbe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.dkU_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dkU_(View view) {
        LogUtil.a("WearHomeMainAction", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void f() {
        if (this.d.f9644a != null && this.d.f9644a.isSupportSyncAccount()) {
            u();
        } else {
            LogUtil.h("WearHomeMainAction", "showDialogSyncAccount current device does not support sync account");
        }
    }

    public void k() {
        EventBus.ICallback iCallback = this.i;
        if (iCallback != null) {
            EventBus.a(iCallback, "sign_account_hms_login_state_action");
        }
    }

    public void g() {
        EventBus.ICallback iCallback = this.i;
        if (iCallback != null) {
            EventBus.d(iCallback, 1, "sign_account_hms_login_state_action");
        }
    }

    private void u() {
        LogUtil.a("WearHomeMainAction", 1, "WearHomeMainAction", "enter showSyncAccountDialog()");
        if (this.m == null) {
            if (!Utils.i()) {
                LogUtil.a("WearHomeMainAction", "showSyncAccountDialog no cloud empty account");
                jfo.e().a(2);
                return;
            }
            String t = t();
            if (TextUtils.isEmpty(t)) {
                LogUtil.a("WearHomeMainAction", "showSyncAccountDialog accountName is null");
                return;
            }
            String a2 = per.a(t);
            boolean matches = Pattern.compile("^([0-9]|(\\+))\\d+$").matcher(t).matches();
            if ((LanguageUtil.ac(this.c) || LanguageUtil.y(this.c)) && matches) {
                LogUtil.a("WearHomeMainAction", "enter isUrduLanguage or isFarsiLanguage");
                String substring = a2.substring(0, 3);
                String substring2 = a2.substring(a2.length() - 2, a2.length());
                StringBuilder sb = new StringBuilder(16);
                sb.append(a2);
                sb.replace(0, 3, substring2);
                sb.replace(sb.length() - 3, sb.length(), substring);
                LogUtil.a("WearHomeMainAction", "stringBuilder:", sb.toString());
                a2 = sb.toString();
            }
            a(a2);
        }
    }

    private void a(String str) {
        String format = String.format(Locale.ENGLISH, this.c.getString(R.string.IDS_device_wear_home_sync_account_content), str);
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, this.c.getString(R.string._2130844402_res_0x7f021af2), str);
        }
        final GuideInteractors guideInteractors = new GuideInteractors(this.c);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.c).b(R.string.IDS_device_wear_home_sync_account_title).e(format).cyU_(R.string.IDS_device_wear_home_confirm_sync_account, new View.OnClickListener() { // from class: pbf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.this.dla_(guideInteractors, view);
            }
        }).cyR_(R.string.IDS_device_wear_home_skip_sync_account, new View.OnClickListener() { // from class: pbk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pbg.this.dlb_(guideInteractors, view);
            }
        }).a();
        this.m = a2;
        a2.setCancelable(false);
        if (this.m.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.m.show();
    }

    /* synthetic */ void dla_(GuideInteractors guideInteractors, View view) {
        b(guideInteractors);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dlb_(GuideInteractors guideInteractors, View view) {
        LogUtil.a("WearHomeMainAction", "showSyncAccountDialog skip click");
        guideInteractors.d(this.d.g, false);
        this.m.dismiss();
        this.m = null;
        jfo.e().a(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private String t() {
        final ArrayList arrayList = new ArrayList();
        LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1001, new StorageDataCallback() { // from class: pbg.11
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult.d() == 0) {
                    arrayList.add(String.valueOf(storageResult.e()));
                }
            }
        });
        if (arrayList.isEmpty()) {
            LogUtil.a("WearHomeMainAction", "getAccountPlainTextInfo is null");
            return "";
        }
        return (String) arrayList.get(0);
    }

    private void b(GuideInteractors guideInteractors) {
        LogUtil.a("WearHomeMainAction", "showSyncAccountDialog sync click");
        guideInteractors.d(this.d.g, false);
        this.m.dismiss();
        this.m = null;
        if (!c(this.d.g)) {
            d(1);
            return;
        }
        jfo.e().a(1);
        y();
        if (this.d.djG_() != null) {
            this.d.djG_().sendEmptyMessageDelayed(300006, 180000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.d.v != null) {
            LogUtil.a("WearHomeMainAction", "mWearHomeSyncCard not null");
            this.d.d(i);
        }
    }

    private void r() {
        LogUtil.a("WearHomeMainAction", 0, "WearHomeMainAction", "Enter sendDeviceListChangeBroadcast()");
        if (CommonUtil.ce()) {
            cvw.c(oxa.a().i(), "WearHomeMainAction");
        }
    }

    public void c() {
        LogUtil.a("WearHomeMainAction", "dissLoadingDialog()");
        CommonDialog21 commonDialog21 = this.n;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.n = null;
        }
    }

    private void y() {
        LogUtil.a("WearHomeMainAction", "showLoadingDialog()");
        CommonDialog21 commonDialog21 = this.n;
        if (commonDialog21 == null) {
            new CommonDialog21(this.c, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this.c);
            this.n = a2;
            a2.e(this.c.getResources().getString(R.string._2130844172_res_0x7f021a0c));
            this.n.setCancelable(true);
            this.n.a();
            LogUtil.a("WearHomeMainAction", "mLoadingSync.show()");
            return;
        }
        commonDialog21.a();
        LogUtil.a("WearHomeMainAction", "mLoadingSync.show()");
    }

    private boolean c(String str) {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            LogUtil.h("WearHomeMainAction", 1, "WearHomeMainAction", "BluetoothAdapter is null.");
            return false;
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("WearHomeMainAction", 1, "WearHomeMainAction", "Bluetooth is not enabled or cannot be used.");
            return false;
        }
        DeviceInfo e = jpt.e(str, "WearHomeMainAction");
        if (e != null) {
            return e.getDeviceConnectState() == 2;
        }
        LogUtil.h("WearHomeMainAction", 1, "WearHomeMainAction", "currentDeviceInfo is null.");
        return false;
    }

    public void b(DeviceInfo deviceInfo) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.b.cancel();
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(String.format(Locale.ENGLISH, this.c.getString(R.string._2130845618_res_0x7f021fb2), deviceInfo.getDeviceName())).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: pbg.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WearHomeMainAction", "showKidAccountNotSupportPairDialog, click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.b = e;
        e.setCancelable(false);
        if (this.b.isShowing() || this.d.isFinishing()) {
            return;
        }
        this.b.show();
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    public void d(int i, Object obj) {
        if (obj instanceof String) {
            b((String) obj);
        } else {
            ac();
        }
    }

    public void n() {
        jgf.b().c(this);
    }
}
