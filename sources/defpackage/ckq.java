package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.measure.fragment.WeightMeasureGuideFragment;
import com.huawei.health.device.ui.measure.fragment.WeightResultFragment;
import com.huawei.health.device.ui.measure.fragment.WifiWeightMeasureGuideFragment;
import com.huawei.health.device.ui.measure.fragment.WifiWeightResultFragment;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasureGuidFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureResultFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarResultFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.DnurseMeasureFragment;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ckq {
    private static final String[][] b = {new String[]{"9bf158ba-49b0-46aa-9fdf-ed75da1569cf", BloodSugarResultFragment.class.getName()}, new String[]{"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", WifiWeightResultFragment.class.getName()}, new String[]{"a8ba095d-4123-43c4-a30a-0240011c58de", WifiWeightResultFragment.class.getName()}, new String[]{"HDK_WEIGHT", WeightResultFragment.class.getName()}, new String[]{"HDK_BLOOD_PRESSURE", BloodPressureResultFragment.class.getName()}};

    /* renamed from: a, reason: collision with root package name */
    private static final String[][] f772a = {new String[]{"9bf158ba-49b0-46aa-9fdf-ed75da1569cf", DnurseMeasureFragment.class.getName()}, new String[]{"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", WifiWeightMeasureGuideFragment.class.getName()}, new String[]{"a8ba095d-4123-43c4-a30a-0240011c58de", WifiWeightMeasureGuideFragment.class.getName()}, new String[]{"HDK_WEIGHT", WeightMeasureGuideFragment.class.getName()}, new String[]{"HDK_BLOOD_PRESSURE", BloodPressureMeasureGuidFragment.class.getName()}};

    public static BaseFragment a(String str) {
        return f(e(str));
    }

    public static BaseFragment c(String str) {
        return f(str);
    }

    private static BaseFragment f(String str) {
        if (str != null) {
            LogUtil.a("DeviceFragmentFactory", str);
            try {
                return (BaseFragment) Class.forName(str).newInstance();
            } catch (ClassNotFoundException e) {
                LogUtil.b("DeviceFragmentFactory", e.getMessage());
            } catch (IllegalAccessException e2) {
                LogUtil.b("DeviceFragmentFactory", e2.getMessage());
            } catch (InstantiationException e3) {
                LogUtil.b("DeviceFragmentFactory", e3.getMessage());
            }
        }
        return null;
    }

    public static BaseFragment b(String str) {
        return f(d(str));
    }

    public static String d(String str) {
        for (String[] strArr : b) {
            if (strArr[0].equals(str)) {
                return strArr[1];
            }
        }
        return null;
    }

    public static String e(String str) {
        String str2;
        HealthDevice a2;
        String[][] strArr = f772a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = null;
                break;
            }
            String[] strArr2 = strArr[i];
            if (strArr2[0].equals(str)) {
                str2 = strArr2[1];
                break;
            }
            i++;
        }
        if (cpa.ae(str) && (a2 = cjx.e().a(str)) != null) {
            str2 = e(a2, str);
        }
        LogUtil.a("DeviceFragmentFactory", "the classname is ", str2);
        return str2;
    }

    private static String e(HealthDevice healthDevice, String str) {
        if ((healthDevice instanceof ctk) && ((ctk) healthDevice).b().k() == 2) {
            if (ceo.d().e(str, true) != null) {
                return WeightMeasureGuideFragment.class.getName();
            }
            LogUtil.h("DeviceFragmentFactory", "the measurableDevice is null");
        }
        return "";
    }
}
