package defpackage;

import androidx.collection.SimpleArrayMap;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class mvs {
    private static volatile Map<Integer, fec> c;
    private static final boolean d = nsn.ag(BaseApplication.e());

    /* renamed from: a, reason: collision with root package name */
    private static SimpleArrayMap<Integer, Integer> f15208a = new SimpleArrayMap<>(14);
    private static SimpleArrayMap<Integer, Integer> b = new SimpleArrayMap<>(14);

    static {
        d();
        f15208a.put(258, 621051339);
        f15208a.put(264, 621051341);
        f15208a.put(257, 621051327);
        f15208a.put(281, 621051327);
        f15208a.put(259, 621051343);
        f15208a.put(10001, 621051325);
        f15208a.put(262, 621051393);
        f15208a.put(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM), 621051393);
        f15208a.put(283, 621051317);
        f15208a.put(137, 621051337);
        f15208a.put(260, 621051396);
        f15208a.put(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT), 621051324);
        f15208a.put(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER), 621051350);
        f15208a.put(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE), 621051379);
    }

    public static int d(int i) {
        return f15208a.getOrDefault(Integer.valueOf(i), 621051319).intValue();
    }

    public static void c(int i) {
        SimpleArrayMap<Integer, Integer> simpleArrayMap = b;
        if (f15208a.get(Integer.valueOf(i)) == null) {
            i = 0;
        }
        simpleArrayMap.put(Integer.valueOf(i), 0);
    }

    public static void c(int i, int i2) {
        SimpleArrayMap<Integer, Integer> simpleArrayMap = b;
        if (f15208a.get(Integer.valueOf(i)) == null) {
            i = 0;
        }
        simpleArrayMap.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static int b(int i) {
        return f15208a.get(Integer.valueOf(i)) == null ? b.getOrDefault(0, 0).intValue() : b.getOrDefault(Integer.valueOf(i), 0).intValue();
    }

    public static void d(int i, fec fecVar) {
        if (c == null) {
            LogUtil.b("share_ShareThemeConfig", "putLastShareInfo map do not load.");
        } else {
            c.put(Integer.valueOf(i), fecVar);
        }
    }

    private static void d() {
        if (c == null) {
            String c2 = mwa.c(mus.k);
            if (StringUtils.g(c2)) {
                LogUtil.b("share_ShareThemeConfig", "background config is empty.");
                c = new HashMap();
            } else {
                c = (Map) new GsonBuilder().create().fromJson(CommonUtil.z(c2), new TypeToken<Map<Integer, fec>>() { // from class: mvs.2
                }.getType());
            }
        }
    }

    public static void c() {
        if (c != null) {
            LogUtil.a("share_ShareThemeConfig", "write background config json result:", Boolean.valueOf(mwa.b(mus.k, new GsonBuilder().create().toJson(c))));
        }
    }

    public static fec a(int i) {
        if (c == null) {
            LogUtil.b("share_ShareThemeConfig", "getShareDataById map do not load.");
            return null;
        }
        return c.get(Integer.valueOf(i));
    }
}
