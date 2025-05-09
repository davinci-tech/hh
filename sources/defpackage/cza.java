package defpackage;

import android.os.Environment;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

/* loaded from: classes3.dex */
public class cza {
    public static void a(int i, String str) {
        File e;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            if (i == 1) {
                e = e("LogDeviceDotting");
            } else {
                e = i != 2 ? null : e("LogDeviceCrash");
            }
            e(str, e);
        }
    }

    private static File e(String str) {
        String str2;
        if (BaseApplication.vZ_().getExternalFilesDir(null) != null) {
            str2 = BaseApplication.vZ_().getExternalFilesDir(null) + File.separator + str + File.separator;
        } else {
            str2 = BaseApplication.vZ_().getFilesDir() + File.separator + str + File.separator;
        }
        LogUtil.a("FileUtils", "dir path is : ", str2);
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(str2, "debugLog.txt");
    }

    public static void e(String str, File file) {
        OutputStreamWriter outputStreamWriter;
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            } catch (Throwable th) {
                th = th;
                outputStreamWriter = outputStreamWriter2;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
            IOUtils.closeQuietly((Writer) outputStreamWriter);
        } catch (IOException e2) {
            e = e2;
            outputStreamWriter2 = outputStreamWriter;
            LogUtil.b("FileUtils", "writeJsonFile IOException ", e.getMessage());
            IOUtils.closeQuietly((Writer) outputStreamWriter2);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly((Writer) outputStreamWriter);
            throw th;
        }
    }
}
