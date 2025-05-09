package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class mjh extends HealthPagerAdapter {
    private Map<String, Bitmap> b;
    private List<MedalInfoDesc> c;
    private Map<String, Integer> d;
    private final Context e = BaseApplication.getContext();

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public mjh(List<MedalInfoDesc> list) {
        HashMap hashMap = new HashMap(16);
        mlb.c(hashMap);
        HashMap hashMap2 = new HashMap(16);
        mlb.e(list, hashMap2);
        this.d = hashMap;
        this.b = hashMap2;
        this.c = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<MedalInfoDesc> list = this.c;
        if (list == null) {
            return 0;
        }
        return Math.max(list.size(), 0);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        String string;
        View inflate = View.inflate(this.e, R.layout.item_medal_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.item_medal_text);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.item_medal_image);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.medalViewLayout);
        if (koq.b(this.c, i) || this.b == null || this.d == null) {
            LogUtil.h("MedalViewPageAdapter", "instantiateItem mMedalInfoDescs is null");
            viewGroup.addView(inflate);
            return inflate;
        }
        mjt.ciR_(this.c.get(i), linearLayout, imageView);
        if (LanguageUtil.b(this.e)) {
            string = this.e.getResources().getString(R.string._2130840762_res_0x7f020cba, this.c.get(i).acquireText());
        } else {
            string = this.e.getResources().getString(R.string._2130841011_res_0x7f020db3, this.c.get(i).acquireText());
        }
        healthTextView.setText(string);
        viewGroup.addView(inflate);
        return inflate;
    }
}
