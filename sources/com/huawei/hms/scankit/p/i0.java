package com.huawei.hms.scankit.p;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes9.dex */
public class i0 implements Camera.AutoFocusCallback {
    private static final Set<String> i;

    /* renamed from: a, reason: collision with root package name */
    private final boolean f5792a;
    private Camera b;
    private AsyncTask<?, ?, ?> c;
    private boolean d = false;
    private boolean e = false;
    private int f = -1;
    private int g = 2;
    private String h = null;

    static class a extends AsyncTask<Object, Object, Object> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<i0> f5793a;

        a(i0 i0Var) {
            this.f5793a = new WeakReference<>(i0Var);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            i0 i0Var = this.f5793a.get();
            if (i0Var == null) {
                return null;
            }
            i0Var.d();
            try {
                Thread.sleep(Math.max(i0Var.c(), 0));
            } catch (InterruptedException unused) {
                Log.e("CameraManager", "CameraFocusManager::doInBackground InterruptedException");
            }
            return null;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        i = hashSet;
        hashSet.add("auto");
        hashSet.add("macro");
    }

    i0(Camera camera) {
        String str;
        this.b = camera;
        try {
            str = camera.getParameters().getFocusMode();
        } catch (RuntimeException e) {
            Log.e("CameraManager", "Unexpected exception while getFocusMode" + e.getMessage());
            str = "auto";
        }
        boolean contains = i.contains(str);
        this.f5792a = contains;
        Log.i("CameraManager", "CameraFocusManager useAutoFocus： " + contains);
    }

    private void b() {
        synchronized (this) {
            AsyncTask<?, ?, ?> asyncTask = this.c;
            if (asyncTask != null) {
                if (asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
                    this.c.cancel(true);
                }
                this.c = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c() {
        int i2;
        synchronized (this) {
            i2 = this.f;
        }
        return i2;
    }

    public void d() {
        synchronized (this) {
            if (this.f5792a) {
                this.c = null;
                if (!this.d && !this.e) {
                    try {
                        this.b.autoFocus(this);
                        this.e = true;
                    } catch (RuntimeException e) {
                        Log.w("CameraManager", "Unexpected exception while focusing" + e.getMessage());
                        a();
                    }
                }
            }
        }
    }

    void e() {
        synchronized (this) {
            this.d = true;
            if (this.f5792a) {
                b();
                try {
                    Camera camera = this.b;
                    if (camera != null) {
                        camera.cancelAutoFocus();
                    }
                } catch (RuntimeException e) {
                    Log.w("CameraManager", "Unexpected exception while cancelling focusing" + e.getMessage());
                }
            }
        }
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        synchronized (this) {
            this.e = false;
            a();
            if (camera != null) {
                try {
                    camera.cancelAutoFocus();
                } catch (RuntimeException e) {
                    Log.i("CameraManager", "Unexpected exception while cancelling focusing" + e.getMessage());
                }
                try {
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setFocusMode(this.h);
                    camera.setParameters(parameters);
                } catch (RuntimeException e2) {
                    Log.i("CameraManager", "CameraFocusManager::setCameraFocusArea failed: " + e2.getMessage());
                }
            }
        }
    }

    private void a() {
        synchronized (this) {
            if (!this.d && this.c == null) {
                a aVar = new a(this);
                try {
                    aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                    this.c = aVar;
                } catch (RejectedExecutionException e) {
                    Log.w("CameraManager", "CameraFocusManager::autoFocusAgainLater RejectedExecutionException: " + e.getMessage());
                }
            }
        }
    }

    public void a(Rect rect, int i2, int i3, boolean z, boolean z2) {
        int i4 = this.g;
        if (i4 < 1) {
            return;
        }
        this.g = i4 - 1;
        Rect a2 = a(rect.centerX(), rect.centerY(), 1.0f, i3, i2, false, z ? 90 : 0);
        if (a2 == null) {
            this.g--;
            return;
        }
        Camera camera = this.b;
        if (camera != null) {
            try {
                camera.cancelAutoFocus();
                Camera.Parameters parameters = this.b.getParameters();
                if (parameters.getMaxNumFocusAreas() <= 0) {
                    Log.i("CameraManager", "focus areas not supported");
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new Camera.Area(a2, 1000));
                    parameters.setFocusAreas(arrayList);
                }
                if (parameters.getMaxNumMeteringAreas() <= 0) {
                    Log.i("CameraManager", "metering areas not supported");
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new Camera.Area(a2, 1000));
                    parameters.setMeteringAreas(arrayList2);
                }
                this.h = parameters.getFocusMode();
                parameters.setFocusMode("macro");
                this.b.setParameters(parameters);
                this.b.autoFocus(this);
            } catch (RuntimeException e) {
                Log.i("CameraManager", "CameraFocusManager::setCameraFocusArea failed: " + e.getMessage());
                this.g = this.g + 1;
            }
        }
    }

    private static Rect a(float f, float f2, float f3, int i2, int i3, boolean z, int i4) {
        int i5 = (int) (((f / i2) * 2000.0f) - 1000.0f);
        int i6 = (int) (((f2 / i3) * 2000.0f) - 1000.0f);
        RectF rectF = new RectF(a(i5 - 150), a(i6 - 150), a(i5 + 150), a(i6 + 150));
        Matrix matrix = new Matrix();
        try {
            a(matrix, z, i4);
            matrix.mapRect(rectF);
            return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        } catch (RuntimeException e) {
            Log.i("CameraManager", "CameraFocusManager::prepareMatrix failed: " + e.getMessage());
            return null;
        }
    }

    public static void a(Matrix matrix, boolean z, int i2) {
        if (matrix == null) {
            return;
        }
        Matrix matrix2 = new Matrix();
        try {
            matrix.reset();
            matrix2.setScale(z ? -1.0f : 1.0f, 1.0f);
            matrix2.postRotate(i2);
            matrix2.invert(matrix);
        } catch (RuntimeException e) {
            Log.i("CameraManager", "CameraFocusManager::prepareMatrix failed: " + e.getMessage());
        }
    }

    private static int a(int i2) {
        if (i2 > 1000) {
            return 1000;
        }
        return Math.max(i2, -1000);
    }
}
