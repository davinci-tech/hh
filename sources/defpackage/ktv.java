package defpackage;

import android.os.Looper;
import android.util.Log;
import health.compact.a.BuildConfigProperties;
import health.compact.a.ThreadManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class ktv {
    private AtomicBoolean b = new AtomicBoolean(true);
    private List<Byte> c = new ArrayList();
    private ktp e;
    private String i;
    private static final boolean d = BuildConfigProperties.e("IS_OUTPUT_LOG", false);

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14591a = new Object();

    public ktv(String str) {
        this.i = null;
        this.i = str;
        Looper bRk_ = ThreadManager.bRk_();
        if (bRk_ == null) {
            Log.w("LogUtil_BytesLogImpl", "ThreadManager.getMainLooper null");
        } else {
            this.e = new ktp(bRk_);
        }
    }

    public void d() {
        synchronized (this) {
            this.c.clear();
        }
        this.e.e();
    }

    public void b(boolean z) {
        synchronized (f14591a) {
            this.b.set(z);
        }
    }

    public void b(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        b(this.i, bArr);
        if (d) {
            e(bArr, z);
        }
    }

    private void b(String str, byte[] bArr) {
        Log.i(str, b(bArr));
    }

    private void e(byte[] bArr, boolean z) {
        boolean z2;
        if (bArr == null || bArr.length == 0) {
            Log.e("LogUtil_BytesLogImpl", "saveToFile byte[] left null");
            return;
        }
        synchronized (this) {
            for (byte b : bArr) {
                this.c.add(Byte.valueOf(b));
            }
        }
        if (z) {
            e();
            return;
        }
        synchronized (this) {
            z2 = this.c.size() >= 8192;
        }
        if (z2) {
            synchronized (this) {
                if (this.c.size() >= 8192 && this.b.get()) {
                    e();
                }
            }
        }
    }

    private void e() {
        synchronized (this) {
            int size = this.c.size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = this.c.get(i).byteValue();
            }
            this.c.clear();
            this.e.c(bArr, "sensor");
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format(Locale.ROOT, "%02X", Integer.valueOf(b & 255)));
        }
        return stringBuffer.toString();
    }

    public void c() {
        synchronized (this) {
            List<Byte> list = this.c;
            if (list == null) {
                Log.e("LogUtil_BytesLogImpl", "mLogFileBuffer null");
            } else {
                if (list.size() == 0) {
                    return;
                }
                synchronized (this) {
                    if (this.c.size() != 0 && this.b.get()) {
                        e();
                    }
                }
            }
        }
    }
}
