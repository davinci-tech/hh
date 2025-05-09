package com.huawei.ui.device.activity.questionsuggestions;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity;
import defpackage.csf;
import defpackage.cuk;
import defpackage.cun;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.gmz;
import defpackage.ixj;
import defpackage.jfq;
import defpackage.jgp;
import defpackage.jpt;
import defpackage.jrx;
import defpackage.jsd;
import defpackage.knx;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nxm;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class QuestionSuggestionActivity extends BaseQuestionSuggestionActivity implements CompoundButton.OnCheckedChangeListener, NotifyPhoneServiceCallback {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9206a;
    private Handler b;
    private CustomProgressDialog.Builder c;
    private CustomProgressDialog e;
    private String g;
    private CustomViewDialog h;
    private CustomTextAlertDialog i;
    private CustomTextAlertDialog j;
    private int l;
    private jfq o;
    private String d = "";
    private DeviceDfxBaseResponseCallback f = new DeviceDfxBaseResponseCallback() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.5
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onSuccess(int i, String str) {
            ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "mDfxCallback onSuccess() ", Integer.valueOf(i));
            if (QuestionSuggestionActivity.this.mLogHandler != null) {
                Message obtainMessage = QuestionSuggestionActivity.this.mLogHandler.obtainMessage();
                obtainMessage.what = 3;
                QuestionSuggestionActivity.this.mLogHandler.sendMessage(obtainMessage);
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Dfx_QuestionSuggestionActivity", "mDfxCallback onFailure(), errorCode is ", Integer.valueOf(i), ", errorMsg = ", str);
            if (QuestionSuggestionActivity.this.mLogHandler != null) {
                Message obtainMessage = QuestionSuggestionActivity.this.mLogHandler.obtainMessage();
                obtainMessage.what = 2;
                QuestionSuggestionActivity.this.mLogHandler.sendMessage(obtainMessage);
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
        public void onProgress(int i, String str) {
            ReleaseLogUtil.e("QuestionSuggestionActivity", "mDfxCallback onProgress() progress is: ", Integer.valueOf(i), ", progressDescription is: ", str);
            if (StringUtils.g(str) || QuestionSuggestionActivity.this.mLogHandler == null) {
                return;
            }
            Message obtainMessage = QuestionSuggestionActivity.this.mLogHandler.obtainMessage();
            obtainMessage.what = 1;
            Bundle bundle = new Bundle();
            bundle.putInt("CURRENT_PROCESS", i);
            Matcher matcher = Pattern.compile("(?<=\\()[^\\)]+").matcher(str);
            String str2 = QuestionSuggestionActivity.this.mLastProgressDescription;
            if (matcher.find()) {
                String group = matcher.group();
                ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "LogHandler progress tipText is: ", group);
                str2 = QuestionSuggestionActivity.this.e(group);
            }
            bundle.putString("CURRENT_PROCESS_DESCRIPTION", str2);
            obtainMessage.setData(bundle);
            QuestionSuggestionActivity.this.mLogHandler.sendMessage(obtainMessage);
        }
    };

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, android.view.View.OnClickListener
    public /* synthetic */ void onClick(View view) {
        super.onClick(view);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str) {
        String str2;
        String str3 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("/");
        if (split == null || split.length <= 1) {
            str2 = "";
        } else {
            String e = UnitUtil.e(nsn.j(split[0]), 1, 0);
            str2 = UnitUtil.e(nsn.j(split[1]), 1, 0);
            str3 = e;
        }
        return str3 + "/" + str2;
    }

    private void e() {
        ReleaseLogUtil.e("QuestionSuggestionActivity", "enter showEnhanceLogDialog");
        if (this.h == null) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.hw_show_enhance_log_view, (ViewGroup) null);
            CustomViewDialog e = new CustomViewDialog.Builder(this.mContext).d(R.string.IDS_device_log_enhancement).czg_(inflate).c(false).cze_(R.string.IDS_device_log_enhancement_ok, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    QuestionSuggestionActivity.this.mLogEnhancementModeCheckbox.setChecked(true);
                    QuestionSuggestionActivity.this.l = 1;
                    QuestionSuggestionActivity.this.b(true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czc_(R.string.IDS_device_log_enhancement_cancel, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    QuestionSuggestionActivity.this.l = 0;
                    QuestionSuggestionActivity.this.mLogEnhancementModeCheckbox.setChecked(false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            this.h = e;
            e.setCancelable(false);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            ReleaseLogUtil.e("QuestionSuggestionActivity", "screen height:", Integer.valueOf(i));
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = i / 3;
            inflate.setLayoutParams(layoutParams);
        }
        if (isFinishing()) {
            return;
        }
        this.h.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        DeviceInfo e = jpt.e(this.d, "QuestionSuggestionActivity");
        if (e == null) {
            ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "sendLogCommand deviceInfo is null");
        } else if (cwi.c(e, 129)) {
            ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "sendTrustListCommand device support log trust is support");
            b(z, e);
        } else {
            ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "sendTrustListCommand device support log trust is not support");
        }
    }

    private void b(boolean z, DeviceInfo deviceInfo) {
        cvq d = d(z);
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "sendLogCommand constructLogSampleConfig: ", d.toString());
        cuk.b().sendSampleConfigCommand(deviceInfo, d, "QuestionSuggestionActivity");
    }

    private cvq d(boolean z) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.uniedevice.hiviewEnhance");
        cvqVar.setWearPkgName("HiviewEnhance");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900100016L);
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        sb.append("");
        int i = !"true".equals(sb.toString()) ? 1 : 0;
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "constructSampleConfig status is: ", Integer.valueOf(i));
        cvnVar.c(cvx.a(str));
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            e();
        } else {
            this.l = 0;
            b(false);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mErrorTipContent = this.mContext.getString(R.string.IDS_device_release_user_profile_log_collect_failure_tip);
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void processIntentData(Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "intent is empty");
            return;
        }
        String stringExtra = intent.getStringExtra("deviceType");
        this.g = stringExtra;
        if (!TextUtils.isEmpty(stringExtra) && "3".equals(this.g)) {
            DeviceInfo b = b();
            if (b != null) {
                this.d = b.getDeviceIdentify();
                this.f9206a = cwi.c(b, 62);
            } else {
                ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "deviceInfo is null");
            }
        } else {
            this.d = intent.getStringExtra("device_id");
            this.f9206a = intent.getBooleanExtra("enhancement_mode", false);
        }
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "isSupportLogEnhancementMode:", Boolean.valueOf(this.f9206a));
        if (Utils.o() || !this.f9206a) {
            return;
        }
        this.mSwitchLayout.setVisibility(0);
        this.mLogEnhancementModeCheckbox.setOnCheckedChangeListener(this);
        b(false);
    }

    private DeviceInfo b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "QuestionSuggestionActivity");
        if (deviceList == null || deviceList.size() == 0) {
            ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "deviceInfos is null ");
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            int versionType = deviceInfo.getVersionType();
            ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "Device version type is ", Integer.valueOf(versionType));
            boolean z = versionType == 0;
            boolean g = jsd.g(deviceInfo.getDeviceIdentify());
            if (z && (!g)) {
                return deviceInfo;
            }
        }
        return null;
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void onAgreeAndContinueClick() {
        if (knx.e()) {
            j();
            return;
        }
        a(this.mContext.getString(R.string.IDS_device_release_user_profile_log_privacy_tip_title) + System.lineSeparator() + System.lineSeparator() + this.mContext.getString(R.string.IDS_device_release_user_profile_log_privacy_tip_content));
    }

    private void a(String str) {
        new NoTitleCustomAlertDialog.Builder(this).e(str).czE_(this.mContext.getString(R.string.IDS_device_release_user_profile_log_collect_agree).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                gmz.d().c(11, true, (String) null, (IBaseResponseCallback) null);
                csf.e(true);
                QuestionSuggestionActivity.this.j();
                QuestionSuggestionActivity.this.mSwitchLayout.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(this.mContext.getString(R.string.IDS_device_release_user_profile_log_collect_cancel).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        deleteLogFiles(new File(jrx.c));
        if (!jsd.a(this.mContext)) {
            ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "FeedBackLog is NoNetworkActive");
            nrh.e(this.mContext, R.string._2130842450_res_0x7f021352);
            return;
        }
        if (this.o == null) {
            this.o = jfq.c();
        }
        DeviceInfo e = jpt.e(this.d, "QuestionSuggestionActivity");
        if (e != null && e.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "startGetDeviceLog() bt is not connected");
            return;
        }
        boolean c = cwi.c(e, 134);
        ReleaseLogUtil.e("QuestionSuggestionActivity", "startGetDeviceLog isSupportGet earPhone log isSupport: ", Boolean.valueOf(c));
        if (c) {
            c(e);
        } else {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (jgp.a(this.mContext).b(this.d)) {
            this.mSwitchLayout.setVisibility(8);
            ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "mSelectedFlag", Integer.valueOf(this.l));
            jgp.a(this.mContext).a(this.d, this.f, this.l);
            String string = this.mContext.getString(R.string.IDS_device_release_user_profile_log_collecting_tip);
            if (nsn.ae(BaseApplication.getContext())) {
                string = String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_pad_device_collecting_tip), 1);
            }
            showLogsCollecting(string);
            return;
        }
        ReleaseLogUtil.d("Dfx_QuestionSuggestionActivity", "startGetDeviceLog() device is not supported");
    }

    private void c() {
        if (this.mLogHandler != null) {
            this.mLogHandler.post(new Runnable() { // from class: com.huawei.ui.device.activity.questionsuggestions.QuestionSuggestionActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    QuestionSuggestionActivity.this.d();
                }
            });
        }
        jfq.c().a("earphoneLogPre");
    }

    private void b(DeviceInfo deviceInfo) {
        c(0, this.mContext.getString(R.string._2130846266_res_0x7f02223a));
        jfq.c().e("earphoneLogPre", this);
        ReleaseLogUtil.e("QuestionSuggestionActivity", "start sendDeviceEvent");
        jfq.c().d("earphoneLogPre", deviceInfo, 0, "send_device_event_beta_club");
    }

    public /* synthetic */ void a() {
        c(this.e);
    }

    @Override // com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback
    public void executeResponse(int i, DeviceInfo deviceInfo, int i2, String str) {
        if (i == 0) {
            ReleaseLogUtil.e("QuestionSuggestionActivity", "executeResponse progress reserve: ", Integer.valueOf(i2));
            if (i2 == 100) {
                runOnUiThread(new Runnable() { // from class: nxk
                    @Override // java.lang.Runnable
                    public final void run() {
                        QuestionSuggestionActivity.this.a();
                    }
                });
                c();
                return;
            } else {
                c(i2, false);
                return;
            }
        }
        if (i == 1) {
            ReleaseLogUtil.e("QuestionSuggestionActivity", "executeResponse timeout");
            c(this.e);
            a(deviceInfo);
        } else {
            ReleaseLogUtil.d("QuestionSuggestionActivity", "others");
            a(deviceInfo);
        }
    }

    private void c(final DeviceInfo deviceInfo) {
        if (this.j == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R.string._2130846273_res_0x7f022241).d(R.string._2130846265_res_0x7f022239).cyU_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: nxj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuestionSuggestionActivity.this.cRY_(deviceInfo, view);
                }
            }).cyR_(R.string._2130846274_res_0x7f022242, new View.OnClickListener() { // from class: nxl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuestionSuggestionActivity.this.cRZ_(view);
                }
            }).a();
            this.j = a2;
            a2.setCancelable(false);
        }
        if (isFinishing()) {
            return;
        }
        this.j.show();
    }

    public /* synthetic */ void cRY_(DeviceInfo deviceInfo, View view) {
        b(deviceInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cRZ_(View view) {
        ReleaseLogUtil.e("QuestionSuggestionActivity", "negative showEarphoneLogCollectDialog");
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(final DeviceInfo deviceInfo) {
        if (this.i == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R.string._2130846267_res_0x7f02223b).d(R.string._2130846313_res_0x7f022269).cyU_(R.string.IDS_notify_retry, new View.OnClickListener() { // from class: nxo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuestionSuggestionActivity.this.cSa_(deviceInfo, view);
                }
            }).cyR_(R.string._2130846274_res_0x7f022242, new View.OnClickListener() { // from class: nxr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuestionSuggestionActivity.this.cSb_(view);
                }
            }).a();
            this.i = a2;
            a2.setCancelable(false);
        }
        if (isFinishing()) {
            return;
        }
        this.i.show();
    }

    public /* synthetic */ void cSa_(DeviceInfo deviceInfo, View view) {
        c(deviceInfo);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cSb_(View view) {
        ReleaseLogUtil.e("QuestionSuggestionActivity", "negative showEarphoneLogCollectFailedDialog");
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(CustomProgressDialog customProgressDialog) {
        ReleaseLogUtil.e("QuestionSuggestionActivity", "enter closeProgress");
        if (customProgressDialog == null || !customProgressDialog.isShowing() || isFinishing()) {
            return;
        }
        customProgressDialog.cancel();
        ReleaseLogUtil.e("QuestionSuggestionActivity", "enter closeProgress cancel");
    }

    private void c(int i, boolean z) {
        ReleaseLogUtil.e("QuestionSuggestionActivity", ",progress: ", Integer.valueOf(i), ",isTimeout: ", Boolean.valueOf(z));
        c(i, this.mContext.getString(R.string._2130846266_res_0x7f02223a));
    }

    private void c(final int i, final String str) {
        ReleaseLogUtil.e("QuestionSuggestionActivity", "startEarphoneDownLoadProgress progress: ", Integer.valueOf(i), " and progressDesc: ", str);
        runOnUiThread(new Runnable() { // from class: nxq
            @Override // java.lang.Runnable
            public final void run() {
                QuestionSuggestionActivity.this.a(i, str);
            }
        });
    }

    public /* synthetic */ void a(int i, String str) {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.c.d(i);
            this.c.c(i);
            return;
        }
        this.e = new CustomProgressDialog(this.mContext);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.mContext);
        this.c = builder;
        builder.d(str);
        CustomProgressDialog e = this.c.e();
        this.e = e;
        e.setCanceledOnTouchOutside(false);
        this.e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.e.show();
        this.c.d(0);
        this.c.c(0);
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity
    void openQuestionSuggestion() {
        String str;
        String str2;
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "openQuestionSuggestion");
        DeviceInfo e = jpt.e(this.d, "QuestionSuggestionActivity");
        if (e != null) {
            int productType = e.getProductType();
            String e2 = jsd.e(productType, jsd.c(productType, e.getDeviceModel()), e.getDeviceModel());
            str2 = e.getSoftVersion();
            str = e2;
        } else {
            str = "";
            str2 = str;
        }
        int bCQ_ = ixj.b().bCQ_(this, true, true, str, str2);
        if (this.b == null) {
            this.b = new nxm();
        }
        this.b.sendEmptyMessage(1);
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "openQuestionSuggestion() errorCode is: ", Integer.valueOf(bCQ_));
    }

    @Override // com.huawei.ui.device.activity.questionsuggestions.BaseQuestionSuggestionActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("Dfx_QuestionSuggestionActivity", "onDestroy");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
