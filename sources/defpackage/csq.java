package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public class csq {
    private static boolean b = true;

    /* renamed from: a, reason: collision with root package name */
    private int f11447a;
    private Context c;
    private int d;
    private BaseCallbackInterface e;
    private int[] f;
    private byte[] g;
    private String h;
    private String i = "";
    private Handler j = new Handler() { // from class: csq.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("MulticastMessage", "mHandler msg is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                csr csrVar = new csr();
                csrVar.a(ctv.a(csq.this.c));
                csrVar.c(csq.this.f);
                csrVar.d(csq.this.m.c());
                csrVar.a(csq.this.m.f());
                csq csqVar = csq.this;
                csqVar.a(csqVar.f11447a, csrVar);
                csq.this.o.startTask();
                return;
            }
            if (i == 1) {
                if (csq.this.o != null) {
                    csq.this.o.pauseTask();
                }
            } else {
                if (i == 2) {
                    if (csq.this.o != null) {
                        csq.this.o.stopTask();
                        return;
                    }
                    return;
                }
                LogUtil.h("mHandler msg is other ", Integer.valueOf(message.what));
            }
        }
    };
    private byte[] k;
    private csu m;
    private AccessNetWorkTask o;

    public static int d(int i, int i2) {
        return (i - 1) | (((i2 >> 4) - 1) << 5);
    }

    public csq(Context context, csu csuVar, BaseCallbackInterface baseCallbackInterface) {
        this.h = "";
        this.c = context;
        this.e = baseCallbackInterface;
        this.m = csuVar;
        if (csuVar != null) {
            this.h = csuVar.e();
        }
    }

    public void d(int i) {
        this.f11447a = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, csr csrVar) {
        if (i == 1) {
            this.o = new css(this.c, csrVar, this.e);
            return;
        }
        if (i == 2) {
            this.o = new csy(this.c, csrVar, this.e);
            return;
        }
        csm csmVar = new csm(this.c, csrVar, this.e);
        this.o = csmVar;
        int i2 = this.d;
        if (i2 == 4) {
            if (csmVar instanceof csm) {
                csmVar.e(i2);
            } else {
                LogUtil.h("MulticastMessage", "mMulticastManager not instanceof CombinationTask");
            }
        }
    }

    public void c() {
        if (!b) {
            LogUtil.h("MulticastMessage", "startMulticast: multicast failed");
        } else {
            ThreadPoolManager.d().d("MulticastMessage", new a());
        }
    }

    class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            csq.this.m.a(cub.a(csq.this.c));
            csq.this.m.c(ctx.b());
            csq.this.m.f(ctx.c(8));
            csq.this.m.b(ctx.a());
            csq.this.m.c(ctx.d());
            csq.this.g();
            csu csuVar = csq.this.m;
            csq csqVar = csq.this;
            csuVar.a(csqVar.a(csqVar.k));
            csq.this.a();
            csq.this.f();
            LogUtil.c("MulticastMessage", "multicastInfo : ", csq.this.m);
            csq.this.j.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.c("MulticastMessage", "generateIpData: in");
        if (this.g == null || this.k.length == 0) {
            b = false;
            this.e.onFailure(2103);
            return;
        }
        String i = this.m.i();
        if (i == null) {
            return;
        }
        int length = i.getBytes(Charset.forName("UTF-8")).length;
        LogUtil.a("MulticastMessage", "ssidStr =", i, " String.length =", Integer.valueOf(i.length()), "  byte[].length =", Integer.valueOf(length));
        b(i, length);
    }

    private void b(String str, int i) {
        byte[] bArr;
        int length = this.k.length;
        int i2 = i + 20;
        int i3 = i2 + length;
        if (i3 % 2 != 0) {
            int[] iArr = new int[i3 + 1];
            this.f = iArr;
            iArr[i3] = 0;
        } else {
            this.f = new int[i3];
        }
        h();
        int i4 = 0;
        while (true) {
            byte[] bArr2 = this.g;
            if (i4 >= bArr2.length) {
                break;
            }
            this.f[i4 + 3] = bArr2[i4];
            i4++;
        }
        this.f[19] = d(i, length);
        int i5 = 20;
        if (d() > 0) {
            i5 = length + 20;
            i2 = 20;
        }
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("MulticastMessage", "generateIpDataDetails catch UnsupportedEncodingException");
            bArr = null;
        }
        if (bArr != null && bArr.length > 0) {
            for (int i6 = 0; i6 < i; i6++) {
                this.f[i5 + i6] = bArr[i6];
            }
        }
        for (int i7 = 0; i7 < length; i7++) {
            this.f[i2 + i7] = this.k[i7];
        }
        a(i + 18 + length);
    }

    private void h() {
        byte[] bArr;
        String c = c(this.h);
        this.i = c;
        try {
            bArr = c.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("MulticastMessage", "initIpData catch UnsupportedEncodingException");
            bArr = null;
        }
        LogUtil.c("MulticastMessage", "info :", ctv.a(bArr));
        this.f[0] = ctx.c(bArr);
        int[] iArr = this.f;
        iArr[1] = 0;
        if (iArr.length > 2) {
            if (this.d == 4) {
                LogUtil.a("MulticastMessage", "generateIpData HAND_ADD_WIFIAP_MODE");
                this.f[2] = 15;
            } else {
                iArr[2] = j();
            }
        }
    }

    private void a(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) this.f[i2 + 2];
        }
        this.f[1] = ctx.c(bArr);
        int[] e = ctv.e(this.f);
        this.f = e;
        LogUtil.a("MulticastMessage", "generateIpData Hex : ", ctv.a(ctv.d(e)));
    }

    private String c(String str) {
        StringBuilder sb = new StringBuilder(16);
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MulticastMessage", "ssid is null");
            return "";
        }
        if (str.length() - 5 >= 0) {
            str2 = str.substring(str.length() - 5);
        } else {
            LogUtil.h("MulticastMessage", "ssid length error");
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.c("MulticastMessage", "encryptKeyData: in mode ", Integer.valueOf(this.m.b()));
        byte[] a2 = ctv.a(this.m.b(), this.m.g(), this.m.d());
        this.g = a2;
        if (a2 == null) {
            LogUtil.h("MulticastMessage", "encryptKeyData: out mKeyEncodeData is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        if (bArr != null && bArr.length >= 16) {
            for (int i = 0; i < 16; i++) {
                bArr2[i] = bArr[i];
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("MulticastMessage", "encryptValueData: in");
        String h = this.m.h();
        if (h == null) {
            return;
        }
        byte[] b2 = ctu.b(h.getBytes(Charset.forName("UTF-8")), this.m.d(), this.m.a());
        this.k = b2;
        LogUtil.c("MulticastMessage", "encryptValueData mValueEncodeData :", ctv.a(b2));
    }

    private int j() {
        int i;
        int d = d();
        int b2 = this.m.b();
        if (b2 != 100) {
            if (b2 == 101) {
                i = 2;
            } else if (b2 != 110) {
                LogUtil.h("MulticastMessage", "getVersion2Mode mEncryptMode ", Integer.valueOf(b2));
            } else {
                i = 3;
            }
            return (d & 15) | ((i & 15) << 4);
        }
        i = 1;
        return (d & 15) | ((i & 15) << 4);
    }

    public void e() {
        this.j.sendEmptyMessage(1);
    }

    public void b() {
        this.j.sendEmptyMessage(2);
    }

    public int d() {
        int i = 0;
        if (this.h.length() > 4) {
            LogUtil.c("MulticastMessage", "getSsidVersion char ", Character.valueOf(this.h.charAt(2)));
            String valueOf = String.valueOf(this.h.charAt(2));
            LogUtil.c("MulticastMessage", "getSsidVersion str ", valueOf);
            try {
                i = Integer.parseInt(valueOf);
            } catch (NumberFormatException unused) {
                LogUtil.b("MulticastMessage", "getSsidVersion catch NumberFormatException");
            }
            LogUtil.a("MulticastMessage", "getSsidVersion version ", Integer.valueOf(i));
        }
        return i;
    }

    public void c(int i) {
        this.d = i;
    }
}
