package com.huawei.hms.scankit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.api.IOnErrorCallback;
import com.huawei.hms.hmsscankit.api.IOnResultCallback;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.p.e0;
import com.huawei.hms.scankit.p.e5;
import com.huawei.hms.scankit.p.f5;
import com.huawei.hms.scankit.p.j0;
import com.huawei.hms.scankit.p.k0;
import com.huawei.hms.scankit.p.l1;
import com.huawei.hms.scankit.p.m0;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.v3;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.w7;
import com.huawei.watchface.videoedit.gles.Constant;
import com.tencent.open.apireq.BaseResp;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes9.dex */
public class b {
    public static final String H = "b";
    public static volatile v3 I;
    public static volatile w3 J;
    private IOnErrorCallback D;

    /* renamed from: a, reason: collision with root package name */
    private final Rect f5703a;
    private final int b;
    private final boolean c;
    private Context d;
    private com.huawei.hms.scankit.a e;
    private f5 f;
    private j0 g;
    private ViewfinderView h;
    public TextureView i;
    private TextureView.SurfaceTextureListener j;
    private Collection<BarcodeFormat> k;
    private Map<l1, ?> l;
    private String m;
    private String o;
    private float q;
    private boolean u;
    private boolean v;
    private boolean w;
    private IObjectWrapper x;
    private e5 y;
    private IOnResultCallback z;
    private boolean p = true;
    private boolean r = true;
    private boolean s = false;
    private boolean t = true;
    private boolean A = false;
    private boolean B = false;
    private boolean E = true;
    private boolean F = false;
    private boolean G = false;
    private boolean n = false;
    private boolean C = false;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.g.m();
        }
    }

    /* renamed from: com.huawei.hms.scankit.b$b, reason: collision with other inner class name */
    class C0151b implements j0.d {
        C0151b() {
        }

        @Override // com.huawei.hms.scankit.p.j0.d
        public void a() {
        }

        @Override // com.huawei.hms.scankit.p.j0.d
        public void b() {
            if (b.this.D != null) {
                try {
                    b.this.D.onError(-1000);
                } catch (RemoteException unused) {
                    o4.b(b.H, "RemoteException");
                }
            }
        }

        @Override // com.huawei.hms.scankit.p.j0.d
        public void c() {
        }
    }

    class c implements TextureView.SurfaceTextureListener {
        c() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            b.this.B = false;
            if (surfaceTexture == null) {
                o4.b(b.H, "*** WARNING *** surfaceCreated() gave us a null surface!");
            }
            if (b.this.n) {
                return;
            }
            b.this.n = true;
            if (b.this.d.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid()) == 0) {
                b bVar = b.this;
                bVar.a(bVar.i);
            } else {
                if (b.this.G || !(b.this.d instanceof Activity)) {
                    return;
                }
                b.this.B = true;
                ((Activity) b.this.d).requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            b.this.n = false;
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    class d implements f5 {
        d() {
        }

        @Override // com.huawei.hms.scankit.p.f5
        public void a(HmsScan[] hmsScanArr, Bitmap bitmap, float f) {
            b.this.a(hmsScanArr, bitmap);
        }
    }

    public b(Context context, TextureView textureView, ViewfinderView viewfinderView, Rect rect, int i, IObjectWrapper iObjectWrapper, boolean z, String str, boolean z2) {
        this.d = context;
        this.h = viewfinderView;
        this.x = iObjectWrapper;
        this.i = textureView;
        this.f5703a = rect;
        this.b = i;
        this.c = z;
        this.o = str;
        this.v = z2;
    }

    private void j() {
        com.huawei.hms.scankit.a aVar = this.e;
        if (aVar != null) {
            aVar.e();
            this.e = null;
        }
        this.g.l();
    }

    public void f() {
        TextureView textureView;
        TextureView textureView2;
        this.C = false;
        try {
            I = new v3((Bundle) ObjectWrapper.unwrap(this.x), this.o);
            I.h();
        } catch (RuntimeException unused) {
            o4.b(H, "RuntimeException");
        } catch (Exception unused2) {
            o4.b(H, "Exception");
        }
        if (!this.A && !this.n && (textureView2 = this.i) != null) {
            textureView2.setSurfaceTextureListener(this.j);
            if (this.n) {
                a(this.i);
            } else {
                this.i.setSurfaceTextureListener(this.j);
            }
        }
        if (this.B && this.d.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid()) == 0 && (textureView = this.i) != null) {
            this.B = false;
            a(textureView);
        }
    }

    public void g() {
        this.C = false;
        TextureView textureView = this.i;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(this.j);
            this.A = true;
            if (this.n) {
                a(this.i);
            } else {
                this.i.setSurfaceTextureListener(this.j);
            }
        }
    }

    public void h() {
        this.C = true;
        if (I != null) {
            I.i();
        }
        I = null;
        if (this.A) {
            j();
        }
    }

    public void i() {
        try {
            j0 j0Var = this.g;
            if (j0Var != null) {
                j0Var.d(1);
            }
        } catch (RuntimeException unused) {
            o4.b(H, "RuntimeException in reset zoomValue");
        } catch (Exception unused2) {
            o4.b(H, "Exception in reset zoomValue");
        }
    }

    public void c() {
        this.C = false;
        try {
            J = new w3((Bundle) ObjectWrapper.unwrap(this.x), this.o);
            J.a("single");
        } catch (RuntimeException unused) {
            o4.b(H, "RuntimeException");
        } catch (Exception unused2) {
            o4.b(H, "Exception");
        }
        if (this.d.getPackageManager() != null && !this.d.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            Log.e("scankit", "has no camera");
            return;
        }
        e0 a2 = a(this.d);
        Log.i(H, "onCreate: CameraManageOncreate");
        this.g = new j0(this.d, a2);
        new Thread(new a()).start();
        this.g.a(new C0151b());
        this.j = new c();
        this.f = new d();
    }

    public void d() {
        this.C = true;
        this.i.setSurfaceTextureListener(null);
        this.g.k();
        J.l.b();
        J = null;
    }

    public void e() {
        this.C = true;
        if (this.A) {
            return;
        }
        j();
    }

    public boolean b(MotionEvent motionEvent) {
        j0 j0Var = this.g;
        if (j0Var == null || !this.p || j0Var.f().a() < j0.c.CAMERA_OPENED.a() || motionEvent.getPointerCount() <= 1) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 2) {
            float a2 = a(motionEvent);
            float f = this.q;
            if (a2 > f + 6.0f) {
                a(true, this.g);
            } else if (a2 < f - 6.0f) {
                a(false, this.g);
            } else {
                o4.d("CaptureHelper", "MotionEvent.ACTION_MOVE no handleZoom");
            }
            this.q = a2;
        } else if (action == 5) {
            this.q = a(motionEvent);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextureView textureView) {
        if (textureView != null && textureView.getSurfaceTexture() != null) {
            try {
                this.g.a(textureView);
                this.g.a(Collections.singletonList(new k0.a(new Rect(-150, -150, 150, 150), 1000)));
                try {
                    this.g.n();
                } catch (Exception unused) {
                    o4.b(H, "initCamera() get exception");
                }
                if (this.e == null) {
                    com.huawei.hms.scankit.a aVar = new com.huawei.hms.scankit.a(this.d, this.h, this.f, this.k, this.l, this.m, this.g, this.f5703a, this.b, this.v, this.E);
                    this.e = aVar;
                    aVar.c(this.u);
                    this.e.a(this.w);
                    this.e.b(this.r);
                    this.e.a(this.y);
                    return;
                }
                return;
            } catch (Exception e) {
                if (I != null) {
                    I.c(BaseResp.CODE_UNSUPPORTED_BRANCH);
                }
                o4.a(H, "initCamera IOException", e);
                return;
            }
        }
        o4.e(H, "initCamera() no surface view");
    }

    public void b(boolean z) {
        this.E = z;
    }

    public boolean b() {
        return this.F;
    }

    private void a(boolean z, j0 j0Var) {
        try {
            m0 g = j0Var.g();
            if (j0Var.j()) {
                int c2 = g.c();
                int b = g.b();
                if (z && b < c2) {
                    b++;
                } else if (b > 0) {
                    b--;
                } else {
                    o4.d(H, "handleZoom  zoom not change");
                }
                j0Var.d(b);
                return;
            }
            o4.d(H, "zoom not supported");
        } catch (RuntimeException unused) {
            Log.e(H, "handleZoom: RuntimeException");
        }
    }

    private float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        double sqrt = Math.sqrt((x * x) + (y * y));
        if (Double.isInfinite(sqrt) || Double.isNaN(sqrt)) {
            return 0.0f;
        }
        return BigDecimal.valueOf(sqrt).floatValue();
    }

    public void a(HmsScan[] hmsScanArr, Bitmap bitmap) {
        o4.a("scan-time", "decode time:" + System.currentTimeMillis());
        try {
            String str = H;
            o4.d(str, "result onResult");
            if (this.y.a()) {
                o4.d(str, "result intercepted");
                return;
            }
            if (I != null) {
                I.a(hmsScanArr);
            }
            if (!this.c) {
                hmsScanArr = w7.a(hmsScanArr);
            }
            if (this.y != null) {
                if (this.h != null && hmsScanArr.length > 0 && hmsScanArr[0] != null) {
                    o4.d(str, "result draw result point");
                    if (this.d instanceof Activity) {
                        this.h.a(hmsScanArr[0].getBorderRect(), w7.c((Activity) this.d), this.g.e());
                    }
                    this.C = false;
                }
                this.y.a(hmsScanArr);
            }
            if (this.z != null) {
                try {
                    o4.d(str, "result callback end: pauseStatus" + this.C);
                    if (this.C) {
                        return;
                    }
                    if (this.w && hmsScanArr != null && hmsScanArr.length > 0 && hmsScanArr[0] != null) {
                        Context context = this.d;
                        if (context instanceof Activity) {
                            hmsScanArr[0].originalBitmap = w7.a(bitmap, ((Activity) context).getWindowManager().getDefaultDisplay().getRotation());
                        }
                    }
                    this.z.onResult(hmsScanArr);
                } catch (RemoteException e) {
                    if (I != null) {
                        I.c(BaseResp.CODE_PERMISSION_NOT_GRANTED);
                    }
                    o4.e("CaptureHelper", "onResult  RemoteException  e:" + e);
                }
            }
        } catch (RuntimeException e2) {
            Log.e(H, "onResult:RuntimeException " + e2);
        } catch (Exception e3) {
            Log.e(H, "onResult:Exception: " + e3);
        }
    }

    public void c(boolean z) {
        this.G = z;
    }

    public b a(boolean z) {
        this.w = z;
        com.huawei.hms.scankit.a aVar = this.e;
        if (aVar != null) {
            aVar.a(z);
        }
        return this;
    }

    public b a(e5 e5Var) {
        this.y = e5Var;
        return this;
    }

    public void a(IOnResultCallback iOnResultCallback) {
        this.z = iOnResultCallback;
    }

    public j0 a() {
        return this.g;
    }

    public void a(IOnErrorCallback iOnErrorCallback) {
        this.D = iOnErrorCallback;
    }

    private e0 a(Context context) {
        e0 a2;
        Activity activity = (Activity) context;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Log.i(H, "initCameraConfig:falserotation" + rotation);
        if (rotation == 0) {
            a2 = new e0.b().a(new Point(Constant.FBO_HEIGHT, 1080)).a(1).b(90).b(false).a(true).a();
        } else if (rotation == 1) {
            a2 = new e0.b().a(new Point(Constant.FBO_HEIGHT, 1080)).a(1).b(0).b(false).a(true).a();
        } else if (rotation == 2) {
            a2 = new e0.b().a(new Point(Constant.FBO_HEIGHT, 1080)).a(1).b(270).b(false).a(true).a();
        } else if (rotation != 3) {
            a2 = new e0.b().a(new Point(Constant.FBO_HEIGHT, 1080)).a(1).b(90).b(false).a(true).a();
        } else {
            a2 = new e0.b().a(new Point(Constant.FBO_HEIGHT, 1080)).a(1).b(180).b(false).a(true).a();
        }
        if (w7.f(context) || w7.b(activity) || w7.e(context)) {
            a2.a(new Point(1080, 1080));
            this.F = true;
        }
        if ("ceres-c3".equals(Build.DEVICE)) {
            a2 = new e0.b().a(new Point(1080, Constant.FBO_HEIGHT)).a(1).b(false).a(true).a();
        }
        boolean b = w7.b();
        boolean e = w7.e();
        if ((!w7.e(context) || b) && (!w7.b(activity) || e)) {
            return a2;
        }
        e0 a3 = new e0.b().a(new Point(1080, 1080)).a(1).b(90).b(false).a(true).a();
        this.F = true;
        return a3;
    }
}
