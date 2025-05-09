package defpackage;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.manager.TrackAltitudeMgr;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class gwy {
    private static int e = -1;

    public static double[] b(double d, double d2, double d3, double d4) {
        return new double[]{(((d4 - d2) * 3.141592653589793d) / 180.0d) * 6378245.0d * Math.cos((d * 3.141592653589793d) / 180.0d), (((d3 - d) * 3.141592653589793d) / 180.0d) * 6378245.0d};
    }

    public static void d(String str, int i, int i2, double d, double d2, double d3, int i3, int[] iArr) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_GPSFileUtils", "saveFileDataHead fileName is empty! ");
            return;
        }
        e = i;
        String str2 = b() + "/tracktest";
        File file = new File(str2);
        if (!file.exists()) {
            LogUtil.a("Track_GPSFileUtils", "saveFileDataHead mkdir result ", Boolean.valueOf(file.mkdirs()));
        }
        String str3 = str2 + "/" + str;
        File file2 = new File(str3);
        try {
            if (!file2.exists()) {
                LogUtil.a("Track_GPSFileUtils", "saveFileDataHead createNewFile result ", Boolean.valueOf(file2.createNewFile()));
            }
            sqc.c(file2.getCanonicalPath(), "S2", 0);
        } catch (IOException e2) {
            LogUtil.b("Track_GPSFileUtils", "saveFileDataHead IOException ", LogAnonymous.b((Throwable) e2));
        }
        LogUtil.a("Track_GPSFileUtils", "saveFileDataHead createFile result ", Boolean.valueOf(a(str3, i, i2, d, d2, d3, i3, iArr)));
    }

    public static void b(String str, short s, short s2, float f, float f2, int i, int i2, int i3, short s3, short s4) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_GPSFileUtils", "saveFileData fileName is empty! ");
            return;
        }
        String str2 = b() + "/tracktest";
        File file = new File(str2);
        if (!file.exists()) {
            LogUtil.a("Track_GPSFileUtils", "saveFileData mkdir result ", Boolean.valueOf(file.mkdirs()));
        }
        String str3 = str2 + "/" + str;
        File file2 = new File(str3);
        try {
            if (!file2.exists()) {
                LogUtil.a("Track_GPSFileUtils", "saveFileData createNewFile result ", Boolean.valueOf(file2.createNewFile()));
            }
            sqc.c(file2.getCanonicalPath(), "S2", 0);
        } catch (IOException e2) {
            LogUtil.b("Track_GPSFileUtils", "saveFileData IOException ", LogAnonymous.b((Throwable) e2));
        }
        e(str3, s, s2, f, f2, i, i2, i3, s3, s4);
    }

    private static boolean a(String str, int i, int i2, double d, double d2, double d3, int i3, int[] iArr) {
        try {
            gvw gvwVar = new gvw(new DataOutputStream(new FileOutputStream(str, true)));
            try {
                gvwVar.write(i);
                gvwVar.e(i2);
                gvwVar.b(Double.doubleToLongBits(d));
                gvwVar.b(Double.doubleToLongBits(d2));
                if (e == 3) {
                    gvwVar.b(Double.doubleToLongBits(d3));
                    gvwVar.e(i3);
                }
                for (int i4 : iArr) {
                    gvwVar.write(i4);
                }
                gvwVar.close();
                return true;
            } finally {
            }
        } catch (IOException e2) {
            LogUtil.b("Track_GPSFileUtils", "createFile error :", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    private static boolean e(String str, short s, short s2, float f, float f2, int i, int i2, int i3, short s3, short s4) {
        try {
            gvw gvwVar = new gvw(new DataOutputStream(new FileOutputStream(str, true)));
            try {
                gvwVar.b((int) s);
                gvwVar.b((int) s2);
                gvwVar.e(Float.floatToIntBits(f));
                gvwVar.e(Float.floatToIntBits(f2));
                gvwVar.write(i);
                gvwVar.write(i2);
                gvwVar.write(i3);
                if (e == 3) {
                    gvwVar.b((int) s3);
                    gvwVar.b((int) s4);
                }
                ReleaseLogUtil.e("Track_GPSFileUtils", "saveGpsPath filePath ", str);
                gvwVar.close();
                return true;
            } finally {
            }
        } catch (IOException e2) {
            LogUtil.b("Track_GPSFileUtils", "createFile error :", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    public static String b() {
        if (!CommonUtil.ar() && (CompileParameterUtil.a("IS_DEBUG_VERSION") || (CommonUtil.as() && d()))) {
            if (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) && !PermissionUtil.c()) {
                return Environment.getExternalStorageDirectory().getPath();
            }
            return LogConfig.m();
        }
        return LogConfig.m();
    }

    private static boolean d() {
        return new TrackAltitudeMgr(BaseApplication.e()).b();
    }

    public static void a() {
        File file = new File(b() + "/tracktest");
        if (file.exists()) {
            e(file);
        }
    }

    private static void e(File file) {
        boolean delete;
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 20) {
            LogUtil.h("Track_GPSFileUtils", "deleteOldFile files is null or less than 20 files.");
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                e(file2);
                delete = file2.delete();
            } else {
                delete = file2.delete();
            }
            LogUtil.a("Track_GPSFileUtils", "deleteOldFile:", Boolean.valueOf(delete));
        }
    }

    public static boolean e(long j, int i) {
        if (i == 1) {
            LogUtil.h("Track_GPSFileUtils", "isExecutedTrackProcess false");
            return false;
        }
        LogUtil.h("Track_GPSFileUtils", "isExecutedTrackProcess sportDuration ", Long.valueOf(j));
        return j <= AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL;
    }

    public static boolean e(int i) {
        if (Utils.o() || gtx.c(BaseApplication.e()).k() != -1 || gtx.c(BaseApplication.e()).aq()) {
            return false;
        }
        if (i == 258 || i == 257) {
            return !CommonUtil.bv() || ntf.b().e();
        }
        return false;
    }

    public static void e(String str, String str2, Context context) {
        if ((CommonUtil.as() || CommonUtil.aq()) && !TextUtils.isEmpty(str)) {
            String str3 = b() + "/tracktest";
            File file = new File(str3);
            if (!file.exists()) {
                LogUtil.a("Track_GPSFileUtils", "saveFileData mkdir result ", Boolean.valueOf(file.mkdirs()));
            }
            String str4 = str3 + "/higeo_" + str + ".txt";
            File file2 = new File(str4);
            try {
                if (!file2.exists()) {
                    LogUtil.a("Track_GPSFileUtils", "saveFileData createNewFile result ", Boolean.valueOf(file2.createNewFile()));
                }
                sqc.c(file2.getCanonicalPath(), "S2", 0);
            } catch (IOException e2) {
                LogUtil.b("Track_GPSFileUtils", "saveFileData IOException ", LogAnonymous.b((Throwable) e2));
            }
            try {
                gvw gvwVar = new gvw(new DataOutputStream(new FileOutputStream(str4, true)));
                try {
                    gvwVar.writeBytes(str2);
                    gvwVar.flush();
                    gvwVar.close();
                } catch (Throwable th) {
                    try {
                        gvwVar.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused) {
                LogUtil.a("Track_GPSFileUtils", "fileNotFound");
            } catch (IOException e3) {
                LogUtil.a("Track_GPSFileUtils", "saveDisAndPaceToFile", e3.getMessage());
            }
            LogUtil.c("Track_GPSFileUtils", "saveDisAndPaceToFile success");
        }
    }
}
