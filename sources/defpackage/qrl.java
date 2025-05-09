package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class qrl {
    public static void dHK_(ViewGroup viewGroup, List<View> list, List<Integer> list2) {
        if (viewGroup == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ColumnLinearLayout) {
                hashMap.put(Integer.valueOf(((ColumnLinearLayout) childAt).getResourceBriefPriority()), childAt);
            }
        }
        viewGroup.removeAllViews();
        if (!koq.b(list)) {
            for (View view : list) {
                if (view instanceof ColumnLinearLayout) {
                    hashMap.put(Integer.valueOf(((ColumnLinearLayout) view).getResourceBriefPriority()), view);
                }
            }
        }
        if (!koq.b(list2)) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (hashMap.containsKey(list2.get(i2))) {
                    hashMap.remove(list2.get(i2));
                }
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList, new Comparator() { // from class: qrh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return qrl.c((Integer) obj, (Integer) obj2);
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            viewGroup.addView((View) hashMap.get(Integer.valueOf(((Integer) it.next()).intValue())));
        }
    }

    static /* synthetic */ int c(Integer num, Integer num2) {
        return num2.intValue() - num.intValue();
    }
}
