package defpackage;

import android.content.Context;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower.DistanceShower;
import com.huawei.ui.main.stories.history.view.MonthTitleItem;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class rck {
    private static Map<String, MonthDataShower> d;

    public static MonthTitleItem.e a(String str, Map<String, Double> map, HwSportTypeInfo hwSportTypeInfo, Context context) {
        MonthDataShower monthDataShower;
        if (d == null) {
            d = e();
        }
        if (!d.containsKey(str) || (monthDataShower = d.get(str)) == null) {
            return null;
        }
        monthDataShower.setBaseInformation(map, hwSportTypeInfo, context);
        if (monthDataShower.getMainMonthData().equals("---")) {
            return null;
        }
        return new MonthTitleItem.e(monthDataShower.getMainMonthData(), monthDataShower.getUnit());
    }

    private static Map<String, MonthDataShower> e() {
        List<String> d2 = d(DistanceShower.class.getPackage().getName());
        HashMap hashMap = new HashMap();
        for (String str : d2) {
            hashMap.put(d(d(str, false)), b(str));
        }
        LogUtil.a("Track_MonthDataHandleType", "list is ", Integer.valueOf(d2.size()));
        return hashMap;
    }

    private static String d(Class<?> cls) {
        MonthStatisticViewType monthStatisticViewType = (MonthStatisticViewType) cls.getAnnotation(MonthStatisticViewType.class);
        if (monthStatisticViewType == null) {
            LogUtil.b("Track_MonthDataHandleType", "clazz ", cls.getName());
            return "";
        }
        LogUtil.a("Track_MonthDataHandleType", " getViewType", monthStatisticViewType.type());
        return monthStatisticViewType.type();
    }

    private static MonthDataShower b(String str) {
        try {
            Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof MonthDataShower) {
                return (MonthDataShower) newInstance;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.b("Track_MonthDataHandleType", "getConstructor failed, clsName", str);
            return null;
        }
    }

    private static ClassLoader c() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static Class<?> d(String str, boolean z) {
        try {
            return Class.forName(str, z, c());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str + ".BasketballCountShower");
        arrayList.add(str + ".CalorieShower");
        arrayList.add(str + ".DistanceShower");
        arrayList.add(str + ".DurationShower");
        arrayList.add(str + ".FirstDistanceShower");
        arrayList.add(str + ".FirstDurationShower");
        arrayList.add(str + ".FirstMeterDistanceShower");
        arrayList.add(str + ".MeterDistanceShower");
        arrayList.add(str + ".TimesShower");
        arrayList.add(str + ".CountShower");
        arrayList.add(str + ".DepthShower");
        arrayList.add(str + ".WaterDuration");
        arrayList.add(str + ".DivingCountShower");
        return arrayList;
    }
}
