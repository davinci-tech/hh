package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i2 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = 0;
        } else {
            i2 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i3 = 2;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (r2.mHorizontalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0051, code lost:
    
        r17 = r15;
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x004c, code lost:
    
        r5 = true;
        r17 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x004a, code lost:
    
        if (r2.mVerticalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x03dd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01ba  */
    /* JADX WARN: Type inference failed for: r2v63, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r38, androidx.constraintlayout.core.LinearSystem r39, int r40, int r41, androidx.constraintlayout.core.widgets.ChainHead r42) {
        /*
            Method dump skipped, instructions count: 1414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }
}
