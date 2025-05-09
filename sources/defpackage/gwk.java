package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* loaded from: classes.dex */
public class gwk {
    public static MotionPathSimplify d(Context context) {
        if (context == null) {
            LogUtil.a("Track_ReadTrackFile", "readSimpleMotionPath ", "context is null");
            return null;
        }
        MotionPathSimplify e = e(context, "simplemotion.txt");
        if (e != null) {
            return e;
        }
        LogUtil.a("Track_ReadTrackFile", "MotionPathSimplify is wrong, try temp file");
        return e(context, "simplemotion_temp.txt");
    }

    public static MotionPathSimplify e(Context context, String str) {
        return (MotionPathSimplify) d(context, str, MotionPathSimplify.class);
    }

    public static enc a(Context context, String str) {
        return (enc) d(context, str, enc.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0145 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> T d(android.content.Context r10, java.lang.String r11, java.lang.Class<T> r12) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gwk.d(android.content.Context, java.lang.String, java.lang.Class):java.lang.Object");
    }

    public static MotionPath a(Context context) {
        return c(context, 0);
    }

    public static MotionPath c(Context context, int i) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        MotionPath motionPath = null;
        if (context == null) {
            LogUtil.a("Track_ReadTrackFile", "readMotionPath2Data ", "context is null");
            return null;
        }
        File file = new File(context.getFilesDir(), "motion_path2.txt");
        try {
            fileInputStream = new FileInputStream(file);
            try {
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        stringBuffer = new StringBuffer(16);
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            LogUtil.a("Track_ReadTrackFile", "readMotionPath2Data ", LogAnonymous.b((Throwable) e));
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.a("Track_ReadTrackFile", "readMotionPath2Data ", LogAnonymous.b((Throwable) e2));
        }
        if (file.exists()) {
            stringBuffer.append(e(inputStreamReader));
            motionPath = gvz.a(stringBuffer.toString(), i);
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return motionPath;
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return null;
    }

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.a("Track_ReadTrackFile", "isMotionPathFileExisted ", "context is null");
            return false;
        }
        return new File(context.getFilesDir(), "motion_path2.txt").exists();
    }

    public static MotionPath c(Context context, String str) {
        return c(context, str, 0);
    }

    public static MotionPath c(Context context, String str, int i) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        MotionPath motionPath = null;
        if (context == null) {
            LogUtil.a("Track_ReadTrackFile", "readTemporaryMotionPath ", "context is null");
            return null;
        }
        try {
            String c = CommonUtil.c(context.getFilesDir().getCanonicalPath() + File.separator + str);
            if (TextUtils.isEmpty(c)) {
                LogUtil.h("Track_ReadTrackFile", "readTemporaryMotionPath savePath is empty");
                return null;
            }
            File file = new File(c);
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        try {
                            stringBuffer = new StringBuffer(16);
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused) {
                LogUtil.a("Track_ReadTrackFile", "fileNotFound");
            } catch (IOException e) {
                LogUtil.a("Track_ReadTrackFile", "readTemporaryMotionPath ", LogAnonymous.b((Throwable) e));
            } catch (IndexOutOfBoundsException e2) {
                LogUtil.a("Track_ReadTrackFile", "readTemporaryMotionPath ", e2.getMessage());
            }
            if (c.equals(CommonUtil.d(c))) {
                if (file.exists() && sqc.a(file, c)) {
                    stringBuffer.append(e(inputStreamReader));
                    motionPath = gvz.a(stringBuffer.toString(), i);
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return motionPath;
                }
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                return null;
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return null;
        } catch (IOException e3) {
            LogUtil.b("Track_ReadTrackFile", LogAnonymous.b((Throwable) e3));
            return null;
        }
    }

    public static List<gyc> d(Context context, String str) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        List<gyc> list = null;
        if (context == null) {
            LogUtil.a("Track_ReadTrackFile", "readTrackPointData", "context is null");
            return null;
        }
        String str2 = context.getFilesDir().getPath() + File.separator + str;
        File file = new File(str2);
        try {
            fileInputStream = new FileInputStream(file);
            try {
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        stringBuffer = new StringBuffer(16);
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            LogUtil.a("Track_ReadTrackFile", "fileNotFound");
        } catch (IOException e) {
            LogUtil.a("Track_ReadTrackFile", "readTrackPointData ", LogAnonymous.b((Throwable) e));
            gwo.e(context, str);
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.a("Track_ReadTrackFile", "readTrackPointData ", e2.getMessage());
        }
        if (str2.equals(CommonUtil.d(str2))) {
            if (file.exists() && sqc.a(file, str2)) {
                if (file.length() > 102400) {
                    LogUtil.h("Track_ReadTrackFile", "The size of file is too large,delete it");
                    gwo.e(context, str);
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return null;
                }
                stringBuffer.append(e(inputStreamReader));
                list = gvz.b(stringBuffer.toString());
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                return list;
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return null;
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return null;
    }

    private static String e(InputStreamReader inputStreamReader) {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder(16);
        while (true) {
            try {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
            } catch (FileNotFoundException unused) {
                LogUtil.a("Track_ReadTrackFile", "fileNotFound");
            } catch (IOException e) {
                LogUtil.a("Track_ReadTrackFile", "read Line Result exception: ", LogAnonymous.b((Throwable) e));
            }
        }
        return sb.toString();
    }

    public static List<ffn> b(Context context) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        List<ffn> list = null;
        if (context == null) {
            LogUtil.b("Track_ReadTrackFile", "readMotionPathToRidePosture context is null");
            return null;
        }
        File file = new File(context.getFilesDir(), "motion_cadence.txt");
        try {
            fileInputStream = new FileInputStream(file);
            try {
                inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        stringBuffer = new StringBuffer(16);
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            LogUtil.b("Track_ReadTrackFile", "readMotionPathToRidePosture ", LogAnonymous.b((Throwable) e));
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.b("Track_ReadTrackFile", "readMotionPathToRidePosture ", LogAnonymous.b((Throwable) e2));
        }
        if (file.exists()) {
            stringBuffer.append(e(inputStreamReader));
            list = gvz.e(stringBuffer.toString());
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return list;
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return null;
    }
}
