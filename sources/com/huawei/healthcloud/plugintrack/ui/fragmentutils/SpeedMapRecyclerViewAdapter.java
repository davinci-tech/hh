package com.huawei.healthcloud.plugintrack.ui.fragmentutils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gvv;
import defpackage.gwh;
import defpackage.hji;
import defpackage.hjw;
import defpackage.koq;
import defpackage.nrt;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SpeedMapRecyclerViewAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private String f3764a;
    private Context b;
    private String c;
    private List<Map.Entry<Integer, Float>> d;
    private float e;
    private boolean f;
    private boolean g;
    private boolean h;
    private double i;
    private boolean j;
    private Resources k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float q;
    private float s;
    private hjw t;

    public SpeedMapRecyclerViewAdapter(Context context, List<Map.Entry<Integer, Float>> list, String str, String str2, float f, float f2, float f3, float f4, float f5, boolean z, hjw hjwVar) {
        this.t = null;
        if (context == null || list == null) {
            throw new RuntimeException("SpeedMapRecyclerViewAdapter invalid params in constructor");
        }
        this.b = context;
        this.k = context.getResources();
        this.d = list;
        this.c = str;
        this.f3764a = str2;
        this.e = 3600.0f / f;
        this.s = 3600.0f / f2;
        this.n = f5;
        this.i = hji.a(hjwVar);
        this.l = f3;
        this.m = f4;
        this.j = z;
        this.t = hjwVar;
        if (f2 != f) {
            float f6 = this.s;
            float f7 = this.e;
            this.o = (f6 / f7) - (f4 / f3);
            this.q = (f3 - f4) / (f7 - f6);
        }
        this.f = LanguageUtil.bc(this.b);
        if (nrt.a(this.b)) {
            this.h = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == 1) {
            return 2;
        }
        if (i == 0) {
            return 1;
        }
        return i == this.d.size() + 2 ? 4 : 3;
    }

    private void e(HealthTextView healthTextView, int i) {
        healthTextView.setTextColor(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bhq_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            return null;
        }
        int i2 = 1;
        if (1 == i) {
            return new c(this.b, bhp_(viewGroup), i2);
        }
        int i3 = 4;
        if (4 == i) {
            c cVar = new c(this.b, bhn_(viewGroup), i3);
            cVar.d(this.j);
            cVar.a(this.h);
            return cVar;
        }
        int i4 = 2;
        if (2 == i) {
            return new c(this.b, bho_(viewGroup), i4);
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_show_pace_item, viewGroup, false);
        if (this.j || this.h) {
            e((HealthTextView) inflate.findViewById(R.id.listview_id), gwh.b);
            if (this.f) {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_rtl_device);
            } else {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_device);
            }
        }
        c cVar2 = new c(this.b, inflate, 3);
        cVar2.d(this.j);
        cVar2.a(this.h);
        return cVar2;
    }

    private View bhn_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_show_pace_item_foot, viewGroup, false);
        if (this.g) {
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.track_show_pace_item_foot_layout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.setMargins(gvv.d(this.b, 16.0f), 0, gvv.d(this.b, 16.0f), gvv.d(this.b, 4.0f));
            relativeLayout.setLayoutParams(layoutParams);
        }
        if (this.j || this.h) {
            e((HealthTextView) inflate.findViewById(R.id.listview_id), gwh.b);
            e((HealthTextView) inflate.findViewById(R.id.tv_use_time), gwh.b);
            if (this.f) {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_rtl_device);
            } else {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_device);
            }
        }
        return inflate;
    }

    private View bho_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_pace_mid_item_layout, viewGroup, false);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_des);
        if (UnitUtil.h()) {
            healthTextView.setText(this.b.getString(R.string._2130839825_res_0x7f020911));
        } else {
            healthTextView.setText(this.b.getString(R.string._2130839826_res_0x7f020912));
        }
        if (this.g) {
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.track_detail_pace_mid_item_linear);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(gvv.d(this.b, 16.0f), 0, gvv.d(this.b, 16.0f), gvv.d(this.b, 10.0f));
            linearLayout.setLayoutParams(layoutParams);
        }
        if (this.j || this.h) {
            e((HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_unit), gwh.b);
            e(healthTextView, gwh.b);
        }
        return inflate;
    }

    private View bhp_(ViewGroup viewGroup) {
        View inflate;
        String string;
        if (this.g) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_pace_top_share_item_layout, viewGroup, false);
            ((HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_title)).setText(R.string._2130839763_res_0x7f0208d3);
            ((HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_title)).setText(R.string._2130842157_res_0x7f02122d);
            ((LinearLayout) inflate.findViewById(R.id.hw_show_linear2)).setVisibility(4);
            if (this.t.e().requestSportType() == 260) {
                LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_show_pace_averagepace_ll);
                LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.hw_show_pace_fastpace_ll);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
            }
            if (this.j || this.h) {
                e((HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_unit), this.b.getResources().getColor(R.color._2131296871_res_0x7f090267));
                e((HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_unit), this.b.getResources().getColor(R.color._2131296871_res_0x7f090267));
            }
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_unit);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_unit);
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_speed_top_item_layout, viewGroup, false);
            ((LinearLayout) inflate.findViewById(R.id.hw_show_linear2)).setVisibility(4);
            HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_unit);
            HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_unit);
            if (UnitUtil.h()) {
                string = this.b.getString(R.string._2130844079_res_0x7f0219af);
            } else {
                string = this.b.getString(R.string._2130844078_res_0x7f0219ae);
            }
            healthTextView3.setText(string);
            healthTextView4.setText(string);
        }
        bhm_(inflate);
        return inflate;
    }

    private void bhm_(View view) {
        if (this.j || this.h) {
            e((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace_title), gwh.b);
            e((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace), gwh.f12970a);
            e((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace_title), gwh.b);
            e((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace), gwh.f12970a);
        }
        if (this.g) {
            if (this.j || this.h) {
                e((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace), this.b.getResources().getColor(R.color._2131296871_res_0x7f090267));
                e((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace), this.b.getResources().getColor(R.color._2131296871_res_0x7f090267));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (cVar == null) {
            return;
        }
        if (cVar.f == 1) {
            cVar.a(this.c, this.f3764a);
            return;
        }
        if (cVar.f == 4) {
            cVar.bhs_(this.k);
            cVar.d(d(UnitUtil.e((this.d.get(i - 3).getKey().intValue() / ExceptionCode.CRASH_EXCEPTION) + 1, 1, 0)), this.n, this.i, gvv.a(this.t) ? 5 : 1);
            return;
        }
        if (cVar.f == 2) {
            LogUtil.a("Track_SpeedMapRecyclerViewAdapter", "onBindViewHolder TYPE_MIDDLE_LAYOUT");
        } else {
            d(cVar, i);
        }
    }

    private void d(c cVar, int i) {
        float f;
        float f2;
        int i2 = i - 2;
        if (koq.b(this.d, i2)) {
            LogUtil.h("Track_SpeedMapRecyclerViewAdapter", "mEntryList.size", Integer.valueOf(this.d.size()), "positionTemp", Integer.valueOf(i2));
            return;
        }
        int intValue = this.d.get(i2).getKey().intValue() / ExceptionCode.CRASH_EXCEPTION;
        cVar.bhs_(this.k);
        Float value = this.d.get(i2).getValue();
        if (Math.abs(value.floatValue()) <= 1.0E-6d) {
            LogUtil.a("Track_SpeedMapRecyclerViewAdapter", "Math.abs(value) <= 1e-6");
            return;
        }
        float floatValue = 3600.0f / value.floatValue();
        if (Math.abs(floatValue - this.s) < 1.0E-6d) {
            if (this.o < 0.0f) {
                f2 = this.m;
            } else {
                f2 = (this.s / this.e) * this.l;
            }
            cVar.e(3, d(UnitUtil.e(intValue, 1, 0)), e(floatValue), f2);
            return;
        }
        if (Math.abs(floatValue - this.e) < 1.0E-6d) {
            cVar.e(1, d(UnitUtil.e(intValue, 1, 0)), e(floatValue), this.l);
            return;
        }
        if (this.o < 0.0f) {
            f = (this.q * (floatValue - this.s)) + this.m;
        } else {
            f = (floatValue / this.e) * this.l;
        }
        cVar.e(3, d(UnitUtil.e(intValue, 1, 0)), e(floatValue), f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size() + 2 + (this.n == 0.0f ? 0 : 1);
    }

    public void b(boolean z) {
        this.g = z;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3765a;
        private Context b;
        private HealthTextView c;
        private boolean d;
        private HealthTextView e;
        private int f;
        private HealthTextView g;
        private boolean h;
        private HealthTextView i;
        private HealthTextView j;
        private View o;

        private c(Context context, View view, int i) {
            super(view);
            this.f = i;
            this.o = view;
            this.b = context;
            this.h = LanguageUtil.bc(context);
            d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(boolean z) {
            this.f3765a = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z) {
            this.d = z;
        }

        private void d() {
            int i = this.f;
            if (i == 1) {
                this.e = (HealthTextView) this.o.findViewById(R.id.hw_show_pace_fastpace);
                this.c = (HealthTextView) this.o.findViewById(R.id.hw_show_pace_averagepace);
                return;
            }
            if (i == 2) {
                HealthTextView healthTextView = (HealthTextView) this.o.findViewById(R.id.track_detail_pace_mid_layout_distance_unit);
                if (UnitUtil.h()) {
                    healthTextView.setText(this.b.getResources().getString(R.string._2130839828_res_0x7f020914));
                    return;
                }
                return;
            }
            if (i == 3) {
                this.i = (HealthTextView) this.o.findViewById(R.id.listview_id);
                this.j = (HealthTextView) this.o.findViewById(R.id.listview_value);
                this.g = (HealthTextView) this.o.findViewById(R.id.tv_title);
            } else {
                if (i == 4) {
                    this.i = (HealthTextView) this.o.findViewById(R.id.listview_id);
                    this.j = (HealthTextView) this.o.findViewById(R.id.tv_use_time);
                    this.g = (HealthTextView) this.o.findViewById(R.id.tv_title);
                    return;
                }
                LogUtil.a("Track_SpeedMapRecyclerViewAdapter", "initView type error");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(String str, float f, double d, int i) {
            String string;
            Resources resources = this.b.getResources();
            if (UnitUtil.h()) {
                string = resources.getString(R.string._2130839714_res_0x7f0208a2, Integer.valueOf(i));
            } else {
                string = resources.getString(R.string._2130839713_res_0x7f0208a1, Integer.valueOf(i));
            }
            String e = UnitUtil.e((d * 3600.0d) / f, 1, 2);
            this.i.setText(str);
            this.j.setText(string + " " + e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2) {
            this.e.setText(str);
            this.c.setText(str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void bhs_(Resources resources) {
            if (this.b == null || this.f3765a || this.d || !this.h) {
                return;
            }
            this.g.setBackground(resources.getDrawable(R.drawable._2131431939_res_0x7f0b1203));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(int i, String str, String str2, float f) {
            Context context = this.b;
            if (context == null) {
                LogUtil.b("Track_SpeedMapRecyclerViewAdapter", "mContext is null");
                return;
            }
            Resources resources = context.getResources();
            this.j.setTextSize(2, 13.0f / resources.getConfiguration().fontScale);
            this.i.setText(str);
            this.j.setText(str2);
            bht_(resources, i);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams.width = (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
            this.j.setLayoutParams(layoutParams);
        }

        private void bht_(Resources resources, int i) {
            if (i == 1) {
                if (this.h) {
                    this.j.setBackground(resources.getDrawable(R.drawable._2131431942_res_0x7f0b1206));
                    return;
                } else {
                    this.j.setBackground(resources.getDrawable(R.drawable._2131431941_res_0x7f0b1205));
                    return;
                }
            }
            if (this.h) {
                this.j.setBackground(resources.getDrawable(R.drawable._2131431936_res_0x7f0b1200));
            } else {
                this.j.setBackground(resources.getDrawable(R.drawable._2131431935_res_0x7f0b11ff));
            }
        }
    }

    private String e(float f) {
        return UnitUtil.e(f, 1, 2);
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_SpeedMapRecyclerViewAdapter", "dealBikeTrackSpeedListId speedId is empty.");
            return str;
        }
        if (!gvv.a(this.t)) {
            return str;
        }
        int h = (CommonUtil.h(str) * 5) - 4;
        int h2 = CommonUtil.h(str) * 5;
        if (this.f) {
            return UnitUtil.e(h, 1, 0) + Constants.LINK + UnitUtil.e(h2, 1, 0);
        }
        return h + Constants.LINK + h2;
    }
}
