package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ccq {
    public static HashMap<String, Object> c(int i, String str, int i2, FitWorkout fitWorkout) {
        HashMap hashMap = new HashMap(i);
        hashMap.put("entrance", str);
        hashMap.put("position", 0);
        hashMap.put("type", Integer.valueOf(i2));
        hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, TextUtils.isEmpty(fitWorkout.acquireId()) ? "" : fitWorkout.acquireId());
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("fitWorkout", fitWorkout);
        hashMap2.put("biMap", hashMap);
        return hashMap2;
    }

    public static void c(HealthSubHeader healthSubHeader) {
        healthSubHeader.setMoreTextVisibility(4);
        healthSubHeader.setMoreLayoutVisibility(4);
    }

    public static void a(HealthRecycleView healthRecycleView, Context context) {
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        eet.c(context, healthRecycleView, new HealthLinearLayoutManager(context), false, 0);
    }

    public static void d(HealthSubHeader healthSubHeader, HashMap<String, Object> hashMap) {
        healthSubHeader.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        dpf.b(hashMap, healthSubHeader);
    }

    public static void b(HealthRecycleView healthRecycleView) {
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
    }

    public static void c(Object obj, HealthSubHeader healthSubHeader, String str, int i, String str2) {
        if (healthSubHeader != null) {
            if (eet.f(obj)) {
                LogUtil.a(str, "subtitle is set");
                healthSubHeader.setMoreTextVisibility(0);
                healthSubHeader.setMoreText((String) obj);
            } else {
                LogUtil.a(str, "subtitle is " + str2);
                healthSubHeader.setMoreTextVisibility(i);
            }
        }
    }

    public static void d(HealthViewPager healthViewPager, int i, String str, int i2, int i3, int i4) {
        try {
            healthViewPager.setPageMargin(i);
            healthViewPager.setPadding(i2, i4, i3, i4);
        } catch (IllegalStateException unused) {
            LogUtil.b(str, "AdViewPager setPageMargin llegalStateException.");
        }
    }

    public static void a(int i, HealthViewPager healthViewPager, int i2) {
        if (i > i2) {
            healthViewPager.setIsScroll(true);
        } else {
            healthViewPager.setIsScroll(false);
        }
    }

    public static int b(Map<String, List<Object>> map, String str) {
        int i = 0;
        if (map == null) {
            LogUtil.h(str, "getMaxListItem sectionDataList is null.");
            return 0;
        }
        Iterator<Map.Entry<String, List<Object>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            List<Object> value = it.next().getValue();
            if (value.size() > i) {
                i = value.size();
            }
        }
        return i;
    }

    public static void Db_(Rect rect, View view, RecyclerView recyclerView, int i, Context context, int i2, int i3, int i4, boolean z) {
        int c;
        int i5;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition % i != 0) {
            i5 = nsn.c(context, i2 / i4);
            c = 0;
        } else {
            c = nsn.c(context, i2 / i4);
            i5 = 0;
        }
        if (z) {
            rect.set(i5, childAdapterPosition == 0 ? 0 : nsn.c(context, i3), c, 0);
        } else {
            float f = i3 / i4;
            rect.set(i5, nsn.c(context, f), c, nsn.c(context, f));
        }
    }
}
