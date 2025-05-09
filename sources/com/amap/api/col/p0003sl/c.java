package com.amap.api.col.p0003sl;

import android.os.Bundle;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.utils.i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static long f936a;

    public static int a(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        int i;
        try {
            JSONObject jSONObject = new JSONObject(str);
            char c = 0;
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("pois")) != null) {
                int i2 = 0;
                while (i2 < optJSONArray.length()) {
                    GeoFence geoFence = new GeoFence();
                    PoiItem poiItem = new PoiItem();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    poiItem.setPoiId(jSONObject2.optString("id"));
                    poiItem.setPoiName(jSONObject2.optString("name"));
                    poiItem.setPoiType(jSONObject2.optString("type"));
                    poiItem.setTypeCode(jSONObject2.optString("typecode"));
                    poiItem.setAddress(jSONObject2.optString("address"));
                    String optString = jSONObject2.optString("location");
                    if (optString != null) {
                        String[] split = optString.split(",");
                        poiItem.setLongitude(Double.parseDouble(split[c]));
                        poiItem.setLatitude(Double.parseDouble(split[1]));
                        List<List<DPoint>> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        i = optInt2;
                        DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                        arrayList2.add(dPoint);
                        arrayList.add(arrayList2);
                        geoFence.setPointList(arrayList);
                        geoFence.setCenter(dPoint);
                    } else {
                        i = optInt2;
                    }
                    poiItem.setTel(jSONObject2.optString("tel"));
                    poiItem.setProvince(jSONObject2.optString("pname"));
                    poiItem.setCity(jSONObject2.optString("cityname"));
                    poiItem.setAdname(jSONObject2.optString("adname"));
                    geoFence.setPoiItem(poiItem);
                    StringBuilder sb = new StringBuilder();
                    sb.append(a());
                    geoFence.setFenceId(sb.toString());
                    if (bundle != null) {
                        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                        geoFence.setPendingIntentAction(bundle.getString("pendingIntentAction"));
                        geoFence.setType(2);
                        geoFence.setRadius(bundle.getFloat("fenceRadius"));
                        geoFence.setExpiration(bundle.getLong("expiration"));
                        geoFence.setActivatesAction(bundle.getInt("activatesAction", 1));
                    }
                    if (list != null) {
                        list.add(geoFence);
                    }
                    i2++;
                    optInt2 = i;
                    c = 0;
                }
            }
            return optInt2;
        } catch (Throwable unused) {
            return 5;
        }
    }

    public static int b(String str, List<GeoFence> list, Bundle bundle) {
        return a(str, list, bundle);
    }

    public final int c(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        ArrayList arrayList;
        String str2;
        int i;
        String str3;
        String str4;
        float f;
        long j;
        boolean z;
        long j2;
        int i2;
        int i3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
            String string2 = bundle.getString("pendingIntentAction");
            float f2 = bundle.getFloat("fenceRadius");
            long j3 = bundle.getLong("expiration");
            int i4 = bundle.getInt("activatesAction", 1);
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("districts")) != null) {
                int i5 = 0;
                while (i5 < optJSONArray.length()) {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    GeoFence geoFence = new GeoFence();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i5);
                    String optString = jSONObject2.optString("citycode");
                    String optString2 = jSONObject2.optString("adcode");
                    String optString3 = jSONObject2.optString("name");
                    JSONArray jSONArray = optJSONArray;
                    String string3 = jSONObject2.getString("center");
                    int i6 = optInt2;
                    DPoint dPoint = new DPoint();
                    int i7 = i5;
                    String str5 = ",";
                    if (string3 != null) {
                        String[] split = string3.split(",");
                        arrayList = arrayList2;
                        str2 = optString3;
                        dPoint.setLatitude(Double.parseDouble(split[1]));
                        dPoint.setLongitude(Double.parseDouble(split[0]));
                        geoFence.setCenter(dPoint);
                    } else {
                        arrayList = arrayList2;
                        str2 = optString3;
                    }
                    geoFence.setCustomId(string);
                    geoFence.setPendingIntentAction(string2);
                    geoFence.setType(3);
                    geoFence.setRadius(f2);
                    geoFence.setExpiration(j3);
                    geoFence.setActivatesAction(i4);
                    StringBuilder sb = new StringBuilder();
                    sb.append(a());
                    geoFence.setFenceId(sb.toString());
                    String optString4 = jSONObject2.optString("polyline");
                    if (optString4 != null) {
                        String[] split2 = optString4.split("\\|");
                        int length = split2.length;
                        i = i4;
                        float f3 = Float.MIN_VALUE;
                        float f4 = Float.MAX_VALUE;
                        int i8 = 0;
                        while (i8 < length) {
                            String str6 = string;
                            String str7 = split2[i8];
                            String[] strArr = split2;
                            DistrictItem districtItem = new DistrictItem();
                            String str8 = string2;
                            List<DPoint> arrayList4 = new ArrayList<>();
                            districtItem.setCitycode(optString);
                            districtItem.setAdcode(optString2);
                            String str9 = optString2;
                            String str10 = str2;
                            districtItem.setDistrictName(str10);
                            str2 = str10;
                            String[] split3 = str7.split(";");
                            float f5 = f2;
                            int i9 = 0;
                            while (i9 < split3.length) {
                                String[] split4 = split3[i9].split(str5);
                                String str11 = str5;
                                String[] strArr2 = split3;
                                if (split4.length > 1) {
                                    String str12 = split4[1];
                                    String str13 = split4[0];
                                    j2 = j3;
                                    double parseDouble = Double.parseDouble(str12);
                                    i2 = length;
                                    i3 = i8;
                                    arrayList4.add(new DPoint(parseDouble, Double.parseDouble(str13)));
                                } else {
                                    j2 = j3;
                                    i2 = length;
                                    i3 = i8;
                                }
                                i9++;
                                length = i2;
                                str5 = str11;
                                split3 = strArr2;
                                j3 = j2;
                                i8 = i3;
                            }
                            String str14 = str5;
                            long j4 = j3;
                            int i10 = length;
                            int i11 = i8;
                            if (arrayList4.size() > 100.0f) {
                                try {
                                    arrayList4 = a(arrayList4, 100.0f);
                                } catch (Throwable unused) {
                                    return 5;
                                }
                            }
                            arrayList3.add(arrayList4);
                            districtItem.setPolyline(arrayList4);
                            ArrayList arrayList5 = arrayList;
                            arrayList5.add(districtItem);
                            f3 = Math.max(f3, a.b(dPoint, arrayList4));
                            f4 = Math.min(f4, a.a(dPoint, arrayList4));
                            i8 = i11 + 1;
                            length = i10;
                            arrayList = arrayList5;
                            string = str6;
                            split2 = strArr;
                            string2 = str8;
                            optString2 = str9;
                            f2 = f5;
                            str5 = str14;
                            j3 = j4;
                        }
                        z = false;
                        str3 = string;
                        str4 = string2;
                        f = f2;
                        j = j3;
                        geoFence.setMaxDis2Center(f3);
                        geoFence.setMinDis2Center(f4);
                        geoFence.setDistrictItemList(arrayList);
                        geoFence.setPointList(arrayList3);
                        list.add(geoFence);
                    } else {
                        i = i4;
                        str3 = string;
                        str4 = string2;
                        f = f2;
                        j = j3;
                        z = false;
                    }
                    i5 = i7 + 1;
                    optJSONArray = jSONArray;
                    optInt2 = i6;
                    i4 = i;
                    string = str3;
                    string2 = str4;
                    f2 = f;
                    j3 = j;
                }
            }
            return optInt2;
        } catch (Throwable unused2) {
        }
    }

    public static long a() {
        long j;
        synchronized (c.class) {
            long b = i.b();
            long j2 = f936a;
            if (b > j2) {
                f936a = b;
            } else {
                f936a = j2 + 1;
            }
            j = f936a;
        }
        return j;
    }

    private List<DPoint> a(List<DPoint> list, float f) {
        if (list == null) {
            return null;
        }
        if (list.size() <= 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = list.get(0);
        DPoint dPoint2 = list.get(list.size() - 1);
        double d = 0.0d;
        int i = 0;
        for (int i2 = 1; i2 < list.size() - 1; i2++) {
            double a2 = a(list.get(i2), dPoint, dPoint2);
            if (a2 > d) {
                i = i2;
                d = a2;
            }
        }
        if (d < f) {
            arrayList.add(dPoint);
            arrayList.add(dPoint2);
            return arrayList;
        }
        List<DPoint> a3 = a(list.subList(0, i + 1), f);
        List<DPoint> a4 = a(list.subList(i, list.size()), f);
        arrayList.addAll(a3);
        arrayList.remove(arrayList.size() - 1);
        arrayList.addAll(a4);
        return arrayList;
    }

    private static double a(DPoint dPoint, DPoint dPoint2, DPoint dPoint3) {
        double longitude;
        double latitude;
        double longitude2 = dPoint.getLongitude();
        double longitude3 = dPoint2.getLongitude();
        double latitude2 = dPoint.getLatitude();
        double latitude3 = dPoint2.getLatitude();
        double longitude4 = dPoint3.getLongitude() - dPoint2.getLongitude();
        double latitude4 = dPoint3.getLatitude() - dPoint2.getLatitude();
        double d = (((longitude2 - longitude3) * longitude4) + ((latitude2 - latitude3) * latitude4)) / ((longitude4 * longitude4) + (latitude4 * latitude4));
        boolean z = dPoint2.getLongitude() == dPoint3.getLongitude() && dPoint2.getLatitude() == dPoint3.getLatitude();
        if (d < 0.0d || z) {
            longitude = dPoint2.getLongitude();
            latitude = dPoint2.getLatitude();
        } else if (d > 1.0d) {
            longitude = dPoint3.getLongitude();
            latitude = dPoint3.getLatitude();
        } else {
            double longitude5 = dPoint2.getLongitude();
            double latitude5 = dPoint2.getLatitude() + (latitude4 * d);
            longitude = longitude5 + (longitude4 * d);
            latitude = latitude5;
        }
        return i.a(new DPoint(dPoint.getLatitude(), dPoint.getLongitude()), new DPoint(latitude, longitude));
    }
}
