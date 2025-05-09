package com.huawei.ui.main.stories.nps.https;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.ParamConstants;
import health.compact.a.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class HttpUtils {
    private static final int BYTE_LEN = 1024;
    public static final int CONNECT_TIMEOUT = 5000;
    private static final String HTTP = "http";
    public static final int HTTP_ERR = -1;
    public static final int HTTP_OK = 0;
    private static final int INIT_BYTE_SIZE = 512;
    private static final int MAX_FILE_SIZE = 10485760;
    public static final int READ_TIMEOUT = 5000;
    private static final int RESULT_DEFAULT_CODE = -1;
    private static final String TAG = "PLGOPER_HttpUtils";

    private HttpUtils() {
    }

    public static int postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HttpResCallback httpResCallback) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isHttpProtocol(context, str)) {
            return Http.postReq(context, str, hashMap, hashMap2, httpResCallback);
        }
        return Https.postReq(context, str, hashMap, hashMap2, httpResCallback);
    }

    public static String postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (isHttpProtocol(context, str)) {
            return Http.postReq(context, str, hashMap, hashMap2);
        }
        return Https.postReq(context, str, hashMap, hashMap2);
    }

    public static int download(Context context, String str, String str2, HttpResCallback httpResCallback) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isHttpProtocol(context, str)) {
            return Http.download(context, str, str2, httpResCallback);
        }
        return Https.download(context, str, str2, httpResCallback);
    }

    public static int download(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isHttpProtocol(context, str)) {
            return Http.download(context, str, str2);
        }
        return Https.download(context, str, str2);
    }

    private static boolean isHttpProtocol(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int max = Math.max(str.indexOf(":"), 0);
        return (str.length() >= max ? str.substring(0, max) : "").equalsIgnoreCase("http");
    }

    public static String getBody(HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        if (hashMap == null || hashMap.size() <= 0 || (r4 = hashMap.entrySet().iterator()) == null) {
            return "";
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            stringBuffer.append("&").append((Object) entry.getKey()).append("=").append((Object) entry.getValue());
        }
        return stringBuffer.toString().trim().substring(1);
    }

    public static void setHeader(Context context, HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        if (httpURLConnection == null || hashMap == null || hashMap.size() <= 0 || (r1 = hashMap.entrySet().iterator()) == null) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                httpURLConnection.setRequestProperty(String.valueOf(key), String.valueOf(value));
            }
        }
    }

    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        byte[] bArr2 = new byte[1024];
        int i = 0;
        do {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i += read;
            } catch (IOException unused) {
                LogUtil.e(TAG, "readInputStream IOException!");
                return bArr2;
            }
        } while (i <= MAX_FILE_SIZE);
        byteArrayOutputStream.flush();
        bArr2 = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return bArr2;
    }

    public static Bitmap readInputStream(Context context, InputStream inputStream) {
        try {
            byte[] readInputStream = readInputStream(inputStream);
            return BitmapFactory.decodeByteArray(readInputStream, 0, readInputStream.length, null);
        } catch (IllegalArgumentException unused) {
            LogUtil.e(TAG, "transformByteToBmp IllegalArgumentException err:");
            return null;
        } finally {
            closeStream(inputStream, null);
        }
    }

    public static boolean saveFile(InputStream inputStream, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        boolean z;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c(TAG, "saveFile isEmpty.");
            return false;
        }
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf < 0) {
            lastIndexOf = 0;
        }
        String fileDir = getFileDir(str, lastIndexOf);
        if (!createFileDir(fileDir)) {
            return false;
        }
        File file = new File(str);
        File file2 = new File(fileDir + "/_" + getFileName(str, lastIndexOf));
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            boolean renameTo = file2.renameTo(file);
            if (renameTo) {
                z = true;
            } else {
                if (file2.exists()) {
                    boolean delete = file2.delete();
                    Object[] objArr = new Object[2];
                    objArr[0] = "tmp isDeleteRet:";
                    objArr[1] = delete ? "sucess" : ParamConstants.CallbackMethod.ON_FAIL;
                    LogUtil.b(TAG, objArr);
                }
                z = false;
            }
            LogUtil.d(TAG, "isRenameOk:", Boolean.valueOf(renameTo));
            closeStream(inputStream, fileOutputStream);
            return z;
        } catch (FileNotFoundException unused3) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.e(TAG, "saveFile exception = FileNotFoundException!");
            closeStream(inputStream, fileOutputStream2);
            return false;
        } catch (IOException unused4) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.e(TAG, "saveFile exception = IOException!");
            closeStream(inputStream, fileOutputStream2);
            return false;
        } catch (Throwable th3) {
            th = th3;
            closeStream(inputStream, fileOutputStream);
            throw th;
        }
    }

    public static void closeStream(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.e(TAG, "streamClose IOException!");
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused2) {
                LogUtil.e(TAG, "streamClose IOException2!");
            }
        }
    }

    private static String getFileDir(String str, int i) {
        return (TextUtils.isEmpty(str) || str.length() < i) ? "" : str.substring(0, i);
    }

    private static boolean createFileDir(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            Object[] objArr = new Object[2];
            objArr[0] = "isMkdirRet:";
            objArr[1] = mkdirs ? "sucess" : ParamConstants.CallbackMethod.ON_FAIL;
            LogUtil.b(TAG, objArr);
            if (!mkdirs) {
                return false;
            }
        }
        return true;
    }

    private static String getFileName(String str, int i) {
        int max;
        return (!TextUtils.isEmpty(str) && str.length() > (max = Math.max(i + 1, 0))) ? str.substring(max) : "";
    }
}
