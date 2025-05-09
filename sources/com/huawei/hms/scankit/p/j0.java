package com.huawei.hms.scankit.p;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;
import com.huawei.hms.scankit.p.k0;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes9.dex */
public class j0 {

    /* renamed from: a, reason: collision with root package name */
    private e0 f5802a;
    private d b;
    private b c;
    private Camera.PreviewCallback d;
    private WeakReference<Context> e;
    private i0 f;
    private h0 g;
    private n0 h;
    private l0 i;
    private Camera j;
    private f0 k;
    private String l;
    private c m = c.CAMERA_CLOSED;
    private int n = -1;

    public interface b {
        void a(Point point);
    }

    public enum c {
        CAMERA_CLOSED(1),
        CAMERA_OPENED(2),
        CAMERA_INITIALED(3),
        PREVIEW_STARTED(4),
        PREVIEW_STOPPED(5);


        /* renamed from: a, reason: collision with root package name */
        private final int f5803a;

        c(int i) {
            this.f5803a = i;
        }

        public int a() {
            return this.f5803a;
        }
    }

    public interface d {
        void a();

        void b();

        void c();
    }

    public interface e {
        void a(byte[] bArr);
    }

    public j0(Context context, e0 e0Var) {
        if (context == null || e0Var == null) {
            throw new IllegalArgumentException("CameraManager constructor param invalid");
        }
        this.e = new WeakReference<>(context);
        this.f5802a = e0Var;
        this.l = e0Var.f();
        this.k = new f0();
        this.g = new h0();
        this.h = new n0();
        this.i = new l0();
    }

    public void a(e eVar) {
        synchronized (this) {
            if (eVar == null) {
                throw new IllegalArgumentException("CameraManager::setFrameCallback param invalid");
            }
            this.d = new f6(eVar);
        }
    }

    public g0 b() {
        synchronized (this) {
            if (this.j == null || this.m.a() == c.CAMERA_CLOSED.a()) {
                return null;
            }
            try {
                return this.g.a();
            } catch (Exception unused) {
                Log.e("CameraManager", "CameraManager::getCameraExposureData failed");
                return null;
            }
        }
    }

    public void c(int i) {
        synchronized (this) {
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                this.g.a(i);
            }
        }
    }

    public void d(int i) {
        synchronized (this) {
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                this.h.a(i);
            }
        }
    }

    public Point e() {
        Point a2;
        synchronized (this) {
            a2 = this.k.a();
        }
        return a2;
    }

    public c f() {
        c cVar;
        synchronized (this) {
            cVar = this.m;
        }
        return cVar;
    }

    public m0 g() {
        synchronized (this) {
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                return this.h.a();
            }
            return null;
        }
    }

    public String h() {
        String str;
        synchronized (this) {
            str = this.l;
        }
        return str;
    }

    public boolean i() {
        boolean z;
        synchronized (this) {
            if (this.j != null) {
                z = this.m.a() >= c.CAMERA_OPENED.a();
            }
        }
        return z;
    }

    public boolean j() {
        boolean b2;
        synchronized (this) {
            b2 = this.h.b();
        }
        return b2;
    }

    public void k() {
        synchronized (this) {
            this.c = null;
        }
    }

    public void l() {
        synchronized (this) {
            try {
                if (this.m.a() == c.PREVIEW_STARTED.a()) {
                    a();
                    q();
                    this.m = c.PREVIEW_STOPPED;
                }
                if (h().equals("torch")) {
                    a("off");
                }
                if (this.m.a() >= c.CAMERA_OPENED.a()) {
                    this.m = c.CAMERA_CLOSED;
                    Camera camera = this.j;
                    if (camera != null) {
                        camera.setPreviewCallback(null);
                        this.j.stopPreview();
                        this.j.release();
                        this.j = null;
                    }
                    d dVar = this.b;
                    if (dVar != null) {
                        dVar.c();
                    }
                }
            } catch (RuntimeException unused) {
                Log.e("CameraManager", "CameraManager::onPause failed");
            }
        }
    }

    public void m() {
        synchronized (this) {
            c cVar = this.m;
            if (cVar == c.CAMERA_CLOSED || cVar == c.PREVIEW_STOPPED) {
                int a2 = a(this.f5802a.b());
                Log.i("CameraManager", "onResume: " + a2);
                try {
                    this.j = Camera.open(a2);
                } catch (RuntimeException e2) {
                    Log.e("CameraManager", "CameraManager::Camera open failed, " + e2.getMessage());
                }
                if (this.j == null) {
                    Log.e("CameraManager", "CameraManager::initCamera failed");
                    d dVar = this.b;
                    if (dVar != null) {
                        dVar.b();
                    }
                } else {
                    d dVar2 = this.b;
                    if (dVar2 != null) {
                        dVar2.a();
                    }
                    this.m = c.CAMERA_OPENED;
                }
            }
        }
    }

    public void n() {
        Camera camera;
        synchronized (this) {
            if (this.m.a() < c.CAMERA_OPENED.a()) {
                return;
            }
            if (this.f5802a.c() != 0 && (camera = this.j) != null) {
                camera.setPreviewCallback(new f());
            }
        }
    }

    public void o() {
        synchronized (this) {
            if (this.f5802a.c() == 1) {
                Log.d("CameraManager", "CameraManager::requestPreviewFrame PREVIEW_ONE_SHOT");
                if (this.m == c.PREVIEW_STOPPED) {
                    return;
                }
                Camera camera = this.j;
                if (camera != null) {
                    camera.setOneShotPreviewCallback(this.d);
                }
            } else if (this.f5802a.c() == 0) {
                Log.d("CameraManager", "CameraManager::requestPreviewFrame PICTURE_MODE");
                if (this.m == c.PREVIEW_STOPPED) {
                    p();
                }
            } else if (this.f5802a.c() == 2) {
                Log.d("CameraManager", "CameraManager::requestPreviewFrame PREVIEW_MULTI_SHOT");
                if (this.m == c.PREVIEW_STOPPED) {
                    return;
                }
                Camera camera2 = this.j;
                if (camera2 != null) {
                    camera2.setPreviewCallback(this.d);
                }
            } else {
                Log.w("CameraManager", "CameraManager::requestPreviewFrame unknown mode");
            }
        }
    }

    public void p() {
        synchronized (this) {
            try {
            } catch (RuntimeException unused) {
                Log.w("CameraManager", "stopPreview error");
            }
            if (this.m.a() < c.CAMERA_INITIALED.a()) {
                Log.w("CameraManager", "CameraManager::startPreview camera is not initialed yet");
                return;
            }
            Camera camera = this.j;
            if (camera != null) {
                camera.startPreview();
                this.m = c.PREVIEW_STARTED;
            }
        }
    }

    public void q() {
        synchronized (this) {
            try {
            } catch (RuntimeException unused) {
                Log.w("CameraManager", "stopPreview error");
            }
            if (this.m.a() < c.PREVIEW_STARTED.a()) {
                Log.w("CameraManager", "CameraManager::startPreview camera is not startPreview yet");
                return;
            }
            Camera camera = this.j;
            if (camera != null) {
                camera.setPreviewCallback(null);
                this.j.stopPreview();
                this.m = c.PREVIEW_STOPPED;
            }
        }
    }

    public void a(d dVar) {
        synchronized (this) {
            if (dVar != null) {
                this.b = dVar;
            } else {
                throw new IllegalArgumentException("CameraManager::setCameraStatusListener param invalid");
            }
        }
    }

    public void a(TextureView textureView) throws IOException {
        synchronized (this) {
            if (textureView != null) {
                if (this.m.a() != c.CAMERA_OPENED.a()) {
                    Log.w("CameraManager", "CameraManager::initCamera camera is not opened yet");
                    m();
                }
                this.g.a(this.j);
                this.h.a(this.j);
                this.i.a(this.j);
                Camera camera = this.j;
                if (camera != null) {
                    camera.setPreviewTexture(textureView.getSurfaceTexture());
                }
                this.k.a(this.j, this.f5802a);
                Camera camera2 = this.j;
                if (camera2 != null) {
                    camera2.setDisplayOrientation(this.f5802a.d());
                }
                b bVar = this.c;
                if (bVar != null) {
                    bVar.a(this.k.a());
                }
                this.m = c.CAMERA_INITIALED;
            } else {
                throw new IllegalArgumentException("CameraManager::initCamera SurfaceHolder is null");
            }
        }
    }

    public k0 c() {
        synchronized (this) {
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                return this.i.a();
            }
            return null;
        }
    }

    public int d() {
        int d2;
        synchronized (this) {
            d2 = this.f5802a.d();
        }
        return d2;
    }

    public void b(int i) {
        synchronized (this) {
            if (this.f5802a != null && this.j != null && this.m.a() >= c.CAMERA_OPENED.a()) {
                this.f5802a.a(i);
                try {
                    try {
                        this.j.setDisplayOrientation(i);
                    } catch (RuntimeException unused) {
                        Log.e("CameraManager", "setDisplayOrientation RuntimeException");
                    }
                } catch (Exception unused2) {
                    Log.e("CameraManager", "setDisplayOrientation Exception");
                }
            }
        }
    }

    public void a(String str) {
        synchronized (this) {
            try {
            } catch (RuntimeException unused) {
                Log.w("CameraManager", "CameraManager::setTorchStatus error");
            }
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                if ("off".equals(str) || "torch".equals(str)) {
                    Camera.Parameters parameters = this.j.getParameters();
                    parameters.setFlashMode(str);
                    this.j.setParameters(parameters);
                    this.l = str;
                }
            }
        }
    }

    public void a(Rect rect, boolean z) {
        synchronized (this) {
            if (this.j == null) {
                return;
            }
            if (this.f == null) {
                this.f = new i0(this.j);
            }
            this.f.a(rect, this.k.a().x, this.k.a().y, z, this.f5802a.b() == 1);
        }
    }

    public void a() {
        synchronized (this) {
            i0 i0Var = this.f;
            if (i0Var != null) {
                i0Var.e();
                this.f = null;
            }
        }
    }

    public void a(List<k0.a> list) {
        synchronized (this) {
            if (this.j != null && this.m.a() != c.CAMERA_CLOSED.a()) {
                this.i.a(list);
            }
        }
    }

    private int a(int i) {
        if (i != 0 && i != 1) {
            return 0;
        }
        try {
            int numberOfCameras = Camera.getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            for (int i2 = 0; i2 < numberOfCameras; i2++) {
                Camera.getCameraInfo(i2, cameraInfo);
                if (cameraInfo.facing == i) {
                    Log.i("CameraManager", "findCameraId: " + i2);
                    return i2;
                }
            }
        } catch (RuntimeException unused) {
            Log.e("CameraManager", "getCameraInfo RuntimeException");
        } catch (Exception unused2) {
            Log.e("CameraManager", "getCameraInfo Exception");
        }
        return 0;
    }

    static class f implements Camera.PreviewCallback {
        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
        }

        private f() {
        }
    }
}
