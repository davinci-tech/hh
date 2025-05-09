package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class exv {
    HiAnalyticsInstance d;

    public static String a(Context context, String str, String str2) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(str), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    return new JSONObject(sb.toString()).getString(str2);
                }
            }
        } catch (IOException unused) {
            eym.c("AAR,HiAUtil", "getConfigUrl IOException");
            return null;
        } catch (JSONException unused2) {
            eym.c("AAR,HiAUtil", "getConfigUrl JSONException");
            return null;
        }
    }

    public void a(Context context) {
        String a2 = a(context, "config.json", "dotUrl");
        if (TextUtils.isEmpty(a2)) {
            eym.c("AAR,HiAUtil", "init IOException");
            return;
        }
        HiAnalyticsConfig build = new HiAnalyticsConfig.Builder().setChannel("RoadDataDownload").setCollectURL(a2).build();
        HiAnalyticsConfig build2 = new HiAnalyticsConfig.Builder().setCollectURL(a2).build();
        if (this.d == null) {
            this.d = new HiAnalyticsInstance.Builder(context).setOperConf(build).setMaintConf(build2).create("RoadDataDownload");
        }
        this.d = new HiAnalyticsInstance.Builder(context).setOperConf(build).setMaintConf(build2).refresh("RoadDataDownload");
    }

    public int a(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        eym.a("AAR,HiAUtil", " type = " + i + " eventId = " + str + " content = " + linkedHashMap.toString());
        HiAnalyticsInstance hiAnalyticsInstance = this.d;
        if (hiAnalyticsInstance != null) {
            hiAnalyticsInstance.onEvent(i, str, linkedHashMap);
            d();
            return 0;
        }
        eym.a("AAR,HiAUtil", "setEvent instance is null");
        return -1;
    }

    public void d() {
        eym.b("AAR,HiAUtil", "onReport");
        this.d.onReport(1);
        this.d.onReport(0);
    }

    private exv() {
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final exv f12374a = new exv();
    }

    public static exv e() {
        return a.f12374a;
    }

    public static String c() {
        return "85040100";
    }
}
