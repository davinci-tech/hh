package com.huawei.ui.main.stories.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.adapter.MyCourseAdapter;
import defpackage.gge;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class MyCourseAdapter extends AdapterWithBottomView {
    private LayoutInflater b;
    private Context c;
    private boolean d;
    private Map<Integer, String> e;

    @Override // com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView
    public int getContentItem() {
        return 3;
    }

    @Override // com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView
    public int getContentItemType() {
        return 0;
    }

    public MyCourseAdapter(Context context) {
        super(context);
        this.e = new HashMap(3);
        this.d = false;
        this.b = LayoutInflater.from(context);
        this.c = context;
    }

    public void b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.e.put(Integer.valueOf(i), str);
        if (!(this.c instanceof Activity)) {
            LogUtil.h("MyCourseAdapter", "mContext instanceof Activity false");
        } else if (this.e.size() == 3) {
            LogUtil.a("MyCourseAdapter", "setData");
            ((Activity) this.c).runOnUiThread(new Runnable() { // from class: rhi
                @Override // java.lang.Runnable
                public final void run() {
                    MyCourseAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView
    public RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new b(this.b.inflate(R.layout.item_my_course, viewGroup, false));
    }

    @Override // com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView
    public void onBindChildViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof b) {
            b(i, (b) viewHolder);
        }
    }

    private void b(final int i, b bVar) {
        bVar.d.setSubHeaderBackgroundColor(ContextCompat.getColor(this.c, R.color._2131299296_res_0x7f090be0));
        final String a2 = a(i, bVar);
        a(a(i), bVar, i);
        bVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: rhh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyCourseAdapter.this.dOp_(i, a2, view);
            }
        });
    }

    public /* synthetic */ void dOp_(int i, String str, View view) {
        if (nsn.a(500)) {
            LogUtil.h("MyCourseAdapter", "view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(i, str);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a(String str, b bVar, int i) {
        if (TextUtils.isEmpty(str) || str.equals("invalidUrl")) {
            a(bVar, false);
            return;
        }
        if (str.contains("customImage")) {
            c(str, bVar);
            a(bVar, true);
        } else if (i == 0) {
            nrf.cIS_(bVar.g, str, nrf.c, 3, R.drawable._2131429871_res_0x7f0b09ef);
            a(bVar, true);
        } else {
            nrf.cIS_(bVar.g, str, nrf.e, 0, R.drawable._2131429871_res_0x7f0b09ef);
            a(bVar, true);
        }
    }

    private void a(int i, String str) {
        if (i == 0) {
            d();
            return;
        }
        if (i == 1) {
            c(str, 4);
        } else if (i == 2) {
            a();
            c(str, 7);
        } else {
            LogUtil.h("MyCourseAdapter", "itemView click, position = ", Integer.valueOf(i));
        }
    }

    private void c(String str, int i) {
        this.d = true;
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), "com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity");
        intent.putExtra("titleName", str);
        intent.putExtra("courseCategoryKey", i);
        intent.setFlags(268435456);
        gnm.aPB_(this.c, intent);
        b();
    }

    private void b() {
        Object obj = this.c;
        if (!(obj instanceof Activity)) {
            obj = com.huawei.haf.application.BaseApplication.wa_();
        }
        if (obj instanceof Activity) {
            ((Activity) obj).overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    private void d() {
        gge.e("1120020");
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi != null) {
            fitnessAdviceApi.launchMyPlanH5();
        }
    }

    private String a(int i, b bVar) {
        String string;
        String string2;
        String str;
        String str2;
        if (i == 0) {
            string = this.c.getString(R$string.sug_home_my_own_plans);
            string2 = this.c.getResources().getString(R$string.IDS_course_lightweight_status, b(R$string.IDS_hwh_home_group_underway), b(R$string.IDS_completed));
        } else if (i == 1) {
            string = this.c.getString(R$string.IDS_course_title_sport);
            String b2 = b(R$string.IDS_FitnessAdvice_load_ok);
            String b3 = b(R$string.IDS_FitnessAdvice_collected_ok);
            if (Utils.o()) {
                string2 = this.c.getResources().getString(R$string.IDS_course_lightweight_status, b2, b3);
            } else {
                string2 = this.c.getResources().getString(R$string.IDS_course_status, b2, b3, b(R$string.IDS_FitnessAdvice_purchase_ok));
            }
        } else {
            if (i == 2) {
                String string3 = this.c.getString(R$string.IDS_hw_sleep_relax);
                String string4 = this.c.getString(R$string.IDS_course_title_health);
                String b4 = b(R$string.IDS_course_used);
                String b5 = b(R$string.IDS_FitnessAdvice_collected_ok);
                if (Utils.o()) {
                    str = this.c.getResources().getString(R$string.IDS_course_lightweight_status, b4, b5);
                } else {
                    str = this.c.getResources().getString(R$string.IDS_course_status, b4, b5, b(R$string.IDS_FitnessAdvice_purchase_ok));
                }
                str2 = string3;
                string = string4;
                bVar.d.setHeadTitleText(string);
                bVar.f10369a.setText(str2);
                bVar.e.setText(str);
                return str2;
            }
            LogUtil.h("MyCourseAdapter", "position is not exists");
            string = "";
            string2 = null;
        }
        str = string2;
        str2 = string;
        bVar.d.setHeadTitleText(string);
        bVar.f10369a.setText(str2);
        bVar.e.setText(str);
        return str2;
    }

    private String b(int i) {
        return this.c.getString(i);
    }

    private void a() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 1);
        ixx.d().d(this.c, AnalyticsValue.INT_PLAN_2030103.value(), hashMap, 0);
    }

    private void c(String str, b bVar) {
        long e = e(str) % 3;
        nrf.cIP_(bVar.g, e == 0 ? R.drawable.pic_custom_yellow_small : e == 1 ? R.drawable.pic_custom_red_small : R.drawable.pic_custom_blue_small, nrf.e, 0, R.drawable._2131429871_res_0x7f0b09ef);
    }

    private void a(b bVar, boolean z) {
        if (LanguageUtil.bc(this.c)) {
            Context context = this.c;
            BitmapDrawable cKm_ = nrz.cKm_(context, context.getResources().getDrawable(R.drawable._2131430901_res_0x7f0b0df5));
            if (cKm_ != null) {
                bVar.c.setImageDrawable(cKm_);
            }
        }
        if (z) {
            bVar.g.setVisibility(0);
            bVar.c.setVisibility(0);
            bVar.b.setVisibility(8);
        } else {
            bVar.g.setVisibility(8);
            bVar.c.setVisibility(8);
            bVar.b.setVisibility(0);
        }
    }

    private String a(int i) {
        Map<Integer, String> map = this.e;
        return (map == null || i > map.size() || TextUtils.isEmpty(this.e.get(Integer.valueOf(i)))) ? "" : this.e.get(Integer.valueOf(i));
    }

    public boolean c() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10369a;
        ImageView b;
        ImageView c;
        HealthSubHeader d;
        HealthTextView e;
        ImageView g;

        b(View view) {
            super(view);
            this.d = (HealthSubHeader) view.findViewById(R.id.course_sub_header);
            this.f10369a = (HealthTextView) view.findViewById(R.id.course_title);
            this.e = (HealthTextView) view.findViewById(R.id.course_subtitle);
            this.b = (ImageView) view.findViewById(R.id.default_icon);
            this.c = (ImageView) view.findViewById(R.id.lamination_icon);
            this.g = (ImageView) view.findViewById(R.id.course_show_icon);
        }
    }

    public static String a(String str) {
        return "customImage" + str;
    }

    private long e(String str) {
        String[] split = str.split("customImage");
        if (split.length > 1) {
            return CommonUtils.g(split[1]);
        }
        LogUtil.h("MyCourseAdapter", "error id ", str);
        return 0L;
    }
}
