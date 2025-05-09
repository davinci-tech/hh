package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.ui.main.R$string;
import defpackage.qjb;

/* loaded from: classes6.dex */
public class StateIndexDayHelper {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10189a;
    private String b;
    private Context c;
    private String d;
    private String e;
    private String g;
    private String i;

    public StateIndexDayHelper(Context context) {
        this.c = context;
        c();
    }

    private void c() {
        this.g = this.c.getString(R$string.IDS_data_index_very_pool);
        this.d = this.c.getString(R$string.IDS_data_index_pool);
        this.e = this.c.getString(R$string.IDS_data_index_normal);
        this.i = this.c.getString(R$string.IDS_data_index_well);
        this.b = this.c.getString(R$string.IDS_data_index_very_well);
    }

    private SpannableStringBuilder dEw_() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.g).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.d).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.e);
        if (this.f10189a) {
            spannableStringBuilder.append((CharSequence) System.lineSeparator());
        }
        spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.i).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.b);
        return spannableStringBuilder;
    }

    public SpannableStringBuilder dEx_(boolean z) {
        this.f10189a = z;
        Drawable drawable = this.c.getDrawable(R.drawable._2131431328_res_0x7f0b0fa0);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        Drawable drawable2 = this.c.getDrawable(R.drawable._2131431327_res_0x7f0b0f9f);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        Drawable drawable3 = this.c.getDrawable(R.drawable._2131431322_res_0x7f0b0f9a);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        Drawable drawable4 = this.c.getDrawable(R.drawable._2131431323_res_0x7f0b0f9b);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        Drawable drawable5 = this.c.getDrawable(R.drawable._2131431325_res_0x7f0b0f9d);
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
        SpannableStringBuilder dEw_ = dEw_();
        dEw_.setSpan(new qjb(drawable, 0, 15), 0, 1, 1);
        int length = this.g.length();
        int i = length + 1;
        dEw_.setSpan(new qjb(drawable2, 70, 15), i, length + 2, 1);
        int length2 = i + this.d.length();
        int i2 = length2 + 1;
        dEw_.setSpan(new qjb(drawable3, 70, 15), i2, length2 + 2, 1);
        int length3 = i2 + this.e.length() + (this.f10189a ? 2 : 1);
        dEw_.setSpan(new qjb(drawable4, 70, 15), length3, length3 + 1, 1);
        int length4 = length3 + this.i.length();
        dEw_.setSpan(new qjb(drawable5, 70, 15), length4 + 1, length4 + 2, 1);
        this.b.length();
        return dEw_;
    }
}
