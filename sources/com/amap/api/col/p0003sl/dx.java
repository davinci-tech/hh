package com.amap.api.col.p0003sl;

import android.content.Context;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class dx {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, dy> f980a = new ConcurrentHashMap();
    private static String b = "";

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            b();
            ik.a(dv.a()).a(context.getApplicationContext());
        } catch (Throwable unused) {
        }
    }

    private static void b() {
        try {
            f980a.put("overlay", new ea());
            f980a.put("normal", new dz());
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2) {
        a(0, "normal", b, str, str2);
    }

    public static void b(String str, String str2) {
        a(1, "normal", b, str, str2);
    }

    private static void c(String str, String str2) {
        a(1, "overlay", b, str, str2);
    }

    private static void a(int i, String str, String str2, String str3, String str4) {
        Map<String, dy> map;
        dy dyVar;
        try {
            String str5 = str3 + str4;
            if (dw.b) {
                a(i, str2, str5);
            }
            if (!dw.f979a || (map = f980a) == null || (dyVar = map.get(str)) == null) {
                return;
            }
            dyVar.a(i, str2, str5);
        } catch (Throwable unused) {
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            boolean a2 = ho.a(jSONObject.optString("able", ""), false);
            boolean a3 = ho.a(jSONObject.optString(JsNetwork.NetworkType.MOBILE, ""), false);
            boolean a4 = ho.a(jSONObject.optString("debugupload", ""), false);
            boolean a5 = ho.a(jSONObject.optString("debugwrite", ""), false);
            boolean a6 = ho.a(jSONObject.optString("forcedUpload", ""), false);
            dw.f979a = a2;
            boolean a7 = ho.a(jSONObject.optString("di", ""), false);
            String optString = jSONObject.optString("dis", "");
            if (!a7 || ia.e(optString)) {
                ik.a(dv.a()).a(a2, a3, a5, a4, Arrays.asList(jSONObject.optString(Constant.FILTER, "").split("&")));
                if (a6) {
                    ik.a(dv.a()).a(a6);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a() {
        try {
            if (dw.f979a) {
                Iterator<Map.Entry<String, dy>> it = f980a.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().a();
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(int i, String str, String str2) {
        if (i == 0) {
            Log.i("linklog", str + " " + str2);
            return;
        }
        Log.e("linklog", str + " " + str2);
    }

    public static void a(String str, String str2, MarkerOptions markerOptions) {
        if (markerOptions != null) {
            c(str, str2 + " " + markerOptions.getPosition() + " " + markerOptions.getIcons());
            return;
        }
        c(str, str2);
    }

    public static void a(String str, String str2, List<MarkerOptions> list) {
        if (list != null) {
            Iterator<MarkerOptions> it = list.iterator();
            while (it.hasNext()) {
                a(str, str2, it.next());
            }
        }
    }

    public static void a(String str, String str2, PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            StringBuilder sb = new StringBuilder();
            List<LatLng> points = polylineOptions.getPoints();
            if (points != null) {
                sb.append("points size =");
                sb.append(points.size());
            }
            sb.append(";width=");
            sb.append(polylineOptions.getWidth());
            sb.append(";color=");
            sb.append(polylineOptions.getColor());
            sb.append(";visible=");
            sb.append(polylineOptions.isVisible());
            c(str, str2 + " " + sb.toString());
            return;
        }
        c(str, str2);
    }

    public static void a(String str, String str2, PolygonOptions polygonOptions) {
        if (polygonOptions != null) {
            StringBuilder sb = new StringBuilder();
            List<LatLng> points = polygonOptions.getPoints();
            if (points != null) {
                sb.append("points size =");
                sb.append(points.size());
            }
            sb.append(";width=");
            sb.append(polygonOptions.getStrokeWidth());
            sb.append(";fillColor=");
            sb.append(polygonOptions.getFillColor());
            sb.append(";strokeColor=");
            sb.append(polygonOptions.getStrokeColor());
            sb.append(";visible=");
            sb.append(polygonOptions.isVisible());
            c(str, str2 + " " + sb.toString());
            return;
        }
        c(str, str2);
    }
}
