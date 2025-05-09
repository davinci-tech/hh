package com.huawei.health.h5pro.jsbridge.system.storage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.health.h5pro.webkit.FileDownloadManager;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.ble.BleConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class FileOperateApi {

    /* renamed from: a, reason: collision with root package name */
    public String f2434a;
    public final WeakReference<Context> b;
    public ExecutorService c;
    public final WeakReference<H5ProInstance> f;
    public static List<String> e = Collections.synchronizedList(new ArrayList());
    public static Handler d = null;

    public void saveFile(long j, String str) {
        LogUtil.i("H5PRO_FileOperateApi", "saveFile");
        if (TextUtils.isEmpty(str)) {
            d(-1, -1, j, "param is empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("url");
            if (TextUtils.isEmpty(optString)) {
                d(-1, -1, j, "url is empty");
                return;
            }
            String optString2 = jSONObject.optString(ContentResource.FILE_NAME);
            if (!GeneralUtil.isSafePath(optString2)) {
                d(-1, -1, j, "invalid fileName");
            } else {
                c(jSONObject.optString("fileSubDir"));
                b(optString, optString2, jSONObject.optString("type", FileType.BASE64.type), j);
            }
        } catch (JSONException e2) {
            LogUtil.e("H5PRO_FileOperateApi", "saveFile: jsonException -> " + e2.getMessage());
            d(-1, -1, j, e2.getMessage());
        }
    }

    public void getFile(final long j, String str) {
        LogUtil.i("H5PRO_FileOperateApi", "getFile");
        if (TextUtils.isEmpty(str)) {
            d(-1, -1, j, "param is empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString(ContentResource.FILE_NAME);
            final String optString2 = jSONObject.optString("type", FileType.BASE64.type);
            if (!GeneralUtil.isSafePath(optString)) {
                d(-1, -1, j, "invalid fileName");
                return;
            }
            c(jSONObject.optString("fileSubDir"));
            if (this.c == null) {
                this.c = Executors.newSingleThreadExecutor();
            }
            this.c.execute(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.storage.FileOperateApi.2
                @Override // java.lang.Runnable
                public void run() {
                    File b = FileOperateApi.this.b(optString, optString2);
                    if (b == null) {
                        FileOperateApi.this.d(-1, -1, j, "File " + optString + " not found");
                        return;
                    }
                    if (TextUtils.equals(optString2, FileType.BASE64.type)) {
                        FileOperateApi.this.d(b, j);
                    } else {
                        FileOperateApi fileOperateApi = FileOperateApi.this;
                        fileOperateApi.d(0, 0, j, fileOperateApi.e(optString));
                    }
                }
            });
        } catch (JSONException e2) {
            LogUtil.e("H5PRO_FileOperateApi", "getFile: jsonException -> " + e2.getMessage());
            d(-1, -1, j, e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void d(int i, int i2, long j, T t) {
        Handler handler = d;
        if (handler == null) {
            LogUtil.w("H5PRO_FileOperateApi", "sendSuccessMessage: mHandler is null");
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        if (obtainMessage == null) {
            obtainMessage = new Message();
        }
        obtainMessage.what = i;
        obtainMessage.arg1 = i2;
        obtainMessage.arg2 = (int) j;
        obtainMessage.obj = t;
        d.sendMessage(obtainMessage);
    }

    private String b(String str) {
        H5ProInstance h5ProInstance = (H5ProInstance) GeneralUtil.getReferent(this.f);
        H5ProAppInfo appInfo = h5ProInstance == null ? null : h5ProInstance.getAppInfo();
        if (appInfo == null) {
            return TextUtils.isEmpty(str) ? "DEFAULT_APP_ID" : str;
        }
        String hostFromUrl = appInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_LIGHT_APP ? AppInfoUtil.getInstance().getHostFromUrl(h5ProInstance.getUrl()) : appInfo.getPkgName();
        if (TextUtils.isEmpty(hostFromUrl)) {
            return TextUtils.isEmpty(str) ? "DEFAULT_APP_ID" : str;
        }
        return hostFromUrl + File.separator + str;
    }

    private void c(String str) {
        String nativeFilePath;
        String b = b(str);
        Context context = (Context) GeneralUtil.getReferent(this.b);
        if (context == null) {
            nativeFilePath = "";
        } else {
            try {
                nativeFilePath = StorageUtil.getNativeFilePath(context, b);
            } catch (IOException e2) {
                LogUtil.e("H5PRO_FileOperateApi", "initFilePath: ioException -> " + e2.getMessage());
                this.f2434a = "";
            }
        }
        this.f2434a = nativeFilePath;
        LogUtil.d("H5PRO_FileOperateApi", "initFilePath: mFilePath -> " + this.f2434a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str) {
        return this.f2434a.endsWith(File.separator) ? String.format(Locale.ENGLISH, "%s%s", this.f2434a, str) : String.format(Locale.ENGLISH, "%s%s%s", this.f2434a, File.separator, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(File file, long j) {
        String str;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                str = new String(bArr, StandardCharsets.UTF_8);
                fileInputStream.close();
            } finally {
            }
        } catch (IOException e2) {
            LogUtil.e("H5PRO_FileOperateApi", "getContentFromBase64File: ioException -> " + e2.getMessage());
            str = "";
        }
        d(0, 0, j, str);
    }

    private String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace(".", "_").toUpperCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File b(String str, String str2) {
        if (!TextUtils.equals(str2, FileType.PATH.type)) {
            if (!TextUtils.equals(str2, FileType.BASE64.type)) {
                LogUtil.w("H5PRO_FileOperateApi", "fileFiltering: Unknown type -> " + str2);
                return null;
            }
            str = a(str);
        }
        File[] listFiles = FileDownloadManager.exists(this.f2434a) ? new File(this.f2434a).listFiles() : null;
        if (listFiles == null || listFiles.length <= 0) {
            return null;
        }
        for (File file : listFiles) {
            if (TextUtils.equals(file.getName(), str)) {
                return file;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(File file, String str, long j) {
        String str2;
        LogUtil.i("H5PRO_FileOperateApi", "encodeSourceFileToBase64File");
        if (file == null || !file.exists()) {
            str2 = "The source file does not exist";
        } else {
            if (GeneralUtil.isSafePath(str)) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        Base64OutputStream base64OutputStream = new Base64OutputStream(new FileOutputStream(new File(this.f2434a, a(str))), 2);
                        try {
                            byte[] bArr = new byte[2097152];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                base64OutputStream.write(bArr, 0, read);
                                base64OutputStream.flush();
                            }
                            if (!file.delete()) {
                                LogUtil.w("H5PRO_FileOperateApi", "Failed to delete the source file");
                            }
                            d(1, 0, j, "The encoding file is saved successfully");
                            base64OutputStream.close();
                            fileInputStream.close();
                            return;
                        } finally {
                        }
                    } finally {
                    }
                } catch (IOException e2) {
                    LogUtil.e("H5PRO_FileOperateApi", "saveFileToBase64: ioException -> " + e2.getMessage());
                    d(-1, -1, j, "Failed to convert the source file to Base64");
                    return;
                }
            }
            str2 = "Invalid source file name";
        }
        d(-1, -1, j, str2);
    }

    private void b(final String str, final String str2, final String str3, final long j) {
        if (e.contains(str)) {
            LogUtil.d("H5PRO_FileOperateApi", "downloading -> " + str);
            d(-1, 2, j, String.format(Locale.ENGLISH, "Downloading( %s )", str));
            return;
        }
        if (FileDownloadManager.mkdirs(this.f2434a)) {
            e.add(str);
            final double[] dArr = {0.0d};
            final String format = String.format(Locale.ENGLISH, "_%s", str2);
            FileDownloadManager.download(str, this.f2434a, format, new FileDownloadManager.DownloadCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.storage.FileOperateApi.1
                @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                public void onSuccess(File file) {
                    if (file != null && file.exists()) {
                        File file2 = new File(FileOperateApi.this.f2434a, str2);
                        if (!file.renameTo(file2)) {
                            onFailure(-1, String.format(Locale.ENGLISH, "Rename failed(%s)", format));
                            return;
                        }
                        if (TextUtils.equals(str3, FileType.PATH.type)) {
                            FileOperateApi fileOperateApi = FileOperateApi.this;
                            fileOperateApi.d(1, 0, j, fileOperateApi.e(str2));
                        } else if (TextUtils.equals(str3, FileType.BASE64.type)) {
                            FileOperateApi.this.a(file2, str2, j);
                        } else {
                            FileOperateApi.this.d(1, 0, j, "File downloaded successfully");
                        }
                        FileOperateApi.e.remove(str);
                        return;
                    }
                    FileOperateApi.this.d(-1, -1, j, "Failed to save the file.");
                    FileOperateApi.e.remove(str);
                }

                @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                public void onProgress(long j2, long j3) {
                    super.onProgress(j2, j3);
                    double doubleValue = new BigDecimal(j3 / j2).setScale(1, 1).doubleValue();
                    if (CommonUtil.isNearZero(doubleValue - dArr[0])) {
                        double[] dArr2 = dArr;
                        dArr2[0] = dArr2[0] + 0.1d;
                        FileOperateApi.this.d(0, 0, j, Double.valueOf(doubleValue));
                    }
                }

                @Override // com.huawei.health.h5pro.webkit.FileDownloadManager.DownloadCallback
                public void onFailure(int i, String str4) {
                    LogUtil.w("H5PRO_FileOperateApi", "download: onFailed -> " + i + "_" + str4);
                    FileOperateApi.this.d(-1, i, j, str4);
                    FileOperateApi.e.remove(str);
                }
            });
            return;
        }
        LogUtil.w("H5PRO_FileOperateApi", "saveFile: Failed to create the file directory -> " + this.f2434a);
        d(-1, -1, j, "Failed to create the file directory");
    }

    public enum FileType {
        BASE64("base64"),
        PATH(BleConstants.KEY_PATH);

        public String type;

        FileType(String str) {
            this.type = str;
        }
    }

    public static class MessageCallbackHandler extends Handler {
        public final WeakReference<H5ProJsCbkInvoker<Object>> d;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.d);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w("H5PRO_FileOperateApi", "handleMessage: h5ProJsCbkInvoker is null");
                return;
            }
            Object obj = message.obj;
            if (!(obj instanceof String)) {
                if ((obj instanceof Double) && message.what == 0) {
                    h5ProJsCbkInvoker.onSuccess(Double.valueOf(((Double) obj).doubleValue()), message.arg2);
                    return;
                } else {
                    h5ProJsCbkInvoker.onFailure(message.arg1, "Data type error", message.arg2);
                    return;
                }
            }
            int i = message.what;
            if (i == -1) {
                h5ProJsCbkInvoker.onFailure(message.arg1, (String) obj, message.arg2);
                return;
            }
            if (i == 0) {
                h5ProJsCbkInvoker.onSuccess((String) obj, message.arg2);
            } else {
                if (i == 1) {
                    h5ProJsCbkInvoker.onComplete(message.arg1, (String) obj, message.arg2);
                    return;
                }
                LogUtil.w("H5PRO_FileOperateApi", "what: " + message.what);
            }
        }

        public MessageCallbackHandler(H5ProInstance h5ProInstance) {
            super(Looper.getMainLooper());
            this.d = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
        }
    }

    public FileOperateApi(Context context, H5ProInstance h5ProInstance) {
        this.b = new WeakReference<>(context);
        this.f = new WeakReference<>(h5ProInstance);
        d = new MessageCallbackHandler(h5ProInstance);
    }
}
