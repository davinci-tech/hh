package androidx.transition;

import android.animation.LayoutTransition;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes8.dex */
class ViewGroupUtilsApi14 {
    private static final int LAYOUT_TRANSITION_CHANGING = 4;
    private static final String TAG = "ViewGroupUtilsApi14";
    private static Method sCancelMethod;
    private static boolean sCancelMethodFetched;
    private static LayoutTransition sEmptyLayoutTransition;
    private static Field sLayoutSuppressedField;
    private static boolean sLayoutSuppressedFieldFetched;

    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void suppressLayout(android.view.ViewGroup r6, boolean r7) {
        /*
            android.animation.LayoutTransition r0 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L28
            androidx.transition.ViewGroupUtilsApi14$1 r0 = new androidx.transition.ViewGroupUtilsApi14$1
            r0.<init>()
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition = r0
            r4 = 2
            r0.setAnimator(r4, r2)
            android.animation.LayoutTransition r0 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r0.setAnimator(r3, r2)
            android.animation.LayoutTransition r0 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r0.setAnimator(r1, r2)
            android.animation.LayoutTransition r0 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r4 = 3
            r0.setAnimator(r4, r2)
            android.animation.LayoutTransition r0 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r4 = 4
            r0.setAnimator(r4, r2)
        L28:
            r0 = 2131572105(0x7f0d3589, float:1.8769912E38)
            if (r7 == 0) goto L49
            android.animation.LayoutTransition r7 = r6.getLayoutTransition()
            if (r7 == 0) goto L43
            boolean r1 = r7.isRunning()
            if (r1 == 0) goto L3c
            cancelLayoutTransition(r7)
        L3c:
            android.animation.LayoutTransition r1 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            if (r7 == r1) goto L43
            r6.setTag(r0, r7)
        L43:
            android.animation.LayoutTransition r7 = androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition
            r6.setLayoutTransition(r7)
            goto L91
        L49:
            r6.setLayoutTransition(r2)
            boolean r7 = androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched
            java.lang.String r4 = "ViewGroupUtilsApi14"
            if (r7 != 0) goto L67
            java.lang.Class<android.view.ViewGroup> r7 = android.view.ViewGroup.class
            java.lang.String r5 = "mLayoutSuppressed"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r5)     // Catch: java.lang.NoSuchFieldException -> L60
            androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField = r7     // Catch: java.lang.NoSuchFieldException -> L60
            r7.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> L60
            goto L65
        L60:
            java.lang.String r7 = "Failed to access mLayoutSuppressed field by reflection"
            android.util.Log.i(r4, r7)
        L65:
            androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched = r1
        L67:
            java.lang.reflect.Field r7 = androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField
            if (r7 == 0) goto L83
            boolean r7 = r7.getBoolean(r6)     // Catch: java.lang.IllegalAccessException -> L78
            if (r7 == 0) goto L7e
            java.lang.reflect.Field r1 = androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField     // Catch: java.lang.IllegalAccessException -> L77
            r1.setBoolean(r6, r3)     // Catch: java.lang.IllegalAccessException -> L77
            goto L7e
        L77:
            r3 = r7
        L78:
            java.lang.String r7 = "Failed to get mLayoutSuppressed field by reflection"
            android.util.Log.i(r4, r7)
            r7 = r3
        L7e:
            if (r7 == 0) goto L83
            r6.requestLayout()
        L83:
            java.lang.Object r7 = r6.getTag(r0)
            android.animation.LayoutTransition r7 = (android.animation.LayoutTransition) r7
            if (r7 == 0) goto L91
            r6.setTag(r0, r2)
            r6.setLayoutTransition(r7)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ViewGroupUtilsApi14.suppressLayout(android.view.ViewGroup, boolean):void");
    }

    private static void cancelLayoutTransition(LayoutTransition layoutTransition) {
        if (!sCancelMethodFetched) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                sCancelMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i(TAG, "Failed to access cancel method by reflection");
            }
            sCancelMethodFetched = true;
        }
        Method method = sCancelMethod;
        if (method != null) {
            try {
                method.invoke(layoutTransition, new Object[0]);
            } catch (IllegalAccessException unused2) {
                Log.i(TAG, "Failed to access cancel method by reflection");
            } catch (InvocationTargetException unused3) {
                Log.i(TAG, "Failed to invoke cancel method by reflection");
            }
        }
    }

    private ViewGroupUtilsApi14() {
    }
}
