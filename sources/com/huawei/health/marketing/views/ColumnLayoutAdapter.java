package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.RcmItem;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleEntryContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFunctionGridContent;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.ColumnLayoutAdapter;
import com.huawei.health.marketing.views.holders.OneGridLandscapeHolder;
import com.huawei.health.marketing.views.holders.SingleHotSeriesRecommendHolder;
import com.huawei.health.marketing.views.holders.SquareLandscapeSlideHolder;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.section.section.MeasureCardView;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.CouponResult;
import com.huawei.trade.datatype.Product;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.dqj;
import defpackage.ehy;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import defpackage.eka;
import defpackage.ffv;
import defpackage.ffy;
import defpackage.gic;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdw;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrq;
import defpackage.nrt;
import defpackage.nsd;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class ColumnLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener ab;
    private boolean ac;
    private List<List<SingleGridContent>> ad;
    private List<SingleEntryContent> ae;
    private List<RcmItem> af;
    private int ag;
    private ResourceBriefInfo ah;
    private String ai;
    private String aj;
    private int ak;
    private long al;
    private int am;
    private List<SingleFunctionGridContent> an;
    private int aq;
    private String g;
    private int h;
    private int j;
    private b l;
    private List<SingleGridContent> n;
    private Context o;
    private String q;
    private int r;
    private LayoutInflater w;
    private static final int b = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
    private static float e = 1.4f;
    private static float d = 1.8f;
    private List<SingleHotSeriesRecommendContent> i = new ArrayList(10);
    private volatile boolean v = true;
    private volatile boolean y = false;
    private volatile boolean u = false;
    private volatile boolean x = false;
    private volatile boolean aa = true;
    private String f = "";
    private boolean z = false;
    private eka k = new eka();
    private Set<SingleGridContent> p = new HashSet();
    private Map<String, SingleGridContent> t = new ConcurrentHashMap();
    private Observer m = new Observer() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.3
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(final String str, final Object... objArr) {
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.3.5
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLayoutAdapter.this.e(str, objArr);
                }
            }, 1000L);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private boolean f2829a = true;
    private int s = -1;
    private boolean c = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, Object... objArr) {
        if (!"marketing_response_arrived".equals(str)) {
            LogUtil.b("ColumnLayoutAdapter", "label error: ", str);
            return;
        }
        if (objArr == null) {
            LogUtil.b("ColumnLayoutAdapter", "args is null");
            return;
        }
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if ((obj instanceof Integer) && ((Integer) obj).intValue() == -1) {
                ObserverManagerUtil.e(this.m, "marketing_response_arrived");
                LogUtil.a("ColumnLayoutAdapter", "mFunctionMenuObserver is unregistered");
                return;
            }
        }
        if (objArr.length == 5) {
            Object obj2 = objArr[0];
            if ((obj2 instanceof Boolean) && (objArr[1] instanceof Boolean) && (objArr[2] instanceof Boolean) && (objArr[3] instanceof String)) {
                this.v = ((Boolean) obj2).booleanValue();
                this.y = ((Boolean) objArr[1]).booleanValue();
                this.u = ((Boolean) objArr[2]).booleanValue();
                this.f = (String) objArr[3];
                Object obj3 = objArr[4];
                if (obj3 instanceof List) {
                    this.af = (List) obj3;
                }
                LogUtil.a("ColumnLayoutAdapter", "mFunctionMenuObserver: ", Boolean.valueOf(this.v), ", ", Boolean.valueOf(this.y), ", ", Boolean.valueOf(this.u), ", mHasBiEventFunctionMenuMap size: ", Integer.valueOf(this.t.size()));
                if (!this.v || this.y) {
                    if (this.t.isEmpty()) {
                        LogUtil.a("ColumnLayoutAdapter", "mHasBiEventFunctionMenuMap is empty");
                        return;
                    }
                    Iterator<Map.Entry<String, SingleGridContent>> it = this.t.entrySet().iterator();
                    while (it.hasNext()) {
                        SingleGridContent value = it.next().getValue();
                        b(1, value, value.getItemPosition());
                    }
                    this.x = true;
                    ObserverManagerUtil.e(this.m, "marketing_response_arrived");
                    LogUtil.a("ColumnLayoutAdapter", "mFunctionMenuObserver is unregistered");
                    return;
                }
                return;
            }
        }
        LogUtil.b("ColumnLayoutAdapter", "params error");
    }

    public ColumnLayoutAdapter(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        this.ac = false;
        Context applicationContext = context.getApplicationContext();
        this.o = applicationContext;
        this.w = LayoutInflater.from(applicationContext);
        this.ag = i;
        this.j = resourceBriefInfo.getContentType();
        this.ai = resourceBriefInfo.getResourceId();
        this.aj = resourceBriefInfo.getResourceName();
        this.g = resourceBriefInfo.getCategory();
        this.ac = nsn.ag(BaseApplication.getActivity());
        this.al = System.currentTimeMillis();
        this.ah = resourceBriefInfo;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void c(boolean z) {
        this.ac = z;
    }

    public void b(List<SingleGridContent> list) {
        this.n = list;
    }

    public void a(String str) {
        this.q = str;
    }

    public void a(List<List<SingleGridContent>> list) {
        this.ad = list;
    }

    public void d(List<SingleEntryContent> list) {
        this.ae = list;
    }

    public void e(List<SingleFunctionGridContent> list) {
        this.an = list;
    }

    public void c(List<SingleHotSeriesRecommendContent> list) {
        this.i = list;
    }

    public void amy_(View.OnClickListener onClickListener) {
        this.ab = onClickListener;
    }

    public void d(boolean z) {
        this.z = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (i == 48) {
            inflate = this.w.inflate(R.layout.item_three_grad_full_image, viewGroup, false);
        } else if (i != 113) {
            switch (i) {
                case 6:
                    inflate = this.w.inflate(R.layout.item_marketing_one_grid_big, viewGroup, false);
                    break;
                case 7:
                case 9:
                    inflate = this.w.inflate(R.layout.item_two_grad_landscape, viewGroup, false);
                    break;
                case 8:
                    inflate = this.w.inflate(R.layout.item_marketing_one_add_two, viewGroup, false);
                    break;
                default:
                    switch (i) {
                        case 15:
                            inflate = this.w.inflate(R.layout.item_marketing_grid_small, viewGroup, false);
                            break;
                        case 16:
                            inflate = this.w.inflate(R.layout.item_two_grad_big_landscape, viewGroup, false);
                            break;
                        case 17:
                            inflate = this.w.inflate(R.layout.item_marketing_grid_two_vertical, viewGroup, false);
                            break;
                        default:
                            switch (i) {
                                case 19:
                                case 20:
                                    return new OneGridLandscapeHolder(this.o, this.w.inflate(R.layout.item_one_grad_landscape, viewGroup, false), this.ah, this.ag);
                                case 21:
                                    return new SquareLandscapeSlideHolder(this.o, this.w.inflate(R.layout.item_marketing_square_landscape_slide, viewGroup, false), this.ah, this.ag);
                                default:
                                    inflate = amq_(viewGroup, i);
                                    break;
                            }
                    }
            }
        } else {
            return new SingleHotSeriesRecommendHolder(this.o, this.w.inflate(R.layout.item_marketing_hot_series_recommend, viewGroup, false), this.ah, this.ag);
        }
        return new b(inflate, i, this.c);
    }

    public int c() {
        return this.s;
    }

    private View amq_(ViewGroup viewGroup, int i) {
        switch (i) {
            case 10:
            case 23:
                return this.w.inflate(R.layout.item_marketing_quick_entry, viewGroup, false);
            case 22:
                return this.w.inflate(R.layout.item_marketing_application_icon, viewGroup, false);
            case 24:
                if (this.c) {
                    return this.w.inflate(R.layout.item_marketing_image_text_with_blank, viewGroup, false);
                }
                return this.w.inflate(R.layout.item_marketing_image_text, viewGroup, false);
            case 35:
                return this.w.inflate(R.layout.item_marketing_text_link, viewGroup, false);
            case 36:
                return this.w.inflate(R.layout.item_marketing_app_turn_page, viewGroup, false);
            case 39:
            case 40:
                return this.w.inflate(R.layout.item_two_grad_landscape_two, viewGroup, false);
            case 47:
                return this.w.inflate(R.layout.item_marketing_function_menu, viewGroup, false);
            case 49:
                return this.w.inflate(R.layout.item_four_grad_full_image, viewGroup, false);
            case 53:
                return this.w.inflate(R.layout.item_marketing_grid_small_ecg, viewGroup, false);
            case 59:
                return this.w.inflate(R.layout.item_marketing_twolines_landscape_slide, viewGroup, false);
            case 76:
                return this.w.inflate(R.layout.item_one_carry_three, viewGroup, false);
            case 79:
                return this.w.inflate(R.layout.item_marketing_square_landscape_slide_favorites, viewGroup, false);
            case 80:
                return this.w.inflate(R.layout.item_marketing_square_landscape_slide_sleep, viewGroup, false);
            case 82:
                return this.w.inflate(R.layout.item_marketing_series_courses_sleep, viewGroup, false);
            case 83:
                return this.w.inflate(R.layout.item_marketing_series_courses_sleep, viewGroup, false);
            case 84:
                return this.w.inflate(R.layout.item_series_courses_third_part, viewGroup, false);
            case 85:
                return this.w.inflate(R.layout.item_sleep_yoga, viewGroup, false);
            case 92:
                return this.w.inflate(R.layout.item_marketing_square_landscape_slide_favorites, viewGroup, false);
            case 94:
                return this.w.inflate(R.layout.item_marketing_vertical_rectangle_slide, viewGroup, false);
            case 96:
                return this.w.inflate(R.layout.item_marketing_one_point_eight_grid_slide, viewGroup, false);
            case 107:
                return this.w.inflate(R.layout.item_one_grid_list_landscape_slide, viewGroup, false);
            case 116:
                return this.w.inflate(R.layout.item_multi_function, viewGroup, false);
            case 9999:
                return this.w.inflate(R.layout.measure_card_view_layout_wrapper, viewGroup, false);
            default:
                LogUtil.h("ColumnLayoutAdapter", "onCreateViewHolder default");
                return this.w.inflate(R.layout.item_two_grad_landscape, viewGroup, false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SingleHotSeriesRecommendHolder) {
            ((SingleHotSeriesRecommendHolder) viewHolder).d(this.i.get(i), i);
            return;
        }
        if (viewHolder instanceof SquareLandscapeSlideHolder) {
            SquareLandscapeSlideHolder squareLandscapeSlideHolder = (SquareLandscapeSlideHolder) viewHolder;
            squareLandscapeSlideHolder.d(this.n, i);
            amu_(this.n.get(i), squareLandscapeSlideHolder.itemView, i);
        } else if (viewHolder instanceof OneGridLandscapeHolder) {
            OneGridLandscapeHolder oneGridLandscapeHolder = (OneGridLandscapeHolder) viewHolder;
            oneGridLandscapeHolder.d(this.n, i);
            amu_(this.n.get(i), oneGridLandscapeHolder.itemView, i);
        } else if (viewHolder instanceof b) {
            a((b) viewHolder, i);
        }
    }

    private void a(b bVar, int i) {
        int i2 = this.j;
        if (i2 == 48) {
            bVar.gb.setVisibility(0);
            l(bVar, i);
            return;
        }
        if (i2 != 80) {
            switch (i2) {
                case 6:
                    p(bVar, i);
                    break;
                case 7:
                case 9:
                    ag(bVar, i);
                    break;
                case 8:
                    if (this.ac) {
                        bVar.cx.setVisibility(0);
                        bVar.ch.setVisibility(8);
                        v(bVar, i);
                        break;
                    } else {
                        bVar.cx.setVisibility(8);
                        bVar.ch.setVisibility(0);
                        l(bVar, i);
                        break;
                    }
                default:
                    switch (i2) {
                        case 15:
                            s(bVar, i);
                            break;
                        case 16:
                            u(bVar, i);
                            break;
                        case 17:
                            v(bVar, i);
                            break;
                        default:
                            switch (i2) {
                                case 82:
                                    ad(bVar, i);
                                    break;
                                case 83:
                                    y(bVar, i);
                                    break;
                                case 84:
                                    z(bVar, i);
                                    break;
                                case 85:
                                    ab(bVar, i);
                                    break;
                                default:
                                    e(bVar, i);
                                    break;
                            }
                    }
            }
            return;
        }
        ai(bVar, i);
    }

    public int d() {
        return this.ak;
    }

    public int g() {
        return this.aq;
    }

    public void c(int i) {
        this.ak = i;
    }

    public void a(int i) {
        this.aq = i;
    }

    public b a() {
        return this.l;
    }

    private void y(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSleepSeriesCoursesLayout singleGridContent is null.");
            return;
        }
        eiv.d(bVar.di, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.dd, b2.getDescription(), b2.getDescriptionVisibility());
        nrf.cIS_(bVar.dc, b2.getPicture(), 0, 1, 0);
        amu_(b2, bVar.dg, i);
    }

    private void e(b bVar, int i) {
        switch (this.j) {
            case 10:
            case 23:
                w(bVar, i);
                break;
            case 22:
                g(bVar, i);
                break;
            case 24:
                if (this.c) {
                    m(bVar, i);
                    break;
                } else {
                    n(bVar, i);
                    break;
                }
            case 35:
                ah(bVar, i);
                break;
            case 36:
                f(bVar, i);
                break;
            case 39:
            case 40:
                af(bVar, i);
                break;
            case 47:
                i(bVar, i);
                break;
            case 49:
                j(bVar, i);
                break;
            case 53:
                q(bVar, i);
                break;
            case 59:
                ae(bVar, i);
                break;
            case 76:
                t(bVar, i);
                break;
            case 79:
                ac(bVar, i);
                break;
            case 92:
                aa(bVar, i);
                break;
            case 94:
                am(bVar, i);
                break;
            case 96:
                x(bVar, i);
                break;
            case 107:
                r(bVar, i);
                break;
            case 116:
                k(bVar, i);
                break;
            case 9999:
                o(bVar, i);
                break;
        }
    }

    private void k(b bVar, int i) {
        SingleFunctionGridContent h = h(bVar, i);
        if (h == null) {
            LogUtil.h("ColumnLayoutAdapter", "setQuickEntryLayout content is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.an.size());
        ViewGroup.LayoutParams layoutParams = bVar.at.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = -2;
        bVar.at.setLayoutParams(layoutParams);
        String picture = h.getPicture();
        if (picture == null) {
            return;
        }
        if (picture.endsWith(MetaCreativeType.GIF)) {
            nrf.cIw_(picture, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), bVar.au);
        } else {
            nrf.cIS_(bVar.au, picture, nrf.e, 0, 0);
        }
        nsy.cMr_(bVar.aw, h.getTheme());
        nsy.cMr_(bVar.av, h.getSubTheme());
        amu_(new SingleGridContent(new SingleGridContent.Builder().setLinkValue(h.getLinkValue()).setTheme(h.getTheme())), bVar.at, i);
    }

    private void amn_(ViewGroup.LayoutParams layoutParams, int i, Context context) {
        if ((layoutParams instanceof RecyclerView.LayoutParams) && (i < this.n.size() - 1)) {
            ((RecyclerView.LayoutParams) layoutParams).setMarginEnd(context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8));
        }
    }

    private void t(b bVar, int i) {
        if (koq.b(this.n, i)) {
            return;
        }
        View view = bVar.itemView;
        amn_(view.getLayoutParams(), i, view.getContext());
        SingleGridContent b2 = b(bVar, i);
        nrf.b(b2.getPicture(), bVar.bn, 0);
        if (b2.isThemeVisibility()) {
            bVar.bo.setText(b2.getTheme());
            bVar.bo.setVisibility(0);
        } else {
            bVar.bo.setVisibility(8);
        }
        float d2 = eiv.d(8.0f);
        float[] fArr = new float[8];
        if (LanguageUtil.bc(this.o)) {
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
        eiv.c(bVar.bp, b2, fArr);
        c(bVar, b2);
        amu_(b2, view, i);
    }

    private void e(b bVar, String str, String str2) {
        bVar.bs.setVisibility(8);
        bVar.bq.setVisibility(8);
        bVar.br.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) bVar.br.findViewById(R.id.price_primary);
        if (healthTextView != null) {
            healthTextView.setTypeface(bVar.ab);
            healthTextView.setText(str);
        }
        HealthTextView healthTextView2 = (HealthTextView) bVar.br.findViewById(R.id.price_secondary);
        if (healthTextView2 != null) {
            healthTextView2.setPaintFlags(16);
            healthTextView2.setTypeface(bVar.ab);
            healthTextView2.setText(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void d(b bVar, SingleGridContent singleGridContent) {
        bVar.br.setVisibility(8);
        bVar.bs.setVisibility(8);
        if (singleGridContent.getDescriptionVisibility()) {
            bVar.bq.setVisibility(0);
            bVar.bq.setText(singleGridContent.getDescription());
        } else {
            bVar.bq.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(b bVar, String str) {
        bVar.bq.setVisibility(8);
        bVar.br.setVisibility(8);
        bVar.bs.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) bVar.bs.findViewById(R.id.vip_tag);
        if (healthTextView != null) {
            healthTextView.setTypeface(bVar.ab);
            healthTextView.setText(this.o.getResources().getString(R$string.IDS_member_price, ""));
        }
        HealthTextView healthTextView2 = (HealthTextView) bVar.bs.findViewById(R.id.vip_price);
        if (healthTextView2 != null) {
            healthTextView2.setTypeface(bVar.ab);
            healthTextView2.setText(str);
        }
    }

    private void c(final b bVar, final SingleGridContent singleGridContent) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            return;
        }
        final String dynamicDataId = singleGridContent.getDynamicDataId();
        if (TextUtils.isEmpty(dynamicDataId)) {
            LogUtil.b("ColumnLayoutAdapter", "scheduleOneCarryThreePrice failed, cause productId is null!");
        } else {
            payApi.getProductDetails(singleGridContent.getDynamicDataId(), new IBaseResponseCallback() { // from class: eiz
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ColumnLayoutAdapter.this.c(dynamicDataId, bVar, singleGridContent, i, obj);
                }
            });
        }
    }

    public /* synthetic */ void c(String str, final b bVar, final SingleGridContent singleGridContent, int i, Object obj) {
        if (i != 0) {
            LogUtil.b("ColumnLayoutAdapter", "getProductDetails failed, cause errorCode is: ", Integer.valueOf(i), ", productId = ", str);
            HandlerExecutor.e(new Runnable() { // from class: eja
                @Override // java.lang.Runnable
                public final void run() {
                    ColumnLayoutAdapter.this.d(bVar, singleGridContent);
                }
            });
            return;
        }
        if (!(obj instanceof Product)) {
            LogUtil.b("ColumnLayoutAdapter", "getProductDetails failed, cause objData is: ", obj, ", productId = ", str);
            HandlerExecutor.e(new Runnable() { // from class: ejg
                @Override // java.lang.Runnable
                public final void run() {
                    ColumnLayoutAdapter.this.b(bVar, singleGridContent);
                }
            });
            return;
        }
        Product product = (Product) obj;
        float microPrice = product.getMicroPrice() / 1000000.0f;
        final String str2 = "￥" + UnitUtil.e(microPrice, 1, (microPrice < 1.0f || (((int) product.getMicroPrice()) % 1000000 > 0)) ? 2 : 0);
        if (!product.getMemberFlag()) {
            HandlerExecutor.e(new Runnable() { // from class: ejd
                @Override // java.lang.Runnable
                public final void run() {
                    ColumnLayoutAdapter.this.b(bVar, str2);
                }
            });
        } else {
            HandlerExecutor.e(new Runnable() { // from class: ejh
                @Override // java.lang.Runnable
                public final void run() {
                    ColumnLayoutAdapter.this.e(bVar, str2);
                }
            });
        }
    }

    public /* synthetic */ void b(b bVar, String str) {
        e(bVar, str, "");
    }

    private void r(b bVar, int i) {
        if (koq.b(this.n, i)) {
            return;
        }
        View view = bVar.itemView;
        amn_(view.getLayoutParams(), i, view.getContext());
        SingleGridContent b2 = b(bVar, i);
        bVar.cc.setText(b2.getTheme());
        nrf.cHI_(b2.getPicture(), bVar.bx, 0);
        amu_(b2, view, i);
        String extend = b2.getExtend();
        if (TextUtils.isEmpty(extend)) {
            return;
        }
        String[] split = extend.split(",");
        if (split.length < 2) {
            return;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), split[0]);
        String str = split[1];
        String b3 = ffy.b(R.plurals._2130903380_res_0x7f030154, m, UnitUtil.e(m, 1, 0));
        String d2 = ehy.d(CommonUtil.m(this.o, str));
        String b4 = ffy.b(R.plurals._2130903305_res_0x7f030109, b2.getCostTime(), gic.e(b2.getCostTime()));
        int numberOfPeople = b2.getNumberOfPeople();
        String d3 = gic.d(BaseApplication.getContext(), com.huawei.ui.commonui.R$string.IDS_activity_social_people_attended, Integer.valueOf(numberOfPeople), UnitUtil.e(numberOfPeople, 1, 0));
        bVar.bz.setText(d2);
        bVar.by.setText(b4);
        bVar.ca.setText(b3);
        bVar.cg.setText(d3);
    }

    private void o(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneGridSmallEcgLayout singleGridContent is null.");
            return;
        }
        eiv.d(bVar.q, b2.getTheme(), b2.isThemeVisibility());
        nrf.cHI_(b2.getPicture(), bVar.k, 0);
        amu_(b2, bVar.l, i);
    }

    private void s(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneGridSmallLayout singleGridContent is null.");
            return;
        }
        d(bVar);
        eiv.d(bVar.eh, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.dy, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.dy, true, false);
        String picture = b2.getPicture();
        if (eie.b(this.ag)) {
            nrf.cIS_(bVar.dv, picture, nrf.d, 0, 0);
        } else {
            nrf.cIS_(bVar.dv, picture, nrf.d, 1, 0);
        }
        eiv.c(bVar.ds, b2, this.g);
        if (b2.getCornerVisibility()) {
            eiv.d(bVar.dx, (CornerTemplate) b2, false);
        } else {
            eiv.a(bVar.dx, b2, this.g, this.ag);
        }
        boolean d2 = nsd.d();
        if (TextUtils.isEmpty(b2.getLinkValue()) || ((!b2.getLinkValue().contains("com.huawei.health.ecg.collection") && !b2.getLinkValue().contains("com.huawei.ecgh5") && !b2.getLinkValue().contains("com.huawei.health.h5.ecg")) || !d2)) {
            bVar.ef.setVisibility(8);
        } else {
            bVar.ef.setVisibility(0);
        }
        amu_(b2, bVar.ei, i);
        String valueOf = bVar.dx.getVisibility() == 0 ? String.valueOf(bVar.dx.getText()) : "";
        String valueOf2 = bVar.ds.getVisibility() == 0 ? String.valueOf(bVar.ds.getText()) : "";
        CharSequence a2 = eiv.a(b2);
        if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(valueOf2)) {
            a2 = nsf.b(R$string.accessibility_status_theme_description_join, valueOf, a2, valueOf2);
        } else if (!TextUtils.isEmpty(valueOf)) {
            a2 = nsf.b(R$string.accessibility_status_theme_description, valueOf, a2);
        } else if (!TextUtils.isEmpty(valueOf2)) {
            a2 = nsf.b(R$string.accessibility_theme_description_join, a2, valueOf2);
        }
        jcf.bEA_(bVar.ei, a2, ImageView.class);
        LogUtil.a("ColumnLayoutAdapter", "setOneGridSmallLayout contentDescription ", a2);
    }

    private void d(b bVar) {
        int e2 = eie.e(this.o, this.j, this.n.size());
        ViewGroup.LayoutParams layoutParams = bVar.dv.getLayoutParams();
        if (eie.b(this.ag)) {
            Context activity = BaseApplication.getActivity();
            if (activity == null) {
                LogUtil.h("ColumnLayoutAdapter", "getActivity = null");
                activity = BaseApplication.getContext();
            }
            int c2 = ScrollUtil.c(activity);
            if (nsn.ag(activity)) {
                e2 = (c2 - nsn.c(this.o, 121.0f)) / 2;
            } else {
                e2 = c2 - nsn.c(this.o, 109.0f);
            }
        }
        int i = (e2 * 9) / 21;
        layoutParams.width = e2;
        layoutParams.height = i;
        bVar.dv.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = bVar.ei.getLayoutParams();
        layoutParams2.height = i;
        layoutParams2.width = e2;
        bVar.ei.setLayoutParams(layoutParams2);
    }

    private void p(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneGridLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int i2 = (e2 * 9) / 16;
        ViewGroup.LayoutParams layoutParams = bVar.bw.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = e2;
            layoutParams2.height = i2;
            bVar.bw.setLayoutParams(layoutParams2);
        }
        ViewGroup.LayoutParams layoutParams3 = bVar.ce.getLayoutParams();
        if (layoutParams3 instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
            layoutParams4.height = i2;
            layoutParams4.width = e2;
            bVar.ce.setLayoutParams(layoutParams4);
        }
        eiv.d(bVar.cf, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.bu, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.bu, true, false);
        nrf.cIS_(bVar.bw, b2.getPicture(), 0, 1, 0);
        eiv.c(bVar.cb, b2, this.g);
        if (b2.getCornerVisibility()) {
            eiv.d(bVar.cd, (CornerTemplate) b2, false);
        } else {
            eiv.a(bVar.cd, b2, this.g, this.ag);
        }
        eiv.d(bVar.bt, this.g, b2.getItemCategory());
        amu_(b2, bVar.bv, i);
    }

    private void ag(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setTwoGridLandscapeLayout singleGridContent is null.");
            return;
        }
        nrf.cIS_(bVar.gh, b2.getPicture(), nrf.e, 0, 0);
        eiv.d(bVar.ge, (CornerTemplate) b2, false);
        String theme = b2.getTheme();
        String description = b2.getDescription();
        boolean z = !TextUtils.isEmpty(theme) && b2.isThemeVisibility();
        boolean z2 = !TextUtils.isEmpty(description) && b2.getDescriptionVisibility();
        if (z2 || z) {
            bVar.gg.setVisibility(0);
            eiv.d(bVar.gi, theme, b2.isThemeVisibility());
            eiv.a(bVar.gd, description, b2.getDescriptionVisibility());
            eiv.a(bVar.gd, true, false);
            if (!z2) {
                bVar.gi.setTextSize(0, this.o.getResources().getDimension(R.dimen._2131365061_res_0x7f0a0cc5));
                ViewGroup.LayoutParams layoutParams = bVar.gg.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    layoutParams2.setMarginStart(nsn.c(this.o, 16.0f));
                    bVar.gg.setLayoutParams(layoutParams2);
                }
            }
        } else {
            bVar.gg.setVisibility(8);
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int e3 = eie.e(this.o, this.j, 4) / 2;
        ViewGroup.LayoutParams layoutParams3 = bVar.gk.getLayoutParams();
        layoutParams3.width = e2;
        if (nsn.s()) {
            layoutParams3.height = -2;
        } else {
            layoutParams3.height = e3;
        }
        bVar.gk.setLayoutParams(layoutParams3);
        amu_(b2, bVar.gk, i);
    }

    private void u(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSecondPalaceVerticalHolder singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int i2 = (e2 * 5) / 4;
        ViewGroup.LayoutParams layoutParams = bVar.cu.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = i2;
        bVar.cu.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.cu, b2.getPicture(), nrf.d, 1, 0);
        eiv.d(bVar.cs, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.cr, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.cr, true, false);
        eiv.d(bVar.ct, (CornerTemplate) b2, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.cw.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = i2;
        bVar.cw.setLayoutParams(layoutParams2);
        amu_(b2, bVar.cw, i);
    }

    private void v(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSecondPalaceVerticalTwoHolder singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int i2 = (e2 * 5) / 4;
        ViewGroup.LayoutParams layoutParams = bVar.cx.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = i2;
        bVar.cx.setLayoutParams(layoutParams);
        String theme = b2.getTheme();
        String description = b2.getDescription();
        eiv.d(bVar.de, theme, b2.isThemeVisibility());
        eiv.d(bVar.cy, (CornerTemplate) b2, false);
        eiv.a(bVar.cz, description, b2.getDescriptionVisibility(), true);
        if (this.ac && this.j == 8) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            bVar.de.measure(makeMeasureSpec, makeMeasureSpec2);
            bVar.cz.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredHeight = bVar.de.getMeasuredHeight();
            int measuredHeight2 = bVar.cz.getMeasuredHeight();
            ViewGroup.LayoutParams layoutParams2 = bVar.da.getLayoutParams();
            int c2 = nsn.c(this.o, 16.0f);
            int c3 = nsn.c(this.o, 2.0f);
            int c4 = (((((i2 - measuredHeight) - measuredHeight2) - c2) - c3) - nsn.c(this.o, 20.0f)) - nsn.c(this.o, 16.0f);
            layoutParams2.height = c4;
            layoutParams2.width = c4;
            bVar.da.setLayoutParams(layoutParams2);
        }
        nrf.cIS_(bVar.da, b2.getPicture(), nrf.d, 1, 0);
        amu_(b2, bVar.cx, i);
    }

    private void l(b bVar, int i) {
        List<SingleGridContent> c2 = c(bVar, i);
        if (koq.b(c2)) {
            LogUtil.h("ColumnLayoutAdapter", "setOneAddTwoHolder contentList is null.");
            return;
        }
        if (koq.b(c2, 0) || koq.b(c2, 1) || koq.b(c2, 2)) {
            LogUtil.h("ColumnLayoutAdapter", "contentList outOfBounds.");
            return;
        }
        SingleGridContent singleGridContent = c2.get(0);
        SingleGridContent singleGridContent2 = c2.get(1);
        SingleGridContent singleGridContent3 = c2.get(2);
        if (singleGridContent == null || singleGridContent2 == null || singleGridContent3 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneAddTwoHolder itemObject is null.");
            return;
        }
        d(bVar, singleGridContent, 0, i);
        d(bVar, singleGridContent2, 1, i);
        d(bVar, singleGridContent3, 2, i);
    }

    private void d(b bVar, SingleGridContent singleGridContent, int i, int i2) {
        if (bVar == null || singleGridContent == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneAddTwoViewSingle holder or content is null.");
            return;
        }
        this.r = i;
        int e2 = eie.e(this.o, this.j, this.ad.size());
        if (nsn.ag(BaseApplication.getActivity())) {
            e2 += this.o.getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be);
        }
        this.h = (e2 * 5) / 4;
        this.am = (this.h - this.o.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8)) / 2;
        if (i == 0) {
            if (this.j == 48) {
                amx_(singleGridContent, bVar.fk, bVar.fl, bVar.fm, e2);
                eiv.d(bVar.fh, (CornerTemplate) singleGridContent, false);
                ViewGroup.LayoutParams layoutParams = bVar.fj.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    layoutParams2.width = e2;
                    layoutParams2.height = this.h;
                    bVar.fj.setLayoutParams(layoutParams2);
                }
                if (TextUtils.equals(this.g, SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    eiv.c(bVar.fn, singleGridContent, this.g);
                }
                amu_(singleGridContent, bVar.fj, i2);
                return;
            }
            amw_(singleGridContent, bVar.ba, bVar.ay, bVar.bc);
            bVar.az.setLayoutParams(new RelativeLayout.LayoutParams(e2, this.h));
            eiv.d(bVar.bb, (CornerTemplate) singleGridContent, false);
            amu_(singleGridContent, bVar.az, i2);
            return;
        }
        if (i == 1) {
            if (this.j == 48) {
                amx_(singleGridContent, bVar.gc, bVar.ft, bVar.fw, e2);
                eiv.d(bVar.fu, (CornerTemplate) singleGridContent, false);
                ViewGroup.LayoutParams layoutParams3 = bVar.ga.getLayoutParams();
                if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
                    layoutParams4.width = e2;
                    layoutParams4.height = this.am;
                    bVar.ga.setLayoutParams(layoutParams4);
                }
                if (TextUtils.equals(this.g, SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    eiv.c(bVar.fx, singleGridContent, this.g);
                }
                amu_(singleGridContent, bVar.ga, i2);
                return;
            }
            amw_(singleGridContent, bVar.bm, bVar.bk, bVar.bj);
            bVar.bl.setLayoutParams(new RelativeLayout.LayoutParams(e2, this.am));
            eiv.d(bVar.bi, (CornerTemplate) singleGridContent, false);
            amu_(singleGridContent, bVar.bl, i2);
            return;
        }
        if (i != 2) {
            return;
        }
        if (this.j == 48) {
            amx_(singleGridContent, bVar.fv, bVar.fo, bVar.fq, e2);
            eiv.d(bVar.fr, (CornerTemplate) singleGridContent, false);
            ViewGroup.LayoutParams layoutParams5 = bVar.fp.getLayoutParams();
            if (layoutParams5 instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) layoutParams5;
                layoutParams6.width = e2;
                layoutParams6.height = this.am;
                bVar.fp.setLayoutParams(layoutParams6);
            }
            if (TextUtils.equals(this.g, SingleDailyMomentContent.ACTIVITY_TYPE)) {
                eiv.c(bVar.fs, singleGridContent, this.g);
            }
            amu_(singleGridContent, bVar.fp, i2);
            return;
        }
        amw_(singleGridContent, bVar.be, bVar.bf, bVar.bg);
        bVar.bd.setLayoutParams(new RelativeLayout.LayoutParams(e2, this.am));
        eiv.d(bVar.bh, (CornerTemplate) singleGridContent, false);
        amu_(singleGridContent, bVar.bd, i2);
    }

    private void amw_(SingleGridContent singleGridContent, HealthTextView healthTextView, HealthTextView healthTextView2, ImageView imageView) {
        int c2;
        eiv.a(healthTextView, singleGridContent.getTheme(), singleGridContent.isThemeVisibility(), true);
        eiv.a(healthTextView2, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility(), true);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        healthTextView.measure(makeMeasureSpec, makeMeasureSpec2);
        healthTextView2.measure(makeMeasureSpec, makeMeasureSpec2);
        int measuredHeight = healthTextView.getMeasuredHeight();
        int measuredHeight2 = healthTextView2.getMeasuredHeight();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (this.r == 0) {
            int i = this.h;
            int c3 = nsn.c(this.o, 16.0f);
            int c4 = nsn.c(this.o, 2.0f);
            c2 = (((((i - measuredHeight) - measuredHeight2) - c3) - c4) - nsn.c(this.o, 20.0f)) - nsn.c(this.o, 16.0f);
        } else {
            int i2 = this.am;
            int c5 = nsn.c(this.o, 16.0f);
            c2 = (((i2 - measuredHeight) - c5) - nsn.c(this.o, 2.0f)) - nsn.c(this.o, 4.0f);
        }
        layoutParams.height = c2;
        layoutParams.width = c2;
        imageView.setLayoutParams(layoutParams);
        nrf.cIS_(imageView, singleGridContent.getPicture(), (int) this.o.getResources().getDimension(R.dimen._2131362819_res_0x7f0a0403), 1, 0);
    }

    private void amx_(SingleGridContent singleGridContent, HealthTextView healthTextView, HealthTextView healthTextView2, ImageView imageView, int i) {
        eiv.a(healthTextView, singleGridContent.getTheme(), singleGridContent.isThemeVisibility(), true);
        eiv.a(healthTextView2, singleGridContent.getDescription(), singleGridContent.getDescriptionVisibility(), true);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (this.r == 0) {
            layoutParams.height = this.h;
            layoutParams.width = i;
        } else {
            layoutParams.height = this.am;
            layoutParams.width = i;
        }
        imageView.setLayoutParams(layoutParams);
        nrf.cIS_(imageView, singleGridContent.getPicture(), (int) this.o.getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6), 1, 0);
        CharSequence a2 = eiv.a(singleGridContent);
        jcf.bEA_(imageView, a2, ImageView.class);
        LogUtil.a("ColumnLayoutAdapter", "setThreeFullImageView contentDescription ", a2);
    }

    private void ac(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSquareLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int dimension = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238);
        ViewGroup.LayoutParams layoutParams = bVar.ep.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = e2;
        bVar.ep.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.ep, b2.getPicture(), dimension, 1, 0);
        eiv.d(bVar.fd, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.el, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.el, true, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.eq.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = e2;
        bVar.eq.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = bVar.fb.getLayoutParams();
        layoutParams3.width = e2;
        layoutParams3.height = -2;
        bVar.fb.setLayoutParams(layoutParams3);
        eiv.d(bVar.eg, (CornerTemplate) b2, true);
        eiv.alZ_(bVar.p, b2, bVar.ew);
        amu_(b2, bVar.fb, i);
        amv_(b2, bVar.ew, i);
    }

    private void aa(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSquareLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int dimension = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238);
        ViewGroup.LayoutParams layoutParams = bVar.et.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = e2;
        bVar.et.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.et, b2.getPicture(), dimension, 1, 0);
        eiv.d(bVar.ez, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.em, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.em, true, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.es.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = e2;
        bVar.es.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = bVar.fc.getLayoutParams();
        layoutParams3.width = e2;
        layoutParams3.height = -2;
        bVar.fc.setLayoutParams(layoutParams3);
        eiv.d(bVar.eo, (CornerTemplate) b2, true);
        eiv.alZ_(bVar.s, b2, bVar.ex);
        amu_(b2, bVar.fc, i);
        amv_(b2, bVar.ex, i);
    }

    private void z(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSleepSeriesCourseThreePart singleGridContent is null.");
            return;
        }
        nrf.cIS_(bVar.ae, b2.getPicture(), 0, 1, 0);
        eiv.d(bVar.gf, b2.getTheme(), b2.isThemeVisibility());
        String d2 = ffv.d(b2.getVip(), b2.getIsPay());
        if (TextUtils.isEmpty(d2)) {
            bVar.m.setVisibility(8);
        } else {
            bVar.m.setVisibility(0);
            ffv.b(bVar.m, d2);
        }
        eiv.a(bVar.co, b2.getPlayPercent(), true);
        amu_(b2, bVar.o, i);
    }

    private void ab(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSleepYoga singleGridContent is null.");
            return;
        }
        nrf.cIS_(bVar.dq, b2.getPicture(), (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238), 1, 0);
        eiv.d(bVar.dr, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.dt, b2.getDescription(), b2.getDescriptionVisibility());
        amu_(b2, bVar.du, i);
    }

    private void ai(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSquareLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int dimension = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238);
        ViewGroup.LayoutParams layoutParams = bVar.eu.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = e2;
        bVar.eu.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.eu, b2.getPicture(), dimension, 1, 0);
        eiv.d(bVar.fg, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.en, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.en, true, false);
        int heatCount = b2.getHeatCount();
        if (heatCount > 0) {
            bVar.ey.setVisibility(0);
            bVar.er.setVisibility(0);
            bVar.er.setText(nrq.c(heatCount));
        } else {
            bVar.ey.setVisibility(8);
            bVar.er.setVisibility(8);
        }
        ViewGroup.LayoutParams layoutParams2 = bVar.ev.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = e2;
        bVar.ev.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = bVar.fa.getLayoutParams();
        layoutParams3.width = e2;
        layoutParams3.height = -2;
        bVar.fa.setLayoutParams(layoutParams3);
        eiv.d(bVar.ek, (CornerTemplate) b2, true);
        amu_(b2, bVar.fa, i);
        amv_(b2, bVar.ey, i);
    }

    private void ad(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSleepSeriesCoursesLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        int i2 = (e2 * 9) / 16;
        ViewGroup.LayoutParams layoutParams = bVar.dm.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = i2;
            layoutParams2.width = e2;
            bVar.dm.setLayoutParams(layoutParams2);
        }
        eiv.d(bVar.dn, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.dp, b2.getDescription(), b2.getDescriptionVisibility());
        bVar.dh.setText(b2.getTheme());
        nrf.cIS_(bVar.dj, b2.getPicture(), 0, 1, 0);
        bVar.f1do.setVisibility(0);
        amu_(b2, bVar.dm, i);
        amu_(b2, bVar.f1do, i);
    }

    private void x(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOnePointEightGridSlideLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        ViewGroup.LayoutParams layoutParams = bVar.ck.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = Math.round(e2 / d);
        bVar.ck.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.ck, b2.getPicture(), 0, 1, 0);
        eiv.d(bVar.cj, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.ci, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.ci, true, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.cl.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = -2;
        bVar.cl.setLayoutParams(layoutParams2);
        amu_(b2, bVar.cl, i);
    }

    private void am(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setSquareLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        int c2 = nsn.c(BaseApplication.getContext(), 130.0f);
        ViewGroup.LayoutParams layoutParams = bVar.gt.getLayoutParams();
        layoutParams.width = c2;
        layoutParams.height = Math.round(c2 * 1.246f);
        bVar.gt.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.gt, b2.getPicture(), 0, 0, 0);
        eiv.d(bVar.gy, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.gy, false, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.ha.getLayoutParams();
        layoutParams2.width = c2;
        layoutParams2.height = -2;
        bVar.ha.setLayoutParams(layoutParams2);
        amu_(b2, bVar.ha, i);
    }

    private void ae(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setTwoLinesLandscapeSlideLayout singleGridContent is null.");
            return;
        }
        nrf.cIS_(bVar.gr, b2.getPicture(), nrf.e, 1, 0);
        eiv.a(bVar.gw, b2.getTheme(), b2.isThemeVisibility(), true);
        eiv.d(bVar.gp, b2);
        if (i % 2 != 0) {
            bVar.go.setVisibility(8);
        } else {
            bVar.go.setVisibility(0);
        }
        eiv.c(bVar.gs, ehy.e(b2, 59), true, false);
        eiv.a(bVar.gs, true, false);
        ViewGroup.LayoutParams layoutParams = bVar.gu.getLayoutParams();
        layoutParams.width = eie.e(this.o, this.j, this.n.size());
        layoutParams.height = -2;
        bVar.gu.setLayoutParams(layoutParams);
        amu_(b2, bVar.gu, i);
    }

    private void g(final b bVar, int i) {
        final SingleGridContent b2 = b(bVar, i);
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("appIconDisplay", 0);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setApplicationIconLayout singleGridContent is null.");
            return;
        }
        sharedPreferences.edit().putBoolean("APP_DISPLAY_ICON", true).apply();
        int c2 = nsn.c(BaseApplication.getContext(), 78.0f);
        nrf.cIT_(bVar.g, b2.getPicture(), nrf.c, 0, new AsyncLoadDrawableCallback() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.2
            @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
            public void getDrawable(Drawable drawable) {
                if (drawable == null) {
                    LogUtil.c("ColumnLayoutAdapter", "load round rectangle picture failed");
                    return;
                }
                Drawable drawable2 = bVar.g.getDrawable();
                if (drawable2 == null) {
                    LogUtil.c("ColumnLayoutAdapter", "mApplicationIconImage background drawable is null,return");
                    return;
                }
                int intrinsicWidth = drawable2.getIntrinsicWidth();
                float d2 = eiv.d(8.0f);
                eiv.c(bVar.i, b2, new float[]{d2, d2, d2, d2, d2, d2, d2, d2});
                eiv.e(bVar.i, intrinsicWidth);
            }
        });
        eiv.d(bVar.h, b2.getTheme(), b2.isThemeVisibility());
        ViewGroup.LayoutParams layoutParams = bVar.f.getLayoutParams();
        layoutParams.width = c2;
        layoutParams.height = -2;
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int dimensionPixelSize = this.o.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = this.o.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            if (i == 0) {
                marginLayoutParams.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
            }
            if (i == this.n.size() - 1) {
                marginLayoutParams.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
            }
        }
        bVar.f.setLayoutParams(layoutParams);
        int i2 = this.ag;
        if (i2 == 4005 || i2 == 4028) {
            EcgFilterManager a2 = EcgFilterManager.a();
            CharSequence text = bVar.h.getText();
            b2.setLinkValue(a2.alD_(bVar.f, b2.getLinkValue(), text != null ? text.toString() : null));
        }
        if (this.ag == 4009) {
            a(bVar);
        }
        amu_(b2, bVar.f, i);
    }

    private void a(b bVar) {
        ViewGroup.LayoutParams layoutParams = bVar.h.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = bVar.g.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams3.setMargins(0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7), 0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
            bVar.h.setLayoutParams(layoutParams3);
        }
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams2;
            layoutParams4.setMargins(0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446), 0, 0);
            bVar.g.setLayoutParams(layoutParams4);
        }
    }

    private void w(b bVar, int i) {
        SingleEntryContent d2 = d(bVar, i);
        if (d2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setQuickEntryLayout content is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.ae.size());
        ViewGroup.LayoutParams layoutParams = bVar.cp.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = -2;
        bVar.cp.setLayoutParams(layoutParams);
        String picture = d2.getPicture();
        if (picture.endsWith(MetaCreativeType.GIF)) {
            nrf.cIw_(picture, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), bVar.cm);
        } else {
            nrf.cIS_(bVar.cm, picture, nrf.e, 0, 0);
        }
        eiv.d(bVar.cv, d2.getName(), d2.isNameVisibility());
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = d2.getRedEndTime() >= currentTimeMillis && currentTimeMillis >= d2.getRedStartTime();
        if (TextUtils.isEmpty(d2.getRedName()) || !z) {
            if (!TextUtils.isEmpty(d2.getRedName()) || !z) {
                bVar.cn.setVisibility(4);
                bVar.cq.setVisibility(4);
            } else {
                bVar.cn.setVisibility(0);
                bVar.cq.setVisibility(4);
            }
        } else {
            bVar.cq.setVisibility(0);
            bVar.cq.setText(d2.getRedName());
            bVar.cn.setVisibility(4);
        }
        amu_(new SingleGridContent(new SingleGridContent.Builder().setLinkValue(d2.getLinkValue()).setTheme(d2.getName())), bVar.cp, i);
    }

    private void n(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setImageTextLayout content is null.");
            return;
        }
        nrf.cIS_(bVar.aq, b2.getPicture(), nrf.e, 0, 0);
        eiv.d(bVar.as, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.ao, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.ak, b2);
        if (nsn.ag(BaseApplication.getActivity()) || i == this.n.size() - 1) {
            bVar.ar.setVisibility(8);
        } else {
            bVar.ar.setVisibility(0);
        }
        amu_(b2, bVar.ap, i);
    }

    private void m(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setImageTextLayout content is null.");
            return;
        }
        nrf.cIS_(bVar.aj, b2.getPicture(), nrf.e, 0, 0);
        eiv.d(bVar.am, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.af, b2.getDescription(), b2.getDescriptionVisibility());
        bVar.ag.setVisibility(8);
        bVar.ai.setVisibility(8);
        bVar.ah.setVisibility(8);
        List<String> avatarUrlList = b2.getAvatarUrlList();
        if (avatarUrlList != null && avatarUrlList.size() != 0) {
            bVar.ah.setVisibility(0);
            ImageView imageView = bVar.ah;
            String str = avatarUrlList.get(avatarUrlList.size() - 1);
            int i2 = b;
            nrf.cIS_(imageView, str, i2, 0, 0);
            if (avatarUrlList.size() > 1) {
                bVar.ai.setVisibility(0);
                nrf.cIS_(bVar.ai, avatarUrlList.get(avatarUrlList.size() - 2), i2, 0, 0);
            }
            if (avatarUrlList.size() > 2) {
                bVar.ag.setVisibility(0);
                nrf.cIS_(bVar.ag, avatarUrlList.get(avatarUrlList.size() - 3), i2, 0, 0);
            }
        }
        if (eiv.b()) {
            bVar.ag.setVisibility(8);
            bVar.ai.setVisibility(8);
            bVar.ah.setVisibility(8);
        }
        eiv.a(bVar.al, b2.getStatistic(), b2.getDescriptionVisibility());
        amu_(b2, bVar.an, i);
        ViewGroup.LayoutParams layoutParams = bVar.an.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i == this.n.size() - 1) {
                marginLayoutParams.bottomMargin = 0;
            } else {
                marginLayoutParams.bottomMargin = nsf.e(R.dimen._2131362886_res_0x7f0a0446);
            }
            bVar.an.setLayoutParams(marginLayoutParams);
        }
    }

    private void i(final b bVar, int i) {
        final SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setFunctionMenuLayout singleGridContent is null.");
            return;
        }
        final int e2 = eie.e(this.o, this.j, this.n.size());
        if (this.ag == 4009) {
            e2 = this.o.getResources().getDimensionPixelSize(R.dimen._2131363101_res_0x7f0a051d);
        }
        String picture = b2.getPicture();
        ReleaseLogUtil.e("ColumnLayoutAdapter", "imgUrl: ", picture);
        String redDotFlag = b2.getRedDotFlag();
        ReleaseLogUtil.e("ColumnLayoutAdapter", "redDotFlag: ", redDotFlag, " MessageCount:", Integer.valueOf(b2.getMessageCount()));
        eiv.d(bVar.aa, b2.getTheme(), b2.isThemeVisibility());
        if (b2.getMessageCount() == 0) {
            if (TextUtils.isEmpty(b2.getMarketingIcon()) || TextUtils.equals(b2.getMarketingIcon(), "none")) {
                bVar.ac.setVisibility(4);
                bVar.z.setVisibility(4);
            } else {
                bVar.ac.setVisibility(4);
                bVar.z.setVisibility(0);
            }
        } else {
            bVar.ac.setText(String.valueOf(b2.getMessageCount()));
            bVar.ac.setVisibility(0);
        }
        d(bVar, b2, picture, redDotFlag);
        bVar.w.setText((CharSequence) null);
        bVar.w.setVisibility(8);
        if ("healthManage".equals(redDotFlag)) {
            a(bVar, b2);
        }
        ViewGroup.LayoutParams layoutParams = bVar.ad.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = -2;
        bVar.ad.setLayoutParams(layoutParams);
        b(bVar);
        amu_(b2, bVar.ad, i);
        e(i, bVar);
        if (b2.getTheme().equals("健康头条")) {
            this.l = bVar;
        }
        HandlerExecutor.d(new Runnable() { // from class: ejf
            @Override // java.lang.Runnable
            public final void run() {
                ColumnLayoutAdapter.this.b(b2, bVar, e2);
            }
        }, 100L);
    }

    public /* synthetic */ void b(SingleGridContent singleGridContent, b bVar, int i) {
        if (TextUtils.isEmpty(singleGridContent.getTheme()) || !singleGridContent.isThemeVisibility()) {
            return;
        }
        TextPaint paint = bVar.aa.getPaint();
        paint.setTextSize(bVar.aa.getTextSize());
        if (((int) paint.measureText(singleGridContent.getTheme())) > i - this.o.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532)) {
            bVar.aa.setLines(2);
        } else {
            bVar.aa.setLines(1);
        }
    }

    public void h() {
        this.k.e();
    }

    public void e() {
        this.k.d();
    }

    public void j() {
        this.k.b();
    }

    public void f() {
        this.k.a();
    }

    private void d(b bVar, SingleGridContent singleGridContent, final String str, String str2) {
        if (!SharedPreferenceManager.a("showLocalResources", "FUNCTION_MENU_SHOW_LOCAL_RESOURCES", false)) {
            if (!"healthManage".equals(str2) || !nsn.a(str)) {
                if (TextUtils.isEmpty(str) || !str.endsWith(MetaCreativeType.GIF)) {
                    nrf.e(str, bVar.u, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE));
                    return;
                } else {
                    nrf.cIx_(str, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), bVar.u, "助眠音乐".equals(singleGridContent.getTheme()) ? ContextCompat.getDrawable(this.o, R.drawable._2131430442_res_0x7f0b0c2a) : null, new RequestListener<GifDrawable>() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.5
                        @Override // com.bumptech.glide.request.RequestListener
                        public boolean onLoadFailed(GlideException glideException, Object obj, Target<GifDrawable> target, boolean z) {
                            LogUtil.b("ColumnLayoutAdapter", "setFunctionMenuImage resource load failed, exception = ", ExceptionUtils.d(glideException));
                            return false;
                        }

                        @Override // com.bumptech.glide.request.RequestListener
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public boolean onResourceReady(final GifDrawable gifDrawable, Object obj, Target<GifDrawable> target, DataSource dataSource, boolean z) {
                            LogUtil.a("ColumnLayoutAdapter", "setFunctionMenuImage resource ready for resource = ", gifDrawable, ", url = ", str, ", isFirstResource = ", Boolean.valueOf(z));
                            if (!z) {
                                return false;
                            }
                            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.5.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    ColumnLayoutAdapter.this.k.d(gifDrawable);
                                }
                            });
                            return false;
                        }
                    });
                    return;
                }
            }
            nrf.b(Integer.parseInt(str), bVar.u);
            return;
        }
        bVar.u.setImageDrawable(ContextCompat.getDrawable(this.o, d(singleGridContent.getPriority())));
    }

    private void e(int i, b bVar) {
        if (this.s == -1 && i == 0) {
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                SingleGridContent singleGridContent = this.n.get(i2);
                if (singleGridContent != null && singleGridContent.getTheme().equals("健康头条")) {
                    this.s = i2;
                }
            }
        }
    }

    private void a(b bVar, SingleGridContent singleGridContent) {
        if (bVar == null || singleGridContent == null) {
            LogUtil.h("ColumnLayoutAdapter", "loadHealthManageMessage holder == null or content == null");
            return;
        }
        ReleaseLogUtil.e("ColumnLayoutAdapter", "loadHealthManageMessage health_manage_status = ", this.q);
        bVar.ac.setVisibility(4);
        bVar.z.setVisibility(4);
        if (!TextUtils.isEmpty(this.q)) {
            if (!this.q.equals("hasEquity")) {
                if (!this.q.equals("hasInquiring")) {
                    ReleaseLogUtil.e("ColumnLayoutAdapter", "loadHealthManageMessage MessageCount = ", Integer.valueOf(singleGridContent.getMessageCount()));
                    if (singleGridContent.getMessageCount() == 0) {
                        if (this.q.equals("hasSigned")) {
                            bVar.w.setText(R$string.IDS_health_service_to_be_plan);
                            bVar.w.setVisibility(0);
                            return;
                        } else {
                            bVar.w.setVisibility(8);
                            return;
                        }
                    }
                    bVar.ac.setText(String.valueOf(singleGridContent.getMessageCount()));
                    bVar.ac.setVisibility(0);
                    bVar.w.setVisibility(8);
                    return;
                }
                bVar.w.setText(R$string.IDS_health_service_inquiring);
                bVar.w.setVisibility(0);
                return;
            }
            bVar.w.setVisibility(8);
            return;
        }
        bVar.w.setText(R$string.IDS_health_service_experience);
        bVar.w.setVisibility(0);
    }

    private void b(b bVar) {
        int f = new HealthColumnSystem(BaseApplication.getContext()).f();
        boolean aa = nsn.aa(BaseApplication.getContext());
        boolean l = nsn.l();
        boolean ag = nsn.ag(BaseApplication.getActivity());
        if (this.j == 47) {
            if (aa && l && f > 4 && !ag) {
                int a2 = a(this.n.size(), eie.c());
                ViewGroup.LayoutParams layoutParams = bVar.ad.getLayoutParams();
                layoutParams.width = a2;
                layoutParams.height = -2;
                bVar.ad.setLayoutParams(layoutParams);
                bVar.aa.setTextSize(1, 10.0f);
                return;
            }
            bVar.aa.setTextSize(1, 12.0f);
        }
    }

    private int a(int i, int i2) {
        float f;
        float f2;
        if (i > 4) {
            if (nsn.r()) {
                f = i2;
                f2 = 2.5f;
            } else {
                f = i2;
                f2 = 4.5f;
            }
            return (int) (f / f2);
        }
        return i2 / i;
    }

    private int d(int i) {
        switch (i) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            default:
                LogUtil.b("ColumnLayoutAdapter", "getDefaultResId error");
                break;
        }
        return R.drawable._2131430032_res_0x7f0b0a90;
    }

    private void f(final b bVar, int i) {
        final SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setAppTurnPageLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        nrf.cIT_(bVar.d, b2.getPicture(), nrf.e, 0, new AsyncLoadDrawableCallback() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.1
            @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
            public void getDrawable(Drawable drawable) {
                if (drawable == null) {
                    LogUtil.c("ColumnLayoutAdapter", "load round rectangle picture failed");
                    return;
                }
                Drawable drawable2 = bVar.d.getDrawable();
                if (drawable2 == null) {
                    LogUtil.c("ColumnLayoutAdapter", "mAppTurnPageImage background drawable is null,return");
                    return;
                }
                int intrinsicWidth = drawable2.getIntrinsicWidth();
                float d2 = eiv.d(8.0f);
                eiv.c(bVar.b, b2, new float[]{d2, d2, d2, d2, d2, d2, d2, d2});
                eiv.e(bVar.b, intrinsicWidth);
            }
        });
        eiv.d(bVar.e, b2.getTheme(), b2.isThemeVisibility());
        if (TextUtils.isEmpty(b2.getMarketingIcon()) || TextUtils.equals(b2.getMarketingIcon(), "none")) {
            bVar.f2837a.setVisibility(8);
        } else {
            bVar.f2837a.setVisibility(8);
            bVar.f2837a.setText(b2.getMarketingIcon().toUpperCase());
        }
        ViewGroup.LayoutParams layoutParams = bVar.c.getLayoutParams();
        layoutParams.width = e2;
        layoutParams.height = -2;
        bVar.c.setLayoutParams(layoutParams);
        amu_(b2, bVar.c, i);
    }

    private void af(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneGridLandscapeLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        ViewGroup.LayoutParams layoutParams = bVar.gl.getLayoutParams();
        int i2 = e2 / 2;
        layoutParams.width = e2;
        layoutParams.height = i2;
        bVar.gl.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.gl, b2.getPicture(), nrf.d, 1, 0);
        eiv.d(bVar.gq, b2.getTheme(), b2.isThemeVisibility());
        eiv.a(bVar.gj, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.gj, true, false);
        eiv.d(bVar.gm, (CornerTemplate) b2, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.gn.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = i2;
        bVar.gn.setLayoutParams(layoutParams2);
        amu_(b2, bVar.gn, i);
    }

    private void ah(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setTextLinkLayout singleGridContent is null.");
            return;
        }
        if (bVar.ff == null) {
            LogUtil.h("ColumnLayoutAdapter", "setTextLinkLayout titleTextView is null.");
            return;
        }
        if (!TextUtils.isEmpty(b2.getTheme())) {
            bVar.ff.setVisibility(0);
            bVar.ff.setText(b2.getTheme());
        } else {
            bVar.ff.setVisibility(8);
        }
        if (TextUtils.isEmpty(b2.getMarketingIcon()) || TextUtils.equals(b2.getMarketingIcon(), "none")) {
            bVar.fe.setVisibility(8);
        } else {
            bVar.fe.setVisibility(0);
            bVar.fe.setText(b2.getMarketingIcon().toUpperCase());
            if (TextUtils.equals(b2.getMarketingIcon(), "hot")) {
                bVar.fe.setBackground(this.o.getResources().getDrawable(R.drawable._2131430839_res_0x7f0b0db7));
            } else {
                bVar.fe.setBackground(this.o.getResources().getDrawable(R.drawable._2131430840_res_0x7f0b0db8));
            }
        }
        amu_(b2, bVar.fi, i);
    }

    private void q(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setOneGridSmallEcgLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        ViewGroup.LayoutParams layoutParams = bVar.dw.getLayoutParams();
        int i2 = (e2 * 9) / 27;
        layoutParams.width = e2;
        layoutParams.height = i2;
        bVar.dw.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = bVar.ec.getLayoutParams();
        layoutParams2.height = i2;
        layoutParams2.width = e2;
        bVar.ec.setLayoutParams(layoutParams2);
        eiv.d(bVar.ea, b2.getTheme(), b2.isThemeVisibility());
        eiv.d(bVar.dz, b2.getTheme(), b2.isThemeVisibility());
        eiv.d(bVar.ed, (CornerTemplate) b2, false);
        boolean s = nsn.s();
        if (!s) {
            if (eiv.b() && bVar.dz.getTextSize() > nsn.c(this.o, 28.0f)) {
                bVar.ea.setTextSize(1, 28.0f);
            }
            bVar.dz.setVisibility(8);
            bVar.ej.setVisibility(8);
            bVar.ea.setVisibility(0);
        } else {
            bVar.dz.setVisibility(0);
            bVar.dz.setTextSize(1, 28.0f);
            bVar.ea.setVisibility(8);
            bVar.eb.setVisibility(8);
        }
        eiv.a(bVar.ee, b2.getDescription(), b2.getDescriptionVisibility());
        eiv.a(bVar.ee, true, false);
        if (s) {
            bVar.ee.setVisibility(8);
        }
        nrf.cIS_(bVar.dw, b2.getPicture(), nrf.d, 0, 0);
        boolean z = !TextUtils.isEmpty(b2.getLinkValue()) && (b2.getLinkValue().contains("com.huawei.health.ecg.collection") || b2.getLinkValue().contains("com.huawei.ecgh5") || b2.getLinkValue().contains("com.huawei.health.h5.ecg")) && nsd.d();
        if (s) {
            bVar.ej.setVisibility(z ? 0 : 8);
        } else {
            bVar.eb.setVisibility(z ? 0 : 8);
        }
        amu_(b2, bVar.ec, i);
    }

    private void j(b bVar, int i) {
        SingleGridContent b2 = b(bVar, i);
        if (b2 == null) {
            LogUtil.h("ColumnLayoutAdapter", "setFourGradFullImageLayout singleGridContent is null.");
            return;
        }
        int e2 = eie.e(this.o, this.j, this.n.size());
        ViewGroup.LayoutParams layoutParams = bVar.v.getLayoutParams();
        int i2 = e2 / 2;
        layoutParams.width = e2;
        layoutParams.height = i2;
        bVar.v.setLayoutParams(layoutParams);
        nrf.cIS_(bVar.v, b2.getPicture(), nrf.d, 1, 0);
        eiv.d(bVar.r, (CornerTemplate) b2, false);
        eiv.d(bVar.x, b2.getTheme(), b2.isThemeVisibility());
        if (nsn.s()) {
            eiv.a(bVar.t, b2.getDescription(), false);
        } else {
            eiv.a(bVar.t, b2.getDescription(), b2.getDescriptionVisibility());
        }
        eiv.a(bVar.t, true, false);
        ViewGroup.LayoutParams layoutParams2 = bVar.y.getLayoutParams();
        layoutParams2.width = e2;
        layoutParams2.height = i2;
        bVar.y.setLayoutParams(layoutParams2);
        amu_(b2, bVar.y, i);
    }

    private void amu_(final SingleGridContent singleGridContent, final View view, final int i) {
        if (singleGridContent == null || view == null) {
            return;
        }
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.6
            @Override // java.lang.Runnable
            public void run() {
                ColumnLayoutAdapter.this.amt_(singleGridContent, view, i);
            }
        }, 100L);
        view.setOnClickListener(amr_(singleGridContent, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void amt_(SingleGridContent singleGridContent, View view, int i) {
        if (view == null || c(singleGridContent)) {
            return;
        }
        nsy.cMa_(view, new c(this, view, singleGridContent, i));
        nsy.cMb_(view, new c(this, view, singleGridContent, i));
        if (this.j == 47 && this.aa) {
            ObserverManagerUtil.d(this.m, "marketing_response_arrived");
            this.aa = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(SingleGridContent singleGridContent) {
        if (this.j != 47) {
            return singleGridContent != null && this.p.contains(singleGridContent);
        }
        if (singleGridContent == null) {
            return false;
        }
        String itemId = singleGridContent.getItemId();
        if (TextUtils.isEmpty(itemId)) {
            return false;
        }
        if (this.x) {
            return this.t.containsKey(itemId);
        }
        return this.t.containsKey(itemId) && (!this.v || this.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void amm_(SingleGridContent singleGridContent, View view, int i) {
        if (view == null || view.getVisibility() != 0 || singleGridContent == null) {
            return;
        }
        int height = view.getHeight();
        int width = view.getWidth();
        Rect rect = new Rect();
        if (view.getLocalVisibleRect(rect) && ColumnLinearLayout.aoN_(rect, height, width) && !c(singleGridContent)) {
            if (this.j == 47) {
                String itemId = singleGridContent.getItemId();
                singleGridContent.setItemPosition(i);
                if (TextUtils.isEmpty(itemId)) {
                    return;
                }
                if (this.x) {
                    this.t.put(itemId, singleGridContent);
                    b(1, singleGridContent, i);
                    return;
                } else {
                    this.t.put(itemId, singleGridContent);
                    return;
                }
            }
            this.p.add(singleGridContent);
            b(1, singleGridContent, i);
        }
    }

    private void amv_(final SingleGridContent singleGridContent, final View view, final int i) {
        if (singleGridContent == null || view == null) {
            return;
        }
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.7
            @Override // java.lang.Runnable
            public void run() {
                ColumnLayoutAdapter.this.amt_(singleGridContent, view, i);
            }
        }, 100L);
        if (TextUtils.isEmpty(singleGridContent.getLinkValue())) {
            LogUtil.h("ColumnLayoutAdapter", "setOnClickListener() linkValue is empty.");
        } else {
            view.setOnClickListener(ams_(singleGridContent, i));
        }
    }

    /* renamed from: com.huawei.health.marketing.views.ColumnLayoutAdapter$8, reason: invalid class name */
    public class AnonymousClass8 implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ SingleGridContent f2836a;
        final /* synthetic */ int d;

        AnonymousClass8(SingleGridContent singleGridContent, int i) {
            this.f2836a = singleGridContent;
            this.d = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String linkValue = this.f2836a.getLinkValue();
            if (!TextUtils.isEmpty(linkValue) && !nsn.o()) {
                if (ColumnLayoutAdapter.this.ab != null) {
                    ColumnLayoutAdapter.this.ab.onClick(view);
                }
                boolean b = eie.b(linkValue);
                final String str = linkValue + "&playing=true";
                if (!b) {
                    ColumnLayoutAdapter.this.b(2, this.f2836a, this.d);
                    ColumnLayoutAdapter.this.a(str, this.d, this.f2836a);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else {
                    LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                    final SingleGridContent singleGridContent = this.f2836a;
                    final int i = this.d;
                    loginInit.browsingToLogin(new IBaseResponseCallback() { // from class: eje
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i2, Object obj) {
                            ColumnLayoutAdapter.AnonymousClass8.this.d(singleGridContent, i, str, i2, obj);
                        }
                    }, AnalyticsValue.MARKETING_RESOURCE.value());
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void d(SingleGridContent singleGridContent, int i, String str, int i2, Object obj) {
            if (i2 == 0) {
                ColumnLayoutAdapter.this.b(2, singleGridContent, i);
                ColumnLayoutAdapter.this.a(str, i, singleGridContent);
            } else {
                LogUtil.h("ColumnLayoutAdapter", "getViewClickListener errorCode = ", Integer.valueOf(i2));
            }
        }
    }

    private View.OnClickListener ams_(SingleGridContent singleGridContent, int i) {
        return new AnonymousClass8(singleGridContent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void amp_(final SingleGridContent singleGridContent, final View view, final int i) {
        if (!CommonUtil.aa(this.o)) {
            nrh.b(BaseApplication.getContext(), R$string.IDS_network_connect_error);
            return;
        }
        String dynamicDataId = singleGridContent.getDynamicDataId();
        LogUtil.a("ColumnLayoutAdapter", "dealWithCouponClick.getDynamicDataId = ", dynamicDataId);
        ArrayList arrayList = new ArrayList();
        arrayList.add(dynamicDataId);
        ((PayApi) Services.c("TradeService", PayApi.class)).receiveCouponList(arrayList, new IBaseResponseCallback() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("ColumnLayoutAdapter", "receive CouponList errorCode = ", Integer.valueOf(i2));
                if (i2 == 0 || i2 == 3090004) {
                    ColumnLayoutAdapter.this.amo_(singleGridContent, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.assets?isImmerse&h5pro=true&pName=com.huawei.health&path=CouponDetail&urlType=4&couponId=" + singleGridContent.getDynamicDataId() + "&status=1", view, i);
                    return;
                }
                if (i2 >= 3090006 && i2 <= 3090009) {
                    nrh.b(BaseApplication.getContext(), R$string.IDS_member_buy_and_receive);
                    return;
                }
                if (i2 == 3090005) {
                    nrh.b(BaseApplication.getContext(), R$string.IDS_member_coupon_receive_out);
                    return;
                }
                if (i2 == 3090013) {
                    if (!(obj instanceof List)) {
                        LogUtil.h("ColumnLayoutAdapter", "objdata is not list.");
                        nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
                        return;
                    }
                    List list = (List) obj;
                    if (list.size() == 1) {
                        int resultCode = ((CouponResult) list.get(0)).getResultCode();
                        LogUtil.a("ColumnLayoutAdapter", "receive single Coupon errorCode = ", Integer.valueOf(i2));
                        ColumnLayoutAdapter.this.aml_(resultCode, singleGridContent, view, i);
                        return;
                    }
                    nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
                    return;
                }
                nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aml_(int i, SingleGridContent singleGridContent, View view, int i2) {
        if (i == 0 || i == 3090004) {
            amo_(singleGridContent, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.assets?isImmerse&h5pro=true&pName=com.huawei.health&path=CouponDetail&urlType=4&couponId=" + singleGridContent.getDynamicDataId() + "&status=1", view, i2);
            return;
        }
        if (i >= 3090006 && i <= 3090009) {
            nrh.b(BaseApplication.getContext(), R$string.IDS_member_buy_and_receive);
        } else if (i == 3090005) {
            nrh.b(BaseApplication.getContext(), R$string.IDS_member_coupon_receive_out);
        } else {
            nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void amo_(final SingleGridContent singleGridContent, String str, View view, final int i) {
        LogUtil.a("ColumnLayoutAdapter", "dealWithCouponClick.linkValue = ", str);
        if (TextUtils.isEmpty(str) || nsn.o()) {
            return;
        }
        View.OnClickListener onClickListener = this.ab;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        boolean b2 = eie.b(str);
        final String str2 = str + "&playing=true";
        if (!b2) {
            b(2, singleGridContent, i);
            a(str2, i, singleGridContent);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: eiy
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    ColumnLayoutAdapter.this.b(singleGridContent, i, str2, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
        }
    }

    public /* synthetic */ void b(SingleGridContent singleGridContent, int i, String str, int i2, Object obj) {
        if (i2 == 0) {
            b(2, singleGridContent, i);
            a(str, i, singleGridContent);
        } else {
            LogUtil.h("ColumnLayoutAdapter", "getViewClickListener errorCode = ", Integer.valueOf(i2));
        }
    }

    /* renamed from: com.huawei.health.marketing.views.ColumnLayoutAdapter$9, reason: invalid class name */
    public class AnonymousClass9 implements View.OnClickListener {
        final /* synthetic */ int b;
        final /* synthetic */ SingleGridContent d;

        AnonymousClass9(SingleGridContent singleGridContent, int i) {
            this.d = singleGridContent;
            this.b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            LogUtil.a("ColumnLayoutAdapter", "isCouponIdVisibility = ", Boolean.valueOf(this.d.isCouponIdVisibility()));
            if (this.d.isCouponIdVisibility()) {
                ThreadPoolManager d = ThreadPoolManager.d();
                final SingleGridContent singleGridContent = this.d;
                final int i = this.b;
                d.execute(new Runnable() { // from class: ejl
                    @Override // java.lang.Runnable
                    public final void run() {
                        ColumnLayoutAdapter.AnonymousClass9.this.amz_(singleGridContent, view, i);
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            final String linkValue = this.d.getLinkValue();
            if (!TextUtils.isEmpty(linkValue) && !nsn.o()) {
                if (ColumnLayoutAdapter.this.ab != null) {
                    ColumnLayoutAdapter.this.ab.onClick(view);
                    if (ColumnLayoutAdapter.this.z) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                if ((ColumnLayoutAdapter.this.j == 53 || ColumnLayoutAdapter.this.j == 15) && (linkValue.contains("com.huawei.health.ecg.collection") || linkValue.contains("com.huawei.ecgh5") || linkValue.contains("com.huawei.health.h5.ecg"))) {
                    nsd.a(false);
                    ColumnLayoutAdapter.this.notifyDataSetChanged();
                }
                if (linkValue.contains("com.huawei.study.hiresearch")) {
                    LogUtil.a("ColumnLayoutAdapter", "Not the first time in the Blood Pressure Health Study.");
                }
                if (TextUtils.equals(ColumnLayoutAdapter.this.g, SingleDailyMomentContent.INFORMATION_TYPE)) {
                    String dynamicDataId = this.d.getDynamicDataId();
                    if (nsn.c(dynamicDataId)) {
                        try {
                            ColumnRequestUtils.reportInfoReadNumber(Integer.parseInt(dynamicDataId));
                        } catch (NumberFormatException unused) {
                            LogUtil.b("ColumnLayoutAdapter", "getViewClickListener dynamicDataId NumberFormatException.");
                        }
                    }
                }
                if (!eie.b(linkValue)) {
                    ColumnLayoutAdapter.this.b(2, this.d, this.b);
                    ColumnLayoutAdapter.this.a(linkValue, this.b, this.d);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else {
                    LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                    final SingleGridContent singleGridContent2 = this.d;
                    final int i2 = this.b;
                    loginInit.browsingToLogin(new IBaseResponseCallback() { // from class: eji
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i3, Object obj) {
                            ColumnLayoutAdapter.AnonymousClass9.this.e(singleGridContent2, i2, linkValue, i3, obj);
                        }
                    }, AnalyticsValue.MARKETING_RESOURCE.value());
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void amz_(SingleGridContent singleGridContent, View view, int i) {
            ColumnLayoutAdapter.this.amp_(singleGridContent, view, i);
        }

        public /* synthetic */ void e(SingleGridContent singleGridContent, int i, String str, int i2, Object obj) {
            if (i2 == 0) {
                ColumnLayoutAdapter.this.b(2, singleGridContent, i);
                ColumnLayoutAdapter.this.a(str, i, singleGridContent);
            } else {
                LogUtil.h("ColumnLayoutAdapter", "getViewClickListener errorCode = ", Integer.valueOf(i2));
            }
        }
    }

    private View.OnClickListener amr_(SingleGridContent singleGridContent, int i) {
        return new AnonymousClass9(singleGridContent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i, SingleGridContent singleGridContent) {
        UserMemberInfo a2;
        LogUtil.a("ColumnLayoutAdapter", "linkValue: ", str);
        int i2 = this.j;
        if (i2 == 83 || i2 == 84) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.setPackage(this.o.getPackageName());
            intent.setFlags(268435456);
            jdw.bGh_(intent, this.o);
            return;
        }
        if ((i2 == 79 || i2 == 92) && b(str)) {
            Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent2.setPackage(this.o.getPackageName());
            intent2.setFlags(268435456);
            jdw.bGh_(intent2, this.o);
            return;
        }
        if (this.j == 96 && ((a2 = gpn.a()) == null || a2.getMemberFlag() == -1 || gpn.d(a2))) {
            AppRouter.b("/OperationBundle/MemberTypeSelectActivity").c("MEMBER_TYPE_SELECT_INDEX", 0).b(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031).c(this.o);
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(eil.c(eil.c(str, this.g), this.ag, i + 1, singleGridContent, this.ah));
        }
    }

    private boolean b(String str) {
        return str != null && str.startsWith("huaweischeme://healthapp/basicHealth?healthType=1&pageType=series_course_detail");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.j;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = this.j;
        if (i == 8) {
            if (this.ac) {
                List<SingleGridContent> list = this.ad.get(0);
                this.n = list;
                if (list == null) {
                    return 0;
                }
                return list.size();
            }
            List<List<SingleGridContent>> list2 = this.ad;
            if (list2 == null) {
                return 0;
            }
            return list2.size();
        }
        if (i == 48) {
            List<List<SingleGridContent>> list3 = this.ad;
            if (list3 == null) {
                return 0;
            }
            return list3.size();
        }
        if (i == 10 || i == 23) {
            List<SingleEntryContent> list4 = this.ae;
            if (list4 == null) {
                return 0;
            }
            return list4.size();
        }
        if (i == 113) {
            List<SingleHotSeriesRecommendContent> list5 = this.i;
            if (list5 == null) {
                return 0;
            }
            return list5.size();
        }
        if (i == 116) {
            List<SingleFunctionGridContent> list6 = this.an;
            if (list6 == null) {
                return 0;
            }
            return list6.size();
        }
        List<SingleGridContent> list7 = this.n;
        if (list7 == null) {
            return 0;
        }
        return list7.size();
    }

    public static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2837a;
        private HealthTextView aa;
        private Typeface ab;
        private HealthTextView ac;
        private RelativeLayout ad;
        private ImageView ae;
        private HealthTextView af;
        private ImageView ag;
        private ImageView ah;
        private ImageView ai;
        private ImageView aj;
        private HealthTextView ak;
        private HealthTextView al;
        private HealthTextView am;
        private LinearLayout an;
        private HealthTextView ao;
        private LinearLayout ap;
        private ImageView aq;
        private HealthDivider ar;
        private HealthTextView as;
        private RelativeLayout at;
        private ImageView au;
        private HealthTextView av;
        private HealthTextView aw;
        private HealthCardView ax;
        private HealthTextView ay;
        private LinearLayout az;
        private HealthTextView b;
        private HealthTextView ba;
        private HealthTextView bb;
        private ImageView bc;
        private LinearLayout bd;
        private HealthTextView be;
        private HealthTextView bf;
        private ImageView bg;
        private HealthTextView bh;
        private HealthTextView bi;
        private ImageView bj;
        private HealthTextView bk;
        private LinearLayout bl;
        private HealthTextView bm;
        private HealthImageView bn;
        private HealthTextView bo;
        private HealthTextView bp;
        private HealthTextView bq;
        private View br;
        private View bs;
        private HealthTextView bt;
        private HealthTextView bu;
        private HealthCardView bv;
        private ImageView bw;
        private ImageView bx;
        private HealthTextView by;
        private HealthTextView bz;
        private RelativeLayout c;
        private HealthTextView ca;
        private HealthTextView cb;
        private HealthTextView cc;
        private HealthTextView cd;
        private RelativeLayout ce;
        private HealthTextView cf;
        private HealthTextView cg;
        private LinearLayout ch;
        private HealthTextView ci;
        private HealthTextView cj;
        private ImageView ck;
        private RelativeLayout cl;
        private ImageView cm;
        private ImageView cn;
        private HealthTextView co;
        private RelativeLayout cp;
        private HealthTextView cq;
        private HealthTextView cr;
        private HealthTextView cs;
        private HealthTextView ct;
        private ImageView cu;
        private HealthTextView cv;
        private RelativeLayout cw;
        private HealthCardView cx;
        private HealthTextView cy;
        private HealthTextView cz;
        private ImageView d;
        private ImageView da;
        private HealthTextView db;
        private ImageView dc;
        private HealthTextView dd;
        private HealthTextView de;
        private ImageView df;
        private LinearLayout dg;
        private HealthTextView dh;
        private HealthTextView di;
        private ImageView dj;
        private HealthCardView dk;
        private HealthCardView dl;
        private LinearLayout dm;
        private HealthTextView dn;

        /* renamed from: do, reason: not valid java name */
        private ImageView f1do;
        private HealthTextView dp;
        private ImageView dq;
        private HealthTextView dr;
        private HealthTextView ds;
        private HealthTextView dt;
        private HealthCardView du;
        private ImageView dv;
        private ImageView dw;
        private HealthTextView dx;
        private HealthTextView dy;
        private HealthTextView dz;
        private HealthTextView e;
        private HealthTextView ea;
        private ImageView eb;
        private RelativeLayout ec;
        private HealthTextView ed;
        private HealthTextView ee;
        private ImageView ef;
        private HealthTextView eg;
        private HealthTextView eh;
        private RelativeLayout ei;
        private ImageView ej;
        private HealthTextView ek;
        private HealthTextView el;
        private HealthTextView em;
        private HealthTextView en;
        private HealthTextView eo;
        private ImageView ep;
        private RelativeLayout eq;
        private HealthTextView er;
        private RelativeLayout es;
        private ImageView et;
        private ImageView eu;
        private RelativeLayout ev;
        private ImageView ew;
        private ImageView ex;
        private ImageView ey;
        private HealthTextView ez;
        private RelativeLayout f;
        private RelativeLayout fa;
        private RelativeLayout fb;
        private RelativeLayout fc;
        private HealthTextView fd;
        private HealthTextView fe;
        private HealthTextView ff;
        private HealthTextView fg;
        private HealthTextView fh;
        private RelativeLayout fi;
        private RelativeLayout fj;
        private HealthTextView fk;
        private HealthTextView fl;
        private ImageView fm;
        private HealthTextView fn;
        private HealthTextView fo;
        private RelativeLayout fp;
        private ImageView fq;
        private HealthTextView fr;
        private HealthTextView fs;
        private HealthTextView ft;
        private HealthTextView fu;
        private HealthTextView fv;
        private ImageView fw;
        private HealthTextView fx;
        private HealthCardView fy;
        private HealthCardView fz;
        private ImageView g;
        private RelativeLayout ga;
        private RelativeLayout gb;
        private HealthTextView gc;
        private HealthTextView gd;
        private HealthTextView ge;
        private HealthTextView gf;
        private LinearLayout gg;
        private ImageView gh;
        private HealthTextView gi;
        private HealthTextView gj;
        private HealthCardView gk;
        private ImageView gl;
        private HealthTextView gm;
        private RelativeLayout gn;
        private HealthDivider go;
        private HealthTextView gp;
        private HealthTextView gq;
        private ImageView gr;
        private HealthTextView gs;
        private ImageView gt;
        private RelativeLayout gu;
        private HealthTextView gv;
        private HealthTextView gw;
        private HealthTextView gy;
        private HealthTextView h;
        private RelativeLayout ha;
        private HealthTextView i;
        private HealthCardView j;
        private ImageView k;
        private RelativeLayout l;
        private HealthTextView m;
        private MeasureCardView n;
        private HealthCardView o;
        private HealthTextView p;
        private HealthTextView q;
        private HealthTextView r;
        private HealthTextView s;
        private HealthTextView t;
        private HealthImageView u;
        private ImageView v;
        private HealthTextView w;
        private HealthTextView x;
        private RelativeLayout y;
        private ImageView z;

        b(View view, int i, boolean z) {
            super(view);
            if (i == 35) {
                aoB_(view);
                return;
            }
            if (i == 36) {
                aoe_(view);
                return;
            }
            if (i == 39 || i == 40) {
                aoE_(view);
                return;
            }
            if (i == 53) {
                aop_(view);
                return;
            }
            if (i == 59) {
                aoG_(view);
                return;
            }
            if (i == 76) {
                aom_(view);
                return;
            }
            if (i == 92) {
                aoz_(view);
                return;
            }
            if (i == 94) {
                aoH_(view);
                return;
            }
            if (i == 96) {
                aor_(view);
                return;
            }
            if (i == 107) {
                aoo_(view);
                return;
            }
            if (i == 116) {
                aoj_(view);
                return;
            }
            if (i == 9999) {
                aok_(view);
                return;
            }
            if (i == 79) {
                aoy_(view);
                return;
            }
            if (i != 80) {
                switch (i) {
                    case 6:
                        aon_(view);
                        return;
                    case 7:
                    case 9:
                        aoD_(view);
                        return;
                    case 8:
                        aoF_(view);
                        aol_(view);
                        return;
                    case 10:
                        break;
                    default:
                        switch (i) {
                            case 15:
                                aoq_(view);
                                return;
                            case 16:
                                aot_(view);
                                return;
                            case 17:
                                aoF_(view);
                                return;
                            default:
                                switch (i) {
                                    case 22:
                                        aof_(view);
                                        break;
                                    case 23:
                                        break;
                                    case 24:
                                        if (z) {
                                            aoI_(view);
                                            break;
                                        } else {
                                            aoi_(view);
                                            break;
                                        }
                                    default:
                                        switch (i) {
                                            case 47:
                                                aoh_(view);
                                                break;
                                            case 48:
                                                aoC_(view);
                                                break;
                                            case 49:
                                                aog_(view);
                                                break;
                                            default:
                                                switch (i) {
                                                    case 82:
                                                        aow_(view);
                                                        break;
                                                    case 83:
                                                        aov_(view);
                                                        break;
                                                    case 84:
                                                        aou_(view);
                                                        break;
                                                    case 85:
                                                        aox_(view);
                                                        break;
                                                    default:
                                                        LogUtil.h("GridPageHolder", "create GridPageHolder failed, unknown contentType = ", Integer.valueOf(i));
                                                        break;
                                                }
                                        }
                                }
                                return;
                        }
                }
                aos_(view);
                return;
            }
            aoA_(view);
        }

        private void aoj_(View view) {
            this.at = (RelativeLayout) view.findViewById(R.id.item_quick_entry_root_layout);
            this.au = (ImageView) view.findViewById(R.id.item_multi_function_image);
            this.aw = (HealthTextView) view.findViewById(R.id.item_multi_function_title);
            this.av = (HealthTextView) view.findViewById(R.id.item_quick_entry_sub_title);
            eiv.a(this.aw, false, true);
            eiv.a(this.av, false, true);
        }

        private void aom_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.bn = (HealthImageView) view.findViewById(R.id.img);
            this.bo = (HealthTextView) view.findViewById(R.id.name);
            this.br = view.findViewById(R.id.two_prices_layout);
            this.bs = view.findViewById(R.id.vip_price_layout);
            this.bq = (HealthTextView) view.findViewById(R.id.description);
            this.bp = (HealthTextView) view.findViewById(R.id.corner_mark);
            this.ab = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        }

        private void aoo_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.bx = (ImageView) view.findViewById(R.id.background_image);
            this.cc = (HealthTextView) view.findViewById(R.id.section_title);
            this.bz = (HealthTextView) view.findViewById(R.id.section_difficulty);
            this.by = (HealthTextView) view.findViewById(R.id.section_duration);
            this.ca = (HealthTextView) view.findViewById(R.id.section_calorie);
            this.cg = (HealthTextView) view.findViewById(R.id.section_train_number);
            eiv.a(this.cc, false, false);
            eiv.a(this.bz, false, false);
            eiv.a(this.by, false, false);
            eiv.a(this.ca, false, false);
        }

        private void aov_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSleepPopluarCoursesHolder() itemView is null.");
                return;
            }
            this.dg = (LinearLayout) view.findViewById(R.id.item_series_courses_sleep_root_layout);
            this.dc = (ImageView) view.findViewById(R.id.item_series_courses_image);
            this.di = (HealthTextView) view.findViewById(R.id.item_series_courses_title);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.item_series_courses_chapters);
            this.db = healthTextView;
            healthTextView.setVisibility(8);
            this.dd = (HealthTextView) view.findViewById(R.id.item_series_courses_recommended_course);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_series_courses_play_button);
            this.df = imageView;
            imageView.setVisibility(8);
            this.j = (HealthCardView) view.findViewById(R.id.card_bottom);
            this.ax = (HealthCardView) view.findViewById(R.id.card_middle);
            Context context = view.getContext();
            if (context != null) {
                if (nrt.a(context)) {
                    this.j.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                    this.ax.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                } else {
                    this.j.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                    this.ax.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                }
            }
            aoJ_(view);
            eiv.a(this.di, false, false);
            eiv.a(this.db, true, false);
            eiv.a(this.dd, false, false);
        }

        private void aoJ_(View view) {
            final HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.item_series_courses_card_view);
            final HealthCardView healthCardView2 = (HealthCardView) view.findViewById(R.id.card_middle);
            final HealthCardView healthCardView3 = (HealthCardView) view.findViewById(R.id.card_bottom);
            healthCardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.b.4
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    healthCardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int measuredWidth = healthCardView.getMeasuredWidth();
                    int c = nsn.c(BaseApplication.getContext(), 4.0f);
                    int i = c * 2;
                    ViewGroup.LayoutParams layoutParams = healthCardView2.getLayoutParams();
                    if (layoutParams instanceof RelativeLayout.LayoutParams) {
                        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                        layoutParams2.width = measuredWidth - i;
                        healthCardView2.setLayoutParams(layoutParams2);
                    }
                    ViewGroup.LayoutParams layoutParams3 = healthCardView3.getLayoutParams();
                    if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
                        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
                        layoutParams4.width = measuredWidth - (c * 4);
                        healthCardView3.setLayoutParams(layoutParams4);
                    }
                }
            });
        }

        private void aox_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSleepYogaHolder() itemView is null.");
                return;
            }
            this.du = (HealthCardView) view.findViewById(R.id.item_sleep_yoga_card_view);
            this.dq = (ImageView) view.findViewById(R.id.item_sleep_yoga_image);
            this.dr = (HealthTextView) view.findViewById(R.id.sleep_yoga_title);
            this.dt = (HealthTextView) view.findViewById(R.id.sleep_yoga_description);
        }

        private void aou_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSeriesCourseThirdPartHolder() itemView is null.");
                return;
            }
            this.o = (HealthCardView) view.findViewById(R.id.item_series_courses_card_view);
            this.ae = (ImageView) view.findViewById(R.id.item_series_courses_image);
            this.gf = (HealthTextView) view.findViewById(R.id.title);
            this.m = (HealthTextView) view.findViewById(R.id.description);
            this.co = (HealthTextView) view.findViewById(R.id.play_percent);
            this.fy = (HealthCardView) view.findViewById(R.id.bottom_card);
            this.fz = (HealthCardView) view.findViewById(R.id.middle_card);
            Context context = view.getContext();
            if (context != null) {
                if (nrt.a(context)) {
                    this.fy.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                    this.fz.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                } else {
                    this.fy.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                    this.fz.setCardBackgroundColor(view.getContext().getColor(R.color._2131296920_res_0x7f090298));
                }
            }
        }

        private void aoq_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getOneGridSmallHolder() itemView is null.");
                return;
            }
            this.ei = (RelativeLayout) view.findViewById(R.id.item_one_grid_small_root_layout);
            this.dv = (ImageView) view.findViewById(R.id.item_one_grid_small_image);
            this.dx = (HealthTextView) view.findViewById(R.id.item_one_grid_small_status);
            this.ds = (HealthTextView) view.findViewById(R.id.item_one_grid_small_join_num);
            this.eh = (HealthTextView) view.findViewById(R.id.item_one_grid_small_title);
            this.dy = (HealthTextView) view.findViewById(R.id.item_one_grid_small_description);
            this.ef = (ImageView) view.findViewById(R.id.item_one_grid_small_red);
            eiv.a(this.dx, false, false);
            eiv.a(this.ds, false, false);
            eiv.a(this.eh, false, true);
            eiv.a(this.dy, true, false);
        }

        private void aoD_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getTwoGridLandscapeHolder() itemView is null.");
                return;
            }
            this.gk = (HealthCardView) view.findViewById(R.id.item_two_landscape_layout);
            this.gh = (ImageView) view.findViewById(R.id.item_two_landscape_image);
            this.gg = (LinearLayout) view.findViewById(R.id.item_two_landscape_in_layout);
            this.gi = (HealthTextView) view.findViewById(R.id.item_two_landscape_title);
            this.gd = (HealthTextView) view.findViewById(R.id.item_two_landscape_description);
            this.ge = (HealthTextView) view.findViewById(R.id.item_two_landscape_corner);
            eiv.a(this.gi, false, true);
            eiv.a(this.gd, true, false);
        }

        private void aot_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSecondPalaceVerticalHolder() itemView is null.");
                return;
            }
            this.cw = (RelativeLayout) view.findViewById(R.id.item_two_big_landscape_layout);
            this.cu = (ImageView) view.findViewById(R.id.item_two_big_landscape_image);
            this.ct = (HealthTextView) view.findViewById(R.id.item_two_big_landscape_corner);
            this.cs = (HealthTextView) view.findViewById(R.id.item_two_big_landscape_title);
            this.cr = (HealthTextView) view.findViewById(R.id.item_two_big_landscape_description);
            eiv.a(this.cs, false, true);
            eiv.a(this.cr, true, false);
        }

        private void aoF_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getTwoGridVerticalTwoItemView() itemView is null.");
                return;
            }
            this.cx = (HealthCardView) view.findViewById(R.id.item_second_vertical_two_layout);
            this.de = (HealthTextView) view.findViewById(R.id.item_second_vertical_two_title);
            this.cz = (HealthTextView) view.findViewById(R.id.item_second_vertical_two_describe);
            this.da = (ImageView) view.findViewById(R.id.item_second_vertical_two_image);
            this.cy = (HealthTextView) view.findViewById(R.id.item_second_vertical_two_corner);
            eiv.a(this.de, false, true);
            eiv.a(this.cz, true, false);
        }

        private void aol_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getOneAddTwoItemView() itemView is null.");
                return;
            }
            this.ch = (LinearLayout) view.findViewById(R.id.item_one_two_root_layout);
            this.az = (LinearLayout) view.findViewById(R.id.item_one_two_big_layout);
            this.ba = (HealthTextView) view.findViewById(R.id.item_one_two_big_title);
            this.ay = (HealthTextView) view.findViewById(R.id.item_one_two_big_describe);
            this.bc = (ImageView) view.findViewById(R.id.item_one_two_big_image);
            this.bb = (HealthTextView) view.findViewById(R.id.item_one_two_big_corner);
            this.bl = (LinearLayout) view.findViewById(R.id.item_one_two_right_top_layout);
            this.bm = (HealthTextView) view.findViewById(R.id.item_one_two_right_top_title);
            this.bk = (HealthTextView) view.findViewById(R.id.item_one_two_right_top_describe);
            this.bj = (ImageView) view.findViewById(R.id.item_one_two_right_top_image);
            this.bi = (HealthTextView) view.findViewById(R.id.item_one_two_right_top_corner);
            this.bd = (LinearLayout) view.findViewById(R.id.item_one_two_right_bottom_layout);
            this.be = (HealthTextView) view.findViewById(R.id.item_one_two_right_bottom_title);
            this.bf = (HealthTextView) view.findViewById(R.id.item_one_two_right_bottom_describe);
            this.bg = (ImageView) view.findViewById(R.id.item_one_two_right_bottom_image);
            this.bh = (HealthTextView) view.findViewById(R.id.item_one_two_right_bottom_corner);
            eiv.a(this.ba, false, true);
            eiv.a(this.ay, true, false);
            eiv.a(this.bm, false, true);
            eiv.a(this.bk, true, false);
            eiv.a(this.be, false, true);
            eiv.a(this.bf, true, false);
        }

        private void aoC_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getThreeGradFullImageView() itemView is null.");
                return;
            }
            this.gb = (RelativeLayout) view.findViewById(R.id.item_three_full_layout);
            this.fj = (RelativeLayout) view.findViewById(R.id.item_three_full_image_layout);
            this.fk = (HealthTextView) view.findViewById(R.id.item_three_full_image_title);
            this.fl = (HealthTextView) view.findViewById(R.id.item_three_full_image_describe);
            this.fm = (ImageView) view.findViewById(R.id.item_three_full_image_image);
            this.fh = (HealthTextView) view.findViewById(R.id.item_three_full_image_corner);
            this.fn = (HealthTextView) view.findViewById(R.id.item_three_full_image_join_num);
            this.ga = (RelativeLayout) view.findViewById(R.id.item_three_full_image_top_layout);
            this.gc = (HealthTextView) view.findViewById(R.id.item_three_full_image_top_title);
            this.ft = (HealthTextView) view.findViewById(R.id.item_three_full_image_top_describe);
            this.fw = (ImageView) view.findViewById(R.id.item_three_full_image_top_image);
            this.fu = (HealthTextView) view.findViewById(R.id.item_three_full_image_top_corner);
            this.fx = (HealthTextView) view.findViewById(R.id.item_three_full_image_top_join_num);
            this.fp = (RelativeLayout) view.findViewById(R.id.item_three_full_image_bottom_layout);
            this.fv = (HealthTextView) view.findViewById(R.id.item_three_full_image_bottom_title);
            this.fo = (HealthTextView) view.findViewById(R.id.item_three_full_image_bottom_describe);
            this.fq = (ImageView) view.findViewById(R.id.item_three_full_image_bottom_image);
            this.fr = (HealthTextView) view.findViewById(R.id.item_three_full_image_bottom_corner);
            this.fs = (HealthTextView) view.findViewById(R.id.item_three_full_image_bottom_join_num);
            eiv.a(this.fk, false, true);
            eiv.a(this.fl, true, false);
            eiv.a(this.fn, false, false);
            eiv.a(this.gc, false, true);
            eiv.a(this.ft, true, false);
            eiv.a(this.fx, false, false);
            eiv.a(this.fv, false, true);
            eiv.a(this.fo, true, false);
            eiv.a(this.fs, false, false);
        }

        private void aon_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getOneGridBigHolder() itemView is null.");
                return;
            }
            this.bv = (HealthCardView) view.findViewById(R.id.item_one_grid_big_root_layout);
            this.ce = (RelativeLayout) view.findViewById(R.id.item_one_grid_big_image_layout);
            this.bw = (ImageView) view.findViewById(R.id.item_one_grid_big_image);
            this.cd = (HealthTextView) view.findViewById(R.id.item_one_grid_big_status);
            this.cf = (HealthTextView) view.findViewById(R.id.item_one_grid_big_title);
            this.cb = (HealthTextView) view.findViewById(R.id.item_one_grid_big_join_num);
            this.bt = (HealthTextView) view.findViewById(R.id.item_one_grid_big_attribute_text);
            this.bu = (HealthTextView) view.findViewById(R.id.item_one_grid_big_description);
            eiv.a(this.cd, false, false);
            eiv.a(this.cf, false, true);
            eiv.a(this.cb, false, false);
            eiv.a(this.bt, false, false);
            eiv.a(this.bu, true, false);
        }

        private void aor_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.cl = (RelativeLayout) view.findViewById(R.id.item_one_point_eight_grid_slide_root_layout);
            this.ck = (ImageView) view.findViewById(R.id.item_one_point_eight_grid_slide_image);
            this.cj = (HealthTextView) view.findViewById(R.id.item_one_point_eight_grid_slide_title);
            this.ci = (HealthTextView) view.findViewById(R.id.item_one_point_eight_grid_slide_description);
            eiv.a(this.cj, false, false);
            eiv.a(this.ci, false, false);
        }

        private void aoH_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.ha = (RelativeLayout) view.findViewById(R.id.item_vertical_rectangle_slide_root_layout);
            this.gt = (ImageView) view.findViewById(R.id.item_vertical_rectangle_slide_image);
            this.gv = (HealthTextView) view.findViewById(R.id.item_vertical_rectangle_slide_corner);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.item_vertical_rectangle_slide_title);
            this.gy = healthTextView;
            eiv.a(healthTextView, false, false);
        }

        private void aoG_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getTwoLinesLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.gu = (RelativeLayout) view.findViewById(R.id.item_twolines_landscape_slide_root_layout);
            this.gr = (ImageView) view.findViewById(R.id.item_twolines_landscape_slide_image);
            this.gw = (HealthTextView) view.findViewById(R.id.item_twolines_landscape_slide_title);
            this.gs = (HealthTextView) view.findViewById(R.id.item_twolines_landscape_slide_time);
            this.go = (HealthDivider) view.findViewById(R.id.item_twolines_divider);
            this.gp = (HealthTextView) view.findViewById(R.id.item_twolines_landscape_slide_corner);
            eiv.a(this.gw, false, true);
            eiv.a(this.gs, false, false);
        }

        private void aof_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getApplicationIconHolder() itemView is null.");
                return;
            }
            this.f = (RelativeLayout) view.findViewById(R.id.item_application_icon_root_layout);
            this.g = (ImageView) view.findViewById(R.id.item_application_icon_image);
            this.h = (HealthTextView) view.findViewById(R.id.item_application_icon_title);
            this.i = (HealthTextView) view.findViewById(R.id.item_application_icon_root_top_corner_mark);
            eiv.a(this.h, false, true);
        }

        private void aos_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getQuickEntryHolder() itemView is null.");
                return;
            }
            this.cp = (RelativeLayout) view.findViewById(R.id.item_quick_entry_root_layout);
            this.cm = (ImageView) view.findViewById(R.id.item_quick_entry_image);
            this.cn = (ImageView) view.findViewById(R.id.item_quick_entry_red_point);
            this.cq = (HealthTextView) view.findViewById(R.id.item_quick_entry_red_name);
            this.cv = (HealthTextView) view.findViewById(R.id.item_quick_entry_title);
            eiv.a(this.cq, false, true);
            eiv.a(this.cv, false, true);
        }

        private void aoi_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getImageTextHolder() itemView is null.");
                return;
            }
            this.ap = (LinearLayout) view.findViewById(R.id.item_image_text_layout);
            this.aq = (ImageView) view.findViewById(R.id.item_image_text_image);
            this.as = (HealthTextView) view.findViewById(R.id.item_image_text_title);
            this.ao = (HealthTextView) view.findViewById(R.id.item_image_text_description);
            this.ar = (HealthDivider) view.findViewById(R.id.item_image_text_line);
            this.ak = (HealthTextView) view.findViewById(R.id.item_image_text_corner_mark);
            if (nsn.s()) {
                nsn.b(this.as);
            }
            if (nsn.c() < 1.45f || nsn.c() >= 2.9f) {
                return;
            }
            this.as.setTextSize(1, (this.as.getTextSize() / this.as.getContext().getResources().getDisplayMetrics().scaledDensity) * ColumnLayoutAdapter.e);
        }

        private void aoI_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "initImageTextBlankHolder failed, itemView is null.");
                return;
            }
            this.an = (LinearLayout) view.findViewById(R.id.item_image_text_blank_layout);
            this.aj = (ImageView) view.findViewById(R.id.item_image_text_blank_image);
            this.am = (HealthTextView) view.findViewById(R.id.item_image_text_blank_title);
            this.af = (HealthTextView) view.findViewById(R.id.item_image_text_blank_description);
            this.ag = (ImageView) view.findViewById(R.id.id_iv_image_text_blank_head_1);
            this.ai = (ImageView) view.findViewById(R.id.id_iv_image_text_blank_head_2);
            this.ah = (ImageView) view.findViewById(R.id.id_iv_image_text_blank_head_3);
            this.al = (HealthTextView) view.findViewById(R.id.id_tv_image_text_blank_statistic);
            if (nsn.s()) {
                nsn.b(this.as);
            }
            if (nsn.c() < 1.45f || nsn.c() >= 2.9f) {
                return;
            }
            this.am.setTextSize(1, (this.am.getTextSize() / this.am.getContext().getResources().getDisplayMetrics().scaledDensity) * ColumnLayoutAdapter.e);
        }

        private void aoh_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getFunctionMenuHolder() itemView is null.");
                return;
            }
            this.ad = (RelativeLayout) view.findViewById(R.id.item_function_menu_root_layout);
            this.u = (HealthImageView) view.findViewById(R.id.item_function_menu_image);
            this.z = (ImageView) view.findViewById(R.id.item_function_menu_red_dot);
            this.ac = (HealthTextView) view.findViewById(R.id.item_function_menu_message);
            this.w = (HealthTextView) view.findViewById(R.id.item_function_menu_corner);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.item_function_menu_title);
            this.aa = healthTextView;
            healthTextView.setHyphenationFrequency(1);
            this.aa.setBreakStrategy(1);
            eiv.a(this.aa, false, true);
        }

        private void aoe_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getAppTurnPageHolder() itemView is null.");
                return;
            }
            this.c = (RelativeLayout) view.findViewById(R.id.item_app_turn_page_root_layout);
            this.d = (ImageView) view.findViewById(R.id.item_app_turn_page_image);
            this.f2837a = (HealthTextView) view.findViewById(R.id.item_app_turn_page_red_name);
            this.e = (HealthTextView) view.findViewById(R.id.item_app_turn_page_title);
            this.b = (HealthTextView) view.findViewById(R.id.item_app_turn_page_top_corner_mark);
            eiv.a(this.f2837a, false, true);
            eiv.a(this.e, false, true);
        }

        private void aoE_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getTwoGridLandscapeTwoHolder() itemView is null.");
                return;
            }
            this.gn = (RelativeLayout) view.findViewById(R.id.item_two_grad_landscape_two_layout);
            this.gl = (ImageView) view.findViewById(R.id.item_two_grad_landscape_two_image);
            this.gq = (HealthTextView) view.findViewById(R.id.item_two_grad_landscape_two_title);
            this.gj = (HealthTextView) view.findViewById(R.id.item_two_grad_landscape_two_description);
            this.gm = (HealthTextView) view.findViewById(R.id.item_two_grad_landscape_two_corner);
            eiv.a(this.gq, false, true);
            eiv.a(this.gj, true, false);
        }

        private void aoB_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getTextLinkHolder() itemView is null.");
                return;
            }
            this.fi = (RelativeLayout) view.findViewById(R.id.item_text_link_root_layout);
            this.fe = (HealthTextView) view.findViewById(R.id.item_text_link_icon);
            this.ff = (HealthTextView) view.findViewById(R.id.item_text_link_title);
            eiv.a(this.fe, false, false);
            eiv.a(this.ff, false, false);
        }

        private void aok_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getOneGridSmallEcgHolder() itemView is null.");
                return;
            }
            this.l = (RelativeLayout) view.findViewById(R.id.root_relative_layout);
            this.q = (HealthTextView) view.findViewById(R.id.title);
            this.k = (ImageView) view.findViewById(R.id.icon);
            this.n = (MeasureCardView) view.findViewById(R.id.measure_card_view);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.primary_tip);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.secondary_tip);
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.time_text);
            eiv.a(this.q, false, true);
            eiv.a(healthTextView, false, true);
            eiv.a(healthTextView2, false, true);
            eiv.a(healthTextView3, false, true);
        }

        private void aop_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getOneGridSmallEcgHolder() itemView is null.");
                return;
            }
            this.ec = (RelativeLayout) view.findViewById(R.id.item_one_grid_small_ecg_root_layout);
            this.dw = (ImageView) view.findViewById(R.id.item_one_grid_small_ecg_image);
            this.ea = (HealthTextView) view.findViewById(R.id.item_one_grid_small_ecg_title);
            this.dz = (HealthTextView) view.findViewById(R.id.item_one_grid_small_title);
            this.ee = (HealthTextView) view.findViewById(R.id.item_one_grid_small_ecg_description);
            this.eb = (ImageView) view.findViewById(R.id.item_one_grid_small_ecg_red);
            this.ej = (ImageView) view.findViewById(R.id.item_one_grid_small_red);
            this.ed = (HealthTextView) view.findViewById(R.id.item_one_grid_small_ecg_corner);
            eiv.a(this.ea, false, true);
            eiv.a(this.dz, false, true);
            eiv.a(this.ee, true, false);
        }

        private void aoy_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.fb = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_root_layout_favorites);
            this.eq = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_image_layout_favorites);
            this.ew = (ImageView) view.findViewById(R.id.item_square_landscape_icon_favorites);
            this.ep = (ImageView) view.findViewById(R.id.item_square_landscape_slide_image_favorites);
            this.fd = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_title_favorites);
            this.el = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_description_favorites);
            this.eg = (HealthTextView) view.findViewById(R.id.item_square_landscape_corner_favorites);
            this.p = (HealthTextView) view.findViewById(R.id.item_square_landscape_series_button);
            eiv.a(this.fd, false, true);
            eiv.a(this.el, true, false);
        }

        private void aoz_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.fc = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_root_layout_favorites);
            this.es = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_image_layout_favorites);
            this.ex = (ImageView) view.findViewById(R.id.item_square_landscape_icon_favorites);
            this.et = (ImageView) view.findViewById(R.id.item_square_landscape_slide_image_favorites);
            this.ez = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_title_favorites);
            this.em = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_description_favorites);
            this.eo = (HealthTextView) view.findViewById(R.id.item_square_landscape_corner_favorites);
            this.s = (HealthTextView) view.findViewById(R.id.item_square_landscape_series_button);
            eiv.a(this.ez, false, true);
            eiv.a(this.em, true, false);
        }

        private void aoA_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.fa = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_root_layout_sleep);
            this.ev = (RelativeLayout) view.findViewById(R.id.item_square_landscape_slide_image_layout_sleep);
            this.ey = (ImageView) view.findViewById(R.id.item_square_landscape_icon_sleep);
            this.er = (HealthTextView) view.findViewById(R.id.item_square_landscape_hot_amount_sleep);
            this.eu = (ImageView) view.findViewById(R.id.item_square_landscape_slide_image_sleep);
            this.fg = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_title_sleep);
            this.en = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_description_sleep);
            this.ek = (HealthTextView) view.findViewById(R.id.item_square_landscape_corner_sleep);
            eiv.a(this.er, false, false);
            eiv.a(this.fg, false, true);
            eiv.a(this.en, true, false);
        }

        private void aow_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getSquareLandscapeSlideHolder() itemView is null.");
                return;
            }
            this.dm = (LinearLayout) view.findViewById(R.id.item_series_courses_sleep_root_layout);
            this.dj = (ImageView) view.findViewById(R.id.item_series_courses_image);
            this.dn = (HealthTextView) view.findViewById(R.id.item_series_courses_title);
            this.dh = (HealthTextView) view.findViewById(R.id.item_series_courses_chapters);
            this.dp = (HealthTextView) view.findViewById(R.id.item_series_courses_recommended_course);
            this.f1do = (ImageView) view.findViewById(R.id.item_series_courses_play_button);
            this.dk = (HealthCardView) view.findViewById(R.id.card_bottom);
            this.dl = (HealthCardView) view.findViewById(R.id.card_middle);
            Context context = view.getContext();
            if (context != null) {
                if (nrt.a(context)) {
                    this.dk.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                    this.dl.setCardBackgroundColor(view.getContext().getColor(R.color._2131296989_res_0x7f0902dd));
                } else {
                    this.dk.setCardBackgroundColor(view.getContext().getColor(R.color._2131296911_res_0x7f09028f));
                    this.dl.setCardBackgroundColor(view.getContext().getColor(R.color._2131296911_res_0x7f09028f));
                }
            }
            aoJ_(view);
            eiv.a(this.dn, false, false);
            eiv.a(this.dh, true, false);
            eiv.a(this.dp, false, false);
        }

        private void aog_(View view) {
            if (view == null) {
                LogUtil.h("GridPageHolder", "getFourGradFullImageHolder() itemView is null.");
                return;
            }
            this.y = (RelativeLayout) view.findViewById(R.id.item_four_grad_full_image_layout);
            this.v = (ImageView) view.findViewById(R.id.item_four_grad_full_image_image);
            this.r = (HealthTextView) view.findViewById(R.id.item_four_grad_full_image_corner);
            this.x = (HealthTextView) view.findViewById(R.id.item_four_grad_full_image_title);
            this.t = (HealthTextView) view.findViewById(R.id.item_four_grad_full_image_description);
            eiv.a(this.x, false, true);
            eiv.a(this.t, true, false);
        }
    }

    private SingleGridContent b(b bVar, int i) {
        SingleGridContent singleGridContent = new SingleGridContent(new SingleGridContent.Builder());
        if (bVar == null || koq.b(this.n)) {
            LogUtil.h("ColumnLayoutAdapter", "getCardItemObject() holder or mGridContentList or position is null.");
            return singleGridContent;
        }
        if (koq.d(this.n, i)) {
            return this.n.get(i);
        }
        LogUtil.h("ColumnLayoutAdapter", "getCardItemObject() outOfBounds, position = ", Integer.valueOf(i));
        return singleGridContent;
    }

    private List<SingleGridContent> c(b bVar, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (bVar == null || koq.b(this.ad)) {
            LogUtil.h("ColumnLayoutAdapter", "getOneAddTwoObject() holder or mOneAddTwoList or position is null.");
            return arrayList;
        }
        if (koq.d(this.ad, i)) {
            return this.ad.get(i);
        }
        LogUtil.h("ColumnLayoutAdapter", "getOneAddTwoObject() outOfBounds, position = ", Integer.valueOf(i));
        return arrayList;
    }

    private SingleEntryContent d(b bVar, int i) {
        SingleEntryContent singleEntryContent = new SingleEntryContent(new SingleEntryContent.Builder());
        if (bVar == null || koq.b(this.ae)) {
            LogUtil.h("ColumnLayoutAdapter", "getSingleEntryObject() holder or mQuickEntryList or position is null.");
            return singleEntryContent;
        }
        if (koq.d(this.ae, i)) {
            return this.ae.get(i);
        }
        LogUtil.h("ColumnLayoutAdapter", "getSingleEntryObject() outOfBounds, position = ", Integer.valueOf(i));
        return singleEntryContent;
    }

    private SingleFunctionGridContent h(b bVar, int i) {
        SingleFunctionGridContent singleFunctionGridContent = new SingleFunctionGridContent(new SingleGridContent.Builder());
        if (bVar == null || koq.b(this.an)) {
            LogUtil.h("ColumnLayoutAdapter", "getSingleEntryObject() holder or mQuickEntryList or position is null.");
            return singleFunctionGridContent;
        }
        if (koq.d(this.an, i)) {
            return this.an.get(i);
        }
        LogUtil.h("ColumnLayoutAdapter", "getSingleEntryObject() outOfBounds, position = ", Integer.valueOf(i));
        return singleFunctionGridContent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i, final SingleGridContent singleGridContent, final int i2) {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.ColumnLayoutAdapter.4
                @Override // java.lang.Runnable
                public void run() {
                    ColumnLayoutAdapter.this.b(i, singleGridContent, i2);
                }
            });
            return;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("resourcePositionId", Integer.valueOf(this.ag));
        hashMap.put("resourceId", this.ai);
        hashMap.put("resourceName", this.aj);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", singleGridContent.getTheme());
        if (this.j == 47) {
            if (TextUtils.isEmpty(singleGridContent.getTheme())) {
                LogUtil.h("ColumnLayoutAdapter", "resourceName is empty");
                return;
            } else {
                hashMap.put("functionID", singleGridContent.getItemId());
                hashMap.put("smartRecommend", Boolean.valueOf(this.u));
                hashMap.put("algId", singleGridContent.isRecommendInfoGenerated() ? singleGridContent.getAlgId() : eil.d(this.af, singleGridContent.getItemId()));
            }
        } else {
            hashMap.put("smartRecommend", Boolean.valueOf(singleGridContent.isRecommendInfoGenerated() ? singleGridContent.isSmartRecommend() : this.ah.getSmartRecommend()));
            hashMap.put("algId", singleGridContent.isRecommendInfoGenerated() ? singleGridContent.getAlgId() : eil.d(this.ah.getRecommendList(), singleGridContent.getItemId()));
        }
        if (i == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.al));
            this.al = currentTimeMillis;
            c(singleGridContent.getTheme());
            e(singleGridContent.getTheme());
        }
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("itemId", this.ah.getSmartRecommend() ? singleGridContent.getItemId() : singleGridContent.getDynamicDataId());
        LogUtil.a("ColumnLayoutAdapter", "marketing biEvent: mPositionId: ", Integer.valueOf(this.ag), ", mResourceName: ", this.aj, ", sortingRules: ", this.ah.getSortingRules(), ", biMap: ", hashMap.toString());
        int i3 = this.j;
        if (i3 == 21 || i3 == 19 || i3 == 15) {
            LogUtil.a("ColumnLayoutAdapter", "biEvent with ab_info. contentType = ", Integer.valueOf(i3));
            MarketingBiUtils.a(hashMap, this.ah);
        } else {
            ixx.d().d(this.o, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
        }
        a(i, singleGridContent.getTheme());
    }

    private void c(String str) {
        int i = this.ag;
        if (i < 4062 || i > 4071) {
            return;
        }
        LogUtil.a("ColumnLayoutAdapter", "set DiscoveryCourse event");
        MarketingBiUtils.e(22, str, 6);
    }

    private void e(String str) {
        if (this.ag == 4014 && this.j == 15) {
            LogUtil.a("ColumnLayoutAdapter", "set checkOneGridSmallTemplateEvent event");
            MarketingBiUtils.e(22, str, 10);
        }
    }

    private void a(int i, String str) {
        if (this.ag == 4061) {
            int i2 = this.j;
            if (i2 == 22 || i2 == 21 || i2 == 47 || i2 == 36) {
                LogUtil.a("ColumnLayoutAdapter", "set vip privilege event");
                if (i == 1) {
                    dqj.f(str);
                } else if (i == 2) {
                    dqj.h(str);
                }
            }
        }
    }

    static class c implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final SingleGridContent b;
        private final WeakReference<View> c;
        private final WeakReference<ColumnLayoutAdapter> d;
        private final int e;

        public c(ColumnLayoutAdapter columnLayoutAdapter, View view, SingleGridContent singleGridContent, int i) {
            this.d = new WeakReference<>(columnLayoutAdapter);
            this.c = new WeakReference<>(view);
            this.b = singleGridContent;
            this.e = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ColumnLayoutAdapter columnLayoutAdapter = this.d.get();
            View view = this.c.get();
            if (columnLayoutAdapter == null || view == null) {
                return;
            }
            if (!columnLayoutAdapter.c(this.b)) {
                columnLayoutAdapter.amm_(this.b, view, this.e);
            } else {
                nsy.cMf_(view, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            ColumnLayoutAdapter columnLayoutAdapter = this.d.get();
            View view = this.c.get();
            if (columnLayoutAdapter == null || view == null) {
                return;
            }
            if (!columnLayoutAdapter.c(this.b)) {
                columnLayoutAdapter.amm_(this.b, view, this.e);
            } else {
                nsy.cMg_(view, this);
            }
        }
    }
}
