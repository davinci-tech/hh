package com.huawei.ui.main.stories.soical.activity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.soical.views.AssessmentRecyclerAdapter;
import com.huawei.ui.main.stories.soical.views.OnItemVisibleListener;
import com.huawei.ui.main.stories.soical.views.RecyclerItemDecoration;
import defpackage.eie;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nrs;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.sdl;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SocialAssessmentActivity extends BaseActivity implements View.OnClickListener {
    private HealthButton b;
    private Typeface e;
    private AssessmentRecyclerAdapter f;
    private HealthTextView g;
    private Typeface h;
    private long j;
    private MarketingApi k;
    private HealthTextView l;
    private ImageView m;
    private RelativeLayout n;
    private ViewGroup o;
    private OperationUtilsApi p;
    private HealthTextView r;
    private String s;
    private RelativeLayout t;
    private CustomTitleBar u;
    private boolean v;
    private LinearLayout w;
    private String y;
    private byte d = 0;
    private volatile byte q = 0;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f10476a = false;
    private final List<SingleGridContent> c = new ArrayList();
    private final Handler i = new b(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_assessment_health);
        c();
        e();
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        h();
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f10476a = true;
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLj_(1000, view)) {
            LogUtil.a("UIDV_SocialMeasurement", "Too fast click frequency!");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (R.id.hwappbarpattern_layout_cancle_icon == id) {
            finish();
        } else if (R.id.social_sort_hot == id) {
            a((byte) 0);
        } else if (R.id.social_sort_newest == id) {
            a((byte) 1);
        } else if (R.id.social_btn_no_net_work == id) {
            CommonUtil.q(this);
        } else if (R.id.social_reload_layout == id) {
            g();
        } else {
            LogUtil.a("UIDV_SocialMeasurement", "Unrecognized View!");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.social_tab_titlebar);
        this.u = customTitleBar;
        customTitleBar.setLeftButtonVisibility(0);
        this.u.setLeftButtonDrawable(getDrawable(R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        if (nrs.a(this)) {
            this.u.setTitleSize(getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
        } else {
            this.u.setTitleSize(getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        this.u.setTitleTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.u.setTitleText(getString(R.string._2130847583_res_0x7f02275f));
        this.g = (HealthTextView) findViewById(R.id.social_sort_hot);
        this.l = (HealthTextView) findViewById(R.id.social_sort_newest);
        this.h = Typeface.create(getString(R.string._2130851582_res_0x7f0236fe), 0);
        this.e = Typeface.create(getString(R.string._2130851581_res_0x7f0236fd), 0);
        this.g.setSelected(true);
        this.g.setTypeface(this.e);
        this.l.setSelected(false);
        this.w = (LinearLayout) findViewById(R.id.social_root_lyt);
        this.n = (RelativeLayout) findViewById(R.id.social_net_work_layout);
        this.m = (ImageView) findViewById(R.id.social_img_no_net_work);
        this.r = (HealthTextView) findViewById(R.id.social_tips_no_net_work);
        this.b = (HealthButton) findViewById(R.id.social_btn_no_net_work);
        this.t = (RelativeLayout) findViewById(R.id.social_reload_layout);
        this.o = (ViewGroup) findViewById(R.id.layout_loading);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.social_assessing_recyclerView);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this, 1, false));
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setItemAnimator(new DefaultItemAnimator());
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363794_res_0x7f0a07d2);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen._2131362007_res_0x7f0a00d7);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        healthRecycleView.addItemDecoration(new RecyclerItemDecoration(0, dimensionPixelSize4, new int[]{dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize4}));
        this.f = new AssessmentRecyclerAdapter(this.c);
        h();
        healthRecycleView.setAdapter(this.f);
    }

    private void e() {
        this.u.setLeftButtonClickable(true);
        this.u.setLeftButtonOnClickListener(this);
        this.g.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f.setOnItemClickListener(new d(this));
        this.f.a(new c(this));
    }

    private void h() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9);
        int d2 = (eie.d() - dimensionPixelSize) - getResources().getDimensionPixelSize(R.dimen._2131362007_res_0x7f0a00d7);
        int i = d2 / 3;
        AssessmentRecyclerAdapter assessmentRecyclerAdapter = this.f;
        if (assessmentRecyclerAdapter != null) {
            assessmentRecyclerAdapter.e(d2, i);
        }
    }

    private void a(byte b2) {
        if (b2 == this.d) {
            return;
        }
        if (b2 == 0) {
            this.g.setSelected(true);
            this.l.setSelected(false);
            this.g.setTypeface(this.e);
            this.l.setTypeface(this.h);
        } else {
            this.g.setSelected(false);
            this.l.setSelected(true);
            this.g.setTypeface(this.h);
            this.l.setTypeface(this.e);
        }
        this.d = b2;
        if (b()) {
            a(this.c);
            j();
        } else {
            g();
        }
    }

    private void a() {
        this.f10476a = false;
        this.q = (byte) 0;
        b(true);
        if (this.q == 1) {
            return;
        }
        f();
        if (this.q == 1) {
            b(false);
        }
    }

    private void j() {
        if (SystemClock.elapsedRealtime() - this.j < PreConnectManager.CONNECT_INTERNAL || (this.q != 1 && this.q != 3)) {
            LogUtil.a("UIDV_SocialMeasurement", "The conditions for requesting data are not met.");
        } else {
            f();
        }
    }

    private void g() {
        if (this.q == 2) {
            b(true);
        } else {
            a();
        }
    }

    private void f() {
        if (this.k == null) {
            this.k = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        if (this.k == null) {
            this.q = (byte) 1;
            return;
        }
        this.q = (byte) 2;
        a aVar = new a(this);
        this.k.getResourceResultInfo(4001).addOnSuccessListener(aVar).addOnFailureListener(aVar);
    }

    private void b(boolean z) {
        if (z) {
            if (CommonUtil.aa(this)) {
                this.w.setVisibility(8);
                this.n.setVisibility(8);
                this.o.setVisibility(0);
                return;
            }
            this.o.setVisibility(8);
            this.w.setVisibility(8);
            this.m.setImageResource(R.drawable._2131430211_res_0x7f0b0b43);
            this.r.setText(getString(R.string._2130842061_res_0x7f0211cd));
            this.b.setVisibility(0);
            this.n.setVisibility(0);
            this.q = (byte) 1;
            return;
        }
        this.o.setVisibility(8);
        if (b()) {
            this.n.setVisibility(8);
            this.w.setVisibility(0);
        } else {
            this.m.setImageResource(R.drawable._2131430454_res_0x7f0b0c36);
            this.r.setText(getString(R.string._2130843528_res_0x7f021788));
            this.b.setVisibility(8);
            this.n.setVisibility(0);
        }
    }

    private void a(List<SingleGridContent> list) {
        if (koq.b(list)) {
            b(false);
            return;
        }
        byte b2 = this.d;
        List<SingleGridContent> list2 = this.c;
        if (list2 != list) {
            list2.clear();
            this.c.addAll(list);
        }
        Collections.sort(this.c, new g(b2));
        AssessmentRecyclerAdapter assessmentRecyclerAdapter = this.f;
        if (assessmentRecyclerAdapter != null) {
            assessmentRecyclerAdapter.notifyDataSetChanged();
        }
        b(false);
    }

    private boolean b() {
        return koq.c(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SingleGridContent singleGridContent, long j, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 4001);
        hashMap.put("resourceId", this.s);
        hashMap.put("resourceName", this.y);
        hashMap.put("pullOrder", 1);
        hashMap.put("smartRecommend", Boolean.valueOf(this.v));
        hashMap.put("algId", "");
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        }
        if (singleGridContent != null) {
            hashMap.put("itemCardName", singleGridContent.getTheme());
            hashMap.put("itemId", singleGridContent.getDynamicDataId());
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OperationUtilsApi d() {
        if (this.p == null) {
            this.p = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
        }
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Map<Integer, ResourceResultInfo> map) {
        MarketingApi marketingApi = this.k;
        if (marketingApi == null) {
            this.i.sendEmptyMessage(12);
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
        if (filterMarketingRules == null || !filterMarketingRules.containsKey(4001) || filterMarketingRules.get(4001) == null) {
            this.i.sendEmptyMessage(12);
            return;
        }
        List<ResourceBriefInfo> resources = filterMarketingRules.get(4001).getResources();
        if (koq.b(resources)) {
            this.i.sendEmptyMessage(12);
            return;
        }
        if (this.f10476a) {
            return;
        }
        GridTemplate b2 = b(resources);
        List<SingleGridContent> c2 = b2 != null ? c(b2.getGridContents()) : null;
        if (this.f10476a) {
            return;
        }
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(11, c2));
    }

    private GridTemplate b(List<ResourceBriefInfo> list) {
        Gson gson = new Gson();
        ResourceBriefInfo resourceBriefInfo = null;
        GridTemplate gridTemplate = null;
        for (ResourceBriefInfo resourceBriefInfo2 : list) {
            if (resourceBriefInfo2 == null || resourceBriefInfo2.getContentType() != 40 || resourceBriefInfo2.getContent() == null || TextUtils.isEmpty(resourceBriefInfo2.getContent().getContent())) {
                LogUtil.a("UIDV_SocialMeasurement", "resource brief info invalid");
            } else {
                String content = resourceBriefInfo2.getContent().getContent();
                LogUtil.a("UIDV_SocialMeasurement", " marketing2.0: ", content);
                GridTemplate gridTemplate2 = (GridTemplate) gson.fromJson(content, GridTemplate.class);
                if (gridTemplate2 == null) {
                    LogUtil.a("UIDV_SocialMeasurement", "resource template parse exception");
                } else if (resourceBriefInfo == null || resourceBriefInfo.getPriority() < resourceBriefInfo2.getPriority()) {
                    resourceBriefInfo = resourceBriefInfo2;
                    gridTemplate = gridTemplate2;
                }
            }
        }
        if (resourceBriefInfo != null) {
            this.s = sdl.b(resourceBriefInfo.getResourceId());
            this.y = sdl.b(resourceBriefInfo.getResourceName());
            this.v = resourceBriefInfo.getSmartRecommend();
        }
        return gridTemplate;
    }

    private List<SingleGridContent> c(List<SingleGridContent> list) {
        if (koq.b(list)) {
            return list;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            SingleGridContent singleGridContent = list.get(size);
            if (!sdl.b(singleGridContent.getItemEffectiveTime(), singleGridContent.getItemExpirationTime())) {
                list.remove(size);
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dSQ_(Message message) {
        int i = message.what;
        if (i != 11) {
            if (i != 12) {
                return;
            }
            this.j = SystemClock.elapsedRealtime();
            b(false);
            return;
        }
        this.j = SystemClock.elapsedRealtime();
        if (!(message.obj instanceof List)) {
            LogUtil.a("UIDV_SocialMeasurement", "The data is null or the data type is incorrect.");
            b(false);
            return;
        }
        List<SingleGridContent> list = (List) message.obj;
        if (koq.b(list) || !(list.get(0) instanceof SingleGridContent)) {
            LogUtil.a("UIDV_SocialMeasurement", "No data exists in the container or the subtype is incorrect.");
            b(false);
        } else {
            a(list);
        }
    }

    /* loaded from: classes8.dex */
    static class b extends BaseHandler<SocialAssessmentActivity> {
        b(SocialAssessmentActivity socialAssessmentActivity) {
            super(socialAssessmentActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dSR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SocialAssessmentActivity socialAssessmentActivity, Message message) {
            socialAssessmentActivity.dSQ_(message);
        }
    }

    /* loaded from: classes8.dex */
    static class d implements BaseRecyclerAdapter.OnItemClickListener<SingleGridContent> {
        private final WeakReference<SocialAssessmentActivity> e;

        d(SocialAssessmentActivity socialAssessmentActivity) {
            this.e = new WeakReference<>(socialAssessmentActivity);
        }

        @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onItemClicked(RecyclerHolder recyclerHolder, int i, SingleGridContent singleGridContent) {
            LogUtil.b("UIDV_SocialMeasurement", "onItemClicked Coming Position: ", Integer.valueOf(i));
            if (nsn.cLj_(1000, recyclerHolder.itemView)) {
                LogUtil.a("UIDV_SocialMeasurement", "Too fast click frequency!");
                return;
            }
            if (singleGridContent == null) {
                LogUtil.b("UIDV_SocialMeasurement", "onItemClicked Wrong Position: ", Integer.valueOf(i));
                return;
            }
            SocialAssessmentActivity socialAssessmentActivity = this.e.get();
            if (socialAssessmentActivity == null || socialAssessmentActivity.isFinishing() || socialAssessmentActivity.isDestroyed()) {
                return;
            }
            nkx.cwZ_(new e(socialAssessmentActivity, singleGridContent), socialAssessmentActivity, socialAssessmentActivity.d().isNotSupportBrowseUrl(singleGridContent.getLinkValue()), AnalyticsValue.SOCIAL_1070004.value()).onClick(recyclerHolder.itemView);
        }
    }

    /* loaded from: classes8.dex */
    static class c implements OnItemVisibleListener<RecyclerHolder, SingleGridContent> {
        private final WeakReference<SocialAssessmentActivity> d;

        c(SocialAssessmentActivity socialAssessmentActivity) {
            this.d = new WeakReference<>(socialAssessmentActivity);
        }

        @Override // com.huawei.ui.main.stories.soical.views.OnItemVisibleListener
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onItemVisible(RecyclerHolder recyclerHolder, int i, SingleGridContent singleGridContent) {
            if (singleGridContent == null) {
                LogUtil.b("UIDV_SocialMeasurement", "onItemClicked Wrong Position: ", Integer.valueOf(i));
                return;
            }
            SocialAssessmentActivity socialAssessmentActivity = this.d.get();
            if (socialAssessmentActivity == null || socialAssessmentActivity.isFinishing() || socialAssessmentActivity.isDestroyed()) {
                return;
            }
            socialAssessmentActivity.a(singleGridContent, 0L, 1);
        }
    }

    /* loaded from: classes8.dex */
    static class e implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SingleGridContent> f10477a;
        private final WeakReference<SocialAssessmentActivity> e;

        e(SocialAssessmentActivity socialAssessmentActivity, SingleGridContent singleGridContent) {
            this.f10477a = new WeakReference<>(singleGridContent);
            this.e = new WeakReference<>(socialAssessmentActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SocialAssessmentActivity socialAssessmentActivity = this.e.get();
            SingleGridContent singleGridContent = this.f10477a.get();
            if (socialAssessmentActivity == null || singleGridContent == null || socialAssessmentActivity.isFinishing() || socialAssessmentActivity.isDestroyed()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            Object tag = view.getTag(R.layout.item_assessment_health);
            socialAssessmentActivity.a(singleGridContent, tag instanceof Long ? ((Long) tag).longValue() : 0L, 2);
            socialAssessmentActivity.e(singleGridContent.getLinkValue());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* loaded from: classes8.dex */
    static class a implements OnSuccessListener<Map<Integer, ResourceResultInfo>>, OnFailureListener {
        private final WeakReference<SocialAssessmentActivity> b;

        a(SocialAssessmentActivity socialAssessmentActivity) {
            this.b = new WeakReference<>(socialAssessmentActivity);
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            SocialAssessmentActivity socialAssessmentActivity = this.b.get();
            if (socialAssessmentActivity == null || socialAssessmentActivity.isFinishing() || socialAssessmentActivity.isDestroyed()) {
                return;
            }
            socialAssessmentActivity.e(map);
            socialAssessmentActivity.q = (byte) 3;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            SocialAssessmentActivity socialAssessmentActivity = this.b.get();
            if (socialAssessmentActivity == null || socialAssessmentActivity.isFinishing() || socialAssessmentActivity.isDestroyed()) {
                return;
            }
            LogUtil.b("UIDV_SocialMeasurement", " load resource failed");
            socialAssessmentActivity.i.sendEmptyMessage(12);
            socialAssessmentActivity.q = (byte) 3;
        }
    }

    /* loaded from: classes8.dex */
    static class g implements Comparator<SingleGridContent>, Serializable {
        private static final long serialVersionUID = 1;
        private final int e;

        g(int i) {
            this.e = i;
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
            if (singleGridContent == null || singleGridContent2 == null) {
                if (singleGridContent == null) {
                    return singleGridContent2 == null ? 0 : -1;
                }
                return 1;
            }
            int i = this.e;
            if (i == 0) {
                int compare = Integer.compare(singleGridContent2.getPriority(), singleGridContent.getPriority());
                if (compare == 0) {
                    compare = Long.compare(singleGridContent2.getItemEffectiveTime(), singleGridContent.getItemEffectiveTime());
                }
                return compare == 0 ? Long.compare(singleGridContent2.getParticipantCount(), singleGridContent.getParticipantCount()) : compare;
            }
            if (i != 1) {
                return 0;
            }
            int compare2 = Long.compare(singleGridContent2.getItemEffectiveTime(), singleGridContent.getItemEffectiveTime());
            if (compare2 == 0) {
                compare2 = Integer.compare(singleGridContent2.getPriority(), singleGridContent.getPriority());
            }
            int i2 = compare2;
            return i2 == 0 ? Long.compare(singleGridContent2.getParticipantCount(), singleGridContent.getParticipantCount()) : i2;
        }
    }
}
