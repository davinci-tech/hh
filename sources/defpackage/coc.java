package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class coc implements HealthViewPagerHolder<cnq> {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f809a;
    private LinearLayout b;
    private HealthTextView c;
    private ImageView d;

    @Override // com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolder
    public View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_hagrid_usage_guide_item_view, (ViewGroup) null);
        this.b = (LinearLayout) inflate.findViewById(R.id.hagrid_usage_guide_image_container);
        this.d = (ImageView) inflate.findViewById(R.id.hagrid_usage_guide_image);
        this.f809a = (HealthTextView) inflate.findViewById(R.id.hagrid_usage_guide_title_tv);
        this.c = (HealthTextView) inflate.findViewById(R.id.hagrid_usage_guide_content_tv);
        return inflate;
    }

    @Override // com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBind(Context context, int i, cnq cnqVar) {
        if (cnqVar == null) {
            LogUtil.h("HagridUsageGuideViewHolder", "onBind model is null");
            return;
        }
        LinearLayout linearLayout = this.b;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 2);
        }
        Object a2 = cnqVar.a();
        if (a2 instanceof Integer) {
            this.d.setImageResource(((Integer) a2).intValue());
        }
        if (a2 instanceof String) {
            this.d.setImageBitmap(dcx.TK_((String) a2));
        }
        this.f809a.setText(cnqVar.d());
        this.c.setText(cnqVar.e());
    }
}
