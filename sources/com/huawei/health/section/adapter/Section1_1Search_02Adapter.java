package com.huawei.health.section.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eet;
import defpackage.eiv;
import defpackage.fbq;
import defpackage.nrf;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes3.dex */
public class Section1_1Search_02Adapter extends RecyclerView.Adapter<e> {

    /* renamed from: a, reason: collision with root package name */
    private static final int f2968a = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
    private Context b;
    private fbq d;
    private int e = -1;

    public Section1_1Search_02Adapter(Context context, fbq fbqVar) {
        this.b = context;
        this.d = fbqVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: avP_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(this.b).inflate(R.layout.section1_1search_02_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        eet.ahd_(this.b, eVar.c, i, getItemCount());
        c(eVar, i);
    }

    private void c(e eVar, int i) {
        String string;
        Resources resources = BaseApplication.getContext().getResources();
        if (eVar.d != null) {
            eVar.d.setText(String.valueOf(i + 1));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(resources.getDimension(R.dimen._2131362362_res_0x7f0a023a));
            if (i == 0) {
                gradientDrawable.setColor(ContextCompat.getColor(this.b, R.color._2131299043_res_0x7f090ae3));
            } else if (i == 1) {
                gradientDrawable.setColor(ContextCompat.getColor(this.b, R.color._2131299044_res_0x7f090ae4));
            } else if (i == 2) {
                gradientDrawable.setColor(ContextCompat.getColor(this.b, R.color._2131299045_res_0x7f090ae5));
            } else {
                gradientDrawable.setColor(ContextCompat.getColor(this.b, R.color._2131299042_res_0x7f090ae2));
            }
            eVar.d.setBackground(gradientDrawable);
            eVar.d.setTextColor(ContextCompat.getColor(this.b, i <= 2 ? R.color._2131299047_res_0x7f090ae7 : R.color._2131299046_res_0x7f090ae6));
        }
        List<SingleGridContent> d = this.d.d();
        if (eet.b(d, i)) {
            SingleGridContent singleGridContent = d.get(i);
            d(singleGridContent, eVar);
            eiv.d(eVar.i, singleGridContent.getTheme(), singleGridContent.isThemeVisibility());
            eiv.d(eVar.f, singleGridContent);
            eiv.a(eVar.h, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility());
            if (eVar.j != null && !TextUtils.isEmpty(singleGridContent.getItemCategory())) {
                String itemCategory = singleGridContent.getItemCategory();
                itemCategory.hashCode();
                if (itemCategory.equals("BreathExercise")) {
                    string = resources.getString(R$string.IDS_settings_one_level_menu_settings_item_text_id13);
                } else if (itemCategory.equals("SleepAudio")) {
                    string = resources.getString(R$string.IDS_hwh_motiontrack_music);
                } else {
                    string = eiv.d(singleGridContent.getItemCategory());
                }
                avO_(string, eVar, resources, singleGridContent);
            }
            eet.ahe_(eVar.b, this.d.e(), i);
        }
    }

    private int d(String str) {
        if (SingleDailyMomentContent.COURSE_TYPE.equals(str)) {
            return ContextCompat.getColor(this.b, R.color._2131299040_res_0x7f090ae0);
        }
        return ContextCompat.getColor(this.b, R.color._2131299041_res_0x7f090ae1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.e == -1) {
            this.e = eet.d(this.d.d());
        }
        return this.e;
    }

    private void d(SingleGridContent singleGridContent, e eVar) {
        if (eVar.f2969a != null) {
            String picture = singleGridContent.getPicture();
            if (!TextUtils.isEmpty(picture)) {
                nrf.c(eVar.f2969a, picture, f2968a, 0, R.drawable._2131431393_res_0x7f0b0fe1);
            } else {
                nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, eVar.f2969a, f2968a);
            }
        }
    }

    private void avO_(String str, e eVar, Resources resources, SingleGridContent singleGridContent) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        nsy.cMA_(eVar.j, 0);
        eVar.j.setText(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(resources.getDimension(R.dimen._2131363063_res_0x7f0a04f7));
        int d = d(singleGridContent.getItemCategory());
        gradientDrawable.setStroke((int) resources.getDimension(R.dimen._2131362945_res_0x7f0a0481), d);
        eVar.j.setTextColor(d);
        eVar.j.setBackground(gradientDrawable);
    }

    class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthImageView f2969a;
        private RelativeLayout b;
        private LinearLayout c;
        private HealthTextView d;
        private HealthTextView f;
        private HealthTextView h;
        private HealthTextView i;
        private HealthTextView j;

        e(View view) {
            super(view);
            this.b = (RelativeLayout) view.findViewById(R.id.recycle_item);
            this.d = (HealthTextView) view.findViewById(R.id.item_index_text);
            this.f2969a = (HealthImageView) view.findViewById(R.id.item_picture);
            this.i = (HealthTextView) view.findViewById(R.id.item_title);
            this.f = (HealthTextView) view.findViewById(R.id.item_special_tag);
            this.h = (HealthTextView) view.findViewById(R.id.item_subtitle);
            this.j = (HealthTextView) view.findViewById(R.id.item_type);
            this.c = (LinearLayout) view.findViewById(R.id.item_divider);
        }
    }
}
