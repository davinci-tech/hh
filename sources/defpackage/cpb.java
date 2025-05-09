package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cpb {
    public static String e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("OpenServiceUtil", "getValueFromJson() serviceJson is empty.");
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has(str2) ? jSONObject.getString(str2) : "";
        } catch (JSONException e) {
            LogUtil.b("OpenServiceUtil", "getValueFromJson JSONException = ", e.getMessage());
            return "";
        }
    }

    public static int c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return (calendar.get(1) * 10000) + ((calendar.get(2) + 1) * 100) + calendar.get(5);
    }
}
