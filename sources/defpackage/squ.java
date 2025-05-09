package defpackage;

import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FileUtils;

/* loaded from: classes.dex */
public class squ {
    private static String h;
    private static final String d = CommonUtil.j(BaseApplication.getContext()).getAbsolutePath() + "/Android/data/";

    /* renamed from: a, reason: collision with root package name */
    private static final String f17223a = CommonUtil.j(BaseApplication.getContext()).getAbsolutePath() + "/Huawei/Health/";
    private static final File b = BaseApplication.getContext().getFilesDir();
    private static final String[] c = {"videos", "audios", "audiosBase", "baseXML", "subtitles"};
    private static final String[] e = {"videosEn", "audiosEn", "audiosBaseEn", "baseXML", "subtitles"};

    public static String f() {
        return ab();
    }

    public static String[] h() {
        return new String[]{aa(), ad()};
    }

    public static String[] e() {
        if (!TextUtils.isEmpty(h)) {
            List<String> ah = ah();
            if (LanguageUtil.j(BaseApplication.getContext())) {
                ah.addAll(Arrays.asList(c));
                return (String[]) ((String[]) ah.toArray(new String[ah.size()])).clone();
            }
            ah.addAll(Arrays.asList(e));
            return (String[]) ((String[]) ah.toArray(new String[ah.size()])).clone();
        }
        if (LanguageUtil.j(BaseApplication.getContext())) {
            return (String[]) c.clone();
        }
        return (String[]) e.clone();
    }

    private static String ad() {
        return d + sqq.e() + "/files";
    }

    private static String am() {
        return ad() + p();
    }

    private static String u() {
        return ad() + k();
    }

    private static String s() {
        return ad() + l();
    }

    private static String z() {
        if (!TextUtils.isEmpty(h)) {
            String str = "/baseXML" + h + "/";
            v("/baseXML" + h);
            return ad() + str;
        }
        return ad() + "/baseXML/";
    }

    private static String aa() {
        return f17223a + "/fitfiles";
    }

    private static String ak() {
        return aa() + p();
    }

    private static String x() {
        return aa() + k();
    }

    private static String r() {
        return aa() + l();
    }

    private static String w() {
        if (!TextUtils.isEmpty(h)) {
            String str = "/baseXML" + h + "/";
            v("/baseXML" + h);
            return aa() + str;
        }
        return aa() + "/baseXML/";
    }

    private static String ab() {
        if (CommonUtil.bu()) {
            return "suggestion/files";
        }
        return e(b) + "/fitness";
    }

    private static String ae() {
        return ab() + p();
    }

    private static String y() {
        return ab() + k();
    }

    private static String t() {
        return ab() + l();
    }

    private static String af() {
        return e(b) + "/suggestion/files_runcourse" + k();
    }

    private static String e(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.h("Suggestion_FileConfig", "getFileCanonicalPath", LogAnonymous.b((Throwable) e2));
            return "";
        }
    }

    private static String ac() {
        return e(b) + "/suggestion/files_runcourse" + l();
    }

    private static String v() {
        if (!TextUtils.isEmpty(h)) {
            String str = "/baseXML" + h + "/";
            v("/baseXML" + h);
            return ab() + str;
        }
        return ab() + "/baseXML/";
    }

    public static String a(String str, String str2, String str3, String str4) {
        String str5 = t() + str + str3 + str2 + str4;
        if (w(str5)) {
            return str5;
        }
        if (CommonUtil.bu()) {
            String str6 = ac() + str + str3 + str2 + str4;
            if (w(str6)) {
                return str6;
            }
        }
        if (an()) {
            return str5;
        }
        String str7 = r() + str + str3 + str2 + str4;
        if (w(str7)) {
            return str7;
        }
        String str8 = s() + str + str3 + str2 + str4;
        return w(str8) ? str8 : str5;
    }

    public static String l(String str) {
        String t = t(str);
        String str2 = ae() + t;
        if (w(str2) || an()) {
            return str2;
        }
        String str3 = ak() + t;
        if (w(str3)) {
            return str3;
        }
        String str4 = am() + t;
        return w(str4) ? str4 : str2;
    }

    public static String k(String str) {
        String t = t(str);
        String str2 = ai() + t;
        if (w(str2) || an()) {
            return str2;
        }
        String str3 = ag() + t;
        if (w(str3)) {
            return str3;
        }
        String str4 = q() + t;
        return w(str4) ? str4 : str2;
    }

    private static String ai() {
        return ab() + "/subtitles/";
    }

    private static String ag() {
        return aa() + "/subtitles/";
    }

    private static String q() {
        return ad() + "/subtitles/";
    }

    public static String n(String str) {
        return ae() + t(str);
    }

    public static String c(String str) {
        String t = t(str);
        String str2 = y() + t;
        if (w(str2)) {
            return str2;
        }
        if (CommonUtil.bu()) {
            String str3 = af() + t;
            if (w(str3)) {
                return str3;
            }
        }
        if (an()) {
            return str2;
        }
        String str4 = x() + t;
        if (w(str4)) {
            return str4;
        }
        String str5 = u() + t;
        return w(str5) ? str5 : str2;
    }

    public static String e(String str) {
        return y() + t(str);
    }

    public static String b(String str) {
        String t = t(str);
        String str2 = t() + t;
        if (w(str2) || an()) {
            return str2;
        }
        String str3 = r() + t;
        if (w(str3)) {
            return str3;
        }
        String str4 = s() + t;
        return w(str4) ? str4 : str2;
    }

    public static String a(String str) {
        return t() + t(str);
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return v() + c() + WatchFaceConstant.XML_SUFFIX;
        }
        return v() + c() + "_" + str + WatchFaceConstant.XML_SUFFIX;
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            String str2 = c() + WatchFaceConstant.XML_SUFFIX;
            String str3 = v() + str2;
            if (w(str3) || an()) {
                return str3;
            }
            String str4 = w() + str2;
            if (w(str4)) {
                return str4;
            }
            String str5 = z() + str2;
            return w(str5) ? str5 : str3;
        }
        String str6 = c() + "_" + str + WatchFaceConstant.XML_SUFFIX;
        String str7 = v() + str6;
        if (w(str7) || an()) {
            return str7;
        }
        String str8 = w() + str6;
        if (w(str8)) {
            return str8;
        }
        String str9 = z() + str6;
        return w(str9) ? str9 : str7;
    }

    private static boolean w(String str) {
        return new File(str).exists();
    }

    public static boolean s(String str) {
        return new File(l(str)).exists();
    }

    public static boolean p(String str) {
        return new File(c(str)).exists();
    }

    public static boolean d(String str, long j) {
        File file = new File(c(str));
        if (file.exists()) {
            return a(file, j);
        }
        return false;
    }

    public static boolean d(String str, long j, String str2) {
        if (!e(str, str2)) {
            return false;
        }
        File file = new File(b(str));
        if (file.exists()) {
            return a(file, j);
        }
        return false;
    }

    private static boolean e(String str, String str2) {
        String str3;
        if (!TextUtils.isEmpty(h)) {
            str3 = "fit_mp3_id_" + h + str;
        } else {
            str3 = "fit_mp3_id_" + str;
        }
        String i = i(str3);
        if (i != null && i.equals(str2)) {
            return true;
        }
        File file = new File(b(str));
        if (file.exists() && file.delete()) {
            LogUtil.h("Suggestion_FileConfig", str, ":isValidFileUrl:", "delete");
        }
        c(str3, str2);
        return false;
    }

    public static boolean a(String str, String str2) {
        File file = FileUtils.getFile(c(str));
        if (file == null || !file.exists()) {
            return false;
        }
        return d(file, str2);
    }

    public static String d(String str) {
        return t(str);
    }

    public static boolean b(String str, long j) {
        File file = new File(l(str));
        if (file.exists()) {
            return a(file, j);
        }
        return false;
    }

    public static boolean d(String str, String str2) {
        File file = FileUtils.getFile(l(str));
        if (file == null || !file.exists()) {
            return false;
        }
        return d(file, str2);
    }

    public static boolean a(File file, long j) {
        long lastModified = file.lastModified();
        boolean z = lastModified / 1000 == j / 1000;
        if (!z) {
            String name = file.getName();
            ReleaseLogUtil.d("Suggestion_FileConfig", name, ":", "is valid, inputModify=", Long.valueOf(j), ", recModify=", Long.valueOf(lastModified));
            if (file.delete()) {
                ReleaseLogUtil.d("Suggestion_FileConfig", name, ":", "delete");
            }
        }
        return z;
    }

    private static boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            return canonicalPath.equals(CommonUtil.d(canonicalPath));
        } catch (IOException e2) {
            LogUtil.h("Suggestion_FileConfig", "isFilePathValid ", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean d(File file, String str) {
        FileInputStream fileInputStream;
        if (!a(file)) {
            LogUtil.h("Suggestion_FileConfig", "file path is not valid");
            return false;
        }
        String name = file.getName();
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        }
        try {
        } catch (FileNotFoundException e3) {
            e = e3;
            fileInputStream3 = fileInputStream;
            LogUtil.h("Suggestion_FileConfig", LogAnonymous.b((Throwable) e));
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    fileInputStream2 = fileInputStream3;
                } catch (IOException e4) {
                    LogUtil.h("Suggestion_FileConfig", LogAnonymous.b((Throwable) e4));
                    fileInputStream2 = fileInputStream3;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    LogUtil.h("Suggestion_FileConfig", LogAnonymous.b((Throwable) e5));
                }
            }
            throw th;
        }
        if (mol.e(fileInputStream).equals(str)) {
            e(name, file.lastModified());
            try {
                fileInputStream.close();
            } catch (IOException e6) {
                LogUtil.h("Suggestion_FileConfig", LogAnonymous.b((Throwable) e6));
            }
            return true;
        }
        Object[] objArr = {name, ":", "is valid"};
        LogUtil.h("Suggestion_FileConfig", objArr);
        if (file.delete()) {
            LogUtil.h("Suggestion_FileConfig", name, ":", "delete");
        }
        try {
            fileInputStream.close();
            fileInputStream2 = objArr;
        } catch (IOException e7) {
            LogUtil.h("Suggestion_FileConfig", LogAnonymous.b((Throwable) e7));
            fileInputStream2 = objArr;
        }
        return false;
    }

    private static String t(String str) {
        if (str == null) {
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

    public static String h(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = c();
        } else {
            str2 = c() + "_" + str;
        }
        if (TextUtils.isEmpty(h)) {
            return str2;
        }
        return str2 + "_" + h;
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + "_" + str2;
    }

    public static boolean m() {
        return sqq.b().f();
    }

    private static boolean an() {
        return !m() || i() || g();
    }

    public static boolean g() {
        String b2 = ash.b("IS_CP_COMPLETE_SD_KEY_MOVE_SERVICE");
        return b2 != null && "1".equals(b2);
    }

    public static void o() {
        ash.a("IS_CP_COMPLETE_SD_KEY_MOVE_SERVICE", "1");
        LogUtil.c("Suggestion_FileConfig", "setCopyCompletePref()");
    }

    public static boolean i() {
        String b2 = ash.b("IS_DEL_COMPLETE_SD_KEY_MOVE_SERVICE");
        return b2 != null && "1".equals(b2);
    }

    public static void n() {
        ash.a("IS_DEL_COMPLETE_SD_KEY_MOVE_SERVICE", "1");
        LogUtil.c("Suggestion_FileConfig", "setDeleteCompletePref()");
    }

    public static void e(String str, long j) {
        BaseApplication.getContext().getApplicationContext().getSharedPreferences("file_fit_workout", 0).edit().putLong(str, j).commit();
    }

    public static long o(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("file_fit_workout", 0).getLong(str, 0L);
    }

    public static void c(String str, String str2) {
        BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).edit().putString(str, str2).commit();
    }

    public static void a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.h("Suggestion_FileConfig", "setMultiLanguageUrl() langName or url is null");
        } else {
            c(b(str, str2), str3);
        }
    }

    public static String i(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).getString(str, null);
    }

    public static void c(String str, boolean z) {
        BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).edit().putBoolean(str, z).commit();
    }

    public static boolean g(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).getBoolean(str, false);
    }

    public static void a(String str, long j) {
        BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).edit().putLong(str, j).commit();
    }

    public static long m(String str) {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("MULTI_LANGUAGE", 0).getLong(str, 0L);
    }

    public static long b() {
        File file = new File(ab());
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.b("Suggestion_FileConfig", "file.mkdirs() fail");
        }
        StatFs statFs = new StatFs(e(file));
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static long j() {
        String ab = ab();
        long j = 0;
        for (String str : e()) {
            j += d(new File(ab + "/" + str));
        }
        return j;
    }

    private static long d(File file) {
        long d2;
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2 != null && file2.isFile()) {
                d2 = file2.length();
            } else {
                d2 = d(file2);
            }
            j += d2;
        }
        return j;
    }

    public static String d() {
        return LanguageUtil.j(BaseApplication.getContext()) ? "BPF001_CN_F_VERSION" : "BPF001_EN_F_VERSION";
    }

    public static String c() {
        return SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "MATCH_MULTI_LANGUAGE_AUDIO_TAG", "BPF001_EN");
    }

    public static String a() {
        if (LanguageUtil.j(BaseApplication.getContext())) {
            return "BPF001_CN";
        }
        return "BPF001_" + BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage().toUpperCase(Locale.ROOT);
    }

    private static String p() {
        return LanguageUtil.j(BaseApplication.getContext()) ? "/videos/" : "/videosEn/";
    }

    private static String k() {
        return LanguageUtil.j(BaseApplication.getContext()) ? "/audios/" : "/audiosEn/";
    }

    private static String l() {
        if (TextUtils.isEmpty(h)) {
            return LanguageUtil.j(BaseApplication.getContext()) ? "/audiosBase/" : "/audiosBaseEn/";
        }
        String str = "/audiosBase" + h + "/";
        v("/audiosBase" + h);
        return str;
    }

    private static void v(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_FileConfig", "saveTimbreFileName() fileName is null!");
            return;
        }
        List<String> ah = ah();
        if (!ah.isEmpty() && ah.contains(str)) {
            LogUtil.h("Suggestion_FileConfig", "saveTimbreFileName() fileName already exists !");
        } else {
            ah.add(str);
            SharedPreferenceManager.e(BaseApplication.getContext().getApplicationContext(), "FILE_NAME_MORE_TIMBRE", "FILE_NAME_MORE_TIMBRE_KEY", nrv.a(ah), (StorageParams) null);
        }
    }

    private static List<String> ah() {
        ArrayList arrayList = new ArrayList(16);
        String[] strArr = (String[]) nrv.b(SharedPreferenceManager.b(BaseApplication.getContext().getApplicationContext(), "FILE_NAME_MORE_TIMBRE", "FILE_NAME_MORE_TIMBRE_KEY"), String[].class);
        if (strArr != null) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return arrayList;
    }

    public static void q(String str) {
        LogUtil.a("Suggestion_FileConfig", "setTimbre() timbre:", str);
        if (TextUtils.equals(str, "0")) {
            h = "";
        } else {
            h = str;
        }
    }

    public static void r(String str) {
        SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "MATCH_MULTI_LANGUAGE_AUDIO_TAG", str);
    }
}
