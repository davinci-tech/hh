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

/* loaded from: classes3.dex */
class cdr {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f649a = new Object();
    private static final Object e = new Object();

    public static String c(Context context, String str) {
        LogUtil.a("ResourceHandleManagerUtil", "enter readFileToData: filePath = ", str);
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        String d = CommonUtil.d(str);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("ResourceHandleManagerUtil", "readFileToData legalPath is empty");
            return "";
        }
        return d(d);
    }

    private static String d(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        RandomAccessFile randomAccessFile2 = null;
        FileLock fileLock = null;
        r4 = null;
        r4 = null;
        RandomAccessFile randomAccessFile3 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    randomAccessFile = new RandomAccessFile(str, "rw");
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream;
                randomAccessFile2 = randomAccessFile3;
            }
        } catch (IOException unused2) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            channel = randomAccessFile.getChannel();
            for (int i = 0; i < 3; i++) {
                try {
                    fileLock = channel.tryLock();
                } catch (OverlappingFileLockException unused3) {
                    c();
                }
                if (fileLock != null) {
                    break;
                }
                c();
            }
        } catch (IOException unused4) {
            randomAccessFile3 = randomAccessFile;
            LogUtil.b("ResourceHandleManagerUtil", "readFileToData IOException");
            IoUtils.e(randomAccessFile3);
            IoUtils.e(byteArrayOutputStream);
            try {
                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            } catch (UnsupportedEncodingException unused5) {
                return "";
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile2 = randomAccessFile;
            IoUtils.e(randomAccessFile2);
            IoUtils.e(byteArrayOutputStream);
            throw th;
        }
        if (fileLock == null) {
            LogUtil.h("ResourceHandleManagerUtil", "lock Valid");
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

    private static void c() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException unused) {
            LogUtil.b("ResourceHandleManagerUtil", "sleep Exception");
        }
    }

    public static boolean e(String str) {
        return new File(cuv.f11488a + str + File.separator + "description.json").exists();
    }

    public static void b(List<cvc> list) {
        synchronized (e) {
            if (list != null) {
                LogUtil.a("ResourceHandleManagerUtil", "clearPluginInfoList");
                list.clear();
            }
        }
    }

    public static cvc a(Context context, String str) {
        String str2 = cuv.f11488a + str + File.separator + "description.json";
        if (!new File(str2).exists()) {
            return null;
        }
        String c = c(context, str2);
        LogUtil.c("ResourceHandleManagerUtil", "getPluginInfo descriptionJson = ", c);
        return cde.e(c);
    }

    public static cvc a(cdi cdiVar, List<cvc> list, String str) {
        synchronized (e) {
            cvc cvcVar = null;
            if (list != null) {
                if (!TextUtils.isEmpty(str)) {
                    Iterator<cvc> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        cvc next = it.next();
                        if (next != null && str.equals(next.e())) {
                            cvcVar = b(next, cdiVar, list);
                            break;
                        }
                    }
                    return cvcVar;
                }
            }
            return null;
        }
    }

    private static cvc b(cvc cvcVar, cdi cdiVar, List<cvc> list) {
        if (cdiVar != null && cdiVar.a() != null && cvcVar != null) {
            String b = cvcVar.b();
            String e2 = cvcVar.e();
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(e2)) {
                synchronized (e) {
                    for (cvk cvkVar : cdiVar.a()) {
                        String e3 = cvkVar.e();
                        if (!TextUtils.isEmpty(e3) && e3.equals(e2)) {
                            String a2 = cvkVar.a();
                            if (a2 != null && a2.equals(b)) {
                                return cvcVar;
                            }
                            if (list == null) {
                                return null;
                            }
                            LogUtil.a("ResourceHandleManagerUtil", "getCachePluin remove cachePluin pluginInfoVersion: ", b);
                            list.remove(cvcVar);
                            return null;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public static void a(List<cvc> list, cvc cvcVar, String str) {
        synchronized (e) {
            if (!TextUtils.isEmpty(str) && list != null && cvcVar != null) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    if (list.get(i).e().equals(str)) {
                        list.remove(i);
                        break;
                    }
                    i++;
                }
                cvj f = cvcVar.f();
                if (str.equals(cvcVar.e()) && f != null) {
                    if (!f.bn()) {
                        list.add(cvcVar);
                    }
                    if (!TextUtils.isEmpty(f.ae())) {
                        list.add(cvcVar);
                    }
                }
            }
        }
    }

    public static String a() {
        String a2 = cdw.a(cuv.d, "MD5");
        return cuv.f11488a + "index" + File.separator + (a2 != null ? a2.toLowerCase(Locale.ENGLISH) : null) + ".json";
    }

    public static String d() {
        String a2 = a();
        if (CommonUtil.bv() || new File(a2).exists()) {
            return a2;
        }
        return cuv.f11488a + "index" + File.separator + "3eaffc90df3744d7a82fda381355dc1e.json";
    }

    public static String b(String str) {
        return a(msr.d.get(str));
    }

    public static String a(String str) {
        return cuv.f11488a + "index" + File.separator + str + ".json";
    }

    public static boolean c(String str, boolean z) {
        if (z) {
            return false;
        }
        String str2 = cdv.b().d(str) + File.separator + "done";
        String str3 = cuv.f11488a + str + File.separator + "description.json";
        LogUtil.c("ResourceHandleManagerUtil", "isPluginAvaiable donePath is = ", str2, " descriptionPath is = ", str3);
        boolean exists = new File(str2).exists();
        LogUtil.c("ResourceHandleManagerUtil", "isPluginAvaiable isHavedoneFile is = ", Boolean.valueOf(exists));
        if (!exists) {
            return false;
        }
        boolean exists2 = new File(str3).exists();
        LogUtil.c("ResourceHandleManagerUtil", "isPluginAvaiable isHaveDesFile is = ", Boolean.valueOf(exists2));
        return exists2;
    }

    public static boolean e(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2);
    }

    public static boolean c(Context context, String str, String str2) {
        LogUtil.a("ResourceHandleManagerUtil", "compareIndexVersion minIndexVersion is :", str, " indexVersion is : ", str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
            return true;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        LogUtil.a("ResourceHandleManagerUtil", "compareIndexVersion minIndexVersionArray length is ", Integer.valueOf(split.length), " indexVersionArray length is ", Integer.valueOf(split2.length));
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

    public static boolean b(String str, cvk cvkVar) {
        if (cvkVar != null) {
            if (!cvkVar.a().equalsIgnoreCase("Deprecated")) {
                return false;
            }
            LogUtil.a("ResourceHandleManagerUtil", "isDeprecated this plugin is deprecated ");
            c(str);
            return true;
        }
        LogUtil.h("ResourceHandleManagerUtil", "isDeprecated this uuid is not exists");
        return false;
    }

    private static void c(String str) {
        String str2 = cuv.f11488a + str + File.separator;
        String str3 = str2 + "done";
        File file = new File(str3);
        File file2 = new File(str2 + "description.json");
        if (file.exists()) {
            LogUtil.c("ResourceHandleManagerUtil", "deletePlugin isDeleteDone is = ", Boolean.valueOf(file.delete()));
        }
        if (file2.exists()) {
            LogUtil.c("ResourceHandleManagerUtil", "deletePlugin isDeleteDescription is = ", Boolean.valueOf(file2.delete()));
        }
    }
}
