package defpackage;

import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jsj {
    public static void e(Collection<File> collection, File file, String str, String str2) throws IOException {
        ZipOutputStream zipOutputStream;
        if (collection == null || file == null) {
            LogUtil.h("ZipUtil", "zipFileList or zipFileParam is null");
            return;
        }
        LogUtil.c("ZipUtil", "zipFiles() size: ", Integer.valueOf(collection.size()));
        ZipOutputStream zipOutputStream2 = null;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(file), 1048576));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            zipOutputStream = zipOutputStream2;
        }
        try {
            for (File file2 : collection) {
                String name = file2.getName();
                LogUtil.c("ZipUtil", "zipFileName: ", name);
                try {
                    name = new String(name.getBytes("8859_1"), "GB2312");
                } catch (UnsupportedEncodingException e2) {
                    LogUtil.b("ZipUtil", "UnsupportedEncodingException e: ", e2.getMessage());
                }
                LogUtil.c("ZipUtil", "zipFile.isDirectory(): ", Boolean.valueOf(file2.isDirectory()), " zipFileName:", name);
                if (file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length != 0) {
                        Iterator<File> it = a(file2, listFiles, str2).iterator();
                        while (it.hasNext()) {
                            a(it.next(), zipOutputStream, name, str);
                        }
                    }
                    LogUtil.c("ZipUtil", "fileLists is null");
                    break;
                }
                a(zipOutputStream, file2);
            }
            try {
                zipOutputStream.setComment("com.huawei.health");
                zipOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("ZipUtil", "IOException exception");
            }
        } catch (IOException e3) {
            e = e3;
            zipOutputStream2 = zipOutputStream;
            LogUtil.b("ZipUtil", "zipFiles IOException exception");
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.setComment("com.huawei.health");
                    zipOutputStream.close();
                } catch (IOException unused2) {
                    LogUtil.b("ZipUtil", "IOException exception");
                }
            }
            throw th;
        }
    }

    public static void d() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "last_zip_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date()), new StorageParams());
    }

    private static boolean i(String str) {
        try {
            LogUtil.a("ZipUtil", "isAddZip fileName: ", str);
            int length = str.length();
            if (length < 18) {
                return true;
            }
            String substring = str.substring(length - 18, length - 4);
            long parseLong = Long.parseLong(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "last_zip_time"));
            long parseLong2 = Long.parseLong(substring);
            LogUtil.a("ZipUtil", "isAddZip lastTime: ", Long.valueOf(parseLong), "currentTime: ", Long.valueOf(parseLong2));
            if (parseLong2 >= parseLong) {
                return true;
            }
            LogUtil.a("ZipUtil", "isAddZip is false");
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b("ZipUtil", "isAddZip NumberFormatException");
            return true;
        }
    }

    private static List<File> a(File file, File[] fileArr, String str) {
        List<File> asList = Arrays.asList(fileArr);
        ArrayList arrayList = new ArrayList(16);
        if ("com.huawei.health_PhoneService".equals(file.getName())) {
            for (File file2 : asList) {
                if (!"log.0".equals(file2.getName()) && i(file2.getName())) {
                    arrayList.add(file2);
                }
            }
        } else if ("MaintenanceLog".equals(file.getName())) {
            for (File file3 : asList) {
                if (file3.getName().contains(str) || file3.getName().contains("bigdata.log")) {
                    arrayList.add(file3);
                }
            }
        } else if (BaseApplication.getAppPackage().equals(file.getName())) {
            for (File file4 : asList) {
                if (i(file4.getName())) {
                    arrayList.add(file4);
                }
            }
        } else {
            arrayList.addAll(asList);
        }
        return arrayList;
    }

    private static void a(ZipOutputStream zipOutputStream, File file) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                byte[] bArr = new byte[1048576];
                String c = CommonUtil.c(file.getCanonicalPath());
                if (TextUtils.isEmpty(c)) {
                    LogUtil.h("ZipUtil", "zipFiles safePath is empty");
                    IoUtils.e((Closeable) null);
                    return;
                }
                bufferedInputStream = new BufferedInputStream(new FileInputStream(c), 1048576);
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            zipOutputStream.write(bArr, 0, read);
                        } else {
                            LogUtil.a("ZipUtil", "zip ok");
                            IoUtils.e(bufferedInputStream);
                            return;
                        }
                    }
                } catch (IOException unused) {
                    bufferedInputStream2 = bufferedInputStream;
                    LogUtil.b("ZipUtil", "processFile IOException");
                    IoUtils.e(bufferedInputStream2);
                } catch (Throwable th) {
                    th = th;
                    IoUtils.e(bufferedInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str, String str2) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr;
        LogUtil.c("ZipUtil", "zipFile()");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        try {
            sb2 = new String(sb2.getBytes("8859_1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("ZipUtil", "UnsupportedEncodingException e: ", e.getMessage());
        }
        LogUtil.c("ZipUtil", "rootPathResult: ", sb2);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            c(file, zipOutputStream, str2, sb2, listFiles);
            return;
        }
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bArr = new byte[1048576];
                bufferedInputStream = new BufferedInputStream(FileUtils.openInputStream(file), 1048576);
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = bufferedInputStream2;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(sb2));
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    IoUtils.e(bufferedInputStream);
                    return;
                }
            }
        } catch (IOException unused2) {
            bufferedInputStream2 = bufferedInputStream;
            LogUtil.b("ZipUtil", "zipFile IOException");
            IoUtils.e(bufferedInputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(bufferedInputStream);
            throw th;
        }
    }

    private static void c(File file, ZipOutputStream zipOutputStream, String str, String str2, File[] fileArr) {
        if ("com.huawei.health_PhoneService".equals(file.getName()) && "HWFEEDBACKAPI_ZIP_COMMENT_KEY".equals(str)) {
            File file2 = fileArr[0];
            if (file2 != null) {
                a(file2, zipOutputStream, str2, str);
                return;
            }
            return;
        }
        for (File file3 : fileArr) {
            a(file3, zipOutputStream, str2, str);
        }
    }

    public static LinkedList<File> e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ZipUtil", "listLinkedFiles path is null");
            return null;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("ZipUtil", "files is null");
            return null;
        }
        LinkedList<File> linkedList = new LinkedList<>();
        LogUtil.a("ZipUtil", "listLinkedFiles files: ", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            String name = file.getName();
            if (e(file, name) || a(name) || file.isDirectory()) {
                linkedList.add(file);
            } else {
                LogUtil.h("ZipUtil", "listLinkedFiles else");
            }
        }
        return linkedList;
    }

    public static LinkedList<File> b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ZipUtil", "listLinkedDeviceFiles path is null");
            return null;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("ZipUtil", "files is null");
            return null;
        }
        LinkedList<File> linkedList = new LinkedList<>();
        LogUtil.a("ZipUtil", "listLinkedDeviceFiles files: ", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            if (j(file.getName())) {
                linkedList.add(file);
            } else {
                LogUtil.h("ZipUtil", "listLinkedDeviceFiles else");
            }
        }
        return linkedList;
    }

    private static boolean a(String str) {
        return g(str) || f(str) || d(str);
    }

    private static boolean e(File file, String str) {
        return !file.isDirectory() && (str.contains("app_crashLog") || str.contains("log"));
    }

    private static boolean g(String str) {
        return str.contains("MCU") || str.contains(Event.TAG) || str.contains("BT");
    }

    private static boolean f(String str) {
        return str.contains("Dump") || str.contains("Power") || str.contains("com.huawei");
    }

    private static boolean d(String str) {
        return str.contains("_appdft_Beta");
    }

    private static boolean j(String str) {
        return str.contains("_device_WearableBeta.zip");
    }

    public static LinkedList<File> c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ZipUtil", "listLinkedOtaFiles path is null");
            return null;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("ZipUtil", "listLinkedOtaFiles directory.listFiles is null");
            return null;
        }
        LinkedList<File> linkedList = new LinkedList<>();
        LogUtil.a("ZipUtil", "listLinkedOtaFiles files: ", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            String name = file.getName();
            if ((!file.isDirectory() && name.contains("_mcu_upg")) || file.isDirectory()) {
                linkedList.add(file);
            } else {
                LogUtil.h("ZipUtil", "listLinkedOtaFiles else");
            }
        }
        return linkedList;
    }
}
