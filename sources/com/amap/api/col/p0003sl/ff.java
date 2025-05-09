package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearchQuery;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class ff extends ew<DistrictSearchQuery, DistrictResult> {
    public ff(Context context, DistrictSearchQuery districtSearchQuery) {
        super(context, districtSearchQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("output=json&page=");
        stringBuffer.append(((DistrictSearchQuery) this.b).getPageNum());
        stringBuffer.append("&offset=").append(((DistrictSearchQuery) this.b).getPageSize());
        if (((DistrictSearchQuery) this.b).isShowBoundary()) {
            stringBuffer.append("&extensions=all");
        } else {
            stringBuffer.append("&extensions=base");
        }
        if (((DistrictSearchQuery) this.b).checkKeyWords()) {
            stringBuffer.append("&keywords=").append(b(((DistrictSearchQuery) this.b).getKeywords()));
        }
        stringBuffer.append("&key=" + hn.f(this.i));
        stringBuffer.append("&subdistrict=" + String.valueOf(((DistrictSearchQuery) this.b).getSubDistrict()));
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public DistrictResult a(String str) throws AMapException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        DistrictResult districtResult = new DistrictResult((DistrictSearchQuery) this.b, arrayList);
        try {
            JSONObject jSONObject = new JSONObject(str);
            districtResult.setPageCount(jSONObject.optInt("count"));
            optJSONArray = jSONObject.optJSONArray("districts");
        } catch (JSONException e) {
            fd.a(e, "DistrictServerHandler", "paseJSONJSONException");
        } catch (Exception e2) {
            fd.a(e2, "DistrictServerHandler", "paseJSONException");
        }
        if (optJSONArray == null) {
            return districtResult;
        }
        fl.a(optJSONArray, arrayList, null);
        return districtResult;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/config/district?";
    }
}
