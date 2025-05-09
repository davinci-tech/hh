package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public final class ksd {

    /* renamed from: a, reason: collision with root package name */
    private static Context f14568a = null;
    private static final String c = "WebHelper";
    private static kss e;
    private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();
    private static Handler d = new Handler(Looper.myLooper()) { // from class: ksd.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1001) {
                return;
            }
            ksy.b(ksd.c, "download succ,begining copy", true);
            ksd.c(ksd.c(), ksd.b(), (String) message.obj);
        }
    };

    public static ConcurrentHashMap<String, String> d() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c() {
        try {
            return f14568a.getDir("hwId", 0).getCanonicalPath() + "/";
        } catch (IOException e2) {
            ksy.c(c, "IOException:" + e2.getClass().getSimpleName(), true);
            return "";
        }
    }

    public static String b() {
        try {
            return f14568a.getDir("hw", 0).getCanonicalPath() + "/";
        } catch (IOException e2) {
            ksy.c(c, "IOException:" + e2.getClass().getSimpleName(), true);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2, String str3) {
        ksy.b(c, "handlerRequestCopyData", true);
        e.c(str, str2, str3);
    }
}
