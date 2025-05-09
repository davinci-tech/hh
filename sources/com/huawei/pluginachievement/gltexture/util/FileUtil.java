package com.huawei.pluginachievement.gltexture.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.pluginachievement.gltexture.impl.AchieveMedalCallBack;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import defpackage.mct;
import defpackage.mdl;
import defpackage.meb;
import defpackage.med;
import defpackage.mlb;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes6.dex */
public class FileUtil {
    private static volatile FileUtil c;
    private ExecutorService b;
    private Context d;

    public FileUtil(Context context) {
        LogUtil.a("PLGACHIEVE_FileUtil", com.huawei.android.hicloud.sync.util.FileUtil.TAG);
        if (context == null) {
            this.d = BaseApplication.getContext();
        } else {
            this.d = context.getApplicationContext();
        }
        this.b = Executors.newSingleThreadExecutor();
        e();
    }

    public static FileUtil c(Context context) {
        if (c == null) {
            synchronized (FileUtil.class) {
                if (c == null) {
                    c = new FileUtil(context);
                }
            }
        }
        return c;
    }

    private boolean d(String str, String str2) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "sanitizeFileName(entryName): untrusted -> " + str);
            return false;
        }
        if (!GeneralUtil.isSafePath(str2)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "sanitizeFileName(intendedDir): untrusted -> " + str2);
            return false;
        }
        String c2 = CommonUtil.c(str2);
        try {
            String canonicalPath = new File(c2, str).getCanonicalPath();
            String canonicalPath2 = new File(CommonUtil.c(c2)).getCanonicalPath();
            if (!TextUtils.isEmpty(canonicalPath)) {
                if (canonicalPath.startsWith(canonicalPath2)) {
                    return true;
                }
            }
        } catch (IOException e) {
            LogUtil.b("PLGACHIEVE_FileUtil", e.getMessage());
        }
        return false;
    }

    private void e() {
        if ("1".equals(mct.b(this.d, "isNeedDelOldMedalDir"))) {
            return;
        }
        LogUtil.a("PLGACHIEVE_FileUtil", "deleteDirWithFile");
        a();
        mct.b(this.d, "isNeedDelOldMedalDir", "1");
    }

    private void a() {
        try {
            e(new File(this.d.getFilesDir().getCanonicalPath() + File.separator + "achievemedal"));
        } catch (IOException e) {
            LogUtil.b("PLGACHIEVE_FileUtil", "FileNotFoundException:", e.getMessage());
        }
    }

    public void c() {
        a();
        med.d();
        mct.b(this.d, "_medalTextureDownload", "");
        mct.b(this.d, "clearMedalResCache", "1");
    }

    public void b(String str, String str2, String str3, AchieveMedalCallBack achieveMedalCallBack) {
        if (achieveMedalCallBack == null) {
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            achieveMedalCallBack.onResponse(-1);
            return;
        }
        String c2 = mlb.c(str2);
        if (meb.b(c2)) {
            c(str, c2 + File.separator + str3 + ".png", achieveMedalCallBack);
            return;
        }
        achieveMedalCallBack.onResponse(-1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v13, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v4 */
    private void c(String str, String str2, AchieveMedalCallBack achieveMedalCallBack) {
        FileOutputStream fileOutputStream;
        HttpURLConnection httpURLConnection;
        ?? r10;
        InputStream inputStream;
        HttpURLConnection httpURLConnection2;
        FileOutputStream fileOutputStream2;
        String str3 = str2;
        if (!GeneralUtil.isSafePath(str2)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "downloadMedalPng: untrusted -> " + str3);
            return;
        }
        int i = -1;
        InputStream inputStream2 = null;
        r9 = null;
        InputStream inputStream3 = null;
        r9 = null;
        inputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        FileOutputStream fileOutputStream4 = null;
        try {
            try {
                URL url = new URL(str);
                URLConnection openConnection = URLConnectionInstrumentation.openConnection(url.openConnection());
                if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH)) && (openConnection instanceof HttpsURLConnection)) {
                    r10 = (HttpsURLConnection) openConnection;
                    try {
                        mdl.d(r10);
                        r10 = r10;
                    } catch (IOException e) {
                        e = e;
                        inputStream = null;
                        LogUtil.b("PLGACHIEVE_FileUtil", "downloadMedalPng IOException");
                        String message = e.getMessage();
                        a(inputStream, fileOutputStream4);
                        b(str3, achieveMedalCallBack, -1, message);
                        httpURLConnection2 = r10;
                        c(httpURLConnection2);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        httpURLConnection = r10;
                        a(inputStream2, fileOutputStream);
                        b(str3, achieveMedalCallBack, -1, "faild");
                        c(httpURLConnection);
                        throw th;
                    }
                } else {
                    if (!(openConnection instanceof HttpURLConnection)) {
                        a((InputStream) null, (FileOutputStream) null);
                        b(str3, achieveMedalCallBack, -1, "faild");
                        c((HttpURLConnection) null);
                        return;
                    }
                    r10 = (HttpURLConnection) openConnection;
                }
                r10.setConnectTimeout(10000);
                r10.setReadTimeout(10000);
                r10.setRequestMethod("GET");
                int responseCode = r10.getResponseCode();
                LogUtil.c("PLGACHIEVE_FileUtil", "download resCode = ", Integer.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream = r10.getInputStream();
                    try {
                        str3 = CommonUtil.c(str2);
                        File a2 = a(str3);
                        if (a2 == null) {
                            throw new IOException();
                        }
                        if (a2.exists()) {
                            LogUtil.c("PLGACHIEVE_FileUtil", "deleteResult is ", Boolean.valueOf(a2.delete()));
                        }
                        LogUtil.c("PLGACHIEVE_FileUtil", "createResult is ", Boolean.valueOf(a2.createNewFile()));
                        fileOutputStream2 = new FileOutputStream(a2);
                        try {
                            i = c(fileOutputStream2, inputStream, responseCode);
                            inputStream3 = inputStream;
                        } catch (IOException e2) {
                            e = e2;
                            fileOutputStream4 = fileOutputStream2;
                            LogUtil.b("PLGACHIEVE_FileUtil", "downloadMedalPng IOException");
                            String message2 = e.getMessage();
                            a(inputStream, fileOutputStream4);
                            b(str3, achieveMedalCallBack, -1, message2);
                            httpURLConnection2 = r10;
                            c(httpURLConnection2);
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream3 = fileOutputStream2;
                            fileOutputStream = fileOutputStream3;
                            inputStream2 = inputStream;
                            httpURLConnection = r10;
                            a(inputStream2, fileOutputStream);
                            b(str3, achieveMedalCallBack, -1, "faild");
                            c(httpURLConnection);
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                } else {
                    fileOutputStream2 = null;
                }
                a(inputStream3, fileOutputStream2);
                b(str3, achieveMedalCallBack, i, "faild");
                httpURLConnection2 = r10;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            r10 = 0;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            httpURLConnection = null;
        }
        c(httpURLConnection2);
    }

    private int c(FileOutputStream fileOutputStream, InputStream inputStream, int i) {
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    return i;
                }
            }
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_FileUtil", "downloadMedalPng download IOException");
            return -1;
        } finally {
            a(inputStream, fileOutputStream);
        }
    }

    private void c(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private void b(String str, AchieveMedalCallBack achieveMedalCallBack, int i, String str2) {
        if (i == -1 && ".png".equals(meb.c(str))) {
            if (!GeneralUtil.isSafePath(str)) {
                LogUtil.h("PLGACHIEVE_FileUtil", "deleteFileAndCallbackResult: untrusted -> " + str);
                return;
            } else {
                File file = new File(str);
                if (file.exists() && !file.delete()) {
                    LogUtil.c("PLGACHIEVE_FileUtil", "downloadMedalPng delete file failed");
                }
            }
        }
        if (achieveMedalCallBack != null) {
            achieveMedalCallBack.onResponse(i);
        }
    }

    private void a(InputStream inputStream, FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_FileUtil", "closeFileStream IOException");
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
                LogUtil.b("PLGACHIEVE_FileUtil", "closeFileStream IOException");
            }
        }
    }

    public void a(String str, String str2, AchieveMedalCallBack achieveMedalCallBack) {
        FileOutputStream fileOutputStream;
        int i;
        InputStream inputStream;
        FileOutputStream fileOutputStream2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || achieveMedalCallBack == null) {
            return;
        }
        String d = d(str2);
        if (!GeneralUtil.isSafePath(d)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "download: untrusted -> " + d);
            return;
        }
        int i2 = -1;
        InputStream inputStream2 = null;
        try {
            HttpURLConnection e = e(str);
            i = e.getResponseCode();
            if (i == 200) {
                try {
                    inputStream = e.getInputStream();
                } catch (IOException unused) {
                    fileOutputStream = null;
                    try {
                        LogUtil.b("PLGACHIEVE_FileUtil", "download IOException");
                        c(inputStream2, fileOutputStream, -1, achieveMedalCallBack);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        i2 = i;
                        c(inputStream2, fileOutputStream, i2, achieveMedalCallBack);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
                try {
                    if (!b(d)) {
                        LogUtil.a("PLGACHIEVE_FileUtil", "createFileDir false");
                        c(inputStream, (FileOutputStream) null, i, achieveMedalCallBack);
                        return;
                    }
                    String c2 = CommonUtil.c(d + ".zip");
                    File a2 = a(c2);
                    int lastIndexOf = d.lastIndexOf(47);
                    int i3 = lastIndexOf + 1;
                    if (!TextUtils.isEmpty(c2) && i3 < c2.length()) {
                        String substring = c2.substring(i3, c2.length());
                        File a3 = a(CommonUtil.c(a(d, lastIndexOf) + File.separator + "_" + substring));
                        if (a3 == null || a2 == null) {
                            throw new IOException();
                        }
                        fileOutputStream2 = new FileOutputStream(a3);
                        try {
                            b(fileOutputStream2, inputStream);
                            i = c(a3, a2, str2, achieveMedalCallBack);
                            inputStream2 = inputStream;
                        } catch (IOException unused2) {
                            inputStream2 = fileOutputStream2;
                            fileOutputStream = inputStream2;
                            inputStream2 = inputStream;
                            LogUtil.b("PLGACHIEVE_FileUtil", "download IOException");
                            c(inputStream2, fileOutputStream, -1, achieveMedalCallBack);
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream2 = inputStream;
                            i2 = i;
                            fileOutputStream = fileOutputStream2;
                            c(inputStream2, fileOutputStream, i2, achieveMedalCallBack);
                            throw th;
                        }
                    }
                    LogUtil.h("PLGACHIEVE_FileUtil", "download zipPath isEmpty or substring IndexOutOfBounds.");
                    c(inputStream, (FileOutputStream) null, i, achieveMedalCallBack);
                    return;
                } catch (IOException unused3) {
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    inputStream2 = inputStream;
                    i2 = i;
                    c(inputStream2, fileOutputStream, i2, achieveMedalCallBack);
                    throw th;
                }
            } else {
                fileOutputStream2 = null;
            }
            c(inputStream2, fileOutputStream2, i, achieveMedalCallBack);
        } catch (IOException unused4) {
            i = -1;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
        }
    }

    private String d(String str) {
        try {
            return this.d.getFilesDir().getCanonicalPath() + File.separator + "achievemedal" + File.separator + str;
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_FileUtil", "initSavePath: IOException");
            return "";
        }
    }

    private HttpURLConnection e(String str) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH))) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            mdl.d(httpsURLConnection);
            httpURLConnection = httpsURLConnection;
        } else {
            httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        }
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("GET");
        return httpURLConnection;
    }

    private void b(FileOutputStream fileOutputStream, InputStream inputStream) {
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    return;
                }
            }
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_FileUtil", "dealFileOutputStream IOException");
            c(inputStream);
            e(fileOutputStream);
        }
    }

    private int c(File file, File file2, String str, AchieveMedalCallBack achieveMedalCallBack) {
        if (!file.renameTo(file2)) {
            if (file.exists()) {
                LogUtil.c("PLGACHIEVE_FileUtil", "deleteRet = ", Boolean.valueOf(file.delete()));
            }
            return -1;
        }
        e(str, achieveMedalCallBack);
        return 0;
    }

    private void c(InputStream inputStream, FileOutputStream fileOutputStream, int i, AchieveMedalCallBack achieveMedalCallBack) {
        c(inputStream);
        e(fileOutputStream);
        if (i != 0) {
            achieveMedalCallBack.onResponse(i);
        }
    }

    public static void e(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null) {
                if (file2.isFile()) {
                    LogUtil.c("PLGACHIEVE_FileUtil", "deleteDirWithFile result=", Boolean.valueOf(file2.delete()));
                } else if (file2.isDirectory()) {
                    e(file2);
                } else {
                    LogUtil.c("PLGACHIEVE_FileUtil", "file type error");
                }
            }
        }
        LogUtil.c("PLGACHIEVE_FileUtil", "deleteDirWithFile result=", Boolean.valueOf(file.delete()));
    }

    private void e(String str, final AchieveMedalCallBack achieveMedalCallBack) {
        try {
            final String str2 = this.d.getFilesDir().getCanonicalPath() + File.separator + "achievemedal" + File.separator + str + ".zip";
            final String str3 = this.d.getFilesDir().getCanonicalPath() + File.separator + "achievemedal" + File.separator + str;
            if (this.b.isShutdown()) {
                return;
            }
            this.b.execute(new Runnable() { // from class: mdt
                @Override // java.lang.Runnable
                public final void run() {
                    FileUtil.this.b(str2, str3, achieveMedalCallBack);
                }
            });
        } catch (IOException e) {
            LogUtil.b("PLGACHIEVE_FileUtil", "FileNotFoundException:", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void b(String str, String str2, AchieveMedalCallBack achieveMedalCallBack) {
        FileOutputStream fileOutputStream;
        ZipInputStream zipInputStream;
        FileOutputStream fileOutputStream2;
        ZipInputStream zipInputStream2;
        ZipInputStream zipInputStream3;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "unZip(zipFileString): untrusted -> " + str);
            return;
        }
        if (!GeneralUtil.isSafePath(str2)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "unZip(outPathString): untrusted -> " + str2);
            return;
        }
        String c2 = CommonUtil.c(str);
        String c3 = CommonUtil.c(str2);
        FileInputStream fileInputStream = null;
        r7 = null;
        ZipInputStream zipInputStream4 = null;
        ZipInputStream zipInputStream5 = null;
        FileOutputStream fileOutputStream3 = null;
        r7 = null;
        FileInputStream fileInputStream2 = null;
        int i = 0;
        try {
            FileInputStream fileInputStream3 = new FileInputStream(c2);
            try {
                zipInputStream3 = new ZipInputStream(fileInputStream3);
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream3.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        String c4 = CommonUtil.c(nextEntry.getName());
                        if (!TextUtils.isEmpty(c4) && d(c4, c3)) {
                            if (nextEntry.isDirectory()) {
                                LogUtil.c("PLGACHIEVE_FileUtil", "createFileDir=", Boolean.valueOf(b(c3 + File.separator + c4.substring(0, c4.length() - 1))));
                            } else {
                                File file = new File(c3 + File.separator + c4);
                                File parentFile = file.getParentFile();
                                if (!parentFile.exists()) {
                                    LogUtil.c("PLGACHIEVE_FileUtil", "parentFile dirResult status=", Boolean.valueOf(parentFile.mkdirs()));
                                }
                                LogUtil.c("PLGACHIEVE_FileUtil", "result = ", Boolean.valueOf(file.createNewFile()));
                                e(fileOutputStream3);
                                fileOutputStream2 = new FileOutputStream(file);
                                try {
                                    e(fileOutputStream2, 0, zipInputStream3);
                                    fileOutputStream3 = fileOutputStream2;
                                } catch (IOException unused) {
                                    zipInputStream4 = zipInputStream3;
                                    zipInputStream2 = zipInputStream4;
                                    fileInputStream2 = fileInputStream3;
                                    try {
                                        LogUtil.b("PLGACHIEVE_FileUtil", "unZip IOException");
                                        zipInputStream = zipInputStream2;
                                        d(fileInputStream2, zipInputStream, fileOutputStream2);
                                        i = -3;
                                        b(str2, i, achieveMedalCallBack);
                                        c(c2);
                                    } catch (Throwable th) {
                                        th = th;
                                        zipInputStream = zipInputStream2;
                                        fileInputStream = fileInputStream2;
                                        fileOutputStream = fileOutputStream2;
                                        d(fileInputStream, zipInputStream, fileOutputStream);
                                        throw th;
                                    }
                                } catch (IllegalArgumentException e) {
                                    e = e;
                                    zipInputStream5 = zipInputStream3;
                                    zipInputStream = zipInputStream5;
                                    fileInputStream2 = fileInputStream3;
                                    try {
                                        LogUtil.b("PLGACHIEVE_FileUtil", "IllegalArgumentException:", e.getMessage());
                                        d(fileInputStream2, zipInputStream, fileOutputStream2);
                                        i = -3;
                                        b(str2, i, achieveMedalCallBack);
                                        c(c2);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileInputStream = fileInputStream2;
                                        fileOutputStream = fileOutputStream2;
                                        d(fileInputStream, zipInputStream, fileOutputStream);
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileOutputStream3 = fileOutputStream2;
                                    fileOutputStream = fileOutputStream3;
                                    fileInputStream = fileInputStream3;
                                    zipInputStream = zipInputStream3;
                                    d(fileInputStream, zipInputStream, fileOutputStream);
                                    throw th;
                                }
                            }
                        }
                        LogUtil.c("PLGACHIEVE_FileUtil", "zipName is empty or zip is too max!");
                    } catch (IOException unused2) {
                        fileOutputStream2 = fileOutputStream3;
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream3;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
                d(fileInputStream3, zipInputStream3, fileOutputStream3);
            } catch (IOException unused3) {
                fileOutputStream2 = null;
            } catch (IllegalArgumentException e3) {
                e = e3;
                fileOutputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                zipInputStream3 = null;
            }
        } catch (IOException unused4) {
            zipInputStream2 = null;
            fileOutputStream2 = null;
        } catch (IllegalArgumentException e4) {
            e = e4;
            zipInputStream = null;
            fileOutputStream2 = null;
        } catch (Throwable th6) {
            th = th6;
            fileOutputStream = null;
            zipInputStream = null;
        }
        b(str2, i, achieveMedalCallBack);
        c(c2);
    }

    private void e(FileOutputStream fileOutputStream, int i, ZipInputStream zipInputStream) {
        int read;
        try {
            byte[] bArr = new byte[1024];
            while (i + 1024 <= 5242880 && (read = zipInputStream.read(bArr)) != -1) {
                fileOutputStream.write(bArr, 0, read);
                i += read;
                fileOutputStream.flush();
            }
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_FileUtil", "dealOutputStream IOException");
            a(zipInputStream);
            e(fileOutputStream);
        }
    }

    private void d(FileInputStream fileInputStream, ZipInputStream zipInputStream, FileOutputStream fileOutputStream) {
        a(fileInputStream);
        a(zipInputStream);
        e(fileOutputStream);
    }

    private void b(String str, int i, AchieveMedalCallBack achieveMedalCallBack) {
        if (new File(str).isDirectory() && i == 0) {
            achieveMedalCallBack.onResponse(0);
        } else {
            LogUtil.c("PLGACHIEVE_FileUtil", "unZip and the dir fail");
            achieveMedalCallBack.onResponse(-3);
        }
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(a(str));
    }

    private void c(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            LogUtil.c("PLGACHIEVE_FileUtil", "deleteDirZipFile result=", Boolean.valueOf(file.delete()));
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null && file2.isFile()) {
                String name = file2.getName();
                int lastIndexOf = name.lastIndexOf(".") + 1;
                if ("zip".equals(name.length() > lastIndexOf ? name.substring(lastIndexOf) : "")) {
                    LogUtil.c("PLGACHIEVE_FileUtil", "deleteDirZipFile result=", Boolean.valueOf(file2.delete()));
                }
            }
        }
    }

    public static File a(String str) {
        return e(new String[]{str});
    }

    public static File e(String[] strArr) {
        if (strArr == null) {
            return new File("");
        }
        File file = null;
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static String a(String str, int i) {
        return (TextUtils.isEmpty(str) || str.length() < i) ? "" : str.substring(0, i);
    }

    private static boolean b(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "createFileDir: untrusted -> " + str);
            return false;
        }
        String c2 = CommonUtil.c(str);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("PLGACHIEVE_FileUtil", "createFileDir, checkPath is isEmpty");
            return false;
        }
        File file = new File(c2);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            Object[] objArr = new Object[2];
            objArr[0] = "dirResult:";
            objArr[1] = mkdirs ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
            LogUtil.c("PLGACHIEVE_FileUtil", objArr);
            if (!mkdirs) {
                return false;
            }
        }
        return true;
    }

    private static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_FileUtil", "closeInputStream IOException");
            }
        }
    }

    private static void e(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_FileUtil", "closeFileOutputStream IOException");
            }
        }
    }

    private static void a(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_FileUtil", "closeFileInputStream IOException");
            }
        }
    }

    private static void a(ZipInputStream zipInputStream) {
        if (zipInputStream != null) {
            try {
                zipInputStream.close();
            } catch (IOException unused) {
                LogUtil.a("PLGACHIEVE_FileUtil", "closeZipInputStream IOException");
            }
        }
    }
}
