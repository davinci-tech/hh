package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseSidingTemplate;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ccq;
import defpackage.eie;
import defpackage.eil;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class BaseSeriesSidingLayout<T> extends BaseLifeCycleLinearLayout implements ResourceBriefInfoOwnable {
    protected final String TAG;
    private String mCategory;
    protected Context mContext;
    private RelativeLayout mHeader;
    private HealthTextView mHeaderSubTitle;
    protected HealthTextView mHeaderTitle;
    private HealthRecycleView mHealthRecycleView;
    private HealthTextView mMore;
    private ImageView mMoreArray;
    protected LinearLayout mMoreLayout;
    private Pair<Integer, Integer> mPair;
    protected int mPositionId;
    private ResourceBriefInfo mResourceBriefInfo;
    protected String mResourceId;
    protected String mResourceName;
    protected BaseSidingTemplate mTemplate;
    protected String mTemplateTheme;
    private View mainView;

    protected abstract BaseSeriesSidingAdapter<T> getAdapter();

    public BaseSeriesSidingLayout(Context context) {
        this(context, null);
    }

    public BaseSeriesSidingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseSeriesSidingLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BaseSeriesSidingLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.TAG = getLayoutTag();
        this.mPair = BaseActivity.getSafeRegionWidth();
        this.mContext = context;
    }

    public ResourceBriefInfo getResourceBriefInfo() {
        return this.mResourceBriefInfo;
    }

    public void initData(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        LogUtil.a(this.TAG, "initData");
        if (resourceBriefInfo == null) {
            LogUtil.b(this.TAG, "resourceBriefInfo is null");
            return;
        }
        if (!(baseTemplate instanceof BaseSidingTemplate)) {
            LogUtil.b(this.TAG, "filterTemplate error");
            return;
        }
        LogUtil.a(this.TAG, "resourceBriefInfo: " + resourceBriefInfo.toString());
        this.mPositionId = i;
        this.mResourceBriefInfo = resourceBriefInfo;
        this.mResourceId = resourceBriefInfo.getResourceId();
        this.mResourceName = this.mResourceBriefInfo.getResourceName();
        this.mCategory = this.mResourceBriefInfo.getCategory();
        BaseSidingTemplate baseSidingTemplate = (BaseSidingTemplate) baseTemplate;
        this.mTemplate = baseSidingTemplate;
        this.mTemplateTheme = baseSidingTemplate.getTheme();
        if (koq.b(this.mTemplate.getGridContents())) {
            LogUtil.h(this.TAG, "List isEmpty");
        } else {
            initViews();
            bindData();
        }
    }

    private void bindData() {
        setMarginLayoutParams(this.mHeader, this.mTemplate.isNameVisibility());
        nsy.cMs_(this.mHeaderTitle, this.mTemplate.getTheme(), true);
        bindTitleData();
        BaseSeriesSidingAdapter<T> adapter = getAdapter();
        if (adapter == null) {
            LogUtil.b(this.TAG, "mAdapter is null");
        } else {
            adapter.setDataList(this.mTemplate.getGridContents(), this.mResourceBriefInfo);
            this.mHealthRecycleView.setAdapter(adapter);
        }
    }

    private void bindTitleData() {
        nsy.cMs_(this.mHeaderSubTitle, this.mTemplate.getSubTitle(), true);
        if (this.mMore == null || TextUtils.isEmpty(this.mTemplate.getLinkValue())) {
            this.mMoreLayout.setVisibility(8);
        } else {
            nsy.cMs_(this.mMore, this.mTemplate.getTopRightCornerText(), true);
            this.mMoreLayout.setVisibility(0);
            nsy.cMn_(this.mMoreLayout, getViewClickListener());
        }
        this.mMoreArray.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable._2131430869_res_0x7f0b0dd5));
    }

    private View.OnClickListener getViewClickListener() {
        return new View.OnClickListener() { // from class: com.huawei.health.marketing.views.BaseSeriesSidingLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String linkValue = BaseSeriesSidingLayout.this.mTemplate.getLinkValue();
                if (TextUtils.isEmpty(linkValue) || nsn.o()) {
                    LogUtil.a(BaseSeriesSidingLayout.this.TAG, "linkValue is error, linkValue: ", linkValue);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    String c = eil.c(linkValue, BaseSeriesSidingLayout.this.mCategory);
                    BaseSeriesSidingLayout baseSeriesSidingLayout = BaseSeriesSidingLayout.this;
                    marketRouterApi.router(baseSeriesSidingLayout.setUrlPositionId(c, baseSeriesSidingLayout.mPositionId));
                    HashMap hashMap = new HashMap();
                    hashMap.put("moreEntryName", BaseSeriesSidingLayout.this.mTemplate.getTopRightCornerText());
                    MarketingBiUtils.d(3, BaseSeriesSidingLayout.this.mPositionId, BaseSeriesSidingLayout.this.mResourceBriefInfo, hashMap);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String setUrlPositionId(String str, int i) {
        if (str.contains("?")) {
            return str + "&pullfrom=" + i;
        }
        return str + "?pullfrom=" + i;
    }

    private void initViews() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.series_siding_layout, this);
        this.mainView = inflate;
        this.mHeader = (RelativeLayout) inflate.findViewById(R.id.section_sub_header);
        this.mHeaderTitle = (HealthTextView) this.mainView.findViewById(R.id.header_title);
        this.mHeaderSubTitle = (HealthTextView) this.mainView.findViewById(R.id.header_subtitle);
        this.mMoreLayout = (LinearLayout) this.mainView.findViewById(R.id.more_layout);
        this.mMore = (HealthTextView) this.mainView.findViewById(R.id.more);
        this.mMoreArray = (ImageView) this.mainView.findViewById(R.id.title_more_array);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.mainView.findViewById(R.id.series_course_recycler_view);
        this.mHealthRecycleView = healthRecycleView;
        ccq.b(healthRecycleView);
        this.mHealthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.mContext, 0, false));
    }

    private void setMarginLayoutParams(RelativeLayout relativeLayout, boolean z) {
        if (relativeLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (!z) {
                relativeLayout.setVisibility(4);
                layoutParams.height = nsf.b(R.dimen._2131362573_res_0x7f0a030d);
            } else {
                relativeLayout.setVisibility(0);
                layoutParams.topMargin = nsf.b(R.dimen._2131362922_res_0x7f0a046a);
                int b = nsf.b(R.dimen._2131362366_res_0x7f0a023e);
                int b2 = nsf.b(R.dimen._2131362365_res_0x7f0a023d);
                if (eie.e(this.mPositionId)) {
                    layoutParams.setMarginStart(b);
                    layoutParams.setMarginEnd(b2);
                } else {
                    layoutParams.setMarginStart(b + ((Integer) this.mPair.first).intValue());
                    layoutParams.setMarginEnd(b2 + ((Integer) this.mPair.second).intValue());
                }
            }
            relativeLayout.setLayoutParams(layoutParams);
        }
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        boolean z = (resourceBriefInfo == null || this.mResourceBriefInfo == null || !resourceBriefInfo.getResourceId().equals(this.mResourceBriefInfo.getResourceId())) ? false : true;
        LogUtil.a(this.TAG, "isOwnedByBriefInfo: " + z);
        return z;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.mResourceBriefInfo;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(this.TAG, "onConfigurationChanged1: ", Integer.valueOf(configuration.orientation));
        HealthRecycleView healthRecycleView = this.mHealthRecycleView;
        if (healthRecycleView == null || healthRecycleView.getAdapter() == null) {
            return;
        }
        this.mHealthRecycleView.getAdapter().notifyDataSetChanged();
    }

    protected String getLayoutTag() {
        return "BaseSeriesSidingLayout";
    }
}
