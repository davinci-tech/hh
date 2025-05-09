package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.data.IKnitLifeCycle;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleEntryContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.datatype.templates.HotSeriesRecommendTemplate;
import com.huawei.health.marketing.datatype.templates.MultiFunctionTemplate;
import com.huawei.health.marketing.datatype.templates.QuickEntryTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFunctionGridContent;
import com.huawei.health.marketing.request.ActivityIdInfo;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.health.marketing.request.BreathExerciseInfo;
import com.huawei.health.marketing.request.BreathExerciseInfoApi;
import com.huawei.health.marketing.request.BreathExerciseInfoReq;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.request.InformationInfo;
import com.huawei.health.marketing.request.RespBreathExerciseInfo;
import com.huawei.health.marketing.request.RespSleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfoApi;
import com.huawei.health.marketing.request.SleepAudioInfoReq;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import defpackage.ffl;
import defpackage.ffv;
import defpackage.gic;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ColumnLinearLayout extends BaseLifeCycleLinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable, IKnitLifeCycle {

    /* renamed from: a, reason: collision with root package name */
    private ColumnLayoutAdapter f2839a;
    private long aa;
    private List<SingleGridContent> ab;
    private ResourceBriefInfo ac;
    private e ad;
    private boolean b;
    private List<List<SingleGridContent>> c;
    private int d;
    private boolean e;
    private int f;
    private a g;
    private e h;
    private List<SingleGridContent> i;
    private Context j;
    private String k;
    private boolean l;
    private HealthSubHeader m;
    private boolean n;
    private boolean o;
    private String p;
    private LinearLayout q;
    private View.OnClickListener r;
    private String s;
    private Handler t;
    private int u;
    private HealthRecycleView v;
    private Pair<Integer, Integer> w;
    private int x;
    private int y;
    private RelativeLayout z;

    static class b extends BaseHandler<ColumnLinearLayout> {
        b(ColumnLinearLayout columnLinearLayout) {
            super(columnLinearLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aoS_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ColumnLinearLayout columnLinearLayout, Message message) {
            if (message.what == 8193 && (message.obj instanceof ColumnLayoutAdapter)) {
                ColumnLayoutAdapter columnLayoutAdapter = (ColumnLayoutAdapter) message.obj;
                if (columnLinearLayout.v != null) {
                    if (columnLinearLayout.m.getVisibility() == 8) {
                        columnLinearLayout.m.setVisibility(0);
                    }
                    columnLayoutAdapter.b(columnLinearLayout.ab);
                    columnLinearLayout.v.setAdapter(columnLayoutAdapter);
                    columnLinearLayout.d();
                }
            }
        }
    }

    public ColumnLinearLayout(Context context) {
        super(context);
        this.y = 1;
        this.o = false;
        this.l = true;
        this.w = BaseActivity.getSafeRegionWidth();
        this.c = new ArrayList(10);
        this.i = new ArrayList(10);
        this.b = true;
        this.d = -1;
        this.n = true;
        this.e = false;
        this.j = context;
        this.t = new b(this);
    }

    private void c() {
        this.l = true;
        View cKr_ = nsf.cKr_(this.j, R.layout.cloumn_linearlayout, this);
        if (cKr_ == null) {
            LogUtil.b("ColumnLinearLayout", "initView NotFoundException.");
            this.l = false;
            return;
        }
        LinearLayout linearLayout = (LinearLayout) cKr_.findViewById(R.id.layout_marketing_grid);
        this.q = linearLayout;
        linearLayout.setVisibility(0);
        this.v = (HealthRecycleView) cKr_.findViewById(R.id.marketing_grid_recycler_view);
        this.z = (RelativeLayout) cKr_.findViewById(R.id.marketing_grid_recycler_view_out);
        HealthSubHeader healthSubHeader = (HealthSubHeader) cKr_.findViewById(R.id.marketing_grid_sub_header);
        this.m = healthSubHeader;
        if (healthSubHeader == null) {
            LogUtil.h("ColumnLinearLayout", "initView mHealthSubHeader is null.");
            this.l = false;
            setVisibility(8);
        } else {
            healthSubHeader.setMoreTextVisibility(4);
            this.v.setNestedScrollingEnabled(false);
            this.v.setHasFixedSize(true);
            this.v.a(false);
            this.v.d(false);
            this.o = nsn.ag(BaseApplication.getActivity());
        }
    }

    public void b(String str) {
        this.k = str;
    }

    public void e(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        c();
        if (this.l) {
            this.u = i;
            this.ac = resourceBriefInfo;
            if (resourceBriefInfo != null) {
                this.f = resourceBriefInfo.getContentType();
            }
            this.v.a(this.f == 47);
            this.y = getLayoutOrientation();
            if (resourceBriefInfo != null) {
                this.x = resourceBriefInfo.getPriority();
            }
            this.aa = System.currentTimeMillis();
            setRecyclerViewBackground(baseTemplate);
            b(baseTemplate);
            this.v.addOnScrollListener(new d(this.ac, this.f2839a));
            b();
            this.t.postDelayed(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.5
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.h();
                }
            }, 100L);
        }
    }

    private void b() {
        if (this.b && this.f == 47) {
            LogUtil.a("ColumnLinearLayout", "register Observer");
            if (this.g == null) {
                this.g = new a(this);
            }
            ObserverManagerUtil.d(this.g, "HOME_RECYCLE_VIEW_MOVE");
            this.b = false;
        }
    }

    private void b(LinearLayoutManager linearLayoutManager, int i) {
        this.v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.6
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int[] iArr = new int[2];
                if (ColumnLinearLayout.this.f2839a.a() != null) {
                    ColumnLinearLayout.this.f2839a.a().itemView.getLocationOnScreen(iArr);
                }
                if (iArr[0] == 0 || iArr[1] == 0) {
                    return;
                }
                ColumnLinearLayout.this.f2839a.c(iArr[0]);
                ColumnLinearLayout.this.f2839a.a(iArr[1]);
                ObserverManagerUtil.c("REFRESH_HEALTH_HEADLINES_SHOW_STATUS", false);
                ColumnLinearLayout.this.v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (i <= findFirstVisibleItemPosition) {
            this.v.scrollToPosition(i);
        } else if (i <= findLastVisibleItemPosition) {
            this.v.scrollBy(this.v.getChildAt(i - findFirstVisibleItemPosition).getLeft(), 0);
        } else {
            this.v.scrollToPosition(Math.min(i + 4, this.i.size() - 1));
        }
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        ColumnLayoutAdapter columnLayoutAdapter;
        super.onResume();
        if (this.f == 84) {
            a(this.i, this.f2839a);
        }
        if (this.f == 113 && (columnLayoutAdapter = this.f2839a) != null) {
            columnLayoutAdapter.notifyDataSetChanged();
        }
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ColumnLayoutAdapter columnLayoutAdapter;
        int i = this.d;
        if (i == -1) {
            return;
        }
        if (i != 1 && this.n && (columnLayoutAdapter = this.f2839a) != null) {
            b((LinearLayoutManager) this.v.getLayoutManager(), columnLayoutAdapter.c() - 2);
            this.n = false;
        } else {
            ObserverManagerUtil.c("REFRESH_HEALTH_HEADLINES_SHOW_STATUS", true);
        }
        this.d = -1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        LogUtil.a("ColumnLinearLayout", "onDetachedFromWindow ");
        nsy.cMf_(this.q, this.h);
        nsy.cMg_(this.q, this.ad);
        int i = this.f;
        if (i == 21 || i == 19) {
            ObserverManagerUtil.e("MARKETING_AUDITION_BTN_REFRESH");
        }
        a aVar = this.g;
        if (aVar != null) {
            ObserverManagerUtil.c(aVar);
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.q != null) {
            if (this.h == null) {
                this.h = new e(this);
            }
            if (this.ad == null) {
                this.ad = new e(this);
            }
            nsy.cMa_(this.q, this.h);
            nsy.cMb_(this.q, this.ad);
        }
    }

    public HealthSubHeader getHealthSubHeader() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LinearLayout linearLayout = this.q;
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            return;
        }
        int height = this.q.getHeight();
        int width = this.q.getWidth();
        Rect rect = new Rect();
        if (getLocalVisibleRect(rect) && aoN_(rect, height, width) && !this.e) {
            ResourceBriefInfo resourceBriefInfo = this.ac;
            LogUtil.a("ColumnLinearLayout", "visible to user, resource name: ", resourceBriefInfo != null ? resourceBriefInfo.getResourceName() : null, ", positionId: ", Integer.valueOf(this.u), ", height: ", Integer.valueOf(height), ", width: ", Integer.valueOf(width), ", getLocalVisibleRect: ", Boolean.valueOf(getLocalVisibleRect(rect)));
            this.e = true;
            HashMap hashMap = new HashMap();
            hashMap.put("moreEntryName", this.s);
            MarketingBiUtils.d(1, this.u, this.ac, hashMap);
        }
    }

    public static boolean aoN_(Rect rect, int i, int i2) {
        return rect != null && rect.left >= 0 && rect.left < i2 && rect.top >= 0 && rect.top < i && rect.bottom >= Math.min(i / 2, 400);
    }

    public void setItemClickListener(View.OnClickListener onClickListener) {
        this.r = onClickListener;
    }

    public void setShowDialogFlag(boolean z) {
        ColumnLayoutAdapter columnLayoutAdapter = this.f2839a;
        if (columnLayoutAdapter != null) {
            columnLayoutAdapter.d(z);
        }
    }

    public void d(ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate, String str) {
        ColumnLayoutAdapter columnLayoutAdapter;
        if (resourceBriefInfo.getContentType() == 47 && (columnLayoutAdapter = this.f2839a) != null && (baseTemplate instanceof GridTemplate)) {
            columnLayoutAdapter.b(((GridTemplate) baseTemplate).getGridContents());
            this.f2839a.a(str);
            this.f2839a.notifyDataSetChanged();
        }
    }

    public ColumnLayoutAdapter getmColumnLayoutAdapter() {
        return this.f2839a;
    }

    static class d extends RecyclerView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        private int f2854a;
        private WeakReference<ColumnLayoutAdapter> b;
        private int c = 0;
        private boolean d = false;
        private ResourceBriefInfo e;
        private int j;

        d(ResourceBriefInfo resourceBriefInfo, ColumnLayoutAdapter columnLayoutAdapter) {
            this.e = resourceBriefInfo;
            this.b = new WeakReference<>(columnLayoutAdapter);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            int findFirstVisibleItemPosition;
            int findFirstCompletelyVisibleItemPosition;
            float f;
            super.onScrollStateChanged(recyclerView, i);
            ColumnLayoutAdapter columnLayoutAdapter = this.b.get();
            if (i == 1 && columnLayoutAdapter != null) {
                columnLayoutAdapter.e();
            }
            if (i == 0) {
                if (columnLayoutAdapter != null) {
                    columnLayoutAdapter.j();
                }
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    return;
                }
                if (this.d) {
                    this.d = false;
                    return;
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!(layoutManager instanceof GridLayoutManager)) {
                    return;
                }
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                if (gridLayoutManager.getOrientation() != 0) {
                    return;
                }
                boolean z = this.c > 0;
                if (z) {
                    findFirstVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                } else {
                    findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                }
                if (z) {
                    findFirstCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                } else {
                    findFirstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                }
                if (findFirstCompletelyVisibleItemPosition == findFirstVisibleItemPosition || findFirstVisibleItemPosition < 0) {
                    return;
                }
                RecyclerView.ViewHolder findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                int[] iArr = new int[2];
                recyclerView.getLocationOnScreen(iArr);
                int i2 = iArr[0];
                float f2 = i2;
                float measuredWidth = i2 + recyclerView.getMeasuredWidth();
                if (findViewHolderForAdapterPosition == null) {
                    LogUtil.c("ColumnLinearLayout", "onScrollStateChanged viewHolder is null,return");
                    return;
                }
                findViewHolderForAdapterPosition.itemView.getLocationOnScreen(iArr);
                float measuredWidth2 = findViewHolderForAdapterPosition.itemView.getMeasuredWidth();
                if (z) {
                    float f3 = measuredWidth - iArr[0];
                    f = f3 / measuredWidth2 > 0.3f ? measuredWidth2 - f3 : -(f3 + 0.5f);
                } else {
                    float f4 = (iArr[0] + measuredWidth2) - f2;
                    f = f4 / measuredWidth2 > 0.3f ? -(measuredWidth2 - f4) : f4 + 0.5f;
                }
                recyclerView.smoothScrollBy((int) f, 0, new DecelerateInterpolator());
                this.d = true;
            }
            ResourceBriefInfo resourceBriefInfo = this.e;
            if (resourceBriefInfo != null && resourceBriefInfo.getContentType() == 47 && i != 1 && this.f2854a == 1 && this.j > 50) {
                c();
            }
            this.f2854a = i;
        }

        private void c() {
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            hashMap.put(CommonUtil.PAGE_TYPE, 2);
            ResourceBriefInfo resourceBriefInfo = this.e;
            hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo != null && "intelligent_alpha".equals(resourceBriefInfo.getSortingRules())));
            LogUtil.a("ColumnLinearLayout", "biEventFunctionMenuDragToRight 2040171: ", hashMap.toString());
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.FUNCTION_CARD_2040171.value(), hashMap, 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i != 0) {
                this.c = i;
            }
            if (this.f2854a == 1) {
                this.j += i;
            } else {
                this.j = 0;
            }
            super.onScrolled(recyclerView, i, i2);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("ColumnLinearLayout", "onConfigurationChanged1: ", Integer.valueOf(configuration.orientation));
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.10
            @Override // java.lang.Runnable
            public void run() {
                eie.d(configuration.orientation);
                ColumnLinearLayout.this.o = nsn.ag(BaseApplication.getActivity());
                if (ColumnLinearLayout.this.v == null || ColumnLinearLayout.this.v.getAdapter() == null) {
                    return;
                }
                if (ColumnLinearLayout.this.v.getAdapter() instanceof ColumnLayoutAdapter) {
                    ((ColumnLayoutAdapter) ColumnLinearLayout.this.v.getAdapter()).c(ColumnLinearLayout.this.o);
                }
                ColumnLinearLayout.this.d();
                ColumnLinearLayout.this.v.getAdapter().notifyDataSetChanged();
            }
        }, 200L);
    }

    public int getContentType() {
        return this.f;
    }

    private void b(BaseTemplate baseTemplate) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (baseTemplate == null) {
            LogUtil.h("ColumnLinearLayout", "gridTemplate is null.");
            if (this.f == 47) {
                this.m.setVisibility(8);
                return;
            }
            return;
        }
        int i6 = this.f;
        if ((i6 == 10 || i6 == 23) && (baseTemplate instanceof QuickEntryTemplate)) {
            List<SingleEntryContent> gridContents = ((QuickEntryTemplate) baseTemplate).getGridContents();
            if (koq.b(gridContents)) {
                this.q.setVisibility(8);
                LogUtil.h("ColumnLinearLayout", "dataShow() entryContentList is empty.");
                return;
            } else {
                c((GridTemplate) null, this.ac, true);
                ColumnLayoutAdapter columnLayoutAdapter = new ColumnLayoutAdapter(this.j, this.u, this.ac);
                this.f2839a = columnLayoutAdapter;
                columnLayoutAdapter.d(gridContents);
            }
        } else if (i6 == 113 && (baseTemplate instanceof HotSeriesRecommendTemplate)) {
            HotSeriesRecommendTemplate hotSeriesRecommendTemplate = (HotSeriesRecommendTemplate) baseTemplate;
            List<SingleHotSeriesRecommendContent> gridContents2 = hotSeriesRecommendTemplate.getGridContents();
            if (koq.b(gridContents2)) {
                this.q.setVisibility(8);
                LogUtil.h("ColumnLinearLayout", "dataShow() HotSeriesRecommendTemplate data is empty.");
                return;
            }
            GridTemplate gridTemplate = new GridTemplate();
            gridTemplate.setName(hotSeriesRecommendTemplate.getName());
            gridTemplate.setNameVisibility(hotSeriesRecommendTemplate.isNameVisibility());
            gridTemplate.setLinkValue(hotSeriesRecommendTemplate.getLinkValue());
            gridTemplate.setMoreMenuTitle(hotSeriesRecommendTemplate.getMoreMenuTitle());
            c(gridTemplate, this.ac, false);
            ColumnLayoutAdapter columnLayoutAdapter2 = new ColumnLayoutAdapter(this.j, this.u, this.ac);
            this.f2839a = columnLayoutAdapter2;
            columnLayoutAdapter2.c(gridContents2);
        } else if (i6 == 116) {
            List<SingleFunctionGridContent> gridContents3 = ((MultiFunctionTemplate) baseTemplate).getGridContents();
            if (koq.b(gridContents3)) {
                this.q.setVisibility(8);
                LogUtil.h("ColumnLinearLayout", "dataShow() entryContentList is empty.");
                return;
            } else {
                c((GridTemplate) null, this.ac, true);
                ColumnLayoutAdapter columnLayoutAdapter3 = new ColumnLayoutAdapter(this.j, this.u, this.ac);
                this.f2839a = columnLayoutAdapter3;
                columnLayoutAdapter3.e(gridContents3);
            }
        } else {
            if (!(baseTemplate instanceof GridTemplate)) {
                this.q.setVisibility(8);
                LogUtil.h("ColumnLinearLayout", "dataShow() GridTemplate is empty.");
                return;
            }
            GridTemplate gridTemplate2 = (GridTemplate) baseTemplate;
            List<SingleGridContent> b2 = eie.b(i6, gridTemplate2.getSleepAudioType(), gridTemplate2.getGridContents());
            this.i = b2;
            if (koq.b(b2)) {
                this.q.setVisibility(8);
                LogUtil.h("ColumnLinearLayout", "dataShow() gridContentList is empty.");
                return;
            }
            c(gridTemplate2, this.ac, false);
            int i7 = this.f;
            if (i7 == 8 || i7 == 48) {
                this.c.clear();
                this.c.add(b2);
                ColumnLayoutAdapter columnLayoutAdapter4 = new ColumnLayoutAdapter(this.j, this.u, this.ac);
                this.f2839a = columnLayoutAdapter4;
                columnLayoutAdapter4.a(this.c);
                if (TextUtils.equals(this.ac.getCategory(), SingleDailyMomentContent.ACTIVITY_TYPE) && this.f == 48) {
                    b(b2, this.f2839a);
                }
            } else {
                LogUtil.a("ColumnLinearLayout", "init ColumnLayoutAdapter: ", Integer.valueOf(this.ac.getContentType()));
                this.f2839a = new ColumnLayoutAdapter(this.j, this.u, this.ac);
                if (this.f == 76) {
                    a(b2.get(0));
                    if (b2.size() > 1) {
                        b2 = b2.subList(1, b2.size());
                    } else {
                        b2 = new ArrayList<>();
                    }
                }
                this.f2839a.b(b2);
                String category = this.ac.getCategory();
                View.OnClickListener onClickListener = this.r;
                if (onClickListener != null) {
                    this.f2839a.amy_(onClickListener);
                }
                if (this.f == 24) {
                    this.f2839a.a(gridTemplate2.hasBlank());
                }
                if (TextUtils.equals(category, SingleDailyMomentContent.ACTIVITY_TYPE) && ((i5 = this.f) == 6 || i5 == 15 || i5 == 19 || (i5 == 24 && gridTemplate2.hasBlank()))) {
                    b(b2, this.f2839a);
                    f();
                    return;
                }
                if (TextUtils.equals(category, SingleDailyMomentContent.INFORMATION_TYPE) && ((i4 = this.f) == 6 || i4 == 15 || (i4 == 24 && gridTemplate2.hasBlank()))) {
                    c(b2, this.f2839a);
                    f();
                    return;
                }
                if (TextUtils.equals(category, "Mixed") && ((i3 = this.f) == 6 || i3 == 15 || (i3 == 24 && gridTemplate2.hasBlank()))) {
                    i(b2, this.f2839a);
                    f();
                    return;
                }
                if (TextUtils.equals(category, "SleepAudio") && ((i2 = this.f) == 21 || i2 == 80)) {
                    c(category, b2, this.f2839a);
                    f();
                    return;
                }
                if ((TextUtils.equals(category, "SleepingSeries") || TextUtils.equals(category, "SleepAudio")) && ((i = this.f) == 19 || i == 21)) {
                    c(category, b2, this.f2839a);
                    f();
                    return;
                }
                if (TextUtils.equals(category, "BreathExercise") && this.f == 21) {
                    d(b2, this.f2839a);
                    f();
                    return;
                }
                if (this.f == 84) {
                    a(b2, this.f2839a);
                    f();
                    return;
                }
                if (TextUtils.equals(category, SingleDailyMomentContent.COURSE_TYPE)) {
                    int i8 = this.f;
                    if (i8 == 6 || i8 == 19 || i8 == 107 || (i8 == 24 && gridTemplate2.hasBlank())) {
                        e(b2, this.f2839a);
                        f();
                        return;
                    } else if (this.f == 59) {
                        e(b2, this.f2839a);
                    }
                }
                setVisibilityIfSleepManagement(gridTemplate2);
            }
        }
        f();
    }

    private void f() {
        this.f2839a.a(this.k);
        this.v.setAdapter(this.f2839a);
        d();
    }

    private void a(SingleGridContent singleGridContent) {
        LinearLayout.LayoutParams layoutParams;
        View inflate = LayoutInflater.from(this.j).inflate(R.layout.card_one_carry_three, (ViewGroup) this, false);
        ViewGroup.LayoutParams layoutParams2 = inflate.getLayoutParams();
        if (!(layoutParams2 instanceof LinearLayout.LayoutParams)) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        } else {
            layoutParams = (LinearLayout.LayoutParams) layoutParams2;
        }
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = this.j.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
        int intValue = ((Integer) safeRegionWidth.first).intValue() + dimensionPixelSize;
        int intValue2 = ((Integer) safeRegionWidth.second).intValue() + dimensionPixelSize;
        if (!nsn.ag(BaseApplication.getActivity())) {
            layoutParams.height = (int) (((nsn.n() - intValue) - intValue2) * 0.42857143f);
            layoutParams.width = -1;
            layoutParams.setMarginStart(intValue);
            layoutParams.setMarginEnd(intValue2);
        } else {
            int dimensionPixelSize2 = this.j.getResources().getDimensionPixelSize(R.dimen._2131362902_res_0x7f0a0456);
            layoutParams.height = dimensionPixelSize2;
            layoutParams.width = (int) (dimensionPixelSize2 / 0.42857143f);
            layoutParams.setMarginStart(intValue);
        }
        layoutParams.bottomMargin = dimensionPixelSize;
        inflate.setLayoutParams(layoutParams);
        nrf.b(singleGridContent.getPicture(), (HealthImageView) inflate.findViewById(R.id.img_background), 0);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.primary_title);
        if (singleGridContent.isThemeVisibility()) {
            healthTextView.setText(singleGridContent.getTheme());
            healthTextView.setVisibility(0);
        } else {
            healthTextView.setVisibility(8);
        }
        ((HealthTextView) inflate.findViewById(R.id.secondary_title)).setText(singleGridContent.getDescription());
        aoM_(inflate, singleGridContent);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.corner_mark);
        float d2 = eiv.d(8.0f);
        float[] fArr = new float[8];
        if (LanguageUtil.bc(this.j)) {
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = d2;
            fArr[5] = d2;
            fArr[6] = 0.0f;
            fArr[7] = 0.0f;
        } else {
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = d2;
            fArr[7] = d2;
        }
        eiv.c(healthTextView2, singleGridContent, fArr);
        LinearLayout linearLayout = this.q;
        if (linearLayout != null) {
            linearLayout.addView(inflate, 1);
        }
    }

    private void aoM_(View view, final SingleGridContent singleGridContent) {
        final String linkValue = singleGridContent.getLinkValue();
        view.setOnClickListener(new View.OnClickListener() { // from class: ejj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ColumnLinearLayout.this.aoP_(linkValue, singleGridContent, view2);
            }
        });
    }

    public /* synthetic */ void aoP_(final String str, final SingleGridContent singleGridContent, View view) {
        if (!eie.b(str)) {
            b(2, singleGridContent);
            e(str);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejq
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ColumnLinearLayout.this.b(singleGridContent, str, i, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void b(SingleGridContent singleGridContent, String str, int i, Object obj) {
        if (i == 0) {
            b(2, singleGridContent);
            e(str);
        } else {
            LogUtil.h("ColumnLinearLayout", "getViewClickListener errorCode = ", Integer.valueOf(i));
        }
    }

    private void b(int i, SingleGridContent singleGridContent) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("resourcePositionId", Integer.valueOf(this.u));
        hashMap.put("resourceId", this.ac.getResourceId());
        hashMap.put("resourceName", this.ac.getResourceName());
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", singleGridContent.getTheme());
        hashMap.put("pullOrder", 1);
        hashMap.put("moreEntryName", this.s);
        if (this.ac != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(this.ac.getSmartRecommend()));
        }
        if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
            hashMap.put("itemId", singleGridContent.getDynamicDataId());
        }
        if (i == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.aa));
            this.aa = currentTimeMillis;
        }
        ixx.d().d(this.j, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    private void setSubHeaderHeight(LinearLayout.LayoutParams layoutParams) {
        this.m.setVisibility(4);
        if (layoutParams != null) {
            layoutParams.height = this.j.getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
        }
    }

    private void c(GridTemplate gridTemplate, ResourceBriefInfo resourceBriefInfo, boolean z) {
        if (z) {
            setSubHeaderHeight(getSubHeaderParams());
            return;
        }
        if (gridTemplate == null) {
            return;
        }
        LinearLayout.LayoutParams subHeaderParams = getSubHeaderParams();
        boolean isNameVisibility = gridTemplate.isNameVisibility();
        String name = gridTemplate.getName();
        if (!isNameVisibility || TextUtils.isEmpty(name)) {
            setSubHeaderHeight(subHeaderParams);
            return;
        }
        this.m.setVisibility(0);
        if (resourceBriefInfo != null && this.f == 84) {
            this.m.setVisibility(8);
        }
        this.m.setHeadTitleText(name);
        this.p = gridTemplate.getLinkValue();
        boolean d2 = d(gridTemplate);
        if (!TextUtils.isEmpty(this.p) && !d2) {
            this.m.setMoreLayoutVisibility(0);
            this.m.setMoreViewClickListener(this);
            if (!TextUtils.isEmpty(gridTemplate.getMoreMenuTitle())) {
                this.s = gridTemplate.getMoreMenuTitle();
                this.m.setMoreTextVisibility(0);
                this.m.setMoreTextMaxLines(2);
                this.m.setMoreTextAlignment(6);
                this.m.setMoreText(gridTemplate.getMoreMenuTitle());
            }
            if (this.f == 59) {
                this.m.setMoreTextVisibility(0);
            }
        } else {
            this.m.setMoreLayoutVisibility(8);
        }
        this.m.setSubHeaderBackgroundColor(0);
    }

    private boolean d(final GridTemplate gridTemplate) {
        boolean z;
        LogUtil.a("ColumnLinearLayout", "initSubHeadCloseLayout closeButton: ", Boolean.valueOf(gridTemplate.isAdvertiseCloseButtonSwitch()), "  InfoButton: ", Boolean.valueOf(gridTemplate.isAdvertiseInfoButtonSwitch()), "  InfoLink: ", gridTemplate.getAdvertiseInfoLink());
        boolean z2 = true;
        if (gridTemplate.isAdvertiseCloseButtonSwitch()) {
            this.m.setCloseBtnVisibility(0);
            z = true;
        } else {
            z = false;
        }
        if (!gridTemplate.isAdvertiseInfoButtonSwitch() || TextUtils.isEmpty(gridTemplate.getAdvertiseInfoLink())) {
            z2 = z;
        } else {
            this.m.setInfoBtnVisibility(0);
        }
        this.m.setCloseBtnClickListener(new View.OnClickListener() { // from class: ejp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColumnLinearLayout.this.aoQ_(view);
            }
        });
        this.m.setInfoBtnClickListener(new View.OnClickListener() { // from class: ejr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColumnLinearLayout.aoO_(GridTemplate.this, view);
            }
        });
        return z2;
    }

    public /* synthetic */ void aoQ_(View view) {
        LogUtil.a("ColumnLinearLayout", "setResourceCloseRecord. closeTime = ", Long.valueOf(System.currentTimeMillis()), "; resourceName = ", this.ac.getResourceName(), "; contentType = ", Integer.valueOf(this.ac.getContentType()));
        eil.a(this.u, 1, this.ac);
        MarketingBiUtils.c(this.u, this.ac, this.aa);
        setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aoO_(GridTemplate gridTemplate, View view) {
        LogUtil.a("ColumnLinearLayout", "infoBtn clicked， url：", gridTemplate.getAdvertiseInfoLink());
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(gridTemplate.getAdvertiseInfoLink());
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        e(eie.c(this.f, this.o, this.v.getAdapter() != null ? this.v.getAdapter().getItemCount() : 0), eie.b(this.f, this.o), eie.a(this.f), eie.a(this.f, this.u));
    }

    private void e(int i, int i2, int i3, int[] iArr) {
        GridLayoutManager gridLayoutManager;
        if (this.j == null) {
            LogUtil.h("ColumnLinearLayout", "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (i < 1) {
            LogUtil.h("ColumnLinearLayout", "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        if (this.y == 2) {
            int i4 = this.f;
            if (i4 == 59 || i4 == 116 || i4 == 23) {
                gridLayoutManager = new GridLayoutManager(this.j, 2);
                gridLayoutManager.setReverseLayout(false);
            } else {
                gridLayoutManager = new GridLayoutManager(this.j, 1);
            }
            gridLayoutManager.setOrientation(0);
        } else {
            gridLayoutManager = new GridLayoutManager(this.j, i);
            gridLayoutManager.setOrientation(1);
        }
        this.v.setLayoutManager(gridLayoutManager);
        if (this.f == 22 && this.u != 4009) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams.setMarginEnd(0);
            layoutParams.setMarginStart(0);
            this.v.setLayoutParams(layoutParams);
        }
        int itemDecorationCount = this.v.getItemDecorationCount();
        if (!this.v.isComputingLayout()) {
            if (itemDecorationCount > 0) {
                while (true) {
                    itemDecorationCount--;
                    if (itemDecorationCount < 0) {
                        break;
                    } else {
                        this.v.removeItemDecorationAt(itemDecorationCount);
                    }
                }
            }
            this.v.addItemDecoration(new ColumnLayoutItemDecoration(i2, i3, i, iArr, this.f));
        }
        this.v.setHasFixedSize(true);
        this.v.setNestedScrollingEnabled(false);
        this.v.setVisibility(0);
    }

    private LinearLayout.LayoutParams getSubHeaderParams() {
        ViewGroup.LayoutParams layoutParams = this.m.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            int i = layoutParams2.leftMargin;
            int i2 = layoutParams2.rightMargin;
            if (eie.e(this.u)) {
                layoutParams2.leftMargin = i;
                layoutParams2.rightMargin = i2;
            } else {
                layoutParams2.leftMargin = i + ((Integer) this.w.first).intValue();
                layoutParams2.rightMargin = i2 + ((Integer) this.w.second).intValue();
            }
        }
        return layoutParams2;
    }

    private int getLayoutOrientation() {
        int i = this.f;
        if (i == 39 || i == 40 || i == 79 || i == 80 || i == 84 || i == 85) {
            return 2;
        }
        switch (i) {
            case 10:
            case 35:
            case 47:
            case 59:
            case 76:
            case 92:
            case 94:
            case 96:
            case 107:
            case 113:
            case 116:
                return 2;
            default:
                switch (i) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        return 2;
                    default:
                        return 1;
                }
        }
    }

    private void setRecyclerViewBackground(BaseTemplate baseTemplate) {
        if (this.f == 24 && (baseTemplate instanceof GridTemplate) && !((GridTemplate) baseTemplate).hasBlank()) {
            this.v.setBackground(ContextCompat.getDrawable(BaseApplication.getContext(), R.drawable.configured_item_image_text_background));
        }
        int i = this.f;
        if (i == 22 || i == 24) {
            i();
            return;
        }
        if (i == 47 && this.u == 4009) {
            this.z.setBackground(ContextCompat.getDrawable(BaseApplication.getContext(), R.drawable.configured_item_image_text_background));
            j();
            return;
        }
        if (i == 116) {
            MultiFunctionTemplate multiFunctionTemplate = (MultiFunctionTemplate) baseTemplate;
            j();
            g();
            setRecycleViewClickListener(multiFunctionTemplate.getLinkValue());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.q.getLayoutParams();
            layoutParams.width = -2;
            this.q.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams2.width = -2;
            this.v.setLayoutParams(layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.z.getLayoutParams();
            layoutParams3.width = -2;
            this.z.setLayoutParams(layoutParams3);
            RequestOptions centerCrop = RequestOptions.bitmapTransform(new RoundedCorners((int) getResources().getDimension(R.dimen._2131362359_res_0x7f0a0237))).centerCrop();
            CustomTarget<Drawable> customTarget = new CustomTarget<Drawable>() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.12
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: aoR_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) ColumnLinearLayout.this.z.getLayoutParams();
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    layoutParams4.height = (layoutParams4.width * bitmapDrawable.getBitmap().getHeight()) / bitmapDrawable.getBitmap().getWidth();
                    ColumnLinearLayout.this.z.setLayoutParams(layoutParams4);
                    ColumnLinearLayout.this.z.setBackground(drawable);
                }
            };
            if (nrt.a(this.j)) {
                nrf.b(multiFunctionTemplate.getDarkBackgroundPicture(), centerCrop, customTarget);
                return;
            } else {
                nrf.b(multiFunctionTemplate.getPicture(), centerCrop, customTarget);
                return;
            }
        }
        this.v.setBackground(null);
    }

    private void setRecycleViewClickListener(final String str) {
        this.z.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ColumnLinearLayout.this.e(str);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g() {
        ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            if (!eie.e(this.u)) {
                dimensionPixelSize += ((Integer) this.w.first).intValue();
                dimensionPixelSize2 += ((Integer) this.w.second).intValue();
            }
            layoutParams2.addRule(14);
            layoutParams2.setMargins(dimensionPixelSize, nrr.e(this.j, 50.0f), dimensionPixelSize2, nrr.e(this.j, 16.0f));
            this.v.setLayoutParams(layoutParams2);
        }
    }

    private void j() {
        int i = this.f;
        if ((i == 47 && this.u == 4009) || i == 116) {
            ViewGroup.LayoutParams layoutParams = this.z.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
                int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
                int intValue = eie.e(this.u) ? 0 : ((Integer) this.w.first).intValue();
                int intValue2 = eie.e(this.u) ? 0 : ((Integer) this.w.second).intValue();
                layoutParams2.setMarginStart(dimensionPixelSize + intValue);
                layoutParams2.setMarginEnd(dimensionPixelSize2 + intValue2);
                this.z.setLayoutParams(layoutParams2);
            }
        }
    }

    private void i() {
        ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            if (eie.e(this.u)) {
                layoutParams2.setMarginStart(dimensionPixelSize);
                layoutParams2.setMarginEnd(dimensionPixelSize2);
            } else {
                layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) this.w.first).intValue());
                layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) this.w.second).intValue());
            }
            this.v.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.11
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.b((List<SingleGridContent>) list, columnLayoutAdapter);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (SingleGridContent singleGridContent : list) {
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId()) && !arrayList.contains(singleGridContent.getDynamicDataId())) {
                arrayList.add(singleGridContent.getDynamicDataId());
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("ColumnLinearLayout", "requestActivityData idLists is empty.");
            h(list, columnLayoutAdapter);
            return;
        }
        List<ActivityIdInfo> requestActivityInfo = ColumnRequestUtils.requestActivityInfo(this.j, this.u, arrayList);
        if (koq.b(requestActivityInfo)) {
            LogUtil.h("ColumnLinearLayout", "requestActivityData activityIdInfos is empty.");
            h(list, columnLayoutAdapter);
        } else {
            d(list, requestActivityInfo);
            h(list, columnLayoutAdapter);
        }
    }

    private void e(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (koq.b(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null && !TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
                arrayList.add(new ffl.d(singleGridContent.getDynamicDataId()).b());
            }
        }
        a((List<ffl>) arrayList, false, new UiCallback<List<FitWorkout>>() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.15
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("ColumnLinearLayout", "getCourseByIds failed, errorCode:" + i + ", errorInfo:" + str);
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list2) {
                if (koq.c(list2)) {
                    LogUtil.a("ColumnLinearLayout", "getCourseByIds success, course count:" + list2.size());
                    ColumnLinearLayout.this.a((List<SingleGridContent>) list, list2);
                }
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }
        });
    }

    private void setVisibilityIfSleepManagement(GridTemplate gridTemplate) {
        if (!koq.b(gridTemplate.getGridContents())) {
            if (gridTemplate.getGridContents().get(0) != null) {
                if (this.f == 15 && "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleep-management?h5pro=true&urlType=4&pName=com.huawei.health&path=distribute&statusBarTextBlack&isImmerse".equals(gridTemplate.getGridContents().get(0).getLinkValue())) {
                    setVisibility(VersionControlUtil.isSupportSleepManagement() ? 0 : 8);
                    return;
                }
                return;
            }
        }
        LogUtil.a("ColumnLinearLayout", "get contents fail");
    }

    private void a(final List<ffl> list, final boolean z, final UiCallback<List<FitWorkout>> uiCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ejm
                @Override // java.lang.Runnable
                public final void run() {
                    ColumnLinearLayout.this.c(list, z, uiCallback);
                }
            });
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("ColumnLinearLayout", "getCourseByIds, courseApi is null.");
            uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, "CourseApi is null");
        } else {
            courseApi.getCourseByIds(list, z, uiCallback);
        }
    }

    public /* synthetic */ void c(List list, boolean z, UiCallback uiCallback) {
        a((List<ffl>) list, z, (UiCallback<List<FitWorkout>>) uiCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.14
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.c((List<SingleGridContent>) list, columnLayoutAdapter);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (SingleGridContent singleGridContent : list) {
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId()) && !sb.toString().contains(singleGridContent.getDynamicDataId())) {
                sb.append(",");
                sb.append(singleGridContent.getDynamicDataId());
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            LogUtil.h("ColumnLinearLayout", "requestInformationData() idStrings is empty.");
            h(list, columnLayoutAdapter);
            return;
        }
        List<InformationInfo> requestInformationInfo = ColumnRequestUtils.requestInformationInfo(sb.toString().substring(1));
        if (koq.b(requestInformationInfo)) {
            LogUtil.h("ColumnLinearLayout", "requestInformationData() informationInfoList is empty.");
            h(list, columnLayoutAdapter);
        } else {
            e(list, requestInformationInfo);
            h(list, columnLayoutAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.16
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.i(list, columnLayoutAdapter);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        StringBuilder sb = new StringBuilder();
        for (SingleGridContent singleGridContent : list) {
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
                String itemCategory = singleGridContent.getItemCategory();
                if (TextUtils.equals(itemCategory, SingleDailyMomentContent.ACTIVITY_TYPE) && !arrayList.contains(singleGridContent.getDynamicDataId())) {
                    arrayList.add(singleGridContent.getDynamicDataId());
                }
                if (TextUtils.equals(itemCategory, SingleDailyMomentContent.INFORMATION_TYPE) && !sb.toString().contains(singleGridContent.getDynamicDataId())) {
                    sb.append(",");
                    sb.append(singleGridContent.getDynamicDataId());
                }
                if (TextUtils.equals(itemCategory, SingleDailyMomentContent.COURSE_TYPE) && !arrayList2.contains(singleGridContent.getDynamicDataId())) {
                    arrayList2.add(singleGridContent.getDynamicDataId());
                }
            }
        }
        if (arrayList.isEmpty() && TextUtils.isEmpty(sb.toString()) && arrayList2.isEmpty()) {
            LogUtil.h("ColumnLinearLayout", "requestMixedData() activityIds and informationIds is empty.");
            h(list, columnLayoutAdapter);
            return;
        }
        List<ActivityIdInfo> requestActivityInfo = arrayList.size() > 0 ? ColumnRequestUtils.requestActivityInfo(this.j, this.u, arrayList) : null;
        List<InformationInfo> requestInformationInfo = TextUtils.isEmpty(sb.toString()) ? null : ColumnRequestUtils.requestInformationInfo(sb.toString().substring(1));
        if (!koq.b(requestActivityInfo)) {
            d(list, requestActivityInfo);
        }
        if (!koq.b(requestInformationInfo)) {
            e(list, requestInformationInfo);
        }
        if (arrayList2.isEmpty()) {
            h(list, columnLayoutAdapter);
            return;
        }
        ArrayList arrayList3 = new ArrayList(10);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(new ffl.d((String) it.next()).b());
        }
        a((List<ffl>) arrayList3, false, new UiCallback<List<FitWorkout>>() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list2) {
                if (koq.c(list2)) {
                    ColumnLinearLayout.this.a((List<SingleGridContent>) list, list2);
                }
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        String dynamicDataId;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.c(str, (List<SingleGridContent>) list, columnLayoutAdapter);
                }
            });
            return;
        }
        ArrayList<String> arrayList = new ArrayList(list.size());
        for (SingleGridContent singleGridContent : list) {
            if (TextUtils.equals(str, "SleepAudio")) {
                dynamicDataId = singleGridContent.getDynamicDataId();
            } else {
                dynamicDataId = (TextUtils.equals(str, "SleepingSeries") && singleGridContent.isAuditionVisibility()) ? singleGridContent.getDynamicDataId() : "";
            }
            if (!TextUtils.isEmpty(dynamicDataId) && !arrayList.contains(dynamicDataId)) {
                arrayList.add(dynamicDataId);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : arrayList) {
            sb.append(",");
            sb.append(str2);
        }
        if (TextUtils.isEmpty(sb.toString())) {
            LogUtil.h("ColumnLinearLayout", "requestSleepData idStrings is empty.");
            h(list, columnLayoutAdapter);
            return;
        }
        String substring = sb.toString().substring(1);
        if (TextUtils.equals(str, "SleepAudio")) {
            d(substring, list, columnLayoutAdapter);
        } else if (TextUtils.equals(str, "SleepingSeries")) {
            e(substring, list, columnLayoutAdapter);
        }
    }

    private void d(String str, List<SingleGridContent> list, ColumnLayoutAdapter columnLayoutAdapter) {
        SleepAudioInfoReq sleepAudioInfoReq = new SleepAudioInfoReq();
        sleepAudioInfoReq.setIds(str);
        sleepAudioInfoReq.setPageNo("1");
        sleepAudioInfoReq.setPageSize("100");
        SleepAudioInfoApi sleepAudioInfoApi = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        Map<String, Object> body = activityInfoListFactory.getBody(sleepAudioInfoReq);
        body.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        try {
            Response<RespSleepAudioInfo> execute = sleepAudioInfoApi.getSleepAudioList(sleepAudioInfoReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(body)).execute();
            if (execute.isOK()) {
                LogUtil.a("ColumnLinearLayout", "requestSleepAudioData response is OK.");
                RespSleepAudioInfo body2 = execute.getBody();
                if (body2 == null) {
                    LogUtil.h("ColumnLinearLayout", "requestSleepAudioData result is null");
                    h(list, columnLayoutAdapter);
                    return;
                }
                if (body2.getResultCode() == 0 && !koq.b(body2.getSleepAudios())) {
                    for (SingleGridContent singleGridContent : list) {
                        if (singleGridContent != null) {
                            String dynamicDataId = singleGridContent.getDynamicDataId();
                            if (!TextUtils.isEmpty(dynamicDataId)) {
                                Iterator<SleepAudioInfo> it = body2.getSleepAudios().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    SleepAudioInfo next = it.next();
                                    if (next != null && TextUtils.equals(String.valueOf(next.getId()), dynamicDataId)) {
                                        singleGridContent.setHeatCount(next.getHeatCount());
                                        singleGridContent.setLabelType(next.getLabelType());
                                        singleGridContent.setVip(next.getIsVip());
                                        singleGridContent.setIsPay(next.getPurchasedStatus());
                                        singleGridContent.setExtend("category=SleepAudio");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    h(list, columnLayoutAdapter);
                    return;
                }
                LogUtil.h("ColumnLinearLayout", "requestSleepAudioData result is error.");
                h(list, columnLayoutAdapter);
                return;
            }
            h(list, columnLayoutAdapter);
        } catch (IOException | NumberFormatException unused) {
            LogUtil.b("ColumnLinearLayout", "requestSleepAudioData exception.");
            h(list, columnLayoutAdapter);
        }
    }

    private void e(String str, final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        LogUtil.a("ColumnLinearLayout", "requestSleepSeriesData， ids = ", str);
        ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).queryAudiosPackageBySeriesId(str, new UiCallback<List<SleepAudioPackage>>() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.h("ColumnLinearLayout", "requestSleepSeriesData fail, errorCode： ", Integer.valueOf(i), ", errorInfo: ", str2);
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<SleepAudioPackage> list2) {
                if (koq.b(list2)) {
                    LogUtil.h("ColumnLinearLayout", "requestSleepSeriesData data is empty");
                    ColumnLinearLayout.this.h(list, columnLayoutAdapter);
                    return;
                }
                for (SingleGridContent singleGridContent : list) {
                    if (singleGridContent != null) {
                        String dynamicDataId = singleGridContent.getDynamicDataId();
                        if (!TextUtils.isEmpty(dynamicDataId)) {
                            Iterator<SleepAudioPackage> it = list2.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                SleepAudioPackage next = it.next();
                                if (next != null) {
                                    SleepAudioSeries sleepAudioSeries = next.getSleepAudioSeries();
                                    if (sleepAudioSeries == null) {
                                        LogUtil.h("ColumnLinearLayout", "series is null");
                                    } else if (TextUtils.equals(String.valueOf(sleepAudioSeries.getId()), dynamicDataId)) {
                                        singleGridContent.setVip(sleepAudioSeries.getIsVip());
                                        singleGridContent.setIsPay(sleepAudioSeries.getPurchasedStatus());
                                        singleGridContent.setExtend("category=SleepingSeries");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                ColumnLinearLayout.this.h(list, columnLayoutAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.4
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.a((List<SingleGridContent>) list, columnLayoutAdapter);
                }
            });
            return;
        }
        LogUtil.a("ColumnLinearLayout", "requestCourseData");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.a("ColumnLinearLayout", "mCourseApi not found");
            h(list, columnLayoutAdapter);
            return;
        }
        String d2 = d(list);
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("ColumnLinearLayout", "topicIds not found");
            h(list, columnLayoutAdapter);
        } else {
            try {
                courseApi.queryAudiosPackageBySeriesId(d2, new UiCallback<List<SleepAudioPackage>>() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.8
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("ColumnLinearLayout", "queryAudiosPackageBySeriesId fail, errorCode： ", Integer.valueOf(i), ", errorInfo: ", str);
                        ColumnLinearLayout.this.h(list, columnLayoutAdapter);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<SleepAudioPackage> list2) {
                        if (!koq.b(list2)) {
                            if (ColumnLinearLayout.this.b(list2)) {
                                ColumnLinearLayout.this.a((List<SingleGridContent>) list, list2, columnLayoutAdapter);
                                return;
                            } else {
                                ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, false);
                                return;
                            }
                        }
                        LogUtil.a("ColumnLinearLayout", "data is empty");
                        ColumnLinearLayout.this.h(list, columnLayoutAdapter);
                    }
                });
            } catch (NumberFormatException unused) {
                LogUtil.b("ColumnLinearLayout", "requestSleepAudioData exception.");
                h(list, columnLayoutAdapter);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SingleGridContent> list, ColumnLayoutAdapter columnLayoutAdapter, List<SleepAudioPackage> list2, boolean z) {
        LogUtil.a("ColumnLinearLayout", "refreshView: isVipUser: ", Boolean.valueOf(z));
        c(list, list2, z);
        h(list, columnLayoutAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<SingleGridContent> list, final List<SleepAudioPackage> list2, final ColumnLayoutAdapter columnLayoutAdapter) {
        LogUtil.a("ColumnLinearLayout", "requestVipInfo: ");
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("ColumnLinearLayout", "refreshVipStateData VipApi is null");
            d(list, columnLayoutAdapter, list2, false);
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.7
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    UserMemberInfo userMemberInfo;
                    if (obj == null) {
                        LogUtil.a("ColumnLinearLayout", "getVipInfo onSuccess result is null");
                        ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, false);
                        return;
                    }
                    if (obj instanceof UserMemberInfo) {
                        userMemberInfo = (UserMemberInfo) obj;
                        LogUtil.a("ColumnLinearLayout", "memberInfo: ", userMemberInfo.toString());
                    } else {
                        userMemberInfo = null;
                    }
                    if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1) {
                        LogUtil.h("ColumnLinearLayout", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                        ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, false);
                    } else if (!gpn.d(userMemberInfo)) {
                        ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, true);
                    } else {
                        LogUtil.h("ColumnLinearLayout", "isVipExpired");
                        ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, false);
                    }
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("ColumnLinearLayout", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                    ColumnLinearLayout.this.d(list, columnLayoutAdapter, list2, false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(List<SleepAudioPackage> list) {
        SleepAudioSeries sleepAudioSeries;
        for (SleepAudioPackage sleepAudioPackage : list) {
            if (sleepAudioPackage != null && (sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries()) != null && sleepAudioSeries.getIsVip() == 1) {
                return true;
            }
        }
        return false;
    }

    private void c(List<SingleGridContent> list, List<SleepAudioPackage> list2, boolean z) {
        SleepAudioSeries sleepAudioSeries;
        LogUtil.a("ColumnLinearLayout", "parseSeriesInfo: isVipUser: ", Boolean.valueOf(z));
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                String dynamicDataId = singleGridContent.getDynamicDataId();
                if (!TextUtils.isEmpty(dynamicDataId)) {
                    Iterator<SleepAudioPackage> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        SleepAudioPackage next = it.next();
                        if (next != null && (sleepAudioSeries = next.getSleepAudioSeries()) != null && TextUtils.equals(String.valueOf(sleepAudioSeries.getId()), dynamicDataId)) {
                            int isVip = sleepAudioSeries.getIsVip();
                            LogUtil.a("ColumnLinearLayout", "parseSeriesInfo: isVip: ", Integer.valueOf(isVip), ", id: ", dynamicDataId);
                            if (isVip == 1) {
                                int i = z ? 1 : 2;
                                LogUtil.a("ColumnLinearLayout", "parseSeriesInfo: isPaid: ", Integer.valueOf(i));
                                singleGridContent.setIsPay(i);
                                singleGridContent.setPlayPercent(ffv.b(next, z, i));
                            } else {
                                int purchasedStatus = sleepAudioSeries.getPurchasedStatus();
                                LogUtil.a("ColumnLinearLayout", "parseSeriesInfo: status: ", Integer.valueOf(purchasedStatus));
                                singleGridContent.setIsPay(purchasedStatus);
                                singleGridContent.setPlayPercent(ffv.b(next, z, purchasedStatus));
                            }
                            singleGridContent.setVip(sleepAudioSeries.getIsVip());
                        }
                    }
                }
            }
        }
    }

    private String d(List<SingleGridContent> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            SingleGridContent singleGridContent = list.get(i);
            if (singleGridContent != null) {
                if (i == list.size() - 1) {
                    sb.append(singleGridContent.getDynamicDataId());
                } else {
                    sb.append(singleGridContent.getDynamicDataId());
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final List<SingleGridContent> list, final ColumnLayoutAdapter columnLayoutAdapter) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.9
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLinearLayout.this.d((List<SingleGridContent>) list, columnLayoutAdapter);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<SingleGridContent> it = list.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            SingleGridContent next = it.next();
            if (!TextUtils.isEmpty(next.getDynamicDataId())) {
                if (!TextUtils.isEmpty(sb.toString())) {
                    String[] split = sb.toString().substring(1).split(",");
                    int length = split.length;
                    while (true) {
                        if (i < length) {
                            if (TextUtils.equals(split[i], next.getDynamicDataId())) {
                                break;
                            } else {
                                i++;
                            }
                        } else {
                            sb.append(",");
                            sb.append(next.getDynamicDataId());
                            break;
                        }
                    }
                } else if (!TextUtils.isEmpty(next.getDynamicDataId())) {
                    sb.append(",");
                    sb.append(next.getDynamicDataId());
                }
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            LogUtil.h("ColumnLinearLayout", "requestBreathExerciseData idBuilder is empty.");
            h(list, columnLayoutAdapter);
            return;
        }
        String substring = sb.toString().substring(1);
        BreathExerciseInfoReq breathExerciseInfoReq = new BreathExerciseInfoReq();
        breathExerciseInfoReq.setIds(substring);
        BreathExerciseInfoApi breathExerciseInfoApi = (BreathExerciseInfoApi) lqi.d().b(BreathExerciseInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        try {
            Response<RespBreathExerciseInfo> execute = breathExerciseInfoApi.getBreathExerciseList(breathExerciseInfoReq.getUrl(), activityInfoListFactory.getHeaders(), lql.b(activityInfoListFactory.getBody(breathExerciseInfoReq))).execute();
            if (execute.isOK()) {
                LogUtil.a("ColumnLinearLayout", "requestBreathExerciseData response is OK.");
                RespBreathExerciseInfo body = execute.getBody();
                if (body == null) {
                    LogUtil.h("ColumnLinearLayout", "requestBreathExerciseInfo result is empty.");
                    h(list, columnLayoutAdapter);
                    return;
                }
                if (body.getResultCode().equals("0") && !koq.b(body.getBreathTrainings())) {
                    for (SingleGridContent singleGridContent : list) {
                        if (singleGridContent != null) {
                            String dynamicDataId = singleGridContent.getDynamicDataId();
                            if (!TextUtils.isEmpty(dynamicDataId)) {
                                Iterator<BreathExerciseInfo> it2 = body.getBreathTrainings().iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    BreathExerciseInfo next2 = it2.next();
                                    if (next2 != null && TextUtils.equals(String.valueOf(next2.getId()), dynamicDataId)) {
                                        singleGridContent.setHeatCount(next2.getHeatCount());
                                        singleGridContent.setVip(Integer.parseInt(next2.getIsVip()));
                                        singleGridContent.setLabelType(next2.getLabelType());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    h(list, columnLayoutAdapter);
                    return;
                }
                LogUtil.h("ColumnLinearLayout", "requestBreathExerciseData result is error. ", body.getResultCode());
                h(list, columnLayoutAdapter);
                return;
            }
            h(list, columnLayoutAdapter);
        } catch (IOException | NumberFormatException e2) {
            LogUtil.b("ColumnLinearLayout", "exception is:", e2.getMessage());
            h(list, columnLayoutAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(List<SingleGridContent> list, ColumnLayoutAdapter columnLayoutAdapter) {
        synchronized (this) {
            this.ab = list;
            Message obtainMessage = this.t.obtainMessage();
            obtainMessage.what = 8193;
            obtainMessage.obj = columnLayoutAdapter;
            this.t.sendMessage(obtainMessage);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (TextUtils.isEmpty(this.p) || nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else if (!eie.b(this.p)) {
            e(this.p);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejk
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ColumnLinearLayout.this.b(i, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            e(this.p);
        } else {
            LogUtil.h("ColumnLinearLayout", "onClick errorCode = ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ColumnLinearLayout", "goToDetail linkValue is empty ");
            return;
        }
        e();
        LogUtil.a("ColumnLinearLayout", "more linkValue: ", str);
        if (this.f == 83) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.setPackage(this.j.getPackageName());
            intent.setFlags(268435456);
            jdw.bGh_(intent, this.j);
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null || str == null) {
            return;
        }
        marketRouterApi.router(str);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.ac == null || !resourceBriefInfo.getResourceId().equals(this.ac.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        return this.x;
    }

    private void e() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("resourcePositionId", Integer.valueOf(this.u));
        hashMap.put("resourceId", this.ac.getResourceId());
        hashMap.put("resourceName", this.ac.getResourceName());
        hashMap.put("event", 3);
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("moreEntryName", this.s);
        hashMap.put("smartRecommend", Boolean.valueOf(this.ac.getSmartRecommend()));
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.aa));
        this.aa = System.currentTimeMillis();
        ixx.d().d(this.j, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    private void d(List<SingleGridContent> list, List<ActivityIdInfo> list2) {
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                String dynamicDataId = singleGridContent.getDynamicDataId();
                if (!TextUtils.isEmpty(dynamicDataId)) {
                    Iterator<ActivityIdInfo> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActivityIdInfo next = it.next();
                        if (next != null && TextUtils.equals(dynamicDataId, next.getActivityId())) {
                            singleGridContent.setBeginDate(next.getBeginDate());
                            singleGridContent.setEndDate(next.getEndDate());
                            singleGridContent.setNumberOfPeople(next.getNumberOfPeople());
                            singleGridContent.setAvatarUrlList(next.getPortraitUrls());
                            singleGridContent.setStatistic(this.j.getResources().getQuantityString(R.plurals._2130903123_res_0x7f030053, next.getNumberOfPeople(), UnitUtil.e(next.getNumberOfPeople(), 1, 0)));
                            break;
                        }
                    }
                }
            }
        }
    }

    private void e(List<SingleGridContent> list, List<InformationInfo> list2) {
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                String dynamicDataId = singleGridContent.getDynamicDataId();
                if (!TextUtils.isEmpty(dynamicDataId)) {
                    Iterator<InformationInfo> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        InformationInfo next = it.next();
                        if (next != null && TextUtils.equals(dynamicDataId, String.valueOf(next.getId()))) {
                            singleGridContent.setNumberOfPeople(next.getReadCount());
                            singleGridContent.setAvatarUrlList(next.getAvatarList());
                            singleGridContent.setStatistic(this.j.getResources().getQuantityString(R.plurals._2130903124_res_0x7f030054, next.getReadCount(), UnitUtil.e(next.getReadCount(), 1, 0)));
                            break;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<SingleGridContent> list, List<FitWorkout> list2) {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        float weightOrDefaultValue = userInfo != null ? userInfo.getWeightOrDefaultValue() : 1.0f;
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                String dynamicDataId = singleGridContent.getDynamicDataId();
                if (!TextUtils.isEmpty(dynamicDataId)) {
                    Iterator<FitWorkout> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        FitWorkout next = it.next();
                        if (next != null && TextUtils.equals(dynamicDataId, next.acquireId())) {
                            singleGridContent.setCostTime(next.acquireDuration());
                            singleGridContent.setNumberOfPeople(next.acquireUsers());
                            singleGridContent.setExtend(((int) gic.d(next.acquireCalorie() * weightOrDefaultValue)) + "," + next.acquireDifficulty());
                            singleGridContent.setAvatarUrlList(next.getPortraitUrlList());
                            singleGridContent.setStatistic(this.j.getResources().getQuantityString(R.plurals._2130903125_res_0x7f030055, next.acquireUsers(), UnitUtil.e((double) next.acquireUsers(), 1, 0)));
                            break;
                        }
                    }
                }
            }
        }
    }

    static class e implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<ColumnLinearLayout> c;

        public e(ColumnLinearLayout columnLinearLayout) {
            this.c = new WeakReference<>(columnLinearLayout);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ColumnLinearLayout columnLinearLayout = this.c.get();
            if (columnLinearLayout == null) {
                return;
            }
            if (columnLinearLayout.e) {
                nsy.cMf_(columnLinearLayout.q, this);
            } else {
                columnLinearLayout.a();
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            ColumnLinearLayout columnLinearLayout = this.c.get();
            if (columnLinearLayout == null) {
                return;
            }
            if (columnLinearLayout.e) {
                nsy.cMg_(columnLinearLayout.q, this);
            } else {
                columnLinearLayout.a();
            }
        }
    }

    static class a implements Observer {
        private WeakReference<ColumnLinearLayout> e;

        a(ColumnLinearLayout columnLinearLayout) {
            this.e = new WeakReference<>(columnLinearLayout);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            final ColumnLinearLayout columnLinearLayout = this.e.get();
            if (columnLinearLayout == null) {
                return;
            }
            if (!koq.e(objArr, 0)) {
                LogUtil.b("ColumnLinearLayout", "null args!");
                return;
            }
            Object obj = objArr[0];
            if ((obj instanceof Boolean) && (objArr[1] instanceof Boolean)) {
                columnLinearLayout.d = ((Boolean) obj).booleanValue() ? 1 : 0;
                if (((Boolean) objArr[1]).booleanValue()) {
                    return;
                }
                HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLinearLayout.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        columnLinearLayout.n();
                    }
                }, 300L);
            }
        }
    }
}
