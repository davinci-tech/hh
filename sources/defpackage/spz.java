package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class spz {
    public static final String b;
    public static final String c;
    private static final String d;
    public static final String e;

    static {
        String str = BaseApplication.getContext().getFilesDir() + File.separator + "medalziptemp";
        b = str;
        c = BaseApplication.getContext().getFilesDir() + File.separator + "medallocal";
        e = BaseApplication.getContext().getFilesDir() + File.separator + "medallocal250" + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(File.separator);
        sb.append("medalConfig.json");
        d = sb.toString();
    }

    public static String b(mfo mfoVar, String str) {
        String d2;
        String a2;
        if (mfoVar != null && !TextUtils.isEmpty(mfoVar.a()) && !TextUtils.isEmpty(str)) {
            String a3 = mfoVar.a();
            String str2 = b;
            File file = new File(str2);
            if (!file.exists()) {
                LogUtil.a("MedalZipUtil", "createMedalZipById isMkdir == ", Boolean.valueOf(file.mkdir()));
            }
            if (Utils.o() && mlb.c().contains(a3)) {
                d2 = i(a3);
                a2 = meb.a(a3);
                if (TextUtils.isEmpty(d2)) {
                    LogUtil.h("MedalZipUtil", "local medalId ", a3, " isPngExists false!");
                    return "";
                }
            } else {
                d2 = mlb.d(a3, ParsedFieldTag.LIGHT_LIST_STYLE);
                boolean exists = new File(d2).exists();
                if (!exists) {
                    LogUtil.h("MedalZipUtil", "medalId ", a3, " isPngExists ", Boolean.valueOf(exists));
                    return "";
                }
                a2 = meb.a(a3);
                if (TextUtils.isEmpty(a2) || !mfl.e(BaseApplication.getContext(), a3)) {
                    LogUtil.h("MedalZipUtil", "medal3d ", a3, " is3dExists not downing!");
                } else if (!new File(a2).exists()) {
                    LogUtil.h("MedalZipUtil", "medalId ", a3, " is3dExists false");
                    return "";
                }
            }
            String e2 = e(mfoVar);
            String str3 = d;
            LogUtil.a("MedalZipUtil", "createMedalZipById isSaveJsonSuccess == ", Boolean.valueOf(c(str3, e2)));
            String str4 = str2 + File.separator + str + "_medal_" + a3 + ".zip";
            boolean a4 = new spw(str4).a(d2, a2, str3);
            f(str3);
            return a4 ? str4 : "";
        }
        return "";
    }

    private static String i(String str) {
        String str2 = c + File.separator + str + "_lightListStyle.png";
        return new File(str2).exists() ? str2 : "";
    }

    private static void f(String str) {
        if (c(str)) {
            LogUtil.a("MedalZipUtil", "deleteMedalConfigFile success");
        }
    }

    public static String e(String str, int i, ArrayList<mfs> arrayList, String str2) {
        String str3;
        if (koq.b(arrayList)) {
            LogUtil.h("MedalZipUtil", "createMedalGainZipById gainList isEmpty.");
            return "";
        }
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.a("MedalZipUtil", "createMedalGainZipById isMkdir == ", Boolean.valueOf(file.mkdir()));
        }
        if (i == 1) {
            str3 = str + File.separator + str2 + "_medal_light_list.json";
        } else if (i != 2) {
            str3 = "";
        } else {
            str3 = str + File.separator + str2 + "_medal_light.json";
        }
        String json = new Gson().toJson(arrayList);
        boolean c2 = c(str3, json);
        LogUtil.a("MedalZipUtil", "createMedalGainZipById jsonStr == ", json, " isSaveJsonSuccess == ", Boolean.valueOf(c2));
        return c2 ? str3 : "";
    }

    private static String e(mfo mfoVar) {
        String json = new Gson().toJson(mfoVar);
        LogUtil.a("MedalZipUtil", "medalList2Json medalListJson ==", json);
        return json;
    }

    private static boolean c(String str, String str2) {
        FileOutputStream fileOutputStream;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("MedalZipUtil", "saveJsonStrToFile: untrusted -> " + str);
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("MedalZipUtil", "saveJsonStrToFile: medalsStr is empty");
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                if (!new File(str).exists()) {
                    LogUtil.h("MedalZipUtil", "saveJsonStrToFile isNewFile == ", Boolean.valueOf(new File(str).createNewFile()));
                }
                fileOutputStream = new FileOutputStream(str);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            fileOutputStream.write(str2.getBytes("UTF-8"));
            b(fileOutputStream);
            return true;
        } catch (IOException unused2) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("MedalZipUtil", "saveJsonStrToFile IOException.");
            b(fileOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            b(fileOutputStream);
            throw th;
        }
    }

    private static void b(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("MedalZipUtil", "saveJsonStrToFile IOException.");
            }
        }
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("MedalZipUtil", "deleteMedalFile fail path == ", str);
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean e(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(str);
        }
        return d(str);
    }

    private static boolean b(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("MedalZipUtil", "deleteFile: untrusted -> " + str);
            return false;
        }
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    private static boolean d(String str) {
        File[] listFiles;
        String str2;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("MedalZipUtil", "deleteDirectory: untrusted -> " + str);
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : listFiles) {
            try {
                str2 = file2.getCanonicalPath();
            } catch (IOException e2) {
                LogUtil.b("MedalZipUtil", "getCanonicalPath suffix invalid,error:", e2.getMessage());
                str2 = "";
            }
            if (TextUtils.isEmpty(str2)) {
                break;
            }
            if (file2.isFile()) {
                z = b(str2);
                if (!z) {
                    break;
                }
            } else {
                z = d(str2);
                if (!z) {
                    break;
                }
            }
        }
        if (z) {
            return file.delete();
        }
        return false;
    }

    public static Bitmap ekm_(String str, String str2) {
        Bitmap bitmap = null;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("MedalZipUtil", "loadImage: untrusted -> " + str);
            return null;
        }
        if (new File(str).exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            try {
                bitmap = BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("MedalZipUtil", "loadImage:OutOfMemoryError");
            }
        }
        if (bitmap == null) {
            LogUtil.h("MedalZipUtil", "showMedalBitmap loadImage is null! medal ", str2);
        }
        return bitmap;
    }

    public static String b(String str, String str2) {
        FileOutputStream fileOutputStream;
        String a2 = a(str2);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("MedalZipUtil", "compressMedalPng png not download!");
            return a(str2, a2);
        }
        Bitmap cJJ_ = nrf.cJJ_(ekm_(a2, str2), 250, 250);
        if (cJJ_ == null) {
            return a(str2, a2);
        }
        StringBuilder sb = new StringBuilder();
        String str3 = e;
        sb.append(str3);
        sb.append(str);
        sb.append("_medal_");
        sb.append(str2);
        sb.append(".png");
        String sb2 = sb.toString();
        if (!GeneralUtil.isSafePath(sb2)) {
            LogUtil.h("MedalZipUtil", "compressMedalPng: untrusted -> " + sb2);
            return "";
        }
        File file = new File(str3);
        if (!file.exists()) {
            LogUtil.a("MedalZipUtil", "createMedalZipById isMkdir == ", Boolean.valueOf(file.mkdir()));
        }
        File file2 = new File(sb2);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        if (!file2.createNewFile()) {
            LogUtil.h("MedalZipUtil", "File ", file2.getPath(), " already exists");
            String a3 = a(str2, sb2);
            a((FileOutputStream) null);
            return a3;
        }
        fileOutputStream = new FileOutputStream(file2);
        try {
            cJJ_.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            a(fileOutputStream);
        } catch (FileNotFoundException unused3) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("MedalZipUtil", "saveImage FileNotFoundException");
            a(fileOutputStream2);
            return a(str2, sb2);
        } catch (IOException unused4) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("MedalZipUtil", "saveImage IOException");
            a(fileOutputStream2);
            return a(str2, sb2);
        } catch (Throwable th2) {
            th = th2;
            a(fileOutputStream);
            throw th;
        }
        return a(str2, sb2);
    }

    public static String a(String str) {
        String d2 = mlb.d(str, ParsedFieldTag.LIGHT_LIST_STYLE);
        boolean exists = new File(d2).exists();
        LogUtil.a("MedalZipUtil", "getMedalPngPath isPngExists ", Boolean.valueOf(exists));
        return (!exists && Utils.o() && mlb.c().contains(str)) ? i(str) : d2;
    }

    private static String a(String str, String str2) {
        boolean exists = new File(str2).exists();
        if (!exists) {
            return "";
        }
        LogUtil.h("MedalZipUtil", "checkMedalPath medalId ", str, " isPngExists ", Boolean.valueOf(exists));
        return str2;
    }

    private static void a(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("MedalZipUtil", "closeStream, IOException");
            }
        }
    }

    public static String d(String str, String str2) {
        String d2;
        String a2;
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            String str3 = e;
            File file = new File(str3);
            if (!file.exists()) {
                LogUtil.a("MedalZipUtil", "createMedalZipById isMkdir == ", Boolean.valueOf(file.mkdir()));
            }
            if (Utils.o() && mlb.c().contains(str2)) {
                d2 = i(str2);
                a2 = meb.a(str2);
                if (TextUtils.isEmpty(d2)) {
                    LogUtil.h("MedalZipUtil", "local medalId ", str2, " isPngExists false!");
                    return "";
                }
            } else {
                d2 = mlb.d(str2, ParsedFieldTag.LIGHT_LIST_STYLE);
                boolean exists = new File(d2).exists();
                if (!exists) {
                    LogUtil.h("MedalZipUtil", "medalId ", str2, " isPngExists ", Boolean.valueOf(exists));
                    return "";
                }
                a2 = meb.a(str2);
                if (TextUtils.isEmpty(a2) || !mfl.e(BaseApplication.getContext(), str2)) {
                    LogUtil.h("MedalZipUtil", "medal3d ", str2, " is3dExists not downing!");
                } else if (!new File(a2).exists()) {
                    LogUtil.h("MedalZipUtil", "medalId ", str2, " is3dExists false");
                    return "";
                }
            }
            String str4 = str3 + str + "_medal_" + str2 + ".zip";
            return new spw(str4).a(d2, a2, d) ? str4 : "";
        }
        return "";
    }
}
