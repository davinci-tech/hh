package com.huawei.health.knit.section.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.model.KnitFragmentModel;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.IViewVisibilityChange;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.eec;
import defpackage.ees;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class SectionBean {

    /* renamed from: a, reason: collision with root package name */
    protected Operation f2611a;
    protected KnitDataProvider b;
    protected Consumer<Boolean> c;
    protected Object d;
    protected boolean e;
    protected KnitFragmentModel f;
    private HashMap<String, Object> g;
    private WeakReference<Context> h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private LoadReason m;
    private String n;
    private JSONObject o;
    private int p;
    private int q;
    private boolean r;
    private ResourceBriefInfo s;
    private int t;
    private WeakReference<BaseSection> v;
    private View w;
    private int y;

    public enum LoadReason {
        NONE,
        LIST_READY,
        FORCE
    }

    public enum Operation {
        UNKNOWN,
        INSERT,
        CHANGE,
        REMOVE
    }

    public eec.d a(KnitDataProvider knitDataProvider) {
        return null;
    }

    public void e(KnitDataProvider knitDataProvider, Object obj) {
    }

    public SectionBean(ResourceBriefInfo resourceBriefInfo, View view, int i, int i2, boolean z) {
        this(resourceBriefInfo.getContentType(), resourceBriefInfo.getPriority(), i, ees.a(resourceBriefInfo), i2);
        this.s = resourceBriefInfo;
        ab();
        this.w = view;
        this.k = z;
    }

    public SectionBean(int i, int i2, int i3, KnitDataProvider knitDataProvider, int i4) {
        this.e = false;
        this.f2611a = Operation.UNKNOWN;
        this.g = new HashMap<>();
        this.v = new WeakReference<>(null);
        this.m = LoadReason.NONE;
        this.r = false;
        this.k = false;
        this.l = true;
        this.j = false;
        this.i = false;
        this.y = i;
        this.p = i2;
        this.t = i3;
        this.b = knitDataProvider;
        this.q = i4;
    }

    public SectionBean(int i, int i2, int i3, KnitDataProvider knitDataProvider, int i4, String str) {
        this.e = false;
        this.f2611a = Operation.UNKNOWN;
        this.g = new HashMap<>();
        this.v = new WeakReference<>(null);
        this.m = LoadReason.NONE;
        this.r = false;
        this.k = false;
        this.l = true;
        this.j = false;
        this.i = false;
        this.y = i;
        this.p = i2;
        this.t = i3;
        this.b = knitDataProvider;
        this.q = i4;
        this.n = str;
    }

    public SectionBean(int i, int i2, int i3, KnitDataProvider knitDataProvider, int i4, JSONObject jSONObject) {
        this.e = false;
        this.f2611a = Operation.UNKNOWN;
        this.g = new HashMap<>();
        this.v = new WeakReference<>(null);
        this.m = LoadReason.NONE;
        this.r = false;
        this.k = false;
        this.l = true;
        this.j = false;
        this.i = false;
        this.y = i;
        this.p = i2;
        this.t = i3;
        this.b = knitDataProvider;
        this.q = i4;
        this.o = jSONObject;
    }

    public void b(BaseSection baseSection) {
        this.v = new WeakReference<>(baseSection);
    }

    public WeakReference<BaseSection> n() {
        return this.v;
    }

    public void e(Consumer<Boolean> consumer) {
        this.c = consumer;
    }

    public Consumer<Boolean> f() {
        return this.c;
    }

    public Fragment b() {
        BaseSection baseSection = this.v.get();
        if (baseSection != null) {
            return baseSection.getKnitFragment();
        }
        return null;
    }

    public List<Bitmap> l() {
        BaseSection baseSection = this.v.get();
        if (baseSection == null) {
            return new ArrayList();
        }
        return baseSection.getShareBitmap();
    }

    public HashMap<String, Object> a() {
        return this.g;
    }

    public void a(HashMap<String, Object> hashMap) {
        this.g = hashMap;
    }

    public void a(Context context, LoadReason loadReason) {
        if (this.b == null) {
            LogUtil.h("SectionBean", "loadData mKnitDataProvider == null");
            return;
        }
        this.h = new WeakReference<>(context);
        if (this.m == LoadReason.LIST_READY && loadReason == LoadReason.LIST_READY) {
            LogUtil.a("SectionBean", "loadData mLoadReason ", this.m, " loadReason ", loadReason);
            boolean z = this.r;
            if (!z && this.o != null) {
                LogUtil.a("SectionBean", "loadData mOnLineDataRefresh ", Boolean.valueOf(z), " mOnLineData ", this.o);
                this.r = true;
                this.b.loadData(aa(), this);
                return;
            }
            LogUtil.a("SectionBean", "refresh again");
            return;
        }
        this.m = loadReason;
        this.b.loadData(aa(), this);
    }

    private Context aa() {
        WeakReference<Context> weakReference = this.h;
        if (weakReference == null) {
            return BaseApplication.getContext();
        }
        Context context = weakReference.get();
        return context == null ? BaseApplication.getContext() : context;
    }

    public void d(Context context) {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.loadDataLite(context);
    }

    public void w() {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.loadDefaultData(this);
    }

    public void u() {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.onCreate();
    }

    public void z() {
        if (this.l) {
            b(true);
        }
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.onResume();
    }

    public void ad() {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.onPause();
    }

    public void ac() {
        if (this.l) {
            b(false);
        }
    }

    public void d(boolean z) {
        this.l = z;
        if (z && this.k) {
            MarketingBiUtils.d(this.q, this.s);
        }
        b(z);
    }

    public void x() {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.onConfigurationChanged();
    }

    public void e(final Object obj) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.section.model.SectionBean.3
                @Override // java.lang.Runnable
                public void run() {
                    SectionBean.this.d(obj);
                }
            });
        } else {
            d(obj);
        }
    }

    private void b(boolean z) {
        KeyEvent.Callback callback = this.w;
        if (callback instanceof IViewVisibilityChange) {
            ((IViewVisibilityChange) callback).onVisibilityChange(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        boolean z;
        KnitDataProvider knitDataProvider = this.b;
        boolean z2 = knitDataProvider != null && knitDataProvider.isActive(BaseApplication.getContext());
        Object obj2 = this.d;
        if ((obj2 == null && obj != null) || (!(z = this.e) && z2)) {
            this.f2611a = Operation.INSERT;
        } else if ((obj2 != null && obj == null) || (z && !z2)) {
            this.f2611a = Operation.REMOVE;
        } else if (obj2 != null && obj != null) {
            this.f2611a = Operation.CHANGE;
        } else {
            this.f2611a = Operation.UNKNOWN;
            return;
        }
        this.e = z2;
        this.d = obj;
        KnitFragmentModel knitFragmentModel = this.f;
        if (knitFragmentModel != null) {
            knitFragmentModel.b(this);
        }
    }

    public KnitDataProvider c() {
        return this.b;
    }

    public int j() {
        return this.t;
    }

    public View agN_() {
        return this.w;
    }

    public int h() {
        return this.p;
    }

    public int o() {
        return this.y;
    }

    public String y() {
        return this.n;
    }

    public Operation g() {
        return this.f2611a;
    }

    public void c(Operation operation) {
        this.f2611a = operation;
    }

    public int k() {
        return this.q;
    }

    public JSONObject i() {
        return this.o;
    }

    public List<String> d() {
        return ees.d(this.s);
    }

    public boolean t() {
        Context context = BaseApplication.getContext();
        KnitDataProvider knitDataProvider = this.b;
        boolean z = knitDataProvider == null || knitDataProvider.isActive(context);
        this.e = z;
        return z && !this.i;
    }

    public void e(boolean z) {
        this.i = z;
    }

    public boolean p() {
        return this.i;
    }

    public boolean s() {
        return this.b != null && this.d == null;
    }

    public Object e() {
        return this.d;
    }

    public void e(KnitFragmentModel knitFragmentModel) {
        this.f = knitFragmentModel;
    }

    public void a(int i) {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider == null) {
            return;
        }
        knitDataProvider.updateGpsSignal(i);
    }

    public boolean r() {
        return this.j;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public void v() {
        KnitDataProvider knitDataProvider = this.b;
        if (knitDataProvider != null) {
            knitDataProvider.onDestroy();
        }
        this.d = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SectionBean{mTemplate=");
        sb.append(this.y);
        sb.append(", mKnitDataProvider=");
        KnitDataProvider knitDataProvider = this.b;
        sb.append(knitDataProvider == null ? null : knitDataProvider.toString());
        sb.append(", isDataInvalid=");
        sb.append(s());
        sb.append(", hashCode=");
        sb.append(hashCode());
        sb.append(", isActive=");
        sb.append(t());
        sb.append(", mLastActive=");
        sb.append(this.e);
        sb.append(", mIsHide = ");
        sb.append(this.i);
        sb.append(", mData=");
        sb.append(this.d);
        sb.append(", mOperation=");
        sb.append(this.f2611a);
        sb.append(", mView=");
        sb.append(this.w);
        sb.append(", mPriority=");
        sb.append(this.p);
        sb.append(", mPageType=");
        sb.append(this.t);
        sb.append(", mDefaultViewMap=");
        sb.append(this.g);
        sb.append(", mResPosId=");
        sb.append(this.q);
        sb.append('}');
        return sb.toString();
    }

    public ResourceBriefInfo m() {
        return this.s;
    }

    private void ab() {
        ResourceContentBase content;
        String content2;
        ResourceBriefInfo resourceBriefInfo = this.s;
        if (resourceBriefInfo == null || (content = resourceBriefInfo.getContent()) == null || (content2 = content.getContent()) == null) {
            return;
        }
        try {
            if ("hide".equals(new JSONObject(content2).optString("extend"))) {
                KnitDataProvider knitDataProvider = this.b;
                if (knitDataProvider != null) {
                    knitDataProvider.setIsActive(false);
                }
                this.i = true;
            }
        } catch (JSONException e) {
            LogUtil.b("SectionBean", "updateActiveStatus exception happened = ", ExceptionUtils.d(e));
        }
    }

    public void a(SectionBean sectionBean) {
        if (sectionBean == null) {
            return;
        }
        if (sectionBean.s != null) {
            if (this.s == null) {
                this.m = LoadReason.FORCE;
            }
            this.s = sectionBean.s;
            ab();
        }
        View view = sectionBean.w;
        if (view != null) {
            this.w = view;
        }
        int i = sectionBean.y;
        if (i != 0) {
            this.y = i;
        }
        this.p = sectionBean.p;
        int i2 = sectionBean.t;
        if (i2 != 0) {
            this.t = i2;
        }
        int i3 = sectionBean.q;
        if (i3 != 0) {
            this.q = i3;
        }
        JSONObject jSONObject = sectionBean.o;
        if (jSONObject != null) {
            this.o = jSONObject;
        }
    }
}
