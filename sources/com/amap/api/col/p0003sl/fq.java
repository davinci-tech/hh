package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class fq extends ew<NearbySearch.NearbyQuery, NearbySearchResult> {
    private Context k;
    private NearbySearch.NearbyQuery l;

    public fq(Context context, NearbySearch.NearbyQuery nearbyQuery) {
        super(context, nearbyQuery);
        this.k = context;
        this.l = nearbyQuery;
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.k));
        LatLonPoint centerPoint = this.l.getCenterPoint();
        if (centerPoint != null) {
            stringBuffer.append("&center=").append(centerPoint.getLongitude()).append(",").append(centerPoint.getLatitude());
        }
        stringBuffer.append("&radius=").append(this.l.getRadius());
        stringBuffer.append("&limit=30&searchtype=");
        stringBuffer.append(this.l.getType());
        stringBuffer.append("&timerange=").append(this.l.getTimeRange());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public NearbySearchResult a(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean z = true;
            if (this.l.getType() != 1) {
                z = false;
            }
            ArrayList<NearbyInfo> a2 = fl.a(jSONObject, z);
            NearbySearchResult nearbySearchResult = new NearbySearchResult();
            nearbySearchResult.setNearbyInfoList(a2);
            return nearbySearchResult;
        } catch (JSONException e) {
            fd.a(e, "NearbySearchHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.d() + "/nearby/around";
    }
}
