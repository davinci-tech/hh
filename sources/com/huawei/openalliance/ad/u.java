package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.ads.jsb.inner.data.H5Ad;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.oy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class u extends i {

    public static class a extends f implements oy.a {
        @Override // com.huawei.openalliance.ad.oy.a
        public void a(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
            if (map == null || map.size() == 0) {
                ho.b("JsbReqInterstitialAd", " ads map is empty.");
                j.a(this.g, this.c, 1005, null, true);
                return;
            }
            List<com.huawei.openalliance.ad.inter.data.b> a2 = a(map, this.f6858a);
            ArrayList arrayList = new ArrayList();
            List<H5Ad> a3 = a(arrayList, a2, com.huawei.openalliance.ad.utils.cp.b(this.b));
            this.d.a(arrayList);
            String b = this.h ? com.huawei.openalliance.ad.utils.be.b(new Pair(12, b(map))) : com.huawei.openalliance.ad.utils.be.b(a3);
            if (a3.size() > 0) {
                j.a(this.g, this.c, 1000, b, true);
            } else {
                ho.b("JsbReqInterstitialAd", " ads map is empty.");
                j.a(this.g, this.c, 1005, null, true);
            }
        }

        @Override // com.huawei.openalliance.ad.oy.a
        public void a(int i) {
            j.a(this.g, this.c, bn.a(i), null, true);
        }

        private Map<String, List<H5Ad>> b(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, List<com.huawei.openalliance.ad.inter.data.b>> entry : map.entrySet()) {
                if (!com.huawei.openalliance.ad.utils.bg.a(entry.getValue())) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<com.huawei.openalliance.ad.inter.data.b> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new H5Ad((com.huawei.openalliance.ad.inter.data.d) it.next()));
                    }
                    hashMap.put(entry.getKey(), arrayList);
                }
            }
            return hashMap;
        }

        private List<com.huawei.openalliance.ad.inter.data.b> a(Map<String, List<com.huawei.openalliance.ad.inter.data.b>> map, String str) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return null;
            }
            if (!TextUtils.isEmpty(str)) {
                return map.get(str);
            }
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, List<com.huawei.openalliance.ad.inter.data.b>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getValue());
            }
            return arrayList;
        }

        private List<H5Ad> a(List<ContentRecord> list, List<com.huawei.openalliance.ad.inter.data.b> list2, byte[] bArr) {
            ArrayList arrayList = new ArrayList(4);
            if (list2 != null && list2.size() > 0) {
                for (com.huawei.openalliance.ad.inter.data.b bVar : list2) {
                    if (bVar != null && !bVar.isExpired() && bVar.c_()) {
                        com.huawei.openalliance.ad.inter.data.d dVar = (com.huawei.openalliance.ad.inter.data.d) bVar;
                        arrayList.add(new H5Ad(dVar));
                        ContentRecord a2 = ox.a(dVar);
                        a2.a(bArr);
                        list.add(a2);
                    }
                }
            }
            return arrayList;
        }

        a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback, String str2) {
            super(context, str, remoteCallResultCallback, str2);
        }

        a(Context context, RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
            super(context, remoteCallResultCallback, str);
        }
    }

    @Override // com.huawei.openalliance.ad.i
    protected void b(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("slotId");
        String optString2 = jSONObject.optString(JsbMapKeyNames.H5_MEDIA_CONTENT);
        RequestOptions c = c(context, str);
        com.huawei.openalliance.ad.inter.g gVar = new com.huawei.openalliance.ad.inter.g(context);
        gVar.a(optString);
        gVar.a((Integer) 3);
        gVar.b(c(optString2));
        gVar.a(g(str));
        gVar.a(new a(context, optString, remoteCallResultCallback, this.f7108a));
        gVar.a(c);
    }

    public u() {
        super("pps.interstitial.request");
    }
}
