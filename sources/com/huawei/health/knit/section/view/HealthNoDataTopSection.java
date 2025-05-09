package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nru;
import defpackage.nsn;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class HealthNoDataTopSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2631a;
    private ImageView b;
    private boolean c;
    private Context d;
    private ImageView e;
    private LinearLayout f;
    private ImageView i;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public HealthNoDataTopSection(Context context) {
        super(context);
    }

    public HealthNoDataTopSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthNoDataTopSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.d = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.health_no_data_top_section_layout, (ViewGroup) this, false);
        this.f2631a = inflate;
        this.i = (ImageView) inflate.findViewById(R.id.no_data_page_head_img);
        this.b = (ImageView) this.f2631a.findViewById(R.id.round_corner_img);
        this.e = (ImageView) this.f2631a.findViewById(R.id.rect_corner_img);
        this.f = (LinearLayout) this.f2631a.findViewById(R.id.toast_view_container);
        return this.f2631a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(final HashMap<String, Object> hashMap) {
        LogUtil.a("HealthNoDataTopSection", "bindParamsToView");
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.huawei.health.knit.section.view.HealthNoDataTopSection.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HealthNoDataTopSection", "register scroll listener");
                if (!(HealthNoDataTopSection.this.getKnitFragment().getActivity() instanceof KnitHealthDetailActivity) || ((KnitHealthDetailActivity) HealthNoDataTopSection.this.getKnitFragment().getActivity()) == null) {
                    return;
                }
                LogUtil.a("HealthNoDataTopSection", "mRoundCornerImage height: ", Integer.valueOf(HealthNoDataTopSection.this.b.getHeight()));
                ((KnitHealthDetailActivity) HealthNoDataTopSection.this.getKnitFragment().getActivity()).configScrollViewListener(HealthNoDataTopSection.this.i, HealthNoDataTopSection.this.b.getHeight());
                if (nru.d((Map) hashMap, "IS_SHOW_TOAST", false)) {
                    ((KnitHealthDetailActivity) HealthNoDataTopSection.this.getKnitFragment().getActivity()).configureNoDataToast(HealthNoDataTopSection.this.f);
                }
            }
        });
        boolean d = nru.d((Map) hashMap, "DEVICE_INFO_FLAG", false);
        this.c = d;
        if (d) {
            ahQ_(nru.d((Map) hashMap, "RECT_CORNER_IMAGE", 0), this.e);
            this.i.setVisibility(8);
            this.b.setVisibility(8);
            this.e.setVisibility(0);
            return;
        }
        a(hashMap);
        ahQ_(nru.d((Map) hashMap, "ROUND_CORNER_IMAGE", 0), this.b);
        this.i.setVisibility(0);
        this.b.setVisibility(0);
        this.e.setVisibility(8);
    }

    private void a(HashMap<String, Object> hashMap) {
        if (!nsn.ag(this.d)) {
            ahQ_(nru.d((Map) hashMap, "TOP_IMAGE", 0), this.i);
            if (this.i.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
                layoutParams.height = (nsn.h(this.d) / 16) * 9;
                this.i.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        ahQ_(nru.d((Map) hashMap, "TOP_IMAGE_TAHITI", 0), this.i);
    }

    private void ahQ_(int i, ImageView imageView) {
        if (i == 0) {
            return;
        }
        imageView.setImageDrawable(this.d.getResources().getDrawable(i));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "HealthNoDataTopSection";
    }
}
