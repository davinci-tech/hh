package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.fd;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.mr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class bf {
    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> c(Context context) {
        Map map = (Map) be.b(fd.a(context).a(), Map.class, new Class[0]);
        if (bl.a(map)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (!cz.b(str) && !cz.b(str2) && 1 != Integer.parseInt(str2) && Integer.parseInt(str2) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static void a(final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bf.1
            @Override // java.lang.Runnable
            public void run() {
                if (d.c(context, 1)) {
                    ho.a("KitDataUtil", "prepare cached contentId");
                    List c = bf.c(context);
                    if (bg.a(c)) {
                        ho.a("KitDataUtil", "no need prepare cached contentId");
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(MapKeyNames.AD_IDS, be.b(c));
                        jSONObject.put("adType", 1);
                        CallResult a2 = mr.a(context).a(RTCMethods.QUERY_CACHED_CONTENT, jSONObject.toString(), String.class);
                        if (a2 != null && 200 == a2.getCode()) {
                            JSONObject jSONObject2 = new JSONObject((String) a2.getData());
                            String optString = jSONObject2.optString(MapKeyNames.CACHED_CONTENT_ID);
                            String optString2 = jSONObject2.optString(MapKeyNames.CACHED_TEMPLATE);
                            ho.b("KitDataUtil", "query kit cached content success: %s", Integer.valueOf(jSONObject2.optInt("resultCode")));
                            if (!cz.b(optString)) {
                                ho.a("KitDataUtil", "parse kit cached content id");
                                List list = (List) be.b(optString, List.class, String.class);
                                if (!bg.a(list)) {
                                    com.huawei.openalliance.ad.dc.a((List<String>) list);
                                }
                            }
                            if (!cz.b(optString2)) {
                                ho.a("KitDataUtil", "parse kit cached template");
                                Map map = (Map) be.b(optString2, Map.class, new Class[0]);
                                HashMap hashMap = new HashMap();
                                if (!bl.a(map)) {
                                    for (Map.Entry entry : map.entrySet()) {
                                        hashMap.put(entry.getKey(), (List) be.b((String) entry.getValue(), List.class, CachedContent.class));
                                    }
                                }
                                if (!bl.a(hashMap)) {
                                    com.huawei.openalliance.ad.dc.a(hashMap);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        ho.c("KitDataUtil", "prepare cached contentId error: %s", th.getClass().getSimpleName());
                    }
                    com.huawei.openalliance.ad.inter.h.a(context);
                }
            }
        });
    }
}
