package defpackage;

import android.content.Context;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.functionsetcard.view.RatioRelativeLayout;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dpw {
    private static Map<Float[], Integer[]> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Float[], Integer[]> f11774a = new HashMap();

    static {
        Map<Float[], Integer[]> map = b;
        Float valueOf = Float.valueOf(0.0f);
        Float valueOf2 = Float.valueOf(1.0f);
        map.put(new Float[]{valueOf, valueOf2}, new Integer[]{1, 1});
        Map<Float[], Integer[]> map2 = b;
        Float valueOf3 = Float.valueOf(1.3f);
        map2.put(new Float[]{valueOf2, valueOf3}, new Integer[]{81, 98});
        Map<Float[], Integer[]> map3 = b;
        Float valueOf4 = Float.valueOf(1.45f);
        map3.put(new Float[]{valueOf3, valueOf4}, new Integer[]{81, 104});
        Map<Float[], Integer[]> map4 = b;
        Float valueOf5 = Float.valueOf(1.75f);
        map4.put(new Float[]{valueOf4, valueOf5}, new Integer[]{81, 127});
        Map<Float[], Integer[]> map5 = b;
        Float valueOf6 = Float.valueOf(2.0f);
        map5.put(new Float[]{valueOf5, valueOf6}, new Integer[]{81, 131});
        Map<Float[], Integer[]> map6 = b;
        Float valueOf7 = Float.valueOf(3.2f);
        map6.put(new Float[]{valueOf6, valueOf7}, new Integer[]{81, 131});
        f11774a.put(new Float[]{valueOf, valueOf2}, new Integer[]{Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY), 75});
        f11774a.put(new Float[]{valueOf2, valueOf3}, new Integer[]{81, 46});
        f11774a.put(new Float[]{valueOf3, valueOf4}, new Integer[]{81, 49});
        f11774a.put(new Float[]{valueOf4, valueOf5}, new Integer[]{81, 88});
        f11774a.put(new Float[]{valueOf5, valueOf6}, new Integer[]{81, 92});
        f11774a.put(new Float[]{valueOf6, valueOf7}, new Integer[]{81, 92});
    }

    public static void YH_(Context context, RatioRelativeLayout ratioRelativeLayout, ImageView imageView, boolean z, boolean z2) {
        int i;
        if (efb.e(context)) {
            ratioRelativeLayout.setWeight(470, 585);
        } else {
            int[] d = d(context, z);
            if (d.length == 2 && (i = d[0]) != 0) {
                ratioRelativeLayout.setWeight(i, d[1]);
            } else {
                ratioRelativeLayout.setWeight(1, 1);
            }
        }
        if (z2) {
            return;
        }
        ratioRelativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new a(ratioRelativeLayout, imageView));
    }

    public static int[] d(Context context, boolean z) {
        int i;
        int[] iArr = new int[2];
        if (context == null) {
            return iArr;
        }
        int[] iArr2 = new int[2];
        float c = nsn.c();
        if (z) {
            d(b, iArr2, c);
        } else {
            d(f11774a, iArr2, c);
        }
        int i2 = iArr2[0];
        return (i2 == 0 || (i = iArr2[1]) == 0) ? iArr : (i2 == 162 && i == 75) ? f(context) : iArr2;
    }

    private static void d(Map<Float[], Integer[]> map, int[] iArr, float f) {
        for (Map.Entry<Float[], Integer[]> entry : map.entrySet()) {
            Float[] key = entry.getKey();
            if (f - key[0].floatValue() > Math.pow(10.0d, -6.0d) && f - key[1].floatValue() < Math.pow(10.0d, -6.0d)) {
                Integer[] value = entry.getValue();
                iArr[0] = value[0].intValue();
                iArr[1] = value[1].intValue();
                return;
            }
        }
    }

    private static int[] f(Context context) {
        int c = c(context);
        LogUtil.a("FunctionSetCardSizeUtil", "cardWidth: ", Integer.valueOf(c));
        int c2 = c(context, c);
        LogUtil.a("FunctionSetCardSizeUtil", "cardBottomMargin: ", Integer.valueOf(c2));
        int i = (c - c2) / 2;
        LogUtil.a("FunctionSetCardSizeUtil", "cardWidth: ", Integer.valueOf(c), "smallCardHeight: ", Integer.valueOf(i));
        return new int[]{c, i};
    }

    public static int b(Context context) {
        int c = c(context);
        return (c - c(context, c)) / 2;
    }

    public static int d(Context context) {
        return nrr.b(context);
    }

    public static int c(Context context, int i) {
        int b2 = nrr.b(context);
        LogUtil.a("FunctionSetCardSizeUtil", "getFunctionSetCardBottom: ", Integer.valueOf(b2));
        return (i - b2) % 2 == 0 ? b2 : b2 + 1;
    }

    public static int a(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
    }

    public static int e(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
    }

    public static int c(Context context) {
        int intValue;
        int i;
        int i2;
        int a2 = a(context);
        int e = e(context);
        int d = d(context);
        if (nsn.ag(BaseApplication.wa_())) {
            intValue = a2 + e + (d * 3);
            i = 4;
        } else {
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            intValue = a2 + e + ((Integer) safeRegionWidth.first).intValue() + ((Integer) safeRegionWidth.second).intValue() + d;
            i = 2;
        }
        int i3 = context.getResources().getDisplayMetrics().widthPixels;
        int i4 = i3 - intValue;
        if (i4 % 2 == 0) {
            i2 = i4 / i;
        } else {
            i2 = (i4 + 1) / i;
        }
        LogUtil.a("FunctionSetCardSizeUtil", "screenWidth: ", Integer.valueOf(i3), " margin:", Integer.valueOf(intValue), " width: ", Integer.valueOf(i2));
        return i2;
    }

    static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private ImageView b;
        private RatioRelativeLayout e;

        public a(RatioRelativeLayout ratioRelativeLayout, ImageView imageView) {
            this.e = ratioRelativeLayout;
            this.b = imageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            LogUtil.a("FunctionSetCardSizeUtil", "onGlobalLayout");
            this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            ImageView imageView = this.b;
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    int width = this.e.getWidth();
                    LogUtil.a("FunctionSetCardSizeUtil", "getWidth: ", Integer.valueOf(width));
                    layoutParams2.height = width;
                    this.b.setLayoutParams(layoutParams2);
                }
            }
        }
    }
}
