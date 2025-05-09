package com.huawei.hms.scankit.p;

import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import com.huawei.hms.scankit.p.k0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class l0 {

    /* renamed from: a, reason: collision with root package name */
    private Camera f5820a;

    public void a(Camera camera) {
        synchronized (this) {
            this.f5820a = camera;
        }
    }

    public k0 a() {
        int i;
        RuntimeException e;
        Rect rect;
        synchronized (this) {
            try {
                i = this.f5820a.getParameters().getMaxNumMeteringAreas();
            } catch (RuntimeException e2) {
                i = 0;
                e = e2;
            }
            try {
                rect = this.f5820a.getParameters().getMeteringAreas().get(0).rect;
            } catch (RuntimeException e3) {
                e = e3;
                Log.w("CameraManager", "CameraMeteringManager::getCameraMeteringData failed: " + e.getMessage());
                rect = null;
                return new k0(i, rect);
            }
        }
        return new k0(i, rect);
    }

    public void a(List<k0.a> list) {
        synchronized (this) {
            Camera camera = this.f5820a;
            if (camera == null) {
                return;
            }
            Camera.Parameters parameters = camera.getParameters();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(new Camera.Area(list.get(i).f5814a, list.get(i).b));
            }
            parameters.setMeteringAreas(arrayList);
            try {
                this.f5820a.setParameters(parameters);
            } catch (RuntimeException e) {
                Log.w("CameraManager", "CameraMeteringManager::setCameraMeteringArea failed: " + e.getMessage());
            }
        }
    }
}
