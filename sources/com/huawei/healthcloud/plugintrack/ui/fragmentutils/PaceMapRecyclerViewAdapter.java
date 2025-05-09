package com.huawei.healthcloud.plugintrack.ui.fragmentutils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.views.MarketingView;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.PaceMapRecyclerViewAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gvv;
import defpackage.gwh;
import defpackage.hji;
import defpackage.koq;
import defpackage.nrt;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PaceMapRecyclerViewAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private float f3762a;
    private List<Map.Entry<Integer, Float>> b;
    private String c;
    private String d;
    private Context e;
    private double f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private float n;
    private boolean o;
    private float p;
    private List<d> q;
    private float r;
    private double s;
    private Resources t;
    private float v;
    private int w;
    private float x;
    private float y;

    public PaceMapRecyclerViewAdapter(Context context, List<Map.Entry<Integer, Float>> list, String str, String str2, float f, float f2, float f3, float f4, float f5, boolean z, double d2, double d3, int i, boolean z2) {
        if (context == null || list == null) {
            throw new RuntimeException("PaceMapRecyclerViewAdapter invalid params in constructor");
        }
        this.e = context;
        this.t = context.getResources();
        this.o = UnitUtil.h();
        this.b = list;
        this.c = str;
        this.d = str2;
        this.f3762a = f;
        this.y = f2;
        this.n = f5;
        this.p = f3;
        this.v = f4;
        this.g = z;
        this.w = i;
        this.j = i == 258 || i == 264;
        this.f = d2;
        this.s = d3;
        this.l = z2;
        if (f2 != f) {
            this.r = (f / f2) - (f4 / f3);
            this.x = (f3 - f4) / (f2 - f);
        }
        LogUtil.a("Track_PaceMapRecyclerViewAdapter", "mRateDiff =", Float.valueOf(this.r), "  DisplayMin =", Float.valueOf(this.v), "DisplayMax = ", Float.valueOf(this.p));
        this.i = LanguageUtil.bc(this.e);
        b();
        if (nrt.a(this.e)) {
            this.h = true;
        }
    }

    private void b() {
        if (c()) {
            this.k = false;
            LogUtil.a("Track_PaceMapRecyclerViewAdapter", "show old pace view");
            return;
        }
        this.q = new ArrayList(16);
        Resources resources = this.e.getResources();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Map.Entry<Integer, Float> entry : this.b) {
            this.q.add(new d(3, entry));
            i++;
            int round = Math.round(entry.getValue().floatValue());
            i2 += round;
            i3 += round;
            if (this.o) {
                if (i % 5 == 0) {
                    this.q.add(new d(5, resources.getString(R.string._2130842379_res_0x7f02130b, resources.getQuantityString(R.plurals._2130903240_res_0x7f0300c8, i, UnitUtil.e(i, 1, 0)), UnitUtil.d(i2)), resources.getString(R.string._2130842385_res_0x7f021311, UnitUtil.d(i3))));
                    i3 = 0;
                }
            } else if (i % 5 == 0) {
                this.q.add(new d(5, resources.getString(R.string._2130842379_res_0x7f02130b, resources.getQuantityString(R.plurals._2130903239_res_0x7f0300c7, i, UnitUtil.e(i, 1, 0)), UnitUtil.d(i2)), resources.getString(R.string._2130842385_res_0x7f021311, UnitUtil.d(i3))));
                i3 = 0;
            }
        }
        if (this.n != 0.0f) {
            this.q.add(new d(4, "", ""));
        }
        this.q.add(new d(6, "", ""));
        this.k = true;
    }

    private boolean c() {
        int size;
        List<Map.Entry<Integer, Float>> list = this.b;
        return list == null || (size = list.size()) == 0 || this.b.get(size + (-1)).getKey().intValue() / ExceptionCode.CRASH_EXCEPTION != size || this.w == 274;
    }

    private void c(HealthTextView healthTextView, int i) {
        healthTextView.setTextColor(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (!this.k) {
            if (i != this.b.size() + 2) {
                return i == this.b.size() + 3 ? 6 : 3;
            }
            LogUtil.a("Track_PaceMapRecyclerViewAdapter", "position == TYPE_FOOT_LAYOUT");
            return 4;
        }
        return this.q.get(i - 2).d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bgX_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            return null;
        }
        int i2 = 1;
        if (1 == i) {
            return new c(this.e, bgU_(viewGroup), i2);
        }
        int i3 = 2;
        if (2 == i) {
            return new c(this.e, bgS_(viewGroup), i3);
        }
        int i4 = 4;
        if (4 == i) {
            c cVar = new c(this.e, bgR_(viewGroup), i4);
            cVar.b(this.g);
            cVar.c(this.h);
            return cVar;
        }
        int i5 = 5;
        if (5 == i) {
            return new c(this.e, bgT_(viewGroup), i5);
        }
        int i6 = 6;
        if (6 == i) {
            if (!e()) {
                return new c(this.e, new LinearLayout(this.e), i6);
            }
            MarketingView marketingView = new MarketingView(this.e);
            marketingView.setInvalidateCallback(new IBaseResponseCallback() { // from class: hjo
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i7, Object obj) {
                    PaceMapRecyclerViewAdapter.this.e(i7, obj);
                }
            });
            marketingView.d(a());
            return new c(this.e, marketingView, i6);
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_show_pace_item, viewGroup, false);
        if (this.g || this.h) {
            c((HealthTextView) inflate.findViewById(R.id.listview_id), gwh.b);
            if (this.i) {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_rtl_device);
            } else {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_device);
            }
        }
        c cVar2 = new c(this.e, inflate, 3);
        cVar2.b(this.g);
        cVar2.c(this.h);
        return cVar2;
    }

    public /* synthetic */ void e(int i, Object obj) {
        notifyDataSetChanged();
    }

    private int a() {
        return this.w == 264 ? 4174 : 4153;
    }

    private boolean e() {
        int i = this.w;
        return (i == 258 || i == 264) && !this.m;
    }

    private View bgT_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_show_pace_item_5n, viewGroup, false);
        if (this.g || this.h) {
            ((HealthTextView) inflate.findViewById(R.id.tv_use_time)).setTextColor(gwh.b);
            ((HealthTextView) inflate.findViewById(R.id.tv_title)).setTextColor(gwh.b);
        }
        return inflate;
    }

    private View bgR_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_show_pace_item_foot, viewGroup, false);
        if (this.m) {
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.track_show_pace_item_foot_layout);
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
                LogUtil.b("Track_PaceMapRecyclerViewAdapter", "object is not instanceof LinearLayout.LayoutParams");
                return null;
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(gvv.d(this.e, 16.0f), 0, gvv.d(this.e, 16.0f), gvv.d(this.e, 4.0f));
            relativeLayout.setLayoutParams(layoutParams2);
        }
        if (this.g || this.h) {
            c((HealthTextView) inflate.findViewById(R.id.listview_id), gwh.b);
            c((HealthTextView) inflate.findViewById(R.id.tv_use_time), gwh.b);
            if (this.i) {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_rtl_device);
            } else {
                inflate.findViewById(R.id.tv_title).setBackgroundResource(R.drawable.track_show_pace_progressbackground_device);
            }
        }
        return inflate;
    }

    private View bgS_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_pace_mid_item_layout, viewGroup, false);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_des);
        d((HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_unit), healthTextView);
        if (this.m) {
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.track_detail_pace_mid_item_linear);
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
                LogUtil.b("Track_PaceMapRecyclerViewAdapter", "object is not instanceof LinearLayout.LayoutParams");
                return null;
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(gvv.d(this.e, 16.0f), 0, gvv.d(this.e, 16.0f), gvv.d(this.e, 10.0f));
            linearLayout.setLayoutParams(layoutParams2);
        }
        if (this.g || this.h) {
            c((HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_unit), gwh.b);
            c((HealthTextView) inflate.findViewById(R.id.track_detail_pace_mid_layout_distance_des), gwh.b);
        }
        if (this.m) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
        }
        return inflate;
    }

    private void d(HealthTextView healthTextView, HealthTextView healthTextView2) {
        String string;
        String string2;
        if (this.w == 274) {
            if (this.o) {
                string = this.e.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
                string2 = this.e.getString(R.string._2130839842_res_0x7f020922, 100);
            } else {
                string = this.e.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
                string2 = this.e.getString(R.string._2130839841_res_0x7f020921, 500);
            }
        } else if (this.o) {
            string = this.e.getResources().getString(R.string._2130839828_res_0x7f020914);
            string2 = this.e.getString(R.string._2130839823_res_0x7f02090f);
        } else {
            string = this.e.getResources().getString(R.string._2130839829_res_0x7f020915);
            string2 = this.e.getString(R.string._2130839824_res_0x7f020910);
        }
        healthTextView.setText(string);
        healthTextView2.setText(string2);
    }

    private View bgU_(ViewGroup viewGroup) {
        View inflate;
        String str;
        if (this.m) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_pace_top_share_item_layout, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_detail_pace_top_item_layout, viewGroup, false);
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_unit);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_unit);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_title);
        if (this.o) {
            str = "/" + this.e.getString(R.string._2130844081_res_0x7f0219b1);
        } else {
            str = "/" + this.e.getString(R.string._2130844082_res_0x7f0219b2);
        }
        if (this.w == 274) {
            if (this.o) {
                str = String.format(this.e.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100), new Object[0]);
            } else {
                str = String.format(String.format(this.e.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500), new Object[0]), new Object[0]);
            }
        }
        healthTextView3.setText(this.e.getResources().getString(R.string._2130839910_res_0x7f020966, str));
        healthTextView.setText(str);
        healthTextView2.setText(str);
        if (this.m) {
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
            if (this.g || this.h) {
                c(healthTextView, this.e.getResources().getColor(R.color._2131296871_res_0x7f090267));
                c(healthTextView2, this.e.getResources().getColor(R.color._2131296871_res_0x7f090267));
            }
        } else if (this.l) {
            bgV_(inflate);
        }
        bgQ_(inflate);
        return inflate;
    }

    private void bgQ_(View view) {
        if (this.g || this.h) {
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace_title), gwh.b);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace), gwh.f12970a);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace_title), gwh.b);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace), gwh.f12970a);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_half_title), gwh.b);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_marathon_title), gwh.b);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_marathon), gwh.f12970a);
            c((HealthTextView) view.findViewById(R.id.hw_show_pace_half), gwh.f12970a);
        }
        if (this.m) {
            if (this.g || this.h) {
                c((HealthTextView) view.findViewById(R.id.hw_show_pace_averagepace), this.e.getResources().getColor(R.color._2131296871_res_0x7f090267));
                c((HealthTextView) view.findViewById(R.id.hw_show_pace_fastpace), this.e.getResources().getColor(R.color._2131296871_res_0x7f090267));
            }
        }
    }

    private void bgV_(View view) {
        String string;
        final View inflate = View.inflate(this.e, R.layout.track_detail_pace_top_item_layout_tips, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.pace_tips);
        if (UnitUtil.h()) {
            if (this.w == 274) {
                string = this.e.getResources().getString(R.string._2130843758_res_0x7f02186e, 100, 100);
            } else {
                string = this.e.getResources().getString(R.string._2130843759_res_0x7f02186f, 1);
            }
        } else if (this.w == 274) {
            string = this.e.getResources().getString(R.string._2130843743_res_0x7f02185f, 500, 500);
        } else {
            string = this.e.getResources().getString(R.string._2130842515_res_0x7f021393);
        }
        healthTextView.setText(string);
        ImageView imageView = (ImageView) view.findViewById(R.id.hw_show_pace_fastpace_title_tips);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragmentutils.PaceMapRecyclerViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PaceMapRecyclerViewAdapter.this.bgW_(inflate);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bgW_(View view) {
        if (this.e == null || view == null) {
            LogUtil.b("Track_PaceMapRecyclerViewAdapter", "showPaceDialog, mContext or view is null");
            return;
        }
        ViewParent parent = view.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeAllViews();
        }
        new CustomViewDialog.Builder(this.e).czg_(view).a(this.e.getString(R.string.IDS_service_area_notice_title)).czd_(this.e.getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragmentutils.PaceMapRecyclerViewAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).e().show();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        Map.Entry<Integer, Float> entry;
        float floatValue;
        if (cVar == null) {
            return;
        }
        if (cVar.o == 1) {
            cVar.d(this.c, this.d, this.j, this.f, this.s);
            return;
        }
        if (cVar.o == 2) {
            LogUtil.c("Track_PaceMapRecyclerViewAdapter", "Type is two");
            return;
        }
        if (cVar.o == 4) {
            cVar.bgZ_(this.t);
            cVar.d(this.w);
            cVar.a((this.k ? this.b.size() : this.b.get(i - 3).getKey().intValue() / ExceptionCode.CRASH_EXCEPTION) + 1, this.n, this.o);
            return;
        }
        if (cVar.o == 5) {
            d dVar = this.q.get(i - 2);
            cVar.a(dVar.b, dVar.c);
            return;
        }
        if (cVar.o == 6) {
            LogUtil.a("Track_PaceMapRecyclerViewAdapter", "Type is TYPE_MARKETING_LAYOUT");
            return;
        }
        LogUtil.a("Track_PaceMapRecyclerViewAdapter", "Type is ", Integer.valueOf(cVar.o));
        int i2 = i - 2;
        if (this.k) {
            if (koq.d(this.q, i2) && this.q.get(i2) != null) {
                entry = this.q.get(i2).e;
            }
            entry = null;
        } else {
            if (koq.d(this.b, i2)) {
                entry = this.b.get(i2);
            }
            entry = null;
        }
        if (entry == null) {
            return;
        }
        double intValue = (entry.getKey().intValue() * 1.0d) / 1.0E7d;
        if (this.w == 274 && !this.o) {
            intValue = ((entry.getKey().intValue() * 1.0d) / 1.0E7d) * 0.5d;
        }
        cVar.bgZ_(this.t);
        if (entry.getValue().floatValue() == this.y) {
            cVar.d(3, a(intValue, this.w), e(entry.getValue().floatValue()), this.p);
            return;
        }
        if (entry.getValue().floatValue() == this.f3762a) {
            b(cVar, entry, a(intValue, this.w));
            return;
        }
        if (this.r < 0.0f) {
            floatValue = (this.x * (entry.getValue().floatValue() - this.f3762a)) + this.v;
        } else {
            floatValue = (entry.getValue().floatValue() / this.y) * this.p;
        }
        cVar.d(3, a(intValue, this.w), e(entry.getValue().floatValue()), floatValue);
    }

    private String a(double d2, int i) {
        if (i == 274 && !this.o) {
            return UnitUtil.e(d2, 1, 1);
        }
        return UnitUtil.e(d2, 1, 0);
    }

    private void b(c cVar, Map.Entry<Integer, Float> entry, String str) {
        float f;
        if (this.r < 0.0f) {
            f = this.v;
        } else {
            f = (this.f3762a / this.y) * this.p;
        }
        cVar.d(1, str, e(entry.getValue().floatValue()), f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.k) {
            return this.q.size() + 2;
        }
        return this.b.size() + 3 + (this.n == 0.0f ? 0 : 1);
    }

    public void c(boolean z) {
        this.m = z;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private Context f3763a;
        private HealthTextView b;
        private boolean c;
        private HealthTextView d;
        private boolean e;
        private boolean f;
        private HealthTextView g;
        private int h;
        private HealthTextView i;
        private HealthTextView j;
        private View l;
        private int o;

        private c(Context context, View view, int i) {
            super(view);
            this.o = i;
            this.l = view;
            this.f3763a = context;
            this.f = LanguageUtil.bc(context);
            c();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(boolean z) {
            this.e = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(boolean z) {
            this.c = z;
        }

        private void c() {
            int i = this.o;
            if (i == 1) {
                this.b = (HealthTextView) this.l.findViewById(R.id.hw_show_pace_fastpace);
                this.d = (HealthTextView) this.l.findViewById(R.id.hw_show_pace_averagepace);
                return;
            }
            if (i == 3) {
                this.i = (HealthTextView) this.l.findViewById(R.id.listview_id);
                this.j = (HealthTextView) this.l.findViewById(R.id.listview_value);
                this.g = (HealthTextView) this.l.findViewById(R.id.tv_title);
            } else if (i == 4) {
                this.i = (HealthTextView) this.l.findViewById(R.id.listview_id);
                this.j = (HealthTextView) this.l.findViewById(R.id.tv_use_time);
                this.g = (HealthTextView) this.l.findViewById(R.id.tv_title);
            } else if (i == 5) {
                this.j = (HealthTextView) this.l.findViewById(R.id.tv_use_time);
                this.g = (HealthTextView) this.l.findViewById(R.id.tv_title);
            } else {
                LogUtil.a("Track_PaceMapRecyclerViewAdapter", "initView type error");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, float f, boolean z) {
            String string;
            Resources resources = this.f3763a.getResources();
            if (this.h == 274) {
                if (z) {
                    string = resources.getString(R.string._2130843532_res_0x7f02178c, 100);
                } else {
                    string = resources.getString(R.string._2130843501_res_0x7f02176d, 500);
                }
            } else if (z) {
                string = resources.getString(R.string._2130839714_res_0x7f0208a2, 1);
            } else {
                string = resources.getString(R.string._2130839713_res_0x7f0208a1, 1);
            }
            String string2 = resources.getString(R.string._2130839715_res_0x7f0208a3, hji.a(f));
            this.i.setText(UnitUtil.e(i, 1, 0));
            this.j.setText(string + " " + string2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(String str, String str2, boolean z, double d, double d2) {
            this.b.setText(str);
            this.d.setText(str2);
            if (!z || d <= 0.0d) {
                return;
            }
            this.l.findViewById(R.id.hw_show_pace_half_ll).setVisibility(0);
            this.l.findViewById(R.id.hw_show_mid_guide_line).setVisibility(0);
            ((HealthTextView) this.l.findViewById(R.id.hw_show_pace_half)).setText(UnitUtil.d((int) Math.round(d)));
            if (d2 > 0.0d) {
                this.l.findViewById(R.id.hw_show_pace_marathon_ll).setVisibility(0);
                ((HealthTextView) this.l.findViewById(R.id.hw_show_pace_marathon)).setText(UnitUtil.d((int) Math.round(d2)));
                return;
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) this.l.findViewById(R.id.hw_show_pace_cl);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.hw_show_pace_averagepace_ll);
            constraintSet.constrainWidth(R.id.hw_show_pace_averagepace_ll, -2);
            constraintSet.constrainHeight(R.id.hw_show_pace_averagepace_ll, -2);
            constraintSet.connect(R.id.hw_show_pace_averagepace_ll, 3, R.id.hw_show_pace_cl, 3);
            constraintSet.connect(R.id.hw_show_pace_averagepace_ll, 6, R.id.hw_show_guide_line, 7);
            constraintSet.connect(R.id.hw_show_pace_averagepace_ll, 7, R.id.hw_show_pace_cl, 7);
            constraintSet.clear(R.id.hw_show_pace_fastpace_ll, 6);
            constraintSet.clear(R.id.hw_show_pace_fastpace_ll, 7);
            constraintSet.connect(R.id.hw_show_pace_fastpace_ll, 6, R.id.hw_show_pace_cl, 6);
            constraintSet.connect(R.id.hw_show_pace_fastpace_ll, 7, R.id.hw_show_guide_line, 6);
            constraintSet.applyTo(constraintLayout);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void bgZ_(Resources resources) {
            if (this.f3763a == null || this.e || this.c || !this.f) {
                return;
            }
            this.g.setBackground(resources.getDrawable(R.drawable._2131431939_res_0x7f0b1203));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i, String str, String str2, float f) {
            Context context = this.f3763a;
            if (context == null) {
                LogUtil.b("Track_PaceMapRecyclerViewAdapter", "mContext is null");
                return;
            }
            Resources resources = context.getResources();
            this.j.setTextSize(2, 13.0f / resources.getConfiguration().fontScale);
            this.i.setText(str);
            this.j.setText(str2);
            bha_(resources, i);
            ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
            if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
                LogUtil.b("Track_PaceMapRecyclerViewAdapter", "object is not instanceof RelativeLayout.LayoutParams");
                return;
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
            this.j.setLayoutParams(layoutParams2);
        }

        private void bha_(Resources resources, int i) {
            if (i == 1) {
                if (this.f) {
                    this.j.setBackground(resources.getDrawable(R.drawable._2131431942_res_0x7f0b1206));
                    return;
                } else {
                    this.j.setBackground(resources.getDrawable(R.drawable._2131431941_res_0x7f0b1205));
                    return;
                }
            }
            if (this.f) {
                this.j.setBackground(resources.getDrawable(R.drawable._2131431936_res_0x7f0b1200));
            } else {
                this.j.setBackground(resources.getDrawable(R.drawable._2131431935_res_0x7f0b11ff));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2) {
            this.g.setText(str2);
            this.j.setText(str);
        }

        public void d(int i) {
            this.h = i;
        }
    }

    private String e(float f) {
        return gvv.a(f);
    }

    static class d {
        private String b;
        private String c;
        private int d;
        private Map.Entry<Integer, Float> e;

        d(int i, Map.Entry<Integer, Float> entry) {
            this.e = entry;
            this.d = i;
        }

        d(int i, String str, String str2) {
            this.d = i;
            this.b = str;
            this.c = str2;
        }
    }
}
