package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/* loaded from: classes5.dex */
public class kys implements Runnable {
    private static boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14707a;
    private Handler c;
    private Context d;
    private kxn f;
    private boolean h;
    private String i;
    private PackageInfo j = null;
    private String e = "";

    public kys(Context context, String str, Handler handler, kxn kxnVar) {
        this.f = new kxn();
        this.d = context;
        this.i = str;
        this.c = handler;
        if (kxnVar != null) {
            this.f14707a = kxnVar.h();
            this.h = kxnVar.j();
            this.f = kxnVar;
        }
    }

    public static void b(boolean z) {
        b = z;
    }

    public void bSP_(PackageInfo packageInfo) {
        this.j = packageInfo;
    }

    public void d(String str) {
        this.e = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        OutputStream b2;
        if (!TextUtils.isEmpty(this.i) && this.i.toLowerCase(Locale.ENGLISH).indexOf("aw70") != -1) {
            kxu.e(this.i);
        } else {
            kxu.f(this.i);
        }
        PackageInfo packageInfo = this.j;
        if (packageInfo != null) {
            b2 = kyr.bSQ_(this.d, packageInfo, this.e, this.f14707a, this.f);
        } else {
            b2 = kyr.b(this.d, this.i, this.f14707a, this.f);
        }
        LogUtil.a("CheckNewVersionThread", "send json: ", System.lineSeparator(), kyr.c(b2.toString()));
        LogUtil.c("CheckNewVersionThread", "send json: ", System.lineSeparator(), b2.toString());
        String e = kyp.e(b2.toString(), this.f14707a, this.h);
        LogUtil.a("CheckNewVersionThread", "receive json:", e);
        a(e, this.h);
    }

    private void a(String str, boolean z) {
        if (str != null) {
            d(kyo.c(str, z));
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 0;
        if (!b) {
            this.c.sendMessage(obtain);
            if (this.f14707a) {
                kxu.a((kxl) null);
                return;
            } else {
                kxu.e((kxl) null);
                return;
            }
        }
        LogUtil.a("CheckNewVersionThread", "processResponse CancelCheckFlag is true;");
    }

    private void d(kxl kxlVar) {
        Message obtain = Message.obtain();
        if (kxlVar != null) {
            bSN_(obtain, kxlVar);
        } else {
            obtain.what = 0;
            bSM_(obtain, null);
        }
    }

    private void bSN_(Message message, kxl kxlVar) {
        LogUtil.a("CheckNewVersionThread", "setApplicationMsg applicationInfo = ", kxlVar.toString(), " sendJsonStreamToServer: applicationInfo.STATUS:", Integer.valueOf(kxlVar.p()), ";");
        String u = kxlVar.u();
        String str = kxlVar.y() + "full/filelist.xml";
        if (kxlVar.p() == 0) {
            kxl e = e(this.d, str, kxlVar);
            if (e != null) {
                LogUtil.a("CheckNewVersionThread", "setApplicationMsg applicationInfo.STATUS:", Integer.valueOf(e.p()), ";");
                e.a(e.y() + "full/" + e.r());
                kxj e2 = kyp.e(e);
                e2.g(u);
                bSO_(message);
                message.obj = e2;
                bSM_(message, e);
                return;
            }
            message.what = 0;
        } else {
            bSO_(message);
            LogUtil.a("CheckNewVersionThread", "setApplicationMsg applicationInfo status is not available;");
        }
        bSM_(message, kxlVar);
    }

    private void bSO_(Message message) {
        if (this.f14707a) {
            message.what = 1;
        } else {
            message.what = 2;
        }
    }

    private void bSM_(Message message, kxl kxlVar) {
        if (this.f14707a) {
            kxu.a((kxl) null);
            kxu.a(kxlVar);
        } else {
            kxu.e(kxlVar);
            kxu.d(this.e, kxlVar);
            HwVersionManager.c(BaseApplication.getContext()).a(kxlVar, this.e);
        }
        if (b) {
            return;
        }
        this.c.sendMessage(message);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.kxl e(android.content.Context r9, java.lang.String r10, defpackage.kxl r11) {
        /*
            r8 = this;
            java.lang.String r0 = "getFileListXmlFromServer success"
            java.lang.String r1 = "CheckNewVersionThread"
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            r3 = 1
            r4 = 0
            r5 = 2
            r6 = 200(0xc8, float:2.8E-43)
            r7 = -1
            int r7 = defpackage.kyp.b(r9, r10, r2)     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            r9 = 3
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            java.lang.String r10 = "retrieve filelist.xml: "
            r9[r4] = r10     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            java.lang.String r10 = java.lang.System.lineSeparator()     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            r9[r3] = r10     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            java.lang.String r10 = "UTF-8"
            java.lang.String r10 = r2.toString(r10)     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            r9[r5] = r10     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)     // Catch: java.lang.Throwable -> L36 java.lang.IllegalStateException -> L38 java.net.URISyntaxException -> L52 java.io.IOException -> L54
            if (r7 != r6) goto L76
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
            goto L6d
        L36:
            r9 = move-exception
            goto L7b
        L38:
            r9 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L36
            java.lang.String r5 = "getFileListXmlFromServer IllegalStateException:"
            r10[r4] = r5     // Catch: java.lang.Throwable -> L36
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L36
            r10[r3] = r9     // Catch: java.lang.Throwable -> L36
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)     // Catch: java.lang.Throwable -> L36
            if (r7 != r6) goto L76
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
            goto L6d
        L52:
            r9 = move-exception
            goto L55
        L54:
            r9 = move-exception
        L55:
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L36
            java.lang.String r5 = "getFileListXmlFromServer IOException:"
            r10[r4] = r5     // Catch: java.lang.Throwable -> L36
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L36
            r10[r3] = r9     // Catch: java.lang.Throwable -> L36
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)     // Catch: java.lang.Throwable -> L36
            if (r7 != r6) goto L76
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
        L6d:
            java.io.InputStream r9 = r8.c(r2)
            kxl r9 = defpackage.kyj.a(r9, r11)
            goto L77
        L76:
            r9 = 0
        L77:
            health.compact.a.IoUtils.e(r2)
            return r9
        L7b:
            if (r7 != r6) goto L8b
            java.lang.Object[] r10 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r10)
            java.io.InputStream r10 = r8.c(r2)
            defpackage.kyj.a(r10, r11)
        L8b:
            health.compact.a.IoUtils.e(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kys.e(android.content.Context, java.lang.String, kxl):kxl");
    }

    private InputStream c(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int i = 0;
        while (i < byteArray.length && byteArray[i] != 60) {
            i++;
        }
        byte[] bArr = new byte[byteArray.length - i];
        System.arraycopy(byteArray, i, bArr, 0, byteArray.length - i);
        return new ByteArrayInputStream(bArr);
    }
}
