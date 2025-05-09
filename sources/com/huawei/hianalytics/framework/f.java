package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.core.transport.TransportHandlerFactory;
import com.huawei.hianalytics.framework.config.ICollectorConfig;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f3860a;
    public final String b;
    public final String c;
    public final String d;
    public final List<Event> e;
    public boolean f;

    @Override // java.lang.Runnable
    public void run() {
        List<Event> list;
        byte[] bArr = this.f3860a;
        if (bArr == null || bArr.length == 0) {
            HiLog.sw("DebugReportTask", "body is empty, tag: " + this.b + ", type: " + this.d);
            return;
        }
        String str = this.c;
        IMandatoryParameters iMandatoryParameters = d.e.f3856a;
        if (TextUtils.isEmpty(a(iMandatoryParameters)) || ".none.".equals(a(iMandatoryParameters))) {
            HiLog.sw("DebugReportTask", "no address, tag: " + this.b + ", type: " + this.d);
            return;
        }
        String a2 = a(iMandatoryParameters);
        ICollectorConfig a3 = d.e.a(this.b);
        ICollectorConfig a4 = d.e.a(this.b);
        String appVer = d.e.f3856a.getAppVer();
        String model = d.e.f3856a.getModel();
        HashMap hashMap = new HashMap();
        hashMap.put("x-hasdk-debug", "true");
        hashMap.put("App-Id", a4.getAppId());
        hashMap.put("App-Ver", appVer);
        hashMap.put("Sdk-Name", "hianalytics");
        hashMap.put("Sdk-Ver", "3.2.13.501");
        hashMap.put("Device-Type", model);
        hashMap.put("servicetag", this.b);
        StringBuilder sb = new StringBuilder("debug send event, reqId : ");
        sb.append(str);
        sb.append(", tag: ");
        sb.append(this.b);
        sb.append(", type: ");
        sb.append(this.d);
        HiLog.i("DebugReportTask", sb.toString());
        hashMap.put("Request-Id", str);
        Map<String, String> httpHeader = a3.getHttpHeader(this.d);
        if (httpHeader != null) {
            hashMap.putAll(httpHeader);
        }
        ICollectorConfig a5 = d.e.a(this.b);
        int httpCode = TransportHandlerFactory.create(a2, hashMap, bArr, a5 == null ? 1 : a5.getMetricPolicy(this.d)).execute().getHttpCode();
        try {
            if (httpCode == 200) {
                if (!this.f) {
                    IStorageHandler c = b.c(this.b);
                    if (c != null && (list = this.e) != null && list.size() > 0) {
                        c.deleteEvents(this.e);
                        IMandatoryParameters iMandatoryParameters2 = d.e.f3856a;
                        if (iMandatoryParameters2.isFlashKey() && c.readEventsAllSize() == 0) {
                            iMandatoryParameters2.refreshKey(EncryptUtil.generateSecureRandomStr(16), 1);
                            c.deleteCommonHeaderExAll();
                        }
                    }
                    b.a(this.b).b(this.d);
                }
            } else if (!this.f) {
                b.a(this.b).a(this.d);
            }
        } finally {
            HiLog.si("DebugReportTask", "send debug event tag: " + this.d + ", tag: " + this.b + ", resultCode: " + httpCode + ", reqID: " + str);
        }
    }

    public final String a(IMandatoryParameters iMandatoryParameters) {
        String debugModeUrl = iMandatoryParameters.getDebugModeUrl();
        if (TextUtils.isEmpty(debugModeUrl) || ".none.".equals(debugModeUrl)) {
            HiLog.si("DebugReportTask", "debug mode url is empty");
            return debugModeUrl;
        }
        String str = this.d;
        str.hashCode();
        return (!str.equals("maint") ? "{url}/common/hmshioperqrt" : "{url}/common/hmshimaintqrt").replace("{url}", debugModeUrl);
    }

    public f(byte[] bArr, String str, String str2, String str3, List<Event> list) {
        this.b = str;
        this.d = str2;
        this.f3860a = bArr;
        this.c = str3;
        this.e = list;
    }
}
