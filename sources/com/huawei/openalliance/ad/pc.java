package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes5.dex */
public class pc {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7429a = new byte[0];
    private static pc b;
    private gc c;
    private String d = a();

    private boolean a(long j) {
        return j != -111111 && j > 0;
    }

    public a a(String str, String str2, long j) {
        if (TextUtils.isEmpty(str)) {
            ho.b("MonitorUrlFomatter", " url is empty");
            return null;
        }
        a aVar = new a();
        aVar.f7430a = str;
        if (str.indexOf("__HWPPSREQUESTID__") > 0) {
            String a2 = TextUtils.isEmpty(str2) ? a(1) : a(str2);
            aVar.b = a2;
            aVar.f7430a = str.replace("__HWPPSREQUESTID__", a2);
        }
        String str3 = aVar.f7430a;
        if (str3.indexOf("__TS__") > 0 && a(j)) {
            aVar.f7430a = str3.replace("__TS__", String.valueOf(j));
        }
        return aVar;
    }

    public a a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            ho.b("MonitorUrlFomatter", " url is empty");
            return null;
        }
        a aVar = new a();
        aVar.f7430a = str;
        if (str.indexOf("__HWPPSREQUESTID__") > 0) {
            String a2 = a(1);
            aVar.b = a2;
            aVar.f7430a = str.replace("__HWPPSREQUESTID__", a2);
        }
        String str2 = aVar.f7430a;
        if (str2.indexOf("__TS__") > 0 && a(j)) {
            aVar.f7430a = str2.replace("__TS__", String.valueOf(j));
        }
        return aVar;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f7430a;
        private String b;

        public String b() {
            return this.b;
        }

        public String a() {
            return this.f7430a;
        }
    }

    private String a(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return a(1);
        }
        try {
            int lastIndexOf = str.lastIndexOf("_");
            if (lastIndexOf <= 0 || lastIndexOf >= str.length() - 1) {
                str3 = "requestId format is illegal.";
            } else {
                int i = lastIndexOf + 1;
                String substring = str.substring(i);
                if (!TextUtils.isEmpty(substring)) {
                    int parseInt = Integer.parseInt(substring);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append((CharSequence) str, 0, i);
                    stringBuffer.append(parseInt + 1);
                    return stringBuffer.toString();
                }
                str3 = "requestId format is illegal. seq is empty";
            }
            ho.b("MonitorUrlFomatter", str3);
        } catch (NumberFormatException unused) {
            str2 = "seq has format exception:";
            ho.c("MonitorUrlFomatter", str2);
            return a(1);
        } catch (Exception unused2) {
            str2 = "increaseRequestIdSeqNum has exception";
            ho.c("MonitorUrlFomatter", str2);
            return a(1);
        }
        return a(1);
    }

    private String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.d);
        stringBuffer.append("_");
        stringBuffer.append(com.huawei.openalliance.ad.utils.an.a(com.huawei.openalliance.ad.utils.cp.a(7)));
        stringBuffer.append("_");
        stringBuffer.append(i);
        return stringBuffer.toString();
    }

    private String a() {
        String bm = this.c.bm();
        if (!TextUtils.isEmpty(bm)) {
            return bm;
        }
        String replaceAll = com.huawei.openalliance.ad.utils.ao.a().replaceAll(Constants.LINK, "");
        this.c.n(replaceAll);
        return replaceAll;
    }

    public static pc a(Context context) {
        pc pcVar;
        synchronized (f7429a) {
            if (b == null) {
                b = new pc(context);
            }
            pcVar = b;
        }
        return pcVar;
    }

    private pc(Context context) {
        this.c = fh.b(context);
    }
}
