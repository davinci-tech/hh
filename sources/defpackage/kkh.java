package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload.ErrorCodeUploadApi;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload.ErrorCodeUploadInfo;
import com.huawei.hwdevicemgr.R$array;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.HwEncryptUtil;
import health.compact.a.WhiteBoxManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class kkh {
    private static String c;

    public static void d(jgo jgoVar) {
        String f = jgoVar.f();
        LogUtil.a("LogUpload Service errorcode", "encryfilePath", f);
        if (TextUtils.isEmpty(f)) {
            return;
        }
        File file = new File(f);
        try {
            LogUtil.a("LogUpload Service errorcode", "encryfilePath", file.getCanonicalPath());
        } catch (IOException e) {
            LogUtil.a("LogUpload Service errorcode", e.getMessage());
        }
        if (file.exists() && file.delete()) {
            LogUtil.a("LogUpload Service errorcode", "File deleted successfully.");
        } else {
            LogUtil.a("LogUpload Service errorcode", "The file does not exist or is incorrect. Failed to delete the file.");
        }
    }

    private static String d(boolean z) {
        WhiteBoxManager d = WhiteBoxManager.d();
        StringBuilder sb = new StringBuilder();
        int i = z ? 41 : 34;
        int i2 = z ? 1041 : 1034;
        int i3 = z ? 2041 : 2034;
        sb.append(d.d(1, i));
        sb.append(d.d(1, i2));
        sb.append(d.d(1, i3));
        return sb.toString();
    }

    public static void c(final jgo jgoVar) {
        LogUtil.a("LogUpload Service errorcode", "getErrorCodeLogUrl logPath:", jgoVar.f());
        String d = d(jgoVar.i());
        if (TextUtils.isEmpty(d)) {
            return;
        }
        c = b();
        final String b = b(jgoVar);
        LogUtil.a("LogUpload Service errorcode", "getServerDomainUrl url:", b);
        final String e = e(jgoVar);
        try {
            final String d2 = jsa.d(FeedbackWebConstants.SERVER_DOMAIN + jgoVar.b(), e, d);
            ThreadPoolManager.d().c("Dfx_LogUpload Service errorcode", new Runnable() { // from class: kkd
                @Override // java.lang.Runnable
                public final void run() {
                    kkh.e(jgoVar, kkh.d(b, d2, e));
                }
            });
        } catch (UnsupportedEncodingException unused) {
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "getErrorCodeLogUrl UnsupportedEncodingException");
        }
    }

    private static String b() {
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R$array.CountryCodes);
        ReleaseLogUtil.e("Dfx_LogUpload Service errorcode", "getCountryCode stringCountryId:", commonCountryCode);
        String str = "";
        if (stringArray == null) {
            return "";
        }
        int length = stringArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String[] split = stringArray[i].split(",");
            if (split.length > 1 && split[1].trim().equals(commonCountryCode.trim())) {
                str = split[0];
                break;
            }
            i++;
        }
        ReleaseLogUtil.e("Dfx_LogUpload Service errorcode", "getCountryCode resourceCountryCode:", str);
        return str;
    }

    private static String b(jgo jgoVar) {
        String str;
        String str2;
        boolean i = jgoVar.i();
        if (i) {
            str = "/v2/getServerDomain?appID=2019";
            str2 = ":443/v2/getServerDomain?appID=2019";
        } else {
            str = "/v2/getServerDomain?appID=1019";
            str2 = ":443/v2/getServerDomain?appID=1019";
        }
        return b(i, str, str2);
    }

    private static String e(jgo jgoVar) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(c).append("&shaSN=");
        stringBuffer.append(jgoVar.h());
        return stringBuffer.toString();
    }

    private static String b(boolean z, String str, String str2) {
        if (CompileParameterUtil.a("IS_TEST_VERSION")) {
            return GRSManager.a(BaseApplication.getContext()).getUrl("biOperation") + str;
        }
        if (z) {
            return jah.c().e("domain_log_service_hicloud_honor") + str2;
        }
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainLogserviceHicloud") + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(jgo jgoVar, JSONObject jSONObject) {
        try {
            if (jSONObject == null) {
                LogUtil.a("LogUpload Service errorcode", "jsonObject is null.");
                return;
            }
            if (jSONObject.has("resCode")) {
                int i = jSONObject.getInt("resCode");
                ReleaseLogUtil.e("Dfx_LogUpload Service errorcode", "handleResult resCode:", Integer.valueOf(i));
                if (i == 0) {
                    if (jSONObject.has("reason")) {
                        LogUtil.a("LogUpload Service errorcode", "result success");
                    }
                    if (jSONObject.has("accessToken")) {
                        b(jgoVar, jSONObject.getString("accessToken"));
                        return;
                    }
                    return;
                }
                if (jSONObject.has("reason")) {
                    LogUtil.a("LogUpload Service errorcode", "result failed, code:", jSONObject.getString("reason"));
                }
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "getErrorcodLogUrl JSONException");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void b(jgo jgoVar, String str) {
        FileInputStream fileInputStream;
        File file;
        FileInputStream fileInputStream2 = null;
        r5 = null;
        byte[] bArr = null;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        try {
            try {
                file = new File(jgoVar.f());
                LogUtil.a("LogUpload Service errorcode", "file name:", file.getPath());
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (IOException unused) {
        } catch (OutOfMemoryError unused2) {
        }
        try {
            byte[] bArr2 = new byte[fileInputStream.available()];
            if (fileInputStream.read(bArr2) != -1) {
                try {
                    byte[] d = HwEncryptUtil.c(BaseApplication.getContext()).d(2, new String(bArr2, "utf-8"));
                    int a2 = jgt.a(d);
                    bArr = new byte[a2];
                    System.arraycopy(d, 4, bArr, 0, a2);
                } catch (Exception unused3) {
                    ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "decryptByteData Exception");
                }
            }
        } catch (IOException unused4) {
            fileInputStream3 = fileInputStream;
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "getErrorCodeUploadInfo IOException");
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    fileInputStream2 = fileInputStream3;
                } catch (IOException unused5) {
                    ReleaseLogUtil.c("LogUpload Service errorcode", "fis.close IOException");
                    fileInputStream2 = fileInputStream3;
                }
            }
            return;
        } catch (OutOfMemoryError unused6) {
            fileInputStream4 = fileInputStream;
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "getErrorCodeUploadInfo OutOfMemoryError");
            fileInputStream2 = fileInputStream4;
            if (fileInputStream4 != null) {
                try {
                    fileInputStream4.close();
                    fileInputStream2 = fileInputStream4;
                } catch (IOException unused7) {
                    ReleaseLogUtil.c("LogUpload Service errorcode", "fis.close IOException");
                    fileInputStream2 = fileInputStream4;
                }
            }
            return;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                    ReleaseLogUtil.c("LogUpload Service errorcode", "fis.close IOException");
                }
            }
            throw th;
        }
        if (bArr != null && bArr.length > 0) {
            String c2 = c(jgoVar, file, bArr, URLEncoder.encode(file.getName(), "UTF-8"));
            String d2 = jsa.d(FeedbackWebConstants.UPLOAD_INFO + jgoVar.b(), c2, str);
            byte[] bArr3 = (byte[]) bArr.clone();
            String a3 = a(jgoVar);
            LogUtil.a("LogUpload Service errorcode", "getUploadInfoUrl", a3);
            JSONObject d3 = d(a3, d2, c2);
            if (d3 != null) {
                d(bArr3, jgoVar, d3, str);
            }
            try {
                fileInputStream.close();
                fileInputStream2 = bArr3;
            } catch (IOException unused9) {
                ReleaseLogUtil.c("LogUpload Service errorcode", "fis.close IOException");
                fileInputStream2 = bArr3;
            }
            return;
        }
        try {
            fileInputStream.close();
        } catch (IOException unused10) {
            ReleaseLogUtil.c("LogUpload Service errorcode", "fis.close IOException");
        }
    }

    private static String a(jgo jgoVar) {
        String str;
        String str2;
        boolean i = jgoVar.i();
        if (i) {
            str = "/v2/getUploadInfo?appID=2019";
            str2 = ":443/v2/getUploadInfo?appID=2019";
        } else {
            str = "/v2/getUploadInfo?appID=1019";
            str2 = ":443/v2/getUploadInfo?appID=1019";
        }
        return b(i, str, str2);
    }

    private static String c(jgo jgoVar, File file, byte[] bArr, String str) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(c).append("&shaSN=");
        stringBuffer.append(jgoVar.h()).append("&logType=0&fileName=");
        stringBuffer.append(str).append("&fileHashList=[{\"fileMd5\":\"\",\"fileSha256\":\"");
        stringBuffer.append(jsa.b(bArr)).append("\",\"fileSize\":\"").append(bArr.length).append("\"}]&fileSize=");
        stringBuffer.append(bArr.length).append("&encryptKey=000000&patchSize=");
        stringBuffer.append(jsa.e(file)).append("&patchNum=1&patchVer=0");
        return stringBuffer.toString();
    }

    private static void d(byte[] bArr, jgo jgoVar, JSONObject jSONObject, String str) {
        if (jSONObject.has("resCode")) {
            try {
                if (jSONObject.getInt("resCode") == 0) {
                    if (jSONObject.has("reason")) {
                        LogUtil.a("LogUpload Service errorcode", "errorCodeUpload success, code", jSONObject.getString("reason"));
                    }
                    if (jSONObject.has("uploadInfoList")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("uploadInfoList");
                        LogUtil.a("LogUpload Service errorcode", "uploadInfoList length ", Integer.valueOf(jSONArray.length()));
                        c(jSONArray, bArr, jSONObject, jgoVar, str);
                        return;
                    }
                    return;
                }
                if (jSONObject.has("reason")) {
                    LogUtil.a("LogUpload Service errorcode", "errorcodeUpload failed, code ", jSONObject.getString("reason"));
                }
            } catch (JSONException unused) {
                ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", " errorcodeUpload JSONException");
            }
        }
    }

    private static <T> T c(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            str = "{}";
        }
        return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
    }

    private static void c(JSONArray jSONArray, byte[] bArr, final JSONObject jSONObject, final jgo jgoVar, final String str) {
        if (jSONArray.length() > 0) {
            try {
                Object obj = jSONArray.get(0);
                LogUtil.a("LogUpload Service errorcode", "Object Object", obj.toString());
                ErrorCodeUploadInfo errorCodeUploadInfo = (ErrorCodeUploadInfo) c(obj.toString(), ErrorCodeUploadInfo.class);
                jse.d(errorCodeUploadInfo.getUploadUrl(), errorCodeUploadInfo.getMethod(), bArr, d(new JSONObject(new Gson().toJson(errorCodeUploadInfo.getHeaders()))), new IBaseResponseCallback() { // from class: kkh.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj2) {
                        ReleaseLogUtil.e("Dfx_LogUpload Service errorcode", "response code:", Integer.valueOf(i), ", isNewHonorDeviceLog:", Boolean.valueOf(jgo.this.i()));
                        if (i == 200) {
                            try {
                                kkh.d(jgo.this, jSONObject.has("fileUniqueFlag") ? jSONObject.getString("fileUniqueFlag") : null, jSONObject.has("currentTime") ? jSONObject.getString("currentTime") : null, str);
                                kkh.d(jgo.this);
                            } catch (JSONException unused) {
                                LogUtil.b("LogUpload Service errorcode", "onResponse JSONException");
                            }
                        }
                    }
                });
            } catch (NullPointerException unused) {
                LogUtil.b("LogUpload Service errorcode", "setJson NullPointerException");
            } catch (JSONException unused2) {
                LogUtil.b("LogUpload Service errorcode", "setJson JSONException");
            }
        }
    }

    private static Map<String, String> d(JSONObject jSONObject) {
        HashMap hashMap = new HashMap(16);
        try {
            if (jSONObject.has("Authorization")) {
                hashMap.put("Authorization", jSONObject.getString("Authorization"));
            }
            if (jSONObject.has("x-amz-content-sha256")) {
                hashMap.put("x-amz-content-sha256", jSONObject.getString("x-amz-content-sha256"));
            }
            if (jSONObject.has("x-amz-date")) {
                hashMap.put("x-amz-date", jSONObject.getString("x-amz-date"));
            }
            if (jSONObject.has("Host")) {
                hashMap.put("Host", jSONObject.getString("Host"));
            }
            if (jSONObject.has("Content-Length")) {
                hashMap.put("Content-Length", jSONObject.getString("Content-Length"));
            }
            if (jSONObject.has(FeedbackWebConstants.CONTENT_MD5)) {
                hashMap.put(FeedbackWebConstants.CONTENT_MD5, jSONObject.getString(FeedbackWebConstants.CONTENT_MD5));
            }
            if (jSONObject.has("Content-Type")) {
                hashMap.put("Content-Type", jSONObject.getString("Content-Type"));
            }
            if (jSONObject.has(FeedbackWebConstants.USER_AGENT)) {
                hashMap.put(FeedbackWebConstants.USER_AGENT, jSONObject.getString(FeedbackWebConstants.USER_AGENT));
            }
        } catch (JSONException e) {
            LogUtil.a("LogUpload Service errorcode", " putHashMap JSONException ", e.getMessage());
        }
        return hashMap;
    }

    public static void d(jgo jgoVar, String str, String str2, String str3) {
        LogUtil.a("LogUpload Service errorcode", "uploadSuccess filePath:", jgoVar.f());
        ReleaseLogUtil.e("Dfx_LogUpload Service errorcode", "ErrorCodeUploadRequest uploadSuccess().");
        try {
            String d = d(jgoVar, str, str2);
            String d2 = jsa.d(FeedbackWebConstants.NOTIFY_UPLOAD_SUCC + jgoVar.b(), d, str3);
            String j = j(jgoVar);
            LogUtil.b("LogUpload Service errorcode", "getUploadSuccessUrl:", j);
            JSONObject d3 = d(j, d2, d);
            if (d3 == null) {
                LogUtil.h("LogUpload Service errorcode", "jsonObject is null");
            } else if (d3.has("reason")) {
                LogUtil.a("LogUpload Service errorcode", "uploadSuccess reason", d3.getString("reason"));
            }
        } catch (UnsupportedEncodingException unused) {
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "uploadSuccess UnsupportedEncodingException");
        } catch (JSONException unused2) {
            ReleaseLogUtil.c("Dfx_LogUpload Service errorcode", "uploadSuccess JSONException");
        }
    }

    private static String j(jgo jgoVar) {
        String str;
        String str2;
        boolean i = jgoVar.i();
        if (i) {
            str = "/v2/notifyUploadSucc?appID=2019";
            str2 = ":443/v2/notifyUploadSucc?appID=2019";
        } else {
            str = "/v2/notifyUploadSucc?appID=1019";
            str2 = ":443/v2/notifyUploadSucc?appID=1019";
        }
        return b(i, str, str2);
    }

    private static String d(jgo jgoVar, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(c).append("&fileUniqueFlag=");
        stringBuffer.append(str).append("&uploadTime=");
        stringBuffer.append(str2).append("&shaSN=");
        stringBuffer.append(jgoVar.h());
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v14, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static JSONObject d(String str, String str2, String str3) {
        Throwable th;
        ?? r9;
        InputStream inputStream;
        Object obj;
        InputStream inputStream2;
        Object obj2;
        ?? r10;
        ?? r5;
        Object obj3;
        ?? r102;
        ?? r52;
        char c2;
        LogUtil.a("LogUpload Service errorcode", "requestUtil urlPath: ", str);
        InputStream inputStream3 = null;
        try {
            ErrorCodeUploadApi errorCodeUploadApi = (ErrorCodeUploadApi) lqi.a(e()).b(ErrorCodeUploadApi.class);
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            hashMap.put("Authorization", str2);
            Response<ResponseBody> execute = errorCodeUploadApi.errorCodeUploadPost(str, hashMap, str3).execute();
            int code = execute.getCode();
            if (!execute.isOK()) {
                LogUtil.a("LogUpload Service errorcode", "requestUtil getResponseCode(): ", Integer.valueOf(code));
                r10 = 0;
                r5 = 0;
            } else {
                ResponseBody body = execute.getBody();
                if (body != null && body.getInputStream() != null) {
                    InputStream inputStream4 = body.getInputStream();
                    try {
                        r102 = new BufferedReader(new InputStreamReader(inputStream4, "utf-8"));
                        try {
                            try {
                                StringBuffer stringBuffer = new StringBuffer(1024);
                                while (true) {
                                    int read = r102.read();
                                    if (read == -1 || (c2 = (char) read) == '\n') {
                                        break;
                                    }
                                    if (stringBuffer.length() >= 104857600) {
                                        throw new IllegalStateException("input too long");
                                    }
                                    stringBuffer.append(c2);
                                }
                                r52 = new JSONObject(stringBuffer.toString());
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream3 = r102;
                                InputStream inputStream5 = inputStream3;
                                inputStream3 = inputStream4;
                                r9 = inputStream5;
                                d(inputStream3);
                                a((BufferedReader) r9);
                                throw th;
                            }
                        } catch (IOException unused) {
                        } catch (JSONException unused2) {
                            r52 = 0;
                        }
                        try {
                            LogUtil.a("LogUpload Service errorcode", (Object[]) new Object[]{"requestUtil jsonObject:", r52});
                            inputStream3 = inputStream4;
                            r5 = r52;
                            r10 = r102;
                        } catch (IOException unused3) {
                            inputStream3 = r52;
                            InputStream inputStream6 = inputStream3;
                            inputStream3 = inputStream4;
                            inputStream2 = inputStream6;
                            obj2 = r102;
                            try {
                                LogUtil.b("LogUpload Service errorcode", "requestUtil IOException");
                                r5 = inputStream2;
                                r10 = obj2;
                                d(inputStream3);
                                a((BufferedReader) r10);
                                return r5;
                            } catch (Throwable th3) {
                                th = th3;
                                r9 = obj2;
                                d(inputStream3);
                                a((BufferedReader) r9);
                                throw th;
                            }
                        } catch (JSONException unused4) {
                            inputStream3 = r102;
                            obj3 = r52;
                            InputStream inputStream7 = inputStream3;
                            inputStream3 = inputStream4;
                            inputStream = inputStream7;
                            obj = obj3;
                            try {
                                LogUtil.b("LogUpload Service errorcode", "requestUtil JSONException");
                                r10 = inputStream;
                                r5 = obj;
                                d(inputStream3);
                                a((BufferedReader) r10);
                                return r5;
                            } catch (Throwable th4) {
                                th = th4;
                                r9 = inputStream;
                                d(inputStream3);
                                a((BufferedReader) r9);
                                throw th;
                            }
                        }
                    } catch (IOException unused5) {
                        r102 = 0;
                    } catch (JSONException unused6) {
                        obj3 = null;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                LogUtil.h("LogUpload Service errorcode", "requestUtil responseBody or responseBody.getInputStream() is null");
                d((InputStream) null);
                a((BufferedReader) null);
                return null;
            }
        } catch (IOException unused7) {
            inputStream2 = null;
            obj2 = null;
        } catch (JSONException unused8) {
            inputStream = null;
            obj = null;
        } catch (Throwable th6) {
            th = th6;
            r9 = 0;
        }
        d(inputStream3);
        a((BufferedReader) r10);
        return r5;
    }

    private static HttpClient e() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(120000).readTimeout(120000).build();
    }

    private static void a(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                LogUtil.b("LogUpload Service errorcode", "requestUtil br IOException ", e.getMessage());
            }
        }
    }

    private static void d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.b("LogUpload Service errorcode", "requestUtil br IOException ", e.getMessage());
            }
        }
    }
}
