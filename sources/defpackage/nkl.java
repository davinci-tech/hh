package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes9.dex */
public class nkl {
    public static FloatBuffer e(float[] fArr) {
        if (fArr == null) {
            njw.c("ArrayToBufferHelper", "floatArrayToBuffer arrays should not be null.");
            throw new RuntimeException("ArrayToBufferHelper floatArrayToBuffer arrays should not be null");
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }
}
