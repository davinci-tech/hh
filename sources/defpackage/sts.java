package defpackage;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;

/* loaded from: classes7.dex */
public final class sts {

    /* renamed from: a, reason: collision with root package name */
    private static int f17227a = 204800;
    private static boolean b = false;
    private static String c = null;
    private static int d = 3;
    private static String e = null;
    private static BufferedWriter f = null;
    private static boolean h = false;

    public static void e(int i, String str, int i2, boolean z) throws IOException {
        h = z;
        if (i > 0) {
            f17227a = i;
        }
        if (i2 > 0) {
            if (i2 > 50) {
                i2 = 50;
            }
            d = i2;
        }
        if (str != null) {
            e = str;
            b = true;
        } else {
            b = false;
        }
        Log.i("LogWrite", "fileLoggerEnable: " + b);
        if (b) {
            File file = new File(e);
            if (!file.exists() && !file.mkdirs()) {
                Log.e("LogWrite", "createFolder fail");
            } else {
                d(e, a());
            }
        }
    }

    private static void d(String str, String str2) throws IOException {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e("LogWrite", "createNewLogFile Exception");
            return;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length >= d) {
            try {
                Arrays.sort(listFiles, new a());
            } catch (IllegalArgumentException unused) {
                Log.e("LogWrite", "Arrays sort IllegalArgumentException");
            }
            a(listFiles);
        }
        synchronized (sts.class) {
            BufferedWriter bufferedWriter = f;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException unused2) {
                    Log.e("LogWrite", "createNewLogFile close IOException");
                }
            }
            try {
                fileOutputStream = new FileOutputStream(new File(str, str2));
            } catch (Exception unused3) {
                Log.e("LogWrite", "createNewLogFile IOException");
            }
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                try {
                    f = new BufferedWriter(outputStreamWriter);
                    c = str2;
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private static boolean a(File[] fileArr) {
        if (fileArr == null || fileArr.length <= 0) {
            return false;
        }
        return fileArr[0].delete();
    }

    private static void a(String str, String str2) throws IOException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e("LogWrite", "openLogFile Exception");
            return;
        }
        synchronized (sts.class) {
            BufferedWriter bufferedWriter = f;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException unused) {
                    Log.e("LogWrite", "createNewLogFile close IOException");
                }
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str, str2), true);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                    try {
                        f = new BufferedWriter(outputStreamWriter);
                        c = str2;
                        outputStreamWriter.close();
                        fileOutputStream.close();
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                Log.e("LogWrite", "openLogFile  Exception");
            }
        }
    }

    private static String a() {
        String format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
        return Process.myPid() + "_" + format + ".log";
    }

    public static void b() {
        synchronized (sts.class) {
            BufferedWriter bufferedWriter = f;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException unused) {
                    Log.e("LogWrite", "shutdown IOException");
                }
            }
            f = null;
        }
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        synchronized (sts.class) {
            try {
                try {
                } catch (IOException unused) {
                    Log.e("LogWrite", "wtf IOException");
                }
                if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(c)) {
                    if (new File(e, c).length() > f17227a) {
                        d(e, a());
                    } else if (f == null) {
                        a(e, c);
                    }
                    f.append((CharSequence) String.format(Locale.ENGLISH, "%s: %s/%s: %s", c(), str, str2, str3 + '\n' + Log.getStackTraceString(th)));
                    f.flush();
                    return;
                }
                Log.e("LogWrite", "wtf Exception");
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private static String c() {
        return new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    static class a implements Comparator<File>, Serializable {
        private static final long serialVersionUID = 1;

        private a() {
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }
}
