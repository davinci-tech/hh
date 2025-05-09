package com.huawei.hms.scankit.p;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes9.dex */
public class f0 {

    /* renamed from: a, reason: collision with root package name */
    private e0 f5774a;
    private Point b;
    private Point c;

    private void b(Camera.Parameters parameters) {
        if (parameters.isZoomSupported()) {
            parameters.setZoom(1);
        } else {
            Log.w("CameraManager", "initCameraParameters::setDefaultZoom not support zoom");
        }
    }

    private void c(Camera.Parameters parameters) {
        String str;
        String[] strArr = {"continuous-picture", "continuous-video", "auto"};
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null) {
            Log.w("CameraManager", "setFocusMode failed, use default");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= 3) {
                str = null;
                break;
            }
            str = strArr[i];
            if (supportedFocusModes.contains(str)) {
                break;
            } else {
                i++;
            }
        }
        if (str != null) {
            Log.i("CameraManager", "setFocusMode: " + str);
            parameters.setFocusMode(str);
        }
    }

    void a(Camera camera, e0 e0Var) {
        if (camera == null || e0Var == null) {
            throw new IllegalArgumentException("initCameraParameters param is invalid");
        }
        Camera.Parameters parameters = camera.getParameters();
        this.f5774a = e0Var;
        this.b = a(parameters, e0Var.a(), false);
        Log.d("CameraManager", "initCameraParameters previewCameraSize: " + this.b.toString());
        if (e0Var.c() == 0) {
            this.c = a(parameters, e0Var.a(), true);
            Log.d("CameraManager", "initCameraParameters pictureCameraSize: " + this.c.toString());
        }
        a(camera, this.b, this.c);
    }

    Point a() {
        return this.b;
    }

    private void a(Camera camera, Point point, Point point2) {
        if (this.f5774a == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(point.x, point.y);
        if (this.f5774a.c() == 0) {
            parameters.setPictureSize(point2.x, point2.y);
        }
        if (this.f5774a.b() != 1) {
            a(parameters);
        }
        c(parameters);
        b(parameters);
        if (this.f5774a.e()) {
            parameters.setRecordingHint(true);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            a(parameters, true);
        }
        camera.setParameters(parameters);
    }

    public static void a(Camera.Parameters parameters, boolean z) {
        try {
            Method method = Camera.Parameters.class.getMethod("setScanOptEnable", Boolean.TYPE);
            if (method != null) {
                method.invoke(parameters, Boolean.valueOf(z));
                Log.i("CameraManager", "setScanOptEnable isOpt " + z);
            }
        } catch (IllegalAccessException unused) {
            Log.e("CameraManager", "setScanOptEnable reflection IllegalAccessException");
        } catch (NoSuchMethodException unused2) {
            Log.e("CameraManager", "setScanOptEnable reflection NoSuchMethodException");
        } catch (InvocationTargetException unused3) {
            Log.e("CameraManager", "setScanOptEnable reflection InvocationTargetException");
        } catch (Exception unused4) {
            Log.e("CameraManager", "setScanOptEnable reflection Exception");
        }
    }

    private Point a(Camera.Parameters parameters, Point point, boolean z) {
        List<Camera.Size> supportedPictureSizes;
        if (!z) {
            supportedPictureSizes = parameters.getSupportedPreviewSizes();
        } else {
            supportedPictureSizes = parameters.getSupportedPictureSizes();
        }
        if (supportedPictureSizes != null && !supportedPictureSizes.isEmpty()) {
            return a(supportedPictureSizes, point);
        }
        Log.e("CameraManager", "CameraConfigImpl::findCameraResolution camera not support");
        return new Point(0, 0);
    }

    private Point a(List<Camera.Size> list, Point point) {
        double d = point.x / point.y;
        int i = 0;
        double d2 = Double.MAX_VALUE;
        int i2 = 0;
        for (Camera.Size size : list) {
            int i3 = size.width;
            int i4 = size.height;
            if (i3 == point.x && i4 == point.y) {
                return new Point(i3, i4);
            }
            if (i3 * i4 >= 153600.0d) {
                double d3 = (i3 / i4) - d;
                if (Math.abs(d3) < d2) {
                    d2 = Math.abs(d3);
                    i2 = i4;
                    i = i3;
                }
            }
        }
        return new Point(i, i2);
    }

    private void a(Camera.Parameters parameters) {
        e0 e0Var = this.f5774a;
        if (e0Var == null) {
            return;
        }
        String f = e0Var.f();
        if (!f.equals("off") && !f.equals("torch")) {
            f = "off";
        }
        parameters.setFlashMode(f);
    }
}
