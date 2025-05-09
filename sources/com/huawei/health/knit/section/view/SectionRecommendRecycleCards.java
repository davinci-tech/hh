package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionRecommendRecyclesAdapter;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.koq;
import defpackage.nru;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionRecommendRecycleCards extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2719a;
    private SectionRecommendRecyclesAdapter b;
    private List<RecommendCardBean> c;
    private HealthRecycleView d;
    private HealthSubHeader e;

    public SectionRecommendRecycleCards(Context context) {
        super(context);
        this.c = Arrays.asList(new RecommendCardBean[3]);
    }

    public SectionRecommendRecycleCards(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = Arrays.asList(new RecommendCardBean[3]);
    }

    public SectionRecommendRecycleCards(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = Arrays.asList(new RecommendCardBean[3]);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionRecommendRecycleCards", "onCreateView");
        d(context);
        return this.f2719a;
    }

    private void d(Context context) {
        if (this.f2719a == null) {
            LogUtil.h("SectionRecommendRecycleCards", "initView mainView is null, start to inflate");
            this.f2719a = LayoutInflater.from(context).inflate(R.layout.section_recycle_recommend_card_layout, (ViewGroup) this, false);
        }
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.f2719a.findViewById(R.id.section_member_type_header);
        this.e = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(context, R.color._2131296971_res_0x7f0902cb));
        this.e.setHeadTitleText(getResources().getString(R$string.IDS_vip_exclusive_plan));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.f2719a.findViewById(R.id.section_recommended_cards_recyclerview);
        this.d = healthRecycleView;
        ccq.b(healthRecycleView);
        this.d.a(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(0);
        gridLayoutManager.setReverseLayout(LanguageUtil.bc(BaseApplication.getContext()));
        this.d.setLayoutManager(gridLayoutManager);
        SectionRecommendRecyclesAdapter sectionRecommendRecyclesAdapter = new SectionRecommendRecyclesAdapter(context);
        this.b = sectionRecommendRecyclesAdapter;
        this.d.setAdapter(sectionRecommendRecyclesAdapter);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionRecommendRecycleCards", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionRecommendRecycleCards", "no need to bind");
            return;
        }
        Hashtable<Integer, RecommendCardBean> hashtable = new Hashtable<>((Map<? extends Integer, ? extends RecommendCardBean>) nru.c(hashMap, "TOP_CARD_BEAN", Hashtable.class, new Hashtable()));
        List<RecommendCardBean> b = b(hashtable);
        this.b.e(b(hashtable));
        if (!b.isEmpty()) {
            RecommendCardBean recommendCardBean = b.get(0);
            if (!TextUtils.isEmpty(recommendCardBean.getSceneShowArticles())) {
                LogUtil.a("SectionRecommendRecycleCards", "first one is ", recommendCardBean.getCardId(), "SceneShowArticles is", recommendCardBean.getSceneShowArticles());
                this.e.setHeadTitleText(recommendCardBean.getSceneShowArticles());
                return;
            } else {
                ReleaseLogUtil.b("R_" + getTag(), "Articles null");
                this.e.setHeadTitleText(getResources().getString(R$string.IDS_vip_exclusive_plan));
                return;
            }
        }
        this.e.setHeadTitleText(getResources().getString(R$string.IDS_vip_exclusive_plan));
    }

    private List<RecommendCardBean> b(Hashtable<Integer, RecommendCardBean> hashtable) {
        if (hashtable.size() == 0) {
            LogUtil.a("SectionRecommendRecycleCards", "getNotNullCardBeanList recommendCardBeanList size 0");
            return this.c;
        }
        Iterator<Integer> it = hashtable.keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (koq.d(this.c, intValue)) {
                this.c.set(intValue, hashtable.get(Integer.valueOf(intValue)));
            }
        }
        ArrayList arrayList = new ArrayList();
        for (RecommendCardBean recommendCardBean : this.c) {
            if (recommendCardBean != null) {
                arrayList.add(recommendCardBean);
                LogUtil.a("SectionRecommendRecycleCards", "bean info ", recommendCardBean.getCardId());
            }
        }
        e(arrayList);
        return arrayList;
    }

    private void e(List<RecommendCardBean> list) {
        Collections.sort(list, new Comparator<RecommendCardBean>() { // from class: com.huawei.health.knit.section.view.SectionRecommendRecycleCards.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(RecommendCardBean recommendCardBean, RecommendCardBean recommendCardBean2) {
                for (int i = 0; i < 3; i++) {
                    if (!recommendCardBean.getLabelWeights().get(i).equals(recommendCardBean2.getLabelWeights().get(i))) {
                        return recommendCardBean2.getLabelWeights().get(i).intValue() - recommendCardBean.getLabelWeights().get(i).intValue();
                    }
                }
                LogUtil.a("SectionRecommendRecycleCards", "other things");
                return 0;
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionRecommendRecycleCards";
    }
}
