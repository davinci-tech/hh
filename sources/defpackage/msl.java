package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class msl {
    public static final Object b = new Object();
    public static final Object e = new Object();

    public static String e(Context context, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        LogUtil.a("EzPluginManagerUtil", "enter readFileToData: filePath = ", str);
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        String d = CommonUtil.d(str);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("EzPluginManagerUtil", "readFileToData legalPath is empty");
            return "";
        }
        RandomAccessFile randomAccessFile2 = null;
        FileLock fileLock = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                randomAccessFile = new RandomAccessFile(d, "rw");
            } catch (IOException unused) {
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
                byteArrayOutputStream = byteArrayOutputStream2;
                randomAccessFile2 = randomAccessFile;
                IoUtils.e(randomAccessFile2);
                IoUtils.e(byteArrayOutputStream);
                throw th;
            }
            try {
                channel = randomAccessFile.getChannel();
                for (int i = 0; i < 3; i++) {
                    try {
                        fileLock = channel.tryLock();
                    } catch (OverlappingFileLockException unused2) {
                        e();
                    }
                    if (fileLock != null) {
                        break;
                    }
                    e();
                }
            } catch (IOException unused3) {
                byteArrayOutputStream2 = byteArrayOutputStream;
                try {
                    LogUtil.b("EzPluginManagerUtil", "readFileToData IOException");
                    IoUtils.e(randomAccessFile);
                    byteArrayOutputStream = byteArrayOutputStream2;
                    IoUtils.e(byteArrayOutputStream);
                    try {
                        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    } catch (UnsupportedEncodingException unused4) {
                        return "";
                    }
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    randomAccessFile2 = randomAccessFile;
                    IoUtils.e(randomAccessFile2);
                    IoUtils.e(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile2 = randomAccessFile;
                IoUtils.e(randomAccessFile2);
                IoUtils.e(byteArrayOutputStream);
                throw th;
            }
        } catch (IOException unused5) {
            randomAccessFile = null;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            IoUtils.e(randomAccessFile2);
            IoUtils.e(byteArrayOutputStream);
            throw th;
        }
        if (fileLock == null) {
            LogUtil.h("EzPluginManagerUtil", "lock Valid");
            IoUtils.e(randomAccessFile);
            IoUtils.e(byteArrayOutputStream);
            return "";
        }
        byte[] bArr = new byte[1024];
        for (int read = randomAccessFile.read(bArr); read != -1; read = randomAccessFile.read(bArr)) {
            byteArrayOutputStream.write(bArr, 0, read);
        }
        fileLock.release();
        channel.close();
        IoUtils.e(randomAccessFile);
        IoUtils.e(byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
    }

    private static void e() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException unused) {
            LogUtil.b("EzPluginManagerUtil", "sleep Exception");
        }
    }

    public static boolean d(String str) {
        return new File(mrv.d + str + File.separator + "description.json").exists();
    }

    public static void a(List<msc> list) {
        synchronized (e) {
            if (list != null) {
                LogUtil.a("EzPluginManagerUtil", "clearPluginInfoList");
                list.clear();
            }
        }
    }

    public static msc b(Context context, String str) {
        String str2 = mrv.d + str + File.separator + "description.json";
        if (!new File(str2).exists()) {
            return null;
        }
        String e2 = e(context, str2);
        LogUtil.c("EzPluginManagerUtil", "getPluginInfo descriptionJson = ", e2);
        return msb.d(e2);
    }

    public static msc d(msi msiVar, List<msc> list, String str) {
        synchronized (e) {
            msc mscVar = null;
            if (list != null) {
                if (!TextUtils.isEmpty(str)) {
                    Iterator<msc> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        msc next = it.next();
                        if (next != null && str.equals(next.a())) {
                            mscVar = c(next, msiVar, list);
                            break;
                        }
                    }
                    return mscVar;
                }
            }
            return null;
        }
    }

    private static msc c(msc mscVar, msi msiVar, List<msc> list) {
        if (msiVar != null && msiVar.d() != null && mscVar != null) {
            String i = mscVar.i();
            String a2 = mscVar.a();
            if (!TextUtils.isEmpty(i) && !TextUtils.isEmpty(a2)) {
                synchronized (e) {
                    for (msa msaVar : msiVar.d()) {
                        String b2 = msaVar.b();
                        if (!TextUtils.isEmpty(b2) && b2.equals(a2)) {
                            String h = msaVar.h();
                            if (h != null && h.equals(i)) {
                                return mscVar;
                            }
                            if (list == null) {
                                return null;
                            }
                            LogUtil.a("EzPluginManagerUtil", "getCachePluin remove cachePluin pluginInfoVersion: ", i);
                            list.remove(mscVar);
                            return null;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public static void a(List<msc> list, msc mscVar, String str) {
        synchronized (e) {
            if (!TextUtils.isEmpty(str) && list != null && mscVar != null) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    if (list.get(i).a().equals(str)) {
                        list.remove(i);
                        break;
                    }
                    i++;
                }
                mrt g = mscVar.g();
                if (str.equals(mscVar.a()) && g != null) {
                    if (!g.h()) {
                        list.add(mscVar);
                    }
                    if (!TextUtils.isEmpty(g.b())) {
                        list.add(mscVar);
                    }
                }
            }
        }
    }

    public static String c() {
        String b2 = mrw.b(mrv.c, "MD5");
        return mrv.d + "index" + File.separator + (b2 != null ? b2.toLowerCase(Locale.ENGLISH) : null) + ".json";
    }

    public static String a(String str) {
        return e(msr.d.get(str));
    }

    public static String e(String str) {
        return mrv.d + "index" + File.separator + str + ".json";
    }

    public static boolean e(String str, boolean z) {
        if (z) {
            return false;
        }
        String str2 = msj.a().d(str) + File.separator + "done";
        String str3 = mrv.d + str + File.separator + "description.json";
        LogUtil.c("EzPluginManagerUtil", "isPluginAvaiable donePath is = ", str2, " descriptionPath is = ", str3);
        boolean exists = new File(str2).exists();
        LogUtil.c("EzPluginManagerUtil", "isPluginAvaiable isHavedoneFile is = ", Boolean.valueOf(exists));
        if (!exists) {
            return false;
        }
        boolean exists2 = new File(str3).exists();
        LogUtil.c("EzPluginManagerUtil", "isPluginAvaiable isHaveDesFile is = ", Boolean.valueOf(exists2));
        return exists2;
    }

    public static boolean e(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2);
    }

    public static boolean e(Context context, String str, String str2) {
        LogUtil.a("EzPluginManagerUtil", "compareIndexVersion minIndexVersion is :", str, " indexVersion is : ", str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
            return true;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        LogUtil.a("EzPluginManagerUtil", "compareIndexVersion minIndexVersionArray length is ", Integer.valueOf(split.length), " indexVersionArray length is ", Integer.valueOf(split2.length));
        int min = Math.min(split.length, split2.length);
        for (int i = 0; i < min; i++) {
            int m = CommonUtil.m(context, split2[i]) - CommonUtil.m(context, split[i]);
            if (m > 0) {
                return true;
            }
            if (m < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(String str, msa msaVar) {
        if (msaVar != null) {
            if (!msaVar.h().equalsIgnoreCase("Deprecated")) {
                return false;
            }
            LogUtil.a("EzPluginManagerUtil", "isDeprecated this plugin is deprecated ");
            c(str);
            return true;
        }
        LogUtil.h("EzPluginManagerUtil", "isDeprecated this uuid is not exists");
        return false;
    }

    private static void c(String str) {
        String str2 = mrv.d + str + File.separator;
        String str3 = str2 + "done";
        File file = new File(str3);
        File file2 = new File(str2 + "description.json");
        if (file.exists()) {
            LogUtil.c("EzPluginManagerUtil", "deletePlugin isDeleteDone is = ", Boolean.valueOf(file.delete()));
        }
        if (file2.exists()) {
            LogUtil.c("EzPluginManagerUtil", "deletePlugin isDeleteDescription is = ", Boolean.valueOf(file2.delete()));
        }
    }
}
