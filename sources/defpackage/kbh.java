package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload.ErrorCodeUploadApi;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload.ErrorCodeUploadInfo;
import com.huawei.hwdevicemgr.R$array;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.WhiteBoxManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kbh {
    private static kbh b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f14251a;
    private String d;
    private IBaseCommonCallback e;

    private kbh() {
    }

    public static kbh a() {
        kbh kbhVar;
        synchronized (c) {
            if (b == null) {
                b = new kbh();
            }
            kbhVar = b;
        }
        return kbhVar;
    }

    public void a(DeviceInfo deviceInfo, String str, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback == null) {
            LogUtil.c("FileUploadLogRelease", "callback is null");
            return;
        }
        this.e = iBaseCommonCallback;
        this.d = str;
        if (TextUtils.isEmpty(str)) {
            kbi.d(this.e, 1, "fileName is empty");
            return;
        }
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            kbi.d(this.e, 1, "fileName is empty");
        } else {
            a(e(deviceInfo, str, b2, ""));
        }
    }

    public void a(Context context) {
        LogUtil.d("FileUploadLogRelease", "startUploadLogWithWifi enter");
        if (context == null) {
            LogUtil.c("FileUploadLogRelease", "startUploadLogWithWifi context is null");
            return;
        }
        String str = kbi.d;
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.c("FileUploadLogRelease", "dir is not exists");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.d("FileUploadLogRelease", "uploadZip files is null.");
            return;
        }
        for (File file2 : listFiles) {
            String name = file2.getName();
            String str2 = str + "/" + file2.getName();
            LogUtil.d("FileUploadLogRelease", "targetPath: ", str2);
            if (!kbi.c(name)) {
                LogUtil.c("FileUploadLogRelease", "isUpload is false");
            } else if (jsd.c(context) && name.contains("_WearableCommercial")) {
                jgo e = e((DeviceInfo) null, name, str2, kbi.b(str2, context));
                e.b(name);
                a(e);
            }
        }
    }

    private jgo e(DeviceInfo deviceInfo, String str, String str2, String str3) {
        jgr jgrVar = new jgr();
        jgrVar.d(BaseApplication.getAppPackage());
        jgrVar.b(1);
        jgrVar.d(Integer.parseInt(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)));
        jgrVar.a(2);
        jgrVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        jgrVar.e(ThirdLoginDataStorageUtil.getTokenTypeValue());
        jgrVar.c(new Date().getTime());
        jgo jgoVar = new jgo();
        jgoVar.c(ResultCode.ERROR_TS_TIMEOUT);
        ReleaseLogUtil.e("Dfx_FileUploadLogRelease", "getEvenLogUpload appId: ", jgoVar.b());
        jgoVar.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        jgoVar.o(CommonUtil.e(BaseApplication.getContext()));
        jgoVar.g(str2);
        jgoVar.b(jgrVar);
        jgoVar.a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        jgoVar.e(CommonUtil.ai());
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            a2 = Build.MODEL;
        }
        jgoVar.i(a2);
        jgoVar.f(Build.VERSION.RELEASE);
        jgoVar.j(d(BaseApplication.getContext()));
        if (!TextUtils.isEmpty(str3)) {
            deviceInfo = jsd.f(str3);
        }
        jgoVar.h(deviceInfo != null ? knl.a(deviceInfo.getSecurityDeviceId()).toLowerCase(Locale.ENGLISH) : "0000000000000000");
        return jgoVar;
    }

    private String b(String str) {
        File file = new File(kbi.d);
        String str2 = "";
        if (!file.exists()) {
            LogUtil.c("FileUploadLogRelease", "file not exists");
            return "";
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.c("FileUploadLogRelease", "files is empty");
            return "";
        }
        int length = listFiles.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file2 = listFiles[i];
            if (file2.getName().equals(str)) {
                try {
                    str2 = file2.getCanonicalPath();
                    break;
                } catch (IOException unused) {
                    LogUtil.e("FileUploadLogRelease", "startUploadLog IOException");
                }
            } else {
                i++;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.c("FileUploadLogRelease", "target files is empty");
        }
        return str2;
    }

    private void a(jgo jgoVar) {
        LogUtil.d("FileUploadLogRelease", "getServerDomain logPath:", jgoVar.f());
        String e = e();
        if (TextUtils.isEmpty(e)) {
            return;
        }
        this.f14251a = d();
        String d = d(":443/v2/getServerDomain?appID=1003");
        LogUtil.d("FileUploadLogRelease", "getServerDomainUrl url:", d);
        String b2 = b(jgoVar);
        LogUtil.d("FileUploadLogRelease", "getServerDomainUrl serverDomainParams:", b2);
        try {
            c(jgoVar, c(d, jsa.d(FeedbackWebConstants.SERVER_DOMAIN + jgoVar.b(), b2, e), b2));
        } catch (UnsupportedEncodingException unused) {
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "getErrorCodeLogUrl UnsupportedEncodingException");
        }
    }

    private String e() {
        WhiteBoxManager d = WhiteBoxManager.d();
        return d.d(1, 44) + d.d(1, 1044) + d.d(1, 2044);
    }

    private String d() {
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R$array.CountryCodes);
        ReleaseLogUtil.e("Dfx_FileUploadLogRelease", "getCountryCode stringCountryId:", commonCountryCode);
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
        ReleaseLogUtil.e("Dfx_FileUploadLogRelease", "getCountryCode resourceCountryCode:", str);
        return str;
    }

    private String d(String str) {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainLogserviceHicloud") + str;
    }

    private String b(jgo jgoVar) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(this.f14251a).append("&shaSN=");
        stringBuffer.append(jgoVar.h());
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v15, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v14, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r5v6 */
    private JSONObject c(String str, String str2, String str3) {
        Throwable th;
        BufferedReader bufferedReader;
        Object obj;
        InputStream inputStream;
        BufferedReader bufferedReader2;
        ?? r5;
        Object obj2;
        ?? r52;
        char c2;
        LogUtil.d("FileUploadLogRelease", "processResult urlPath: ", str);
        InputStream inputStream2 = null;
        try {
            ErrorCodeUploadApi errorCodeUploadApi = (ErrorCodeUploadApi) lqi.a(b()).b(ErrorCodeUploadApi.class);
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            hashMap.put("Authorization", str2);
            Response<ResponseBody> execute = errorCodeUploadApi.errorCodeUploadPost(str, hashMap, str3).execute();
            int code = execute.getCode();
            if (!execute.isOK()) {
                LogUtil.c("FileUploadLogRelease", "processResult responseCode: ", Integer.valueOf(code));
                bufferedReader2 = null;
                r5 = 0;
            } else {
                ResponseBody body = execute.getBody();
                if (body != null && body.getInputStream() != null) {
                    InputStream inputStream3 = body.getInputStream();
                    try {
                        bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream3, "utf-8"));
                        try {
                            try {
                                StringBuffer stringBuffer = new StringBuffer(16);
                                while (true) {
                                    int read = bufferedReader2.read();
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
                                inputStream2 = bufferedReader2;
                                InputStream inputStream4 = inputStream2;
                                inputStream2 = inputStream3;
                                bufferedReader = inputStream4;
                                b(inputStream2);
                                d(bufferedReader);
                                throw th;
                            }
                        } catch (IOException unused) {
                        } catch (JSONException unused2) {
                            r52 = 0;
                        }
                        try {
                            LogUtil.d("FileUploadLogRelease", new Object[]{"processResult jsonObject:", r52});
                            inputStream2 = inputStream3;
                            r5 = r52;
                        } catch (IOException unused3) {
                            inputStream2 = r52;
                            InputStream inputStream5 = inputStream2;
                            inputStream2 = inputStream3;
                            inputStream = inputStream5;
                            try {
                                LogUtil.e("FileUploadLogRelease", "processResult IOException");
                                r5 = inputStream;
                                b(inputStream2);
                                d(bufferedReader2);
                                return r5;
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedReader = bufferedReader2;
                                b(inputStream2);
                                d(bufferedReader);
                                throw th;
                            }
                        } catch (JSONException unused4) {
                            inputStream2 = bufferedReader2;
                            obj2 = r52;
                            InputStream inputStream6 = inputStream2;
                            inputStream2 = inputStream3;
                            bufferedReader = inputStream6;
                            obj = obj2;
                            try {
                                LogUtil.e("FileUploadLogRelease", "processResult JSONException");
                                bufferedReader2 = bufferedReader;
                                r5 = obj;
                                b(inputStream2);
                                d(bufferedReader2);
                                return r5;
                            } catch (Throwable th4) {
                                th = th4;
                                b(inputStream2);
                                d(bufferedReader);
                                throw th;
                            }
                        }
                    } catch (IOException unused5) {
                        bufferedReader2 = null;
                    } catch (JSONException unused6) {
                        obj2 = null;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                LogUtil.c("FileUploadLogRelease", "requestUtil responseBody or responseBody.getInputStream() is null");
                b((InputStream) null);
                d((BufferedReader) null);
                return null;
            }
        } catch (IOException unused7) {
            inputStream = null;
            bufferedReader2 = null;
        } catch (JSONException unused8) {
            bufferedReader = null;
            obj = null;
        } catch (Throwable th6) {
            th = th6;
            bufferedReader = null;
        }
        b(inputStream2);
        d(bufferedReader2);
        return r5;
    }

    private HttpClient b() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(120000).readTimeout(120000).build();
    }

    private void b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.e("FileUploadLogRelease", "closeInputStream IOException");
            }
        }
    }

    private void d(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                LogUtil.e("FileUploadLogRelease", "closeBufferedReader IOException");
            }
        }
    }

    private void c(jgo jgoVar, JSONObject jSONObject) {
        try {
            if (jSONObject == null) {
                LogUtil.d("FileUploadLogRelease", "jsonObject is null.");
                kbi.d(this.e, 1, "null object");
                return;
            }
            if (jSONObject.has("resCode")) {
                int i = jSONObject.getInt("resCode");
                ReleaseLogUtil.e("Dfx_FileUploadLogRelease", "handleResult resCode:", Integer.valueOf(i));
                if (i == 0) {
                    if (jSONObject.has("reason")) {
                        LogUtil.d("FileUploadLogRelease", "result success");
                    }
                    if (jSONObject.has("accessToken")) {
                        b(jgoVar, jSONObject.getString("accessToken"));
                        return;
                    }
                    return;
                }
                if (jSONObject.has("reason")) {
                    LogUtil.d("FileUploadLogRelease", "result failed, code:", jSONObject.getString("reason"));
                    kbi.d(this.e, 1, jSONObject.getString("reason"));
                }
            }
        } catch (JSONException unused) {
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "getErrorcodLogUrl JSONException");
        }
    }

    private void b(jgo jgoVar, String str) {
        FileInputStream fileInputStream;
        File file;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                file = new File(jgoVar.f());
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException unused) {
        } catch (OutOfMemoryError unused2) {
        }
        try {
            int available = fileInputStream.available();
            LogUtil.d("FileUploadLogRelease", "getErrorCodeUploadInfo size:", Integer.valueOf(available));
            byte[] bArr = new byte[available];
            LogUtil.d("FileUploadLogRelease", "getErrorCodeUploadInfo ret:", Integer.valueOf(fileInputStream.read(bArr)));
            String e = e(jgoVar, file, URLEncoder.encode(file.getName(), "UTF-8"), jsa.b(bArr));
            LogUtil.d("FileUploadLogRelease", "getErrorCodeUploadInfo uploadInfoParams:", e);
            String d = jsa.d(FeedbackWebConstants.UPLOAD_INFO + jgoVar.b(), e, str);
            String d2 = d(":443/v2/getUploadInfo?appID=1003");
            LogUtil.d("FileUploadLogRelease", "getErrorCodeUploadInfo", d2);
            JSONObject c2 = c(d2, d, e);
            if (c2 != null) {
                b(bArr, jgoVar, c2, str);
            }
            a(fileInputStream);
        } catch (IOException unused3) {
            fileInputStream2 = fileInputStream;
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "getErrorCodeUploadInfo IOException");
            a(fileInputStream2);
        } catch (OutOfMemoryError unused4) {
            fileInputStream2 = fileInputStream;
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "getErrorCodeUploadInfo OutOfMemoryError");
            a(fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            a(fileInputStream);
            throw th;
        }
    }

    private void a(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException unused) {
                ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "close IOException");
            }
        }
    }

    private String e(jgo jgoVar, File file, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(this.f14251a).append("&shaSN=");
        stringBuffer.append(jgoVar.h()).append("&logType=0&fileName=");
        stringBuffer.append(str).append("&fileHashList=[{\"fileMd5\":\"\",\"fileSha256\":\"");
        stringBuffer.append(str2).append("\",\"fileSize\":\"").append(file.length()).append("\"}]&fileSize=");
        stringBuffer.append(file.length()).append("&encryptKey=000000&patchSize=");
        stringBuffer.append(jsa.e(file)).append("&patchNum=1&patchVer=0");
        return stringBuffer.toString();
    }

    private void b(byte[] bArr, jgo jgoVar, JSONObject jSONObject, String str) {
        if (!jSONObject.has("resCode")) {
            kbi.d(this.e, 1, "wrong code");
            return;
        }
        try {
            int i = jSONObject.getInt("resCode");
            if (i != 0) {
                LogUtil.c("FileUploadLogRelease", "errorCodeUpload failed, resCode ", Integer.valueOf(i));
                kbi.d(this.e, 1, "wrong code");
            } else if (jSONObject.has("uploadInfoList")) {
                JSONArray jSONArray = jSONObject.getJSONArray("uploadInfoList");
                LogUtil.d("FileUploadLogRelease", "uploadInfoList length ", Integer.valueOf(jSONArray.length()));
                b(jSONArray, bArr, jSONObject, jgoVar, str);
            }
        } catch (JSONException unused) {
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "errorCodeUpload JSONException");
        }
    }

    private void b(JSONArray jSONArray, byte[] bArr, final JSONObject jSONObject, final jgo jgoVar, final String str) {
        if (jSONArray.length() == 0) {
            kbi.d(this.e, 1, "list is empty");
            return;
        }
        try {
            Object obj = jSONArray.get(0);
            LogUtil.d("FileUploadLogRelease", "Object Object", obj.toString());
            ErrorCodeUploadInfo errorCodeUploadInfo = (ErrorCodeUploadInfo) e(obj.toString(), ErrorCodeUploadInfo.class);
            jse.d(errorCodeUploadInfo.getUploadUrl(), errorCodeUploadInfo.getMethod(), bArr, a(new JSONObject(new Gson().toJson(errorCodeUploadInfo.getHeaders()))), new IBaseResponseCallback() { // from class: kbh.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj2) {
                    if (i == 200) {
                        kbi.d(kbh.this.e, 0, kbh.this.d);
                        try {
                            String string = jSONObject.has("fileUniqueFlag") ? jSONObject.getString("fileUniqueFlag") : null;
                            String string2 = jSONObject.has("currentTime") ? jSONObject.getString("currentTime") : null;
                            ReleaseLogUtil.e("Dfx_FileUploadLogRelease", "uploadSuccess result ok");
                            kbi.d(jgoVar.e());
                            kbh.this.b(jgoVar, string, string2, str);
                            kbh.this.c(jgoVar);
                        } catch (JSONException unused) {
                            LogUtil.e("FileUploadLogRelease", "onResponse JSONException");
                            kbi.d(kbh.this.e, 1, TrackConstants$Events.EXCEPTION);
                        }
                    }
                }
            });
        } catch (NullPointerException unused) {
            LogUtil.e("FileUploadLogRelease", "setJson NullPointerException");
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
        } catch (JSONException e) {
            LogUtil.e("FileUploadLogRelease", "setJson JSONException", e.getMessage());
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
        }
    }

    private <T> T e(String str, Class<T> cls) {
        LogUtil.d("FileUploadLogRelease", "jsonStr:", str);
        if (TextUtils.isEmpty(str)) {
            str = "{}";
        }
        LogUtil.d("FileUploadLogRelease", "getModelFromJson:111111");
        return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
    }

    private Map<String, String> a(JSONObject jSONObject) {
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
        } catch (JSONException unused) {
            LogUtil.d("FileUploadLogRelease", " putHashMap JSONException");
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jgo jgoVar, String str, String str2, String str3) {
        LogUtil.d("FileUploadLogRelease", "getUploadSuccess filePath:", jgoVar.f());
        try {
            String a2 = a(jgoVar, str, str2);
            LogUtil.d("FileUploadLogRelease", "getUploadSuccess uploadSuccessParams:", a2);
            String d = jsa.d(FeedbackWebConstants.NOTIFY_UPLOAD_SUCC + jgoVar.b(), a2, str3);
            String d2 = d(":443/v2/notifyUploadSucc?appID=1003");
            LogUtil.d("FileUploadLogRelease", "getUploadSuccess successUrl:", d2);
            if (c(d2, d, a2) == null) {
                LogUtil.c("FileUploadLogRelease", "jsonObject is null");
            }
        } catch (UnsupportedEncodingException unused) {
            kbi.d(this.e, 1, TrackConstants$Events.EXCEPTION);
            ReleaseLogUtil.c("Dfx_FileUploadLogRelease", "getUploadSuccess UnsupportedEncodingException");
        }
    }

    private String a(jgo jgoVar, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer("model=");
        stringBuffer.append(jgoVar.d()).append("&romVersion=");
        stringBuffer.append(jgoVar.g()).append("&emuiVersion=");
        stringBuffer.append(jgoVar.c()).append("&osVersion=");
        stringBuffer.append(jgoVar.a()).append("&countryCode=");
        stringBuffer.append(this.f14251a).append("&fileUniqueFlag=");
        stringBuffer.append(str).append("&uploadTime=");
        stringBuffer.append(str2).append("&shaSN=");
        stringBuffer.append(jgoVar.h());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(jgo jgoVar) {
        String f = jgoVar.f();
        LogUtil.d("FileUploadLogRelease", "encryfilePath", f);
        if (TextUtils.isEmpty(f)) {
            return;
        }
        File file = new File(f);
        try {
            LogUtil.d("FileUploadLogRelease", "encryfilePath", file.getCanonicalPath());
        } catch (IOException e) {
            LogUtil.d("FileUploadLogRelease", e.getMessage());
        }
        if (file.exists() && file.delete()) {
            LogUtil.d("FileUploadLogRelease", "File deleted successfully.");
        } else {
            LogUtil.d("FileUploadLogRelease", "The file does not exist or is incorrect. Failed to delete the file.");
        }
    }

    private String a(String str) {
        if (str == null) {
            return "";
        }
        String str2 = "Talos-";
        if (!str.contains("Talos-")) {
            str2 = "Fortuna-";
            if (!str.contains("Fortuna-")) {
                str2 = "Latona";
                if (!str.contains("Latona")) {
                    str2 = "ELA";
                    if (!str.contains("ELA")) {
                        str2 = "Crius";
                        if (!str.contains("Crius")) {
                            str2 = "TerraB09";
                            if (!str.contains("TerraB09")) {
                                str2 = "Terra";
                                if (!str.contains("Terra")) {
                                    str2 = "Diana";
                                    if (!str.contains("Diana")) {
                                        return "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return str2;
    }

    private String d(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("FileUploadLogRelease", "getAppVersionName() NameNotFoundException");
            str = "2.0";
        }
        LogUtil.b("FileUploadLogRelease", "getAppVersionName() return=", str);
        return str;
    }
}
