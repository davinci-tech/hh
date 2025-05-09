package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.ui.main.R$string;
import defpackage.qjb;

/* loaded from: classes6.dex */
public class Vo2maxLevelHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f10190a;
    private String b;
    private String c;
    private String d;
    private Context e;
    private String f;
    private boolean h;
    private String i;
    private String j;

    public Vo2maxLevelHelper(Context context) {
        this.e = context;
        b();
    }

    private void b() {
        this.j = this.e.getString(R$string.IDS_device_hagrid_body_element_content_low);
        this.f = this.e.getString(R$string.IDS_hwh_health_vo2max_level_poor);
        this.i = this.e.getString(R$string.IDS_hwh_health_vo2max_level_fair);
        this.d = this.e.getString(R$string.IDS_hwh_health_vo2max_level_average);
        this.c = this.e.getString(R$string.IDS_hwh_health_vo2max_level_good);
        this.b = this.e.getString(R$string.IDS_hwh_health_vo2max_level_verygood);
        this.f10190a = this.e.getString(R$string.IDS_hwh_health_vo2max_level_excellent);
    }

    private SpannableStringBuilder dEy_() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.j).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.f).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.i).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.d);
        if (this.h) {
            spannableStringBuilder.append((CharSequence) System.lineSeparator());
        }
        spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.c).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.b).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.f10190a);
        return spannableStringBuilder;
    }

    public SpannableStringBuilder dEz_(boolean z) {
        this.h = z;
        Drawable drawable = this.e.getDrawable(R.drawable._2131431328_res_0x7f0b0fa0);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        Drawable drawable2 = this.e.getDrawable(R.drawable._2131431327_res_0x7f0b0f9f);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        Drawable drawable3 = this.e.getDrawable(R.drawable._2131431326_res_0x7f0b0f9e);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        Drawable drawable4 = this.e.getDrawable(R.drawable._2131431322_res_0x7f0b0f9a);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        Drawable drawable5 = this.e.getDrawable(R.drawable._2131431323_res_0x7f0b0f9b);
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
        Drawable drawable6 = this.e.getDrawable(R.drawable._2131431324_res_0x7f0b0f9c);
        drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
        Drawable drawable7 = this.e.getDrawable(R.drawable._2131431325_res_0x7f0b0f9d);
        drawable7.setBounds(0, 0, drawable7.getMinimumWidth(), drawable7.getMinimumHeight());
        SpannableStringBuilder dEy_ = dEy_();
        dEy_.setSpan(new qjb(drawable, 0, 15), 0, 1, 1);
        int length = this.j.length();
        int i = length + 1;
        dEy_.setSpan(new qjb(drawable2, 30, 15), i, length + 2, 1);
        int length2 = i + this.f.length();
        int i2 = length2 + 1;
        dEy_.setSpan(new qjb(drawable3, 30, 15), i2, length2 + 2, 1);
        int length3 = i2 + this.i.length();
        int i3 = length3 + 1;
        dEy_.setSpan(new qjb(drawable4, 30, 15), i3, length3 + 2, 1);
        int length4 = i3 + this.d.length() + (this.h ? 2 : 1);
        dEy_.setSpan(new qjb(drawable5, 30, 15), length4, length4 + 1, 1);
        int length5 = length4 + this.c.length();
        int i4 = length5 + 1;
        dEy_.setSpan(new qjb(drawable6, 30, 15), i4, length5 + 2, 1);
        int length6 = i4 + this.b.length();
        dEy_.setSpan(new qjb(drawable7, 30, 15), length6 + 1, length6 + 2, 1);
        return dEy_;
    }
}
