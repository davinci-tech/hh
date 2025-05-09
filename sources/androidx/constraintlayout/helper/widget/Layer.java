package androidx.constraintlayout.helper.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes8.dex */
public class Layer extends ConstraintHelper {
    private static final String TAG = "Layer";
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;
    protected float mComputedCenterX;
    protected float mComputedCenterY;
    protected float mComputedMaxX;
    protected float mComputedMaxY;
    protected float mComputedMinX;
    protected float mComputedMinY;
    ConstraintLayout mContainer;
    private float mGroupRotateAngle;
    boolean mNeedBounds;
    private float mRotationCenterX;
    private float mRotationCenterY;
    private float mScaleX;
    private float mScaleY;
    private float mShiftX;
    private float mShiftY;
    View[] mViews;

    public Layer(Context context) {
        super(context);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mUseViewMeasure = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.visibility, R.attr.layout_width, R.attr.layout_height, R.attr.layout_margin, R.attr.layout_marginLeft, R.attr.layout_marginTop, R.attr.layout_marginRight, R.attr.layout_marginBottom, R.attr.maxWidth, R.attr.maxHeight, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingEnd, R.attr.layout_marginStart, R.attr.layout_marginEnd, R.attr.elevation, R.attr.layout_marginHorizontal, R.attr.layout_marginVertical, com.huawei.health.R.attr._2131099768_res_0x7f060078, com.huawei.health.R.attr._2131099769_res_0x7f060079, com.huawei.health.R.attr._2131099770_res_0x7f06007a, com.huawei.health.R.attr._2131099856_res_0x7f0600d0, com.huawei.health.R.attr._2131099892_res_0x7f0600f4, com.huawei.health.R.attr._2131099893_res_0x7f0600f5, com.huawei.health.R.attr._2131099894_res_0x7f0600f6, com.huawei.health.R.attr._2131099895_res_0x7f0600f7, com.huawei.health.R.attr._2131099896_res_0x7f0600f8, com.huawei.health.R.attr._2131099929_res_0x7f060119, com.huawei.health.R.attr._2131099932_res_0x7f06011c, com.huawei.health.R.attr._2131099933_res_0x7f06011d, com.huawei.health.R.attr._2131100092_res_0x7f0601bc, com.huawei.health.R.attr._2131100093_res_0x7f0601bd, com.huawei.health.R.attr._2131100094_res_0x7f0601be, com.huawei.health.R.attr._2131100095_res_0x7f0601bf, com.huawei.health.R.attr._2131100096_res_0x7f0601c0, com.huawei.health.R.attr._2131100097_res_0x7f0601c1, com.huawei.health.R.attr._2131100098_res_0x7f0601c2, com.huawei.health.R.attr._2131100099_res_0x7f0601c3, com.huawei.health.R.attr._2131100100_res_0x7f0601c4, com.huawei.health.R.attr._2131100101_res_0x7f0601c5, com.huawei.health.R.attr._2131100102_res_0x7f0601c6, com.huawei.health.R.attr._2131100103_res_0x7f0601c7, com.huawei.health.R.attr._2131100104_res_0x7f0601c8, com.huawei.health.R.attr._2131100106_res_0x7f0601ca, com.huawei.health.R.attr._2131100107_res_0x7f0601cb, com.huawei.health.R.attr._2131100108_res_0x7f0601cc, com.huawei.health.R.attr._2131100109_res_0x7f0601cd, com.huawei.health.R.attr._2131100110_res_0x7f0601ce, com.huawei.health.R.attr._2131100129_res_0x7f0601e1, com.huawei.health.R.attr._2131100705_res_0x7f060421, com.huawei.health.R.attr._2131100715_res_0x7f06042b, com.huawei.health.R.attr._2131100716_res_0x7f06042c, com.huawei.health.R.attr._2131100717_res_0x7f06042d, com.huawei.health.R.attr._2131100718_res_0x7f06042e, com.huawei.health.R.attr._2131100719_res_0x7f06042f, com.huawei.health.R.attr._2131100720_res_0x7f060430, com.huawei.health.R.attr._2131100721_res_0x7f060431, com.huawei.health.R.attr._2131100722_res_0x7f060432, com.huawei.health.R.attr._2131100723_res_0x7f060433, com.huawei.health.R.attr._2131100724_res_0x7f060434, com.huawei.health.R.attr._2131100725_res_0x7f060435, com.huawei.health.R.attr._2131100726_res_0x7f060436, com.huawei.health.R.attr._2131100727_res_0x7f060437, com.huawei.health.R.attr._2131100728_res_0x7f060438, com.huawei.health.R.attr._2131100729_res_0x7f060439, com.huawei.health.R.attr._2131100730_res_0x7f06043a, com.huawei.health.R.attr._2131100731_res_0x7f06043b, com.huawei.health.R.attr._2131100732_res_0x7f06043c, com.huawei.health.R.attr._2131100733_res_0x7f06043d, com.huawei.health.R.attr._2131100734_res_0x7f06043e, com.huawei.health.R.attr._2131100735_res_0x7f06043f, com.huawei.health.R.attr._2131100736_res_0x7f060440, com.huawei.health.R.attr._2131100737_res_0x7f060441, com.huawei.health.R.attr._2131100738_res_0x7f060442, com.huawei.health.R.attr._2131100739_res_0x7f060443, com.huawei.health.R.attr._2131100740_res_0x7f060444, com.huawei.health.R.attr._2131100741_res_0x7f060445, com.huawei.health.R.attr._2131100742_res_0x7f060446, com.huawei.health.R.attr._2131100743_res_0x7f060447, com.huawei.health.R.attr._2131100744_res_0x7f060448, com.huawei.health.R.attr._2131100745_res_0x7f060449, com.huawei.health.R.attr._2131100746_res_0x7f06044a, com.huawei.health.R.attr._2131100747_res_0x7f06044b, com.huawei.health.R.attr._2131100748_res_0x7f06044c, com.huawei.health.R.attr._2131100749_res_0x7f06044d, com.huawei.health.R.attr._2131100750_res_0x7f06044e, com.huawei.health.R.attr._2131100751_res_0x7f06044f, com.huawei.health.R.attr._2131100752_res_0x7f060450, com.huawei.health.R.attr._2131100753_res_0x7f060451, com.huawei.health.R.attr._2131100754_res_0x7f060452, com.huawei.health.R.attr._2131100755_res_0x7f060453, com.huawei.health.R.attr._2131100756_res_0x7f060454, com.huawei.health.R.attr._2131100757_res_0x7f060455, com.huawei.health.R.attr._2131100758_res_0x7f060456, com.huawei.health.R.attr._2131100759_res_0x7f060457, com.huawei.health.R.attr._2131100760_res_0x7f060458, com.huawei.health.R.attr._2131100762_res_0x7f06045a, com.huawei.health.R.attr._2131100763_res_0x7f06045b, com.huawei.health.R.attr._2131100764_res_0x7f06045c, com.huawei.health.R.attr._2131100765_res_0x7f06045d, com.huawei.health.R.attr._2131100766_res_0x7f06045e, com.huawei.health.R.attr._2131100767_res_0x7f06045f, com.huawei.health.R.attr._2131100768_res_0x7f060460, com.huawei.health.R.attr._2131100769_res_0x7f060461, com.huawei.health.R.attr._2131100770_res_0x7f060462, com.huawei.health.R.attr._2131100773_res_0x7f060465, com.huawei.health.R.attr._2131100774_res_0x7f060466, com.huawei.health.R.attr._2131100777_res_0x7f060469});
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 6) {
                    this.mApplyVisibilityOnAttach = true;
                } else if (index == 22) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout) getParent();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.mCount; i++) {
                View viewById = this.mContainer.getViewById(this.mIds[i]);
                if (viewById != null) {
                    if (this.mApplyVisibilityOnAttach) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.mApplyElevationOnAttach && elevation > 0.0f) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout) {
        this.mContainer = constraintLayout;
        float rotation = getRotation();
        if (rotation == 0.0f) {
            if (Float.isNaN(this.mGroupRotateAngle)) {
                return;
            }
            this.mGroupRotateAngle = rotation;
            return;
        }
        this.mGroupRotateAngle = rotation;
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.mGroupRotateAngle = f;
        transform();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.mScaleX = f;
        transform();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.mScaleY = f;
        transform();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.mRotationCenterX = f;
        transform();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.mRotationCenterY = f;
        transform();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.mShiftX = f;
        transform();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.mShiftY = f;
        transform();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        applyLayoutFeatures();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        reCacheViews();
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).getConstraintWidget();
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        calcCenters();
        layout(((int) this.mComputedMinX) - getPaddingLeft(), ((int) this.mComputedMinY) - getPaddingTop(), ((int) this.mComputedMaxX) + getPaddingRight(), ((int) this.mComputedMaxY) + getPaddingBottom());
        transform();
    }

    private void reCacheViews() {
        if (this.mContainer == null || this.mCount == 0) {
            return;
        }
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for (int i = 0; i < this.mCount; i++) {
            this.mViews[i] = this.mContainer.getViewById(this.mIds[i]);
        }
    }

    protected void calcCenters() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mNeedBounds || Float.isNaN(this.mComputedCenterX) || Float.isNaN(this.mComputedCenterY)) {
            if (Float.isNaN(this.mRotationCenterX) || Float.isNaN(this.mRotationCenterY)) {
                View[] views = getViews(this.mContainer);
                int left = views[0].getLeft();
                int top = views[0].getTop();
                int right = views[0].getRight();
                int bottom = views[0].getBottom();
                for (int i = 0; i < this.mCount; i++) {
                    View view = views[i];
                    left = Math.min(left, view.getLeft());
                    top = Math.min(top, view.getTop());
                    right = Math.max(right, view.getRight());
                    bottom = Math.max(bottom, view.getBottom());
                }
                this.mComputedMaxX = right;
                this.mComputedMaxY = bottom;
                this.mComputedMinX = left;
                this.mComputedMinY = top;
                if (Float.isNaN(this.mRotationCenterX)) {
                    this.mComputedCenterX = (left + right) / 2;
                } else {
                    this.mComputedCenterX = this.mRotationCenterX;
                }
                if (Float.isNaN(this.mRotationCenterY)) {
                    this.mComputedCenterY = (top + bottom) / 2;
                    return;
                } else {
                    this.mComputedCenterY = this.mRotationCenterY;
                    return;
                }
            }
            this.mComputedCenterY = this.mRotationCenterY;
            this.mComputedCenterX = this.mRotationCenterX;
        }
    }

    private void transform() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mViews == null) {
            reCacheViews();
        }
        calcCenters();
        double radians = Float.isNaN(this.mGroupRotateAngle) ? 0.0d : Math.toRadians(this.mGroupRotateAngle);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f = this.mScaleX;
        float f2 = this.mScaleY;
        float f3 = -f2;
        for (int i = 0; i < this.mCount; i++) {
            View view = this.mViews[i];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f4 = left - this.mComputedCenterX;
            float f5 = top - this.mComputedCenterY;
            float f6 = this.mShiftX;
            float f7 = this.mShiftY;
            view.setTranslationX(((((f * cos) * f4) + ((f3 * sin) * f5)) - f4) + f6);
            view.setTranslationY((((f4 * (f * sin)) + ((f2 * cos) * f5)) - f5) + f7);
            view.setScaleY(this.mScaleY);
            view.setScaleX(this.mScaleX);
            if (!Float.isNaN(this.mGroupRotateAngle)) {
                view.setRotation(this.mGroupRotateAngle);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout) {
        applyLayoutFeatures(constraintLayout);
    }
}
