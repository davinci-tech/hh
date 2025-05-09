package com.huawei.openalliance.ad.constant;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApiWhiteList;
import com.huawei.openalliance.ad.beans.metadata.TrustAppList;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.af;
import com.huawei.openalliance.ad.utils.be;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class WhiteListPkgList {
    private static final String APPGALLERY_PACKAGE = "com.huawei.appmarket";
    private static final String[] APPGALLERY_SIGNATURE;
    private static final String BROWSER_PACKAGE = "com.android.browser";
    private static final String[] BROWSER_SIGNATURE;
    private static final String CALENDAR_PACKAGE = "com.android.calendar";
    private static final String[] CALENDAR_SIGNATURE;
    private static final String DISCOVER_PACKAGE = "com.huawei.discover";
    private static final String[] DISCOVER_SIGNATURE;
    private static final String FASTAPP_HONOR_PACKAGE = "com.hihonor.fastapp";
    private static final String[] FASTAPP_HONOR_SIGNATURE;
    private static final String FAST_APP_DEV_PACKAGE = "com.huawei.fastapp.dev";
    private static final String FAST_APP_PACKAGE = "com.huawei.fastapp";
    private static final String[] FAST_APP_SIGNATURE;
    private static final String HEALTH_PACKAGE = "com.huawei.health";
    private static final String[] HEALTH_SIGNATURE;
    private static final String HICLOUD_BROWSER_PACKAGE = "com.hicloud.browser";
    private static final String[] HICLOUD_BROWSER_SIGNATURE;
    private static final String HIFOLER_PACKAGE = "com.huawei.hifolder";
    private static final String[] HIFOLER_SIGNATURE;
    private static final String HIMOVIE_OVERSEAS_PACKAGE = "com.huawei.himovie.overseas";
    private static final String[] HIMOVIE_OVERSEAS_SIGNATURE;
    private static final String HIMOVIE_PACKAGE = "com.huawei.himovie";
    private static final String[] HIMOVIE_SIGNATURE;
    private static final String HISEARCH_PACKAGE = "com.huawei.search";
    private static final String HISKYSTONE_PACKAGE = "com.huawei.hiskytone";
    private static final String[] HISKYSTONE_SIGNATURE;
    private static final String HONOR_BROWSER_PACKAGE = "com.hihonor.browser";
    private static final String[] HONOR_BROWSER_SIGNATURE;
    private static final String HW_BROWSER_PACKAGE = "com.huawei.browser";
    private static final String[] HW_BROWSER_SIGNATURE;
    private static final String HW_SEARCH_PACKAGE = "com.huawei.hwsearch";
    private static final String[] HW_SEARCH_SIGNATURE;
    private static final String HW_YOUKU_PACKAGE = "com.huawei.hwvplayer.youku";
    private static final String[] HW_YOUKU_SIGNATURE;
    private static final Map<String, List<String>> INNER_WHITE_LIST;
    private static final String[] INTELLIGENT_SIGNATURE;
    private static final String INTELLIGNET_PACKAGE = "com.huawei.intelligent";
    private static final String MEDIACENTER_PACKAGE = "com.android.mediacenter";
    private static final String[] MEDIACENTER_SIGNATURE;
    private static final String MUSIC_PACKAGE = "com.huawei.music";
    private static final String[] MUSIC_SIGNATURE;
    private static final String MY_CENTER_PACKAGE = "com.huawei.mycenter";
    private static final String[] MY_CENTER_SIGNATURE;
    private static final String PETAL_LITE_GAMES = "com.petal.litegames";
    private static final String[] PETAL_LITE_GAMES_SIGNATURE;
    private static final String QQ_LIVE_PACKAGE = "com.tencent.qqlivehuawei";
    private static final String[] QQ_LIVE_SIGNATURE;
    private static final String SOHU_V_PACKAGE = "com.huawei.hwvplayer";
    private static final String[] SOHU_V_SIGNATURE;
    private static final String SUGGESTION_PACKAGE = "com.huawei.ohos.suggestion";
    private static final String TAG = "WhiteListPkgList";
    private static final String THEME_PACKAGE = "com.huawei.android.thememanager";
    private static final String[] THEME_SIGNATURE;
    private static final String VASSISTANT_PACKAGE = "com.huawei.vassistant";
    private static final String[] VASSISTANT_SIGNATURE;
    private static final String VMALL_PACKAGE = "com.vmall.client";
    private static final String[] VMALL_SIGNATURE;
    private static final String WALLET_PACKAGE = "com.huawei.wallet";
    private static final String[] WALLET_SIGNATURE;
    private static final String WEATHER_PACKAGE = "com.huawei.android.totemweather";
    private static final String[] WEATHER_SIGNATURE;
    private static final List<String> needAddFlagsPkgList;

    public static boolean isTrustApp(Context context, String str, String str2) {
        boolean inWhiteList = inWhiteList(str, str2);
        return !inWhiteList ? inTrustAppList(context, str, str2) : inWhiteList;
    }

    public static boolean isPkgNeedAddFlags(String str) {
        if (str == null) {
            return false;
        }
        return needAddFlagsPkgList.contains(str);
    }

    public static boolean isHwBrowserPkgName(String str) {
        return HONOR_BROWSER_PACKAGE.equalsIgnoreCase(str) || "com.huawei.browser".equalsIgnoreCase(str) || HICLOUD_BROWSER_PACKAGE.equalsIgnoreCase(str) || BROWSER_PACKAGE.equalsIgnoreCase(str);
    }

    public static boolean inWhiteList(String str, String str2) {
        return af.a(INNER_WHITE_LIST, str, str2);
    }

    private static boolean inTrustAppList(Context context, String str, String str2) {
        String str3;
        String bL = fh.b(context).bL();
        if (TextUtils.isEmpty(bL)) {
            str3 = "inTrustAppList trustAppList is empty";
        } else {
            TrustAppList trustAppList = (TrustAppList) be.b(bL, TrustAppList.class, new Class[0]);
            if (trustAppList == null) {
                str3 = "inTrustAppList toObjectNoException is null";
            } else {
                Map<String, List<String>> a2 = trustAppList.a();
                if (a2 == null) {
                    str3 = "inTrustAppList map is null";
                } else {
                    List<String> list = a2.get(str);
                    if (list != null) {
                        return check(list, str2);
                    }
                    str3 = "inTrustAppList signList is null";
                }
            }
        }
        ho.b(TAG, str3);
        return false;
    }

    public static boolean inApiServerWhiteList(Context context, String str, String str2) {
        String str3;
        String bd = fh.b(context).bd();
        if (TextUtils.isEmpty(bd)) {
            str3 = "inApiServerWhiteList white list is empty";
        } else {
            ApiWhiteList apiWhiteList = (ApiWhiteList) be.b(bd, ApiWhiteList.class, new Class[0]);
            if (apiWhiteList == null) {
                str3 = "inApiServerWhiteList toObjectNoException is null";
            } else {
                Map<String, List<String>> a2 = apiWhiteList.a();
                if (a2 == null) {
                    str3 = "inApiServerWhiteList map is null";
                } else {
                    List<String> list = a2.get(str);
                    if (list != null) {
                        return check(list, str2);
                    }
                    str3 = "inApiServerWhiteList signList is null";
                }
            }
        }
        ho.b(TAG, str3);
        return false;
    }

    private static boolean check(List<String> list, String str) {
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
        }
        ho.a(TAG, "check false!");
        return false;
    }

    public static boolean accInitPkgList(Context context) {
        if (context == null) {
            return false;
        }
        String packageName = context.getPackageName();
        return HISEARCH_PACKAGE.equals(packageName) || "com.huawei.ohos.suggestion".equals(packageName);
    }

    static {
        String[] strArr = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05", "59321357AB0A6BACBE3D32665B0084DCBB709B1D234EC684431AAEC5A0F0B8B1"};
        FAST_APP_SIGNATURE = strArr;
        String[] strArr2 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        INTELLIGENT_SIGNATURE = strArr2;
        String[] strArr3 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"};
        MEDIACENTER_SIGNATURE = strArr3;
        String[] strArr4 = {"3e13f630c77618de3a580dbbaffe0ac04a16444633cc0253afb088d3e3ab6efe"};
        MUSIC_SIGNATURE = strArr4;
        String[] strArr5 = {"7031f6af3a934c09ba00f0196c68e7a0e6f2f948a2db7becb3b15c303cf02bde"};
        HW_YOUKU_SIGNATURE = strArr5;
        String[] strArr6 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05", "595103FD70B6793C64D85343D1549CC7AD4F508F0D310F19D07A50A1C2CE7BA4"};
        HIMOVIE_SIGNATURE = strArr6;
        String[] strArr7 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"};
        HISKYSTONE_SIGNATURE = strArr7;
        String[] strArr8 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"};
        HEALTH_SIGNATURE = strArr8;
        String[] strArr9 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"};
        WALLET_SIGNATURE = strArr9;
        String[] strArr10 = {"2771bcfe40c0f6194ce52701daad4efa0f8c380c844e83081e4592f0b13163e5"};
        CALENDAR_SIGNATURE = strArr10;
        String[] strArr11 = {"50787dff857ccc7423352c5273275ad14b21f2b977ca3c124cf4684c1a9bc05c"};
        WEATHER_SIGNATURE = strArr11;
        String[] strArr12 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        THEME_SIGNATURE = strArr12;
        String[] strArr13 = {"bbe2ff269828a0d922498ee87f65afe769c27d62f489d5c19b9cc6c444c80811", "d8a4db56b7ebc39fe5f3004215f0e0decb43b9cfcbe9b2d948383fedd434e7d9", "1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        BROWSER_SIGNATURE = strArr13;
        String[] strArr14 = {"0a612d7aac96ac5d85f1c4a3768dd3ca7032681bae68e0dd7009bd25ad1fa3a0"};
        VMALL_SIGNATURE = strArr14;
        String[] strArr15 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        HW_BROWSER_SIGNATURE = strArr15;
        String[] strArr16 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        MY_CENTER_SIGNATURE = strArr16;
        String[] strArr17 = {"7031f6af3a934c09ba00f0196c68e7a0e6f2f948a2db7becb3b15c303cf02bde"};
        SOHU_V_SIGNATURE = strArr17;
        String[] strArr18 = {"b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"};
        HIMOVIE_OVERSEAS_SIGNATURE = strArr18;
        String[] strArr19 = {"94519206e8823c6373dd7998f4eddc4c717c6bbdd8f102766df7efe77a35c56a"};
        QQ_LIVE_SIGNATURE = strArr19;
        String[] strArr20 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        HIFOLER_SIGNATURE = strArr20;
        String[] strArr21 = {"ffe391e0ea186d0734ed601e4e70e3224b7309d48e2075bac46d8c667eae7212", "3baf59a2e5331c30675fab35ff5fff0d116142d3d4664f1c3cb804068b40614f", "a9436644e0bd71ff512c63839f8ac27114399f36956958688555dfcc63257ede"};
        APPGALLERY_SIGNATURE = strArr21;
        String[] strArr22 = {"8f7dad6b598fa1d5f4c3164f7de6169ca173cf1c7e984e0fd5073ce632e4dbde"};
        FASTAPP_HONOR_SIGNATURE = strArr22;
        String[] strArr23 = {"fb87009b7de1fbee3d19e853f449103633c420ecc0717549cfd0fb9262a544fa"};
        DISCOVER_SIGNATURE = strArr23;
        String[] strArr24 = {"7d7408a30409cf10b80f4186267ee050e6b4ca50648abec8c2763cb51b901fa3"};
        HICLOUD_BROWSER_SIGNATURE = strArr24;
        String[] strArr25 = {"0ad6f8772f1eb7920cb62acb9bf00395f50a644b670b1b266056b5166a414cca"};
        HW_SEARCH_SIGNATURE = strArr25;
        HashMap hashMap = new HashMap();
        INNER_WHITE_LIST = hashMap;
        String[] strArr26 = {"22dcb04cfaa28f382b613794eba4441a8bcb1dbc8576776f1b1e6a457b00d449"};
        HONOR_BROWSER_SIGNATURE = strArr26;
        String[] strArr27 = {"A9436644E0BD71FF512C63839F8AC27114399F36956958688555DFCC63257EDE"};
        PETAL_LITE_GAMES_SIGNATURE = strArr27;
        String[] strArr28 = {"1e3eee2a88a6df75fb4af56adc8373bb818f3cb90a4935c7821582b8cebb694c"};
        VASSISTANT_SIGNATURE = strArr28;
        needAddFlagsPkgList = Arrays.asList("com.taobao.taobao");
        hashMap.put("com.huawei.intelligent", Arrays.asList(strArr2));
        hashMap.put(MEDIACENTER_PACKAGE, Arrays.asList(strArr3));
        hashMap.put(HW_YOUKU_PACKAGE, Arrays.asList(strArr5));
        hashMap.put(HIMOVIE_PACKAGE, Arrays.asList(strArr6));
        hashMap.put(HISKYSTONE_PACKAGE, Arrays.asList(strArr7));
        hashMap.put("com.huawei.health", Arrays.asList(strArr8));
        hashMap.put(WALLET_PACKAGE, Arrays.asList(strArr9));
        hashMap.put(CALENDAR_PACKAGE, Arrays.asList(strArr10));
        hashMap.put(WEATHER_PACKAGE, Arrays.asList(strArr11));
        hashMap.put(THEME_PACKAGE, Arrays.asList(strArr12));
        hashMap.put(BROWSER_PACKAGE, Arrays.asList(strArr13));
        hashMap.put(VMALL_PACKAGE, Arrays.asList(strArr14));
        hashMap.put("com.huawei.browser", Arrays.asList(strArr15));
        hashMap.put(MY_CENTER_PACKAGE, Arrays.asList(strArr16));
        hashMap.put(SOHU_V_PACKAGE, Arrays.asList(strArr17));
        hashMap.put(HIFOLER_PACKAGE, Arrays.asList(strArr20));
        hashMap.put("com.huawei.appmarket", Arrays.asList(strArr21));
        hashMap.put("com.huawei.hwid", Arrays.asList("b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"));
        hashMap.put("com.huawei.hwid.tv", Arrays.asList("3517262215d8d3008cbf888750b6418edc4d562ac33ed6874e0d73aba667bc3c"));
        hashMap.put("com.huawei.hms", Arrays.asList("e49d5c2c0e11b3b1b96ca56c6de2a14ec7dab5ccc3b5f300d03e5b4dba44f539"));
        hashMap.put(HIMOVIE_OVERSEAS_PACKAGE, Arrays.asList(strArr18));
        hashMap.put(QQ_LIVE_PACKAGE, Arrays.asList(strArr19));
        hashMap.put("com.huawei.fastapp", Arrays.asList(strArr));
        hashMap.put(MUSIC_PACKAGE, Arrays.asList(strArr4));
        hashMap.put(FASTAPP_HONOR_PACKAGE, Arrays.asList(strArr22));
        hashMap.put(DISCOVER_PACKAGE, Arrays.asList(strArr23));
        hashMap.put(HICLOUD_BROWSER_PACKAGE, Arrays.asList(strArr24));
        hashMap.put("com.huawei.fastapp.dev", Arrays.asList(strArr));
        hashMap.put(HONOR_BROWSER_PACKAGE, Arrays.asList(strArr26));
        hashMap.put(PETAL_LITE_GAMES, Arrays.asList(strArr27));
        hashMap.put(HW_SEARCH_PACKAGE, Arrays.asList(strArr25));
        hashMap.put(VASSISTANT_PACKAGE, Arrays.asList(strArr28));
    }
}
