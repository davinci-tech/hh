package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.featureuserprofile.route.bean.KomootRouteBean;
import com.huawei.featureuserprofile.route.bean.KomootRouteData;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcloudmodel.model.unite.RouteExtendData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.route.GetRouteDetailRsp;
import health.compact.a.UnitUtil;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class btx {
    public static void a(final HashMap<String, InputStream> hashMap, final RouteResultCallBack<CloudCommonReponse> routeResultCallBack) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: btv
            @Override // java.lang.Runnable
            public final void run() {
                btx.b(hashMap, routeResultCallBack);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a9 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void b(java.util.HashMap r10, com.huawei.health.userprofilemgr.model.RouteResultCallBack r11) {
        /*
            java.lang.String r0 = "."
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "addKomootRoutes routeMaps size -->"
            r1.<init>(r2)
            int r2 = r10.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "KomootImportUtil"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r1)
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 0
        L2b:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto Lb3
            java.lang.Object r4 = r10.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.String r5 = (java.lang.String) r5
            r6 = 1
            java.lang.Object r4 = r4.getValue()     // Catch: java.text.ParseException -> L84 org.xmlpull.v1.XmlPullParserException -> L86 java.io.IOException -> L88
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch: java.text.ParseException -> L84 org.xmlpull.v1.XmlPullParserException -> L86 java.io.IOException -> L88
            com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser r7 = new com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser     // Catch: java.lang.Throwable -> L6d
            r7.<init>()     // Catch: java.lang.Throwable -> L6d
            boolean r3 = r5.contains(r0)     // Catch: java.lang.Throwable -> L6b
            if (r3 == 0) goto L59
            int r3 = r5.lastIndexOf(r0)     // Catch: java.lang.Throwable -> L6b
            int r3 = r3 + r6
            java.lang.String r3 = r5.substring(r3)     // Catch: java.lang.Throwable -> L6b
            goto L5b
        L59:
            java.lang.String r3 = "gpx"
        L5b:
            int r3 = r7.navigationParser(r4, r3)     // Catch: java.lang.Throwable -> L6b
            if (r4 == 0) goto L9a
            r4.close()     // Catch: java.text.ParseException -> L65 org.xmlpull.v1.XmlPullParserException -> L67 java.io.IOException -> L69
            goto L9a
        L65:
            r4 = move-exception
            goto L8d
        L67:
            r4 = move-exception
            goto L8d
        L69:
            r4 = move-exception
            goto L8d
        L6b:
            r3 = move-exception
            goto L71
        L6d:
            r7 = move-exception
            r9 = r7
            r7 = r3
            r3 = r9
        L71:
            if (r4 == 0) goto L7b
            r4.close()     // Catch: java.lang.Throwable -> L77
            goto L7b
        L77:
            r4 = move-exception
            r3.addSuppressed(r4)     // Catch: java.text.ParseException -> L7c org.xmlpull.v1.XmlPullParserException -> L7e java.io.IOException -> L80
        L7b:
            throw r3     // Catch: java.text.ParseException -> L7c org.xmlpull.v1.XmlPullParserException -> L7e java.io.IOException -> L80
        L7c:
            r3 = move-exception
            goto L81
        L7e:
            r3 = move-exception
            goto L81
        L80:
            r3 = move-exception
        L81:
            r4 = r3
            r3 = r7
            goto L89
        L84:
            r4 = move-exception
            goto L89
        L86:
            r4 = move-exception
            goto L89
        L88:
            r4 = move-exception
        L89:
            r7 = 0
            r9 = r7
            r7 = r3
            r3 = r9
        L8d:
            java.lang.String r8 = "IOException | XmlPullParserException | ParseException = "
            java.lang.String r4 = r4.getMessage()
            java.lang.Object[] r4 = new java.lang.Object[]{r8, r4}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r4)
        L9a:
            r4 = r3
            r3 = r7
            if (r3 == 0) goto La9
            if (r4 == r6) goto La1
            goto La9
        La1:
            com.huawei.hwcloudmodel.model.unite.RouteData r4 = a(r3, r5)
            r1.add(r4)
            goto L2b
        La9:
            java.lang.String r10 = "parser is error"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r10)
            return
        Lb3:
            defpackage.bto.d(r1, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btx.b(java.util.HashMap, com.huawei.health.userprofilemgr.model.RouteResultCallBack):void");
    }

    private static RouteData a(NavigationFileParser navigationFileParser, String str) {
        RouteData routeData = new RouteData();
        String c = c(str, 2);
        routeData.setRouteName(c);
        String sportType = navigationFileParser.getSportType();
        if (!TextUtils.isEmpty(sportType)) {
            c = sportType;
        }
        routeData.setSportType(c);
        routeData.setRoutePoints(sqm.e(navigationFileParser.getRouteType(), navigationFileParser.getPoints()));
        routeData.setRouteDistance(navigationFileParser.getSportTotalDistance());
        routeData.setRouteTime(navigationFileParser.getSportTotalTime());
        routeData.setType(3);
        routeData.setRouteType(navigationFileParser.getRouteType());
        routeData.setExtendData(g(str));
        return routeData;
    }

    private static String c(String str, int i) {
        int i2 = 0;
        while (i2 < i) {
            if (str.lastIndexOf("_") != -1) {
                str = str.substring(0, str.lastIndexOf("_"));
                i2++;
            }
        }
        return str;
    }

    private static String g(String str) {
        RouteExtendData routeExtendData = new RouteExtendData();
        int length = c(str, 2).length() + 1;
        routeExtendData.setRouteId(str.substring(length, str.lastIndexOf("_") == -1 ? length : str.lastIndexOf("_")));
        return new Gson().toJson(routeExtendData);
    }

    public static KomootRouteBean a(String str) {
        KomootRouteBean komootRouteBean = new KomootRouteBean();
        JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
        if (asJsonObject.has("_embedded")) {
            JsonObject asJsonObject2 = asJsonObject.get("_embedded").getAsJsonObject();
            if (!asJsonObject2.has("tours")) {
                LogUtil.h("KomootImportUtil", "parse error because json data has no FIELD_TOURS");
                return komootRouteBean;
            }
            e(komootRouteBean, asJsonObject2);
        }
        b(komootRouteBean, asJsonObject);
        return komootRouteBean;
    }

    private static void e(KomootRouteBean komootRouteBean, JsonObject jsonObject) {
        JsonArray asJsonArray = jsonObject.getAsJsonArray("tours");
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = asJsonArray.iterator();
        while (it.hasNext()) {
            JsonElement next = it.next();
            KomootRouteBean.Tour tour = new KomootRouteBean.Tour();
            if (next instanceof JsonObject) {
                JsonObject jsonObject2 = (JsonObject) next;
                tour.setId(jsonObject2.has("id") ? jsonObject2.get("id").getAsLong() : 0L);
                tour.setSport(jsonObject2.has("sport") ? jsonObject2.get("sport").getAsString() : "");
                tour.setDate(jsonObject2.has("date") ? jsonObject2.get("date").getAsString() : "");
                tour.setName(jsonObject2.has("name") ? jsonObject2.get("name").getAsString() : "");
                tour.setDistance(Double.valueOf(jsonObject2.has("distance") ? jsonObject2.get("distance").getAsDouble() : 0.0d));
                tour.setDuration(jsonObject2.has("duration") ? jsonObject2.get("duration").getAsLong() : 0L);
                if (jsonObject2.has("map_image_preview")) {
                    JsonObject asJsonObject = jsonObject2.get("map_image_preview").getAsJsonObject();
                    tour.setPreviewSrc(asJsonObject.has("src") ? asJsonObject.get("src").getAsString() : "");
                }
                arrayList.add(tour);
            }
        }
        komootRouteBean.setTours(arrayList);
    }

    private static void b(KomootRouteBean komootRouteBean, JsonObject jsonObject) {
        if (jsonObject.has("page")) {
            JsonObject asJsonObject = jsonObject.get("page").getAsJsonObject();
            komootRouteBean.setTotalElements(asJsonObject.has("totalElements") ? asJsonObject.get("totalElements").getAsInt() : 0);
            komootRouteBean.setTotalPages(asJsonObject.has("totalPages") ? asJsonObject.get("totalPages").getAsInt() : 0);
            komootRouteBean.setPageNumber(asJsonObject.has("number") ? asJsonObject.get("number").getAsInt() : 0);
        }
    }

    public static String e(Context context, String str) {
        if ("All".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130838844_res_0x7f02053c);
        }
        if ("jogging".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848905_res_0x7f022c89);
        }
        if ("touringbicycle".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848906_res_0x7f022c8a);
        }
        if ("e_touringbicycle".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848908_res_0x7f022c8c);
        }
        if ("mountaineering".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848909_res_0x7f022c8d);
        }
        if ("racebike".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848910_res_0x7f022c8e);
        }
        if ("e_racebike".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848911_res_0x7f022c8f);
        }
        if ("mtb".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130850279_res_0x7f0231e7);
        }
        if ("e_mtb".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848912_res_0x7f022c90);
        }
        if ("mtb_easy".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130850280_res_0x7f0231e8);
        }
        if ("e_mtb_easy".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848913_res_0x7f022c91);
        }
        if ("mtb_advanced".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848914_res_0x7f022c92);
        }
        if ("e_mtb_advanced".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130850278_res_0x7f0231e6);
        }
        if ("hike".equalsIgnoreCase(str)) {
            return context.getResources().getString(R.string._2130848907_res_0x7f022c8b);
        }
        return b(str);
    }

    public static String b(String str) {
        char[] charArray = str.toCharArray();
        charArray[0] = (char) (charArray[0] - ' ');
        return String.valueOf(charArray);
    }

    public static List<KomootRouteData> e(KomootRouteBean komootRouteBean, List<Long> list) {
        ArrayList arrayList = new ArrayList();
        List<KomootRouteBean.Tour> tours = komootRouteBean.getTours();
        if (koq.b(tours)) {
            return arrayList;
        }
        Iterator<KomootRouteBean.Tour> it = tours.iterator();
        while (it.hasNext()) {
            arrayList.add(new KomootRouteData(0, it.next()));
        }
        return c(arrayList, list);
    }

    private static List<KomootRouteData> c(List<KomootRouteData> list, List<Long> list2) {
        KomootRouteBean.Tour data;
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.h("KomootImportUtil", "filterRouteList param list is empty , return the original");
            return list;
        }
        Iterator<KomootRouteData> it = list.iterator();
        while (it.hasNext()) {
            KomootRouteData next = it.next();
            if (next != null && (data = next.getData()) != null && list2.contains(Long.valueOf(data.getId()))) {
                StringBuilder sb = new StringBuilder("filterRouteList remove:");
                sb.append(e(data.getName()));
                sb.append(" - ");
                sb.append(e(data.getId() + ""));
                LogUtil.c("KomootImportUtil", sb.toString());
                it.remove();
            }
        }
        return list;
    }

    public static List<Long> a(List<RouteData> list) {
        ArrayList arrayList = new ArrayList();
        for (RouteData routeData : list) {
            if (routeData.getType() != 3) {
                LogUtil.h("KomootImportUtil", "getKomootUploadedRouteIds continue for not Komoot route:" + routeData.toString());
            } else if (TextUtils.isEmpty(routeData.getRouteId())) {
                LogUtil.h("KomootImportUtil", "getKomootUploadedRouteIds continue for id is empty :" + routeData.toString());
            } else {
                LogUtil.c("KomootImportUtil", "getKomootUploadedRouteIds id :" + e(routeData.getRouteId()));
                arrayList.add(Long.valueOf(routeData.getRouteId()));
            }
        }
        return arrayList;
    }

    public static String d(KomootRouteBean.Tour tour, String str) {
        StringBuilder sb = new StringBuilder();
        if (tour != null) {
            sb.append(tour.getName());
            sb.append(str);
            sb.append("_");
            sb.append(tour.getId());
            sb.append("_komoot.gpx");
        }
        return sb.toString();
    }

    public static String uP_(String str, ImageView imageView) {
        return imageView == null ? str : str.replace("{width}", Integer.toString(imageView.getWidth())).replace("{height}", Integer.toString(imageView.getHeight())).replace("&crop={crop}", "");
    }

    public static String d(String str) {
        if (str == null) {
            return "";
        }
        return nsj.a(ggl.e(str.trim().substring(0, str.contains(ExifInterface.GPS_DIRECTION_TRUE) ? str.indexOf(ExifInterface.GPS_DIRECTION_TRUE) : str.length()), "yyyy-MM-dd").getTime());
    }

    public static String c(String str) {
        if (str == null) {
            return "";
        }
        return str.trim().substring(0, str.contains(ExifInterface.GPS_DIRECTION_TRUE) ? str.indexOf(ExifInterface.GPS_DIRECTION_TRUE) : str.length());
    }

    public static String e(Context context, long j) {
        return context.getResources().getString(R.string._2130839551_res_0x7f0207ff, Integer.valueOf(Double.valueOf(new BigDecimal(Double.toString(j)).divide(new BigDecimal(Double.toString(60.0d)), 1, 4).doubleValue()).intValue()));
    }

    public static String d(Context context, double d) {
        if (context == null) {
            LogUtil.a("KomootImportUtil", "getFormatDistance context is null");
            return "";
        }
        double doubleValue = new BigDecimal(Double.toString(d)).divide(new BigDecimal(1000.0d), 2, 4).doubleValue();
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903095_res_0x7f030037, (int) UnitUtil.e(doubleValue, 3), UnitUtil.e(doubleValue, 1, 2));
        }
        return context.getResources().getQuantityString(R.plurals._2130903096_res_0x7f030038, (int) doubleValue, UnitUtil.e(doubleValue, 1, 2));
    }

    public static String e(String str) {
        if (str.isEmpty() || str.length() <= 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.length() <= 3) {
            return sb.replace(1, str.length() - 1, Constants.CONFUSION_CHARS).toString();
        }
        return sb.replace(1, str.length() - 3, Constants.CONFUSION_CHARS).toString();
    }

    public static void b(RouteResultCallBack<GetRouteDetailRsp> routeResultCallBack) {
        bto.e(0, 0L, routeResultCallBack);
    }
}
