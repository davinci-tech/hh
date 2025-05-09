package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.zip.ZipInputStream;

/* loaded from: classes7.dex */
public class rei {
    public static final void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "closeIOException:", e.getMessage());
            }
        }
    }

    public static final void b(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "OutputStream IOException:", e.getMessage());
            }
        }
    }

    public static final void d(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public static final void c(OutputStreamWriter outputStreamWriter) {
        if (outputStreamWriter != null) {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "IOException :" + e.getMessage());
            }
        }
    }

    public static final void c(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    public static final void b(InputStreamReader inputStreamReader) {
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "IOException :", e.getMessage());
            }
        }
    }

    public static final void a(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "FileOutputStream IOException:", e.getMessage());
            }
        }
    }

    public static final void d(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "FileInputStream IOException:", e.getMessage());
            }
        }
    }

    public static final void a(ZipInputStream zipInputStream) {
        if (zipInputStream != null) {
            try {
                zipInputStream.close();
            } catch (IOException e) {
                LogUtil.b("UIDV_CloseUtil", "IOException:", e.getMessage());
            }
        }
    }
}
