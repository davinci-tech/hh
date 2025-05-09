package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.marketing.datatype.ArticleImage;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.dqj;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsa;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionArticleCombination extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<ArticleImage> f2669a;
    private HashMap<Integer, Integer> b;
    private boolean c;
    private Context d;
    private LinearLayout e;
    private HashMap<Integer, Boolean> f;
    private HashMap<Integer, List<Drawable>> g;
    private boolean h;
    private Set<String> i;
    private List<Integer> j;
    private HashMap<ImageView, String> k;
    private View m;
    private HashMap<ImageView, ViewTreeVisibilityListener.ViewTreeListenee> o;

    public SectionArticleCombination(Context context) {
        super(context);
        this.c = false;
        this.h = false;
        this.g = new HashMap<>();
        this.b = new HashMap<>();
        this.f = new HashMap<>();
        this.k = new HashMap<>();
        this.o = new HashMap<>();
        this.j = new ArrayList(10);
        this.f2669a = new ArrayList(10);
        this.i = new HashSet();
    }

    public SectionArticleCombination(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.h = false;
        this.g = new HashMap<>();
        this.b = new HashMap<>();
        this.f = new HashMap<>();
        this.k = new HashMap<>();
        this.o = new HashMap<>();
        this.j = new ArrayList(10);
        this.f2669a = new ArrayList(10);
        this.i = new HashSet();
    }

    public SectionArticleCombination(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.h = false;
        this.g = new HashMap<>();
        this.b = new HashMap<>();
        this.f = new HashMap<>();
        this.k = new HashMap<>();
        this.o = new HashMap<>();
        this.j = new ArrayList(10);
        this.f2669a = new ArrayList(10);
        this.i = new HashSet();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionArticleCombination", "onCreateView");
        this.d = context;
        d();
        return this.m;
    }

    private void d() {
        if (this.m == null) {
            LogUtil.h("SectionArticleCombination", "initView mainView is null, start to inflate");
            this.m = LayoutInflater.from(this.d).inflate(R.layout.section_article_combine_layout, (ViewGroup) this, false);
        }
        this.e = (LinearLayout) this.m.findViewById(R.id.section_article_combine);
        LogUtil.h("SectionArticleCombination", "initSectionArticleCombinationState");
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionArticleCombination", "start to bindViewParams");
        List<Integer> d = nru.d(hashMap, "CARD_TITLE_INDEX", Integer.class, null);
        List<ArticleImage> d2 = nru.d(hashMap, "ARTICLE_IMAGE_LIST", ArticleImage.class, null);
        HashMap<Integer, Integer> hashMap2 = (HashMap) nru.c(hashMap, "GRID_ORIENTATION", HashMap.class, null);
        HashMap<Integer, List<Drawable>> hashMap3 = (HashMap) nru.c(hashMap, "CARD_COMBIN_IMAGES", HashMap.class, null);
        if (koq.b(d) || koq.b(d2) || hashMap3 == null || hashMap2 == null) {
            LogUtil.a("SectionArticleCombination", "bindViewParams has null data");
            return;
        }
        this.f2669a = d2;
        this.g = hashMap3;
        this.b = hashMap2;
        this.j = d;
        if (!this.c) {
            Iterator<Integer> it = d.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                c(intValue);
                this.f.put(Integer.valueOf(intValue), false);
            }
            this.c = true;
        }
        Iterator<Integer> it2 = d.iterator();
        while (it2.hasNext()) {
            int intValue2 = it2.next().intValue();
            if (!this.f.get(Integer.valueOf(intValue2)).booleanValue()) {
                e(intValue2);
            }
        }
    }

    private void c(int i) {
        ArticleImage articleImage;
        HashMap<Integer, List<Drawable>> hashMap = this.g;
        if (hashMap == null || hashMap.isEmpty() || !this.b.containsKey(Integer.valueOf(i)) || !koq.d(this.f2669a, i)) {
            return;
        }
        int intValue = this.b.get(Integer.valueOf(i)).intValue();
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        if (intValue == 1) {
            linearLayout.setOrientation(0);
        } else {
            linearLayout.setOrientation(1);
        }
        linearLayout.setGravity(1);
        if (i < this.f2669a.size() && (articleImage = this.f2669a.get(i)) != null) {
            aiC_(linearLayout, i, articleImage.getCombinNums());
        }
    }

    private void aiC_(LinearLayout linearLayout, int i, int i2) {
        ArticleImage articleImage;
        for (int i3 = 0; i3 < i2; i3++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            ImageView imageView = new ImageView(this.d);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(layoutParams);
            int i4 = i + i3;
            if (koq.d(this.f2669a, i4) && (articleImage = this.f2669a.get(i4)) != null) {
                String imgUrl = articleImage.getImgUrl();
                if (imgUrl != null) {
                    this.k.put(imageView, imgUrl);
                    imageView.setOnClickListener(this);
                }
                aiE_(imageView, i4);
                linearLayout.addView(imageView);
            }
        }
        this.e.addView(linearLayout);
    }

    private void aiE_(final ImageView imageView, final int i) {
        this.o.put(imageView, new ViewTreeVisibilityListener.ViewTreeListenee() { // from class: com.huawei.health.knit.section.view.SectionArticleCombination.5
            @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
            public void checkVisibilityAndSetBiEvent() {
                if (ViewTreeVisibilityListener.Zx_(imageView) && !hasSetBiEvent() && koq.d(SectionArticleCombination.this.f2669a, i)) {
                    LogUtil.a("SectionArticleCombination", "visible to user: ", ((ArticleImage) SectionArticleCombination.this.f2669a.get(i)).getImgName());
                    biEvent();
                    SectionArticleCombination.this.i.add(((ArticleImage) SectionArticleCombination.this.f2669a.get(i)).getImgName());
                }
            }

            @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
            public boolean hasSetBiEvent() {
                if (koq.d(SectionArticleCombination.this.f2669a, i)) {
                    return SectionArticleCombination.this.i.contains(((ArticleImage) SectionArticleCombination.this.f2669a.get(i)).getImgName());
                }
                return false;
            }

            @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
            public void biEvent() {
                ArticleImage articleImage;
                if (imageView == null || !koq.d(SectionArticleCombination.this.f2669a, i) || SectionArticleCombination.this.k == null || (articleImage = (ArticleImage) SectionArticleCombination.this.f2669a.get(i)) == null || articleImage.getImgName() == null) {
                    return;
                }
                String imgName = articleImage.getImgName();
                if (articleImage.getImgUrl() != null) {
                    imgName = articleImage.getImgUrl();
                }
                String queryParameter = Uri.parse(nsa.g(imgName)).getQueryParameter("data");
                if (SectionArticleCombination.this.k.get(imageView) != null) {
                    imgName = (String) SectionArticleCombination.this.k.get(imageView);
                }
                if (TextUtils.isEmpty(imgName)) {
                    return;
                }
                if (imgName.contains("data")) {
                    dqj.n(queryParameter);
                } else {
                    dqj.j(imgName);
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view) || !this.k.containsKey(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        String str = this.k.get(view);
        if (str == null || TextUtils.isEmpty(str)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("SectionArticleCombination", "url :", str);
        String queryParameter = Uri.parse(nsa.g(str)).getQueryParameter("data");
        if (str.contains("data")) {
            dqj.m(queryParameter);
        } else {
            dqj.i(str);
        }
        marketRouterApi.router(str);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(int i) {
        List<Drawable> list;
        if (!koq.d(this.f2669a, i) || !this.g.containsKey(Integer.valueOf(i)) || (list = this.g.get(Integer.valueOf(i))) == null || list.isEmpty()) {
            return;
        }
        int indexOf = this.j.indexOf(Integer.valueOf(i));
        LinearLayout linearLayout = this.e;
        if (linearLayout == null || indexOf > linearLayout.getChildCount()) {
            return;
        }
        View childAt = this.e.getChildAt(indexOf);
        ArticleImage articleImage = this.f2669a.get(i);
        if (articleImage == null) {
            return;
        }
        int combinNums = articleImage.getCombinNums();
        if (childAt instanceof LinearLayout) {
            aiF_(i, combinNums, (LinearLayout) childAt, list);
        }
        this.f.put(Integer.valueOf(i), true);
        LogUtil.a("SectionArticleCombination", "refreshRowLayout ", Integer.valueOf(i));
    }

    private void aiF_(int i, int i2, LinearLayout linearLayout, List<Drawable> list) {
        HashMap<Integer, Integer> hashMap;
        int i3;
        if (linearLayout == null || list.isEmpty() || !koq.d(this.f2669a, i) || (hashMap = this.b) == null || !hashMap.containsKey(Integer.valueOf(i))) {
            return;
        }
        list.removeAll(Collections.singleton(null));
        if (this.b.get(Integer.valueOf(i)).intValue() == 1) {
            Iterator<Drawable> it = list.iterator();
            i3 = 0;
            while (it.hasNext()) {
                Drawable next = it.next();
                i3 += next == null ? 0 : next.getIntrinsicWidth();
            }
        } else {
            i3 = 0;
            for (Drawable drawable : list) {
                if (drawable == null) {
                    i3 = Math.max(0, i3);
                } else {
                    i3 = Math.max(i3, drawable.getIntrinsicWidth());
                }
            }
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = LanguageUtil.bc(this.d) ? (i2 - 1) - i4 : i4;
            if ((linearLayout.getChildAt(i5) instanceof ImageView) && koq.d(list, i4)) {
                ImageView imageView = (ImageView) linearLayout.getChildAt(i5);
                imageView.setImageBitmap(aiH_(list.get(i4), i3));
                aiG_(imageView, i + i4);
            }
        }
    }

    private void aiG_(View view, int i) {
        if (koq.d(this.f2669a, i)) {
            ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this.o.get(view)));
        }
    }

    private Bitmap aiD_(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    private Bitmap aiH_(Drawable drawable, int i) {
        float intValue = (this.d.getResources().getDisplayMetrics().widthPixels - (((Integer) BaseActivity.getSafeRegionWidth().first).intValue() * 2)) / i;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap aiD_ = aiD_(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(intValue, intValue);
        return Bitmap.createBitmap(aiD_, 0, 0, intrinsicWidth, intrinsicHeight, matrix, true);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionArticleCombination";
    }
}
