package com.huawei.healthcloud.plugintrack.ui.view.glrender.util;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import android.view.WindowManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes4.dex */
public class Camera2Helper {

    /* renamed from: a, reason: collision with root package name */
    private String f3814a;
    private Handler b;
    private CameraDevice c;
    private HandlerThread e;
    private ImageReader f;
    private Activity g;
    private Range<Integer> i;
    private CameraCaptureSession j;
    private CaptureRequest m;
    private OnPreviewSizeListener n;
    private OnPreviewListener o;
    private Size p;
    private CaptureRequest.Builder s;
    private int t;
    private SurfaceTexture x;
    private boolean d = false;
    private float r = 1.0f;
    private boolean k = false;
    private final CameraDevice.StateCallback q = new CameraDevice.StateCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.1
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            LogUtil.a("Track_Camera2Helper", "Camera2 onOpened");
            Camera2Helper.this.c = cameraDevice;
            Camera2Helper.this.a();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            LogUtil.a("Track_Camera2Helper", "Camera2 onDisconnected");
            cameraDevice.close();
            Camera2Helper.this.c = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            LogUtil.h("Track_Camera2Helper", "Camera2 error:", Integer.valueOf(i));
            cameraDevice.close();
            Camera2Helper.this.c = null;
        }
    };
    private ImageReader.OnImageAvailableListener l = new ImageReader.OnImageAvailableListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.5
        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Image acquireNextImage = imageReader.acquireNextImage();
            if (acquireNextImage == null) {
                return;
            }
            if (Camera2Helper.this.o != null) {
                Camera2Helper.this.o.onPreviewFrame(acquireNextImage, acquireNextImage.getWidth(), acquireNextImage.getHeight());
            }
            acquireNextImage.close();
        }
    };
    private CameraCaptureSession.CaptureCallback h = new CameraCaptureSession.CaptureCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.4
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
        }
    };

    public interface OnPreviewListener {
        void onPreviewFrame(Image image, int i, int i2);
    }

    public interface OnPreviewSizeListener {
        void onSize(int i, int i2);
    }

    public Camera2Helper(Activity activity) {
        this.g = activity;
    }

    public void bke_(int i, int i2, SurfaceTexture surfaceTexture, boolean z, boolean z2) {
        this.x = surfaceTexture;
        this.d = z2;
        d();
        d(i, i2, z);
        CameraManager cameraManager = (CameraManager) this.g.getSystemService("camera");
        String str = this.f3814a;
        if (str == null || str.isEmpty()) {
            ReleaseLogUtil.c("Track_Camera2Helper", "getCameraId failed, please check and try again.");
        } else {
            if (this.g.checkSelfPermission("android.permission.CAMERA") != 0) {
                return;
            }
            try {
                cameraManager.openCamera(this.f3814a, this.q, this.b);
            } catch (CameraAccessException e) {
                LogUtil.b("Track_Camera2Helper", "openCamera failed. pls check.", LogAnonymous.b((Throwable) e));
            }
        }
    }

    public void b() {
        CameraCaptureSession cameraCaptureSession = this.j;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.j = null;
        }
        CameraDevice cameraDevice = this.c;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.c = null;
        }
        ImageReader imageReader = this.f;
        if (imageReader != null) {
            imageReader.close();
            this.f = null;
        }
        c();
    }

    public void e(boolean z) {
        this.k = z;
    }

    public void b(float f) {
        this.r = f;
    }

    private void d() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.e = handlerThread;
        handlerThread.start();
        this.b = new Handler(this.e.getLooper());
    }

    private void c() {
        this.e.quitSafely();
        this.e = null;
        this.b = null;
    }

    private void d(int i, int i2, boolean z) {
        StreamConfigurationMap streamConfigurationMap;
        if (!(this.g.getSystemService("camera") instanceof CameraManager)) {
            LogUtil.h("Track_Camera2Helper", "initCameraInfo object instanceof CameraManager false");
            return;
        }
        CameraManager cameraManager = (CameraManager) this.g.getSystemService("camera");
        try {
            for (String str : cameraManager.getCameraIdList()) {
                try {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if ((num == null || num.intValue() != (!z)) && (streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)) != null) {
                        Object obj = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                        if (obj != null) {
                            this.t = ((Integer) obj).intValue();
                        }
                        bka_(cameraManager, str);
                        if (this.k) {
                            this.p = bkb_(streamConfigurationMap.getOutputSizes(SurfaceTexture.class), i, i2);
                        } else {
                            this.p = bkc_(streamConfigurationMap.getOutputSizes(SurfaceTexture.class), i2, i);
                        }
                        Size size = this.p;
                        if (size == null) {
                            LogUtil.h("Track_Camera2Helper", "setUpCameraParams mPreviewSize is null.");
                            return;
                        }
                        ReleaseLogUtil.e("R_Track_Camera2Helper", "chooseOptimalSize getWidth ", Integer.valueOf(size.getWidth()), " getHeight ", Integer.valueOf(this.p.getHeight()));
                        OnPreviewSizeListener onPreviewSizeListener = this.n;
                        if (onPreviewSizeListener != null) {
                            onPreviewSizeListener.onSize(this.p.getWidth(), this.p.getHeight());
                        }
                        ImageReader newInstance = ImageReader.newInstance(this.p.getWidth(), this.p.getHeight(), 35, 2);
                        this.f = newInstance;
                        newInstance.setOnImageAvailableListener(this.l, this.b);
                        this.f3814a = str;
                        return;
                    }
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (CameraAccessException e) {
            LogUtil.b("Track_Camera2Helper", "setUpCameraParams CameraAccessException ", LogAnonymous.b((Throwable) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            if (this.c == null) {
                LogUtil.b("Track_Camera2Helper", "createCameraPreviewSession() The mCameraDevice is null. pls check");
                return;
            }
            this.x.setDefaultBufferSize(this.p.getWidth(), this.p.getHeight());
            Surface surface = new Surface(this.x);
            CaptureRequest.Builder createCaptureRequest = this.c.createCaptureRequest(3);
            this.s = createCaptureRequest;
            createCaptureRequest.addTarget(surface);
            if (this.k) {
                this.s.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.i);
            }
            if (this.d) {
                this.s.addTarget(this.f.getSurface());
            }
            this.c.createCaptureSession(Arrays.asList(surface, this.f.getSurface()), new CameraCaptureSession.StateCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.2
                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    if (Camera2Helper.this.c != null) {
                        Camera2Helper.this.j = cameraCaptureSession;
                        try {
                            Camera2Helper.this.s.set(CaptureRequest.CONTROL_AF_MODE, 4);
                            Camera2Helper camera2Helper = Camera2Helper.this;
                            camera2Helper.m = camera2Helper.s.build();
                            if (Camera2Helper.this.j != null) {
                                Camera2Helper.this.j.setRepeatingRequest(Camera2Helper.this.m, Camera2Helper.this.h, Camera2Helper.this.b);
                                return;
                            } else {
                                ReleaseLogUtil.d("Track_Camera2Helper", "mCaptureSession == null");
                                return;
                            }
                        } catch (CameraAccessException | IllegalStateException | SecurityException e) {
                            LogUtil.b("Track_Camera2Helper", "createCameraPreviewSession() start display the camera preview failed.", LogAnonymous.b(e));
                            return;
                        }
                    }
                    LogUtil.b("Track_Camera2Helper", "createCameraPreviewSession() The camera is already closed.");
                }

                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    LogUtil.b("Track_Camera2Helper", "createCaptureSession onConfigureFailed: ");
                }
            }, this.b);
        } catch (CameraAccessException e) {
            LogUtil.b("Track_Camera2Helper", "createCameraPreviewSession() start display the camera preview failed.", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            LogUtil.b("Track_Camera2Helper", "createCameraPreviewSession Exception ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void bka_(CameraManager cameraManager, String str) {
        try {
            Range[] rangeArr = (Range[]) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            LogUtil.a("Track_Camera2Helper", "ranges: ", Arrays.toString(rangeArr));
            Range range = null;
            for (Range range2 : rangeArr) {
                int intValue = ((Integer) range2.getLower()).intValue();
                int intValue2 = ((Integer) range2.getUpper()).intValue();
                if (range != null) {
                    if (((Integer) range.getLower()).intValue() >= 20) {
                        if (intValue >= 20) {
                            if (intValue <= ((Integer) range.getLower()).intValue()) {
                                if (intValue2 > ((Integer) range.getUpper()).intValue()) {
                                }
                            }
                        }
                    }
                }
                range = range2;
            }
            if (range != null) {
                this.i = new Range<>((Integer) range.getLower(), (Integer) range.getUpper());
                ReleaseLogUtil.e("R_Track_Camera2Helper", "mFrameRateRange:  " + this.i.toString());
            }
        } catch (CameraAccessException unused) {
            LogUtil.a("Track_Camera2Helper", "CameraAccessException");
        }
    }

    private Size bkb_(Size[] sizeArr, int i, int i2) {
        boolean z;
        float f;
        float f2;
        Size size = null;
        if (sizeArr == null || sizeArr.length == 0 || i == 0 || i2 == 0) {
            return null;
        }
        boolean e = e();
        StringBuilder sb = new StringBuilder();
        float f3 = i;
        float f4 = i2;
        float f5 = f3 / f4;
        LogUtil.a("Track_Camera2Helper", "screenWidth ", Integer.valueOf(i), " screenHeight ", Integer.valueOf(i2), " ratio is ", Float.valueOf(f5));
        Arrays.sort(sizeArr, new Comparator() { // from class: hlv
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int signum;
                Size size2 = (Size) obj;
                Size size3 = (Size) obj2;
                signum = Long.signum((size3.getWidth() * size3.getHeight()) - (size2.getWidth() * size2.getHeight()));
                return signum;
            }
        });
        int i3 = 0;
        float f6 = Float.MAX_VALUE;
        while (i3 < sizeArr.length) {
            Size size2 = sizeArr[i3];
            sb.append("[" + size2.getWidth() + ", " + size2.getHeight() + "]");
            if (size2.getHeight() == 0 || size2.getWidth() == 0) {
                z = e;
            } else {
                int height = e ? size2.getHeight() : size2.getWidth();
                int width = e ? size2.getWidth() : size2.getHeight();
                float f7 = height;
                z = e;
                float f8 = this.r;
                if (f7 >= f8 * f3) {
                    float f9 = width;
                    if (f9 >= f8 * f4) {
                        float abs = Math.abs(f5 - ((f7 * 1.0f) / f9));
                        f = f3;
                        f2 = f4;
                        float floatValue = new BigDecimal(abs).setScale(1, 5).floatValue();
                        if (floatValue <= f6) {
                            f6 = floatValue;
                            size = size2;
                        }
                        i3++;
                        f3 = f;
                        e = z;
                        f4 = f2;
                    }
                }
            }
            f = f3;
            f2 = f4;
            i3++;
            f3 = f;
            e = z;
            f4 = f2;
        }
        LogUtil.a("Track_Camera2Helper", "chooseOptimalSize: ", sb);
        if (size != null) {
            LogUtil.a("Track_Camera2Helper", "chooseOptimalSize get bigEnough size");
            return size;
        }
        LogUtil.a("Track_Camera2Helper", "chooseOptimalSize fail, find best preview size");
        return bkc_(sizeArr, i2, i);
    }

    private boolean e() {
        int i;
        int i2;
        Activity activity = this.g;
        if (activity == null) {
            return false;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        return ((rotation == 0 || rotation == 2) && ((i = this.t) == 90 || i == 270)) || ((rotation == 1 || rotation == 3) && ((i2 = this.t) == 0 || i2 == 180));
    }

    private Size bkc_(Size[] sizeArr, int i, int i2) {
        Size size = null;
        if (sizeArr != null && sizeArr.length != 0) {
            Object systemService = this.g.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            int rotation = systemService instanceof WindowManager ? ((WindowManager) systemService).getDefaultDisplay().getRotation() : 0;
            if (i <= i2 || rotation == 1 || rotation == 3) {
                i2 = i;
                i = i2;
            }
            for (Size size2 : sizeArr) {
                if (size2.getWidth() == i && size2.getHeight() == i2) {
                    return size2;
                }
            }
            float f = i / i2;
            float f2 = Float.MAX_VALUE;
            for (Size size3 : sizeArr) {
                if (size3.getHeight() != 0) {
                    float abs = Math.abs(f - ((size3.getWidth() * 1.0f) / size3.getHeight()));
                    if (abs < f2) {
                        size = size3;
                        f2 = abs;
                    }
                }
            }
        }
        return size;
    }

    public void d(OnPreviewSizeListener onPreviewSizeListener) {
        this.n = onPreviewSizeListener;
    }

    public void e(OnPreviewListener onPreviewListener) {
        this.o = onPreviewListener;
    }
}
