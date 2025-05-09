package defpackage;

import android.text.TextUtils;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReflectionUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class ees {
    private static JSONObject b(ResourceBriefInfo resourceBriefInfo) {
        ResourceContentBase content;
        JSONObject jSONObject = null;
        if (resourceBriefInfo == null || (content = resourceBriefInfo.getContent()) == null) {
            return null;
        }
        String content2 = content.getContent();
        if (TextUtils.isEmpty(content2)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(content2);
        } catch (JSONException e) {
            LogUtil.b("SectionBeanUtils", "exception happened when parseOnlineData, cause = " + e.getCause());
        }
        if (jSONObject == null) {
            LogUtil.b("SectionBeanUtils", "onlineDataJson is null!");
        }
        return jSONObject;
    }

    public static List<String> d(ResourceBriefInfo resourceBriefInfo) {
        ArrayList arrayList = new ArrayList();
        JSONObject b = b(resourceBriefInfo);
        if (b == null) {
            return arrayList;
        }
        String optString = b.optString("picture");
        String optString2 = b.optString("tahitiPicture");
        arrayList.add(optString);
        arrayList.add(optString2);
        return arrayList;
    }

    public static KnitDataProvider a(ResourceBriefInfo resourceBriefInfo) {
        JSONObject b = b(resourceBriefInfo);
        if (b == null) {
            return null;
        }
        String a2 = eai.a(b.optString("dataUrl"));
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        Object e = ReflectionUtils.e(a2);
        if (e instanceof KnitDataProvider) {
            return (KnitDataProvider) e;
        }
        return null;
    }
}
