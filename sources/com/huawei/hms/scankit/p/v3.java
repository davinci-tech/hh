package com.huawei.hms.scankit.p;

import android.os.Bundle;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.wearengine.sensor.DataResult;
import com.tencent.open.apireq.BaseResp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.TimeZone;
import java.util.UUID;

/* loaded from: classes9.dex */
public final class v3 extends u3 {
    private static String o = "AiDetect";
    private static String p = "defaultDetect";
    private int h;
    protected String i;
    protected String j;
    protected long k;
    protected long l;
    protected long m;
    protected boolean n;

    class a extends SimpleDateFormat {
        a(String str) {
            super(str);
            setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        }
    }

    class b extends LinkedHashMap<String, String> {
        b() {
            v3.this.g();
            putAll(v3.this.b);
        }
    }

    public v3(Bundle bundle, String str) {
        super(bundle, DynamicModuleInitializer.getContext().getApplicationContext());
        this.h = BaseResp.CODE_QQ_LOW_VERSION;
        this.i = u3.d;
        this.j = u3.e;
        this.b.put("callTime", new a("yyyyMMddHHmmss.SSS").format(Long.valueOf(System.currentTimeMillis())));
        this.b.put("transId", UUID.randomUUID().toString());
        this.b.put("apiName", str);
    }

    private void j() {
        this.h = BaseResp.CODE_QQ_LOW_VERSION;
        this.i = u3.d;
        this.j = u3.e;
    }

    public void a(long j) {
        this.k = j;
    }

    public void c(int i) {
        this.h = i;
    }

    public void h() {
        this.c = System.currentTimeMillis();
    }

    public void i() {
        try {
            if (a()) {
                b bVar = new b();
                bVar.put("result", String.valueOf(this.h));
                bVar.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(System.currentTimeMillis() - this.c));
                bVar.put("scanType", this.i);
                bVar.put("sceneType", this.j);
                if (this.l != 0 && this.m != 0) {
                    if (this.n) {
                        bVar.put("recognizeMode", o);
                        bVar.put("defaultDetectTime", String.valueOf(this.l - this.k));
                        bVar.put("aiDetectTime", String.valueOf(this.m - this.l));
                    } else {
                        bVar.put("recognizeMode", p);
                        bVar.put("defaultDetectTime", String.valueOf(this.l - this.k));
                    }
                    bVar.put("recognizeSuccessTime", String.valueOf(this.m - this.k));
                }
                a4.b().b("60000", bVar);
                j();
            }
        } catch (NullPointerException unused) {
            o4.b("HaLog60000", "nullPoint");
        } catch (Exception unused2) {
            o4.b("HaLog60000", "logEnd Exception");
        }
    }

    public void a(long j, long j2, boolean z) {
        this.l = j;
        this.m = j2;
        this.n = z;
    }

    public void a(HmsScan[] hmsScanArr) {
        if (hmsScanArr != null) {
            this.h = hmsScanArr.length;
            for (HmsScan hmsScan : hmsScanArr) {
                this.i = u3.a(hmsScan.scanType);
                this.j = u3.b(hmsScan.scanTypeForm);
            }
        }
    }
}
