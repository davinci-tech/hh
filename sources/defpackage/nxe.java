package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.health.R;
import com.huawei.health.device.ui.privacy.HonorDeviceShowPrivacyActivity;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.maps.offlinedata.health.p2p.receiver.OfflineMapReceiver;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.DevicePairDoneActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.device.activity.pairing.KeepAliveTipsActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import com.huawei.ui.main.stories.me.views.privacy.OtaPrivacyActivity;
import com.huawei.ui.main.stories.userprofile.activity.WorkModeConflictDialogActivity;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class nxe {
    public static final String c = System.lineSeparator();
    private AnimationDrawable b;
    private DevicePairGuideActivity e;
    private nuc o;
    private ExecutorService h = Executors.newCachedThreadPool();
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    private long f15529a = -1;
    private NoTitleCustomAlertDialog d = null;
    private int k = 1;
    private nwz f = new nwz();
    private nxc m = new nxc();
    private nxb i = new nxb();
    private nxg g = new nxg();

    public void d(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity != null) {
            this.e = devicePairGuideActivity;
        }
    }

    public void e() {
        if (this.e != null) {
            this.e = null;
        }
    }

    public void a() {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "destroyInstance() start.");
        jfq.c().d("deleteDevice", new DeviceInfo(), 0, String.valueOf(false));
        ExecutorService executorService = this.h;
        if (executorService != null) {
            executorService.shutdown();
        }
        this.g.d();
    }

    public void d() {
        this.g.b();
    }

    public void a(final DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.c.start();
        i(devicePairGuideActivity);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(devicePairGuideActivity.dg, new DownloadDeviceInfoCallBack() { // from class: nxe.1
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onSuccess() {
                devicePairGuideActivity.c.stop();
                nxe.this.f(devicePairGuideActivity);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                devicePairGuideActivity.c.stop();
                oau.c(100102, "");
                nxe.this.e(devicePairGuideActivity, i);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void netWorkError() {
                devicePairGuideActivity.c.stop();
                nxe.this.g(devicePairGuideActivity);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onDownload(int i) {
                nxe.this.a(devicePairGuideActivity, i);
            }
        });
    }

    private void cRQ_(int i, ImageView imageView) {
        if (jfu.c(i).n() == 2) {
            imageView.setImageResource(R.drawable._2131430656_res_0x7f0b0d00);
        } else {
            DevicePairGuideUtil.cRF_(i, imageView);
        }
    }

    private void j(int i) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.bu.setVisibility(8);
        devicePairGuideActivity.bs.setVisibility(8);
        devicePairGuideActivity.ai.setVisibility(8);
        devicePairGuideActivity.cs.setVisibility(0);
        devicePairGuideActivity.am.setVisibility(0);
        devicePairGuideActivity.cu.setVisibility(8);
        devicePairGuideActivity.l.setVisibility(8);
        devicePairGuideActivity.k.setVisibility(8);
        devicePairGuideActivity.n.setVisibility(8);
        devicePairGuideActivity.m.setVisibility(8);
        devicePairGuideActivity.cb.setVisibility(8);
        devicePairGuideActivity.cv.setVisibility(0);
        devicePairGuideActivity.cv.setImageResource(R.drawable._2131430226_res_0x7f0b0b52);
        devicePairGuideActivity.cw.setText(i);
        devicePairGuideActivity.cy.setVisibility(0);
        devicePairGuideActivity.s.setVisibility(8);
    }

    private void a(GuideInteractors guideInteractors) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        m(devicePairGuideActivity);
        devicePairGuideActivity.t.setVisibility(0);
        n(devicePairGuideActivity);
        devicePairGuideActivity.by.setVisibility(8);
        devicePairGuideActivity.bx.setVisibility(0);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "Enter MSG_SHOW_PAIR_SUCCESS_RESULT():");
        guideInteractors.d(true);
        if (!obb.e(devicePairGuideActivity.an)) {
            guideInteractors.e(true);
            guideInteractors.b(true);
            guideInteractors.a(true);
        }
        String string = devicePairGuideActivity.getResources().getString(R.string._2130841379_res_0x7f020f23);
        String string2 = devicePairGuideActivity.getResources().getString(R.string._2130841524_res_0x7f020fb4);
        devicePairGuideActivity.ak = DeviceSettingsInteractors.d(BaseApplication.getContext());
        devicePairGuideActivity.as = devicePairGuideActivity.ak.e(devicePairGuideActivity.di);
        devicePairGuideActivity.ao.setVisibility(0);
        devicePairGuideActivity.x.setVisibility(8);
        if (devicePairGuideActivity.as != null && devicePairGuideActivity.as.isMessageAlert()) {
            devicePairGuideActivity.bi = true;
            LogUtil.a("DEVMGR_MemberAndViewHelper", "device is support notification");
            if (devicePairGuideActivity.bd && !lcu.e()) {
                devicePairGuideActivity.ao.setText(string2.toUpperCase(Locale.ENGLISH));
                return;
            } else {
                devicePairGuideActivity.ao.setText(string.toUpperCase(Locale.ENGLISH));
                return;
            }
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "device is not support notification");
        if (lcu.e()) {
            devicePairGuideActivity.ao.setText(string.toUpperCase(Locale.ENGLISH));
        } else {
            devicePairGuideActivity.ao.setText(string2.toUpperCase(Locale.ENGLISH));
        }
    }

    private void m(DevicePairGuideActivity devicePairGuideActivity) {
        DeviceCapability deviceCapability;
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "DEVMGR_MemberAndViewHelper");
        if (a2 != null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "filter deviceCapabilityMap is null");
            deviceCapability = a2.get(devicePairGuideActivity.ae);
        } else {
            deviceCapability = null;
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "query deviceCapabilityMap:", iyl.d().e(devicePairGuideActivity.ae));
        if (cvt.b(devicePairGuideActivity.an) || cvt.a(devicePairGuideActivity.an)) {
            devicePairGuideActivity.cy.setText(nsn.ae(BaseApplication.getContext()) ? R.string._2130844348_res_0x7f021abc : R.string._2130842732_res_0x7f02146c);
        } else if (d(deviceCapability)) {
            p(devicePairGuideActivity);
        } else {
            a(devicePairGuideActivity, deviceCapability);
        }
    }

    private boolean d(DeviceCapability deviceCapability) {
        DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
        if (e != null) {
            if (e.getSportVersion() == 1) {
                return true;
            }
            if (e.getSportVersion() == 0) {
                return false;
            }
        }
        return deviceCapability != null && deviceCapability.isSupportSmartWatchVersionStatus();
    }

    private void a(DevicePairGuideActivity devicePairGuideActivity, DeviceCapability deviceCapability) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "setSportWatchPairResult in. ");
        if (this.k == 2) {
            devicePairGuideActivity.cy.setText(devicePairGuideActivity.getResources().getString(R.string._2130844458_res_0x7f021b2a));
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "deviceCapability == null in. ";
        objArr[1] = deviceCapability == null ? null : Boolean.valueOf(deviceCapability.isSupportHiWear());
        LogUtil.a("DEVMGR_MemberAndViewHelper", objArr);
        if (Utils.i() && deviceCapability != null && deviceCapability.isSupportHiWear()) {
            String string = devicePairGuideActivity.getResources().getString(R.string.IDS_device_paring_success_tip);
            if (nsn.ae(BaseApplication.getContext())) {
                string = devicePairGuideActivity.getResources().getString(R.string.IDS_pad_device_paring_tip);
            }
            String d = jfu.d(devicePairGuideActivity.an);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(d);
            LogUtil.a("DEVMGR_MemberAndViewHelper", "Paired successful tip: ", d, ", isPluginAvailable.", Boolean.valueOf(isResourcesAvailable));
            if (isResourcesAvailable) {
                cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d);
                LogUtil.a("DEVMGR_MemberAndViewHelper", "get devicePluginInfo. ");
                if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
                    LogUtil.h("DEVMGR_MemberAndViewHelper", "devicePluginInfo or WearDeviceInfo is null");
                } else {
                    cvj f = pluginInfoByUuid.f();
                    if (!TextUtils.isEmpty(f.bd())) {
                        string = f.bd();
                    }
                }
            }
            b(devicePairGuideActivity, string);
            return;
        }
        String string2 = devicePairGuideActivity.getResources().getString(R.string.IDS_device_paring_success_descommon_info_union_new);
        if (nsn.ae(BaseApplication.getContext())) {
            string2 = devicePairGuideActivity.getResources().getString(R.string.IDS_pad_device_descommon_union);
        }
        devicePairGuideActivity.cy.setText(string2);
    }

    private void p(DevicePairGuideActivity devicePairGuideActivity) {
        if (Utils.o()) {
            if (Utils.i()) {
                String string = devicePairGuideActivity.getResources().getString(R.string._2130844824_res_0x7f021c98);
                if (nsn.ae(BaseApplication.getContext())) {
                    string = devicePairGuideActivity.getResources().getString(R.string._2130844822_res_0x7f021c96);
                }
                b(devicePairGuideActivity, string);
                return;
            }
            String string2 = devicePairGuideActivity.getResources().getString(R.string._2130844826_res_0x7f021c9a);
            if (nsn.ae(BaseApplication.getContext())) {
                string2 = devicePairGuideActivity.getResources().getString(R.string._2130844827_res_0x7f021c9b);
            }
            devicePairGuideActivity.cy.setText(string2);
            return;
        }
        String string3 = devicePairGuideActivity.getResources().getString(R.string._2130844825_res_0x7f021c99);
        if (nsn.ae(BaseApplication.getContext())) {
            string3 = devicePairGuideActivity.getResources().getString(R.string._2130844823_res_0x7f021c97);
        }
        b(devicePairGuideActivity, string3);
    }

    private void n(DevicePairGuideActivity devicePairGuideActivity) {
        if (nsn.s()) {
            ViewGroup.LayoutParams layoutParams = devicePairGuideActivity.t.getLayoutParams();
            layoutParams.height = nsn.c(BaseApplication.getContext(), 80.0f);
            devicePairGuideActivity.t.setLayoutParams(layoutParams);
        }
    }

    private void b(DevicePairGuideActivity devicePairGuideActivity, String str) {
        String string = devicePairGuideActivity.getResources().getString(R.string._2130841418_res_0x7f020f4a);
        String string2 = devicePairGuideActivity.getResources().getString(R.string.IDS_device_pair_minute_and_second, str, string);
        SpannableString spannableString = new SpannableString(string2);
        int length = string2.length() - string.length();
        nsi.cKG_(spannableString, length, spannableString.toString().length(), new rvq(devicePairGuideActivity.getBaseContext(), "HealthPrivacy"));
        nsi.cKG_(spannableString, length, spannableString.toString().length(), new TypefaceSpan(Constants.FONT));
        devicePairGuideActivity.cy.setMovementMethod(LinkMovementMethod.getInstance());
        devicePairGuideActivity.cy.setHighlightColor(devicePairGuideActivity.getResources().getColor(android.R.color.transparent));
        devicePairGuideActivity.cy.setText(spannableString);
    }

    public boolean j() {
        return this.f.e(this.e);
    }

    protected void h() {
        this.f.c(this.e);
    }

    public void a(long j, int i, int i2) {
        this.f15529a = j;
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "receiverPairRequest activity is null");
            return;
        }
        boolean d = this.f.d(i, i2);
        if (e(i2)) {
            c(10001, d);
        } else {
            this.f.d(devicePairGuideActivity, d);
        }
    }

    private boolean e(int i) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "isNeedToHonorPrivacy activity is null");
            return false;
        }
        if (dks.d(devicePairGuideActivity) && jfu.n(i)) {
            return !SharedPreferenceManager.e(devicePairGuideActivity, LoginInit.getInstance(devicePairGuideActivity).getUsetId());
        }
        return false;
    }

    public void c(boolean z) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "sendBluetoothConnection isOpenTestButton:", Boolean.valueOf(z));
        if (z) {
            nxf.c(BaseApplication.getContext()).a(this.f15529a, oae.d(b()));
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(10026);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(this.f15529a);
                deviceDialogMessage.setDeviceName(null);
                int d = oae.d(b());
                if (CompileParameterUtil.a("IS_SUPPORT_NEW_ADD_MODE", false) && d != -1) {
                    deviceDialogMessage.setBluetoothType(d);
                }
                LogUtil.a("DEVMGR_MemberAndViewHelper", "connectSelectedDevice deviceName:", deviceDialogMessage.getDeviceName(), ", bluetoothType:", Integer.valueOf(deviceDialogMessage.getBluetoothType()));
                e.dialogMessage(deviceDialogMessage, null);
            } catch (RemoteException unused) {
                LogUtil.b("DEVMGR_MemberAndViewHelper", "connectSelectedDevice RemoteException");
            }
        }
    }

    public void d(boolean z) {
        this.f.c(this.e, z);
    }

    public void s() {
        this.m.c(this.e);
    }

    public void f() {
        this.i.b(this.e);
    }

    public void i() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        DevicePairGuideActivity.setViewSafeRegion(false, devicePairGuideActivity.cs, devicePairGuideActivity.am);
        DevicePairGuideActivity.setViewSafeRegion(false, devicePairGuideActivity.ai, devicePairGuideActivity.l, devicePairGuideActivity.s);
        devicePairGuideActivity.by.setOnClickListener(devicePairGuideActivity);
        devicePairGuideActivity.bx.setOnClickListener(devicePairGuideActivity);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "mIsFromScanList:", Boolean.valueOf(devicePairGuideActivity.bb));
        h(devicePairGuideActivity);
        if (devicePairGuideActivity.bb && devicePairGuideActivity.bg) {
            v(devicePairGuideActivity);
            q(devicePairGuideActivity);
            a(devicePairGuideActivity);
            return;
        }
        w(devicePairGuideActivity);
    }

    private void h(final DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ao = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.done_button);
        devicePairGuideActivity.ao.setOnClickListener(new View.OnClickListener() { // from class: nxd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nxe.this.cRT_(devicePairGuideActivity, view);
            }
        });
        devicePairGuideActivity.au = (HealthButton) nsy.cMc_(devicePairGuideActivity, R.id.got_it_button);
        devicePairGuideActivity.au.setOnClickListener(new View.OnClickListener() { // from class: nxi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nxe.this.cRU_(view);
            }
        });
        devicePairGuideActivity.dn = (HealthCheckBox) nsy.cMc_(devicePairGuideActivity, R.id.wlan_auto_download_checkbox);
        devicePairGuideActivity.dl = (LinearLayout) nsy.cMc_(devicePairGuideActivity, R.id.wlan_auto_download_layout);
        devicePairGuideActivity.dp = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.wlan_auto_download_text);
        if (Utils.o()) {
            devicePairGuideActivity.dp.setText(R.string.IDS_wlan_auto_update_device_new_overseas);
        } else {
            devicePairGuideActivity.dp.setText(R.string.IDS_wlan_auto_update_device_new);
        }
        devicePairGuideActivity.h = (LinearLayout) nsy.cMc_(this.e, R.id.auto_install_layout);
        devicePairGuideActivity.f9180a = (HealthCheckBox) nsy.cMc_(this.e, R.id.auto_install_checkbox);
        devicePairGuideActivity.f = (LinearLayout) nsy.cMc_(this.e, R.id.auto_open_wifi_layout);
        devicePairGuideActivity.j = (HealthCheckBox) nsy.cMc_(this.e, R.id.auto_open_wifi_checkbox);
        devicePairGuideActivity.i = (HealthTextView) nsy.cMc_(this.e, R.id.auto_open_wifi_text);
        devicePairGuideActivity.g = (HealthTextView) nsy.cMc_(this.e, R.id.auto_open_wifi_content_text);
        devicePairGuideActivity.cx = (HealthTextView) nsy.cMc_(devicePairGuideActivity, R.id.pair_result_device_ota_privacy_txt);
    }

    /* synthetic */ void cRT_(DevicePairGuideActivity devicePairGuideActivity, View view) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "mDoneButton onclick");
        DevicePairGuideActivity devicePairGuideActivity2 = this.e;
        if (devicePairGuideActivity2 == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        devicePairGuideActivity2.a();
        s(devicePairGuideActivity);
        x();
        if (this.e.h.getVisibility() == 0 && !this.e.f9180a.isChecked()) {
            this.e.startActivityForResult(new Intent(this.e, (Class<?>) DevicePairDoneActivity.class), 10003);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        e(true);
        if (lcu.e()) {
            y();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(true);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void cRU_(View view) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "mGotItButton onclick");
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            devicePairGuideActivity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(DevicePairGuideActivity devicePairGuideActivity) {
        DevicePairGuideActivity.e = false;
        if (CommonUtil.bh()) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "sendPairSuccessReceiver is huawei system");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void y() {
        Intent intent = new Intent(this.e, (Class<?>) KeepAliveTipsActivity.class);
        intent.putExtra("DEVICE_NAME", this.e.bl);
        this.e.startActivityForResult(intent, 10004);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ak = DeviceSettingsInteractors.d(BaseApplication.getContext());
        devicePairGuideActivity.ag = devicePairGuideActivity.ak.e(u());
        if (devicePairGuideActivity.ag == null) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "mDeviceCapability is null");
            return;
        }
        boolean isSupportAutoUpdate = devicePairGuideActivity.ag.getIsSupportAutoUpdate();
        LogUtil.a("DEVMGR_MemberAndViewHelper", "isSupportAutoUpdate is ", Boolean.valueOf(isSupportAutoUpdate));
        if (isSupportAutoUpdate) {
            DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
            if (e != null) {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "saveAutoInstallCheck save AutoOnCheckedListener");
                oau.d(e.getDeviceIdentify(), e.getDeviceUdid(), z);
                jkk.d().a(this.e.ae, z ? 23 : 24);
                return;
            }
            LogUtil.h("DEVMGR_MemberAndViewHelper", "currentDeviceInfo is null");
        }
    }

    private void q(DevicePairGuideActivity devicePairGuideActivity) {
        if (TextUtils.isEmpty(devicePairGuideActivity.bj)) {
            return;
        }
        if (TextUtils.equals(devicePairGuideActivity.bj, "wear_watch")) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        } else if (TextUtils.equals(devicePairGuideActivity.bj, "SPORTS_GENIE".toLowerCase())) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430635_res_0x7f0b0ceb);
        } else if (TextUtils.equals(devicePairGuideActivity.bj, "wear_band")) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
        }
    }

    private void w(DevicePairGuideActivity devicePairGuideActivity) {
        boolean m = jfu.m(devicePairGuideActivity.an);
        if (!CommonUtil.as() && m) {
            boolean d = ntt.d(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(devicePairGuideActivity.an)), devicePairGuideActivity.an, CommonUtil.d(BaseApplication.getContext()));
            LogUtil.a("DEVMGR_MemberAndViewHelper", "startPair isUpdate: ", Boolean.valueOf(d));
            if (d) {
                devicePairGuideActivity.ax.sendEmptyMessage(14);
                return;
            }
        }
        devicePairGuideActivity.c();
        devicePairGuideActivity.c.start();
        if (devicePairGuideActivity.az && (devicePairGuideActivity.ah == null || devicePairGuideActivity.ah.f() == null)) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "mDeviceInfoForPlugin or mWearDeviceInfo is null");
            devicePairGuideActivity.ax.sendEmptyMessageDelayed(15, 500L);
        } else {
            e(devicePairGuideActivity);
        }
    }

    public void e(DevicePairGuideActivity devicePairGuideActivity) {
        if (LogConfig.d() && jpo.e(devicePairGuideActivity.bl, devicePairGuideActivity.ah)) {
            k(devicePairGuideActivity);
            if ("1".equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1004))) {
                c(devicePairGuideActivity, 1);
                oau.b();
                return;
            } else {
                p();
                return;
            }
        }
        b(devicePairGuideActivity);
    }

    public void b(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        if (devicePairGuideActivity.bb) {
            if (e(devicePairGuideActivity.an)) {
                c(10002, false);
                return;
            } else {
                j(devicePairGuideActivity);
                return;
            }
        }
        l(devicePairGuideActivity);
    }

    private void j(DevicePairGuideActivity devicePairGuideActivity) {
        if (this.f.d(devicePairGuideActivity)) {
            if (Utils.m() && !Utils.o()) {
                devicePairGuideActivity.ca.setVisibility(8);
                this.f.b(devicePairGuideActivity, true);
                return;
            } else {
                this.f.b(devicePairGuideActivity);
                cRQ_(devicePairGuideActivity.an, devicePairGuideActivity.ct);
                return;
            }
        }
        l(devicePairGuideActivity);
    }

    private void l(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.e();
        devicePairGuideActivity.b();
    }

    private void v(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setVisibility(8);
        devicePairGuideActivity.bs.setVisibility(8);
        devicePairGuideActivity.ai.setVisibility(8);
        devicePairGuideActivity.t.setVisibility(8);
        devicePairGuideActivity.cs.setVisibility(0);
        devicePairGuideActivity.am.setVisibility(0);
        devicePairGuideActivity.cb.setVisibility(0);
        devicePairGuideActivity.cu.setVisibility(0);
        devicePairGuideActivity.cy.setVisibility(8);
        devicePairGuideActivity.dl.setVisibility(8);
        devicePairGuideActivity.h.setVisibility(8);
        devicePairGuideActivity.f.setVisibility(8);
        devicePairGuideActivity.cx.setVisibility(8);
        devicePairGuideActivity.cv.setVisibility(8);
        devicePairGuideActivity.dh.setVisibility(8);
        devicePairGuideActivity.s.setVisibility(8);
        devicePairGuideActivity.x.setVisibility(8);
        devicePairGuideActivity.aw.setVisibility(0);
    }

    private boolean d(List<String> list, DevicePairGuideActivity devicePairGuideActivity) {
        for (String str : list) {
            cuw e = jfu.e(str);
            if (e == null) {
                LogUtil.h("DEVMGR_MemberAndViewHelper", "deviceInfoPlugin is null");
                cuw c2 = jfu.c(str, false);
                if (c2 == null) {
                    LogUtil.h("DEVMGR_MemberAndViewHelper", "deviceInfoNew is null");
                    return false;
                }
                int m = c2.m();
                LogUtil.a("DEVMGR_MemberAndViewHelper", "deviceType: ", Integer.valueOf(m));
                if (m == devicePairGuideActivity.an) {
                    devicePairGuideActivity.aj = c2.f();
                    return true;
                }
            } else {
                int m2 = e.m();
                LogUtil.a("DEVMGR_MemberAndViewHelper", "deviceType: ", Integer.valueOf(m2));
                if (m2 == devicePairGuideActivity.an) {
                    devicePairGuideActivity.aj = e.f();
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.aw.setVisibility(8);
        devicePairGuideActivity.ap.setVisibility(8);
        devicePairGuideActivity.db.setVisibility(8);
        devicePairGuideActivity.bn.setVisibility(8);
        devicePairGuideActivity.an = jfu.c(devicePairGuideActivity.bl);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "downLoadSuccessInfo :", Integer.valueOf(devicePairGuideActivity.an), ", name:", devicePairGuideActivity.bl);
        if (devicePairGuideActivity.an == -1) {
            jfu.n();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
                LogUtil.b("DEVMGR_MemberAndViewHelper", "downLoadSuccessInfo exception");
            }
            devicePairGuideActivity.an = jfu.c(devicePairGuideActivity.bl);
        }
        if (devicePairGuideActivity.an == -1) {
            sqo.m("get device type error");
            devicePairGuideActivity.ax.sendEmptyMessage(14);
            return;
        }
        if (koq.b(devicePairGuideActivity.dg)) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "uuidList is empty");
            sqo.m("uuidList is empty");
            e(devicePairGuideActivity, -1);
        } else if (!d(devicePairGuideActivity.dg, devicePairGuideActivity)) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "not found device");
            sqo.m("not found device");
            e(devicePairGuideActivity, -1);
        } else {
            devicePairGuideActivity.de = devicePairGuideActivity.an;
            w(devicePairGuideActivity);
        }
    }

    private void i(DevicePairGuideActivity devicePairGuideActivity) {
        ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "enter downLoadDeviceResource()");
        devicePairGuideActivity.cw.setText("");
        devicePairGuideActivity.cu.setText("");
        devicePairGuideActivity.ap.setVisibility(0);
        devicePairGuideActivity.db.setVisibility(8);
        devicePairGuideActivity.bn.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final DevicePairGuideActivity devicePairGuideActivity, int i) {
        sqo.m("download device resource error");
        ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "enter downLoadDeviceResourceError()");
        devicePairGuideActivity.cw.setText(devicePairGuideActivity.getString(R.string.IDS_downlod_device_error));
        devicePairGuideActivity.cu.setText(R.string._2130844160_res_0x7f021a00);
        devicePairGuideActivity.ap.setVisibility(8);
        devicePairGuideActivity.db.setVisibility(0);
        devicePairGuideActivity.bn.setVisibility(8);
        if (i == -3) {
            devicePairGuideActivity.dc.setText(R.string._2130844862_res_0x7f021cbe);
        } else {
            devicePairGuideActivity.dc.setText(R.string.IDS_no_device_res);
        }
        devicePairGuideActivity.db.setOnClickListener(new View.OnClickListener() { // from class: nxe.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nxe.this.a(devicePairGuideActivity);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final DevicePairGuideActivity devicePairGuideActivity) {
        ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "enter downLoadDeviceResourceNetError()");
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastManagerUtil.bFB_(devicePairGuideActivity, devicePairGuideActivity.bo, intentFilter);
        } catch (IllegalStateException unused) {
            LogUtil.b("DEVMGR_MemberAndViewHelper", "registerNetBroadCast IllegalStateException");
        }
        devicePairGuideActivity.cw.setText(devicePairGuideActivity.getString(R.string.IDS_downlod_device_error));
        devicePairGuideActivity.cu.setText(R.string._2130844160_res_0x7f021a00);
        devicePairGuideActivity.ap.setVisibility(8);
        devicePairGuideActivity.db.setVisibility(8);
        devicePairGuideActivity.bn.setVisibility(0);
        devicePairGuideActivity.bn.setOnClickListener(new View.OnClickListener() { // from class: nxe.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nxe.this.o == null) {
                    nxe.this.o = new nuc();
                }
                nxe.this.o.a(devicePairGuideActivity);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DevicePairGuideActivity devicePairGuideActivity, int i) {
        devicePairGuideActivity.ap.setVisibility(0);
        devicePairGuideActivity.db.setVisibility(8);
        devicePairGuideActivity.bn.setVisibility(8);
        devicePairGuideActivity.ap.setProgress(i);
    }

    private void c(int i, boolean z) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.c("DEVMGR_MemberAndViewHelper", "DevicePairGuideActivity is null ");
            return;
        }
        devicePairGuideActivity.ba.setVisibility(4);
        Intent intent = new Intent(devicePairGuideActivity, (Class<?>) HonorDeviceShowPrivacyActivity.class);
        intent.putExtra("is_support_special_midware", z);
        intent.putExtra("honor_device_type_key", devicePairGuideActivity.an);
        devicePairGuideActivity.startActivityForResult(intent, i);
    }

    public void cRV_(int i, int i2, Intent intent) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "requestCode:", Integer.valueOf(i), "resultCode:", Integer.valueOf(i2), "data:", intent);
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.c("DEVMGR_MemberAndViewHelper", "DevicePairGuideActivity is null ");
            return;
        }
        devicePairGuideActivity.ba.setVisibility(0);
        if (c(i, i2, devicePairGuideActivity)) {
            return;
        }
        if (i == 10004 && i2 == 3) {
            a(true);
        }
        if (i == 10003) {
            e(i2 == 2);
            if (lcu.e()) {
                y();
                return;
            }
            a(true);
        }
        if (intent == null || intent.getIntExtra("honor_show_privacy_key", 0) != 1) {
            devicePairGuideActivity.finish();
            return;
        }
        if (i == 10002) {
            j(devicePairGuideActivity);
        } else if (i == 10001) {
            this.f.cRE_(devicePairGuideActivity, intent);
        } else {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "do nothing");
        }
    }

    private boolean c(int i, int i2, DevicePairGuideActivity devicePairGuideActivity) {
        if (i != 10005) {
            return false;
        }
        if (i2 == 6) {
            devicePairGuideActivity.at.setVisibility(0);
            devicePairGuideActivity.at.setText(String.format(Locale.ROOT, devicePairGuideActivity.getResources().getString(R.string._2130845798_res_0x7f022066), devicePairGuideActivity.getResources().getString(R.string.IDS_wear_home_device_earphone)));
            d(R.string._2130845799_res_0x7f022067);
            return true;
        }
        d(R.string._2130841385_res_0x7f020f29);
        devicePairGuideActivity.ct.setImageResource(R.drawable._2131430621_res_0x7f0b0cdd);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        if (devicePairGuideActivity.dl.getVisibility() == 0) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "mWlanAutoDownloadLayout is show ,isCheck ", Boolean.valueOf(devicePairGuideActivity.dn.isChecked()));
            oau.b(devicePairGuideActivity.dn.isChecked());
            jkk.d().a(this.e.ae, devicePairGuideActivity.dn.isChecked() ? 21 : 22);
        }
        r(devicePairGuideActivity);
        if (devicePairGuideActivity.f.getVisibility() == 0) {
            DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
            if (e != null) {
                oau.a(devicePairGuideActivity.j.isChecked(), e.getDeviceIdentify());
            } else {
                LogUtil.h("DEVMGR_MemberAndViewHelper", "handleWlanStatus currentDeviceInfo is null");
            }
        }
    }

    private void r(DevicePairGuideActivity devicePairGuideActivity) {
        DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
        boolean z = cwi.c(e, 108) && !Utils.l();
        LogUtil.a("DEVMGR_MemberAndViewHelper", "enter saveWlanSwitch isOverseaNoCloudVersion ", Boolean.valueOf(Utils.l()));
        if (z) {
            HwOtaUpgradeManager.a().d(e.getDeviceUdid(), String.valueOf(devicePairGuideActivity.dn.isChecked()));
        }
    }

    private void o(DevicePairGuideActivity devicePairGuideActivity) {
        if (obb.d(this.k)) {
            AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), Collections.singletonList("PluginWearAbility"), true, true);
        }
        if (obb.c()) {
            AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), Collections.singletonList("PluginHiAiEngine"), true, true);
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "background downloading");
    }

    public void g() {
        final DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        String string = devicePairGuideActivity.getResources().getString(R.string.IDS_device_pair_guide_step);
        devicePairGuideActivity.bz.setText(String.format(Locale.ENGLISH, string, 1));
        devicePairGuideActivity.cg.setText(String.format(Locale.ENGLISH, string, 2));
        devicePairGuideActivity.cc.setText(String.format(Locale.ENGLISH, string, 3));
        devicePairGuideActivity.cd.setText(String.format(Locale.ENGLISH, string, 4));
        devicePairGuideActivity.aa.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: nxe.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "onClick(): mCustomTitleBar");
                if (devicePairGuideActivity.d) {
                    nxe.this.d();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                devicePairGuideActivity.b(4, 1);
                if (!TextUtils.isEmpty(devicePairGuideActivity.di) && !nxe.this.j) {
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(devicePairGuideActivity.di);
                    ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "mCustomTitleBar LeftButton onClick unPair device");
                    devicePairGuideActivity.af.e(arrayList, true);
                    sqo.m("user cancel pair device");
                    devicePairGuideActivity.d();
                    devicePairGuideActivity.finish();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!nsn.o()) {
                    nxe.this.q();
                    devicePairGuideActivity.a();
                    nxe.this.s(devicePairGuideActivity);
                    nxe.this.x();
                    if (devicePairGuideActivity.h.getVisibility() != 0 || devicePairGuideActivity.f9180a.isChecked()) {
                        nxe.this.e(true);
                        nxe.this.a(false);
                    } else {
                        devicePairGuideActivity.startActivityForResult(new Intent(devicePairGuideActivity, (Class<?>) DevicePairDoneActivity.class), 10003);
                    }
                } else {
                    LogUtil.a("DEVMGR_MemberAndViewHelper", "click too fast");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        if (z) {
            Intent intent = new Intent();
            intent.putExtra("device_id", devicePairGuideActivity.di);
            intent.putExtra("is_support_notification", devicePairGuideActivity.bi);
            intent.putExtra("product_type", devicePairGuideActivity.an);
            devicePairGuideActivity.setResult(2, intent);
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "first_pair_success_status");
        LogUtil.a("DEVMGR_MemberAndViewHelper", "isConnectSuccessGoBack value is ", b);
        if (!"true".equals(b)) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "isConnectSuccessGoBack first pair not success");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "first_pair_success_status", "true", (StorageParams) null);
        }
        devicePairGuideActivity.d();
        devicePairGuideActivity.finish();
    }

    public CustomAlertDialog.Builder c() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return null;
        }
        CustomAlertDialog.Builder e = new CustomAlertDialog.Builder(devicePairGuideActivity).e(R.string.IDS_service_area_notice_title);
        e.cyo_(R.string._2130841379_res_0x7f020f23, new DialogInterface.OnClickListener() { // from class: nxe.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "showunBindDialog onclick");
                DevicePairGuideActivity devicePairGuideActivity2 = nxe.this.e;
                if (devicePairGuideActivity2 == null) {
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    return;
                }
                devicePairGuideActivity2.ax.sendEmptyMessageDelayed(9, 5000L);
                oae.c(BaseApplication.getContext()).a(new IBaseResponseCallback() { // from class: nxe.13.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("DEVMGR_MemberAndViewHelper", "initBindBuild migrateWearDeviceList errCode：", Integer.valueOf(i2));
                        DevicePairGuideActivity devicePairGuideActivity3 = nxe.this.e;
                        if (devicePairGuideActivity3 == null || i2 != 0) {
                            return;
                        }
                        nxe.this.aa();
                        dwo.d().b(devicePairGuideActivity3.z.d(), new DevicePairGuideActivity.i(devicePairGuideActivity3));
                    }
                });
                if (devicePairGuideActivity2.ad != null) {
                    devicePairGuideActivity2.ad.dismiss();
                    devicePairGuideActivity2.ad = null;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        e.cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nxe.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "showDialogToMigrate setNegativeButton onclick");
                nxe.this.w();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity != null) {
            devicePairGuideActivity.ax.postDelayed(new Runnable() { // from class: nxe.12
                @Override // java.lang.Runnable
                public void run() {
                    nxe.this.e.z.c();
                }
            }, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        if (devicePairGuideActivity.ad != null) {
            devicePairGuideActivity.ad.dismiss();
            devicePairGuideActivity.ad = null;
        }
        devicePairGuideActivity.finish();
    }

    public void q() {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "enter stopPairGuideAnim():!");
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "stopPairGuideAnim mActivity is null");
            return;
        }
        if (devicePairGuideActivity.p != null) {
            devicePairGuideActivity.p.stop();
            devicePairGuideActivity.p = null;
        } else {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "mB1PairGuideAnimation is null!");
        }
        if (devicePairGuideActivity.q != null) {
            devicePairGuideActivity.q.stop();
            devicePairGuideActivity.p = null;
        } else {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "mB2PairGuideAnimation is null!");
        }
        if (devicePairGuideActivity.r != null) {
            devicePairGuideActivity.r.stop();
            devicePairGuideActivity.p = null;
        } else {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "mB1PairGuideAnimation is null!");
        }
    }

    private void b(String str) {
        boolean isOtaUpdate;
        boolean isSupportAutoUpdate;
        int i;
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ak = DeviceSettingsInteractors.d((Context) devicePairGuideActivity);
        devicePairGuideActivity.ag = devicePairGuideActivity.ak.e(str);
        if (devicePairGuideActivity.ag == null) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "mDeviceCapability is null");
            isOtaUpdate = false;
            isSupportAutoUpdate = false;
        } else {
            isOtaUpdate = devicePairGuideActivity.ag.isOtaUpdate();
            isSupportAutoUpdate = devicePairGuideActivity.ag.getIsSupportAutoUpdate();
        }
        DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
        boolean z = cwi.c(e, 108) && !Utils.l();
        boolean c2 = cvt.c(devicePairGuideActivity.an);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "isOtaUpdate is ", Boolean.valueOf(isOtaUpdate), " isSupportAutoUpdate is ", Boolean.valueOf(isSupportAutoUpdate), " isAw70 is ", Boolean.valueOf(c2), " mDeviceProductType is ", Integer.valueOf(devicePairGuideActivity.an), ", isSupportOtaUpgrade:", Boolean.valueOf(z), " isOverseaNoCloudVersion ", Boolean.valueOf(Utils.l()));
        if (cwc.e(devicePairGuideActivity.an, isOtaUpdate, c2) || z) {
            devicePairGuideActivity.dn.setOnCheckedChangeListener(devicePairGuideActivity.br);
            i = 0;
            devicePairGuideActivity.dl.setVisibility(0);
            devicePairGuideActivity.dn.setChecked(o());
        } else {
            i = 0;
        }
        if (isSupportAutoUpdate) {
            devicePairGuideActivity.h.setVisibility(i);
            devicePairGuideActivity.f9180a.setChecked(o());
        }
        if (z) {
            devicePairGuideActivity.h.setVisibility(8);
        }
        b(e(isOtaUpdate, e));
        b(e);
    }

    public boolean o() {
        return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isSupportPairCheckedOtaAutoUpdateSwitch();
    }

    private void b(DeviceInfo deviceInfo) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "initAutoOpenWifiView activity is null");
            return;
        }
        boolean z = cwi.c(deviceInfo, 35) || cwi.c(deviceInfo, 76);
        if (Build.VERSION.SDK_INT > 30 && CommonUtil.bh() && z) {
            devicePairGuideActivity.f.setVisibility(0);
            if (!Utils.o()) {
                devicePairGuideActivity.i.setText(R.string.IDS_device_transmit_text);
                devicePairGuideActivity.g.setText(R.string.IDS_device_transmit_tips);
                devicePairGuideActivity.j.setChecked(o());
            } else {
                devicePairGuideActivity.i.setText(R.string.IDS_device_transmit_text_overseas);
                devicePairGuideActivity.g.setText(R.string.IDS_device_transmit_tips_overseas);
                devicePairGuideActivity.j.setChecked(o());
            }
        } else {
            devicePairGuideActivity.f.setVisibility(8);
            LogUtil.h("DEVMGR_MemberAndViewHelper", "initAutoOpenWifiView is not Harmony3.0 or not support wifiP2P");
        }
        boolean l = Utils.l();
        if (!cwi.c(deviceInfo, 108) || l) {
            return;
        }
        devicePairGuideActivity.f.setVisibility(8);
        LogUtil.h("DEVMGR_MemberAndViewHelper", "initAutoOpenWifiView is Galileo");
    }

    private boolean e(boolean z, DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 58);
        boolean c3 = cwi.c(deviceInfo, 108);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "isOtaUpdate is ", Boolean.valueOf(z), "isSupportDetect is ", Boolean.valueOf(c2), "isSupportOtaUpgrade is ", Boolean.valueOf(c3));
        return z || c2 || c3;
    }

    public void r() {
        o(this.e);
        SharedPreferenceManager.c("privacy_center", "account_device", String.valueOf(System.currentTimeMillis()));
        ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "Enter showPairSuccessResult():");
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        boolean z = false;
        devicePairGuideActivity.cw.setVisibility(0);
        DeviceInfo e = jpt.e(u(), "DEVMGR_MemberAndViewHelper");
        jpp.i(e);
        jpp.e();
        if (cwi.c(e, 112)) {
            devicePairGuideActivity.d = true;
            this.g.c(e, devicePairGuideActivity);
            return;
        }
        d(R.string._2130841385_res_0x7f020f29);
        boolean z2 = cwi.c(e, 201) || cwi.c(e, 202);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "isSupport offlinemap : ", Boolean.valueOf(z2));
        if (e != null && e.getProductType() == 99 && CommonUtil.bv()) {
            z = true;
        }
        if (!z2 || z) {
            return;
        }
        oat.c().d(BaseApplication.getContext(), e.getDeviceUdid());
        OfflineMapReceiver.getInstance().onConnectStatusChanged(com.huawei.haf.application.BaseApplication.vZ_(), 2);
    }

    protected void d(int i) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        this.j = true;
        final HashMap hashMap = new HashMap(16);
        String u = u();
        hashMap.put("productId", u);
        hashMap.put("deviceId", Integer.valueOf(jfu.c(devicePairGuideActivity.an).o()));
        LogUtil.a("DEVMGR_MemberAndViewHelper", "PluginAchieve setEvent productID:", iyl.d().e(u));
        ExecutorService executorService = this.h;
        if (executorService != null && !executorService.isShutdown()) {
            this.h.execute(new Runnable() { // from class: nxe.18
                @Override // java.lang.Runnable
                public void run() {
                    DevicePairGuideActivity devicePairGuideActivity2 = nxe.this.e;
                    if (devicePairGuideActivity2 == null) {
                        return;
                    }
                    mcv.d(devicePairGuideActivity2).c(devicePairGuideActivity2, String.valueOf(1500), hashMap);
                }
            });
        }
        devicePairGuideActivity.c.stop();
        devicePairGuideActivity.cb.setVisibility(8);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "stop anims");
        devicePairGuideActivity.be = true;
        af();
        j(i);
        b(u);
        GuideInteractors guideInteractors = new GuideInteractors(BaseApplication.getContext());
        a(guideInteractors);
        e(guideInteractors);
        cRQ_(devicePairGuideActivity.an, devicePairGuideActivity.ct);
        if (this.k != 2) {
            gmn.b(devicePairGuideActivity);
        }
        ad();
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
        devicePairGuideActivity.setResult(3);
        ocp.e(jpt.e(u(), "DEVMGR_MemberAndViewHelper"));
    }

    private void b(boolean z) {
        final DevicePairGuideActivity devicePairGuideActivity;
        if (z && (devicePairGuideActivity = this.e) != null) {
            int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
            LogUtil.a("DEVMGR_MemberAndViewHelper", "initPairResultOtaPrivacyView siteId:", Integer.valueOf(m));
            if (m != 7) {
                LogUtil.h("DEVMGR_MemberAndViewHelper", "initPairResultOtaPrivacyView is not EUROPE_LOGIN_SITE_ID");
                devicePairGuideActivity.cx.setVisibility(8);
                return;
            }
            devicePairGuideActivity.cx.setVisibility(0);
            String string = devicePairGuideActivity.getResources().getString(R.string._2130846069_res_0x7f022175);
            String string2 = devicePairGuideActivity.getResources().getString(R.string.IDS_main_no_device_click);
            String format = String.format(Locale.ENGLISH, string, string2);
            SpannableString spannableString = new SpannableString(format);
            int indexOf = format.indexOf(string2);
            int length = string2.length() + indexOf;
            spannableString.setSpan(new ClickableSpan() { // from class: nxe.16
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    try {
                        devicePairGuideActivity.startActivity(new Intent(devicePairGuideActivity, (Class<?>) OtaPrivacyActivity.class));
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("DEVMGR_MemberAndViewHelper", "initPairResultOtaPrivacyView ActivityNotFoundException");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    if (textPaint != null) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(Color.parseColor("#fb6522"));
                        textPaint.setUnderlineText(false);
                    }
                }
            }, indexOf, length, 18);
            spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, length, 33);
            devicePairGuideActivity.cx.setMovementMethod(LinkMovementMethod.getInstance());
            devicePairGuideActivity.cx.setHighlightColor(devicePairGuideActivity.getResources().getColor(android.R.color.transparent));
            devicePairGuideActivity.cx.setText(spannableString);
        }
    }

    private void e(GuideInteractors guideInteractors) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        int c2 = com.huawei.haf.application.BaseApplication.c();
        LogUtil.a("DEVMGR_MemberAndViewHelper", "showPairSuccessResult CommonUtil.HIHEALTH_VERSION_CODE is ", 20100000, " appVersionCode is ", Integer.valueOf(c2));
        if (c2 >= 20100000 || guideInteractors.h()) {
            nsy.cMc_(devicePairGuideActivity, R.id.pair_text_layout).setVisibility(8);
            devicePairGuideActivity.ao.setVisibility(0);
            devicePairGuideActivity.x.setVisibility(8);
            devicePairGuideActivity.ac.setVisibility(8);
            return;
        }
        devicePairGuideActivity.dd.setText(devicePairGuideActivity.getResources().getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.ENGLISH));
    }

    private String u() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return "default";
        }
        if (TextUtils.isEmpty(devicePairGuideActivity.ae)) {
            return !TextUtils.isEmpty(devicePairGuideActivity.di) ? devicePairGuideActivity.di : "default";
        }
        return devicePairGuideActivity.ae;
    }

    public void a(DeviceInfo deviceInfo) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        if (deviceInfo.getProductType() != -1 && devicePairGuideActivity.an != deviceInfo.getProductType()) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "report device connect state not match wanted device");
            sqo.m("deviceType is error");
            return;
        }
        devicePairGuideActivity.di = deviceInfo.getDeviceIdentify();
        LogUtil.a("DEVMGR_MemberAndViewHelper", "mConnectStateChangedReceiver : DEVICE_CONNECTING！, mTempMac is", iyl.d().e(devicePairGuideActivity.di));
        Message obtainMessage = devicePairGuideActivity.ax.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.arg1 = deviceInfo.getDeviceConnectState();
        obtainMessage.obj = deviceInfo;
        devicePairGuideActivity.ax.sendMessage(obtainMessage);
        devicePairGuideActivity.ax.sendEmptyMessageDelayed(12, 120000L);
    }

    public void c(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_MemberAndViewHelper", "Enter showPairFailureResult():errorCode:", Integer.valueOf(i2));
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        this.j = false;
        a(i2);
        t(devicePairGuideActivity);
        if (jfu.m(devicePairGuideActivity.an) && devicePairGuideActivity.ah != null && devicePairGuideActivity.ah.f() != null) {
            if (i == 1) {
                devicePairGuideActivity.cu.setVisibility(0);
                devicePairGuideActivity.cu.setText(R.string._2130842171_res_0x7f02123b);
            } else if (i2 == 6) {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "showPairFailureResult() DeviceConnectState.DEVICE_WORK_MODE_CONFLICT");
                ab();
            } else {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "showPairFailureResult() other errorType");
            }
        }
        b(i, i2);
    }

    private void t(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.c.stop();
        devicePairGuideActivity.cb.setVisibility(8);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "showPairFailureResult stop animal");
        af();
        devicePairGuideActivity.be = false;
        devicePairGuideActivity.bu.setVisibility(8);
        devicePairGuideActivity.bs.setVisibility(8);
        devicePairGuideActivity.ai.setVisibility(8);
        devicePairGuideActivity.t.setVisibility(0);
        n(devicePairGuideActivity);
        devicePairGuideActivity.by.setVisibility(0);
        devicePairGuideActivity.dd.setText(devicePairGuideActivity.getResources().getString(R.string._2130841467_res_0x7f020f7b).toUpperCase(Locale.ENGLISH));
        devicePairGuideActivity.cs.setVisibility(0);
        devicePairGuideActivity.am.setVisibility(0);
        devicePairGuideActivity.cu.setVisibility(8);
        devicePairGuideActivity.l.setVisibility(8);
        devicePairGuideActivity.k.setVisibility(8);
        devicePairGuideActivity.n.setVisibility(8);
        devicePairGuideActivity.m.setVisibility(8);
        devicePairGuideActivity.cb.setVisibility(8);
        devicePairGuideActivity.cv.setVisibility(0);
        devicePairGuideActivity.cv.setImageResource(R.drawable._2131430228_res_0x7f0b0b54);
        devicePairGuideActivity.cw.setText(R.string._2130841386_res_0x7f020f2a);
        devicePairGuideActivity.cy.setVisibility(8);
        devicePairGuideActivity.dl.setVisibility(8);
        devicePairGuideActivity.s.setVisibility(8);
        devicePairGuideActivity.x.setVisibility(8);
        devicePairGuideActivity.ac.setVisibility(8);
    }

    private void a(int i) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "activity is null");
        } else {
            oau.c(jfu.c(devicePairGuideActivity.an, devicePairGuideActivity.bl, devicePairGuideActivity.bf), i);
        }
    }

    private void ab() {
        DeviceInfo deviceInfo;
        LogUtil.a("DEVMGR_MemberAndViewHelper", "showModeConflictDialog enter");
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        List<DeviceInfo> c2 = devicePairGuideActivity.af.c();
        if (c2 == null) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "currentDeviceList is null");
            return;
        }
        Iterator<DeviceInfo> it = c2.iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it.next();
            if (deviceInfo.getDeviceConnectState() == 2) {
                LogUtil.a("DEVMGR_MemberAndViewHelper", "showConflictDialog find connected device");
                break;
            }
        }
        if (deviceInfo == null) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "showConflictDialog connectedDevice is null");
            return;
        }
        String format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130842682_res_0x7f02143a), devicePairGuideActivity.af.b(deviceInfo.getProductType()), devicePairGuideActivity.af.b(devicePairGuideActivity.an));
        Intent intent = new Intent();
        intent.putExtra("content", format);
        intent.setClass(devicePairGuideActivity, WorkModeConflictDialogActivity.class);
        devicePairGuideActivity.startActivity(intent);
    }

    private void b(int i, int i2) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        int i3 = devicePairGuideActivity.an;
        if (i3 != 5) {
            if (i3 != 15) {
                if (i3 == 7) {
                    devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                    if (i == 1) {
                        LogUtil.a("DEVMGR_MemberAndViewHelper", "PairFailureResult B3 errorType ：1");
                        devicePairGuideActivity.cu.setVisibility(0);
                        devicePairGuideActivity.cu.setText(R.string._2130842667_res_0x7f02142b);
                        return;
                    }
                    i(i2);
                    return;
                }
                if (i3 != 8) {
                    if (i3 == 12) {
                        devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                        i(i2);
                        return;
                    } else if (i3 != 13) {
                        e(i, i2);
                        return;
                    }
                }
            }
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
            z();
            return;
        }
        devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        i(i2);
    }

    private void i(int i) {
        String format;
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.cw.setVisibility(0);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "enter showPairFailureNote():", Integer.valueOf(devicePairGuideActivity.an));
        devicePairGuideActivity.cu.setVisibility(0);
        String b = devicePairGuideActivity.af.b(devicePairGuideActivity.an);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "content is ", b);
        if (devicePairGuideActivity.an == 11 && ("HUAWEI CM-R1P".equals(devicePairGuideActivity.bl) || devicePairGuideActivity.getString(R.string._2130849807_res_0x7f02300f).equals(devicePairGuideActivity.bl) || devicePairGuideActivity.getString(R.string.IDS_device_r1_pro_name_title).equals(devicePairGuideActivity.bl))) {
            b = devicePairGuideActivity.getString(R.string._2130849807_res_0x7f02300f);
        } else if (!TextUtils.isEmpty(devicePairGuideActivity.bl)) {
            b = devicePairGuideActivity.bl;
        }
        if (i == -1) {
            format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string.IDS_startup_pair_device_timeout), b);
        } else if (i == 1003003) {
            format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string.IDS_device_pair_fail_sub_account), new Object[0]);
        } else {
            boolean ae = nsn.ae(BaseApplication.getContext());
            String lowerCase = Build.BRAND.toLowerCase();
            boolean z = lowerCase.equals("xiaomi") || lowerCase.equals("vivo");
            boolean z2 = LanguageUtil.h(devicePairGuideActivity) || LanguageUtil.p(devicePairGuideActivity);
            if (ae) {
                format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130844215_res_0x7f021a37, b), new Object[0]);
            } else if (z2 && z) {
                format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130850520_res_0x7f0232d8, 1, 2, b), new Object[0]);
                devicePairGuideActivity.cu.setTextSize(0, devicePairGuideActivity.getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
                devicePairGuideActivity.cu.setGravity(3);
            } else {
                format = String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130842175_res_0x7f02123f, b), new Object[0]);
            }
        }
        devicePairGuideActivity.cu.setText(format);
    }

    private void z() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "enter showPairFailureOnlyPhoneNote():", Integer.valueOf(devicePairGuideActivity.an));
        devicePairGuideActivity.cu.setVisibility(0);
        devicePairGuideActivity.cu.setText(devicePairGuideActivity.getResources().getString(nsn.ae(BaseApplication.getContext()) ? R.string._2130844214_res_0x7f021a36 : R.string._2130842174_res_0x7f02123e));
    }

    private void e(int i, int i2) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        int i3 = devicePairGuideActivity.an;
        if (i3 != 1) {
            if (i3 == 3) {
                devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
                devicePairGuideActivity.cw.setText(R.string.IDS_device_fragment_w1_pairing_guide_2);
                devicePairGuideActivity.ax.sendEmptyMessage(5);
                LogUtil.a("DEVMGR_MemberAndViewHelper", "Android Wear");
                devicePairGuideActivity.bx.setVisibility(0);
                devicePairGuideActivity.dd.setText(R.string.IDS_device_fragment_pairing_btn_open_android_wear);
                return;
            }
            if (i3 == 14) {
                devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                if (i == 1) {
                    LogUtil.a("DEVMGR_MemberAndViewHelper", "PairFailureResult GRUS errorType ：1");
                    devicePairGuideActivity.cu.setVisibility(0);
                    devicePairGuideActivity.cu.setText(R.string._2130842667_res_0x7f02142b);
                    return;
                }
                i(i2);
                return;
            }
            if (i3 == 32 || i3 == 10) {
                ac();
                return;
            }
            if (i3 == 11) {
                devicePairGuideActivity.ct.setImageResource(R.drawable._2131430609_res_0x7f0b0cd1);
                i(i2);
                return;
            } else if (i3 != 23 && i3 != 24 && i3 != 36 && i3 != 37) {
                i(i2);
                return;
            }
        }
        devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
        i(i2);
    }

    private void ac() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(devicePairGuideActivity.getResources().getString(R.string._2130842361_res_0x7f0212f9));
        String str = c;
        stringBuffer.append(str);
        stringBuffer.append(String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130842363_res_0x7f0212fb), String.valueOf(1)));
        stringBuffer.append(str);
        stringBuffer.append(String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130842362_res_0x7f0212fa), String.valueOf(2)));
        stringBuffer.append(str);
        stringBuffer.append(String.format(Locale.ENGLISH, devicePairGuideActivity.getResources().getString(R.string._2130842490_res_0x7f02137a), String.valueOf(3)));
        stringBuffer.append(str);
        devicePairGuideActivity.cw.setText(devicePairGuideActivity.getResources().getString(R.string._2130841386_res_0x7f020f2a));
        devicePairGuideActivity.cw.setVisibility(8);
        devicePairGuideActivity.dh.setText(stringBuffer.toString());
        devicePairGuideActivity.dh.setVisibility(0);
        devicePairGuideActivity.ax.sendEmptyMessage(5);
        LogUtil.a("DEVMGR_MemberAndViewHelper", "Android Wear");
        devicePairGuideActivity.bx.setVisibility(0);
        devicePairGuideActivity.dd.setText(R.string.IDS_device_fragment_pairing_btn_open_android_wear);
    }

    public void m() {
        v();
    }

    public void k() {
        v();
    }

    public void n() {
        v();
    }

    public void l() {
        v();
    }

    public void cRW_(Message message) {
        int i = message.arg1;
        LogUtil.a("DEVMGR_MemberAndViewHelper", "updatePairProcessTips status: ", Integer.valueOf(i));
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "updatePairProcessTips activity is null");
            return;
        }
        String string = devicePairGuideActivity.getString(R.string.IDS_device_paring_tip_des_info_21);
        switch (i) {
            case 9:
                string = devicePairGuideActivity.getString(R.string.IDS_device_pair_account_inconsistent);
                devicePairGuideActivity.cw.setVisibility(0);
                break;
            case 10:
                devicePairGuideActivity.ax.removeMessages(12);
                string = devicePairGuideActivity.getString(R.string.IDS_device_pair_select_the_language_on_the_watch);
                devicePairGuideActivity.av.setVisibility(0);
                devicePairGuideActivity.w.setVisibility(8);
                devicePairGuideActivity.cw.setVisibility(0);
                af();
                devicePairGuideActivity.bk = true;
                break;
            case 11:
                cRP_(message, devicePairGuideActivity);
                break;
            case 12:
            default:
                LogUtil.h("DEVMGR_MemberAndViewHelper", "updatePairProcessTips status is not match, return.");
                return;
            case 13:
                string = devicePairGuideActivity.getString(R.string.IDS_device_pair_account_inconsistent_opt);
                devicePairGuideActivity.cw.setVisibility(0);
                break;
            case 14:
                devicePairGuideActivity.ax.removeMessages(12);
                string = devicePairGuideActivity.getString(R.string.IDS_device_pair_watch_clean_restart);
                devicePairGuideActivity.w.setVisibility(8);
                devicePairGuideActivity.cw.setVisibility(0);
                devicePairGuideActivity.cz[0] = R.string.IDS_device_pair_watch_clean_restart;
                devicePairGuideActivity.f();
                break;
        }
        devicePairGuideActivity.cw.setText(string);
    }

    private void cRP_(Message message, final DevicePairGuideActivity devicePairGuideActivity) {
        if (message.obj instanceof DeviceInfo) {
            final DeviceInfo deviceInfo = (DeviceInfo) message.obj;
            DevicePairGuideUtil.a(deviceInfo, new DevicePairGuideUtil.ShowKidDialogCallback() { // from class: nxe.4
                @Override // com.huawei.ui.device.activity.pairing.DevicePairGuideUtil.ShowKidDialogCallback
                public void onResult(boolean z) {
                    LogUtil.a("DEVMGR_MemberAndViewHelper", "isShowDialog:", Boolean.valueOf(z));
                    if (z) {
                        nxe.this.c(deviceInfo);
                        nxe.this.c(devicePairGuideActivity);
                        return;
                    }
                    int deviceFactoryReset = deviceInfo.getDeviceFactoryReset();
                    LogUtil.a("DEVMGR_MemberAndViewHelper", "type value:", Integer.valueOf(deviceFactoryReset));
                    if (deviceFactoryReset == 1) {
                        nxe.this.d(devicePairGuideActivity, deviceInfo);
                    } else {
                        LogUtil.a("DEVMGR_MemberAndViewHelper", "type value not 1");
                    }
                }
            });
        } else {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "message is not DeviceInfo");
        }
    }

    public void c(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Result", 2);
        hashMap.put("deviceType", "HDK_WEAR");
        hashMap.put("Action", 3);
        hashMap.put("Page", 4);
        hashMap.put("deviceName", deviceInfo.getDeviceName());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.UNAUTHORIZED_DEVICE_PAIRING_FAILURE.value(), hashMap, 0);
    }

    public void c(final DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.runOnUiThread(new Runnable() { // from class: nxe.2
            @Override // java.lang.Runnable
            public void run() {
                if (nxe.this.d != null && nxe.this.d.isShowing()) {
                    nxe.this.d.cancel();
                }
                NoTitleCustomAlertDialog.Builder czC_ = new NoTitleCustomAlertDialog.Builder(devicePairGuideActivity.b).e(String.format(Locale.ENGLISH, nxe.this.e.b.getString(R.string._2130845618_res_0x7f021fb2), nxe.this.e.bl)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: nxe.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(devicePairGuideActivity.di)) {
                            LogUtil.a("DEVMGR_MemberAndViewHelper", "activity.mTempMac is not empty and unPair device");
                            ArrayList arrayList = new ArrayList(16);
                            arrayList.add(devicePairGuideActivity.di);
                            devicePairGuideActivity.af.e(arrayList, true);
                            devicePairGuideActivity.d();
                        } else {
                            LogUtil.h("DEVMGR_MemberAndViewHelper", "activity.mTempMac isEmpty");
                        }
                        devicePairGuideActivity.finish();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                nxe.this.d = czC_.e();
                nxe.this.d.setCancelable(false);
                if (nxe.this.d.isShowing() || devicePairGuideActivity.isFinishing()) {
                    return;
                }
                nxe.this.d.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DevicePairGuideActivity devicePairGuideActivity, DeviceInfo deviceInfo) {
        new GuideInteractors(BaseApplication.getContext()).d(devicePairGuideActivity.di, true);
        AgreementDeclarationActivity.b = true;
        Intent intent = new Intent();
        intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
        intent.setClass(devicePairGuideActivity, AgreementDeclarationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("deviceInfo", deviceInfo);
        intent.putExtras(bundle);
        intent.putExtra("device_country_code", deviceInfo.getCountryCode());
        intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
        devicePairGuideActivity.startActivity(intent);
    }

    public void c(int i) {
        LogUtil.a("DEVMGR_MemberAndViewHelper", "pairProcessOtherAnimation deviceType: ", Integer.valueOf(i));
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null || i == 0 || i == -1) {
            return;
        }
        int n = jfu.c(i).n();
        LogUtil.a("DEVMGR_MemberAndViewHelper", "pairProcessOtherAnimation deviceShapes: ", Integer.valueOf(n));
        if (n == 1) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
            ae();
            return;
        }
        if (n == 2) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430656_res_0x7f0b0d00);
            ae();
            return;
        }
        if (n == 3) {
            if (cvt.c(i)) {
                l();
                return;
            } else {
                devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                ae();
                return;
            }
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "pairProcessOtherAnimation isBand: ", Boolean.valueOf(jfu.c(i).s()));
        if (jfu.c(i).s()) {
            if (cvt.c(i)) {
                l();
                return;
            } else {
                devicePairGuideActivity.ct.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                ae();
                return;
            }
        }
        if (jfu.c(i).i() == 6) {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430635_res_0x7f0b0ceb);
            ae();
        } else {
            devicePairGuideActivity.ct.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
            ae();
        }
    }

    private void ae() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        if (obb.e(devicePairGuideActivity.an)) {
            devicePairGuideActivity.cw.setVisibility(0);
            devicePairGuideActivity.cu.setVisibility(8);
        } else {
            devicePairGuideActivity.cu.setVisibility(8);
            devicePairGuideActivity.cw.setVisibility(0);
            devicePairGuideActivity.cw.setText(devicePairGuideActivity.getString(devicePairGuideActivity.cz[0], new Object[]{devicePairGuideActivity.aj}));
        }
    }

    private void ad() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null || TextUtils.isEmpty(devicePairGuideActivity.di)) {
            return;
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "enter setDeviceActiveTime");
        if (SharedPreferenceManager.d(BaseApplication.getContext(), String.valueOf(2000000)).contains(bgv.e(devicePairGuideActivity.di))) {
            LogUtil.a("DEVMGR_MemberAndViewHelper", "setDeviceActiveTime device already active.");
            return;
        }
        StorageParams storageParams = new StorageParams();
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(2000000), bgv.e(devicePairGuideActivity.di), String.valueOf(currentTimeMillis), storageParams);
    }

    private void cRS_(Context context, ImageView imageView, int i, int i2) {
        AnimationDrawable cRK_ = nxh.c().cRK_(context, imageView, i, i2);
        this.b = cRK_;
        if (cRK_ != null) {
            cRK_.start();
        } else {
            LogUtil.c("DEVMGR_MemberAndViewHelper", "framesAnimation is null");
        }
    }

    private void af() {
        AnimationDrawable animationDrawable = this.b;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    public int b() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return -1;
        }
        return devicePairGuideActivity.an;
    }

    public void b(int i) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity != null) {
            devicePairGuideActivity.an = i;
        }
    }

    public void c(String str) {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity != null) {
            devicePairGuideActivity.dj = str;
        }
    }

    public void t() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "resetUserSelectProductType mActivity is null.");
            return;
        }
        LogUtil.a("DEVMGR_MemberAndViewHelper", "resetUserSelectProductType mDeviceProductType :", Integer.valueOf(devicePairGuideActivity.an), ", mSaveDeviceProductType :", Integer.valueOf(devicePairGuideActivity.de));
        if (devicePairGuideActivity.an != devicePairGuideActivity.de) {
            devicePairGuideActivity.an = devicePairGuideActivity.de;
        }
    }

    private void v() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null) {
            return;
        }
        devicePairGuideActivity.cs.setVisibility(8);
        devicePairGuideActivity.am.setVisibility(8);
        devicePairGuideActivity.cb.setVisibility(8);
        if (nsn.ag(devicePairGuideActivity)) {
            devicePairGuideActivity.l.setVisibility(8);
            devicePairGuideActivity.n.setVisibility(0);
            devicePairGuideActivity.m.setVisibility(0);
            cRS_(devicePairGuideActivity, devicePairGuideActivity.m, R.array._2130968681_res_0x7f040069, 200);
        } else {
            devicePairGuideActivity.l.setVisibility(0);
            devicePairGuideActivity.n.setVisibility(8);
            devicePairGuideActivity.k.setVisibility(0);
            cRS_(devicePairGuideActivity, devicePairGuideActivity.k, R.array._2130968681_res_0x7f040069, 200);
        }
        devicePairGuideActivity.cu.setVisibility(8);
        devicePairGuideActivity.cu.setText(devicePairGuideActivity.getString(devicePairGuideActivity.o[0], new Object[]{devicePairGuideActivity.aj}));
    }

    public void p() {
        DevicePairGuideActivity devicePairGuideActivity = this.e;
        if (devicePairGuideActivity == null || devicePairGuideActivity.b == null) {
            LogUtil.h("DEVMGR_MemberAndViewHelper", "showSelectVersionDialog mActivity is null");
            return;
        }
        this.k = 1;
        View inflate = LayoutInflater.from(devicePairGuideActivity.b).inflate(R.layout.dialog_select_mode, (ViewGroup) null);
        final HealthRadioButton healthRadioButton = (HealthRadioButton) inflate.findViewById(R.id.settings_mode_self_radioButton);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) inflate.findViewById(R.id.settings_mode_family_members_radioButton);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.settings_mode_self_layout);
        RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(R.id.settings_mode_family_layout);
        healthRadioButton2.setChecked(false);
        healthRadioButton.setChecked(true);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: nxe.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                healthRadioButton2.setChecked(false);
                healthRadioButton.setChecked(true);
                nxe.this.k = 1;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: nxe.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                healthRadioButton2.setChecked(true);
                healthRadioButton.setChecked(false);
                nxe.this.k = 2;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a(healthRadioButton2, healthRadioButton);
        cRR_(devicePairGuideActivity, inflate);
    }

    private void a(final HealthRadioButton healthRadioButton, final HealthRadioButton healthRadioButton2) {
        healthRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: nxe.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    healthRadioButton.setChecked(true);
                    healthRadioButton2.setChecked(false);
                    nxe.this.k = 2;
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        healthRadioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: nxe.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    healthRadioButton.setChecked(false);
                    healthRadioButton2.setChecked(true);
                    nxe.this.k = 1;
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void cRR_(final DevicePairGuideActivity devicePairGuideActivity, View view) {
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(devicePairGuideActivity.b).e(R.string._2130844384_res_0x7f021ae0).cyp_(view).cyo_(R.string._2130841131_res_0x7f020e2b, new DialogInterface.OnClickListener() { // from class: nxe.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                nxe nxeVar = nxe.this;
                nxeVar.c(devicePairGuideActivity, nxeVar.k);
                oau.e(nxe.this.k);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: nxe.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DevicePairGuideActivity devicePairGuideActivity2 = devicePairGuideActivity;
                if (devicePairGuideActivity2 != null) {
                    devicePairGuideActivity2.finish();
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        c2.setCancelable(false);
        c2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DevicePairGuideActivity devicePairGuideActivity, int i) {
        jpo.a(devicePairGuideActivity.ae, i);
        b(devicePairGuideActivity);
    }

    private void k(DevicePairGuideActivity devicePairGuideActivity) {
        if (jfu.h(devicePairGuideActivity.an)) {
            devicePairGuideActivity.bj = "wear_band";
        } else {
            devicePairGuideActivity.bj = "wear_watch";
        }
        devicePairGuideActivity.cs.setVisibility(0);
        devicePairGuideActivity.cv.setVisibility(8);
        q(devicePairGuideActivity);
    }
}
