package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.section.view.BaseBiItemView;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.SingleTextContent;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class fbh {
    private static final Map<Integer, String> e = new HashMap<Integer, String>() { // from class: fbh.3
        {
            put(200, AnalyticsValue.SEARCH_2130058.value());
            put(201, AnalyticsValue.SEARCH_2130059.value());
            put(202, AnalyticsValue.SEARCH_2130061.value());
            put(203, AnalyticsValue.SEARCH_2130060.value());
            put(206, AnalyticsValue.SEARCH_2130062.value());
            put(204, AnalyticsValue.SEARCH_2130063.value());
            put(300, AnalyticsValue.SEARCH_2130059.value());
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, String> f12421a = new HashMap<Integer, String>() { // from class: fbh.1
        {
            put(200, AnalyticsValue.SEARCH_2130065.value());
            put(201, AnalyticsValue.SEARCH_2130066.value());
            put(202, AnalyticsValue.SEARCH_2130068.value());
            put(203, AnalyticsValue.SEARCH_2130067.value());
            put(206, AnalyticsValue.SEARCH_2130069.value());
            put(204, AnalyticsValue.SEARCH_2130070.value());
            put(300, AnalyticsValue.SEARCH_2130066.value());
        }
    };
    private static final Map<Integer, String> d = new HashMap<Integer, String>() { // from class: fbh.5
        {
            put(200, AnalyticsValue.SEARCH_2130078.value());
            put(201, AnalyticsValue.SEARCH_2130079.value());
            put(202, AnalyticsValue.SEARCH_2130081.value());
            put(203, AnalyticsValue.SEARCH_2130080.value());
            put(206, AnalyticsValue.SEARCH_2130082.value());
            put(204, AnalyticsValue.SEARCH_2130083.value());
            put(300, AnalyticsValue.SEARCH_2130079.value());
        }
    };
    private static final Map<Integer, String> b = new HashMap<Integer, String>() { // from class: fbh.2
        {
            put(200, AnalyticsValue.SEARCH_2130085.value());
            put(201, AnalyticsValue.SEARCH_2130086.value());
            put(202, AnalyticsValue.SEARCH_2130088.value());
            put(203, AnalyticsValue.SEARCH_2130087.value());
            put(206, AnalyticsValue.SEARCH_2130089.value());
            put(204, AnalyticsValue.SEARCH_2130090.value());
            put(300, AnalyticsValue.SEARCH_2130086.value());
            put(Integer.valueOf(a.z), AnalyticsValue.SEARCH_2130095.value());
            put(Integer.valueOf(a.A), AnalyticsValue.SEARCH_2130096.value());
        }
    };

    public static void e(Context context, int i, InputBoxTemplate inputBoxTemplate) {
        Map<String, Object> d2 = d();
        a(d2, inputBoxTemplate);
        d2.put("positionId", Integer.valueOf(i));
        c(context, AnalyticsValue.SEARCH_2130050.value(), d2, 0);
    }

    public static void a(Context context) {
        c(context, AnalyticsValue.SEARCH_2130051.value(), d(), 0);
    }

    public static void c(Context context, InputBoxTemplate inputBoxTemplate) {
        Map<String, Object> d2 = d();
        a(d2, inputBoxTemplate);
        c(context, AnalyticsValue.SEARCH_2130052.value(), d2, 0);
    }

    public static void d(Context context, String str) {
        Map<String, Object> d2 = d();
        d2.put("theme", str);
        c(context, AnalyticsValue.SEARCH_2130053.value(), d2, 0);
    }

    public static void a(Context context, SingleTextContent singleTextContent) {
        Map<String, Object> d2 = d();
        b(d2, singleTextContent);
        c(context, AnalyticsValue.SEARCH_2130054.value(), d2, 0);
    }

    public static void b(Context context, SingleGridContent singleGridContent) {
        Map<String, Object> d2 = d();
        b(d2, singleGridContent);
        c(context, AnalyticsValue.SEARCH_2130055.value(), d2, 0);
    }

    public static void a(Context context, String str) {
        Map<String, Object> d2 = d();
        d2.put("word", str);
        c(context, AnalyticsValue.SEARCH_2130056.value(), d2, 0);
    }

    public static void d(Context context, int i) {
        Map<String, Object> d2 = d();
        d2.put("size", Integer.valueOf(i));
        c(context, AnalyticsValue.SEARCH_2130057.value(), d2, 0);
    }

    public static void b(Context context, int i) {
        c(context, e.get(Integer.valueOf(i)), d(), 0);
    }

    public static void b(Context context, String str, Set<Integer> set) {
        Map<String, Object> d2 = d();
        d2.put("event", 0);
        d2.put("word", str);
        d2.put("category", new ArrayList(set));
        c(context, AnalyticsValue.SEARCH_2130094.value(), d2, 0);
    }

    public static void e(Context context, Map<String, Object> map) {
        if (map.get("category") instanceof Integer) {
            String str = f12421a.get(Integer.valueOf(map.get("page") instanceof Integer ? ((Integer) map.get("page")).intValue() : 0));
            if (str == null) {
                return;
            }
            map.remove("page");
            c(context, str, map, 0);
        }
    }

    public static void d(Context context, int i, InputBoxTemplate inputBoxTemplate) {
        Map<String, Object> c = c();
        a(c, inputBoxTemplate);
        c.put("positionId", Integer.valueOf(i));
        c(context, AnalyticsValue.SEARCH_2130072.value(), c, 0);
    }

    public static void e(Context context, InputBoxTemplate inputBoxTemplate) {
        Map<String, Object> c = c();
        a(c, inputBoxTemplate);
        c(context, AnalyticsValue.SEARCH_2130073.value(), c, 0);
    }

    public static void e(Context context, String str) {
        Map<String, Object> c = c();
        c.put("theme", str);
        c(context, AnalyticsValue.SEARCH_2130074.value(), c, 0);
    }

    public static void e(Context context, SingleTextContent singleTextContent) {
        Map<String, Object> c = c();
        b(c, singleTextContent);
        c(context, AnalyticsValue.SEARCH_2130075.value(), c, 0);
    }

    public static void c(Context context, SingleGridContent singleGridContent) {
        Map<String, Object> c = c();
        b(c, singleGridContent);
        c(context, AnalyticsValue.SEARCH_2130076.value(), c, 0);
    }

    public static void c(Context context, String str) {
        Map<String, Object> c = c();
        c.put("theme", str);
        c(context, AnalyticsValue.SEARCH_2130077.value(), c, 0);
    }

    public static void d(Context context, String str, int i) {
        String str2 = d.get(Integer.valueOf(i));
        Map<String, Object> c = c();
        c(context, str2, c, 0);
        c.put("event", 1);
        c.put("word", str);
        c.put("category", Integer.valueOf(i));
        c(context, AnalyticsValue.SEARCH_2130094.value(), c, 0);
    }

    public static void b(Context context, String str, int i, String str2, String str3, boolean z) {
        Map<String, Object> c = c();
        c.put("event", 2);
        c.put("word", str);
        c.put("category", Integer.valueOf(i));
        c.put("id", str2);
        c.put("name", str3);
        c(context, z ? AnalyticsValue.SEARCH_2130085.value() : b.get(Integer.valueOf(i)), c, 0);
        c(context, AnalyticsValue.SEARCH_2130094.value(), c, 0);
    }

    public static void d(Context context, String str, String str2, String str3) {
        Map<String, Object> c = c();
        c.put("word", str);
        c.put("category", 301);
        c.put("id", str2);
        c.put("name", str2);
        c.put("url", str3);
        c(context, AnalyticsValue.SEARCH_2130085.value(), c, 0);
    }

    public static void b(Context context, String str) {
        Map<String, Object> c = c();
        c.put("word", str);
        c(context, AnalyticsValue.SEARCH_2130092.value(), c, 0);
    }

    private static void c(final Context context, final String str, final Map<String, Object> map, final int i) {
        if (str == null || map == null) {
            return;
        }
        LogUtil.a("SearchBIUtil", "eventId: ", str, " eventMap: ", map);
        ThreadPoolManager.d().execute(new Runnable() { // from class: fbh.4
            @Override // java.lang.Runnable
            public void run() {
                ixx.d().d(context, str, map, i);
            }
        });
    }

    private static Map<String, Object> d() {
        HashMap hashMap = new HashMap();
        hashMap.put(ParamConstants.CallbackMethod.ON_SHOW, "1");
        return hashMap;
    }

    private static Map<String, Object> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        return hashMap;
    }

    private static void a(Map<String, Object> map, InputBoxTemplate inputBoxTemplate) {
        if (inputBoxTemplate == null) {
            return;
        }
        map.put("theme", inputBoxTemplate.getTheme());
        map.put("linkType", inputBoxTemplate.getLinkType());
        map.put("linkValue", inputBoxTemplate.getLinkValue());
    }

    private static void b(Map<String, Object> map, SingleTextContent singleTextContent) {
        if (singleTextContent == null) {
            return;
        }
        map.put("theme", singleTextContent.getTheme());
        map.put("marketingIcon", singleTextContent.getMarketingIcon());
        map.put("linkType", singleTextContent.getLinkType());
        map.put("linkValue", singleTextContent.getLinkValue());
    }

    private static void b(Map<String, Object> map, SingleGridContent singleGridContent) {
        if (singleGridContent == null) {
            return;
        }
        map.put("theme", singleGridContent.getTheme());
        map.put("description", singleGridContent.getDescription());
        map.put("category", singleGridContent.getItemCategory());
        map.put("linkValue", singleGridContent.getLinkValue());
    }

    public static String d(int i) {
        return "BI_" + i;
    }

    public static Observer a(final HealthRecycleView healthRecycleView) {
        return new Observer() { // from class: fbh.6
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                RecyclerView.LayoutManager layoutManager = HealthRecycleView.this.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    View findViewByPosition = layoutManager.findViewByPosition(i);
                    if (findViewByPosition instanceof BaseBiItemView) {
                        Rect rect = new Rect();
                        findViewByPosition.getLocalVisibleRect(rect);
                        if (rect.top == 0 && rect.bottom >= findViewByPosition.getHeight() / 2) {
                            ((BaseBiItemView) findViewByPosition).e();
                        }
                    }
                }
            }
        };
    }
}
