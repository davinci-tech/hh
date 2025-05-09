package com.autonavi.base.ae.gmap;

/* loaded from: classes2.dex */
public class GLEngineIDController {
    private static final String TAG = "GLEngineIDController";
    private static GLEngineIDController sController = new GLEngineIDController();
    private int engineIDIndex = 10000;

    private GLEngineIDController() {
    }

    public static GLEngineIDController getController() {
        return sController;
    }

    public int generate() {
        int i;
        synchronized (this) {
            i = this.engineIDIndex + 1;
            this.engineIDIndex = i;
        }
        return i;
    }
}
