package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.utils.a;
import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes5.dex */
public class ksr {
    private static boolean b(Context context) {
        ksy.b("GlobalDownloadUtil", "check needUpdateProp ", true);
        long e = e(context);
        long time = new Date().getTime();
        if (e > time) {
            e = 0;
        }
        if (e == 0 || time - e > 86400000) {
            ksy.b("GlobalDownloadUtil", "need check prop", true);
            return true;
        }
        ksy.b("GlobalDownloadUtil", "no need check prop", true);
        return false;
    }

    public static void c(final Context context) {
        if (b(context)) {
            krs.a().execute(new Runnable() { // from class: ksr.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ksr.d(context);
                    } catch (IOException unused) {
                        ksy.c("GlobalDownloadUtil", "Error occured when download global Asyn.", true);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context) throws IOException {
        ksy.b("GlobalDownloadUtil", "Start syn download global.", true);
        String e = kqe.e().e(context, "com.huawei.cloud.hwid", "Root");
        if (TextUtils.isEmpty(e)) {
            ksy.c("GlobalDownloadUtil", "asUrl is null", true);
            throw new IOException("asUrl is null");
        }
        String str = e + "/AccountServer/global_cfg_for_android_mobile.xml";
        RestClient c = ksv.c(context, e);
        if (c == null) {
            ksy.c("GlobalDownloadUtil", "restClient init Failed", true);
            throw new IOException(Constants.LOG_ERROR);
        }
        try {
            Response<ResponseBody> execute = ((a) c.create(a.class)).a(str).execute();
            if (execute == null || execute.getBody() == null || !execute.isOK()) {
                return false;
            }
            ksy.b("GlobalDownloadUtil", "downloadGlobalCountrySiteSyn Succ", true);
            ksu.c(context, "global_cfg_for_android_mobile.xml", new String(execute.getBody().bytes(), "UTF-8"));
            krj.c().e(context);
            krl.e(context).d("lastupdate", String.valueOf(System.currentTimeMillis()));
            return true;
        } catch (IOException e2) {
            ksy.c("GlobalDownloadUtil", "IOException", true);
            throw new IOException("IOException[don't set proxy]:" + e2.getClass().getSimpleName());
        }
    }

    private static long e(Context context) {
        long j;
        try {
            return Long.parseLong(krl.e(context).a("lastupdate", "0"));
        } catch (Exception e) {
            ksy.c("GlobalDownloadUtil", "get string lastUpdate " + e.getClass().getSimpleName(), true);
            try {
                long c = krl.e(context).c("lastupdate", 0L);
                if (c > 0) {
                    try {
                        krl.e(context).d("lastupdate", String.valueOf(c));
                    } catch (Exception e2) {
                        e = e2;
                        j = c;
                        ksy.c("GlobalDownloadUtil", "get long lastUpdate " + e.getClass().getSimpleName(), true);
                        return j;
                    }
                }
                return c;
            } catch (Exception e3) {
                e = e3;
                j = 0;
            }
        }
    }
}
