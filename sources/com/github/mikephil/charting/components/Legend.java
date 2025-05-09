package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] legendEntryArr) {
        this();
        if (legendEntryArr == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = legendEntryArr;
    }

    public void setEntries(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint paint) {
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormToTextSpace);
        float f = 0.0f;
        float f2 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? this.mFormSize : legendEntry.formSize);
            if (convertDpToPixel2 > f2) {
                f2 = convertDpToPixel2;
            }
            String str = legendEntry.label;
            if (str != null) {
                float calcTextWidth = Utils.calcTextWidth(paint, str);
                if (calcTextWidth > f) {
                    f = calcTextWidth;
                }
            }
        }
        return f + f2 + convertDpToPixel;
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            String str = legendEntry.label;
            if (str != null) {
                float calcTextHeight = Utils.calcTextHeight(paint, str);
                if (calcTextHeight > f) {
                    f = calcTextHeight;
                }
            }
        }
        return f;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> list) {
        this.mExtraEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public void setExtra(LegendEntry[] legendEntryArr) {
        if (legendEntryArr == null) {
            legendEntryArr = new LegendEntry[0];
        }
        this.mExtraEntries = legendEntryArr;
    }

    public void setExtra(int[] iArr, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < Math.min(iArr.length, strArr.length); i++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.formColor = iArr[i];
            legendEntry.label = strArr[i];
            if (legendEntry.formColor == 1122868 || legendEntry.formColor == 0) {
                legendEntry.form = LegendForm.NONE;
            } else if (legendEntry.formColor == 1122867) {
                legendEntry.form = LegendForm.EMPTY;
            }
            arrayList.add(legendEntry);
        }
        this.mExtraEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public void setCustom(LegendEntry[] legendEntryArr) {
        this.mEntries = legendEntryArr;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public void setFormSize(float f) {
        this.mFormSize = f;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float f) {
        this.mFormLineWidth = f;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float f) {
        this.mXEntrySpace = f;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float f) {
        this.mYEntrySpace = f;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float f) {
        this.mFormToTextSpace = f;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float f) {
        this.mStackSpace = f;
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float f) {
        this.mMaxSizePercent = f;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint paint, ViewPortHandler viewPortHandler) {
        float max;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        float f6;
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormSize);
        float convertDpToPixel2 = Utils.convertDpToPixel(this.mStackSpace);
        float convertDpToPixel3 = Utils.convertDpToPixel(this.mFormToTextSpace);
        float convertDpToPixel4 = Utils.convertDpToPixel(this.mXEntrySpace);
        float convertDpToPixel5 = Utils.convertDpToPixel(this.mYEntrySpace);
        boolean z = this.mWordWrapEnabled;
        LegendEntry[] legendEntryArr = this.mEntries;
        int length = legendEntryArr.length;
        this.mTextWidthMax = getMaximumEntryWidth(paint);
        this.mTextHeightMax = getMaximumEntryHeight(paint);
        int i2 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[this.mOrientation.ordinal()];
        if (i2 == 1) {
            float lineHeight = Utils.getLineHeight(paint);
            boolean z2 = false;
            float f7 = 0.0f;
            float f8 = 0.0f;
            float f9 = 0.0f;
            for (int i3 = 0; i3 < length; i3++) {
                LegendEntry legendEntry = legendEntryArr[i3];
                boolean z3 = legendEntry.form != LegendForm.NONE;
                float convertDpToPixel6 = Float.isNaN(legendEntry.formSize) ? convertDpToPixel : Utils.convertDpToPixel(legendEntry.formSize);
                String str = legendEntry.label;
                if (!z2) {
                    f8 = 0.0f;
                }
                if (z3) {
                    if (z2) {
                        f8 += convertDpToPixel2;
                    }
                    f8 += convertDpToPixel6;
                }
                if (str != null) {
                    if (z3 && !z2) {
                        f8 += convertDpToPixel3;
                    } else if (z2) {
                        max = Math.max(f9, f8);
                        f7 += lineHeight + convertDpToPixel5;
                        z2 = false;
                        f = 0.0f;
                        f7 += lineHeight + convertDpToPixel5;
                        float calcTextWidth = f + Utils.calcTextWidth(paint, str);
                        f9 = max;
                        f8 = calcTextWidth;
                    }
                    float f10 = f9;
                    f = f8;
                    max = f10;
                    f7 += lineHeight + convertDpToPixel5;
                    float calcTextWidth2 = f + Utils.calcTextWidth(paint, str);
                    f9 = max;
                    f8 = calcTextWidth2;
                } else {
                    f8 += convertDpToPixel6;
                    if (i3 < length - 1) {
                        f8 += convertDpToPixel2;
                    }
                    z2 = true;
                }
                f9 = Math.max(f9, f8);
            }
            this.mNeededWidth = f9;
            this.mNeededHeight = f7;
        } else if (i2 == 2) {
            float lineHeight2 = Utils.getLineHeight(paint);
            float lineSpacing = Utils.getLineSpacing(paint);
            float contentWidth = viewPortHandler.contentWidth();
            float f11 = this.mMaxSizePercent;
            this.mCalculatedLabelBreakPoints.clear();
            this.mCalculatedLabelSizes.clear();
            this.mCalculatedLineSizes.clear();
            int i4 = 0;
            float f12 = 0.0f;
            float f13 = 0.0f;
            int i5 = -1;
            float f14 = 0.0f;
            while (i4 < length) {
                LegendEntry legendEntry2 = legendEntryArr[i4];
                float f15 = convertDpToPixel;
                float f16 = convertDpToPixel4;
                boolean z4 = legendEntry2.form != LegendForm.NONE;
                float convertDpToPixel7 = Float.isNaN(legendEntry2.formSize) ? f15 : Utils.convertDpToPixel(legendEntry2.formSize);
                String str2 = legendEntry2.label;
                LegendEntry[] legendEntryArr2 = legendEntryArr;
                float f17 = convertDpToPixel5;
                this.mCalculatedLabelBreakPoints.add(false);
                int i6 = i5;
                float f18 = i6 == -1 ? 0.0f : f12 + convertDpToPixel2;
                if (str2 != null) {
                    f2 = convertDpToPixel2;
                    f3 = lineSpacing;
                    this.mCalculatedLabelSizes.add(Utils.calcTextSize(paint, str2));
                    f4 = f18 + (z4 ? convertDpToPixel3 + convertDpToPixel7 : 0.0f) + this.mCalculatedLabelSizes.get(i4).width;
                } else {
                    f2 = convertDpToPixel2;
                    f3 = lineSpacing;
                    float f19 = convertDpToPixel7;
                    this.mCalculatedLabelSizes.add(FSize.getInstance(0.0f, 0.0f));
                    f4 = f18 + (z4 ? f19 : 0.0f);
                    if (i6 == -1) {
                        i6 = i4;
                    }
                }
                if (str2 != null || i4 == length - 1) {
                    float f20 = f14;
                    float f21 = f20 == 0.0f ? 0.0f : f16;
                    if (!z || f20 == 0.0f || (contentWidth * f11) - f20 >= f21 + f4) {
                        f5 = f13;
                        i = -1;
                        f6 = f20 + f21 + f4;
                    } else {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f20, lineHeight2));
                        float max2 = Math.max(f13, f20);
                        this.mCalculatedLabelBreakPoints.set(i6 > -1 ? i6 : i4, true);
                        f6 = f4;
                        f5 = max2;
                        i = -1;
                    }
                    if (i4 == length - 1) {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f6, lineHeight2));
                        f5 = Math.max(f5, f6);
                    }
                    f13 = f5;
                    f14 = f6;
                } else {
                    i = -1;
                }
                if (str2 != null) {
                    i6 = i;
                }
                i4++;
                convertDpToPixel2 = f2;
                lineSpacing = f3;
                convertDpToPixel = f15;
                convertDpToPixel4 = f16;
                i5 = i6;
                f12 = f4;
                legendEntryArr = legendEntryArr2;
                convertDpToPixel5 = f17;
            }
            float f22 = convertDpToPixel5;
            float f23 = lineSpacing;
            this.mNeededWidth = f13;
            this.mNeededHeight = (lineHeight2 * this.mCalculatedLineSizes.size()) + ((f23 + f22) * (this.mCalculatedLineSizes.size() == 0 ? 0 : this.mCalculatedLineSizes.size() - 1));
        }
        this.mNeededHeight += this.mYOffset;
        this.mNeededWidth += this.mXOffset;
    }

    /* renamed from: com.github.mikephil.charting.components.Legend$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;

        static {
            int[] iArr = new int[LegendOrientation.values().length];
            $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = iArr;
            try {
                iArr[LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
