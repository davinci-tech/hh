package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* loaded from: classes8.dex */
public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    public void setSide(int i) {
        this.mSide = i;
    }

    public void setPropagationSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    @Override // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        int i2;
        int i3;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null && transitionValues2 == null) {
            return 0L;
        }
        Rect epicenter = transition.getEpicenter();
        if (transitionValues2 == null || getViewVisibility(transitionValues3) == 0) {
            i = -1;
        } else {
            transitionValues3 = transitionValues2;
            i = 1;
        }
        int viewX = getViewX(transitionValues3);
        int viewY = getViewY(transitionValues3);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (epicenter != null) {
            i2 = epicenter.centerX();
            i3 = epicenter.centerY();
        } else {
            i2 = (round + width) / 2;
            i3 = (round2 + height) / 2;
        }
        float distance = distance(viewGroup, viewX, viewY, i2, i3, round, round2, width, height) / getMaxDistance(viewGroup);
        long duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return Math.round(((duration * i) / this.mPropagationSpeed) * distance);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x001e, code lost:
    
        r0 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x001a, code lost:
    
        if (androidx.core.view.ViewCompat.getLayoutDirection(r6) == 1) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000e, code lost:
    
        if (androidx.core.view.ViewCompat.getLayoutDirection(r6) == 1) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001c, code lost:
    
        r0 = 3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int distance(android.view.View r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14) {
        /*
            r5 = this;
            int r0 = r5.mSide
            r1 = 8388611(0x800003, float:1.1754948E-38)
            r2 = 5
            r3 = 3
            r4 = 1
            if (r0 != r1) goto L11
            int r6 = androidx.core.view.ViewCompat.getLayoutDirection(r6)
            if (r6 != r4) goto L1c
            goto L1e
        L11:
            r1 = 8388613(0x800005, float:1.175495E-38)
            if (r0 != r1) goto L1f
            int r6 = androidx.core.view.ViewCompat.getLayoutDirection(r6)
            if (r6 != r4) goto L1e
        L1c:
            r0 = r3
            goto L1f
        L1e:
            r0 = r2
        L1f:
            if (r0 == r3) goto L45
            if (r0 == r2) goto L3d
            r6 = 48
            if (r0 == r6) goto L35
            r6 = 80
            if (r0 == r6) goto L2d
            r6 = 0
            goto L4c
        L2d:
            int r8 = r8 - r12
            int r9 = r9 - r7
            int r6 = java.lang.Math.abs(r9)
            int r6 = r6 + r8
            goto L4c
        L35:
            int r14 = r14 - r8
            int r9 = r9 - r7
            int r6 = java.lang.Math.abs(r9)
            int r6 = r6 + r14
            goto L4c
        L3d:
            int r7 = r7 - r11
            int r10 = r10 - r8
            int r6 = java.lang.Math.abs(r10)
            int r6 = r6 + r7
            goto L4c
        L45:
            int r13 = r13 - r7
            int r10 = r10 - r8
            int r6 = java.lang.Math.abs(r10)
            int r6 = r6 + r13
        L4c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.SidePropagation.distance(android.view.View, int, int, int, int, int, int, int, int):int");
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        int i = this.mSide;
        if (i == 3 || i == 5 || i == 8388611 || i == 8388613) {
            return viewGroup.getWidth();
        }
        return viewGroup.getHeight();
    }
}
