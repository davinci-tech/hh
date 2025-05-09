package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.webkit.FileDownloadManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class HpkManager {
    public static final HpkManager b = new HpkManager();

    public boolean versionMatches(String str) {
        return !TextUtils.isEmpty(str) && str.matches("[0-9]+(\\.[0-9]+)*");
    }

    public int versionCompare(String str, String str2) {
        if (!versionMatches(str)) {
            LogUtil.w("H5PRO_HpkManager", String.format(Locale.ENGLISH, "versionCompare: invalid version1: %s", str));
            throw new HpkVersionException(str);
        }
        if (!versionMatches(str2)) {
            LogUtil.w("H5PRO_HpkManager", String.format(Locale.ENGLISH, "versionCompare: invalid version2: %s", str2));
            throw new HpkVersionException(str2);
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        for (int i2 = 0; i2 < min; i2++) {
            i = Integer.compare(Integer.parseInt(split[i2]), Integer.parseInt(split2[i2]));
            if (i != 0) {
                break;
            }
        }
        return (i != 0 || split.length == split2.length) ? i : Integer.compare(split.length, split2.length);
    }

    public void updateInstalledFile(Context context, String str) {
        if (context == null) {
            LogUtil.w("H5PRO_HpkManager", "updateInstalledFile: context is null");
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(getInstalledFilePath(context, str));
        try {
            String json = new InstalledMeta(new Date().getTime(), EnvironmentHelper.getInstance().getBuildType()).toJson();
            if (!TextUtils.isEmpty(json)) {
                fileOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public boolean isInstalled(Context context, String str) {
        if (!new File(getInstalledFilePath(context, str)).exists()) {
            return false;
        }
        try {
            return new File(getInstallPath(context, str)).exists();
        } catch (FileNotFoundException e) {
            LogUtil.e("H5PRO_HpkManager", "isInstalled: installDir exist -> " + e.getMessage());
            return false;
        }
    }

    public boolean isDownloadedHpk(Context context, String str) {
        if (context != null) {
            return FileDownloadManager.exists(getHpkFilePath(context, str));
        }
        LogUtil.w("H5PRO_HpkManager", "isDownloadedHpk: context is null");
        return false;
    }

    public boolean installDownloadedHpk(Context context, String str) {
        if (context == null) {
            LogUtil.w("H5PRO_HpkManager", "installDownloadedHpk: context is null");
            return false;
        }
        FileDownloadManager.deleteFiles(getInstalledFilePath(context, str), false);
        try {
            String installPath = getInstallPath(context, str);
            FileDownloadManager.deleteFiles(installPath, false);
            String hpkFilePath = getHpkFilePath(context, str);
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(hpkFilePath));
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        LogUtil.w("H5PRO_HpkManager", "installHpk: the hpk file is damaged");
                        zipInputStream.close();
                        return false;
                    }
                    do {
                        File file = new File(installPath, nextEntry.getName());
                        File parentFile = nextEntry.isDirectory() ? file : file.getParentFile();
                        if (parentFile == null) {
                            LogUtil.w("H5PRO_HpkManager", "installHpk: destFileDir is null");
                            zipInputStream.close();
                            return false;
                        }
                        if (!parentFile.exists() && !parentFile.mkdirs()) {
                            LogUtil.w("H5PRO_HpkManager", "installHpk: failed to make dir -> " + parentFile.getAbsolutePath());
                            zipInputStream.close();
                            return false;
                        }
                        if (!nextEntry.isDirectory()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                fileOutputStream.close();
                            } finally {
                            }
                        }
                        nextEntry = zipInputStream.getNextEntry();
                    } while (nextEntry != null);
                    updateInstalledFile(context, str);
                    zipInputStream.close();
                    return true;
                } catch (Throwable th) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                LogUtil.w("H5PRO_HpkManager", "installHpk: ioException -> " + e.getMessage());
                return false;
            } finally {
                FileDownloadManager.deleteFiles(hpkFilePath, false);
            }
        } catch (FileNotFoundException e2) {
            LogUtil.w("H5PRO_HpkManager", "installHpk: fileNotFoundException -> " + e2.getMessage());
            return false;
        }
    }

    public boolean hasNewVersion(Context context, String str) {
        if (!isInstalled(context, str)) {
            return true;
        }
        try {
            String versionFilePath = getVersionFilePath(context, str);
            String readFileContent = FileDownloadManager.readFileContent(context, getInstalledFilePath(context, str));
            if (!GeneralUtil.isNumbers(readFileContent) || System.currentTimeMillis() - Long.parseLong(readFileContent) > 60000) {
                FileDownloadManager.download(getVersionDownloadUrl(str), new File(versionFilePath), new FileDownloadManager.DownloadCallback() { // from class: com.huawei.health.h5pro.webkit.HpkManager.1
                    @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                    public void onSuccess(File file) {
                        LogUtil.i("H5PRO_HpkManager", "hasNewVersion: onSuccess");
                    }

                    @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                    public void onFailure(int i, String str2) {
                        LogUtil.w("H5PRO_HpkManager", String.format(Locale.ENGLISH, "hasNewVersion(onFailure): %d -> %s", Integer.valueOf(i), str2));
                    }
                });
            }
            return versionCompare(new JSONObject(FileDownloadManager.readFileContent(context, getManifestFilePath(context, str))).optString("version"), new JSONObject(FileDownloadManager.readFileContent(context, versionFilePath)).optString("latest")) == -1;
        } catch (HpkVersionException | FileNotFoundException | NumberFormatException | JSONException e) {
            LogUtil.e("H5PRO_HpkManager", "hasNewVersion: exception -> " + e.getMessage());
            return false;
        }
    }

    public String getVersionFilePath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s", getH5WorkPath(context, str), getVersionFileName(str));
    }

    public String getVersionFileName(String str) {
        return String.format(Locale.ENGLISH, "%s-version.json", str);
    }

    public String getVersionDownloadUrl(String str) {
        return String.format(Locale.ENGLISH, "%s%s-version.json", getBaseUrl(str), str);
    }

    public String getRootDir(Context context) {
        File file = new File(WebKitUtil.isStrictMode() ? context.getFilesDir() : context.getExternalFilesDir(""), "h5pro");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public String getManifestFilePath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/manifest.json", getInstallPath(context, str));
    }

    public String getInstalledFilePath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s", getH5WorkPath(context, str), getInstalledFileName(str));
    }

    public String getInstalledFileName(String str) {
        return String.format(Locale.ENGLISH, "%s.installed", str);
    }

    public String getInstallPath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s", getH5WorkPath(context, str), getInstallDirNamePrefix(a(context, str)) + EnvironmentHelper.getInstance().getUserFlag());
    }

    public String getInstallDirNamePrefix(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        return str.substring(length < 10 ? 0 : length - 10, length < 10 ? Math.min(length, 7) : length - 3);
    }

    public String getInitFilePath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s/init.em", getRootDir(context), str);
    }

    public String getHpkFilePath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s", getH5WorkPath(context, str), getHpkFileName(str));
    }

    public String getHpkFileName(String str) {
        return String.format(Locale.ENGLISH, "%s.hpk", str);
    }

    public String getHpkDownloadUrl(String str, String str2) {
        return String.format(Locale.ENGLISH, "%s%s-%s.hpk", getBaseUrl(str), str, str2);
    }

    public String getH5WorkPath(Context context, String str) {
        return String.format(Locale.ENGLISH, "%s/%s", getRootDir(context), str);
    }

    public String getBaseUrl(String str) {
        return String.format(Locale.ENGLISH, "%s%s/", EnvironmentHelper.getInstance().getUrl(), str);
    }

    private String a(Context context, String str) {
        File file = new File(getInitFilePath(context, str));
        if (file.exists()) {
            return String.valueOf(file.lastModified());
        }
        throw new FileNotFoundException("init.em not created!");
    }
}
