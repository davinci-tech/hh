package defpackage;

import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class tpr {
    private static final Object d = new Object();
    private b b;
    private final Object e;

    static class c {
        private static final tpr e = new tpr();
    }

    private tpr() {
        this.e = new Object();
        this.b = new b(100);
        LogUtil.d("NotificationHandlerManager", "enter NotificationHandlerManager");
    }

    public static tpr e() {
        return c.e;
    }

    public String a() {
        long elapsedRealtime;
        synchronized (d) {
            elapsedRealtime = SystemClock.elapsedRealtime();
        }
        return String.valueOf(elapsedRealtime);
    }

    public void c(String str, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) {
        if (notifySendCallback == null) {
            LogUtil.c("NotificationHandlerManager", "registerNotificationListener callback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (notificationParcel == null) {
            LogUtil.c("NotificationHandlerManager", "registerNotificationListener notificationParcel is null");
            try {
                notifySendCallback.onError(null, 5);
            } catch (RemoteException unused) {
                LogUtil.e("NotificationHandlerManager", "generateErrorResult remoteException");
            } catch (Exception unused2) {
                LogUtil.e("NotificationHandlerManager", "registerNotificationListener onError has exception");
            }
            throw new IllegalStateException(String.valueOf(5));
        }
        LogUtil.d("NotificationHandlerManager", "registerNotificationListener callback ok");
        synchronized (this.e) {
            this.b.put(str, new e(notifySendCallback, notificationParcel));
        }
    }

    public void e(byte[] bArr) {
        List<cwd> a2 = tqy.a(bArr);
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        String str = "";
        int i = -1;
        for (cwd cwdVar : a2) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 4) {
                str = cvx.e(cwdVar.c());
            } else if (w == 9) {
                i = tqy.b(cwdVar);
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.e) {
            d(str, i);
        }
    }

    private void d(String str, int i) {
        e eVar = this.b.get(str);
        if (eVar == null) {
            LogUtil.d("NotificationHandlerManager", "notification {} has bean cleared.", str);
            return;
        }
        NotifySendCallback c2 = eVar.c();
        NotificationParcel e2 = eVar.e();
        try {
            try {
                if (trb.b(i)) {
                    c2.onError(e2, trb.d(i));
                } else {
                    c2.onResult(e2, i);
                }
                if (i == 0) {
                    return;
                }
            } catch (RemoteException unused) {
                LogUtil.e("NotificationHandlerManager", "handleNotifyCallback RemoteException");
                if (i == 0) {
                    return;
                }
            } catch (Exception unused2) {
                LogUtil.e("NotificationHandlerManager", "handleNotifyCallback notifySendCallback has exception");
                if (i == 0) {
                    return;
                }
            }
            this.b.remove(str);
        } catch (Throwable th) {
            if (i != 0) {
                this.b.remove(str);
            }
            throw th;
        }
    }

    static class b extends LruCache<String, e> {
        public b(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void entryRemoved(boolean z, String str, e eVar, e eVar2) {
            if (!z || eVar == null) {
                return;
            }
            NotifySendCallback c = eVar.c();
            NotificationParcel e = eVar.e();
            if (c == null || e == null) {
                return;
            }
            try {
                c.onResult(e, 0);
            } catch (RemoteException unused) {
                LogUtil.e("NotificationHandlerManager", "handleNotifyCallback RemoteException");
            }
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        NotificationParcel f17330a;
        NotifySendCallback b;

        public e(NotifySendCallback notifySendCallback, NotificationParcel notificationParcel) {
            this.b = notifySendCallback;
            this.f17330a = notificationParcel;
        }

        public NotifySendCallback c() {
            return this.b;
        }

        public NotificationParcel e() {
            return this.f17330a;
        }
    }
}
