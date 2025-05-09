package defpackage;

import android.animation.AnimatorSet;
import android.view.View;

/* loaded from: classes7.dex */
public class sku {
    public static bno c(float f, float f2, int i) {
        float abs = Math.abs(f - f2);
        if (Float.compare(abs, 0.0f) == 0) {
            abs = 0.050000012f;
        }
        float f3 = abs;
        return i != 0 ? i != 1 ? i != 2 ? new bno(350.0f, 35.0f, f3, 0.5f, 0.002f) : new bno(410.0f, 38.0f, f3, 1.0f, 0.002f) : new bno(350.0f, 35.0f, f3, 0.5f, 0.002f) : new bno(240.0f, 28.0f, f3, 0.0f, 0.002f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
    
        if (r10 < 0.0f) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.animation.AnimatorSet ebo_(android.view.View r8, int r9, float r10) {
        /*
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L8
        L6:
            r10 = r0
            goto Le
        L8:
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto Le
            goto L6
        Le:
            float r0 = r8.getScaleX()
            bno r9 = c(r0, r10, r9)
            float r0 = r9.getDuration()
            long r0 = (long) r0
            float r2 = r8.getScaleX()
            r3 = 2
            float[] r4 = new float[r3]
            r5 = 0
            r4[r5] = r2
            r2 = 1
            r4[r2] = r10
            java.lang.String r6 = "scaleX"
            android.animation.ObjectAnimator r4 = android.animation.ObjectAnimator.ofFloat(r8, r6, r4)
            r4.setInterpolator(r9)
            r4.setDuration(r0)
            float r6 = r8.getScaleY()
            float[] r7 = new float[r3]
            r7[r5] = r6
            r7[r2] = r10
            java.lang.String r10 = "scaleY"
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r8, r10, r7)
            r8.setInterpolator(r9)
            r8.setDuration(r0)
            android.animation.AnimatorSet r9 = new android.animation.AnimatorSet
            r9.<init>()
            android.animation.Animator[] r10 = new android.animation.Animator[r3]
            r10[r5] = r4
            r10[r2] = r8
            r9.playTogether(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sku.ebo_(android.view.View, int, float):android.animation.AnimatorSet");
    }

    public static AnimatorSet ebp_(View view, int i) {
        return ebo_(view, i, (i == 0 || i == 1 || i != 2) ? 0.95f : 0.9f);
    }

    public static AnimatorSet ebq_(View view, int i) {
        return ebo_(view, i, 1.0f);
    }
}
