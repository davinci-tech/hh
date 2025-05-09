package com.huawei.ui.main.stories.template.health.impl;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.LoginResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract;
import com.huawei.ui.main.stories.template.health.impl.HealthDetailCommonActivityPresenter;
import defpackage.koq;
import defpackage.pyw;
import defpackage.qle;
import defpackage.qpr;
import defpackage.qqk;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class HealthDetailCommonActivityPresenter implements DataDetailActivityContract.DetailActivityPresenter<DataDetailActivityContract.DetailActivityView> {
    protected boolean mIsSubscribe;
    private d mListener;
    private int mShowType;
    protected List<Integer> mSubscribeSuccessList;
    private String mTag;
    private WeakReference<DataDetailActivityContract.DetailActivityView> mViewWeakRef;
    protected boolean mNeedResolution = true;
    private final IBaseResponseCallback mBaseResponseCallback = new b(this);

    protected abstract void getLastDataTimestamp(IBaseResponseCallback iBaseResponseCallback);

    protected abstract List<Integer> getSubscribeList();

    protected abstract void initToolBar();

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void notifyChartDateStatus(DateType dateType, boolean z, boolean z2) {
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void notifyViewPagerChange(int i) {
    }

    protected void onLogined(String str) {
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void setChartStartTimeAndEndTime(long j, long j2) {
    }

    public HealthDetailCommonActivityPresenter(String str) {
        this.mTag = "Main_HealthDetailCommonActivityPresenter";
        if (StringUtils.i(str)) {
            this.mTag = str;
        }
    }

    static class b implements IBaseResponseCallback {
        WeakReference<HealthDetailCommonActivityPresenter> b;

        b(HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter) {
            this.b = new WeakReference<>(healthDetailCommonActivityPresenter);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            long longValue = obj instanceof Long ? ((Long) obj).longValue() : 0L;
            HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter = this.b.get();
            if (healthDetailCommonActivityPresenter == null) {
                return;
            }
            if (i != 0 || longValue <= 0) {
                healthDetailCommonActivityPresenter.showNoDataNoDevicesDetailView(i == 2);
            } else {
                healthDetailCommonActivityPresenter.showDetailView(longValue);
            }
        }
    }

    public DataDetailActivityContract.DetailActivityView getView() {
        WeakReference<DataDetailActivityContract.DetailActivityView> weakReference = this.mViewWeakRef;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void attachView(DataDetailActivityContract.DetailActivityView detailActivityView) {
        this.mViewWeakRef = new WeakReference<>(detailActivityView);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public boolean isViewAttached() {
        WeakReference<DataDetailActivityContract.DetailActivityView> weakReference = this.mViewWeakRef;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityPresenter
    public void initPage(String str, long j, String str2) {
        if (isViewAttached()) {
            initPageInfo(str, j, str2);
        } else {
            LogUtil.h(this.mTag, getClass().getName(), " don't attach View");
        }
    }

    public void initTitleBar(String str, String str2) {
        if (!this.mNeedResolution) {
            LogUtil.a(this.mTag, "initTitleBar not need resolution");
        } else {
            ResourceParseHelper.a(str, str2, new a());
        }
    }

    protected void refreshPage() {
        subscribeData();
        getLastDataTimestamp(this.mBaseResponseCallback);
    }

    private void subscribeData() {
        DataDetailActivityContract.DetailActivityView view = getView();
        if (view == null) {
            return;
        }
        if (this instanceof qle) {
            this.mListener = new d(this.mTag, this);
            qqk.a().d(this.mListener);
            this.mIsSubscribe = true;
            return;
        }
        HiHealthNativeApi.a(view.getViewContext()).subscribeHiHealthData(getSubscribeList(), this.mListener);
    }

    private void unSubscribeData() {
        LogUtil.a(this.mTag, "unSubscribeData");
        if (koq.c(this.mSubscribeSuccessList)) {
            HiHealthNativeApi.a(getView().getViewContext()).unSubscribeHiHealthData(this.mSubscribeSuccessList, new e(this.mTag, "unSubscribeData isSuccess :"));
        } else {
            qqk.a().a(this.mListener);
        }
        this.mIsSubscribe = false;
    }

    protected void updateSuccessList(List<Integer> list) {
        LogUtil.a(this.mTag, "subscribeData, onResult");
        if (koq.c(list)) {
            LogUtil.a(this.mTag, "registerDataChangeListener success");
            this.mSubscribeSuccessList = list;
            this.mIsSubscribe = true;
        }
    }

    private void initPageInfo(String str, long j, String str2) {
        initTitleBar(str, str2);
        initToolBar();
        subscribeData();
        if (j <= 0) {
            getLastDataTimestamp(this.mBaseResponseCallback);
        } else {
            showDetailView(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDetailView(long j) {
        if (isViewAttached()) {
            this.mShowType = 0;
            this.mViewWeakRef.get().showDataView(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoDataNoDevicesDetailView(boolean z) {
        if (!isViewAttached() || this.mShowType == -1) {
            return;
        }
        this.mShowType = -1;
        this.mViewWeakRef.get().showNoDataView(z);
    }

    @Override // com.huawei.ui.main.stories.template.BasePresenter
    public void detachView() {
        if (isViewAttached()) {
            unSubscribeData();
            this.mViewWeakRef.clear();
            this.mViewWeakRef = null;
        }
    }

    public boolean isSubscribeType() {
        return this.mIsSubscribe;
    }

    protected void login(String str) {
        login(str, "");
    }

    protected void login(String str, String str2) {
        if (getView() == null || getView().getViewContext() == null) {
            LogUtil.h(this.mTag, "getView is null || getViewContext is null");
        } else if (LoginInit.getInstance(getView().getViewContext()).getIsLogined()) {
            onLogined(str);
        } else {
            LoginInit.getInstance(getView().getViewContext()).browsingToLogin(new c(this, str, this.mTag), str2);
        }
    }

    protected static class d implements HiSubscribeListener {
        private final WeakReference<HealthDetailCommonActivityPresenter> b;
        private final String d;

        public d(String str, HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter) {
            this.d = str;
            this.b = new WeakReference<>(healthDetailCommonActivityPresenter);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter = this.b.get();
            if (healthDetailCommonActivityPresenter != null) {
                healthDetailCommonActivityPresenter.updateSuccessList(list);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData != null) {
                LogUtil.a(this.d, "onChange, type = ", Integer.valueOf(i), ", newValue = ", hiHealthData.toString());
            } else {
                LogUtil.a(this.d, "onChange, type = ", Integer.valueOf(i));
            }
            HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter = this.b.get();
            if (healthDetailCommonActivityPresenter == null || !healthDetailCommonActivityPresenter.isSubscribeType()) {
                return;
            }
            if (i == DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value() || i == DicDataTypeUtil.DataType.SKIN_TEMPERATURE_SET.value()) {
                qpr.a(str);
            }
            if (i == 10 || i == HiSubscribeType.e) {
                pyw.e().d();
            }
            healthDetailCommonActivityPresenter.getLastDataTimestamp(this.b.get().mBaseResponseCallback);
        }
    }

    protected static class e implements HiUnSubscribeListener {
        private final String d;
        private final String e;

        public e(String str, String str2) {
            this.e = str;
            this.d = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a(this.e, this.d, Boolean.valueOf(z));
        }
    }

    public static class c extends LoginResultCallback {
        private final String b;
        private final String c;
        private final WeakReference<HealthDetailCommonActivityPresenter> d;

        @Override // com.huawei.hwbasemgr.LoginResultCallback
        public boolean isNeedWait() {
            return true;
        }

        c(HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter, String str, String str2) {
            this.d = new WeakReference<>(healthDetailCommonActivityPresenter);
            this.b = str;
            this.c = str2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            final HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter = this.d.get();
            if (healthDetailCommonActivityPresenter == null) {
                return;
            }
            if (i == 0) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: rys
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthDetailCommonActivityPresenter.c.this.c(healthDetailCommonActivityPresenter);
                    }
                });
                LogUtil.h(this.c, "browsingToLogin errorCode is success ", Integer.valueOf(i));
            } else {
                LogUtil.h(this.c, "browsingToLogin errorCode is not success ", Integer.valueOf(i));
            }
        }

        public /* synthetic */ void c(HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter) {
            healthDetailCommonActivityPresenter.refreshPage();
            healthDetailCommonActivityPresenter.onLogined(this.b);
        }
    }

    static class a implements ResourceParseHelper.ConfigInfoCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HealthDetailCommonActivityPresenter> f10515a;

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
        public void getDescription(String str) {
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
        public void getImagePath(String str) {
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
        public void showParseErrorAlert() {
        }

        private a(HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter) {
            this.f10515a = new WeakReference<>(healthDetailCommonActivityPresenter);
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
        public void getTitleName(String str) {
            HealthDetailCommonActivityPresenter healthDetailCommonActivityPresenter = this.f10515a.get();
            if (healthDetailCommonActivityPresenter == null || healthDetailCommonActivityPresenter.getView() == null || !healthDetailCommonActivityPresenter.isViewAttached()) {
                return;
            }
            healthDetailCommonActivityPresenter.getView().setTitle(str);
        }
    }
}
