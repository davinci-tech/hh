package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.cloud.CloudItem;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class fb extends ez<CloudSearch.Query, CloudResult> {
    private int k;

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        return null;
    }

    public fb(Context context, CloudSearch.Query query) {
        super(context, query);
        this.k = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        String str = fc.e() + "/datasearch";
        String shape = ((CloudSearch.Query) this.b).getBound().getShape();
        if (shape.equals("Bound")) {
            return str + "/around";
        }
        if (shape.equals("Polygon") || shape.equals("Rectangle")) {
            return str + "/polygon";
        }
        if (!shape.equals(CloudSearch.SearchBound.LOCAL_SHAPE)) {
            return str;
        }
        return str + "/local";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public CloudResult a(String str) throws AMapException {
        ArrayList<CloudItem> arrayList = null;
        if (str == null || str.equals("")) {
            return CloudResult.createPagedResult((CloudSearch.Query) this.b, this.k, ((CloudSearch.Query) this.b).getBound(), ((CloudSearch.Query) this.b).getPageSize(), null);
        }
        try {
            arrayList = d(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return CloudResult.createPagedResult((CloudSearch.Query) this.b, this.k, ((CloudSearch.Query) this.b).getBound(), ((CloudSearch.Query) this.b).getPageSize(), arrayList);
    }

    private ArrayList<CloudItem> d(JSONObject jSONObject) throws JSONException {
        ArrayList<CloudItem> arrayList = new ArrayList<>();
        JSONArray a2 = a(jSONObject);
        if (a2 == null) {
            return arrayList;
        }
        this.k = b(jSONObject);
        for (int i = 0; i < a2.length(); i++) {
            JSONObject optJSONObject = a2.optJSONObject(i);
            CloudItemDetail c = c(optJSONObject);
            a(c, optJSONObject);
            arrayList.add(c);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.i));
        hashtable.put("output", "json");
        if (((CloudSearch.Query) this.b).getBound() != null) {
            if (((CloudSearch.Query) this.b).getBound().getShape().equals("Bound")) {
                hashtable.put("center", fd.a(((CloudSearch.Query) this.b).getBound().getCenter().getLongitude()) + "," + fd.a(((CloudSearch.Query) this.b).getBound().getCenter().getLatitude()));
                StringBuilder sb = new StringBuilder();
                sb.append(((CloudSearch.Query) this.b).getBound().getRange());
                hashtable.put("radius", sb.toString());
            } else if (((CloudSearch.Query) this.b).getBound().getShape().equals("Rectangle")) {
                LatLonPoint lowerLeft = ((CloudSearch.Query) this.b).getBound().getLowerLeft();
                LatLonPoint upperRight = ((CloudSearch.Query) this.b).getBound().getUpperRight();
                double a2 = fd.a(lowerLeft.getLatitude());
                double a3 = fd.a(lowerLeft.getLongitude());
                double a4 = fd.a(upperRight.getLatitude());
                hashtable.put("polygon", a3 + "," + a2 + ";" + fd.a(upperRight.getLongitude()) + "," + a4);
            } else if (((CloudSearch.Query) this.b).getBound().getShape().equals("Polygon")) {
                List<LatLonPoint> polyGonList = ((CloudSearch.Query) this.b).getBound().getPolyGonList();
                if (polyGonList != null && polyGonList.size() > 0) {
                    hashtable.put("polygon", fd.a(polyGonList, ";"));
                }
            } else if (((CloudSearch.Query) this.b).getBound().getShape().equals(CloudSearch.SearchBound.LOCAL_SHAPE)) {
                hashtable.put(DistrictSearchQuery.KEYWORDS_CITY, ((CloudSearch.Query) this.b).getBound().getCity());
            }
        }
        hashtable.put("layerId", ((CloudSearch.Query) this.b).getTableID());
        if (!fd.a(f())) {
            hashtable.put("sortrule", f());
        }
        String g = g();
        if (!fd.a(g)) {
            hashtable.put(Constant.FILTER, g);
        }
        String queryString = ((CloudSearch.Query) this.b).getQueryString();
        if (queryString != null && !"".equals(queryString)) {
            hashtable.put("keywords", queryString);
        } else {
            hashtable.put("keywords", "");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((CloudSearch.Query) this.b).getPageSize());
        hashtable.put(IAchieveDBMgr.PARAM_PAGE_SIZE, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(((CloudSearch.Query) this.b).getPageNum());
        hashtable.put("pageNum", sb3.toString());
        String a5 = hq.a();
        String a6 = hq.a(this.i, a5, a(hashtable));
        hashtable.put("ts", a5);
        hashtable.put("scode", a6);
        return hashtable;
    }

    private static String d(String str) {
        return str != null ? str.replace("&&", "%26%26") : str;
    }

    private static String e(String str) {
        return str != null ? str.replace("%26%26", "&&") : str;
    }

    private static String a(Map<String, String> map) {
        return f(b(map));
    }

    private static String f(String str) {
        try {
        } catch (Throwable th) {
            is.a(th, "ut", "sPa");
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        str = d(str);
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(str2);
            stringBuffer.append("&");
        }
        String e = e(stringBuffer.toString());
        if (e.length() > 1) {
            return (String) e.subSequence(0, e.length() - 1);
        }
        return str;
    }

    private static String b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String f() {
        return ((CloudSearch.Query) this.b).getSortingrules() != null ? ((CloudSearch.Query) this.b).getSortingrules().toString() : "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String g() {
        StringBuffer stringBuffer = new StringBuffer();
        String filterString = ((CloudSearch.Query) this.b).getFilterString();
        String filterNumString = ((CloudSearch.Query) this.b).getFilterNumString();
        stringBuffer.append(filterString);
        if (!fd.a(filterString) && !fd.a(filterNumString)) {
            stringBuffer.append("&&");
        }
        stringBuffer.append(filterNumString);
        return stringBuffer.toString();
    }
}
