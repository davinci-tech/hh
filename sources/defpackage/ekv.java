package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.TopBannerTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ekv;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes3.dex */
public class ekv extends HealthPagerAdapter {
    private long b;
    private Context c;
    private List<ResourceBriefInfo> d;
    private final OperationUtilsApi h;
    private HealthViewPager j;
    private Set<String> g = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private WeakHashMap<Integer, Drawable> f12073a = new WeakHashMap<>();
    private Handler e = new Handler(Looper.getMainLooper());

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ekv(List<ResourceBriefInfo> list, HealthViewPager healthViewPager, Context context) {
        this.d = list;
        LogUtil.a("TopBannerLayoutAdapter", "enter TopBannerLayoutAdapter:", Integer.valueOf(list.size()));
        this.j = healthViewPager;
        this.c = context;
        this.b = System.currentTimeMillis();
        this.h = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
    }

    public void d(List<ResourceBriefInfo> list) {
        if (koq.b(list)) {
            LogUtil.h("TopBannerLayoutAdapter", "list is empty");
        } else {
            LogUtil.a("TopBannerLayoutAdapter", "setList", Integer.valueOf(list.size()));
            this.d = list;
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<ResourceBriefInfo> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("TopBannerLayoutAdapter", "isOutOfBounds");
            return LayoutInflater.from(this.c).inflate(R.layout.layout_ad_cardview, (ViewGroup) null);
        }
        TopBannerTemplate topBannerTemplate = (TopBannerTemplate) new Gson().fromJson(this.d.get(i).getContent().getContent(), TopBannerTemplate.class);
        View aqm_ = aqm_(topBannerTemplate, this.j, i);
        if (viewGroup instanceof HealthViewPager) {
            ((HealthViewPager) viewGroup).addView(aqm_);
        }
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof BaseActivity) {
            aqm_.setOnClickListener(nkx.cwZ_(aqn_(topBannerTemplate, this.d.get(i), i), (BaseActivity) activity, this.h.isNotSupportBrowseUrl(topBannerTemplate.getLinkValue()), AnalyticsValue.SOCIAL_1070004.value()));
            return aqm_;
        }
        aqm_.setOnClickListener(aqn_(topBannerTemplate, this.d.get(i), i));
        return aqm_;
    }

    private View.OnClickListener aqn_(final TopBannerTemplate topBannerTemplate, final ResourceBriefInfo resourceBriefInfo, final int i) {
        return new View.OnClickListener() { // from class: ekv.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.h("TopBannerLayoutAdapter", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ekv.this.a(2, resourceBriefInfo, i);
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    String c = eil.c(topBannerTemplate.getLinkValue(), resourceBriefInfo.getCategory());
                    if (!TextUtils.isEmpty(c)) {
                        marketRouterApi.router(ekv.this.e(c, 1003, resourceBriefInfo, i + 1));
                    } else {
                        LogUtil.h("TopBannerLayoutAdapter", "detailUri is null");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str, int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        return sb.toString() + i + "&resourceName=" + resourceBriefInfo.getResourceName() + "&resourceId=" + resourceBriefInfo.getResourceId() + "&pullOrder=" + i2;
    }

    private void aqo_(TopBannerTemplate topBannerTemplate, int i, ImageView imageView, WeakHashMap<Integer, Drawable> weakHashMap, int i2) {
        Drawable drawable = this.f12073a.get(Integer.valueOf(i));
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            imageView.setImageResource(R.drawable.shape_device_banner_bg);
            ThreadPoolManager.d().execute(new AnonymousClass5(i2, topBannerTemplate, weakHashMap, i, imageView));
        }
    }

    /* renamed from: ekv$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f12075a;
        final /* synthetic */ int b;
        final /* synthetic */ WeakHashMap d;
        final /* synthetic */ ImageView e;
        final /* synthetic */ TopBannerTemplate i;

        AnonymousClass5(int i, TopBannerTemplate topBannerTemplate, WeakHashMap weakHashMap, int i2, ImageView imageView) {
            this.b = i;
            this.i = topBannerTemplate;
            this.d = weakHashMap;
            this.f12075a = i2;
            this.e = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            final Drawable cId_;
            if (this.b <= 0) {
                cId_ = nrf.cIb_(BaseApplication.getContext(), this.i.getPicture());
            } else {
                cId_ = nrf.cId_(BaseApplication.getContext(), this.i.getPicture(), this.b);
            }
            Handler handler = ekv.this.e;
            final WeakHashMap weakHashMap = this.d;
            final int i = this.f12075a;
            final ImageView imageView = this.e;
            handler.post(new Runnable() { // from class: ekt
                @Override // java.lang.Runnable
                public final void run() {
                    ekv.AnonymousClass5.aqp_(weakHashMap, i, cId_, imageView);
                }
            });
        }

        static /* synthetic */ void aqp_(WeakHashMap weakHashMap, int i, Drawable drawable, ImageView imageView) {
            weakHashMap.put(Integer.valueOf(i), drawable);
            imageView.setImageDrawable(drawable);
        }
    }

    private View aqm_(TopBannerTemplate topBannerTemplate, HealthViewPager healthViewPager, int i) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.layout_ad_cardview, (ViewGroup) null);
        if (!(healthViewPager.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return inflate;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        layoutParams.width = eie.b(this.c, 1003);
        LogUtil.a("TopBannerLayoutAdapter", "buildCardViewLayout width:", Integer.valueOf(layoutParams.width));
        int d = eiv.d(this.c, 0);
        int i2 = (d * 9) / 16;
        layoutParams.height = i2;
        healthViewPager.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.banner);
        if (relativeLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams2.width = d;
            relativeLayout.setLayoutParams(layoutParams2);
        }
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.card_view);
        if (healthCardView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) healthCardView.getLayoutParams();
            layoutParams3.width = d;
            layoutParams3.height = i2;
            healthCardView.setLayoutParams(layoutParams3);
        }
        healthCardView.setForceClipRoundCorner(true);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.item_one_landscape_corner);
        if (topBannerTemplate.isCornerVisibility()) {
            d(healthTextView, topBannerTemplate);
        }
        aqo_(topBannerTemplate, i, (ImageView) inflate.findViewById(R.id.card_image), this.f12073a, nrf.d);
        return inflate;
    }

    public static void d(HealthTextView healthTextView, TopBannerTemplate topBannerTemplate) {
        if (topBannerTemplate == null) {
            LogUtil.h("TopBannerLayoutAdapter", "content = null");
            return;
        }
        if (!a(healthTextView, topBannerTemplate)) {
            LogUtil.h("TopBannerLayoutAdapter", "setCornerShow can not show");
            return;
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, eiv.d(12.0f), eiv.d(12.0f)});
            gradientDrawable.setColor(Color.parseColor(topBannerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("TopBannerLayoutAdapter", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(topBannerTemplate.getCorner());
        healthTextView.setVisibility(0);
    }

    private static boolean a(HealthTextView healthTextView, TopBannerTemplate topBannerTemplate) {
        if (healthTextView == null) {
            LogUtil.h("TopBannerLayoutAdapter", "textView is null");
            return false;
        }
        if (topBannerTemplate.isCornerVisibility() && !TextUtils.isEmpty(topBannerTemplate.getCorner()) && !TextUtils.isEmpty(topBannerTemplate.getCornerColor())) {
            return true;
        }
        healthTextView.setVisibility(8);
        return false;
    }

    public void a(int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        if (resourceBriefInfo == null) {
            LogUtil.b("TopBannerLayoutAdapter", "biEvent resourceBriefInfo is null");
            return;
        }
        String resourceId = resourceBriefInfo.getResourceId();
        if (i == 1 && this.g.contains(resourceId)) {
            LogUtil.a("TopBannerLayoutAdapter", "has shown id: ", resourceId);
            return;
        }
        LogUtil.a("TopBannerLayoutAdapter", "biEvent event: ", Integer.valueOf(i), " resourceId: ", resourceId);
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 1003);
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.b));
            this.b = System.currentTimeMillis();
        }
        if (i == 1) {
            this.g.add(resourceId);
        }
        hashMap.put("algId", "");
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(this.c, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }
}
