package defpackage;

import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.templates.DailyMomentContent;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class doh {
    public static int c(Context context, DailyMomentContent dailyMomentContent) {
        String d;
        if (context != null && dailyMomentContent != null) {
            return ((dailyMomentContent.getRank() != null ? SharedPreferenceManager.e(context, Integer.toString(11000), "rank", Integer.toString(dailyMomentContent.getRank().intValue()), new StorageParams()) : -1) == 0 && (dailyMomentContent.getDisplayRule() != null ? SharedPreferenceManager.e(context, Integer.toString(11000), "display_rule", Integer.toString(dailyMomentContent.getDisplayRule().intValue()), new StorageParams()) : -1) == 0 && (dailyMomentContent.getDisplayUpperLimit() != null ? SharedPreferenceManager.e(context, Integer.toString(11000), "display_upper_limit", Integer.toString(dailyMomentContent.getDisplayUpperLimit().intValue()), new StorageParams()) : -1) == 0 && ((dailyMomentContent.getMomentContents() == null || (d = d(dailyMomentContent.getMomentContents())) == null || d.isEmpty()) ? -1 : SharedPreferenceManager.e(context, Integer.toString(11000), "moment_contents", d, new StorageParams())) == 0) ? 0 : -1;
        }
        LogUtil.b("SharedPreferenceUtil", "content or context is invalid");
        return -1;
    }

    public static DailyMomentContent b(Context context) {
        int i;
        int i2;
        List<SingleDailyMomentContent> a2;
        int i3;
        int i4;
        if (context == null) {
            LogUtil.b("SharedPreferenceUtil", "context is invalid");
            return null;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(11000), "rank");
        String b2 = SharedPreferenceManager.b(context, Integer.toString(11000), "display_rule");
        String b3 = SharedPreferenceManager.b(context, Integer.toString(11000), "display_upper_limit");
        String b4 = SharedPreferenceManager.b(context, Integer.toString(11000), "moment_contents");
        int i5 = 0;
        if (b == null || b2 == null || b3 == null) {
            i = 0;
            i2 = 0;
        } else {
            try {
                i3 = Integer.parseInt(b);
                try {
                    i4 = Integer.parseInt(b2);
                    try {
                        i5 = Integer.parseInt(b3);
                    } catch (NumberFormatException e) {
                        e = e;
                        LogUtil.b("SharedPreferenceUtil", " set rank or displayRule error", e.getMessage());
                        int i6 = i3;
                        i2 = i5;
                        i = i4;
                        i5 = i6;
                        a2 = a(b4);
                        return a2 == null ? null : null;
                    }
                } catch (NumberFormatException e2) {
                    e = e2;
                    i4 = 0;
                }
            } catch (NumberFormatException e3) {
                e = e3;
                i3 = 0;
                i4 = 0;
            }
            int i62 = i3;
            i2 = i5;
            i = i4;
            i5 = i62;
        }
        a2 = a(b4);
        if (a2 == null && !a2.isEmpty()) {
            return new DailyMomentContent(i5, i, i2, a2);
        }
    }

    public static int b(Context context, List<SingleDailyMomentContent> list) {
        if (context == null || list == null || list.isEmpty()) {
            LogUtil.b("SharedPreferenceUtil", "invalid params");
            return -1;
        }
        List<SingleDailyMomentContent> d = d(context);
        Iterator<SingleDailyMomentContent> it = list.iterator();
        while (it.hasNext()) {
            String contentId = it.next().getContentId();
            for (SingleDailyMomentContent singleDailyMomentContent : d) {
                if (singleDailyMomentContent.getContentId().equals(contentId)) {
                    singleDailyMomentContent.setLatestShowTime(Long.toString(System.currentTimeMillis()));
                }
            }
        }
        return c(context, d);
    }

    private static List<SingleDailyMomentContent> d(Context context) {
        if (context == null) {
            LogUtil.b("SharedPreferenceUtil", "context is invalid");
            return new ArrayList();
        }
        List<SingleDailyMomentContent> a2 = a(SharedPreferenceManager.b(context, Integer.toString(11000), "moment_contents"));
        return (a2 == null || a2.isEmpty()) ? new ArrayList() : a2;
    }

    private static int c(Context context, List<SingleDailyMomentContent> list) {
        return SharedPreferenceManager.e(context, Integer.toString(11000), "moment_contents", d(list), new StorageParams());
    }

    private static String d(List<SingleDailyMomentContent> list) {
        return nrv.e(list, new TypeToken<List<SingleDailyMomentContent>>() { // from class: doh.3
        }.getType());
    }

    private static List<SingleDailyMomentContent> a(String str) {
        try {
            return (List) nrv.c(str, new TypeToken<List<SingleDailyMomentContent>>() { // from class: doh.4
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("SharedPreferenceUtil", "JsonSyntaxException");
            return Collections.emptyList();
        }
    }
}
