package defpackage;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes6.dex */
public class plb extends ppt {
    private Context b;

    public plb(Context context, List<Integer> list, List<String> list2, List<String> list3) {
        super(context, list, list2, list3);
        this.b = context;
    }

    public plb(Context context, List<Integer> list, List<String> list2, List<String> list3, List<String> list4) {
        super(context, list, list2, list3, list4);
        this.b = context;
    }

    @Override // defpackage.ppt
    protected void drT_(View view, int i, HealthTextView healthTextView, ImageView imageView) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.float_content);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int i2 = this.b.getResources().getDisplayMetrics().widthPixels;
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(((Integer) safeRegionWidth.first).intValue() + dimensionPixelSize, 0, ((Integer) safeRegionWidth.second).intValue() + dimensionPixelSize2, 0);
            linearLayout.setLayoutParams(layoutParams);
        }
        drW_(safeRegionWidth, dimensionPixelSize, dimensionPixelSize2, i2, imageView);
    }
}
