package androidx.constraintlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes2.dex */
public abstract class ConstraintHelper extends View {
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds;
    protected HashMap<Integer, String> mMap;
    protected String mReferenceIds;
    protected String mReferenceTags;
    protected boolean mUseViewMeasure;
    private View[] mViews;
    protected Context myContext;

    protected void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout) {
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
    }

    public void updatePostConstraints(ConstraintLayout constraintLayout) {
    }

    public void updatePostLayout(ConstraintLayout constraintLayout) {
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout) {
    }

    public void updatePreDraw(ConstraintLayout constraintLayout) {
    }

    public ConstraintHelper(Context context) {
        super(context);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(null);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(attributeSet);
    }

    public void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.visibility, R.attr.layout_width, R.attr.layout_height, R.attr.layout_margin, R.attr.layout_marginLeft, R.attr.layout_marginTop, R.attr.layout_marginRight, R.attr.layout_marginBottom, R.attr.maxWidth, R.attr.maxHeight, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingEnd, R.attr.layout_marginStart, R.attr.layout_marginEnd, R.attr.elevation, R.attr.layout_marginHorizontal, R.attr.layout_marginVertical, com.huawei.health.R.attr._2131099768_res_0x7f060078, com.huawei.health.R.attr._2131099769_res_0x7f060079, com.huawei.health.R.attr._2131099770_res_0x7f06007a, com.huawei.health.R.attr._2131099856_res_0x7f0600d0, com.huawei.health.R.attr._2131099892_res_0x7f0600f4, com.huawei.health.R.attr._2131099893_res_0x7f0600f5, com.huawei.health.R.attr._2131099894_res_0x7f0600f6, com.huawei.health.R.attr._2131099895_res_0x7f0600f7, com.huawei.health.R.attr._2131099896_res_0x7f0600f8, com.huawei.health.R.attr._2131099929_res_0x7f060119, com.huawei.health.R.attr._2131099932_res_0x7f06011c, com.huawei.health.R.attr._2131099933_res_0x7f06011d, com.huawei.health.R.attr._2131100092_res_0x7f0601bc, com.huawei.health.R.attr._2131100093_res_0x7f0601bd, com.huawei.health.R.attr._2131100094_res_0x7f0601be, com.huawei.health.R.attr._2131100095_res_0x7f0601bf, com.huawei.health.R.attr._2131100096_res_0x7f0601c0, com.huawei.health.R.attr._2131100097_res_0x7f0601c1, com.huawei.health.R.attr._2131100098_res_0x7f0601c2, com.huawei.health.R.attr._2131100099_res_0x7f0601c3, com.huawei.health.R.attr._2131100100_res_0x7f0601c4, com.huawei.health.R.attr._2131100101_res_0x7f0601c5, com.huawei.health.R.attr._2131100102_res_0x7f0601c6, com.huawei.health.R.attr._2131100103_res_0x7f0601c7, com.huawei.health.R.attr._2131100104_res_0x7f0601c8, com.huawei.health.R.attr._2131100106_res_0x7f0601ca, com.huawei.health.R.attr._2131100107_res_0x7f0601cb, com.huawei.health.R.attr._2131100108_res_0x7f0601cc, com.huawei.health.R.attr._2131100109_res_0x7f0601cd, com.huawei.health.R.attr._2131100110_res_0x7f0601ce, com.huawei.health.R.attr._2131100129_res_0x7f0601e1, com.huawei.health.R.attr._2131100705_res_0x7f060421, com.huawei.health.R.attr._2131100715_res_0x7f06042b, com.huawei.health.R.attr._2131100716_res_0x7f06042c, com.huawei.health.R.attr._2131100717_res_0x7f06042d, com.huawei.health.R.attr._2131100718_res_0x7f06042e, com.huawei.health.R.attr._2131100719_res_0x7f06042f, com.huawei.health.R.attr._2131100720_res_0x7f060430, com.huawei.health.R.attr._2131100721_res_0x7f060431, com.huawei.health.R.attr._2131100722_res_0x7f060432, com.huawei.health.R.attr._2131100723_res_0x7f060433, com.huawei.health.R.attr._2131100724_res_0x7f060434, com.huawei.health.R.attr._2131100725_res_0x7f060435, com.huawei.health.R.attr._2131100726_res_0x7f060436, com.huawei.health.R.attr._2131100727_res_0x7f060437, com.huawei.health.R.attr._2131100728_res_0x7f060438, com.huawei.health.R.attr._2131100729_res_0x7f060439, com.huawei.health.R.attr._2131100730_res_0x7f06043a, com.huawei.health.R.attr._2131100731_res_0x7f06043b, com.huawei.health.R.attr._2131100732_res_0x7f06043c, com.huawei.health.R.attr._2131100733_res_0x7f06043d, com.huawei.health.R.attr._2131100734_res_0x7f06043e, com.huawei.health.R.attr._2131100735_res_0x7f06043f, com.huawei.health.R.attr._2131100736_res_0x7f060440, com.huawei.health.R.attr._2131100737_res_0x7f060441, com.huawei.health.R.attr._2131100738_res_0x7f060442, com.huawei.health.R.attr._2131100739_res_0x7f060443, com.huawei.health.R.attr._2131100740_res_0x7f060444, com.huawei.health.R.attr._2131100741_res_0x7f060445, com.huawei.health.R.attr._2131100742_res_0x7f060446, com.huawei.health.R.attr._2131100743_res_0x7f060447, com.huawei.health.R.attr._2131100744_res_0x7f060448, com.huawei.health.R.attr._2131100745_res_0x7f060449, com.huawei.health.R.attr._2131100746_res_0x7f06044a, com.huawei.health.R.attr._2131100747_res_0x7f06044b, com.huawei.health.R.attr._2131100748_res_0x7f06044c, com.huawei.health.R.attr._2131100749_res_0x7f06044d, com.huawei.health.R.attr._2131100750_res_0x7f06044e, com.huawei.health.R.attr._2131100751_res_0x7f06044f, com.huawei.health.R.attr._2131100752_res_0x7f060450, com.huawei.health.R.attr._2131100753_res_0x7f060451, com.huawei.health.R.attr._2131100754_res_0x7f060452, com.huawei.health.R.attr._2131100755_res_0x7f060453, com.huawei.health.R.attr._2131100756_res_0x7f060454, com.huawei.health.R.attr._2131100757_res_0x7f060455, com.huawei.health.R.attr._2131100758_res_0x7f060456, com.huawei.health.R.attr._2131100759_res_0x7f060457, com.huawei.health.R.attr._2131100760_res_0x7f060458, com.huawei.health.R.attr._2131100762_res_0x7f06045a, com.huawei.health.R.attr._2131100763_res_0x7f06045b, com.huawei.health.R.attr._2131100764_res_0x7f06045c, com.huawei.health.R.attr._2131100765_res_0x7f06045d, com.huawei.health.R.attr._2131100766_res_0x7f06045e, com.huawei.health.R.attr._2131100767_res_0x7f06045f, com.huawei.health.R.attr._2131100768_res_0x7f060460, com.huawei.health.R.attr._2131100769_res_0x7f060461, com.huawei.health.R.attr._2131100770_res_0x7f060462, com.huawei.health.R.attr._2131100773_res_0x7f060465, com.huawei.health.R.attr._2131100774_res_0x7f060466, com.huawei.health.R.attr._2131100777_res_0x7f060469});
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 35) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mReferenceIds = string;
                    setIds(string);
                } else if (index == 36) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.mReferenceTags = string2;
                    setReferenceTags(string2);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceIds;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.mReferenceTags;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    public void addView(View view) {
        if (view == this) {
            return;
        }
        if (view.getId() == -1) {
            Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have an id");
        } else {
            if (view.getParent() == null) {
                Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have a parent");
                return;
            }
            this.mReferenceIds = null;
            addRscID(view.getId());
            requestLayout();
        }
    }

    public int removeView(View view) {
        int i;
        int id = view.getId();
        int i2 = -1;
        if (id == -1) {
            return -1;
        }
        this.mReferenceIds = null;
        int i3 = 0;
        while (true) {
            if (i3 >= this.mCount) {
                break;
            }
            if (this.mIds[i3] == id) {
                int i4 = i3;
                while (true) {
                    i = this.mCount - 1;
                    if (i4 >= i) {
                        break;
                    }
                    int[] iArr = this.mIds;
                    int i5 = i4 + 1;
                    iArr[i4] = iArr[i5];
                    i4 = i5;
                }
                this.mIds[i] = 0;
                this.mCount = i;
                i2 = i3;
            } else {
                i3++;
            }
        }
        requestLayout();
        return i2;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void setReferencedIds(int[] iArr) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for (int i : iArr) {
            addRscID(i);
        }
    }

    private void addRscID(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.mCount;
        int[] iArr = this.mIds;
        if (i2 + 1 > iArr.length) {
            this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.mIds;
        int i3 = this.mCount;
        iArr2[i3] = i;
        this.mCount = i3 + 1;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mUseViewMeasure) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void validateParams() {
        if (this.mHelperWidget == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).widget = (ConstraintWidget) this.mHelperWidget;
        }
    }

    private void addID(String str) {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String trim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int findId = findId(trim);
        if (findId != 0) {
            this.mMap.put(Integer.valueOf(findId), trim);
            addRscID(findId);
        } else {
            Log.w("ConstraintHelper", "Could not find id of \"" + trim + "\"");
        }
    }

    private void addTag(String str) {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String trim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.LayoutParams) && trim.equals(((ConstraintLayout.LayoutParams) layoutParams).constraintTag)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    addRscID(childAt.getId());
                }
            }
        }
    }

    private int findId(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int i = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object designInformation = constraintLayout.getDesignInformation(0, str);
            if (designInformation instanceof Integer) {
                i = ((Integer) designInformation).intValue();
            }
        }
        if (i == 0 && constraintLayout != null) {
            i = findId(constraintLayout, str);
        }
        if (i == 0) {
            try {
                i = R$id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return i == 0 ? this.myContext.getResources().getIdentifier(str, "id", this.myContext.getPackageName()) : i;
    }

    private int findId(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String str2;
        if (str == null || constraintLayout == null || (resources = this.myContext.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            if (childAt.getId() != -1) {
                try {
                    str2 = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    str2 = null;
                }
                if (str.equals(str2)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    protected void setIds(String str) {
        this.mReferenceIds = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.mCount = 0;
        while (true) {
            int indexOf = str.indexOf(44, i);
            if (indexOf == -1) {
                addID(str.substring(i));
                return;
            } else {
                addID(str.substring(i, indexOf));
                i = indexOf + 1;
            }
        }
    }

    protected void setReferenceTags(String str) {
        this.mReferenceTags = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.mCount = 0;
        while (true) {
            int indexOf = str.indexOf(44, i);
            if (indexOf == -1) {
                addTag(str.substring(i));
                return;
            } else {
                addTag(str.substring(i, indexOf));
                i = indexOf + 1;
            }
        }
    }

    protected void applyLayoutFeatures(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i = 0; i < this.mCount; i++) {
            View viewById = constraintLayout.getViewById(this.mIds[i]);
            if (viewById != null) {
                viewById.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected void applyLayoutFeatures() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        applyLayoutFeatures((ConstraintLayout) parent);
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) {
        String str;
        int findId;
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        Helper helper = this.mHelperWidget;
        if (helper == null) {
            return;
        }
        helper.removeAllIds();
        for (int i = 0; i < this.mCount; i++) {
            int i2 = this.mIds[i];
            View viewById = constraintLayout.getViewById(i2);
            if (viewById == null && (findId = findId(constraintLayout, (str = this.mMap.get(Integer.valueOf(i2))))) != 0) {
                this.mIds[i] = findId;
                this.mMap.put(Integer.valueOf(findId), str);
                viewById = constraintLayout.getViewById(findId);
            }
            if (viewById != null) {
                this.mHelperWidget.add(constraintLayout.getViewWidget(viewById));
            }
        }
        this.mHelperWidget.updateConstraints(constraintLayout.mLayoutWidget);
    }

    public void updatePreLayout(ConstraintWidgetContainer constraintWidgetContainer, Helper helper, SparseArray<ConstraintWidget> sparseArray) {
        helper.removeAllIds();
        for (int i = 0; i < this.mCount; i++) {
            helper.add(sparseArray.get(this.mIds[i]));
        }
    }

    protected View[] getViews(ConstraintLayout constraintLayout) {
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for (int i = 0; i < this.mCount; i++) {
            this.mViews[i] = constraintLayout.getViewById(this.mIds[i]);
        }
        return this.mViews;
    }

    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        if (constraint.layout.mReferenceIds != null) {
            setReferencedIds(constraint.layout.mReferenceIds);
        } else if (constraint.layout.mReferenceIdString != null) {
            if (constraint.layout.mReferenceIdString.length() > 0) {
                constraint.layout.mReferenceIds = convertReferenceString(this, constraint.layout.mReferenceIdString);
            } else {
                constraint.layout.mReferenceIds = null;
            }
        }
        if (helperWidget != null) {
            helperWidget.removeAllIds();
            if (constraint.layout.mReferenceIds != null) {
                for (int i = 0; i < constraint.layout.mReferenceIds.length; i++) {
                    ConstraintWidget constraintWidget = sparseArray.get(constraint.layout.mReferenceIds[i]);
                    if (constraintWidget != null) {
                        helperWidget.add(constraintWidget);
                    }
                }
            }
        }
    }

    private int[] convertReferenceString(View view, String str) {
        String[] split = str.split(",");
        view.getContext();
        int[] iArr = new int[split.length];
        int i = 0;
        for (String str2 : split) {
            int findId = findId(str2.trim());
            if (findId != 0) {
                iArr[i] = findId;
                i++;
            }
        }
        return i != split.length ? Arrays.copyOf(iArr, i) : iArr;
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.mReferenceIds == null) {
            addRscID(i);
        }
    }

    public boolean containsId(int i) {
        for (int i2 : this.mIds) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public int indexFromId(int i) {
        int i2 = -1;
        for (int i3 : this.mIds) {
            i2++;
            if (i3 == i) {
                return i2;
            }
        }
        return i2;
    }
}
