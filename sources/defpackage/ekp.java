package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
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
import defpackage.ekp;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class ekp extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f12069a;
    private Context b;
    private GridTemplate c;
    private int e;
    private double f;
    private int g;
    private boolean i;
    private int j;
    private ResourceBriefInfo l;
    private HealthViewPager m;
    private List<SingleGridContent> o;
    private Set<String> n = new HashSet();
    private Handler h = new Handler(Looper.getMainLooper());
    private long d = System.currentTimeMillis();
    private final OperationUtilsApi k = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ekp(HealthViewPager healthViewPager, Context context) {
        this.m = healthViewPager;
        this.b = context;
    }

    public void c(int i, GridTemplate gridTemplate, ResourceBriefInfo resourceBriefInfo, boolean z) {
        this.g = i;
        this.l = resourceBriefInfo;
        this.e = resourceBriefInfo.getContentType();
        this.i = z;
        this.c = gridTemplate;
        if (koq.b(gridTemplate.getGridContents())) {
            LogUtil.h("SportBannerLayoutAdapter", "list is empty");
        } else {
            LogUtil.a("SportBannerLayoutAdapter", "setList", gridTemplate.getGridContents());
            this.o = gridTemplate.getGridContents();
        }
    }

    public void b(int i) {
        this.j = i;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.c(this.o)) {
            return this.o.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (!(obj instanceof View) || koq.b(this.o, i)) {
            return;
        }
        ((HealthViewPager) viewGroup).removeView((View) obj);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View apZ_;
        if (koq.b(this.o, i)) {
            LogUtil.h("SportBannerLayoutAdapter", "isOutOfBounds");
            return LayoutInflater.from(this.b).inflate(R.layout.item_sport_banner, (ViewGroup) null);
        }
        SingleGridContent singleGridContent = this.o.get(i);
        LogUtil.a("SportBannerLayoutAdapter", "instantiateItem:", Integer.valueOf(i));
        int i2 = this.e;
        if (i2 == 51) {
            apZ_ = apX_(singleGridContent, this.m, i);
        } else if (i2 == 52) {
            apZ_ = apW_(singleGridContent, this.m, i);
        } else if (i2 == 98) {
            apZ_ = apY_(singleGridContent, this.m, nsn.ag(BaseApplication.getActivity()));
        } else {
            apZ_ = apZ_(singleGridContent, this.m, i);
        }
        if (viewGroup instanceof HealthViewPager) {
            viewGroup.addView(apZ_);
        }
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof BaseActivity) {
            apZ_.setOnClickListener(nkx.cwZ_(aqa_(singleGridContent, i), (BaseActivity) activity, this.k.isNotSupportBrowseUrl(singleGridContent.getLinkValue()), ""));
            return apZ_;
        }
        apZ_.setOnClickListener(aqa_(singleGridContent, i));
        return apZ_;
    }

    private View apY_(SingleGridContent singleGridContent, HealthViewPager healthViewPager, boolean z) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.layout_member_center_ad, (ViewGroup) null);
        if (!(healthViewPager.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return inflate;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        layoutParams.width = this.b.getResources().getDisplayMetrics().widthPixels;
        int i = layoutParams.width;
        int i2 = (int) (i * this.f);
        ObserverManagerUtil.c("IMMERSION_IMAGE_HEIGHT", Integer.valueOf(i2));
        layoutParams.height = i2;
        healthViewPager.setLayoutParams(layoutParams);
        inflate.findViewById(R.id.banner_projection).setVisibility(8);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.banner);
        if (relativeLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams2.width = i;
            relativeLayout.setLayoutParams(layoutParams2);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.card_view);
        if (frameLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams3.width = i;
            layoutParams3.height = i2;
            frameLayout.setLayoutParams(layoutParams3);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.card_image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        aqb_(singleGridContent, imageView, 0, z);
        View findViewById = inflate.findViewById(R.id.card_infoLayout);
        ViewGroup.LayoutParams layoutParams4 = findViewById.getLayoutParams();
        layoutParams4.width = (int) (i * 0.618f);
        findViewById.setLayoutParams(layoutParams4);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.card_title);
        healthTextView.setText(String.valueOf(singleGridContent.getTheme()));
        healthTextView.setVisibility(4);
        eiv.a(healthTextView, false, true);
        eiv.a((HealthTextView) inflate.findViewById(R.id.card_content), true, false);
        return inflate;
    }

    private View.OnClickListener aqa_(SingleGridContent singleGridContent, int i) {
        return new b(this, singleGridContent, i, this.g, this.l);
    }

    static final class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final int f12071a;
        private final WeakReference<ekp> b;
        private final WeakReference<SingleGridContent> c;
        private final ResourceBriefInfo d;
        private final int e;

        public b(ekp ekpVar, SingleGridContent singleGridContent, int i, int i2, ResourceBriefInfo resourceBriefInfo) {
            this.b = new WeakReference<>(ekpVar);
            this.c = new WeakReference<>(singleGridContent);
            this.e = i;
            this.f12071a = i2;
            this.d = resourceBriefInfo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SingleGridContent singleGridContent = this.c.get();
            ekp ekpVar = this.b.get();
            if (singleGridContent == null || ekpVar == null || TextUtils.isEmpty(singleGridContent.getLinkValue())) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (nsn.cLk_(view)) {
                LogUtil.h("SportBannerLayoutAdapter", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ekpVar.d(2, singleGridContent, this.e);
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(eil.c(singleGridContent.getLinkValue(), this.f12071a, this.e + 1, singleGridContent, this.d));
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: ekp$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ ImageView b;
        final /* synthetic */ int c;
        final /* synthetic */ boolean d;
        final /* synthetic */ SingleGridContent e;

        AnonymousClass5(int i, boolean z, SingleGridContent singleGridContent, ImageView imageView) {
            this.c = i;
            this.d = z;
            this.e = singleGridContent;
            this.b = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            final Drawable cId_;
            if (this.c <= 0) {
                cId_ = nrf.cIb_(BaseApplication.getContext(), this.d ? this.e.getTahitiPicture() : this.e.getPicture());
            } else {
                cId_ = nrf.cId_(BaseApplication.getContext(), this.d ? this.e.getTahitiPicture() : this.e.getPicture(), this.c);
            }
            Handler handler = ekp.this.h;
            final ImageView imageView = this.b;
            final SingleGridContent singleGridContent = this.e;
            handler.post(new Runnable() { // from class: ekn
                @Override // java.lang.Runnable
                public final void run() {
                    ekp.AnonymousClass5.aqg_(imageView, cId_, singleGridContent);
                }
            });
        }

        static /* synthetic */ void aqg_(ImageView imageView, Drawable drawable, SingleGridContent singleGridContent) {
            imageView.setImageDrawable(drawable);
            CharSequence a2 = eiv.a(singleGridContent);
            jcf.bEA_(imageView, a2, ImageView.class);
            LogUtil.a("SportBannerLayoutAdapter", "handleImageView contentDescription ", a2);
        }
    }

    private void aqb_(SingleGridContent singleGridContent, ImageView imageView, int i, boolean z) {
        imageView.setImageResource(R.drawable._2131431447_res_0x7f0b1017);
        ThreadPoolManager.d().execute(new AnonymousClass5(i, z, singleGridContent, imageView));
    }

    public void b(double d) {
        this.f = d;
    }

    private View apW_(SingleGridContent singleGridContent, HealthViewPager healthViewPager, int i) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.item_sport_banner, (ViewGroup) null);
        if (!(healthViewPager.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return inflate;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        layoutParams.width = e() - this.j;
        LogUtil.a("SportBannerLayoutAdapter", "adViewPagerLayoutParams width:", Integer.valueOf(layoutParams.width), "  mOtherMargin:", Integer.valueOf(this.j));
        int d = eiv.d(this.b, this.j);
        LogUtil.a("SportBannerLayoutAdapter", "buildFullImgCardViewLayout width:", Integer.valueOf(d), "  height:", Integer.valueOf(layoutParams.height));
        healthViewPager.setLayoutParams(layoutParams);
        aqe_((RelativeLayout) inflate.findViewById(R.id.banner), d, inflate, layoutParams.height);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.card_image);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.icon_top_corner);
        aqb_(singleGridContent, imageView, nrf.d, false);
        if (singleGridContent.getCornerVisibility()) {
            healthTextView.setVisibility(0);
            eiv.c(healthTextView, singleGridContent, 0, 0, 0, 8);
        }
        aqc_(inflate);
        return inflate;
    }

    private void aqc_(View view) {
        GridTemplate gridTemplate = this.c;
        if (gridTemplate == null) {
            LogUtil.h("SportBannerLayoutAdapter", "initFullImgCloseAndInfoBtn mGridTemplate is null");
            return;
        }
        LogUtil.a("SportBannerLayoutAdapter", "initFullImgCloseAndInfoBtn closeButton: ", Boolean.valueOf(gridTemplate.isAdvertiseCloseButtonSwitch()), "  InfoButton: ", Boolean.valueOf(this.c.isAdvertiseInfoButtonSwitch()), "  InfoLink: ", this.c.getAdvertiseInfoLink());
        ImageView imageView = (ImageView) view.findViewById(R.id.close_btn);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.info_btn);
        if (this.c.isAdvertiseCloseButtonSwitch()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        if (this.c.isAdvertiseInfoButtonSwitch() && !TextUtils.isEmpty(this.c.getAdvertiseInfoLink())) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        imageView.setOnClickListener(this.f12069a);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: ekm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ekp.this.aqd_(view2);
            }
        });
    }

    /* synthetic */ void aqd_(View view) {
        ReleaseLogUtil.e("R_SportBannerLayoutAdapter", "infoBtn clicked");
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(this.c.getAdvertiseInfoLink());
        ViewClickInstrumentation.clickOnView(view);
    }

    private View apX_(SingleGridContent singleGridContent, HealthViewPager healthViewPager, int i) {
        int dimensionPixelSize;
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.item_sport_banner_image_text, (ViewGroup) null);
        if (!(healthViewPager.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return inflate;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        layoutParams.width = e() - this.j;
        if (this.i) {
            dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131363134_res_0x7f0a053e);
        } else {
            dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131363107_res_0x7f0a0523);
        }
        layoutParams.height = dimensionPixelSize;
        healthViewPager.setLayoutParams(layoutParams);
        aqe_((RelativeLayout) inflate.findViewById(R.id.banner), b(), inflate, dimensionPixelSize);
        aqb_(singleGridContent, (ImageView) inflate.findViewById(R.id.card_image), nrf.d, false);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.item_sport_banner_title);
        healthTextView.setText(singleGridContent.getTheme());
        eiv.a(healthTextView, false, true);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.item_sport_banner_description);
        healthTextView2.setText(singleGridContent.getDescription());
        eiv.a(healthTextView2, true, false);
        return inflate;
    }

    private View apZ_(SingleGridContent singleGridContent, HealthViewPager healthViewPager, int i) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.layout_ad_cardview_sport, (ViewGroup) null);
        if (!(healthViewPager.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return inflate;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        layoutParams.width = e() - this.j;
        int b2 = b();
        LogUtil.a("SportBannerLayoutAdapter", "buildStdCardViewLayout width:", Integer.valueOf(b2));
        int i2 = (b2 * 9) / 16;
        layoutParams.height = i2;
        healthViewPager.setLayoutParams(layoutParams);
        inflate.findViewById(R.id.banner_projection).setVisibility(8);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.banner);
        if (relativeLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams2.width = b2;
            relativeLayout.setLayoutParams(layoutParams2);
        }
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.card_view);
        if (healthCardView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) healthCardView.getLayoutParams();
            layoutParams3.width = b2;
            layoutParams3.height = i2;
            healthCardView.setLayoutParams(layoutParams3);
        }
        aqb_(singleGridContent, (ImageView) inflate.findViewById(R.id.card_image), nrf.d, false);
        View findViewById = inflate.findViewById(R.id.card_infoLayout);
        ViewGroup.LayoutParams layoutParams4 = findViewById.getLayoutParams();
        layoutParams4.width = (int) (b2 * 0.618f);
        findViewById.setLayoutParams(layoutParams4);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.card_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.icon_top_corner_mark);
        healthTextView.setText(String.valueOf(singleGridContent.getTheme()));
        healthTextView.setVisibility(singleGridContent.isThemeVisibility() ? 0 : 4);
        eiv.a(healthTextView, false, true);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.card_content);
        healthTextView3.setText(String.valueOf(singleGridContent.getDescription()));
        healthTextView3.setVisibility(TextUtils.isEmpty(singleGridContent.getDescription()) ? 4 : 0);
        eiv.a(healthTextView3, true, false);
        if (singleGridContent.getCornerVisibility()) {
            healthTextView2.setVisibility(0);
            eiv.c(healthTextView2, singleGridContent, 0, 0, 0, 12);
        }
        return inflate;
    }

    public void d(int i, SingleGridContent singleGridContent, int i2) {
        if (1 == i && this.n.contains(singleGridContent.getTheme())) {
            LogUtil.a("SportBannerLayoutAdapter", "has shown name: ", singleGridContent.getTheme());
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", Integer.valueOf(this.g));
        hashMap.put("resourceName", this.l.getResourceName());
        hashMap.put("resourceId", this.l.getResourceId());
        hashMap.put("itemCardName", singleGridContent.getTheme());
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.d));
            this.d = System.currentTimeMillis();
        }
        hashMap.put("smartRecommend", Boolean.valueOf(singleGridContent.isSmartRecommend()));
        hashMap.put("itemId", this.l.getSmartRecommend() ? singleGridContent.getItemId() : singleGridContent.getDynamicDataId());
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("algId", singleGridContent.isRecommendInfoGenerated() ? singleGridContent.getAlgId() : eil.d(this.l.getRecommendList(), singleGridContent.getItemId()));
        LogUtil.a("SportBannerLayoutAdapter", "marketing biEvent SportBannerLayoutAdapter biMap: ", hashMap.toString());
        if (singleGridContent.isRecommendInfoGenerated()) {
            ixx.d().c(BaseApplication.getContext(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, singleGridContent.getAbInfo(), 0);
        } else {
            MarketingBiUtils.a(hashMap, this.l);
        }
        if (1 == i) {
            this.n.add(singleGridContent.getTheme());
        }
    }

    public void aqe_(RelativeLayout relativeLayout, int i, View view, int i2) {
        if (relativeLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = i;
            relativeLayout.setLayoutParams(layoutParams);
        }
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.card_view);
        if (healthCardView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) healthCardView.getLayoutParams();
            layoutParams2.width = i;
            layoutParams2.height = i2;
            healthCardView.setLayoutParams(layoutParams2);
        }
    }

    public int e() {
        return eie.b(this.b, this.g);
    }

    public int b() {
        return eiv.d(this.b, this.j);
    }

    public void aqf_(View.OnClickListener onClickListener) {
        this.f12069a = onClickListener;
    }
}
