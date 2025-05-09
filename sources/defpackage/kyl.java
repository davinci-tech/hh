package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import health.compact.a.IoUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class kyl {

    public static class c {
        public int e = -1;
        public int b = -1;
        public List<e> d = null;

        /* renamed from: a, reason: collision with root package name */
        public boolean f14700a = false;

        public static class a {
            public String e = null;
            public List<String> d = null;
        }

        /* renamed from: kyl$c$c, reason: collision with other inner class name */
        public static class C0322c {
            public String b = null;
            public List<String> e = null;
        }

        public static class e {
            public String c = null;
            public int b = -1;

            /* renamed from: a, reason: collision with root package name */
            public List<C0322c> f14701a = null;
            public List<a> d = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005d, code lost:
    
        c(r7, r12);
        r5 = c(r7);
        r8 = a(r5, r10, r11);
        r6 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
    
        if (r6 == 200) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        if (r8 == 200) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.lang.Object[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static kyl.c c(android.content.Context r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "getChangeLogFromServer serverUri = "
            java.lang.String r2 = ", languageName = "
            java.lang.String r4 = ", newLanguageName = "
            r1 = r9
            r3 = r10
            r5 = r11
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5}
            java.lang.String r1 = "AppPullChangeLogGetXml"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 1
            r2 = 0
            r3 = 2
            r4 = 200(0xc8, float:2.8E-43)
            r5 = 0
            r6 = -1
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L42 java.net.URISyntaxException -> L45 java.io.IOException -> L47
            r7.<init>()     // Catch: java.lang.Throwable -> L42 java.net.URISyntaxException -> L45 java.io.IOException -> L47
            int r8 = b(r8, r9, r7)     // Catch: java.lang.Throwable -> L3c java.net.URISyntaxException -> L3e java.io.IOException -> L40
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            java.lang.String r6 = "getChangeLogFromServer statusCode = "
            r9[r2] = r6     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            r9[r0] = r6     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            if (r8 != r4) goto L69
            goto L5d
        L34:
            r9 = move-exception
            r6 = r8
            goto L71
        L37:
            r9 = move-exception
        L38:
            r6 = r8
            goto L4a
        L3a:
            r9 = move-exception
            goto L38
        L3c:
            r9 = move-exception
            goto L71
        L3e:
            r9 = move-exception
            goto L4a
        L40:
            r9 = move-exception
            goto L4a
        L42:
            r8 = move-exception
            r7 = r5
            goto L72
        L45:
            r8 = move-exception
            goto L48
        L47:
            r8 = move-exception
        L48:
            r9 = r8
            r7 = r5
        L4a:
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L3c
            java.lang.String r3 = "getChangeLogFromServer Exception"
            r8[r2] = r3     // Catch: java.lang.Throwable -> L3c
            java.lang.String r9 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> L3c
            r8[r0] = r9     // Catch: java.lang.Throwable -> L3c
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)     // Catch: java.lang.Throwable -> L3c
            if (r7 == 0) goto L69
            if (r6 != r4) goto L69
        L5d:
            c(r7, r12)
            java.io.InputStream r5 = c(r7)
            kyl$c r8 = a(r5, r10, r11)
            goto L6a
        L69:
            r8 = r5
        L6a:
            health.compact.a.IoUtils.e(r7)
            health.compact.a.IoUtils.e(r5)
            return r8
        L71:
            r8 = r9
        L72:
            if (r7 == 0) goto L80
            if (r6 != r4) goto L80
            c(r7, r12)
            java.io.InputStream r5 = c(r7)
            a(r5, r10, r11)
        L80:
            health.compact.a.IoUtils.e(r7)
            health.compact.a.IoUtils.e(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kyl.c(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):kyl$c");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v9 */
    private static void c(ByteArrayOutputStream byteArrayOutputStream, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        BufferedWriter bufferedWriter;
        OutputStreamWriter outputStreamWriter;
        Closeable closeable;
        BufferedWriter bufferedWriter2;
        ?? r7;
        Closeable closeable2;
        BufferedWriter bufferedWriter3;
        Closeable closeable3;
        LogUtil.a("AppPullChangeLogGetXml", "getChangeLogFromServer changeLogFilePath=", str);
        InputStream c2 = c(byteArrayOutputStream);
        BufferedWriter bufferedWriter4 = null;
        try {
            File file = new File(str);
            fileOutputStream = FileUtils.openOutputStream(file);
            try {
                if (file.createNewFile()) {
                    LogUtil.a("AppPullChangeLogGetXml", "getChangeLogFromServer createNewFile");
                }
                outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                try {
                    bufferedWriter2 = new BufferedWriter(outputStreamWriter);
                    try {
                        r7 = new InputStreamReader(c2, "UTF-8");
                        try {
                            try {
                                ?? bufferedReader = new BufferedReader(r7);
                                while (true) {
                                    try {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine == null) {
                                            break;
                                        }
                                        LogUtil.a("AppPullChangeLogGetXml", "readerLine" + readLine);
                                        bufferedWriter2.write(readLine + "\n");
                                        bufferedWriter2.flush();
                                    } catch (IOException unused) {
                                        bufferedWriter4 = bufferedReader;
                                        LogUtil.a("AppPullChangeLogGetXml", "saveChangeLog IOException");
                                        IoUtils.e(bufferedWriter2);
                                        bufferedWriter3 = bufferedWriter4;
                                        closeable3 = r7;
                                        IoUtils.e(outputStreamWriter);
                                        IoUtils.e(bufferedWriter3);
                                        IoUtils.e(closeable3);
                                        IoUtils.e(fileOutputStream);
                                        IoUtils.e(c2);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedWriter4 = bufferedReader;
                                        closeable2 = r7;
                                        bufferedWriter = bufferedWriter4;
                                        bufferedWriter4 = bufferedWriter2;
                                        closeable = closeable2;
                                        IoUtils.e(bufferedWriter4);
                                        IoUtils.e(outputStreamWriter);
                                        IoUtils.e(bufferedWriter);
                                        IoUtils.e(closeable);
                                        IoUtils.e(fileOutputStream);
                                        IoUtils.e(c2);
                                        throw th;
                                    }
                                }
                                IoUtils.e(bufferedWriter2);
                                bufferedWriter3 = bufferedReader;
                                closeable3 = r7;
                            } catch (IOException unused2) {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            closeable2 = r7;
                        }
                    } catch (IOException unused3) {
                        r7 = 0;
                    } catch (Throwable th4) {
                        th = th4;
                        closeable2 = null;
                    }
                } catch (IOException unused4) {
                    bufferedWriter2 = null;
                    r7 = bufferedWriter2;
                    LogUtil.a("AppPullChangeLogGetXml", "saveChangeLog IOException");
                    IoUtils.e(bufferedWriter2);
                    bufferedWriter3 = bufferedWriter4;
                    closeable3 = r7;
                    IoUtils.e(outputStreamWriter);
                    IoUtils.e(bufferedWriter3);
                    IoUtils.e(closeable3);
                    IoUtils.e(fileOutputStream);
                    IoUtils.e(c2);
                } catch (Throwable th5) {
                    th = th5;
                    bufferedWriter = null;
                    closeable = null;
                    IoUtils.e(bufferedWriter4);
                    IoUtils.e(outputStreamWriter);
                    IoUtils.e(bufferedWriter);
                    IoUtils.e(closeable);
                    IoUtils.e(fileOutputStream);
                    IoUtils.e(c2);
                    throw th;
                }
            } catch (IOException unused5) {
                outputStreamWriter = null;
                bufferedWriter2 = null;
                r7 = bufferedWriter2;
                LogUtil.a("AppPullChangeLogGetXml", "saveChangeLog IOException");
                IoUtils.e(bufferedWriter2);
                bufferedWriter3 = bufferedWriter4;
                closeable3 = r7;
                IoUtils.e(outputStreamWriter);
                IoUtils.e(bufferedWriter3);
                IoUtils.e(closeable3);
                IoUtils.e(fileOutputStream);
                IoUtils.e(c2);
            } catch (Throwable th6) {
                th = th6;
                bufferedWriter = null;
                outputStreamWriter = null;
                closeable = null;
                IoUtils.e(bufferedWriter4);
                IoUtils.e(outputStreamWriter);
                IoUtils.e(bufferedWriter);
                IoUtils.e(closeable);
                IoUtils.e(fileOutputStream);
                IoUtils.e(c2);
                throw th;
            }
        } catch (IOException unused6) {
            fileOutputStream = null;
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
        }
        IoUtils.e(outputStreamWriter);
        IoUtils.e(bufferedWriter3);
        IoUtils.e(closeable3);
        IoUtils.e(fileOutputStream);
        IoUtils.e(c2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x005d, code lost:
    
        r5 = a(c(r7), r10, r11);
        r6 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        if (r6 == 200) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
    
        if (r8 == 200) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.lang.Object[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static kyl.c c(android.content.Context r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "getChangeLogFromServer serverUri = "
            java.lang.String r2 = ", languageName = "
            java.lang.String r4 = ", newLanguageName = "
            r1 = r9
            r3 = r10
            r5 = r11
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5}
            java.lang.String r1 = "AppPullChangeLogGetXml"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            r0 = 1
            r2 = 0
            r3 = 2
            r4 = 200(0xc8, float:2.8E-43)
            r5 = 0
            r6 = -1
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L43 java.net.URISyntaxException -> L45 java.io.IOException -> L47
            r7.<init>()     // Catch: java.lang.Throwable -> L43 java.net.URISyntaxException -> L45 java.io.IOException -> L47
            int r8 = b(r8, r9, r7)     // Catch: java.lang.Throwable -> L3c java.net.URISyntaxException -> L3f java.io.IOException -> L41
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            java.lang.String r6 = "getChangeLogFromServer statusCode = "
            r9[r2] = r6     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            r9[r0] = r6     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            com.huawei.hwlogsmodel.LogUtil.c(r1, r9)     // Catch: java.lang.Throwable -> L34 java.net.URISyntaxException -> L37 java.io.IOException -> L3a
            if (r8 != r4) goto L65
            goto L5d
        L34:
            r9 = move-exception
            r6 = r8
            goto L3d
        L37:
            r9 = move-exception
        L38:
            r6 = r8
            goto L4a
        L3a:
            r9 = move-exception
            goto L38
        L3c:
            r9 = move-exception
        L3d:
            r5 = r7
            goto L69
        L3f:
            r9 = move-exception
            goto L4a
        L41:
            r9 = move-exception
            goto L4a
        L43:
            r8 = move-exception
            goto L6a
        L45:
            r8 = move-exception
            goto L48
        L47:
            r8 = move-exception
        L48:
            r9 = r8
            r7 = r5
        L4a:
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L3c
            java.lang.String r3 = "getChangeLogFromServer Exception"
            r8[r2] = r3     // Catch: java.lang.Throwable -> L3c
            java.lang.String r9 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> L3c
            r8[r0] = r9     // Catch: java.lang.Throwable -> L3c
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)     // Catch: java.lang.Throwable -> L3c
            if (r7 == 0) goto L65
            if (r6 != r4) goto L65
        L5d:
            java.io.InputStream r8 = c(r7)
            kyl$c r5 = a(r8, r10, r11)
        L65:
            health.compact.a.IoUtils.e(r7)
            return r5
        L69:
            r8 = r9
        L6a:
            if (r5 == 0) goto L75
            if (r6 != r4) goto L75
            java.io.InputStream r9 = c(r5)
            a(r9, r10, r11)
        L75:
            health.compact.a.IoUtils.e(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kyl.c(android.content.Context, java.lang.String, java.lang.String, java.lang.String):kyl$c");
    }

    private static InputStream c(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int i = 0;
        while (i < byteArray.length && byteArray[i] != 60) {
            i++;
        }
        byte[] bArr = new byte[byteArray.length - i];
        System.arraycopy(byteArray, i, bArr, 0, byteArray.length - i);
        return new ByteArrayInputStream(bArr);
    }

    private static int b(Context context, String str, OutputStream outputStream) throws IOException, URISyntaxException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(str);
        kxu.a(httpGet, defaultHttpClient, context);
        HttpParams params = httpGet.getParams();
        params.setIntParameter("http.socket.timeout", 20000);
        params.setIntParameter("http.connection.timeout", 20000);
        HttpProtocolParams.setUserAgent(params, kxu.m());
        int b2 = b(defaultHttpClient, httpGet, outputStream);
        httpGet.abort();
        LogUtil.c("AppPullChangeLogGetXml", "getXMLStreamFromServer statusCode = ", Integer.valueOf(b2), ", request abort");
        return b2;
    }

    private static int b(HttpClient httpClient, HttpGet httpGet, OutputStream outputStream) {
        HttpResponse execute;
        int i = -1;
        try {
            if (httpClient instanceof HttpClient) {
                HttpClient httpClient2 = httpClient;
                execute = ApacheClientInstrumentation.execute(httpClient, httpGet);
            } else {
                execute = httpClient.execute(httpGet);
            }
            i = execute.getStatusLine().getStatusCode();
            LogUtil.c("AppPullChangeLogGetXml", "getXMLStreamFromServerExecute statusCode = ", Integer.valueOf(i));
            if (outputStream != null) {
                execute.getEntity().writeTo(outputStream);
            }
        } catch (IOException unused) {
            LogUtil.b("AppPullChangeLogGetXml", "getXmlStreamFromServerExecute IOException");
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static kyl.c a(java.io.InputStream r5, java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            java.lang.String r1 = "AppPullChangeLogGetXml"
            if (r5 != 0) goto Lf
            java.lang.String r5 = "buildChangeLogXml inputStream is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
            return r0
        Lf:
            java.lang.String r2 = "buildChangeLogXml begin"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r2)
            r2 = 0
            org.xmlpull.v1.XmlPullParserFactory r3 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            org.xmlpull.v1.XmlPullParser r3 = r3.newPullParser()     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            if (r3 != 0) goto L2e
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            java.lang.String r6 = "buildChangeLogXml parser exception,pullParser is null"
            r5[r2] = r6     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            return r0
        L2e:
            java.lang.String r4 = "UTF-8"
            r3.setInput(r5, r4)     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            kyl$b r5 = new kyl$b     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            r5.<init>(r3, r6, r7)     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            kyl$a r5 = kyl.b.d(r5)     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            kyl$c r6 = r5.d     // Catch: java.lang.Exception -> L41 org.xmlpull.v1.XmlPullParserException -> L4c
            boolean r2 = r5.c     // Catch: java.lang.Exception -> L42 org.xmlpull.v1.XmlPullParserException -> L4d
            goto L56
        L41:
            r6 = r0
        L42:
            java.lang.String r5 = "buildChangeLogXml, Exception"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
            goto L56
        L4c:
            r6 = r0
        L4d:
            java.lang.String r5 = "buildChangeLogXml, XmlPullParserException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
        L56:
            if (r2 != 0) goto L62
            java.lang.String r5 = "buildChangeLogXml, fail"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
            return r0
        L62:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kyl.a(java.io.InputStream, java.lang.String, java.lang.String):kyl$c");
    }

    static class b {
        private String k;
        private String l;
        private XmlPullParser n;
        private String o;
        private c b = null;
        private String d = null;
        private String e = null;
        private boolean h = false;

        /* renamed from: a, reason: collision with root package name */
        private int f14699a = -1;
        private c.e j = null;
        private List<c.e> c = null;
        private c.C0322c f = null;
        private List<String> i = null;
        private String g = "";
        private c.a q = null;
        private List<String> s = null;
        private String t = "";
        private a m = new a();

        b(XmlPullParser xmlPullParser, String str, String str2) {
            this.n = xmlPullParser;
            this.l = str;
            this.k = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public a b() throws XmlPullParserException {
            int eventType = this.n.getEventType();
            this.m.d = null;
            this.m.c = false;
            while (eventType != 1) {
                try {
                    this.o = this.n.getName();
                    LogUtil.c("AppPullChangeLogGetXml", "parse eventType = ", Integer.valueOf(eventType), ", nodeName = ", this.o);
                    if (eventType == 2) {
                        k();
                    } else if (eventType == 3) {
                        g();
                    }
                    eventType = this.n.next();
                } catch (IOException unused) {
                    LogUtil.b("AppPullChangeLogGetXml", "ChangeLogParser.parse, IOException");
                } catch (XmlPullParserException unused2) {
                    LogUtil.b("AppPullChangeLogGetXml", "ChangeLogParser.parse, XmlPullParserException");
                }
            }
            this.m.d = this.b;
            return this.m;
        }

        private void k() throws XmlPullParserException, IOException {
            if ("cleardata-flag".equalsIgnoreCase(this.o)) {
                this.n.next();
                boolean equalsIgnoreCase = "true".equalsIgnoreCase(this.n.getText());
                this.h = equalsIgnoreCase;
                LogUtil.c("AppPullChangeLogGetXml", "parseStartTag cleardata-flag start,cleardata-flag=", Boolean.valueOf(equalsIgnoreCase));
                return;
            }
            if ("default-language".equalsIgnoreCase(this.o)) {
                h();
                try {
                    this.f14699a = Integer.parseInt(this.n.nextText());
                } catch (NumberFormatException unused) {
                    LogUtil.b("AppPullChangeLogGetXml", "parseStartTag NumberFormatException");
                }
                LogUtil.c("AppPullChangeLogGetXml", "parseStartTag default-language,defaultLanguageCode=", Integer.valueOf(this.f14699a));
                return;
            }
            if ("language".equalsIgnoreCase(this.o)) {
                LogUtil.c("AppPullChangeLogGetXml", "parseStartTag language");
                m();
                return;
            }
            if ("features".equalsIgnoreCase(this.o)) {
                this.i = new ArrayList();
                this.g = "";
                i();
            } else {
                if (TrackConstants$Events.FEATURE.equalsIgnoreCase(this.o)) {
                    d();
                    return;
                }
                if ("updatelogs".equals(this.o)) {
                    this.s = new ArrayList();
                    this.t = "";
                    o();
                } else if ("updateLog".equals(this.o)) {
                    c();
                } else {
                    l();
                }
            }
        }

        private void o() {
            c.a aVar;
            if (this.b != null) {
                this.q = new c.a();
            }
            if (this.n.getAttributeCount() > 0 && "module".equals(this.n.getAttributeName(0))) {
                if (this.j == null || (aVar = this.q) == null) {
                    return;
                }
                aVar.e = this.n.getAttributeValue(0);
                return;
            }
            c.a aVar2 = this.q;
            if (aVar2 != null) {
                aVar2.e = "";
            }
        }

        private void c() {
            try {
                if (this.j == null || this.n == null) {
                    return;
                }
                this.t += this.n.nextText() + System.lineSeparator();
            } catch (Exception unused) {
                LogUtil.b("AppPullChangeLogGetXml", "appendUpdateLogString Exception");
            }
        }

        private void i() {
            c.C0322c c0322c;
            if (this.b != null) {
                this.f = new c.C0322c();
            }
            if (this.n.getAttributeCount() > 0 && "module".equals(this.n.getAttributeName(0))) {
                if (this.j == null || (c0322c = this.f) == null) {
                    return;
                }
                c0322c.b = this.n.getAttributeValue(0);
                return;
            }
            c.C0322c c0322c2 = this.f;
            if (c0322c2 != null) {
                c0322c2.b = "";
            }
        }

        private void m() {
            if (this.b != null) {
                c.e eVar = new c.e();
                this.j = eVar;
                eVar.f14701a = new ArrayList();
                this.j.d = new ArrayList();
            }
            for (int i = 0; i < this.n.getAttributeCount(); i++) {
                String attributeName = this.n.getAttributeName(i);
                if ("name".equals(attributeName)) {
                    c.e eVar2 = this.j;
                    if (eVar2 != null) {
                        eVar2.c = this.n.getAttributeValue(i);
                    }
                } else if ("code".equals(attributeName)) {
                    c.e eVar3 = this.j;
                    if (eVar3 != null) {
                        try {
                            eVar3.b = Integer.parseInt(this.n.getAttributeValue(i));
                        } catch (NumberFormatException unused) {
                            LogUtil.b("AppPullChangeLogGetXml", "parseStartLanguageTag NumberFormatException");
                        }
                    }
                } else {
                    LogUtil.a("AppPullChangeLogGetXml", "parseStartLanguageTag attributeName = ", attributeName);
                }
            }
        }

        private void g() {
            if ("root".equalsIgnoreCase(this.o)) {
                j();
                this.m.c = true;
            } else if ("language".equalsIgnoreCase(this.o)) {
                f();
            } else {
                a();
            }
        }

        private void a() {
            if ("features".equalsIgnoreCase(this.o)) {
                e();
                return;
            }
            if ("updatelogs".equalsIgnoreCase(this.o)) {
                n();
                return;
            }
            if ("cleardata-flag".equalsIgnoreCase(this.o)) {
                c cVar = this.b;
                if (cVar != null) {
                    cVar.f14700a = this.h;
                    LogUtil.c("AppPullChangeLogGetXml", "parseChangeLogTag cleardata-flag end,changeLog.isClearData=", Boolean.valueOf(this.b.f14700a));
                    return;
                }
                return;
            }
            LogUtil.c("AppPullChangeLogGetXml", "parseChangeLogTag unknown node");
        }

        private void n() {
            c.e eVar = this.j;
            if (eVar != null) {
                if (eVar.d != null) {
                    this.j.d.add(this.q);
                }
                List<String> list = this.s;
                if (list != null) {
                    list.add(this.t);
                }
                c.a aVar = this.q;
                if (aVar != null) {
                    aVar.d = this.s;
                }
            }
        }

        private void e() {
            c.e eVar = this.j;
            if (eVar != null) {
                if (eVar.f14701a != null) {
                    this.j.f14701a.add(this.f);
                }
                List<String> list = this.i;
                if (list != null) {
                    list.add(this.g);
                }
                c.C0322c c0322c = this.f;
                if (c0322c != null) {
                    c0322c.e = this.i;
                }
            }
        }

        private void l() {
            if ("root".equalsIgnoreCase(this.o)) {
                LogUtil.c("AppPullChangeLogGetXml", "parseStartRootTag");
                this.b = new c();
            }
        }

        private void j() {
            c cVar = this.b;
            if (cVar != null) {
                cVar.d = this.c;
            }
        }

        private void h() {
            this.d = "";
            this.e = "";
            for (int i = 0; i < this.n.getAttributeCount(); i++) {
                if ("name".equals(this.n.getAttributeName(i))) {
                    this.d = this.n.getAttributeValue(i);
                }
                if ("status".equals(this.n.getAttributeName(i))) {
                    this.e = this.n.getAttributeValue(i);
                }
            }
            LogUtil.c("AppPullChangeLogGetXml", "parseDefaultLanguageTag mDefaultLanguageName ", this.d, " ,mChangeLogStatus ", this.e);
        }

        private void d() {
            try {
                if (this.j == null || this.n == null) {
                    return;
                }
                this.g += this.n.nextText() + System.lineSeparator();
            } catch (Exception unused) {
                LogUtil.b("AppPullChangeLogGetXml", "appendFeatureString, Exception");
            }
        }

        private void f() {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            c.e eVar = this.j;
            if (eVar == null) {
                return;
            }
            this.c.add(eVar);
            if (this.j.c == null || this.b == null) {
                return;
            }
            if (this.j.c.equalsIgnoreCase(this.d) && this.j.b == this.f14699a) {
                this.b.b = this.c.size() - 1;
            }
            if (!TextUtils.isEmpty(this.e) && "new".equals(this.e)) {
                if (this.j.c.equalsIgnoreCase(this.k)) {
                    this.b.e = this.c.size() - 1;
                    return;
                }
                return;
            }
            if (this.j.c.equalsIgnoreCase(this.l)) {
                this.b.e = this.c.size() - 1;
            }
        }
    }

    static class a {
        boolean c;
        c d;

        private a() {
        }
    }
}
