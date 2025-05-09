package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.main.api.MainNavigationApi;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.soical.NewSocialFragment;
import com.huawei.ui.main.stories.soical.SocialFragmentHelper;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.main.stories.soical.views.RecyclerItemDecoration;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes7.dex */
public class rxq {
    private static final String d = "com.huawei.ui.main.stories.soical.views.BannerInteractor";

    /* renamed from: a, reason: collision with root package name */
    private HealthViewPager f16952a;
    private List<ImageView> b;
    private final WeakReference<NewSocialFragment> c;
    private List<MessageObject> e;
    private final Context f;
    private HealthViewPager h;
    private rxu i;
    private HealthDotsPageIndicator j;
    private int[] l;
    private View m;
    private HealthRecycleView n;
    private final OperationInteractorsApi o;
    private final OperationUtilsApi s;
    private final OperationWebActivityIntentBuilderApi t;
    private final Set<String> k = new HashSet();
    private final Pair<Integer, Integer> q = BaseActivity.getSafeRegionWidth();
    private volatile int g = 41;

    public int d(int i) {
        if (i == 37 || i == 38) {
            return i;
        }
        return 41;
    }

    public int e(boolean z, int i) {
        if (z) {
            return i;
        }
        return 0;
    }

    public rxq(NewSocialFragment newSocialFragment, OperationInteractorsApi operationInteractorsApi, OperationWebActivityIntentBuilderApi operationWebActivityIntentBuilderApi, OperationUtilsApi operationUtilsApi) {
        this.s = operationUtilsApi;
        this.o = operationInteractorsApi;
        this.t = operationWebActivityIntentBuilderApi;
        this.c = new WeakReference<>(newSocialFragment);
        this.f = newSocialFragment.getActivity();
    }

    public void c(HealthViewPager healthViewPager, HealthViewPager healthViewPager2, HealthRecycleView healthRecycleView, HealthDotsPageIndicator healthDotsPageIndicator) {
        this.f16952a = healthViewPager;
        this.h = healthViewPager2;
        this.n = healthRecycleView;
        this.j = healthDotsPageIndicator;
    }

    public void dTA_(View view) {
        this.m = view;
    }

    public void e(List<ImageView> list, List<MessageObject> list2) {
        this.b = list;
        this.e = list2;
    }

    public OperationUtilsApi e() {
        return this.s;
    }

    public void dTy_(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: rxr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                rxq.this.dTu_(view2);
            }
        });
    }

    /* synthetic */ void dTu_(View view) {
        SocialFragmentHelper.a(this.f, "0", "0", "0");
        GRSManager.a(this.f).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: rxq.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a(rxq.d, "GRSManager onCallBackSuccess ACTIVITY_KEY");
                rxq.this.a(str);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(String str) {
        this.o.setNeedUpdateActivity(this.f, true);
        try {
            this.f.startActivity(this.t.builder(this.f, str + ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + Constants.ACTIVITY_HTML).setFlags(268435456).build());
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(d, "ActivityNotFoundException", e2.getMessage());
        }
    }

    public void dTz_(mdf mdfVar, TextView textView) {
        if (eiv.b()) {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            textView.setLayoutParams(layoutParams);
        }
        String p = mdfVar.p();
        if (TextUtils.equals(p, String.valueOf(0))) {
            textView.setText(mdfVar.z());
            textView.setVisibility(0);
        } else if (TextUtils.equals(p, String.valueOf(1))) {
            textView.setText(mdfVar.aa());
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.huawei.health.messagecenter.model.MessageObject r16, long r17, int r19) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rxq.b(com.huawei.health.messagecenter.model.MessageObject, long, int):void");
    }

    public void c(String str) {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
    }

    public static class e implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MessageObject> f16955a;
        private final WeakReference<rxq> b;

        public e(rxq rxqVar, MessageObject messageObject) {
            this.b = new WeakReference<>(rxqVar);
            this.f16955a = new WeakReference<>(messageObject);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MessageObject messageObject = this.f16955a.get();
            rxq rxqVar = this.b.get();
            if (messageObject == null || nsn.o() || rxqVar == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            Object tag = view.getTag(R.layout.layout_new_social_cardview);
            long longValue = tag instanceof Long ? ((Long) tag).longValue() : 0L;
            LogUtil.a(rxq.d, "tmpObj: ", Long.valueOf(longValue));
            rxqVar.e(messageObject, longValue);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void e(MessageObject messageObject, long j) {
        String msgId = messageObject.getMsgId();
        String msgTitle = messageObject.getMsgTitle();
        String module = messageObject.getModule();
        String detailUri = messageObject.getDetailUri();
        boolean z = messageObject instanceof pgq;
        pgq pgqVar = (pgq) messageObject;
        if (z && pgqVar.b()) {
            b(messageObject, j, 2);
            if (!TextUtils.equals(pgqVar.d().p(), String.valueOf(1))) {
                if (z) {
                    bzw.e().goToTaskPage(this.f, pgqVar.d());
                    return;
                }
                return;
            } else {
                NewSocialFragment newSocialFragment = this.c.get();
                if (newSocialFragment != null && newSocialFragment.isVisible() && newSocialFragment.isAdded()) {
                    newSocialFragment.c(pgqVar);
                    return;
                }
                return;
            }
        }
        if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            SocialFragmentHelper.a(this.f, msgId, msgTitle, module);
            try {
                this.f.startActivity(this.t.builder(this.f, detailUri).setBI(msgId, msgTitle, "SOCIALBANNER", "SHOW_TIME_BI").build());
                return;
            } catch (ActivityNotFoundException e2) {
                LogUtil.b(d, "ActivityNotFoundException", e2.getMessage());
                return;
            }
        }
        b(messageObject, j, 2);
        c(b(detailUri, messageObject));
    }

    public String b(String str, MessageObject messageObject) {
        String msgId = messageObject.getMsgId();
        if (messageObject instanceof pgq) {
            pgq pgqVar = (pgq) messageObject;
            if (pgqVar.b()) {
                msgId = pgqVar.d().h();
            } else {
                msgId = pgqVar.getMsgId();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        return sb.toString() + "1002&resourceName=" + messageObject.getMsgTitle() + "&resourceId=" + msgId + "&pullOrder=1&itemId=" + msgId;
    }

    private boolean j() {
        return this.g != 38;
    }

    public boolean c(mdf mdfVar) {
        String p = mdfVar.p();
        return TextUtils.equals(p, String.valueOf(0)) || TextUtils.equals(p, String.valueOf(1)) || mdfVar.ac() == 1;
    }

    public void c(int i) {
        this.f16952a.getAdapter().notifyDataSetChanged();
        if (i != this.g) {
            this.f16952a.setPageTransformer(false, null);
        }
        this.h.getAdapter().notifyDataSetChanged();
        if (i != this.g) {
            this.h.setPageTransformer(false, null);
        }
        this.f16952a.setVisibility(8);
        this.h.setVisibility(8);
        this.j.setVisibility(8);
        this.n.getAdapter().notifyDataSetChanged();
        this.n.setVisibility(8);
    }

    public void b(rxu rxuVar) {
        this.i = rxuVar;
    }

    public void c(List<MessageObject> list) {
        int i = this.g;
        if (koq.b(list)) {
            this.g = 41;
        } else {
            MessageObject messageObject = list.get(0);
            if (messageObject instanceof pgq) {
                this.g = d(((pgq) messageObject).e());
            } else {
                this.g = 41;
            }
        }
        rxh.e(this.g);
        int i2 = this.g;
        if (i2 == 37) {
            this.f16952a.getAdapter().notifyDataSetChanged();
            this.f16952a.setPageTransformer(false, null);
            rxu rxuVar = this.i;
            if (rxuVar != null) {
                this.f16952a.removeOnPageChangeListener(rxuVar);
                this.i.d();
            }
            this.f16952a.setVisibility(8);
            this.h.getAdapter().notifyDataSetChanged();
            this.h.setVisibility(0);
            this.j.setVisibility(8);
            this.n.getAdapter().notifyDataSetChanged();
            this.n.setVisibility(8);
            return;
        }
        if (i2 == 38) {
            this.f16952a.setVisibility(8);
            this.f16952a.getAdapter().notifyDataSetChanged();
            this.f16952a.setPageTransformer(false, null);
            rxu rxuVar2 = this.i;
            if (rxuVar2 != null) {
                this.f16952a.removeOnPageChangeListener(rxuVar2);
                this.i.d();
            }
            this.h.setVisibility(8);
            this.h.getAdapter().notifyDataSetChanged();
            this.j.setVisibility(8);
            this.n.getAdapter().notifyDataSetChanged();
            this.n.setVisibility(0);
            return;
        }
        c(i);
    }

    public void f() {
        if (this.b == null || this.j == null || !j()) {
            return;
        }
        if (this.b.size() > 1) {
            if (nsn.ag(this.f) && this.b.size() <= 2) {
                this.j.c();
                return;
            } else {
                this.j.a(4000);
                return;
            }
        }
        this.j.c();
    }

    public void c() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.j;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.c();
        }
    }

    public void dTw_(ViewGroup viewGroup) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (viewGroup == null || koq.b(this.b)) {
            LogUtil.h(d, "resizeViewPager() mADImageShowList is empty.");
            return;
        }
        boolean z = viewGroup instanceof HealthViewPager;
        boolean z2 = viewGroup instanceof RecyclerView;
        int h = nsn.h(this.f);
        int i6 = 0;
        try {
            i = (int) this.f.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b(d, "resizeViewPager() dimen id is not found.");
            i = 0;
        }
        int e2 = rxh.e(this.f);
        if (nsn.ag(this.f)) {
            int b2 = nrr.b(this.f);
            int size = this.b.size();
            if (size == 1) {
                i3 = e(z2, b2);
                dTC_(z, viewGroup, false);
                i2 = (h - e2) / 2;
            } else {
                if (size == 2) {
                    i4 = ((h - (e2 * 2)) - b2) / 2;
                    dTC_(z, viewGroup, false);
                    i5 = (h - e2) - i4;
                    i6 = b2;
                    LogUtil.a(d, "resizePageMargin ", Integer.valueOf(i6), " startPadding ", Integer.valueOf(i4), " endPadding ", Integer.valueOf(i5));
                    dTB_(viewGroup, i6, i4, i5);
                }
                if (size >= 3) {
                    dTC_(z, viewGroup, true);
                    i2 = (h - e2) / 2;
                    i3 = b2;
                } else {
                    LogUtil.h(d, "resizeViewPager() imageListSize <= 0.");
                    i5 = 0;
                    i4 = i5;
                    LogUtil.a(d, "resizePageMargin ", Integer.valueOf(i6), " startPadding ", Integer.valueOf(i4), " endPadding ", Integer.valueOf(i5));
                    dTB_(viewGroup, i6, i4, i5);
                }
            }
        } else {
            LogUtil.a(d, "not bigCD");
            int intValue = ((Integer) this.q.second).intValue() + ((int) this.f.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) + ((Integer) this.q.first).intValue();
            int e3 = e(h, e2, i);
            dTx_(viewGroup, z);
            i2 = e3;
            i3 = intValue;
        }
        i5 = i2;
        i6 = i3;
        i4 = i5;
        LogUtil.a(d, "resizePageMargin ", Integer.valueOf(i6), " startPadding ", Integer.valueOf(i4), " endPadding ", Integer.valueOf(i5));
        dTB_(viewGroup, i6, i4, i5);
    }

    public void dTC_(boolean z, ViewGroup viewGroup, boolean z2) {
        if (z) {
            ((HealthViewPager) viewGroup).setIsScroll(z2);
        }
    }

    public int e(int i, int i2, int i3) {
        if (this.g == 37) {
            return (i - i2) / 2;
        }
        return ((Integer) this.q.first).intValue() + i3;
    }

    public void dTx_(ViewGroup viewGroup, boolean z) {
        int size = this.b.size();
        if (z) {
            ((HealthViewPager) viewGroup).setIsScroll(size > 1);
        }
    }

    public void dTB_(ViewGroup viewGroup, int i, int i2, int i3) {
        boolean z = viewGroup instanceof RecyclerView;
        if (viewGroup instanceof HealthViewPager) {
            try {
                ((HealthViewPager) viewGroup).setPageMargin(this.g == 41 ? i : 0);
            } catch (IllegalStateException unused) {
                LogUtil.b(d, "AdViewPager setPageMargin llegalStateException.");
                return;
            }
        }
        if (z) {
            RecyclerView recyclerView = (RecyclerView) viewGroup;
            for (int itemDecorationCount = recyclerView.getItemDecorationCount() - 1; itemDecorationCount >= 0; itemDecorationCount--) {
                recyclerView.removeItemDecorationAt(itemDecorationCount);
            }
            recyclerView.addItemDecoration(new RecyclerItemDecoration(i, 0, new int[]{i, 0, i, 0}));
        }
        if (this.g == 38) {
            viewGroup.setPadding(0, 0, 0, 0);
        } else {
            viewGroup.setPadding(i2, 0, i3, 0);
        }
    }

    public void b(int i) {
        String str = d;
        LogUtil.a(str, "updateAdSelector, mBannerStyle: ", Integer.valueOf(this.g), "size: ", Integer.valueOf(i));
        if (!j()) {
            this.f16952a.setVisibility(8);
            this.h.setVisibility(8);
            this.j.setVisibility(8);
            return;
        }
        if (i == 1) {
            this.f16952a.setVisibility(8);
            this.h.setVisibility(0);
            this.j.setViewPager(this.h);
            this.j.setVisibility(8);
            i();
            return;
        }
        if (i == 2) {
            if (this.g == 37 || (this.g == 41 && !nsn.ag(this.f))) {
                this.f16952a.setVisibility(0);
                this.h.setVisibility(8);
                this.j.setViewPager(this.f16952a);
                this.j.setVisibility(0);
                return;
            }
            this.f16952a.setVisibility(8);
            this.h.setVisibility(0);
            this.j.setViewPager(this.h);
            this.j.setVisibility(8);
            return;
        }
        if (i == 3) {
            this.f16952a.setVisibility(0);
            this.h.setVisibility(8);
            this.j.setViewPager(this.f16952a);
            this.j.setVisibility(0);
            i();
            return;
        }
        LogUtil.c(str, "mAdImageShowList size is illegal");
        this.f16952a.setVisibility(0);
        this.h.setVisibility(8);
        this.j.setViewPager(this.f16952a);
        this.j.setVisibility(0);
    }

    private void i() {
        HandlerExecutor.d(new Runnable() { // from class: rxq.3
            @Override // java.lang.Runnable
            public void run() {
                if (rxq.this.h == null || rxq.this.h.getVisibility() != 0) {
                    LogUtil.h(rxq.d, "mCantLoopViewPager is not visible any more");
                    return;
                }
                NewSocialFragment newSocialFragment = rxq.this.c != null ? (NewSocialFragment) rxq.this.c.get() : null;
                if (newSocialFragment == null) {
                    LogUtil.b(rxq.d, "setAllBannerShowEvent fragment is null");
                    return;
                }
                int size = rxq.this.e != null ? rxq.this.e.size() : 0;
                LogUtil.a(rxq.d, "setAllBannerShowEvent, size: ", Integer.valueOf(size));
                for (int i = 0; i < size; i++) {
                    newSocialFragment.d(i);
                }
            }
        }, 500L);
    }

    public static class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<rxq> f16954a;

        public b(rxq rxqVar) {
            this.f16954a = new WeakReference<>(rxqVar);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            rxq rxqVar = this.f16954a.get();
            if (rxqVar != null) {
                rxqVar.dTs_(view, rxqVar.m);
                ViewClickInstrumentation.clickOnView(view);
            } else {
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    public static class c implements View.OnClickListener {
        private final WeakReference<rxq> e;

        c(rxq rxqVar) {
            this.e = new WeakReference<>(rxqVar);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            rxq rxqVar = this.e.get();
            if (rxqVar == null) {
                ViewClickInstrumentation.clickOnView(view);
            } else {
                rxqVar.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    public void dTs_(View view, View view2) {
        if (nsn.cLk_(view)) {
            return;
        }
        if (view2 != null && view2.getVisibility() == 0) {
            view2.performClick();
        }
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            b();
            return;
        }
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof BaseActivity) {
            nkx.cwZ_(new c(this), (BaseActivity) activity, LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode(), "").onClick(view);
        }
    }

    public void b() {
        MainNavigationApi mainNavigationApi = (MainNavigationApi) Services.a("Main", MainNavigationApi.class);
        if (mainNavigationApi != null) {
            mainNavigationApi.goToAchieveKaka(this.f);
        }
    }

    public void dTD_(View view, CustomTitleBar customTitleBar, View view2) {
        if (view == null) {
            return;
        }
        boolean b2 = sdl.b();
        String str = d;
        LogUtil.a(str, "canShowDiscoverKaka", Boolean.valueOf(!b2));
        if (!b2) {
            view.setVisibility(4);
            return;
        }
        String b3 = SharedPreferenceManager.b(this.f, Integer.toString(10005), "discover_first_login_show_kaka_tips");
        if (!TextUtils.isEmpty(b3) && !"true".equals(b3)) {
            view.setVisibility(4);
            return;
        }
        Context context = this.f;
        if (!(context instanceof Activity) || ((Activity) context).isDestroyed() || ((Activity) this.f).isFinishing()) {
            return;
        }
        LogUtil.a(str, "PopupWindow = show");
        View rightSoftKey = customTitleBar.getRightSoftKey();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            float x = (((customTitleBar.getX() + view2.getX()) + rightSoftKey.getX()) + (rightSoftKey.getMeasuredWidth() / 2.0f)) - (view.getMeasuredWidth() / 2.0f);
            if (LanguageUtil.bc(this.f)) {
                marginLayoutParams.rightMargin = (int) ((eie.d() - x) - view.getMeasuredWidth());
            } else {
                marginLayoutParams.leftMargin = (int) x;
            }
            view.setLayoutParams(marginLayoutParams);
        }
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
        view.setOnClickListener(new View.OnClickListener() { // from class: rxt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                rxq.this.dTv_(view3);
            }
        });
    }

    /* synthetic */ void dTv_(View view) {
        view.setVisibility(4);
        view.setOnClickListener(null);
        SharedPreferenceManager.e(this.f, Integer.toString(10005), "discover_first_login_show_kaka_tips", "false", (StorageParams) null);
        ViewClickInstrumentation.clickOnView(view);
    }

    public int d() {
        return this.g;
    }

    public View dTr_(Drawable drawable, ViewGroup viewGroup, int i, PPSNativeView pPSNativeView) {
        int i2;
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.width = -1;
        int e2 = rxh.e(this.f);
        if (rxh.e(this.l)) {
            int[] iArr = this.l;
            i2 = (iArr[1] * e2) / iArr[0];
        } else {
            i2 = (e2 * 9) / 21;
        }
        layoutParams.height = i2;
        viewGroup.setLayoutParams(layoutParams);
        if (koq.d(this.e, i) && this.e.get(i).getNativeAd() != null) {
            pPSNativeView.register(this.e.get(i).getNativeAd());
            pPSNativeView.setOnNativeAdClickListener(new PPSNativeView.OnNativeAdClickListener() { // from class: rxs
                @Override // com.huawei.openalliance.ad.views.PPSNativeView.OnNativeAdClickListener
                public final void onClick(View view) {
                    rxq.this.dTt_(view);
                }
            });
        }
        RelativeLayout relativeLayout = (RelativeLayout) pPSNativeView.findViewById(R.id.banner);
        ViewGroup.LayoutParams layoutParams2 = relativeLayout.getLayoutParams();
        layoutParams2.width = e2;
        relativeLayout.setLayoutParams(layoutParams2);
        HealthCardView healthCardView = (HealthCardView) pPSNativeView.findViewById(R.id.card_view);
        ViewGroup.LayoutParams layoutParams3 = healthCardView.getLayoutParams();
        layoutParams3.width = e2;
        layoutParams3.height = i2;
        healthCardView.setLayoutParams(layoutParams3);
        ImageView imageView = (ImageView) pPSNativeView.findViewById(R.id.card_image);
        ViewGroup.LayoutParams layoutParams4 = imageView.getLayoutParams();
        layoutParams4.width = e2;
        layoutParams4.height = i2;
        imageView.setLayoutParams(layoutParams4);
        imageView.setImageDrawable(drawable);
        if (this.g == 37 && this.f16952a.getCurrentItem() != i) {
            pPSNativeView.setScaleX(0.9f);
            pPSNativeView.setScaleY(0.9f);
        }
        return pPSNativeView;
    }

    /* synthetic */ void dTt_(View view) {
        LogUtil.a(d, "OnNativeAdClickListener");
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return;
        }
        Context context = this.f;
        Toast.makeText(context, context.getResources().getString(R.string._2130841392_res_0x7f020f30), 0).show();
    }

    public void c(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        this.l = Arrays.copyOf(iArr, iArr.length);
    }
}
