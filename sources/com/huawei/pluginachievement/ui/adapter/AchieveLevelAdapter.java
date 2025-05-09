package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.jed;
import defpackage.mki;
import defpackage.mku;
import defpackage.mkw;
import defpackage.mlc;
import defpackage.nrf;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f8422a;
    private HashMap<Integer, Integer> b;
    private HashMap<Integer, Integer> d;
    private Context e;
    private HashMap<Integer, Integer> g;
    private ArrayList<mki> h;
    private mku i;
    private int c = 0;
    private Pair<Integer, Integer> j = BaseActivity.getSafeRegionWidth();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public AchieveLevelAdapter(Context context, ArrayList<mki> arrayList, HashMap<Integer, Integer> hashMap, HashMap<Integer, Integer> hashMap2, HashMap<Integer, Integer> hashMap3) {
        this.e = context;
        this.h = arrayList;
        this.g = hashMap;
        this.b = hashMap2;
        this.d = hashMap3;
        this.f8422a = LayoutInflater.from(context);
    }

    public void b(ArrayList<mki> arrayList, int i) {
        this.h = arrayList;
        this.c = i - 1;
        notifyDataSetChanged();
    }

    public void c(mku mkuVar) {
        this.i = mkuVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new e(this.f8422a.inflate(R.layout.achieve_level_layout_header, viewGroup, false));
        }
        if (i == 1) {
            return new d(this.f8422a.inflate(R.layout.achieve_level_listview_item, viewGroup, false));
        }
        LogUtil.h("AchieveLevelAdapter", "viewType unknow:", Integer.valueOf(i));
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i < 0 || i > this.h.size()) {
            return;
        }
        if (viewHolder instanceof e) {
            e eVar = (e) viewHolder;
            eVar.itemView.setBackgroundColor(mlc.j(this.c + 1));
            e(eVar, this.i);
        } else {
            if (viewHolder instanceof d) {
                d dVar = (d) viewHolder;
                dVar.itemView.setBackgroundColor(ContextCompat.getColor(this.e, R.color._2131296657_res_0x7f090191));
                dVar.f8423a.setPadding(((Integer) this.j.first).intValue(), dVar.f8423a.getPaddingTop(), ((Integer) this.j.second).intValue(), dVar.f8423a.getPaddingBottom());
                d(dVar, i);
                return;
            }
            LogUtil.h("AchieveLevelAdapter", "onBindViewHolder error type position = ", Integer.valueOf(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.h.size() + 1;
    }

    private void a(e eVar, mku mkuVar) {
        int a2 = mkuVar.a();
        eVar.e.setVisibility(0);
        eVar.b.setMax(Math.abs(mkuVar.e()));
        eVar.b.setProgress(Math.abs(mkuVar.c()));
        String string = this.e.getResources().getString(R.string._2130840814_res_0x7f020cee, Integer.valueOf(a2 + 1));
        int abs = Math.abs(Math.abs(mkuVar.e()) - Math.abs(mkuVar.c()));
        String e2 = UnitUtil.e(abs, 1, 0);
        String quantityString = this.e.getResources().getQuantityString(R.plurals._2130903176_res_0x7f030088, abs, e2, string);
        SpannableString spannableString = new SpannableString(quantityString);
        int indexOf = quantityString.indexOf(e2);
        int i = indexOf != -1 ? indexOf : 0;
        int length = e2.length();
        int length2 = quantityString.length() - string.length();
        int i2 = length + i;
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), i, i2, 34);
        spannableString.setSpan(new StyleSpan(3), i, i2, 34);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), length2, spannableString.length(), 17);
        spannableString.setSpan(new StyleSpan(3), length2, spannableString.length(), 17);
        eVar.e.setText(spannableString);
    }

    private void a(e eVar) {
        eVar.e.setVisibility(0);
        eVar.b.setMax(100);
        eVar.b.setProgress(100);
        String string = this.e.getResources().getString(R.string._2130840695_res_0x7f020c77);
        String string2 = this.e.getResources().getString(this.d.get(20).intValue());
        String format = String.format(Locale.ROOT, string, string2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(string2);
        int i = indexOf != -1 ? indexOf : 0;
        int length = string2.length() + i;
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), i, length, 34);
        spannableString.setSpan(new StyleSpan(3), i, length, 34);
        eVar.e.setText(spannableString);
    }

    private void e(e eVar, mku mkuVar) {
        if (mkuVar == null || eVar.c == null) {
            return;
        }
        int a2 = mkuVar.a();
        eVar.d.setImageResource(this.g.get(Integer.valueOf(a2)).intValue());
        String string = this.e.getResources().getString(R.string._2130840814_res_0x7f020cee, Integer.valueOf(a2));
        eVar.g.setText(string + " ");
        if (a2 + 1 <= 20) {
            a(eVar, mkuVar);
        } else {
            a(eVar);
        }
        String string2 = this.e.getResources().getString(R.string._2130840774_res_0x7f020cc6);
        double b = mkuVar.b();
        eVar.f8424a.setText(String.format(Locale.ROOT, string2, jed.b(b, 2, b != 0.0d ? mkw.b(b) : 0)));
    }

    private void d(d dVar, int i) {
        int i2 = i - 1;
        mki mkiVar = this.h.get(i2);
        if (mkiVar != null) {
            int e2 = mkiVar.e();
            if (i2 <= this.c) {
                dVar.e.setImageResource(this.g.get(Integer.valueOf(e2)).intValue());
            } else {
                dVar.e.setImageResource(this.b.get(Integer.valueOf(e2)).intValue());
                dVar.e.setImageDrawable(nrf.cJH_(dVar.e.getDrawable(), this.e.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
            }
            String string = this.e.getResources().getString(R.string._2130840814_res_0x7f020cee, Integer.valueOf(e2));
            dVar.c.setText(string + " ");
            dVar.b.setText(mlc.ckm_(this.e, mkiVar.b()));
            String string2 = this.e.getResources().getString(R.string._2130840774_res_0x7f020cc6);
            double a2 = mkiVar.a();
            dVar.d.setText(String.format(Locale.ROOT, string2, jed.b(a2, 2, a2 != 0.0d ? mkw.b(a2) : 0)));
        }
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f8424a;
        private HealthProgressBar b;
        private LinearLayout c;
        private ImageView d;
        private HealthTextView e;
        private HealthTextView g;

        e(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.achieve_level_image);
            this.g = (HealthTextView) view.findViewById(R.id.achieve_level_text);
            this.b = (HealthProgressBar) view.findViewById(R.id.achieve_level_progress);
            this.e = (HealthTextView) view.findViewById(R.id.achieve_level_des);
            this.f8424a = (HealthTextView) view.findViewById(R.id.achieve_level_person_count);
            this.c = (LinearLayout) view.findViewById(R.id.achieve_level_header);
        }
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private View f8423a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;
        private ImageView e;

        d(View view) {
            super(view);
            this.f8423a = view.findViewById(R.id.level_listview_content_layout);
            this.e = (ImageView) view.findViewById(R.id.achieve_level_listview_iv);
            this.c = (HealthTextView) view.findViewById(R.id.achieve_level_listview_tv);
            this.b = (HealthTextView) view.findViewById(R.id.achieve_level_listview_content_tv);
            this.d = (HealthTextView) view.findViewById(R.id.achieve_level_listview_person_count);
        }
    }
}
