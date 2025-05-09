package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwcrowdtestapi.HealthSendLogCallback;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.util.Locale;

/* loaded from: classes5.dex */
public class kbg {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14248a = new Object();
    private static kbg b;
    private c c = new c();
    private int d = 0;
    private ExtendHandler e;

    private kbg() {
    }

    public static kbg e() {
        kbg kbgVar;
        synchronized (f14248a) {
            if (b == null) {
                b = new kbg();
            }
            kbgVar = b;
        }
        return kbgVar;
    }

    private void a() {
        this.e = HandlerCenter.yt_(this.c, "FileUploadLogBeta");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
    
        r12 = r6.getCanonicalPath();
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(com.huawei.health.devicemgr.business.entity.DeviceInfo r11, java.lang.String r12, com.huawei.health.IBaseCommonCallback r13) {
        /*
            r10 = this;
            java.lang.String r0 = "enter startUploadLog"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "FileUploadLogBeta"
            health.compact.a.util.LogUtil.d(r1, r0)
            if (r13 != 0) goto L17
            java.lang.String r11 = "callback is null"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            health.compact.a.util.LogUtil.c(r1, r11)
            return
        L17:
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            java.lang.String r2 = "fileName is empty"
            r3 = 1
            if (r0 == 0) goto L24
            defpackage.kbi.d(r13, r3, r2)
            return
        L24:
            java.io.File r0 = new java.io.File
            java.lang.String r4 = defpackage.kbi.d
            r0.<init>(r4)
            boolean r4 = r0.exists()
            if (r4 != 0) goto L35
            defpackage.kbi.d(r13, r3, r2)
            return
        L35:
            java.io.File[] r0 = r0.listFiles()
            if (r0 == 0) goto L78
            int r4 = r0.length
            if (r4 != 0) goto L3f
            goto L78
        L3f:
            int r4 = r0.length
            r5 = 0
        L41:
            if (r5 >= r4) goto L62
            r6 = r0[r5]
            java.lang.String r7 = r6.getName()
            boolean r7 = r7.equals(r12)
            if (r7 == 0) goto L5f
            java.lang.String r12 = r6.getCanonicalPath()     // Catch: java.io.IOException -> L54
            goto L63
        L54:
            java.lang.String r12 = "startUploadLog IOException"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            health.compact.a.util.LogUtil.e(r1, r12)
            goto L62
        L5f:
            int r5 = r5 + 1
            goto L41
        L62:
            r12 = 0
        L63:
            r6 = r12
            boolean r12 = android.text.TextUtils.isEmpty(r6)
            if (r12 == 0) goto L6e
            defpackage.kbi.d(r13, r3, r2)
            return
        L6e:
            java.lang.String r8 = ""
            r9 = 0
            r4 = r10
            r5 = r13
            r7 = r11
            r4.a(r5, r6, r7, r8, r9)
            return
        L78:
            defpackage.kbi.d(r13, r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kbg.c(com.huawei.health.devicemgr.business.entity.DeviceInfo, java.lang.String, com.huawei.health.IBaseCommonCallback):void");
    }

    private void a(IBaseCommonCallback iBaseCommonCallback, String str, DeviceInfo deviceInfo, String str2, boolean z) {
        LogUtil.d("FileUploadLogBeta", "startUploadLogPart enter");
        kbi.c();
        this.d++;
        if (this.e == null) {
            a();
        }
        this.e.removeMessages(1070);
        Message obtain = Message.obtain();
        obtain.what = 1070;
        this.e.sendEmptyMessage(obtain.what, 600000L);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException unused) {
            LogUtil.e("FileUploadLogBeta", "startUploadLogPart InterruptedException sleep");
        }
        HealthFeedbackParams healthFeedbackParams = new HealthFeedbackParams();
        b(healthFeedbackParams, str, deviceInfo, str2);
        b(iBaseCommonCallback, str, healthFeedbackParams, z);
    }

    public void a(Context context) {
        LogUtil.d("FileUploadLogBeta", "uploadZip enter");
        if (context == null) {
            LogUtil.c("FileUploadLogBeta", "uploadZip context is null");
            return;
        }
        if (jsf.e(context)) {
            LogUtil.c("FileUploadLogBeta", "uploadZip:isAuto isBatteryControl not upload");
            return;
        }
        kbl.e().a(kbi.d);
        boolean c2 = jsd.c(context);
        LogUtil.d("FileUploadLogBeta", "uploadZip isWifi: ", Boolean.valueOf(c2));
        if (c2) {
            LogUtil.d("FileUploadLogBeta", "uploadLog External conditions met");
            d(kbi.d, context);
        }
    }

    private void d(String str, Context context) {
        LogUtil.d("FileUploadLogBeta", "startUploadZipFile path: ", str);
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.c("FileUploadLogBeta", "dir is not exists");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.d("FileUploadLogBeta", "uploadZip files is null.");
            return;
        }
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (name.contains("_WearableBeta")) {
                if (!kbi.c(name)) {
                    LogUtil.c("FileUploadLogBeta", "isUpload is false");
                } else {
                    String str2 = str + "/" + name;
                    a(null, str2, null, kbi.b(str2, context), true);
                }
            }
        }
    }

    private void b(final IBaseCommonCallback iBaseCommonCallback, String str, HealthFeedbackParams healthFeedbackParams, final boolean z) {
        LogUtil.d("FileUploadLogBeta", "startUploadLog targetPath: ", str);
        jeq.e().sendLog(BaseApplication.e(), healthFeedbackParams, str, false, new HealthSendLogCallback() { // from class: kbg.2
            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onSuccess(String str2) {
                LogUtil.d("FileUploadLogBeta", "uploadLogFile onSuccess uploadFileName: ", str2);
                kbi.d(str2);
                if (!z) {
                    kbi.d(iBaseCommonCallback, 0, str2);
                }
                kbg.this.c();
            }

            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onFailed(String str2) {
                LogUtil.d("FileUploadLogBeta", "uploadLogFile onFailed uploadFileName: ", str2);
                if (!z) {
                    kbi.d(iBaseCommonCallback, 1, str2);
                }
                kbg.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.e == null) {
            a();
        }
        int i = this.d;
        if (i >= 1) {
            int i2 = i - 1;
            this.d = i2;
            if (i2 == 0) {
                LogUtil.d("FileUploadLogBeta", "unInitSdkDeal count is 0");
                this.e.removeMessages(1070);
                kbi.a();
                return;
            }
            return;
        }
        LogUtil.d("FileUploadLogBeta", "unInitSdkDeal else");
        this.e.removeMessages(1070);
        kbi.a();
        this.d = 0;
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.d("FileUploadLogBeta", "UnInitSdkHandler handleMessage msg: ", Integer.valueOf(message.what));
            if (message.what == 1070) {
                kbi.a();
                kbg.this.d = 0;
                return true;
            }
            LogUtil.c("FileUploadLogBeta", "UnInitSdkHandler default");
            return false;
        }
    }

    private void b(HealthFeedbackParams healthFeedbackParams, String str, DeviceInfo deviceInfo, String str2) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != -1) {
            String substring = str.substring(lastIndexOf + 1, str.length());
            com.huawei.hwlogsmodel.LogUtil.a("FileUploadLogBeta", "fileNamePath: ", substring);
            String[] split = substring.split("_");
            String e = (split == null || split.length <= 0) ? "WEAR" : e(split[0]);
            if (!TextUtils.isEmpty(str2)) {
                deviceInfo = jsd.f(str2);
            }
            String securityDeviceId = deviceInfo != null ? deviceInfo.getSecurityDeviceId() : "0000000000000000";
            if (!TextUtils.isEmpty(securityDeviceId) && securityDeviceId.contains(":")) {
                securityDeviceId = securityDeviceId.replace(":", "");
            }
            CommonUtil.ac(securityDeviceId);
            healthFeedbackParams.setDeviceModel(e);
            if (!TextUtils.isEmpty(securityDeviceId)) {
                healthFeedbackParams.setDeviceSn(securityDeviceId);
            }
            if (Utils.o()) {
                healthFeedbackParams.setProductType(20);
            } else {
                healthFeedbackParams.setProductType(1);
            }
        }
    }

    private String e(String str) {
        if (str == null) {
            return "WEAR";
        }
        for (String str2 : kbj.e) {
            if (str.toUpperCase(Locale.ENGLISH).contains(str2.toUpperCase(Locale.ENGLISH))) {
                return str2;
            }
        }
        return "WEAR";
    }
}
