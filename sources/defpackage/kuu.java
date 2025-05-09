package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.operation.jsoperation.JsUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kuu extends kvb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataSourceId")
    private String f14613a;

    @SerializedName("localDate")
    private String b;

    @SerializedName("dataSource")
    private b c;

    @SerializedName("dataType")
    private kun e;

    @SerializedName("modifiedTime")
    private long h;

    @SerializedName("timeZone")
    private String i;

    @SerializedName("subUser")
    private String j;

    @SerializedName("fields")
    private Map<String, Object> d = new HashMap();

    @SerializedName(JsUtil.SUMMARIES_KEY)
    private Map<String, Object> f = new HashMap();

    public kuu(HiHealthData hiHealthData) {
        d(hiHealthData.getStartTime());
        c(hiHealthData.getEndTime());
        e(hiHealthData.getModifiedTime());
        d(hiHealthData.getTimeZone());
        a(hiHealthData.getString("device_uniquecode"));
        c(DateFormatUtil.a(hiHealthData.getStartTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY));
    }

    public class c {

        @SerializedName("productId")
        private String b;

        public c() {
        }

        public void d(String str) {
            this.b = str;
        }
    }

    public class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("deviceInfo")
        private c f14614a;

        public b() {
        }

        public void b(c cVar) {
            this.f14614a = cVar;
        }
    }

    public void e(kun kunVar) {
        this.e = kunVar;
    }

    public void e(long j) {
        this.h = j;
    }

    public void c(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.i = str;
    }

    public Map<String, Object> a() {
        return this.d;
    }

    public String c() {
        return this.f14613a;
    }

    public void a(String str) {
        this.f14613a = str;
    }

    public void b(b bVar) {
        this.c = bVar;
    }

    public kuv e(String str) {
        if (this.d.containsKey(str) || (this.d.get(str) instanceof kuv)) {
            return (kuv) this.d.get(str);
        }
        return new kuv();
    }

    public void e(String str, Object obj) {
        this.d.put(str, obj);
    }

    public void c(String str, Object obj) {
        this.f.put(str, obj);
    }

    public void d() {
        this.d.clear();
    }

    public void b(String str) {
        c cVar = new c();
        cVar.d(str);
        b bVar = new b();
        bVar.b(cVar);
        b(bVar);
    }
}
