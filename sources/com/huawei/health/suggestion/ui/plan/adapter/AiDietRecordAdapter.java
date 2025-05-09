package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TextAppearanceSpan;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.AiDietRecordAdapter;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ase;
import defpackage.dpe;
import defpackage.fiu;
import defpackage.fyv;
import defpackage.koq;
import defpackage.nmj;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.quh;
import defpackage.qul;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class AiDietRecordAdapter extends BaseRecyclerAdapter<List<fiu>> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3240a;
    private final boolean b;
    private final Context c;
    private int d;
    private int e;
    private quh g;
    private OnItemClickListener h;
    private List<List<fiu>> j;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    private int b(double d) {
        if (d < 0.0d) {
            return 2;
        }
        return d == 0.0d ? 0 : 1;
    }

    public AiDietRecordAdapter() {
        super(new ArrayList(), R.layout.item_recycler_dietary_records);
        Context e = BaseApplication.e();
        this.c = e;
        this.j = new ArrayList(3);
        this.b = LanguageUtil.bc(e);
    }

    public void a(List<List<fiu>> list, quh quhVar, long j) {
        if (koq.b(list)) {
            LogUtil.c("AiDietRecordAdapter", "setData list ", list);
            return;
        }
        if (list.size() > 3) {
            ArrayList arrayList = new ArrayList(3);
            for (int i = 0; i < 3; i++) {
                arrayList.add(list.get(i));
            }
            this.j = arrayList;
        } else {
            this.j = list;
        }
        this.g = quhVar;
        this.f3240a = fyv.e(quhVar, j);
        this.d = DateFormatUtil.b(j);
        this.e = DateFormatUtil.b(System.currentTimeMillis());
        refreshDataChange(this.j);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.j)) {
            return 0;
        }
        return this.j.size();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, List<fiu> list) {
        String h;
        if (recyclerHolder == null || koq.b(list) || koq.b(this.j, i)) {
            LogUtil.c("AiDietRecordAdapter", "convert holder ", recyclerHolder, " itemData ", list, " position ", Integer.valueOf(i), " mList ", this.j);
            return;
        }
        int d = fyv.d(i);
        float e = e(d);
        boolean z = Float.compare(e, 0.0f) == 1;
        if (d == 10) {
            h = nsf.h(z ? R.string._2130848787_res_0x7f022c13 : R.string._2130848784_res_0x7f022c10);
        } else if (d == 20) {
            h = nsf.h(z ? R.string._2130848788_res_0x7f022c14 : R.string._2130848785_res_0x7f022c11);
        } else if (d != 30) {
            h = "";
        } else {
            h = nsf.h(z ? R.string._2130848789_res_0x7f022c15 : R.string._2130848786_res_0x7f022c12);
        }
        recyclerHolder.b(R.id.title, h);
        c(recyclerHolder, list, e, d);
        recyclerHolder.cwN_(R.id.arrow, this.b ? nrz.cKn_(this.c, R.drawable._2131430240_res_0x7f0b0b60) : ContextCompat.getDrawable(this.c, R.drawable._2131430240_res_0x7f0b0b60));
        recyclerHolder.a(R.id.divider, d == 30 ? 8 : 0);
        setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: ftm
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder2, int i2, Object obj) {
                AiDietRecordAdapter.this.d(recyclerHolder2, i2, obj);
            }
        });
    }

    public /* synthetic */ void d(RecyclerHolder recyclerHolder, int i, Object obj) {
        OnItemClickListener onItemClickListener = this.h;
        if (onItemClickListener == null || this.d > this.e) {
            LogUtil.c("AiDietRecordAdapter", "convert mOnItemClickListener ", onItemClickListener, " mDay ", Integer.valueOf(this.d), " mCurrentDay ", Integer.valueOf(this.e));
        } else {
            onItemClickListener.onItemClick(fyv.d(i));
        }
    }

    private void c(RecyclerHolder recyclerHolder, List<fiu> list, float f, int i) {
        if (ase.e()) {
            e(recyclerHolder, list, f);
        } else {
            e(recyclerHolder, f, i);
        }
    }

    private void e(RecyclerHolder recyclerHolder, float f, int i) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.heat);
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.heatRefer);
        if (healthTextView == null || healthTextView2 == null) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat textView is null");
            return;
        }
        if (dpe.d(dpe.a(dpe.b(this.g), i), this.g.d().c(), i, b(this.g.d().j())).length == 0) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat range is empty");
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
            return;
        }
        healthTextView2.setVisibility(0);
        healthTextView.setVisibility(0);
        double d = f;
        String a2 = nsf.a(R.plurals._2130903380_res_0x7f030154, UnitUtil.e(d, Locale.getDefault()), UnitUtil.e(d, 1, 0));
        SpannableString spannableString = new SpannableString(a2);
        spannableString.setSpan(new TextAppearanceSpan(this.c, R.style.list_text_title_primary), 0, a2.length(), 17);
        spannableString.setSpan(new nmj(nsk.cKO_()), 0, a2.length(), 17);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, a2.length(), 17);
        healthTextView.setText(spannableString);
        healthTextView2.setText(nsf.b(R.string._2130848759_res_0x7f022bf7, UnitUtil.e(r11[0], 1, 0), nsf.a(R.plurals._2130903380_res_0x7f030154, UnitUtil.e(r11[1], Locale.getDefault()), UnitUtil.e(r11[1], 1, 0))));
    }

    private void e(RecyclerHolder recyclerHolder, List<fiu> list, float f) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.heat);
        if (healthTextView == null) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat textView is null");
            return;
        }
        double b = b(list);
        String b2 = nsf.b(R.string._2130845622_res_0x7f021fb6, UnitUtil.e(f, 1, 0), nsf.a(R.plurals._2130903474_res_0x7f0301b2, UnitUtil.e(b), UnitUtil.e(b, 1, 0)));
        if (TextUtils.isEmpty(b2)) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat source ", b2, " kcal ", Float.valueOf(f), " list ", list);
            healthTextView.setVisibility(8);
            return;
        }
        healthTextView.setVisibility(0);
        String[] split = b2.split("/");
        if (koq.b(split, 0)) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat array ", split, " source ", b2, " kcal ", Float.valueOf(f), " list ", list);
            healthTextView.setText(b2);
            return;
        }
        String str = split[0];
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat resultHeat ", str, " array ", split, " source ", b2, " kcal ", Float.valueOf(f), " list ", list);
            healthTextView.setText(b2);
            return;
        }
        int length = str.length();
        if (length >= b2.length()) {
            ReleaseLogUtil.a("R_AiDietRecordAdapter", "setHeat resultLength ", Integer.valueOf(length), " resultHeat ", str, " array ", split, " source ", b2, " kcal ", Float.valueOf(f), " list ", list);
            healthTextView.setText(b2);
            return;
        }
        SpannableString spannableString = new SpannableString(b2);
        spannableString.setSpan(new TextAppearanceSpan(this.c, R.style.list_text_title_primary), 0, length, 17);
        spannableString.setSpan(new nmj(nsk.cKO_()), 0, length, 17);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, length, 17);
        healthTextView.setText(spannableString);
    }

    private float e(int i) {
        quh quhVar = this.g;
        if (quhVar == null) {
            LogUtil.d("AiDietRecordAdapter", "getKcal mRecord is null mealType ", Integer.valueOf(i));
            return 0.0f;
        }
        if (!this.f3240a) {
            LogUtil.c("AiDietRecordAdapter", "getKcal mIsSameDay false");
            return 0.0f;
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            LogUtil.c("AiDietRecordAdapter", "getKcal list ", a2);
            return 0.0f;
        }
        for (qul qulVar : a2) {
            if (qulVar != null && qulVar.h() == i) {
                return qulVar.b();
            }
        }
        return 0.0f;
    }

    private float b(List<fiu> list) {
        float f = 0.0f;
        if (koq.b(list)) {
            LogUtil.c("AiDietRecordAdapter", "getHeat list ", list);
            return 0.0f;
        }
        for (fiu fiuVar : list) {
            if (fiuVar != null) {
                f += fiuVar.g();
            }
        }
        return f;
    }

    public void e(OnItemClickListener onItemClickListener) {
        this.h = onItemClickListener;
    }
}
