package defpackage;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes6.dex */
public class oju extends ppt {
    private Context d;

    public oju(Context context, List<Integer> list, List<String> list2, List<String> list3) {
        super(context, list, list2, list3);
        this.d = context;
    }

    @Override // defpackage.ppt
    protected void drT_(View view, int i, HealthTextView healthTextView, ImageView imageView) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.float_content);
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.addRule(10, -1);
            layoutParams.addRule(20, -1);
            layoutParams.topMargin = qrp.a(this.d, 148.0f);
            linearLayout.setLayoutParams(layoutParams);
        }
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams2.width = qrp.a(this.d, 206.0f);
            layoutParams2.height = qrp.a(this.d, 168.0f);
            layoutParams2.gravity = GravityCompat.START;
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
            imageView.setLayoutParams(layoutParams2);
        }
    }
}
