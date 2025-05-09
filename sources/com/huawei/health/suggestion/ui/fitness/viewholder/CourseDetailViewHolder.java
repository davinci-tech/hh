package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.adapter.FitnessCourseHorizontalAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.arx;
import defpackage.koq;
import defpackage.mmz;
import defpackage.nrf;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CourseDetailViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f3201a;
    private HealthSubHeader aa;
    private HealthSubHeader ad;
    private HealthRecycleView b;
    private Context c;
    private HealthSubHeader d;
    private HealthSubHeader e;
    private FitnessCourseHorizontalAdapter f = new FitnessCourseHorizontalAdapter();
    private LinearLayout g;
    private LinearLayout h;
    private ImageView i;
    private HealthSubHeader j;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private LinearLayout o;
    private LinearLayout p;
    private View q;
    private HealthSubHeader r;
    private LinearLayout s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthTextView z;

    public CourseDetailViewHolder(Context context) {
        context = context == null ? BaseApplication.e() : context;
        this.c = context;
        this.q = LayoutInflater.from(context).inflate(R.layout.sug_fitness_activity_detail_info, (ViewGroup) null);
        e();
    }

    private void e() {
        this.s = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_re_course_layout);
        this.p = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout0);
        this.k = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout1);
        this.m = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout2);
        this.n = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout3);
        this.h = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout4);
        this.g = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout5);
        this.o = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout6);
        LinearLayout linearLayout = (LinearLayout) this.q.findViewById(R.id.sug_detail_info_layout_course_partner);
        this.l = linearLayout;
        BaseActivity.setViewSafeRegion(false, this.p, this.k, this.m, this.n, this.h, this.g, this.o, linearLayout, this.q.findViewById(R.id.sug_detail_info_course_header));
        this.d = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_course_desc_title);
        this.j = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_course_type_title);
        this.ad = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_train_equipment_title);
        this.aa = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_train_advice_title);
        this.r = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_suit_people_title);
        this.f3201a = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_ban_people_title);
        this.e = (HealthSubHeader) this.q.findViewById(R.id.sug_detail_info_attention_title);
        this.i = (ImageView) this.q.findViewById(R.id.sug_iv_coach_intro_course_partner_img);
        this.d.setPaddingStartEnd(0.0f, 0.0f);
        this.j.setPaddingStartEnd(0.0f, 0.0f);
        this.ad.setPaddingStartEnd(0.0f, 0.0f);
        this.aa.setPaddingStartEnd(0.0f, 0.0f);
        this.r.setPaddingStartEnd(0.0f, 0.0f);
        this.f3201a.setPaddingStartEnd(0.0f, 0.0f);
        this.e.setPaddingStartEnd(0.0f, 0.0f);
        this.z = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text0);
        this.v = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text2);
        this.w = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text3);
        this.x = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text4);
        this.t = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text5);
        this.u = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_text6);
        this.y = (HealthTextView) this.q.findViewById(R.id.sug_detail_info_notice);
        this.b = (HealthRecycleView) this.q.findViewById(R.id.sug_recycleview_relative_course);
    }

    public void c() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(arx.b());
        linearLayoutManager.setOrientation(0);
        this.b.setLayoutManager(linearLayoutManager);
        this.b.setAdapter(this.f);
    }

    public void b(String str) {
        if ("".equals(str)) {
            return;
        }
        this.m.setVisibility(0);
        this.ad.setHeadTitleText(BaseApplication.e().getResources().getString(R.string._2130848509_res_0x7f022afd));
        this.v.setText(str);
    }

    public void d(Map<String, mmz> map) {
        if (map.containsKey("courseDesc")) {
            aFh_(map.get("courseDesc"), this.z, this.d, this.p);
        }
        if (map.containsKey("trainingSuggest")) {
            aFh_(map.get("trainingSuggest"), this.w, this.aa, this.n);
        }
        if (map.containsKey("suitPeople")) {
            aFh_(map.get("suitPeople"), this.x, this.r, this.h);
        }
        if (map.containsKey("bannerPeople")) {
            aFh_(map.get("bannerPeople"), this.t, this.f3201a, this.g);
        }
        if (map.containsKey("attention")) {
            aFh_(map.get("attention"), this.u, this.e, this.o);
        }
    }

    private void aFh_(mmz mmzVar, HealthTextView healthTextView, HealthSubHeader healthSubHeader, LinearLayout linearLayout) {
        if (mmzVar == null) {
            LogUtil.h("Suggestion_CourseDetailViewHolder", "updateContent workoutExtendBean == null");
            return;
        }
        String a2 = mmzVar.a();
        String e = mmzVar.e();
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(e)) {
            LogUtil.h("Suggestion_CourseDetailViewHolder", "updateContent content title == null");
            return;
        }
        if (linearLayout == null || healthTextView == null || healthSubHeader == null) {
            LogUtil.h("Suggestion_CourseDetailViewHolder", "updateContent view == null");
            return;
        }
        linearLayout.setVisibility(0);
        healthSubHeader.setHeadTitleText(mmzVar.e());
        healthTextView.setText(mmzVar.a());
    }

    public void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            nrf.cJB_(str, this.i);
        } else {
            this.l.setVisibility(8);
        }
    }

    public View aFi_() {
        return this.q;
    }

    public void d(int i) {
        View view = this.q;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public void aFj_(SpannableString spannableString) {
        if (spannableString == null) {
            return;
        }
        this.y.setText(spannableString);
        this.y.setMovementMethod(LinkMovementMethod.getInstance());
        this.y.setHighlightColor(BaseApplication.e().getResources().getColor(android.R.color.transparent));
        this.y.setVisibility(0);
    }

    public void c(List<FitWorkout> list) {
        if (koq.c(list)) {
            this.s.setVisibility(0);
            this.f.d(list);
        }
    }

    public void b() {
        this.s.setVisibility(8);
        this.p.setVisibility(8);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.o.setVisibility(8);
    }
}
