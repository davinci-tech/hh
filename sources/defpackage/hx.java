package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alipay.sdk.m.a.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
public class hx {
    public com.alipay.sdk.m.a.a c = null;
    public String d = null;
    public String b = null;
    public final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    public ServiceConnection f13322a = new c();

    public static class a {
        public static final hx e = new hx(null);
    }

    public class c implements ServiceConnection {
        public c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            hx.this.c = a.AbstractBinderC0008a.a(iBinder);
            synchronized (hx.this.e) {
                hx.this.e.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            hx.this.c = null;
        }
    }

    public /* synthetic */ hx(c cVar) {
    }

    public final String c(Context context, String str) {
        Signature[] signatureArr;
        if (TextUtils.isEmpty(this.d)) {
            this.d = context.getPackageName();
        }
        if (TextUtils.isEmpty(this.b)) {
            String str2 = null;
            try {
                signatureArr = context.getPackageManager().getPackageInfo(this.d, 64).signatures;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    if (messageDigest != null) {
                        byte[] digest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : digest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        str2 = sb.toString();
                    }
                } catch (NoSuchAlgorithmException e2) {
                    e2.printStackTrace();
                }
            }
            this.b = str2;
        }
        String e3 = ((a.AbstractBinderC0008a.e) this.c).e(this.d, this.b, str);
        return TextUtils.isEmpty(e3) ? "" : e3;
    }

    public boolean e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            return Build.VERSION.SDK_INT >= 28 ? packageInfo != null && packageInfo.getLongVersionCode() >= 1 : packageInfo != null && packageInfo.versionCode >= 1;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String a(Context context, String str) {
        synchronized (this) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                if (this.c == null) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
                    intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
                    if (context.bindService(intent, this.f13322a, 1)) {
                        synchronized (this.e) {
                            try {
                                this.e.wait(3000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (this.c == null) {
                        return "";
                    }
                    try {
                        return c(context, str);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return "";
                    }
                }
                try {
                    return c(context, str);
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                    return "";
                }
            }
            throw new IllegalStateException("Cannot run on MainThread");
        }
    }
}
