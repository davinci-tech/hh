package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.SectionGroup;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import defpackage.eev;
import defpackage.efb;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class CollapsibleSectionGroup extends SectionGroup {

    /* renamed from: a, reason: collision with root package name */
    protected List<BaseSection> f2622a;
    protected boolean b;
    private ImageView c;
    protected LinearLayout d;
    protected HealthCardView e;
    private int f;
    private LinearLayout g;
    private Context h;
    private View i;
    private HealthTextView j;
    private int l;
    private boolean o;

    protected void b() {
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    public /* bridge */ /* synthetic */ HealthCardView getCardView() {
        return super.getCardView();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ KnitFragment getKnitFragment() {
        return super.getKnitFragment();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, android.view.View
    public /* bridge */ /* synthetic */ LinearLayout getRootView() {
        return super.getRootView();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    public /* bridge */ /* synthetic */ List getSectionList() {
        return super.getSectionList();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ View onCreateView(Context context) {
        return super.onCreateView(context);
    }

    public CollapsibleSectionGroup(Context context) {
        this(context, null);
    }

    public CollapsibleSectionGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CollapsibleSectionGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0;
        this.f = 0;
        this.h = context;
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        super.initView(knitFragment, i);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.collapsible_section_group_button_layout, (ViewGroup) getRootView(), false);
        this.i = inflate;
        this.g = (LinearLayout) inflate.findViewById(R.id.expand_root_layout);
        this.d = (LinearLayout) this.i.findViewById(R.id.sleep_expand_btn_layout);
        this.c = (ImageView) this.i.findViewById(R.id.sleep_service_expand);
        this.j = (HealthTextView) this.i.findViewById(R.id.sleep_expand_title);
        if (efb.g()) {
            getKnitFragment().setRecyclerViewDescendantFocusability(393216);
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public void onBind(SectionBean sectionBean) {
        super.onBind(sectionBean);
        ViewParent parent = this.i.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.i);
        }
        getRootView().addView(this.i);
        KnitDataProvider c = sectionBean.c();
        if (c != null) {
            c.parseParams(getContextRef(), getViewMap(), sectionBean.e());
            bindParamsToView(getViewMap());
        }
        b();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        this.o = (hashMap.containsKey("IS_DATA_TYPE_DAY") && (hashMap.get("IS_DATA_TYPE_DAY") instanceof Boolean)) ? ((Boolean) hashMap.get("IS_DATA_TYPE_DAY")).booleanValue() : false;
        this.f = a(hashMap, "Dreamtalknumber");
        this.l = a(hashMap, "Snorenumber");
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            if (key.equals("IS_GROUP_ARRAY_VISIBILITY")) {
                if (value instanceof Integer) {
                    StringBuilder sb = new StringBuilder("set array visibility: ");
                    Integer num = (Integer) value;
                    sb.append(num.intValue());
                    LogUtil.a("CollapsibleSectionGroup", sb.toString());
                    this.i.setVisibility(num.intValue());
                }
            } else if (key.equals("IS_SECTION_GROUP_EXPAND") && (value instanceof Boolean)) {
                StringBuilder sb2 = new StringBuilder("IsGroupExpand: ");
                Boolean bool = (Boolean) value;
                sb2.append(bool.booleanValue());
                LogUtil.a("CollapsibleSectionGroup", sb2.toString());
                this.b = bool.booleanValue();
                e(hashMap);
            }
        }
    }

    private int a(HashMap<String, Object> hashMap, String str) {
        if (hashMap.containsKey(str)) {
            return ((Integer) hashMap.get(str)).intValue();
        }
        return 0;
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    protected void bindParamsToView(int i, HashMap<String, Object> hashMap) {
        List<BaseSection> sectionList = getSectionList();
        this.f2622a = sectionList;
        if (koq.b(sectionList, i)) {
            LogUtil.b("CollapsibleSectionGroup", "position is out of bounds");
        } else {
            this.f2622a.get(i).bindParamsToView(hashMap);
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    protected void setSectionGroupCardColor(int i) {
        HealthCardView cardView = getCardView();
        this.e = cardView;
        if (cardView != null) {
            cardView.setBackgroundColor(getContext().getResources().getColor(i));
        }
    }

    protected void e(final HashMap<String, Object> hashMap) {
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.CollapsibleSectionGroup.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CollapsibleSectionGroup", "button is clicked, mIsExpand: ", Boolean.valueOf(CollapsibleSectionGroup.this.b));
                CollapsibleSectionGroup.this.b = !r0.b;
                ObserverManagerUtil.c("group_array_clicked_label", Boolean.valueOf(CollapsibleSectionGroup.this.b));
                ObserverManagerUtil.c("SLEEP_MORE_ANALYSIS", AnalyticsValue.HEALTH_SLEEP_CORE_SLEEP_TIME_2030111.value());
                CollapsibleSectionGroup.this.a(hashMap);
                if (!CollapsibleSectionGroup.this.b) {
                    CollapsibleSectionGroup.this.getKnitFragment().getHealthScrollView().fling(0);
                    CollapsibleSectionGroup.this.getKnitFragment().getHealthScrollView().smoothScrollTo(0, 0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a(hashMap);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public List<Bitmap> getShareBitmap() {
        ArrayList arrayList = new ArrayList();
        if (!isSupportShare()) {
            return arrayList;
        }
        c(arrayList);
        return arrayList;
    }

    private void c(List<Bitmap> list) {
        for (BaseSection baseSection : this.f2622a) {
            List<Bitmap> arrayList = new ArrayList<>();
            if (!this.b && (baseSection instanceof SectionScoring) && !(baseSection instanceof SectionScoringMgmt)) {
                SectionScoring sectionScoring = (SectionScoring) baseSection;
                sectionScoring.setSuggestRootLayoutVisibility(0);
                View view = this.i;
                if (view != null) {
                    view.setVisibility(8);
                }
                Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
                measure(View.MeasureSpec.makeMeasureSpec((nsn.n() - (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue())) - (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue()), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(nsn.j(), Integer.MIN_VALUE));
                layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
                Bitmap createBitmap = Bitmap.createBitmap(getContext().getResources().getDisplayMetrics().widthPixels, getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(getContext().getColor(R.color._2131297873_res_0x7f090651));
                canvas.save();
                draw(canvas);
                canvas.restore();
                if (createBitmap != null) {
                    arrayList.add(createBitmap);
                    this.i.setVisibility(0);
                    sectionScoring.setSuggestRootLayoutVisibility(8);
                    list.addAll(arrayList);
                }
            } else {
                arrayList = baseSection.getShareBitmap();
                if (!koq.b(arrayList)) {
                    list.addAll(arrayList);
                }
            }
        }
    }

    protected void a(HashMap<String, Object> hashMap) {
        if (this.b) {
            this.c.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131428280_res_0x7f0b03b8));
            this.d.setBackgroundColor(this.h.getColor(R.color._2131299296_res_0x7f090be0));
            nsy.cMr_(this.j, nru.b(hashMap, "ITEM_BUTTON_TEXT", ""));
        } else {
            this.c.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131428279_res_0x7f0b03b7));
            this.d.setBackgroundColor(this.h.getColor(R.color._2131299296_res_0x7f090be0));
            nsy.cMr_(this.j, nru.b(hashMap, "BUTTON_TEXT", ""));
        }
        if (koq.b(this.f2622a)) {
            return;
        }
        c(hashMap);
    }

    protected void c(HashMap<String, Object> hashMap) {
        Iterator<BaseSection> it = this.f2622a.iterator();
        while (it.hasNext()) {
            setSectionVisibility(it.next());
        }
        HashMap hashMap2 = new HashMap(16);
        for (SectionGroup.d dVar : this.mViewList) {
            if (dVar.d() == 8) {
                LogUtil.a("CollapsibleSectionGroup", "set view gone, view: " + dVar.ajj_() + ", visibility: " + dVar.d());
                dVar.ajj_().setVisibility(8);
            } else {
                d(hashMap2, dVar);
            }
        }
        hashMap2.put("Snorenumber", Integer.valueOf(this.l));
        hashMap2.put("Dreamtalknumber", Integer.valueOf(this.f));
        if (this.b) {
            ObserverManagerUtil.c("SLEEP_MORE_ANALYSIS", AnalyticsValue.SLEEP_MONITORING_RESULTS_21300045.value(), hashMap2);
        }
    }

    private void setSectionVisibility(BaseSection baseSection) {
        int i = !this.b ? 8 : 0;
        if (VersionControlUtil.isSupportSleepManagement() && this.o) {
            if (baseSection instanceof SectionExpandReport) {
                ((SectionExpandReport) baseSection).setTopItemVisibility(i);
                return;
            } else {
                if ((baseSection instanceof SectionWebview) || (baseSection instanceof SectionEnvNoiseLineChart) || (baseSection instanceof Section_SleepReport_Text_01) || (baseSection instanceof Section_SleepOrigin_Text)) {
                    baseSection.setVisibility(i);
                    return;
                }
                return;
            }
        }
        if (baseSection instanceof SectionExpandReport) {
            ((SectionExpandReport) baseSection).setTopItemVisibility(i);
        } else if (baseSection instanceof SectionScoring) {
            ((SectionScoring) baseSection).setSuggestRootLayoutVisibility(i);
        } else {
            baseSection.setVisibility(i);
        }
    }

    public void d(Map map, SectionGroup.d dVar) {
        char c;
        if (this.b) {
            map.put("click", "1");
            String simpleName = dVar.ajj_().getClass().getSimpleName();
            simpleName.hashCode();
            int hashCode = simpleName.hashCode();
            if (hashCode == -1867610764) {
                if (simpleName.equals("SectionWebview")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 577569880) {
                if (hashCode == 946680393 && simpleName.equals("SectionRingChart")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (simpleName.equals("SectionEnvNoiseLineChart")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c == 1) {
                    map.put("type", "3");
                    return;
                } else {
                    if (c != 2) {
                        return;
                    }
                    map.put("type", "0");
                    return;
                }
            }
            int e = eev.e() != 0 ? eev.e() : 2;
            if (e == 1) {
                map.put("type", "2");
            } else {
                if (e != 2) {
                    return;
                }
                map.put("type", "1");
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "CollapsibleSectionGroup";
    }
}
