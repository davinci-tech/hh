package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.MultiGridsTemplate;
import com.huawei.health.marketing.datatype.RcmItem;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.health.marketing.request.RespSleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfoApi;
import com.huawei.health.marketing.request.SleepAudioInfoReq;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bkz;
import defpackage.eil;
import defpackage.ffv;
import defpackage.jdw;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.trg;
import health.compact.a.Services;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class SleepRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MultiGridsTemplate b;
    private Context c;
    private int e;
    private long f;
    private long g;
    private int h;

    /* renamed from: a, reason: collision with root package name */
    private List<SingleGridContent> f10131a = new ArrayList();
    private List<SingleGridContent> j = new ArrayList();
    private int d = -1;
    private Set<View> i = new HashSet();

    public SleepRecommendAdapter(Context context) {
        this.c = context;
    }

    public void b(int i, final MultiGridsTemplate multiGridsTemplate, int i2, List<RcmItem> list) {
        this.e = i;
        this.h = i2;
        this.b = multiGridsTemplate;
        c(i, multiGridsTemplate.getGridContents());
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                SleepRecommendAdapter.this.a(multiGridsTemplate.getGridContents());
            }
        });
        this.g = SystemClock.elapsedRealtime();
    }

    private void c(int i, List<SingleGridContent> list) {
        LogUtil.a("SleepRecommendAdapter", "updateData, gridContents: ", list.toString());
        this.f10131a.clear();
        this.j.clear();
        if (i == 21) {
            this.d = 1;
            this.f10131a.addAll(new ArrayList(list));
        } else if (i == 20) {
            this.d = 2;
            this.j.addAll(new ArrayList(list));
        } else {
            this.d = -1;
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    SleepRecommendAdapter.this.a();
                }
            });
        } else {
            notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<SingleGridContent> list) {
        LogUtil.a("SleepRecommendAdapter", "requestNumber");
        if (bkz.e(list)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            SingleGridContent singleGridContent = list.get(i);
            if (singleGridContent != null && !TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
                String dynamicDataId = singleGridContent.getDynamicDataId();
                if (i == list.size() - 1) {
                    sb.append(dynamicDataId);
                } else {
                    sb.append(dynamicDataId);
                    sb.append(",");
                }
            }
        }
        String sb2 = sb.toString();
        LogUtil.a("SleepRecommendAdapter", "ids: ", sb2);
        e(sb2, list);
    }

    private void e(String str, List<SingleGridContent> list) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SleepAudioInfoReq sleepAudioInfoReq = new SleepAudioInfoReq();
        sleepAudioInfoReq.setIds(str);
        sleepAudioInfoReq.setPageNo("1");
        sleepAudioInfoReq.setPageSize("100");
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        Map<String, Object> body = activityInfoListFactory.getBody(sleepAudioInfoReq);
        body.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        String b = lql.b(body);
        SleepAudioInfoApi sleepAudioInfoApi = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        if (sleepAudioInfoApi == null) {
            return;
        }
        c(sleepAudioInfoReq, activityInfoListFactory, b, sleepAudioInfoApi, list);
    }

    private void c(SleepAudioInfoReq sleepAudioInfoReq, ParamsFactory paramsFactory, String str, SleepAudioInfoApi sleepAudioInfoApi, List<SingleGridContent> list) {
        try {
            Response<RespSleepAudioInfo> execute = sleepAudioInfoApi.getSleepAudioList(sleepAudioInfoReq.getUrl(), paramsFactory.getHeaders(), str).execute();
            if (execute.isOK()) {
                LogUtil.a("SleepRecommendAdapter", "requestSleepAudioData response is OK.");
                RespSleepAudioInfo body = execute.getBody();
                if (body == null) {
                    LogUtil.h("SleepRecommendAdapter", "requestSleepAudioData result is null");
                    return;
                }
                List<SleepAudioInfo> sleepAudios = body.getSleepAudios();
                if (body.getResultCode() == 0 && !bkz.e(sleepAudios)) {
                    for (SingleGridContent singleGridContent : list) {
                        if (singleGridContent != null) {
                            String dynamicDataId = singleGridContent.getDynamicDataId();
                            if (!TextUtils.isEmpty(dynamicDataId)) {
                                Iterator<SleepAudioInfo> it = sleepAudios.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    SleepAudioInfo next = it.next();
                                    if (next != null && TextUtils.equals(String.valueOf(next.getId()), dynamicDataId)) {
                                        singleGridContent.setHeatCount(next.getHeatCount());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    c(this.e, list);
                    return;
                }
                LogUtil.h("SleepRecommendAdapter", "requestSleepAudioData result is error.");
                return;
            }
            LogUtil.a("SleepRecommendAdapter", "requestSleepAudioData response is fail.");
        } catch (IOException unused) {
            LogUtil.h("SleepRecommendAdapter", "requestSleepAudioData exception.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == -1) {
            return null;
        }
        if (i == 1) {
            return new c(LayoutInflater.from(this.c).inflate(R.layout.sleep_single_item, viewGroup, false));
        }
        if (i == 2) {
            return new a(LayoutInflater.from(this.c).inflate(R.layout.sleep_series_item, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            LogUtil.a("SleepRecommendAdapter", "holder is null");
            return;
        }
        if (viewHolder instanceof c) {
            LogUtil.a("SleepRecommendAdapter", "RecommendCourseViewHolder");
            if (trg.a(this.f10131a, i)) {
                LogUtil.a("SleepRecommendAdapter", "position is invalid: ", Integer.valueOf(i));
                return;
            }
            c cVar = (c) viewHolder;
            cVar.itemView.setLayoutParams(dCU_(cVar.c.getLayoutParams(), i));
            d(cVar, i);
            dCS_(cVar.itemView, i);
            return;
        }
        if (viewHolder instanceof a) {
            LogUtil.a("SleepRecommendAdapter", "RecommendSeriesViewHolder");
            if (trg.a(this.j, i)) {
                LogUtil.a("SleepRecommendAdapter", "series position is invalid: ", Integer.valueOf(i));
                return;
            }
            a aVar = (a) viewHolder;
            aVar.itemView.setLayoutParams(dCU_(aVar.j.getLayoutParams(), i));
            e(aVar, i);
            dCS_(aVar.itemView, i);
        }
    }

    private void dCS_(final View view, final int i) {
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                SingleGridContent singleGridContent;
                SingleGridContent singleGridContent2;
                if (SleepRecommendAdapter.this.d == 2 && (singleGridContent2 = (SingleGridContent) SleepRecommendAdapter.this.j.get(i)) != null) {
                    SleepRecommendAdapter.this.dCX_(singleGridContent2.getTheme(), view, i);
                }
                if (SleepRecommendAdapter.this.d != 1 || (singleGridContent = (SingleGridContent) SleepRecommendAdapter.this.f10131a.get(i)) == null) {
                    return;
                }
                SleepRecommendAdapter.this.dCX_(singleGridContent.getTheme(), view, i);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dCX_(String str, View view, int i) {
        if (view == null || dCV_(view)) {
            return;
        }
        nsy.cMa_(view, new e(this, view, str, i));
        nsy.cMb_(view, new e(this, view, str, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dCV_(View view) {
        return view != null && this.i.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dCT_(String str, View view, int i) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        int height = view.getHeight();
        int width = view.getWidth();
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        if (!dCW_(rect, height, width) || dCV_(view)) {
            return;
        }
        LogUtil.a("SleepRecommendAdapter", "item visible to user, theme: ", str, ", positionId: ", Integer.valueOf(this.h), ", height: ", Integer.valueOf(height), ", width: ", Integer.valueOf(width), ", rect: ", rect);
        this.i.add(view);
        LogUtil.a("SleepRecommendAdapter", "item visible to user, theme: ", this.b.getName(), ", positionId: ", Integer.valueOf(this.h), ", mContentType: ", Integer.valueOf(this.e));
        this.f = System.currentTimeMillis();
        a(1, i);
        this.g = SystemClock.elapsedRealtime();
    }

    private boolean dCW_(Rect rect, int i, int i2) {
        return rect != null && rect.left >= 0 && rect.left < i2 && rect.right >= Math.min(i2 / 2, 400) && rect.top >= 0 && rect.top < i && rect.bottom >= Math.min(i / 2, 400);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourcePositionId", Integer.valueOf(this.h));
        hashMap.put("resourceName", this.b.getName());
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("smartRecommend", Boolean.valueOf(this.b.isIntelligentFlag()));
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.f));
            this.f = System.currentTimeMillis();
        }
        SingleGridContent singleGridContent = this.d == 2 ? this.j.get(i2) : null;
        if (this.d == 1) {
            singleGridContent = this.f10131a.get(i2);
        }
        if (singleGridContent != null) {
            hashMap.put("itemId", this.b.isIntelligentFlag() ? singleGridContent.getItemId() : singleGridContent.getDynamicDataId());
            hashMap.put("resourceId", singleGridContent.getItemId());
            hashMap.put("itemCardName", singleGridContent.getTheme());
            hashMap.put("algId", singleGridContent.getAlgId());
        }
        ffv.b(hashMap);
    }

    private RecyclerView.LayoutParams dCU_(ViewGroup.LayoutParams layoutParams, int i) {
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.a("SleepRecommendAdapter", "params error");
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int dimensionPixelSize3 = this.c.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (i == 0) {
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
        } else if (i == getItemCount() - 1) {
            layoutParams2.setMarginStart(dimensionPixelSize3);
            layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
        } else {
            layoutParams2.setMarginStart(dimensionPixelSize3);
        }
        return layoutParams2;
    }

    private void d(c cVar, final int i) {
        LogUtil.a("SleepRecommendAdapter", "bindCourse");
        SingleGridContent singleGridContent = this.f10131a.get(i);
        nrf.cIS_(cVar.d, singleGridContent.getPicture(), 0, 0, R.drawable._2131427609_res_0x7f0b0119);
        b(cVar.b, singleGridContent);
        cVar.e.setText(singleGridContent.getTheme());
        cVar.f10136a.setBackground(this.c.getResources().getDrawable(R.drawable._2131427897_res_0x7f0b0239));
        final String linkValue = singleGridContent.getLinkValue();
        cVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SleepRecommendAdapter", "course is clicked: ", Integer.valueOf(i));
                SleepRecommendAdapter.this.c(linkValue);
                SleepRecommendAdapter.this.a(2, i);
                SleepRecommendAdapter.this.g = SystemClock.elapsedRealtime();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e(a aVar, final int i) {
        LogUtil.a("SleepRecommendAdapter", "bindSeries");
        SingleGridContent singleGridContent = this.j.get(i);
        nrf.cIS_(aVar.f10135a, singleGridContent.getPicture(), 0, 0, R.drawable._2131427609_res_0x7f0b0119);
        b(aVar.d, singleGridContent);
        aVar.e.setText(singleGridContent.getTheme());
        final String linkValue = singleGridContent.getLinkValue();
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SleepRecommendAdapter", "series is clicked: ", Integer.valueOf(i));
                SleepRecommendAdapter.this.e(linkValue);
                SleepRecommendAdapter.this.a(2, i);
                SleepRecommendAdapter.this.g = SystemClock.elapsedRealtime();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (TextUtils.isEmpty(str) || nsn.o()) {
            return;
        }
        String a2 = ffv.a(str);
        LogUtil.a("SleepRecommendAdapter", "linkValue: ", a2);
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(a2));
        intent.setPackage(this.c.getPackageName());
        intent.setFlags(268435456);
        jdw.bGh_(intent, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (TextUtils.isEmpty(str) || nsn.o()) {
            return;
        }
        String a2 = ffv.a(str);
        LogUtil.a("SleepRecommendAdapter", "linkValue: ", a2);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(a2);
        }
    }

    private void b(HealthTextView healthTextView, SingleGridContent singleGridContent) {
        String b = ffv.b(singleGridContent.getHeatCount());
        if (TextUtils.isEmpty(b)) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(b);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = this.d;
        if (i == -1) {
            return 0;
        }
        if (i == 2) {
            return this.j.size();
        }
        return this.f10131a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.d;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10136a;
        private HealthTextView b;
        private LinearLayout c;
        private ImageView d;
        private HealthTextView e;

        public c(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.root_layout);
            this.d = (ImageView) view.findViewById(R.id.item_series_courses_image);
            this.b = (HealthTextView) view.findViewById(R.id.number);
            this.e = (HealthTextView) view.findViewById(R.id.name);
            this.f10136a = (ImageView) view.findViewById(R.id.play_icon);
        }
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10135a;
        private HealthCardView b;
        private HealthCardView c;
        private HealthTextView d;
        private HealthTextView e;
        private LinearLayout j;

        public a(View view) {
            super(view);
            this.j = (LinearLayout) view.findViewById(R.id.root_layout);
            this.f10135a = (ImageView) view.findViewById(R.id.item_series_courses_image);
            this.d = (HealthTextView) view.findViewById(R.id.number);
            this.e = (HealthTextView) view.findViewById(R.id.name);
            this.b = (HealthCardView) view.findViewById(R.id.bottom_card);
            this.c = (HealthCardView) view.findViewById(R.id.middle_card);
            Context context = view.getContext();
            if (context != null) {
                if (nrt.a(context)) {
                    this.b.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                    this.c.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                } else {
                    this.b.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                    this.c.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                }
            }
        }
    }

    static class e implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<View> b;
        private int c;
        private final String d;
        private final WeakReference<SleepRecommendAdapter> e;

        public e(SleepRecommendAdapter sleepRecommendAdapter, View view, String str, int i) {
            this.e = new WeakReference<>(sleepRecommendAdapter);
            this.b = new WeakReference<>(view);
            this.d = str;
            this.c = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SleepRecommendAdapter sleepRecommendAdapter = this.e.get();
            View view = this.b.get();
            if (sleepRecommendAdapter == null || view == null) {
                return;
            }
            if (!sleepRecommendAdapter.dCV_(view)) {
                sleepRecommendAdapter.dCT_(this.d, view, this.c);
            } else {
                nsy.cMf_(view, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            SleepRecommendAdapter sleepRecommendAdapter = this.e.get();
            View view = this.b.get();
            if (sleepRecommendAdapter == null || view == null) {
                return;
            }
            if (!sleepRecommendAdapter.dCV_(view)) {
                sleepRecommendAdapter.dCT_(this.d, view, this.c);
            } else {
                nsy.cMg_(view, this);
            }
        }
    }
}
