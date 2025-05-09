package com.huawei.hwversionmgr.utils.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.selfupdate.appupdate.UpdateBase;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler;
import defpackage.kxf;
import defpackage.kxj;
import defpackage.kxm;
import defpackage.kxp;
import defpackage.kxu;
import defpackage.kxv;
import defpackage.kxy;
import defpackage.kxz;
import defpackage.kye;
import defpackage.kyk;
import defpackage.kyp;
import health.compact.a.CommonUtil;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public class ScaleUpdateService extends Service {
    private String g;
    private String o;
    private Context c = null;
    private UpdateBase m = null;
    private String b = null;
    private String d = null;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f6414a = 0;
    private String i = null;
    private int f = -1;
    private int j = 4;
    private AppDownloadHandler n = new AppDownloadHandler() { // from class: com.huawei.hwversionmgr.utils.service.ScaleUpdateService.1
        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadFailed(int i) {
            LogUtil.b("Scale_ScaleUpdateService", "doDownloadFailed: statusCode = ", Integer.valueOf(i));
            kye.c(22, i);
            ScaleUpdateService.this.stopSelf();
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadSuccess(kxf kxfVar) {
            if (kxfVar == null) {
                LogUtil.a("Scale_ScaleUpdateService", "doDownloadSuccess: appDownloadInfo is null");
                return;
            }
            String a2 = kxfVar.a();
            LogUtil.a("Scale_ScaleUpdateService", "doDownloadSuccess: mCheckNewVersionCode = ", ScaleUpdateService.this.b, ", strAppStorePath = ", a2, ", mReportSuccess = ", Integer.valueOf(ScaleUpdateService.this.f));
            if (ScaleUpdateService.this.f == 5 || ScaleUpdateService.this.f == 4) {
                kxp.c().e(a2, ScaleUpdateService.this.g);
                kxp.c().d(ScaleUpdateService.this.b, ScaleUpdateService.this.g);
            }
            kye.c(23, 0);
            ScaleUpdateService.this.stopSelf();
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doInDownloadProgress(kxf kxfVar) {
            if (kxfVar == null) {
                return;
            }
            LogUtil.a("Scale_ScaleUpdateService", "doInDownloadProgress() total=", Long.valueOf(kxfVar.c()), ",current=", Long.valueOf(kxfVar.e()));
            kye.c(21, (int) ((kxfVar.e() * 100) / kxfVar.c()));
        }
    };
    private AppPullChangeLogHandler k = new AppPullChangeLogHandler() { // from class: com.huawei.hwversionmgr.utils.service.ScaleUpdateService.3
        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogFailed() {
            LogUtil.a("Scale_ScaleUpdateService", "mScaleBandPullChangeLogHandler pullChangeLogFail");
            if (ScaleUpdateService.this.j == 5) {
                kye.c(52, -1);
            }
            ScaleUpdateService.this.stopSelf();
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogSuccess(List<kxm> list) {
            LogUtil.a("Scale_ScaleUpdateService", "mScaleBandPullChangeLogHandler pullChangeLogSuccess");
            ScaleUpdateService scaleUpdateService = ScaleUpdateService.this;
            scaleUpdateService.f = scaleUpdateService.j;
            if (list != null) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    stringBuffer.append(list.get(i).e());
                    stringBuffer.append(System.lineSeparator());
                    stringBuffer.append(list.get(i).c());
                    LogUtil.a("Scale_ScaleUpdateService", "mBandPullChangeLogHandler pullChangeLogSuccess() i = ", Integer.valueOf(i), ", Title = ", list.get(i).e(), ", Content=", list.get(i).c());
                }
                String stringBuffer2 = stringBuffer.toString();
                LogUtil.a("Scale_ScaleUpdateService", "BAND_MANUAL_UPDATE()");
                if (ScaleUpdateService.this.j == 4) {
                    LogUtil.a("Scale_ScaleUpdateService", "BAND_AUTO_UPDATE()");
                    e(stringBuffer2);
                }
                if (ScaleUpdateService.this.j == 5) {
                    LogUtil.a("Scale_ScaleUpdateService", "BAND_MANUAL_UPDATE()");
                    kye.a(53, 0, stringBuffer2, ScaleUpdateService.this.i, ScaleUpdateService.this.f6414a);
                }
                LogUtil.a("Scale_ScaleUpdateService", "mScaleBandPullChangeLogHandler pullChangeLogSuccess() strFeatures = ", stringBuffer2);
            } else {
                LogUtil.b("Scale_ScaleUpdateService", "mScaleBandPullChangeLogHandler pullChangeLogSuccess() feature is null");
            }
            ScaleUpdateService.this.stopSelf();
        }

        private void e(String str) {
            kye.b(new kye.c(15).c(ScaleUpdateService.this.d).c(ScaleUpdateService.this.e).d(str).a(ScaleUpdateService.this.i).d(ScaleUpdateService.this.f6414a).e(ScaleUpdateService.this.g), ScaleUpdateService.this.o);
        }
    };
    private AppCheckNewVersionHandler h = new AppCheckNewVersionHandler() { // from class: com.huawei.hwversionmgr.utils.service.ScaleUpdateService.4
        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckSuccess(kxj kxjVar) {
            if (kxjVar != null) {
                ScaleUpdateService.this.b = kxjVar.j();
                ScaleUpdateService.this.d = kxjVar.b();
                ScaleUpdateService.this.e = (int) kxjVar.c();
                LogUtil.a("Scale_ScaleUpdateService", "mBandCheckNewVersionHandler handleManualCheckSuccess: mCheckNewVersionCode = ", ScaleUpdateService.this.b);
                ScaleUpdateService.this.f6414a = kxjVar.a();
                ScaleUpdateService.this.i = kxjVar.d();
                if (ScaleUpdateService.this.j == 4) {
                    kxy.e(ScaleUpdateService.this.d, ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    kxy.j(ScaleUpdateService.this.d, ScaleUpdateService.this.c, ScaleUpdateService.this.o);
                    if (TextUtils.isEmpty(ScaleUpdateService.this.i)) {
                        kxy.d(kxz.d(), ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    } else {
                        kxy.d("", ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    }
                    LogUtil.a("Scale_ScaleUpdateService", "mBandCheckNewVersion success");
                }
                if (ScaleUpdateService.this.j == 5) {
                    kxy.e(ScaleUpdateService.this.d, ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    kxy.j(ScaleUpdateService.this.d, ScaleUpdateService.this.c, ScaleUpdateService.this.o);
                    if (TextUtils.isEmpty(ScaleUpdateService.this.i)) {
                        kxy.d(kxz.d(), ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    } else {
                        kxy.d("", ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    }
                    kye.a(50, ScaleUpdateService.this.e, ScaleUpdateService.this.d, ScaleUpdateService.this.i, ScaleUpdateService.this.f6414a);
                }
                LogUtil.a("Scale_ScaleUpdateService", kxjVar.toString());
                ScaleUpdateService.this.b();
            }
            ScaleUpdateService.this.stopSelf();
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckFailed(int i) {
            LogUtil.b("Scale_ScaleUpdateService", "mScaleCheckNewVersionHandler HandlerhandleCheckFailed: stausCode = ", Integer.valueOf(i), " mCheck = ", Integer.valueOf(ScaleUpdateService.this.j));
            if (ScaleUpdateService.this.j == 4) {
                if (i == 0) {
                    LogUtil.b("Scale_ScaleUpdateService", "mScaleCheckNewVersionHandler handleAutoCheckFailed() reason = FAILED_REASON_NOTFOUND");
                    kxy.d(kxz.d(), ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                } else {
                    LogUtil.b("Scale_ScaleUpdateService", "mScaleCheckNewVersionHandler handleAutoCheckFailed() reason other");
                }
                kxy.e("", ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                kxy.j("", ScaleUpdateService.this.c, ScaleUpdateService.this.o);
            }
            if (ScaleUpdateService.this.j == 5) {
                if (i == 0) {
                    LogUtil.b("Scale_ScaleUpdateService", "mScaleCheckNewVersionHandler handleAutoCheckFailed() reason = FAILED_REASON_NOTFOUND");
                    kxy.e("", ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                    kxy.j("", ScaleUpdateService.this.c, ScaleUpdateService.this.o);
                    kxy.d(kxz.d(), ScaleUpdateService.this.c, ScaleUpdateService.this.g);
                }
                kye.c(49, i);
            }
            ScaleUpdateService.this.stopSelf();
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        this.c = applicationContext;
        this.m = new UpdateBase(applicationContext);
        LogUtil.a("Scale_ScaleUpdateService", "onCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("Scale_ScaleUpdateService", "onStartCommand: intent = ", intent);
        bSG_(intent);
        return 2;
    }

    private void bSG_(Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        LogUtil.a("Scale_ScaleUpdateService", "handleIntent: action = ", action);
        if ("action_scale_check_new_version".equals(action)) {
            bSH_(intent, 5);
            return;
        }
        if ("action_scale_auto_check_new_version".equals(action)) {
            bSH_(intent, 4);
            return;
        }
        if ("action_scale_download_new_version".equals(action)) {
            this.g = intent.getStringExtra("scale_name");
            c();
        } else {
            if ("action_cancel_download_app".equals(action)) {
                LogUtil.a("Scale_ScaleUpdateService", "cancel download app!");
                this.g = intent.getStringExtra("scale_name");
                this.m.a();
                return;
            }
            LogUtil.h("Scale_ScaleUpdateService", "handleIntent is other action");
        }
    }

    private void bSH_(Intent intent, int i) {
        this.j = i;
        String stringExtra = intent.getStringExtra("extra_band_unique_id");
        this.o = stringExtra;
        c(stringExtra);
        String stringExtra2 = intent.getStringExtra("extra_band_version");
        this.g = intent.getStringExtra("scale_name");
        String stringExtra3 = intent.getStringExtra("scale_mac_address");
        LogUtil.a("Scale_ScaleUpdateService", "checkNewVersion bandVersion = ", stringExtra2, ",deviceName = ", CommonUtil.l(this.g), ",scaleCheckStatus = ", Integer.valueOf(this.j));
        kye.b(this.g, stringExtra2, this.m, this.h, stringExtra3);
    }

    private void c(String str) {
        boolean h = kxy.h(this.c, str);
        UpdateBase.c(h);
        kyp.d(h);
        kyk.b(h);
    }

    private void c() {
        this.f = 5;
        boolean a2 = kye.a(5, this.g);
        LogUtil.a("Scale_ScaleUpdateService", "downloadFile: isNewVersionExist = ", Boolean.valueOf(a2));
        if (a2) {
            if (a()) {
                kye.c(23, 0);
                return;
            } else {
                d();
                return;
            }
        }
        d();
    }

    private void d() {
        this.m.downloadScaleFile(this.n, false);
    }

    private boolean a() {
        LogUtil.a("Scale_ScaleUpdateService", "enter checkMd5 mReportSuccess:", Integer.valueOf(this.f));
        String g = kxp.c().g(this.g);
        String h = kxu.l().h();
        String e = kxv.e(g);
        File file = new File(g);
        LogUtil.a("Scale_ScaleUpdateService", "srcMd5=", h, " ,path=", g, " file exists:", Boolean.valueOf(file.exists()), " file size:", Long.valueOf(file.length()));
        if (TextUtils.isEmpty(h)) {
            LogUtil.b("Scale_ScaleUpdateService", "verify md5 srcMd5 is null");
            kxp.c().b(this.g);
            return false;
        }
        if (h.equalsIgnoreCase(e)) {
            LogUtil.a("Scale_ScaleUpdateService", "verify md5 success  ", e);
            return true;
        }
        LogUtil.a("Scale_ScaleUpdateService", "verify md5 failed  ", e);
        kxp.c().b(this.g);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("Scale_ScaleUpdateService", "fetchChangeLogForBand");
        kye.c(51, -1);
        this.m.fetchScaleChangeLog(this.k, false);
    }
}
