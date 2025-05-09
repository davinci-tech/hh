package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nru;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class HealthNoDataDesSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2630a;
    private HealthTextView b;
    private HealthTextView c;
    private ImageView d;
    private Context e;
    private View f;
    private ImageView g;
    private ImageView h;
    private LinearLayout j;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public HealthNoDataDesSection(Context context) {
        super(context);
    }

    public HealthNoDataDesSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthNoDataDesSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.health_no_data_des_section_layout, (ViewGroup) this, false);
        this.f = inflate;
        this.h = (ImageView) inflate.findViewById(R.id.no_data_page_head_img);
        this.g = (ImageView) this.f.findViewById(R.id.round_corner_img);
        this.d = (ImageView) this.f.findViewById(R.id.has_device_no_data_img);
        this.j = (LinearLayout) this.f.findViewById(R.id.toast_view_container);
        this.b = (HealthTextView) this.f.findViewById(R.id.no_data_title);
        this.c = (HealthTextView) this.f.findViewById(R.id.no_data_desc);
        return this.f;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(final HashMap<String, Object> hashMap) {
        LogUtil.a("HealthNoDataDesSection", "bindParamsToView");
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        boolean d = nru.d((Map) hashMap, "DEVICE_INFO_FLAG", false);
        this.f2630a = d;
        if (!d) {
            this.g.post(new Runnable() { // from class: com.huawei.health.knit.section.view.HealthNoDataDesSection.5
                @Override // java.lang.Runnable
                public void run() {
                    FragmentActivity activity;
                    LogUtil.a("HealthNoDataDesSection", "register scroll listener");
                    if (HealthNoDataDesSection.this.getKnitFragment() == null || (activity = HealthNoDataDesSection.this.getKnitFragment().getActivity()) == null || !(activity instanceof KnitHealthDetailActivity)) {
                        return;
                    }
                    KnitHealthDetailActivity knitHealthDetailActivity = (KnitHealthDetailActivity) activity;
                    LogUtil.a("HealthNoDataDesSection", "mRoundCornerImage height: ", Integer.valueOf(HealthNoDataDesSection.this.g.getHeight()));
                    knitHealthDetailActivity.configScrollViewListener(HealthNoDataDesSection.this.h, HealthNoDataDesSection.this.g.getHeight());
                    if (nru.d((Map) hashMap, "IS_SHOW_TOAST", false)) {
                        knitHealthDetailActivity.configureNoDataToast(HealthNoDataDesSection.this.j);
                    }
                }
            });
        }
        this.c.setText(nru.b(hashMap, "NO_DATA_DESCRIPTION", ""));
        if (this.f2630a) {
            ahM_(nru.b(hashMap, "HAS_DEVICE_NO_DATA_IMAGE", ""), this.d);
            this.h.setVisibility(8);
            this.g.setVisibility(8);
            this.d.setVisibility(0);
            this.b.setVisibility(0);
            FragmentActivity activity = getKnitFragment().getActivity();
            if (activity != null && (activity instanceof KnitHealthDetailActivity)) {
                ((KnitHealthDetailActivity) activity).setTitleStyleNoData();
                return;
            }
            return;
        }
        ahM_(nru.b(hashMap, "TOP_IMAGE", ""), this.h);
        this.h.setVisibility(0);
        this.b.setVisibility(8);
        this.g.setVisibility(0);
        this.d.setVisibility(8);
    }

    private void ahM_(String str, ImageView imageView) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        nrf.cIv_(str, new RequestOptions().skipMemoryCache(true), imageView);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "HealthNoDataDesSection";
    }
}
