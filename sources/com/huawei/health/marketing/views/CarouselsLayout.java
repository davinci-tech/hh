package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.TimePeriod;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.MultiGridsSleepPageTemplate;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.health.marketing.request.RespSleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfo;
import com.huawei.health.marketing.request.SleepAudioInfoApi;
import com.huawei.health.marketing.request.SleepAudioInfoReq;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.CarouselsLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.viewpager.carouselsviewpager.CarouselsPagerAdapter;
import com.huawei.ui.commonui.viewpager.carouselsviewpager.CarouselsTransformer;
import com.huawei.ui.commonui.viewpager.carouselsviewpager.CarouselsViewPager;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import defpackage.ixx;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.mld;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class CarouselsLayout extends BaseLifeCycleLinearLayout implements ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2825a;
    private HealthCardView b;
    private List<String> c;
    private CarouselsPagerAdapter d;
    private List<SingleGridContent> e;
    private String f;
    private ImageView g;
    private int h;
    private Context i;
    private boolean j;
    private HealthTextView k;
    private HealthTextView l;
    private int m;
    private ImageView n;
    private HealthTextView o;
    private ResourceBriefInfo p;
    private String q;
    private LinearLayout r;
    private List<SingleGridContent> s;
    private String t;
    private MultiGridsSleepPageTemplate u;
    private long v;
    private View w;
    private CarouselsViewPager x;
    private HealthSubHeader y;

    public CarouselsLayout(Context context) {
        this(context, null);
    }

    public CarouselsLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarouselsLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CarouselsLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = new ArrayList();
        this.s = new ArrayList();
        this.c = new ArrayList(3);
        this.f2825a = new ArrayList(3);
        this.i = context;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        boolean z = (resourceBriefInfo == null || this.p == null || !resourceBriefInfo.getResourceId().equals(this.p.getResourceId())) ? false : true;
        LogUtil.a("CarouselsLayout", "isOwnedByBriefInfo: " + z);
        return z;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.p;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    public void d(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        LogUtil.a("CarouselsLayout", "initData");
        if (resourceBriefInfo != null) {
            LogUtil.a("CarouselsLayout", "resourceBriefInfo: " + resourceBriefInfo.toString());
        }
        this.m = i;
        this.p = resourceBriefInfo;
        if (resourceBriefInfo != null) {
            this.q = resourceBriefInfo.getResourceId();
            this.t = this.p.getResourceName();
            this.f = this.p.getCategory();
        }
        this.v = System.currentTimeMillis();
        if (baseTemplate instanceof MultiGridsSleepPageTemplate) {
            MultiGridsSleepPageTemplate multiGridsSleepPageTemplate = (MultiGridsSleepPageTemplate) baseTemplate;
            this.u = multiGridsSleepPageTemplate;
            this.e = multiGridsSleepPageTemplate.getGridContents();
        }
        if (koq.b(this.e)) {
            LogUtil.h("CarouselsLayout", "List isEmpty");
        } else {
            d();
        }
    }

    public void d() {
        LogUtil.a("CarouselsLayout", "initView");
        a();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        try {
            this.j = Integer.parseInt(simpleDateFormat.format(date)) >= 6 && Integer.parseInt(simpleDateFormat.format(date)) < 18;
        } catch (NumberFormatException e) {
            LogUtil.b("CarouselsLayout", "NumberFormatException", e.getMessage());
        }
        c();
        e();
        b(this.s);
        ArrayList<String> arrayList = new ArrayList<>();
        for (SingleGridContent singleGridContent : this.s) {
            arrayList.add(singleGridContent.getPicture());
            this.c.add(singleGridContent.getTheme());
        }
        b(arrayList);
        this.x.setPageTransformer(false, new CarouselsTransformer(this.x));
        this.x.setOffscreenPageLimit(3);
        this.x.setQuantityOfShowPages(3);
        this.x.setAdapter(this.d);
        this.x.b();
        LogUtil.a("CarouselsLayout", "mDayAudioThemeList size: " + this.c.size() + " ,mAudioHeatCount size: " + this.f2825a.size());
        setPageResource(0);
        this.x.setPageScrolledListener(new b());
        this.g.setImageDrawable(ContextCompat.getDrawable(this.i, R.mipmap._2131821475_res_0x7f1103a3));
        this.g.setOnClickListener(amd_(this.s, true));
        this.b.setOnClickListener(amd_(this.s, false));
        if (((Integer) BaseActivity.getSafeRegionWidth().first).intValue() > 0 || ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() > 0) {
            LogUtil.a("CarouselsLayout", "isCurvedScreen");
            ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMarginStart(((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                marginLayoutParams.setMarginEnd(((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            }
        }
    }

    private void b(final ArrayList<String> arrayList) {
        this.d = new CarouselsPagerAdapter(this.i, arrayList, new CarouselsPagerAdapter.IViewGenerator() { // from class: com.huawei.health.marketing.views.CarouselsLayout.1
            @Override // com.huawei.ui.commonui.viewpager.carouselsviewpager.CarouselsPagerAdapter.IViewGenerator
            public View inflate(Context context, final String str) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.imageview_layout, (ViewGroup) null, false);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.imgaview);
                imageView.setRotation(180.0f);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.CarouselsLayout.1.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CarouselsLayout.this.d((List<SingleGridContent>) CarouselsLayout.this.s, true, arrayList.indexOf(str));
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                nrf.cIS_(imageView, str, nrf.e, 1, 0);
                return inflate;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageResource(int i) {
        String quantityString;
        Object[] objArr = new Object[2];
        objArr[0] = "pageId: " + i;
        StringBuilder sb = new StringBuilder(", mAudioHeatCount size: ");
        List<Integer> list = this.f2825a;
        sb.append(list == null ? null : Integer.valueOf(list.size()));
        objArr[1] = sb.toString();
        LogUtil.a("CarouselsLayout", objArr);
        List<String> list2 = this.c;
        if (list2 != null && list2.size() > i) {
            nsy.cMr_(this.k, this.c.get(i));
        }
        List<Integer> list3 = this.f2825a;
        if (list3 != null && list3.size() > i) {
            int intValue = this.f2825a.get(i).intValue();
            if (LanguageUtil.h(this.i)) {
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903420_res_0x7f03017c, intValue, UnitUtil.e(intValue / 10000.0d, 1, 1));
            } else {
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903420_res_0x7f03017c, intValue, UnitUtil.e(intValue / 1000.0d, 1, 1));
            }
            nsy.cMr_(this.l, quantityString);
        }
        List<SingleGridContent> list4 = this.s;
        if (list4 == null || list4.size() <= i) {
            return;
        }
        this.b.setCardBackgroundColor(Color.parseColor(this.s.get(i).getBackgroundColor()));
    }

    private View.OnClickListener amd_(final List<SingleGridContent> list, final boolean z) {
        LogUtil.a("CarouselsLayout", "getViewClickListener");
        return new View.OnClickListener() { // from class: com.huawei.health.marketing.views.CarouselsLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarouselsLayout carouselsLayout = CarouselsLayout.this;
                carouselsLayout.d((List<SingleGridContent>) list, z, carouselsLayout.h);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SingleGridContent> list, boolean z, int i) {
        CarouselsViewPager carouselsViewPager = this.x;
        if (carouselsViewPager == null) {
            LogUtil.a("CarouselsLayout", "mViewPager is null");
            return;
        }
        carouselsViewPager.c();
        if (list == null || this.x.getCurrentRealItem() < 0 || this.x.getCurrentRealItem() >= list.size()) {
            return;
        }
        final SingleGridContent singleGridContent = list.get(this.x.getCurrentRealItem());
        if (i != -1) {
            singleGridContent = list.get(i % list.size());
        }
        final String linkValue = singleGridContent.getLinkValue();
        if (TextUtils.isEmpty(linkValue) || nsn.o()) {
            return;
        }
        boolean b2 = eie.b(linkValue);
        if (z) {
            linkValue = linkValue + "&playing=true";
        }
        if (!b2) {
            d(2, singleGridContent.getTheme(), singleGridContent.getDynamicDataId());
            e(linkValue, singleGridContent.getDynamicDataId());
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejb
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    CarouselsLayout.this.b(singleGridContent, linkValue, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
        }
    }

    public /* synthetic */ void b(SingleGridContent singleGridContent, String str, int i, Object obj) {
        if (i == 0) {
            d(2, singleGridContent.getTheme(), singleGridContent.getDynamicDataId());
            e(str, singleGridContent.getDynamicDataId());
        } else {
            LogUtil.h("CarouselsLayout", "getViewClickListener errorCode = ", Integer.valueOf(i));
        }
    }

    private void e(String str, String str2) {
        LogUtil.a("CarouselsLayout", "goToDetail");
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(d(eil.c(str, this.f), this.m, str2));
        }
    }

    private String d(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        return sb.toString() + i + "&resourceName=" + this.t + "&resourceId=" + this.q + "&pullOrder=" + (this.x.getCurrentRealItem() + 1) + "&itemId=" + str2;
    }

    private void d(int i, String str, String str2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("resourcePositionId", Integer.valueOf(this.m));
        hashMap.put("resourceId", this.q);
        hashMap.put("resourceName", this.t);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", str);
        MultiGridsSleepPageTemplate multiGridsSleepPageTemplate = this.u;
        if (multiGridsSleepPageTemplate != null) {
            hashMap.put("moreEntryName", multiGridsSleepPageTemplate.getMoreMenuTitle());
        }
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.v));
            this.v = System.currentTimeMillis();
        }
        hashMap.put("pullOrder", Integer.valueOf(this.x.getCurrentRealItem() + 1));
        if (this.p != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(this.p.getSmartRecommend()));
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("itemId", str2);
        }
        ixx.d().d(this.i, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<SingleGridContent> list) {
        LogUtil.a("CarouselsLayout", "requestSleepAudioData");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new a(this, list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (SingleGridContent singleGridContent : list) {
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
                if (!TextUtils.isEmpty(sb.toString())) {
                    String[] split = sb.toString().substring(1).split(",");
                    int length = split.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (TextUtils.equals(split[i], singleGridContent.getDynamicDataId())) {
                                break;
                            } else {
                                i++;
                            }
                        } else {
                            sb.append(",");
                            sb.append(singleGridContent.getDynamicDataId());
                            break;
                        }
                    }
                } else if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
                    sb.append(",");
                    sb.append(singleGridContent.getDynamicDataId());
                }
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            LogUtil.h("CarouselsLayout", "requestSleepAudioData idStrings is empty.");
            return;
        }
        String substring = sb.substring(1);
        SleepAudioInfoReq sleepAudioInfoReq = new SleepAudioInfoReq();
        c(sleepAudioInfoReq, substring);
        SleepAudioInfoApi sleepAudioInfoApi = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        Map<String, Object> body = activityInfoListFactory.getBody(sleepAudioInfoReq);
        body.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        String b2 = lql.b(body);
        LogUtil.a("CarouselsLayout", "requestSleepAudioData, body: " + b2);
        d(sleepAudioInfoApi, sleepAudioInfoReq, activityInfoListFactory, list, b2);
    }

    private void c() {
        MultiGridsSleepPageTemplate multiGridsSleepPageTemplate = this.u;
        if (multiGridsSleepPageTemplate != null) {
            eiv.e(multiGridsSleepPageTemplate.isNameVisibility(), this.y);
            this.y.setHeadTitleText(this.u.getName());
            this.y.setSubHeaderBackgroundColor(0);
            if (TextUtils.isEmpty(this.u.getLinkValue())) {
                this.y.setRightArrayVisibility(8);
            } else {
                if (!TextUtils.isEmpty(this.u.getMoreMenuTitle())) {
                    this.y.setMoreTextMaxLines(2);
                    this.y.setMoreTextAlignment(6);
                    this.y.setMoreText(this.u.getMoreMenuTitle());
                } else {
                    this.y.setMoreText("");
                }
                this.y.setRightArrayVisibility(0);
                this.y.setMoreViewClickListener(new View.OnClickListener() { // from class: ejc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CarouselsLayout.this.ame_(view);
                    }
                });
            }
        }
        if (this.j) {
            nsy.cMm_(this.n, ContextCompat.getDrawable(this.i, R.mipmap._2131821476_res_0x7f1103a4));
        } else {
            nsy.cMm_(this.n, ContextCompat.getDrawable(this.i, R.mipmap._2131821474_res_0x7f1103a2));
        }
    }

    public /* synthetic */ void ame_(View view) {
        HashMap hashMap = new HashMap();
        hashMap.put("moreEntryName", this.u.getMoreMenuTitle());
        MarketingBiUtils.d(3, this.m, this.p, hashMap);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(this.u.getLinkValue());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        List<SingleGridContent> list = this.e;
        if (list == null || list.size() == 0) {
            LogUtil.a("CarouselsLayout", "no resource");
            nsy.cMA_(this.r, 8);
            return;
        }
        for (SingleGridContent singleGridContent : this.e) {
            LogUtil.a("CarouselsLayout", "singleGridContent: " + singleGridContent.toString());
            if (d(this.u.getTimePeriods()) == singleGridContent.getTimePeriodIdx()) {
                this.s.add(singleGridContent);
            }
        }
        while (this.s.size() < 3) {
            this.s.add(this.e.get(0));
        }
        MultiGridsSleepPageTemplate multiGridsSleepPageTemplate = this.u;
        if (multiGridsSleepPageTemplate == null || multiGridsSleepPageTemplate.getTimePeriods() == null) {
            return;
        }
        for (TimePeriod timePeriod : this.u.getTimePeriods()) {
            if (c(timePeriod)) {
                nsy.cMr_(this.o, timePeriod.getDescription());
                return;
            }
        }
    }

    private void a() {
        View inflate = LayoutInflater.from(this.i).inflate(R.layout.section_recommended_layout, this);
        this.w = inflate;
        this.r = (LinearLayout) inflate.findViewById(R.id.root_section_recommended);
        this.b = (HealthCardView) this.w.findViewById(R.id.section_recommended_cardview);
        this.y = (HealthSubHeader) this.w.findViewById(R.id.section_recommended_subhead);
        this.o = (HealthTextView) this.w.findViewById(R.id.section_recommended_title);
        this.n = (ImageView) this.w.findViewById(R.id.section_recommended_title_image);
        this.k = (HealthTextView) this.w.findViewById(R.id.section_recommended_subtitle_text);
        this.l = (HealthTextView) this.w.findViewById(R.id.section_recommended_subtitle_num);
        this.x = (CarouselsViewPager) this.w.findViewById(R.id.carouisels_viewpager);
        this.g = (ImageView) this.w.findViewById(R.id.music_button_image);
        if (getDensityDpi() < 440) {
            this.x.setPadding(0, 0, mld.d(this.i, r0 - 440), 0);
        }
    }

    private void d(SleepAudioInfoApi sleepAudioInfoApi, SleepAudioInfoReq sleepAudioInfoReq, ParamsFactory paramsFactory, List<SingleGridContent> list, String str) {
        try {
            Response<RespSleepAudioInfo> execute = sleepAudioInfoApi.getSleepAudioList(sleepAudioInfoReq.getUrl(), paramsFactory.getHeaders(), str).execute();
            if (execute.isOK()) {
                LogUtil.a("CarouselsLayout", "requestSleepAudioData response is OK.");
                RespSleepAudioInfo body = execute.getBody();
                if (body == null) {
                    LogUtil.h("CarouselsLayout", "requestSleepAudioData result is null");
                    return;
                }
                if (body.getResultCode() == 0 && !koq.b(body.getSleepAudios())) {
                    for (SingleGridContent singleGridContent : list) {
                        if (singleGridContent != null) {
                            String dynamicDataId = singleGridContent.getDynamicDataId();
                            if (!TextUtils.isEmpty(dynamicDataId)) {
                                Iterator<SleepAudioInfo> it = body.getSleepAudios().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    SleepAudioInfo next = it.next();
                                    if (next != null && TextUtils.equals(String.valueOf(next.getId()), dynamicDataId)) {
                                        this.f2825a.add(Integer.valueOf(next.getHeatCount()));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
                LogUtil.h("CarouselsLayout", "requestSleepAudioData result is error.");
            }
        } catch (IOException unused) {
            LogUtil.h("CarouselsLayout", "requestSleepAudioData exception.");
        }
    }

    private int d(List<TimePeriod> list) {
        LogUtil.a("CarouselsLayout", "getCurrentTimePeriodIdx");
        if (list == null || list.size() == 0) {
            LogUtil.a("CarouselsLayout", "timePeriodList is empty");
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (c(list.get(i))) {
                return i;
            }
        }
        LogUtil.a("CarouselsLayout", "no match time period");
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(com.huawei.health.marketing.datatype.TimePeriod r11) {
        /*
            r10 = this;
            java.lang.String r0 = "CarouselsLayout"
            r1 = 0
            if (r11 != 0) goto L10
            java.lang.String r11 = "timePeriod is null"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            return r1
        L10:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isCurrentInTimePeriod, beginTime: "
            r2.<init>(r3)
            java.lang.String r3 = r11.getBeginTime()
            r2.append(r3)
            java.lang.String r3 = ", endTime: "
            r2.append(r3)
            java.lang.String r3 = r11.getEndTime()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = r11.getBeginTime()
            java.lang.String r5 = ":"
            java.lang.String[] r4 = r4.split(r5)
            java.lang.String r11 = r11.getEndTime()
            java.lang.String[] r11 = r11.split(r5)
            int r5 = r4.length
            r6 = 2
            if (r5 != r6) goto La6
            int r5 = r11.length
            if (r5 == r6) goto L53
            goto La6
        L53:
            r5 = 1
            r6 = r4[r1]     // Catch: java.lang.NumberFormatException -> L76
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.NumberFormatException -> L76
            r4 = r4[r5]     // Catch: java.lang.NumberFormatException -> L72
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L72
            r7 = r11[r1]     // Catch: java.lang.NumberFormatException -> L6f
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.NumberFormatException -> L6f
            r11 = r11[r5]     // Catch: java.lang.NumberFormatException -> L6d
            int r11 = java.lang.Integer.parseInt(r11)     // Catch: java.lang.NumberFormatException -> L6d
            goto L88
        L6d:
            r11 = move-exception
            goto L7a
        L6f:
            r11 = move-exception
            r7 = r1
            goto L7a
        L72:
            r11 = move-exception
            r4 = r1
            r7 = r4
            goto L7a
        L76:
            r11 = move-exception
            r4 = r1
            r6 = r4
            r7 = r6
        L7a:
            java.lang.String r8 = "exception is"
            java.lang.String r11 = r11.getMessage()
            java.lang.Object[] r11 = new java.lang.Object[]{r8, r11}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)
            r11 = r1
        L88:
            if (r7 >= r6) goto L8c
            int r7 = r7 + 24
        L8c:
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = defpackage.jdl.e(r8, r6, r4)
            int r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r0 >= 0) goto La5
            long r8 = java.lang.System.currentTimeMillis()
            long r6 = defpackage.jdl.e(r8, r7, r11)
            int r11 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r11 <= 0) goto La5
            r1 = r5
        La5:
            return r1
        La6:
            java.lang.String r11 = "time array size error"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.CarouselsLayout.c(com.huawei.health.marketing.datatype.TimePeriod):boolean");
    }

    private void c(SleepAudioInfoReq sleepAudioInfoReq, String str) {
        sleepAudioInfoReq.setIds(str);
        sleepAudioInfoReq.setPageNo("1");
        sleepAudioInfoReq.setPageSize("100");
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("CarouselsLayout", "onResume");
        super.onResume();
        this.x.b();
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onPause() {
        LogUtil.a("CarouselsLayout", "onPause");
        CarouselsViewPager carouselsViewPager = this.x;
        if (carouselsViewPager == null) {
            LogUtil.a("CarouselsLayout", "mViewPager is null");
        } else {
            carouselsViewPager.c();
        }
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        CarouselsViewPager carouselsViewPager = this.x;
        if (carouselsViewPager == null) {
            LogUtil.a("CarouselsLayout", "mViewPager is null");
        } else {
            carouselsViewPager.c();
        }
    }

    private int getDensityDpi() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = this.i.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (systemService instanceof WindowManager) {
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.densityDpi;
    }

    static class b implements CarouselsViewPager.PageScrolledListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<CarouselsLayout> f2828a;

        private b(CarouselsLayout carouselsLayout) {
            this.f2828a = new WeakReference<>(carouselsLayout);
        }

        @Override // com.huawei.ui.commonui.viewpager.carouselsviewpager.CarouselsViewPager.PageScrolledListener
        public void pageScrolled(int i) {
            WeakReference<CarouselsLayout> weakReference = this.f2828a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            CarouselsLayout carouselsLayout = this.f2828a.get();
            carouselsLayout.h = i;
            carouselsLayout.setPageResource(i);
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<CarouselsLayout> f2827a;
        private List<SingleGridContent> d = new ArrayList();

        public a(CarouselsLayout carouselsLayout, List<SingleGridContent> list) {
            this.f2827a = new WeakReference<>(carouselsLayout);
            this.d.addAll(list);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<CarouselsLayout> weakReference = this.f2827a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2827a.get().b(this.d);
        }
    }
}
