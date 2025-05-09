package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.AoiItem;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.SubPoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.ChargeStationInfo;
import com.amap.api.services.route.Cost;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.District;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DrivePathV2;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveRouteResultV2;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.DriveStepV2;
import com.amap.api.services.route.ElecConsumeInfo;
import com.amap.api.services.route.Navi;
import com.amap.api.services.route.Path;
import com.amap.api.services.route.Railway;
import com.amap.api.services.route.RailwaySpace;
import com.amap.api.services.route.RailwayStationItem;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RideStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteRailwayItem;
import com.amap.api.services.route.RouteSearchCity;
import com.amap.api.services.route.TMC;
import com.amap.api.services.route.TaxiItem;
import com.amap.api.services.route.TruckPath;
import com.amap.api.services.route.TruckRouteRestult;
import com.amap.api.services.route.TruckStep;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.amap.api.services.routepoisearch.RoutePOIItem;
import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherLive;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.swh;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class fl {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f1034a = {"010", "021", "022", "023", "1852", "1853"};

    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.ArrayList<com.amap.api.services.nearby.NearbyInfo> a(org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.lang.String r0 = "datas"
            org.json.JSONArray r13 = r13.optJSONArray(r0)
            if (r13 == 0) goto L81
            int r0 = r13.length()
            if (r0 != 0) goto L10
            goto L81
        L10:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r13.length()
            r2 = 0
            r3 = r2
        L1b:
            if (r3 >= r1) goto L80
            org.json.JSONObject r4 = r13.optJSONObject(r3)
            java.lang.String r5 = "userid"
            java.lang.String r5 = a(r4, r5)
            java.lang.String r6 = "location"
            java.lang.String r6 = a(r4, r6)
            if (r6 == 0) goto L47
            java.lang.String r7 = ","
            java.lang.String[] r6 = r6.split(r7)
            int r7 = r6.length
            r8 = 2
            if (r7 != r8) goto L47
            r7 = r6[r2]
            double r7 = r(r7)
            r9 = 1
            r6 = r6[r9]
            double r9 = r(r6)
            goto L4a
        L47:
            r7 = 0
            r9 = r7
        L4a:
            java.lang.String r6 = "distance"
            java.lang.String r6 = a(r4, r6)
            java.lang.String r11 = "updatetime"
            java.lang.String r4 = a(r4, r11)
            long r11 = s(r4)
            int r4 = p(r6)
            com.amap.api.services.core.LatLonPoint r6 = new com.amap.api.services.core.LatLonPoint
            r6.<init>(r9, r7)
            com.amap.api.services.nearby.NearbyInfo r7 = new com.amap.api.services.nearby.NearbyInfo
            r7.<init>()
            r7.setUserID(r5)
            r7.setTimeStamp(r11)
            r7.setPoint(r6)
            if (r14 == 0) goto L77
            r7.setDrivingDistance(r4)
            goto L7a
        L77:
            r7.setDistance(r4)
        L7a:
            r0.add(r7)
            int r3 = r3 + 1
            goto L1b
        L80:
            return r0
        L81:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.fl.a(org.json.JSONObject, boolean):java.util.ArrayList");
    }

    public static ArrayList<SuggestionCity> a(JSONObject jSONObject) throws JSONException, NumberFormatException {
        JSONArray optJSONArray;
        ArrayList<SuggestionCity> arrayList = new ArrayList<>();
        if (!jSONObject.has("cities") || (optJSONArray = jSONObject.optJSONArray("cities")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new SuggestionCity(a(optJSONObject, "name"), a(optJSONObject, "citycode"), a(optJSONObject, "adcode"), p(a(optJSONObject, "num"))));
            }
        }
        return arrayList;
    }

    public static ArrayList<String> b(JSONObject jSONObject) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("keywords");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return arrayList;
    }

    public static ArrayList<PoiItem> c(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList<PoiItem> arrayList = new ArrayList<>();
        if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray("pois")) != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(d(optJSONObject));
                }
            }
        }
        return arrayList;
    }

    public static PoiItem d(JSONObject jSONObject) throws JSONException {
        PoiItem poiItem = new PoiItem(a(jSONObject, "id"), c(jSONObject, "location"), a(jSONObject, "name"), a(jSONObject, "address"));
        poiItem.setAdCode(a(jSONObject, "adcode"));
        poiItem.setProvinceName(a(jSONObject, "pname"));
        poiItem.setCityName(a(jSONObject, "cityname"));
        poiItem.setAdName(a(jSONObject, "adname"));
        poiItem.setCityCode(a(jSONObject, "citycode"));
        poiItem.setProvinceCode(a(jSONObject, "pcode"));
        poiItem.setDirection(a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION));
        if (jSONObject.has("distance")) {
            String a2 = a(jSONObject, "distance");
            if (!g(a2)) {
                try {
                    poiItem.setDistance((int) Float.parseFloat(a2));
                } catch (NumberFormatException e) {
                    fd.a(e, swh.e, "parseBasePoi");
                } catch (Exception e2) {
                    fd.a(e2, swh.e, "parseBasePoi");
                }
            }
        }
        poiItem.setTel(a(jSONObject, "tel"));
        poiItem.setTypeDes(a(jSONObject, "type"));
        poiItem.setEnter(c(jSONObject, "entr_location"));
        poiItem.setExit(c(jSONObject, "exit_location"));
        poiItem.setWebsite(a(jSONObject, "website"));
        poiItem.setPostcode(a(jSONObject, "postcode"));
        String a3 = a(jSONObject, "business_area");
        if (g(a3)) {
            a3 = a(jSONObject, "businessarea");
        }
        poiItem.setBusinessArea(a3);
        poiItem.setEmail(a(jSONObject, "email"));
        if (o(a(jSONObject, "indoor_map"))) {
            poiItem.setIndoorMap(false);
        } else {
            poiItem.setIndoorMap(true);
        }
        poiItem.setParkingType(a(jSONObject, "parking_type"));
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("children")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("children");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(j(optJSONObject));
                    }
                }
            }
            poiItem.setSubPois(arrayList);
        }
        poiItem.setIndoorDate(e(jSONObject, "indoor_data"));
        poiItem.setPoiExtension(f(jSONObject, "biz_ext"));
        poiItem.setTypeCode(a(jSONObject, "typecode"));
        poiItem.setShopID(a(jSONObject, "shopid"));
        a(poiItem, jSONObject);
        return poiItem;
    }

    private static SubPoiItem j(JSONObject jSONObject) throws JSONException {
        SubPoiItem subPoiItem = new SubPoiItem(a(jSONObject, "id"), c(jSONObject, "location"), a(jSONObject, "name"), a(jSONObject, "address"));
        subPoiItem.setSubName(a(jSONObject, "sname"));
        subPoiItem.setSubTypeDes(a(jSONObject, "subtype"));
        if (jSONObject.has("distance")) {
            String a2 = a(jSONObject, "distance");
            if (!g(a2)) {
                try {
                    subPoiItem.setDistance((int) Float.parseFloat(a2));
                } catch (NumberFormatException e) {
                    fd.a(e, swh.e, "parseSubPoiItem");
                } catch (Exception e2) {
                    fd.a(e2, swh.e, "parseSubPoiItem");
                }
            }
        }
        return subPoiItem;
    }

    public static ArrayList<BusStationItem> e(JSONObject jSONObject) throws JSONException {
        ArrayList<BusStationItem> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(k(optJSONObject));
                }
            }
        }
        return arrayList;
    }

    private static BusStationItem k(JSONObject jSONObject) throws JSONException {
        BusStationItem l = l(jSONObject);
        l.setAdCode(a(jSONObject, "adcode"));
        l.setCityCode(a(jSONObject, "citycode"));
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            l.setBusLineItems(arrayList);
            return l;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(m(optJSONObject));
            }
        }
        l.setBusLineItems(arrayList);
        return l;
    }

    private static BusStationItem l(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationId(a(jSONObject, "id"));
        busStationItem.setLatLonPoint(c(jSONObject, "location"));
        busStationItem.setBusStationName(a(jSONObject, "name"));
        return busStationItem;
    }

    private static BusLineItem m(JSONObject jSONObject) throws JSONException {
        BusLineItem busLineItem = new BusLineItem();
        busLineItem.setBusLineId(a(jSONObject, "id"));
        busLineItem.setBusLineType(a(jSONObject, "type"));
        busLineItem.setBusLineName(a(jSONObject, "name"));
        busLineItem.setDirectionsCoordinates(d(jSONObject, "polyline"));
        busLineItem.setCityCode(a(jSONObject, "citycode"));
        busLineItem.setOriginatingStation(a(jSONObject, "start_stop"));
        busLineItem.setTerminalStation(a(jSONObject, "end_stop"));
        return busLineItem;
    }

    public static ArrayList<BusLineItem> f(JSONObject jSONObject) throws JSONException {
        ArrayList<BusLineItem> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(n(optJSONObject));
            }
        }
        return arrayList;
    }

    private static BusLineItem n(JSONObject jSONObject) throws JSONException {
        BusLineItem m = m(jSONObject);
        m.setFirstBusTime(fd.d(a(jSONObject, "start_time")));
        m.setLastBusTime(fd.d(a(jSONObject, "end_time")));
        m.setBusCompany(a(jSONObject, "company"));
        m.setDistance(q(a(jSONObject, "distance")));
        m.setBasicPrice(q(a(jSONObject, "basic_price")));
        m.setTotalPrice(q(a(jSONObject, "total_price")));
        m.setBounds(d(jSONObject, "bounds"));
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null) {
            m.setBusStations(arrayList);
            return m;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(l(optJSONObject));
            }
        }
        m.setBusStations(arrayList);
        return m;
    }

    private static DistrictItem o(JSONObject jSONObject) throws JSONException {
        String optString;
        DistrictItem districtItem = new DistrictItem();
        districtItem.setCitycode(a(jSONObject, "citycode"));
        districtItem.setAdcode(a(jSONObject, "adcode"));
        districtItem.setName(a(jSONObject, "name"));
        districtItem.setLevel(a(jSONObject, "level"));
        districtItem.setCenter(c(jSONObject, "center"));
        if (jSONObject.has("polyline") && (optString = jSONObject.optString("polyline")) != null && optString.length() > 0) {
            districtItem.setDistrictBoundary(optString.split("\\|"));
        }
        a(jSONObject.optJSONArray("districts"), new ArrayList(), districtItem);
        return districtItem;
    }

    public static void a(JSONArray jSONArray, ArrayList<DistrictItem> arrayList, DistrictItem districtItem) throws JSONException {
        if (jSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(o(optJSONObject));
            }
        }
        if (districtItem != null) {
            districtItem.setSubDistrict(arrayList);
        }
    }

    public static ArrayList<GeocodeAddress> g(JSONObject jSONObject) throws JSONException {
        ArrayList<GeocodeAddress> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("geocodes");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    GeocodeAddress geocodeAddress = new GeocodeAddress();
                    geocodeAddress.setFormatAddress(a(optJSONObject, "formatted_address"));
                    geocodeAddress.setProvince(a(optJSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
                    geocodeAddress.setCity(a(optJSONObject, DistrictSearchQuery.KEYWORDS_CITY));
                    geocodeAddress.setDistrict(a(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                    geocodeAddress.setTownship(a(optJSONObject, "township"));
                    geocodeAddress.setNeighborhood(a(optJSONObject.optJSONObject("neighborhood"), "name"));
                    geocodeAddress.setBuilding(a(optJSONObject.optJSONObject("building"), "name"));
                    geocodeAddress.setAdcode(a(optJSONObject, "adcode"));
                    geocodeAddress.setLatLonPoint(c(optJSONObject, "location"));
                    geocodeAddress.setLevel(a(optJSONObject, "level"));
                    geocodeAddress.setCountry(a(optJSONObject, "country"));
                    geocodeAddress.setPostcode(a(optJSONObject, "postcode"));
                    arrayList.add(geocodeAddress);
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<Tip> h(JSONObject jSONObject) throws JSONException {
        ArrayList<Tip> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("tips");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            Tip tip = new Tip();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                tip.setName(a(optJSONObject, "name"));
                tip.setDistrict(a(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                tip.setAdcode(a(optJSONObject, "adcode"));
                tip.setID(a(optJSONObject, "id"));
                tip.setAddress(a(optJSONObject, "address"));
                tip.setTypeCode(a(optJSONObject, "typecode"));
                String a2 = a(optJSONObject, "location");
                if (!TextUtils.isEmpty(a2)) {
                    String[] split = a2.split(",");
                    if (split.length == 2) {
                        tip.setPostion(new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
                    }
                }
                arrayList.add(tip);
            }
        }
        return arrayList;
    }

    public static void a(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Crossroad crossroad = new Crossroad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                crossroad.setId(a(optJSONObject, "id"));
                crossroad.setDirection(a(optJSONObject, HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION));
                crossroad.setDistance(q(a(optJSONObject, "distance")));
                crossroad.setCenterPoint(c(optJSONObject, "location"));
                crossroad.setFirstRoadId(a(optJSONObject, "first_id"));
                crossroad.setFirstRoadName(a(optJSONObject, "first_name"));
                crossroad.setSecondRoadId(a(optJSONObject, "second_id"));
                crossroad.setSecondRoadName(a(optJSONObject, "second_name"));
                arrayList.add(crossroad);
            }
        }
        regeocodeAddress.setCrossroads(arrayList);
    }

    public static void b(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RegeocodeRoad regeocodeRoad = new RegeocodeRoad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                regeocodeRoad.setId(a(optJSONObject, "id"));
                regeocodeRoad.setName(a(optJSONObject, "name"));
                regeocodeRoad.setLatLngPoint(c(optJSONObject, "location"));
                regeocodeRoad.setDirection(a(optJSONObject, HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION));
                regeocodeRoad.setDistance(q(a(optJSONObject, "distance")));
                arrayList.add(regeocodeRoad);
            }
        }
        regeocodeAddress.setRoads(arrayList);
    }

    public static void a(JSONObject jSONObject, RegeocodeAddress regeocodeAddress) throws JSONException {
        regeocodeAddress.setCountry(a(jSONObject, "country"));
        regeocodeAddress.setCountryCode(a(jSONObject, "countrycode"));
        regeocodeAddress.setProvince(a(jSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
        regeocodeAddress.setCity(a(jSONObject, DistrictSearchQuery.KEYWORDS_CITY));
        regeocodeAddress.setCityCode(a(jSONObject, "citycode"));
        regeocodeAddress.setAdCode(a(jSONObject, "adcode"));
        regeocodeAddress.setDistrict(a(jSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
        regeocodeAddress.setTownship(a(jSONObject, "township"));
        regeocodeAddress.setNeighborhood(a(jSONObject.optJSONObject("neighborhood"), "name"));
        regeocodeAddress.setBuilding(a(jSONObject.optJSONObject("building"), "name"));
        StreetNumber streetNumber = new StreetNumber();
        JSONObject optJSONObject = jSONObject.optJSONObject("streetNumber");
        streetNumber.setStreet(a(optJSONObject, "street"));
        streetNumber.setNumber(a(optJSONObject, "number"));
        streetNumber.setLatLonPoint(c(optJSONObject, "location"));
        streetNumber.setDirection(a(optJSONObject, HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION));
        streetNumber.setDistance(q(a(optJSONObject, "distance")));
        regeocodeAddress.setStreetNumber(streetNumber);
        regeocodeAddress.setBusinessAreas(p(jSONObject));
        regeocodeAddress.setTowncode(a(jSONObject, "towncode"));
        a(regeocodeAddress);
    }

    private static void a(RegeocodeAddress regeocodeAddress) {
        if ((regeocodeAddress.getCity() == null || regeocodeAddress.getCity().length() <= 0) && l(regeocodeAddress.getCityCode())) {
            regeocodeAddress.setCity(regeocodeAddress.getProvince());
        }
    }

    private static boolean l(String str) {
        if (str != null && str.length() > 0) {
            for (String str2 : f1034a) {
                if (str.trim().equals(str2.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<BusinessArea> p(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("businessAreas");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                BusinessArea businessArea = new BusinessArea();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    businessArea.setCenterPoint(c(optJSONObject, "location"));
                    businessArea.setName(a(optJSONObject, "name"));
                    arrayList.add(businessArea);
                }
            }
        }
        return arrayList;
    }

    public static BusRouteResult a(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            BusRouteResult busRouteResult = new BusRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            if (optJSONObject == null) {
                return busRouteResult;
            }
            busRouteResult.setStartPos(c(optJSONObject, "origin"));
            busRouteResult.setTargetPos(c(optJSONObject, "destination"));
            busRouteResult.setTaxiCost(q(a(optJSONObject, "taxi_cost")));
            if (!optJSONObject.has("transits") || (optJSONArray = optJSONObject.optJSONArray("transits")) == null) {
                return busRouteResult;
            }
            busRouteResult.setPaths(a(optJSONArray));
            return busRouteResult;
        } catch (JSONException unused) {
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static List<BusPath> a(JSONArray jSONArray) throws JSONException {
        BusStep q;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            BusPath busPath = new BusPath();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                busPath.setCost(q(a(optJSONObject, "cost")));
                busPath.setDuration(s(a(optJSONObject, "duration")));
                busPath.setNightBus(t(a(optJSONObject, "nightflag")));
                busPath.setWalkDistance(q(a(optJSONObject, "walking_distance")));
                busPath.setDistance(q(a(optJSONObject, "distance")));
                JSONArray optJSONArray = optJSONObject.optJSONArray("segments");
                if (optJSONArray != null) {
                    ArrayList arrayList2 = new ArrayList();
                    float f = 0.0f;
                    float f2 = 0.0f;
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null && (q = q(optJSONObject2)) != null) {
                            arrayList2.add(q);
                            if (q.getWalk() != null) {
                                f2 += q.getWalk().getDistance();
                            }
                            if (q.getBusLines() != null && q.getBusLines().size() > 0) {
                                f += q.getBusLines().get(0).getDistance();
                            }
                        }
                    }
                    busPath.setSteps(arrayList2);
                    busPath.setBusDistance(f);
                    busPath.setWalkDistance(f2);
                    arrayList.add(busPath);
                }
            }
        }
        return arrayList;
    }

    private static BusStep q(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        BusStep busStep = new BusStep();
        JSONObject optJSONObject = jSONObject.optJSONObject("walking");
        if (optJSONObject != null) {
            busStep.setWalk(r(optJSONObject));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("bus");
        if (optJSONObject2 != null) {
            busStep.setBusLines(s(optJSONObject2));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject("entrance");
        if (optJSONObject3 != null) {
            busStep.setEntrance(t(optJSONObject3));
        }
        JSONObject optJSONObject4 = jSONObject.optJSONObject("exit");
        if (optJSONObject4 != null) {
            busStep.setExit(t(optJSONObject4));
        }
        JSONObject optJSONObject5 = jSONObject.optJSONObject(u3.j);
        if (optJSONObject5 != null) {
            busStep.setRailway(y(optJSONObject5));
        }
        JSONObject optJSONObject6 = jSONObject.optJSONObject("taxi");
        if (optJSONObject6 != null) {
            busStep.setTaxi(E(optJSONObject6));
        }
        if ((busStep.getWalk() == null || busStep.getWalk().getSteps().size() == 0) && busStep.getBusLines().size() == 0 && busStep.getRailway() == null && busStep.getTaxi() == null) {
            return null;
        }
        return busStep;
    }

    private static RouteBusWalkItem r(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        if (jSONObject == null) {
            return null;
        }
        RouteBusWalkItem routeBusWalkItem = new RouteBusWalkItem();
        routeBusWalkItem.setOrigin(c(jSONObject, "origin"));
        routeBusWalkItem.setDestination(c(jSONObject, "destination"));
        routeBusWalkItem.setDistance(q(a(jSONObject, "distance")));
        routeBusWalkItem.setDuration(s(a(jSONObject, "duration")));
        if (!jSONObject.has(MedalConstants.EVENT_STEPS) || (optJSONArray = jSONObject.optJSONArray(MedalConstants.EVENT_STEPS)) == null) {
            return routeBusWalkItem;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(u(optJSONObject));
            }
        }
        routeBusWalkItem.setSteps(arrayList);
        a(routeBusWalkItem, arrayList);
        return routeBusWalkItem;
    }

    private static List<RouteBusLineItem> s(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("buslines")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(v(optJSONObject));
            }
        }
        return arrayList;
    }

    private static Doorway t(JSONObject jSONObject) throws JSONException {
        Doorway doorway = new Doorway();
        doorway.setName(a(jSONObject, "name"));
        doorway.setLatLonPoint(c(jSONObject, "location"));
        return doorway;
    }

    private static WalkStep u(JSONObject jSONObject) throws JSONException {
        WalkStep walkStep = new WalkStep();
        walkStep.setInstruction(a(jSONObject, "instruction"));
        walkStep.setOrientation(a(jSONObject, ParamConstants.Param.ORIENTATION));
        walkStep.setRoad(a(jSONObject, "road"));
        walkStep.setDistance(q(a(jSONObject, "distance")));
        walkStep.setDuration(q(a(jSONObject, "duration")));
        walkStep.setPolyline(d(jSONObject, "polyline"));
        walkStep.setAction(a(jSONObject, "action"));
        walkStep.setAssistantAction(a(jSONObject, "assistant_action"));
        return walkStep;
    }

    private static RouteBusLineItem v(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        RouteBusLineItem routeBusLineItem = new RouteBusLineItem();
        routeBusLineItem.setDepartureBusStation(x(jSONObject.optJSONObject("departure_stop")));
        routeBusLineItem.setArrivalBusStation(x(jSONObject.optJSONObject("arrival_stop")));
        routeBusLineItem.setBusLineName(a(jSONObject, "name"));
        routeBusLineItem.setBusLineId(a(jSONObject, "id"));
        routeBusLineItem.setBusLineType(a(jSONObject, "type"));
        routeBusLineItem.setDistance(q(a(jSONObject, "distance")));
        routeBusLineItem.setDuration(q(a(jSONObject, "duration")));
        routeBusLineItem.setPolyline(d(jSONObject, "polyline"));
        routeBusLineItem.setFirstBusTime(fd.d(a(jSONObject, "start_time")));
        routeBusLineItem.setLastBusTime(fd.d(a(jSONObject, "end_time")));
        routeBusLineItem.setPassStationNum(p(a(jSONObject, "via_num")));
        routeBusLineItem.setPassStations(w(jSONObject));
        return routeBusLineItem;
    }

    private static List<BusStationItem> w(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("via_stops")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(x(optJSONObject));
            }
        }
        return arrayList;
    }

    private static BusStationItem x(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationName(a(jSONObject, "name"));
        busStationItem.setBusStationId(a(jSONObject, "id"));
        busStationItem.setLatLonPoint(c(jSONObject, "location"));
        return busStationItem;
    }

    private static RouteRailwayItem y(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || !jSONObject.has("id") || !jSONObject.has("name")) {
            return null;
        }
        RouteRailwayItem routeRailwayItem = new RouteRailwayItem();
        routeRailwayItem.setID(a(jSONObject, "id"));
        routeRailwayItem.setName(a(jSONObject, "name"));
        routeRailwayItem.setTime(a(jSONObject, "time"));
        routeRailwayItem.setTrip(a(jSONObject, "trip"));
        routeRailwayItem.setDistance(q(a(jSONObject, "distance")));
        routeRailwayItem.setType(a(jSONObject, "type"));
        routeRailwayItem.setDeparturestop(z(jSONObject.optJSONObject("departure_stop")));
        routeRailwayItem.setArrivalstop(z(jSONObject.optJSONObject("arrival_stop")));
        routeRailwayItem.setViastops(A(jSONObject));
        routeRailwayItem.setAlters(B(jSONObject));
        routeRailwayItem.setSpaces(C(jSONObject));
        return routeRailwayItem;
    }

    private static RailwayStationItem z(JSONObject jSONObject) throws JSONException {
        RailwayStationItem railwayStationItem = new RailwayStationItem();
        railwayStationItem.setID(a(jSONObject, "id"));
        railwayStationItem.setName(a(jSONObject, "name"));
        railwayStationItem.setLocation(c(jSONObject, "location"));
        railwayStationItem.setAdcode(a(jSONObject, "adcode"));
        railwayStationItem.setTime(a(jSONObject, "time"));
        railwayStationItem.setisStart(t(a(jSONObject, "start")));
        railwayStationItem.setisEnd(t(a(jSONObject, "end")));
        railwayStationItem.setWait(q(a(jSONObject, "wait")));
        return railwayStationItem;
    }

    private static List<RailwayStationItem> A(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("via_stops")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(z(optJSONObject));
            }
        }
        return arrayList;
    }

    private static List<Railway> B(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("alters")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Railway railway = new Railway();
                railway.setID(a(optJSONObject, "id"));
                railway.setName(a(optJSONObject, "name"));
                arrayList.add(railway);
            }
        }
        return arrayList;
    }

    private static List<RailwaySpace> C(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("spaces")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(D(optJSONObject));
            }
        }
        return arrayList;
    }

    private static RailwaySpace D(JSONObject jSONObject) throws JSONException {
        return new RailwaySpace(a(jSONObject, "code"), q(a(jSONObject, "cost")));
    }

    private static TaxiItem E(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        TaxiItem taxiItem = new TaxiItem();
        taxiItem.setOrigin(c(jSONObject, "origin"));
        taxiItem.setDestination(c(jSONObject, "destination"));
        taxiItem.setDistance(q(a(jSONObject, "distance")));
        taxiItem.setDuration(q(a(jSONObject, "duration")));
        taxiItem.setSname(a(jSONObject, "sname"));
        taxiItem.setTname(a(jSONObject, "tname"));
        return taxiItem;
    }

    public static DriveRouteResult b(String str) throws AMapException {
        JSONArray optJSONArray;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            DriveRouteResult driveRouteResult = new DriveRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            if (optJSONObject == null) {
                return driveRouteResult;
            }
            driveRouteResult.setStartPos(c(optJSONObject, "origin"));
            driveRouteResult.setTargetPos(c(optJSONObject, "destination"));
            driveRouteResult.setTaxiCost(q(a(optJSONObject, "taxi_cost")));
            if (!optJSONObject.has("paths") || (optJSONArray = optJSONObject.optJSONArray("paths")) == null) {
                return driveRouteResult;
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < optJSONArray.length()) {
                DrivePath drivePath = new DrivePath();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    drivePath.setDistance(q(a(optJSONObject2, "distance")));
                    drivePath.setDuration(s(a(optJSONObject2, "duration")));
                    drivePath.setStrategy(a(optJSONObject2, "strategy"));
                    drivePath.setTolls(q(a(optJSONObject2, "tolls")));
                    drivePath.setTollDistance(q(a(optJSONObject2, "toll_distance")));
                    drivePath.setTotalTrafficlights(p(a(optJSONObject2, "traffic_lights")));
                    drivePath.setRestriction(p(a(optJSONObject2, "restriction")));
                    JSONArray optJSONArray2 = optJSONObject2.optJSONArray(MedalConstants.EVENT_STEPS);
                    if (optJSONArray2 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        int i2 = 0;
                        while (i2 < optJSONArray2.length()) {
                            DriveStep driveStep = new DriveStep();
                            JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                            if (optJSONObject3 != null) {
                                jSONArray2 = optJSONArray;
                                driveStep.setInstruction(a(optJSONObject3, "instruction"));
                                driveStep.setOrientation(a(optJSONObject3, ParamConstants.Param.ORIENTATION));
                                driveStep.setRoad(a(optJSONObject3, "road"));
                                driveStep.setDistance(q(a(optJSONObject3, "distance")));
                                driveStep.setTolls(q(a(optJSONObject3, "tolls")));
                                driveStep.setTollDistance(q(a(optJSONObject3, "toll_distance")));
                                driveStep.setTollRoad(a(optJSONObject3, "toll_road"));
                                driveStep.setDuration(q(a(optJSONObject3, "duration")));
                                driveStep.setPolyline(d(optJSONObject3, "polyline"));
                                driveStep.setAction(a(optJSONObject3, "action"));
                                driveStep.setAssistantAction(a(optJSONObject3, "assistant_action"));
                                b(driveStep, optJSONObject3);
                                a(driveStep, optJSONObject3);
                                arrayList2.add(driveStep);
                            } else {
                                jSONArray2 = optJSONArray;
                            }
                            i2++;
                            optJSONArray = jSONArray2;
                        }
                        jSONArray = optJSONArray;
                        drivePath.setSteps(arrayList2);
                        b(drivePath, arrayList2);
                        arrayList.add(drivePath);
                        i++;
                        optJSONArray = jSONArray;
                    }
                }
                jSONArray = optJSONArray;
                i++;
                optJSONArray = jSONArray;
            }
            driveRouteResult.setPaths(arrayList);
            return driveRouteResult;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseDriveRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        } catch (Throwable th) {
            fd.a(th, swh.e, "parseDriveRouteThrowable");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static DriveRouteResultV2 c(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            DriveRouteResultV2 driveRouteResultV2 = new DriveRouteResultV2();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            if (optJSONObject == null) {
                return driveRouteResultV2;
            }
            driveRouteResultV2.setStartPos(c(optJSONObject, "origin"));
            driveRouteResultV2.setTargetPos(c(optJSONObject, "destination"));
            driveRouteResultV2.setTaxiCost(q(a(optJSONObject, "taxi_cost")));
            if (!optJSONObject.has("paths") || (optJSONArray = optJSONObject.optJSONArray("paths")) == null) {
                return driveRouteResultV2;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                DrivePathV2 drivePathV2 = new DrivePathV2();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    drivePathV2.setDistance(q(a(optJSONObject2, "distance")));
                    drivePathV2.setStrategy(a(optJSONObject2, "strategy"));
                    drivePathV2.setRestriction(p(a(optJSONObject2, "restriction")));
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("cost");
                    if (optJSONObject3 != null) {
                        Cost cost = new Cost();
                        a(cost, optJSONObject3);
                        drivePathV2.setCost(cost);
                    }
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("elec_consume_info");
                    if (optJSONObject4 != null) {
                        drivePathV2.setElecConsumeInfo(F(optJSONObject4));
                    }
                    JSONArray optJSONArray2 = optJSONObject2.optJSONArray("charge_station_info");
                    if (optJSONArray2 != null) {
                        drivePathV2.setChargeStationInfo(b(optJSONArray2));
                    }
                    JSONArray optJSONArray3 = optJSONObject2.optJSONArray(MedalConstants.EVENT_STEPS);
                    if (optJSONArray3 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                            DriveStepV2 driveStepV2 = new DriveStepV2();
                            JSONObject optJSONObject5 = optJSONArray3.optJSONObject(i2);
                            if (optJSONObject5 != null) {
                                driveStepV2.setInstruction(a(optJSONObject5, "instruction"));
                                driveStepV2.setOrientation(a(optJSONObject5, ParamConstants.Param.ORIENTATION));
                                driveStepV2.setStepDistance(p(a(optJSONObject5, "step_distance")));
                                driveStepV2.setRoad(a(optJSONObject5, "road_name"));
                                driveStepV2.setPolyline(d(optJSONObject5, "polyline"));
                                JSONObject optJSONObject6 = optJSONObject5.optJSONObject("cost");
                                if (optJSONObject6 != null) {
                                    Cost cost2 = new Cost();
                                    a(cost2, optJSONObject6);
                                    driveStepV2.setCostDetail(cost2);
                                }
                                JSONObject optJSONObject7 = optJSONObject5.optJSONObject("navi");
                                if (optJSONObject7 != null) {
                                    driveStepV2.setNavi(G(optJSONObject7));
                                }
                                JSONArray optJSONArray4 = optJSONObject5.optJSONArray("cities");
                                if (optJSONArray4 != null) {
                                    driveStepV2.setRouteSearchCityList(d(optJSONArray4));
                                }
                                JSONArray optJSONArray5 = optJSONObject5.optJSONArray("tmcs");
                                if (optJSONArray5 != null) {
                                    driveStepV2.setTMCs(c(optJSONArray5));
                                }
                                arrayList2.add(driveStepV2);
                            }
                        }
                        drivePathV2.setSteps(arrayList2);
                        c(drivePathV2, arrayList2);
                        arrayList.add(drivePathV2);
                    }
                }
            }
            driveRouteResultV2.setPaths(arrayList);
            return driveRouteResultV2;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseDriveRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        } catch (Throwable th) {
            fd.a(th, swh.e, "parseDriveRouteThrowable");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static ElecConsumeInfo F(JSONObject jSONObject) throws AMapException {
        try {
            ElecConsumeInfo elecConsumeInfo = new ElecConsumeInfo();
            elecConsumeInfo.setRunOutPoint(c(jSONObject, "runout_point"));
            elecConsumeInfo.setRunOutStepIndex(b(jSONObject, "runout_step_index"));
            elecConsumeInfo.setConsumeEnergy(b(jSONObject, "consume_energy"));
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("left_energy");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(Integer.valueOf(optJSONArray.optInt(i)));
                }
            }
            elecConsumeInfo.setLeftEnergy(arrayList);
            return elecConsumeInfo;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseElecConsumeInfo");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static List<ChargeStationInfo> b(JSONArray jSONArray) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                ChargeStationInfo chargeStationInfo = new ChargeStationInfo();
                chargeStationInfo.setName(a(jSONObject, "name"));
                chargeStationInfo.setPoiId(a(jSONObject, "poiid"));
                chargeStationInfo.setBrandName(a(jSONObject, "brand_name"));
                chargeStationInfo.setShowPoint(c(jSONObject, "show_point"));
                chargeStationInfo.setProjectivePoint(c(jSONObject, "projective_point"));
                chargeStationInfo.setMaxPower(b(jSONObject, "max_power"));
                chargeStationInfo.setChargePercent(b(jSONObject, "charge_percent"));
                chargeStationInfo.setChargeTime(b(jSONObject, "charge_time"));
                chargeStationInfo.setRemainingCapacity(b(jSONObject, "remaining_capacity"));
                chargeStationInfo.setVoltage(b(jSONObject, "voltage"));
                chargeStationInfo.setAmperage(b(jSONObject, "amperage"));
                chargeStationInfo.setStepIndex(b(jSONObject, "step_index"));
                arrayList.add(chargeStationInfo);
            }
            return arrayList;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseChargeStationInfo");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static void a(Cost cost, JSONObject jSONObject) throws AMapException {
        try {
            cost.setTolls(q(a(jSONObject, "tolls")));
            cost.setTollDistance(q(a(jSONObject, "toll_distance")));
            cost.setTollRoad(a(jSONObject, "toll_road"));
            cost.setDuration(q(a(jSONObject, "duration")));
            cost.setTrafficLights(p(a(jSONObject, "traffic_lights")));
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseCostDetail");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static Navi G(JSONObject jSONObject) throws AMapException {
        try {
            Navi navi = new Navi();
            navi.setAction(a(jSONObject, "action"));
            navi.setAssistantAction(a(jSONObject, "assistant_action"));
            return navi;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseNavi");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static List<TMC> c(JSONArray jSONArray) throws AMapException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                TMC tmc = new TMC();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    tmc.setDistance(p(a(optJSONObject, "tmc_distance")));
                    tmc.setStatus(a(optJSONObject, "tmc_status"));
                    tmc.setPolyline(d(optJSONObject, "tmc_polyline"));
                    arrayList.add(tmc);
                }
            } catch (JSONException e) {
                fd.a(e, swh.e, "parseTMCsV5");
                throw new AMapException("协议解析错误 - ProtocolException");
            }
        }
        return arrayList;
    }

    private static void a(DriveStep driveStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("tmcs");
            if (optJSONArray == null) {
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                TMC tmc = new TMC();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    tmc.setDistance(p(a(optJSONObject, "distance")));
                    tmc.setStatus(a(optJSONObject, "status"));
                    tmc.setPolyline(d(optJSONObject, "polyline"));
                    arrayList.add(tmc);
                }
            }
            driveStep.setTMCs(arrayList);
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseTMCs");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static List<RouteSearchCity> d(JSONArray jSONArray) throws AMapException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                RouteSearchCity routeSearchCity = new RouteSearchCity();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    routeSearchCity.setSearchCityName(a(optJSONObject, "name"));
                    routeSearchCity.setSearchCitycode(a(optJSONObject, "citycode"));
                    routeSearchCity.setSearchCityhAdCode(a(optJSONObject, "adcode"));
                    a(routeSearchCity, optJSONObject);
                    arrayList.add(routeSearchCity);
                }
            } catch (JSONException e) {
                fd.a(e, swh.e, "parseCrossCity");
                throw new AMapException("协议解析错误 - ProtocolException");
            }
        }
        return arrayList;
    }

    private static void b(DriveStep driveStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray == null) {
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                RouteSearchCity routeSearchCity = new RouteSearchCity();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    routeSearchCity.setSearchCityName(a(optJSONObject, "name"));
                    routeSearchCity.setSearchCitycode(a(optJSONObject, "citycode"));
                    routeSearchCity.setSearchCityhAdCode(a(optJSONObject, "adcode"));
                    a(routeSearchCity, optJSONObject);
                    arrayList.add(routeSearchCity);
                }
            }
            driveStep.setRouteSearchCityList(arrayList);
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseCrossCity");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static void a(RouteSearchCity routeSearchCity, JSONObject jSONObject) throws AMapException {
        if (jSONObject.has("districts")) {
            try {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("districts");
                if (optJSONArray == null) {
                    routeSearchCity.setDistricts(arrayList);
                    return;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    District district = new District();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        district.setDistrictName(a(optJSONObject, "name"));
                        district.setDistrictAdcode(a(optJSONObject, "adcode"));
                        arrayList.add(district);
                    }
                }
                routeSearchCity.setDistricts(arrayList);
            } catch (JSONException e) {
                fd.a(e, swh.e, "parseCrossDistricts");
                throw new AMapException("协议解析错误 - ProtocolException");
            }
        }
    }

    public static WalkRouteResult d(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            WalkRouteResult walkRouteResult = new WalkRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            walkRouteResult.setStartPos(c(optJSONObject, "origin"));
            walkRouteResult.setTargetPos(c(optJSONObject, "destination"));
            if (!optJSONObject.has("paths")) {
                return walkRouteResult;
            }
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
            if (optJSONArray == null) {
                walkRouteResult.setPaths(arrayList);
                return walkRouteResult;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                WalkPath walkPath = new WalkPath();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    walkPath.setDistance(q(a(optJSONObject2, "distance")));
                    walkPath.setDuration(s(a(optJSONObject2, "duration")));
                    if (optJSONObject2.has(MedalConstants.EVENT_STEPS)) {
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray(MedalConstants.EVENT_STEPS);
                        ArrayList arrayList2 = new ArrayList();
                        if (optJSONArray2 != null) {
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                WalkStep walkStep = new WalkStep();
                                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                                if (optJSONObject3 != null) {
                                    walkStep.setInstruction(a(optJSONObject3, "instruction"));
                                    walkStep.setOrientation(a(optJSONObject3, ParamConstants.Param.ORIENTATION));
                                    walkStep.setRoad(a(optJSONObject3, "road"));
                                    walkStep.setDistance(q(a(optJSONObject3, "distance")));
                                    walkStep.setDuration(q(a(optJSONObject3, "duration")));
                                    walkStep.setPolyline(d(optJSONObject3, "polyline"));
                                    walkStep.setAction(a(optJSONObject3, "action"));
                                    walkStep.setAssistantAction(a(optJSONObject3, "assistant_action"));
                                    arrayList2.add(walkStep);
                                }
                            }
                            walkPath.setSteps(arrayList2);
                            a(walkPath, arrayList2);
                        }
                    }
                    arrayList.add(walkPath);
                }
            }
            walkRouteResult.setPaths(arrayList);
            return walkRouteResult;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseWalkRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static LocalWeatherLive e(String str) throws AMapException {
        JSONObject optJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("lives")) {
                return null;
            }
            LocalWeatherLive localWeatherLive = new LocalWeatherLive();
            JSONArray optJSONArray = jSONObject.optJSONArray("lives");
            if (optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
                return localWeatherLive;
            }
            localWeatherLive.setAdCode(a(optJSONObject, "adcode"));
            localWeatherLive.setProvince(a(optJSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
            localWeatherLive.setCity(a(optJSONObject, DistrictSearchQuery.KEYWORDS_CITY));
            localWeatherLive.setWeather(a(optJSONObject, HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER));
            localWeatherLive.setTemperature(a(optJSONObject, HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE));
            localWeatherLive.setWindDirection(a(optJSONObject, "winddirection"));
            localWeatherLive.setWindPower(a(optJSONObject, "windpower"));
            localWeatherLive.setHumidity(a(optJSONObject, "humidity"));
            localWeatherLive.setReportTime(a(optJSONObject, "reporttime"));
            return localWeatherLive;
        } catch (JSONException e) {
            fd.a(e, swh.e, "WeatherForecastResult");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static LocalWeatherForecast f(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("forecasts")) {
                return null;
            }
            LocalWeatherForecast localWeatherForecast = new LocalWeatherForecast();
            JSONArray jSONArray = jSONObject.getJSONArray("forecasts");
            if (jSONArray != null && jSONArray.length() > 0) {
                JSONObject optJSONObject = jSONArray.optJSONObject(0);
                if (optJSONObject == null) {
                    return localWeatherForecast;
                }
                localWeatherForecast.setCity(a(optJSONObject, DistrictSearchQuery.KEYWORDS_CITY));
                localWeatherForecast.setAdCode(a(optJSONObject, "adcode"));
                localWeatherForecast.setProvince(a(optJSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
                localWeatherForecast.setReportTime(a(optJSONObject, "reporttime"));
                if (!optJSONObject.has("casts")) {
                    return localWeatherForecast;
                }
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = optJSONObject.optJSONArray("casts");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        LocalDayWeatherForecast localDayWeatherForecast = new LocalDayWeatherForecast();
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null) {
                            localDayWeatherForecast.setDate(a(optJSONObject2, "date"));
                            localDayWeatherForecast.setWeek(a(optJSONObject2, "week"));
                            localDayWeatherForecast.setDayWeather(a(optJSONObject2, "dayweather"));
                            localDayWeatherForecast.setNightWeather(a(optJSONObject2, "nightweather"));
                            localDayWeatherForecast.setDayTemp(a(optJSONObject2, "daytemp"));
                            localDayWeatherForecast.setNightTemp(a(optJSONObject2, "nighttemp"));
                            localDayWeatherForecast.setDayWindDirection(a(optJSONObject2, "daywind"));
                            localDayWeatherForecast.setNightWindDirection(a(optJSONObject2, "nightwind"));
                            localDayWeatherForecast.setDayWindPower(a(optJSONObject2, "daypower"));
                            localDayWeatherForecast.setNightWindPower(a(optJSONObject2, "nightpower"));
                            arrayList.add(localDayWeatherForecast);
                        }
                    }
                    localWeatherForecast.setWeatherForecast(arrayList);
                    return localWeatherForecast;
                }
                localWeatherForecast.setWeatherForecast(arrayList);
            }
            return localWeatherForecast;
        } catch (JSONException e) {
            fd.a(e, swh.e, "WeatherForecastResult");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject == null || !jSONObject.has(str) || jSONObject.optString(str).equals("[]")) ? "" : jSONObject.optString(str).trim();
    }

    private static int b(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str)) {
            return jSONObject.optInt(str);
        }
        return -1;
    }

    private static LatLonPoint c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str)) {
            return n(jSONObject.optString(str));
        }
        return null;
    }

    private static ArrayList<LatLonPoint> d(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return m(jSONObject.optString(str));
        }
        return null;
    }

    private static ArrayList<LatLonPoint> m(String str) {
        ArrayList<LatLonPoint> arrayList = new ArrayList<>();
        for (String str2 : str.split(";")) {
            arrayList.add(n(str2));
        }
        return arrayList;
    }

    private static LatLonPoint n(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return null;
        }
        String[] split = str.split(",| ");
        if (split.length != 2) {
            return null;
        }
        return new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
    }

    private static boolean o(String str) {
        return str == null || str.equals("") || str.equals("0");
    }

    public static boolean g(String str) {
        return str == null || str.equals("");
    }

    private static int p(String str) {
        if (str != null && !str.equals("") && !str.equals("[]")) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                fd.a(e, swh.e, "str2int");
            }
        }
        return 0;
    }

    private static float q(String str) {
        if (str != null && !str.equals("") && !str.equals("[]")) {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException e) {
                fd.a(e, swh.e, "str2float");
            }
        }
        return 0.0f;
    }

    private static double r(String str) {
        if (str != null && !str.equals("") && !str.equals("[]")) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                fd.a(e, swh.e, "str2float");
            }
        }
        return 0.0d;
    }

    private static long s(String str) {
        if (str != null && !str.equals("") && !str.equals("[]")) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                fd.a(e, swh.e, "str2long");
            }
        }
        return 0L;
    }

    private static boolean t(String str) {
        return (str == null || str.equals("") || str.equals("[]") || str.equals("0") || !str.equals("1")) ? false : true;
    }

    private static IndoorData e(JSONObject jSONObject, String str) throws JSONException {
        String str2;
        int i;
        String str3;
        JSONObject optJSONObject;
        if (jSONObject.has(str) && (optJSONObject = jSONObject.optJSONObject(str)) != null && optJSONObject.has("cpid") && optJSONObject.has("floor")) {
            str2 = a(optJSONObject, "cpid");
            i = p(a(optJSONObject, "floor"));
            str3 = a(optJSONObject, "truefloor");
        } else {
            str2 = "";
            i = 0;
            str3 = "";
        }
        return new IndoorData(str2, i, str3);
    }

    public static void c(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            AoiItem aoiItem = new AoiItem();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                aoiItem.setId(a(optJSONObject, "id"));
                aoiItem.setName(a(optJSONObject, "name"));
                aoiItem.setAdcode(a(optJSONObject, "adcode"));
                aoiItem.setLocation(c(optJSONObject, "location"));
                aoiItem.setArea(Float.valueOf(q(a(optJSONObject, "area"))));
                arrayList.add(aoiItem);
            }
        }
        regeocodeAddress.setAois(arrayList);
    }

    private static void a(PoiItem poiItem, JSONObject jSONObject) throws JSONException {
        List<Photo> H = H(jSONObject.optJSONObject("deep_info"));
        if (H.size() == 0) {
            H = H(jSONObject);
        }
        poiItem.setPhotos(H);
    }

    private static List<Photo> H(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || !jSONObject.has("photos")) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("photos");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            Photo photo = new Photo();
            photo.setTitle(a(optJSONObject, "title"));
            photo.setUrl(a(optJSONObject, "url"));
            arrayList.add(photo);
        }
        return arrayList;
    }

    private static PoiItemExtension f(JSONObject jSONObject, String str) throws JSONException {
        String str2;
        String str3;
        JSONObject optJSONObject;
        if (!jSONObject.has(str) || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            str2 = "";
            str3 = "";
        } else {
            str2 = a(optJSONObject, "open_time");
            str3 = a(optJSONObject, "rating");
        }
        return new PoiItemExtension(str2, str3);
    }

    public static ArrayList<RoutePOIItem> i(JSONObject jSONObject) throws JSONException {
        ArrayList<RoutePOIItem> arrayList = new ArrayList<>();
        Object opt = jSONObject.opt("pois");
        if (opt instanceof JSONArray) {
            JSONArray optJSONArray = jSONObject.optJSONArray("pois");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(I(optJSONObject));
                }
            }
        } else if (opt instanceof JSONObject) {
            arrayList.add(I(((JSONObject) opt).optJSONObject("poi")));
        }
        return arrayList;
    }

    private static RoutePOIItem I(JSONObject jSONObject) throws JSONException {
        RoutePOIItem routePOIItem = new RoutePOIItem();
        routePOIItem.setID(a(jSONObject, "id"));
        routePOIItem.setTitle(a(jSONObject, "name"));
        routePOIItem.setPoint(c(jSONObject, "location"));
        routePOIItem.setDistance(q(a(jSONObject, "distance")));
        routePOIItem.setDuration(q(a(jSONObject, "duration")));
        return routePOIItem;
    }

    public static RideRouteResult h(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return null;
            }
            RideRouteResult rideRouteResult = new RideRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            rideRouteResult.setStartPos(c(optJSONObject, "origin"));
            rideRouteResult.setTargetPos(c(optJSONObject, "destination"));
            ArrayList arrayList = new ArrayList();
            Object opt = optJSONObject.opt("paths");
            if (opt == null) {
                rideRouteResult.setPaths(arrayList);
                return rideRouteResult;
            }
            if (opt instanceof JSONArray) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RidePath J = J(optJSONArray.optJSONObject(i));
                    if (J != null) {
                        arrayList.add(J);
                    }
                }
            } else if (opt instanceof JSONObject) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("paths");
                if (!optJSONObject2.has(BleConstants.KEY_PATH)) {
                    rideRouteResult.setPaths(arrayList);
                    return rideRouteResult;
                }
                RidePath J2 = J(optJSONObject2.optJSONObject(BleConstants.KEY_PATH));
                if (J2 != null) {
                    arrayList.add(J2);
                }
            }
            rideRouteResult.setPaths(arrayList);
            return rideRouteResult;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseRideRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static RidePath J(JSONObject jSONObject) throws AMapException {
        RidePath ridePath = new RidePath();
        if (jSONObject == null) {
            return null;
        }
        try {
            ridePath.setDistance(q(a(jSONObject, "distance")));
            ridePath.setDuration(s(a(jSONObject, "duration")));
            if (jSONObject.has(MedalConstants.EVENT_STEPS)) {
                JSONArray optJSONArray = jSONObject.optJSONArray(MedalConstants.EVENT_STEPS);
                ArrayList arrayList = new ArrayList();
                if (optJSONArray == null) {
                    return null;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RideStep rideStep = new RideStep();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        rideStep.setInstruction(a(optJSONObject, "instruction"));
                        rideStep.setOrientation(a(optJSONObject, ParamConstants.Param.ORIENTATION));
                        rideStep.setRoad(a(optJSONObject, "road"));
                        rideStep.setDistance(q(a(optJSONObject, "distance")));
                        rideStep.setDuration(q(a(optJSONObject, "duration")));
                        rideStep.setPolyline(d(optJSONObject, "polyline"));
                        rideStep.setAction(a(optJSONObject, "action"));
                        rideStep.setAssistantAction(a(optJSONObject, "assistant_action"));
                        arrayList.add(rideStep);
                    }
                }
                ridePath.setSteps(arrayList);
                d(ridePath, arrayList);
            }
            return ridePath;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseRidePath");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static DistanceResult i(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(ParsedFieldTag.RESULTS)) {
                return null;
            }
            DistanceResult distanceResult = new DistanceResult();
            JSONArray optJSONArray = jSONObject.optJSONArray(ParsedFieldTag.RESULTS);
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                DistanceItem distanceItem = new DistanceItem();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                distanceItem.setOriginId(p(a(jSONObject2, "origin_id")));
                distanceItem.setDestId(p(a(jSONObject2, "dest_id")));
                distanceItem.setDistance(q(a(jSONObject2, "distance")));
                distanceItem.setDuration(q(a(jSONObject2, "duration")));
                String a2 = a(jSONObject2, CloudParamKeys.INFO);
                if (!TextUtils.isEmpty(a2)) {
                    distanceItem.setErrorInfo(a2);
                    distanceItem.setErrorCode(p(a(jSONObject2, "code")));
                }
                arrayList.add(distanceItem);
            }
            distanceResult.setDistanceResults(arrayList);
            return distanceResult;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseRouteDistance");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static TruckRouteRestult j(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return null;
            }
            TruckRouteRestult truckRouteRestult = new TruckRouteRestult();
            JSONObject optJSONObject = jSONObject.optJSONObject("data").optJSONObject("route");
            truckRouteRestult.setStartPos(c(optJSONObject, "origin"));
            truckRouteRestult.setTargetPos(c(optJSONObject, "destination"));
            if (!optJSONObject.has("paths") || (optJSONArray = optJSONObject.optJSONArray("paths")) == null) {
                return truckRouteRestult;
            }
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                TruckPath truckPath = new TruckPath();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                truckPath.setDistance(q(a(jSONObject2, "distance")));
                truckPath.setDuration(s(a(jSONObject2, "duration")));
                truckPath.setStrategy(a(jSONObject2, "strategy"));
                truckPath.setTolls(q(a(jSONObject2, "tolls")));
                truckPath.setTollDistance(q(a(jSONObject2, "toll_distance")));
                truckPath.setTotalTrafficlights(p(a(jSONObject2, "traffic_lights")));
                truckPath.setRestriction(p(a(jSONObject2, "restriction")));
                JSONArray optJSONArray2 = jSONObject2.optJSONArray(MedalConstants.EVENT_STEPS);
                if (optJSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        TruckStep truckStep = new TruckStep();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            truckStep.setInstruction(a(optJSONObject2, "instruction"));
                            truckStep.setOrientation(a(optJSONObject2, ParamConstants.Param.ORIENTATION));
                            truckStep.setRoad(a(optJSONObject2, "road"));
                            truckStep.setDistance(q(a(optJSONObject2, "distance")));
                            truckStep.setTolls(q(a(optJSONObject2, "tolls")));
                            truckStep.setTollDistance(q(a(optJSONObject2, "toll_distance")));
                            truckStep.setTollRoad(a(optJSONObject2, "toll_road"));
                            truckStep.setDuration(q(a(optJSONObject2, "duration")));
                            truckStep.setPolyline(d(optJSONObject2, "polyline"));
                            truckStep.setAction(a(optJSONObject2, "action"));
                            truckStep.setAssistantAction(a(optJSONObject2, "assistant_action"));
                            a(truckStep, optJSONObject2);
                            b(truckStep, optJSONObject2);
                            arrayList2.add(truckStep);
                        }
                    }
                    truckPath.setSteps(arrayList2);
                    arrayList.add(truckPath);
                }
            }
            truckRouteRestult.setPaths(arrayList);
            return truckRouteRestult;
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseTruckRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static void a(TruckStep truckStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray == null) {
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                RouteSearchCity routeSearchCity = new RouteSearchCity();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    routeSearchCity.setSearchCityName(a(optJSONObject, "name"));
                    routeSearchCity.setSearchCitycode(a(optJSONObject, "citycode"));
                    routeSearchCity.setSearchCityhAdCode(a(optJSONObject, "adcode"));
                    a(routeSearchCity, optJSONObject);
                    arrayList.add(routeSearchCity);
                }
            }
            truckStep.setRouteSearchCityList(arrayList);
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseCrossCity");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    private static void b(TruckStep truckStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("tmcs");
            if (optJSONArray == null) {
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                TMC tmc = new TMC();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    tmc.setDistance(p(a(optJSONObject, "distance")));
                    tmc.setStatus(a(optJSONObject, "status"));
                    tmc.setPolyline(d(optJSONObject, "polyline"));
                    arrayList.add(tmc);
                }
            }
            truckStep.setTMCs(arrayList);
        } catch (JSONException e) {
            fd.a(e, swh.e, "parseTMCs");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x01a4 A[Catch: all -> 0x021b, JSONException -> 0x021d, TryCatch #5 {JSONException -> 0x021d, all -> 0x021b, blocks: (B:41:0x00cf, B:43:0x00e2, B:55:0x00eb, B:57:0x00fa, B:61:0x0102, B:64:0x010f, B:67:0x0116, B:68:0x011c, B:70:0x0122, B:72:0x012d, B:74:0x0134, B:76:0x0147, B:77:0x014d, B:79:0x0153, B:81:0x015e, B:87:0x0199, B:89:0x01a4, B:90:0x01ac, B:92:0x01b2, B:94:0x01bf, B:96:0x01e2, B:100:0x01e9, B:102:0x01f6, B:107:0x01ff, B:109:0x020e, B:115:0x0217), top: B:40:0x00cf }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.amap.api.services.route.DriveRoutePlanResult k(java.lang.String r20) throws com.amap.api.services.core.AMapException {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.fl.k(java.lang.String):com.amap.api.services.route.DriveRoutePlanResult");
    }

    private static void a(Path path, List<WalkStep> list) {
        List<LatLonPoint> polyline = path.getPolyline();
        if (polyline == null) {
            polyline = new ArrayList<>();
        }
        for (WalkStep walkStep : list) {
            if (walkStep != null && walkStep.getPolyline() != null) {
                polyline.addAll(walkStep.getPolyline());
            }
        }
        path.setPolyline(polyline);
    }

    private static void b(Path path, List<DriveStep> list) {
        List<LatLonPoint> polyline = path.getPolyline();
        if (polyline == null) {
            polyline = new ArrayList<>();
        }
        for (DriveStep driveStep : list) {
            if (driveStep != null && driveStep.getPolyline() != null) {
                polyline.addAll(driveStep.getPolyline());
            }
        }
        path.setPolyline(polyline);
    }

    private static void c(Path path, List<DriveStepV2> list) {
        List<LatLonPoint> polyline = path.getPolyline();
        if (polyline == null) {
            polyline = new ArrayList<>();
        }
        for (DriveStepV2 driveStepV2 : list) {
            if (driveStepV2 != null && driveStepV2.getPolyline() != null) {
                polyline.addAll(driveStepV2.getPolyline());
            }
        }
        path.setPolyline(polyline);
    }

    private static void d(Path path, List<RideStep> list) {
        List<LatLonPoint> polyline = path.getPolyline();
        if (polyline == null) {
            polyline = new ArrayList<>();
        }
        for (RideStep rideStep : list) {
            if (rideStep != null && rideStep.getPolyline() != null) {
                polyline.addAll(rideStep.getPolyline());
            }
        }
        path.setPolyline(polyline);
    }
}
