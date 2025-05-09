package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class bdf {
    public static void nW_(Context context, Bitmap bitmap, int i) {
        fdu fduVar = new fdu(1);
        fduVar.awp_(bitmap);
        fduVar.b("12");
        HashMap hashMap = new HashMap(1);
        hashMap.put(ParsedFieldTag.TASK_TYPE, Integer.valueOf(i));
        fduVar.b((Map<String, Object>) hashMap);
        fduVar.i(false);
        fduVar.c(false);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
    }

    public static String d(String str, int i) {
        return UnitUtil.a(a(str), i != 0 ? i != 1 ? i != 2 ? i != 3 ? 20 : 131080 : 131076 : 24 : 52);
    }

    public static Date a(String str) {
        try {
            return DateFormatUtil.d(str, DateFormatUtil.DateFormatType.DATE_FORMAT_8);
        } catch (ParseException e) {
            LogUtil.b("HealthLife_WeeklyShareUtils", "getParseDate, parseException:", e.getMessage());
            return new Date();
        }
    }
}
