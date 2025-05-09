package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.ads.jsb.inner.data.H5Ad;
import com.huawei.hms.ads.jsb.inner.data.JsbCallBackData;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.NativeAdLoader;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class v extends i {

    public static class a implements NativeAdListener {

        /* renamed from: a, reason: collision with root package name */
        private String f7762a;
        private Context b;
        private String c;
        private fs d;
        private fs e;
        private RemoteCallResultCallback<String> f;
        private boolean g;
        private int h;
        private int i;

        @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
        public void onAdsLoaded(Map<String, List<INativeAd>> map) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                ho.b("JsbReqNativeAd", " ads map is empty.");
                j.a(this.f, this.c, 1005, null, true);
                return;
            }
            ho.b("JsbReqNativeAd", "onAdsLoaded, is multi slot id: %s", Boolean.valueOf(this.g));
            int i = this.i;
            if (i == 2) {
                a(map);
            } else {
                if (i != 3) {
                    return;
                }
                b(map);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
        public void onAdFailed(int i) {
            j.a(this.f, this.c, bn.a(i), null, true);
        }

        private void b(Map<String, List<INativeAd>> map) {
            Object obj;
            List<INativeAd> a2 = a(map, this.f7762a);
            ArrayList arrayList = new ArrayList();
            a(a2, com.huawei.openalliance.ad.utils.cp.b(this.b), arrayList);
            if (this.g) {
                HashMap hashMap = new HashMap();
                a(map, hashMap);
                obj = new Pair(Integer.valueOf(this.h), hashMap);
            } else {
                obj = arrayList;
            }
            if (arrayList.size() > 0) {
                j.a(this.f, this.c, 1000, new JsbCallBackData(obj, true, null));
            } else {
                ho.b("JsbReqNativeAd", " ads map is empty.");
                j.a(this.f, this.c, 1005, null, true);
            }
        }

        private <T> void a(Map<String, List<INativeAd>> map, Map<String, List<T>> map2) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return;
            }
            for (Map.Entry<String, List<INativeAd>> entry : map.entrySet()) {
                if (!com.huawei.openalliance.ad.utils.bg.a(entry.getValue())) {
                    List<T> arrayList = new ArrayList<>();
                    for (INativeAd iNativeAd : entry.getValue()) {
                        if (iNativeAd != null) {
                            int i = this.i;
                            if (i == 3) {
                                arrayList.add(AdContentData.a((com.huawei.openalliance.ad.inter.data.e) iNativeAd));
                            } else if (i == 2) {
                                arrayList.add(new H5Ad((com.huawei.openalliance.ad.inter.data.e) iNativeAd));
                            }
                        }
                    }
                    map2.put(entry.getKey(), arrayList);
                }
            }
        }

        private void a(Map<String, List<INativeAd>> map) {
            String b;
            List<INativeAd> a2 = a(map, this.f7762a);
            ArrayList arrayList = new ArrayList();
            a(a2, com.huawei.openalliance.ad.utils.cp.b(this.b), arrayList);
            if (this.g) {
                HashMap hashMap = new HashMap();
                a(map, hashMap);
                b = com.huawei.openalliance.ad.utils.be.b(new Pair(Integer.valueOf(this.h), hashMap));
            } else {
                b = com.huawei.openalliance.ad.utils.be.b(arrayList);
            }
            if (arrayList.size() > 0) {
                j.a(this.f, this.c, 1000, new JsbCallBackData(b, true, null));
            } else {
                ho.b("JsbReqNativeAd", " ads map is empty.");
                j.a(this.f, this.c, 1005, null, true);
            }
        }

        private <T> void a(List<INativeAd> list, byte[] bArr, List<T> list2) {
            if (com.huawei.openalliance.ad.utils.bg.a(list)) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (INativeAd iNativeAd : list) {
                if (iNativeAd != null) {
                    com.huawei.openalliance.ad.inter.data.e eVar = (com.huawei.openalliance.ad.inter.data.e) iNativeAd;
                    ContentRecord a2 = pd.a(eVar);
                    a2.a(bArr);
                    int i = this.i;
                    if (i == 3) {
                        list2.add(AdContentData.a(eVar));
                        a(a2);
                    } else if (i == 2) {
                        list2.add(new H5Ad(eVar));
                        arrayList.add(a2);
                    }
                }
            }
            if (this.i == 2) {
                this.d.a(arrayList);
            }
        }

        private void a(ContentRecord contentRecord) {
            if (contentRecord == null || TextUtils.isEmpty(contentRecord.m()) || TextUtils.isEmpty(contentRecord.l())) {
                return;
            }
            if (contentRecord.aO() != 3) {
                if (this.d.a(contentRecord.m(), contentRecord.l()) == null) {
                    contentRecord.e(com.huawei.openalliance.ad.utils.ao.c());
                }
                this.d.b(contentRecord);
            } else {
                if (TextUtils.isEmpty(contentRecord.aN())) {
                    return;
                }
                if (this.e.a(contentRecord.m(), contentRecord.aN(), contentRecord.l()) == null) {
                    contentRecord.e(com.huawei.openalliance.ad.utils.ao.c());
                    ho.a("JsbReqNativeAd", "insertContent");
                }
                this.e.c(contentRecord);
            }
        }

        private List<INativeAd> a(Map<String, List<INativeAd>> map, String str) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return null;
            }
            if (!TextUtils.isEmpty(str)) {
                return map.get(str);
            }
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, List<INativeAd>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getValue());
            }
            return arrayList;
        }

        a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback, String str2, int i) {
            this.g = false;
            this.h = 3;
            this.i = 2;
            this.f7762a = str;
            this.b = context;
            this.f = remoteCallResultCallback;
            this.c = str2;
            this.d = es.a(context);
            this.e = et.b(context);
            this.i = i;
        }

        a(Context context, RemoteCallResultCallback<String> remoteCallResultCallback, String str, int i, int i2) {
            this.g = false;
            this.h = 3;
            this.i = 2;
            this.b = context;
            this.c = str;
            this.f = remoteCallResultCallback;
            this.d = es.a(context);
            this.e = et.b(context);
            this.g = true;
            this.h = i;
            this.i = i2;
        }
    }

    @Override // com.huawei.openalliance.ad.i
    protected void b(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("slotId");
        String optString2 = jSONObject.optString(JsbMapKeyNames.H5_MEDIA_CONTENT);
        JSONArray optJSONArray = jSONObject.optJSONArray(JsbMapKeyNames.H5_CREATIVE_TYPES);
        int optInt = jSONObject.optInt(JsbMapKeyNames.JSSDK_VER, 2);
        String optString3 = jSONObject.optString(JsbMapKeyNames.JSSDK_VERSION);
        int optInt2 = jSONObject.optInt(JsbMapKeyNames.H5_SMART_BANNER, -111111);
        int optInt3 = jSONObject.optInt(JsbMapKeyNames.H5_PHY_WIDTH, -111111);
        int optInt4 = jSONObject.optInt(JsbMapKeyNames.H5_PHY_HEIGHT, -111111);
        int optInt5 = jSONObject.optInt(JsbMapKeyNames.H5_BANNER_REFRESH_FLAG, -111111);
        int optInt6 = jSONObject.optInt("adType", 3);
        int optInt7 = jSONObject.optInt("deviceType", 4);
        int optInt8 = jSONObject.optInt("maxCount", 0);
        boolean optBoolean = jSONObject.optBoolean(JsbMapKeyNames.H5_RETURN_VIDEOAD, true);
        boolean optBoolean2 = jSONObject.optBoolean("directCacheVideo", false);
        boolean optBoolean3 = jSONObject.optBoolean(JsbMapKeyNames.SUPPORT_TEMPLATE_AD, false);
        RequestOptions c = c(context, str);
        NativeAdLoader nativeAdLoader = new NativeAdLoader(context, new String[]{optString}, optInt6, b(jSONObject.optJSONArray(JsbMapKeyNames.H5_AD_CACHEIDS)));
        nativeAdLoader.setRequestOptions(c);
        nativeAdLoader.setContentBundle(c(optString2));
        nativeAdLoader.setDetailedCreativeType(a(optJSONArray));
        nativeAdLoader.enableDirectReturnVideoAd(optBoolean);
        nativeAdLoader.enableDirectCacheVideo(optBoolean2);
        if (optInt5 != -111111) {
            nativeAdLoader.a(Integer.valueOf(optInt5));
        }
        if (optInt3 != -111111) {
            nativeAdLoader.setAdWidth(Integer.valueOf(optInt3));
        }
        if (optInt4 != -111111) {
            nativeAdLoader.setAdHeight(Integer.valueOf(optInt4));
        }
        if (optInt2 != -111111) {
            nativeAdLoader.setIsSmart(Integer.valueOf(optInt2));
        }
        if (optInt8 > 0) {
            nativeAdLoader.setMaxAdNumbers(optInt8);
        }
        nativeAdLoader.setContentIdListener(new b(remoteCallResultCallback, this.f7108a));
        nativeAdLoader.setListener(new a(context, optString, remoteCallResultCallback, this.f7108a, optInt));
        nativeAdLoader.b((Integer) 3);
        nativeAdLoader.setLocation(g(str));
        nativeAdLoader.setSupportTptAd(optBoolean3);
        nativeAdLoader.setJssdkVersion(optString3);
        nativeAdLoader.loadAds(optInt7, false);
    }

    static class b implements ContentIdListener {

        /* renamed from: a, reason: collision with root package name */
        private String f7763a;
        private RemoteCallResultCallback<String> b;

        @Override // com.huawei.openalliance.ad.inter.listeners.ContentIdListener
        public void a(List<String> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            j.a(this.b, this.f7763a, 1000, new JsbCallBackData(com.huawei.openalliance.ad.utils.be.b(list), false, "native.cb.invalidcontentid"));
        }

        b(RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
            this.b = remoteCallResultCallback;
            this.f7763a = str;
        }
    }

    private List<String> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.optString(i);
                if (!TextUtils.isEmpty(optString)) {
                    arrayList.add(optString);
                }
            }
        }
        return arrayList;
    }

    private List<Integer> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                int optInt = jSONArray.optInt(i, -111111);
                if (optInt != -111111) {
                    arrayList.add(Integer.valueOf(optInt));
                }
            }
        }
        return arrayList;
    }

    public v() {
        super("pps.native.request");
    }
}
