package androidx.constraintlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean MEASURE = false;
    private static final boolean OPTIMIZE_HEIGHT_CHANGE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.1.4";
    private static SharedValues sSharedValues;
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    protected ConstraintLayoutStates mConstraintLayoutSpec;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private ConstraintsChangedListener mConstraintsChangedListener;
    private HashMap<String, Integer> mDesignIds;
    protected boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    public ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    Measurer mMeasurer;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private int mOnMeasureHeightMeasureSpec;
    private int mOnMeasureWidthMeasureSpec;
    private int mOptimizationLevel;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget;

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public static SharedValues getSharedValues() {
        if (sSharedValues == null) {
            sSharedValues = new SharedValues();
        }
        return sSharedValues;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> hashMap = this.mDesignIds;
        if (hashMap == null || !hashMap.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    public ConstraintLayout(Context context) {
        super(context);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(null, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, i, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new Measurer(this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(attributeSet, i, i2);
    }

    @Override // android.view.View
    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    class Measurer implements BasicMeasure.Measurer {
        ConstraintLayout layout;
        int layoutHeightSpec;
        int layoutWidthSpec;
        int paddingBottom;
        int paddingHeight;
        int paddingTop;
        int paddingWidth;

        public void captureLayoutInfo(int i, int i2, int i3, int i4, int i5, int i6) {
            this.paddingTop = i3;
            this.paddingBottom = i4;
            this.paddingWidth = i5;
            this.paddingHeight = i6;
            this.layoutWidthSpec = i;
            this.layoutHeightSpec = i2;
        }

        public Measurer(ConstraintLayout constraintLayout) {
            this.layout = constraintLayout;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        public final void measure(ConstraintWidget constraintWidget, BasicMeasure.Measure measure) {
            int makeMeasureSpec;
            int makeMeasureSpec2;
            int baseline;
            int max;
            int i;
            int i2;
            int i3;
            if (constraintWidget == null) {
                return;
            }
            if (constraintWidget.getVisibility() == 8 && !constraintWidget.isInPlaceholder()) {
                measure.measuredWidth = 0;
                measure.measuredHeight = 0;
                measure.measuredBaseline = 0;
                return;
            }
            if (constraintWidget.getParent() == null) {
                return;
            }
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = measure.verticalBehavior;
            int i4 = measure.horizontalDimension;
            int i5 = measure.verticalDimension;
            int i6 = this.paddingTop + this.paddingBottom;
            int i7 = this.paddingWidth;
            View view = (View) constraintWidget.getCompanionWidget();
            int i8 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour.ordinal()];
            if (i8 == 1) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
            } else if (i8 == 2) {
                makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, i7, -2);
            } else if (i8 == 3) {
                makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, i7 + constraintWidget.getHorizontalMargin(), -1);
            } else if (i8 != 4) {
                makeMeasureSpec = 0;
            } else {
                makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.layoutWidthSpec, i7, -2);
                boolean z = constraintWidget.mMatchConstraintDefaultWidth == 1;
                if (measure.measureStrategy == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                    boolean z2 = view.getMeasuredHeight() == constraintWidget.getHeight();
                    if (measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z || ((z && z2) || (view instanceof Placeholder) || constraintWidget.isResolvedHorizontally())) {
                        makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                    }
                }
            }
            int i9 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour2.ordinal()];
            if (i9 == 1) {
                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
            } else if (i9 == 2) {
                makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, i6, -2);
            } else if (i9 == 3) {
                makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, i6 + constraintWidget.getVerticalMargin(), -1);
            } else if (i9 != 4) {
                makeMeasureSpec2 = 0;
            } else {
                makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.layoutHeightSpec, i6, -2);
                boolean z3 = constraintWidget.mMatchConstraintDefaultHeight == 1;
                if (measure.measureStrategy == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS || measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) {
                    boolean z4 = view.getMeasuredWidth() == constraintWidget.getWidth();
                    if (measure.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS || !z3 || ((z3 && z4) || (view instanceof Placeholder) || constraintWidget.isResolvedVertically())) {
                        makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                    }
                }
            }
            ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
            if (constraintWidgetContainer != null && Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 256) && view.getMeasuredWidth() == constraintWidget.getWidth() && view.getMeasuredWidth() < constraintWidgetContainer.getWidth() && view.getMeasuredHeight() == constraintWidget.getHeight() && view.getMeasuredHeight() < constraintWidgetContainer.getHeight() && view.getBaseline() == constraintWidget.getBaselineDistance() && !constraintWidget.isMeasureRequested() && isSimilarSpec(constraintWidget.getLastHorizontalMeasureSpec(), makeMeasureSpec, constraintWidget.getWidth()) && isSimilarSpec(constraintWidget.getLastVerticalMeasureSpec(), makeMeasureSpec2, constraintWidget.getHeight())) {
                measure.measuredWidth = constraintWidget.getWidth();
                measure.measuredHeight = constraintWidget.getHeight();
                measure.measuredBaseline = constraintWidget.getBaselineDistance();
                return;
            }
            boolean z5 = dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z6 = dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z7 = dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED;
            boolean z8 = dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED;
            boolean z9 = z5 && constraintWidget.mDimensionRatio > 0.0f;
            boolean z10 = z6 && constraintWidget.mDimensionRatio > 0.0f;
            if (view == null) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (measure.measureStrategy != BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS && measure.measureStrategy != BasicMeasure.Measure.USE_GIVEN_DIMENSIONS && z5 && constraintWidget.mMatchConstraintDefaultWidth == 0 && z6 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                i3 = -1;
                baseline = 0;
                max = 0;
                i2 = 0;
            } else {
                if ((view instanceof VirtualLayout) && (constraintWidget instanceof androidx.constraintlayout.core.widgets.VirtualLayout)) {
                    ((VirtualLayout) view).onMeasure((androidx.constraintlayout.core.widgets.VirtualLayout) constraintWidget, makeMeasureSpec, makeMeasureSpec2);
                } else {
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                }
                constraintWidget.setLastMeasureSpec(makeMeasureSpec, makeMeasureSpec2);
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                baseline = view.getBaseline();
                max = constraintWidget.mMatchConstraintMinWidth > 0 ? Math.max(constraintWidget.mMatchConstraintMinWidth, measuredWidth) : measuredWidth;
                if (constraintWidget.mMatchConstraintMaxWidth > 0) {
                    max = Math.min(constraintWidget.mMatchConstraintMaxWidth, max);
                }
                if (constraintWidget.mMatchConstraintMinHeight > 0) {
                    i2 = Math.max(constraintWidget.mMatchConstraintMinHeight, measuredHeight);
                    i = makeMeasureSpec2;
                } else {
                    i = makeMeasureSpec2;
                    i2 = measuredHeight;
                }
                if (constraintWidget.mMatchConstraintMaxHeight > 0) {
                    i2 = Math.min(constraintWidget.mMatchConstraintMaxHeight, i2);
                }
                if (!Optimizer.enabled(ConstraintLayout.this.mOptimizationLevel, 1)) {
                    if (z9 && z7) {
                        max = (int) ((i2 * constraintWidget.mDimensionRatio) + 0.5f);
                    } else if (z10 && z8) {
                        i2 = (int) ((max / constraintWidget.mDimensionRatio) + 0.5f);
                    }
                }
                if (measuredWidth != max || measuredHeight != i2) {
                    if (measuredWidth != max) {
                        makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max, 1073741824);
                    }
                    int makeMeasureSpec3 = measuredHeight != i2 ? View.MeasureSpec.makeMeasureSpec(i2, 1073741824) : i;
                    view.measure(makeMeasureSpec, makeMeasureSpec3);
                    constraintWidget.setLastMeasureSpec(makeMeasureSpec, makeMeasureSpec3);
                    max = view.getMeasuredWidth();
                    i2 = view.getMeasuredHeight();
                    baseline = view.getBaseline();
                }
                i3 = -1;
            }
            boolean z11 = baseline != i3;
            measure.measuredNeedsSolverPass = (max == measure.horizontalDimension && i2 == measure.verticalDimension) ? false : true;
            if (layoutParams.needsBaseline) {
                z11 = true;
            }
            if (z11 && baseline != -1 && constraintWidget.getBaselineDistance() != baseline) {
                measure.measuredNeedsSolverPass = true;
            }
            measure.measuredWidth = max;
            measure.measuredHeight = i2;
            measure.measuredHasBaseline = z11;
            measure.measuredBaseline = baseline;
        }

        private boolean isSimilarSpec(int i, int i2, int i3) {
            if (i == i2) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i);
            View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
            }
            return false;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        public final void didMeasures() {
            int childCount = this.layout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.layout.getChildAt(i);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).updatePostMeasure(this.layout);
                }
            }
            int size = this.layout.mConstraintHelpers.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    ((ConstraintHelper) this.layout.mConstraintHelpers.get(i2)).updatePostMeasure(this.layout);
                }
            }
        }
    }

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mLayoutWidget.setMeasurer(this.mMeasurer);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.visibility, R.attr.layout_width, R.attr.layout_height, R.attr.layout_margin, R.attr.layout_marginLeft, R.attr.layout_marginTop, R.attr.layout_marginRight, R.attr.layout_marginBottom, R.attr.maxWidth, R.attr.maxHeight, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingEnd, R.attr.layout_marginStart, R.attr.layout_marginEnd, R.attr.elevation, R.attr.layout_marginHorizontal, R.attr.layout_marginVertical, com.huawei.health.R.attr._2131099768_res_0x7f060078, com.huawei.health.R.attr._2131099769_res_0x7f060079, com.huawei.health.R.attr._2131099770_res_0x7f06007a, com.huawei.health.R.attr._2131099856_res_0x7f0600d0, com.huawei.health.R.attr._2131099892_res_0x7f0600f4, com.huawei.health.R.attr._2131099893_res_0x7f0600f5, com.huawei.health.R.attr._2131099894_res_0x7f0600f6, com.huawei.health.R.attr._2131099895_res_0x7f0600f7, com.huawei.health.R.attr._2131099896_res_0x7f0600f8, com.huawei.health.R.attr._2131099929_res_0x7f060119, com.huawei.health.R.attr._2131099932_res_0x7f06011c, com.huawei.health.R.attr._2131099933_res_0x7f06011d, com.huawei.health.R.attr._2131100092_res_0x7f0601bc, com.huawei.health.R.attr._2131100093_res_0x7f0601bd, com.huawei.health.R.attr._2131100094_res_0x7f0601be, com.huawei.health.R.attr._2131100095_res_0x7f0601bf, com.huawei.health.R.attr._2131100096_res_0x7f0601c0, com.huawei.health.R.attr._2131100097_res_0x7f0601c1, com.huawei.health.R.attr._2131100098_res_0x7f0601c2, com.huawei.health.R.attr._2131100099_res_0x7f0601c3, com.huawei.health.R.attr._2131100100_res_0x7f0601c4, com.huawei.health.R.attr._2131100101_res_0x7f0601c5, com.huawei.health.R.attr._2131100102_res_0x7f0601c6, com.huawei.health.R.attr._2131100103_res_0x7f0601c7, com.huawei.health.R.attr._2131100104_res_0x7f0601c8, com.huawei.health.R.attr._2131100106_res_0x7f0601ca, com.huawei.health.R.attr._2131100107_res_0x7f0601cb, com.huawei.health.R.attr._2131100108_res_0x7f0601cc, com.huawei.health.R.attr._2131100109_res_0x7f0601cd, com.huawei.health.R.attr._2131100110_res_0x7f0601ce, com.huawei.health.R.attr._2131100129_res_0x7f0601e1, com.huawei.health.R.attr._2131100705_res_0x7f060421, com.huawei.health.R.attr._2131100715_res_0x7f06042b, com.huawei.health.R.attr._2131100716_res_0x7f06042c, com.huawei.health.R.attr._2131100717_res_0x7f06042d, com.huawei.health.R.attr._2131100718_res_0x7f06042e, com.huawei.health.R.attr._2131100719_res_0x7f06042f, com.huawei.health.R.attr._2131100720_res_0x7f060430, com.huawei.health.R.attr._2131100721_res_0x7f060431, com.huawei.health.R.attr._2131100722_res_0x7f060432, com.huawei.health.R.attr._2131100723_res_0x7f060433, com.huawei.health.R.attr._2131100724_res_0x7f060434, com.huawei.health.R.attr._2131100725_res_0x7f060435, com.huawei.health.R.attr._2131100726_res_0x7f060436, com.huawei.health.R.attr._2131100727_res_0x7f060437, com.huawei.health.R.attr._2131100728_res_0x7f060438, com.huawei.health.R.attr._2131100729_res_0x7f060439, com.huawei.health.R.attr._2131100730_res_0x7f06043a, com.huawei.health.R.attr._2131100731_res_0x7f06043b, com.huawei.health.R.attr._2131100732_res_0x7f06043c, com.huawei.health.R.attr._2131100733_res_0x7f06043d, com.huawei.health.R.attr._2131100734_res_0x7f06043e, com.huawei.health.R.attr._2131100735_res_0x7f06043f, com.huawei.health.R.attr._2131100736_res_0x7f060440, com.huawei.health.R.attr._2131100737_res_0x7f060441, com.huawei.health.R.attr._2131100738_res_0x7f060442, com.huawei.health.R.attr._2131100739_res_0x7f060443, com.huawei.health.R.attr._2131100740_res_0x7f060444, com.huawei.health.R.attr._2131100741_res_0x7f060445, com.huawei.health.R.attr._2131100742_res_0x7f060446, com.huawei.health.R.attr._2131100743_res_0x7f060447, com.huawei.health.R.attr._2131100744_res_0x7f060448, com.huawei.health.R.attr._2131100745_res_0x7f060449, com.huawei.health.R.attr._2131100746_res_0x7f06044a, com.huawei.health.R.attr._2131100747_res_0x7f06044b, com.huawei.health.R.attr._2131100748_res_0x7f06044c, com.huawei.health.R.attr._2131100749_res_0x7f06044d, com.huawei.health.R.attr._2131100750_res_0x7f06044e, com.huawei.health.R.attr._2131100751_res_0x7f06044f, com.huawei.health.R.attr._2131100752_res_0x7f060450, com.huawei.health.R.attr._2131100753_res_0x7f060451, com.huawei.health.R.attr._2131100754_res_0x7f060452, com.huawei.health.R.attr._2131100755_res_0x7f060453, com.huawei.health.R.attr._2131100756_res_0x7f060454, com.huawei.health.R.attr._2131100757_res_0x7f060455, com.huawei.health.R.attr._2131100758_res_0x7f060456, com.huawei.health.R.attr._2131100759_res_0x7f060457, com.huawei.health.R.attr._2131100760_res_0x7f060458, com.huawei.health.R.attr._2131100762_res_0x7f06045a, com.huawei.health.R.attr._2131100763_res_0x7f06045b, com.huawei.health.R.attr._2131100764_res_0x7f06045c, com.huawei.health.R.attr._2131100765_res_0x7f06045d, com.huawei.health.R.attr._2131100766_res_0x7f06045e, com.huawei.health.R.attr._2131100767_res_0x7f06045f, com.huawei.health.R.attr._2131100768_res_0x7f060460, com.huawei.health.R.attr._2131100769_res_0x7f060461, com.huawei.health.R.attr._2131100770_res_0x7f060462, com.huawei.health.R.attr._2131100773_res_0x7f060465, com.huawei.health.R.attr._2131100774_res_0x7f060466, com.huawei.health.R.attr._2131100777_res_0x7f060469}, i, i2);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == 16) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == 17) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == 14) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == 15) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == 113) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == 56) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.load(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    protected void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof androidx.constraintlayout.core.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new androidx.constraintlayout.core.widgets.Guideline();
            layoutParams.isGuideline = true;
            ((androidx.constraintlayout.core.widgets.Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        this.mLayoutWidget.remove(getViewWidget(view));
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = true;
    }

    public void setMinWidth(int i) {
        if (i == this.mMinWidth) {
            return;
        }
        this.mMinWidth = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.mMinHeight) {
            return;
        }
        this.mMinHeight = i;
        requestLayout();
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        if (i == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = i;
        requestLayout();
    }

    public void setMaxHeight(int i) {
        if (i == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = i;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private boolean updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            setChildrenConstraints();
        }
        return z;
    }

    private void setChildrenConstraints() {
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (isInEditMode) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.applyToInternal(this, true);
        }
        this.mLayoutWidget.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i4 = 0; i4 < size; i4++) {
                this.mConstraintHelpers.get(i4).updatePreLayout(this);
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt3 = getChildAt(i5);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).updatePreLayout(this);
            }
        }
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
        this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt4 = getChildAt(i6);
            this.mTempMapIdToWidget.put(childAt4.getId(), getViewWidget(childAt4));
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt5 = getChildAt(i7);
            ConstraintWidget viewWidget2 = getViewWidget(childAt5);
            if (viewWidget2 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt5.getLayoutParams();
                this.mLayoutWidget.add(viewWidget2);
                applyConstraintsFromLayoutParams(isInEditMode, childAt5, viewWidget2, layoutParams, this.mTempMapIdToWidget);
            }
        }
    }

    public void applyConstraintsFromLayoutParams(boolean z, View view, ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        layoutParams.validate();
        layoutParams.helped = false;
        constraintWidget.setVisibility(view.getVisibility());
        if (layoutParams.isInPlaceholder) {
            constraintWidget.setInPlaceholder(true);
            constraintWidget.setVisibility(8);
        }
        constraintWidget.setCompanionWidget(view);
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).resolveRtl(constraintWidget, this.mLayoutWidget.isRtl());
        }
        if (layoutParams.isGuideline) {
            androidx.constraintlayout.core.widgets.Guideline guideline = (androidx.constraintlayout.core.widgets.Guideline) constraintWidget;
            int i = layoutParams.resolvedGuideBegin;
            int i2 = layoutParams.resolvedGuideEnd;
            float f = layoutParams.resolvedGuidePercent;
            if (f != -1.0f) {
                guideline.setGuidePercent(f);
                return;
            } else if (i != -1) {
                guideline.setGuideBegin(i);
                return;
            } else {
                if (i2 != -1) {
                    guideline.setGuideEnd(i2);
                    return;
                }
                return;
            }
        }
        int i3 = layoutParams.resolvedLeftToLeft;
        int i4 = layoutParams.resolvedLeftToRight;
        int i5 = layoutParams.resolvedRightToLeft;
        int i6 = layoutParams.resolvedRightToRight;
        int i7 = layoutParams.resolveGoneLeftMargin;
        int i8 = layoutParams.resolveGoneRightMargin;
        float f2 = layoutParams.resolvedHorizontalBias;
        if (layoutParams.circleConstraint != -1) {
            ConstraintWidget constraintWidget6 = sparseArray.get(layoutParams.circleConstraint);
            if (constraintWidget6 != null) {
                constraintWidget.connectCircularConstraint(constraintWidget6, layoutParams.circleAngle, layoutParams.circleRadius);
            }
        } else {
            if (i3 != -1) {
                ConstraintWidget constraintWidget7 = sparseArray.get(i3);
                if (constraintWidget7 != null) {
                    constraintWidget.immediateConnect(ConstraintAnchor.Type.LEFT, constraintWidget7, ConstraintAnchor.Type.LEFT, layoutParams.leftMargin, i7);
                }
            } else if (i4 != -1 && (constraintWidget2 = sparseArray.get(i4)) != null) {
                constraintWidget.immediateConnect(ConstraintAnchor.Type.LEFT, constraintWidget2, ConstraintAnchor.Type.RIGHT, layoutParams.leftMargin, i7);
            }
            if (i5 != -1) {
                ConstraintWidget constraintWidget8 = sparseArray.get(i5);
                if (constraintWidget8 != null) {
                    constraintWidget.immediateConnect(ConstraintAnchor.Type.RIGHT, constraintWidget8, ConstraintAnchor.Type.LEFT, layoutParams.rightMargin, i8);
                }
            } else if (i6 != -1 && (constraintWidget3 = sparseArray.get(i6)) != null) {
                constraintWidget.immediateConnect(ConstraintAnchor.Type.RIGHT, constraintWidget3, ConstraintAnchor.Type.RIGHT, layoutParams.rightMargin, i8);
            }
            if (layoutParams.topToTop != -1) {
                ConstraintWidget constraintWidget9 = sparseArray.get(layoutParams.topToTop);
                if (constraintWidget9 != null) {
                    constraintWidget.immediateConnect(ConstraintAnchor.Type.TOP, constraintWidget9, ConstraintAnchor.Type.TOP, layoutParams.topMargin, layoutParams.goneTopMargin);
                }
            } else if (layoutParams.topToBottom != -1 && (constraintWidget4 = sparseArray.get(layoutParams.topToBottom)) != null) {
                constraintWidget.immediateConnect(ConstraintAnchor.Type.TOP, constraintWidget4, ConstraintAnchor.Type.BOTTOM, layoutParams.topMargin, layoutParams.goneTopMargin);
            }
            if (layoutParams.bottomToTop != -1) {
                ConstraintWidget constraintWidget10 = sparseArray.get(layoutParams.bottomToTop);
                if (constraintWidget10 != null) {
                    constraintWidget.immediateConnect(ConstraintAnchor.Type.BOTTOM, constraintWidget10, ConstraintAnchor.Type.TOP, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
                }
            } else if (layoutParams.bottomToBottom != -1 && (constraintWidget5 = sparseArray.get(layoutParams.bottomToBottom)) != null) {
                constraintWidget.immediateConnect(ConstraintAnchor.Type.BOTTOM, constraintWidget5, ConstraintAnchor.Type.BOTTOM, layoutParams.bottomMargin, layoutParams.goneBottomMargin);
            }
            if (layoutParams.baselineToBaseline != -1) {
                setWidgetBaseline(constraintWidget, layoutParams, sparseArray, layoutParams.baselineToBaseline, ConstraintAnchor.Type.BASELINE);
            } else if (layoutParams.baselineToTop != -1) {
                setWidgetBaseline(constraintWidget, layoutParams, sparseArray, layoutParams.baselineToTop, ConstraintAnchor.Type.TOP);
            } else if (layoutParams.baselineToBottom != -1) {
                setWidgetBaseline(constraintWidget, layoutParams, sparseArray, layoutParams.baselineToBottom, ConstraintAnchor.Type.BOTTOM);
            }
            if (f2 >= 0.0f) {
                constraintWidget.setHorizontalBiasPercent(f2);
            }
            if (layoutParams.verticalBias >= 0.0f) {
                constraintWidget.setVerticalBiasPercent(layoutParams.verticalBias);
            }
        }
        if (z && (layoutParams.editorAbsoluteX != -1 || layoutParams.editorAbsoluteY != -1)) {
            constraintWidget.setOrigin(layoutParams.editorAbsoluteX, layoutParams.editorAbsoluteY);
        }
        if (!layoutParams.horizontalDimensionFixed) {
            if (layoutParams.width == -1) {
                if (layoutParams.constrainedWidth) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                } else {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                }
                constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = layoutParams.leftMargin;
                constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = layoutParams.rightMargin;
            } else {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                constraintWidget.setWidth(0);
            }
        } else {
            constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.setWidth(layoutParams.width);
            if (layoutParams.width == -2) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        }
        if (!layoutParams.verticalDimensionFixed) {
            if (layoutParams.height == -1) {
                if (layoutParams.constrainedHeight) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                } else {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                }
                constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).mMargin = layoutParams.topMargin;
                constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = layoutParams.bottomMargin;
            } else {
                constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                constraintWidget.setHeight(0);
            }
        } else {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.setHeight(layoutParams.height);
            if (layoutParams.height == -2) {
                constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            }
        }
        constraintWidget.setDimensionRatio(layoutParams.dimensionRatio);
        constraintWidget.setHorizontalWeight(layoutParams.horizontalWeight);
        constraintWidget.setVerticalWeight(layoutParams.verticalWeight);
        constraintWidget.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
        constraintWidget.setVerticalChainStyle(layoutParams.verticalChainStyle);
        constraintWidget.setWrapBehaviorInParent(layoutParams.wrapBehaviorInParent);
        constraintWidget.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
        constraintWidget.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
    }

    private void setWidgetBaseline(ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray, int i, ConstraintAnchor.Type type) {
        View view = this.mChildrenByIds.get(i);
        ConstraintWidget constraintWidget2 = sparseArray.get(i);
        if (constraintWidget2 == null || view == null || !(view.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        layoutParams.needsBaseline = true;
        if (type == ConstraintAnchor.Type.BASELINE) {
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.needsBaseline = true;
            layoutParams2.widget.setHasBaseline(true);
        }
        constraintWidget.getAnchor(ConstraintAnchor.Type.BASELINE).connect(constraintWidget2.getAnchor(type), layoutParams.baselineMargin, layoutParams.goneBaselineMargin, true);
        constraintWidget.setHasBaseline(true);
        constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).reset();
        constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
    }

    private final ConstraintWidget getTargetWidget(int i) {
        if (i == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(i);
        if (view == null && (view = findViewById(i)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).widget;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).widget;
        }
        return null;
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    public void resolveSystem(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3) {
        int i4;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int max = Math.max(0, getPaddingTop());
        int max2 = Math.max(0, getPaddingBottom());
        int i5 = max + max2;
        int paddingWidth = getPaddingWidth();
        this.mMeasurer.captureLayoutInfo(i2, i3, max, max2, paddingWidth, i5);
        int max3 = Math.max(0, getPaddingStart());
        int max4 = Math.max(0, getPaddingEnd());
        if (max3 > 0 || max4 > 0) {
            i4 = isRtl() ? max4 : max3;
        } else {
            i4 = Math.max(0, getPaddingLeft());
        }
        int i6 = size - paddingWidth;
        int i7 = size2 - i5;
        setSelfDimensionBehaviour(constraintWidgetContainer, mode, i6, mode2, i7);
        constraintWidgetContainer.measure(i, mode, i6, mode2, i7, this.mLastMeasureWidth, this.mLastMeasureHeight, i4, max);
    }

    public void resolveMeasuredDimension(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5 = this.mMeasurer.paddingHeight;
        int resolveSizeAndState = resolveSizeAndState(i3 + this.mMeasurer.paddingWidth, i, 0);
        int resolveSizeAndState2 = resolveSizeAndState(i4 + i5, i2, 0);
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK);
        int min2 = Math.min(this.mMaxHeight, resolveSizeAndState2 & ViewCompat.MEASURED_SIZE_MASK);
        if (z) {
            min |= 16777216;
        }
        if (z2) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
        this.mLastMeasureWidth = min;
        this.mLastMeasureHeight = min2;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (!this.mDirtyHierarchy) {
            int childCount = getChildCount();
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    break;
                }
                if (getChildAt(i3).isLayoutRequested()) {
                    this.mDirtyHierarchy = true;
                    break;
                }
                i3++;
            }
        }
        this.mOnMeasureWidthMeasureSpec = i;
        this.mOnMeasureHeightMeasureSpec = i2;
        this.mLayoutWidget.setRtl(isRtl());
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            if (updateHierarchy()) {
                this.mLayoutWidget.updateHierarchy();
            }
        }
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i, i2);
        resolveMeasuredDimension(i, i2, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
    }

    public boolean isRtl() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int max2 = Math.max(0, getPaddingStart()) + Math.max(0, getPaddingEnd());
        return max2 > 0 ? max2 : max;
    }

    protected void setSelfDimensionBehaviour(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3, int i4) {
        int i5 = this.mMeasurer.paddingHeight;
        int i6 = this.mMeasurer.paddingWidth;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        int childCount = getChildCount();
        if (i == Integer.MIN_VALUE) {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i2 = Math.max(0, this.mMinWidth);
            }
        } else if (i != 0) {
            if (i == 1073741824) {
                i2 = Math.min(this.mMaxWidth - i6, i2);
            }
            i2 = 0;
        } else {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i2 = Math.max(0, this.mMinWidth);
            }
            i2 = 0;
        }
        if (i3 == Integer.MIN_VALUE) {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i4 = Math.max(0, this.mMinHeight);
            }
        } else if (i3 != 0) {
            if (i3 == 1073741824) {
                i4 = Math.min(this.mMaxHeight - i5, i4);
            }
            i4 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (childCount == 0) {
                i4 = Math.max(0, this.mMinHeight);
            }
            i4 = 0;
        }
        if (i2 != constraintWidgetContainer.getWidth() || i4 != constraintWidgetContainer.getHeight()) {
            constraintWidgetContainer.invalidateMeasures();
        }
        constraintWidgetContainer.setX(0);
        constraintWidgetContainer.setY(0);
        constraintWidgetContainer.setMaxWidth(this.mMaxWidth - i6);
        constraintWidgetContainer.setMaxHeight(this.mMaxHeight - i5);
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setHorizontalDimensionBehaviour(dimensionBehaviour);
        constraintWidgetContainer.setWidth(i2);
        constraintWidgetContainer.setVerticalDimensionBehaviour(dimensionBehaviour2);
        constraintWidgetContainer.setHeight(i4);
        constraintWidgetContainer.setMinWidth(this.mMinWidth - i6);
        constraintWidgetContainer.setMinHeight(this.mMinHeight - i5);
    }

    public void setState(int i, int i2, int i3) {
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i, i2, i3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || layoutParams.isVirtualGroup || isInEditMode) && !layoutParams.isInPlaceholder) {
                int x = constraintWidget.getX();
                int y = constraintWidget.getY();
                int width = constraintWidget.getWidth() + x;
                int height = constraintWidget.getHeight() + y;
                childAt.layout(x, y, width, height);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(x, y, width, height);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                this.mConstraintHelpers.get(i6).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        this.mLayoutWidget.setOptimizationLevel(i);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int i) {
        return this.mChildrenByIds.get(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                this.mConstraintHelpers.get(i).updatePreDraw(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i3 = (int) ((parseInt / 1080.0f) * width);
                        int i4 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(SupportMenu.CATEGORY_MASK);
                        float f = i3;
                        float f2 = i4;
                        float f3 = i3 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float parseInt4 = i4 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, parseInt4, paint);
                        canvas.drawLine(f3, parseInt4, f, parseInt4, paint);
                        canvas.drawLine(f, parseInt4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, parseInt4, paint);
                        canvas.drawLine(f, parseInt4, f3, f2, paint);
                    }
                }
            }
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.setOnConstraintsChanged(constraintsChangedListener);
        }
    }

    public void loadLayoutDescription(int i) {
        if (i != 0) {
            try {
                this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i);
                return;
            } catch (Resources.NotFoundException unused) {
                this.mConstraintLayoutSpec = null;
                return;
            }
        }
        this.mConstraintLayoutSpec = null;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int CIRCLE = 8;
        public static final int END = 7;
        public static final int GONE_UNSET = Integer.MIN_VALUE;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
        public static final int WRAP_BEHAVIOR_INCLUDED = 0;
        public static final int WRAP_BEHAVIOR_SKIPPED = 3;
        public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String constraintTag;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBaselineMargin;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean guidelineUseRtl;
        boolean heightSet;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        boolean isVirtualGroup;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;
        boolean widthSet;
        public int wrapBehaviorInParent;

        public ConstraintWidget getConstraintWidget() {
            return this.widget;
        }

        public void setWidgetDebugName(String str) {
            this.widget.setDebugName(str);
        }

        public void reset() {
            ConstraintWidget constraintWidget = this.widget;
            if (constraintWidget != null) {
                constraintWidget.reset();
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.widthSet = true;
            this.heightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.isVirtualGroup = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = Integer.MIN_VALUE;
            this.resolveGoneRightMargin = Integer.MIN_VALUE;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.guidelineUseRtl = layoutParams.guidelineUseRtl;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.baselineToTop = layoutParams.baselineToTop;
            this.baselineToBottom = layoutParams.baselineToBottom;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.goneBaselineMargin = layoutParams.goneBaselineMargin;
            this.baselineMargin = layoutParams.baselineMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.dimensionRatioValue = layoutParams.dimensionRatioValue;
            this.dimensionRatioSide = layoutParams.dimensionRatioSide;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.horizontalDimensionFixed = layoutParams.horizontalDimensionFixed;
            this.verticalDimensionFixed = layoutParams.verticalDimensionFixed;
            this.needsBaseline = layoutParams.needsBaseline;
            this.isGuideline = layoutParams.isGuideline;
            this.resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
            this.resolvedLeftToRight = layoutParams.resolvedLeftToRight;
            this.resolvedRightToLeft = layoutParams.resolvedRightToLeft;
            this.resolvedRightToRight = layoutParams.resolvedRightToRight;
            this.resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
            this.resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
            this.constraintTag = layoutParams.constraintTag;
            this.wrapBehaviorInParent = layoutParams.wrapBehaviorInParent;
            this.widget = layoutParams.widget;
            this.widthSet = layoutParams.widthSet;
            this.heightSet = layoutParams.heightSet;
        }

        static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int GUIDELINE_USE_RTL = 67;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF = 53;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF = 52;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT = 65;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TAG = 51;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH = 64;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BASELINE = 55;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int LAYOUT_MARGIN_BASELINE = 54;
            public static final int LAYOUT_WRAP_BEHAVIOR_IN_PARENT = 66;
            public static final int UNUSED = 0;
            public static final SparseIntArray map;

            private Table() {
            }

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                map = sparseIntArray;
                sparseIntArray.append(98, 64);
                sparseIntArray.append(75, 65);
                sparseIntArray.append(84, 8);
                sparseIntArray.append(85, 9);
                sparseIntArray.append(87, 10);
                sparseIntArray.append(88, 11);
                sparseIntArray.append(94, 12);
                sparseIntArray.append(93, 13);
                sparseIntArray.append(65, 14);
                sparseIntArray.append(64, 15);
                sparseIntArray.append(60, 16);
                sparseIntArray.append(62, 52);
                sparseIntArray.append(61, 53);
                sparseIntArray.append(66, 2);
                sparseIntArray.append(68, 3);
                sparseIntArray.append(67, 4);
                sparseIntArray.append(103, 49);
                sparseIntArray.append(104, 50);
                sparseIntArray.append(72, 5);
                sparseIntArray.append(73, 6);
                sparseIntArray.append(74, 7);
                sparseIntArray.append(55, 67);
                sparseIntArray.append(0, 1);
                sparseIntArray.append(89, 17);
                sparseIntArray.append(90, 18);
                sparseIntArray.append(71, 19);
                sparseIntArray.append(70, 20);
                sparseIntArray.append(108, 21);
                sparseIntArray.append(111, 22);
                sparseIntArray.append(109, 23);
                sparseIntArray.append(106, 24);
                sparseIntArray.append(110, 25);
                sparseIntArray.append(107, 26);
                sparseIntArray.append(105, 55);
                sparseIntArray.append(112, 54);
                sparseIntArray.append(80, 29);
                sparseIntArray.append(95, 30);
                sparseIntArray.append(69, 44);
                sparseIntArray.append(82, 45);
                sparseIntArray.append(97, 46);
                sparseIntArray.append(81, 47);
                sparseIntArray.append(96, 48);
                sparseIntArray.append(58, 27);
                sparseIntArray.append(57, 28);
                sparseIntArray.append(99, 31);
                sparseIntArray.append(76, 32);
                sparseIntArray.append(101, 33);
                sparseIntArray.append(100, 34);
                sparseIntArray.append(102, 35);
                sparseIntArray.append(78, 36);
                sparseIntArray.append(77, 37);
                sparseIntArray.append(79, 38);
                sparseIntArray.append(83, 39);
                sparseIntArray.append(92, 40);
                sparseIntArray.append(86, 41);
                sparseIntArray.append(63, 42);
                sparseIntArray.append(59, 43);
                sparseIntArray.append(91, 51);
                sparseIntArray.append(114, 66);
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.widthSet = true;
            this.heightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.isVirtualGroup = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = Integer.MIN_VALUE;
            this.resolveGoneRightMargin = Integer.MIN_VALUE;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.visibility, R.attr.layout_width, R.attr.layout_height, R.attr.layout_margin, R.attr.layout_marginLeft, R.attr.layout_marginTop, R.attr.layout_marginRight, R.attr.layout_marginBottom, R.attr.maxWidth, R.attr.maxHeight, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingEnd, R.attr.layout_marginStart, R.attr.layout_marginEnd, R.attr.elevation, R.attr.layout_marginHorizontal, R.attr.layout_marginVertical, com.huawei.health.R.attr._2131099768_res_0x7f060078, com.huawei.health.R.attr._2131099769_res_0x7f060079, com.huawei.health.R.attr._2131099770_res_0x7f06007a, com.huawei.health.R.attr._2131099856_res_0x7f0600d0, com.huawei.health.R.attr._2131099892_res_0x7f0600f4, com.huawei.health.R.attr._2131099893_res_0x7f0600f5, com.huawei.health.R.attr._2131099894_res_0x7f0600f6, com.huawei.health.R.attr._2131099895_res_0x7f0600f7, com.huawei.health.R.attr._2131099896_res_0x7f0600f8, com.huawei.health.R.attr._2131099929_res_0x7f060119, com.huawei.health.R.attr._2131099932_res_0x7f06011c, com.huawei.health.R.attr._2131099933_res_0x7f06011d, com.huawei.health.R.attr._2131100092_res_0x7f0601bc, com.huawei.health.R.attr._2131100093_res_0x7f0601bd, com.huawei.health.R.attr._2131100094_res_0x7f0601be, com.huawei.health.R.attr._2131100095_res_0x7f0601bf, com.huawei.health.R.attr._2131100096_res_0x7f0601c0, com.huawei.health.R.attr._2131100097_res_0x7f0601c1, com.huawei.health.R.attr._2131100098_res_0x7f0601c2, com.huawei.health.R.attr._2131100099_res_0x7f0601c3, com.huawei.health.R.attr._2131100100_res_0x7f0601c4, com.huawei.health.R.attr._2131100101_res_0x7f0601c5, com.huawei.health.R.attr._2131100102_res_0x7f0601c6, com.huawei.health.R.attr._2131100103_res_0x7f0601c7, com.huawei.health.R.attr._2131100104_res_0x7f0601c8, com.huawei.health.R.attr._2131100106_res_0x7f0601ca, com.huawei.health.R.attr._2131100107_res_0x7f0601cb, com.huawei.health.R.attr._2131100108_res_0x7f0601cc, com.huawei.health.R.attr._2131100109_res_0x7f0601cd, com.huawei.health.R.attr._2131100110_res_0x7f0601ce, com.huawei.health.R.attr._2131100129_res_0x7f0601e1, com.huawei.health.R.attr._2131100705_res_0x7f060421, com.huawei.health.R.attr._2131100715_res_0x7f06042b, com.huawei.health.R.attr._2131100716_res_0x7f06042c, com.huawei.health.R.attr._2131100717_res_0x7f06042d, com.huawei.health.R.attr._2131100718_res_0x7f06042e, com.huawei.health.R.attr._2131100719_res_0x7f06042f, com.huawei.health.R.attr._2131100720_res_0x7f060430, com.huawei.health.R.attr._2131100721_res_0x7f060431, com.huawei.health.R.attr._2131100722_res_0x7f060432, com.huawei.health.R.attr._2131100723_res_0x7f060433, com.huawei.health.R.attr._2131100724_res_0x7f060434, com.huawei.health.R.attr._2131100725_res_0x7f060435, com.huawei.health.R.attr._2131100726_res_0x7f060436, com.huawei.health.R.attr._2131100727_res_0x7f060437, com.huawei.health.R.attr._2131100728_res_0x7f060438, com.huawei.health.R.attr._2131100729_res_0x7f060439, com.huawei.health.R.attr._2131100730_res_0x7f06043a, com.huawei.health.R.attr._2131100731_res_0x7f06043b, com.huawei.health.R.attr._2131100732_res_0x7f06043c, com.huawei.health.R.attr._2131100733_res_0x7f06043d, com.huawei.health.R.attr._2131100734_res_0x7f06043e, com.huawei.health.R.attr._2131100735_res_0x7f06043f, com.huawei.health.R.attr._2131100736_res_0x7f060440, com.huawei.health.R.attr._2131100737_res_0x7f060441, com.huawei.health.R.attr._2131100738_res_0x7f060442, com.huawei.health.R.attr._2131100739_res_0x7f060443, com.huawei.health.R.attr._2131100740_res_0x7f060444, com.huawei.health.R.attr._2131100741_res_0x7f060445, com.huawei.health.R.attr._2131100742_res_0x7f060446, com.huawei.health.R.attr._2131100743_res_0x7f060447, com.huawei.health.R.attr._2131100744_res_0x7f060448, com.huawei.health.R.attr._2131100745_res_0x7f060449, com.huawei.health.R.attr._2131100746_res_0x7f06044a, com.huawei.health.R.attr._2131100747_res_0x7f06044b, com.huawei.health.R.attr._2131100748_res_0x7f06044c, com.huawei.health.R.attr._2131100749_res_0x7f06044d, com.huawei.health.R.attr._2131100750_res_0x7f06044e, com.huawei.health.R.attr._2131100751_res_0x7f06044f, com.huawei.health.R.attr._2131100752_res_0x7f060450, com.huawei.health.R.attr._2131100753_res_0x7f060451, com.huawei.health.R.attr._2131100754_res_0x7f060452, com.huawei.health.R.attr._2131100755_res_0x7f060453, com.huawei.health.R.attr._2131100756_res_0x7f060454, com.huawei.health.R.attr._2131100757_res_0x7f060455, com.huawei.health.R.attr._2131100758_res_0x7f060456, com.huawei.health.R.attr._2131100759_res_0x7f060457, com.huawei.health.R.attr._2131100760_res_0x7f060458, com.huawei.health.R.attr._2131100762_res_0x7f06045a, com.huawei.health.R.attr._2131100763_res_0x7f06045b, com.huawei.health.R.attr._2131100764_res_0x7f06045c, com.huawei.health.R.attr._2131100765_res_0x7f06045d, com.huawei.health.R.attr._2131100766_res_0x7f06045e, com.huawei.health.R.attr._2131100767_res_0x7f06045f, com.huawei.health.R.attr._2131100768_res_0x7f060460, com.huawei.health.R.attr._2131100769_res_0x7f060461, com.huawei.health.R.attr._2131100770_res_0x7f060462, com.huawei.health.R.attr._2131100773_res_0x7f060465, com.huawei.health.R.attr._2131100774_res_0x7f060466, com.huawei.health.R.attr._2131100777_res_0x7f060469});
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                int i2 = Table.map.get(index);
                switch (i2) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        this.circleConstraint = resourceId;
                        if (resourceId == -1) {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        float f = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        this.circleAngle = f;
                        if (f < 0.0f) {
                            this.circleAngle = (360.0f - f) % 360.0f;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        int resourceId2 = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        this.leftToLeft = resourceId2;
                        if (resourceId2 == -1) {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        int resourceId3 = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        this.leftToRight = resourceId3;
                        if (resourceId3 == -1) {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        int resourceId4 = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        this.rightToLeft = resourceId4;
                        if (resourceId4 == -1) {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        int resourceId5 = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        this.rightToRight = resourceId5;
                        if (resourceId5 == -1) {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        int resourceId6 = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        this.topToTop = resourceId6;
                        if (resourceId6 == -1) {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        int resourceId7 = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        this.topToBottom = resourceId7;
                        if (resourceId7 == -1) {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        int resourceId8 = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        this.bottomToTop = resourceId8;
                        if (resourceId8 == -1) {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        int resourceId9 = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        this.bottomToBottom = resourceId9;
                        if (resourceId9 == -1) {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        int resourceId10 = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        this.baselineToBaseline = resourceId10;
                        if (resourceId10 == -1) {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        int resourceId11 = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        this.startToEnd = resourceId11;
                        if (resourceId11 == -1) {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        int resourceId12 = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        this.startToStart = resourceId12;
                        if (resourceId12 == -1) {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 19:
                        int resourceId13 = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        this.endToStart = resourceId13;
                        if (resourceId13 == -1) {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        int resourceId14 = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        this.endToEnd = resourceId14;
                        if (resourceId14 == -1) {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        int i3 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultWidth = i3;
                        if (i3 == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 32:
                        int i4 = obtainStyledAttributes.getInt(index, 0);
                        this.matchConstraintDefaultHeight = i4;
                        if (i4 == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) == -2) {
                                this.matchConstraintMinWidth = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) == -2) {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        this.matchConstraintDefaultWidth = 2;
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) == -2) {
                                this.matchConstraintMinHeight = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) == -2) {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        this.matchConstraintDefaultHeight = 2;
                        break;
                    default:
                        switch (i2) {
                            case 44:
                                ConstraintSet.parseDimensionRatioString(this, obtainStyledAttributes.getString(index));
                                break;
                            case 45:
                                this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                                break;
                            case 46:
                                this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                                break;
                            case 47:
                                this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                                break;
                            case 50:
                                this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                                break;
                            case 51:
                                this.constraintTag = obtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = obtainStyledAttributes.getResourceId(index, this.baselineToTop);
                                this.baselineToTop = resourceId15;
                                if (resourceId15 == -1) {
                                    this.baselineToTop = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                int resourceId16 = obtainStyledAttributes.getResourceId(index, this.baselineToBottom);
                                this.baselineToBottom = resourceId16;
                                if (resourceId16 == -1) {
                                    this.baselineToBottom = obtainStyledAttributes.getInt(index, -1);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                break;
                            case 55:
                                this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                break;
                            default:
                                switch (i2) {
                                    case 64:
                                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 0);
                                        this.widthSet = true;
                                        break;
                                    case 65:
                                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 1);
                                        this.heightSet = true;
                                        break;
                                    case 66:
                                        this.wrapBehaviorInParent = obtainStyledAttributes.getInt(index, this.wrapBehaviorInParent);
                                        break;
                                    case 67:
                                        this.guidelineUseRtl = obtainStyledAttributes.getBoolean(index, this.guidelineUseRtl);
                                        break;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                if (this.matchConstraintDefaultWidth == 0) {
                    this.matchConstraintDefaultWidth = 1;
                }
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                if (this.matchConstraintDefaultHeight == 0) {
                    this.matchConstraintDefaultHeight = 1;
                }
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent == -1.0f && this.guideBegin == -1 && this.guideEnd == -1) {
                return;
            }
            this.isGuideline = true;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (!(this.widget instanceof androidx.constraintlayout.core.widgets.Guideline)) {
                this.widget = new androidx.constraintlayout.core.widgets.Guideline();
            }
            ((androidx.constraintlayout.core.widgets.Guideline) this.widget).setOrientation(this.orientation);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.widthSet = true;
            this.heightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.isVirtualGroup = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = Integer.MIN_VALUE;
            this.resolveGoneRightMargin = Integer.MIN_VALUE;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.widthSet = true;
            this.heightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.isVirtualGroup = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = Integer.MIN_VALUE;
            this.resolveGoneRightMargin = Integer.MIN_VALUE;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0050  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0081  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void resolveLayoutDirection(int r11) {
            /*
                Method dump skipped, instructions count: 258
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public String getConstraintTag() {
            return this.constraintTag;
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        markHierarchyDirty();
        super.requestLayout();
    }

    @Override // android.view.View
    public void forceLayout() {
        markHierarchyDirty();
        super.forceLayout();
    }

    private void markHierarchyDirty() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.mLayoutWidget.stringId == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.mLayoutWidget.stringId = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.mLayoutWidget.stringId = "parent";
            }
        }
        if (this.mLayoutWidget.getDebugName() == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
            constraintWidgetContainer.setDebugName(constraintWidgetContainer.stringId);
            Log.v(TAG, " setDebugName " + this.mLayoutWidget.getDebugName());
        }
        Iterator<ConstraintWidget> it = this.mLayoutWidget.getChildren().iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            View view = (View) next.getCompanionWidget();
            if (view != null) {
                if (next.stringId == null && (id = view.getId()) != -1) {
                    next.stringId = getContext().getResources().getResourceEntryName(id);
                }
                if (next.getDebugName() == null) {
                    next.setDebugName(next.stringId);
                    Log.v(TAG, " setDebugName " + next.getDebugName());
                }
            }
        }
        this.mLayoutWidget.getSceneString(sb);
        return sb.toString();
    }
}
