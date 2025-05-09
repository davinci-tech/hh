package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.TraceLocation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class hi extends hg<List<TraceLocation>, List<LatLng>> implements Runnable {
    private List<TraceLocation> j;
    private Handler k;
    private int l;
    private int m;
    private String n;

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isSupportIPV6() {
        return true;
    }

    @Override // com.amap.api.col.p0003sl.hg, com.amap.api.col.p0003sl.hf
    protected final /* synthetic */ Object a(String str) throws he {
        return b(str);
    }

    public hi(Context context, Handler handler, List<TraceLocation> list, String str, int i, int i2) {
        super(context, list);
        this.j = list;
        this.k = handler;
        this.m = i;
        this.l = i2;
        this.n = str;
    }

    @Override // com.amap.api.col.p0003sl.hg, com.amap.api.col.p0003sl.hf
    protected final String c() {
        JSONArray jSONArray = new JSONArray();
        long j = 0;
        for (int i = 0; i < this.j.size(); i++) {
            TraceLocation traceLocation = this.j.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("x", traceLocation.getLongitude());
                jSONObject.put("y", traceLocation.getLatitude());
                jSONObject.put("ag", (int) traceLocation.getBearing());
                long time = traceLocation.getTime();
                if (i == 0) {
                    if (time == 0) {
                        time = (System.currentTimeMillis() - PreConnectManager.CONNECT_INTERNAL) / 1000;
                    }
                    jSONObject.put("tm", time / 1000);
                } else {
                    if (time != 0) {
                        long j2 = time - j;
                        if (j2 >= 1000) {
                            jSONObject.put("tm", j2 / 1000);
                        }
                    }
                    jSONObject.put("tm", 1);
                }
                j = time;
                jSONObject.put("sp", (int) traceLocation.getSpeed());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        this.h = getURL() + "&" + jSONArray.toString();
        return jSONArray.toString();
    }

    private static List<LatLng> b(String str) throws he {
        JSONObject jSONObject;
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (jSONObject.has("data") && (optJSONArray = jSONObject.optJSONObject("data").optJSONArray("points")) != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                arrayList.add(new LatLng(Double.parseDouble(optJSONObject.optString("y")), Double.parseDouble(optJSONObject.optString("x"))));
            }
            return arrayList;
        }
        return arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        new ArrayList();
        try {
            try {
                hk.a().a(this.n, this.l, d());
                hk.a().a(this.n).a(this.k);
            } catch (he e) {
                hk.a();
                hk.a(this.k, this.m, e.a());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        String str = "key=" + hn.f(this.g);
        String a2 = hq.a();
        return "http://restsdk.amap.com/v4/grasproad/driving?" + str + "&ts=".concat(String.valueOf(a2)) + "&scode=".concat(String.valueOf(hq.a(this.g, a2, str)));
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        return dv.a(getURL());
    }
}
