package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

/* loaded from: classes5.dex */
public class kyt implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f14708a = false;
    private Context b;
    private Handler c;
    private PackageInfo d = null;
    private final String e;
    private String g;
    private final String i;

    public kyt(Context context, String str, Handler handler, String str2, String str3) {
        this.b = context;
        this.g = str;
        this.c = handler;
        this.i = str2;
        this.e = str3;
    }

    public static boolean d() {
        return f14708a;
    }

    public void bSW_(PackageInfo packageInfo) {
        this.d = packageInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        kxu.h(this.g);
        OutputStream a2 = kyr.a(this.b, this.g, this.i, this.d.versionName, this.e);
        LogUtil.a("ScaleCheckNewVersionThread", "send json: ", System.lineSeparator(), kyr.c(a2.toString()));
        LogUtil.c("ScaleCheckNewVersionThread", "send json: ", System.lineSeparator(), a2.toString());
        String d = kyp.d(a2.toString());
        IoUtils.e(a2);
        LogUtil.a("ScaleCheckNewVersionThread", "receive json:", d);
        e(d);
    }

    private void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            a(kyo.c(str, false));
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 0;
        if (!d()) {
            this.c.sendMessage(obtain);
            kxu.b((kxl) null);
        } else {
            LogUtil.a("ScaleCheckNewVersionThread", "cancelCheckFlag is true;");
        }
    }

    private void a(kxl kxlVar) {
        Message obtain = Message.obtain();
        if (kxlVar != null) {
            bSU_(obtain, kxlVar);
        } else {
            obtain.what = 0;
            bST_(obtain, null);
        }
    }

    private void bSU_(Message message, kxl kxlVar) {
        LogUtil.a("ScaleCheckNewVersionThread", "==ww== new version buildNewVersionInfoXML localApplicationInfo = ", kxlVar.toString());
        String u = kxlVar.u();
        ReleaseLogUtil.e("R_Weight_ScaleCheckNewVersionThread", "sendJsonStreamToServer: localApplicationInfo.STATUS:", Integer.valueOf(kxlVar.p()), "; VERSION: ", u);
        String str = kxlVar.y() + "full/filelist.xml";
        if (kxlVar.p() == 0) {
            kxl d = d(this.b, str, kxlVar);
            if (d == null) {
                message.what = 0;
            } else {
                LogUtil.a("ScaleCheckNewVersionThread", "getFileListXMLFromServer: localApplicationInfo.STATUS:", Integer.valueOf(d.p()), ";");
                d.a(d.y() + "full/" + d.r());
                kxj e = kyp.e(d);
                e.g(u);
                message = bSV_(message);
                message.obj = e;
                kxlVar = d;
            }
        } else {
            message = bSV_(message);
            LogUtil.a("ScaleCheckNewVersionThread", "localApplicationInfo is NULL;");
        }
        bST_(message, kxlVar);
    }

    private Message bSV_(Message message) {
        message.what = 2;
        return message;
    }

    private void bST_(Message message, kxl kxlVar) {
        kxu.b((kxl) null);
        kxu.b(kxlVar);
        if (d()) {
            return;
        }
        this.c.sendMessage(message);
    }

    private kxl d(Context context, String str, kxl kxlVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        kxl kxlVar2 = null;
        int i = -1;
        try {
            try {
                i = kyp.b(context, str, byteArrayOutputStream);
                LogUtil.a("ScaleCheckNewVersionThread", "retrieve filelist.xml: ", System.lineSeparator(), byteArrayOutputStream.toString("UTF-8"));
                if (i == 200) {
                    LogUtil.a("ScaleCheckNewVersionThread", "get file list success");
                    kxlVar2 = b(byteArrayOutputStream, kxlVar);
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("ScaleCheckNewVersionThread", "pack error!");
                }
            } finally {
                if (i == 200) {
                    LogUtil.a("ScaleCheckNewVersionThread", "get file list success");
                    b(byteArrayOutputStream, kxlVar);
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                    LogUtil.b("ScaleCheckNewVersionThread", "pack error!");
                }
            }
        } catch (IOException | URISyntaxException e) {
            LogUtil.b("ScaleCheckNewVersionThread", "Exception: ", ExceptionUtils.d(e));
        }
        return kxlVar2;
    }

    private kxl b(ByteArrayOutputStream byteArrayOutputStream, kxl kxlVar) {
        LogUtil.a("ScaleCheckNewVersionThread", "in dealSuccess");
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int i = 0;
        while (i < byteArray.length && byteArray[i] != 60) {
            i++;
        }
        byte[] bArr = new byte[byteArray.length - i];
        System.arraycopy(byteArray, i, bArr, 0, byteArray.length - i);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        kxl a2 = kyj.a(byteArrayInputStream, kxlVar);
        IoUtils.e(byteArrayInputStream);
        return a2;
    }
}
