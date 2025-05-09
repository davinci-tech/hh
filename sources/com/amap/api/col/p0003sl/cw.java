package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.hms.network.embedded.u3;

/* loaded from: classes2.dex */
public final class cw {

    /* renamed from: a, reason: collision with root package name */
    public static final int[][] f952a = {new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, new int[]{12}, new int[]{1}, new int[]{13}, new int[]{14}, new int[]{15, 16}, new int[]{17, 18, 19, 20, 21, 26, 27, 28}, new int[]{22, 23}, new int[]{24, 25}, new int[]{39, 40, 41}, new int[]{29, 30, 31}, new int[]{32, 33, 34, 35, 36, 37, 38}};
    public static final String[] b = {"land", "water", "green", "building", "highway", "arterial", "local", u3.j, "subway", "boundary", "poilabel", "districtlable"};
    public static final String[][] c = {new String[]{"land", "edu", "public", "traffic", "scenicSpot", "culture", "health", "sports", CardMgrSdkConst.CardInfoDesc.PARAMS_BUSINESS, "parkingLot", "subway"}, new String[]{"water"}, new String[]{"green"}, new String[]{"buildings"}, new String[]{"highWay"}, new String[]{"ringRoad", "nationalRoad"}, new String[]{"provincialRoad", "secondaryRoad", "levelThreeRoad", "levelFourRoad", "roadsBeingBuilt", "overPass", "underPass", "other"}, new String[]{u3.j, "highSpeedRailway"}, new String[]{"subwayline", "subwayBeingBuilt"}, new String[]{"China", "foreign", "provincial"}, new String[]{"guideBoards", "pois", "aois"}, new String[]{"continent", "country", DistrictSearchQuery.KEYWORDS_PROVINCE, DistrictSearchQuery.KEYWORDS_CITY, DistrictSearchQuery.KEYWORDS_DISTRICT, "town", "village"}};
    public static final String[] d = {"regions", "water", "regions", "buildings", "roads", "roads", "roads", "roads", "roads", "borders", "labels", "labels"};

    public static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (true) {
            String[] strArr = b;
            if (i >= strArr.length) {
                i = -1;
                break;
            }
            if (strArr[i].equals(str)) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            return c[i];
        }
        return null;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        while (true) {
            String[] strArr = b;
            if (i >= strArr.length) {
                i = -1;
                break;
            }
            if (strArr[i].equals(str)) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            return d[i];
        }
        return null;
    }
}
