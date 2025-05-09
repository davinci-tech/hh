package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.Image;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.WindowManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.CameraGlView;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.filter.CameraFilter;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.filter.ScreenFilter;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes4.dex */
public class hly implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener, Camera2Helper.OnPreviewSizeListener, Camera2Helper.OnPreviewListener {
    private Camera2Helper b;
    private CameraFilter c;
    private final CameraGlView e;
    private Context f;
    private int g;
    private int h;
    private Camera2Helper.OnPreviewListener k;
    private ScreenFilter n;
    private SurfaceTexture o;
    private int q;
    private int[] r;
    private int t;
    private float[] m = new float[16];
    private float[] i = new float[16];

    /* renamed from: a, reason: collision with root package name */
    private boolean f13243a = false;
    private boolean d = true;
    private float l = 1.0f;
    private boolean j = false;

    public hly(CameraGlView cameraGlView) {
        this.e = cameraGlView;
        this.f = cameraGlView.getContext();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Context context = this.f;
        if (context instanceof Activity) {
            this.b = new Camera2Helper((Activity) context);
        } else {
            this.b = new Camera2Helper(BaseApplication.wa_());
        }
        int[] iArr = new int[1];
        this.r = iArr;
        GLES20.glGenTextures(1, iArr, 0);
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.r[0]);
        this.o = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.c = new CameraFilter(this.e.getContext());
        this.n = new ScreenFilter(this.e.getContext());
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.b.d(this);
        this.b.e(this);
        this.b.b(this.l);
        this.b.e(this.j);
        this.b.bke_(i, i2, this.o, this.f13243a, this.d);
        this.q = i2;
        this.t = i;
        d();
        this.c.e(this.i);
        this.c.e(i, i2, 0, 0);
        this.n.e(i, i2, 0, 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        this.o.updateTexImage();
        this.o.getTransformMatrix(this.m);
        this.c.b(this.m);
        this.n.d(this.c.d(this.r[0]));
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.e.requestRender();
    }

    public void a() {
        Camera2Helper camera2Helper = this.b;
        if (camera2Helper != null) {
            camera2Helper.b();
            this.b.d(null);
        }
        CameraFilter cameraFilter = this.c;
        if (cameraFilter != null) {
            cameraFilter.d();
        }
        ScreenFilter screenFilter = this.n;
        if (screenFilter != null) {
            screenFilter.d();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.OnPreviewSizeListener
    public void onSize(int i, int i2) {
        LogUtil.h("Track_GlRenderWrapper", "camera preview size. with:", Integer.valueOf(i), " height:", Integer.valueOf(i2));
        this.g = i;
        this.h = i2;
    }

    private void d() {
        float min;
        float f;
        Matrix.setIdentityM(this.i, 0);
        double min2 = (Math.min(this.t, this.q) * 1.0f) / Math.max(this.t, this.q);
        if ((Math.min(this.h, this.g) * 1.0f) / Math.max(this.h, this.g) < min2) {
            f = (float) ((Math.max(this.h, this.g) * 1.0f) / (Math.min(this.h, this.g) / min2));
            min = 1.0f;
        } else {
            min = (float) ((Math.min(this.h, this.g) * 1.0f) / (Math.max(this.h, this.g) * min2));
            f = 1.0f;
        }
        Matrix.scaleM(this.i, 0, min, f, 1.0f);
        int rotation = ((WindowManager) this.e.getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            Matrix.rotateM(this.i, 0, -90.0f, 0.0f, 0.0f, 1.0f);
        } else if (rotation == 2) {
            Matrix.rotateM(this.i, 0, -180.0f, 0.0f, 0.0f, 1.0f);
        } else {
            if (rotation != 3) {
                return;
            }
            Matrix.rotateM(this.i, 0, 90.0f, 0.0f, 0.0f, 1.0f);
        }
    }

    public void e(Camera2Helper.OnPreviewListener onPreviewListener) {
        this.k = onPreviewListener;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.OnPreviewListener
    public void onPreviewFrame(Image image, int i, int i2) {
        Camera2Helper.OnPreviewListener onPreviewListener = this.k;
        if (onPreviewListener != null) {
            onPreviewListener.onPreviewFrame(image, this.t, this.q);
        }
    }

    public void d(boolean z) {
        if (z == this.f13243a) {
            LogUtil.h("Track_GlRenderWrapper", "same camera direction, return");
            return;
        }
        this.f13243a = z;
        Camera2Helper camera2Helper = this.b;
        if (camera2Helper != null) {
            camera2Helper.b();
            this.b.bke_(this.t, this.q, this.o, this.f13243a, this.d);
        }
    }

    public boolean c() {
        return this.f13243a;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public void c(float f) {
        this.l = f;
    }
}
