package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.huawei.hms.network.embedded.x2;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e {

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f4543a;
        final /* synthetic */ ArrayList b;
        final /* synthetic */ JSONArray c;

        @Override // java.lang.Runnable
        public void run() {
            com.huawei.hms.framework.network.grs.g.j.a aVar = new com.huawei.hms.framework.network.grs.g.j.a();
            aVar.put("total_time", this.f4543a);
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (dVar.o() || dVar.m()) {
                    aVar.put(e.b(dVar));
                    it.remove();
                    break;
                }
            }
            if (this.b.size() > 0) {
                d dVar2 = (d) this.b.get(r1.size() - 1);
                aVar.put(e.b(dVar2));
                this.b.remove(dVar2);
            }
            if (this.b.size() > 0) {
                Iterator it2 = this.b.iterator();
                while (it2.hasNext()) {
                    this.c.put(new JSONObject(e.b((d) it2.next())));
                }
            }
            if (this.c.length() > 0) {
                aVar.put(x2.FAILED_INFO, this.c.toString());
            }
            Logger.d("HaReportHelper", "grssdk report data to aiops is: %s", new JSONObject(aVar.get()));
            HianalyticsHelper.getInstance().onEvent(aVar.get(), "grs_request");
        }

        a(long j, ArrayList arrayList, JSONArray jSONArray) {
            this.f4543a = j;
            this.b = arrayList;
            this.c = jSONArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinkedHashMap<String, String> b(d dVar) {
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        Exception d = dVar.d();
        if (d != null) {
            linkedHashMapPack.put("error_code", ExceptionCode.getErrorCodeFromException(d));
            linkedHashMapPack.put("exception_name", d.getClass().getSimpleName());
            linkedHashMapPack.put("message", StringUtils.anonymizeMessage(d.getMessage()));
        } else {
            linkedHashMapPack.put("error_code", dVar.b());
            linkedHashMapPack.put("exception_name", dVar.c());
        }
        try {
            linkedHashMapPack.put("domain", new URL(dVar.l()).getHost());
        } catch (MalformedURLException e) {
            Logger.w("HaReportHelper", "report host MalformedURLException", e);
        }
        linkedHashMapPack.put("req_start_time", dVar.h());
        linkedHashMapPack.put("req_end_time", dVar.g());
        linkedHashMapPack.put("req_total_time", dVar.i());
        return linkedHashMapPack.getAll();
    }

    public static void a(ArrayList<d> arrayList, long j, JSONArray jSONArray, Context context) {
        if (context == null || arrayList == null || arrayList.size() <= 0 || !HianalyticsHelper.getInstance().isEnableReport(context)) {
            return;
        }
        HianalyticsHelper.getInstance().getReportExecutor().submit(new a(j, arrayList, jSONArray));
    }
}
