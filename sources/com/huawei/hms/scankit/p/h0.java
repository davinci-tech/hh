package com.huawei.hms.scankit.p;

import android.hardware.Camera;
import android.util.Log;

/* loaded from: classes9.dex */
public class h0 {

    /* renamed from: a, reason: collision with root package name */
    private Camera f5783a;

    public void a(Camera camera) {
        synchronized (this) {
            this.f5783a = camera;
        }
    }

    public g0 a() {
        g0 g0Var;
        synchronized (this) {
            g0Var = new g0(this.f5783a.getParameters().getMaxExposureCompensation(), this.f5783a.getParameters().getMinExposureCompensation(), this.f5783a.getParameters().getExposureCompensation(), this.f5783a.getParameters().getExposureCompensationStep());
        }
        return g0Var;
    }

    public void a(int i) {
        synchronized (this) {
            Camera camera = this.f5783a;
            if (camera == null) {
                return;
            }
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setExposureCompensation(i);
                Log.i("WWYYEHG", "setExpuseModeA: " + parameters.getAutoExposureLock());
                this.f5783a.setParameters(parameters);
                Log.i("WWYYEHG", "setExpuseModeB: " + parameters.getAutoExposureLock());
            } catch (RuntimeException unused) {
                Log.w("CameraManager", "CameraExposureManager::setCompensation failed");
            }
        }
    }
}
