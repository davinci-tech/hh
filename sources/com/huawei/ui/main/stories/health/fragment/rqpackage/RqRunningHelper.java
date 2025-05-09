package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.ui.main.R$string;
import defpackage.qjb;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RqRunningHelper {

    /* renamed from: a, reason: collision with root package name */
    private Context f10188a;
    private boolean b;
    private int c;
    private boolean d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String[] k;
    private String m;
    private String n;
    private int o = 1;

    public RqRunningHelper(Context context) {
        this.f10188a = context;
        d();
        this.c = 15;
        if (this.b) {
            this.e = 30;
            return;
        }
        this.e = 65;
        if (!LanguageUtil.bc(this.f10188a) || LanguageUtil.bp(this.f10188a) || LanguageUtil.y(this.f10188a)) {
            return;
        }
        this.c = 65;
        this.e = 15;
    }

    private void d() {
        this.g = this.f10188a.getString(R$string.IDS_data_running_index_level_d);
        this.h = this.f10188a.getString(R$string.IDS_data_running_index_level_c);
        this.f = this.f10188a.getString(R$string.IDS_data_running_index_level_b);
        this.i = this.f10188a.getString(R$string.IDS_data_running_index_level_a);
        this.j = this.f10188a.getString(R$string.IDS_data_running_index_level_s);
        this.m = this.f10188a.getString(R$string.IDS_data_running_index_level_ss);
        String string = this.f10188a.getString(R$string.IDS_data_running_index_level_sss);
        this.n = string;
        this.k = new String[]{this.g, this.h, this.f, this.i, this.j, this.m, string};
        this.b = LanguageUtil.h(BaseApplication.e());
    }

    private SpannableStringBuilder dEu_() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (LanguageUtil.bp(this.f10188a) || LanguageUtil.y(this.f10188a) || LanguageUtil.bn(this.f10188a)) {
            spannableStringBuilder.append((CharSequence) this.k[0]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[1]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[2]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[3]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[4]);
            if (this.d) {
                spannableStringBuilder.append((CharSequence) System.lineSeparator());
            }
            spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[5]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[6]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D);
        } else {
            spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[0]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[1]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[2]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[3]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[4]);
            if (this.d) {
                spannableStringBuilder.append((CharSequence) System.lineSeparator());
            }
            spannableStringBuilder.append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[5]).append((CharSequence) FitRunPlayAudio.PLAY_TYPE_D).append((CharSequence) this.k[6]);
        }
        return spannableStringBuilder;
    }

    public SpannableStringBuilder dEv_(boolean z) {
        this.d = z;
        ArrayList arrayList = new ArrayList();
        Drawable drawable = this.f10188a.getDrawable(R.drawable._2131431325_res_0x7f0b0f9d);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        arrayList.add(drawable);
        Drawable drawable2 = this.f10188a.getDrawable(R.drawable._2131431324_res_0x7f0b0f9c);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        arrayList.add(drawable2);
        Drawable drawable3 = this.f10188a.getDrawable(R.drawable._2131431323_res_0x7f0b0f9b);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        arrayList.add(drawable3);
        Drawable drawable4 = this.f10188a.getDrawable(R.drawable._2131431322_res_0x7f0b0f9a);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        arrayList.add(drawable4);
        Drawable drawable5 = this.f10188a.getDrawable(R.drawable._2131431326_res_0x7f0b0f9e);
        drawable5.setBounds(0, 0, drawable5.getMinimumWidth(), drawable5.getMinimumHeight());
        arrayList.add(drawable5);
        Drawable drawable6 = this.f10188a.getDrawable(R.drawable._2131431327_res_0x7f0b0f9f);
        drawable6.setBounds(0, 0, drawable6.getMinimumWidth(), drawable6.getMinimumHeight());
        arrayList.add(drawable6);
        Drawable drawable7 = this.f10188a.getDrawable(R.drawable._2131431328_res_0x7f0b0fa0);
        drawable7.setBounds(0, 0, drawable7.getMinimumWidth(), drawable7.getMinimumHeight());
        arrayList.add(drawable7);
        ArrayList arrayList2 = new ArrayList(arrayList);
        if (LanguageUtil.bp(this.f10188a) || LanguageUtil.y(this.f10188a) || LanguageUtil.bn(this.f10188a)) {
            this.o = 33;
            this.k = new String[]{this.n, this.m, this.j, this.i, this.f, this.h, this.g};
            arrayList2.clear();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList2.add((Drawable) arrayList.get(size));
            }
        }
        SpannableStringBuilder dEu_ = dEu_();
        if (LanguageUtil.bp(this.f10188a) || LanguageUtil.y(this.f10188a) || LanguageUtil.bn(this.f10188a)) {
            dEt_(arrayList2, dEu_);
        } else {
            dEs_(arrayList2, dEu_);
        }
        return dEu_;
    }

    private void dEs_(List<Drawable> list, SpannableStringBuilder spannableStringBuilder) {
        if (LanguageUtil.bc(this.f10188a)) {
            spannableStringBuilder.setSpan(new qjb(list.get(0), this.e, 0), 0, 1, this.o);
        } else {
            spannableStringBuilder.setSpan(new qjb(list.get(0), 0, this.c), 0, 1, this.o);
        }
        int length = this.k[0].length();
        int i = length + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(1), this.e, this.c), i, length + 2, this.o);
        int length2 = i + this.k[1].length();
        int i2 = length2 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(2), this.e, this.c), i2, length2 + 2, this.o);
        int length3 = i2 + this.k[2].length();
        int i3 = length3 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(3), this.e, this.c), i3, length3 + 2, this.o);
        int length4 = i3 + this.k[3].length();
        int i4 = length4 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(4), this.e, this.c), i4, length4 + 2, this.o);
        int length5 = i4 + this.k[4].length() + (this.d ? 2 : 1);
        spannableStringBuilder.setSpan(new qjb(list.get(5), this.e, this.c), length5, length5 + 1, this.o);
        int length6 = length5 + this.k[5].length();
        spannableStringBuilder.setSpan(new qjb(list.get(6), this.e, this.c), length6 + 1, length6 + 2, this.o);
    }

    private void dEt_(List<Drawable> list, SpannableStringBuilder spannableStringBuilder) {
        int length = this.k[0].length();
        spannableStringBuilder.setSpan(new qjb(list.get(0), this.e, this.c), length, length + 1, this.o);
        int length2 = length + this.k[1].length();
        int i = length2 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(1), this.e, this.c), i, length2 + 2, this.o);
        int length3 = i + this.k[2].length();
        int i2 = length3 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(2), this.e, this.c), i2, length3 + 2, this.o);
        int length4 = i2 + this.k[3].length();
        int i3 = length4 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(3), this.e, this.c), i3, length4 + 2, this.o);
        int length5 = i3 + this.k[4].length();
        int i4 = length5 + 1;
        spannableStringBuilder.setSpan(new qjb(list.get(4), this.e, this.c), i4, length5 + 2, this.o);
        int length6 = i4 + this.k[5].length() + (this.d ? 2 : 1);
        spannableStringBuilder.setSpan(new qjb(list.get(5), this.e, this.c), length6, length6 + 1, this.o);
        int length7 = length6 + this.k[6].length();
        int i5 = length7 + 1;
        if (LanguageUtil.bn(this.f10188a)) {
            Drawable drawable = list.get(6);
            int i6 = this.e;
            spannableStringBuilder.setSpan(new qjb(drawable, i6, i6), i5, length7 + 2, this.o);
        } else {
            Drawable drawable2 = list.get(6);
            int i7 = this.c;
            spannableStringBuilder.setSpan(new qjb(drawable2, i7, i7), i5, length7 + 2, this.o);
        }
    }
}
