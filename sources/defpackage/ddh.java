package defpackage;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.wifi.lib.db.dbtable.DeviceListManager;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes3.dex */
public class ddh {
    private static final Object b = new Object();
    private static ddh e;

    private ddh() {
    }

    public static ddh c() {
        ddh ddhVar;
        synchronized (b) {
            if (e == null) {
                e = new ddh();
            }
            ddhVar = e;
        }
        return ddhVar;
    }

    public boolean b() {
        String b2;
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime");
        if (!Utils.o()) {
            LogUtil.a("ResourceManagerHelper", " isToUpdate freshTime");
            b2 = deviceCloudSharePreferencesManager.b("freshtime");
        } else {
            LogUtil.a("ResourceManagerHelper", " isToUpdate abroadFreshTime");
            b2 = deviceCloudSharePreferencesManager.b("abroadfreshtime");
        }
        try {
            if ((new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(b2).getTime()) / 86400000 >= 1) {
                LogUtil.a("ResourceManagerHelper", " isToUpdate");
                return true;
            }
        } catch (ParseException e2) {
            LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper isToUpdate", e2.getMessage());
        }
        return false;
    }

    public boolean e() {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime");
        if (!Utils.o()) {
            if (!"0".equals(deviceCloudSharePreferencesManager.b("freshtime"))) {
                return false;
            }
            LogUtil.a("ResourceManagerHelper", " group isFirstToDownload");
            return true;
        }
        if (!"0".equals(deviceCloudSharePreferencesManager.b("abroadfreshtime"))) {
            return false;
        }
        LogUtil.a("ResourceManagerHelper", " group_abroad isFirstToDownload");
        return true;
    }

    public boolean a(String str) {
        return new File(g(str)).exists() && new File(j(str)).exists() && new File(e(str)).exists();
    }

    public String e(String str) {
        return cos.e + str + File.separator + str + File.separator + "img";
    }

    public String j(String str) {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        if (str == null) {
            LogUtil.h("ResourceManagerHelper", " getStringPath productId is empty");
            return "";
        }
        if (new File(cos.e.concat(str).concat(File.separator).concat(str).concat(File.separator).concat("product.xml")).exists()) {
            String concat = cos.e.concat(str).concat(File.separator).concat(str).concat(File.separator);
            String concat2 = concat.concat("lang/strings").concat(locale.getLanguage());
            String concat3 = concat2.concat(WatchFaceConstant.XML_SUFFIX);
            String concat4 = concat2.concat(Constants.LINK).concat(country).concat(WatchFaceConstant.XML_SUFFIX);
            if (new File(concat4).exists()) {
                return concat4;
            }
            if (new File(concat3).exists()) {
                return concat3;
            }
            if ("CN".equals(country)) {
                return concat.concat("lang/stringszh-rCN.xml");
            }
            return concat.concat("lang/strings.xml");
        }
        if ("CN".equals(country)) {
            return cos.d.concat(str).concat(File.separator).concat("lang/stringszh-rCN.xml");
        }
        return cos.d.concat(str).concat(File.separator).concat("lang/strings.xml");
    }

    public String g(String str) {
        return cos.e + str + File.separator + str + File.separator + "product.xml";
    }

    public boolean f(String str) {
        return new File(str).exists();
    }

    public String b(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(cos.e);
        sb.append(str);
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append("conf/main_help.json");
        return sb.toString();
    }

    public String c(String str) {
        int lastIndexOf;
        return (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(".")) >= 0) ? str.substring(lastIndexOf) : "";
    }

    public void a(String str, String str2, ResourceFileListener resourceFileListener) {
        LogUtil.c("ResourceManagerHelper", "HttpUtil postRequest url servicesupport/updateserver/getLatestVersion");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d(str, str2, resourceFileListener);
    }

    private HttpClient a() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(10000).readTimeout(10000).build();
    }

    public Map<String, Object> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-type", "application/json");
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d6, code lost:
    
        if (r13 != 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d8, code lost:
    
        r13.onResult(-1, r0);
        r11 = r11;
        r12 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00db, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ba, code lost:
    
        if (r13 != 0) goto L48;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v26, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v30 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13, types: [int] */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [int] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.huawei.health.device.manager.ResourceFileListener] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(java.lang.String r11, java.lang.String r12, com.huawei.health.device.manager.ResourceFileListener r13) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ddh.d(java.lang.String, java.lang.String, com.huawei.health.device.manager.ResourceFileListener):void");
    }

    private String d(InputStreamReader inputStreamReader) {
        int read;
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        do {
            try {
                read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
            } catch (IOException e2) {
                LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper postRequestResult postRequest result e:", e2.getMessage());
            }
        } while (sb.length() + read <= 104857600);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
    
        if (android.text.TextUtils.isEmpty(r26) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0082, code lost:
    
        r2 = -1;
        r1 = r23;
        r3 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
    
        a(r26, r27, -1, "faild");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0056, code lost:
    
        if (android.text.TextUtils.isEmpty(r26) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007f, code lost:
    
        if (android.text.TextUtils.isEmpty(r26) == false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(defpackage.dch r24, java.lang.String r25, java.lang.String r26, com.huawei.health.device.manager.ResourceFileListener r27) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ddh.e(dch, java.lang.String, java.lang.String, com.huawei.health.device.manager.ResourceFileListener):void");
    }

    private boolean c(dch dchVar, ResourceFileListener resourceFileListener) {
        if (dchVar != null) {
            return false;
        }
        if (resourceFileListener == null) {
            return true;
        }
        resourceFileListener.onResult(-1, "groupFileParser is null");
        return true;
    }

    private String i(String str) {
        return CommonUtil.c(str);
    }

    private void e(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                return;
            }
        }
    }

    private File o(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.a("ResourceManagerHelper", " getTemp create_result is ", Boolean.valueOf(file.createNewFile()));
        }
        return file;
    }

    private boolean a(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        LogUtil.a("ResourceManagerHelper", " isFailedCode resCode = ", Integer.valueOf(responseCode));
        return responseCode != 200;
    }

    private void k(String str) {
        File file = new File(str.substring(0, str.lastIndexOf(File.separator)));
        if (file.exists()) {
            return;
        }
        LogUtil.a("ResourceManagerHelper", " initDirectoryFile isCreateResult is ", Boolean.valueOf(file.mkdirs()));
    }

    private HttpURLConnection n(String str) throws IOException {
        URL url = new URL(str);
        if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH))) {
            LogUtil.a("ResourceManagerHelper", " getHttpUrlConnection download https");
            if (URLConnectionInstrumentation.openConnection(url.openConnection()) instanceof HttpsURLConnection) {
                return (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            }
        } else if (URLConnectionInstrumentation.openConnection(url.openConnection()) instanceof HttpURLConnection) {
            return (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
        }
        return null;
    }

    private void b(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("GET");
    }

    private void e(int i, long j, long j2) {
        int i2 = i == 200000 ? 0 : 1;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("target", "2");
        linkedHashMap.put(OpAnalyticsConstants.TARGET_SOURCE_TYPE, "6");
        linkedHashMap.put(OpAnalyticsConstants.DELAY_MS, String.valueOf(j2 - j));
        linkedHashMap.put("flag", String.valueOf(i2));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEPEND_85040001.value(), linkedHashMap);
    }

    private void a(String str, ResourceFileListener resourceFileListener, int i, String str2) {
        if (i == -1 && ".zip".equals(c(str))) {
            File file = new File(str);
            if (!file.exists()) {
                return;
            }
            if (!file.delete()) {
                LogUtil.h("ResourceManagerHelper", " deleteFileAndCallbackResult delete file failed");
            }
        }
        if (resourceFileListener != null) {
            resourceFileListener.onResult(i, str2);
        }
    }

    private void b(InputStream inputStream, FileOutputStream fileOutputStream) {
        IoUtils.e(fileOutputStream);
        IoUtils.e(inputStream);
    }

    public void c(String str, InputStream inputStream, String str2, ResourceFileListener resourceFileListener, boolean z) {
        ZipEntry a2;
        if (TextUtils.isEmpty(CommonUtil.c(str))) {
            if (resourceFileListener != null) {
                resourceFileListener.onResult(-1, "unzipfaild");
                return;
            }
            return;
        }
        b g = new b(str2).g();
        try {
            try {
                if (!new File(str).exists()) {
                    a2 = c(str, g);
                } else {
                    a2 = a(str, inputStream, g);
                }
                b(z, g);
            } catch (IOException unused) {
                LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper unUpdateZip IOException:");
            }
            if (e(z, g, a2)) {
                return;
            }
            if (CommonUtil.cg()) {
                String c = g.c();
                StringBuilder sb = new StringBuilder(16);
                sb.append(str2);
                sb.append(File.separator);
                sb.append(c);
                a(sb.toString(), g.c());
            }
            g.b(200);
            g.d(g.c());
            String c2 = CommonUtil.c(b(g.c()));
            if (!TextUtils.isEmpty(c2)) {
                h(c2);
            }
        } finally {
            c(str, resourceFileListener, z, g);
        }
    }

    public void a(String str, String str2) {
        File file = new File(str);
        String b2 = SharedPreferenceManager.b(cpp.a(), String.valueOf(10000), "import_zip_product_id");
        if (c(file) && str2.equals(b2)) {
            StringBuilder sb = new StringBuilder(16);
            sb.append("com.huawei.health.device.");
            sb.append(b2);
            sb.append(".hpk");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(16);
            sb3.append(cpp.a().getExternalFilesDir(""));
            sb3.append(File.separator);
            sb3.append("h5pro");
            sb3.append(File.separator);
            sb3.append("com.huawei.health.device.");
            sb3.append(str2);
            sb3.append(File.separator);
            sb3.append(sb2);
            File file2 = new File(sb3.toString());
            File e2 = e(file);
            LogUtil.a("ResourceManagerHelper", "sourceHpkFile : ", e2);
            b(e2, file2);
            d(b2);
        }
    }

    public void d(String str) {
        try {
            StringBuilder sb = new StringBuilder(16);
            sb.append(cpp.a().getExternalFilesDir("").getCanonicalPath());
            sb.append(File.separator);
            sb.append("h5pro");
            File file = new File(sb.toString());
            if (file.exists() && file.isDirectory()) {
                d(sb.toString(), str);
            } else {
                boolean mkdir = file.mkdir();
                LogUtil.a("ResourceManagerHelper", "mkdir : ", Boolean.valueOf(mkdir));
                if (mkdir) {
                    d(sb.toString(), str);
                }
            }
        } catch (IOException unused) {
            LogUtil.h("ResourceManagerHelper", "createInstalledFile failed : IOException");
        }
    }

    private void d(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(str);
        sb.append(File.separator);
        sb.append("com.huawei.health.device.");
        sb.append(str2);
        sb.append(".installed");
        try {
            m(sb.toString());
        } catch (IOException unused) {
            LogUtil.h("ResourceManagerHelper", "writeInstallTime exception : IOException");
        }
    }

    private void m(String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        try {
            fileOutputStream.write(Long.toString(new Date().getTime()).getBytes(StandardCharsets.UTF_8));
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.h("ResourceManagerHelper", "fout close failed : IOException");
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (IOException unused2) {
                LogUtil.h("ResourceManagerHelper", "fout close failed : IOException");
            }
            throw th;
        }
    }

    private boolean c(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            for (File file2 : listFiles) {
                if (c(file2)) {
                    return true;
                }
            }
            return false;
        }
        return file.getName().trim().endsWith(".hpk");
    }

    private File e(File file) {
        File file2 = null;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return null;
            }
            for (File file3 : listFiles) {
                file2 = e(file3);
                if (file2 != null) {
                    return file2;
                }
            }
            return file2;
        }
        if (file.getName().trim().endsWith(".hpk")) {
            return file;
        }
        return null;
    }

    public void b(File file, File file2) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
            LogUtil.a("ResourceManagerHelper", "copyFile failed, fromFile is null, or is not exists or is not fileor cannot read, or toFile's parentFile is null");
            return;
        }
        if (file2.getParentFile() == null) {
            LogUtil.a("ResourceManagerHelper", "copyFile failed, toFile.getParentFile() is null");
            return;
        }
        if (!file2.getParentFile().exists() && !file2.getParentFile().mkdirs()) {
            LogUtil.a("ResourceManagerHelper", "copyFile : make parentFile dir failed");
            return;
        }
        if (file2.exists() && !file2.delete()) {
            LogUtil.a("ResourceManagerHelper", "copyFile : delete origin file failed");
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read > 0) {
                            fileOutputStream3.write(bArr, 0, read);
                        } else {
                            LogUtil.a("ResourceManagerHelper", "copyFile : copy %s to %s, success", file.getCanonicalPath(), file2.getCanonicalPath());
                            e(fileInputStream2, fileOutputStream3);
                            return;
                        }
                    }
                } catch (IOException unused) {
                    fileInputStream = fileOutputStream3;
                    fileOutputStream2 = fileInputStream;
                    fileInputStream = fileInputStream2;
                    try {
                        LogUtil.h("ResourceManagerHelper", "copy file failed : IOException");
                        e(fileInputStream, fileOutputStream2);
                    } catch (Throwable th) {
                        fileOutputStream = fileOutputStream2;
                        th = th;
                        e(fileInputStream, fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileOutputStream3;
                    fileOutputStream = fileInputStream;
                    fileInputStream = fileInputStream2;
                    e(fileInputStream, fileOutputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException unused3) {
            fileOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private void e(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException unused) {
                LogUtil.b("ResourceManagerHelper", "streamClose failed : IOException");
                return;
            }
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

    private boolean e(boolean z, b bVar, ZipEntry zipEntry) throws IOException {
        String a2 = bVar.a();
        int i = 0;
        int i2 = 0;
        while (zipEntry != null) {
            String name = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                String substring = name.substring(0, name.length() - 1);
                boolean b2 = dbw.b(a2 + File.separator + substring);
                if (CommonUtil.bo()) {
                    b2 = !b2;
                }
                if (b2) {
                    return true;
                }
                LogUtil.a("ResourceManagerHelper", " unUpdateZip create File is error", a2, File.separator, substring);
            } else {
                String c = dbw.c(name, a2);
                if (z) {
                    c = new File(a2, name).getCanonicalPath();
                }
                String c2 = CommonUtil.c(c);
                if (c2 == null) {
                    LogUtil.h("ResourceManagerHelper", " unUpdateZip canonicalPath == null");
                    return true;
                }
                i = a(bVar, i, c2);
            }
            i2++;
            if (i2 > 1024) {
                throw new IOException("Too many files to unzip.");
            }
            if (i > 104857600) {
                throw new IOException("File being unzipped is too big.");
            }
            ZipInputStream h = bVar.h();
            if (h != null) {
                zipEntry = h.getNextEntry();
            }
        }
        return false;
    }

    private int a(b bVar, int i, String str) {
        try {
            try {
                bVar.c(new FileOutputStream(new File(str)));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bVar.h().read(bArr);
                    if (read == -1) {
                        break;
                    }
                    int i2 = i + read;
                    if (i2 > 104857600) {
                        throw new IOException("File being unzipped is too big.");
                    }
                    bVar.b().write(bArr, 0, read);
                    try {
                        bVar.b().flush();
                        i = i2;
                    } catch (IOException unused) {
                        i = i2;
                        LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper unUpdateZip IOException");
                        return i;
                    }
                }
            } catch (IOException unused2) {
            }
            return i;
        } finally {
            b((InputStream) null, bVar.b());
        }
    }

    private void b(boolean z, b bVar) {
        ArrayList arrayList = new ArrayList();
        String a2 = bVar.a();
        String c = bVar.c();
        arrayList.add(a2 + File.separator + c + File.separator + c);
        if (!z) {
            arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "img");
            arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "lang");
            arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "H5");
            arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "libs");
            arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "conf");
        } else {
            arrayList.clear();
            arrayList.add(a2 + File.separator + c);
        }
        dbw.b((ArrayList<String>) arrayList);
        if (!"6d5416d9-2167-41df-ab10-c492e152b44f".equals(c) && !"am16".equals(c)) {
            bVar.c(a2 + File.separator + c);
            return;
        }
        arrayList.clear();
        arrayList.add(a2 + File.separator + c + File.separator + "img");
        arrayList.add(a2 + File.separator + c + File.separator + "lang");
        arrayList.add(a2 + File.separator + c + File.separator + c + File.separator + "H5");
        StringBuilder sb = new StringBuilder();
        sb.append(a2);
        sb.append(File.separator);
        sb.append(c);
        sb.append(File.separator);
        sb.append("libs");
        arrayList.add(sb.toString());
        arrayList.add(a2 + File.separator + c + File.separator + "conf");
        dbw.b((ArrayList<String>) arrayList);
    }

    private void c(String str, ResourceFileListener resourceFileListener, boolean z, b bVar) {
        String c = bVar.c();
        String e2 = bVar.e();
        int d = bVar.d();
        LogUtil.a("ResourceManagerHelper", " exitUnzip finished resCode:", Integer.valueOf(d), ":result:", e2);
        d(bVar.h(), bVar.b());
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourDevice(c)) {
            e(str, bVar.a(), c, d);
            c(resourceFileListener, d, e2);
            d(c, resourceFileListener, d);
        } else if (!z) {
            e(str, bVar.a(), c, d);
            c(resourceFileListener, d, e2);
        } else if (resourceFileListener != null) {
            resourceFileListener.onResult(d, c);
        }
    }

    private ZipEntry a(String str, InputStream inputStream, b bVar) throws IOException {
        if (CommonUtil.cg()) {
            bVar.b(new ZipInputStream(inputStream));
        } else {
            bVar.b(new ZipInputStream(new FileInputStream(str)));
        }
        ZipEntry nextEntry = bVar.h().getNextEntry();
        if (nextEntry != null && nextEntry.getName() != null && !nextEntry.getName().contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
            bVar.e(nextEntry.getName());
            String c = bVar.c();
            int lastIndexOf = c.indexOf("/") <= 0 ? c.lastIndexOf(".") : c.indexOf("/");
            if (lastIndexOf > 0 && lastIndexOf <= c.length()) {
                bVar.e(c.substring(0, lastIndexOf));
            }
            LogUtil.a("ResourceManagerHelper", " getExistsZipEntry sdcard productId = ", c);
        } else {
            LogUtil.h("ResourceManagerHelper", " getExistsZipEntry zipEntry is null");
        }
        return nextEntry;
    }

    private ZipEntry c(String str, b bVar) throws IOException {
        bVar.e(str);
        bVar.b(new ZipInputStream(cpp.a().getAssets().open(str + ".zip")));
        return bVar.h().getNextEntry();
    }

    private void h(String str) throws FileNotFoundException {
        File file = new File(str);
        if (file.exists()) {
            b(file);
        }
    }

    private void c(ResourceFileListener resourceFileListener, int i, String str) {
        if (resourceFileListener != null) {
            LogUtil.a("ResourceManagerHelper", " resourceFileListenerResult resultCallBack is not null,resCode = ", Integer.valueOf(i), ",result = ", str);
            resourceFileListener.onResult(i, str);
        } else {
            ResourceManager.e().e(i, str);
        }
    }

    public void a(String str, InputStream inputStream, String str2, ResourceFileListener resourceFileListener) {
        c(str, inputStream, str2, resourceFileListener, false);
    }

    private void d(final String str, final ResourceFileListener resourceFileListener, final int i) {
        final dcz d;
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourDevice(str) && (d = ResourceManager.e().d(str)) != null) {
            File file = new File(cos.d + str + File.separator + (d.k().substring(0, d.k().indexOf(":") - 3) + "zip"));
            LogUtil.a("ResourceManagerHelper", " unSoZip jniLibs ");
            if (!file.exists()) {
                if (resourceFileListener != null) {
                    LogUtil.a("ResourceManagerHelper", " unSoZip resultCallBack is not null");
                    resourceFileListener.onResult(i, str);
                    return;
                } else {
                    LogUtil.h("ResourceManagerHelper", " unSoZip resultCallBack is null");
                    return;
                }
            }
            try {
                String canonicalPath = file.getCanonicalPath();
                int lastIndexOf = canonicalPath.lastIndexOf("/");
                if (lastIndexOf == -1) {
                    return;
                }
                a(file.getCanonicalPath(), (InputStream) null, canonicalPath.substring(0, lastIndexOf), new ResourceFileListener() { // from class: ddm
                    @Override // com.huawei.health.device.manager.ResourceFileListener
                    public final void onResult(int i2, String str2) {
                        ddh.d(dcz.this, str, resourceFileListener, i, i2, str2);
                    }
                });
            } catch (IOException e2) {
                LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper unSoZip IOException is ", e2.getMessage());
            }
        }
    }

    static /* synthetic */ void d(dcz dczVar, String str, ResourceFileListener resourceFileListener, int i, int i2, String str2) {
        LogUtil.a("ResourceManagerHelper", " unSoZip jniLibs resultCode ", Integer.valueOf(i2));
        ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).registerExternalKit(dczVar.s(), ResourceManager.e().c(str) + File.separator + dczVar.k());
        if (resourceFileListener != null) {
            LogUtil.a("ResourceManagerHelper", " unSoZip onResult resultCallBack is not null");
            resourceFileListener.onResult(i, str);
        } else {
            LogUtil.h("ResourceManagerHelper", " unSoZip resultCallBack is null");
        }
    }

    private void e(String str, String str2, String str3, int i) {
        if (i == 200) {
            if (new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime").e("isdemo")) {
                return;
            }
            File file = new File(str);
            if (file.exists() && !file.delete()) {
                LogUtil.h("ResourceManagerHelper", " deleteZipFileAndCallbackResult delete file failed");
                return;
            }
            return;
        }
        if (i == -1) {
            LogUtil.a("ResourceManagerHelper", " deleteZipFileAndCallbackResult UNZIP_FAIL_CODE delete file , path is ", str2, str3);
            File file2 = new File(str2 + str3);
            if (file2.exists() && !file2.delete()) {
                LogUtil.h("ResourceManagerHelper", " deleteZipFileAndCallbackResult delete unzipFile failed");
                return;
            }
            return;
        }
        LogUtil.h("ResourceManagerHelper", " deleteZipFileAndCallbackResult delete unzipFile can't find resCode");
    }

    private void d(ZipInputStream zipInputStream, FileOutputStream fileOutputStream) {
        IoUtils.e(fileOutputStream);
        IoUtils.e(zipInputStream);
    }

    public String e(String str, String str2) {
        FileInputStream fileInputStream;
        MessageDigest messageDigest;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance(str2);
                fileInputStream = new FileInputStream(str);
            } catch (IOException | NoSuchAlgorithmException unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    String e2 = e(messageDigest.digest());
                    IoUtils.e(fileInputStream);
                    return e2;
                }
            }
        } catch (IOException | NoSuchAlgorithmException unused2) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper getHashCode Exception");
            IoUtils.e(fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileInputStream);
            throw th;
        }
    }

    private void b(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            try {
                LogUtil.a("ResourceManagerHelper", " addMainHelp in");
                Gson gson = new Gson();
                byte[] bArr = new byte[fileInputStream.available()];
                if (fileInputStream.read(bArr) != -1) {
                    LogUtil.a("ResourceManagerHelper", " addMainHelp reading");
                    String str = new String(bArr, "UTF-8");
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    List<dkz> list = (List) gson.fromJson(CommonUtil.z(str), new TypeToken<List<dkz>>() { // from class: ddh.1
                    }.getType());
                    if (list != null && list.size() > 0) {
                        if (DeviceListManager.c(cpp.a()).c(list) > 0) {
                            LogUtil.c("ResourceManagerHelper", "insertDevice ------ success");
                        } else {
                            LogUtil.c("ResourceManagerHelper", "insertDevice ------ fail");
                        }
                    }
                }
            } catch (IOException unused) {
                LogUtil.b("ResourceManagerHelper", "ResourceManagerHelper addMainHelp inputStream IO exception");
            }
        } finally {
            IoUtils.e(fileInputStream);
        }
    }

    public String e(byte[] bArr) {
        String d = dis.d(bArr, null);
        if (d != null) {
            return d.toUpperCase(Locale.ENGLISH);
        }
        return null;
    }

    class e {

        /* renamed from: a, reason: collision with root package name */
        private dch f11604a;
        private String b;
        private String c;
        private int d;
        private ResourceFileListener h;

        e(dch dchVar, ResourceFileListener resourceFileListener, int i, String str, String str2) {
            this.f11604a = dchVar;
            this.h = resourceFileListener;
            this.d = i;
            this.b = str;
            this.c = str2;
        }

        public int d() {
            return this.d;
        }

        public String a() {
            return this.b;
        }

        public e b() {
            if (WatchFaceConstant.XML_SUFFIX.equals(ddh.this.c(this.c))) {
                LogUtil.a("ResourceManagerHelper", " FileManager download the extension is xml");
                String str = this.c;
                ArrayList<dcu> c = c(this.f11604a, str.substring(str.lastIndexOf("/") + 1));
                LogUtil.a("ResourceManagerHelper", "  group parse success ");
                if (this.h != null) {
                    LogUtil.a("ResourceManagerHelper", "  group parse success callback");
                    this.h.onResult(200, MessageConstant.GROUP_MEDAL_TYPE);
                }
                if (c.size() > 0) {
                    LogUtil.a("ResourceManagerHelper", " ====toDownloadList size=", Integer.valueOf(c.size()));
                    this.d = 4000;
                    this.b = WatchFaceConstant.XML_SUFFIX;
                    ResourceManager.e().e(c);
                } else {
                    this.d = 4001;
                    this.b = WatchFaceConstant.XML_SUFFIX;
                }
            }
            if (".zip".equals(ddh.this.c(this.c))) {
                LogUtil.a("ResourceManagerHelper", " FileManager download the extension is zip");
                this.d = AwarenessConstants.REGISTER_SUCCESS_CODE;
                this.b = this.c;
            }
            return this;
        }

        private ArrayList<dcu> c(dch dchVar, String str) {
            String d;
            ArrayList<dcr> a2;
            String d2;
            if (str.equals("groups.xml")) {
                d = dch.d();
                a2 = dch.a("groups.xml");
                d2 = dch.d();
                new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime").a("freshtime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            } else if (CommonUtil.cg()) {
                d = dch.d();
                a2 = dch.a("groups.xml");
                d2 = dch.d();
                new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime").a("freshtime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            } else {
                d = dch.d();
                a2 = dch.a("groups_abroad.xml");
                d2 = dch.d();
                new DeviceCloudSharePreferencesManager(cpp.a(), "devicedownloadtime").a("abroadfreshtime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            }
            ArrayList<dcu> arrayList = new ArrayList<>();
            if (d2 != null && !d2.equals(d)) {
                LogUtil.a("ResourceManagerHelper", " getProductFileInfos oldVersion != newVersion");
                return dchVar.e(a2);
            }
            LogUtil.a("ResourceManagerHelper", " getProductFileInfos oldVersion == newVersion");
            return arrayList;
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f11603a;
        private String b;
        private FileOutputStream c;
        private int d;
        private String e;
        private ZipInputStream g;
        private String h;

        b(String str) {
            this.e = str;
        }

        public int d() {
            return this.d;
        }

        public void b(int i) {
            this.d = i;
        }

        public String e() {
            return this.f11603a;
        }

        public void d(String str) {
            this.f11603a = str;
        }

        public String a() {
            return this.h;
        }

        public void c(String str) {
            this.h = str;
        }

        public String c() {
            return this.b;
        }

        public void e(String str) {
            this.b = str;
        }

        public ZipInputStream h() {
            return this.g;
        }

        public void b(ZipInputStream zipInputStream) {
            this.g = zipInputStream;
        }

        public FileOutputStream b() {
            return this.c;
        }

        public void c(FileOutputStream fileOutputStream) {
            this.c = fileOutputStream;
        }

        public b g() {
            this.d = -1;
            this.f11603a = "unzipfaild";
            this.h = this.e;
            this.b = "";
            this.g = null;
            this.c = null;
            return this;
        }
    }
}
