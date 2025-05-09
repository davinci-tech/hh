package com.huawei.health.marketing.views.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eie;
import defpackage.eiv;
import defpackage.ekx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrq;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SquareLandscapeSlideHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2916a;
    private ResourceBriefInfo b;
    private ImageView c;
    private int d;
    private Context e;
    private RelativeLayout f;
    private HealthTextView g;
    private ImageView h;
    private HealthTextView i;
    private ImageView j;
    private HealthTextView m;
    private RelativeLayout n;

    public SquareLandscapeSlideHolder(Context context, View view, ResourceBriefInfo resourceBriefInfo, int i) {
        super(view);
        this.e = context;
        this.d = i;
        this.b = resourceBriefInfo;
        this.n = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_root_layout);
        this.f = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_image_layout);
        this.j = (ImageView) view.findViewById(R.id.item_square_landscape_icon);
        this.c = (ImageView) view.findViewById(R.id.item_square_landscape_audition_icon);
        this.i = (HealthTextView) view.findViewById(R.id.item_square_landscape_hot_amount);
        this.h = (ImageView) view.findViewById(R.id.item_square_landscape_slide_image);
        this.m = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_title);
        this.g = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_description);
        this.f2916a = (HealthTextView) view.findViewById(R.id.item_square_landscape_corner);
        eiv.a(this.i, false, false);
        eiv.a(this.m, false, true);
        eiv.a(this.g, true, false);
    }

    public void d(List<SingleGridContent> list, int i) {
        SingleGridContent singleGridContent = koq.d(list, i) ? list.get(i) : null;
        if (singleGridContent == null) {
            LogUtil.h("SingleHotSeriesRecommendHolder", "setSquareLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        int e = eie.e(BaseApplication.getContext(), 21, list.size());
        ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
        layoutParams.width = e;
        layoutParams.height = e;
        this.h.setLayoutParams(layoutParams);
        nrf.cIS_(this.h, singleGridContent.getPicture(), 0, 1, 0);
        eiv.d(this.m, singleGridContent.getTheme(), singleGridContent.isThemeVisibility());
        eiv.a(this.g, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility());
        eiv.a(this.g, true, false);
        int heatCount = singleGridContent.getHeatCount();
        if (heatCount > 0) {
            this.j.setVisibility(0);
            this.i.setVisibility(0);
            this.i.setText(nrq.c(heatCount));
        } else {
            this.j.setVisibility(8);
            this.i.setVisibility(8);
        }
        if (singleGridContent.isAuditionVisibility()) {
            b(singleGridContent, i);
        } else {
            this.c.setVisibility(8);
        }
        ViewGroup.LayoutParams layoutParams2 = this.f.getLayoutParams();
        layoutParams2.width = e;
        layoutParams2.height = e;
        this.f.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = this.n.getLayoutParams();
        layoutParams3.width = e;
        layoutParams3.height = -2;
        this.n.setLayoutParams(layoutParams3);
        eiv.d(this.f2916a, (CornerTemplate) singleGridContent, true);
    }

    private void b(SingleGridContent singleGridContent, int i) {
        this.c.setVisibility(0);
        this.j.setVisibility(8);
        HashMap hashMap = new HashMap(16);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, String.valueOf(this.d));
        hashMap.put("resourceId", this.b.getResourceId());
        hashMap.put("resourceName", this.b.getResourceName());
        hashMap.put("pullOrder", String.valueOf(i + 1));
        ekx.aqu_(this.itemView, this.c, singleGridContent, hashMap);
    }
}
