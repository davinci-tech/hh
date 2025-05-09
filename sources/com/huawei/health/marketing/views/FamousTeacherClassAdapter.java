package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.FamousTeacherSeriesRecTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherContent;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherStandardContent;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.FamousTeacherClassAdapter;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eie;
import defpackage.eiv;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class FamousTeacherClassAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f2867a;
    private int b;
    private long c;
    private Set<SingleFamousTeacherContent> d = new HashSet();
    private Context e;
    private FamousTeacherSeriesRecTemplate f;
    private List<SingleFamousTeacherContent> g;
    private SingleFamousTeacherStandardContent i;

    public FamousTeacherClassAdapter(Context context, int i, ResourceBriefInfo resourceBriefInfo, List<SingleFamousTeacherContent> list, FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate) {
        this.g = new ArrayList();
        this.b = 0;
        this.e = context;
        this.g = list;
        this.f2867a = resourceBriefInfo;
        this.b = i;
        this.f = famousTeacherSeriesRecTemplate;
    }

    public void c(SingleFamousTeacherStandardContent singleFamousTeacherStandardContent) {
        this.i = singleFamousTeacherStandardContent;
        this.g = singleFamousTeacherStandardContent.getSubContents();
        this.c = System.currentTimeMillis();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: apg_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.e).inflate(R.layout.item_famous_teacher_class, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.g, i)) {
            LogUtil.h("FamousTeacherClassAdapter", "mSingleArticleContent isOutOfBounds");
            return;
        }
        SingleFamousTeacherContent singleFamousTeacherContent = this.g.get(i);
        LogUtil.a("FamousTeacherClassAdapter", "singleFamousTeacherContent:", singleFamousTeacherContent.toString());
        nrf.b(singleFamousTeacherContent.getPicture(), viewHolder.e, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238));
        eiv.d(viewHolder.c, singleFamousTeacherContent.getTheme(), singleFamousTeacherContent.isThemeVisibility());
        eiv.d(viewHolder.d, singleFamousTeacherContent.getDescription(), singleFamousTeacherContent.isDescriptionVisibility());
        viewHolder.b.setOnClickListener(new AnonymousClass5(singleFamousTeacherContent, i));
        nsy.cMb_(viewHolder.b, new b(this, viewHolder.b, singleFamousTeacherContent, i));
    }

    /* renamed from: com.huawei.health.marketing.views.FamousTeacherClassAdapter$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ SingleFamousTeacherContent b;
        final /* synthetic */ int d;

        AnonymousClass5(SingleFamousTeacherContent singleFamousTeacherContent, int i) {
            this.b = singleFamousTeacherContent;
            this.d = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (nsn.cLk_(view) || TextUtils.isEmpty(this.b.getLinkValue())) {
                LogUtil.h("FamousTeacherClassAdapter", "click too fast or url is empty");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            final String linkValue = this.b.getLinkValue();
            if (!eie.b(linkValue)) {
                FamousTeacherClassAdapter.this.c(linkValue, this.b, this.d);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LoginInit loginInit = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
            final SingleFamousTeacherContent singleFamousTeacherContent = this.b;
            final int i = this.d;
            loginInit.browsingToLogin(new IBaseResponseCallback() { // from class: ejv
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    FamousTeacherClassAdapter.AnonymousClass5.this.e(linkValue, singleFamousTeacherContent, i, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void e(String str, SingleFamousTeacherContent singleFamousTeacherContent, int i, int i2, Object obj) {
            if (i2 == 0) {
                FamousTeacherClassAdapter.this.c(str, singleFamousTeacherContent, i);
            } else {
                LogUtil.h("FamousTeacherClassAdapter", "onClick errorCode = ", Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, SingleFamousTeacherContent singleFamousTeacherContent, int i) {
        LogUtil.a("FamousTeacherClassAdapter", "more linkValue: ", str);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            String b2 = b(str, this.f2867a.getResourceName() + " " + this.i.getTheme(), this.f2867a.getResourceId(), i + 1, this.b);
            LogUtil.a("FamousTeacherClassAdapter", "jumping to detail page, url: ", b2);
            marketRouterApi.router(b2);
        }
        e(2, singleFamousTeacherContent, i);
    }

    private String b(String str, String str2, String str3, int i, int i2) {
        return str + "&resourceName=" + str2 + "&resourceId=" + str3 + "&pullOrder=" + i + "&pullfrom=" + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.g.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, SingleFamousTeacherContent singleFamousTeacherContent, int i2) {
        String str = this.f2867a.getResourceName() + " " + this.i.getTheme();
        HashMap hashMap = new HashMap(10);
        hashMap.put("resourceName", str);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", singleFamousTeacherContent.getTheme());
        if (i == 2) {
            hashMap.put("durationTime", Integer.valueOf((int) (System.currentTimeMillis() - this.c)));
            this.c = System.currentTimeMillis();
        }
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        if (!TextUtils.isEmpty(singleFamousTeacherContent.getDynamicDataId())) {
            hashMap.put("itemId", singleFamousTeacherContent.getDynamicDataId());
        }
        FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate = this.f;
        if (famousTeacherSeriesRecTemplate != null) {
            hashMap.put("moreEntryName", famousTeacherSeriesRecTemplate.getMoreMenuTitle());
        }
        LogUtil.a("FamousTeacherClassAdapter", "marketing biEvent: mPositionId: ", Integer.valueOf(this.b), ", biMap: ", hashMap.toString());
        MarketingBiUtils.d(i, this.b, this.f2867a, hashMap);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthImageView e;

        ViewHolder(View view) {
            super(view);
            this.e = (HealthImageView) view.findViewById(R.id.class_picture);
            this.c = (HealthTextView) view.findViewById(R.id.tv_class_name);
            this.d = (HealthTextView) view.findViewById(R.id.tv_class_describe);
            this.b = (LinearLayout) view.findViewById(R.id.item_class_layout);
        }
    }

    static class b implements ViewTreeObserver.OnScrollChangedListener {
        private final int b;
        private final WeakReference<FamousTeacherClassAdapter> c;
        private final SingleFamousTeacherContent d;
        private final WeakReference<View> e;

        public b(FamousTeacherClassAdapter famousTeacherClassAdapter, View view, SingleFamousTeacherContent singleFamousTeacherContent, int i) {
            this.c = new WeakReference<>(famousTeacherClassAdapter);
            this.e = new WeakReference<>(view);
            this.d = singleFamousTeacherContent;
            this.b = i;
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            FamousTeacherClassAdapter famousTeacherClassAdapter = this.c.get();
            View view = this.e.get();
            if (famousTeacherClassAdapter == null || view == null) {
                return;
            }
            if (famousTeacherClassAdapter.d.contains(this.d)) {
                nsy.cMg_(view, this);
            } else if (MarketingBiUtils.alP_(view)) {
                famousTeacherClassAdapter.e(1, this.d, this.b);
                famousTeacherClassAdapter.d.add(this.d);
                nsy.cMg_(view, this);
            }
        }
    }
}
