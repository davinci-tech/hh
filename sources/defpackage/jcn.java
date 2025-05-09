package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
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
import android.view.TextureView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.camera.CameraStateListener;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class jcn {

    /* renamed from: a, reason: collision with root package name */
    private Handler f13743a;
    private CameraDevice b;
    private HandlerThread d;
    private Activity e;
    private CameraStateListener h;
    private String i;
    private CameraCaptureSession j;
    private boolean k;
    private ImageReader l;
    private Size m;
    private int n;
    private Range<Integer> o;
    private CaptureRequest.Builder q;
    private boolean r;
    private CaptureRequest s;
    private boolean t;
    private Size u;
    private TextureView w;
    private int y;
    private boolean c = false;
    private int x = 0;
    private Semaphore g = new Semaphore(1);
    private CameraCaptureSession.CaptureCallback f = new CameraCaptureSession.CaptureCallback() { // from class: jcn.1
        private void bFd_(CaptureResult captureResult) {
            int i = jcn.this.x;
            if (i == 1) {
                Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num == null) {
                    LogUtil.e("CameraHelper", "CameraCaptureSession.CaptureCallback afState is null");
                    jcn.this.i();
                    return;
                } else {
                    if (num.intValue() == 4 || num.intValue() == 5) {
                        Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                        if (num2 == null || num2.intValue() == 2) {
                            jcn.this.x = 4;
                            jcn.this.i();
                            return;
                        } else {
                            jcn.this.k();
                            return;
                        }
                    }
                    return;
                }
            }
            if (i == 2) {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num3 == null || num3.intValue() == 5 || num3.intValue() == 4) {
                    jcn.this.x = 3;
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
            Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
            if (num4 == null || num4.intValue() != 5) {
                jcn.this.x = 4;
                jcn.this.i();
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            bFd_(captureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            bFd_(totalCaptureResult);
        }
    };
    private ImageReader.OnImageAvailableListener p = new ImageReader.OnImageAvailableListener() { // from class: jcn.4
        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Image acquireNextImage = imageReader.acquireNextImage();
            if (acquireNextImage == null || acquireNextImage.getPlanes() == null) {
                return;
            }
            if (jcn.this.h != null) {
                jcn.this.h.onPreviewFrame(acquireNextImage, jcn.this.y);
            }
            acquireNextImage.close();
        }
    };
    private final CameraDevice.StateCallback v = new CameraDevice.StateCallback() { // from class: jcn.3
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            LogUtil.c("CameraHelper", "StateCallback camera opened,start preview");
            jcn.this.g.release();
            jcn.this.b = cameraDevice;
            jcn.this.b();
            if (jcn.this.h != null) {
                jcn.this.h.onCameraOpened(jcn.this.y);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            LogUtil.c("CameraHelper", "StateCallback camera disconnected");
            jcn.this.g.release();
            cameraDevice.close();
            jcn.this.b = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            LogUtil.c("CameraHelper", "StateCallback camera error=", Integer.valueOf(i));
            jcn.this.g.release();
            cameraDevice.close();
            jcn.this.b = null;
            if (jcn.this.e != null) {
                jcn.this.e.finish();
            }
            if (jcn.this.h != null) {
                jcn.this.h.onCameraError("CameraDevice.StateCallback int error state, errorCode=" + i);
            }
        }
    };

    public jcn(Activity activity, TextureView textureView, CameraStateListener cameraStateListener, boolean z, int i) {
        this.n = i;
        this.t = z;
        this.e = activity;
        this.w = textureView;
        this.h = cameraStateListener;
        this.k = b(activity);
    }

    public boolean c() {
        return this.b != null;
    }

    public void e(int i, int i2, boolean z) {
        CameraStateListener cameraStateListener;
        if (PermissionUtil.e(this.e, PermissionUtil.PermissionType.CAMERA) != PermissionUtil.PermissionResult.GRANTED) {
            CameraStateListener cameraStateListener2 = this.h;
            if (cameraStateListener2 != null) {
                cameraStateListener2.onCameraError("openCamera error: camera permission is not permitted!");
                return;
            }
            return;
        }
        LogUtil.c("CameraHelper", "openCamera start");
        Object systemService = this.e.getSystemService("camera");
        CameraManager cameraManager = systemService instanceof CameraManager ? (CameraManager) systemService : null;
        if (cameraManager == null) {
            CameraStateListener cameraStateListener3 = this.h;
            if (cameraStateListener3 != null) {
                cameraStateListener3.onCameraError("openCamera cameraManager is null");
            }
            this.e.finish();
            return;
        }
        try {
            if (!this.g.tryAcquire(2500L, TimeUnit.MILLISECONDS) && (cameraStateListener = this.h) != null) {
                cameraStateListener.onCameraError("Time out waiting to lock camera opening.");
            }
            n();
            a(i, i2, z);
            a(i, i2);
            String str = this.i;
            if (str == null) {
                ReleaseLogUtil.a("CameraHelper", "cameraId is null");
            } else {
                cameraManager.openCamera(str, this.v, this.f13743a);
            }
        } catch (CameraAccessException e) {
            CameraStateListener cameraStateListener4 = this.h;
            if (cameraStateListener4 != null) {
                cameraStateListener4.onCameraError("openCamera CameraAccessException: " + ExceptionUtils.d(e));
            }
        } catch (IllegalArgumentException e2) {
            CameraStateListener cameraStateListener5 = this.h;
            if (cameraStateListener5 != null) {
                cameraStateListener5.onCameraError("openCamera IllegalArgumentException: " + ExceptionUtils.d(e2));
            }
        } catch (InterruptedException e3) {
            CameraStateListener cameraStateListener6 = this.h;
            if (cameraStateListener6 != null) {
                cameraStateListener6.onCameraError("Interrupted while trying to lock camera opening:" + ExceptionUtils.d(e3));
            }
        }
    }

    private void a(int i, int i2, boolean z) {
        StreamConfigurationMap streamConfigurationMap;
        Object systemService = this.e.getSystemService("camera");
        CameraManager cameraManager = systemService instanceof CameraManager ? (CameraManager) systemService : null;
        if (cameraManager == null) {
            LogUtil.e("CameraHelper", "setUpCameraOutputs cameraManager is null");
            this.e.finish();
            return;
        }
        try {
            boolean z2 = false;
            for (String str : cameraManager.getCameraIdList()) {
                if (this.i == null) {
                    this.i = str;
                }
                LogUtil.c("CameraHelper", "setUpCameraOutputs cameraId=", str);
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                LogUtil.c("CameraHelper", "setUpCameraOutputs facing=", num);
                if ((num == null || num.intValue() != (!z)) && (streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)) != null) {
                    int intValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
                    LogUtil.c("CameraHelper", "setUpCameraOutputs current device support camera 2 level=", Integer.valueOf(intValue));
                    if (intValue == 2) {
                        LogUtil.e("CameraHelper", "setUpCameraOutputs current device cannot support camera 2");
                    }
                    Object obj = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                    if (obj != null && (obj instanceof Integer)) {
                        this.y = ((Integer) obj).intValue();
                    }
                    LogUtil.c("CameraHelper", "isSwappedDimensions mSensorOrientation=", Integer.valueOf(this.y));
                    this.c = f();
                    bFa_(i, i2, streamConfigurationMap);
                    LogUtil.c("CameraHelper", "setUpCameraOutputs mSelectPreviewSize.width=", Integer.valueOf(this.u.getWidth()), " mSelectPreviewSize.height=", Integer.valueOf(this.u.getHeight()));
                    Size bEZ_ = bEZ_(streamConfigurationMap.getOutputSizes(this.n), i, i2, this.c);
                    this.m = bEZ_;
                    if (bEZ_ == null) {
                        this.m = this.u;
                        LogUtil.a("CameraHelper", "mCaptureSize == null use mSelectPreviewSize");
                    }
                    LogUtil.c("CameraHelper", "mCaptureSize.getWidth(): ", Integer.valueOf(this.m.getWidth()), "mCaptureSize.getHeight(): ", Integer.valueOf(this.m.getHeight()));
                    h();
                    Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    if (bool != null) {
                        z2 = bool.booleanValue();
                    }
                    this.r = z2;
                    bEY_(cameraManager, str);
                    this.i = str;
                    return;
                }
            }
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "CameraAccessException: ", ExceptionUtils.d(e));
        } catch (NullPointerException e2) {
            LogUtil.e("CameraHelper", "setUpCameraOutputs NullPointerException: ", ExceptionUtils.d(e2));
        }
    }

    private void bEY_(CameraManager cameraManager, String str) throws CameraAccessException {
        Range[] rangeArr = (Range[]) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        LogUtil.c("CameraHelper", "ranges: ", Arrays.toString(rangeArr));
        Range range = null;
        for (Range range2 : rangeArr) {
            int intValue = ((Integer) range2.getLower()).intValue();
            int intValue2 = ((Integer) range2.getUpper()).intValue();
            if (range == null || ((Integer) range.getLower()).intValue() < 20 || (intValue >= 20 && intValue <= ((Integer) range.getLower()).intValue() && intValue2 <= ((Integer) range.getUpper()).intValue())) {
                range = range2;
            }
        }
        if (range != null) {
            this.o = new Range<>((Integer) range.getLower(), (Integer) range.getUpper());
            ReleaseLogUtil.b("R_CameraHelper", "mFrameRateRange:  " + this.o.toString());
        }
    }

    private void h() {
        LogUtil.c("CameraHelper", "enter initImageReader");
        Size size = this.m;
        if (size == null) {
            ReleaseLogUtil.a("R_CameraHelper", "mCaptureSize == null ");
            return;
        }
        this.l = ImageReader.newInstance(size.getWidth(), this.m.getHeight(), this.n, 2);
        if (this.f13743a == null) {
            LogUtil.c("CameraHelper", "mBackgroundHandler == null startBackgroundThread");
            n();
        }
        this.l.setOnImageAvailableListener(this.p, this.f13743a);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003d A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean f() {
        /*
            r3 = this;
            android.app.Activity r0 = r3.e
            android.view.WindowManager r0 = r0.getWindowManager()
            android.view.Display r0 = r0.getDefaultDisplay()
            int r0 = r0.getRotation()
            r1 = 1
            if (r0 == 0) goto L32
            if (r0 == r1) goto L29
            r2 = 2
            if (r0 == r2) goto L32
            r2 = 3
            if (r0 == r2) goto L29
            java.lang.String r1 = "Display rotation is invalid displayRotation=: "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "CameraHelper"
            health.compact.a.LogUtil.a(r1, r0)
            goto L3d
        L29:
            int r0 = r3.y
            if (r0 == 0) goto L3e
            r2 = 180(0xb4, float:2.52E-43)
            if (r0 != r2) goto L3d
            goto L3e
        L32:
            int r0 = r3.y
            r2 = 90
            if (r0 == r2) goto L3e
            r2 = 270(0x10e, float:3.78E-43)
            if (r0 != r2) goto L3d
            goto L3e
        L3d:
            r1 = 0
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jcn.f():boolean");
    }

    private void bFa_(int i, int i2, StreamConfigurationMap streamConfigurationMap) {
        this.e.getWindowManager().getDefaultDisplay().getSize(new Point());
        if (this.c) {
            LogUtil.c("CameraHelper", "getPreviewSize swapped dimensions");
        } else {
            i2 = i;
            i = i2;
        }
        LogUtil.c("CameraHelper", "getPreviewSize previewWidth=", Integer.valueOf(i), " previewHeight=", Integer.valueOf(i2), " isSwappedDimensions=", Boolean.valueOf(this.c));
        this.u = bEZ_(streamConfigurationMap.getOutputSizes(SurfaceTexture.class), i, i2, this.c);
    }

    private static Size bEZ_(Size[] sizeArr, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        float f = i;
        float f2 = i2;
        float f3 = f / f2;
        Arrays.sort(sizeArr, new Comparator() { // from class: jcq
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int signum;
                Size size = (Size) obj;
                Size size2 = (Size) obj2;
                signum = Long.signum((size2.getWidth() * size2.getHeight()) - (size.getWidth() * size.getHeight()));
                return signum;
            }
        });
        float f4 = Float.MAX_VALUE;
        Size size = null;
        for (Size size2 : sizeArr) {
            sb.append("[" + size2.getWidth() + ", " + size2.getHeight() + "]");
            if (size2.getHeight() != 0 && size2.getWidth() != 0) {
                int height = z ? size2.getHeight() : size2.getWidth();
                int width = z ? size2.getWidth() : size2.getHeight();
                if (height >= f * 0.8f) {
                    if (width >= 0.8f * f2) {
                        float floatValue = new BigDecimal(Math.abs(f3 - ((r7 * 1.0f) / r8))).setScale(4, 4).floatValue();
                        if (floatValue <= f4) {
                            size = size2;
                            f4 = floatValue;
                        }
                    }
                }
                arrayList.add(size2);
            }
        }
        LogUtil.c("CameraHelper", "chooseOptimalSize ratio=", Float.valueOf(f3), " deltaRatioMin=", Float.valueOf(f4));
        LogUtil.c("CameraHelper", "chooseOptimalSize: ", sb);
        if (size != null) {
            LogUtil.c("CameraHelper", "chooseOptimalSize get bigEnough size");
            return size;
        }
        if (arrayList.size() > 0) {
            LogUtil.c("CameraHelper", "chooseOptimalSize get notBigEnough size");
            return (Size) Collections.max(arrayList, new c());
        }
        LogUtil.a("CameraHelper", "Couldn't find any suitable preview size");
        return sizeArr[sizeArr.length / 2];
    }

    static class c implements Comparator<Size> {
        c() {
        }

        @Override // java.util.Comparator
        /* renamed from: bFe_, reason: merged with bridge method [inline-methods] */
        public int compare(Size size, Size size2) {
            return Long.signum((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
        }
    }

    private void n() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.d = handlerThread;
        handlerThread.start();
        this.f13743a = new Handler(this.d.getLooper());
    }

    private void l() {
        LogUtil.c("CameraHelper", "stopBackgroundThread");
        HandlerThread handlerThread = this.d;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            try {
                this.d.join();
                this.d = null;
                this.f13743a = null;
            } catch (InterruptedException e) {
                LogUtil.e("CameraHelper", "stopBackgroundThread InterruptedException: ", ExceptionUtils.d(e));
            }
        }
    }

    public void a() {
        try {
            try {
                LogUtil.c("CameraHelper", "closeCamera");
                this.g.acquire();
                CameraCaptureSession cameraCaptureSession = this.j;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.close();
                    this.j = null;
                }
                CameraDevice cameraDevice = this.b;
                if (cameraDevice != null) {
                    cameraDevice.close();
                    this.b = null;
                }
                ImageReader imageReader = this.l;
                if (imageReader != null) {
                    imageReader.setOnImageAvailableListener(null, null);
                    this.l.close();
                    this.l = null;
                }
                l();
            } catch (InterruptedException e) {
                LogUtil.e("CameraHelper", "Interrupted while trying to lock camera closing.", ExceptionUtils.d(e));
            }
        } finally {
            this.g.release();
        }
    }

    public void b() {
        try {
            LogUtil.c("CameraHelper", "createCameraPreviewSession start");
            TextureView textureView = this.w;
            if (textureView == null) {
                LogUtil.a("CameraHelper", "createCameraPreviewSession mTextureView is null");
                return;
            }
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            Size size = this.u;
            if (size != null && surfaceTexture != null) {
                surfaceTexture.setDefaultBufferSize(size.getWidth(), this.u.getHeight());
                Surface surface = new Surface(surfaceTexture);
                CameraDevice cameraDevice = this.b;
                if (cameraDevice == null) {
                    LogUtil.a("CameraHelper", "mCameraDevice == null");
                    return;
                }
                CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(1);
                this.q = createCaptureRequest;
                if (this.o != null) {
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.o);
                }
                this.q.addTarget(surface);
                if (this.l == null) {
                    h();
                }
                ImageReader imageReader = this.l;
                if (imageReader == null) {
                    ReleaseLogUtil.a("R_CameraHelper", "mImageReader == null");
                    return;
                }
                if (this.t) {
                    this.q.addTarget(imageReader.getSurface());
                }
                this.b.createCaptureSession(Arrays.asList(surface, this.l.getSurface()), new CameraCaptureSession.StateCallback() { // from class: jcn.2
                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        if (jcn.this.b == null) {
                            LogUtil.e("CameraHelper", "createCameraPreviewSession onConfigured mCameraDevice is null");
                            return;
                        }
                        LogUtil.c("CameraHelper", "createCameraPreviewSession onConfigured");
                        jcn.this.j = cameraCaptureSession;
                        try {
                            jcn.this.q.set(CaptureRequest.CONTROL_AF_MODE, 4);
                            jcn jcnVar = jcn.this;
                            jcnVar.bFc_(jcnVar.q);
                            jcn jcnVar2 = jcn.this;
                            jcnVar2.s = jcnVar2.q.build();
                            jcn.this.j.setRepeatingRequest(jcn.this.s, jcn.this.f, jcn.this.f13743a);
                        } catch (CameraAccessException e) {
                            LogUtil.e("CameraHelper", "createCameraPreviewSession CameraAccessException：", ExceptionUtils.d(e));
                        } catch (IllegalArgumentException e2) {
                            LogUtil.e("CameraHelper", "createCameraPreviewSession IllegalArgumentException：", ExceptionUtils.d(e2));
                        } catch (IllegalStateException e3) {
                            if (jcn.this.h != null) {
                                jcn.this.h.onCameraError("createCameraPreviewSession IllegalStateException: " + ExceptionUtils.d(e3));
                            }
                        } catch (NullPointerException e4) {
                            LogUtil.e("CameraHelper", "createCameraPreviewSession NullPointerException：", ExceptionUtils.d(e4));
                        }
                    }

                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        LogUtil.c("CameraHelper", "createCameraPreviewSession createCaptureSession failed");
                    }
                }, null);
                return;
            }
            LogUtil.a("CameraHelper", "mSelectPreviewSize or texture is null");
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "createCameraPreviewSession CameraAccessException: ", ExceptionUtils.d(e));
        } catch (IllegalStateException e2) {
            LogUtil.e("CameraHelper", "createCameraPreviewSession IllegalStateException: ", ExceptionUtils.d(e2));
        }
    }

    public void a(int i, int i2) {
        Activity activity;
        if (this.w == null || this.u == null || (activity = this.e) == null) {
            LogUtil.e("CameraHelper", "configureTransform fail");
            return;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        float f = i;
        float f2 = i2;
        RectF rectF = new RectF(0.0f, 0.0f, f, f2);
        RectF rectF2 = new RectF(0.0f, 0.0f, this.u.getHeight(), this.u.getWidth());
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        boolean b = b(this.e);
        LogUtil.c("CameraHelper", "configureTransform rotation=", Integer.valueOf(rotation), " mIsDefaultInMagicWindow: ", Boolean.valueOf(this.k), " isInMagicWindow:", Boolean.valueOf(b));
        if (this.k && b && (rotation == 1 || rotation == 3)) {
            rotation = 0;
        }
        if (rotation == 1 || rotation == 3) {
            rectF2.offset(centerX - rectF2.centerX(), centerY - rectF2.centerY());
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            float max = Math.max(f2 / this.u.getHeight(), f / this.u.getWidth());
            matrix.postScale(max, max, centerX, centerY);
            matrix.postRotate((rotation - 2) * 90, centerX, centerY);
        } else if (rotation == 2) {
            matrix.postRotate(180.0f, centerX, centerY);
        } else {
            LogUtil.c("CameraHelper", "configureTransform normal rotation");
        }
        this.w.setTransform(matrix);
    }

    private boolean b(Context context) {
        if (context == null) {
            LogUtil.a("CameraHelper", "isInMagicWindow() context is null!!");
            return false;
        }
        String configuration = context.getResources().getConfiguration().toString();
        return configuration.contains("hwMultiwindow-magic") || configuration.contains("hw-magic-windows");
    }

    private void g() {
        if (this.e == null || this.b == null) {
            LogUtil.e("CameraHelper", "cannot lock focus to take photo");
            return;
        }
        try {
            LogUtil.c("CameraHelper", "takePicture lockFocus");
            this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            this.x = 1;
            CameraCaptureSession cameraCaptureSession = this.j;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.capture(this.q.build(), this.f, this.f13743a);
            } else {
                LogUtil.e("CameraHelper", "lockFocus mCaptureSession is null");
            }
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "lockFocus CameraAccessException: ", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.c("CameraHelper", "runPreCaptureSequence");
        if (this.b == null || this.e == null) {
            LogUtil.e("CameraHelper", "cannot run pre capture sequence");
            return;
        }
        try {
            this.q.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
            CameraCaptureSession cameraCaptureSession = this.j;
            if (cameraCaptureSession != null) {
                this.x = 2;
                cameraCaptureSession.capture(this.q.build(), this.f, this.f13743a);
            }
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "runPreCaptureSequence CameraAccessException: ", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            if (this.e != null && this.b != null) {
                LogUtil.c("CameraHelper", "captureStillPicture start");
                CaptureRequest.Builder createCaptureRequest = this.b.createCaptureRequest(2);
                createCaptureRequest.addTarget(this.l.getSurface());
                createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
                bFc_(createCaptureRequest);
                CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() { // from class: jcn.5
                    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                    public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                        LogUtil.c("CameraHelper", "captureStillPicture onCaptureCompleted");
                    }
                };
                CameraCaptureSession cameraCaptureSession = this.j;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.stopRepeating();
                    this.j.capture(createCaptureRequest.build(), captureCallback, this.f13743a);
                    return;
                }
                return;
            }
            LogUtil.e("CameraHelper", "captureStillPicture mActivity|mCameraDevice is null");
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "captureStillPicture CameraAccessException: ", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bFc_(CaptureRequest.Builder builder) {
        if (this.r) {
            LogUtil.c("CameraHelper", "setAutoFlash mode on auto flash");
            builder.set(CaptureRequest.CONTROL_AE_MODE, 2);
        }
    }

    public void j() {
        try {
            LogUtil.c("CameraHelper", "unlockFocus...");
            if (this.b != null && this.e != null) {
                this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                bFc_(this.q);
                CameraCaptureSession cameraCaptureSession = this.j;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.capture(this.q.build(), this.f, this.f13743a);
                    this.x = 0;
                    this.j.setRepeatingRequest(this.s, this.f, this.f13743a);
                    return;
                }
                return;
            }
            LogUtil.e("CameraHelper", "cannot unlock focus after capture");
        } catch (CameraAccessException e) {
            LogUtil.e("CameraHelper", "unlockFocus CameraAccessException: ", ExceptionUtils.d(e));
        }
    }

    public void d() {
        g();
    }

    public void d(boolean z) {
        a();
        e(this.w.getWidth(), this.w.getHeight(), z);
    }

    public void e() {
        LogUtil.c("CameraHelper", "onDestroy");
        this.w = null;
        this.e = null;
        this.h = null;
    }
}
