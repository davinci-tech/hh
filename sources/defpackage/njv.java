package defpackage;

import android.opengl.GLES20;

/* loaded from: classes9.dex */
public class njv {
    public static void d(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        njw.c("ES20_ERROR", njw.b() + str + ": glError " + glGetError + " errString=" + d(glGetError));
        throw new RuntimeException(str + ": glError " + glGetError + " errString=" + d(glGetError));
    }

    public static String d(int i) {
        if (i == 0) {
            return "No error has been recorded.";
        }
        if (i == 1285) {
            return "There is not enough memory left to execute the command. The state of the GL is undefined, except for the state of the error flags, after this error is recorded.";
        }
        if (i == 1286) {
            return "The command is trying to render to or read from the framebuffer while the currently bound framebuffer is not framebuffer complete (i.e. the return value from glCheckFramebufferStatus is not GL_FRAMEBUFFER_COMPLETE).";
        }
        switch (i) {
            case 1280:
                return "An unacceptable value is specified for an enumerated argument.";
            case 1281:
                return "A numeric argument is out of range.";
            case 1282:
                return "The specified operation is not allowed in the current state.";
            default:
                return "UNKNOW ERROR";
        }
    }
}
