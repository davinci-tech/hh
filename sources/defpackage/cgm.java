package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cgm {
    private static final Object b = new Object();
    private static cgm e;
    private byte[] c;
    private byte[] d;
    private List<byte[]> i;
    private byte[] p;
    private byte[] s;
    private long r = 0;
    private int o = 2;

    /* renamed from: a, reason: collision with root package name */
    private int f704a = 0;
    private int j = 0;
    private int q = 0;
    private int m = 0;
    private int k = 0;
    private byte l = 0;
    private byte n = 0;
    private int f = 0;
    private boolean g = false;
    private Handler h = new Handler(Looper.getMainLooper()) { // from class: cgm.5
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            LogUtil.a("WeightScalesUpdate", "msg what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                cgm.this.EN_(message);
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                LogUtil.a("WeightScalesUpdate", "OTA upgrade power is too low...");
                return;
            }
            if (message.arg1 == 0) {
                LogUtil.a("WeightScalesUpdate", "update success.");
            } else if (message.arg1 == 1) {
                LogUtil.a("WeightScalesUpdate", "update fail. timeout");
            } else if (message.arg1 == 2) {
                LogUtil.a("WeightScalesUpdate", "update fail. CS error");
            } else if (message.arg1 == 4) {
                LogUtil.a("WeightScalesUpdate", "update fail. power low");
            } else {
                LogUtil.a("WeightScalesUpdate", "update fail");
            }
            cgm.this.b(message.arg1);
        }
    };

    private cgm() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EN_(Message message) {
        float f;
        if (this.q <= 0 || message == null || !(message.obj instanceof String)) {
            return;
        }
        try {
            f = Float.parseFloat((String) message.obj);
        } catch (NumberFormatException e2) {
            LogUtil.b("WeightScalesUpdate", "updateProgress exception = ", e2.getMessage());
            f = 0.0f;
        }
        int i = (int) ((f / this.q) * 100.0f);
        this.f704a = i;
        if (i > this.j) {
            this.j = i;
        } else {
            LogUtil.a("WeightScalesUpdate", "mFirmwarePro <= mNowPro");
        }
        LogUtil.a("WeightScalesUpdate", "mProSize: ", Integer.valueOf(this.q), ", OTA progress: ", Float.valueOf(f), ", mFirmwarePro: ", Integer.valueOf(this.f704a));
        a(this.f704a);
    }

    public static cgm d() {
        cgm cgmVar;
        synchronized (cgm.class) {
            synchronized (b) {
                if (e == null) {
                    e = new cgm();
                }
                cgmVar = e;
            }
        }
        return cgmVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LogUtil.a("WeightScalesUpdate", "Enter sendUpdateStatus!");
        Intent intent = new Intent();
        intent.putExtra("update_status", i);
        EventBus.d(new EventBus.b("upgrade_update_status", intent));
    }

    private void a(int i) {
        LogUtil.a("WeightScalesUpdate", "Enter sendUpdateProgress!");
        Intent intent = new Intent();
        intent.putExtra("update_progress", i);
        EventBus.d(new EventBus.b("upgrade_update_progress", intent));
    }

    public void d(String str, String str2) {
        LogUtil.a("WeightScalesUpdate", "update scalePath:", str, ",blePath:", str2);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f704a = 0;
            this.j = 0;
            this.q = 0;
            this.f = 0;
            this.g = false;
            String a2 = a(str);
            if (TextUtils.isEmpty(a2)) {
                Message obtainMessage = this.h.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = 2;
                this.h.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("WeightScalesUpdate", "no TextUtils.isEmpty(strScale) ");
            this.c = cgi.a(a2);
            this.s = cgi.a(a2.substring(32));
            String a3 = a(str2);
            if (TextUtils.isEmpty(a3)) {
                Message obtainMessage2 = this.h.obtainMessage();
                obtainMessage2.what = 2;
                obtainMessage2.arg1 = 2;
                this.h.sendMessage(obtainMessage2);
                return;
            }
            LogUtil.a("WeightScalesUpdate", "bleScale is not empty");
            this.d = cgi.a(a3);
            this.p = cgi.a(a3.substring(32));
            this.o = 2;
            b(str, str2);
            this.g = true;
            if (cgk.d().a()) {
                LogUtil.a("WeightScalesUpdate", "ota is connect");
                cgk.d().e(cgg.c(this.o, this.m, this.l));
                return;
            } else {
                LogUtil.h("WeightScalesUpdate", "no connect");
                return;
            }
        }
        LogUtil.h("WeightScalesUpdate", "scalePath or blePath is null");
    }

    public void b(boolean z) {
        LogUtil.a("WeightScalesUpdate", "------onOtaUpgradeReady------- result: ", Boolean.valueOf(z), ", ", "mFirstPkgDataIdx: ", Integer.valueOf(this.f), ", mOtaByteList size: ", Integer.valueOf(this.i.size()));
        this.r = System.currentTimeMillis();
        if (this.g) {
            LogUtil.a("WeightScalesUpdate", "start scale");
            cgk.d().e(cgg.c(this.c));
        } else {
            LogUtil.a("WeightScalesUpdate", "start ble ");
            cgk.d().e(cgg.c(this.d));
        }
    }

    public void b() {
        LogUtil.a("WeightScalesUpdate", "------onReceiveShaPackageOne-------");
        if (this.g) {
            LogUtil.a("WeightScalesUpdate", "start scale");
            cgk.d().e(cgg.d(this.s));
        } else {
            LogUtil.a("WeightScalesUpdate", "start ble");
            cgk.d().e(cgg.d(this.p));
        }
    }

    public void a() {
        LogUtil.a("WeightScalesUpdate", "------onReceiveShaPackageTwo-------");
        this.r = System.currentTimeMillis();
        int i = this.f;
        if (i >= 0 && i < this.i.size()) {
            cgk.d().e(cgg.a(this.i.get(this.f), 0));
        } else {
            LogUtil.a("WeightScalesUpdate", "onReceiveSha256PkgTwo false");
        }
    }

    public void d(int i, int i2) {
        LogUtil.a("WeightScalesUpdate", "------onUpgradeResult-------result： ", Integer.valueOf(i), ", type: ", Integer.valueOf(i2), ", mIsUpgradeOtaAndBle: ", Boolean.valueOf(this.g));
        this.f = 0;
        LogUtil.a("WeightScalesUpdate", "upgrade type is : ", Integer.valueOf(this.o), " times:", Long.valueOf(System.currentTimeMillis() - this.r), "ms");
        if (this.g && i == 0) {
            this.f = this.m;
            this.g = false;
            cgk.d().e(cgg.c(1, this.k, this.n));
        } else {
            cgk.d().e(cgg.b());
            Message obtainMessage = this.h.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.arg1 = i;
            this.h.sendMessage(obtainMessage);
        }
    }

    public void a(int i, boolean z) {
        int i2;
        int i3 = this.q;
        int i4 = this.m;
        if (i3 <= i4 || this.f != i4) {
            LogUtil.a("WeightScalesUpdate", "onUpgradeResponse false");
            i2 = i;
        } else {
            i2 = i4 + i;
        }
        Message obtainMessage = this.h.obtainMessage();
        obtainMessage.obj = String.valueOf(i2);
        obtainMessage.what = 1;
        obtainMessage.arg1 = i2;
        this.h.sendMessage(obtainMessage);
        LogUtil.a("WeightScalesUpdate", "------onUpgradeResponse-------pkgNo: ", Integer.valueOf(i), ", result: ", Boolean.valueOf(z), ", currProgress: ", Integer.valueOf(i2), ", mOtaByteList size: ", Integer.valueOf(this.i.size()));
        int i5 = i2 + 1;
        if (i5 < this.i.size()) {
            if (z) {
                cgk.d().e(cgg.a(this.i.get(i5), i + 1));
            } else {
                cgk.d().e(cgg.a(this.i.get(i2), i));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cgm.b(java.lang.String, java.lang.String):void");
    }

    private void e(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e2) {
            LogUtil.b("WeightScalesUpdate", " setScaleOtaByte 1 ", e2.getMessage());
        }
    }

    private static String a(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException unused) {
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            int i = 0;
            do {
                int read = fileInputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
                i += read;
            } while (i <= 10485760);
            String d = cvx.d(messageDigest.digest());
            try {
                fileInputStream.close();
                return d;
            } catch (IOException e3) {
                LogUtil.b("WeightScalesUpdate", " setScaleOtaByte 3 ", e3.getMessage());
                return d;
            }
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("WeightScalesUpdate", "update IOException");
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4) {
                    LogUtil.b("WeightScalesUpdate", " setScaleOtaByte 3 ", e4.getMessage());
                }
            }
            return "";
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            fileInputStream2 = fileInputStream;
            LogUtil.b("WeightScalesUpdate", "update NoSuchAlgorithmException ", e.getMessage());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e6) {
                    LogUtil.b("WeightScalesUpdate", " setScaleOtaByte 3 ", e6.getMessage());
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e7) {
                    LogUtil.b("WeightScalesUpdate", " setScaleOtaByte 3 ", e7.getMessage());
                }
            }
            throw th;
        }
    }

    private byte c(byte[] bArr, byte b2) {
        for (byte b3 : bArr) {
            b2 = (byte) (b2 ^ b3);
            for (int i = 0; i < 8; i++) {
                int i2 = b2 & 128;
                int i3 = b2 << 1;
                if (i2 > 0) {
                    i3 = ((byte) i3) ^ 7;
                }
                b2 = (byte) i3;
            }
        }
        return b2;
    }

    public void c(int i, byte[] bArr, Object obj) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("WeightScalesUpdate", "sendOtaType data is null");
            return;
        }
        if (obj == null) {
            LogUtil.h("WeightScalesUpdate", "sendOtaType resultObject is null");
            return;
        }
        if (i == 9) {
            a(obj);
            return;
        }
        if (i == 10) {
            LogUtil.a("WeightScalesUpdate", "ota result...");
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                int i2 = jSONObject.getInt("type");
                int i3 = jSONObject.getInt("result");
                LogUtil.a("WeightScalesUpdate", "-----------ota update result ----------- type: ", Integer.valueOf(i2), ", result: ", Integer.valueOf(i3));
                d(i3, i2);
                return;
            } catch (JSONException e2) {
                LogUtil.a("WeightScalesUpdate", "json analysis fail. error msg: ", e2.toString());
                return;
            }
        }
        if (i == 15) {
            LogUtil.a("WeightScalesUpdate", "---OTA readied---");
            b(bArr[3] == 0);
        } else if (i == 20) {
            LogUtil.a("WeightScalesUpdate", "---TYPE_SHA256PKG1--- ", obj);
            b();
        } else if (i == 21) {
            LogUtil.a("WeightScalesUpdate", "---TYPE_SHA256PKG2--- ", obj);
            a();
        } else {
            LogUtil.a("WeightScalesUpdate", "parseType: ", Integer.valueOf(i));
        }
    }

    private void a(Object obj) {
        LogUtil.a("WeightScalesUpdate", "----ota pkg response----");
        try {
            JSONObject jSONObject = new JSONObject(obj.toString());
            int i = jSONObject.getInt("pkgNo");
            boolean z = jSONObject.getBoolean("result");
            LogUtil.a("WeightScalesUpdate", "json object pkgNo: ", Integer.valueOf(i), ", result: ", Boolean.valueOf(z));
            a(i, z);
        } catch (JSONException e2) {
            LogUtil.a("WeightScalesUpdate", "json analysis fail. error msg: ", e2.toString());
        }
    }
}
