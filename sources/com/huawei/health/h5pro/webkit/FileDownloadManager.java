package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.y;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class FileDownloadManager {

    public static abstract class DownloadCallback {
        public abstract void onFailure(int i, String str);

        public void onProgress(long j, long j2) {
        }

        public abstract void onSuccess(File file);
    }

    public static String readFileContent(Context context, String str) {
        if (GeneralUtil.isSafePath(str)) {
            return readFileContent(context, new File(str));
        }
        LogUtil.w("H5PRO_FileDownloadOperate", "readFileContent: Invalid file path.");
        return "";
    }

    public static String readFileContent(Context context, File file) {
        if (file == null || !file.getAbsolutePath().startsWith(HpkManager.b.getRootDir(context))) {
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        String sb2 = sb.toString();
                        fileInputStream.close();
                        return sb2;
                    }
                    sb.append(readLine);
                }
            } finally {
            }
        } catch (IOException unused) {
            return "";
        }
    }

    public static boolean mkdirs(String str) {
        if (GeneralUtil.isSafePath(str)) {
            File file = new File(str);
            return file.exists() || file.mkdirs();
        }
        LogUtil.w("H5PRO_FileDownloadOperate", "mkdirs: Invalid file path.");
        return false;
    }

    public static boolean exists(String str) {
        if (GeneralUtil.isSafePath(str)) {
            return new File(str).exists();
        }
        LogUtil.w("H5PRO_FileDownloadOperate", "exists: Invalid file path.");
        return false;
    }

    public static void download(final String str, String str2, final String str3, final DownloadCallback downloadCallback) {
        String str4;
        if (TextUtils.isEmpty(str) || !Patterns.WEB_URL.matcher(str).matches()) {
            str4 = "Invalid URL.";
        } else {
            if (GeneralUtil.isSafePath(str2) && GeneralUtil.isSafePath(str3)) {
                final File file = new File(str2);
                if (!file.exists() && !file.mkdirs()) {
                    downloadCallback.onFailure(-1, "Invalid file path.");
                    return;
                }
                ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.health.h5pro.webkit.FileDownloadManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FileDownloadManager.download(str, new File(file, str3), downloadCallback);
                    }
                });
                newSingleThreadExecutor.shutdown();
                return;
            }
            str4 = "Invalid file path or file name.";
        }
        downloadCallback.onFailure(-1, str4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    public static void download(String str, File file, DownloadCallback downloadCallback) {
        HttpURLConnection httpURLConnection;
        ?? r7;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        ?? r72;
        URLConnection openConnection;
        int responseCode;
        HttpURLConnection httpURLConnection2;
        LogUtil.i("H5PRO_FileDownloadOperate", "download start");
        if (TextUtils.isEmpty(str) || !Patterns.WEB_URL.matcher(str).matches()) {
            downloadCallback.onFailure(-1, "Invalid URL.");
            return;
        }
        FileOutputStream fileOutputStream3 = null;
        try {
            try {
                openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
            } catch (IOException e) {
                e = e;
                httpURLConnection = null;
                fileOutputStream = null;
            }
            try {
                if (openConnection instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) openConnection;
                    try {
                        try {
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setReadTimeout(y.c);
                            httpURLConnection.setRequestProperty(j2.v, "identity");
                            httpURLConnection.setRequestProperty("Charset", StandardCharsets.UTF_8.name());
                            httpURLConnection.connect();
                            responseCode = httpURLConnection.getResponseCode();
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection = httpURLConnection;
                            r7 = 0;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        httpURLConnection = httpURLConnection;
                    }
                    if (responseCode == 200) {
                        ?? inputStream = httpURLConnection.getInputStream();
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                byte[] bArr = new byte[1048576];
                                long currentTimeMillis = System.currentTimeMillis();
                                long contentLength = httpURLConnection.getContentLength();
                                long j = 0;
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                    httpURLConnection2 = httpURLConnection;
                                    j += read;
                                    try {
                                        downloadCallback.onProgress(contentLength, j);
                                        httpURLConnection = httpURLConnection2;
                                    } catch (IOException e3) {
                                        e = e3;
                                        httpURLConnection = httpURLConnection2;
                                        fileOutputStream3 = inputStream;
                                        downloadCallback.onFailure(-1, e.getMessage());
                                        r72 = fileOutputStream3;
                                        closeIo(fileOutputStream, r72, httpURLConnection);
                                        return;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        httpURLConnection = httpURLConnection2;
                                        fileOutputStream2 = inputStream;
                                        fileOutputStream3 = fileOutputStream;
                                        r7 = fileOutputStream2;
                                        closeIo(fileOutputStream3, r7, httpURLConnection);
                                        throw th;
                                    }
                                }
                                httpURLConnection2 = httpURLConnection;
                                long currentTimeMillis2 = System.currentTimeMillis();
                                downloadCallback.onSuccess(file);
                                LogUtil.i("H5PRO_FileDownloadOperate", "Download duration: " + (currentTimeMillis2 - currentTimeMillis) + " ms");
                                httpURLConnection = httpURLConnection2;
                                r72 = inputStream;
                            } catch (IOException e4) {
                                e = e4;
                                httpURLConnection2 = httpURLConnection;
                            } catch (Throwable th3) {
                                th = th3;
                                httpURLConnection2 = httpURLConnection;
                            }
                        } catch (IOException e5) {
                            e = e5;
                            httpURLConnection = httpURLConnection;
                            fileOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            httpURLConnection = httpURLConnection;
                            r7 = inputStream;
                            closeIo(fileOutputStream3, r7, httpURLConnection);
                            throw th;
                        }
                        closeIo(fileOutputStream, r72, httpURLConnection);
                        return;
                    }
                    try {
                        downloadCallback.onFailure(responseCode, "Download failed,The return code is " + responseCode + ".");
                        closeIo(null, null, httpURLConnection);
                        return;
                    } catch (IOException e6) {
                        e = e6;
                    }
                } else {
                    try {
                        downloadCallback.onFailure(-1, "urlConnection error");
                        closeIo(null, null, null);
                        return;
                    } catch (IOException e7) {
                        e = e7;
                        httpURLConnection = null;
                    }
                }
                downloadCallback.onFailure(-1, e.getMessage());
                r72 = fileOutputStream3;
                closeIo(fileOutputStream, r72, httpURLConnection);
                return;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream2 = fileOutputStream3;
                fileOutputStream3 = fileOutputStream;
                r7 = fileOutputStream2;
                closeIo(fileOutputStream3, r7, httpURLConnection);
                throw th;
            }
            fileOutputStream = null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            r7 = 0;
        }
    }

    public static void deleteFiles(String str, boolean z) {
        if (GeneralUtil.isSafePath(str)) {
            deleteFiles(new File(str), z);
        } else {
            LogUtil.w("H5PRO_FileDownloadOperate", "deleteFiles: Invalid file path.");
        }
    }

    public static void deleteFiles(File file, boolean z) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                deleteFiles(file2, true);
            }
        }
        if (z) {
            file.delete();
        }
    }

    public static void closeIo(OutputStream outputStream, InputStream inputStream, HttpURLConnection httpURLConnection) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                LogUtil.e("H5PRO_FileDownloadOperate", "IO close faile");
                return;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
