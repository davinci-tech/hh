package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class eap extends HealthPagerAdapter {
    private Context d;
    private ArrayList<View> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public eap(ArrayList<View> arrayList, Context context) {
        new ArrayList(10);
        this.e = arrayList;
        this.d = context;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.e)) {
            return 0;
        }
        return this.e.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (koq.d(this.e, i)) {
            viewGroup.removeView(this.e.get(i));
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!koq.d(this.e, i)) {
            return null;
        }
        View view = this.e.get(i);
        if (view == null) {
            return view;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        viewGroup.addView(view);
        return view;
    }
}
