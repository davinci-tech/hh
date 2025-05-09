package com.huawei.dnurse.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;

/* loaded from: classes3.dex */
public class DnurseDeviceTest {
    private static SimpleDateFormat M;
    private static DnurseDeviceTest e;
    private byte D;
    private byte E;
    private byte F;
    private b G;
    private a H;
    private Context I;
    private IMeasureDataResultCallback J;
    private Timer L;
    private float g;
    private byte j;
    private int k;
    private int l;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private boolean t;
    private boolean u;
    private byte v;
    private byte x;
    private byte y;
    private int z;
    private int f = 0;
    private int[][] h = {new int[]{4, 7, 11, 16, 11, 20, 1}, new int[]{7, 12, 17, 22, 19, 20, 2}, new int[]{10, 16, 23, 30, 19, 20, 3}};
    private int i = 0;
    private int m = 10;
    private boolean s = false;
    private boolean w = true;
    private String A = "";
    private boolean B = false;
    private boolean C = false;
    private Handler K = new Handler(Looper.getMainLooper());
    private float N = 0.0f;
    private float O = 0.0f;
    private BroadcastReceiver P = new com.huawei.dnurse.sdk.a(this);

    /* renamed from: a, reason: collision with root package name */
    Runnable f1951a = new com.huawei.dnurse.sdk.b(this);
    Runnable b = new c(this);
    Runnable c = new d(this);
    Runnable d = new e(this);

    public void wakeupDevice() {
        if (!this.B || DnurseConstant.isWorking(this.f)) {
            return;
        }
        this.K.postDelayed(this.f1951a, this.o);
    }

    public void stopTest() {
        try {
            this.I.unregisterReceiver(this.P);
        } catch (IllegalArgumentException unused) {
        }
        this.f = 0;
        c();
        a aVar = this.H;
        if (aVar != null) {
            aVar.e();
            this.H = null;
        }
    }

    public void startTest(IMeasureDataResultCallback iMeasureDataResultCallback) {
        this.J = iMeasureDataResultCallback;
        if (this.H == null) {
            this.H = new a();
        }
        if (this.G == null) {
            this.G = new b();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        this.I.registerReceiver(this.P, intentFilter);
        this.J.onMeasuring(this.f, 0);
    }

    public String getDeviceSn() {
        return this.A;
    }

    public static String newIdWithTag(String str) {
        String format = d().format(Calendar.getInstance().getTime());
        String format2 = String.format(Locale.US, "%06d", Integer.valueOf(new Random().nextInt(1000000)));
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        sb.append(format);
        sb.append(format2);
        return sb.toString();
    }

    static /* synthetic */ byte k(DnurseDeviceTest dnurseDeviceTest) {
        byte b2 = dnurseDeviceTest.D;
        dnurseDeviceTest.D = (byte) (b2 - 1);
        return b2;
    }

    public static DnurseDeviceTest getInstance(Context context) {
        Log.d("HWHealthSDK", "DnurseDeviceTest: 333");
        if (e == null) {
            synchronized (DnurseDeviceTest.class) {
                if (e == null) {
                    e = new DnurseDeviceTest(context);
                }
            }
        }
        return e;
    }

    private static SimpleDateFormat d() {
        if (M == null) {
            synchronized (DnurseDeviceTest.class) {
                if (M == null) {
                    M = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.US);
                }
            }
        }
        return M;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        a aVar;
        this.K.removeCallbacks(this.f1951a);
        this.K.removeCallbacks(this.c);
        this.K.removeCallbacks(this.d);
        a aVar2 = this.H;
        if (aVar2 != null) {
            aVar2.e();
        }
        if (this.B && (aVar = this.H) != null) {
            aVar.b();
        }
        b bVar = this.G;
        if (bVar != null) {
            bVar.d();
        }
        this.C = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void b(int i) {
        if (i == this.f) {
            return;
        }
        Log.i("HWHealthSDK", "notifyChange,state=" + i);
        if (i != 0) {
            switch (i) {
                case 2:
                    if (this.f == 1) {
                        if (this.i <= 1) {
                            this.H.a(true);
                        } else {
                            this.H.a(false);
                        }
                        a(this.n);
                        this.E = (byte) -1;
                        break;
                    }
                    break;
                case 3:
                    this.C = true;
                    a(195000);
                    break;
                case 4:
                case 5:
                case 6:
                    a(195000);
                    break;
                case 7:
                    a();
                    break;
                case 8:
                    a(195000);
                    this.H.d();
                    if (this.x == -1 || this.E != this.F) {
                        this.E = this.F;
                        Calendar calendar = Calendar.getInstance();
                        SparseArray sparseArray = new SparseArray();
                        sparseArray.put(1, Float.valueOf(this.g));
                        sparseArray.put(2, calendar);
                        sparseArray.put(3, this.A);
                        this.J.onSuccess(sparseArray);
                        break;
                    }
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                    c();
                    break;
            }
            return;
        }
        this.x = (byte) -1;
        this.y = (byte) -1;
        this.z = 0;
        this.A = "";
        this.f = i;
        if (i == 7) {
            this.J.onMeasuring(i, this.D);
        } else {
            this.J.onMeasuring(i, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int i;
        b(1);
        if (!this.H.a()) {
            Log.i("HWHealthSDK", "AudPly: start failed!");
            i = 14;
        } else if (this.G.c().booleanValue()) {
            a(this.n);
            this.K.postDelayed(this.c, this.p);
            return;
        } else {
            Log.i("HWHealthSDK", "AudRec: start failed!");
            i = 15;
        }
        b(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.K.removeCallbacks(this.d);
        Timer timer = this.L;
        if (timer != null) {
            timer.cancel();
        }
        this.K.postDelayed(this.d, i);
    }

    private void a() {
        Date date = new Date(System.currentTimeMillis() + 1000);
        this.D = (byte) 10;
        this.K.removeCallbacks(this.d);
        Timer timer = this.L;
        if (timer != null) {
            timer.cancel();
            this.L.purge();
        }
        Timer timer2 = new Timer(true);
        this.L = timer2;
        timer2.scheduleAtFixedRate(new f(this), date, 1000L);
    }

    class b {
        private AudioRecord b;
        private int c;
        private short d = 0;

        class a implements Runnable {
            private short[] b;
            private byte[] c;
            private int d;
            private byte e;
            private final byte[] f = {Byte.MIN_VALUE, 8, -120, 0};
            private final byte[] g = {Byte.MIN_VALUE, 8, -86, 85};
            private final byte[] h = {-61, 60, -52, 51};
            private final byte[] i = {-61, 60, -91, 90};
            private byte j = 8;
            private byte[] k = new byte[255];
            private byte l = 0;
            private byte m = 0;
            private byte n = 0;
            private byte o = 0;
            private byte p = 0;
            private byte q = 0;
            private short r = 0;
            private boolean s = false;
            private boolean t = false;
            private int[] u = new int[9];
            private int v = 0;
            private int w = 0;
            private int x = 0;
            private int y = 0;

            @Override // java.lang.Runnable
            public void run() {
                Log.d("HWHealthSDK", "AudRec: thread started!");
                if (b.this.b != null && b.this.b.getRecordingState() != 3) {
                    Log.i("HWHealthSDK", "AudRec: can't record, released");
                    b.this.b.release();
                    b.this.b = null;
                    DnurseDeviceTest.this.b(15);
                }
                while (b.this.b != null && b.this.b.getRecordingState() == 3) {
                    int read = b.this.b.read(this.b, 0, this.d);
                    if (-3 != read) {
                        b(read);
                    }
                }
                if (b.this.b != null) {
                    Log.i("HWHealthSDK", "AudRec: released");
                    b.this.b.release();
                    b.this.b = null;
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:100:0x034a, code lost:
            
                if (r19.f1954a.f1953a.f == 2) goto L122;
             */
            /* JADX WARN: Code restructure failed: missing block: B:101:0x0497, code lost:
            
                r1 = r19.f1954a.f1953a;
                r12 = 3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:118:0x0495, code lost:
            
                if (r19.f1954a.f1953a.f == 2) goto L122;
             */
            /* JADX WARN: Removed duplicated region for block: B:103:0x0303  */
            /* JADX WARN: Removed duplicated region for block: B:89:0x02d7  */
            /* JADX WARN: Removed duplicated region for block: B:97:0x033b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private void f() {
                /*
                    Method dump skipped, instructions count: 1210
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.dnurse.sdk.DnurseDeviceTest.b.a.f():void");
            }

            private void e() {
                int i;
                boolean z;
                if (DnurseDeviceTest.this.v == 0) {
                    int i2 = DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][0];
                    int i3 = DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][2];
                    for (int i4 = 0; i4 < 9; i4++) {
                        if (this.u[i4] > DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][1]) {
                            int i5 = this.u[i4];
                            if (i5 < i3) {
                                i3 = i5;
                            }
                        } else {
                            int i6 = this.u[i4];
                            if (i6 > i2) {
                                i2 = i6;
                            }
                        }
                    }
                    byte b = this.e;
                    if (i3 - i2 < b / 4) {
                        this.e = (byte) (b - 1);
                        Log.i("HWHealthSDK", "RECV Diff: changed to " + ((int) this.e));
                        return;
                    }
                }
                byte[] bArr = this.k;
                byte b2 = this.p;
                byte b3 = (byte) (b2 + 1);
                this.p = b3;
                bArr[b2] = (byte) (this.r & 255);
                if (b3 == 4) {
                    int i7 = 0;
                    while (i7 < 4 && this.k[i7] == this.f[i7]) {
                        i7++;
                    }
                    if (i7 == 4) {
                        Log.i("HWHealthSDK", "R: Wave HIGH first");
                        if (DnurseDeviceTest.this.v == 0) {
                            DnurseDeviceTest.this.w = true;
                        }
                        i = 0;
                        z = true;
                    } else {
                        i = 0;
                        while (i < 4 && this.k[i] == this.g[i]) {
                            i++;
                        }
                        z = false;
                    }
                    if (i == 4) {
                        Log.i("HWHealthSDK", "R: Wave HIGH(2) first");
                        if (DnurseDeviceTest.this.v == 0) {
                            DnurseDeviceTest.this.w = true;
                        }
                        i = 0;
                        z = true;
                    } else if (DnurseDeviceTest.this.v == 0) {
                        i = 0;
                        while (i < 4 && this.k[i] == this.h[i]) {
                            i++;
                        }
                    }
                    if (i != 4) {
                        if (DnurseDeviceTest.this.v == 0) {
                            i = 0;
                            while (i < 4 && this.k[i] == this.i[i]) {
                                i++;
                            }
                        }
                    } else {
                        Log.i("HWHealthSDK", "R: Wave LOW first");
                        DnurseDeviceTest.this.w = false;
                        i = 0;
                        z = true;
                    }
                    if (i == 4) {
                        Log.i("HWHealthSDK", "R: Wave LOW(2) first");
                        DnurseDeviceTest.this.w = false;
                        z = true;
                    }
                    if (!z) {
                        a();
                    }
                    if (z) {
                        DnurseDeviceTest.this.b(2);
                        c();
                    }
                }
            }

            private void d() {
                this.t = false;
                this.l = (byte) 0;
            }

            private void c() {
                this.p = (byte) 0;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0052  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x005e  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0061 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private void b(int r6) {
                /*
                    r5 = this;
                    r0 = 0
                L1:
                    if (r0 >= r6) goto L64
                    short[] r1 = r5.b
                    short r2 = r1[r0]
                    int r3 = r0 + 1
                    short r1 = r1[r3]
                    int r2 = r2 + r1
                    int r2 = r2 / 2
                    short r1 = (short) r2
                    com.huawei.dnurse.sdk.DnurseDeviceTest$b r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.b.this
                    short r4 = com.huawei.dnurse.sdk.DnurseDeviceTest.b.c(r2)
                    r4 = r4 | r1
                    short r4 = (short) r4
                    com.huawei.dnurse.sdk.DnurseDeviceTest.b.a(r2, r4)
                    byte[] r2 = r5.c
                    r4 = r1 & 255(0xff, float:3.57E-43)
                    byte r4 = (byte) r4
                    r2[r0] = r4
                    int r4 = r1 >> 8
                    byte r4 = (byte) r4
                    r2[r3] = r4
                    com.huawei.dnurse.sdk.DnurseDeviceTest$b r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.b.this
                    com.huawei.dnurse.sdk.DnurseDeviceTest r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.this
                    boolean r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.G(r2)
                    if (r2 == 0) goto L37
                    r2 = -2000(0xfffffffffffff830, float:NaN)
                    if (r1 < r2) goto L37
                    int r1 = r1 + (-2000)
                    goto L47
                L37:
                    com.huawei.dnurse.sdk.DnurseDeviceTest$b r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.b.this
                    com.huawei.dnurse.sdk.DnurseDeviceTest r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.this
                    boolean r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.H(r2)
                    if (r2 == 0) goto L48
                    r2 = 2000(0x7d0, float:2.803E-42)
                    if (r1 > r2) goto L48
                    int r1 = r1 + 2000
                L47:
                    short r1 = (short) r1
                L48:
                    com.huawei.dnurse.sdk.DnurseDeviceTest$b r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.b.this
                    com.huawei.dnurse.sdk.DnurseDeviceTest r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.this
                    boolean r2 = com.huawei.dnurse.sdk.DnurseDeviceTest.I(r2)
                    if (r2 != 0) goto L55
                    int r1 = r1 / (-4)
                    goto L57
                L55:
                    int r1 = r1 / 4
                L57:
                    short r1 = (short) r1
                    int r1 = r5.a(r1)
                    if (r1 == 0) goto L61
                    r5.a(r1)
                L61:
                    int r0 = r0 + 2
                    goto L1
                L64:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.dnurse.sdk.DnurseDeviceTest.b.a.b(int):void");
            }

            private void b() {
                int i = 1;
                while (i < this.p && this.k[i] != -1) {
                    i++;
                }
                int i2 = 0;
                while (i < this.p) {
                    byte[] bArr = this.k;
                    bArr[i2] = bArr[i];
                    i++;
                    i2++;
                }
                this.p = (byte) i2;
            }

            /* JADX WARN: Removed duplicated region for block: B:45:0x01ea  */
            /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private void a(int r9) {
                /*
                    Method dump skipped, instructions count: 678
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.dnurse.sdk.DnurseDeviceTest.b.a.a(int):void");
            }

            private void a() {
                int i = 0;
                while (true) {
                    int i2 = this.p - 1;
                    if (i >= i2) {
                        this.p = (byte) i2;
                        return;
                    }
                    byte[] bArr = this.k;
                    int i3 = i + 1;
                    bArr[i] = bArr[i3];
                    i = i3;
                }
            }

            private int a(short s) {
                int i;
                int i2 = 0;
                if ((s <= 0 || this.v <= 0) && (!(s == 0 && this.v == 0) && (s >= 0 || this.v >= 0))) {
                    if (this.v == 0 || this.w <= DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][6]) {
                        this.y += this.w;
                    } else {
                        int i3 = this.v;
                        if ((i3 <= 0 || this.x <= 0) && i3 >= 0) {
                            i2 = this.y;
                            i = this.w;
                        } else {
                            i = this.y + this.w;
                        }
                        this.y = i;
                        this.x = i3;
                    }
                    this.v = s;
                    this.w = 1;
                } else {
                    this.w++;
                }
                return i2;
            }

            public a(int i) {
                this.e = (byte) 0;
                if (DnurseDeviceTest.this.j != 1) {
                    if (DnurseDeviceTest.this.j != 2 && DnurseDeviceTest.this.j == 3) {
                        DnurseDeviceTest.this.i = 2;
                    } else {
                        DnurseDeviceTest.this.i = 1;
                    }
                } else {
                    DnurseDeviceTest.this.i = 0;
                }
                this.e = (byte) DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][4];
                if (DnurseDeviceTest.this.v == 0 || DnurseDeviceTest.this.v == 1) {
                    DnurseDeviceTest.this.w = true;
                } else {
                    DnurseDeviceTest.this.w = false;
                }
                int i2 = i / 2;
                this.d = i2;
                this.b = new short[i2];
                this.c = new byte[i2 * 2];
                if (b.this.b == null || b.this.b.getRecordingState() != 3) {
                    return;
                }
                b.this.b.read(this.b, 0, this.d);
            }
        }

        public Boolean c() {
            if (this.c <= 0) {
                return false;
            }
            AudioRecord audioRecord = new AudioRecord(1, 44100, 16, 2, this.c);
            this.b = audioRecord;
            if (audioRecord.getState() == 1) {
                this.b.startRecording();
                new Thread(new a(this.c / 2)).start();
                Log.i("HWHealthSDK", "AudRec: Recording started!");
                return true;
            }
            Log.i("HWHealthSDK", "AudRec: Create failed!");
            if (this.b != null) {
                Log.i("HWHealthSDK", "AudRec: released");
                this.b.release();
                this.b = null;
            }
            return false;
        }

        public boolean b() {
            return this.d == 0;
        }

        public Boolean a() {
            return Boolean.valueOf(this.b == null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            AudioRecord audioRecord = this.b;
            if (audioRecord == null || audioRecord.getRecordingState() != 3) {
                return;
            }
            Log.i("HWHealthSDK", "AudRec: stopped");
            this.b.stop();
        }

        public b() {
            int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
            Log.i("HWHealthSDK", "AudRec: min buffer=" + minBufferSize);
            if (minBufferSize < 0) {
                Log.i("HWHealthSDK", "AudRec: Error to get min buffer!");
                this.c = 0;
            } else {
                this.c = 16384;
                if (16384 < minBufferSize) {
                    this.c = ((minBufferSize * 8) + 7) / 8;
                }
            }
        }
    }

    public static String MD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (str == null || "".equals(str)) {
                return "";
            }
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = messageDigest.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f1952a;
        private int d;
        private AudioManager e;
        private AudioTrack f;
        private int c = -1;
        private final short[] g = {0, 0, 16384, -16384, 26624, -26624, 31744, -31744, Short.MAX_VALUE, HealthData.INVALID_VALUES_SHORT, 31744, -31744, 26624, -26624, 16384, -16384, 0, 0, -16384, 16384, -26624, 26624, -31744, 31744, HealthData.INVALID_VALUES_SHORT, Short.MAX_VALUE, -31744, 31744, -26624, 26624, -16384, 16384};
        private final short[] h = {0, 0, 31744, -31744, Short.MAX_VALUE, HealthData.INVALID_VALUES_SHORT, 31744, -31744, 0, 0, -31744, 31744, HealthData.INVALID_VALUES_SHORT, Short.MAX_VALUE, -31744, 31744, 0, 0, 31744, -31744, Short.MAX_VALUE, HealthData.INVALID_VALUES_SHORT, 31744, -31744, 0, 0, -31744, 31744, HealthData.INVALID_VALUES_SHORT, Short.MAX_VALUE, -31744, 31744};
        private final short[] i = {0, 0, 0, 0, 0, 0, 0, 0};

        public void e() {
            AudioTrack audioTrack = this.f;
            if (audioTrack != null) {
                audioTrack.flush();
                this.f.stop();
                Log.i("HWHealthSDK", "AudPly: Stop playing!");
            }
        }

        public void d() {
            a(this.g, DnurseDeviceTest.this.l, DnurseDeviceTest.this.m);
        }

        public void c() {
            int i = DnurseDeviceTest.this.m + 4;
            if (DnurseDeviceTest.this.j == 1) {
                a(this.h, DnurseDeviceTest.this.l, i);
            } else {
                a(this.g, DnurseDeviceTest.this.l, i);
            }
        }

        public void b() {
            int i = this.c;
            if (i != -1) {
                this.e.setStreamVolume(3, i, 0);
                Log.i("HWHealthSDK", "AudPly: Vol change to " + this.c);
                this.c = -1;
            }
        }

        public boolean a() {
            int i;
            int streamVolume = this.e.getStreamVolume(3);
            Log.i("HWHealthSDK", "AudPly: setMaxVolume Old=" + streamVolume);
            if (this.c == -1) {
                this.c = streamVolume;
            }
            int streamMaxVolume = this.e.getStreamMaxVolume(3);
            this.e.setStreamVolume(3, streamMaxVolume, 0);
            if (DnurseDeviceTest.this.t) {
                if (streamMaxVolume != this.e.getStreamVolume(3)) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                i = streamMaxVolume;
                while (i > (streamMaxVolume * 2) / 3 && i != this.e.getStreamVolume(3)) {
                    i--;
                    this.e.setStreamVolume(3, i, 0);
                }
                if (Build.VERSION.RELEASE.compareTo("4.3") >= 0 && i < streamMaxVolume) {
                    this.e.setStreamVolume(3, streamMaxVolume, 9);
                }
                if (i != this.e.getStreamVolume(3)) {
                    return false;
                }
            } else {
                i = streamMaxVolume;
            }
            Log.i("HWHealthSDK", "AudPly: Vol change from " + streamVolume + " to " + i);
            this.f1952a = i == streamMaxVolume;
            return a(this.g, DnurseDeviceTest.this.k, 0);
        }

        public void a(boolean z) {
            int i = DnurseDeviceTest.this.m + DnurseDeviceTest.this.h[DnurseDeviceTest.this.i][5];
            if (z) {
                a(this.h, DnurseDeviceTest.this.l, i);
            } else {
                a(this.g, DnurseDeviceTest.this.l, i);
            }
        }

        private boolean a(short[] sArr, int i, int i2) {
            AudioTrack audioTrack = this.f;
            if (audioTrack == null) {
                return false;
            }
            int i3 = this.d;
            short[] sArr2 = this.i;
            int length = ((i3 / sArr2.length) / 2) + 2;
            if (audioTrack == null) {
                return true;
            }
            int length2 = (sArr.length * i2) + (sArr2.length * i) + (sArr2.length * length);
            short[] sArr3 = new short[length2];
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = 0;
                while (true) {
                    short[] sArr4 = this.i;
                    if (i6 < sArr4.length) {
                        sArr3[i4] = sArr4[i6];
                        i6++;
                        i4++;
                    }
                }
            }
            for (int i7 = 0; i7 < i2; i7++) {
                int i8 = 0;
                while (i8 < sArr.length) {
                    sArr3[i4] = sArr[i8];
                    i8++;
                    i4++;
                }
            }
            for (int i9 = 0; i9 < length; i9++) {
                int i10 = 0;
                while (true) {
                    short[] sArr5 = this.i;
                    if (i10 < sArr5.length) {
                        sArr3[i4] = sArr5[i10];
                        i10++;
                        i4++;
                    }
                }
            }
            this.f.flush();
            this.f.play();
            this.f.write(sArr3, 0, length2);
            if (!DnurseDeviceTest.this.u) {
                this.f.stop();
            }
            Log.i("HWHealthSDK", "AudPly: Play F:" + i + " B:" + length + " T:" + i2 + " finished!");
            return true;
        }

        a() {
            this.d = 0;
            this.e = (AudioManager) DnurseDeviceTest.this.I.getSystemService(PresenterUtils.AUDIO);
            this.d = AudioTrack.getMinBufferSize(4000, 12, 2);
            Log.i("HWHealthSDK", "AudPly: min buffer=" + this.d);
            AudioTrack audioTrack = new AudioTrack(3, 4000, 12, 2, this.d, 1);
            this.f = audioTrack;
            if (audioTrack.getState() == 1) {
                this.f.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
            } else {
                this.f.release();
                this.f = null;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x0263, code lost:
    
        if (android.os.Build.MODEL.equalsIgnoreCase("HTC T329t") == false) goto L111;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private DnurseDeviceTest(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 700
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.dnurse.sdk.DnurseDeviceTest.<init>(android.content.Context):void");
    }
}
