package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.eac;
import defpackage.eed;
import defpackage.nrv;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;

/* loaded from: classes8.dex */
public abstract class FitnessEntranceProvider<T> extends BaseKnitDataProvider<T> {
    private static final String TAG = "FitnessEntranceProvider";
    protected T mData;
    protected boolean mIsLoad;
    private boolean mIsLogin = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
    private WeakReference<SectionBean> mSectionBeanWeakRef = new WeakReference<>(null);
    private String mPrivacyGoodsData = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "personalized_recommend");

    protected abstract int getCourseCategory();

    protected T getDefaultData() {
        return null;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public abstract String getLogTag();

    protected abstract int getPageType();

    protected abstract String getSubViewTitle();

    protected abstract int getType();

    public boolean isCustomActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public abstract void loadData(Context context, SectionBean sectionBean);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public abstract void parseParams(Context context, HashMap<String, Object> hashMap, T t);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return isCustomActive(context) && super.isActive(context);
    }

    public boolean isNeedUpdate() {
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "personalized_recommend");
        if (b == null) {
            b = "";
        }
        if (this.mPrivacyGoodsData == null) {
            this.mPrivacyGoodsData = "";
        }
        LogUtil.a(getLogTag(), "isLoad:", Boolean.valueOf(this.mIsLoad), " isLogin:", Boolean.valueOf(this.mIsLogin), " current login:", Boolean.valueOf(isLogined), " privacyGoodsData:", b);
        if (this.mIsLoad && this.mIsLogin == isLogined && b.equals(this.mPrivacyGoodsData)) {
            return false;
        }
        this.mIsLogin = isLogined;
        this.mPrivacyGoodsData = b;
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mSectionBeanWeakRef = new WeakReference<>(sectionBean);
        T defaultData = getDefaultData();
        this.mData = defaultData;
        if (defaultData != null) {
            sectionBean.e(defaultData);
        } else {
            sectionBean.e((Object) null);
        }
        LogUtil.a(getLogTag(), "loadDefaultData cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    protected T getCacheData(Type type) {
        LogUtil.a(getLogTag(), "start to get cache");
        String b = eac.e().b(getCacheKey());
        if (b == null) {
            return null;
        }
        T t = (T) nrv.c(b, type);
        LogUtil.a(getLogTag(), "end to get cache");
        return t;
    }

    protected String getCacheKey() {
        return eac.a(getLogTag() + LanguageUtil.e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: updateCache, reason: merged with bridge method [inline-methods] */
    public void m518x8a2b48e2(final T t) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gfb
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessEntranceProvider.this.m518x8a2b48e2(t);
                }
            });
        }
        LogUtil.a(getLogTag(), "updateCache result:", Boolean.valueOf(eac.e().e(getCacheKey(), nrv.a(t))));
    }

    protected boolean isDataUpdate(T t) {
        return !TextUtils.equals(nrv.a(t), nrv.a(this.mData));
    }

    public void checkAndUpdateUi(T t) {
        if (isDataUpdate(t)) {
            LogUtil.a(getLogTag(), " new data arrived");
            this.mData = t;
            SectionBean sectionBean = getSectionBean();
            if (sectionBean == null) {
                ReleaseLogUtil.d(getLogTag(), "Update Ui failed with sectionBean is null");
            } else {
                sectionBean.e(t);
            }
            m518x8a2b48e2(t);
            return;
        }
        LogUtil.a(getLogTag(), " new data is same");
    }

    protected SectionBean getSectionBean() {
        return this.mSectionBeanWeakRef.get();
    }

    protected eed getTagData(int i) {
        eed eedVar;
        Context context = BaseApplication.getContext();
        if (i == 1) {
            eedVar = new eed(context.getString(R.string._2130845344_res_0x7f021ea0), context.getColor(R.color._2131297622_res_0x7f090556), ContextCompat.getDrawable(context, R.drawable._2131431681_res_0x7f0b1101));
        } else if (i == 2) {
            eedVar = new eed(context.getString(R.string._2130847510_res_0x7f022716), context.getColor(R.color._2131297622_res_0x7f090556), ContextCompat.getDrawable(context, R.drawable._2131431678_res_0x7f0b10fe));
        } else {
            return new eed();
        }
        return eedVar;
    }
}
