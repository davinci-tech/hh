package androidx.core.util;

import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.ueu;
import defpackage.uhx;
import defpackage.uhy;
import defpackage.uju;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a3\u0010\u0007\u001a\u00020\b*\u00020\u00022!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\nH\u0087\bø\u0001\u0000\u001a\u0014\u0010\u000f\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001H\u0007\u001a\u001e\u0010\u0011\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"readBytes", "", "Landroid/util/AtomicFile;", "readText", "", "charset", "Ljava/nio/charset/Charset;", "tryWrite", "", "block", "Lkotlin/Function1;", "Ljava/io/FileOutputStream;", "Lkotlin/ParameterName;", "name", "out", "writeBytes", "array", "writeText", Constant.TEXT, "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class AtomicFileKt {
    public static final void tryWrite(android.util.AtomicFile atomicFile, Function1<? super FileOutputStream, ueu> function1) {
        uhy.e((Object) atomicFile, "");
        uhy.e((Object) function1, "");
        FileOutputStream startWrite = atomicFile.startWrite();
        try {
            uhy.a(startWrite, "");
            function1.invoke(startWrite);
            uhx.a(1);
            atomicFile.finishWrite(startWrite);
            uhx.b(1);
        } catch (Throwable th) {
            uhx.a(1);
            atomicFile.failWrite(startWrite);
            uhx.b(1);
            throw th;
        }
    }

    public static /* synthetic */ void writeText$default(android.util.AtomicFile atomicFile, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = uju.h;
        }
        writeText(atomicFile, str, charset);
    }

    public static final void writeText(android.util.AtomicFile atomicFile, String str, Charset charset) {
        uhy.e((Object) atomicFile, "");
        uhy.e((Object) str, "");
        uhy.e((Object) charset, "");
        byte[] bytes = str.getBytes(charset);
        uhy.a(bytes, "");
        writeBytes(atomicFile, bytes);
    }

    public static final byte[] readBytes(android.util.AtomicFile atomicFile) {
        uhy.e((Object) atomicFile, "");
        byte[] readFully = atomicFile.readFully();
        uhy.a(readFully, "");
        return readFully;
    }

    public static /* synthetic */ String readText$default(android.util.AtomicFile atomicFile, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = uju.h;
        }
        return readText(atomicFile, charset);
    }

    public static final String readText(android.util.AtomicFile atomicFile, Charset charset) {
        uhy.e((Object) atomicFile, "");
        uhy.e((Object) charset, "");
        byte[] readFully = atomicFile.readFully();
        uhy.a(readFully, "");
        return new String(readFully, charset);
    }

    public static final void writeBytes(android.util.AtomicFile atomicFile, byte[] bArr) {
        uhy.e((Object) atomicFile, "");
        uhy.e((Object) bArr, "");
        FileOutputStream startWrite = atomicFile.startWrite();
        try {
            uhy.a(startWrite, "");
            startWrite.write(bArr);
            atomicFile.finishWrite(startWrite);
        } catch (Throwable th) {
            atomicFile.failWrite(startWrite);
            throw th;
        }
    }
}
