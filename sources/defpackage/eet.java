package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.health.knit.section.utils.FitnessItemDecoration;
import com.huawei.health.knit.section.view.BaseBiItemView;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class eet {

    /* renamed from: a, reason: collision with root package name */
    private static final int f11989a = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    public static <Section extends BaseSection> Section d(Context context, Class<Section> cls) {
        try {
            return cls.getConstructor(Context.class).newInstance(context);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.h("Section_SectionUtils", "create Section failed");
            return null;
        }
    }

    public static boolean h(Object obj) {
        Object obj2;
        if (obj == null || !(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() > 0 && (obj2 = list.get(0)) != null) {
            return obj2 instanceof String;
        }
        return false;
    }

    public static boolean e(Object obj) {
        return obj != null && (obj instanceof List) && ((List) obj).size() > 0;
    }

    public static boolean d(Object obj) {
        return obj != null && (obj instanceof Long);
    }

    public static boolean b(Object obj) {
        return obj != null && (obj instanceof SpannableString);
    }

    public static boolean f(Object obj) {
        return obj != null && (obj instanceof String);
    }

    public static boolean c(Object obj) {
        return obj != null && (obj instanceof Integer);
    }

    public static boolean a(Object obj) {
        return obj != null && (obj instanceof OnClickSectionListener);
    }

    public static <T> boolean b(List<T> list, int i) {
        return list != null && i >= 0 && i < list.size();
    }

    public static int d(List... listArr) {
        int size;
        if (listArr == null || listArr.length <= 0) {
            return 0;
        }
        int i = 0;
        for (List list : listArr) {
            if (list != null && (size = list.size()) > i) {
                i = size;
            }
        }
        return i;
    }

    public static void c(Context context, HealthRecycleView healthRecycleView, LinearLayoutManager linearLayoutManager, boolean z, int i) {
        if (context == null || healthRecycleView == null || linearLayoutManager == null) {
            LogUtil.b("Section_SectionUtils", "setRecyclerViewLayout(), context or recycleView or linearLayoutManager is null!");
            return;
        }
        boolean z2 = false;
        if (nsn.ag(context)) {
            int a2 = nsn.a(context, nrr.b(context));
            if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
                healthRecycleView.removeItemDecorationAt(0);
            }
            healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, a2, b(context)));
            int i2 = 1;
            int i3 = 2;
            if (z) {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
                return;
            } else {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, i3, i2, z2) { // from class: eet.5
                    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                return;
            }
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, 0, i));
        healthRecycleView.setLayoutManager(linearLayoutManager);
    }

    public static int b(Context context) {
        return (int) Utils.convertPixelsToDp(context.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8));
    }

    public static void b(HealthImageView healthImageView, Object obj) {
        if (healthImageView == null) {
            LogUtil.a("Section_SectionUtils", "loadImageContent imageView is null");
            return;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() > 0) {
                nrf.c(healthImageView, str, f11989a, 0, R.drawable._2131431393_res_0x7f0b0fe1);
                return;
            }
            return;
        }
        if (obj instanceof Integer) {
            nrf.a(((Integer) obj).intValue(), healthImageView, f11989a);
        } else if (obj instanceof Bitmap) {
            healthImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            healthImageView.setImageBitmap((Bitmap) obj);
        } else {
            a(healthImageView);
        }
    }

    public static void a(HealthImageView healthImageView) {
        nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, healthImageView, f11989a);
    }

    public static void ahc_(View view, List<Map<String, Object>> list, int i) {
        if (view instanceof BaseBiItemView) {
            if (b(list, i)) {
                ((BaseBiItemView) view).b(list.get(i));
            } else {
                ((BaseBiItemView) view).b(null);
            }
        }
    }

    public static void e(HealthImageView healthImageView, List<Object> list, int i) {
        if (b(list, i) && healthImageView != null) {
            b(healthImageView, list.get(i));
        } else {
            a(healthImageView);
        }
    }

    public static void ahf_(TextView textView, List<String> list, String str, int i, boolean z) {
        String str2;
        if (b(list, i) && textView != null) {
            if (z) {
                str2 = dpf.Ym_(list.get(i), str);
            } else {
                str2 = list.get(i);
            }
            nsy.cMs_(textView, str2, true);
            return;
        }
        nsy.cMA_(textView, 8);
    }

    public static void ahb_(View view, List<Drawable> list, int i) {
        if (b(list, i) && view != null) {
            nsy.cMj_(view, list.get(i));
        } else {
            nsy.cMj_(view, null);
        }
    }

    public static void ahe_(View view, final OnClickSectionListener onClickSectionListener, final int i) {
        nsy.cMn_(view, new View.OnClickListener() { // from class: eet.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OnClickSectionListener onClickSectionListener2 = OnClickSectionListener.this;
                if (onClickSectionListener2 != null) {
                    onClickSectionListener2.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    public static void ahd_(Context context, View view, int i, int i2) {
        if (i == i2 - 1) {
            nsy.cMA_(view, 8);
        } else if (context != null && nsn.ag(context) && i == i2 - 2) {
            nsy.cMA_(view, 8);
        } else {
            nsy.cMA_(view, 0);
        }
    }

    public static boolean b(HashMap<String, Object> hashMap) {
        return hashMap.containsKey("IS_LOAD_DEFAULT_VIEW") && (hashMap.get("IS_LOAD_DEFAULT_VIEW") instanceof Boolean) && ((Boolean) hashMap.get("IS_LOAD_DEFAULT_VIEW")).booleanValue();
    }

    public static boolean c(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        if (hashMap.size() != hashMap2.size()) {
            return false;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (!entry.getKey().equals("CLICK_EVENT_LISTENER") && !entry.getKey().equals("BUTTON_CLICK_EVENT") && (entry.getValue() == null || !entry.getValue().equals(hashMap2.get(entry.getKey())))) {
                return false;
            }
        }
        return true;
    }

    public static void e(Context context, HealthRecycleView healthRecycleView, RecyclerView.Adapter adapter) {
        if (context == null || healthRecycleView == null || adapter == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        healthRecycleView.setLayoutManager(null);
        healthRecycleView.setAdapter(adapter);
        c(context, healthRecycleView, new HealthLinearLayoutManager(context), false, 0);
        adapter.notifyDataSetChanged();
    }

    public static void b(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("page", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.VIEW_DIFFERENT_DATES_1040094.value(), hashMap, 0);
    }
}
