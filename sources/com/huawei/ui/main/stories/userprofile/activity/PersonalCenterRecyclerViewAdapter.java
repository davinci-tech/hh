package com.huawei.ui.main.stories.userprofile.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.stories.userprofile.plantabs.PlanViewPagerHolder;
import defpackage.eie;
import defpackage.koq;
import defpackage.mct;
import defpackage.mfg;
import defpackage.mlb;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.rzs;
import defpackage.say;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class PersonalCenterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f10526a;
    private Context d;
    private int f;
    private say g;
    private int j;
    private ImageView[] b = new ImageView[16];
    private boolean e = true;
    private List<String> i = new ArrayList(10);
    private List<rzs> c = new ArrayList(10);

    PersonalCenterRecyclerViewAdapter(Context context) {
        this.f10526a = LayoutInflater.from(context);
        this.d = context;
    }

    public void d(List<rzs> list) {
        if (list == null) {
            return;
        }
        if (koq.c(this.c)) {
            this.c.clear();
        }
        this.c = list;
        notifyDataSetChanged();
    }

    public void c(say sayVar) {
        this.g = sayVar;
    }

    public void c(rzs rzsVar) {
        int a2 = a(rzsVar);
        LogUtil.a("PersonalCenterRecyclerViewAdapter", "notifyItemChangeByData,position", Integer.valueOf(a2));
        if (a2 > -1) {
            notifyItemChanged(a2);
        }
    }

    public int a(rzs rzsVar) {
        if (rzsVar != null && rzsVar.a() != 0) {
            for (int i = 0; i < this.c.size(); i++) {
                if (this.c.get(i) != null && rzsVar.a() == this.c.get(i).a()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void c(boolean z) {
        this.e = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.c, i)) {
            return -1;
        }
        return this.c.get(i).f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new e(this.f10526a.inflate(R.layout.item_divider_layout, viewGroup, false));
        }
        if (i == 4 || i == 3) {
            return new a(this.f10526a.inflate(R.layout.fragment_personal_center_item_content, viewGroup, false));
        }
        if (i == 2) {
            return new d(this.f10526a.inflate(R.layout.user_profile_myreward_layout, viewGroup, false));
        }
        if (i == 6) {
            c cVar = new c(this.f10526a.inflate(R.layout.item_personal_center_banner_ad, viewGroup, false));
            cVar.a();
            return cVar;
        }
        if (i == 7) {
            return new b(this.f10526a.inflate(R.layout.item_personal_center_grid, viewGroup, false));
        }
        if (i == 8) {
            return new PlanViewPagerHolder(this.f10526a.inflate(R.layout.fragment_personal_center_item_plan, viewGroup, false));
        }
        LogUtil.h("PersonalCenterRecyclerViewAdapter", "onCreateViewHolder error type");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        rzs rzsVar = i < this.c.size() ? this.c.get(i) : null;
        if (rzsVar == null || viewHolder == null) {
            LogUtil.h("PersonalCenterRecyclerViewAdapter", "onBindViewHolder model or holder is null");
            return;
        }
        if (viewHolder instanceof e) {
            e eVar = (e) viewHolder;
            if (rzsVar.a() != 0) {
                eVar.c.setText(rzsVar.a());
                eVar.c.setVisibility(0);
                return;
            } else {
                eVar.c.setVisibility(8);
                return;
            }
        }
        if (viewHolder instanceof a) {
            b((a) viewHolder, rzsVar);
            return;
        }
        if (viewHolder instanceof d) {
            d((d) viewHolder, rzsVar);
            return;
        }
        if (viewHolder instanceof c) {
            c cVar = (c) viewHolder;
            cVar.b(rzsVar.i(), rzsVar);
            cVar.d.setOnClickListener(rzsVar.dUz_());
            if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
                LogUtil.h("PersonalCenterRecyclerViewAdapter", "onBindViewHolder Basic mode");
                cVar.c.setOnClickListener(new say.a(this.g));
                return;
            } else {
                LogUtil.h("PersonalCenterRecyclerViewAdapter", "onBindViewHolder Full mode");
                cVar.c.setOnClickListener(rzsVar.dUz_());
                return;
            }
        }
        if (viewHolder instanceof b) {
            ((b) viewHolder).e();
        } else if (viewHolder instanceof PlanViewPagerHolder) {
            ((PlanViewPagerHolder) viewHolder).c(rzsVar);
        } else {
            LogUtil.h("PersonalCenterRecyclerViewAdapter", "onBindViewHolder type error");
        }
    }

    private void d(d dVar, rzs rzsVar) {
        if (LanguageUtil.p(this.d)) {
            dVar.f10529a.setText(dVar.f10529a.getText().toString());
        }
        if (LanguageUtil.bc(this.d)) {
            dVar.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (nsn.s()) {
            dVar.f10529a.setLayoutParams(new LinearLayout.LayoutParams(this.d.getResources().getDimensionPixelOffset(R.dimen._2131363456_res_0x7f0a0680), -2));
            nsn.b(dVar.f10529a);
            nsn.b(dVar.m);
        }
        b(dVar);
        if (rzsVar.d() == null) {
            dVar.m.setText("");
            dVar.l.setVisibility(8);
            dVar.o.setVisibility(0);
            this.i.clear();
        } else if (rzsVar.d() instanceof List) {
            d(dVar, (List<String>) rzsVar.d());
        } else {
            LogUtil.h("PersonalCenterRecyclerViewAdapter", "medal data error");
        }
        if (rzsVar.l()) {
            dVar.e.setVisibility(0);
        } else {
            dVar.e.setVisibility(8);
        }
        dVar.itemView.setBackgroundResource(R.drawable.list_item_background_single_normal);
        dVar.itemView.setOnClickListener(rzsVar.dUz_());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<rzs> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private void b(a aVar, rzs rzsVar) {
        if (LanguageUtil.bc(this.d)) {
            aVar.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            aVar.h.setBackground(nrz.cKn_(this.d, rzsVar.e()));
        } else {
            aVar.h.setBackgroundResource(rzsVar.e());
        }
        aVar.f10527a.setText(rzsVar.a());
        if (rzsVar.j()) {
            aVar.j.setText(rzsVar.c());
        } else {
            aVar.j.setText("");
        }
        if (rzsVar.l()) {
            aVar.g.setVisibility(0);
        } else {
            aVar.g.setVisibility(8);
        }
        if (rzsVar.g()) {
            aVar.d.setVisibility(0);
        } else {
            aVar.d.setVisibility(8);
        }
        dUC_(aVar, rzsVar, aVar.itemView.getLayoutParams());
        if (rzsVar.m()) {
            aVar.e.setVisibility(0);
            aVar.b.setVisibility(8);
        } else {
            aVar.e.setVisibility(8);
            aVar.b.setVisibility(0);
        }
    }

    private void dUC_(a aVar, rzs rzsVar, ViewGroup.LayoutParams layoutParams) {
        if (rzsVar.i()) {
            layoutParams.width = -1;
            layoutParams.height = -2;
            if (rzsVar.b() == R.drawable.list_item_background_single_normal) {
                aVar.c.setVisibility(0);
                aVar.f.setVisibility(0);
            } else if (rzsVar.b() == R.drawable.list_item_background_bottom_normal) {
                aVar.c.setVisibility(0);
                aVar.f.setVisibility(8);
            } else if (rzsVar.b() == R.drawable.list_item_background_top_normal) {
                aVar.f.setVisibility(0);
                aVar.c.setVisibility(8);
            } else {
                aVar.f.setVisibility(8);
                aVar.c.setVisibility(8);
            }
            if (nsn.s()) {
                layoutParams.height = -2;
                nsn.b(aVar.f10527a);
            }
            aVar.itemView.setVisibility(0);
        } else {
            layoutParams.height = 0;
            layoutParams.width = 0;
            aVar.itemView.setVisibility(8);
        }
        if (rzsVar.j()) {
            aVar.j.setVisibility(0);
        } else {
            aVar.j.setVisibility(8);
        }
        aVar.itemView.setLayoutParams(layoutParams);
        aVar.itemView.setOnClickListener(rzsVar.dUz_());
        aVar.itemView.setBackgroundResource(rzsVar.b());
    }

    private void d(final d dVar, final List<String> list) {
        LogUtil.a("PersonalCenterRecyclerViewAdapter", "getRewardLayoutWith() enter.");
        if (dVar.n != null) {
            dVar.n.post(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterRecyclerViewAdapter.4
                @Override // java.lang.Runnable
                public void run() {
                    PersonalCenterRecyclerViewAdapter.this.j = dVar.n.getWidth();
                    PersonalCenterRecyclerViewAdapter personalCenterRecyclerViewAdapter = PersonalCenterRecyclerViewAdapter.this;
                    personalCenterRecyclerViewAdapter.f = nsn.c(personalCenterRecyclerViewAdapter.d, 55.0f);
                    LogUtil.a("PersonalCenterRecyclerViewAdapter", "getRewardLayoutWith(), medalWidth = ", Integer.valueOf(PersonalCenterRecyclerViewAdapter.this.j), "singleMedalWidth = ", Integer.valueOf(PersonalCenterRecyclerViewAdapter.this.f));
                    PersonalCenterRecyclerViewAdapter.this.d((List<String>) list, dVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<String> list, d dVar) {
        if (this.j == 0) {
            return;
        }
        if (list == null || a(list)) {
            HashMap hashMap = new HashMap(16);
            mlb.a(hashMap);
            int i = 0;
            if (koq.c(list)) {
                int size = list.size();
                int b2 = b(list, hashMap);
                dVar.m.setText(nsf.a(R.plurals._2130903256_res_0x7f0300d8, size, Integer.valueOf(size)));
                dVar.l.setVisibility(0);
                dVar.o.setVisibility(8);
                i = b2;
            }
            a(i, dVar);
        }
    }

    private int b(List<String> list, Map<String, Integer> map) {
        int i;
        String b2 = mct.b(this.d, "_medalPngStatusDownload");
        int c2 = c(list);
        if (!koq.b(this.i)) {
            this.i.clear();
        }
        int i2 = 0;
        for (String str : list) {
            Bitmap cko_ = mfg.b("", str, this.d, b2) ? mlb.cko_(str, true, true) : null;
            if (cko_ != null) {
                this.b[i2].setVisibility(0);
                this.b[i2].setImageBitmap(cko_);
                i2++;
                this.i.add(str);
            } else {
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    LogUtil.b("PersonalCenterRecyclerViewAdapter", "NumberFormatException");
                    i = 0;
                }
                if (i > 0 && i <= 19) {
                    int intValue = map.get(str).intValue();
                    this.b[i2].setVisibility(0);
                    this.b[i2].setImageResource(intValue);
                    i2++;
                    this.i.add(str);
                }
            }
            if (i2 >= c2) {
                break;
            }
        }
        return i2;
    }

    private void a(int i, d dVar) {
        if (i == 0 && dVar != null) {
            LogUtil.a("PersonalCenterRecyclerViewAdapter", "Enter setMedals no medals");
            dVar.m.setText(this.d.getResources().getQuantityString(R.plurals._2130903256_res_0x7f0300d8, 0, 0));
            dVar.l.setVisibility(8);
            dVar.o.setVisibility(0);
            return;
        }
        LogUtil.a("PersonalCenterRecyclerViewAdapter", "Enter setMedals sArray.length");
    }

    private int c(List<String> list) {
        int size = list.size();
        if (nsn.ag(this.d)) {
            return Math.min(size, 8);
        }
        return b(size);
    }

    private int b(int i) {
        int min = Math.min(i, 5);
        if (min != 5) {
            return min;
        }
        int c2 = (this.f * 5) + nsn.c(this.d, 32.0f);
        LogUtil.a("PersonalCenterRecyclerViewAdapter", "medalLayoutWidth = ", Integer.valueOf(this.j), "totalMedalWidth = ", Integer.valueOf(c2));
        return c2 > this.j ? min - 1 : min;
    }

    private void b(d dVar) {
        if (this.e) {
            this.e = false;
            if (nsn.ag(this.d)) {
                LogUtil.a("PersonalCenterRecyclerViewAdapter", "adapterMedalTahiti is tahiti");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nsn.c(this.d, 55.0f), nsn.c(this.d, 55.0f));
                layoutParams.setMargins(0, 0, nsn.c(this.d, 12.0f), 0);
                dVar.d.setLayoutParams(layoutParams);
                dVar.c.setLayoutParams(layoutParams);
                dVar.g.setLayoutParams(layoutParams);
                dVar.h.setLayoutParams(layoutParams);
                dVar.j.setLayoutParams(layoutParams);
                dVar.i.setLayoutParams(layoutParams);
                dVar.f.setLayoutParams(layoutParams);
                dVar.k.setLayoutParams(layoutParams);
                d();
                return;
            }
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(nsn.c(this.d, 55.0f), nsn.c(this.d, 55.0f));
            layoutParams2.setMargins(0, 0, nsn.c(this.d, 8.0f), 0);
            dVar.d.setLayoutParams(layoutParams2);
            dVar.c.setLayoutParams(layoutParams2);
            dVar.g.setLayoutParams(layoutParams2);
            dVar.h.setLayoutParams(layoutParams2);
            dVar.j.setLayoutParams(layoutParams2);
            dVar.i.setVisibility(8);
            dVar.f.setVisibility(8);
            dVar.k.setVisibility(8);
        }
    }

    private void d() {
        for (int i = 0; i < this.i.size(); i++) {
            ImageView[] imageViewArr = this.b;
            if (i < imageViewArr.length) {
                imageViewArr[i].setVisibility(0);
            }
        }
    }

    private boolean a(List<String> list) {
        int c2 = c(list);
        boolean z = false;
        for (int i = 0; i < c2; i++) {
            if (koq.b(list, i) || koq.b(this.i, i) || !list.get(i).equals(this.i.get(i))) {
                z = true;
            }
        }
        return z;
    }

    static class e extends RecyclerView.ViewHolder {
        HealthTextView c;

        e(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.divider_content);
        }
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10527a;
        ImageView b;
        View c;
        HealthDivider d;
        HealthProgressBar e;
        View f;
        ImageView g;
        ImageView h;
        HealthTextView j;

        a(View view) {
            super(view);
            this.h = (ImageView) view.findViewById(R.id.content_icon);
            this.f10527a = (HealthTextView) view.findViewById(R.id.content_title);
            this.g = (ImageView) view.findViewById(R.id.list_content_red_point);
            this.d = (HealthDivider) view.findViewById(R.id.list_content_line);
            this.b = (ImageView) view.findViewById(R.id.list_content_arrow_right);
            this.e = (HealthProgressBar) view.findViewById(R.id.hw_update_loading_hpb);
            this.f = view.findViewById(R.id.top_padding);
            this.c = view.findViewById(R.id.bottom_padding);
            this.j = (HealthTextView) view.findViewById(R.id.list_point_tips);
        }
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f10529a;
        ImageView b;
        ImageView c;
        ImageView d;
        ImageView e;
        ImageView f;
        ImageView g;
        ImageView h;
        ImageView i;
        ImageView j;
        ImageView k;
        LinearLayout l;
        HealthTextView m;
        FrameLayout n;
        LinearLayout o;

        d(View view) {
            super(view);
            this.f10529a = (HealthTextView) view.findViewById(R.id.user_profile_myreward_title_tv);
            this.n = (FrameLayout) view.findViewById(R.id.user_profile_myreward_content_layout);
            this.o = (LinearLayout) view.findViewById(R.id.user_profile_myreward_none_layout);
            this.l = (LinearLayout) view.findViewById(R.id.social_reward_lly);
            this.b = (ImageView) view.findViewById(R.id.my_medal_list_arrow_gray2);
            this.m = (HealthTextView) view.findViewById(R.id.user_profile_myreward_total_tv);
            this.e = (ImageView) view.findViewById(R.id.mymedal_red_point);
            this.d = (ImageView) view.findViewById(R.id.social_reward_1);
            this.c = (ImageView) view.findViewById(R.id.social_reward_2);
            this.g = (ImageView) view.findViewById(R.id.social_reward_3);
            this.h = (ImageView) view.findViewById(R.id.social_reward_4);
            this.j = (ImageView) view.findViewById(R.id.social_reward_5);
            this.i = (ImageView) view.findViewById(R.id.social_reward_6);
            this.f = (ImageView) view.findViewById(R.id.social_reward_7);
            this.k = (ImageView) view.findViewById(R.id.social_reward_8);
            PersonalCenterRecyclerViewAdapter.this.b[0] = this.d;
            PersonalCenterRecyclerViewAdapter.this.b[1] = this.c;
            PersonalCenterRecyclerViewAdapter.this.b[2] = this.g;
            PersonalCenterRecyclerViewAdapter.this.b[3] = this.h;
            PersonalCenterRecyclerViewAdapter.this.b[4] = this.j;
            PersonalCenterRecyclerViewAdapter.this.b[5] = this.i;
            PersonalCenterRecyclerViewAdapter.this.b[6] = this.f;
            PersonalCenterRecyclerViewAdapter.this.b[7] = this.k;
        }
    }

    static class c extends RecyclerView.ViewHolder {
        ImageView c;
        ImageView d;

        c(View view) {
            super(view);
        }

        void a() {
            this.d = (ImageView) this.itemView.findViewById(R.id.item_banner_ad_img);
            this.c = (ImageView) this.itemView.findViewById(R.id.item_banner_ad_close_icon);
        }

        void b(boolean z, rzs rzsVar) {
            boolean z2 = (z && rzsVar != null && (rzsVar.d() instanceof String)) ? false : true;
            ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
            if (z2) {
                layoutParams.height = 0;
                layoutParams.width = 0;
                this.itemView.setLayoutParams(layoutParams);
                this.itemView.setVisibility(8);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = this.d.getLayoutParams();
            int c = eie.c();
            layoutParams2.width = c;
            layoutParams2.height = c / 4;
            this.d.setLayoutParams(layoutParams2);
            layoutParams.height = -2;
            layoutParams.width = -1;
            this.itemView.setLayoutParams(layoutParams);
            this.itemView.setVisibility(0);
            this.itemView.setTag(R.layout.item_personal_center_banner_ad, Long.valueOf(System.currentTimeMillis()));
            nrf.cIu_((String) rzsVar.d(), this.d);
        }
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private Map<Integer, ResourceResultInfo> f10528a;

        b(View view) {
            super(view);
        }

        void e() {
            final LinearLayout linearLayout = (LinearLayout) this.itemView.findViewById(R.id.item_my_grid);
            final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            ArrayList arrayList = new ArrayList();
            arrayList.add(4168);
            arrayList.add(9013);
            Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(arrayList);
            LogUtil.a("PersonalCenterRecyclerViewAdapter", "GridHolder start reach Message");
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterRecyclerViewAdapter.b.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (b.this.f10528a == null || !b.this.f10528a.equals(filterMarketingRules)) {
                        b.this.f10528a = filterMarketingRules;
                        ArrayList arrayList2 = new ArrayList();
                        for (Map.Entry<Integer, ResourceResultInfo> entry : filterMarketingRules.entrySet()) {
                            if (entry.getKey().intValue() == 9013) {
                                List<View> b = b.this.b(9013, filterMarketingRules);
                                if (koq.c(b) && b.size() >= 1) {
                                    arrayList2.add(b.get(0));
                                }
                            } else {
                                arrayList2.addAll(b.this.b(entry.getKey().intValue(), filterMarketingRules));
                            }
                        }
                        LogUtil.a("PersonalCenterRecyclerViewAdapter", "GridHolder viewList.size : ", Integer.valueOf(arrayList2.size()));
                        linearLayout.removeAllViews();
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            linearLayout.addView((View) it.next());
                        }
                        return;
                    }
                    LogUtil.h("PersonalCenterRecyclerViewAdapter", "GridHolder same resource map");
                }
            });
        }

        List<View> b(int i, Map<Integer, ResourceResultInfo> map) {
            MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(i), map.get(Integer.valueOf(i)));
            return marketingApi.getMarketingViewList(BaseApplication.getContext(), hashMap);
        }
    }
}
