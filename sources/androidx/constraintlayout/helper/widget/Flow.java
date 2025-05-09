package androidx.constraintlayout.helper.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.VirtualLayout;

/* loaded from: classes8.dex */
public class Flow extends VirtualLayout {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    private static final String TAG = "Flow";
    public static final int VERTICAL = 1;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private androidx.constraintlayout.core.widgets.Flow mFlow;

    public Flow(Context context) {
        super(context);
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
        this.mFlow.applyRtl(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onMeasure(int i, int i2) {
        onMeasure(this.mFlow, i, i2);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout virtualLayout, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (virtualLayout != null) {
            virtualLayout.measure(mode, size, mode2, size2);
            setMeasuredDimension(virtualLayout.getMeasuredWidth(), virtualLayout.getMeasuredHeight());
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.loadParameters(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof androidx.constraintlayout.core.widgets.Flow) {
            androidx.constraintlayout.core.widgets.Flow flow = (androidx.constraintlayout.core.widgets.Flow) helperWidget;
            if (layoutParams.orientation != -1) {
                flow.setOrientation(layoutParams.orientation);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mFlow = new androidx.constraintlayout.core.widgets.Flow();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.visibility, R.attr.layout_width, R.attr.layout_height, R.attr.layout_margin, R.attr.layout_marginLeft, R.attr.layout_marginTop, R.attr.layout_marginRight, R.attr.layout_marginBottom, R.attr.maxWidth, R.attr.maxHeight, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingEnd, R.attr.layout_marginStart, R.attr.layout_marginEnd, R.attr.elevation, R.attr.layout_marginHorizontal, R.attr.layout_marginVertical, com.huawei.health.R.attr._2131099768_res_0x7f060078, com.huawei.health.R.attr._2131099769_res_0x7f060079, com.huawei.health.R.attr._2131099770_res_0x7f06007a, com.huawei.health.R.attr._2131099856_res_0x7f0600d0, com.huawei.health.R.attr._2131099892_res_0x7f0600f4, com.huawei.health.R.attr._2131099893_res_0x7f0600f5, com.huawei.health.R.attr._2131099894_res_0x7f0600f6, com.huawei.health.R.attr._2131099895_res_0x7f0600f7, com.huawei.health.R.attr._2131099896_res_0x7f0600f8, com.huawei.health.R.attr._2131099929_res_0x7f060119, com.huawei.health.R.attr._2131099932_res_0x7f06011c, com.huawei.health.R.attr._2131099933_res_0x7f06011d, com.huawei.health.R.attr._2131100092_res_0x7f0601bc, com.huawei.health.R.attr._2131100093_res_0x7f0601bd, com.huawei.health.R.attr._2131100094_res_0x7f0601be, com.huawei.health.R.attr._2131100095_res_0x7f0601bf, com.huawei.health.R.attr._2131100096_res_0x7f0601c0, com.huawei.health.R.attr._2131100097_res_0x7f0601c1, com.huawei.health.R.attr._2131100098_res_0x7f0601c2, com.huawei.health.R.attr._2131100099_res_0x7f0601c3, com.huawei.health.R.attr._2131100100_res_0x7f0601c4, com.huawei.health.R.attr._2131100101_res_0x7f0601c5, com.huawei.health.R.attr._2131100102_res_0x7f0601c6, com.huawei.health.R.attr._2131100103_res_0x7f0601c7, com.huawei.health.R.attr._2131100104_res_0x7f0601c8, com.huawei.health.R.attr._2131100106_res_0x7f0601ca, com.huawei.health.R.attr._2131100107_res_0x7f0601cb, com.huawei.health.R.attr._2131100108_res_0x7f0601cc, com.huawei.health.R.attr._2131100109_res_0x7f0601cd, com.huawei.health.R.attr._2131100110_res_0x7f0601ce, com.huawei.health.R.attr._2131100129_res_0x7f0601e1, com.huawei.health.R.attr._2131100705_res_0x7f060421, com.huawei.health.R.attr._2131100715_res_0x7f06042b, com.huawei.health.R.attr._2131100716_res_0x7f06042c, com.huawei.health.R.attr._2131100717_res_0x7f06042d, com.huawei.health.R.attr._2131100718_res_0x7f06042e, com.huawei.health.R.attr._2131100719_res_0x7f06042f, com.huawei.health.R.attr._2131100720_res_0x7f060430, com.huawei.health.R.attr._2131100721_res_0x7f060431, com.huawei.health.R.attr._2131100722_res_0x7f060432, com.huawei.health.R.attr._2131100723_res_0x7f060433, com.huawei.health.R.attr._2131100724_res_0x7f060434, com.huawei.health.R.attr._2131100725_res_0x7f060435, com.huawei.health.R.attr._2131100726_res_0x7f060436, com.huawei.health.R.attr._2131100727_res_0x7f060437, com.huawei.health.R.attr._2131100728_res_0x7f060438, com.huawei.health.R.attr._2131100729_res_0x7f060439, com.huawei.health.R.attr._2131100730_res_0x7f06043a, com.huawei.health.R.attr._2131100731_res_0x7f06043b, com.huawei.health.R.attr._2131100732_res_0x7f06043c, com.huawei.health.R.attr._2131100733_res_0x7f06043d, com.huawei.health.R.attr._2131100734_res_0x7f06043e, com.huawei.health.R.attr._2131100735_res_0x7f06043f, com.huawei.health.R.attr._2131100736_res_0x7f060440, com.huawei.health.R.attr._2131100737_res_0x7f060441, com.huawei.health.R.attr._2131100738_res_0x7f060442, com.huawei.health.R.attr._2131100739_res_0x7f060443, com.huawei.health.R.attr._2131100740_res_0x7f060444, com.huawei.health.R.attr._2131100741_res_0x7f060445, com.huawei.health.R.attr._2131100742_res_0x7f060446, com.huawei.health.R.attr._2131100743_res_0x7f060447, com.huawei.health.R.attr._2131100744_res_0x7f060448, com.huawei.health.R.attr._2131100745_res_0x7f060449, com.huawei.health.R.attr._2131100746_res_0x7f06044a, com.huawei.health.R.attr._2131100747_res_0x7f06044b, com.huawei.health.R.attr._2131100748_res_0x7f06044c, com.huawei.health.R.attr._2131100749_res_0x7f06044d, com.huawei.health.R.attr._2131100750_res_0x7f06044e, com.huawei.health.R.attr._2131100751_res_0x7f06044f, com.huawei.health.R.attr._2131100752_res_0x7f060450, com.huawei.health.R.attr._2131100753_res_0x7f060451, com.huawei.health.R.attr._2131100754_res_0x7f060452, com.huawei.health.R.attr._2131100755_res_0x7f060453, com.huawei.health.R.attr._2131100756_res_0x7f060454, com.huawei.health.R.attr._2131100757_res_0x7f060455, com.huawei.health.R.attr._2131100758_res_0x7f060456, com.huawei.health.R.attr._2131100759_res_0x7f060457, com.huawei.health.R.attr._2131100760_res_0x7f060458, com.huawei.health.R.attr._2131100762_res_0x7f06045a, com.huawei.health.R.attr._2131100763_res_0x7f06045b, com.huawei.health.R.attr._2131100764_res_0x7f06045c, com.huawei.health.R.attr._2131100765_res_0x7f06045d, com.huawei.health.R.attr._2131100766_res_0x7f06045e, com.huawei.health.R.attr._2131100767_res_0x7f06045f, com.huawei.health.R.attr._2131100768_res_0x7f060460, com.huawei.health.R.attr._2131100769_res_0x7f060461, com.huawei.health.R.attr._2131100770_res_0x7f060462, com.huawei.health.R.attr._2131100773_res_0x7f060465, com.huawei.health.R.attr._2131100774_res_0x7f060466, com.huawei.health.R.attr._2131100777_res_0x7f060469});
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 0) {
                    this.mFlow.setOrientation(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 1) {
                    this.mFlow.setPadding(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 18) {
                    this.mFlow.setPaddingStart(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 19) {
                    this.mFlow.setPaddingEnd(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 2) {
                    this.mFlow.setPaddingLeft(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 3) {
                    this.mFlow.setPaddingTop(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 4) {
                    this.mFlow.setPaddingRight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 5) {
                    this.mFlow.setPaddingBottom(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 54) {
                    this.mFlow.setWrapMode(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 44) {
                    this.mFlow.setHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 53) {
                    this.mFlow.setVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 38) {
                    this.mFlow.setFirstHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 46) {
                    this.mFlow.setLastHorizontalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 40) {
                    this.mFlow.setFirstVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 48) {
                    this.mFlow.setLastVerticalStyle(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 42) {
                    this.mFlow.setHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 37) {
                    this.mFlow.setFirstHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 45) {
                    this.mFlow.setLastHorizontalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 39) {
                    this.mFlow.setFirstVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 47) {
                    this.mFlow.setLastVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 51) {
                    this.mFlow.setVerticalBias(obtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == 41) {
                    this.mFlow.setHorizontalAlign(obtainStyledAttributes.getInt(index, 2));
                } else if (index == 50) {
                    this.mFlow.setVerticalAlign(obtainStyledAttributes.getInt(index, 2));
                } else if (index == 43) {
                    this.mFlow.setHorizontalGap(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 52) {
                    this.mFlow.setVerticalGap(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 49) {
                    this.mFlow.setMaxElementsWrap(obtainStyledAttributes.getInt(index, -1));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mFlow;
        validateParams();
    }

    public void setOrientation(int i) {
        this.mFlow.setOrientation(i);
        requestLayout();
    }

    public void setPadding(int i) {
        this.mFlow.setPadding(i);
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.mFlow.setPaddingLeft(i);
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.mFlow.setPaddingTop(i);
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.mFlow.setPaddingRight(i);
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.mFlow.setPaddingBottom(i);
        requestLayout();
    }

    public void setLastHorizontalStyle(int i) {
        this.mFlow.setLastHorizontalStyle(i);
        requestLayout();
    }

    public void setLastVerticalStyle(int i) {
        this.mFlow.setLastVerticalStyle(i);
        requestLayout();
    }

    public void setLastHorizontalBias(float f) {
        this.mFlow.setLastHorizontalBias(f);
        requestLayout();
    }

    public void setLastVerticalBias(float f) {
        this.mFlow.setLastVerticalBias(f);
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.mFlow.setWrapMode(i);
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.mFlow.setHorizontalStyle(i);
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.mFlow.setVerticalStyle(i);
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.mFlow.setHorizontalBias(f);
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.mFlow.setVerticalBias(f);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFlow.setFirstHorizontalStyle(i);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.mFlow.setFirstVerticalStyle(i);
        requestLayout();
    }

    public void setFirstHorizontalBias(float f) {
        this.mFlow.setFirstHorizontalBias(f);
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.mFlow.setFirstVerticalBias(f);
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.mFlow.setHorizontalAlign(i);
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.mFlow.setVerticalAlign(i);
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.mFlow.setHorizontalGap(i);
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.mFlow.setVerticalGap(i);
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.mFlow.setMaxElementsWrap(i);
        requestLayout();
    }
}
