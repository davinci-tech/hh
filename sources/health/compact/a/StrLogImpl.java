package health.compact.a;

import android.os.Looper;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.tencent.open.apireq.BaseResp;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class StrLogImpl {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayDeque<StrLogBuilder> f13142a;
    private StringBuffer b;
    private StrLogFileHandler f;
    private AtomicBoolean i = new AtomicBoolean(true);
    private static final boolean e = BuildConfigProperties.e("IS_OUTPUT_LOG", false);
    private static final String[] c = {ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "I", "W", ExifInterface.LONGITUDE_EAST, ExifInterface.LATITUDE_SOUTH};
    private static final Object d = new Object();

    public StrLogImpl() {
        Looper bRk_ = ThreadManager.bRk_();
        if (bRk_ == null) {
            Log.w("LogUtil_LogImpl", "ThreadManager.getMainLooper null");
        } else {
            this.f = new StrLogFileHandler(bRk_, this);
        }
        this.b = new StringBuffer(8192);
        this.f13142a = new ArrayDeque<>(3);
    }

    public void d() {
        synchronized (this) {
            this.b.delete(0, this.b.toString().length());
            this.f.e();
        }
    }

    public void e(boolean z) {
        synchronized (d) {
            this.i.set(z);
        }
    }

    public void c(int i, String str, String str2, Object[] objArr, boolean z) {
        StrLogBuilder a2;
        boolean e2 = LogAllowListConfig.e(str, d);
        if ((e2 || e) && (a2 = a(str)) != null) {
            int b = a2.b(str2, c[i], objArr);
            int i2 = 0;
            boolean z2 = b > 1000;
            int i3 = b + BaseResp.CODE_ERROR_PARAMS;
            String str3 = null;
            int i4 = 0;
            while (b > 0) {
                int min = Math.min(b, 1000);
                if (e2 || z2) {
                    str3 = a2.c(i4, i4 + min);
                }
                if (e2 && i2 < 2) {
                    if (!BuildTypeConfig.c()) {
                        i2++;
                    }
                    if (i2 == 2 && i3 > 0) {
                        b(i, str, str3 + ("... message length overlay 2000, " + i3 + " characters dropped"));
                    } else {
                        b(i, str, str3);
                    }
                }
                if (e) {
                    d(a2, str3, z);
                }
                i4 += min;
                b -= min;
            }
            c(a2);
        }
    }

    private StrLogBuilder a(String str) {
        StrLogBuilder pollLast;
        if (str == null) {
            return null;
        }
        synchronized (this.f13142a) {
            pollLast = this.f13142a.pollLast();
        }
        if (pollLast == null) {
            pollLast = new StrLogBuilder(e);
        }
        pollLast.d(str);
        return pollLast;
    }

    private void c(StrLogBuilder strLogBuilder) {
        if (strLogBuilder == null) {
            return;
        }
        synchronized (this.f13142a) {
            if (this.f13142a.size() >= 3) {
                return;
            }
            this.f13142a.addLast(strLogBuilder);
        }
    }

    private void b(int i, String str, String str2) {
        if (i == 0) {
            Log.v(str, str2);
            return;
        }
        if (i == 1) {
            Log.d(str, str2);
            return;
        }
        if (i == 2) {
            Log.i(str, str2);
            return;
        }
        if (i == 3) {
            Log.w(str, str2);
        } else if (i == 4) {
            Log.e(str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    private void d(StrLogBuilder strLogBuilder, String str, boolean z) {
        strLogBuilder.d(this.b, str);
        if (z) {
            e();
        } else if (this.b.length() >= 31744) {
            synchronized (this) {
                if (this.b.length() >= 31744 && this.i.get()) {
                    e();
                }
            }
        }
    }

    private void e() {
        synchronized (this) {
            String stringBuffer = this.b.toString();
            this.b.delete(0, stringBuffer.length());
            this.f.e(stringBuffer);
        }
    }

    public void a() {
        StringBuffer stringBuffer = this.b;
        if (stringBuffer == null) {
            Log.e("LogUtil_LogImpl", "mLogFileBuffer null");
            return;
        }
        if (stringBuffer.length() == 0) {
            return;
        }
        synchronized (this) {
            if (this.b.length() != 0) {
                String stringBuffer2 = this.b.toString();
                this.b.delete(0, stringBuffer2.length());
                if (this.i.get()) {
                    this.f.e(stringBuffer2);
                }
            }
        }
    }
}
