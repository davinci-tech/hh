package health.compact.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.browse.Cloudapi;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CloudImpl implements Cloudapi {
    private static volatile CloudImpl b;
    private static Context c;
    private static final Object d = new Object();

    private CloudImpl() {
    }

    public static CloudImpl c(Context context) {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new CloudImpl();
                    c = context == null ? BaseApplication.getContext() : context.getApplicationContext();
                    LoginInit.setCloudapi(b);
                }
            }
        }
        return b;
    }

    @Override // com.huawei.browse.Cloudapi
    public boolean isOverseaJudgeByCountry(String str) {
        String e;
        if ("CN".equalsIgnoreCase(str)) {
            return true;
        }
        String[] stringArray = c.getResources().getStringArray(R.array._2130968672_res_0x7f040060);
        if (TextUtils.isEmpty(str)) {
            str = Locale.getDefault().getCountry();
        }
        com.huawei.hwlogsmodel.LogUtil.a("PLGLOGIN_CloudImpl", "isOverseaJudgeByCountry() country=", str);
        for (String str2 : stringArray) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        String str3 = CommonUtil.bv() ? "health.oversea" : "health.oversea.beta";
        if (AuthorizationUtils.a(c)) {
            e = GRSManager.a(c).getNoCheckUrl("SITEID", str3, str);
        } else {
            e = e("SITEID", str3, str);
        }
        com.huawei.hwlogsmodel.LogUtil.c("PLGLOGIN_CloudImpl", "isOverseaJudgeByCountry() healthCloudUrl=", e);
        return !TextUtils.isEmpty(e);
    }

    @Override // com.huawei.browse.Cloudapi
    public int getSiteIdByCountry(String str) {
        String e;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.equalsIgnoreCase("CN")) {
            return 1;
        }
        String str2 = CommonUtil.bv() ? "health.oversea" : "health.oversea.beta";
        if (AuthorizationUtils.a(c)) {
            e = GRSManager.a(c).getNoCheckUrl("SITEID", str2, str);
            com.huawei.hwlogsmodel.LogUtil.a("PLGLOGIN_CloudImpl", "getSiteIdByCountry() healthCloudUrl=", e);
        } else {
            e = e("SITEID", str2, str);
            com.huawei.hwlogsmodel.LogUtil.a("PLGLOGIN_CloudImpl", "getSiteIdByCountry() local healthCloudUrl=", e);
        }
        return e(e);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int e(String str) {
        char c2;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1652637892:
                if (str.equals("https://sportdata-dre.things.dbankcloud.com")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -393746446:
                if (str.equals("https://sportdata-drru.things.dbankcloud.com")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1276483529:
                if (str.equals("https://sporthealth-drcn.dbankcdn.com")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1958238776:
                if (str.equals("https://sportdata-dra.things.dbankcloud.com")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            i = 7;
        } else if (c2 == 1) {
            i = 8;
        } else if (c2 == 2) {
            i = 1;
        } else if (c2 == 3) {
            i = 5;
        }
        com.huawei.hwlogsmodel.LogUtil.a("PLGLOGIN_CloudImpl", "getSiteIdByUrl() getSiteIdByUrl=", Integer.valueOf(i));
        return i;
    }

    private String e(String str, String str2, String str3) {
        com.huawei.hwlogsmodel.LogUtil.c("PLGLOGIN_CloudImpl", "key= ", str, "service= ", str2, "countryCode= ", str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            com.huawei.hwlogsmodel.LogUtil.h("PLGLOGIN_CloudImpl", "input is null");
            return "";
        }
        Context context = BaseApplication.getContext();
        StringBuilder sb = new StringBuilder();
        try {
            InputStream open = context.getAssets().open("grs_app_global_route_config.json");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, StandardCharsets.UTF_8));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } finally {
                    }
                }
                bufferedReader.close();
                if (open != null) {
                    open.close();
                }
                try {
                    JSONArray jSONArray = new JSONObject(sb.toString()).getJSONArray("services");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (str2.equals(jSONObject.getString("name"))) {
                            return a(jSONObject.getJSONArray("servings"), d(str3, jSONObject.getJSONArray("countryGroups")), str);
                        }
                    }
                } catch (JSONException unused) {
                    com.huawei.hwlogsmodel.LogUtil.h("PLGLOGIN_CloudImpl", "json exception");
                }
                return "";
            } finally {
            }
        } catch (IOException unused2) {
            com.huawei.hwlogsmodel.LogUtil.h("PLGLOGIN_CloudImpl", "io Exception");
            return "";
        }
    }

    private static String d(String str, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.getJSONObject(i).getString("countries").contains(str)) {
                    return jSONArray.getJSONObject(i).getString("id");
                }
            } catch (JSONException unused) {
                com.huawei.hwlogsmodel.LogUtil.h("PLGLOGIN_CloudImpl", "getCountryGroupId json exception");
                return "";
            }
        }
        return "";
    }

    private static String a(JSONArray jSONArray, String str, String str2) {
        if (jSONArray == null || jSONArray.length() == 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (str.equals(jSONObject.getString("countryGroup"))) {
                    return jSONObject.getJSONObject("addresses").getString(str2);
                }
            } catch (JSONException unused) {
                com.huawei.hwlogsmodel.LogUtil.h("PLGLOGIN_CloudImpl", "getAddress json exception");
                return "";
            }
        }
        return "";
    }
}
