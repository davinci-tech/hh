package defpackage;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.layoutmanager.HwFloatingBubbleLayout;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* loaded from: classes7.dex */
public class smd {
    public static List<sma> a(int i, int i2, int i3, int i4, int i5) {
        if (i5 + i4 >= i2) {
            if (i4 > i2) {
                Log.w("HwFloatingBubblesCalculationUtils", "calculateViewLocations: the diameter is invalid");
                i4 = i2;
                i5 = 0;
            } else {
                i5 = i2 - i4;
                Log.w("HwFloatingBubblesCalculationUtils", "calculateViewLocations: the margin is invalid");
            }
        }
        List<sma> d = d(i, i2, i4, i5);
        int e = d.size() > 0 ? d.get(0).e() - d.get(0).a() : 0;
        int i6 = Integer.MIN_VALUE;
        for (int i7 = 0; i7 < d.size(); i7++) {
            sma smaVar = d.get(i7);
            int e2 = smaVar.e() - e;
            i6 = (i6 == Integer.MIN_VALUE || e2 - i6 >= 50) ? e2 : i6 + 50;
            smaVar.a(i6);
        }
        return d;
    }

    private static List<sma> d(int i, int i2, int i3, int i4) {
        int i5;
        int random;
        ArrayList arrayList = new ArrayList();
        int i6 = i3 / 2;
        arrayList.add(new sma((int) ((Math.random() * (i2 - (i6 * 2))) + i6), i6, i6));
        int i7 = (int) ((i3 + i4) * 0.8f);
        ArrayList arrayList2 = new ArrayList();
        int i8 = 1;
        while (true) {
            while (i8 <= i) {
                int i9 = i8 - 1;
                int e = ((sma) arrayList.get(i9)).e();
                random = ((int) (Math.random() * (i6 + i4))) + e + i5;
                arrayList2.clear();
                arrayList2.add(new int[]{i6, i2 - i6});
                int i10 = random - e;
                while (i9 >= 0 && i10 < i7) {
                    sma smaVar = (sma) arrayList.get(i9);
                    int sqrt = (int) Math.sqrt(Math.pow(i7, 2.0d) - Math.pow(i10, 2.0d));
                    a(arrayList2, smaVar.c() + sqrt, smaVar.c() - sqrt);
                    i9--;
                    if (i9 >= 0) {
                        i10 = random - ((sma) arrayList.get(i9)).e();
                    }
                }
                i5 = arrayList2.size() == 0 ? i5 + i4 : 0;
            }
            return arrayList;
            arrayList.add(i8, new sma(c(arrayList2), random, i6));
            i8++;
        }
    }

    private static int c(List<int[]> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int[] iArr = list.get(i2);
            i += iArr[1] - iArr[0];
        }
        int nextInt = new SecureRandom().nextInt(1) * i;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int[] iArr2 = list.get(i3);
            int i4 = iArr2[1];
            int i5 = iArr2[0];
            int i6 = i4 - i5;
            if (nextInt <= i6) {
                return i5 + nextInt;
            }
            nextInt -= i6;
        }
        return nextInt;
    }

    private static void a(List<int[]> list, int i, int i2) {
        int i3;
        int i4 = 0;
        boolean z = true;
        while (i4 < list.size()) {
            int[] iArr = list.get(i4);
            if (z) {
                int i5 = iArr[0];
                if (i2 >= i5) {
                    if (i2 > i5 && i2 < iArr[1]) {
                        list.set(i4, new int[]{i5, i2});
                        i4++;
                        list.add(i4, new int[]{i2, iArr[1]});
                    }
                    i4++;
                }
                z = false;
            } else {
                int i6 = iArr[0];
                if (i < i6) {
                    i4++;
                } else {
                    if (i > i6 && i < (i3 = iArr[1])) {
                        list.set(i4, new int[]{i, i3});
                        return;
                    }
                    list.remove(i4);
                }
            }
        }
    }

    public static void eeL_(RecyclerView.LayoutManager layoutManager, View view, float f, sme smeVar) {
        float translationY = view.getTranslationY() + (view.getHeight() * 0.5f) + view.getTop();
        if (Float.compare(translationY, layoutManager.getHeight()) < 0 && Float.compare(translationY, 0.0f) > 0) {
            float a2 = a(smeVar, view, Math.abs(translationY - f) / f);
            view.setScaleY(a2);
            view.setScaleX(a2);
        } else {
            view.setScaleY(0.0f);
            view.setScaleX(0.0f);
        }
        eeK_(view, layoutManager.getWidth());
    }

    public static void eeK_(View view, int i) {
        float f;
        Drawable background = view.getBackground();
        if (background instanceof ska) {
            ska skaVar = (ska) background;
            f = skaVar.c();
            skaVar.d(i, view.getLeft(), view.getTop(), view.getScaleX());
        } else {
            f = 1.0f;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt != null) {
                    float f2 = (1.286f * f) - 0.286f;
                    if (f2 < 0.0f) {
                        f2 = 0.0f;
                    }
                    childAt.setAlpha(f2);
                }
            }
        }
    }

    private static float b(float f, float f2, float f3) {
        if (Float.compare(f2, 0.0f) == 0 || Float.compare(f3, 0.0f) == 0) {
            return f;
        }
        if (Float.compare(f2, 1.0f) == 1) {
            return 0.3f;
        }
        return ((0.3f - f) * f2) + f;
    }

    public static int[][] a(boolean z, int i, RecyclerView.LayoutManager layoutManager, sme smeVar) {
        int childCount = layoutManager.getChildCount();
        double[] dArr = new double[childCount];
        double[] dArr2 = new double[childCount];
        for (int i2 = 0; i2 < childCount; i2++) {
            dArr[i2] = 0.0d;
            dArr2[i2] = i;
        }
        c(z, layoutManager, smeVar, dArr, dArr2);
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, childCount);
        for (int i3 = 0; i3 < childCount; i3++) {
            iArr[0][i3] = (int) dArr[i3];
            iArr[1][i3] = (int) dArr2[i3];
        }
        return iArr;
    }

    private static void c(boolean z, RecyclerView.LayoutManager layoutManager, sme smeVar, double[] dArr, double[] dArr2) {
        int childCount = layoutManager.getChildCount();
        int height = layoutManager.getHeight();
        if (height == 0) {
            return;
        }
        float f = height / 2.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = layoutManager.getChildAt(i);
            if (childAt != null) {
                float height2 = childAt.getHeight() / 2.0f;
                double d = f;
                eeJ_(i, childAt, a(smeVar, childAt, (float) (Math.abs((((childAt.getTop() + height2) + (z ? 0.0f : childAt.getTranslationY())) + dArr2[i]) - d) / d)), layoutManager, dArr);
                c(layoutManager, smeVar, dArr, dArr2, i);
            }
        }
    }

    private static void c(RecyclerView.LayoutManager layoutManager, sme smeVar, double[] dArr, double[] dArr2, int i) {
        float f;
        float f2;
        char c;
        View view;
        int i2;
        float f3;
        float f4;
        int i3;
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        sme smeVar2 = smeVar;
        int i4 = i;
        int childCount = layoutManager.getChildCount();
        View childAt = layoutManager2.getChildAt(i4);
        float height = childAt.getHeight();
        char c2 = IOUtils.DIR_SEPARATOR;
        float f5 = height / 2.0f;
        float height2 = layoutManager.getHeight() / 2.0f;
        float left = childAt.getLeft() + f5 + childAt.getTranslationX();
        float translationY = childAt.getTranslationY() + childAt.getTop() + f5;
        int i5 = i4 + 1;
        while (i5 < childCount) {
            View childAt2 = layoutManager2.getChildAt(i5);
            if (childAt2 == null) {
                f2 = translationY;
                i3 = i5;
                i2 = childCount;
                view = childAt;
                c = c2;
                f = f5;
                f3 = height2;
                f4 = left;
            } else {
                double d = translationY + dArr2[i4];
                f = f5;
                double d2 = height2;
                f2 = translationY;
                float a2 = a(smeVar2, childAt, (float) (Math.abs(d - d2) / d2));
                float height3 = childAt2.getHeight();
                c = IOUtils.DIR_SEPARATOR;
                float f6 = height3 / 2.0f;
                int i6 = childCount;
                view = childAt;
                double translationY2 = childAt2.getTranslationY() + childAt2.getTop() + f6 + dArr2[i5];
                i2 = i6;
                double translationX = childAt2.getTranslationX() + childAt2.getLeft() + f6 + dArr[i5];
                float a3 = a(smeVar2, childAt2, (float) (Math.abs(translationY2 - d2) / d2));
                double d3 = left + dArr[i4];
                f3 = height2;
                f4 = left;
                double d4 = d3 - translationX;
                i3 = i5;
                double d5 = d - translationY2;
                double pow = Math.pow(d4, 2.0d) + Math.pow(d5, 2.0d);
                double d6 = a2 * f;
                double d7 = a3 * f6;
                double d8 = d6 + d7;
                float eeI_ = eeI_(smeVar2, view, childAt2);
                if (Double.compare(pow, Math.pow((1.0f - eeI_) * d8, 2.0d)) == -1) {
                    double sqrt = Math.sqrt(pow);
                    if (Double.compare(sqrt, 0.0d) == 0 || Double.compare(d8, 0.0d) == 0) {
                        return;
                    }
                    double d9 = d8 - sqrt;
                    double d10 = eeI_;
                    double d11 = ((d9 * d7) - ((d7 * d10) * d8)) / d8;
                    double d12 = (d11 * d4) / sqrt;
                    double d13 = (d11 * d5) / sqrt;
                    double c3 = c(layoutManager, d3, d6, d12);
                    double abs = Double.compare(d13, 0.0d) == 1 ? d13 + Math.abs(c3) : d13 - Math.abs(c3);
                    double d14 = -(((d9 * d6) - ((d6 * d10) * d8)) / d8);
                    double d15 = (d4 * d14) / sqrt;
                    double d16 = (d14 * d5) / sqrt;
                    double c4 = c(layoutManager, translationX, d7, d15);
                    double abs2 = Double.compare(d16, 0.0d) == 1 ? d16 + Math.abs(c4) : d16 - Math.abs(c4);
                    double a4 = a(view, childAt2, abs, abs2);
                    dArr[i] = dArr[i] + d12 + c3;
                    dArr2[i] = dArr2[i] + abs + a4;
                    dArr[i3] = dArr[i3] + d15 + c4;
                    dArr2[i3] = dArr2[i3] + abs2 + a4;
                } else {
                    continue;
                }
            }
            i5 = i3 + 1;
            layoutManager2 = layoutManager;
            smeVar2 = smeVar;
            i4 = i;
            c2 = c;
            f5 = f;
            childAt = view;
            childCount = i2;
            translationY = f2;
            height2 = f3;
            left = f4;
        }
    }

    private static double a(View view, View view2, double d, double d2) {
        double d3 = view.isSelected() ? 0.0d + (-d) : 0.0d;
        return view2.isSelected() ? d3 + (-d2) : d3;
    }

    private static float eeI_(sme smeVar, View view, View view2) {
        float b = smeVar.b();
        if (view.isSelected() || view2.isSelected()) {
            return 0.0f;
        }
        return b;
    }

    private static double c(RecyclerView.LayoutManager layoutManager, double d, double d2, double d3) {
        double d4 = d - d2;
        if (b(layoutManager.getPaddingLeft(), d4 + d3)) {
            return (layoutManager.getPaddingLeft() - d4) - d3;
        }
        double d5 = d + d2;
        if (b(d5 + d3, layoutManager.getWidth() - layoutManager.getPaddingRight())) {
            return ((layoutManager.getWidth() - layoutManager.getPaddingRight()) - d5) - d3;
        }
        return 0.0d;
    }

    private static void eeJ_(int i, View view, float f, RecyclerView.LayoutManager layoutManager, double[] dArr) {
        int width = view.getWidth() >> 1;
        int left = view.getLeft() + width;
        int i2 = (int) (width * f);
        double d = left - i2;
        if (b(layoutManager.getPaddingLeft(), d)) {
            dArr[i] = (dArr[i] + layoutManager.getPaddingLeft()) - d;
            return;
        }
        double d2 = left + i2;
        if (b(d2, layoutManager.getWidth() - layoutManager.getPaddingRight())) {
            dArr[i] = ((dArr[i] + layoutManager.getWidth()) - layoutManager.getPaddingRight()) - d2;
        }
    }

    private static boolean b(double d, double d2) {
        return Double.compare(d, d2) == 1;
    }

    public static float a(sme smeVar, View view, float f) {
        float b = b(smeVar.a(), f, smeVar.g() * (view instanceof HwFloatingBubbleLayout ? ((HwFloatingBubbleLayout) view).getRandomFactor() : 1.0f));
        return view.isSelected() ? b * smeVar.e() : b;
    }
}
