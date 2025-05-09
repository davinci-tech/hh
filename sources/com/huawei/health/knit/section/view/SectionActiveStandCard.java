package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.nru;
import defpackage.nrz;
import defpackage.nsf;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionActiveStandCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2667a;
    private ConstraintLayout b;
    private final Context c;
    private HealthImageView d;
    private HealthButton e;
    private View f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthProgressBar i;
    private HealthDivider j;

    public SectionActiveStandCard(Context context) {
        super(context);
        this.c = BaseApplication.e();
    }

    public SectionActiveStandCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = BaseApplication.e();
    }

    public SectionActiveStandCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = BaseApplication.e();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_stand_card, (ViewGroup) this, false);
        this.f = inflate;
        this.b = (ConstraintLayout) inflate.findViewById(R.id.section_active_stand_card_layout);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.f.findViewById(R.id.section_active_stand_card_target_sub_header);
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.c, R.color._2131296971_res_0x7f0902cb));
        LinearLayout parentLayout = healthSubHeader.getParentLayout();
        if (parentLayout != null) {
            parentLayout.setPadding(0, parentLayout.getPaddingTop(), 0, parentLayout.getPaddingBottom());
        }
        this.f2667a = (HealthTextView) this.f.findViewById(R.id.section_active_stand_card_target_edit);
        HealthImageView healthImageView = (HealthImageView) this.f.findViewById(R.id.section_active_stand_card_target_edit_icon);
        this.d = healthImageView;
        healthImageView.setImageDrawable(nrz.cKl_(this.c, R.drawable._2131429937_res_0x7f0b0a31));
        this.g = (HealthTextView) this.f.findViewById(R.id.section_active_stand_card_target_progress);
        this.i = (HealthProgressBar) this.f.findViewById(R.id.section_active_stand_card_target_progress_bar);
        this.j = (HealthDivider) this.f.findViewById(R.id.section_active_stand_card_progress_bar_divider);
        this.h = (HealthTextView) this.f.findViewById(R.id.section_active_stand_card_target_tip);
        this.e = (HealthButton) this.f.findViewById(R.id.section_active_stand_card_target_tip_button);
        View view = this.f;
        ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this));
        return this.f;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() <= 0) {
            LogUtil.h("SCUI_SectionActiveStandCard", "bindParamsToView map ", hashMap);
            return;
        }
        this.g.setText(nru.e(hashMap, "STAND_PROGRESS_TEXT", ""));
        this.i.setProgress(nru.d((Map) hashMap, "STAND_PROGRESS", 0));
        if (nru.d((Map) hashMap, "STAND_TODAY_DATA", true)) {
            Object d = nru.d(hashMap, "RIGHT_ICON_CLICK_EVENT", (Object) null);
            if (d instanceof View.OnClickListener) {
                View.OnClickListener onClickListener = (View.OnClickListener) d;
                this.f2667a.setVisibility(0);
                this.f2667a.setOnClickListener(onClickListener);
                this.d.setVisibility(0);
                this.d.setOnClickListener(onClickListener);
            }
            this.j.setVisibility(0);
            this.h.setVisibility(0);
            this.h.setText(nru.b(hashMap, "STAND_TIP", ""));
            Object d2 = nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null);
            if (d2 instanceof View.OnClickListener) {
                this.e.setVisibility(0);
                this.e.setOnClickListener((View.OnClickListener) d2);
                this.e.setText(nru.e(hashMap, "STAND_CONTENT", ""));
            }
            setLayoutParamsBottom(nsf.b(R.dimen._2131363063_res_0x7f0a04f7));
            return;
        }
        this.f2667a.setVisibility(8);
        this.d.setVisibility(8);
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.e.setVisibility(8);
        setLayoutParamsBottom(0);
    }

    private void setLayoutParamsBottom(int i) {
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(0, 0, 0, i);
            this.b.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SCUI_SectionActiveStandCard";
    }
}
