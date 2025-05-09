package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.jsb.inner.data.H5Ad;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.PlacementAdLoader;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.listeners.PlacementAdListener;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class w extends i {

    public static class a implements PlacementAdListener {

        /* renamed from: a, reason: collision with root package name */
        private String f8208a;
        private Context b;
        private String c;
        private fs d;
        private RemoteCallResultCallback<String> e;

        @Override // com.huawei.openalliance.ad.inter.listeners.PlacementAdListener
        public void onAdsLoaded(Map<String, List<IPlacementAd>> map) {
            if (map != null && map.size() > 0) {
                List<H5Ad> a2 = a(map.get(this.f8208a), com.huawei.openalliance.ad.utils.cp.b(this.b));
                if (a2.size() > 0) {
                    j.a(this.e, this.c, 1000, com.huawei.openalliance.ad.utils.be.b(a2), true);
                    return;
                }
            }
            ho.b("JsbReqPlacementAd", " ads map is empty.");
            j.a(this.e, this.c, 1005, null, true);
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.PlacementAdListener
        public void onAdFailed(int i) {
            j.a(this.e, this.c, bn.a(i), null, true);
        }

        private List<H5Ad> a(List<IPlacementAd> list, byte[] bArr) {
            ArrayList arrayList = new ArrayList(4);
            if (list != null && list.size() > 0) {
                for (IPlacementAd iPlacementAd : list) {
                    if (iPlacementAd != null) {
                        com.huawei.openalliance.ad.inter.data.g gVar = (com.huawei.openalliance.ad.inter.data.g) iPlacementAd;
                        arrayList.add(new H5Ad(gVar));
                        ContentRecord a2 = pi.a(gVar);
                        a2.a(bArr);
                        this.d.a(a2);
                    }
                }
            }
            return arrayList;
        }

        a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback, String str2) {
            this.f8208a = str;
            this.b = context;
            this.e = remoteCallResultCallback;
            this.c = str2;
            this.d = es.a(context);
        }
    }

    @Override // com.huawei.openalliance.ad.i
    protected void b(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("slotId");
        int optInt = jSONObject.optInt("deviceType", 4);
        int optInt2 = jSONObject.optInt("maxCount", 1);
        int optInt3 = jSONObject.optInt(JsbMapKeyNames.H5_PLACEMENT_REQUEST_MAX_DURATION, 300);
        PlacementAdLoader build = new PlacementAdLoader.Builder(context).setTest(false).setAdIds(new String[]{optString}).setRequestOptions(c(context, str)).setDeviceType(optInt).a((Integer) 3).setLocation(g(str)).build();
        if (optInt3 > 0) {
            build.loadAds(new a(context, optString, remoteCallResultCallback, this.f7108a));
        } else if (optInt2 > 0) {
            build.loadAds(new a(context, optString, remoteCallResultCallback, this.f7108a), optInt3);
        } else {
            build.loadAds(new a(context, optString, remoteCallResultCallback, this.f7108a), optInt3, optInt2);
        }
    }

    public w() {
        super("pps.placement.request");
    }
}
