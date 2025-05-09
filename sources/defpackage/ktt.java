package defpackage;

import android.util.Log;
import com.huawei.hwlogsmodel.impl.writer.bytes.IBytesWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class ktt implements IBytesWriter {
    @Override // com.huawei.hwlogsmodel.impl.writer.bytes.IBytesWriter
    public void clearLogCache() {
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.bytes.IBytesWriter
    public void flush(File file, boolean z) {
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.bytes.IBytesWriter
    public boolean write(File file, byte[] bArr, boolean z) {
        if (bArr == null) {
            Log.w("LogUtil_BytesWriterImpl", "message is null, write failed");
            return false;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, z), StandardCharsets.UTF_8));
            try {
                bufferedWriter.write(Arrays.toString(bArr));
                bufferedWriter.close();
                return true;
            } finally {
            }
        } catch (IOException unused) {
            Log.w("LogUtil_BytesWriterImpl", "writeFile IOException");
            return false;
        }
    }
}
