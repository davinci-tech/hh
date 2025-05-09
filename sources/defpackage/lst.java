package defpackage;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.openplatform.abl.log.a;
import com.huawei.openplatform.abl.log.i;
import com.huawei.openplatform.abl.log.util.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* loaded from: classes5.dex */
public final class lst extends a {
    private File d;

    @Override // com.huawei.openplatform.abl.log.i
    public void a(lta ltaVar, int i, String str) {
        if (ltaVar == null) {
            return;
        }
        b(ltaVar.c() + ltaVar.e());
        i iVar = this.f8220a;
        if (iVar != null) {
            iVar.a(ltaVar, i, str);
        }
    }

    @Override // com.huawei.openplatform.abl.log.i
    public i a(String str, String str2) {
        String str3;
        if (str2 == null || str2.isEmpty()) {
            Log.e("FileLogNode", "Failed to initialize the file logger, parameter error.");
            return this;
        }
        if (this.d == null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    File canonicalFile = new File(str, "Log").getCanonicalFile();
                    if (canonicalFile.isDirectory() || c.d(canonicalFile)) {
                        File file = new File(canonicalFile, str2 + ".log");
                        this.d = file;
                        file.setReadable(true);
                        this.d.setWritable(true);
                        this.d.setExecutable(false, false);
                        return this;
                    }
                }
            } catch (IOException unused) {
                str3 = "file path error. ioex";
                Log.e("FileLogNode", str3);
                Log.w("FileLogNode", "the file logger has been created already.");
                return this;
            } catch (Throwable th) {
                str3 = "file path error. " + th.getClass().getSimpleName();
                Log.e("FileLogNode", str3);
                Log.w("FileLogNode", "the file logger has been created already.");
                return this;
            }
        }
        Log.w("FileLogNode", "the file logger has been created already.");
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(String str) {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3;
        OutputStream outputStream4;
        OutputStream outputStream5;
        OutputStream outputStream6;
        OutputStreamWriter outputStreamWriter;
        OutputStream outputStream7 = null;
        try {
            outputStream4 = new FileOutputStream(this.d, true);
            try {
                outputStream5 = new BufferedOutputStream(outputStream4);
                try {
                    outputStreamWriter = new OutputStreamWriter(outputStream5, "UTF-8");
                } catch (FileNotFoundException | IOException unused) {
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException | IOException unused2) {
                outputStream5 = null;
            } catch (Throwable th2) {
                th = th2;
                outputStream5 = null;
            }
        } catch (FileNotFoundException | IOException unused3) {
            outputStream3 = null;
            outputStream4 = null;
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            outputStream2 = null;
            OutputStream outputStream8 = outputStream;
            outputStream4 = outputStream7;
            outputStream7 = outputStream2;
            outputStream5 = outputStream8;
            b(outputStream7);
            b(outputStream5);
            b(outputStream4);
            throw th;
        }
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
            outputStream6 = outputStreamWriter;
        } catch (FileNotFoundException | IOException unused4) {
            outputStream7 = outputStreamWriter;
            outputStream3 = outputStream7;
            outputStream7 = outputStream5;
            try {
                Log.w("FileLogNode", "Exception when writing the log file.");
                outputStream6 = outputStream3;
                outputStream5 = outputStream7;
                b(outputStream6);
                b(outputStream5);
                b(outputStream4);
            } catch (Throwable th4) {
                outputStream2 = outputStream3;
                th = th4;
                OutputStream outputStream9 = outputStream4;
                outputStream = outputStream7;
                outputStream7 = outputStream9;
                OutputStream outputStream82 = outputStream;
                outputStream4 = outputStream7;
                outputStream7 = outputStream2;
                outputStream5 = outputStream82;
                b(outputStream7);
                b(outputStream5);
                b(outputStream4);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            outputStream7 = outputStreamWriter;
            b(outputStream7);
            b(outputStream5);
            b(outputStream4);
            throw th;
        }
        b(outputStream6);
        b(outputStream5);
        b(outputStream4);
    }

    private void b(String str) {
        if (str == null || this.d == null) {
            return;
        }
        String str2 = str + '\n';
        if (e(str2)) {
            a(str2);
        }
    }

    private boolean e(String str) {
        String str2;
        if (this.d.length() + str.length() <= 4194304) {
            return true;
        }
        File file = new File(this.d.getPath() + ".bak");
        if (file.exists() && !file.delete()) {
            str2 = "Cannot rename log file to bak.";
        } else {
            if (this.d.renameTo(file)) {
                return true;
            }
            str2 = "Failed to backup the log file.";
        }
        Log.w("FileLogNode", str2);
        return false;
    }

    private static void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.w("FileLogNode", "Exception when closing the closeable.");
            }
        }
    }

    public static i d() {
        return new lsw(new lst());
    }

    private lst() {
    }
}
