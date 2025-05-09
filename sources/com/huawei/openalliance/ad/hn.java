package com.huawei.openalliance.ad;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* loaded from: classes5.dex */
public final class hn extends hm {
    private File b;

    @Override // com.huawei.openalliance.ad.ht
    public void a(hv hvVar, int i, String str) {
        if (hvVar == null) {
            return;
        }
        a(hvVar.a() + hvVar.b());
        if (this.f6914a != null) {
            this.f6914a.a(hvVar, i, str);
        }
    }

    @Override // com.huawei.openalliance.ad.ht
    public ht a(String str, String str2) {
        String str3;
        if (str2 == null || str2.isEmpty()) {
            Log.e("FileLogNode", "Failed to initialize the file logger, parameter error.");
            return this;
        }
        if (this.b == null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    File canonicalFile = new File(str, "Log").getCanonicalFile();
                    if (canonicalFile.isDirectory() || com.huawei.openalliance.ad.utils.ae.g(canonicalFile)) {
                        File file = new File(canonicalFile, str2 + ".log");
                        this.b = file;
                        file.setReadable(true);
                        this.b.setWritable(true);
                        this.b.setExecutable(false, false);
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

    private void c(String str) {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        OutputStreamWriter outputStreamWriter;
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(this.b, true);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    try {
                        outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
                    } catch (FileNotFoundException | IOException unused) {
                    }
                    try {
                        outputStreamWriter.write(str);
                        outputStreamWriter.flush();
                        a(outputStreamWriter);
                    } catch (FileNotFoundException | IOException unused2) {
                        outputStreamWriter2 = outputStreamWriter;
                        Log.w("FileLogNode", "Exception when writing the log file.");
                        a(outputStreamWriter2);
                        a(bufferedOutputStream);
                        a(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter2 = outputStreamWriter;
                        a(outputStreamWriter2);
                        a(bufferedOutputStream);
                        a(fileOutputStream);
                        throw th;
                    }
                } catch (FileNotFoundException | IOException unused3) {
                    bufferedOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = bufferedOutputStream;
                outputStreamWriter2 = outputStreamWriter2;
            }
        } catch (FileNotFoundException | IOException unused4) {
            fileOutputStream = null;
            bufferedOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            bufferedOutputStream = null;
        }
        a(bufferedOutputStream);
        a(fileOutputStream);
    }

    private boolean b(String str) {
        String str2;
        if (this.b.length() + str.length() <= 4194304) {
            return true;
        }
        File file = new File(this.b.getPath() + ".bak");
        if (file.exists() && !file.delete()) {
            str2 = "Cannot rename log file to bak.";
        } else {
            if (this.b.renameTo(file)) {
                return true;
            }
            str2 = "Failed to backup the log file.";
        }
        Log.w("FileLogNode", str2);
        return false;
    }

    private void a(String str) {
        if (str == null || this.b == null) {
            return;
        }
        String str2 = str + '\n';
        if (b(str2)) {
            c(str2);
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.w("FileLogNode", "Exception when closing the closeable.");
            }
        }
    }

    public static ht a() {
        return new hr(new hn());
    }

    private hn() {
    }
}
