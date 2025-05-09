package com.huawei.hms.scankit.p;

import android.hardware.Camera;
import android.util.Log;

/* loaded from: classes9.dex */
public class n0 {

    /* renamed from: a, reason: collision with root package name */
    private Camera f5835a;

    public void a(Camera camera) {
        synchronized (this) {
            this.f5835a = camera;
        }
    }

    public boolean b() {
        synchronized (this) {
            Camera camera = this.f5835a;
            if (camera == null) {
                return false;
            }
            return camera.getParameters().isZoomSupported();
        }
    }

    public m0 a() {
        m0 m0Var;
        synchronized (this) {
            m0Var = new m0(this.f5835a.getParameters().getMaxZoom(), this.f5835a.getParameters().getZoom(), this.f5835a.getParameters().getZoomRatios());
        }
        return m0Var;
    }

    public void a(int i) {
        synchronized (this) {
            Camera camera = this.f5835a;
            if (camera == null) {
                return;
            }
            Camera.Parameters parameters = camera.getParameters();
            parameters.setZoom(i);
            try {
                this.f5835a.setParameters(parameters);
            } catch (RuntimeException e) {
                Log.e("CameraManager", "CameraZoomManager::setCameraZoomIndex failed: " + e.getMessage());
            }
        }
    }
}
