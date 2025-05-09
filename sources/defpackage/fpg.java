package defpackage;

import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import java.util.List;

/* loaded from: classes4.dex */
public class fpg {
    public static String[] e(List<Attribute> list) {
        String[] strArr = new String[0];
        if (koq.c(list)) {
            strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = list.get(i).getName();
            }
        }
        return strArr;
    }

    public static void c(List<Integer> list, int i) {
        if (list == null) {
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!list.contains(Integer.valueOf(i2))) {
                list.add(Integer.valueOf(i2));
            }
        }
    }

    public static void aCz_(boolean z, View view) {
        if (view == null || view.getParent() == null) {
            LogUtil.h("RecommendTrainHelper: ", "visibilityToolDelMode view is null");
        } else if (view.getParent().getParent() instanceof View) {
            if (z) {
                ((View) view.getParent().getParent()).setVisibility(0);
            } else {
                ((View) view.getParent().getParent()).setVisibility(8);
            }
        }
    }

    public static void a(List<Integer> list, RecyclerHolder recyclerHolder, int i) {
        if (recyclerHolder == null || koq.b(list)) {
            return;
        }
        recyclerHolder.a(R.id.sug_rv_checkbox, 0);
        if (list.contains(Integer.valueOf(i))) {
            recyclerHolder.a(R.id.sug_rv_checkbox, true);
        } else {
            recyclerHolder.a(R.id.sug_rv_checkbox, false);
        }
    }
}
