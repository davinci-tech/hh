package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.jt;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.maps.AMapException;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ConnectTimeoutException;

/* loaded from: classes2.dex */
public final class jx {
    private static SoftReference<SSLContext> k;
    private static SoftReference<jy> t;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1237a;
    private SSLContext b;
    private Proxy c;
    private String g;
    private jt.a h;
    private d i;
    private boolean l;
    private String m;
    private String n;
    private volatile boolean d = false;
    private long e = -1;
    private long f = 0;
    private String j = "";
    private boolean o = false;
    private boolean p = false;
    private String q = "";
    private String r = "";
    private String s = "";
    private f u = new f();

    public static void b() {
    }

    private void d(ka kaVar) throws hm {
        this.i = new d((byte) 0);
        this.p = kaVar.isIPV6Request();
        this.c = kaVar.getProxy();
        this.h = kaVar.getUrlConnectionImpl();
        this.l = kaVar.isBinary();
        this.j = kaVar.parseSdkNameFromRequest();
        this.f1237a = ht.a().b(kaVar.isHttps());
        String b2 = kaVar.getDegradeType().b() ? kaVar.b() : kaVar.a();
        this.m = b2;
        this.m = jw.a(b2, this.j);
        this.n = kaVar.getIPDNSName();
        if ("loc".equals(this.j)) {
            String a2 = kaVar.a();
            String b3 = kaVar.b();
            if (!TextUtils.isEmpty(a2)) {
                try {
                    this.r = new URL(a2).getHost();
                } catch (Exception unused) {
                }
            }
            if (TextUtils.isEmpty(b3)) {
                return;
            }
            try {
                if (!TextUtils.isEmpty(this.n)) {
                    this.q = this.n;
                } else {
                    this.q = new URL(b3).getHost();
                }
            } catch (Exception unused2) {
            }
        }
    }

    jx() {
        ho.e();
        try {
            this.g = UUID.randomUUID().toString().replaceAll(Constants.LINK, "").toLowerCase();
        } catch (Throwable th) {
            is.a(th, "ht", "ic");
        }
    }

    final void a() {
        this.d = true;
    }

    final void a(long j) {
        this.f = j;
    }

    final void b(long j) {
        this.e = j;
    }

    private static String a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (jt.e != null) {
            if (map != null) {
                map.putAll(jt.e);
            } else {
                map = jt.e;
            }
        }
        if (map == null || map.size() <= 0) {
            return str;
        }
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            HashMap hashMap = new HashMap();
            String substring = str.substring(indexOf);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (!substring.matches(".*[\\?\\&]" + URLEncoder.encode(key) + "=.*")) {
                    hashMap.put(key, value);
                }
            }
            map = hashMap;
        }
        if (map.size() == 0) {
            return str;
        }
        String a2 = a(map);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (indexOf >= 0) {
            if (!str.endsWith("?") && !str.endsWith("&")) {
                stringBuffer.append("&");
            }
        } else {
            stringBuffer.append("?");
        }
        if (a2 != null) {
            stringBuffer.append(a2);
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:6|7|8|(3:507|508|(15:510|11|13|14|(4:457|458|459|460)(1:16)|17|18|19|(1:21)(1:434)|22|(1:24)|25|(3:27|(2:38|39)|(2:30|31))(12:310|311|312|313|(6:320|321|(4:323|324|325|(1:327)(1:334))(1:405)|(2:329|330)(2:332|333)|331|314)|407|335|(1:337)(1:360)|338|(2:352|353)|(2:347|348)|(2:342|343))|36|37))|10|11|13|14|(0)(0)|17|18|19|(0)(0)|22|(0)|25|(0)(0)|36|37) */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x02dc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x02dd, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x02ee, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x02ef, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x032d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x032e, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0312, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0313, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0300, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x0301, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x02f7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x02f8, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0309, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x030a, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x0324, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x0325, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x031b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x031c, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x02e5, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:454:0x02e6, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:455:0x02d3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x02d4, code lost:
    
        r16 = r7;
        r7 = null;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x033b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x033c, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x0347, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x0348, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x0371, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x0372, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x035f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x0360, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:493:0x0353, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x0354, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x034d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x034e, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0359, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x035a, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x036b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x036c, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x0365, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x0366, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:0x0341, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x0342, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0336, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0337, code lost:
    
        r7 = null;
        r5 = r0;
        r8 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x044d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0442 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0431 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x05f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x05e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x05d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0534 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0529 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0518 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x04c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x04be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x04ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x04f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x04e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x05b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x05ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x059a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0575 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x056a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0559 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0411 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0406 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:279:0x03f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0148 A[Catch: all -> 0x00cc, hm -> 0x00d0, IOException -> 0x00d4, InterruptedIOException -> 0x00d8, SocketTimeoutException -> 0x00dc, SocketException -> 0x00e0, UnknownHostException -> 0x00e4, MalformedURLException -> 0x00e8, ConnectTimeoutException -> 0x00ec, SSLException -> 0x00f0, ConnectException -> 0x00f4, TRY_ENTER, TRY_LEAVE, TryCatch #52 {hm -> 0x00d0, SocketTimeoutException -> 0x00dc, InterruptedIOException -> 0x00d8, ConnectException -> 0x00f4, MalformedURLException -> 0x00e8, SocketException -> 0x00e0, UnknownHostException -> 0x00e4, SSLException -> 0x00f0, ConnectTimeoutException -> 0x00ec, IOException -> 0x00d4, all -> 0x00cc, blocks: (B:460:0x00bc, B:27:0x0148), top: B:459:0x00bc }] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0399 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0388 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x01c0 A[Catch: all -> 0x02d3, hm -> 0x02dc, IOException -> 0x02e5, InterruptedIOException -> 0x02ee, SocketTimeoutException -> 0x02f7, SocketException -> 0x0300, UnknownHostException -> 0x0309, MalformedURLException -> 0x0312, ConnectTimeoutException -> 0x031b, SSLException -> 0x0324, ConnectException -> 0x032d, TRY_ENTER, TRY_LEAVE, TryCatch #53 {hm -> 0x02dc, InterruptedIOException -> 0x02ee, ConnectException -> 0x032d, MalformedURLException -> 0x0312, SocketException -> 0x0300, SocketTimeoutException -> 0x02f7, UnknownHostException -> 0x0309, SSLException -> 0x0324, ConnectTimeoutException -> 0x031b, IOException -> 0x02e5, all -> 0x02d3, blocks: (B:18:0x0130, B:310:0x01c0), top: B:17:0x0130 }] */
    /* JADX WARN: Removed duplicated region for block: B:434:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x048b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0480 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x046f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void a(com.amap.api.col.p0003sl.ka r19, com.amap.api.col.3sl.jv.a r20) {
        /*
            Method dump skipped, instructions count: 1580
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jx.a(com.amap.api.col.3sl.ka, com.amap.api.col.3sl.jv$a):void");
    }

    final Map<String, String> a(ka kaVar) throws hm {
        Throwable th;
        ConnectTimeoutException e2;
        SSLException e3;
        SocketException e4;
        SocketTimeoutException e5;
        ConnectException e6;
        hm e7;
        int i;
        HttpURLConnection httpURLConnection;
        String headerFieldKey;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                d(kaVar);
                this.m = a(this.m, kaVar.getParams());
                httpURLConnection = a(kaVar, false, false).f1239a;
            } catch (Throwable th2) {
                if (0 != 0) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Throwable th3) {
                        is.a(th3, "hth", "mgr");
                    }
                }
                this.u.d();
                throw th2;
            }
        } catch (hm e8) {
            e7 = e8;
        } catch (ConnectException e9) {
            e6 = e9;
        } catch (SocketTimeoutException e10) {
            e5 = e10;
        } catch (InterruptedIOException unused) {
        } catch (MalformedURLException unused2) {
        } catch (SocketException e11) {
            e4 = e11;
        } catch (UnknownHostException unused3) {
        } catch (SSLException e12) {
            e3 = e12;
        } catch (ConnectTimeoutException e13) {
            e2 = e13;
        } catch (IOException unused4) {
        } catch (Throwable th4) {
            th = th4;
        }
        try {
            this.u.b = SystemClock.elapsedRealtime();
            httpURLConnection.connect();
            this.u.a();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 400) {
                this.u.b(responseCode);
                this.u.a(10);
                hm hmVar = new hm("http读取header失败");
                hmVar.a(responseCode);
                throw hmVar;
            }
            HashMap hashMap = new HashMap();
            for (i = 0; i < 50 && (headerFieldKey = httpURLConnection.getHeaderFieldKey(i)) != null; i++) {
                hashMap.put(headerFieldKey.toLowerCase(), httpURLConnection.getHeaderField(headerFieldKey));
            }
            this.u.a((kb) null);
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th5) {
                    is.a(th5, "hth", "mgr");
                }
            }
            this.u.d();
            return hashMap;
        } catch (hm e14) {
            e7 = e14;
            this.u.a(e7.g());
            throw e7;
        } catch (ConnectException e15) {
            e6 = e15;
            this.u.b(a(e6));
            this.u.a(6);
            throw new hm(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException unused5) {
            this.u.a(8);
            throw new hm("url异常 - MalformedURLException");
        } catch (ConnectTimeoutException e16) {
            e2 = e16;
            e2.printStackTrace();
            this.u.b(a(e2));
            this.u.a(2);
            throw new hm("IO 操作异常 - IOException");
        } catch (InterruptedIOException unused6) {
            this.u.b(7101);
            this.u.a(7);
            throw new hm(AMapException.ERROR_UNKNOWN);
        } catch (SocketException e17) {
            e4 = e17;
            this.u.b(a(e4));
            this.u.a(6);
            throw new hm(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e18) {
            e5 = e18;
            this.u.b(a(e5));
            this.u.a(2);
            throw new hm("socket 连接超时 - SocketTimeoutException");
        } catch (UnknownHostException unused7) {
            this.u.a(9);
            throw new hm("未知主机 - UnKnowHostException");
        } catch (SSLException e19) {
            e3 = e19;
            e3.printStackTrace();
            this.u.b(a(e3));
            this.u.a(4);
            throw new hm("IO 操作异常 - IOException");
        } catch (IOException unused8) {
            this.u.a(7);
            throw new hm("IO 操作异常 - IOException");
        } catch (Throwable th6) {
            th = th6;
            this.u.a(9);
            th.printStackTrace();
            throw new hm(AMapException.ERROR_UNKNOWN);
        }
    }

    final kb b(ka kaVar) throws hm {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    d(kaVar);
                                    String a2 = a(this.m, kaVar.getParams());
                                    this.m = a2;
                                    kb b2 = jw.b(a2, this.j);
                                    if (b2 == null) {
                                        b a3 = a(kaVar, false, true);
                                        httpURLConnection = a3.f1239a;
                                        this.u.b = SystemClock.elapsedRealtime();
                                        httpURLConnection.connect();
                                        this.u.a();
                                        kb a4 = a(a3, kaVar.isIgnoreGZip());
                                        this.u.a(a4);
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                            } catch (Throwable th) {
                                                is.a(th, "ht", "mgr");
                                            }
                                        }
                                        this.u.d();
                                        return a4;
                                    }
                                    this.u.d();
                                    return b2;
                                } catch (ConnectException e2) {
                                    this.u.b(a(e2));
                                    this.u.a(6);
                                    throw new hm(AMapException.ERROR_CONNECTION);
                                }
                            } catch (SSLException e3) {
                                e3.printStackTrace();
                                this.u.b(a(e3));
                                this.u.a(4);
                                throw new hm("IO 操作异常 - IOException");
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                                this.u.a(9);
                                throw new hm(AMapException.ERROR_UNKNOWN);
                            }
                        } catch (MalformedURLException unused) {
                            this.u.a(8);
                            throw new hm("url异常 - MalformedURLException");
                        } catch (UnknownHostException unused2) {
                            this.u.a(9);
                            throw new hm("未知主机 - UnKnowHostException");
                        }
                    } catch (hm e4) {
                        if (!e4.i() && e4.g() != 10) {
                            this.u.a(e4.f());
                        }
                        throw e4;
                    } catch (SocketTimeoutException e5) {
                        this.u.b(a(e5));
                        this.u.a(2);
                        throw new hm("socket 连接超时 - SocketTimeoutException");
                    }
                } catch (ConnectTimeoutException e6) {
                    e6.printStackTrace();
                    this.u.b(a(e6));
                    this.u.a(2);
                    throw new hm("IO 操作异常 - IOException");
                } catch (IOException unused3) {
                    this.u.a(7);
                    throw new hm("IO 操作异常 - IOException");
                }
            } catch (InterruptedIOException unused4) {
                this.u.b(7101);
                this.u.a(7);
                throw new hm(AMapException.ERROR_UNKNOWN);
            } catch (SocketException e7) {
                this.u.b(a(e7));
                this.u.a(6);
                throw new hm(AMapException.ERROR_SOCKET);
            }
        } catch (Throwable th3) {
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th4) {
                    is.a(th4, "ht", "mgr");
                }
            }
            this.u.d();
            throw th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v17 */
    final kb c(ka kaVar) throws hm {
        OutputStream outputStream;
        ?? r7 = 0;
        r7 = 0;
        try {
            try {
                d(kaVar);
                kb b2 = jw.b(this.m, this.j);
                if (b2 == null) {
                    b a2 = a(kaVar, true, true);
                    HttpURLConnection httpURLConnection = a2.f1239a;
                    try {
                        this.u.b = SystemClock.elapsedRealtime();
                        httpURLConnection.connect();
                        this.u.a();
                        byte[] entityBytes = kaVar.getEntityBytes();
                        if (entityBytes == null || entityBytes.length == 0) {
                            Map<String, String> params = kaVar.getParams();
                            if (jt.e != null) {
                                if (params != null) {
                                    params.putAll(jt.e);
                                } else {
                                    params = jt.e;
                                }
                            }
                            String a3 = a(params);
                            if (!TextUtils.isEmpty(a3)) {
                                entityBytes = ia.a(a3);
                            }
                        }
                        if (entityBytes != null && entityBytes.length > 0) {
                            try {
                                this.u.b = SystemClock.elapsedRealtime();
                                outputStream = httpURLConnection.getOutputStream();
                                try {
                                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                                    try {
                                        dataOutputStream.write(entityBytes);
                                        dataOutputStream.close();
                                        if (outputStream != null) {
                                            outputStream.close();
                                        }
                                        this.u.b();
                                    } catch (Throwable th) {
                                        th = th;
                                        r7 = dataOutputStream;
                                        if (r7 != 0) {
                                            r7.close();
                                        }
                                        if (outputStream != null) {
                                            outputStream.close();
                                        }
                                        this.u.b();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                outputStream = null;
                            }
                        }
                        kb a4 = a(a2, kaVar.isIgnoreGZip());
                        this.u.a(a4);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Throwable th4) {
                                is.a(th4, "ht", "mPt");
                            }
                        }
                        this.u.d();
                        return a4;
                    } catch (hm e2) {
                        e = e2;
                        if (!e.i() && e.g() != 10) {
                            this.u.a(e.g());
                        }
                        is.a(e, "ht", "mPt");
                        throw e;
                    } catch (ConnectTimeoutException e3) {
                        e = e3;
                        e.printStackTrace();
                        this.u.b(a(e));
                        this.u.a(2);
                        throw new hm("IO 操作异常 - IOException");
                    } catch (InterruptedIOException unused) {
                        this.u.b(7101);
                        this.u.a(7);
                        throw new hm(AMapException.ERROR_UNKNOWN);
                    } catch (ConnectException e4) {
                        e = e4;
                        e.printStackTrace();
                        this.u.b(a(e));
                        this.u.a(6);
                        throw new hm(AMapException.ERROR_CONNECTION);
                    } catch (MalformedURLException e5) {
                        e = e5;
                        e.printStackTrace();
                        this.u.a(8);
                        throw new hm("url异常 - MalformedURLException");
                    } catch (SocketException e6) {
                        e = e6;
                        e.printStackTrace();
                        this.u.b(a(e));
                        this.u.a(6);
                        throw new hm(AMapException.ERROR_SOCKET);
                    } catch (SocketTimeoutException e7) {
                        e = e7;
                        e.printStackTrace();
                        this.u.b(a(e));
                        this.u.a(2);
                        throw new hm("socket 连接超时 - SocketTimeoutException");
                    } catch (UnknownHostException e8) {
                        e = e8;
                        e.printStackTrace();
                        this.u.a(5);
                        throw new hm("未知主机 - UnKnowHostException");
                    } catch (SSLException e9) {
                        e = e9;
                        e.printStackTrace();
                        this.u.b(a(e));
                        this.u.a(4);
                        throw new hm("IO 操作异常 - IOException");
                    } catch (IOException e10) {
                        e = e10;
                        e.printStackTrace();
                        this.u.a(7);
                        throw new hm("IO 操作异常 - IOException");
                    } catch (Throwable th5) {
                        th = th5;
                        is.a(th, "ht", "mPt");
                        this.u.a(9);
                        throw new hm(AMapException.ERROR_UNKNOWN);
                    }
                }
                this.u.d();
                return b2;
            } catch (Throwable th6) {
                if (0 != 0) {
                    try {
                        r7.disconnect();
                    } catch (Throwable th7) {
                        is.a(th7, "ht", "mPt");
                    }
                }
                this.u.d();
                throw th6;
            }
        } catch (hm e11) {
            e = e11;
        } catch (ConnectException e12) {
            e = e12;
        } catch (MalformedURLException e13) {
            e = e13;
        } catch (SocketTimeoutException e14) {
            e = e14;
        } catch (InterruptedIOException unused2) {
        } catch (SocketException e15) {
            e = e15;
        } catch (UnknownHostException e16) {
            e = e16;
        } catch (SSLException e17) {
            e = e17;
        } catch (ConnectTimeoutException e18) {
            e = e18;
        } catch (IOException e19) {
            e = e19;
        } catch (Throwable th8) {
            th = th8;
        }
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public HttpURLConnection f1239a;
        public int b = this.b;
        public int b = this.b;

        public b(HttpURLConnection httpURLConnection) {
            this.f1239a = httpURLConnection;
        }
    }

    private jy c() {
        try {
            SoftReference<jy> softReference = t;
            if (softReference == null || softReference.get() == null) {
                t = new SoftReference<>(new jy(ho.c, this.b));
            }
            jy jyVar = k != null ? t.get() : null;
            return jyVar == null ? new jy(ho.c, this.b) : jyVar;
        } catch (Throwable th) {
            iv.c(th, "ht", "gsf");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0212 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0142 A[Catch: all -> 0x01af, TryCatch #2 {all -> 0x01af, blocks: (B:31:0x00cd, B:34:0x00e5, B:36:0x00e8, B:38:0x00ec, B:40:0x00f2, B:43:0x00fb, B:46:0x0107, B:48:0x010a, B:52:0x0110, B:53:0x013c, B:55:0x0142, B:57:0x014c, B:58:0x015d, B:60:0x0185, B:62:0x01a6, B:63:0x01a9, B:50:0x0126, B:68:0x012a, B:70:0x012d, B:74:0x0133, B:72:0x0138), top: B:30:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0185 A[Catch: all -> 0x01af, TryCatch #2 {all -> 0x01af, blocks: (B:31:0x00cd, B:34:0x00e5, B:36:0x00e8, B:38:0x00ec, B:40:0x00f2, B:43:0x00fb, B:46:0x0107, B:48:0x010a, B:52:0x0110, B:53:0x013c, B:55:0x0142, B:57:0x014c, B:58:0x015d, B:60:0x0185, B:62:0x01a6, B:63:0x01a9, B:50:0x0126, B:68:0x012a, B:70:0x012d, B:74:0x0133, B:72:0x0138), top: B:30:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0260  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amap.api.col.3sl.jx.b a(com.amap.api.col.p0003sl.ka r17, boolean r18, boolean r19) throws java.io.IOException, com.amap.api.col.p0003sl.hm {
        /*
            Method dump skipped, instructions count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jx.a(com.amap.api.col.3sl.ka, boolean, boolean):com.amap.api.col.3sl.jx$b");
    }

    private static String a(HttpURLConnection httpURLConnection) {
        List<String> list;
        if (httpURLConnection == null) {
            return "";
        }
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            if (headerFields != null && (list = headerFields.get("gsid")) != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0238 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x022d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0222 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0217 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x017e A[Catch: all -> 0x01cb, IOException -> 0x01d1, SocketTimeoutException -> 0x0201, ConnectTimeoutException -> 0x0206, TRY_ENTER, TryCatch #19 {SocketTimeoutException -> 0x0201, ConnectTimeoutException -> 0x0206, IOException -> 0x01d1, all -> 0x01cb, blocks: (B:3:0x0009, B:5:0x001d, B:7:0x0027, B:9:0x002d, B:10:0x0034, B:32:0x00a6, B:151:0x017e, B:152:0x01ca), top: B:2:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a6 A[Catch: all -> 0x01cb, IOException -> 0x01d1, SocketTimeoutException -> 0x0201, ConnectTimeoutException -> 0x0206, TRY_ENTER, TRY_LEAVE, TryCatch #19 {SocketTimeoutException -> 0x0201, ConnectTimeoutException -> 0x0206, IOException -> 0x01d1, all -> 0x01cb, blocks: (B:3:0x0009, B:5:0x001d, B:7:0x0027, B:9:0x002d, B:10:0x0034, B:32:0x00a6, B:151:0x017e, B:152:0x01ca), top: B:2:0x0009 }] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amap.api.col.p0003sl.kb a(com.amap.api.col.3sl.jx.b r18, boolean r19) throws com.amap.api.col.p0003sl.hm, java.io.IOException {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jx.a(com.amap.api.col.3sl.jx$b, boolean):com.amap.api.col.3sl.kb");
    }

    private static String b(Map<String, List<String>> map) {
        try {
            List<String> list = map.get("sc");
            if (list != null && list.size() > 0) {
                String str = list.get(0);
                if (!TextUtils.isEmpty(str)) {
                    if (!str.contains("#")) {
                        return str;
                    }
                    String[] split = str.split("#");
                    if (split.length > 1) {
                        return split[0];
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    private boolean a(Map<String, List<String>> map, boolean z) {
        List<String> list;
        try {
            List<String> list2 = map.get("sc");
            if (list2 == null || list2.size() <= 0) {
                return false;
            }
            String str = list2.get(0);
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (str.contains("#")) {
                String[] split = str.split("#");
                if (split.length <= 1 || !"1".equals(split[1])) {
                    return false;
                }
            }
            if (!z) {
                return true;
            }
            if (!map.containsKey("lct") || (list = map.get("lct")) == null || list.size() <= 0) {
                return false;
            }
            String str2 = list.get(0);
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            return ho.a(this.j, Long.valueOf(str2).longValue());
        } catch (Throwable unused) {
            return false;
        }
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection, boolean z) {
        String str;
        c g;
        if (map != null) {
            try {
                for (String str2 : map.keySet()) {
                    httpURLConnection.addRequestProperty(str2, map.get(str2));
                }
            } catch (Throwable th) {
                is.a(th, "ht", "adh");
                return;
            }
        }
        if (jt.d != null) {
            for (String str3 : jt.d.keySet()) {
                httpURLConnection.addRequestProperty(str3, jt.d.get(str3));
            }
        }
        if (z && !this.m.contains("/v3/iasdkauth") && !TextUtils.isEmpty(this.j) && ho.a(this.j)) {
            this.o = true;
            httpURLConnection.addRequestProperty("lct", String.valueOf(ho.c(this.j)));
        }
        httpURLConnection.addRequestProperty("csid", this.g);
        if (a(this.u.c.e)) {
            f fVar = this.u;
            if (TextUtils.isEmpty(fVar.c.c)) {
                str = "";
            } else {
                String b2 = hs.b(jp.a(fVar.c.c.getBytes(), "YXBtX25ldHdvcmtf".getBytes()));
                String str4 = fVar.c.c;
                str = b2;
            }
            if (!TextUtils.isEmpty(str)) {
                httpURLConnection.addRequestProperty("sip", str);
            }
            if (ho.j && (g = ho.g()) != null) {
                httpURLConnection.addRequestProperty("nls", g.b());
                this.u.e = g;
            }
            a f2 = ho.f();
            if (f2 != null) {
                httpURLConnection.addRequestProperty("nlf", f2.b());
                this.u.d = f2;
            }
        }
    }

    static final class e implements HostnameVerifier {

        /* renamed from: a, reason: collision with root package name */
        private String f1242a;
        private String b;

        private e() {
        }

        /* synthetic */ e(byte b) {
            this();
        }

        public final void a(String str) {
            String[] split;
            if (!TextUtils.isEmpty(this.f1242a) && str.contains(":") && (split = str.split(":")) != null && split.length > 0) {
                this.f1242a = split[0];
            } else {
                this.f1242a = str;
            }
        }

        public final void b(String str) {
            this.b = str;
        }

        public final String a() {
            return this.b;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!TextUtils.isEmpty(this.f1242a)) {
                return this.f1242a.equals(str);
            }
            if (!TextUtils.isEmpty(this.b)) {
                return defaultHostnameVerifier.verify(this.b, sSLSession);
            }
            return defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Vector<e> f1241a;
        private volatile e b;

        private d() {
            this.f1241a = new Vector<>();
            this.b = new e((byte) 0);
        }

        /* synthetic */ d(byte b) {
            this();
        }

        public final e a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.b;
            }
            byte b = 0;
            for (int i = 0; i < this.f1241a.size(); i++) {
                e eVar = this.f1241a.get(i);
                if (eVar != null && eVar.a().equals(str)) {
                    return eVar;
                }
            }
            e eVar2 = new e(b);
            eVar2.b(str);
            this.f1241a.add(eVar2);
            return eVar2;
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(key));
            sb.append("=");
            sb.append(URLEncoder.encode(value));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if (this.l) {
            return true;
        }
        return (!TextUtils.isEmpty(this.n) && (this.n.contains("rest") || this.n.contains("apilocate"))) || b(str);
    }

    private static boolean b(String str) {
        return str.contains("rest") || str.contains("apilocate");
    }

    private static int a(Exception exc) {
        if (exc instanceof SSLHandshakeException) {
            return 4101;
        }
        if (exc instanceof SSLKeyException) {
            return 4102;
        }
        if (exc instanceof SSLProtocolException) {
            return 4103;
        }
        if (exc instanceof SSLPeerUnverifiedException) {
            return 4104;
        }
        if (exc instanceof ConnectException) {
            return 6101;
        }
        if (exc instanceof SocketException) {
            return 6102;
        }
        if (exc instanceof ConnectTimeoutException) {
            return 2101;
        }
        return exc instanceof SocketTimeoutException ? 2102 : 0;
    }

    final class f {

        /* renamed from: a, reason: collision with root package name */
        long f1243a = 0;
        long b = 0;
        c c = new c();
        a d;
        c e;
        String f;
        URL g;

        f() {
        }

        public final void a(ka kaVar, URL url) {
            this.g = url;
            this.c.d = url.getPath();
            this.c.e = url.getHost();
            if (!TextUtils.isEmpty(jx.this.n) && kaVar.getDegradeType().b()) {
                c cVar = this.c;
                cVar.c = cVar.e.replace("[", "").replace("]", "");
                this.c.e = jx.this.n;
            }
            if (kaVar.getDegradeType().b()) {
                kaVar.setNon_degrade_final_Host(this.c.e);
            }
            if (kaVar.getDegradeType().d()) {
                this.f = kaVar.getNon_degrade_final_Host();
            }
        }

        public final void a() {
            this.c.h = SystemClock.elapsedRealtime() - this.b;
        }

        public final void b() {
            this.c.i = SystemClock.elapsedRealtime() - this.b;
        }

        public final void c() {
            this.c.j = SystemClock.elapsedRealtime() - this.b;
        }

        public final void a(kb kbVar) {
            c clone;
            try {
                this.c.f = SystemClock.elapsedRealtime() - this.f1243a;
                if (kbVar != null) {
                    kbVar.f = this.c.b.c();
                }
                if (this.c.b.b() && this.c.f > PreConnectManager.CONNECT_INTERNAL) {
                    ho.a(false, this.c.e);
                }
                if (this.c.b.d()) {
                    ho.a(false, this.f);
                }
                boolean a2 = jx.this.a(this.c.e);
                if (a2) {
                    ho.c(this.c);
                    ho.a(true, this.d);
                    if (this.c.f > ho.e && (clone = this.c.clone()) != null) {
                        clone.m = 1;
                        ho.b(clone);
                        clone.toString();
                        jx.b();
                    }
                }
                ho.a(this.g.toString(), this.c.b.c(), false, a2);
                this.c.toString();
                jx.b();
            } catch (Throwable unused) {
            }
        }

        public final void a(int i) {
            "----errorcode-----".concat(String.valueOf(i));
            jx.b();
            try {
                this.c.f = SystemClock.elapsedRealtime() - this.f1243a;
                this.c.m = i;
                if (this.c.b.e()) {
                    ho.a(false, this.c.e);
                }
                boolean a2 = jx.this.a(this.c.e);
                if (a2) {
                    if (jx.this.p && !TextUtils.isEmpty(jx.this.n) && this.c.b.b()) {
                        ho.d();
                    }
                    if (this.c.b.c()) {
                        ho.a(this.c.b.c(), this.c.e);
                    }
                    ho.c(this.e);
                    ho.a(false, this.d);
                    ho.b(this.c);
                }
                ho.a(this.g.toString(), this.c.b.c(), true, a2);
                this.c.toString();
                jx.b();
            } catch (Throwable unused) {
            }
        }

        public final void b(int i) {
            this.c.n = i;
        }

        public final void a(long j) {
            this.c.l = new DecimalFormat("0.00").format(j / 1024.0f);
        }

        public final void d() {
            c clone = this.c.clone();
            if (this.c.f > ho.e) {
                clone.m = 1;
            }
            ho.a(clone);
        }
    }

    public static final class c implements Cloneable {

        /* renamed from: a, reason: collision with root package name */
        public String f1240a = "";
        public ka.b b = ka.b.FIRST_NONDEGRADE;
        public String c = "";
        public String d = "";
        public String e = "";
        public long f = 0;
        public long g = 0;
        public long h = 0;
        public long i = 0;
        public long j = 0;
        public String k = Constants.LINK;
        public String l = Constants.LINK;
        public int m = 0;
        public int n = 0;

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c clone() {
            try {
                return (c) super.clone();
            } catch (CloneNotSupportedException unused) {
                return null;
            }
        }

        protected final String b() {
            String str;
            String str2;
            if (TextUtils.isEmpty(this.c)) {
                str = "-#";
            } else {
                str = this.c + "#";
            }
            if (!TextUtils.isEmpty(this.d)) {
                str2 = str + this.d + "#";
            } else {
                str2 = str + "-#";
            }
            String b = hs.b(jp.a(((((str2 + this.b.a() + "#") + this.h + "#") + this.j + "#") + this.f).getBytes(), "YXBtX25ldHdvcmtf".getBytes()));
            jx.b();
            return b;
        }

        public final String toString() {
            return "RequestInfo{csid='" + this.f1240a + "', degradeType=" + this.b + ", serverIp='" + this.c + "', path='" + this.d + "', hostname='" + this.e + "', totalTime=" + this.f + ", DNSTime=" + this.g + ", connectionTime=" + this.h + ", writeTime=" + this.i + ", readTime=" + this.j + ", serverTime='" + this.k + "', datasize='" + this.l + "', errorcode=" + this.m + ", errorcodeSub=" + this.n + '}';
        }
    }

    public static final class a implements Cloneable, Comparable {

        /* renamed from: a, reason: collision with root package name */
        public int f1238a;
        public String b;
        public String c;
        public String d;
        public String e;
        public int f;
        public int g;
        public int h;
        public long i;
        public volatile AtomicInteger j = new AtomicInteger(1);

        public a(c cVar) {
            this.b = cVar.c;
            this.c = cVar.e;
            this.e = cVar.d;
            this.f = cVar.m;
            this.g = cVar.n;
            this.h = cVar.b.a();
            this.d = cVar.f1240a;
            this.i = cVar.f;
            if (this.f == 10) {
                this.f1238a = 0;
            }
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final a clone() {
            try {
                return (a) super.clone();
            } catch (CloneNotSupportedException unused) {
                return null;
            }
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            return this.f1238a - ((a) obj).f1238a;
        }

        public final String b() {
            String str;
            String str2;
            String str3;
            String str4;
            try {
                String str5 = this.f + "#";
                if (!TextUtils.isEmpty(this.e)) {
                    str = str5 + this.e + "#";
                } else {
                    str = str5 + "-#";
                }
                String str6 = (str + this.h + "#") + this.j + "#";
                if (!TextUtils.isEmpty(this.b)) {
                    str2 = str6 + this.b + "#";
                } else {
                    str2 = str6 + "-#";
                }
                if (this.f == 1) {
                    str3 = str2 + this.d + "#";
                } else {
                    str3 = str2 + "-#";
                }
                if (this.f == 1) {
                    str4 = str3 + this.i + "#";
                } else {
                    str4 = str3 + "-#";
                }
                String b = hs.b(jp.a(((str4 + this.c + "#") + this.g).getBytes(), "YXBtX25ldHdvcmtf".getBytes()));
                jx.b();
                return b;
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
