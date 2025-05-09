package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback;
import com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameGuideActivity;
import com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener;
import health.compact.a.GRSManager;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class pvw {
    private static volatile boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private Context f16281a;
    private long b;
    private CustomProgressDialog c;
    private CustomProgressDialog.Builder d;
    private NoTitleCustomAlertDialog g;
    private CustomTextAlertDialog h;
    private NoTitleCustomAlertDialog j;
    private a k;
    private int l;
    private SharedPreferences m;
    private boolean i = false;
    private int f = 0;

    public static void e(UiCallback<String> uiCallback) {
        if (e) {
            return;
        }
        e = true;
        pun.d().downloadWorkoutMediaFiles(uiCallback);
    }

    public void a() {
        a(1, 0, 0);
        if (this.j == null) {
            LogUtil.a("StressGameDownloadUtils", "mDialigDeviceConn == null");
            this.j = new NoTitleCustomAlertDialog.Builder(this.f16281a).e(this.f16281a.getString(R$string.Stress_game_base_measure_connection_devices)).czE_(this.f16281a.getString(R$string.IDS_hw_pressure_known), new View.OnClickListener() { // from class: pvw.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.j.dismiss();
                    pvw.this.f16281a = null;
                    pvw.this.j = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czA_(this.f16281a.getString(R$string.IDS_hw_pressure_learn_more), new View.OnClickListener() { // from class: pvw.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.o();
                    pvw.this.j.dismiss();
                    pvw.this.j = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        } else {
            LogUtil.a("StressGameDownloadUtils", "mDialigDeviceConn != null");
        }
        if (!this.j.isShowing()) {
            LogUtil.a("StressGameDownloadUtils", "!mDialigDeviceConn.isShowing()");
            this.j.setCancelable(false);
            this.j.show();
            return;
        }
        LogUtil.a("StressGameDownloadUtils", "mDialigDeviceConn.isShowing()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        String str;
        this.b = 0L;
        this.l = 0;
        String e2 = pul.e();
        if (pvo.a()) {
            LogUtil.a("StressGameDownloadUtils", "special phone");
            str = "filesServer.xml";
        } else {
            str = "filesServer_old.xml";
        }
        pvu.b(this.f16281a, e2, str);
        this.b = puk.e(new ArrayList(10));
    }

    public void d() {
        n();
        if (this.b == 0) {
            s();
        } else {
            j();
        }
    }

    private float h() {
        LogUtil.a("StressGameDownloadUtils", "initDownLoadFileSize() mByteLength ==", Long.valueOf(this.b));
        float f = (this.b / 1024.0f) / 1024.0f;
        LogUtil.a("StressGameDownloadUtils", "initDownLoadFileSize() mByteLength ==", Float.valueOf(f), "before change");
        float floatValue = new BigDecimal(f).setScale(1, 4).floatValue();
        LogUtil.a("StressGameDownloadUtils", "initDownLoadFileSize() mByteLength == ", Float.valueOf(floatValue), "after change");
        if (floatValue >= 1.0f) {
            return floatValue;
        }
        LogUtil.a("StressGameDownloadUtils", "initDownLoadFileSize() mByteLength == ", Float.valueOf(1.0f), "after change again");
        return 1.0f;
    }

    private void s() {
        pvr.d(new d(2, this.k));
        gnm.aPB_(this.f16281a, new Intent(this.f16281a, (Class<?>) StressGameGuideActivity.class));
        this.f16281a = null;
    }

    private String g() {
        String string = this.f16281a.getString(R$string.IDS_device_upgrade_file_size_mb, Float.valueOf(h()));
        if (this.m.getBoolean("havefile", false)) {
            LogUtil.a("StressGameDownloadUtils", "getDownLoadDialgContent() file Upload");
            return this.f16281a.getString(R$string.Stress_game_base_measure_current_material_upload, string);
        }
        LogUtil.a("StressGameDownloadUtils", "getDownLoadDialgContent() file DownLoad");
        return this.f16281a.getString(R$string.Stress_game_base_measure_current_material, string);
    }

    private void j() {
        if (this.h == null) {
            LogUtil.a("StressGameDownloadUtils", "mDoalogDownLoadFile == null");
            this.h = new CustomTextAlertDialog.Builder(this.f16281a).b(R$string.IDS_hw_health_show_common_dialog_title).e(g()).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: pvw.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.b(0, 0);
                    pvw.this.h.dismiss();
                    pvw.this.f16281a = null;
                    pvw.this.h = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyU_(R$string.Stress_game_base_measure_download, new View.OnClickListener() { // from class: pvw.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.m();
                    pvw.this.d(false);
                    pvr.d(new d(1, pvw.this.k));
                    pvw.this.h.dismiss();
                    pvw.this.h = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
        }
        if (this.h.isShowing()) {
            return;
        }
        LogUtil.a("StressGameDownloadUtils", "!mDoalogDownLoadFile.isShowing()");
        this.h.setCancelable(false);
        this.h.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.g == null) {
            LogUtil.a("StressGameDownloadUtils", "mDownloadAgainDialog is null");
            this.g = new NoTitleCustomAlertDialog.Builder(this.f16281a).e(R$string.Stress_game_base_measure_download_fail).czz_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: pvw.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.b(1, 0);
                    pvw.this.b();
                    pvw.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R$string.IDS_hwh_stressdialog_download_again, new View.OnClickListener() { // from class: pvw.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    pvw.this.c();
                    pvw.this.i();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        if (this.g.isShowing()) {
            return;
        }
        LogUtil.a("StressGameDownloadUtils", "mDoalogDownloadFile begin to show");
        this.g.setCancelable(false);
        this.g.show();
    }

    public UiCallback<String> e() {
        return new UiCallback<String>() { // from class: pvw.9
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
            public void onFailure(int i, String str) {
                boolean unused = pvw.e = false;
                LogUtil.a("StressGameDownloadUtils", "downloadWorkoutMediaFiles onFailure: ", Integer.valueOf(i), "errorInfo:", str);
                pvw.this.k();
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                boolean unused = pvw.e = false;
                if ("cancel".equals(str)) {
                    LogUtil.a("StressGameDownloadUtils", "getCallback() onsuccess err");
                } else if (pvu.d("data/data/com.huawei.health/files/stress/audiosBase/open_source.zip")) {
                    pvw.this.f();
                } else {
                    LogUtil.a("StressGameDownloadUtils", "getCallback() file download fail and can't jump to next activity");
                    pvw.this.b();
                }
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
            public void onProgress(long j, long j2) {
                super.onProgress(j, j2);
                long j3 = pvw.this.b;
                if (pvw.this.d == null || j3 == 0 || pvw.this.f != 0) {
                    return;
                }
                pvw.this.l = (int) ((j / j3) * 100.0d);
                pvw.this.d.d(pvw.this.l);
                pvw.this.d.c(pvw.this.l);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
            public boolean isCanceled() {
                return pvw.this.i;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        this.i = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.c = new CustomProgressDialog(this.f16281a);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f16281a);
        this.d = builder;
        builder.d(this.f16281a.getString(R$string.IDS_update_downloading)).cyH_(new View.OnClickListener() { // from class: pvw.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("StressGameDownloadUtils", "mCustomProgressDialog setCancelButton");
                pvw.this.b();
                pvw.this.d(true);
                boolean unused = pvw.e = false;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e2 = this.d.e();
        this.c = e2;
        e2.setCanceledOnTouchOutside(false);
        this.c.show();
        this.c.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: pvw.7
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1 || keyEvent.getAction() == 4) {
                    LogUtil.a("StressGameDownloadUtils", "mCustomProgressDialog setOnKeyListener");
                    pvw.this.b();
                    pvw.this.d(true);
                    boolean unused = pvw.e = false;
                    if (pvw.this.f16281a instanceof Activity) {
                        ((Activity) pvw.this.f16281a).finish();
                    }
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        d(false);
        pvr.d(new d(1, this.k));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.g;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                LogUtil.a("StressGameDownloadUtils", "dismissProgressDialog");
                this.g.dismiss();
            }
            this.g = null;
        }
    }

    public void b() {
        LogUtil.a("StressGameDownloadUtils", "cancelProgressDialog");
        CustomProgressDialog customProgressDialog = this.c;
        if (customProgressDialog != null) {
            if (customProgressDialog.isShowing()) {
                LogUtil.a("StressGameDownloadUtils", "dismissProgressDialog");
                this.c.dismiss();
            }
            this.c = null;
            this.d = null;
            this.f16281a = null;
            LogUtil.a("StressGameDownloadUtils", "cancelProgressDialog clear dialog");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.f16281a != null) {
            if (msp.c("data/data/com.huawei.health/files/stress/audiosBase/open_source.zip", "data/data/com.huawei.health/files/stress/audiosBase/") <= 0) {
                LogUtil.a("StressGameDownloadUtils", "unzip failure");
                if (b("data/data/com.huawei.health/files/stress/audiosBase/open_source.zip")) {
                    d(false);
                    e(e());
                    int i = this.f + 1;
                    this.f = i;
                    if (i == 2) {
                        LogUtil.a("StressGameDownloadUtils", "doZipExtractorWork second err");
                        this.f = 0;
                        Toast.makeText(this.f16281a, this.f16281a.getResources().getString(R$string.Stress_game_guide_info_download_fail), 0).show();
                        b();
                        return;
                    }
                    return;
                }
                return;
            }
            b(1, 0);
            LogUtil.a("StressGameDownloadUtils", "doZipExtractorWork success");
            l();
            b();
            return;
        }
        LogUtil.h("StressGameDownloadUtils", "doZipExtractorWork null == mContext");
    }

    public boolean b(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                LogUtil.a("StressGameDownloadUtils", "delete file ", str, "success");
                return true;
            }
            LogUtil.a("StressGameDownloadUtils", "delete file ", str, ParamConstants.CallbackMethod.ON_FAIL);
            return false;
        }
        LogUtil.a("StressGameDownloadUtils", "delete file err ：", str, " can't find");
        return false;
    }

    private void l() {
        this.m.edit().putBoolean("havefile", true).commit();
        gnm.aPB_(this.f16281a, new Intent(this.f16281a, (Class<?>) StressGameGuideActivity.class));
        b();
        this.f16281a = null;
        d(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Context context = this.f16281a;
        if (context != null) {
            String url = GRSManager.a(context).getUrl("messageCenterUrl");
            if (TextUtils.isEmpty(url)) {
                LogUtil.b("StressGameDownloadUtils", "startActivity messageCenterHost is empty");
                return;
            }
            LogUtil.c("StressGameDownloadUtils", "startActivity messageCenterHost = ", url);
            Intent intent = new Intent(this.f16281a, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", url + "/messageH5/sleephtml/salesPromotion.html");
            gnm.aPB_(this.f16281a, intent);
            this.f16281a = null;
            return;
        }
        LogUtil.a("StressGameDownloadUtils", "startActivity() mContext = " + this.f16281a);
    }

    public void b(Context context) {
        if (context == null) {
            return;
        }
        this.f16281a = context;
        this.m = context.getSharedPreferences("sleep_shared_pref_smart_msg", 0);
        this.k = new a(this);
        DeviceInfo a2 = jpt.a("StressGameDownloadUtils");
        if (a2 != null) {
            if (a2.getDeviceConnectState() != 2) {
                a();
                return;
            }
            if (a2.getDeviceConnectState() == 2) {
                DeviceCapability d2 = cvs.d();
                if (d2 == null) {
                    LogUtil.h("StressGameDownloadUtils", "deviceCapability == null");
                    return;
                }
                boolean isSupportPressAutoMonitor = d2.isSupportPressAutoMonitor();
                LogUtil.a("StressGameDownloadUtils", "Adjust isSupportPressAutoMonitor() = " + isSupportPressAutoMonitor);
                if (isSupportPressAutoMonitor) {
                    if (HwVersionManager.c(BaseApplication.getContext()).o(a2.getDeviceIdentify())) {
                        qba.a(this.f16281a);
                        return;
                    } else {
                        a(1, 1, 1);
                        d();
                        return;
                    }
                }
                a();
                return;
            }
            LogUtil.a("StressGameDownloadUtils", "Adjust isSupportPressAutoMonitor()  err");
            return;
        }
        a();
    }

    private void a(int i, int i2, int i3) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", Integer.valueOf(i));
        hashMap.put("havedevice", Integer.valueOf(i2));
        hashMap.put("support_devices", Integer.valueOf(i3));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_CLICK_2160017.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("download", Integer.valueOf(i));
        hashMap.put("download_status", Integer.valueOf(i2));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_DOWNLOAD_CLICK_2160018.value(), hashMap);
    }

    static class a extends BaseHandler<pvw> {
        a(pvw pvwVar) {
            super(pvwVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: duu_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(pvw pvwVar, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    if (message.obj instanceof String) {
                        if (MonitorResult.SUCCESS.equals((String) message.obj)) {
                            LogUtil.a("StressGameDownloadUtils", "phone xml upload success");
                            return;
                        } else {
                            LogUtil.h("StressGameDownloadUtils", "phone xml upload fail");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("StressGameDownloadUtils", "now it do it ");
                return;
            }
            if (message.obj instanceof String) {
                String str = (String) message.obj;
                if (MonitorResult.SUCCESS.equals(str)) {
                    LogUtil.a("StressGameDownloadUtils", "phone xml download success");
                    pvwVar.n();
                    pvw.e(pvwVar.e());
                } else {
                    LogUtil.h("StressGameDownloadUtils", "MyHandler message HANDLER_WHAT_DOWNLOAD is filed message = ", str);
                    pvwVar.k();
                }
            }
        }
    }

    static class d implements CasePhoneDownloadListener {

        /* renamed from: a, reason: collision with root package name */
        private a f16285a;
        private int c;

        d(int i, a aVar) {
            this.c = i;
            this.f16285a = aVar;
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener
        public void onResult(int i, String str) {
            d(str);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.stressgame.casephone.CasePhoneDownloadListener
        public void onFailed(int i, String str) {
            d(str);
        }

        private void d(String str) {
            if (str != null) {
                Message obtain = Message.obtain();
                obtain.what = this.c;
                obtain.obj = str;
                this.f16285a.sendMessage(obtain);
            }
        }
    }
}
