package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mef;
import defpackage.mkb;
import defpackage.mlg;
import defpackage.mlh;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public class BreakInfoRecyclerAdapter extends RecyclerView.Adapter<b> {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<mkb> f8425a;
    private LayoutInflater b;
    private Context d;

    public BreakInfoRecyclerAdapter(Context context, Map<Integer, Pair<Double, Long>> map) {
        this.d = context;
        this.b = LayoutInflater.from(context);
        this.f8425a = a(map);
    }

    private ArrayList<mkb> a(Map<Integer, Pair<Double, Long>> map) {
        if (map == null || map.size() <= 0) {
            LogUtil.h("PLGACHIEVE_BreakInfoRecyclerAdapter", "getListFromMap() mMedalList is empty.");
            return new ArrayList<>();
        }
        ArrayList<mkb> arrayList = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Pair<Double, Long>> entry : map.entrySet()) {
            mkb mkbVar = new mkb();
            Pair<Double, Long> value = entry.getValue();
            double doubleValue = ((Double) value.first).doubleValue();
            long longValue = ((Long) value.second).longValue();
            mkbVar.a(entry.getKey().intValue());
            mkbVar.a(longValue);
            mkbVar.b(doubleValue);
            arrayList.add(mkbVar);
        }
        Collections.sort(arrayList, new Comparator<mkb>() { // from class: com.huawei.pluginachievement.ui.adapter.BreakInfoRecyclerAdapter.2
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(mkb mkbVar2, mkb mkbVar3) {
                return (int) (mkbVar3.c() - mkbVar2.c());
            }
        });
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ciw_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(this.b.inflate(R.layout.achieve_report_single_item_break_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        if (koq.b(this.f8425a, i)) {
            return;
        }
        mkb mkbVar = this.f8425a.get(i);
        int d = mkbVar.d();
        long c = mkbVar.c();
        bVar.c.setText(d(mkbVar.e(), d));
        String lowerCase = mlg.d(d, this.d).toLowerCase(Locale.ENGLISH);
        bVar.f8426a.setText(mlh.e(this.d.getResources().getString(R.string._2130840975_res_0x7f020d8f, mlg.a(c, 2), lowerCase), lowerCase, this.d.getResources().getColor(R.color._2131299236_res_0x7f090ba4), 0, this.d.getResources().getString(R.string._2130851581_res_0x7f0236fd)));
        if (i == this.f8425a.size() - 1) {
            bVar.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131427680_res_0x7f0b0160));
        } else {
            bVar.e.setBackground(this.d.getResources().getDrawable(R.drawable._2131427679_res_0x7f0b015f));
        }
        b(bVar, i);
    }

    private void b(b bVar, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        bVar.c.measure(makeMeasureSpec, makeMeasureSpec2);
        bVar.f8426a.measure(makeMeasureSpec, makeMeasureSpec2);
        int i2 = bVar.f8426a.getMeasuredWidth() > ((int) Math.ceil((double) bVar.f8426a.getPaint().measureText(bVar.f8426a.getText().toString()))) + 20 ? 1 : 2;
        int measuredHeight = bVar.c.getMeasuredHeight();
        int ceil = (int) Math.ceil((bVar.f8426a.getMeasuredHeight() * i2) + bVar.f8426a.getLineSpacingExtra());
        ViewGroup.LayoutParams layoutParams = bVar.b.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = bVar.d.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams;
            int c = nsn.c(this.d, 12.0f);
            RelativeLayout.LayoutParams layoutParams4 = layoutParams2 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams2 : null;
            if (i == 0) {
                layoutParams3.topMargin = c;
                layoutParams3.height = (((measuredHeight + ceil) + nsn.c(this.d, 36.0f)) + 1) - c;
                if (layoutParams4 != null) {
                    layoutParams4.topMargin = 0;
                }
            } else {
                layoutParams3.height = measuredHeight + ceil + nsn.c(this.d, 36.0f) + 1;
                layoutParams3.topMargin = 0;
                if (layoutParams4 != null) {
                    layoutParams4.topMargin = c;
                }
            }
            if (layoutParams4 != null) {
                bVar.d.setLayoutParams(layoutParams4);
            }
            bVar.b.setLayoutParams(layoutParams3);
        }
    }

    private CharSequence d(double d, int i) {
        int i2;
        double d2;
        Context context = BaseApplication.getContext();
        if (i != 1) {
            if (i == 2) {
                String e = UnitUtil.e(d, 1, 0);
                String quantityString = context.getResources().getQuantityString(R.plurals._2130903205_res_0x7f0300a5, (int) d, e);
                return mlh.e(quantityString, quantityString.replace(e, ""), this.d.getResources().getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5));
            }
            if (i != 3 && i != 10) {
                if (i == 11) {
                    return c(context, d);
                }
                return mlg.d((int) (d + 0.5d));
            }
        }
        double d3 = d / 1000.0d;
        if (UnitUtil.h()) {
            d2 = UnitUtil.e(d3, 3);
            i2 = R.plurals._2130903240_res_0x7f0300c8;
        } else {
            i2 = R.plurals._2130903239_res_0x7f0300c7;
            d2 = d3;
        }
        String e2 = UnitUtil.e(d2, 1, 2);
        String quantityString2 = context.getResources().getQuantityString(i2, (int) d3, e2);
        return mlh.e(quantityString2, quantityString2.replace(e2, ""), this.d.getResources().getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5));
    }

    private CharSequence c(Context context, double d) {
        String a2 = mlg.a(d);
        String string = context.getResources().getString(R.string._2130844078_res_0x7f0219ae);
        if (UnitUtil.h()) {
            a2 = UnitUtil.e(UnitUtil.e(mef.d(a2), 3), 1, 2);
            string = context.getString(R.string._2130844079_res_0x7f0219af);
        }
        return mlh.e(a2 + string, string, this.d.getResources().getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.f8425a)) {
            return 0;
        }
        return this.f8425a.size();
    }

    public void c(Map<Integer, Pair<Double, Long>> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        this.f8425a.clear();
        this.f8425a = a(map);
        notifyDataSetChanged();
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f8426a;
        RelativeLayout b;
        HealthTextView c;
        ImageView d;
        ImageView e;

        b(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.index_value);
            this.f8426a = (HealthTextView) view.findViewById(R.id.index_desc);
            this.e = (ImageView) view.findViewById(R.id.image_line);
            this.b = (RelativeLayout) view.findViewById(R.id.line_layout);
            this.d = (ImageView) view.findViewById(R.id.image_dot);
        }
    }
}
