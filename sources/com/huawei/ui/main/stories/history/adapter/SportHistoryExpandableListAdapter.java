package com.huawei.ui.main.stories.history.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.model.HealthDataStatPolicy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.MonthTitleContainer;
import com.huawei.ui.main.stories.history.view.MonthTitleItem;
import defpackage.cat;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.hkc;
import defpackage.hln;
import defpackage.jcf;
import defpackage.koq;
import defpackage.kpj;
import defpackage.kxb;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.rck;
import defpackage.rdo;
import defpackage.rdr;
import defpackage.rdu;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class SportHistoryExpandableListAdapter extends BaseExpandableListAdapter {
    private static final List<Integer> d = new ArrayList(Arrays.asList(258, 257, 259, 260, 262, 283));
    private List<rdo> c;
    private Context e;
    private int h;
    private boolean i;
    private String b = "--";

    /* renamed from: a, reason: collision with root package name */
    private int f10300a = 0;

    private boolean i(double d2) {
        return d2 <= 0.0d;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        if (i2 > 0) {
            return i2;
        }
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        if (i > 0) {
            return i;
        }
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public SportHistoryExpandableListAdapter(Context context, Fragment fragment) {
        d(context, fragment);
    }

    private void d(Context context, Fragment fragment) {
        this.e = context;
        this.b = context.getResources().getString(R$string.IDS_motiontrack_show_invalid_data);
    }

    public void c(List<rdo> list, int i) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "resetGroupData");
        this.c = list;
        this.f10300a = i;
        this.i = Utils.l();
    }

    public void e(int i) {
        this.h = i;
    }

    public void a() {
        List<rdo> list = this.c;
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<rdo> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<rdo> list = this.c;
        if (list == null || i < 0 || list.size() <= i) {
            return 0;
        }
        return this.c.get(i).d();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        List<rdo> list = this.c;
        if (list == null || list.size() == 0 || koq.b(this.c, i)) {
            return null;
        }
        return this.c.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        if (!koq.b(this.c, i) && this.c.get(i2).d() > i2) {
            return this.c.get(i).a(i2);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null || (!this.i && !(view.getTag() instanceof a))) {
            aVar = new a();
            view = LayoutInflater.from(this.e).inflate(R.layout.layout_sport_history_father_item, (ViewGroup) null);
            dKb_(view, aVar);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (!koq.b(this.c, i) && !c(i, z, aVar, this.c.get(i).g()) && this.h == 1) {
            aVar.f.setVisibility(8);
            view.findViewById(R.id.hw_show_health_data_running_history_father_line).setVisibility(8);
        }
        return view;
    }

    private boolean c(int i, boolean z, a aVar, int i2) {
        if (koq.b(this.c, i)) {
            return true;
        }
        if (this.f10300a == 0) {
            aVar.g.setVisibility(0);
            a(aVar, i);
        } else {
            aVar.g.setVisibility(8);
        }
        if (z) {
            aVar.f.setBackgroundResource(R.drawable._2131428605_res_0x7f0b04fd);
        } else {
            aVar.f.setBackgroundResource(R.drawable._2131428606_res_0x7f0b04fe);
        }
        if (aVar.g.getExpandStatus()) {
            aVar.e.setText(this.e.getResources().getString(R$string.IDS_device_health_retract));
            aVar.c.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_sel);
        } else {
            aVar.e.setText(this.e.getResources().getString(R$string.IDS_user_profile_more_new));
            aVar.c.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_nor);
        }
        aVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.adapter.SportHistoryExpandableListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null) {
                    SportHistoryExpandableListAdapter.this.dKc_(view);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.b("Track_SportHistoryExpandableListAdapter", "view is null");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        if (i >= this.c.size()) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "groupPostition is is more than groupData size");
            return true;
        }
        e(z, aVar, i2);
        aVar.b.setText(this.c.get(i).i());
        e(i, aVar);
        jcf.bED_(aVar.i, nsf.b(R$string.accessibility_sport_record_summary_month, aVar.b.getText(), aVar.h.getText()), z);
        return false;
    }

    private void e(int i, a aVar) {
        if (koq.b(this.c, i)) {
            LogUtil.b("Track_SportHistoryExpandableListAdapter", Integer.valueOf(i), "isOutOfBounds ");
        } else {
            aVar.h.setText(b(this.c.get(i)));
        }
    }

    public String b(rdo rdoVar) {
        String string;
        if (rdoVar == null) {
            return "";
        }
        int i = this.f10300a;
        if (i == 0 && rdoVar.m()) {
            i = rdoVar.f();
        }
        if (this.f10300a == 0 && rdoVar.m() && i == 287) {
            return rdoVar.e(false);
        }
        if (i == 287) {
            return rdoVar.e(true);
        }
        String d2 = d(rdoVar);
        String e2 = e(rdoVar);
        double a2 = a(rdoVar);
        String c2 = c(rdoVar, i);
        if (hkc.e(this.f10300a) || hkc.e(rdoVar.f())) {
            string = this.e.getResources().getString(R$string.IDS_history_desc_four, c2, c(a2), d2, e2);
        } else {
            string = this.e.getResources().getString(R$string.IDS_history_desc_three, c(a2), d2, e2);
        }
        return string.trim();
    }

    private String c(rdo rdoVar, int i) {
        double c2 = rdoVar.c(i) / 1000.0d;
        if (i(c2)) {
            return "";
        }
        if (UnitUtil.h()) {
            return nsf.a(R.plurals._2130903302_res_0x7f030106, (int) c2, UnitUtil.e(UnitUtil.e(c2, 3), 1, 2));
        }
        return nsf.a(R.plurals._2130903301_res_0x7f030105, (int) c2, UnitUtil.e(c2, 1, 2));
    }

    private double a(rdo rdoVar) {
        return ((rdoVar.b(this.f10300a) - ((int) rdoVar.a(ComponentInfo.PluginPay_A_N, 4))) - ((int) rdoVar.a(288, 4))) / 1000.0d;
    }

    private String d(rdo rdoVar) {
        int i;
        int i2;
        int j = rdoVar.j();
        if (this.f10300a == 0) {
            i2 = (int) rdoVar.a(ComponentInfo.PluginPay_A_N, 5);
            i = (int) rdoVar.a(288, 5);
        } else {
            i = 0;
            i2 = 0;
        }
        int i3 = (j - i2) - i;
        double d2 = i3;
        return i(d2) ? "" : this.e.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, i3, UnitUtil.e(d2, 1, 0));
    }

    private String e(rdo rdoVar) {
        double d2 = rdoVar.d(this.f10300a) / 1000.0d;
        return i(d2) ? "" : nsf.a(R.plurals._2130903422_res_0x7f03017e, (int) d2, UnitUtil.e(d2, 1, 0));
    }

    private String c(double d2) {
        return i(d2) ? "" : cat.c(d2);
    }

    private void e(boolean z, a aVar, int i) {
        if (z) {
            aVar.f10302a.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_sel);
            aVar.d.setVisibility(0);
        } else {
            aVar.d.setVisibility(8);
            aVar.f10302a.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_nor);
        }
        if (i <= 6) {
            aVar.d.setVisibility(8);
        }
    }

    private void dKb_(View view, a aVar) {
        aVar.b = (HealthTextView) view.findViewById(R.id.hw_show_health_data_running_history_father_date);
        aVar.f10302a = (ImageView) view.findViewById(R.id.hw_show_health_data_running_history_father_arrow);
        aVar.h = (HealthTextView) view.findViewById(R.id.hw_show_health_father_summary_time);
        aVar.g = (MonthTitleContainer) view.findViewById(R.id.month_history_title);
        aVar.f = (LinearLayout) view.findViewById(R.id.history_father_layout);
        aVar.i = (LinearLayout) view.findViewById(R.id.first_item_title);
        aVar.j = (LinearLayout) view.findViewById(R.id.month_sport_summary_layout);
        aVar.j.setOnClickListener(null);
        aVar.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.main.stories.history.adapter.SportHistoryExpandableListAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                return true;
            }
        });
        aVar.d = (LinearLayout) view.findViewById(R.id.inner_center_layout);
        aVar.c = (ImageView) view.findViewById(R.id.expand_arrow);
        aVar.e = (HealthTextView) view.findViewById(R.id.collapse_or_expand_text);
        view.setPadding(0, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dKc_(View view) {
        MonthTitleContainer monthTitleContainer = (MonthTitleContainer) ((View) view.getParent()).findViewById(R.id.month_history_title);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.collapse_or_expand_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.expand_arrow);
        if (monthTitleContainer.getExpandStatus()) {
            monthTitleContainer.setExpandStatus(false);
            monthTitleContainer.requestLayout();
            healthTextView.setText(this.e.getResources().getString(R$string.IDS_user_profile_more_new));
            imageView.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_nor);
            return;
        }
        monthTitleContainer.setExpandStatus(true);
        monthTitleContainer.requestLayout();
        healthTextView.setText(this.e.getResources().getString(R$string.IDS_device_health_retract));
        imageView.setBackgroundResource(R.drawable.ic_health_list_drop_down_arrow_sel);
    }

    private void a(a aVar, int i) {
        Context context;
        if (!koq.d(this.c, i) || this.c.get(i) == null || (context = this.e) == null) {
            return;
        }
        c cVar = new c(context, this.c.get(i));
        if (this.f10300a == 0) {
            cVar.e(aVar.g, true, this.e.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b));
        } else {
            cVar.e(aVar.g, false, this.e.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b));
        }
    }

    private String b(int i) {
        return cat.b(i, 0);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            e eVar2 = new e();
            View inflate = LayoutInflater.from(this.e).inflate(R.layout.layout_sport_history_child_item, (ViewGroup) null);
            dKa_(eVar2, inflate);
            inflate.setTag(eVar2);
            eVar = eVar2;
            view = inflate;
        } else {
            eVar = (e) view.getTag();
        }
        if (koq.b(this.c, i)) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "groupPostion is outOfBounds mGroupData");
            return view;
        }
        if (this.c.get(i) == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "groupData is null");
            return view;
        }
        rdr a2 = this.c.get(i).a(i2);
        if (a2 == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "child is null'");
            return view;
        }
        String d2 = rdu.d(a2.s(), this.e);
        if (!TextUtils.isEmpty(a2.q()) && "7".equals(a2.q())) {
            d2 = this.e.getResources().getString(R$string.IDS_lactate_threshold_measurement);
        } else if (222 == a2.s() && StringUtils.i(a2.z()) && LanguageUtil.h(this.e)) {
            d2 = String.format(this.e.getString(R$string.IDS_sport_history_run_course_name), d2, a2.z());
        }
        eVar.n.setText(d2);
        eVar.n.setCompoundDrawablePadding(0);
        eVar.n.setCompoundDrawables(null, null, null, null);
        a(eVar, a2);
        a(z, eVar, a2);
        d(i2, eVar);
        r(eVar, a2);
        return view;
    }

    private void a(boolean z, e eVar, rdr rdrVar) {
        if (rdrVar.ag()) {
            dKe_(z, eVar.f, R.drawable._2131428600_res_0x7f0b04f8, R.drawable._2131428604_res_0x7f0b04fc);
        } else {
            dKe_(z, eVar.f, R.drawable._2131428599_res_0x7f0b04f7, R.drawable._2131428603_res_0x7f0b04fb);
        }
    }

    private void d(int i, e eVar) {
        int dimension = (int) this.e.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        if (this.h == 1) {
            eVar.c.setPadding((int) this.e.getResources().getDimension(R.dimen._2131362582_res_0x7f0a0316), 0, 0, 0);
            dimension = 0;
        }
        if (i == 0) {
            eVar.c.setVisibility(8);
            eVar.f.setPadding(dimension, (int) this.e.getResources().getDimension(R.dimen._2131362564_res_0x7f0a0304), dimension, (int) this.e.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
        } else {
            eVar.c.setVisibility(0);
            eVar.f.setPadding(dimension, (int) this.e.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e), dimension, (int) this.e.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
        }
    }

    public void dKe_(boolean z, View view, int i, int i2) {
        if (this.h == 1) {
            return;
        }
        if (z) {
            view.setBackground(ContextCompat.getDrawable(this.e, i));
        } else {
            view.setBackground(ContextCompat.getDrawable(this.e, i2));
        }
    }

    private void r(e eVar, rdr rdrVar) {
        if (rdrVar.ae() == 7) {
            Drawable drawable = ContextCompat.getDrawable(this.e, R.drawable._2131429699_res_0x7f0b0943);
            if (LanguageUtil.bc(this.e)) {
                drawable = nrz.cKn_(this.e, R.drawable._2131429699_res_0x7f0b0943);
            }
            if (drawable != null) {
                eVar.b.setVisibility(0);
                eVar.b.setBackground(drawable);
            }
        } else {
            eVar.b.setVisibility(8);
        }
        if (rdrVar.b() == 0) {
            e(eVar, rdrVar);
        } else {
            eVar.d.setVisibility(0);
            eVar.d.setBackgroundResource(R.drawable._2131430483_res_0x7f0b0c53);
        }
    }

    private void e(e eVar, rdr rdrVar) {
        if (rdrVar.h() == 1) {
            eVar.d.setBackgroundResource(R.drawable._2131430320_res_0x7f0b0bb0);
            eVar.d.setVisibility(0);
        } else {
            eVar.d.setVisibility(8);
        }
    }

    private void dKa_(e eVar, View view) {
        eVar.i = (LinearLayout) view.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_data_layout);
        eVar.h = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_data_text);
        eVar.g = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_data_unit);
        eVar.j = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_left_date_text);
        eVar.t = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_center_text);
        eVar.e = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_right_text);
        eVar.k = (ImageView) view.findViewById(R.id.hw_show_health_running_history_child_item_left_img);
        eVar.l = (ImageView) view.findViewById(R.id.hw_show_health_running_history_child_item_left_bg);
        eVar.s = (ImageView) view.findViewById(R.id.hw_show_health_running_history_track_type_img);
        eVar.c = (HealthDivider) view.findViewById(R.id.hw_show_main_layout_sport_bottom_image_interval);
        eVar.f = (RelativeLayout) view.findViewById(R.id.hw_show_health_running_history_child_item_layout);
        eVar.o = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_date);
        eVar.n = (HealthTextView) view.findViewById(R.id.hw_show_health_running_history_child_item_chief_sport_type_text);
        if (LanguageUtil.ai(this.e)) {
            eVar.i.setLayoutDirection(1);
        }
        eVar.f10303a = (HealthTextView) view.findViewById(R.id.hw_health_sport_item_accessory_sport_data_unit);
        eVar.d = (ImageView) view.findViewById(R.id.hw_health_sport_history_child_abnormal_track);
        eVar.b = (ImageView) view.findViewById(R.id.hw_health_sport_history_child_ai_track);
        eVar.m = (ImageView) view.findViewById(R.id.hw_show_health_running_history_workout_origin_img);
    }

    private void a(e eVar, rdr rdrVar) {
        if (eVar == null || rdrVar == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "viewholder or data is null");
            return;
        }
        c(eVar);
        eVar.g.setVisibility(0);
        eVar.j.setText(d(0, rdrVar.v(), 1));
        eVar.t.setText(d(1, rdrVar.k(), 1));
        c(eVar, rdrVar);
        eVar.o.setText(d(2, rdrVar.v(), 1));
        int i = rdrVar.i();
        if (i == 0) {
            eVar.s.setVisibility(8);
        } else {
            eVar.s.setVisibility(0);
            d(eVar, rdrVar, i);
        }
        b(eVar, rdrVar);
        d(eVar, rdrVar);
    }

    private void c(e eVar, rdr rdrVar) {
        eVar.e.setVisibility(0);
        eVar.f10303a.setVisibility(0);
        eVar.t.setVisibility(0);
        if (rdrVar.c() == 0) {
            if (rdrVar.x() == 262 || rdrVar.x() == 266) {
                i(eVar, rdrVar);
            } else if (rdrVar.x() == 260) {
                f(eVar, rdrVar);
            } else if (rdrVar.ai() != null || !TextUtils.isEmpty(rdrVar.af())) {
                d(rdrVar.ai(), eVar, rdrVar);
            } else {
                h(eVar, rdrVar);
            }
            eVar.f10303a.setText(b(this.e, rdrVar.x()));
            return;
        }
        if (rdrVar.c() == 3) {
            o(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 4) {
            s(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 5) {
            g(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 6) {
            n(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 7) {
            q(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 8) {
            j(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 9) {
            t(eVar, rdrVar);
            return;
        }
        if (rdrVar.c() == 10) {
            l(eVar, rdrVar);
        } else if (rdrVar.c() == 11) {
            p(eVar, rdrVar);
        } else {
            k(eVar, rdrVar);
        }
    }

    private void j(e eVar, rdr rdrVar) {
        String quantityString;
        double d2;
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "setChiefDataTypeDiving");
        if (UnitUtil.h()) {
            quantityString = this.e.getResources().getQuantityString(R.plurals._2130903306_res_0x7f03010a, (int) UnitUtil.e(rdrVar.d(), 1), "");
        } else {
            quantityString = this.e.getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, (int) rdrVar.d(), "");
        }
        if (rdrVar.x() == 291) {
            eVar.n.setText(cat.a(rdrVar.aa(), this.e));
        }
        if (UnitUtil.h()) {
            d2 = UnitUtil.e(rdrVar.d(), 1);
        } else {
            d2 = rdrVar.d();
        }
        String b = cat.b(d2, 1);
        eVar.e.setText(this.e.getResources().getQuantityString(R.plurals._2130903347_res_0x7f030133, (int) rdrVar.e(), UnitUtil.e((int) rdrVar.e(), 1, 0)));
        eVar.h.setText(b);
        eVar.g.setText(quantityString);
        eVar.f10303a.setText("");
    }

    private void t(e eVar, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "setChiefDataTypeTrain");
        if (rdrVar.e() == 0.0f) {
            eVar.t.setText(this.e.getResources().getQuantityString(R.plurals._2130903131_res_0x7f03005b, 0, this.b));
        } else {
            eVar.t.setText(this.e.getResources().getQuantityString(R.plurals._2130903131_res_0x7f03005b, (int) rdrVar.e(), UnitUtil.e(rdrVar.e(), 1, 0)));
        }
        eVar.h.setText(d(1, rdrVar.k(), 1));
        eVar.g.setText("");
        eVar.f10303a.setText("");
        eVar.e.setText("");
    }

    private void q(e eVar, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "setChiefDataTypeSkipping");
        String af = rdrVar.af();
        if (!TextUtils.isEmpty(af)) {
            eVar.n.setText(af);
        }
        eVar.e.setText(UnitUtil.e((int) rdrVar.e(), 1, 0));
        eVar.h.setText(UnitUtil.e((int) rdrVar.d(), 1, 0));
        eVar.g.setText(this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, (int) rdrVar.d(), ""));
        eVar.f10303a.setText(this.e.getResources().getText(R$string.IDS_indoor_skipper_number_minute));
    }

    private void d(String str, e eVar, rdr rdrVar) {
        String d2 = gwg.d(this.e, str, rdrVar.af());
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "courseName is null,courseId= ", str);
            h(eVar, rdrVar);
            return;
        }
        eVar.n.setText(d2);
        eVar.g.setText(cat.c(this.e, rdrVar.d()));
        eVar.h.setText(c(rdrVar.s(), 0, rdrVar.d() / 1000.0d, 1));
        eVar.f10303a.setVisibility(8);
        eVar.e.setVisibility(8);
        if (this.f10300a == 10001) {
            return;
        }
        eVar.e.setVisibility(0);
        eVar.f10303a.setVisibility(0);
        if (!d(rdrVar.x())) {
            eVar.e.setText(c(rdrVar.s(), 1, rdrVar.e(), 1));
        } else if (rdrVar.e() != 0.0f) {
            eVar.e.setText(c(rdrVar.s(), 5, (1.0f / rdrVar.e()) * 3600.0f, 1));
        } else {
            eVar.e.setText(this.b);
        }
    }

    private void d(e eVar, rdr rdrVar, int i) {
        Drawable drawable = this.e.getResources().getDrawable(i);
        if (i != R.drawable._2131429997_res_0x7f0b0a6d && i != R.drawable._2131430489_res_0x7f0b0c59 && i != R.drawable._2131430488_res_0x7f0b0c58 && i != R.drawable._2131430791_res_0x7f0b0d87) {
            eVar.s.setBackground(nrf.cJH_(drawable, this.e.getResources().getColor(R.color._2131297781_res_0x7f0905f5)));
        } else {
            eVar.s.setBackgroundResource(i);
        }
    }

    private void b(e eVar, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", " workOutOriginType：", Integer.valueOf(rdrVar.w()));
        if (rdrVar.w() == 221) {
            eVar.m.setVisibility(0);
        } else {
            eVar.m.setVisibility(8);
        }
    }

    private void d(e eVar, rdr rdrVar) {
        Drawable dMv_ = rdrVar.dMv_(this.e);
        Drawable Cw_ = cat.Cw_(this.e, dMv_, rdrVar.s());
        if (rdrVar.s() != 512) {
            dMv_ = nrf.cJH_(dMv_, this.e.getColor(R.color._2131296937_res_0x7f0902a9));
        }
        int e2 = rdrVar.e(this.e);
        if (e2 == 0) {
            e2 = this.e.getColor(R.color._2131296927_res_0x7f09029f);
        }
        Drawable cJH_ = nrf.cJH_(this.e.getDrawable(R.drawable._2131430592_res_0x7f0b0cc0), e2);
        if (!TextUtils.isEmpty(rdrVar.q()) && "7".equals(rdrVar.q())) {
            Cw_ = this.e.getDrawable(R.drawable._2131430162_res_0x7f0b0b12);
            dMv_ = Cw_;
        }
        if (rdrVar.s() != 512) {
            cat.Cx_(eVar.k, Cw_, dMv_);
            eVar.k.setVisibility(0);
            eVar.l.setBackground(cJH_);
        } else {
            eVar.k.setVisibility(8);
            cat.Cx_(eVar.l, Cw_, dMv_);
        }
    }

    private void l(e eVar, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "setChiefDataTypePaddle");
        eVar.h.setText(c(rdrVar.s(), 2, rdrVar.d(), 1));
        eVar.g.setText(this.e.getString(R$string.IDS_track_num_total_calorie_kcal, ""));
        eVar.e.setText(UnitUtil.e((int) rdrVar.e(), 1, 0));
        eVar.f10303a.setText(this.e.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, (int) rdrVar.e()));
    }

    private void p(e eVar, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryExpandableListAdapter", "setChiefDataTypeTime");
        eVar.h.setText(gvv.c((long) rdrVar.d(), R.style.sport_day_hour_min_num_20dp, R.style.sport_day_hour_min_unit));
        eVar.g.setVisibility(8);
        eVar.e.setText(c(rdrVar.s(), 0, rdrVar.e() / 1000.0d, 1));
        eVar.f10303a.setText(cat.c(this.e, rdrVar.e()));
        eVar.t.setVisibility(8);
    }

    private void k(e eVar, rdr rdrVar) {
        if (rdrVar.s() == 290) {
            m(eVar, rdrVar);
            return;
        }
        eVar.h.setText(c(rdrVar.s(), 2, rdrVar.d(), 1));
        eVar.g.setText(this.e.getString(R$string.IDS_track_num_total_calorie_kcal, ""));
        if (rdrVar.c() == 1) {
            eVar.e.setText(c(rdrVar.s(), 3, rdrVar.e(), 1));
            eVar.f10303a.setText(R$string.IDS_main_watch_heart_rate_unit_string);
        } else if (rdrVar.c() != 2) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "not has setChiefDataTypeOthers", Integer.valueOf(rdrVar.c()));
        } else {
            eVar.e.setVisibility(4);
            eVar.f10303a.setVisibility(4);
        }
    }

    private void s(e eVar, rdr rdrVar) {
        if (rdrVar.e() != 0.0f) {
            eVar.e.setText(c(rdrVar.s(), 2, rdrVar.a(), 1));
        } else {
            eVar.e.setText(this.b);
        }
        eVar.f10303a.setText(this.e.getString(R$string.IDS_band_data_sport_energy_unit));
        eVar.h.setText(j(rdrVar.d()));
        eVar.g.setText(cat.c(this.e, rdrVar.d()));
    }

    private void g(e eVar, rdr rdrVar) {
        String format;
        eVar.e.setText(UnitUtil.e((int) rdrVar.e(), 1, 0));
        String string = this.e.getString(R$string.IDS_aw_version2_show_score);
        String string2 = this.e.getString(R$string.IDS_aw_version2_basketball);
        if (LanguageUtil.b(this.e)) {
            format = String.format(this.e.getString(R$string.IDS_sport_history_run_course_name), string, string2);
        } else {
            format = String.format(this.e.getString(R$string.IDS_sport_history_run_course_name), string2, string);
        }
        eVar.n.setText(format);
        eVar.h.setText(UnitUtil.e((int) rdrVar.d(), 1, 0));
        eVar.g.setText(String.format(this.e.getResources().getQuantityString(R.plurals._2130903221_res_0x7f0300b5, (int) rdrVar.d()), ""));
        eVar.f10303a.setText(this.e.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, (int) rdrVar.e()));
    }

    private void n(e eVar, rdr rdrVar) {
        eVar.h.setText(cat.b(rdrVar.d(), 0));
        if (rdrVar.s() == 286) {
            String o = rdrVar.o();
            if (StringUtils.a(o)) {
                o = cat.b(Double.parseDouble(o), 0);
            }
            eVar.h.setText(String.format(Locale.ROOT, "%s(%s)", cat.b(rdrVar.d(), 0), o));
            LogUtil.a("Track_SportHistoryExpandableListAdapter", "golfCourse ChiefDataValue :" + rdrVar.d() + ", golfCourse ParValue :" + rdrVar.o());
        }
        eVar.g.setText(this.e.getResources().getQuantityString(R.plurals._2130903269_res_0x7f0300e5, (int) rdrVar.d(), ""));
        eVar.e.setText(c(rdrVar.s(), 2, rdrVar.a(), 1));
        eVar.f10303a.setText(this.e.getString(R$string.IDS_band_data_sport_energy_unit));
    }

    private void m(e eVar, rdr rdrVar) {
        String z = rdrVar.z();
        if (TextUtils.isEmpty(z)) {
            z = gwg.e(this.e, rdrVar.s());
        }
        eVar.n.setText(z);
        eVar.e.setVisibility(8);
        eVar.f10303a.setVisibility(8);
        eVar.g.setText(this.e.getString(R$string.IDS_track_num_total_calorie_kcal, ""));
        eVar.h.setText(c(rdrVar.s(), 2, rdrVar.d(), 1));
    }

    private void o(e eVar, rdr rdrVar) {
        Drawable drawable;
        eVar.n.setText(rdrVar.ac());
        if ((rdrVar.p() == 1 || rdrVar.p() == 2) && (drawable = ContextCompat.getDrawable(this.e, R.drawable._2131427535_res_0x7f0b00cf)) != null) {
            drawable.setBounds(0, 0, nsn.c(this.e, 19.0f), nsn.c(this.e, 13.0f));
            eVar.n.setCompoundDrawablePadding(nsn.c(this.e, 8.0f));
            eVar.n.setCompoundDrawables(null, null, drawable, null);
        }
        eVar.e.setVisibility(8);
        eVar.f10303a.setVisibility(8);
        eVar.g.setText(this.e.getString(R$string.IDS_track_num_total_calorie_kcal, ""));
        eVar.h.setText(c(rdrVar.s(), 2, rdrVar.d(), 1));
    }

    private void h(e eVar, rdr rdrVar) {
        eVar.h.setText(c(rdrVar.s(), 0, rdrVar.d() / 1000.0d, 1));
        eVar.g.setText(cat.c(this.e, rdrVar.d()));
        if (!d(rdrVar.x())) {
            eVar.e.setText(c(rdrVar.s(), 1, rdrVar.e(), 1));
            return;
        }
        if (rdrVar.e() != 0.0f) {
            if (kxb.c(rdrVar.x())) {
                eVar.e.setText(c(rdrVar.s(), 5, rdrVar.e(), 1));
                return;
            } else {
                eVar.e.setText(c(rdrVar.s(), 5, (1.0f / rdrVar.e()) * 3600.0f, 1));
                return;
            }
        }
        eVar.e.setText(this.b);
    }

    private void f(e eVar, rdr rdrVar) {
        eVar.h.setText(c(rdrVar.s(), 0, rdrVar.d() / 1000.0d, 1));
        eVar.g.setText(cat.c(this.e, rdrVar.d()));
        if (rdrVar.d() <= 0.0f) {
            eVar.h.setText(this.b);
        }
        if (rdrVar.e() <= 0.0f) {
            eVar.e.setText(this.b);
        } else {
            eVar.e.setText(a(rdrVar.e()));
        }
    }

    private void i(e eVar, rdr rdrVar) {
        eVar.h.setText(e(rdrVar.d()));
        eVar.g.setText(d(this.e, (int) rdrVar.d()));
        if (rdrVar.d() < 1.0f) {
            eVar.h.setText(this.b);
        }
        eVar.e.setText(b(rdrVar.e()));
    }

    private boolean d(int i) {
        return i == 259 || i == 265 || i == 273 || kxb.a(i);
    }

    private void c(e eVar) {
        if (eVar.e.getVisibility() == 4) {
            eVar.e.setVisibility(0);
            eVar.f10303a.setVisibility(0);
        }
        if (eVar.d.getVisibility() == 4) {
            eVar.d.setVisibility(0);
        }
        if (eVar.b.getVisibility() == 4) {
            eVar.b.setVisibility(0);
        }
        if (eVar.s.getVisibility() == 4) {
            eVar.s.setVisibility(0);
        }
    }

    private String c(int i, int i2, double d2, int i3) {
        String e2 = e(i2, d2, i3);
        if (e2 != null) {
            return e2;
        }
        switch (i2) {
            case 0:
            case 10:
                return d(d2);
            case 1:
                if (d2 > 360000.0d || d2 <= 3.6d) {
                    return this.b;
                }
                return gvv.a((float) kpj.a(i, true, 3, d2));
            case 2:
                return cat.b(d2 / 1000.0d, 0);
            case 3:
                return cat.b(d2, 0);
            case 4:
            case 6:
            case 8:
            case 9:
            case 11:
                return cat.d(d2);
            case 5:
                return UnitUtil.e(kpj.d(false, 3, d2), 1, 2);
            case 7:
                return b((int) d2);
            default:
                return this.b;
        }
    }

    private String d(double d2) {
        if (!UnitUtil.h()) {
            if (d2 < 0.005d) {
                return this.b;
            }
            if (this.f10300a == 262) {
                return cat.b(d2, 0);
            }
            return cat.b(d2, 2);
        }
        if (this.f10300a == 262) {
            double e2 = UnitUtil.e(d2, 2);
            if (e2 < 0.005d) {
                return this.b;
            }
            return cat.b(e2, 0);
        }
        double e3 = UnitUtil.e(d2, 3);
        if (e3 < 0.005d) {
            return this.b;
        }
        return cat.b(e3, 2);
    }

    private String e(int i, double d2, int i2) {
        if (d2 != 0.0d && !cat.e(d2)) {
            return null;
        }
        if (i2 == 1) {
            return this.b;
        }
        if (i == 0 && this.f10300a != 262) {
            return UnitUtil.e(0.0d, 1, 2);
        }
        return UnitUtil.e(0.0d, 1, 0);
    }

    private String e(double d2) {
        if (UnitUtil.h()) {
            return cat.b(UnitUtil.e(d2, 2), 0);
        }
        return cat.b(d2, 0);
    }

    private String j(double d2) {
        if (!UnitUtil.h()) {
            if (d2 < 0.005d) {
                return this.b;
            }
            return cat.b(d2 / 1000.0d, 2);
        }
        double e2 = UnitUtil.e(d2 / 1000.0d, 3);
        if (e2 < 0.005d) {
            return this.b;
        }
        return cat.b(e2, 2);
    }

    private String a(double d2) {
        if (UnitUtil.h()) {
            return cat.b(UnitUtil.e(d2, 1), 2);
        }
        return cat.b(d2, 1);
    }

    private String d(Context context, int i) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "acquireSumFitUnit  and context is null");
            return " ";
        }
        if (UnitUtil.h()) {
            return " " + context.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, i);
        }
        return context.getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, i, "");
    }

    private String b(double d2) {
        float f = ((float) d2) / 10.0f;
        double d3 = f;
        if (d3 > 360000.0d || d3 <= 3.6d) {
            return this.b;
        }
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(d3, 2);
        }
        return gvv.a(f);
    }

    private String d(int i, long j, int i2) {
        String e2 = e(i, j, i2);
        if (e2 != null) {
            return e2;
        }
        if (i == 0) {
            if (LanguageUtil.bb(this.e) || LanguageUtil.aw(this.e)) {
                return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "HH:mm")).format(Long.valueOf(j));
            }
            return nsj.c(this.e, j, 1);
        }
        if (i == 1) {
            return UnitUtil.d((int) (j / 1000));
        }
        if (i == 2) {
            return DateUtils.formatDateTime(this.e, j, 24);
        }
        return this.b;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:202)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private java.lang.String b(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 262(0x106, float:3.67E-43)
            if (r3 == r0) goto L2f
            r0 = 273(0x111, float:3.83E-43)
            if (r3 == r0) goto L2a
            r0 = 274(0x112, float:3.84E-43)
            if (r3 == r0) goto L25
            switch(r3) {
                case 217: goto L2a;
                case 218: goto L2a;
                case 219: goto L2a;
                default: goto Lf;
            }
        Lf:
            switch(r3) {
                case 257: goto L20;
                case 258: goto L20;
                case 259: goto L2a;
                case 260: goto L1b;
                default: goto L12;
            }
        L12:
            switch(r3) {
                case 264: goto L20;
                case 265: goto L2a;
                case 266: goto L2f;
                default: goto L15;
            }
        L15:
            switch(r3) {
                case 280: goto L20;
                case 281: goto L20;
                case 282: goto L20;
                default: goto L18;
            }
        L18:
            java.lang.String r2 = ""
            goto L33
        L1b:
            java.lang.String r2 = r1.d(r2)
            goto L33
        L20:
            java.lang.String r2 = defpackage.cat.e(r2)
            goto L33
        L25:
            java.lang.String r2 = r1.c(r2)
            goto L33
        L2a:
            java.lang.String r2 = r1.b(r2)
            goto L33
        L2f:
            java.lang.String r2 = r1.e(r2)
        L33:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.adapter.SportHistoryExpandableListAdapter.b(android.content.Context, int):java.lang.String");
    }

    private String d(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "acquireClimHillUnit and context is null");
            return "";
        }
        if (!UnitUtil.h()) {
            return context.getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        }
        return context.getResources().getString(R$string.IDS_ft);
    }

    private String e(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "acquire Swim Unit and context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
    }

    private String c(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryExpandableListAdapter", "acquire Swim Unit and context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
    }

    private String b(Context context) {
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getString(R$string.IDS_motiontrack_show_detail_average_speed_imp);
        }
        return context.getResources().getString(R$string.IDS_motiontrack_show_detail_average_speed);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10302a;
        private HealthTextView b;
        private ImageView c;
        private LinearLayout d;
        private HealthTextView e;
        private LinearLayout f;
        private MonthTitleContainer g;
        private HealthTextView h;
        private LinearLayout i;
        private LinearLayout j;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10303a;
        private ImageView b;
        private HealthDivider c;
        private ImageView d;
        private HealthTextView e;
        private RelativeLayout f;
        private HealthTextView g;
        private HealthTextView h;
        private LinearLayout i;
        private HealthTextView j;
        private ImageView k;
        private ImageView l;
        private ImageView m;
        private HealthTextView n;
        private HealthTextView o;
        private ImageView s;
        private HealthTextView t;
    }

    class c {
        private Context b;
        private List<MonthTitleItem.e> c;
        private rdo e;

        private c(Context context, rdo rdoVar) {
            this.b = context;
            this.e = rdoVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(MonthTitleContainer monthTitleContainer, boolean z, int i) {
            if (SportHistoryExpandableListAdapter.this.c == null) {
                LogUtil.a("SportHistoryTitleDrawer", "drawMonthHistoryTitle ", "mGroupData is null");
                return;
            }
            List<MonthTitleItem.e> list = this.c;
            if (list == null) {
                this.c = new ArrayList(16);
            } else {
                list.clear();
            }
            if (!z) {
                d(SportHistoryExpandableListAdapter.this.f10300a, false);
            } else {
                a(monthTitleContainer);
            }
            c(monthTitleContainer, i);
        }

        private void a(MonthTitleContainer monthTitleContainer) {
            List<String> b = this.e.b();
            if (b == null) {
                LogUtil.b("SportHistoryTitleDrawer", "allRequestString is null");
                return;
            }
            b.remove("Track_Count_Sum");
            if (b.size() == 0) {
                LogUtil.b("SportHistoryTitleDrawer", "allRequestString is null");
                return;
            }
            List<HwSportTypeInfo> d = hln.c(this.b).d(b);
            if (koq.b(d)) {
                monthTitleContainer.setVisibility(8);
                LogUtil.b("SportHistoryTitleDrawer", "buildAllCategoryItems requestInfo == null ");
                return;
            }
            e(b, d);
            boolean z = true;
            boolean z2 = true;
            for (HwSportTypeInfo hwSportTypeInfo : d) {
                if (hwSportTypeInfo.getSportTypeId() == 220 || hwSportTypeInfo.getSportTypeId() == 286) {
                    if (z) {
                        e(286);
                        z = false;
                    }
                } else if (hwSportTypeInfo.getSportTypeId() != 287 && hwSportTypeInfo.getSportTypeId() != 291) {
                    e(d(hwSportTypeInfo, b), hwSportTypeInfo);
                } else if (z2) {
                    e(287);
                    z2 = false;
                }
            }
        }

        private void e(int i) {
            HwSportTypeInfo d = hln.c(this.b).d(i);
            e(d.getHistoryList().mainPositionData(false), d);
        }

        private void e(List<String> list, List<HwSportTypeInfo> list2) {
            HwSportTypeInfo hwSportTypeInfo;
            for (Integer num : SportHistoryExpandableListAdapter.d) {
                Iterator<HwSportTypeInfo> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        hwSportTypeInfo = it.next();
                        if (num.equals(Integer.valueOf(hwSportTypeInfo.getSportTypeId()))) {
                            break;
                        }
                    } else {
                        hwSportTypeInfo = null;
                        break;
                    }
                }
                if (hwSportTypeInfo != null && list.contains(rdu.c(num.intValue(), 5)) && list.contains(rdu.c(num.intValue(), 4))) {
                    list2.remove(list2.lastIndexOf(hwSportTypeInfo));
                }
            }
        }

        private void e(String str, HwSportTypeInfo hwSportTypeInfo) {
            MonthTitleItem.e a2 = rck.a(str, this.e.e(), hwSportTypeInfo, this.b);
            if (a2 == null) {
                LogUtil.h("SportHistoryTitleDrawer", "buildItem itemData is null");
            } else {
                this.c.add(a2);
            }
        }

        private void d(int i, boolean z) {
            hln c = hln.c(this.b);
            LogUtil.a("SportHistoryTitleDrawer", " mSportType= ", Integer.valueOf(i));
            HwSportTypeInfo d = c.d(i);
            if (d == null) {
                LogUtil.b("SportHistoryTitleDrawer", " hwSportTypeInfo == null ");
                return;
            }
            ArrayList<String> allMonthlyType = d.getHistoryList().getAllMonthlyType();
            if (allMonthlyType == null) {
                LogUtil.b("SportHistoryTitleDrawer", " config == null ");
                return;
            }
            for (int i2 = 0; i2 < 3 && i2 < allMonthlyType.size(); i2++) {
                if (allMonthlyType.get(i2) != null) {
                    if (z) {
                        e(a(allMonthlyType.get(i2)), d);
                    } else if (!allMonthlyType.get(i2).equals(HealthDataStatPolicy.COUNT) || d.getSportTypeId() != 287) {
                        e(b(allMonthlyType.get(i2)), d);
                    }
                }
            }
        }

        private String a(String str) {
            return "METER_DISTANCE".equals(str) ? a() : str;
        }

        private String d(HwSportTypeInfo hwSportTypeInfo, List<String> list) {
            if (hwSportTypeInfo == null) {
                return PlayInfo.KEY_DURATION;
            }
            if (SportHistoryExpandableListAdapter.this.d(list, hwSportTypeInfo.getSportTypeId())) {
                return HealthDataStatPolicy.COUNT;
            }
            if (hwSportTypeInfo.getSportTypeId() == 262) {
                return a();
            }
            return koq.b(hwSportTypeInfo.getHistoryList().getMonthlyStatisticsData()) ? PlayInfo.KEY_DURATION : hwSportTypeInfo.getHistoryList().mainPositionData(false);
        }

        private String a() {
            return (!gvv.b(this.e.n()) || this.e.o() <= 0) ? "METER_DISTANCE" : PlayInfo.KEY_DURATION;
        }

        private String b(String str) {
            return "METER_DISTANCE".equals(str) ? (!gvv.b(this.e.n()) || this.e.o() <= 0) ? "FIRST_METER_DISTANCE" : "FIRST_DURATION" : PlayInfo.KEY_DURATION.equals(str) ? "FIRST_DURATION" : "DISTANCE".equals(str) ? "FIRST_DISTANCE" : "BASKETBALL_COUNT".equals(str) ? "TIME" : str;
        }

        private void c(MonthTitleContainer monthTitleContainer, int i) {
            if (koq.b(this.c)) {
                LogUtil.a("SportHistoryTitleDrawer", "fillTitleContainer mItemDataList is null");
                return;
            }
            if (monthTitleContainer == null) {
                LogUtil.a("SportHistoryTitleDrawer", "fillTitleContainer container is null");
                return;
            }
            monthTitleContainer.removeAllViews();
            Object systemService = this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (!(systemService instanceof WindowManager)) {
                LogUtil.b("SportHistoryTitleDrawer", "object is invalid type");
                return;
            }
            int size = this.c.size();
            monthTitleContainer.b(3);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((((WindowManager) systemService).getDefaultDisplay().getWidth() - (i * 2)) - (((Integer) BaseActivity.getSafeRegionWidth().first).intValue() * 2)) / 3, -2);
            layoutParams.gravity = 7;
            MonthTitleItem monthTitleItem = new MonthTitleItem(this.b);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int c = monthTitleItem.c(makeMeasureSpec, makeMeasureSpec);
            for (int i2 = 0; i2 < size; i2++) {
                MonthTitleItem monthTitleItem2 = new MonthTitleItem(this.b);
                monthTitleItem2.setLinearLayoutParams(layoutParams);
                MonthTitleItem.e eVar = this.c.get(i2);
                monthTitleItem2.setValueUnitMaxLine(2);
                monthTitleItem2.setItemView(eVar);
                monthTitleItem2.setValueHeight(c);
                monthTitleItem2.setValueGravity(80);
                monthTitleContainer.addView(monthTitleItem2);
                if (SportHistoryExpandableListAdapter.this.e(3, i2, this.c)) {
                    monthTitleContainer.addView(SportHistoryExpandableListAdapter.this.dJZ_(19));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(List<String> list, int i) {
        return d.contains(Integer.valueOf(i)) && list.contains(rdu.c(i, 5)) && !list.contains(rdu.c(i, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i, int i2, List<MonthTitleItem.e> list) {
        return ((i2 + 1) % i == 0 || i2 == list.size() - 1) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LinearLayout dJZ_(int i) {
        ImageView imageView = new ImageView(this.e);
        float c2 = nsn.c();
        imageView.setBackgroundColor(this.e.getResources().getColor(R.color._2131298738_res_0x7f0909b2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.e.getResources().getDimensionPixelSize(R.dimen._2131363715_res_0x7f0a0783), (int) (this.e.getResources().getDimensionPixelSize(R.dimen._2131363699_res_0x7f0a0773) * c2));
        float f = i * c2;
        layoutParams.setMargins(0, nsn.c(this.e, f), 0, nsn.c(this.e, f));
        imageView.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(this.e);
        linearLayout.addView(imageView);
        return linearLayout;
    }

    public void dKd_(List<MonthTitleItem.e> list, LinearLayout linearLayout) {
        if (koq.b(list)) {
            LogUtil.a("Track_SportHistoryExpandableListAdapter", "fillTitleContainer mItemDataList is null");
            return;
        }
        if (linearLayout == null) {
            LogUtil.a("Track_SportHistoryExpandableListAdapter", "fillTitleContainer container is null");
            return;
        }
        linearLayout.removeAllViews();
        Object systemService = this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b("Track_SportHistoryExpandableListAdapter", "object is invalid type");
            return;
        }
        int width = ((WindowManager) systemService).getDefaultDisplay().getWidth();
        int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int size = list.size();
        int intValue = ((width - (dimensionPixelSize * 2)) - (((Integer) BaseActivity.getSafeRegionWidth().first).intValue() * 2)) / size;
        for (int i = 0; i < size; i++) {
            MonthTitleItem monthTitleItem = new MonthTitleItem(this.e);
            monthTitleItem.setLinearLayoutParams(new LinearLayout.LayoutParams(intValue, -2, 1.0f));
            MonthTitleItem.e eVar = koq.d(list, i) ? list.get(i) : null;
            monthTitleItem.setValueAutoTextSize(R.dimen._2131365075_res_0x7f0a0cd3);
            monthTitleItem.setValueAutoTextInfo(9, 1);
            monthTitleItem.setValueTypeFace(Typeface.createFromAsset(this.e.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
            monthTitleItem.setValueUnitMaxLine(2);
            monthTitleItem.setItemView(eVar);
            if (monthTitleItem.getValueUnitTextWidth() < intValue || nsn.t()) {
                monthTitleItem.setDefaultHeight(48);
            }
            monthTitleItem.setValueLineSpacing(false);
            linearLayout.addView(monthTitleItem);
            if (e(size, i, list)) {
                linearLayout.addView(dJZ_(11));
            }
        }
    }
}
