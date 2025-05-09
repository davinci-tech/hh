package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;

/* loaded from: classes8.dex */
public class axv extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private int f277a;
    private final Context b = BaseApplication.getContext();
    private final SparseArray<String> c;
    private int d;
    private final SparseArray<BitmapDrawable> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public axv(SparseArray<BitmapDrawable> sparseArray, SparseArray<String> sparseArray2) {
        this.e = sparseArray;
        this.c = sparseArray2;
        a(false);
    }

    public void a(boolean z) {
        this.f277a = nla.e(z, 3);
        this.d = nsn.j() / 5;
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        SparseArray<BitmapDrawable> sparseArray = this.e;
        if (sparseArray == null) {
            return 0;
        }
        return Math.max(sparseArray.size(), 0);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = View.inflate(this.b, R.layout.item_mask, null);
        if (this.e == null) {
            LogUtil.h("HealthLife_MaskViewPageAdapter", "instantiateItem mDrawableArray is null");
            viewGroup.addView(inflate);
            return inflate;
        }
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.item_mask_card_view);
        if (this.d > 0) {
            ViewGroup.LayoutParams layoutParams = healthCardView.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = this.d;
                healthCardView.setLayoutParams(layoutParams2);
            }
        }
        int i2 = i + 1;
        if (this.c == null) {
            LogUtil.h("HealthLife_MaskViewPageAdapter", "instantiateItem mStringArray is null");
        } else {
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.item_mask_text);
            healthTextView.setText(this.c.get(i2));
            ViewGroup.LayoutParams layoutParams3 = healthTextView.getLayoutParams();
            layoutParams3.width = this.f277a;
            healthTextView.setLayoutParams(layoutParams3);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.item_mask_image);
        BitmapDrawable bitmapDrawable = this.e.get(i2);
        imageView.setImageDrawable(bitmapDrawable);
        if (bitmapDrawable == null) {
            LogUtil.h("HealthLife_MaskViewPageAdapter", "instantiateItem bitmapDrawable is null");
            kT_(imageView, 1946, 1101);
            viewGroup.addView(inflate);
            return inflate;
        }
        Bitmap bitmap = bitmapDrawable.getBitmap();
        if (bitmap == null) {
            LogUtil.h("HealthLife_MaskViewPageAdapter", "instantiateItem bitmap is null");
            kT_(imageView, 1946, 1101);
            viewGroup.addView(inflate);
            return inflate;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        kT_(imageView, width > 0 ? width : 1946, height > 0 ? height : 1101);
        viewGroup.addView(inflate);
        return inflate;
    }

    private void kT_(ImageView imageView, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = (i2 * this.f277a) / i;
        layoutParams.width = this.f277a;
        imageView.setLayoutParams(layoutParams);
    }
}
