package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.network.embedded.b6;
import com.huawei.hms.network.embedded.r1;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.CommonApi;
import com.huawei.pluginmgr.filedownload.FilePullerApi;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.tencent.open.SocialOperation;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class msn {
    private static String b;
    private static msn c;
    private DownloadPluginUrl j;
    private static final Object e = new Object();
    private static final String d = "and_health_" + EnvironmentUtils.a();
    private ConcurrentHashMap<msq, mso> g = new ConcurrentHashMap<>(16);
    private ThreadPoolManager h = ThreadPoolManager.e(5, 5, "FilePuller");

    /* renamed from: a, reason: collision with root package name */
    private Context f15151a = BaseApplication.getContext();

    public static msn c() {
        msn msnVar;
        synchronized (e) {
            if (c == null) {
                c = new msn();
            }
            msnVar = c;
        }
        return msnVar;
    }

    public static String a() {
        return b;
    }

    public static void c(String str) {
        b = str;
    }

    public void e(DownloadPluginUrl downloadPluginUrl) {
        LogUtil.a("EzPlugin_FilePuller", "enter setDownloadPluginUrl.");
        this.j = downloadPluginUrl;
    }

    public DownloadPluginUrl d() {
        LogUtil.a("EzPlugin_FilePuller", "getDownloadPluginUrl.");
        return this.j;
    }

    public static boolean c(String str, String str2) {
        if (str != null) {
            String b2 = SecurityUtils.b(new File(str2), null, true);
            LogUtil.a("EzPlugin_FilePuller", "verifyHashCode digest is =", str, " hashcode is =", b2);
            if (!str.equalsIgnoreCase(b2)) {
                LogUtil.a("EzPlugin_FilePuller", "verifyHashCode hashcode checkout failure");
                e(new File(str2), "verifyHashCode deleteFile is = ");
                return false;
            }
            LogUtil.a("EzPlugin_FilePuller", "verifyHashCode hashcode checkout success");
        }
        return true;
    }

    private static void e(File file, String str) {
        if (file.exists() && file.isFile()) {
            LogUtil.a("EzPlugin_FilePuller", str, Boolean.valueOf(file.delete()));
        }
    }

    private static String c(msq msqVar, mso msoVar) {
        String a2;
        String g = msqVar.g();
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        LogUtil.a("EzPlugin_FilePuller", "getFileUrl start: ", msqVar.j());
        String str = null;
        if (TextUtils.isEmpty(msqVar.j())) {
            LogUtil.h("EzPlugin_FilePuller", "getFileUrl http url isEmpty");
            return null;
        }
        if (d(msqVar.j())) {
            a2 = d(msqVar, msoVar);
        } else {
            a2 = a(msqVar, msoVar);
        }
        if (!TextUtils.isEmpty(a2)) {
            str = a2.trim();
            if ("Not Modified".equals(str)) {
                return str;
            }
        }
        if ("plugin_index".equals(msqVar.c())) {
            LogUtil.c("EzPlugin_FilePuller", "getFileUrl to cache, fileUrlJson is :", str);
            c(str);
        } else {
            LogUtil.a("EzPlugin_FilePuller", "getFileUrl fileUrlJson is :", str);
        }
        return str;
    }

    private static String d(msq msqVar, mso msoVar) {
        return e(msqVar, msoVar, false);
    }

    public static String e() {
        if (!CommonUtil.bv() && "1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch"))) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "app_enter_module", "app_environment_change_url");
            if (!TextUtils.isEmpty(b2)) {
                LogUtil.a("EzPlugin_FilePuller", "grayEnvironmentUrl length:", Integer.valueOf(b2.length()));
                return b2;
            }
        }
        return "";
    }

    private static String a(msq msqVar, mso msoVar) {
        return e(msqVar, msoVar, true);
    }

    private static String e(msq msqVar, mso msoVar, boolean z) {
        InputStream inputStream;
        String str;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2 = null;
        String str2 = null;
        inputStreamReader2 = null;
        InputStream inputStream2 = null;
        try {
            FilePullerApi filePullerApi = (FilePullerApi) lqi.a(i()).b(FilePullerApi.class);
            Map<String, String> b2 = b(msqVar);
            Response<ResponseBody> execute = ("GET".equals(msqVar.i()) ? filePullerApi.getFileUrlGet(msqVar.j(), b2) : filePullerApi.getFileUrlPost(msqVar.j(), b2, msqVar.l())).execute();
            int code = execute.getCode();
            if (code != 200) {
                String a2 = a(code, msqVar, z, null);
                IoUtils.e((Closeable) null);
                IoUtils.e((Closeable) null);
                return a2;
            }
            ResponseBody body = execute.getBody();
            if (body != null && body.getInputStream() != null) {
                if (!TextUtils.isEmpty(execute.getHeaders().get("ETag"))) {
                    msoVar.c(execute.getHeaders().get("ETag"));
                }
                inputStream = body.getInputStream();
                try {
                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    try {
                        try {
                            StringBuilder sb = new StringBuilder(4096);
                            char[] cArr = new char[4096];
                            while (true) {
                                int read = inputStreamReader.read(cArr, 0, 4096);
                                if (read == -1) {
                                    String sb2 = sb.toString();
                                    try {
                                        msqVar.e(code, sb2);
                                        IoUtils.e(inputStreamReader);
                                        IoUtils.e(inputStream);
                                        return sb2;
                                    } catch (IOException e2) {
                                        e = e2;
                                        str2 = sb2;
                                        String str3 = str2;
                                        inputStream2 = inputStream;
                                        str = str3;
                                        try {
                                            ReleaseLogUtil.c("PluginDevice_FilePuller", "getFileUrlContent ex=", ExceptionUtils.d(e));
                                            IoUtils.e(inputStreamReader);
                                            IoUtils.e(inputStream2);
                                            return str;
                                        } catch (Throwable th) {
                                            th = th;
                                            inputStream = inputStream2;
                                            inputStreamReader2 = inputStreamReader;
                                            IoUtils.e(inputStreamReader2);
                                            IoUtils.e(inputStream);
                                            throw th;
                                        }
                                    }
                                }
                                sb.append(cArr, 0, read);
                            }
                        } catch (IOException e3) {
                            e = e3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream;
                        inputStream = inputStream2;
                        inputStreamReader2 = inputStreamReader;
                        IoUtils.e(inputStreamReader2);
                        IoUtils.e(inputStream);
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    inputStreamReader = null;
                    inputStream2 = inputStream;
                    str = null;
                } catch (Throwable th3) {
                    th = th3;
                    IoUtils.e(inputStreamReader2);
                    IoUtils.e(inputStream);
                    throw th;
                }
            }
            LogUtil.h("EzPlugin_FilePuller", "getFileUrlContent responseBody or responseBody.getInputStream() is null");
            IoUtils.e((Closeable) null);
            IoUtils.e((Closeable) null);
            return null;
        } catch (IOException e5) {
            e = e5;
            str = null;
            inputStreamReader = null;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
        }
    }

    private static String a(int i, msq msqVar, boolean z, String str) {
        if (i == 304) {
            ReleaseLogUtil.e("PluginDevice_FilePuller", "getFileUrlJson not modified");
            msqVar.e(i, "");
            return "Not Modified";
        }
        Object[] objArr = new Object[3];
        objArr[0] = z ? "getFileURLHttps" : "getFileURLHttp";
        objArr[1] = " ConRspCode = ";
        objArr[2] = Integer.valueOf(i);
        LogUtil.h("PluginDevice_FilePuller", objArr);
        msqVar.e(i, "");
        return str;
    }

    private static HttpClient i() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        if (CloudUtils.d()) {
            LogUtil.a("EzPlugin_FilePuller", "getHttpClient oversea download device file");
            return new HttpClient.Builder().connectTimeout(20000).readTimeout(20000).build();
        }
        return new HttpClient.Builder().connectTimeout(10000).readTimeout(10000).build();
    }

    private static Map<String, String> b(msq msqVar) {
        HashMap hashMap = new HashMap();
        if (!msqVar.h()) {
            String e2 = e();
            if (!TextUtils.isEmpty(e2)) {
                hashMap.put("Device-ID", e2);
            }
        }
        hashMap.put("Content-Type", "application/json");
        if (msqVar.b()) {
            if (msqVar.h()) {
                hashMap.put("x-version", d);
            } else {
                hashMap.put(b6.a.b, "HealthApp");
            }
            hashMap.put("traceId", "afe21fcf-2ff0-4b63-a3ef-a04136517298");
        }
        if ("GET".equals(msqVar.i()) && !TextUtils.isEmpty(msqVar.d())) {
            hashMap.put(r1.b.n, msqVar.d());
        }
        return hashMap;
    }

    private static String c(String str, String str2, boolean z, mso msoVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (z && str2 != null && !jSONObject.isNull("fileList")) {
                return d(jSONObject.getJSONArray("fileList"), str2, msoVar);
            }
            if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
                return jSONObject.getString(RecommendConstants.DOWNLOAD_URL);
            }
            LogUtil.h("EzPlugin_FilePuller", "parseJsonObjectUrl jsonObject url key is null, text=", String.valueOf(jSONObject));
            return null;
        } catch (JSONException unused) {
            LogUtil.b("EzPlugin_FilePuller", "parseJsonObjectUrl parse fileUrlJson JSONException");
            return null;
        }
    }

    private static String a(String str, String str2, mso msoVar) {
        try {
            return d(new JSONArray(str), str2, msoVar);
        } catch (JSONException unused) {
            LogUtil.b("EzPlugin_FilePuller", "parseJsonArrayUrl parse fileUrlJson JSONException");
            return null;
        }
    }

    private static String d(JSONArray jSONArray, String str, mso msoVar) throws JSONException {
        JSONObject jSONObject;
        int i = 0;
        while (true) {
            if (i >= jSONArray.length()) {
                jSONObject = null;
                break;
            }
            jSONObject = jSONArray.getJSONObject(i);
            if (!jSONObject.isNull(RecommendConstants.FILE_ID) && str.equals(jSONObject.getString(RecommendConstants.FILE_ID))) {
                break;
            }
            i++;
        }
        if (jSONObject == null) {
            LogUtil.h("EzPlugin_FilePuller", "parseJsonArrayUrl jsonObject is null");
            return null;
        }
        if (!jSONObject.isNull(RecommendConstants.VER) && !jSONObject.isNull(SocialOperation.GAME_SIGNATURE)) {
            msoVar.b(jSONObject.getString(RecommendConstants.VER));
            msoVar.a(jSONObject.getString(SocialOperation.GAME_SIGNATURE));
        }
        if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
            return jSONObject.getString(RecommendConstants.DOWNLOAD_URL);
        }
        LogUtil.h("EzPlugin_FilePuller", "parseJsonArrayUrl jsonObject url key is null, text=", String.valueOf(jSONObject));
        return null;
    }

    private static String d(String str, String str2, boolean z, mso msoVar) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("EzPlugin_FilePuller", "parseJsonUrl fileUrlJson is empty");
            return null;
        }
        if (!z && str2 != null && str.charAt(0) == '[') {
            return a(str, str2, msoVar);
        }
        return c(str, str2, z, msoVar);
    }

    private static void d(File file, boolean z) throws IOException {
        if (z ? file.exists() : file.exists() & file.isFile()) {
            LogUtil.a("EzPlugin_FilePuller", "initializeDestFile deleteDestFile existed: ", Boolean.valueOf(file.delete()));
        }
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            LogUtil.a("EzPlugin_FilePuller", "initializeDestFile mkdirFile is =", Boolean.valueOf(file.getParentFile().mkdirs()));
        }
        if (file.isFile()) {
            return;
        }
        LogUtil.a("EzPlugin_FilePuller", "initializeDestFile createNewFile is =", Boolean.valueOf(file.createNewFile()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void e(String str, String str2) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileOutputStream fileOutputStream;
        synchronized (msn.class) {
            LogUtil.a("EzPlugin_FilePuller", "renameFile oldPath is = ", str, ", destPath is =", str2);
            File file = new File(str);
            File file2 = new File(str2);
            FileInputStream fileInputStream3 = null;
            try {
                d(file2, false);
                fileInputStream2 = new FileInputStream(file);
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    byte[] bArr = new byte[4096];
                    for (int read = fileInputStream2.read(bArr); read != -1; read = fileInputStream2.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    IoUtils.e(fileInputStream2);
                    IoUtils.e(fileOutputStream);
                } catch (IOException e3) {
                    e = e3;
                    fileInputStream3 = fileOutputStream;
                    LogUtil.b("PluginDevice_FilePuller", "renameFile file ex=", ExceptionUtils.d(e));
                    IoUtils.e(fileInputStream2);
                    IoUtils.e(fileInputStream3);
                    e(file, "renameFile isOldFileDeleted: ");
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream3 = fileOutputStream;
                    fileInputStream = fileInputStream3;
                    fileInputStream3 = fileInputStream2;
                    IoUtils.e(fileInputStream3);
                    IoUtils.e(fileInputStream);
                    e(file, "renameFile isOldFileDeleted: ");
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                fileInputStream2 = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                IoUtils.e(fileInputStream3);
                IoUtils.e(fileInputStream);
                e(file, "renameFile isOldFileDeleted: ");
                throw th;
            }
            e(file, "renameFile isOldFileDeleted: ");
        }
    }

    private static boolean d(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = str.indexOf(":")) >= 0) {
            return "http".equalsIgnoreCase(str.substring(0, indexOf));
        }
        return false;
    }

    public void a(final msq msqVar) {
        if (msqVar == null) {
            LogUtil.h("EzPlugin_FilePuller", "addTask task is null");
            return;
        }
        mso msoVar = new mso();
        msoVar.e(0);
        msoVar.b(-8);
        LogUtil.a("EzPlugin_FilePuller", "addTask download file total size:", Integer.valueOf(msqVar.k()));
        msoVar.a(msqVar.k());
        this.g.put(msqVar, msoVar);
        this.h.execute(new Runnable() { // from class: msn.1
            @Override // java.lang.Runnable
            public void run() {
                mso msoVar2 = (mso) msn.this.g.get(msqVar);
                if (msoVar2 != null) {
                    msoVar2.b(0);
                    msoVar2.e(0);
                    msn.this.g(msqVar, msoVar2);
                }
            }
        });
    }

    public void c(msq msqVar) {
        mso msoVar = this.g.get(msqVar);
        if (msoVar != null) {
            switch (msoVar.i()) {
                case -8:
                case -6:
                case -5:
                case -4:
                case -3:
                case -2:
                case -1:
                case 1:
                    msoVar.b(-10);
                    this.g.remove(msqVar);
                    break;
                case 0:
                    msoVar.b(-10);
                    break;
            }
        }
    }

    public mso d(msq msqVar) {
        mso msoVar = this.g.get(msqVar);
        if (msoVar == null) {
            mso msoVar2 = new mso();
            msoVar2.e(0);
            msoVar2.b(-7);
            return msoVar2;
        }
        int i = msoVar.i();
        if (i == 0 || i == -8) {
            return msoVar;
        }
        this.g.remove(msqVar);
        return msoVar;
    }

    public msq e(String str) {
        if (str == null) {
            return null;
        }
        for (msq msqVar : this.g.keySet()) {
            if (str.equals(msqVar.o())) {
                return msqVar;
            }
        }
        return null;
    }

    private String e(msq msqVar) {
        if ((msqVar.f() & 6) != 0) {
            try {
                String str = this.f15151a.getDir("cache", 0).getCanonicalPath() + File.pathSeparator;
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(jdq.e(this.f15151a, msqVar.j() + msqVar.o()));
                return sb.toString();
            } catch (IOException e2) {
                LogUtil.b("EzPlugin_FilePuller", "getPathName:getCanonicalPath ex=", ExceptionUtils.d(e2));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(File.pathSeparator);
                sb2.append(jdq.e(this.f15151a, msqVar.j() + msqVar.o()));
                return sb2.toString();
            }
        }
        return msqVar.e() + ".tmp";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(msq msqVar, mso msoVar) {
        msoVar.d(e(msqVar));
        LogUtil.a("EzPlugin_FilePuller", "processTask pathname is = ", msoVar.c());
        String c2 = c(msqVar, msoVar);
        if (c2 == null) {
            LogUtil.h("EzPlugin_FilePuller", "processTask fileUrlJson is null");
            msoVar.b(-1);
            msqVar.e(msoVar);
            msqVar.b((PullListener) null);
            return;
        }
        if ("Not Modified".equals(c2)) {
            LogUtil.a("EzPlugin_FilePuller", "processTask fileUrlJson not modified");
            msoVar.b(-11);
            msqVar.e(msoVar);
            msqVar.b((PullListener) null);
            return;
        }
        msoVar.e(c2);
        String d2 = d(c2, msqVar.c(), msqVar.h(), msoVar);
        if (d2 == null) {
            LogUtil.h("EzPlugin_FilePuller", "processTask fileUrlResult is null");
            msoVar.b(-5);
            msqVar.e(msoVar);
            msqVar.b((PullListener) null);
            return;
        }
        if (!e(msqVar, msoVar.e())) {
            LogUtil.a("EzPlugin_FilePuller", "processTask file not modified");
            msoVar.b(-11);
            msqVar.e(msoVar);
            msqVar.b((PullListener) null);
            return;
        }
        String j = msqVar.j();
        msqVar.h(d2);
        msqVar.e(msoVar);
        d(e(msqVar, msoVar), msqVar, msoVar, j);
        LogUtil.a("EzPlugin_FilePuller", "processTask result status : ", Integer.valueOf(msoVar.i()));
        msqVar.e(msoVar);
        msqVar.b((PullListener) null);
    }

    private boolean e(msq msqVar, String str) {
        if ("GET".equals(msqVar.i()) && !TextUtils.isEmpty(str)) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), j() + msqVar.c() + "_version");
            LogUtil.a("EzPlugin_FilePuller", "indexSpVer:", b2, " indexVersion:", str);
            if (!TextUtils.isEmpty(b2) && str.compareTo(b2) <= 0) {
                return false;
            }
        }
        return true;
    }

    private String j() {
        if (CommonUtil.bv()) {
            return "Release_";
        }
        if (CommonUtil.as()) {
            return "Beta_";
        }
        if (CommonUtil.cc()) {
            return "Test_";
        }
        LogUtil.h("EzPlugin_FilePuller", "indexKeySuffix else branch");
        return "";
    }

    private void d(int i, msq msqVar, mso msoVar, String str) {
        if (i != 0) {
            if (i == -3) {
                e(new File(msoVar.c()), "timeout deleteFile is = ");
                msoVar.b(-3);
                return;
            } else if (i == -10) {
                e(new File(msoVar.c()), "cancel deleteFile is = ");
                msoVar.b(-10);
                return;
            } else {
                e(new File(msoVar.c()), "unexpected deleteFile is = ");
                msoVar.b(-1);
                return;
            }
        }
        if (!c(msqVar.a(), msoVar.c())) {
            msoVar.b(-6);
            return;
        }
        if ("plugin_index".equals(msqVar.c()) || "device_index_all".equals(msqVar.c()) || msr.d.containsValue(msqVar.c()) || "index_sport_intensity".equals(msqVar.c())) {
            if (!d(msoVar)) {
                msoVar.b(-6);
                e(new File(msoVar.c()), "incomplete deleteFile is = ");
                return;
            }
            if ("device_index_all".equals(msqVar.c())) {
                str = str + "device_index_all";
            }
            d(str, msoVar.d());
            a(msqVar.c(), msoVar.e());
        }
        msoVar.b(1);
        LogUtil.a("EzPlugin_FilePuller", "processTask resource type : ", Integer.valueOf(msqVar.f()));
        j(msqVar, msoVar);
    }

    private boolean d(mso msoVar) {
        String b2 = SecurityUtils.b(new File(msoVar.c()), msoVar.e().getBytes(StandardCharsets.UTF_8), true);
        LogUtil.a("EzPlugin_FilePuller", "verifySignature signature is ", msoVar.h(), ", hashSignature is ", b2);
        return b2 != null && b2.equalsIgnoreCase(msoVar.h());
    }

    public void a(String str, String str2) {
        String str3 = j() + str + "_version";
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str3, str2, storageParams);
    }

    public void d(String str, String str2) {
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str, str2, storageParams);
    }

    private void j(msq msqVar, mso msoVar) {
        if ((msqVar.f() & 4) != 0) {
            String c2 = msoVar.c();
            if ((msqVar.f() & 2) != 0) {
                msoVar.d(msoVar.c() + ".d");
            }
            if (!msp.a(c2, msoVar.c())) {
                LogUtil.a("EzPlugin_FilePuller", "processTaskOption decryptFile failure");
                msoVar.b(-6);
            }
            e(new File(c2), "decrypt deleteFile is = ");
        }
        if ((msqVar.f() & 2) != 0) {
            if (msp.c(msoVar.c(), msqVar.e()) <= 0) {
                LogUtil.a("EzPlugin_FilePuller", "processTaskOption unzip failure");
                msoVar.b(-6);
            }
            e(new File(msoVar.c()), "unzip deleteFile is = ");
            return;
        }
        if ((msqVar.f() & 4) == 0) {
            e(msoVar.c(), msqVar.e());
        }
    }

    private int e(msq msqVar, mso msoVar) {
        LogUtil.a("EzPlugin_FilePuller", "downloadFile start: ", msqVar.j());
        if (!TextUtils.isEmpty(msqVar.j())) {
            return b(msqVar, msoVar);
        }
        LogUtil.h("EzPlugin_FilePuller", "downloadFile http url isEmpty");
        return -1;
    }

    private int b(msq msqVar, mso msoVar) {
        try {
            Response<ResponseBody> execute = ((CommonApi) lqi.a(i()).b(CommonApi.class)).commonDownload(msqVar.j()).execute();
            int code = execute.getCode();
            if (code == 200) {
                return d(msqVar, msoVar, execute.getBody());
            }
            Object[] objArr = new Object[3];
            objArr[0] = d(msqVar.j()) ? "downloadFileHttps" : "downloadFileHttp";
            objArr[1] = " ConRspCode = ";
            objArr[2] = Integer.valueOf(code);
            LogUtil.h("PluginDevice_FilePuller", objArr);
            return -3;
        } catch (IOException e2) {
            ReleaseLogUtil.c("PluginDevice_FilePuller", "downloadFileContent ex=", ExceptionUtils.d(e2));
            return -1;
        }
    }

    private int d(msq msqVar, mso msoVar, ResponseBody responseBody) throws IOException {
        if (responseBody == null || responseBody.getInputStream() == null) {
            LogUtil.h("EzPlugin_FilePuller", "downloadFileContent responseBody or responseBody.getInputStream() is null");
            return -1;
        }
        File file = new File(msoVar.c());
        d(file, true);
        InputStream inputStream = responseBody.getInputStream();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    msoVar.e(msoVar.b() + read);
                    fileOutputStream.write(bArr, 0, read);
                    if (msoVar.i() == -10) {
                        this.g.remove(msqVar);
                        break;
                    }
                    msqVar.e(msoVar);
                }
                fileOutputStream.flush();
                if (msoVar.i() != -10) {
                    fileOutputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return 0;
                }
                LogUtil.a("EzPlugin_FilePuller", "downloadFileContent cancel download task");
                fileOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
                return -10;
            } finally {
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
    }

    public ArrayList<msq> b() {
        ArrayList<msq> arrayList = new ArrayList<>(this.g.keySet().size());
        Iterator<msq> it = this.g.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
