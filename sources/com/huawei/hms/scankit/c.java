package com.huawei.hms.scankit;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.p.g0;
import com.huawei.hms.scankit.p.j0;
import com.huawei.hms.scankit.p.k0;
import com.huawei.hms.scankit.p.l1;
import com.huawei.hms.scankit.p.m0;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.s6;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.y6;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
final class c extends Handler {
    private static a j;
    private static long k;

    /* renamed from: a, reason: collision with root package name */
    private final Context f5708a;
    private final j0 b;
    private final com.huawei.hms.scankit.a c;
    private boolean d = true;
    private int e = 50;
    private Rect f;
    private int g;
    private IRemoteFrameDecoderDelegate h;
    private boolean i;

    static class a extends AsyncTask<Object, Object, Object> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<c> f5709a;
        private List<k0.a> e;
        private List<k0.a> f;
        private boolean b = true;
        private boolean c = false;
        private int d = 0;
        private int g = 0;
        private int h = 0;

        public a(c cVar) {
            this.f5709a = new WeakReference<>(cVar);
        }

        public void b(int i) {
            this.h = i;
            c cVar = this.f5709a.get();
            if (cVar != null) {
                try {
                    cVar.a(this.h, this.f);
                    o4.d("DecodeHandler", "ScanCode handle global value" + this.h);
                } catch (RuntimeException e) {
                    o4.b("DecodeHandler", "RuntimeException: " + e.getMessage());
                } catch (Exception unused) {
                    o4.b("DecodeHandler", "Exception");
                }
            }
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            Log.i("ScankitDecode", "doInBackground: ");
            if (c.j.isCancelled()) {
                return null;
            }
            while (!this.c) {
                if (this.b) {
                    try {
                        Thread.sleep(400L);
                    } catch (InterruptedException unused) {
                        o4.d("ScankitDecode", "doInBackground  get InterruptedException  error!!!");
                    }
                    this.b = false;
                } else {
                    c cVar = this.f5709a.get();
                    int i = this.g;
                    if (i == 0) {
                        this.b = true;
                    } else if (cVar != null) {
                        try {
                            cVar.a(this.d / i, this.e);
                            o4.d("DecodeHandler", "ScanCode handle auto value" + (this.d / this.g));
                            a();
                            this.b = true;
                        } catch (RuntimeException e) {
                            o4.b("DecodeHandler", "RuntimeException: " + e.getMessage());
                        } catch (Exception unused2) {
                            o4.b("DecodeHandler", "Exception");
                        }
                    }
                }
            }
            return null;
        }

        public void a(int i) {
            this.d += i;
            this.g++;
        }

        public void a(List<Rect> list, int i, int i2, boolean z) {
            if (list == null) {
                o4.a("ScankitDecode", "areas is null");
                return;
            }
            if (list.size() == 0) {
                this.f = Collections.singletonList(new k0.a(new Rect(-100, -100, 100, 100), 1000));
                return;
            }
            this.f = new ArrayList();
            for (Rect rect : list) {
                int centerX = ((rect.centerX() * 2000) / i) - 1000;
                int centerY = ((rect.centerY() * 2000) / i2) - 1000;
                int width = ((rect.width() * 2000) / i) / 2;
                int height = ((rect.height() * 2000) / i2) / 2;
                this.f.add(new k0.a(new Rect(centerX - (width / 2), centerY - (height / 2), centerX + width, centerY + height), 1000 / list.size()));
            }
            list.clear();
        }

        public void b(List<Rect> list, int i, int i2, boolean z) {
            if (list == null) {
                o4.a("ScankitDecode", "areas is null");
                return;
            }
            if (list.size() == 0) {
                this.e = Collections.singletonList(new k0.a(new Rect(-100, -100, 100, 100), 1000));
                return;
            }
            this.e = new ArrayList();
            if (z) {
                int i3 = i2 > i ? i2 - i : i - i2;
                for (Rect rect : list) {
                    int centerY = (((rect.centerY() + (i3 >> 1)) * 2000) / i) - 1000;
                    int centerX = ((rect.centerX() * 2000) / i2) - 1000;
                    int height = ((rect.height() * 2000) / i) / 2;
                    int width = ((rect.width() * 2000) / i2) / 2;
                    this.e.add(new k0.a(new Rect(centerY - (height / 2), centerX - (width / 2), centerY + height, centerX + width), 1000 / list.size()));
                }
                return;
            }
            for (Rect rect2 : list) {
                int centerX2 = ((rect2.centerX() * 2000) / i) - 1000;
                int centerY2 = ((rect2.centerY() * 2000) / i2) - 1000;
                int width2 = ((rect2.width() * 2000) / i) / 2;
                int height2 = ((rect2.height() * 2000) / i2) / 2;
                this.e.add(new k0.a(new Rect(centerX2 - (width2 / 2), centerY2 - (height2 / 2), centerX2 + width2, centerY2 + height2), 1000 / list.size()));
            }
            list.clear();
        }

        private void a() {
            this.d = 0;
            this.g = 0;
        }
    }

    c(Context context, j0 j0Var, com.huawei.hms.scankit.a aVar, Map<l1, Object> map, Rect rect, boolean z) {
        this.i = false;
        this.f5708a = context;
        this.b = j0Var;
        this.c = aVar;
        this.f = rect;
        if (j == null) {
            a aVar2 = new a(this);
            j = aVar2;
            aVar2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        }
        this.g = 0;
        this.i = z;
        a(context);
    }

    private boolean d() {
        Context context = this.f5708a;
        if (context == null) {
            return true;
        }
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            o4.d("ScankitDecode", "isScreenPortrait  getSystemService  WINDOW_SERVICE  error!!!");
            return true;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x < point.y;
    }

    public boolean b(float f) {
        boolean z;
        com.huawei.hms.scankit.a aVar = this.c;
        if (aVar != null && aVar.a()) {
            return false;
        }
        try {
            m0 g = this.b.g();
            if (g == null) {
                o4.d("ScankitDecode", "Zoom not supported,data is null");
                return false;
            }
            int c = g.c();
            int b = g.b();
            float intValue = ((r1.get(b).intValue() * 1.0f) / 100.0f) * f;
            if (((int) (intValue * 100.0f)) > g.a().get(c).intValue()) {
                intValue = (c * 1.0f) / 100.0f;
            }
            if (!this.b.j()) {
                o4.d("ScankitDecode", "Zoom not supported");
                return false;
            }
            int a2 = a(intValue);
            if (a2 > b) {
                this.b.d(a2);
                z = true;
            } else {
                this.b.d(b);
                z = false;
            }
            this.b.a(Collections.singletonList(new k0.a(new Rect(-150, -150, 150, 150), 1000)));
            return z;
        } catch (RuntimeException unused) {
            o4.b("ScankitDecode", "Zoom not supported,RuntimeException happen");
            return false;
        } catch (Exception unused2) {
            o4.b("ScankitDecode", "Zoom not supported,Exception happen");
            return false;
        }
    }

    public float c() {
        if (b() == null) {
            return 1.0f;
        }
        return Math.round(r0.get(r0.size() - 1).intValue() / 100.0f);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null || !this.d) {
            return;
        }
        int i = message.what;
        if (i == R.id.scankit_decode) {
            int i2 = this.g;
            if (i2 <= 1) {
                this.g = i2 + 1;
                this.c.sendEmptyMessage(R.id.scankit_decode_failed);
                return;
            } else {
                Object obj = message.obj;
                if (obj instanceof byte[]) {
                    a((byte[]) obj, d());
                    return;
                }
                return;
            }
        }
        if (i != R.id.scankit_quit) {
            o4.d("ScankitDecode", "handleMessage  message.what:" + message.what);
        } else {
            this.d = false;
            a aVar = j;
            if (aVar != null) {
                aVar.c = true;
                j.cancel(true);
            }
            Looper.myLooper().quit();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0056 A[Catch: RemoteException -> 0x0061, TryCatch #0 {RemoteException -> 0x0061, blocks: (B:7:0x0045, B:9:0x0049, B:12:0x0056, B:14:0x005a), top: B:6:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0049 A[Catch: RemoteException -> 0x0061, TryCatch #0 {RemoteException -> 0x0061, blocks: (B:7:0x0045, B:9:0x0049, B:12:0x0056, B:14:0x005a), top: B:6:0x0045 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = "ScankitDecode"
            boolean r1 = r3.i     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            if (r1 == 0) goto L12
            java.lang.String r4 = "use local decoder"
            android.util.Log.d(r0, r4)     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            java.lang.Class<com.huawei.hms.scankit.DecoderCreator> r4 = com.huawei.hms.scankit.DecoderCreator.class
            java.lang.Object r4 = r4.newInstance()     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            goto L45
        L12:
            java.lang.String r1 = "use remote decoder"
            android.util.Log.d(r0, r1)     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            android.content.Context r4 = com.huawei.hms.hmsscankit.g.e(r4)     // Catch: java.lang.Throwable -> L1b java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
        L1b:
            java.lang.ClassLoader r1 = r4.getClassLoader()     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            java.lang.String r2 = "com.huawei.hms.scankit.DecoderCreator"
            java.lang.Class r1 = r1.loadClass(r2)     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            java.lang.String r2 = "com.huawei.hms.scankit.aiscan.common.BarcodeFormat"
            r4.loadClass(r2)     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            java.lang.Object r4 = r1.newInstance()     // Catch: java.lang.InstantiationException -> L33 java.lang.ClassNotFoundException -> L39 java.lang.IllegalAccessException -> L3f
            goto L45
        L33:
            java.lang.String r4 = "InstantiationException"
            com.huawei.hms.scankit.p.o4.a(r0, r4)
            goto L44
        L39:
            java.lang.String r4 = "ClassNotFoundException"
            com.huawei.hms.scankit.p.o4.a(r0, r4)
            goto L44
        L3f:
            java.lang.String r4 = "IllegalAccessException"
            com.huawei.hms.scankit.p.o4.a(r0, r4)
        L44:
            r4 = 0
        L45:
            boolean r1 = r4 instanceof android.os.IBinder     // Catch: android.os.RemoteException -> L61
            if (r1 == 0) goto L56
            android.os.IBinder r4 = (android.os.IBinder) r4     // Catch: android.os.RemoteException -> L61
            com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator r4 = com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator.Stub.asInterface(r4)     // Catch: android.os.RemoteException -> L61
            com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate r4 = r4.newRemoteFrameDecoderDelegate()     // Catch: android.os.RemoteException -> L61
            r3.h = r4     // Catch: android.os.RemoteException -> L61
            return
        L56:
            com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate r4 = r3.h     // Catch: android.os.RemoteException -> L61
            if (r4 != 0) goto L66
            com.huawei.hms.scankit.p.h4 r4 = com.huawei.hms.scankit.p.h4.a()     // Catch: android.os.RemoteException -> L61
            r3.h = r4     // Catch: android.os.RemoteException -> L61
            goto L66
        L61:
            java.lang.String r4 = "RemoteException"
            com.huawei.hms.scankit.p.o4.a(r0, r4)
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.c.a(android.content.Context):void");
    }

    public List<Integer> b() {
        return this.b.g().a();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(byte[] r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.c.a(byte[], boolean):void");
    }

    private boolean a(float f, s6[] s6VarArr, w3.c cVar) {
        if (!b(f)) {
            return false;
        }
        Message obtain = Message.obtain();
        obtain.what = R.id.scankit_decode_succeeded;
        HmsScan[] a2 = y6.a(s6VarArr);
        obtain.obj = a2;
        if (b.J != null) {
            b.J.a(a2, cVar);
        }
        this.c.sendMessage(obtain);
        return true;
    }

    private void a(s6[] s6VarArr, byte[] bArr, int i, int i2, w3.c cVar) {
        if (this.c != null) {
            Message obtain = Message.obtain(this.c, R.id.scankit_decode_succeeded, y6.a(s6VarArr));
            Log.d("ScankitDecode", "scankit decode succeed msg SCAN_MODE: FULLSDK VERSION_CODE: 21200301 VERSION_NAME: 2.12.0.301");
            try {
                if (b.I != null) {
                    b.I.a(s6VarArr[0].e(), s6VarArr[0].b(), s6VarArr[0].m());
                }
            } catch (Exception unused) {
                Log.d("ScankitDecode", "ha is null");
            }
            if (this.c.c()) {
                Bundle bundle = new Bundle();
                a(bArr, i, i2, bundle);
                obtain.setData(bundle);
            }
            obtain.sendToTarget();
        }
    }

    private static void a(byte[] bArr, int i, int i2, Bundle bundle) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", 1.0f);
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
            Log.e("ScankitDecode", "RemoteException");
        }
    }

    public void a(Rect rect, boolean z) {
        com.huawei.hms.scankit.a aVar = this.c;
        if (aVar == null || !aVar.a()) {
            this.b.a(rect, z);
        }
    }

    public void a(int i, List<k0.a> list) {
        g0 b = this.b.b();
        int b2 = b.b();
        int c = b.c();
        int a2 = b.a();
        if (i == 0) {
            return;
        }
        int i2 = a2 + i;
        if (i2 <= b2) {
            b2 = i2 < c ? c : i2;
        }
        this.b.c(b2);
        k0 c2 = this.b.c();
        Rect b3 = c2.b();
        if (c2.a() > 0) {
            if (c2.a() == 1) {
                int centerX = b3.centerX();
                int centerY = b3.centerY();
                if (Math.sqrt(((centerX - list.get(0).f5814a.centerX()) * (centerX - list.get(0).f5814a.centerX())) + (centerY - list.get(0).f5814a.centerY()) + (centerY - list.get(0).f5814a.centerY())) > this.e) {
                    list.set(0, new k0.a(list.get(0).f5814a, 1000));
                    this.b.a(list.subList(0, 1));
                    return;
                }
                return;
            }
            this.b.a(list);
        }
    }

    public int a(float f) {
        List<Integer> b = b();
        if (b == null) {
            return -3;
        }
        if (b.size() <= 0) {
            return -4;
        }
        if (Math.abs(f - 1.0f) < 1.0E-6f) {
            return 0;
        }
        if (f == c()) {
            return b.size() - 1;
        }
        for (int i = 1; i < b.size(); i++) {
            float f2 = 100.0f * f;
            if (b.get(i).intValue() >= f2 && b.get(i - 1).intValue() <= f2) {
                return i;
            }
        }
        return -1;
    }
}
