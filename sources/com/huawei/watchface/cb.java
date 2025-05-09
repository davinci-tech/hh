package com.huawei.watchface;

import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class cb {
    public cb(final String str, final LinkedHashMap<String, String> linkedHashMap, final File file, final ResponseListener<Integer> responseListener) {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.cb$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                cb.this.a(str, linkedHashMap, responseListener, file);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, LinkedHashMap linkedHashMap, ResponseListener responseListener, File file) {
        OutputStream outputStream;
        FileInputStream fileInputStream;
        HttpsURLConnection a2;
        BufferedReader bufferedReader = null;
        try {
            a2 = a(str, linkedHashMap);
        } catch (Exception e) {
            e = e;
            outputStream = null;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            outputStream = null;
            fileInputStream = null;
        }
        if (a2 == null) {
            HwLog.i("ImagePutRequest", "ImagePutRequest connection is null");
            responseListener.onError();
            Utils.closeSilently(null);
            Utils.closeSilently(null);
            Utils.closeSilently(null);
            return;
        }
        outputStream = a2.getOutputStream();
        try {
            fileInputStream = PversionSdUtils.b(file);
            try {
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else {
                            outputStream.write(bArr, 0, read);
                        }
                    }
                    outputStream.flush();
                    outputStream.close();
                    int responseCode = a2.getResponseCode();
                    HwLog.i("ImagePutRequest", "ImagePutRequest responseCode =" + responseCode);
                    if (a2.getResponseCode() == 200) {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(a2.getInputStream(), StandardCharsets.UTF_8));
                        while (true) {
                            try {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                } else {
                                    HwLog.i("ImagePutRequest", readLine);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                bufferedReader = bufferedReader2;
                                HwLog.e("ImagePutRequest", "ImagePutRequest: " + HwLog.printException(e));
                                responseListener.onError();
                                Utils.closeSilently(bufferedReader);
                                Utils.closeSilently(outputStream);
                                Utils.closeSilently(fileInputStream);
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader2;
                                Utils.closeSilently(bufferedReader);
                                Utils.closeSilently(outputStream);
                                Utils.closeSilently(fileInputStream);
                                throw th;
                            }
                        }
                        bufferedReader = bufferedReader2;
                    } else {
                        BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(a2.getErrorStream(), StandardCharsets.UTF_8));
                        while (true) {
                            try {
                                String readLine2 = bufferedReader3.readLine();
                                if (readLine2 == null) {
                                    break;
                                } else {
                                    HwLog.i("ImagePutRequest", readLine2);
                                }
                            } catch (Exception e3) {
                                e = e3;
                                bufferedReader = bufferedReader3;
                                HwLog.e("ImagePutRequest", "ImagePutRequest: " + HwLog.printException(e));
                                responseListener.onError();
                                Utils.closeSilently(bufferedReader);
                                Utils.closeSilently(outputStream);
                                Utils.closeSilently(fileInputStream);
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedReader = bufferedReader3;
                                Utils.closeSilently(bufferedReader);
                                Utils.closeSilently(outputStream);
                                Utils.closeSilently(fileInputStream);
                                throw th;
                            }
                        }
                        bufferedReader = bufferedReader3;
                    }
                    a2.disconnect();
                    responseListener.onResponse(Integer.valueOf(responseCode));
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = fileInputStream;
                bufferedReader = bufferedReader;
            }
        } catch (Exception e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
        }
        Utils.closeSilently(bufferedReader);
        Utils.closeSilently(outputStream);
        Utils.closeSilently(fileInputStream);
    }

    private HttpsURLConnection a(String str, LinkedHashMap<String, String> linkedHashMap) {
        try {
            URL url = new URL(str);
            SSFCompatiableSystemCA sSFCompatiableSystemCA = SSFCompatiableSystemCA.getInstance(Environment.getApplicationContext(), EncryptUtil.genSecureRandom());
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                httpsURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            httpsURLConnection.setSSLSocketFactory(sSFCompatiableSystemCA);
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod(ProfileRequestConstants.PUT_TYPE);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setConnectTimeout(5000);
            httpsURLConnection.setReadTimeout(5000);
            httpsURLConnection.connect();
            return httpsURLConnection;
        } catch (IOException unused) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection IOEexception.");
            return null;
        } catch (KeyManagementException unused2) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection KeyManagementException.");
            return null;
        } catch (KeyStoreException unused3) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection KeyStoreException.");
            return null;
        } catch (NoSuchAlgorithmException unused4) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection NoSuchAlgorithmException.");
            return null;
        } catch (CertificateException unused5) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection CertificateException.");
            return null;
        } catch (Exception e) {
            HwLog.e("ImagePutRequest", "configHttpUrlConnection exception:" + HwLog.printException(e));
            return null;
        }
    }
}
