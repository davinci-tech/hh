package com.huawei.operation.h5pro.jsmodules.device;

import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.media.MediaUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FileServiceJsApi extends JsBaseModule {
    private static final String TAG_RELEASE = "DEVMGR_FileServiceJsApi";

    @JavascriptInterface
    public void convertFileToBase64(long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "convertFileToBase64 JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "convertFileToBase64 jsonObject is null");
            onFailureCallback(j, "jsonObject is null", -1);
            return;
        }
        String optString = jSONObject.optString("filePath");
        if (TextUtils.isEmpty(optString)) {
            ReleaseLogUtil.d(TAG_RELEASE, "convertFileToBase64 filePath is empty");
            onFailureCallback(j, "filePath is empty", -1);
            return;
        }
        String fileToBase64 = MediaUtils.fileToBase64(optString);
        if (TextUtils.isEmpty(fileToBase64)) {
            ReleaseLogUtil.d(TAG_RELEASE, "convertFileToBase64 result is empty");
            onFailureCallback(j, "result is empty", -1);
        } else {
            onSuccessCallback(j, fileToBase64);
        }
    }

    @JavascriptInterface
    public void saveBase64ToFile(long j, String str) {
        JSONObject jSONObject;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "saveBase64ToFile JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveBase64ToFile jsonObject is null");
            onFailureCallback(j, "jsonObject is null");
            return;
        }
        String c = CommonUtil.c(jSONObject.optString("filePath"));
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveBase64ToFile path error");
            onFailureCallback(j, "path error");
            return;
        }
        File file = new File(c);
        initializeDestFile(file);
        byte[] decode = Base64.decode(Pattern.compile("data:\\w+/\\w+;base64,").matcher(jSONObject.optString("fileData")).replaceFirst(""), 0);
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        try {
            fileOutputStream.write(decode);
            fileOutputStream.flush();
            IoUtils.e(fileOutputStream);
            onSuccessCallback(j, 0);
        } catch (FileNotFoundException e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            ReleaseLogUtil.c(TAG_RELEASE, "saveBase64ToFile FileNotFoundException:", ExceptionUtils.d(e));
            onFailureCallback(j, "FileNotFoundException");
            IoUtils.e(fileOutputStream2);
        } catch (IOException e5) {
            e = e5;
            fileOutputStream2 = fileOutputStream;
            ReleaseLogUtil.c(TAG_RELEASE, "saveBase64ToFile IOException:", ExceptionUtils.d(e));
            onFailureCallback(j, "IOException");
            IoUtils.e(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileOutputStream);
            throw th;
        }
    }

    private static void initializeDestFile(File file) {
        try {
            if (file.exists()) {
                ReleaseLogUtil.e(TAG_RELEASE, "initializeDestFile deleteDestFile isDeleteFile :", Boolean.valueOf(FileUtils.d(file)));
            }
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                ReleaseLogUtil.e(TAG_RELEASE, "initializeDestFile isMkdirFile :", Boolean.valueOf(parentFile.mkdirs()));
            }
            if (file.isFile()) {
                return;
            }
            ReleaseLogUtil.e(TAG_RELEASE, "initializeDestFile isCreateNewFile :", Boolean.valueOf(file.createNewFile()));
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "initializeDestFile IOException :", ExceptionUtils.d(e));
        } catch (SecurityException e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "initializeDestFile SecurityException :", ExceptionUtils.d(e2));
        }
    }

    @JavascriptInterface
    public void isFileExistsAtPath(long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "isFileExistsAtPath JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "isFileExistsAtPath jsonObject is null");
            onFailureCallback(j, "jsonObject is null");
            return;
        }
        String c = CommonUtil.c(jSONObject.optString("filePath"));
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "isFileExistsAtPath path error");
            onFailureCallback(j, "path error");
        } else {
            boolean exists = new File(c).exists();
            ReleaseLogUtil.e(TAG_RELEASE, "isFileExistsAtPath isFileExist:", Boolean.valueOf(exists));
            onSuccessCallback(j, Boolean.valueOf(exists));
        }
    }

    @JavascriptInterface
    public void removeFileAtPath(long j, String str) {
        try {
            String c = CommonUtil.c(new JSONObject(str).optString("filePath"));
            if (TextUtils.isEmpty(c)) {
                ReleaseLogUtil.d(TAG_RELEASE, "removeFileAtPath path error");
                onFailureCallback(j, "path error");
                return;
            }
            File file = new File(c);
            if (!file.exists()) {
                LogUtil.a(this.TAG, "removeFileAtPath file not exist");
                onSuccessCallback(j, false);
            } else {
                boolean delete = file.delete();
                LogUtil.a(this.TAG, "removeFileAtPath result:", Boolean.valueOf(delete));
                onSuccessCallback(j, Boolean.valueOf(delete));
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "removeFileAtPath JSONException ", ExceptionUtils.d(e));
            onFailureCallback(j, "removeFileAtPath JSONException");
        }
    }

    @JavascriptInterface
    public void getFilesNameByPath(long j, String str) {
        File[] listFiles;
        try {
            String c = CommonUtil.c(new JSONObject(str).getString("filePath"));
            if (TextUtils.isEmpty(c)) {
                onFailureCallback(j, "getFilesNameByPath path is empty");
                return;
            }
            ArrayList arrayList = new ArrayList();
            File file = new File(c);
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    arrayList.add(file2.getName());
                }
            }
            onSuccessCallback(j, arrayList);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "getFilesNameByPath JSONException ", ExceptionUtils.d(e));
            onFailureCallback(j, "getFilesNameByPath JSONException");
        }
    }
}
