package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudHttpCallBack;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class ree {
    private ExecutorService c = Executors.newSingleThreadExecutor();
    private Context e;
    private static final Pattern d = Pattern.compile("(.*([/\\\\]{1}[\\.\\.]{1,2}|[\\.\\.]{1,2}[/\\\\]{1}|\\.\\.).*|\\.)");

    /* renamed from: a, reason: collision with root package name */
    private static volatile ree f16731a = null;

    private ree(Context context) {
        this.e = context.getApplicationContext();
    }

    public static ree e(Context context) {
        LogUtil.a("UIDV_FileUtil", "getInstance");
        if (f16731a == null) {
            synchronized (ree.class) {
                if (f16731a == null) {
                    f16731a = new ree(context);
                }
            }
        }
        return f16731a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str, String str2) {
        try {
            return Normalizer.normalize(new File(str2, str).getCanonicalPath(), Normalizer.Form.NFKC).startsWith(Normalizer.normalize(new File(str2).getCanonicalPath(), Normalizer.Form.NFKC));
        } catch (IOException e) {
            LogUtil.b("UIDV_FileUtil", e.getMessage());
            return false;
        }
    }

    public void e(reh rehVar, final LightCloudHttpCallBack lightCloudHttpCallBack) {
        LogUtil.a("UIDV_FileUtil", "doDownload");
        if (rehVar == null) {
            LogUtil.a("UIDV_FileUtil", "lightCloudObject is null");
            return;
        }
        final String g = g(rehVar.d());
        final String e = rehVar.e();
        if (g == null || "".equals(g)) {
            LogUtil.h("UIDV_FileUtil", "fileId is null");
            return;
        }
        if (e == null || "".equals(e)) {
            LogUtil.h("UIDV_FileUtil", "downloadUrl is null");
            return;
        }
        String b = b();
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("UIDV_FileUtil", "doDownload() canonicalPath is empty.");
            return;
        }
        final String str = b + File.separator + "lightcloud" + File.separator;
        final String str2 = str + g;
        if (str2 == null || "".equals(str2)) {
            LogUtil.h("UIDV_FileUtil", "savePath is null");
            return;
        }
        ExecutorService executorService = this.c;
        if (executorService == null || executorService.isShutdown()) {
            this.c = Executors.newSingleThreadExecutor();
        }
        this.c.execute(new Runnable() { // from class: ree.3
            @Override // java.lang.Runnable
            public void run() {
                if (ree.this.e(g, str)) {
                    ree.this.e(e, str2, lightCloudHttpCallBack);
                } else {
                    LogUtil.a("UIDV_FileUtil", "savePath is not sanitze");
                }
            }
        });
    }

    private String b() {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("UIDV_FileUtil", "getFileCanonicalPath mContext is null");
            return "";
        }
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            try {
                return filesDir.getCanonicalPath();
            } catch (IOException unused) {
                LogUtil.b("UIDV_FileUtil", "doDownload() getCanonicalPath meet io exception");
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public void e(String str, String str2, LightCloudHttpCallBack lightCloudHttpCallBack) {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        FileOutputStream fileOutputStream;
        int i;
        InputStream inputStream2;
        InputStream inputStream3;
        String message;
        ?? r2 = ProxyConfig.MATCH_HTTPS;
        LogUtil.a("UIDV_FileUtil", "download");
        String str3 = RecommendConstants.DOWNLOAD_FAIL;
        int i2 = -1;
        HttpURLConnection httpURLConnection2 = null;
        InputStream inputStream4 = null;
        r9 = null;
        InputStream inputStream5 = null;
        InputStream inputStream6 = null;
        FileOutputStream fileOutputStream2 = null;
        httpURLConnection2 = null;
        httpURLConnection2 = null;
        httpURLConnection2 = null;
        httpURLConnection2 = null;
        try {
        } catch (Throwable th) {
            th = th;
            inputStream = r2;
            httpURLConnection = httpURLConnection2;
        }
        try {
            try {
                URL url = new URL(str);
                if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH))) {
                    LogUtil.a("UIDV_FileUtil", ProxyConfig.MATCH_HTTPS);
                    httpURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                } else {
                    httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                }
                try {
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setRequestMethod("GET");
                    i = httpURLConnection.getResponseCode();
                    try {
                        LogUtil.a("UIDV_FileUtil", "resCode = ", Integer.valueOf(i));
                        if (i == 200) {
                            inputStream = httpURLConnection.getInputStream();
                            try {
                                LogUtil.a("UIDV_FileUtil", "saveFile");
                                if (!b(str2)) {
                                    LogUtil.h("UIDV_FileUtil", "createFileDir false");
                                    rei.d(httpURLConnection);
                                    rei.a(inputStream);
                                    rei.a((FileOutputStream) null);
                                    lightCloudHttpCallBack.onResponce(i, RecommendConstants.DOWNLOAD_FAIL);
                                    return;
                                }
                                LogUtil.c("UIDV_FileUtil", "savePath = ", str2);
                                String str4 = str2 + ".zip";
                                LogUtil.c("UIDV_FileUtil", "zipPath = ", str4);
                                int lastIndexOf = str2.lastIndexOf(47);
                                String substring = str4.substring(lastIndexOf + 1, str4.length());
                                LogUtil.a("UIDV_FileUtil", "fileName = ", substring);
                                File file = new File(e(str2, lastIndexOf) + File.separator + "_" + substring);
                                fileOutputStream = new FileOutputStream(file);
                                try {
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        } else {
                                            fileOutputStream.write(bArr, 0, read);
                                        }
                                    }
                                    fileOutputStream.flush();
                                    boolean renameTo = file.renameTo(new File(str4));
                                    LogUtil.a("UIDV_FileUtil", "isRenameOk = ", Boolean.valueOf(renameTo));
                                    if (renameTo) {
                                        str3 = "success";
                                        i2 = 0;
                                    } else {
                                        if (file.exists()) {
                                            LogUtil.a("UIDV_FileUtil", "deleteRet = ", Boolean.valueOf(file.delete()));
                                        }
                                        str3 = "rename fail";
                                    }
                                    i = i2;
                                    inputStream4 = inputStream;
                                } catch (MalformedURLException e) {
                                    e = e;
                                    inputStream5 = inputStream;
                                    InputStream inputStream7 = inputStream5;
                                    httpURLConnection2 = httpURLConnection;
                                    inputStream3 = inputStream7;
                                    LogUtil.b("UIDV_FileUtil", "MalformedURLException e = ", e.getMessage());
                                    message = e.getMessage();
                                    r2 = inputStream3;
                                    rei.d(httpURLConnection2);
                                    rei.a((InputStream) r2);
                                    rei.a(fileOutputStream);
                                    lightCloudHttpCallBack.onResponce(-1, message);
                                } catch (IOException e2) {
                                    e = e2;
                                    inputStream6 = inputStream;
                                    InputStream inputStream8 = inputStream6;
                                    httpURLConnection2 = httpURLConnection;
                                    inputStream2 = inputStream8;
                                    LogUtil.b("UIDV_FileUtil", "IOException e = ", e.getMessage());
                                    message = e.getMessage();
                                    r2 = inputStream2;
                                    rei.d(httpURLConnection2);
                                    rei.a((InputStream) r2);
                                    rei.a(fileOutputStream);
                                    lightCloudHttpCallBack.onResponce(-1, message);
                                } catch (Throwable th2) {
                                    th = th2;
                                    i2 = i;
                                    fileOutputStream2 = fileOutputStream;
                                    rei.d(httpURLConnection);
                                    rei.a(inputStream);
                                    rei.a(fileOutputStream2);
                                    lightCloudHttpCallBack.onResponce(i2, RecommendConstants.DOWNLOAD_FAIL);
                                    throw th;
                                }
                            } catch (MalformedURLException e3) {
                                e = e3;
                                fileOutputStream = null;
                            } catch (IOException e4) {
                                e = e4;
                                fileOutputStream = null;
                            } catch (Throwable th3) {
                                th = th3;
                                i2 = i;
                                rei.d(httpURLConnection);
                                rei.a(inputStream);
                                rei.a(fileOutputStream2);
                                lightCloudHttpCallBack.onResponce(i2, RecommendConstants.DOWNLOAD_FAIL);
                                throw th;
                            }
                        } else {
                            fileOutputStream = null;
                        }
                        rei.d(httpURLConnection);
                        rei.a(inputStream4);
                        rei.a(fileOutputStream);
                        lightCloudHttpCallBack.onResponce(i, str3);
                    } catch (MalformedURLException e5) {
                        e = e5;
                        fileOutputStream = null;
                        InputStream inputStream72 = inputStream5;
                        httpURLConnection2 = httpURLConnection;
                        inputStream3 = inputStream72;
                        LogUtil.b("UIDV_FileUtil", "MalformedURLException e = ", e.getMessage());
                        message = e.getMessage();
                        r2 = inputStream3;
                        rei.d(httpURLConnection2);
                        rei.a((InputStream) r2);
                        rei.a(fileOutputStream);
                        lightCloudHttpCallBack.onResponce(-1, message);
                    } catch (IOException e6) {
                        e = e6;
                        fileOutputStream = null;
                        InputStream inputStream82 = inputStream6;
                        httpURLConnection2 = httpURLConnection;
                        inputStream2 = inputStream82;
                        LogUtil.b("UIDV_FileUtil", "IOException e = ", e.getMessage());
                        message = e.getMessage();
                        r2 = inputStream2;
                        rei.d(httpURLConnection2);
                        rei.a((InputStream) r2);
                        rei.a(fileOutputStream);
                        lightCloudHttpCallBack.onResponce(-1, message);
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                    }
                } catch (MalformedURLException e7) {
                    e = e7;
                    i = -1;
                } catch (IOException e8) {
                    e = e8;
                    i = -1;
                } catch (Throwable th5) {
                    th = th5;
                    inputStream = null;
                }
            } catch (Throwable th6) {
                th = th6;
                inputStream = r2;
                httpURLConnection = httpURLConnection2;
                fileOutputStream2 = fileOutputStream;
                rei.d(httpURLConnection);
                rei.a(inputStream);
                rei.a(fileOutputStream2);
                lightCloudHttpCallBack.onResponce(i2, RecommendConstants.DOWNLOAD_FAIL);
                throw th;
            }
        } catch (MalformedURLException e9) {
            e = e9;
            i = -1;
            inputStream3 = null;
            fileOutputStream = null;
        } catch (IOException e10) {
            e = e10;
            i = -1;
            inputStream2 = null;
            fileOutputStream = null;
        } catch (Throwable th7) {
            th = th7;
            r2 = 0;
            fileOutputStream = null;
            inputStream = r2;
            httpURLConnection = httpURLConnection2;
            fileOutputStream2 = fileOutputStream;
            rei.d(httpURLConnection);
            rei.a(inputStream);
            rei.a(fileOutputStream2);
            lightCloudHttpCallBack.onResponce(i2, RecommendConstants.DOWNLOAD_FAIL);
            throw th;
        }
    }

    public void a(final reh rehVar, final LightCloudCallBack lightCloudCallBack) {
        LogUtil.a("UIDV_FileUtil", "doUnZip");
        if (rehVar == null) {
            LogUtil.h("UIDV_FileUtil", "lightCloudObject is null");
            return;
        }
        String g = g(rehVar.d());
        if (g == null || "".equals(g)) {
            LogUtil.h("UIDV_FileUtil", "fileId is null");
            return;
        }
        String b = rehVar.b();
        if (b == null || "".equals(b)) {
            LogUtil.h("UIDV_FileUtil", "ver is null");
            return;
        }
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("UIDV_FileUtil", "doUnZip() canonicalPath is empty.");
            return;
        }
        final String str = b2 + File.separator + "lightcloud" + File.separator + g + ".zip";
        final String str2 = b2 + File.separator + "lightcloud" + File.separator + g;
        ExecutorService executorService = this.c;
        if (executorService == null || executorService.isShutdown()) {
            this.c = Executors.newSingleThreadExecutor();
        }
        this.c.execute(new Runnable() { // from class: ree.1
            @Override // java.lang.Runnable
            public void run() {
                ree.this.c(str, str2, rehVar, lightCloudCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0117, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("UIDV_FileUtil", "entryNum exceed max");
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0134, code lost:
    
        defpackage.rei.d(r10);
        defpackage.rei.a(r11);
        defpackage.rei.a(r9);
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b0 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(java.lang.String r18, java.lang.String r19, defpackage.reh r20, com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack r21) {
        /*
            Method dump skipped, instructions count: 529
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ree.c(java.lang.String, java.lang.String, reh, com.huawei.ui.main.stories.lightcloud.service.LightCloudCallBack):void");
    }

    private File c(String str) {
        if (!a(str)) {
            return null;
        }
        File file = new File(e(str));
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            LogUtil.a("UIDV_FileUtil", "parentFile mkdir status=", Boolean.valueOf(parentFile.mkdirs()));
        }
        return file;
    }

    private static String e(String str, int i) {
        try {
            return str.substring(0, i);
        } catch (StringIndexOutOfBoundsException e) {
            LogUtil.b("UIDV_FileUtil", "StringIndexOutOfBoundsException:", e.getMessage());
            return null;
        }
    }

    private static boolean b(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        return file.exists() || file.mkdirs();
    }

    private String g(String str) {
        String str2 = "";
        if (str == null || "".equals(str)) {
            LogUtil.a("UIDV_FileUtil", "fileId null");
            return "";
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        String str3 = "airule";
        if (!normalize.startsWith(Normalizer.normalize("airule", Normalizer.Form.NFKC))) {
            str3 = "servicefwo";
            if (!normalize.startsWith(Normalizer.normalize("servicefwo", Normalizer.Form.NFKC))) {
                str3 = "servicefw";
                if (!normalize.startsWith(Normalizer.normalize("servicefw", Normalizer.Form.NFKC))) {
                    str3 = "healthconfig";
                    if (!normalize.startsWith(Normalizer.normalize("healthconfig", Normalizer.Form.NFKC))) {
                        str3 = "kit";
                        if (!normalize.startsWith(Normalizer.normalize("kit", Normalizer.Form.NFKC))) {
                            str3 = "oversea_app_update";
                            if (!normalize.startsWith(Normalizer.normalize("oversea_app_update", Normalizer.Form.NFKC))) {
                                str3 = "shortcuts";
                                if (!normalize.startsWith(Normalizer.normalize("shortcuts", Normalizer.Form.NFKC))) {
                                    if ("ecg_blocklist".equals(normalize)) {
                                        str2 = "ecgblock";
                                    }
                                    LogUtil.a("UIDV_FileUtil", "folder is ", str2);
                                    return str2;
                                }
                            }
                        }
                    }
                }
            }
        }
        str2 = str3;
        LogUtil.a("UIDV_FileUtil", "folder is ", str2);
        return str2;
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return !d.matcher(str).matches();
    }

    private static String d(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (int i = 0; i < str.length(); i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= 94) {
                    break;
                }
                if (str.charAt(i) == "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".charAt(i2)) {
                    stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".charAt(i2));
                    break;
                }
                i2++;
            }
        }
        return stringBuffer.toString();
    }

    private static String e(String str) {
        if (a(str)) {
            return d(str);
        }
        throw new IllegalArgumentException("Invalid file path");
    }
}
