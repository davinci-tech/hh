package health.compact.a;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler;
import com.huawei.hwlogsmodel.impl.writer.str.IStrWriter;

/* loaded from: classes.dex */
public class StrLogFileHandler extends FlushTickFileHandler {
    private StrLogImpl b;
    private IStrWriter e;

    public StrLogFileHandler(Looper looper, StrLogImpl strLogImpl) {
        super(looper);
        this.e = new StrWriterImpl();
        this.b = strLogImpl;
    }

    public void e(String str) {
        saveLogObj(str);
    }

    public void e() {
        this.e.clearLogCache();
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler
    public void dataTicker(Object obj, String str) {
        com.huawei.hwlogsmodel.common.LogConfig e;
        if (obj instanceof String) {
            String str2 = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                e = com.huawei.hwlogsmodel.LogUtil.d(str);
            } else {
                e = com.huawei.hwlogsmodel.LogUtil.e();
            }
            if (this.e.write(e.k(), str2, true)) {
                return;
            }
            Log.w("LogUtil_LogFileHandler", "writer.write() in Handler failed");
            return;
        }
        Log.e("LogUtil_LogFileHandler", "instanceof error");
    }

    @Override // com.huawei.hwlogsmodel.impl.writer.common.FlushTickFileHandler
    public void onFlushLog() {
        this.b.a();
    }
}
