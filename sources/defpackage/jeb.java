package defpackage;

import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/* loaded from: classes5.dex */
public class jeb {
    public static String d(String str, String str2) {
        if (str2 == null) {
            LogUtil.h("HwCommonHttpUtil", "doHttpPost null data");
            return null;
        }
        return a(str, str2, false);
    }

    private static String a(String str, String str2, boolean z) {
        String str3;
        InputStream inputStream = null;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                LogUtil.a("HwCommonHttpUtil", "postRequest url.openConnection!");
                LogUtil.a("HwCommonHttpUtil", "postRequest openConnection already!");
                try {
                    httpURLConnection.setRequestMethod("POST");
                    LogUtil.a("HwCommonHttpUtil", "postRequest setRequestMethod(HTTP_POST)!");
                    byte[] e = e(str2, z, httpURLConnection);
                    try {
                        if (e == null) {
                            LogUtil.h("HwCommonHttpUtil", "postRequest null data");
                            return null;
                        }
                        try {
                            inputStream = a(e, httpURLConnection);
                            LogUtil.a("HwCommonHttpUtil", "postRequest getHttpResponsesStream!");
                            str3 = c(inputStream);
                        } catch (Exception unused) {
                            LogUtil.b("HwCommonHttpUtil", "postRequest getHttpResponsesStream Exception");
                            LogUtil.a("HwCommonHttpUtil", "postRequest getHttpResponsesStream close response");
                            IoUtils.e(inputStream);
                            str3 = "";
                        }
                        return str3;
                    } finally {
                        LogUtil.a("HwCommonHttpUtil", "postRequest getHttpResponsesStream close response");
                        IoUtils.e(inputStream);
                    }
                } catch (ProtocolException e2) {
                    LogUtil.b("HwCommonHttpUtil", "postRequest setRequestMethod failed ", e2.getMessage());
                    return null;
                }
            } catch (IOException e3) {
                LogUtil.b("HwCommonHttpUtil", "postRequest openConnection failed", e3.getMessage());
                return null;
            }
        } catch (MalformedURLException e4) {
            LogUtil.b("HwCommonHttpUtil", "postRequest MalformedURLException", e4.getMessage());
            return null;
        }
    }

    public static String b(InputStream inputStream) {
        if (inputStream == null) {
            LogUtil.h("HwCommonHttpUtil", "getResponseLine response is null");
            return "";
        }
        return c(inputStream);
    }

    private static String c(InputStream inputStream) {
        BufferedReader bufferedReader;
        char c;
        InputStreamReader inputStreamReader = null;
        try {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream, "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader2);
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            int read = bufferedReader.read();
                            if (read == -1 || (c = (char) read) == '\n') {
                                break;
                            }
                            if (stringBuffer.length() >= 104857600) {
                                throw new IllegalStateException("input too long");
                            }
                            stringBuffer.append(c);
                        }
                        String stringBuffer2 = stringBuffer.toString();
                        IoUtils.e(inputStreamReader2);
                        IoUtils.e(bufferedReader);
                        IoUtils.e(inputStream);
                        return stringBuffer2;
                    } catch (UnsupportedEncodingException e) {
                        e = e;
                        inputStreamReader = inputStreamReader2;
                        LogUtil.b("HwCommonHttpUtil", "doPostRequest UnsupportedEncodingException!", e.getMessage());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(bufferedReader);
                        IoUtils.e(inputStream);
                        return "";
                    } catch (IOException e2) {
                        e = e2;
                        inputStreamReader = inputStreamReader2;
                        LogUtil.b("HwCommonHttpUtil", "doPostRequest IOException!", e.getMessage());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(bufferedReader);
                        IoUtils.e(inputStream);
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        inputStreamReader = inputStreamReader2;
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(bufferedReader);
                        IoUtils.e(inputStream);
                        throw th;
                    }
                } catch (UnsupportedEncodingException e3) {
                    bufferedReader = null;
                    inputStreamReader = inputStreamReader2;
                    e = e3;
                } catch (IOException e4) {
                    bufferedReader = null;
                    inputStreamReader = inputStreamReader2;
                    e = e4;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (UnsupportedEncodingException e5) {
            e = e5;
            bufferedReader = null;
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] e(java.lang.String r2, boolean r3, java.net.HttpURLConnection r4) {
        /*
            java.lang.String r0 = "HwCommonHttpUtil"
            if (r2 == 0) goto L14
            java.lang.String r1 = "UTF-8"
            byte[] r2 = r2.getBytes(r1)     // Catch: java.io.UnsupportedEncodingException -> Lb
            goto L15
        Lb:
            java.lang.String r2 = "getData UnsupportedEncodingException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L14:
            r2 = 0
        L15:
            e(r4, r2)
            java.lang.String r1 = "getData setUrlConnectionCommon end"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            if (r3 != 0) goto L2a
            java.lang.String r3 = "Accept-Encoding"
            java.lang.String r1 = "identity"
            r4.setRequestProperty(r3, r1)
        L2a:
            java.lang.String r3 = "getData setRequestProperty jsonObject already!"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jeb.e(java.lang.String, boolean, java.net.HttpURLConnection):byte[]");
    }

    public static void e(HttpURLConnection httpURLConnection, byte[] bArr) {
        if (httpURLConnection == null || bArr == null) {
            LogUtil.h("HwCommonHttpUtil", "setUrlConnectionCommon param is null");
            return;
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
        httpURLConnection.setRequestProperty("Connection", w9.j);
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x005d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:17:0x005d */
    private static InputStream a(byte[] bArr, HttpURLConnection httpURLConnection) {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3 = null;
        try {
            try {
                httpURLConnection.connect();
                LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream connect");
                outputStream = httpURLConnection.getOutputStream();
                try {
                    LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream getOutput");
                    outputStream.write(bArr);
                    LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream write");
                    LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream finally closeOutputStream!");
                    a(outputStream);
                    return e(httpURLConnection);
                } catch (IOException e) {
                    e = e;
                    LogUtil.b("HwCommonHttpUtil", "getHttpResponsesStream IOException", e.getMessage());
                    LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream finally closeOutputStream!");
                    a(outputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                outputStream3 = outputStream2;
                LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream finally closeOutputStream!");
                a(outputStream3);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            outputStream = null;
        } catch (Throwable th2) {
            th = th2;
            LogUtil.a("HwCommonHttpUtil", "getHttpResponsesStream finally closeOutputStream!");
            a(outputStream3);
            throw th;
        }
    }

    public static InputStream e(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                return httpURLConnection.getInputStream();
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("HwCommonHttpUtil", "getInputStream UnsupportedEncodingException");
            } catch (IOException e) {
                LogUtil.b("HwCommonHttpUtil", "getInputStream IOException", e.getMessage());
            }
        }
        return null;
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException unused) {
                LogUtil.b("HwCommonHttpUtil", "closeOutputStream IOException");
            }
        }
    }

    public static URL e(String str) {
        try {
            URL url = new URL(str);
            LogUtil.a("HwCommonHttpUtil", "getUrl url: ", str);
            return url;
        } catch (MalformedURLException e) {
            LogUtil.b("HwCommonHttpUtil", "getUrl MalformedURLException ", e.getMessage());
            return null;
        }
    }

    public static int b(String str, OutputStream outputStream) {
        URL e = e(str);
        int i = -1;
        if (e == null) {
            return -1;
        }
        try {
            byte[] bArr = null;
            HttpURLConnection httpURLConnection = URLConnectionInstrumentation.openConnection(e.openConnection()) instanceof HttpURLConnection ? (HttpURLConnection) URLConnectionInstrumentation.openConnection(e.openConnection()) : null;
            LogUtil.a("HwCommonHttpUtil", "doReportStatus (HttpURLConnection)url.openConnection!");
            LogUtil.a("HwCommonHttpUtil", "doReportStatus openConnection already!");
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.setRequestMethod("POST");
                } catch (ProtocolException e2) {
                    LogUtil.b("HwCommonHttpUtil", "doReportStatus setRequestMethod failed", e2.getMessage());
                    return -1;
                }
            }
            LogUtil.a("HwCommonHttpUtil", "doReportStatus setRequestMethod(HTTP_POST)!");
            if (outputStream != null) {
                try {
                    bArr = outputStream.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e3) {
                    LogUtil.b("HwCommonHttpUtil", "doReportStatus UnsupportedEncodingException", e3.getMessage());
                }
            }
            if (bArr == null) {
                LogUtil.h("HwCommonHttpUtil", "doReportStatus null data!");
                return -1;
            }
            e(httpURLConnection, bArr);
            LogUtil.a("HwCommonHttpUtil", "doReportStatus setUrlConnectionCommon end");
            try {
                i = b(bArr, httpURLConnection);
                LogUtil.a("HwCommonHttpUtil", "doReportStatus getHttpResponsesStream!");
            } catch (Exception unused) {
                LogUtil.b("HwCommonHttpUtil", "doReportStatus getHttpResponsesStream Exception");
            }
            LogUtil.a("HwCommonHttpUtil", "doReportStatus StatusCode is ", Integer.valueOf(i));
            return i;
        } catch (IOException e4) {
            LogUtil.b("HwCommonHttpUtil", "doReportStatus openConnection failed", e4.getMessage());
            return -1;
        }
    }

    private static int b(byte[] bArr, HttpURLConnection httpURLConnection) {
        int i = -1;
        if (httpURLConnection == null) {
            LogUtil.b("HwCommonHttpUtil", "getHttpReportCode httpConnection is null");
            return -1;
        }
        OutputStream outputStream = null;
        try {
            httpURLConnection.connect();
            LogUtil.a("HwCommonHttpUtil", "getHttpReportCode connect");
            outputStream = httpURLConnection.getOutputStream();
            LogUtil.a("HwCommonHttpUtil", "getHttpReportCode getOutput");
            outputStream.write(bArr);
            LogUtil.a("HwCommonHttpUtil", "getHttpReportCode write");
            httpURLConnection.getInputStream();
            i = httpURLConnection.getResponseCode();
            LogUtil.a("HwCommonHttpUtil", "getHttpReportCode statusCode: ", Integer.valueOf(i));
            return i;
        } catch (IOException e) {
            LogUtil.b("HwCommonHttpUtil", "getHttpReportCode IOException", e.getMessage());
            return i;
        } finally {
            LogUtil.a("HwCommonHttpUtil", "getHttpReportCode finally closeOutputStream!");
            a(outputStream);
        }
    }
}
