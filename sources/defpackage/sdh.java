package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.R$string;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class sdh {
    public static int a(int i) {
        if (i >= 1 && i <= 29) {
            return 1;
        }
        if (i <= 29 || i >= 60) {
            return (i < 60 || i >= 80) ? 4 : 3;
        }
        return 2;
    }

    public static String e(int i) {
        return d(a(i));
    }

    private static String d(int i) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(1, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_pressure_relaxed));
        hashMap.put(2, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_pressure_normal));
        hashMap.put(3, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3));
        hashMap.put(4, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_pressure_highly));
        return (String) hashMap.get(Integer.valueOf(i));
    }

    public static boolean d(List<sdf> list) {
        Iterator<sdf> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().c() > 0) {
                return true;
            }
        }
        return false;
    }
}
