package defpackage;

import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.history.view.InterfaceItemDataShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import com.huawei.ui.main.stories.history.view.staticitem.DistanceStaticItemShower;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class red {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, InterfaceItemDataShower> f16730a;

    public static SportDetailItem.b a(Map<String, Double> map, String str, int i) {
        if (f16730a == null) {
            f16730a = b();
        }
        if (!f16730a.containsKey(str)) {
            LogUtil.b("Track_MonthDataHandleType", "ShouwerMap is not contiankey viewType");
            return null;
        }
        InterfaceItemDataShower interfaceItemDataShower = f16730a.get(str);
        if (interfaceItemDataShower == null) {
            LogUtil.b("Track_MonthDataHandleType", "monthDataShower is null");
            return null;
        }
        interfaceItemDataShower.setBaseInformation(map, i);
        if ("--".equals(interfaceItemDataShower.getMainStaticData())) {
            LogUtil.b("Track_MonthDataHandleType", "getMainStaticData is --");
            return null;
        }
        return new SportDetailItem.b(null, interfaceItemDataShower.getMainStaticName(), interfaceItemDataShower.getMainStaticData(), interfaceItemDataShower.getUnit());
    }

    private static Map<String, InterfaceItemDataShower> b() {
        List<String> d = d(DistanceStaticItemShower.class.getPackage().getName());
        HashMap hashMap = new HashMap();
        for (String str : d) {
            hashMap.put(c(c(str, false)), a(str));
        }
        LogUtil.a("Track_MonthDataHandleType", "list is ", Integer.valueOf(d.size()));
        return hashMap;
    }

    private static String c(Class<?> cls) {
        StasticViewType stasticViewType = (StasticViewType) cls.getAnnotation(StasticViewType.class);
        LogUtil.a("Track_MonthDataHandleType", " getViewType", stasticViewType.getDataType());
        return stasticViewType.getDataType();
    }

    private static InterfaceItemDataShower a(String str) {
        try {
            Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof InterfaceItemDataShower) {
                return (InterfaceItemDataShower) newInstance;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.b("Track_MonthDataHandleType", "getConstructor failed, clsName", str);
            return null;
        }
    }

    public static ClassLoader e() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> c(String str, boolean z) {
        try {
            return Class.forName(str, z, e());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str + ".ActiveTimeStaticItemShower");
        arrayList.add(str + ".AveragePaceStaticItemShower");
        arrayList.add(str + ".AverageSpeedStaticItemShower");
        arrayList.add(str + ".CaloriesStaticItemShower");
        arrayList.add(str + ".DistanceStaticItemShower");
        arrayList.add(str + ".SkipRopeNumberItemShower");
        arrayList.add(str + ".SportTimesStaticItemShower");
        arrayList.add(str + ".StrengthTrainingTimeItemShower");
        arrayList.add(str + ".StepStaticItemShower");
        arrayList.add(str + ".TimeStaticItemShower");
        arrayList.add(str + ".VerticalJumpsStaticsItemShower");
        arrayList.add(str + ".DivingDepthItemShower");
        arrayList.add(str + ".DivingDurationItemShower");
        arrayList.add(str + ".DivingTimesShower");
        arrayList.add(str + ".DivingUnderwaterTimeItemShower");
        arrayList.add(str + ".ExpDistanceShower");
        return arrayList;
    }
}
