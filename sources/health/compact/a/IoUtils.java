package health.compact.a;

import com.huawei.haf.common.os.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class IoUtils {
    private IoUtils() {
    }

    public static void e(Closeable closeable) {
        FileUtils.d(closeable);
    }

    public static InputStream e(byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }
}
