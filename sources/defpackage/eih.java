package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListContent;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class eih {

    /* renamed from: a, reason: collision with root package name */
    private int f12027a;
    private long b;
    private ResourceBriefInfo c;
    private volatile Set<Integer> d = new HashSet();
    private String e;
    private String j;

    public eih(int i, String str, ResourceBriefInfo resourceBriefInfo) {
        this.f12027a = i;
        this.j = str;
        this.c = resourceBriefInfo;
        if (resourceBriefInfo != null) {
            this.e = resourceBriefInfo.getResourceId();
        }
        this.b = System.currentTimeMillis();
    }

    public void alO_(final String str, final String str2, final ColumRecommendInfo columRecommendInfo, final int i, final View view) {
        HandlerExecutor.d(new Runnable() { // from class: eih.4
            @Override // java.lang.Runnable
            public void run() {
                eih.this.alL_(str, str2, columRecommendInfo, i, view);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void alL_(String str, String str2, ColumRecommendInfo columRecommendInfo, int i, View view) {
        if (view == null || alN_(str, str2, view)) {
            return;
        }
        nsy.cMa_(view, new c(view, str, str2, i, columRecommendInfo));
        nsy.cMb_(view, new c(view, str, str2, i, columRecommendInfo));
    }

    public boolean alN_(String str, String str2, View view) {
        return this.d.contains(Integer.valueOf(Objects.hash(str, str2, view)));
    }

    public void c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", Integer.valueOf(this.f12027a));
        hashMap.put("resourceId", this.e);
        hashMap.put("resourceName", this.j + " " + str);
        hashMap.put("event", 3);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", false);
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.b));
        this.b = System.currentTimeMillis();
        LogUtil.a("MarketingBiUtil", "marketing biEvent: mPositionId: ", Integer.valueOf(this.f12027a), ", mResourceName: ", str, ", biMap: ", hashMap.toString());
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    class c implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private final int f12029a;
        private final WeakReference<View> b;
        private final ColumRecommendInfo d;
        private final String e;
        private final String f;

        public c(View view, String str, String str2, int i, ColumRecommendInfo columRecommendInfo) {
            this.b = new WeakReference<>(view);
            this.e = str2;
            this.f = str;
            this.f12029a = i;
            this.d = columRecommendInfo;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            View view = this.b.get();
            if (view == null) {
                return;
            }
            if (!eih.this.alN_(this.f, this.e, view)) {
                eih.this.alJ_(this.e, view, this.f, this.f12029a, this.d);
            } else {
                nsy.cMf_(view, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            View view = this.b.get();
            if (view == null) {
                return;
            }
            if (!eih.this.alN_(this.f, this.e, view)) {
                eih.this.alJ_(this.e, view, this.f, this.f12029a, this.d);
            } else {
                nsy.cMg_(view, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void alJ_(String str, View view, String str2, int i, ColumRecommendInfo columRecommendInfo) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        int height = view.getHeight();
        int width = view.getWidth();
        Rect rect = new Rect();
        if (view.getLocalVisibleRect(rect) && alK_(rect, height, width) && !alN_(str2, str, view)) {
            this.d.add(Integer.valueOf(Objects.hash(str2, str, view)));
            if (columRecommendInfo instanceof SingleCourseRecommendListStandardContent) {
                a(1, "", (SingleCourseRecommendListStandardContent) columRecommendInfo, i);
            } else {
                d(1, str, str2, i, columRecommendInfo);
            }
        }
    }

    private boolean alK_(Rect rect, int i, int i2) {
        return rect != null && rect.left >= 0 && rect.left < i2 && rect.right >= Math.min(i2 / 2, 200) && rect.top >= 0 && rect.top < i && rect.bottom >= Math.min((i * 2) / 3, 400);
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d(final int i, final String str, final String str2, final int i2, final ColumRecommendInfo columRecommendInfo) {
        if (this.c == null) {
            LogUtil.a("MarketingBiUtil", "biEvent resourceBriefInfo is null, return");
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eij
                @Override // java.lang.Runnable
                public final void run() {
                    eih.this.d(i, str, str2, i2, columRecommendInfo);
                }
            });
            return;
        }
        String resourceId = this.c.getResourceId();
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", Integer.valueOf(this.f12027a));
        hashMap.put("resourceName", this.j + " " + str2);
        hashMap.put("resourceId", resourceId);
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.b));
            this.b = currentTimeMillis;
        }
        hashMap.put("itemCardName", str);
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        LogUtil.a("MarketingBiUtil", "marketing biEvent: mPositionId: ", Integer.valueOf(this.f12027a), ", mResourceName: ", str2, ", biMap: ", hashMap.toString());
        if (columRecommendInfo != null) {
            hashMap.put("itemId", columRecommendInfo.getItemId());
            hashMap.put("algId", columRecommendInfo.getAlgId());
            hashMap.put("smartRecommend", Boolean.valueOf(columRecommendInfo.isSmartRecommend()));
            ixx.d().c(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, columRecommendInfo.getAbInfo(), 0);
            return;
        }
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(this.c.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public void a(int i, String str, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            hashMap.put(FunctionSetBeanReader.BI_ELEMENT, str);
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.b));
            this.b = currentTimeMillis;
        }
        hashMap.put("resourcePositionId", Integer.valueOf(this.f12027a));
        hashMap.put("resourceId", this.c.getResourceId());
        hashMap.put("resourceName", this.c.getResourceName());
        hashMap.put("resourceTopic", this.j);
        hashMap.put("itemTopic", singleCourseRecommendListStandardContent.getTheme());
        hashMap.put("subItemTopic", singleCourseRecommendListStandardContent.getSubTheme());
        hashMap.put("moreEntryName", singleCourseRecommendListStandardContent.getButtonDescription());
        hashMap.put("itemId", singleCourseRecommendListStandardContent.getItemId());
        hashMap.put("algId", singleCourseRecommendListStandardContent.getAlgId());
        hashMap.put("smartRecommend", Boolean.valueOf(singleCourseRecommendListStandardContent.isSmartRecommend()));
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        ixx.d().c(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, singleCourseRecommendListStandardContent.getAbInfo(), 0);
    }

    public void alM_(View view, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, SingleCourseRecommendListContent singleCourseRecommendListContent, int i) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        int height = view.getHeight();
        int width = view.getWidth();
        Rect rect = new Rect();
        if (view.getLocalVisibleRect(rect) && alK_(rect, height, width) && !alN_(singleCourseRecommendListStandardContent.getTheme(), singleCourseRecommendListContent.getTheme(), view)) {
            this.d.add(Integer.valueOf(Objects.hash(singleCourseRecommendListStandardContent.getTheme(), singleCourseRecommendListContent.getTheme(), view)));
            d(1, singleCourseRecommendListStandardContent, singleCourseRecommendListContent, i);
        }
    }

    public void d(int i, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent, SingleCourseRecommendListContent singleCourseRecommendListContent, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "e3");
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.b));
            this.b = currentTimeMillis;
        }
        hashMap.put("resourcePositionId", Integer.valueOf(this.f12027a));
        hashMap.put("resourceId", this.c.getResourceId());
        hashMap.put("resourceName", this.c.getResourceName());
        hashMap.put("resourceTopic", this.j);
        hashMap.put("itemTopic", singleCourseRecommendListStandardContent.getTheme());
        hashMap.put("subItemTopic", singleCourseRecommendListStandardContent.getSubTheme());
        hashMap.put("moreEntryName", singleCourseRecommendListStandardContent.getButtonDescription());
        hashMap.put("itemCardName", singleCourseRecommendListContent.getTheme());
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("itemId", singleCourseRecommendListContent.getItemId());
        hashMap.put("algId", singleCourseRecommendListContent.getAlgId());
        hashMap.put("smartRecommend", Boolean.valueOf(singleCourseRecommendListContent.isSmartRecommend()));
        ixx.d().c(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, singleCourseRecommendListContent.getAbInfo(), 0);
    }
}
