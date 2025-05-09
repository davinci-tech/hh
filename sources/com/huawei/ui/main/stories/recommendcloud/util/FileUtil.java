package com.huawei.ui.main.stories.recommendcloud.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.ui.main.stories.recommendcloud.data.RecommendCloudObject;
import com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudCallBack;
import com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudHttpCallBack;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class FileUtil {
    private static final int BUFFER_SIZE = 1024;
    private static final int MAXSIZE = 10485760;
    private static final String TAG = "UIDV_RecommendFileUtil";
    private static volatile FileUtil sInstance;
    private Context mContext;
    private ExecutorService mExecutorService;

    private FileUtil(Context context) {
        LogUtil.a(TAG, com.huawei.android.hicloud.sync.util.FileUtil.TAG);
        this.mContext = context.getApplicationContext();
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static FileUtil getInstance(Context context) {
        LogUtil.a(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (FileUtil.class) {
                if (sInstance == null) {
                    sInstance = new FileUtil(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRightFileName(String str, String str2) {
        try {
            return Normalizer.normalize(new File(str2, str).getCanonicalPath(), Normalizer.Form.NFKC).startsWith(Normalizer.normalize(new File(str2).getCanonicalPath(), Normalizer.Form.NFKC));
        } catch (IOException unused) {
            LogUtil.b(TAG, "isRightFileName IOException");
            return false;
        }
    }

    public void doDownload(RecommendCloudObject recommendCloudObject, final RecommendCloudHttpCallBack recommendCloudHttpCallBack) {
        LogUtil.a(TAG, "doDownload");
        if (recommendCloudHttpCallBack == null) {
            LogUtil.h(TAG, "doDownload callBack is null");
            return;
        }
        if (recommendCloudObject == null) {
            LogUtil.h(TAG, "RecommendCloudObject is null");
            recommendCloudHttpCallBack.onResponce(-1, RecommendConstants.DOWNLOAD_FAIL);
            return;
        }
        final String fileId = recommendCloudObject.getFileId();
        final String downloadUrl = recommendCloudObject.getDownloadUrl();
        if (TextUtils.isEmpty(fileId) || TextUtils.isEmpty(downloadUrl)) {
            LogUtil.h(TAG, "fileId or downloadUrl is null");
            recommendCloudHttpCallBack.onResponce(-1, RecommendConstants.DOWNLOAD_FAIL);
            return;
        }
        try {
            final String str = this.mContext.getFilesDir().getCanonicalPath() + File.separator + RecommendConstants.DOWNLOAD_DIR + File.separator;
            final String str2 = str + fileId;
            LogUtil.a(TAG, "savePath :", str2, "downloadUrl :", downloadUrl);
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h(TAG, "savePath is null");
                recommendCloudHttpCallBack.onResponce(-1, RecommendConstants.DOWNLOAD_FAIL);
                return;
            }
            ExecutorService executorService = this.mExecutorService;
            if (executorService == null || executorService.isShutdown()) {
                this.mExecutorService = Executors.newSingleThreadExecutor();
            }
            this.mExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.recommendcloud.util.FileUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    if (FileUtil.this.isRightFileName(fileId, str)) {
                        FileUtil.this.download(downloadUrl, str2, recommendCloudHttpCallBack);
                    } else {
                        LogUtil.h(FileUtil.TAG, "savePath is not sanitze");
                        recommendCloudHttpCallBack.onResponce(-1, RecommendConstants.DOWNLOAD_FAIL);
                    }
                }
            });
        } catch (IOException unused) {
            LogUtil.b(TAG, "doDownload IOException");
            recommendCloudHttpCallBack.onResponce(-1, RecommendConstants.DOWNLOAD_FAIL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download(String str, String str2, RecommendCloudHttpCallBack recommendCloudHttpCallBack) {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        int responseCode;
        FileOutputStream fileOutputStream2;
        LogUtil.a(TAG, "download");
        String str3 = RecommendConstants.DOWNLOAD_FAIL;
        int i = -1;
        InputStream inputStream2 = null;
        r7 = null;
        InputStream inputStream3 = null;
        inputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        FileOutputStream fileOutputStream4 = null;
        try {
            try {
                URL url = new URL(str);
                if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH))) {
                    LogUtil.a(TAG, "download https");
                    httpURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                } else {
                    httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                }
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestMethod("GET");
                responseCode = httpURLConnection.getResponseCode();
            } catch (Throwable th) {
                th = th;
            }
            try {
                LogUtil.a(TAG, "resultCode = ", Integer.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        LogUtil.a(TAG, "saveFile");
                        if (!createFileDir(str2)) {
                            LogUtil.h(TAG, "createFileDir false");
                            closeInputStream(inputStream);
                            closeFileOutputStream(null);
                            recommendCloudHttpCallBack.onResponce(responseCode, RecommendConstants.DOWNLOAD_FAIL);
                            return;
                        }
                        String str4 = str2 + ".zip";
                        LogUtil.c(TAG, "zipPath = ", str4);
                        int lastIndexOf = str2.lastIndexOf(47);
                        String fileDir = getFileDir(str2, lastIndexOf);
                        String substring = str4.substring(lastIndexOf + 1, str4.length());
                        LogUtil.a(TAG, "fileName = ", substring);
                        File file = new File(fileDir + File.separator + "_" + substring);
                        fileOutputStream2 = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                } else {
                                    fileOutputStream2.write(bArr, 0, read);
                                }
                            }
                            fileOutputStream2.flush();
                            boolean renameTo = file.renameTo(new File(str4));
                            LogUtil.a(TAG, "isRenameOk = ", Boolean.valueOf(renameTo));
                            if (renameTo) {
                                str3 = "success";
                                i = 0;
                            } else {
                                if (file.exists()) {
                                    LogUtil.a(TAG, "isDeleted = ", Boolean.valueOf(file.delete()));
                                }
                                str3 = "rename fail";
                            }
                            responseCode = i;
                            inputStream3 = inputStream;
                        } catch (IOException e) {
                            e = e;
                            fileOutputStream3 = fileOutputStream2;
                            String message = e.getMessage();
                            LogUtil.b(TAG, "download IOException");
                            closeInputStream(inputStream);
                            closeFileOutputStream(fileOutputStream3);
                            recommendCloudHttpCallBack.onResponce(-1, message);
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream4 = fileOutputStream2;
                            fileOutputStream = fileOutputStream4;
                            inputStream2 = inputStream;
                            i = responseCode;
                            closeInputStream(inputStream2);
                            closeFileOutputStream(fileOutputStream);
                            recommendCloudHttpCallBack.onResponce(i, RecommendConstants.DOWNLOAD_FAIL);
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                } else {
                    fileOutputStream2 = null;
                }
                closeInputStream(inputStream3);
                closeFileOutputStream(fileOutputStream2);
                recommendCloudHttpCallBack.onResponce(responseCode, str3);
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
                String message2 = e.getMessage();
                LogUtil.b(TAG, "download IOException");
                closeInputStream(inputStream);
                closeFileOutputStream(fileOutputStream3);
                recommendCloudHttpCallBack.onResponce(-1, message2);
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                i = responseCode;
                closeInputStream(inputStream2);
                closeFileOutputStream(fileOutputStream);
                recommendCloudHttpCallBack.onResponce(i, RecommendConstants.DOWNLOAD_FAIL);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            closeInputStream(inputStream2);
            closeFileOutputStream(fileOutputStream);
            recommendCloudHttpCallBack.onResponce(i, RecommendConstants.DOWNLOAD_FAIL);
            throw th;
        }
    }

    public void doUnZip(final RecommendCloudObject recommendCloudObject, final RecommendCloudCallBack recommendCloudCallBack) {
        LogUtil.a(TAG, "doUnZip doUnZip");
        if (recommendCloudCallBack == null) {
            LogUtil.h(TAG, "doUnZip callBack is null");
            return;
        }
        if (recommendCloudObject == null) {
            LogUtil.h(TAG, "RecommendCloudObject is null");
            recommendCloudCallBack.onResponce("", -3);
            return;
        }
        String fileId = recommendCloudObject.getFileId();
        if (TextUtils.isEmpty(fileId)) {
            LogUtil.h(TAG, "fileId is null");
            recommendCloudCallBack.onResponce("", -3);
            return;
        }
        if (TextUtils.isEmpty(recommendCloudObject.getVer())) {
            LogUtil.h(TAG, "ver is null");
            recommendCloudCallBack.onResponce("", -3);
            return;
        }
        try {
            final String str = this.mContext.getFilesDir().getCanonicalPath() + File.separator + RecommendConstants.DOWNLOAD_DIR + File.separator + fileId;
            final String str2 = str + ".zip";
            ExecutorService executorService = this.mExecutorService;
            if (executorService == null || executorService.isShutdown()) {
                this.mExecutorService = Executors.newSingleThreadExecutor();
            }
            this.mExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.recommendcloud.util.FileUtil.3
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a(FileUtil.TAG, "zipFileString :", str2, " outPathString :", str, " RecommendCloudObject :", recommendCloudObject);
                    FileUtil.this.unZip(str2, str, recommendCloudObject, recommendCloudCallBack);
                    File file = new File(str2);
                    if (file.exists()) {
                        LogUtil.a(FileUtil.TAG, "isDelete=", Boolean.valueOf(file.delete()));
                    }
                }
            });
        } catch (IOException unused) {
            LogUtil.b(TAG, "doUnZip IOException");
            recommendCloudCallBack.onResponce("", -3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unZip(String str, String str2, RecommendCloudObject recommendCloudObject, RecommendCloudCallBack recommendCloudCallBack) {
        FileOutputStream fileOutputStream;
        ZipInputStream zipInputStream;
        FileInputStream fileInputStream;
        int read;
        FileInputStream fileInputStream2 = null;
        r6 = null;
        r6 = null;
        r6 = null;
        FileOutputStream fileOutputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        int i = 0;
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    zipInputStream = new ZipInputStream(fileInputStream);
                    int i2 = 0;
                    while (i2 + 1024 <= MAXSIZE) {
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            String name = nextEntry.getName();
                            if (isRightFileName(name, str2) && !name.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                                if (nextEntry.isDirectory()) {
                                    LogUtil.a(TAG, "entryName isCreated ", Boolean.valueOf(createFileDir(str2 + File.separator + name.substring(0, name.length() - 1))));
                                } else {
                                    String str3 = str2 + File.separator + name;
                                    if (TextUtils.isEmpty(CommonUtil.c(str3))) {
                                        closeFileInputStream(fileInputStream);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream3);
                                        return;
                                    }
                                    File file = new File(str3);
                                    File parentFile = file.getParentFile();
                                    if (parentFile != null && !parentFile.exists()) {
                                        LogUtil.a(TAG, "parent isCreated ", Boolean.valueOf(parentFile.mkdirs()));
                                    }
                                    LogUtil.a(TAG, "isCreatedNew ", Boolean.valueOf(file.createNewFile()));
                                    closeFileOutputStream(fileOutputStream3);
                                    String d = CommonUtil.d(file.getCanonicalPath());
                                    if (TextUtils.isEmpty(d)) {
                                        LogUtil.h(TAG, "unZip legalPath is empty");
                                        closeFileInputStream(fileInputStream);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream3);
                                        return;
                                    }
                                    FileOutputStream fileOutputStream4 = new FileOutputStream(d);
                                    try {
                                        byte[] bArr = new byte[1024];
                                        while (i2 + 1024 <= MAXSIZE && (read = zipInputStream.read(bArr)) != -1) {
                                            fileOutputStream4.write(bArr, 0, read);
                                            i2 += read;
                                            fileOutputStream4.flush();
                                        }
                                        if (i2 > MAXSIZE) {
                                            LogUtil.h(TAG, "File being unzipped is too big.");
                                            closeFileInputStream(fileInputStream);
                                            closeZipInputStream(zipInputStream);
                                            closeFileOutputStream(fileOutputStream4);
                                            return;
                                        }
                                        fileOutputStream3 = fileOutputStream4;
                                    } catch (IOException e) {
                                        e = e;
                                        fileOutputStream3 = fileOutputStream4;
                                        LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                                        closeFileInputStream(fileInputStream);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream3);
                                        i = -3;
                                        unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                                    } catch (IllegalArgumentException e2) {
                                        e = e2;
                                        fileOutputStream3 = fileOutputStream4;
                                        LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                                        closeFileInputStream(fileInputStream);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream3);
                                        i = -3;
                                        unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                                    } catch (IllegalStateException e3) {
                                        e = e3;
                                        fileOutputStream3 = fileOutputStream4;
                                        LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                                        closeFileInputStream(fileInputStream);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream3);
                                        i = -3;
                                        unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                                    } catch (Throwable th) {
                                        th = th;
                                        fileOutputStream2 = fileOutputStream4;
                                        fileOutputStream = fileOutputStream2;
                                        fileInputStream2 = fileInputStream;
                                        closeFileInputStream(fileInputStream2);
                                        closeZipInputStream(zipInputStream);
                                        closeFileOutputStream(fileOutputStream);
                                        throw th;
                                    }
                                }
                            }
                            LogUtil.h(TAG, "outPath is not right");
                        } catch (IOException e4) {
                            e = e4;
                        } catch (IllegalArgumentException e5) {
                            e = e5;
                        } catch (IllegalStateException e6) {
                            e = e6;
                        }
                    }
                    closeFileInputStream(fileInputStream);
                    closeZipInputStream(zipInputStream);
                    closeFileOutputStream(fileOutputStream3);
                } catch (IOException e7) {
                    e = e7;
                    zipInputStream = null;
                    LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                    closeFileInputStream(fileInputStream);
                    closeZipInputStream(zipInputStream);
                    closeFileOutputStream(fileOutputStream3);
                    i = -3;
                    unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                } catch (IllegalArgumentException e8) {
                    e = e8;
                    zipInputStream = null;
                    LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                    closeFileInputStream(fileInputStream);
                    closeZipInputStream(zipInputStream);
                    closeFileOutputStream(fileOutputStream3);
                    i = -3;
                    unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                } catch (IllegalStateException e9) {
                    e = e9;
                    zipInputStream = null;
                    LogUtil.b(TAG, "unZip Exception = ", e.getClass());
                    closeFileInputStream(fileInputStream);
                    closeZipInputStream(zipInputStream);
                    closeFileOutputStream(fileOutputStream3);
                    i = -3;
                    unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
                } catch (Throwable th2) {
                    th = th2;
                    zipInputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e10) {
            e = e10;
            fileInputStream = null;
            zipInputStream = null;
            LogUtil.b(TAG, "unZip Exception = ", e.getClass());
            closeFileInputStream(fileInputStream);
            closeZipInputStream(zipInputStream);
            closeFileOutputStream(fileOutputStream3);
            i = -3;
            unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
        } catch (IllegalArgumentException e11) {
            e = e11;
            fileInputStream = null;
            zipInputStream = null;
            LogUtil.b(TAG, "unZip Exception = ", e.getClass());
            closeFileInputStream(fileInputStream);
            closeZipInputStream(zipInputStream);
            closeFileOutputStream(fileOutputStream3);
            i = -3;
            unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
        } catch (IllegalStateException e12) {
            e = e12;
            fileInputStream = null;
            zipInputStream = null;
            LogUtil.b(TAG, "unZip Exception = ", e.getClass());
            closeFileInputStream(fileInputStream);
            closeZipInputStream(zipInputStream);
            closeFileOutputStream(fileOutputStream3);
            i = -3;
            unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            zipInputStream = null;
            closeFileInputStream(fileInputStream2);
            closeZipInputStream(zipInputStream);
            closeFileOutputStream(fileOutputStream);
            throw th;
        }
        unZipCallback(str2, recommendCloudObject, recommendCloudCallBack, i);
    }

    private void unZipCallback(String str, RecommendCloudObject recommendCloudObject, RecommendCloudCallBack recommendCloudCallBack, int i) {
        if (new File(str).isDirectory() && i == 0) {
            LogUtil.a(TAG, "unZip finish");
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(this.mContext, String.valueOf(10000), recommendCloudObject.getFileId() + RecommendConstants.VER_POSTFIX, recommendCloudObject.getVer(), storageParams);
            recommendCloudCallBack.onResponce(recommendCloudObject.getFileId(), i);
            return;
        }
        LogUtil.h(TAG, "unZip and the dir fail");
        recommendCloudCallBack.onResponce(recommendCloudObject.getFileId(), -3);
    }

    private static String getFileDir(String str, int i) {
        try {
            return str.substring(0, i);
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b(TAG, "getFileDir StringIndexOutOfBoundsException");
            return "";
        }
    }

    private static boolean createFileDir(String str) {
        if (TextUtils.isEmpty(CommonUtil.d(str))) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            Object[] objArr = new Object[2];
            objArr[0] = "isCreated ";
            objArr[1] = mkdirs ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
            LogUtil.a(TAG, objArr);
            if (!mkdirs) {
                return false;
            }
        }
        return true;
    }

    private static void closeInputStream(InputStream inputStream) {
        LogUtil.a(TAG, "closeInputStream");
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "closeInputStream IOException");
            }
        }
    }

    private static void closeFileOutputStream(FileOutputStream fileOutputStream) {
        LogUtil.a(TAG, "closeFileOutputStream");
        if (fileOutputStream != null) {
            try {
                try {
                    fileOutputStream.close();
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b(TAG, "closeFileOutputStream IOException");
                    }
                } catch (IOException unused2) {
                    LogUtil.b(TAG, "closeFileOutputStream IOException");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b(TAG, "closeFileOutputStream IOException");
                    }
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b(TAG, "closeFileOutputStream IOException");
                }
                throw th;
            }
        }
    }

    private static void closeFileInputStream(FileInputStream fileInputStream) {
        LogUtil.a(TAG, "closeFileOutputStream");
        if (fileInputStream != null) {
            try {
                try {
                    fileInputStream.close();
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b(TAG, "closeFileInputStream IOException");
                    }
                } catch (IOException unused2) {
                    LogUtil.b(TAG, "closeFileInputStream IOException");
                    try {
                        fileInputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b(TAG, "closeFileInputStream IOException");
                    }
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b(TAG, "closeFileInputStream IOException");
                }
                throw th;
            }
        }
    }

    private static void closeZipInputStream(ZipInputStream zipInputStream) {
        LogUtil.a(TAG, "closeZipInputStream");
        if (zipInputStream != null) {
            try {
                try {
                    zipInputStream.close();
                    try {
                        zipInputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b(TAG, "closeZipInputStream IOException");
                    }
                } catch (IOException unused2) {
                    LogUtil.b(TAG, "closeZipInputStream IOException");
                    try {
                        zipInputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b(TAG, "closeZipInputStream IOException");
                    }
                }
            } catch (Throwable th) {
                try {
                    zipInputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b(TAG, "closeZipInputStream IOException");
                }
                throw th;
            }
        }
    }
}
