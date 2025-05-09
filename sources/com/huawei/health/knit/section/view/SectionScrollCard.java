package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eei;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nrs;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionScrollCard extends BaseSection implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2727a;
    private String[] b;
    private int c;
    protected List<ScrollCardParentView> d;
    private Context e;
    private int f;
    private a g;
    private int h;
    private int i;
    private int j;
    private OnSelectListener k;
    private boolean l;
    private Map<String, SpannableStringBuilder> m;
    private int n;
    private boolean o;
    private int p;
    private List<ScrollCardParentView> q;
    private int r;
    private HealthRecycleView s;
    private int t;
    private View u;
    private Map<String, eei> v;
    private View w;
    private String x;
    private View y;

    public interface OnSelectListener {
        void onSelect(String str, boolean z);
    }

    public SectionScrollCard(Context context) {
        this(context, null);
    }

    public SectionScrollCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionScrollCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new ArrayList();
        this.x = "TEMPERATURE_MIN_MAX";
        this.b = new String[]{"TEMPERATURE_MIN_MAX", "SKIN_TEMPERATURE_MIN_MAX"};
        this.o = false;
        this.l = false;
        this.c = 0;
        this.i = 0;
        this.n = 0;
        this.m = new HashMap();
        this.v = new HashMap();
        this.e = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionScrollCard", "onCreateView");
        this.e = context;
        c();
        return this.w;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionScrollCard", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionScrollCard", "not need to bind");
            return;
        }
        Map<String, eei> map = (Map) nru.c(hashMap, "TEMPERATURE_CARD_DATA", HashMap.class, null);
        this.v = map;
        if (map != null) {
            this.m.put("TEMPERATURE_MIN_MAX", map.get("TEMPERATURE_MIN_MAX").agS_());
            this.m.put("SKIN_TEMPERATURE_MIN_MAX", this.v.get("SKIN_TEMPERATURE_MIN_MAX").agS_());
        }
        this.f2727a = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        if (!this.l) {
            LogUtil.a("SectionScrollCard", "mHasInitDataAndView" + this.l);
            b();
        }
        d(this.m);
    }

    public void d(Map<String, SpannableStringBuilder> map) {
        LogUtil.a("SectionScrollCard", "begin to updateContent");
        if (!koq.b(this.q) && map != null && !map.isEmpty()) {
            LogUtil.c("SectionScrollCard", "updateContent ", Integer.valueOf(map.size()));
            for (int i = 0; i < this.q.size(); i++) {
                for (Map.Entry<String, SpannableStringBuilder> entry : map.entrySet()) {
                    ScrollCardParentView scrollCardParentView = this.q.get(i);
                    if (scrollCardParentView.e().equals(entry.getKey())) {
                        scrollCardParentView.ajI_(entry.getValue());
                        ajG_(scrollCardParentView, entry.getValue());
                        LogUtil.a("SectionScrollCard", "updateContent ", entry.getValue());
                    }
                }
            }
            return;
        }
        LogUtil.h("SectionScrollCard", "updateContent mScrollCardParentViewList is null");
    }

    private void c() {
        if (this.w == null) {
            LogUtil.h("SectionScrollCard", "initView mainView is null, start to inflate");
            this.w = LayoutInflater.from(this.e).inflate(R.layout.layout_blood_sugar_charteye, (ViewGroup) this, false);
        }
        this.d = new ArrayList();
        this.g = new a();
        View view = new View(this.e);
        this.y = view;
        view.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.y.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        LogUtil.c("SectionScrollCard", "mViewDivider height ", Integer.valueOf(this.y.getMinimumHeight()), "mViewDivider width ", Integer.valueOf(this.y.getMinimumWidth()));
        View view2 = new View(this.e);
        this.u = view2;
        view2.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.u.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.w.findViewById(R.id.observer_view_container);
        this.s = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.e, 0, false));
        this.s.setAdapter(this.g);
    }

    private void b() {
        LogUtil.c("SectionScrollCard", "begin to initDataAndView");
        Utils.init(this.e);
        this.o = nsn.ag(this.e);
        this.c = getCardWidth();
        this.i = getGutter();
        this.n = getMargin();
        a(this.v);
        this.l = true;
    }

    private int getCardWidth() {
        return (int) Utils.convertDpToPixel(getNormalWidth());
    }

    private int getMargin() {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("SectionScrollCard", "getMargin() mContext is null.");
            return 0;
        }
        return context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
    }

    private int getGutter() {
        return nrr.b(this.e);
    }

    private int getNormalWidth() {
        if (this.e == null) {
            LogUtil.h("SectionScrollCard", "getNormalWidth() mContext is null.");
            return MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY;
        }
        LogUtil.c("SectionScrollCard", "getNormalWidth() safeWidth is ", Integer.valueOf(((Integer) BaseActivity.getSafeRegionWidth().first).intValue() * 2));
        return nrr.d(this.e, ((nrs.c(this.e) - ((getMargin() + r0) * 2)) - getGutter()) / 2);
    }

    private void a(Map<String, eei> map) {
        LogUtil.a("SectionScrollCard", "enableClassifiedViewObserver");
        this.h = R.color._2131299241_res_0x7f090ba9;
        this.j = R.color._2131299236_res_0x7f090ba4;
        this.f = R.drawable._2131427568_res_0x7f0b00f0;
        this.t = R.color._2131299238_res_0x7f090ba6;
        this.r = R.color._2131299238_res_0x7f090ba6;
        this.p = R.drawable._2131427583_res_0x7f0b00ff;
        this.y.setMinimumWidth(this.i);
        this.u.setMinimumWidth(this.n);
        this.q = new ArrayList(16);
        if (map == null) {
            LogUtil.b("SectionScrollCard", "enableClassifiedViewObserver dataMap null");
            return;
        }
        for (int i = 0; i < this.b.length; i++) {
            for (Map.Entry<String, eei> entry : map.entrySet()) {
                if (entry != null && entry.getKey() != null && entry.getKey().equals(this.b[i])) {
                    eei value = entry.getValue();
                    ScrollCardParentView scrollCardParentView = new ScrollCardParentView(this.e, value.c(), value.a(), entry.getKey(), value.agS_());
                    ajG_(scrollCardParentView, value.agS_());
                    this.q.add(scrollCardParentView);
                }
            }
        }
        a(this.q);
        if (this.b[0].equals(this.x)) {
            setSelectIndex(this.q.get(0));
        } else if (this.b[1].equals(this.x)) {
            setSelectIndex(this.q.get(1));
        } else {
            LogUtil.h("SectionScrollCard", "TemperatureType Is UnKnow");
        }
    }

    public void a(List<ScrollCardParentView> list) {
        this.d.clear();
        this.d.addAll(list);
        this.g.notifyDataSetChanged();
    }

    private void ajG_(ScrollCardParentView scrollCardParentView, SpannableStringBuilder spannableStringBuilder) {
        if (spannableStringBuilder == null) {
            scrollCardParentView.a().setVisibility(8);
        } else if ("--".equals(spannableStringBuilder.toString())) {
            scrollCardParentView.a().setVisibility(8);
        } else {
            scrollCardParentView.a().setVisibility(0);
        }
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f2731a;

        e(View view) {
            super(view);
            this.f2731a = null;
            this.f2731a = (LinearLayout) view.findViewById(R.id.observer_view_item_place);
        }

        public void c(ScrollCardParentView scrollCardParentView) {
            if (scrollCardParentView != null && (scrollCardParentView.getParent() instanceof ViewGroup)) {
                ((ViewGroup) scrollCardParentView.getParent()).removeView(scrollCardParentView);
            }
            this.f2731a.removeAllViews();
            this.f2731a.addView(scrollCardParentView);
        }
    }

    static class c extends RecyclerView.ViewHolder {
        c(View view) {
            super(view);
        }
    }

    static class d extends RecyclerView.ViewHolder {
        d(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ajF_(ViewGroup viewGroup, View view) {
        if (viewGroup == null || view == null) {
            LogUtil.h("SectionScrollCard", "adaptView input parameters can't be null.");
        } else {
            view.setLayoutParams(new FrameLayout.LayoutParams(this.c, -2));
        }
    }

    public void setSelectIndex(View view) {
        LogUtil.a("SectionScrollCard", "setSelectIndex");
        for (ScrollCardParentView scrollCardParentView : this.d) {
            if (scrollCardParentView instanceof ScrollCardParentView) {
                scrollCardParentView.setClickable(true);
                ScrollCardParentView scrollCardParentView2 = scrollCardParentView;
                b(scrollCardParentView2, this.h, this.j, this.f);
                if (scrollCardParentView == this.d.get(0)) {
                    LogUtil.a("SectionScrollCard", "init card 0 TEMPERATURE_SELECT");
                    d(scrollCardParentView2, "TEMPERATURE_SELECT");
                } else if (scrollCardParentView == this.d.get(1)) {
                    LogUtil.a("SectionScrollCard", "init card 1 SKIN_TEMPERATURE_SELECT");
                    d(scrollCardParentView2, "SKIN_TEMPERATURE_SELECT");
                } else {
                    LogUtil.a("SectionScrollCard", "fault select");
                }
            }
        }
        if (view instanceof ScrollCardParentView) {
            ScrollCardParentView scrollCardParentView3 = (ScrollCardParentView) view;
            b(scrollCardParentView3, this.t, this.r, this.p);
            scrollCardParentView3.setClickable(false);
            scrollCardParentView3.setOnClickListener(null);
            this.g.notifyDataSetChanged();
        }
    }

    private void b(ScrollCardParentView scrollCardParentView, int i, int i2, int i3) {
        if (scrollCardParentView == null) {
            LogUtil.h("SectionScrollCard", "setViewResource cardView is null.");
            return;
        }
        scrollCardParentView.setBackgroundResource(i3);
        scrollCardParentView.setTextColor(scrollCardParentView.b(), i);
        scrollCardParentView.setTextColor(scrollCardParentView.c(), i2);
        scrollCardParentView.setTextColor(scrollCardParentView.a(), i2);
    }

    private void d(ScrollCardParentView scrollCardParentView, final String str) {
        scrollCardParentView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionScrollCard.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SectionScrollCard", "start to setViewListener, keyString is " + str);
                SectionScrollCard.this.f2727a.onClick(str);
                SectionScrollCard.this.setSelectIndex(view);
                SectionScrollCard.this.g.notifyDataSetChanged();
                if (SectionScrollCard.this.k != null && (view instanceof ScrollCardParentView)) {
                    SectionScrollCard.this.k.onSelect(((ScrollCardParentView) view).e() + "", true);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    class a extends RecyclerView.Adapter {
        private a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, SectionScrollCard.this.y.getMinimumHeight());
            if (i == 0) {
                View view = new View(SectionScrollCard.this.e);
                view.setMinimumWidth(SectionScrollCard.this.y.getMinimumWidth());
                view.setMinimumHeight(SectionScrollCard.this.y.getMinimumHeight());
                view.setLayoutParams(layoutParams);
                return new c(view);
            }
            if (i == 2) {
                View view2 = new View(SectionScrollCard.this.e);
                view2.setMinimumWidth(SectionScrollCard.this.u.getMinimumWidth());
                view2.setMinimumHeight(SectionScrollCard.this.u.getMinimumHeight());
                view2.setLayoutParams(layoutParams);
                return new d(view2);
            }
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bloodsugar_charteye_placeholder, viewGroup, false);
            SectionScrollCard.this.ajF_(viewGroup, inflate);
            return new e(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int i2;
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                if (viewHolder instanceof c) {
                    viewHolder.itemView.setMinimumWidth(SectionScrollCard.this.y.getMinimumWidth());
                    viewHolder.itemView.setMinimumHeight(SectionScrollCard.this.y.getMinimumHeight());
                    return;
                }
                return;
            }
            if (itemViewType == 2) {
                if (viewHolder instanceof d) {
                    LogUtil.c("SectionScrollCard", Integer.valueOf(i), "mViewEdge.getMinimumWidth() ", Integer.valueOf(SectionScrollCard.this.u.getMinimumWidth()));
                    viewHolder.itemView.setMinimumWidth(SectionScrollCard.this.u.getMinimumWidth() + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                    viewHolder.itemView.setMinimumHeight(SectionScrollCard.this.u.getMinimumHeight());
                    return;
                }
                return;
            }
            if (!(viewHolder instanceof e) || (i2 = i / 2) >= SectionScrollCard.this.d.size()) {
                return;
            }
            ((e) viewHolder).c(SectionScrollCard.this.d.get(i2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return SectionScrollCard.this.d.size() + SectionScrollCard.this.d.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            if (i == 0 || i == getItemCount() - 1) {
                return 2;
            }
            return (i < 0 || i % 2 != 1) ? 0 : 1;
        }
    }

    class ScrollCardParentView extends LinearLayout {
        private Context b;
        private HealthTextView c;
        private String d;
        private HealthTextView e;
        private HealthTextView i;

        public ScrollCardParentView(Context context, String str, String str2, String str3, SpannableStringBuilder spannableStringBuilder) {
            super(context);
            this.b = context;
            ajH_(str, str2, spannableStringBuilder);
            this.d = str3;
        }

        public void ajI_(SpannableStringBuilder spannableStringBuilder) {
            HealthTextView healthTextView = this.e;
            if (healthTextView != null) {
                healthTextView.setText(spannableStringBuilder);
            }
        }

        public HealthTextView a() {
            return this.i;
        }

        public HealthTextView b() {
            return this.c;
        }

        public HealthTextView c() {
            return this.e;
        }

        public void setTextColor(View view, int i) {
            if (view == null) {
                LogUtil.h("ScrollCardParentView_temperature", "view is null");
            } else if (view instanceof HealthTextView) {
                ((HealthTextView) view).setTextColor(ContextCompat.getColor(this.b, i));
            }
        }

        private void ajH_(String str, String str2, SpannableStringBuilder spannableStringBuilder) {
            LayoutInflater.from(getContext()).inflate(R.layout.item_temperature_cardeview, (ViewGroup) this, true);
            this.c = (HealthTextView) findViewById(R.id.eye_card_temperature_title);
            this.e = (HealthTextView) findViewById(R.id.eye_card_temperature_data);
            this.i = (HealthTextView) findViewById(R.id.eye_card_temperature_unit);
            this.c.setText(str);
            this.e.setText(spannableStringBuilder);
            this.i.setVisibility(8);
        }

        public String e() {
            return this.d;
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionScrollCard";
    }
}
