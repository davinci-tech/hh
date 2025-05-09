package defpackage;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* loaded from: classes9.dex */
public class nki implements GLSurfaceView.EGLConfigChooser {
    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eGLDisplay, new int[]{12329, 0, 12352, 4, 12351, 12430, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12338, 1, 12326, 0, 12337, 4, 12344}, eGLConfigArr, 1, iArr);
        if (iArr[0] == 0) {
            return null;
        }
        return eGLConfigArr[0];
    }
}
