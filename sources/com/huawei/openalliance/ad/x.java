package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.ads.jsb.inner.data.H5Ad;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.openalliance.ad.inter.RewardAdLoader;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.listeners.RewardAdListener;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class x extends i {

    public static class a extends f implements RewardAdListener {
        @Override // com.huawei.openalliance.ad.inter.listeners.RewardAdListener
        public void onAdsLoaded(Map<String, List<IRewardAd>> map) {
            if (map == null || map.size() == 0) {
                ho.b("JsbReqRewardAd", " ads map is empty.");
                j.a(this.g, this.c, 1005, null, true);
                return;
            }
            ho.b("JsbReqRewardAd", "onAdsLoaded, is multi slot id: %s", Boolean.valueOf(this.h));
            List<IRewardAd> a2 = a(map, this.f6858a);
            ArrayList arrayList = new ArrayList();
            List<H5Ad> a3 = a(arrayList, a2, com.huawei.openalliance.ad.utils.cp.b(this.b));
            a(arrayList);
            String b = this.h ? com.huawei.openalliance.ad.utils.be.b(new Pair(7, a(map))) : com.huawei.openalliance.ad.utils.be.b(a3);
            if (a3.size() > 0) {
                j.a(this.g, this.c, 1000, b, true);
            } else {
                ho.b("JsbReqRewardAd", " ads map is empty.");
                j.a(this.g, this.c, 1005, null, true);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.RewardAdListener
        public void onAdFailed(int i) {
            j.a(this.g, this.c, bn.a(i), null, true);
        }

        private void a(List<ContentRecord> list) {
            if (com.huawei.openalliance.ad.utils.bg.a(list)) {
                return;
            }
            for (ContentRecord contentRecord : list) {
                if (contentRecord != null) {
                    if (contentRecord.aO() == 3) {
                        if (this.e.a(contentRecord.m(), contentRecord.aN(), contentRecord.l()) == null) {
                            contentRecord.e(com.huawei.openalliance.ad.utils.ao.c());
                        }
                        ho.b("JsbReqRewardAd", "insertOrUpdateV3Content");
                        this.e.c(contentRecord);
                        if (!TextUtils.isEmpty(contentRecord.aN()) && !com.huawei.openalliance.ad.utils.bg.a(contentRecord.aV())) {
                            ContentTemplateRecord contentTemplateRecord = new ContentTemplateRecord(contentRecord.m(), contentRecord.aN(), contentRecord.aV(), contentRecord.aT());
                            if (!TextUtils.isEmpty(contentRecord.aX())) {
                                contentTemplateRecord.a(contentRecord.aX());
                            }
                            this.f.a(contentTemplateRecord);
                        }
                    } else {
                        if (this.d.a(contentRecord.m(), contentRecord.l()) == null) {
                            contentRecord.e(com.huawei.openalliance.ad.utils.ao.c());
                        }
                        ho.b("JsbReqRewardAd", "insertOrUpdateV2Content");
                        this.d.b(contentRecord);
                    }
                }
            }
        }

        private Map<String, List<H5Ad>> a(Map<String, List<IRewardAd>> map) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, List<IRewardAd>> entry : map.entrySet()) {
                if (!com.huawei.openalliance.ad.utils.bg.a(entry.getValue())) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<IRewardAd> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new H5Ad((com.huawei.openalliance.ad.inter.data.h) it.next()));
                    }
                    hashMap.put(entry.getKey(), arrayList);
                }
            }
            return hashMap;
        }

        private List<IRewardAd> a(Map<String, List<IRewardAd>> map, String str) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return null;
            }
            if (!TextUtils.isEmpty(str)) {
                return map.get(str);
            }
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, List<IRewardAd>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getValue());
            }
            return arrayList;
        }

        private List<H5Ad> a(List<ContentRecord> list, List<IRewardAd> list2, byte[] bArr) {
            ArrayList arrayList = new ArrayList(4);
            if (list2 != null && list2.size() > 0) {
                for (IRewardAd iRewardAd : list2) {
                    if (iRewardAd != null) {
                        com.huawei.openalliance.ad.inter.data.h hVar = (com.huawei.openalliance.ad.inter.data.h) iRewardAd;
                        arrayList.add(new H5Ad(hVar));
                        ContentRecord a2 = pn.a(hVar);
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
        int optInt = jSONObject.optInt("deviceType", 4);
        RequestOptions c = c(context, str);
        RewardAdLoader rewardAdLoader = new RewardAdLoader(context, new String[]{optString});
        rewardAdLoader.setRequestOptions(c);
        rewardAdLoader.a((Integer) 3);
        rewardAdLoader.setContentBundle(c(optString2));
        rewardAdLoader.setLocation(g(str));
        rewardAdLoader.setListener(new a(context, optString, remoteCallResultCallback, this.f7108a));
        rewardAdLoader.loadAds(optInt, false);
    }

    public x() {
        super("pps.reward.request");
    }
}
