package com.tencent.open.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.i;
import com.tencent.open.utils.l;
import com.tencent.open.utils.m;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    protected static h f11342a;
    protected HandlerThread e;
    protected Handler f;
    protected Random b = new SecureRandom();
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
    protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
    protected Executor g = l.b();
    protected Executor h = l.b();

    protected void b() {
    }

    public static h a() {
        h hVar;
        synchronized (h.class) {
            if (f11342a == null) {
                f11342a = new h();
            }
            hVar = f11342a;
        }
        return hVar;
    }

    private h() {
        this.e = null;
        if (this.e == null) {
            HandlerThread handlerThread = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e = handlerThread;
            handlerThread.start();
        }
        if (!this.e.isAlive() || this.e.getLooper() == null) {
            return;
        }
        this.f = new Handler(this.e.getLooper()) { // from class: com.tencent.open.b.h.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    h.this.b();
                } else if (i == 1001) {
                    h.this.d();
                }
                super.handleMessage(message);
            }
        };
    }

    public void a(final Bundle bundle, String str, final boolean z) {
        if (bundle == null) {
            return;
        }
        SLog.v("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
        if (a("report_via", str) || z) {
            this.g.execute(new Runnable() { // from class: com.tencent.open.b.h.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("uin", "1000");
                        bundle2.putString("platform", "1");
                        bundle2.putString("os_ver", Build.VERSION.RELEASE);
                        bundle2.putString("position", "");
                        bundle2.putString(HAWebViewInterface.NETWORK, a.a(com.tencent.open.utils.g.a()));
                        bundle2.putString("language", d.a());
                        bundle2.putString("resolution", d.a(com.tencent.open.utils.g.a()));
                        bundle2.putString("apn", a.b(com.tencent.open.utils.g.a()));
                        bundle2.putString(Constants.PARAM_MODEL_NAME, com.tencent.open.utils.f.a().c(com.tencent.open.utils.g.a()));
                        bundle2.putString(DBSessionCommon.COLUMN_TIME_ZONE, TimeZone.getDefault().getID());
                        bundle2.putString("sdk_ver", Constants.SDK_VERSION);
                        bundle2.putString("qz_ver", m.d(com.tencent.open.utils.g.a(), Constants.PACKAGE_QZONE));
                        bundle2.putString(Constants.PARAM_QQ_VER, m.c(com.tencent.open.utils.g.a(), "com.tencent.mobileqq"));
                        bundle2.putString("qua", m.e(com.tencent.open.utils.g.a(), com.tencent.open.utils.g.b()));
                        bundle2.putString("packagename", com.tencent.open.utils.g.b());
                        bundle2.putString(Constants.PARAM_APP_VER, m.d(com.tencent.open.utils.g.a(), com.tencent.open.utils.g.b()));
                        Bundle bundle3 = bundle;
                        if (bundle3 != null) {
                            bundle2.putAll(bundle3);
                        }
                        h.this.d.add(new c(bundle2));
                        int size = h.this.d.size();
                        int a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Agent_ReportTimeInterval");
                        if (a2 == 0) {
                            a2 = 10000;
                        }
                        if (!h.this.a("report_via", size) && !z) {
                            if (h.this.f.hasMessages(1001)) {
                                return;
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 1001;
                            h.this.f.sendMessageDelayed(obtain, a2);
                            return;
                        }
                        h.this.d();
                        h.this.f.removeMessages(1001);
                    } catch (Exception e) {
                        SLog.e("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, "", false);
    }

    public void a(String str, long j, long j2, long j3, int i, String str2, boolean z) {
        SLog.v("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0052, code lost:
    
        if (r5.b.nextInt(100) < r6) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0054, code lost:
    
        r4 = r6;
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0057, code lost:
    
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
    
        if (r5.b.nextInt(100) < r6) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "-->availableFrequency, report: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r1 = " | ext: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "openSDK_LOG.ReportManager"
            com.tencent.open.log.SLog.d(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r2 = 0
            if (r0 == 0) goto L23
            return r2
        L23:
            java.lang.String r0 = "report_cgi"
            boolean r0 = r6.equals(r0)
            r3 = 1
            r4 = 100
            if (r0 == 0) goto L40
            int r6 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> L3f
            int r6 = r5.a(r6)
            java.util.Random r7 = r5.b
            int r7 = r7.nextInt(r4)
            if (r7 >= r6) goto L57
            goto L54
        L3f:
            return r2
        L40:
            java.lang.String r0 = "report_via"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L58
            int r6 = com.tencent.open.b.f.a(r7)
            java.util.Random r7 = r5.b
            int r7 = r7.nextInt(r4)
            if (r7 >= r6) goto L57
        L54:
            r4 = r6
            r2 = r3
            goto L58
        L57:
            r4 = r6
        L58:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "-->availableFrequency, result: "
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r7 = " | frequency: "
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.tencent.open.log.SLog.d(r1, r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.h.a(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:
    
        r0 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        if (r0 == 0) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
    
        if (r0 == 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = "report_cgi"
            boolean r0 = r5.equals(r0)
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L1b
            android.content.Context r0 = com.tencent.open.utils.g.a()
            com.tencent.open.utils.i r0 = com.tencent.open.utils.i.a(r0, r2)
            java.lang.String r2 = "Common_CGIReportMaxcount"
            int r0 = r0.a(r2)
            if (r0 != 0) goto L36
            goto L33
        L1b:
            java.lang.String r0 = "report_via"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L35
            android.content.Context r0 = com.tencent.open.utils.g.a()
            com.tencent.open.utils.i r0 = com.tencent.open.utils.i.a(r0, r2)
            java.lang.String r2 = "Agent_ReportBatchCount"
            int r0 = r0.a(r2)
            if (r0 != 0) goto L36
        L33:
            r0 = 5
            goto L36
        L35:
            r0 = r1
        L36:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "-->availableCount, report: "
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r5 = " | dataSize: "
            r2.append(r5)
            r2.append(r6)
            java.lang.String r5 = " | maxcount: "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r5 = r2.toString()
            java.lang.String r2 = "openSDK_LOG.ReportManager"
            com.tencent.open.log.SLog.d(r2, r5)
            if (r6 < r0) goto L5d
            r5 = 1
            return r5
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.h.a(java.lang.String, int):boolean");
    }

    protected int a(int i) {
        if (i == 0) {
            int a2 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_CGIReportFrequencySuccess");
            if (a2 == 0) {
                return 10;
            }
            return a2;
        }
        int a3 = i.a(com.tencent.open.utils.g.a(), (String) null).a("Common_CGIReportFrequencyFailed");
        if (a3 == 0) {
            return 100;
        }
        return a3;
    }

    protected Map<String, String> c() {
        List<Serializable> b = g.b("report_via");
        if (b != null) {
            this.d.addAll(b);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.d) {
            JSONObject jSONObject = new JSONObject();
            c cVar = (c) serializable;
            for (String str : cVar.f11339a.keySet()) {
                try {
                    String str2 = cVar.f11339a.get(str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                } catch (JSONException e) {
                    SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        SLog.v("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject2.toString());
            return hashMap;
        } catch (JSONException e2) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void d() {
        if (m.b(com.tencent.open.utils.g.a())) {
            this.g.execute(new Runnable() { // from class: com.tencent.open.b.h.3
                /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
                
                    r18 = r5;
                    r22 = r9;
                    r20 = r14;
                    r7 = true;
                 */
                /* JADX WARN: Removed duplicated region for block: B:39:0x00be A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:41:? A[LOOP:0: B:9:0x002c->B:41:?, LOOP_END, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        Method dump skipped, instructions count: 271
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.h.AnonymousClass3.run():void");
                }
            });
        }
    }

    public void a(final String str, final Map<String, String> map) {
        if (m.b(com.tencent.open.utils.g.a())) {
            l.b(new Runnable() { // from class: com.tencent.open.b.h.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        int a2 = f.a();
                        if (a2 == 0) {
                            a2 = 3;
                        }
                        SLog.d("openSDK_LOG.ReportManager", "-->httpRequest, retryCount: " + a2);
                        int i = 0;
                        do {
                            i++;
                            try {
                                SLog.i("openSDK_LOG.ReportManager", "-->httpRequest, statusCode: " + com.tencent.open.a.f.a().a(str, map).d());
                            } catch (SocketTimeoutException e) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException:", e);
                            } catch (Exception e2) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception:", e2);
                            }
                        } while (i < a2);
                    } catch (Exception e3) {
                        SLog.e("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor:", e3);
                    }
                }
            });
        }
    }
}
