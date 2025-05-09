package com.huawei.openalliance.ad.download;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dl;
import java.io.File;

/* loaded from: classes5.dex */
public class g {
    public static HttpConnection b(c cVar) {
        if (cVar == null) {
            return null;
        }
        HttpConnection d = cVar.d();
        d.a("dl-from", cVar.a("dl-from"));
        return d;
    }

    private static boolean a(DownloadTask downloadTask, String str) {
        String str2;
        File file = new File(str);
        if (!ae.d(file)) {
            str2 = "isFileValid - dst file not exist";
        } else {
            if (!downloadTask.z() || ae.a(downloadTask.c(), file)) {
                return true;
            }
            str2 = "isFileValid - dst file not valid";
        }
        ho.c("DownloadUtil", str2);
        return false;
    }

    static boolean a(Context context, DownloadTask downloadTask) {
        String str;
        String str2;
        ho.b("DownloadUtil", "isDownloadedFileValid " + dl.a(downloadTask.n()));
        String d = downloadTask.d();
        String c = dk.i(d) ? dh.a(context, "normal").c(d) : d;
        if (TextUtils.isEmpty(c)) {
            str = "isDownloadedFileValid - real path is empty";
        } else {
            if (a(downloadTask, c)) {
                a(context, d);
                return true;
            }
            str = "isDownloadedFileValid - real file is invalid";
        }
        ho.b("DownloadUtil", str);
        ho.b("DownloadUtil", "check tmp file");
        String e = downloadTask.e();
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        File file = new File(e);
        if (a(downloadTask, e)) {
            if (ae.a(context, file, d, downloadTask.A(), "normal")) {
                return true;
            }
            str2 = "isDownloadedFileValid - tmp file rename failed";
        } else {
            if (file.length() < downloadTask.f() || downloadTask.f() <= 0) {
                return false;
            }
            str2 = "isDownloadedFileValid - tmp file invalid";
        }
        ho.b("DownloadUtil", str2);
        ae.a(context, e);
        return false;
    }

    public static class a extends Exception {

        /* renamed from: a, reason: collision with root package name */
        private String f6808a;

        public String a() {
            return this.f6808a;
        }

        public a(String str) {
            super("Url is redirected!");
            this.f6808a = str;
        }
    }

    private static void a(Context context, String str) {
        if (dk.i(str)) {
            dh.a(context, "normal").h(str);
        } else {
            ae.c(new File(str));
        }
    }

    public static long a(String str) {
        String str2;
        long j = -1;
        if (!cz.b(str) && str.startsWith("bytes")) {
            int indexOf = str.indexOf(47);
            if (-1 != indexOf) {
                try {
                    j = Long.parseLong(str.substring(indexOf + 1));
                    if (ho.a()) {
                        ho.a("DownloadUtil", "get new filelength by Content-Range:%d", Long.valueOf(j));
                    }
                } catch (NumberFormatException unused) {
                    str2 = "getEntityLegth NumberFormatException";
                }
            } else {
                str2 = "getEntityLegth failed Content-Range";
            }
            ho.d("DownloadUtil", str2);
        }
        return j;
    }

    public static long a(c cVar) {
        int b = cVar.b();
        ho.a("DownloadUtil", "responseCode:%d", Integer.valueOf(b));
        if (206 == b) {
            return a(cVar.a("Content-Range"));
        }
        if (200 == b) {
            return cVar.c();
        }
        if (302 != b) {
            return 0L;
        }
        throw new a(cVar.a("Location"));
    }
}
