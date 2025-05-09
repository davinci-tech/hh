package com.amap.api.col.p0003sl;

import android.opengl.GLSurfaceView;
import com.amap.api.col.p0003sl.w;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* loaded from: classes2.dex */
public class dg implements GLSurfaceView.EGLContextFactory, w.f {
    @Override // android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.3sl.w.f
    public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        return null;
    }

    @Override // android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.3sl.w.f
    public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
    }
}
