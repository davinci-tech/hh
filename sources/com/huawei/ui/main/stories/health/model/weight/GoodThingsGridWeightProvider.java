package com.huawei.ui.main.stories.health.model.weight;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider;
import defpackage.koq;
import defpackage.rjd;
import defpackage.rjg;
import defpackage.rjo;
import defpackage.rjq;
import java.util.List;

/* loaded from: classes9.dex */
public class GoodThingsGridWeightProvider extends GoodThingsGridProvider {

    /* renamed from: a, reason: collision with root package name */
    private SectionWeightView f10208a;
    private boolean c = true;

    @Override // com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider
    public void getNetData(SectionBean sectionBean) {
        LogUtil.a("GoodThingsGridWeightProvider", "getNetData");
        a();
        rjd.b().getShoppingInfoList(buildGetShoppingInfoListReq(), new UiCallback<rjg>() { // from class: com.huawei.ui.main.stories.health.model.weight.GoodThingsGridWeightProvider.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("GoodThingsGridWeightProvider", "getShoppingInfoList onFailure ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rjg rjgVar) {
                List<rjq> e = rjgVar.e();
                LogUtil.a("GoodThingsGridWeightProvider", "getShoppingInfoList success list is null ", Boolean.valueOf(koq.b(e)));
                if (koq.b(e)) {
                    return;
                }
                rjo rjoVar = new rjo();
                rjoVar.a(e);
                GoodThingsGridWeightProvider.this.mPaginationRsp = rjgVar.d();
                if (GoodThingsGridWeightProvider.this.f10208a != null) {
                    GoodThingsGridWeightProvider.this.mShowStartTime = System.currentTimeMillis();
                    GoodThingsGridWeightProvider.this.f10208a.setVisibility(0);
                    GoodThingsGridWeightProvider goodThingsGridWeightProvider = GoodThingsGridWeightProvider.this;
                    goodThingsGridWeightProvider.parseParams(goodThingsGridWeightProvider.f10208a.getContext(), GoodThingsGridWeightProvider.this.f10208a.getViewMap(), rjoVar);
                    GoodThingsGridWeightProvider.this.f10208a.bindParamsToView(GoodThingsGridWeightProvider.this.f10208a.getViewMap());
                }
            }
        });
    }

    private void a() {
        LogUtil.c("GoodThingsGridWeightProvider", "preGetNetData");
        if (this.f10208a != null && isActive(null) && this.c) {
            parseParams(this.f10208a.getContext(), this.f10208a.getViewMap(), new rjo());
            SectionWeightView sectionWeightView = this.f10208a;
            sectionWeightView.bindParamsToView(sectionWeightView.getViewMap());
            this.c = false;
        }
    }

    @Override // com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider
    public void onNoInterestButtonClicked() {
        LogUtil.a("GoodThingsGridWeightProvider", "onNoInterstButtonClicked");
        updateButtonClickedInfo();
        MarketingBiUtils.c(this.mResPosId, this.mResourceBriefInfo, this.mShowStartTime);
        this.f10208a.setVisibility(8);
        ObserverManagerUtil.c("MARKETING_GOOD_THING_CLOSE" + this.mResourceBriefInfo.getResourceId(), new Object[0]);
    }

    @Override // com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider
    public void onChangeBatchButtonClicked() {
        LogUtil.a("GoodThingsGridWeightProvider", "onChangeBatchButtonClicked");
        updatePaginationRsp();
        getNetData(null);
    }
}
