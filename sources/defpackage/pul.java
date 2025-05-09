package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
public class pul {
    private static final File b;
    private static String c = "";
    private static String d = "";

    static {
        try {
            c = CommonUtil.j(BaseApplication.getContext()).getCanonicalPath() + "/Android/data/";
            d = CommonUtil.j(BaseApplication.getContext()).getCanonicalPath() + "/Huawei/Health/";
        } catch (IOException unused) {
            LogUtil.b("FileConfig", "IOException");
        }
        b = BaseApplication.getContext().getFilesDir();
    }

    private static String h() {
        return c + sqq.e() + "/files";
    }

    private static String d() {
        return h() + "/audiosBase/";
    }

    private static String j() {
        return h() + "/baseXML/";
    }

    private static String f() {
        return d + "/fitfiles";
    }

    private static String b() {
        return f() + "/audiosBase/";
    }

    private static String i() {
        return f() + "/baseXML/";
    }

    private static String g() {
        String str;
        try {
            str = b.getCanonicalPath();
        } catch (IOException e) {
            LogUtil.b("FileConfig", e.getMessage());
            str = "";
        }
        return str + "/stress";
    }

    private static String a() {
        return g() + "/audiosBase/";
    }

    public static String e() {
        return g() + "/baseXML/";
    }

    private static String g(String str) {
        String j = j(str);
        String str2 = a() + j;
        if (b(str2) || m()) {
            return str2;
        }
        String str3 = b() + j;
        if (b(str3)) {
            return str3;
        }
        String str4 = d() + j;
        return b(str4) ? str4 : str2;
    }

    public static String d(String str) {
        return a() + j(str);
    }

    public static String a(String str) {
        String str2;
        if (pvo.a()) {
            LogUtil.a("FileConfig", "特殊手机");
            str2 = "filesServer";
        } else {
            str2 = "filesServer_old";
        }
        if (TextUtils.isEmpty(str)) {
            String concat = str2.concat(WatchFaceConstant.XML_SUFFIX);
            String str3 = e() + concat;
            if (b(str3) || m()) {
                return str3;
            }
            String str4 = i() + concat;
            if (b(str4)) {
                return str4;
            }
            String str5 = j() + concat;
            return b(str5) ? str5 : str3;
        }
        String str6 = str2 + "_" + str + WatchFaceConstant.XML_SUFFIX;
        String str7 = e() + str6;
        if (b(str7) || m()) {
            return str7;
        }
        String str8 = i() + str6;
        if (b(str8)) {
            return str8;
        }
        String str9 = j() + str6;
        return b(str9) ? str9 : str7;
    }

    private static boolean b(String str) {
        return new File(str).exists();
    }

    public static boolean c(String str, long j, String str2) {
        if (!b(str, str2)) {
            return false;
        }
        File file = new File(g(str));
        if (file.exists()) {
            return b(file, j);
        }
        return false;
    }

    private static boolean b(String str, String str2) {
        String h = h("fit_mp3_id_" + str);
        if (h != null && h.equals(str2)) {
            return true;
        }
        File file = new File(g(str));
        if (file.exists() && file.delete()) {
            LogUtil.h("FileConfig", str + ":isVaildFileUrl:delete");
        }
        d("fit_mp3_id_" + str, str2);
        return false;
    }

    public static String c(String str) {
        return j(str);
    }

    public static boolean b(File file, long j) {
        boolean z = file.lastModified() == j;
        if (!z) {
            String name = file.getName();
            LogUtil.h("FileConfig", name + ":is valid");
            if (file.delete()) {
                LogUtil.h("FileConfig", name + ":delete");
            }
        }
        return z;
    }

    private static String j(String str) {
        if (str == null) {
            LogUtil.h("FileConfig", "getFileName url is null");
            return "";
        }
        int lastIndexOf = str.lastIndexOf("/");
        int lastIndexOf2 = str.lastIndexOf("?");
        if (lastIndexOf < 0) {
            lastIndexOf = -1;
        }
        if (lastIndexOf2 <= lastIndexOf) {
            lastIndexOf2 = str.length();
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    public static boolean c() {
        return sqq.b().f();
    }

    private static boolean m() {
        return !c();
    }

    public static long e(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("stress_sp", 0).getLong(str, 0L);
    }

    private static void d(String str, String str2) {
        BaseApplication.getContext().getApplicationContext().getSharedPreferences("stress_xml_sp", 0).edit().putString(str, str2).commit();
    }

    private static String h(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("stress_xml_sp", 0).getString(str, null);
    }
}
