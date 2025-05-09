package com.huawei.appgallery.marketinstallerservice.impl.download;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;
import com.huawei.hms.network.embedded.j2;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import defpackage.agj;
import defpackage.agk;
import defpackage.agp;
import defpackage.agr;
import defpackage.agu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: classes3.dex */
public class a extends AsyncTask<Void, Integer, Void> {

    /* renamed from: a, reason: collision with root package name */
    private String f1879a;
    private String b;
    private String c;
    private MarketInfo d;
    private InterfaceC0042a e;
    private String g = null;
    private int j = 0;
    private int h = 0;

    /* renamed from: com.huawei.appgallery.marketinstallerservice.impl.download.a$a, reason: collision with other inner class name */
    public interface InterfaceC0042a {
        void a(int i, int i2);

        void a(MarketInfo marketInfo, int i, int i2);

        Context b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        if (numArr.length < 2) {
            return;
        }
        int intValue = numArr[0].intValue();
        int intValue2 = numArr[1].intValue();
        if (intValue == 0) {
            this.e.a(this.d, this.j, this.h);
            return;
        }
        if (intValue == 1 || intValue == 2 || intValue == 3 || intValue == 4 || intValue == 5) {
            this.e.a(intValue, intValue2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        if (this.e == null) {
            agr.e("MarketDownloadTask", "the callback is null error!");
            return null;
        }
        if (this.d == null) {
            agr.c("MarketDownloadTask", "start getMarketInfo");
            com.huawei.appgallery.marketinstallerservice.a.e.a newInstance = com.huawei.appgallery.marketinstallerservice.a.e.a.newInstance(this.e.b());
            newInstance.setServiceUrl(this.c);
            newInstance.setSubsystem(this.b);
            newInstance.setMarketPkg(this.f1879a);
            ResponseBean a2 = agp.a(newInstance);
            if (!(a2 instanceof agk)) {
                agr.e("MarketDownloadTask", "getMarketInfo error response is null!");
                this.j = -1;
                publishProgress(0, 0);
                return null;
            }
            this.j = a2.getResponseCode();
            int rtnCode = a2.getRtnCode();
            this.h = rtnCode;
            if (this.j == 0 && rtnCode == 0) {
                agk agkVar = (agk) a2;
                if (agkVar.getHiAppInfo() != null) {
                    this.d = agkVar.getHiAppInfo();
                    publishProgress(0, 0);
                }
            }
            agr.e("MarketDownloadTask", "getMarketInfo error: responseCode:" + this.j + ", returnCode:" + this.j);
            publishProgress(0, 0);
            return null;
        }
        agr.c("MarketDownloadTask", "allready has marketinfo!");
        publishProgress(0, 0);
        if (TextUtils.isEmpty(this.d.getPkgName()) || this.d.getFileSize() == 0 || TextUtils.isEmpty(this.d.getSha256()) || TextUtils.isEmpty(this.d.getDownUrl())) {
            agr.e("MarketDownloadTask", "getMarketInfo content is error!");
            publishProgress(4, 0);
            return null;
        }
        this.g = agj.a(this.e.b());
        if (b()) {
            d();
            return null;
        }
        publishProgress(2, 0);
        return null;
    }

    private void d() {
        HttpURLConnection e = e();
        if (e == null) {
            publishProgress(2, 0);
            return;
        }
        if (!d(e)) {
            publishProgress(2, 0);
        } else if (c()) {
            publishProgress(3, 0);
        } else {
            agr.b("MarketDownloadTask", "checkDownloadedFile failed");
            publishProgress(5, 0);
        }
    }

    private boolean b() {
        File file = new File(this.g);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            agr.e("MarketDownloadTask", "createDownloadFile failed");
            return false;
        }
        if (!file.exists() || file.delete()) {
            return true;
        }
        agr.e("MarketDownloadTask", "createDownloadFile failed");
        return false;
    }

    private HttpURLConnection e() {
        String str;
        try {
            HttpURLConnection e = e(this.d.getDownUrl());
            e.connect();
            int responseCode = e.getResponseCode();
            if (responseCode == 200) {
                return e;
            }
            agr.b("MarketDownloadTask", "AppDownloadTask responseCode error:" + responseCode);
            return null;
        } catch (IOException unused) {
            str = "AppDownloadTask connect IOException!";
            agr.b("MarketDownloadTask", str);
            return null;
        } catch (IllegalAccessException unused2) {
            str = "AppDownloadTask connect IllegalAccessException!";
            agr.b("MarketDownloadTask", str);
            return null;
        } catch (KeyManagementException unused3) {
            str = "AppDownloadTask connect KeyManagementException!";
            agr.b("MarketDownloadTask", str);
            return null;
        } catch (KeyStoreException unused4) {
            str = "AppDownloadTask connect KeyStoreException!";
            agr.b("MarketDownloadTask", str);
            return null;
        } catch (NoSuchAlgorithmException unused5) {
            str = "AppDownloadTask connect NoSuchAlgorithmException!";
            agr.b("MarketDownloadTask", str);
            return null;
        } catch (CertificateException unused6) {
            str = "AppDownloadTask connect CertificateException!";
            agr.b("MarketDownloadTask", str);
            return null;
        }
    }

    private boolean d(HttpURLConnection httpURLConnection) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(this.g)));
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[8192];
                long j = 0;
                int i = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        agu.c(bufferedInputStream);
                        agu.c(bufferedOutputStream);
                        return true;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                    j += read;
                    int fileSize = (int) ((100 * j) / this.d.getFileSize());
                    if (fileSize != i) {
                        publishProgress(1, Integer.valueOf(fileSize));
                        i = fileSize;
                    }
                }
            } catch (IOException unused2) {
                bufferedInputStream2 = bufferedInputStream;
                agr.b("MarketDownloadTask", "AppDownloadTask readStream exception IOException!");
                agu.c(bufferedInputStream2);
                agu.c(bufferedOutputStream);
                return false;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                agu.c(bufferedInputStream2);
                agu.c(bufferedOutputStream);
                throw th;
            }
        } catch (IOException unused3) {
            bufferedOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
        }
    }

    private boolean c() {
        String d = agu.d(this.g, "SHA-256");
        if (d != null) {
            return d.equalsIgnoreCase(this.d.getSha256());
        }
        agr.b("MarketDownloadTask", "checkDownloadedFile: file hash is null");
        return false;
    }

    private HttpURLConnection e(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty(j2.v, "identity");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactory.getInstance(this.e.b()));
            httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        }
        return httpURLConnection;
    }

    public a(InterfaceC0042a interfaceC0042a, InstallParamSpec installParamSpec) {
        this.c = null;
        this.b = null;
        this.f1879a = null;
        this.e = interfaceC0042a;
        this.d = installParamSpec.getMarketInfo();
        this.c = installParamSpec.getServerUrl();
        this.b = installParamSpec.getSubsystem();
        this.f1879a = installParamSpec.getMarketPkg();
    }
}
