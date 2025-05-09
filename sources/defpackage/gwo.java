package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* loaded from: classes4.dex */
public class gwo {
    private gwo() {
    }

    public static boolean a(Context context, MotionPathSimplify motionPathSimplify, String str) {
        boolean z;
        synchronized (gwo.class) {
            boolean z2 = true;
            if (context == null || str == null || motionPathSimplify == null) {
                LogUtil.a("Track_SaveFileData", "context or filename or motionPathSimplify is null");
                return false;
            }
            try {
                sqc.c(new File(context.getFilesDir(), str).getCanonicalPath(), "S2", 0);
            } catch (IOException e) {
                LogUtil.b("Track_SaveFileData", "saveSimpleFile", LogAnonymous.b((Throwable) e));
            }
            try {
                FileOutputStream openFileOutput = context.openFileOutput(str, 0);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
                    try {
                        objectOutputStream.writeObject(motionPathSimplify);
                        objectOutputStream.flush();
                        try {
                            objectOutputStream.close();
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (FileNotFoundException unused) {
                                    z = true;
                                    LogUtil.a("Track_SaveFileData", "fileNotFound");
                                    z2 = z;
                                    return z2;
                                } catch (IOException e2) {
                                    e = e2;
                                    z = true;
                                    LogUtil.a("Track_SaveFileData", "saveSimpleFile", e.getMessage());
                                    z2 = z;
                                    return z2;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = true;
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (Throwable th2) {
                                    try {
                                        th.addSuppressed(th2);
                                    } catch (FileNotFoundException unused2) {
                                        LogUtil.a("Track_SaveFileData", "fileNotFound");
                                        z2 = z;
                                        return z2;
                                    } catch (IOException e3) {
                                        e = e3;
                                        LogUtil.a("Track_SaveFileData", "saveSimpleFile", e.getMessage());
                                        z2 = z;
                                        return z2;
                                    }
                                }
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z = false;
                }
            } catch (FileNotFoundException unused3) {
                z = false;
            } catch (IOException e4) {
                e = e4;
                z = false;
            }
            return z2;
        }
    }

    public static boolean c(Context context, enc encVar, String str) {
        boolean z;
        synchronized (gwo.class) {
            boolean z2 = true;
            if (context == null || str == null || encVar == null) {
                LogUtil.a("Track_SaveFileData", "context or filename or hotPathDetailInfo is null");
                return false;
            }
            try {
                sqc.c(new File(context.getFilesDir(), str).getCanonicalPath(), "S2", 0);
            } catch (IOException e) {
                LogUtil.b("Track_SaveFileData", "savehotPathDetailInfo", LogAnonymous.b((Throwable) e));
            }
            try {
                FileOutputStream openFileOutput = context.openFileOutput(str, 0);
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
                    try {
                        objectOutputStream.writeObject(encVar);
                        objectOutputStream.flush();
                        try {
                            objectOutputStream.close();
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (FileNotFoundException unused) {
                                    z = true;
                                    LogUtil.a("Track_SaveFileData", "fileNotFound");
                                    z2 = z;
                                    return z2;
                                } catch (IOException e2) {
                                    e = e2;
                                    z = true;
                                    LogUtil.a("Track_SaveFileData", "hotPathDetailInfo", e.getMessage());
                                    z2 = z;
                                    return z2;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = true;
                            if (openFileOutput != null) {
                                try {
                                    openFileOutput.close();
                                } catch (Throwable th2) {
                                    try {
                                        th.addSuppressed(th2);
                                    } catch (FileNotFoundException unused2) {
                                        LogUtil.a("Track_SaveFileData", "fileNotFound");
                                        z2 = z;
                                        return z2;
                                    } catch (IOException e3) {
                                        e = e3;
                                        LogUtil.a("Track_SaveFileData", "hotPathDetailInfo", e.getMessage());
                                        z2 = z;
                                        return z2;
                                    }
                                }
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z = false;
                }
            } catch (FileNotFoundException unused3) {
                z = false;
            } catch (IOException e4) {
                e = e4;
                z = false;
            }
            return z2;
        }
    }

    public static boolean a(Context context, MotionPath motionPath, String str) {
        FileOutputStream fileOutputStream;
        boolean z = false;
        if (context == null) {
            LogUtil.a("Track_SaveFileData", "saveMotionPath ", "context is null");
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        try {
            sqc.c(file.getCanonicalPath(), "S2", 0);
        } catch (IOException e) {
            LogUtil.b("Track_SaveFileData", "saveMotionPath()", LogAnonymous.b((Throwable) e));
        }
        try {
            fileOutputStream = new FileOutputStream(file.getPath(), true);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    try {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        if (motionPath != null) {
                            bufferedWriter.write(motionPath.toString());
                        }
                        bufferedWriter.flush();
                        ReleaseLogUtil.e("Track_SaveFileData", "saveMotionPath");
                        try {
                            bufferedWriter.close();
                            try {
                                outputStreamWriter.close();
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            z = true;
                            try {
                                outputStreamWriter.close();
                            } catch (Throwable th4) {
                                th.addSuppressed(th4);
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        try {
                            bufferedWriter.close();
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            } catch (Throwable th8) {
                th = th8;
                fileOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException e2) {
            e = e2;
        } catch (SecurityException e3) {
            e = e3;
        }
        try {
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException unused2) {
            z = true;
            LogUtil.a("Track_SaveFileData", "fileNotFound");
            return z;
        } catch (IOException e4) {
            e = e4;
            z = true;
            LogUtil.b("Track_SaveFileData", "1 saveMotionPath()", e.getMessage());
            return z;
        } catch (SecurityException e5) {
            e = e5;
            z = true;
            LogUtil.a("Track_SaveFileData", "1 saveMotionPath()", e.getMessage());
            return z;
        }
    }

    public static boolean b(Context context, String str, String str2) {
        boolean z = false;
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.b("Track_SaveFileData", "saveContentFile context or tempString is null");
            return false;
        }
        File file = new File(context.getFilesDir(), str2);
        try {
            sqc.c(file.getCanonicalPath(), "S2", 0);
        } catch (IOException e) {
            LogUtil.b("Track_SaveFileData", "saveContentFile()", LogAnonymous.b((Throwable) e));
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    try {
                        if (file.getParentFile() != null && !file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        bufferedWriter.write(str);
                        bufferedWriter.flush();
                        try {
                            bufferedWriter.close();
                            try {
                                outputStreamWriter.close();
                                try {
                                    fileOutputStream.close();
                                    return true;
                                } catch (FileNotFoundException e2) {
                                    e = e2;
                                    z = true;
                                    LogUtil.b("Track_SaveFileData", "saveContentFile()", LogAnonymous.b((Throwable) e));
                                    return z;
                                } catch (IOException e3) {
                                    e = e3;
                                    z = true;
                                    LogUtil.b("Track_SaveFileData", "saveContentFile()", LogAnonymous.b((Throwable) e));
                                    return z;
                                } catch (SecurityException e4) {
                                    e = e4;
                                    z = true;
                                    LogUtil.b("Track_SaveFileData", "saveContentFile()", LogAnonymous.b((Throwable) e));
                                    return z;
                                }
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            z = true;
                            try {
                                outputStreamWriter.close();
                            } catch (Throwable th4) {
                                th.addSuppressed(th4);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Throwable th5) {
                    th = th5;
                    outputStreamWriter.close();
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
        } catch (IOException e6) {
            e = e6;
        } catch (SecurityException e7) {
            e = e7;
        }
    }

    public static boolean e(Context context, ArrayList<ffn> arrayList) {
        if (koq.b(arrayList) || context == null) {
            return false;
        }
        List<String> b = gwm.b(arrayList);
        if (koq.b(b)) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        if (TextUtils.isEmpty(stringBuffer.toString())) {
            return false;
        }
        return b(context, stringBuffer.toString(), "motion_cadence.txt");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0114 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.BufferedWriter] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.lang.Long r13, android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwo.c(java.lang.Long, android.content.Context):boolean");
    }

    private static void a(BufferedWriter bufferedWriter, OutputStreamWriter outputStreamWriter, FileOutputStream fileOutputStream) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.close();
            } catch (FileNotFoundException unused) {
                LogUtil.b("Track_SaveFileData", "error 10001");
            } catch (IOException e) {
                LogUtil.b("Track_SaveFileData", "closeWriter() bufferedWriter ", e.getMessage());
            }
        }
        if (outputStreamWriter != null) {
            try {
                outputStreamWriter.close();
            } catch (FileNotFoundException unused2) {
                LogUtil.b("Track_SaveFileData", "error 10001");
            } catch (IOException e2) {
                LogUtil.b("Track_SaveFileData", "closeWriter() streamWriter ", e2.getMessage());
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (FileNotFoundException unused3) {
                LogUtil.b("Track_SaveFileData", "error 10001");
            } catch (IOException e3) {
                LogUtil.b("Track_SaveFileData", "closeWriter() fileOutputStream ", e3.getMessage());
            }
        }
    }

    private static void b(Long l, BufferedWriter bufferedWriter, BufferedReader bufferedReader) throws IOException {
        long j = 0;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                LogUtil.a("Track_SaveFileData", "readCount", Long.valueOf(j));
                return;
            } else if (l.longValue() > j) {
                bufferedWriter.write(readLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                j++;
            }
        }
    }

    public static boolean c(Context context, String str, String str2) {
        boolean z = false;
        if (context == null) {
            LogUtil.a("Track_SaveFileData", "copySimpleFile ", "context is null");
            return false;
        }
        try {
            FileInputStream openFileInput = context.openFileInput(str);
            try {
                FileOutputStream openFileOutput = context.openFileOutput(str2, 0);
                try {
                    File file = new File(context.getFilesDir(), str);
                    File file2 = new File(context.getFilesDir(), str2);
                    if ((!file.getParentFile().exists() || !file2.getParentFile().exists()) && file.getParentFile().mkdirs()) {
                        file2.getParentFile().mkdirs();
                    }
                    ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
                    objectOutputStream.writeObject((MotionPathSimplify) objectInputStream.readObject());
                    objectOutputStream.flush();
                    objectInputStream.close();
                    objectOutputStream.close();
                    z = true;
                    if (openFileOutput != null) {
                        openFileOutput.close();
                    }
                    if (openFileInput != null) {
                        openFileInput.close();
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            LogUtil.a("Track_SaveFileData", "fileNotFound");
        } catch (IOException e) {
            LogUtil.b("Track_SaveFileData", "copySimpleFile()", e.getMessage());
        } catch (ClassNotFoundException e2) {
            LogUtil.b("Track_SaveFileData", "copySimpleFile()", e2.getMessage());
        } catch (SecurityException e3) {
            LogUtil.b("Track_SaveFileData", "copySimpleFile()", e3.getMessage());
        }
        return z;
    }

    public static boolean e(Context context, String str) {
        StringBuilder sb = new StringBuilder("delete ");
        sb.append(str);
        sb.append(" ");
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            ReleaseLogUtil.d("Track_SaveFileData", "deleteFile filesDir is null");
            return false;
        }
        File file = new File(filesDir, str);
        if (file.exists()) {
            try {
                String canonicalPath = filesDir.getCanonicalPath();
                if (!sqd.e(file, canonicalPath, str)) {
                    LogUtil.h("Track_SaveFileData", "deleteFile fileName ", str, " canonicalPath ", canonicalPath, " file ", file);
                    return false;
                }
                if (!file.delete()) {
                    sb.append("failed");
                    ReleaseLogUtil.d("Track_SaveFileData", sb);
                    return false;
                }
                sb.append("success");
                ReleaseLogUtil.e("Track_SaveFileData", sb);
                return true;
            } catch (IOException e) {
                ReleaseLogUtil.c("Track_SaveFileData", "deleteFile exception ", ExceptionUtils.d(e));
                return false;
            }
        }
        sb.append("not exist");
        ReleaseLogUtil.e("Track_SaveFileData", sb);
        return false;
    }

    public static boolean b(Context context, String str, List<gyc> list) {
        boolean z = false;
        if (context == null) {
            LogUtil.a("Track_SaveFileData", "saveTrackPointData ", "context is null");
            return false;
        }
        if (koq.b(list)) {
            LogUtil.h("Track_SaveFileData", "saveTrackPointData trackPointDataList is null or empty");
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        if (!file.isFile()) {
            ReleaseLogUtil.d("Track_SaveFileData", "saveTrackPointData file is not file");
            return false;
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(file);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openOutputStream, "UTF-8");
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    try {
                        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                            LogUtil.a("Track_SaveFileData", "saveTrackPointData mkdirs failed");
                        }
                        StringBuilder sb = new StringBuilder(16);
                        for (int i = 0; i < list.size(); i++) {
                            sb.append(list.get(i).toString());
                            sb.append(System.lineSeparator());
                        }
                        bufferedWriter.write(sb.toString());
                        bufferedWriter.flush();
                        try {
                            bufferedWriter.close();
                            try {
                                outputStreamWriter.close();
                                if (openOutputStream == null) {
                                    return true;
                                }
                                try {
                                    openOutputStream.close();
                                    return true;
                                } catch (FileNotFoundException unused) {
                                    z = true;
                                    ReleaseLogUtil.c("Track_SaveFileData", "fileNotFound");
                                    return z;
                                } catch (IOException e) {
                                    e = e;
                                    z = true;
                                    ReleaseLogUtil.c("Track_SaveFileData", "saveTrackPointData ", LogAnonymous.b((Throwable) e));
                                    return z;
                                }
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                if (openOutputStream != null) {
                                    try {
                                        openOutputStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            z = true;
                            try {
                                outputStreamWriter.close();
                            } catch (Throwable th4) {
                                th.addSuppressed(th4);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Throwable th5) {
                    th = th5;
                    outputStreamWriter.close();
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (FileNotFoundException unused2) {
            ReleaseLogUtil.c("Track_SaveFileData", "fileNotFound");
            return z;
        } catch (IOException e2) {
            e = e2;
            ReleaseLogUtil.c("Track_SaveFileData", "saveTrackPointData ", LogAnonymous.b((Throwable) e));
            return z;
        }
    }

    public static void a() {
        Context context = BaseApplication.getContext();
        e(context, "motion_path2.txt");
        e(context, "simplemotion.txt");
        e(context, "simplemotion_temp.txt");
        e(context, "motion_cadence.txt");
        e(context, "target_motion_simplify.txt");
        e(context, "target_motion_path.txt");
        e(context, "hotPathDetailInfo.txt");
        e(context, "motion_path_process.txt");
    }

    public static void d(Context context, String str, String str2) {
        if (context == null) {
            ReleaseLogUtil.c("Track_SaveFileData", "context is null");
            return;
        }
        File file = new File(context.getFilesDir(), str);
        File file2 = new File(context.getFilesDir(), str2);
        if (d(file, file2)) {
            return;
        }
        try {
            com.huawei.haf.common.os.FileUtils.d(file, file2);
        } catch (IOException e) {
            LogUtil.h("Track_SaveFileData", "copy file failed : IOException", e.toString());
        }
    }

    public static boolean e(Context context, String str, String str2) {
        if (context == null) {
            ReleaseLogUtil.c("Track_SaveFileData", "context is null");
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        File file2 = new File(context.getFilesDir(), str2);
        if (d(file, file2)) {
            return false;
        }
        try {
            com.huawei.haf.common.os.FileUtils.d(file, file2);
            return true;
        } catch (IOException e) {
            LogUtil.h("Track_SaveFileData", "copy file failed :", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private static boolean d(File file, File file2) {
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            ReleaseLogUtil.c("Track_SaveFileData", "copyFile failed, fromFile is null, or is not exists or is not fileor cannot read, or toFile's parentFile is null");
            return true;
        }
        if (file2.getParentFile() == null) {
            ReleaseLogUtil.c("Track_SaveFileData", "copyFile failed, toFile.getParentFile() is null");
            return true;
        }
        if (!file2.getParentFile().exists() && !file2.getParentFile().mkdirs()) {
            ReleaseLogUtil.c("Track_SaveFileData", "copyFile : make parentFile dir failed");
            return true;
        }
        if (!file2.exists() || file2.delete()) {
            return false;
        }
        ReleaseLogUtil.c("Track_SaveFileData", "copyFile : delete origin file failed");
        return true;
    }
}
