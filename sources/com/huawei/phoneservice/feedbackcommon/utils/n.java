package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import com.huawei.phoneservice.feedbackcommon.entity.aa;
import com.huawei.phoneservice.feedbackcommon.entity.ad;
import com.huawei.phoneservice.feedbackcommon.entity.p;
import com.huawei.phoneservice.feedbackcommon.entity.s;
import com.huawei.phoneservice.feedbackcommon.entity.t;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.crypto.signer.CredentialSigner;
import defpackage.tvv;
import defpackage.twc;
import defpackage.twi;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public final class n extends com.huawei.phoneservice.feedbackcommon.utils.c {
    private String h;
    private String i;
    private WeakReference<NotifyUploadZipListener> n;
    private String p;
    private CredentialClient q;
    private long r;
    private String s;
    private Credential x;
    private String g = "";
    private String j = "";
    private int k = 0;
    private long o = 0;
    private int m = 0;
    private int l = 0;
    private boolean t = false;
    private boolean v = false;
    private boolean w = false;
    private boolean u = false;

    public void c(NotifyUploadZipListener notifyUploadZipListener) {
        if (notifyUploadZipListener != null) {
            this.n = new WeakReference<>(notifyUploadZipListener);
        }
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_SECRET_KEY);
        if (!TextUtils.isEmpty(sdk)) {
            this.t = false;
            d(sdk);
            return;
        }
        this.t = true;
        String b2 = twi.b("ucs_entity_key", "", this.d);
        if (!TextUtils.isEmpty(b2)) {
            try {
                CredentialClient build = new CredentialClient.Builder().context(this.d).serCountry(com.huawei.phoneservice.faq.base.util.j.c().getSdk("country")).build();
                this.q = build;
                if (build == null) {
                    b(false);
                    return;
                }
                Credential genCredentialFromString = build.genCredentialFromString(b2);
                this.x = genCredentialFromString;
                if (genCredentialFromString == null) {
                    b(false);
                    return;
                } else if (System.currentTimeMillis() + 3600000 > this.x.getExpireTime()) {
                    a(1, (File) null, 0);
                    return;
                } else {
                    d();
                    return;
                }
            } catch (twc unused) {
            }
        }
        a(1, (File) null, 0);
    }

    public void a(NotifyUploadZipListener notifyUploadZipListener) {
        if (notifyUploadZipListener != null) {
            this.n = new WeakReference<>(notifyUploadZipListener);
        }
        b();
    }

    static /* synthetic */ int i(n nVar) {
        int i2 = nVar.l;
        nVar.l = i2 + 1;
        return i2;
    }

    private void g() {
        String str;
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);
        String sdk2 = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);
        t tVar = new t(sdk, this.i);
        String format = new SimpleDateFormat(FeedbackWebConstants.TIME_FORMATS, Locale.US).format(new Date());
        tVar.a(this.h);
        tVar.c(format);
        String mapToString = ZipUtil.mapToString(new Gson().toJson(tVar));
        try {
            str = com.huawei.phoneservice.feedbackcommon.utils.h.d(FeedbackWebConstants.NOTIFY_UPLOAD_SUCC + sdk2, mapToString, this.g);
        } catch (UnsupportedEncodingException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
            str = "";
        }
        Map<String, String> commonHeaderParameter = ZipUtil.getCommonHeaderParameter(str);
        c("notifyUploadSuccess", "0", "start");
        Submit notifyUploadSucc = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getNotifyUploadSucc(this.d, commonHeaderParameter, mapToString, sdk2, this.j, new h());
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(notifyUploadSucc);
    }

    static /* synthetic */ int j(n nVar) {
        int i2 = nVar.m;
        nVar.m = i2 + 1;
        return i2;
    }

    private void b() {
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);
        String sdk2 = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);
        File file = new File(this.p);
        aa aaVar = new aa(sdk, this.i);
        aaVar.a(0);
        aaVar.d(this.s);
        aaVar.b(com.huawei.phoneservice.feedbackcommon.utils.encrypt.c.d(this.d));
        long j2 = this.o;
        if (j2 <= 0) {
            j2 = 33554432;
        }
        aaVar.a(j2);
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            b(false);
            return;
        }
        this.k = listFiles.length;
        this.r = 0L;
        for (File file2 : listFiles) {
            ad adVar = new ad();
            adVar.d(ZipUtil.fileSha256(file2));
            adVar.a(file2.length());
            arrayList.add(adVar);
            this.r += file2.length();
        }
        String str = "";
        c("getUploadInfo", "zipFileSize:" + this.r, "");
        aaVar.b(this.r);
        aaVar.d(listFiles.length);
        aaVar.b(arrayList);
        String mapToString = ZipUtil.mapToString(new Gson().toJson(aaVar));
        try {
            str = com.huawei.phoneservice.feedbackcommon.utils.h.d(FeedbackWebConstants.UPLOAD_INFO + sdk2, mapToString, this.g);
        } catch (UnsupportedEncodingException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
        }
        Map<String, String> commonHeaderParameter = ZipUtil.getCommonHeaderParameter(str);
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(c(sdk2, file, mapToString, commonHeaderParameter));
    }

    private NotifyUploadZipListener e() {
        WeakReference<NotifyUploadZipListener> weakReference = this.n;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        File file = new File(this.p);
        com.huawei.phoneservice.feedbackcommon.entity.j jVar = new com.huawei.phoneservice.feedbackcommon.entity.j();
        jVar.a(this.s);
        long j2 = this.o;
        if (j2 <= 0) {
            j2 = 33554432;
        }
        jVar.c(j2);
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            b(false);
            return;
        }
        this.k = listFiles.length;
        this.r = 0L;
        for (File file2 : listFiles) {
            ad adVar = new ad();
            adVar.d(ZipUtil.fileSha256(file2));
            adVar.a(file2.length());
            arrayList.add(adVar);
            this.r += file2.length();
        }
        c("getFeedbackUploadInfo", "zipFileSize:" + this.r, "");
        jVar.b(this.r);
        jVar.d(listFiles.length);
        jVar.a(arrayList);
        String json = new Gson().toJson(jVar);
        this.f8350a = new WeakReference<>(com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedbackUploadInfo(this.d, b("POST", FeedbackWebConstants.FEEDBACK_UPLOAD_INFO, json), json, new d(file)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, String str3) {
        com.huawei.phoneservice.faq.base.util.i.e("UploadZipTask", "Request Error in " + str + " resCode is " + str2 + " reason is " + str3);
        com.huawei.phoneservice.faq.base.tracker.d.a(str2, str3, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(File file, int i2) {
        String str;
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);
        String sdk2 = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);
        com.huawei.phoneservice.feedbackcommon.entity.n nVar = new com.huawei.phoneservice.feedbackcommon.entity.n(sdk, this.i);
        nVar.e(this.h);
        String mapToString = ZipUtil.mapToString(new Gson().toJson(nVar));
        try {
            str = com.huawei.phoneservice.feedbackcommon.utils.h.d(FeedbackWebConstants.NEW_UPLOAD_INFO + sdk2, mapToString, this.g);
        } catch (UnsupportedEncodingException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
            str = "";
        }
        Map<String, String> commonHeaderParameter = ZipUtil.getCommonHeaderParameter(str);
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        Submit newUploadInfo = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getNewUploadInfo(this.d, commonHeaderParameter, mapToString, sdk2, this.j);
        a(file, i2, newUploadInfo);
        this.f8350a = new WeakReference<>(newUploadInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Response response) throws IOException {
        String str;
        String str2 = "400";
        try {
            com.huawei.phoneservice.feedbackcommon.entity.k kVar = (com.huawei.phoneservice.feedbackcommon.entity.k) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), com.huawei.phoneservice.feedbackcommon.entity.k.class);
            if (kVar == null || kVar.b() != 0) {
                if (kVar != null) {
                    str2 = String.valueOf(kVar.b());
                    str = kVar.c();
                } else {
                    str = "failure";
                }
                c("getServerDomain", str2, str);
                b(false);
                return;
            }
            c("getServerDomain", "200", "success");
            this.g = kVar.d();
            this.j = "https://" + kVar.e();
            b();
        } catch (JsonSyntaxException unused) {
            c("getServerDomain", "400", "JsonSyntaxException");
            b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.huawei.phoneservice.feedbackcommon.entity.h hVar = new com.huawei.phoneservice.feedbackcommon.entity.h();
        hVar.d(new SimpleDateFormat(FeedbackWebConstants.TIME_FORMATS, Locale.US).format(new Date()));
        hVar.b(this.h);
        String json = new Gson().toJson(hVar);
        Map<String, String> b2 = b("POST", FeedbackWebConstants.FEEDBACK_NOTIFY_SUCCESS, json);
        c("notifyUploadSuccess", "0", "start");
        Submit feedbackNotifySuccess = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.feedbackNotifySuccess(this.d, b2, json, new g());
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(feedbackNotifySuccess);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        NotifyUploadZipListener e2 = e();
        if (e2 != null) {
            e2.notifyUpload(this.e, z);
        }
    }

    private void a(String str, Map<String, String> map, File file, int i2, String str2, String str3, int i3) {
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        Submit fileUploadToService = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFileUploadToService(this.d, str, map, file, str2, str3);
        c cVar = new c(file, str, i2, i3);
        c("uploadFile", "0", "start");
        if (fileUploadToService != null) {
            fileUploadToService.enqueue(cVar);
            this.f8350a = new WeakReference<>(fileUploadToService);
        }
    }

    private void d(String str) {
        String str2;
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);
        String sdk2 = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);
        this.i = com.huawei.phoneservice.faq.base.util.j.c().getSdk("country");
        String mapToString = ZipUtil.mapToString(new Gson().toJson(new com.huawei.phoneservice.feedbackcommon.entity.a(sdk, this.i)));
        try {
            str2 = com.huawei.phoneservice.feedbackcommon.utils.h.d(FeedbackWebConstants.SERVER_DOMAIN + sdk2, mapToString, str);
        } catch (UnsupportedEncodingException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
            str2 = "";
        }
        Map<String, String> commonHeaderParameter = ZipUtil.getCommonHeaderParameter(str2);
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        c("getServerDomain", "0", "start");
        this.f8350a = new WeakReference<>(com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getServerDomain(this.d, commonHeaderParameter, mapToString, sdk2, new e()));
    }

    private void a(File file, int i2, Submit submit) {
        if (submit == null) {
            return;
        }
        submit.enqueue(new j(file, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(File file, int i2) {
        com.huawei.phoneservice.feedbackcommon.entity.e eVar = new com.huawei.phoneservice.feedbackcommon.entity.e();
        eVar.b(this.h);
        String json = new Gson().toJson(eVar);
        Map<String, String> b2 = b("POST", FeedbackWebConstants.FEEDBACK_NEW_UPLOAD_INFO, json);
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedbackNewUploadInfo(this.d, b2, json, new i(file, i2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(s sVar, File file, int i2) {
        this.h = sVar.d();
        HashMap hashMap = new HashMap(16);
        for (int i3 = 0; i3 < sVar.c().size(); i3++) {
            try {
                for (Map.Entry<String, String> entry : sVar.c().get(i3).c().entrySet()) {
                    if (entry.getKey() != null && entry.getValue() != null) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                a(sVar.c().get(i3).b(), hashMap, file, i2, sVar.c().get(i3).a(), sVar.c().get(i3).c().get("Content-Type"), 1);
            } catch (NumberFormatException e2) {
                com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.huawei.phoneservice.feedbackcommon.entity.m mVar, File file) {
        if (mVar == null) {
            e((com.huawei.phoneservice.feedbackcommon.entity.b) null);
            return;
        }
        String b2 = mVar.b();
        com.huawei.phoneservice.feedbackcommon.entity.b d2 = mVar.d();
        if (!"200".equals(b2) || d2 == null) {
            if (!"tsms.auth.token.expired.60006".equals(b2) && !"tsms.ak.restore.failed.60009".equals(b2) && !"tsms.critical.exipred.60010".equals(b2)) {
                e((com.huawei.phoneservice.feedbackcommon.entity.b) null);
                return;
            } else if (this.v) {
                e((com.huawei.phoneservice.feedbackcommon.entity.b) null);
                this.v = false;
                return;
            } else {
                a(1, (File) null, 0);
                this.v = true;
                return;
            }
        }
        if (d2.b() == 0) {
            c("getUploadInfo", "200", "success");
            this.h = d2.c();
            b(d2, d2.d().size(), file);
            return;
        }
        if (d2.b() == 200002) {
            try {
                int parseInt = Integer.parseInt(d2.e().b());
                if (this.k > parseInt) {
                    this.o = ((long) Math.ceil(this.r / parseInt)) + 1048576;
                    ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(this.d)));
                    b(this.o);
                } else {
                    b(d2, d2.d().size(), file);
                }
                return;
            } catch (Exception unused) {
            }
        }
        e(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(com.huawei.phoneservice.feedbackcommon.entity.i iVar) {
        if (iVar == null) {
            c("notifyUploadSuccess", "400", "failure");
        } else {
            String a2 = iVar.a();
            p d2 = iVar.d();
            if ("200".equals(a2) && d2 != null) {
                if (d2.c() == 0) {
                    this.c = true;
                    c("notifyUploadSuccess", "200", "success");
                } else {
                    c("notifyUploadSuccess", iVar.a(), iVar.b());
                }
            }
            if ("tsms.auth.token.expired.60006".equals(a2) || "tsms.ak.restore.failed.60009".equals(a2) || "tsms.critical.exipred.60010".equals(a2)) {
                if (!this.w) {
                    a(3, (File) null, 0);
                    this.w = true;
                    return;
                } else {
                    c("notifyUploadSuccess", iVar.a(), iVar.b());
                    this.w = false;
                }
            }
        }
        b(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.phoneservice.feedbackcommon.entity.g gVar, File file, int i2, Submit submit) {
        if (gVar == null) {
            c("getNewUploadInfo", "400", "failure");
            b(false);
            return;
        }
        String c2 = gVar.c();
        s a2 = gVar.a();
        if ("200".equals(c2) && a2 != null) {
            if (a2.a() == 0) {
                a(a2, file, i2);
                return;
            }
            c("getNewUploadInfo", String.valueOf(a2.a()), a2.e());
            int i3 = this.l + 1;
            this.l = i3;
            if (this.m + i3 > i2) {
                WeakReference<Submit> weakReference = this.f8350a;
                if (weakReference != null && weakReference.get() != null && submit.equals(this.f8350a.get())) {
                    this.f8350a.get().cancel();
                    this.f8350a.clear();
                }
                b(false);
                return;
            }
        }
        if (!"tsms.auth.token.expired.60006".equals(c2) && !"tsms.ak.restore.failed.60009".equals(c2) && !"tsms.critical.exipred.60010".equals(c2)) {
            e((com.huawei.phoneservice.feedbackcommon.entity.b) null);
        } else if (this.u) {
            e((com.huawei.phoneservice.feedbackcommon.entity.b) null);
            this.u = false;
        } else {
            a(2, file, i2);
            this.u = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.huawei.phoneservice.feedbackcommon.entity.b bVar, int i2, File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || i2 != listFiles.length) {
            b(false);
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            HashMap hashMap = new HashMap(16);
            for (Map.Entry<String, String> entry : bVar.d().get(i3).c().entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
            a(bVar.d().get(i3).b(), hashMap, listFiles[i3], i2, bVar.d().get(i3).a(), bVar.d().get(i3).c().get("Content-Type"), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(com.huawei.phoneservice.feedbackcommon.entity.b bVar) {
        if (bVar != null) {
            c("getUploadInfo", String.valueOf(bVar.b()), bVar.a());
        } else {
            c("getUploadInfo", "400", "failure");
        }
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Response response) throws IOException {
        boolean z;
        String str;
        String str2 = "400";
        try {
            p pVar = (p) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), p.class);
            if (pVar == null || pVar.c() != 0) {
                if (pVar != null) {
                    str2 = String.valueOf(pVar.c());
                    str = pVar.e();
                } else {
                    str = "failure";
                }
                c("notifyUploadSuccess", str2, str);
            } else {
                this.c = true;
                c("notifyUploadSuccess", "200", "success");
            }
            z = this.c;
        } catch (JsonSyntaxException unused) {
            c("notifyUploadSuccess", "400", "JsonSyntaxException");
            z = false;
        }
        b(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j2) {
        NotifyUploadZipListener e2 = e();
        if (e2 != null) {
            e2.notifyUploadAgain(j2);
        }
    }

    private void a(int i2, File file, int i3) {
        new Thread(new a(i2, file, i3)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2, Submit submit) {
        int i3 = this.m;
        if (i3 == i2) {
            c("uploadFile", "200", "success");
            if (this.t) {
                c();
                return;
            } else {
                g();
                return;
            }
        }
        if (this.l + i3 > i2) {
            WeakReference<Submit> weakReference = this.f8350a;
            if (weakReference != null && weakReference.get() != null && this.f8350a.get().equals(submit)) {
                this.f8350a.get().cancel();
                this.f8350a.clear();
            }
            c("uploadFile", "400", "failure");
            b(false);
        }
    }

    private Map<String, String> b(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str3) && this.x != null && this.q != null) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk("channel");
                String signBase64 = new CredentialSigner.Builder().withCredential(this.x).withCredentialClient(this.q).build().getSignHandler().from((str + '&' + str2 + "&&" + str3 + "&appid=" + sdk + "&timestamp=" + currentTimeMillis).getBytes(StandardCharsets.UTF_8)).signBase64();
                StringBuilder sb = new StringBuilder("CLOUDSOA-HMAC-SHA256 appid=");
                sb.append(sdk);
                sb.append(", timestamp=");
                sb.append(currentTimeMillis);
                sb.append(", signature=\"");
                sb.append(signBase64);
                sb.append('\"');
                hashMap.put("Authorization", sb.toString());
                String accessKey = this.x.getAccessKey();
                if (accessKey != null) {
                    hashMap.put("X-UCS-AK", accessKey);
                }
                if (sdk != null) {
                    hashMap.put("channelId", sdk);
                }
                String sdkVersion = com.huawei.phoneservice.faq.base.util.j.c().getSdkVersion();
                if (sdkVersion != null) {
                    hashMap.put("sdkVersion", sdkVersion);
                }
            } catch (tvv | twc e2) {
                com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e2.getMessage());
            }
        }
        return hashMap;
    }

    class b implements Callback {
        final /* synthetic */ File b;

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            try {
                com.huawei.phoneservice.feedbackcommon.entity.b bVar = (com.huawei.phoneservice.feedbackcommon.entity.b) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), com.huawei.phoneservice.feedbackcommon.entity.b.class);
                if (bVar != null && bVar.b() == 0) {
                    n.this.c("getUploadInfo", "200", "success");
                    n.this.h = bVar.c();
                    n.this.b(bVar, bVar.d().size(), this.b);
                    return;
                }
                if (bVar != null && bVar.b() == 200002) {
                    try {
                        int parseInt = Integer.parseInt(bVar.e().b());
                        if (n.this.k <= parseInt) {
                            n.this.b(bVar, bVar.d().size(), this.b);
                        } else {
                            n.this.o = ((long) Math.ceil(r0.r / parseInt)) + 1048576;
                            ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(n.this.d)));
                            n nVar = n.this;
                            nVar.b(nVar.o);
                        }
                        return;
                    } catch (Exception unused) {
                    }
                }
                n.this.e(bVar);
            } catch (JsonSyntaxException unused2) {
                n.this.c("getUploadInfo", "400", "JsonSyntaxException");
                n.this.b(false);
            }
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.this.c("getUploadInfo", "400", "failure");
            n.this.b(false);
        }

        b(File file) {
            this.b = file;
        }
    }

    class c implements Callback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8361a;
        final /* synthetic */ File b;
        final /* synthetic */ String d;
        final /* synthetic */ int e;

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            int code = response == null ? 0 : response.getCode();
            if (200 == code) {
                n.this.e.add(new FeedbackZipBean(this.b.getName(), this.d));
                n.j(n.this);
                n.this.e(this.e, submit);
                return;
            }
            int i = this.f8361a;
            if (i == 0) {
                n.i(n.this);
                n.this.a(this.b, this.e);
            } else if (1 == i) {
                n.i(n.this);
            }
            n.this.e(this.e, submit);
            StringBuilder sb = new StringBuilder(" uploadFile ");
            sb.append(code);
            sb.append(" ");
            sb.append(response == null ? "" : response.getMessage());
            com.huawei.phoneservice.faq.base.util.i.e("UploadZipTask", sb.toString());
            n.this.c("uploadFile", "failure", String.valueOf(code));
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.this.c("uploadFile", "failure", th == null ? "" : th.getMessage());
            int i = this.f8361a;
            if (i == 0) {
                n.i(n.this);
                if (n.this.t) {
                    n.this.d(this.b, this.e);
                } else {
                    n.this.a(this.b, this.e);
                }
            } else if (1 == i) {
                n.i(n.this);
            }
            n.this.e(this.e, submit);
        }

        c(File file, String str, int i, int i2) {
            this.b = file;
            this.d = str;
            this.e = i;
            this.f8361a = i2;
        }
    }

    class d implements Callback {
        final /* synthetic */ File d;

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            try {
                n.this.d((com.huawei.phoneservice.feedbackcommon.entity.m) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), com.huawei.phoneservice.feedbackcommon.entity.m.class), this.d);
            } catch (JsonSyntaxException unused) {
                n.this.c("getUploadInfo", "400", "JsonSyntaxException");
                n.this.b(false);
            }
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.this.c("getUploadInfo", "400", "failure");
            n.this.b(false);
        }

        d(File file) {
            this.d = file;
        }
    }

    class e implements Callback {
        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            n.this.d(response);
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.this.c("getServerDomain", "400", "failure");
            n.this.b(false);
        }

        e() {
        }
    }

    class g implements Callback {
        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            try {
                n.this.e((com.huawei.phoneservice.feedbackcommon.entity.i) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), com.huawei.phoneservice.feedbackcommon.entity.i.class));
            } catch (JsonSyntaxException unused) {
                n.this.b(false);
                n.this.c("notifyUploadSuccess", "400", "JsonSyntaxException");
            }
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.this.b(false);
            n.this.c("notifyUploadSuccess", "400", "failure");
        }

        g() {
        }
    }

    class h implements Callback {
        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            n.this.c(response);
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n nVar = n.this;
            nVar.b(nVar.c);
            n.this.c("notifyUploadSuccess", "400", "failure");
        }

        h() {
        }
    }

    class i implements Callback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f8363a;
        final /* synthetic */ int e;

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            try {
                n.this.a((com.huawei.phoneservice.feedbackcommon.entity.g) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), com.huawei.phoneservice.feedbackcommon.entity.g.class), this.f8363a, this.e, submit);
            } catch (JsonSyntaxException unused) {
                n.this.c("getNewUploadInfo", "400", "JsonSyntaxException");
                n.this.b(false);
            }
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.i(n.this);
            if (n.this.m + n.this.l > this.e) {
                WeakReference<Submit> weakReference = n.this.f8350a;
                if (weakReference != null && weakReference.get() != null && submit.equals(n.this.f8350a.get())) {
                    n.this.f8350a.get().cancel();
                    n.this.f8350a.clear();
                }
                n.this.b(false);
            }
        }

        i(File file, int i) {
            this.f8363a = file;
            this.e = i;
        }
    }

    class j implements Callback {
        final /* synthetic */ File b;
        final /* synthetic */ int d;

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onResponse(Submit submit, Response response) throws IOException {
            try {
                s sVar = (s) new Gson().fromJson(StringUtils.byte2Str(response.getBody().bytes()), s.class);
                if (sVar == null || sVar.a() != 0) {
                    if (sVar != null) {
                        n.this.c("getNewUploadInfo", String.valueOf(sVar.a()), sVar.e());
                    }
                    n.i(n.this);
                    if (n.this.m + n.this.l > this.d) {
                        WeakReference<Submit> weakReference = n.this.f8350a;
                        if (weakReference != null && weakReference.get() != null && submit.equals(n.this.f8350a.get())) {
                            n.this.f8350a.get().cancel();
                            n.this.f8350a.clear();
                        }
                        n.this.b(false);
                        return;
                    }
                    return;
                }
                n.this.a(sVar, this.b, this.d);
            } catch (JsonSyntaxException unused) {
                n.this.c("getNewUploadInfo", "400", "JsonSyntaxException");
                n.this.b(false);
            }
        }

        @Override // com.huawei.hms.framework.network.restclient.hwhttp.Callback
        public void onFailure(Submit submit, Throwable th) {
            n.i(n.this);
            if (n.this.m + n.this.l > this.d) {
                WeakReference<Submit> weakReference = n.this.f8350a;
                if (weakReference != null && weakReference.get() != null && submit.equals(n.this.f8350a.get())) {
                    n.this.f8350a.get().cancel();
                    n.this.f8350a.clear();
                }
                n.this.b(false);
            }
        }

        j(File file, int i) {
            this.b = file;
            this.d = i;
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8360a;
        final /* synthetic */ File c;
        final /* synthetic */ int d;

        @Override // java.lang.Runnable
        public void run() {
            try {
                n.this.q = new CredentialClient.Builder().context(n.this.d).serCountry(com.huawei.phoneservice.faq.base.util.j.c().getSdk("country")).build();
                if (n.this.q == null) {
                    n.this.b(false);
                    return;
                }
                n nVar = n.this;
                nVar.x = nVar.q.applyCredential("com.huawei.wisesupport.feedback");
                if (n.this.x != null) {
                    twi.c("ucs_entity_key", n.this.x.toString(), n.this.d);
                    int i = this.d;
                    if (i == 2) {
                        n.this.d(this.c, this.f8360a);
                        return;
                    } else if (i != 3) {
                        n.this.d();
                        return;
                    } else {
                        n.this.c();
                        return;
                    }
                }
                n.this.b(false);
            } catch (twc e) {
                n.this.b(false);
                com.huawei.phoneservice.faq.base.util.i.c("UploadZipTask", e.getMessage());
            }
        }

        a(int i, File file, int i2) {
            this.d = i;
            this.c = file;
            this.f8360a = i2;
        }
    }

    private Submit c(String str, File file, String str2, Map<String, String> map) {
        c("getUploadInfo", "0", "start");
        return com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getUploadInfo(this.d, map, str2, str, this.j, new b(file));
    }

    public n(Context context, String str, String str2) {
        this.d = context;
        this.p = str;
        this.s = str2;
    }
}
