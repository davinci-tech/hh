package health.compact.a;

import android.util.Log;
import com.huawei.hwlogsmodel.impl.writer.str.IStrWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public class StrWriterImpl implements IStrWriter {
    @Override // com.huawei.hwlogsmodel.impl.writer.str.IStrWriter
    public void clearLogCache() {
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.str.IStrWriter
    public boolean write(File file, String str, boolean z) {
        if (str == null) {
            Log.w("LogUtil_StrWriterImpl", "write null");
            return false;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, z), StandardCharsets.UTF_8));
            try {
                bufferedWriter.write(str);
                bufferedWriter.close();
                return true;
            } finally {
            }
        } catch (IOException unused) {
            Log.w("LogUtil_StrWriterImpl", "writeFile IOException");
            return false;
        }
    }
}
