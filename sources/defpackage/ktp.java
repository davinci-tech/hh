package defpackage;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.hwlogsmodel.impl.writer.bytes.IBytesWriter;
import com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler;

/* loaded from: classes5.dex */
public class ktp extends FlushTickFileHandler {
    private IBytesWriter c;

    @Override // com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler
    public void onFlushLog() {
    }

    public ktp(Looper looper) {
        super(looper);
        this.c = new ktt();
    }

    public void e() {
        this.c.clearLogCache();
    }

    public void c(byte[] bArr, String str) {
        saveLogObj(bArr, str);
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler
    public void dataTicker(Object obj, String str) {
        LogConfig e;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (!TextUtils.isEmpty(str)) {
                e = LogUtil.d(str);
            } else {
                e = LogUtil.e();
            }
            if (this.c.write(e.k(), bArr, true)) {
                return;
            }
            Log.w("BytesLogFileHandler", "writer.write() in Handler failed");
            return;
        }
        Log.e("BytesLogFileHandler", "instanceof error");
    }
}
