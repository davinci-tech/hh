package defpackage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.huawei.ucd.medal.MedalViewCallback;

/* loaded from: classes9.dex */
public class nka {
    private static final String e = "TextureHelper";

    public static int cww_(Bitmap bitmap, nkc nkcVar, boolean z) {
        njw.c(e, "initTextureID(Bitmap bitmap, TextureOptions options, boolean recycleBmp)");
        if (nkcVar == null) {
            nkcVar = nkc.e();
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i = iArr[0];
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, nkcVar.f15340a);
        GLES20.glTexParameterf(3553, 10240, nkcVar.c);
        GLES20.glTexParameterf(3553, 10242, nkcVar.d);
        GLES20.glTexParameterf(3553, 10243, nkcVar.b);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (nkcVar.e) {
            GLES20.glGenerateMipmap(3553);
        }
        if (z) {
            bitmap.recycle();
        }
        return i;
    }

    public static void cwu_(Bitmap bitmap) {
        GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
    }

    public static int cwx_(Bitmap bitmap, nkc nkcVar, boolean z, MedalViewCallback medalViewCallback) {
        int i;
        String str = e;
        njw.c(str, "initTextureID(Bitmap bitmap, TextureOptions options, boolean recycleBmp)");
        if (nkcVar == null) {
            nkcVar = nkc.e();
        }
        int[] iArr = new int[1];
        int i2 = 0;
        try {
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        } catch (Exception e2) {
            e = e2;
        }
        try {
            GLES20.glBindTexture(3553, i);
            GLES20.glTexParameterf(3553, 10241, nkcVar.f15340a);
            GLES20.glTexParameterf(3553, 10240, nkcVar.c);
            GLES20.glTexParameterf(3553, 10242, nkcVar.d);
            GLES20.glTexParameterf(3553, 10243, nkcVar.b);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            boolean z2 = nkcVar.e;
            njw.c(str, "initTextureID useMipmap=" + z2);
            if (z2) {
                GLES20.glGenerateMipmap(3553);
            }
            if (z) {
                bitmap.recycle();
            }
            GLES20.glFinish();
            medalViewCallback.onResponse(200, "initTextureID texImage2D success.");
            return i;
        } catch (Exception e3) {
            e = e3;
            i2 = i;
            njw.c(e, "initTextureID Exception error.");
            medalViewCallback.onResponse(-1, e.getMessage());
            return i2;
        }
    }

    public static void cwv_(Bitmap bitmap, MedalViewCallback medalViewCallback) {
        njw.c(e, "changeTextureImage");
        try {
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            GLES20.glFinish();
            medalViewCallback.onResponse(200, "changeTextureImage texImage2D success.");
        } catch (Exception e2) {
            njw.c(e, "changeTextureImage Exception error.");
            medalViewCallback.onResponse(-1, e2.getMessage());
        }
    }
}
