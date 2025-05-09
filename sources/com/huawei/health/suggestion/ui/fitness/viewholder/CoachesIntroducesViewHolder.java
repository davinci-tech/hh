package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.CoachBar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.nrf;
import health.compact.a.LogAnonymous;

/* loaded from: classes4.dex */
public class CoachesIntroducesViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3200a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthSubHeader d;
    private HealthTextView e;

    public CoachesIntroducesViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_fitness_item_cocaher_introduct, viewGroup, false));
        this.f3200a = (ImageView) this.itemView.findViewById(R.id.item_avatar_iv);
        this.c = (HealthTextView) this.itemView.findViewById(R.id.item_name_tv);
        this.b = (HealthTextView) this.itemView.findViewById(R.id.item_certified_info_tv);
        this.e = (HealthTextView) this.itemView.findViewById(R.id.item_info_tv);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.itemView.findViewById(R.id.item_sub_header);
        this.d = healthSubHeader;
        healthSubHeader.setMoreText("");
        this.d.setMoreLayoutVisibility(4);
    }

    public void d(CoachBar coachBar) {
        if (coachBar == null || TextUtils.isEmpty(coachBar.getName())) {
            this.itemView.setVisibility(8);
            return;
        }
        this.itemView.setVisibility(0);
        this.d.setHeadTitleText(TextUtils.isEmpty(coachBar.getName()) ? "" : coachBar.getName());
        if (!TextUtils.isEmpty(coachBar.getImage())) {
            this.f3200a.setVisibility(0);
            try {
                nrf.cJF_(Uri.parse(coachBar.getImage()), this.f3200a);
            } catch (SecurityException e) {
                LogUtil.h("Suggestion_CoachesIntroducesViewHolder", "url error = ", LogAnonymous.b((Throwable) e));
            }
        } else {
            this.f3200a.setVisibility(8);
        }
        a(this.c, coachBar.getImageName());
        a(this.b, coachBar.getImageDesc());
        a(this.e, coachBar.getDetailInformation());
    }

    private void a(HealthTextView healthTextView, String str) {
        if (TextUtils.isEmpty(str)) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        }
    }
}
