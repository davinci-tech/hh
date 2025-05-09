package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.utlis.clouddevice.DevicePopTipBiUtil;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper;
import com.huawei.ui.homewear21.home.holder.WearHomeTipHolder;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes6.dex */
public class pdp extends WearHomeBaseCard {
    private NoTitleCustomAlertDialog g;
    private WearHomeTipHolder i;
    private NoTitleCustomAlertDialog j;
    private int c = 2;

    /* renamed from: a, reason: collision with root package name */
    private DeviceCapability f16075a = null;
    private boolean b = false;
    private boolean d = true;
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: pdp.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("WearHomeTipCard", "EcgServiceActiveCallback onResponse errCode:", Integer.valueOf(i));
            if (i == 0) {
                pdp.this.b = true;
                pdp.this.w();
            }
            pdp.this.c();
        }
    };

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
    }

    public void b(boolean z) {
        this.d = z;
    }

    public pdp(Context context, WearHomeActivity wearHomeActivity) {
        this.mContext = context;
        this.mActivity = wearHomeActivity;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.i = new WearHomeTipHolder(layoutInflater.inflate(R.layout.wear_home_tip_layout, viewGroup, false));
        LogUtil.a("WearHomeTipCard", "getCardViewHolder onUiCreate");
        p();
        return this.i;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        LogUtil.a("WearHomeTipCard", "deviceConnectionChange:", Integer.valueOf(i));
        this.c = i;
        z();
        LogUtil.a("WearHomeTipCard", "deviceConnectionChange mSensitiveUnbind is " + this.mActivity.t, ",isBRDevice is " + this.d);
        if (this.mActivity.t && !this.d && i == 3) {
            LogUtil.a("WearHomeTipCard", "ble device unpair requestPermissions");
            this.mActivity.t = false;
            if (this.mActivity.c("deviceConnectionChange")) {
                return;
            }
            obb.c(this.mActivity.g, this.mActivity, this.mActivity.e, this.mActivity.b.getDeviceBluetoothType());
        }
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        z();
    }

    private void p() {
        z();
    }

    private void m() {
        if (!CommonUtil.bh() && this.mActivity.i) {
            boolean areNotificationsEnabled = NotificationManagerCompat.from(this.mContext).areNotificationsEnabled();
            LogUtil.a("WearHomeTipCard", "notification switch status:", Boolean.valueOf(areNotificationsEnabled));
            if (!areNotificationsEnabled) {
                s();
                return;
            } else {
                j();
                return;
            }
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("WearHomeTipCard", "dealOtherNotificationDialog in. ");
        if (this.mActivity == null) {
            return;
        }
        if (this.c != 2) {
            this.i.dmG_().setVisibility(8);
            return;
        }
        if (this.mContext == null) {
            return;
        }
        if (lcu.e() && ((!lcu.d(this.mContext) || jrg.d()) && pep.e("wear_keep_app_alive_tip_time", 7))) {
            r();
        } else {
            a(new WechatDeviceProviderHelper.CheckResultListener() { // from class: pdp.2
                @Override // com.huawei.ui.homewear21.home.card.WechatDeviceProviderHelper.CheckResultListener
                public void onResult(boolean z) {
                    if (z) {
                        pdp.this.l();
                    } else {
                        pdp.this.g();
                    }
                }
            });
        }
    }

    private void r() {
        LogUtil.a("WearHomeTipCard", "initKeepAppAliveTip");
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130845370_res_0x7f021eba).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        String string = this.mContext.getResources().getString(R.string._2130847324_res_0x7f02265c);
        String string2 = this.mContext.getResources().getString(R.string._2130847325_res_0x7f02265d);
        a(DevicePopTipBiUtil.Content.THIRD_PARTY_KEEP_ALIVE, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        nlg.cxV_(new IBaseResponseCallback() { // from class: pdp.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipCard", "initKeepAppAliveTip onResponse errCode:", Integer.valueOf(i));
                if (pdp.this.mActivity == null) {
                    LogUtil.h("WearHomeTipCard", "initKeepAppAliveTip onResponse mActivity == null");
                    return;
                }
                if (i == 0) {
                    jrg.c(false);
                    pdp.this.a(DevicePopTipBiUtil.Content.THIRD_PARTY_KEEP_ALIVE, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
                    pdp.this.mActivity.x.execute(new Runnable() { // from class: pdp.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            OpAnalyticsUtil.getInstance().setRiskWarningEvent("gotoKeepAliveSetting", "TipKeepAppAlive");
                            obb.d(pdp.this.mActivity, "11073");
                        }
                    });
                } else {
                    if (i == 100000) {
                        jrg.c(false);
                        pep.b("wear_keep_app_alive_tip_time");
                        pdp.this.a(DevicePopTipBiUtil.Content.THIRD_PARTY_KEEP_ALIVE, DevicePopTipBiUtil.Action.CANCEL, DevicePopTipBiUtil.Event.CLICK);
                        return;
                    }
                    LogUtil.h("WearHomeTipCard", "initKeepAppAliveTip onResponse nothing");
                }
            }
        }, this.i.dmG_(), string, string2, null);
    }

    private void f() {
        if (this.c != 2) {
            LogUtil.h("WearHomeTipCard", "dealMissSensitiveNotificationDialog device disconnect.");
            this.i.dmG_().setVisibility(8);
        } else if (pep.d(this.mContext, this.mActivity.f)) {
            t();
        } else {
            o();
        }
    }

    private void h() {
        if (pep.i(this.mContext)) {
            x();
        } else {
            m();
        }
    }

    private void x() {
        ReleaseLogUtil.e("R_WearHomeTipCard", "reopenNotificationTip");
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130842041_res_0x7f0211b9).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        String string = this.mContext.getResources().getString(R.string._2130847551_res_0x7f02273f);
        String string2 = this.mContext.getResources().getString(R.string._2130847552_res_0x7f022740);
        String string3 = this.mContext.getResources().getString(R.string._2130847553_res_0x7f022741);
        String string4 = this.mContext.getResources().getString(R.string._2130847554_res_0x7f022742);
        a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        nlg.cxU_(new IBaseResponseCallback() { // from class: pdp.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse errCode:", Integer.valueOf(i));
                if (pdp.this.mActivity == null) {
                    LogUtil.h("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse mActivity == null");
                    return;
                }
                if (i == 0) {
                    pdp.this.a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
                    pdp.this.mActivity.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                } else if (i == 100000) {
                    pdp.this.a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION, DevicePopTipBiUtil.Action.CANCEL, DevicePopTipBiUtil.Event.CLICK);
                    pep.b("wear_reopen_notification_time");
                } else {
                    LogUtil.h("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse nothing");
                }
            }
        }, this.i.dmG_(), string, string2, null, string3, string4);
    }

    public void e() {
        boolean z;
        if (this.mActivity.q != null) {
            String c = oak.b().c();
            LogUtil.a("WearHomeTipCard", "isShowMissSensitiveNotificationDialog wearOsPkg:", c);
            if (this.mActivity.f == 32) {
                z = this.mActivity.q.a(c);
            } else {
                z = this.mActivity.q.b() && this.mActivity.q.e();
            }
            ReleaseLogUtil.e("R_WearHomeTipCard", "isShowMissSensitiveNotificationDialog isOpened:", Boolean.valueOf(z));
            if (z) {
                b();
            }
        }
    }

    public void b() {
        a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION_DIALOG, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        String string = this.mContext.getResources().getString(R.string._2130847552_res_0x7f022740);
        String string2 = this.mContext.getResources().getString(R.string._2130847553_res_0x7f022741);
        String string3 = this.mContext.getResources().getString(R.string._2130847554_res_0x7f022742);
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).czx_(dlU_(String.format(string, string2, string3), string2, string3)).czC_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: pdl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.this.dlZ_(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: pdt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.this.dma_(view);
            }
        }).e();
        this.j = e;
        e.setCancelable(false);
        this.j.show();
    }

    /* synthetic */ void dlZ_(View view) {
        LogUtil.a("WearHomeTipCard", "showSwitchNotifactionDialog setting");
        a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION_DIALOG, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
        this.mActivity.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dma_(View view) {
        a(DevicePopTipBiUtil.Content.REOPEN_NOTIFICATION_DIALOG, DevicePopTipBiUtil.Action.CANCEL, DevicePopTipBiUtil.Event.CLICK);
        ViewClickInstrumentation.clickOnView(view);
    }

    private SpannableString dlU_(String str, String str2, String str3) {
        SpannableString spannableString = new SpannableString(str);
        nsi.cKH_(spannableString, str2, new StyleSpan(1));
        nsi.cKH_(spannableString, str3, new StyleSpan(1));
        return spannableString;
    }

    private void t() {
        ReleaseLogUtil.e("R_WearHomeTipCard", "initMissSensitiveNotificationTip");
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130847540_res_0x7f022734).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        String string = this.mContext.getResources().getString(R.string._2130847538_res_0x7f022732);
        String string2 = this.mContext.getResources().getString(R.string._2130847539_res_0x7f022733);
        a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        nlg.cxV_(new IBaseResponseCallback() { // from class: pdp.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse errCode:", Integer.valueOf(i));
                if (pdp.this.mActivity == null) {
                    LogUtil.h("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse mActivity == null");
                    return;
                }
                if (i == 0) {
                    pdp.this.a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
                    if (jgf.b().a(pdp.this.mActivity.g)) {
                        pdp.this.v();
                        return;
                    } else {
                        pdp.this.u();
                        return;
                    }
                }
                if (i == 100000) {
                    pdp.this.a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION, DevicePopTipBiUtil.Action.CANCEL, DevicePopTipBiUtil.Event.CLICK);
                    pep.b("wear_sensitive_notification_time");
                } else {
                    LogUtil.h("WearHomeTipCard", "initMissSensitiveNotificationTip onResponse nothing");
                }
            }
        }, this.i.dmG_(), string, string2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getResources().getString(R.string.IDS_device_ota_later_note)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: pdo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.dlW_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dlW_(View view) {
        LogUtil.a("WearHomeTipCard", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d() {
        a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION_SECONDARY, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getResources().getString(R.string._2130847550_res_0x7f02273e)).czC_(R.string._2130847541_res_0x7f022735, new View.OnClickListener() { // from class: pdu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.this.dlX_(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: pdq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.this.dlY_(view);
            }
        }).e();
        this.g = e;
        e.setCancelable(false);
        this.g.show();
    }

    /* synthetic */ void dlX_(View view) {
        LogUtil.a("WearHomeTipCard", "requestPermissions try again");
        a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION_SECONDARY, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
        if (this.mActivity.c("showMissSensitiveNotificationDialog")) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            obb.c(this.mActivity.g, this.mActivity, this.mActivity.e, this.mActivity.b.getDeviceBluetoothType());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void dlY_(View view) {
        a(DevicePopTipBiUtil.Content.SENSITIVE_NOTIFICATION_SECONDARY, DevicePopTipBiUtil.Action.CANCEL, DevicePopTipBiUtil.Event.CLICK);
        if (this.mActivity != null) {
            this.mActivity.k();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.mActivity.t = true;
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.mActivity.g);
        if (this.mActivity.c("unbindAndReconnectDevice")) {
            return;
        }
        int deviceBluetoothType = this.mActivity.b.getDeviceBluetoothType();
        boolean z = deviceBluetoothType == 1;
        this.d = z;
        ReleaseLogUtil.e("R_WearHomeTipCard", "unbindAndReconnectDevice isBRDevice:", Boolean.valueOf(z), " deviceBlueToothType:", Integer.valueOf(deviceBluetoothType));
        if (this.d) {
            SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", true);
            ReleaseLogUtil.e("R_WearHomeTipCard", "set WEARABLE_UNPAIR_RECONNECTION true");
        }
        oxa.a().c(arrayList, this.d);
    }

    private void a(WechatDeviceProviderHelper.CheckResultListener checkResultListener) {
        if (this.mActivity.b == null) {
            ReleaseLogUtil.d("R_WearHomeTipCard", "checkNeedShowWechatCard mCurrentDeviceInfo is null");
            checkResultListener.onResult(false);
            return;
        }
        boolean a2 = SharedPreferenceManager.a(Integer.toString(10006), "banner_do_not_ask_again_tag_wechat_device" + this.mActivity.b.getDeviceUdid(), false);
        LogUtil.a("WearHomeTipCard", "tag_wechat_device", "isNotAskAgain: ", Boolean.valueOf(a2));
        if (a2) {
            checkResultListener.onResult(false);
        } else if (this.mActivity.d() == null) {
            checkResultListener.onResult(false);
        } else {
            this.mActivity.d().e(checkResultListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (i()) {
            y();
        }
    }

    private void y() {
        LogUtil.a("WearHomeTipCard", "showEarphonePairToastView");
        if (this.mActivity.s.d()) {
            LogUtil.a("WearHomeTipCard", "earphone pair dialog is showing. ");
        } else {
            jgs.c().c(this.mActivity.b, new EarPhoneResponseCallback() { // from class: pdp.8
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
                public void onResponse(int i, final cuz cuzVar) {
                    pdp.this.mActivity.runOnUiThread(new Runnable() { // from class: pdp.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            cuz cuzVar2 = cuzVar;
                            if (cuzVar2 == null || TextUtils.isEmpty(cuzVar2.c())) {
                                pdp.this.n();
                            } else {
                                if (jgs.c().b(cuzVar.c())) {
                                    return;
                                }
                                pdp.this.n();
                            }
                        }
                    });
                }
            });
        }
    }

    private boolean i() {
        LogUtil.a("WearHomeTipCard", "checkNeedShowEarphonePair in.");
        if (!cwi.c(this.mActivity.b, 112)) {
            LogUtil.h("WearHomeTipCard", "showEarphonePairToastView not support. ");
            return false;
        }
        String b = SharedPreferenceManager.b(this.mContext, Integer.toString(10006), "banner_do_not_ask_again_tag_earphone_pair");
        LogUtil.a("WearHomeTipCard", "tag_earphone_pair", "isNotAskAgain: ", b);
        return !"true".equals(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("WearHomeTipCard", "initEarphonePairToastView in. ");
        if (this.mContext == null || this.mActivity == null) {
            return;
        }
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130846322_res_0x7f022272).toUpperCase(Locale.ENGLISH));
        this.i.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        final DeviceInfo a2 = DeviceSettingsInteractors.d(this.mContext).a();
        if (a2 == null) {
            LogUtil.h("WearHomeTipCard", "initEarphonePairToastView refresh dialog Support deviceInfo is null");
            return;
        }
        nlg.cxV_(new IBaseResponseCallback() { // from class: pdp.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipCard", "initEarphonePairToastView onResponse errCode:", Integer.valueOf(i));
                if (i == 0) {
                    pdp.this.mActivity.s.e(a2, 103, true);
                } else if (i == 100000) {
                    LogUtil.a("WearHomeTipCard", "initEarphonePairToastView click ignore or never mind. ");
                } else {
                    LogUtil.h("WearHomeTipCard", "initEarphonePairToastView onResponse nothing");
                }
            }
        }, this.i.dmG_(), this.mContext.getResources().getString(R.string._2130846320_res_0x7f022270), this.mContext.getResources().getString(R.string._2130846321_res_0x7f022271), "tag_earphone_pair");
    }

    private void s() {
        if (this.mContext == null) {
            return;
        }
        this.i.c().setText(this.mContext.getResources().getString(R.string.IDS_device_notification_open).toUpperCase(Locale.ENGLISH));
        this.i.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        String string = this.mContext.getResources().getString(R.string._2130842829_res_0x7f0214cd);
        final RelativeLayout dmG_ = this.i.dmG_();
        if (!nlg.cxO_(BaseApplication.getContext(), dmG_, "wear_activity_note_permission_no_agin_tip")) {
            LogUtil.a("WearHomeTipCard", "not need remind");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(dmG_, R.id.toast_title_tv_bold);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(dmG_, R.id.toast_title_tv);
        healthTextView.setVisibility(8);
        healthTextView2.setText(string);
        final IBaseResponseCallback k = k();
        final int cxN_ = nlg.cxN_(BaseApplication.getContext(), dmG_, "wear_activity_note_permission_count", 1, k);
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), "wear_activity_note_permission_is_show", "true", new StorageParams());
        ((LinearLayout) nsy.cMd_(dmG_, R.id.toast_no_notice_layout)).setOnClickListener(new View.OnClickListener() { // from class: pdr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pdp.dlV_(dmG_, k, cxN_, view);
            }
        });
        nlg.cxP_(BaseApplication.getContext(), k, dmG_, "wear_activity_note_permission_dialog_time", "wear_activity_note_permission_count", "wear_activity_note_permission_is_show", cxN_);
    }

    static /* synthetic */ void dlV_(RelativeLayout relativeLayout, IBaseResponseCallback iBaseResponseCallback, int i, View view) {
        nlg.cxL_(relativeLayout, iBaseResponseCallback, BaseApplication.getContext(), "wear_activity_note_permission_no_agin_tip", "wear_activity_note_permission_is_show");
        nlg.e(BaseApplication.getContext(), "wear_activity_note_permission_dialog_time", "wear_activity_note_permission_count", i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private IBaseResponseCallback k() {
        return new IBaseResponseCallback() { // from class: pdp.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ResolveInfo resolveActivity;
                LogUtil.a("WearHomeTipCard", "onResponse errorCode:", Integer.valueOf(i));
                if (i == 0) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    intent.putExtra("android.provider.extra.APP_PACKAGE", pdp.this.mActivity.getPackageName());
                    LogUtil.a("WearHomeTipCard", "CommonAutoTestToast onResponse.");
                    PackageManager packageManager = pdp.this.mActivity.getPackageManager();
                    if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
                        return;
                    }
                    intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                    pdp.this.mActivity.startActivity(intent);
                }
            }
        };
    }

    private void q() {
        if (this.mContext == null) {
            return;
        }
        this.i.c().setText(this.mContext.getResources().getString(R.string.IDS_device_notification_open).toUpperCase(Locale.ENGLISH));
        this.i.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase(Locale.ENGLISH));
        DeviceInfo a2 = DeviceSettingsInteractors.d(this.mContext).a();
        if (a2 == null) {
            LogUtil.h("WearHomeTipCard", "refresh dialog Support deviceInfo is null");
            return;
        }
        String string = this.mContext.getResources().getString(R.string.IDS_device_notification_watch);
        String string2 = this.mContext.getResources().getString(R.string.IDS_device_notification_watch_lower_case);
        if (jfu.h(a2.getProductType())) {
            string = this.mContext.getResources().getString(R.string.IDS_device_notification_band);
            string2 = this.mContext.getResources().getString(R.string.IDS_device_notification_band_lower_case);
        }
        nlg.cya_(BaseApplication.getContext(), "notification_open_tip", new IBaseResponseCallback() { // from class: pdp.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearHomeTipCard", "onResponse errCode:", Integer.valueOf(i));
                if (i != 0) {
                    if (i == 100000) {
                        pdp.this.j();
                        return;
                    } else {
                        LogUtil.h("WearHomeTipCard", "onResponse nothing");
                        return;
                    }
                }
                try {
                    Intent intent = new Intent(pdp.this.mContext, (Class<?>) NotificationSettingActivity.class);
                    intent.putExtra("isFromDialog", true);
                    intent.putExtra("device_id", pdp.this.mActivity.g);
                    pdp.this.mContext.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("WearHomeTipCard", "initNotificationOpenToastView ActivityNotFoundException");
                }
            }
        }, this.i.dmG_(), String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string.IDS_device_notification_switch_closed_remind_text), string), String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string.IDS_device_notification_open_help_remind_text), string2));
    }

    private void z() {
        if (this.i == null) {
            return;
        }
        f();
    }

    public void c() {
        LogUtil.a("WearHomeTipCard", "enter showNotificationView");
        DeviceCapability e = DeviceSettingsInteractors.d(this.mContext).e(this.mActivity.g);
        this.f16075a = e;
        boolean z = e != null && e.isMessageAlert();
        this.i.dmG_().setVisibility(8);
        if (jpo.c(this.mActivity.g) == 2) {
            LogUtil.h("WearHomeTipCard", "updateHealthData is family_pair_mode");
            return;
        }
        if (this.mActivity.k) {
            return;
        }
        String b = SharedPreferenceManager.b(this.mContext, Integer.toString(10006), "wear_activity_tip_no_againnotification_open_tip");
        LogUtil.a("WearHomeTipCard", "notRemind:", b, "isSupportMessage:", Boolean.valueOf(z));
        if (!"true".equals(b) && !jjb.b().c() && z) {
            q();
        } else {
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        boolean z = false;
        boolean isSupportEcgAuth = this.mActivity.f9644a != null ? this.mActivity.f9644a.isSupportEcgAuth() : false;
        if (cwi.c(this.mActivity.b, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce")) {
            z = true;
        }
        boolean o = Utils.o();
        LogUtil.a("WearHomeTipCard", "startEcgForParamsByCapability isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isOversea:", Boolean.valueOf(o));
        if (z) {
            LogUtil.a("WearHomeTipCard", "startEcgForParamsByCapability start ecg analysis");
            mxv.b(BaseApplication.getContext(), "com.huawei.health.h5.ecgce", 2, "interpretation");
        } else {
            if (!isSupportEcgAuth || o) {
                return;
            }
            mxv.b(BaseApplication.getContext(), "com.huawei.health.ecg.collection", 2, "interpretation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("WearHomeTipCard", "initBindWechatCardView");
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130846630_res_0x7f0223a6).toUpperCase(Locale.ENGLISH));
        this.i.d().setText(this.mContext.getResources().getString(R.string._2130842880_res_0x7f021500).toUpperCase(Locale.ENGLISH));
        this.i.c().setMaxWidth(nsn.c(this.mContext, 120.0f));
        this.i.d().setMaxWidth(nsn.c(this.mContext, 120.0f));
        String string = this.mContext.getResources().getString(R.string._2130846628_res_0x7f0223a4);
        String string2 = this.mContext.getResources().getString(R.string._2130846629_res_0x7f0223a5);
        final RelativeLayout dmG_ = this.i.dmG_();
        a(DevicePopTipBiUtil.Content.WECHAT_DEVICE_BINDING, DevicePopTipBiUtil.Action.NONE, DevicePopTipBiUtil.Event.SHOW);
        nlg.cxT_(BaseApplication.getContext(), new IBaseResponseCallback() { // from class: pdp.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (nsn.o()) {
                    return;
                }
                if (pdp.this.mActivity.b == null) {
                    ReleaseLogUtil.d("R_WearHomeTipCard", "initBindWechatCardView onResponse mCurrentDeviceInfo is null");
                    return;
                }
                LogUtil.a("WearHomeTipCard", "onResponse errCode:", Integer.valueOf(i));
                if (i == 0) {
                    LogUtil.a("WearHomeTipCard", "showBindWechatLayout bindWechat");
                    pdp.this.mActivity.d().c(pdp.this.mActivity);
                    pdp.this.a(DevicePopTipBiUtil.Content.WECHAT_DEVICE_BINDING, DevicePopTipBiUtil.Action.DETERMINED, DevicePopTipBiUtil.Event.CLICK);
                    dmG_.setVisibility(8);
                    return;
                }
                if (i == 100000) {
                    SharedPreferenceManager.e(Integer.toString(10006), "banner_do_not_ask_again_tag_wechat_device" + pdp.this.mActivity.b.getDeviceUdid(), true);
                    pdp.this.a(DevicePopTipBiUtil.Content.WECHAT_DEVICE_BINDING, DevicePopTipBiUtil.Action.NOT_PROMPT_AGAIN, DevicePopTipBiUtil.Event.CLICK);
                    return;
                }
                LogUtil.h("WearHomeTipCard", "click nothing");
            }
        }, dmG_, string, string2);
    }

    private void o() {
        if (this.c != 2) {
            LogUtil.h("WearHomeTipCard", "initEcgServiceActiveCardView device disconnect.");
            this.i.dmG_().setVisibility(8);
            return;
        }
        if (this.mContext == null || Utils.o()) {
            LogUtil.h("WearHomeTipCard", "initEcgServiceActiveCardView mContext is null");
            c();
            return;
        }
        if (this.b) {
            LogUtil.h("WearHomeTipCard", "initEcgServiceActiveCardView has show EcgServiceView");
            c();
            return;
        }
        boolean c = this.mActivity.b != null ? cwi.c(this.mActivity.b, 45) : false;
        LogUtil.a("WearHomeTipCard", "isSupportEcgParsingService:", Boolean.valueOf(c));
        if (!c) {
            LogUtil.h("WearHomeTipCard", "not support EcgServiceIV");
            c();
        } else {
            jgc.a().e(new IBaseResponseCallback() { // from class: pdp.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof EcgServiceActivationData)) {
                        LogUtil.h("WearHomeTipCard", "objData not instanceof EcgServiceActivationData");
                        pdp.this.e(1037);
                        return;
                    }
                    EcgServiceActivationData ecgServiceActivationData = (EcgServiceActivationData) obj;
                    LogUtil.a("WearHomeTipCard", "WearHomeTipCard data is ok.");
                    if (ecgServiceActivationData.getStatus() != 0 && (ecgServiceActivationData.getStatus() != 1 || ecgServiceActivationData.getProbationUser() != 0)) {
                        pdp.this.e(1037);
                    } else {
                        LogUtil.a("WearHomeTipCard", "Need show EcgServiceActiveCardView.");
                        pdp.this.e(1035);
                    }
                }
            }, this.mActivity.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (this.mActivity == null || this.mActivity.djG_() == null) {
            return;
        }
        this.mActivity.djG_().sendEmptyMessage(i);
    }

    public void a() {
        this.i.c().setText(this.mContext.getResources().getString(R.string._2130838367_res_0x7f02035f).toUpperCase(Locale.ENGLISH));
        this.i.b().setText(this.mContext.getResources().getString(R.string._2130838404_res_0x7f020384).toUpperCase(Locale.ENGLISH));
        nlg.cxW_(BaseApplication.getContext(), this.e, this.i.dmG_(), this.mContext.getResources().getString(R.string._2130838366_res_0x7f02035e), this.mContext.getResources().getString(R.string._2130838365_res_0x7f02035d, "599"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DevicePopTipBiUtil.Content content, DevicePopTipBiUtil.Action action, DevicePopTipBiUtil.Event event) {
        if (this.mActivity.b == null) {
            ReleaseLogUtil.d("R_WearHomeTipCard", "reportWechatTipBiEvent mCurrentDeviceInfo is null");
            return;
        }
        DevicePopTipBiUtil.TipType tipType = DevicePopTipBiUtil.TipType.TIP;
        String deviceName = this.mActivity.b.getDeviceName();
        int productType = this.mActivity.b.getProductType();
        DevicePopTipBiUtil.b(tipType, content, action, deviceName, String.valueOf(productType), this.mActivity.b.getHiLinkDeviceId(), DevicePopTipBiUtil.Page.DEVICE_DETAIL, event);
    }
}
