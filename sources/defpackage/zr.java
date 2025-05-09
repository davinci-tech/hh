package defpackage;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class zr {
    private static final Object c = new Object();
    private static zr e;
    private final Context d;
    private aah b = new aah();

    /* renamed from: a, reason: collision with root package name */
    private aae f17775a = new aae();

    private zr(Context context) {
        this.d = context;
    }

    public static zr a(Context context) {
        zr zrVar;
        synchronized (c) {
            if (e == null) {
                e = new zr(context);
            }
            zrVar = e;
        }
        return zrVar;
    }

    private zw a(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        zw zwVar = new zw();
        try {
            zwVar.a(str);
            zwVar.c(a(this.d).d(str));
            zwVar.c(a(this.d).e(str));
            long f = abe.e(this.d).f(str);
            long c2 = a(this.d).c(str);
            zwVar.b(f);
            zwVar.a(c2);
            zwVar.b(f, c2);
            if (this.f17775a == null) {
                this.f17775a = new aae();
            }
            zwVar.d(this.f17775a.e(str));
        } catch (Exception e2) {
            abd.b("PreReportHelper", "buildReportInfo err " + e2.getMessage());
        }
        return zwVar;
    }

    private boolean i(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        if (this.b.a(str) <= 0) {
            return false;
        }
        abd.c("PreReportHelper", "has many fail records, report now");
        return true;
    }

    public long c(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        return this.b.c(str);
    }

    public String d(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        return this.b.d(str);
    }

    public int e(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        abd.c("PreReportHelper", "countRecords syncType = " + str);
        return this.b.a(str);
    }

    private int c(String str, boolean z) {
        if (this.d == null || TextUtils.isEmpty(str)) {
            return 1;
        }
        if (!i(str)) {
            abd.c("PreReportHelper", "no record, no need report");
            return 0;
        }
        zw a2 = a(str);
        abd.c("PreReportHelper", "doReport");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("syncType", a2.f());
            jSONObject.put("prepareTraceId", a2.d());
            jSONObject.put("prepareTimes", abe.e(this.d).i(str));
            jSONObject.put("firstStartTime", String.valueOf(a2.a()));
            jSONObject.put("lastEndTime", String.valueOf(a2.e()));
            jSONObject.put("elapsedRealtime", String.valueOf(a2.c()));
            JSONArray jSONArray = new JSONArray();
            List<zx> b = a2.b();
            if (b != null && !b.isEmpty()) {
                for (zx zxVar : b) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("c", zxVar.a());
                    jSONObject2.put("s", zxVar.c());
                    jSONObject2.put("e", zxVar.b());
                    jSONObject2.put(NotificationCompat.CATEGORY_ERROR, zxVar.e());
                    jSONObject2.put("times", zxVar.d());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("details", jSONArray);
        } catch (Exception e2) {
            abd.b("PreReportHelper", "doReport reportJson err " + e2.getMessage());
        }
        abd.a("PreReportHelper", "doReport info: " + jSONObject.toString());
        if (z) {
            return aav.a(str, jSONObject.toString(), this.d, aat.eW_());
        }
        return aav.a(str, jSONObject.toString(), this.d, aaw.eX_());
    }

    public void d(String str, boolean z) {
        abd.c("PreReportHelper", "reportEventNow syncType = " + str);
        if (c(str, z) == 0) {
            b(str);
        }
    }

    public void b(String str) {
        if (this.b == null) {
            this.b = new aah();
        }
        abd.c("PreReportHelper", "clearDbSpInfo syncType = " + str);
        this.b.e(str);
        abe.e(this.d).c(str);
        abe.e(this.d).g(str);
        abe.e(this.d).b(str);
        abe.e(this.d).d(str);
        abe.e(this.d).a(str);
        abe.e(this.d).e(str);
    }

    public void b(String str, boolean z) {
        abd.c("PreReportHelper", "finishPrepare, syncType = " + str);
        if (!aav.b(this.d)) {
            abd.d("PreReportHelper", "hidisk not support report prepare");
            return;
        }
        if (i(str)) {
            abd.c("PreReportHelper", "finishPrepare reportEvent, syncType = " + str);
            d(str, z);
            return;
        }
        abd.c("PreReportHelper", "finishPrepare, syncType = " + str);
        b(str);
    }

    public void b() {
        abd.c("PreReportHelper", "clearAll");
        if (this.b == null) {
            this.b = new aah();
        }
        this.b.d();
        abe.e(this.d).c();
    }
}
