package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileLockHelper;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import defpackage.yi;
import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes8.dex */
final class yx implements Closeable {
    private final File b;
    private final File c;
    private final FileLockHelper d;

    yx(File file, File file2) throws IOException {
        this.c = file;
        this.b = file2;
        this.d = FileLockHelper.b(new File(file2, "ModuleLib.lock"));
    }

    List<File> a(yi yiVar, boolean z) throws IOException {
        if (!this.d.c()) {
            throw new IllegalStateException("ModuleLibExtractor was closed");
        }
        File[] listFiles = this.b.listFiles(new FileFilterUtils.FileNotEqualsCollectFilter("ModuleLib.lock"));
        if (!z) {
            return a(listFiles, yiVar);
        }
        return b(listFiles, yiVar);
    }

    private List<File> b(File[] fileArr, yi yiVar) throws IOException {
        b(fileArr, yiVar.b().b());
        ZipFile zipFile = new ZipFile(this.c);
        try {
            return a(zipFile, yiVar);
        } finally {
            FileUtils.d(zipFile);
        }
    }

    private List<File> a(ZipFile zipFile, yi yiVar) throws IOException {
        String format = String.format(Locale.ENGLISH, "lib/%s/", yiVar.b().d());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        ArrayList arrayList = new ArrayList(yiVar.b().b().size());
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            if (name.startsWith(format) && name.endsWith(".so")) {
                String substring = name.substring(name.lastIndexOf(47) + 1);
                yi.c.C0336c b = b(substring, yiVar.b().b());
                if (b == null) {
                    throw new IOException(String.format(Locale.ENGLISH, "Failed to find %s in module-info", substring));
                }
                File file = new File(this.b, substring);
                if (file.exists()) {
                    if (a(b, file)) {
                        arrayList.add(file);
                    } else {
                        FileUtils.i(file);
                        if (file.exists()) {
                            LogUtil.a("Bundle_LibExtractor", "Failed to delete corrupted lib file ", yw.d(file));
                        }
                    }
                }
                if (!b(file, b, zipFile, nextElement)) {
                    throw new IOException("Could not create lib file " + yw.d(file));
                }
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    private boolean b(File file, yi.c.C0336c c0336c, ZipFile zipFile, ZipEntry zipEntry) throws IOException {
        File createTempFile = File.createTempFile("tmp-" + c0336c.d(), "", yw.a().d());
        boolean z = false;
        int i = 0;
        while (i < 3 && !z) {
            i++;
            try {
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                try {
                    FileUtils.a(inputStream, createTempFile);
                    if (!createTempFile.renameTo(file)) {
                        LogUtil.a("Bundle_LibExtractor", "Failed to rename ", yw.d(createTempFile), " to ", yw.d(file));
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused) {
                LogUtil.a("Bundle_LibExtractor", "Failed to extract so :", c0336c.d(), ", attempts times : ", Integer.valueOf(i));
            }
            z = a(c0336c, file);
            if (z) {
                Object[] objArr = new Object[6];
                objArr[0] = "Extraction ";
                objArr[1] = z ? "succeeded" : "failed";
                objArr[2] = " '";
                objArr[3] = yw.d(file);
                objArr[4] = "': length ";
                objArr[5] = Long.valueOf(file.length());
                LogUtil.c("Bundle_LibExtractor", objArr);
                FileUtils.i(createTempFile);
                return z;
            }
            FileUtils.i(file);
            if (file.exists()) {
                LogUtil.a("Bundle_LibExtractor", "Failed to delete extracted lib that has been corrupted ", yw.d(file));
            }
        }
        FileUtils.i(createTempFile);
        return z;
    }

    private boolean a(yi.c.C0336c c0336c, File file) {
        FileUtils.f(file);
        if (c0336c.a() != file.length()) {
            return false;
        }
        String c = SecurityUtils.c(file);
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("Bundle_LibExtractor", "checkLibSha256, get ", c0336c.d(), " sha-256 fail.");
            return true;
        }
        if (c0336c.e().equals(c)) {
            return true;
        }
        LogUtil.a("Bundle_LibExtractor", "Failed to check ", c0336c.d(), " sha-256, excepted ", c0336c.e(), " but ", c);
        return false;
    }

    private void b(File[] fileArr, List<yi.c.C0336c> list) {
        if (CollectionUtils.b(fileArr)) {
            return;
        }
        for (File file : fileArr) {
            if (b(file.getName(), list) == null) {
                FileUtils.b(file);
            }
        }
    }

    private yi.c.C0336c b(String str, List<yi.c.C0336c> list) {
        for (yi.c.C0336c c0336c : list) {
            if (c0336c.d().equals(str)) {
                return c0336c;
            }
        }
        return null;
    }

    private List<File> a(File[] fileArr, yi yiVar) throws IOException {
        try {
            return a(fileArr, yiVar.b().b());
        } catch (IOException unused) {
            return b(fileArr, yiVar);
        }
    }

    private List<File> a(File[] fileArr, List<yi.c.C0336c> list) throws IOException {
        if (CollectionUtils.b(fileArr)) {
            throw new IOException("Missing extracted lib file '" + yw.d(this.b) + "'");
        }
        if (fileArr.length != list.size()) {
            throw new IOException("Unknown lib file present '" + yw.d(this.b) + "'");
        }
        ArrayList arrayList = new ArrayList(fileArr.length);
        for (yi.c.C0336c c0336c : list) {
            File d = d(c0336c, fileArr);
            if (d == null) {
                throw new IOException(String.format(Locale.ENGLISH, "Invalid extracted lib: file %s is not existing!", c0336c.d()));
            }
            if (!a(c0336c, d)) {
                throw new IOException("Invalid extracted lib : file sha-256 is unmatched!");
            }
            arrayList.add(d);
        }
        LogUtil.c("Bundle_LibExtractor", "Existing lib files loaded, num=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private File d(yi.c.C0336c c0336c, File[] fileArr) {
        for (File file : fileArr) {
            if (c0336c.d().equals(file.getName())) {
                return file;
            }
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.d.close();
    }
}
