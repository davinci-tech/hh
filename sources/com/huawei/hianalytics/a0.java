package com.huawei.hianalytics;

import android.os.Handler;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a0 {

    /* renamed from: a, reason: collision with root package name */
    public final Handler f3832a;

    /* renamed from: a, reason: collision with other field name */
    public final x f4a = new x();

    public final List<x> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        List<String> queryMcInfo = n.a(EnvUtils.getAppContext()).queryMcInfo();
        if (queryMcInfo.isEmpty()) {
            return arrayList;
        }
        try {
            Iterator<String> it = queryMcInfo.iterator();
            while (it.hasNext()) {
                x xVar = new x(it.next());
                if (j.a(xVar.c)) {
                    list.add(xVar.f102a);
                } else {
                    arrayList.add(xVar);
                }
            }
        } catch (JSONException unused) {
            HiLog.w("MPL", "RAR JSONException");
        }
        if (arrayList.size() <= w.a().c) {
            return arrayList;
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.huawei.hianalytics.a0$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return a0.a((x) obj, (x) obj2);
            }
        });
        return arrayList.subList(0, w.a().c);
    }

    /* renamed from: a, reason: collision with other method in class */
    public final Map<String, y> m543a(List<String> list) {
        List<String> queryMcTag = n.a(EnvUtils.getAppContext()).queryMcTag("MC");
        HashMap hashMap = new HashMap();
        if (queryMcTag != null && !queryMcTag.isEmpty()) {
            try {
                Iterator<String> it = queryMcTag.iterator();
                while (it.hasNext()) {
                    y yVar = new y(it.next());
                    if (j.a(yVar.f3963a)) {
                        list.add(yVar.f108a);
                    } else {
                        hashMap.put(yVar.b, yVar);
                    }
                }
            } catch (JSONException unused) {
                HiLog.w("MPL", "HMTD JSONException");
            }
        }
        return hashMap;
    }

    public static int a(x xVar, x xVar2) {
        long j = xVar.c;
        long j2 = xVar2.c;
        if (j == j2) {
            return 0;
        }
        return j - j2 > 0 ? -1 : 1;
    }

    public final void a(List<x> list, List<String> list2, List<x> list3, Map<String, y> map) {
        boolean z = list3 != null;
        for (x xVar : list) {
            if (z) {
                list2.add(xVar.f102a);
            }
            if (map.containsKey(xVar.f104b)) {
                y yVar = map.get(xVar.f104b);
                if (yVar != null) {
                    String str = yVar.d;
                    if (!TextUtils.isEmpty(str)) {
                        JSONObject a2 = j.a(xVar, "content_code", str, "uuid_metric_info");
                        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_metric_mc");
                        if (instanceByTag == null) {
                            HiLog.w("MRUtils", "metric instance is null");
                        } else {
                            instanceByTag.onEventSync(0, "108", a2);
                        }
                    }
                    String str2 = yVar.c;
                    if (!TextUtils.isEmpty(str2)) {
                        JSONObject a3 = j.a(xVar, "metric_tag", str2, "uuid_tag_info");
                        HiAnalyticsInstance instanceByTag2 = HiAnalyticsManager.getInstanceByTag("ha_metric_mc");
                        if (instanceByTag2 == null) {
                            HiLog.w("MRUtils", "metric instance is null");
                        } else {
                            instanceByTag2.onEventSync(0, "109", a3);
                        }
                    }
                }
            } else if (z) {
                list3.add(xVar);
            }
        }
    }

    public final boolean a(List<x> list, Map<String, y> map) {
        List<String> list2;
        ArrayList arrayList = new ArrayList();
        try {
            Iterator it = new HashSet(list).iterator();
            while (it.hasNext()) {
                arrayList.add(((x) it.next()).a().toString());
            }
        } catch (JSONException unused) {
            HiLog.w("MPL", "DD JSONException");
        }
        int min = Math.min(w.a().d, 30);
        if (arrayList.size() <= min) {
            list2 = j.a(arrayList);
        } else {
            int ceil = (int) Math.ceil(arrayList.size() / min);
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < ceil; i++) {
                int i2 = i * min;
                arrayList2.addAll(j.a((List<String>) arrayList.subList(i2, Math.min(i2 + min, arrayList.size()))));
            }
            HiLog.d("MPL", "count_i: " + list.size() + ", count_o: " + arrayList2.size());
            HiLog.d("MPL", "batchSize: " + min + ", batches: " + ceil);
            list2 = arrayList2;
        }
        if (list2.isEmpty()) {
            return false;
        }
        try {
            Iterator<String> it2 = list2.iterator();
            while (it2.hasNext()) {
                y yVar = new y(it2.next());
                map.put(yVar.b, yVar);
            }
        } catch (JSONException unused2) {
            HiLog.w("MPL", "MAR JSONException");
        }
        n.a(EnvUtils.getAppContext()).insertMcTagList(list2);
        return true;
    }

    public a0(Handler handler) {
        this.f3832a = handler;
    }
}
