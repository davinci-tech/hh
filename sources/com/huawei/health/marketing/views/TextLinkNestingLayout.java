package com.huawei.health.marketing.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleTextContent;
import com.huawei.health.marketing.datatype.TextLinkTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.TextLinkNestingLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eiv;
import defpackage.koq;
import defpackage.nsy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class TextLinkNestingLayout extends BaseLifeCycleLinearLayout implements ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private List<SingleTextContent> f2892a;
    private int b;
    private final Context c;
    private Fragment d;
    private int e;
    private boolean f;
    private final List<KnitFragment> g;
    private boolean[] h;
    private FragmentManager i;
    private boolean j;
    private boolean k;
    private ResourceBriefInfo l;
    private HealthSubHeader m;
    private LinearLayout n;
    private List<SingleTextContent> o;
    private TextLinkNestingTitleAdapter p;
    private e q;
    private String s;
    private ExtendHandler t;

    public TextLinkNestingLayout(Context context) {
        super(context);
        this.g = new ArrayList();
        this.e = -1;
        this.o = new ArrayList();
        this.f2892a = new ArrayList();
        this.k = false;
        this.f = true;
        this.j = false;
        this.c = context;
        this.t = HandlerCenter.yt_(new b(this), "text_link_nesting_handler");
    }

    public void a(ResourceBriefInfo resourceBriefInfo, TextLinkTemplate textLinkTemplate, FragmentManager fragmentManager) {
        LogUtil.a("TextLinkNestingLayout", "initData enter");
        this.l = resourceBriefInfo;
        this.i = fragmentManager;
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.text_link_nesting_layout, (ViewGroup) this, true);
        this.n = (LinearLayout) inflate.findViewById(R.id.text_link_layout);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.sub_header);
        this.m = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        this.m.setHeadTitleText(textLinkTemplate.getName());
        this.s = textLinkTemplate.getName();
        this.k = textLinkTemplate.isNameVisible();
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.titles_list);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.c, 0, false));
        e(healthRecycleView);
        TextLinkNestingTitleAdapter textLinkNestingTitleAdapter = new TextLinkNestingTitleAdapter(this.c);
        this.p = textLinkNestingTitleAdapter;
        healthRecycleView.setAdapter(textLinkNestingTitleAdapter);
        healthRecycleView.setOnScrollListener(new MarketingBiUtils.FitnessOnScrollListener(22, this.s, 5));
        List<SingleTextContent> gridContents = textLinkTemplate.getGridContents();
        if (koq.b(gridContents)) {
            LogUtil.h("TextLinkNestingLayout", "init failed singleTextContentList isEmpty");
            setVisibility(8);
            return;
        }
        View findViewById = inflate.findViewById(R.id.discovery_course_content);
        int generateViewId = View.generateViewId();
        this.b = generateViewId;
        findViewById.setId(generateViewId);
        List<SingleTextContent> b2 = b(gridContents);
        this.f2892a.clear();
        this.f2892a.addAll(b2);
        c(b2);
        this.f = false;
    }

    private void e(HealthRecycleView healthRecycleView) {
        healthRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.marketing.views.TextLinkNestingLayout.4
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int dimensionPixelSize = TextLinkNestingLayout.this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
                int dimensionPixelSize2 = TextLinkNestingLayout.this.c.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
                int dimensionPixelSize3 = TextLinkNestingLayout.this.c.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
                int size = TextLinkNestingLayout.this.p.c().size() - 1;
                if (childAdapterPosition == size) {
                    rect.right = ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() + dimensionPixelSize2;
                    rect.left = dimensionPixelSize3 / 2;
                }
                if (childAdapterPosition == 0) {
                    rect.left = ((Integer) BaseActivity.getSafeRegionWidth().first).intValue() + dimensionPixelSize;
                    rect.right = dimensionPixelSize3 / 2;
                }
                if (childAdapterPosition <= 0 || childAdapterPosition >= size) {
                    return;
                }
                int i = dimensionPixelSize3 / 2;
                rect.left = i;
                rect.right = i;
            }
        });
    }

    private void c(final List<SingleTextContent> list) {
        if (koq.b(list)) {
            LogUtil.b("TextLinkNestingLayout", "schedulePreloadFragmentList failed, singleTextContentList is empty!");
            setVisibility(8);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ekr
                @Override // java.lang.Runnable
                public final void run() {
                    TextLinkNestingLayout.this.a(list);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x004e, code lost:
    
        if (r12.await(com.huawei.hms.network.httpclient.util.PreConnectManager.CONNECT_INTERNAL, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void a(java.util.List r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r2 = "TextLinkNestingLayout"
            int r0 = r18.size()
            com.huawei.health.marketing.datatype.SingleTextContent[] r10 = new com.huawei.health.marketing.datatype.SingleTextContent[r0]
            com.huawei.health.knit.section.listener.BasePageResTrigger[] r11 = new com.huawei.health.knit.section.listener.BasePageResTrigger[r0]
            java.util.concurrent.CountDownLatch r12 = new java.util.concurrent.CountDownLatch
            int r3 = r18.size()
            r12.<init>(r3)
            r3 = 0
            r13 = r3
        L17:
            if (r13 >= r0) goto L46
            r14 = r18
            java.lang.Object r3 = r14.get(r13)
            r7 = r3
            com.huawei.health.marketing.datatype.SingleTextContent r7 = (com.huawei.health.marketing.datatype.SingleTextContent) r7
            com.huawei.health.knit.section.listener.BasePageResTrigger r15 = new com.huawei.health.knit.section.listener.BasePageResTrigger
            android.content.Context r3 = r1.c
            int r4 = r7.getPositionId()
            r5 = 0
            r15.<init>(r3, r4, r5)
            com.huawei.health.marketing.views.TextLinkNestingLayout$$ExternalSyntheticLambda1 r9 = new com.huawei.health.marketing.views.TextLinkNestingLayout$$ExternalSyntheticLambda1
            r3 = r9
            r4 = r12
            r5 = r13
            r6 = r10
            r8 = r11
            r16 = r0
            r0 = r9
            r9 = r15
            r3.<init>()
            androidx.fragment.app.FragmentManager r3 = r1.i
            r15.onPageLoadOnlineSections(r0, r3)
            int r13 = r13 + 1
            r0 = r16
            goto L17
        L46:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L51
            r3 = 10000(0x2710, double:4.9407E-320)
            boolean r0 = r12.await(r3, r0)     // Catch: java.lang.InterruptedException -> L51
            if (r0 != 0) goto L68
            goto L5f
        L51:
            r0 = move-exception
            java.lang.String r3 = "schedulePreloadFragmentList interrupted, exception = "
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L5f:
            java.lang.String r0 = "schedulePreloadFragmentList count down time out!"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L68:
            eku r0 = new eku
            r0.<init>()
            com.huawei.haf.handler.HandlerExecutor.e(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.TextLinkNestingLayout.a(java.util.List):void");
    }

    static /* synthetic */ void a(CountDownLatch countDownLatch, int i, SingleTextContent[] singleTextContentArr, SingleTextContent singleTextContent, BasePageResTrigger[] basePageResTriggerArr, BasePageResTrigger basePageResTrigger, List list) {
        if (koq.b(list)) {
            countDownLatch.countDown();
            LogUtil.a("TextLinkNestingLayout", "schedulePreloadFragmentList count down empty at position =", Integer.valueOf(i));
            return;
        }
        singleTextContentArr[i] = singleTextContent;
        basePageResTriggerArr[i] = basePageResTrigger;
        basePageResTrigger.setCacheBeansList(list);
        countDownLatch.countDown();
        LogUtil.a("TextLinkNestingLayout", "schedulePreloadFragmentList count down at position =", Integer.valueOf(i));
    }

    private List<SingleTextContent> b(List<SingleTextContent> list) {
        ArrayList arrayList = new ArrayList();
        for (SingleTextContent singleTextContent : list) {
            if (singleTextContent != null) {
                if (singleTextContent.getPositionId() <= 0) {
                    LogUtil.h("TextLinkNestingLayout", "addValidSingleTextContents rule out singleTextContent theme = ", singleTextContent.getTheme(), ", positionId = ", Integer.valueOf(singleTextContent.getPositionId()));
                } else {
                    arrayList.add(singleTextContent);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void d(SingleTextContent[] singleTextContentArr, BasePageResTrigger[] basePageResTriggerArr) {
        this.o.clear();
        this.g.clear();
        if (singleTextContentArr == null || singleTextContentArr.length <= 0) {
            LogUtil.h("TextLinkNestingLayout", "singleTextContents is empty, hide TextLinkNestingLayout");
            setVisibility(8);
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < singleTextContentArr.length; i2++) {
            SingleTextContent singleTextContent = singleTextContentArr[i2];
            BasePageResTrigger basePageResTrigger = basePageResTriggerArr[i2];
            if (singleTextContent != null && basePageResTrigger != null) {
                KnitFragment newInstance = KnitFragment.newInstance("", basePageResTrigger);
                newInstance.setIsBottomPadding(false);
                this.g.add(newInstance);
                singleTextContent.setOnClickListener(new a(this, i));
                this.o.add(singleTextContent);
                i++;
            }
        }
        if (koq.b(this.g)) {
            LogUtil.h("TextLinkNestingLayout", "mFragmentList is empty, hide TextLinkNestingLayout");
            setVisibility(8);
            return;
        }
        eiv.e(this.k, this.m);
        this.h = new boolean[this.o.size()];
        if (this.q == null) {
            this.q = new e(this);
        }
        nsy.cMa_(this.n, this.q);
        this.p.d(this.o);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        boolean z = (resourceBriefInfo == null || this.l == null || !resourceBriefInfo.getResourceId().equals(this.l.getResourceId())) ? false : true;
        LogUtil.a("TextLinkNestingLayout", "isOwnedByBriefInfo: " + z);
        return z;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.l;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    private FragmentTransaction getTransaction() {
        if (this.i == null) {
            LogUtil.h("TextLinkNestingLayout", "mFragmentManager not init by setFragmentManger, generate by top activity!");
            Activity wa_ = BaseApplication.wa_();
            if (!(wa_ instanceof FragmentActivity)) {
                LogUtil.b("TextLinkNestingLayout", "switchFragment failed, topActivity is not FragmentActivity: ", wa_);
                return null;
            }
            try {
                this.i = FragmentManager.findFragment(this).getChildFragmentManager();
            } catch (IllegalStateException unused) {
                LogUtil.h("TextLinkNestingLayout", "find fragment fail");
                this.i = ((FragmentActivity) wa_).getSupportFragmentManager();
            }
        }
        return this.i.beginTransaction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        int i2;
        if (koq.b(this.g, i)) {
            LogUtil.b("TextLinkNestingLayout", "switchFragment failed, cause position = ", Integer.valueOf(i), ", total fragments' size = ", Integer.valueOf(this.g.size()));
            return;
        }
        KnitFragment knitFragment = this.g.get(i);
        if (knitFragment == this.d) {
            return;
        }
        if (!koq.b(this.o, this.e)) {
            this.o.get(this.e).setSelected(false);
        }
        if (!koq.b(this.o, i)) {
            this.o.get(i).setSelected(true);
        }
        this.p.notifyDataSetChanged();
        FragmentTransaction transaction = getTransaction();
        if (transaction == null) {
            LogUtil.b("TextLinkNestingLayout", "switchFragment failed, transaction is null!");
            return;
        }
        Fragment fragment = this.d;
        if (fragment != null) {
            transaction.hide(fragment);
        }
        if (!this.h[i] && (i2 = this.b) != 0) {
            transaction.add(i2, knitFragment);
            this.h[i] = true;
        }
        transaction.show(knitFragment);
        this.d = knitFragment;
        this.e = i;
        transaction.commitNowAllowingStateLoss();
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        super.onResume();
        if (this.f || !this.j) {
            return;
        }
        d();
        c(this.f2892a);
        this.j = false;
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
        super.onStop();
        d(true);
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        List<KnitFragment> list = this.g;
        if (list != null) {
            list.clear();
            this.d = null;
        }
    }

    private void a() {
        ExtendHandler extendHandler = this.t;
        if (extendHandler == null) {
            return;
        }
        extendHandler.sendEmptyMessage(1001, 60000L);
    }

    private void d() {
        ExtendHandler extendHandler = this.t;
        if (extendHandler == null) {
            return;
        }
        extendHandler.removeMessages(1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        boolean isEmpty = this.g.isEmpty();
        d();
        if (this.f || !(!isEmpty)) {
            return;
        }
        if (z) {
            a();
        } else {
            c();
        }
    }

    private void c() {
        List<KnitFragment> list = this.g;
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("TextLinkNestingLayout", "cleanAllFragment");
        for (KnitFragment knitFragment : this.g) {
        }
        this.g.clear();
        this.j = true;
    }

    static final class a implements View.OnClickListener {
        private WeakReference<TextLinkNestingLayout> b;
        private final int e;

        public a(TextLinkNestingLayout textLinkNestingLayout, int i) {
            this.b = new WeakReference<>(textLinkNestingLayout);
            this.e = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TextLinkNestingLayout textLinkNestingLayout = this.b.get();
            if (textLinkNestingLayout != null) {
                MarketingBiUtils.e(22, textLinkNestingLayout.s, 4);
                textLinkNestingLayout.d(this.e);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static class b extends BaseHandlerCallback<TextLinkNestingLayout> {
        b(TextLinkNestingLayout textLinkNestingLayout) {
            super(textLinkNestingLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: aqi_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(TextLinkNestingLayout textLinkNestingLayout, Message message) {
            LogUtil.a("TextLinkNestingLayout", "TextLinkNestingLayout, message is: ", Integer.valueOf(message.what));
            if (message.what != 1001) {
                LogUtil.a("TextLinkNestingLayout", "TextLinkNestingHandler, unknown message code");
                return false;
            }
            if (textLinkNestingLayout == null) {
                return true;
            }
            textLinkNestingLayout.d(false);
            return true;
        }
    }

    static class e implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<TextLinkNestingLayout> d;

        public e(TextLinkNestingLayout textLinkNestingLayout) {
            this.d = new WeakReference<>(textLinkNestingLayout);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            TextLinkNestingLayout textLinkNestingLayout = this.d.get();
            if (textLinkNestingLayout != null) {
                textLinkNestingLayout.d(0);
                nsy.cMf_(textLinkNestingLayout.n, this);
            } else {
                LogUtil.h("TextLinkNestingLayout", "onScrollChanged layout is null");
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        nsy.cMf_(this.n, this.q);
    }
}
