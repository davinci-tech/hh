package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.PromotionPolicyActivity;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.health.vip.datatypes.PromotionPolicyCacheInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.PromotionPolicyInfo;
import com.huawei.ui.commonui.dialog.QueueDialog;
import com.huawei.ui.commonui.dialog.VibrantLifeDialog;
import com.huawei.ui.homehealth.HomeFragment;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class odd {
    private static long d;

    /* renamed from: a, reason: collision with root package name */
    private String f15619a;
    private WeakReference<HomeFragment> c;
    private WeakReference<Context> e;
    private VibrantLifeDialog j;
    private int h = 0;
    private long b = 0;

    public odd(HomeFragment homeFragment, Context context) {
        this.c = new WeakReference<>(homeFragment);
        this.e = new WeakReference<>(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HomeFragment f() {
        WeakReference<HomeFragment> weakReference = this.c;
        if (weakReference == null) {
            LogUtil.b("HomeFragmentDialogMgr", "mRef is null");
            return null;
        }
        HomeFragment homeFragment = weakReference.get();
        if (homeFragment != null) {
            return homeFragment;
        }
        LogUtil.b("HomeFragmentDialogMgr", "fragment is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context j() {
        Context context = this.e.get();
        if (context == null) {
            return null;
        }
        return context;
    }

    public void g() {
        TodoDataApi todoDataApi = (TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class);
        if (f() == null) {
            LogUtil.b("HomeFragmentDialogMgr", "fragment is null");
            return;
        }
        todoDataApi.hideOrShowTodoFloatView(f().getActivity(), (f().o || !f().m || f().g) ? false : true);
        Context j = j();
        if (j == null) {
            LogUtil.a("HomeFragmentDialogMgr", "updateTodoFloatView context is null");
            return;
        }
        LogUtil.a("HomeFragmentDialogMgr", "updateTodoFloatView end");
        LogUtil.a("HomeFragmentDialogMgr", "updateShowAd, ", Boolean.valueOf(f().g));
        LogUtil.a("HomeFragmentDialogMgr", "mContext, ", j);
        LogUtil.a("HomeFragmentDialogMgr", "mFragmentVisible, ", Boolean.valueOf(f().b));
        if (f().g || !f().b || !f().i || LoginInit.getInstance(j).isBrowseMode()) {
            return;
        }
        f().i = false;
        if (!nsd.a("FRAGMENT_SHOW_DIALOG")) {
            d = nsd.c("FRAGMENT_START_TIME");
            this.h = nsd.d("FRAGMENT_SHOW_DIALOG_COUNT");
        }
        LogUtil.a("HomeFragmentDialogMgr", "queryingShowDialogCondition, ", this);
        o();
        LogUtil.a("HomeFragmentDialogMgr", "shouldLoadDialog,", Boolean.valueOf(b()));
        if (!b() && this.j == null) {
            e();
        }
        h();
        if (f().o || TimeUtil.b(this.b, System.currentTimeMillis()) || LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return;
        }
        HandlerCenter.d().e(new Runnable() { // from class: odj
            @Override // java.lang.Runnable
            public final void run() {
                odd.this.a();
            }
        }, 3000L);
    }

    private void o() {
        List<DeviceBenefitQueryParam> e = njn.e("HomeFragmentDialogMgr", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL);
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi != null) {
            payApi.getAllDeviceBenefits(e, new a());
        }
    }

    class a implements IBaseResponseCallback {
        private a() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (odd.this.f() != null) {
                Context j = odd.this.j();
                if (j == null) {
                    LogUtil.a("HomeFragmentDialogMgr", "context is null");
                    return;
                }
                LogUtil.a("HomeFragmentDialogMgr", "HomeEquityDialogCallBack== ", Integer.valueOf(i));
                if (i == 0) {
                    qqs.d(obj, j, 1, odd.d, odd.this.h);
                    return;
                }
                return;
            }
            LogUtil.a("HomeFragmentDialogMgr", "homeFragment== ", null);
        }
    }

    public static boolean b() {
        if (CommonUtil.bu()) {
            return false;
        }
        String e = nsd.e();
        LogUtil.a("HomeFragmentDialogMgr", "needNova = ", e);
        return "needNova".equals(e);
    }

    public void e() {
        if (CommonUtil.bh()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: odd.3
                @Override // java.lang.Runnable
                public void run() {
                    odd.this.f15619a = njn.a(BaseApplication.getContext());
                    LogUtil.a("HomeFragmentDialogMgr", "getDeviceSn end is deviceSn empty", Boolean.valueOf(TextUtils.isEmpty(odd.this.f15619a)));
                    if (TextUtils.isEmpty(odd.this.f15619a)) {
                        return;
                    }
                    odd.this.i();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        DeviceBenefitQueryParam build = new DeviceBenefitQueryParam.Builder().setDeviceType(PhoneInfoUtils.getDeviceModel()).setBenefitType(DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_PERF_PURCHASE).setDeviceCategory(DeviceBenefitQueryParam.DeviceCategory.DEVICE_CATEGORY_PHONE.getCategory()).setDeviceSn(this.f15619a).setNeedProductInfo(true).build();
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        LogUtil.a("HomeFragmentDialogMgr", "deviceModel", PhoneInfoUtils.getDeviceModel());
        if (payApi != null) {
            payApi.getDeviceBenefits(build, new c());
        }
    }

    class c implements IBaseResponseCallback {
        private c() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 0) {
                LogUtil.h("HomeFragmentDialogMgr", "HomeMemberCallBack errorCode", Integer.valueOf(i));
                return;
            }
            if (obj == null) {
                LogUtil.h("HomeFragmentDialogMgr", "HomeMemberCallBack objData is null");
                return;
            }
            DeviceBenefits deviceBenefits = (DeviceBenefits) obj;
            List<DevicePerfPurchaseInfo> perfPurchaseInfos = deviceBenefits.getPerfPurchaseInfos();
            if (koq.b(perfPurchaseInfos) || koq.b(perfPurchaseInfos, 0)) {
                LogUtil.a("HomeFragmentDialogMgr", "HomeFragment perfPurchaseInfos == null");
                return;
            }
            String deviceSn = deviceBenefits.getDeviceSn();
            long b = gpn.b(false, deviceSn);
            LogUtil.a("HomeFragmentDialogMgr", "getDeviceBenefitsListAndShowDialog ", " lastShowDialogTime:", Long.valueOf(b));
            if (!rsr.c(b, System.currentTimeMillis()) || dpx.d(false, deviceSn)) {
                return;
            }
            DevicePerfPurchaseInfo devicePerfPurchaseInfo = perfPurchaseInfos.get(0);
            LogUtil.a("HomeFragmentDialogMgr", "devicePerfPurchaseInfo ==" + devicePerfPurchaseInfo);
            odd.this.c(devicePerfPurchaseInfo, deviceSn);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final DevicePerfPurchaseInfo devicePerfPurchaseInfo, final String str) {
        long effectiveStartTime = devicePerfPurchaseInfo.getEffectiveStartTime();
        long effectiveEndTime = devicePerfPurchaseInfo.getEffectiveEndTime();
        int activeStatus = devicePerfPurchaseInfo.getActiveStatus();
        if (!qqs.c(effectiveStartTime, effectiveEndTime) || activeStatus != 1) {
            LogUtil.h("HomeFragmentDialogMgr", "devicePerfPurchase is expired or active");
            return;
        }
        HomeFragment f = f();
        if (f == null) {
            return;
        }
        if (f.getActivity() == null) {
            LogUtil.h("HomeFragmentDialogMgr", "activity is null");
            return;
        }
        String backgroundUrl = devicePerfPurchaseInfo.getBackgroundUrl();
        if (TextUtils.isEmpty(backgroundUrl)) {
            LogUtil.h("HomeFragmentDialogMgr", "backgroundUrl is empty");
            return;
        }
        final String deviceName = devicePerfPurchaseInfo.getDeviceName();
        final Drawable cIb_ = nrf.cIb_(BaseApplication.getContext(), backgroundUrl);
        HandlerExecutor.e(new Runnable() { // from class: odk
            @Override // java.lang.Runnable
            public final void run() {
                odd.this.cWr_(devicePerfPurchaseInfo, deviceName, cIb_, str);
            }
        });
        d(devicePerfPurchaseInfo);
    }

    /* synthetic */ void cWr_(DevicePerfPurchaseInfo devicePerfPurchaseInfo, String str, Drawable drawable, String str2) {
        cWp_(devicePerfPurchaseInfo, str, drawable, str2);
        gpn.a(false, str2);
        nsd.f("NotNeedNova");
    }

    private void cWp_(DevicePerfPurchaseInfo devicePerfPurchaseInfo, String str, Drawable drawable, final String str2) {
        String e = gpn.e(gpp.e(devicePerfPurchaseInfo), gpn.a(devicePerfPurchaseInfo));
        if (f() == null) {
            LogUtil.b("HomeFragmentDialogMgr", "getFragment is null");
            return;
        }
        FragmentActivity activity = f().getActivity();
        if (activity == null) {
            LogUtil.h("HomeFragmentDialogMgr", "activity is null");
            return;
        }
        this.j = new VibrantLifeDialog(activity);
        if (drawable == null) {
            LogUtil.h("HomeFragmentDialogMgr", "drawable is null");
        }
        this.j.czX_(drawable);
        try {
            String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R$string.IDS_equity_first), str);
            String format2 = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R$string.IDS_user_member_content), e);
            this.j.d(format);
            this.j.c(format2);
        } catch (IllegalFormatConversionException e2) {
            LogUtil.a("HomeFragmentDialogMgr", "IllegalFormatConversionException : format exception" + LogAnonymous.b((Throwable) e2));
        }
        final int linkType = devicePerfPurchaseInfo.getLinkType();
        final String linkValue = devicePerfPurchaseInfo.getLinkValue();
        LogUtil.a("HomeFragmentDialogMgr", "linkValue ==" + linkValue);
        HandlerExecutor.d(new Runnable() { // from class: odg
            @Override // java.lang.Runnable
            public final void run() {
                odd.this.e(linkValue, linkType, str2);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void e(final String str, final int i, String str2) {
        if (f() != null) {
            f().f9349a = false;
        }
        this.j.czY_(new View.OnClickListener() { // from class: odi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                odd.this.cWq_(str, i, view);
            }
        });
        QueueDialog queueDialog = this.j;
        queueDialog.enqueueShowing(queueDialog);
        dpx.b(false, str2);
        ObserverManagerUtil.c("EQUITY_AND_VIBRANT", new Object[0]);
    }

    /* synthetic */ void cWq_(String str, int i, View view) {
        HomeFragment f = f();
        if (f == null) {
            LogUtil.a("HomeFragmentDialogMgr", "homeFragment is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            qqs.e(f.getActivity(), str, i);
            this.j.dismiss();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void d(final DevicePerfPurchaseInfo devicePerfPurchaseInfo) {
        LogUtil.a("HomeFragmentDialogMgr", "generateBenefitMessage enter");
        if (devicePerfPurchaseInfo == null) {
            LogUtil.h("HomeFragmentDialogMgr", "devicePerfPurchaseInfo = null");
            return;
        }
        final String deviceName = devicePerfPurchaseInfo.getDeviceName();
        final String e = gpn.e(gpp.e(devicePerfPurchaseInfo), gpn.a(devicePerfPurchaseInfo));
        ThreadPoolManager.d().execute(new Runnable() { // from class: odd.5
            @Override // java.lang.Runnable
            public void run() {
                MessageObject messageObject = new MessageObject();
                messageObject.setMsgType(2);
                messageObject.setPosition(3);
                messageObject.setMsgPosition(11);
                messageObject.setReceiveTime(System.currentTimeMillis());
                messageObject.setModule(String.valueOf(70));
                messageObject.setType(MessageConstant.MEMBER_TYPE);
                messageObject.setReadFlag(0);
                messageObject.setMsgId("20211113");
                messageObject.setDetailUri("messagecenter://benefit?type=" + devicePerfPurchaseInfo.getLinkType() + "&key=" + gic.a(devicePerfPurchaseInfo.getLinkValue()));
                messageObject.setMsgTitle(String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R.string.IDS_benifits), deviceName));
                messageObject.setMsgContent(String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R.string.IDS_benifits_content), deviceName, e, devicePerfPurchaseInfo.getPerfPurchaseName()));
                ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).insertMessage(messageObject);
            }
        });
    }

    private void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: odd.4
            @Override // java.lang.Runnable
            public void run() {
                odd.this.e(njn.e("HomeFragmentDialogMgr", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<DeviceBenefitQueryParam> list) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi != null) {
            payApi.getAllDeviceBenefits(list, new b());
        }
    }

    class b implements IBaseResponseCallback {
        private b() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            if (odd.this.f() == null) {
                LogUtil.a("HomeFragmentDialogMgr", "homeFragment is null");
                return;
            }
            LogUtil.a("HomeFragmentDialogMgr", "HomeEquityVibrantLifeDialogCallBack ", Integer.valueOf(i));
            if (i == 0) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: odd.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Context j = odd.this.j();
                        if (j == null) {
                            LogUtil.a("HomeFragmentDialogMgr", "HomeEquityVibrantLifeDialogCallBack context is null");
                        } else {
                            qrm.d(obj, j);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public void a() {
        long c2 = nsd.c("FRAGMENT_START_TIME");
        long e = gpn.e();
        long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.e("R_HomeFragmentDialogMgr", "et sw tm:", Long.valueOf(c2), " vi sw tm:", Long.valueOf(e));
        if (TimeUtil.b(c2, currentTimeMillis) || TimeUtil.b(e, currentTimeMillis)) {
            LogUtil.h("HomeFragmentDialogMgr", "queryingPromotionAndShowDialog has showed equity dialog today, return");
        } else if (!dpx.a()) {
            LogUtil.h("HomeFragmentDialogMgr", "queryingPromotionAndShowDialog has showed this month");
        } else {
            this.b = System.currentTimeMillis();
            ThreadPoolManager.d().execute(new Runnable() { // from class: odc
                @Override // java.lang.Runnable
                public final void run() {
                    odd.this.c();
                }
            });
        }
    }

    /* synthetic */ void c() {
        ((PayApi) Services.c("TradeService", PayApi.class)).getPromotionPolicy(new IBaseResponseCallback() { // from class: ode
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                odd.this.a(i, obj);
            }
        });
    }

    /* synthetic */ void a(int i, Object obj) {
        if (obj instanceof PromotionPolicyInfo) {
            PromotionPolicyInfo promotionPolicyInfo = (PromotionPolicyInfo) obj;
            if (!promotionPolicyInfo.isDialogFlag()) {
                LogUtil.h("HomeFragmentDialogMgr", "queryingPromotionAndShowDialog need not to show promotion dialog");
                return;
            }
            PromotionPolicyCacheInfo b2 = dpx.b();
            if (TextUtils.equals(promotionPolicyInfo.getPromotionPolicyId(), b2.getPolicyId()) && b2.getLastPolicyShowCount() >= 3) {
                LogUtil.h("HomeFragmentDialogMgr", "same promotion dialog has showed for 3 times, return");
                return;
            } else {
                d(promotionPolicyInfo);
                return;
            }
        }
        LogUtil.h("HomeFragmentDialogMgr", "failed !! getPromotionPolicy errorCode = " + i);
    }

    private void d(final PromotionPolicyInfo promotionPolicyInfo) {
        String dialogPicUrl;
        if (promotionPolicyInfo == null) {
            LogUtil.h("HomeFragmentDialogMgr", "preloadImage() error, promotionInfo is null");
            return;
        }
        if (nsn.ag(BaseApplication.getActivity())) {
            dialogPicUrl = promotionPolicyInfo.getTahitiDialogPicUrl();
        } else {
            dialogPicUrl = promotionPolicyInfo.getDialogPicUrl();
        }
        if (TextUtils.isEmpty(dialogPicUrl)) {
            LogUtil.h("HomeFragmentDialogMgr", "preloadImage() error, imageUrl is null or empty");
            return;
        }
        ReleaseLogUtil.e("R_HomeFragmentDialogMgr", "start preloadImage");
        nrf.d(BaseApplication.getContext(), dialogPicUrl, new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.DATA), new RequestListener<Drawable>() { // from class: odd.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                ReleaseLogUtil.e("R_HomeFragmentDialogMgr", "preloadImage failed");
                odd.this.b = 0L;
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            /* renamed from: cWs_, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                ReleaseLogUtil.e("R_HomeFragmentDialogMgr", "preloadImage success");
                Context j = odd.this.j();
                HomeFragment f = odd.this.f();
                if (j == null || f == null) {
                    odd.this.b = 0L;
                    LogUtil.h("HomeFragmentDialogMgr", "preloadImage context or homeFragment is null");
                    return true;
                }
                if (!f.isResumed()) {
                    odd.this.b = 0L;
                    LogUtil.h("HomeFragmentDialogMgr", "homeFragment is not resumed");
                    return true;
                }
                Intent intent = new Intent(j, (Class<?>) PromotionPolicyActivity.class);
                intent.putExtra("promotionPolicyInfo", nrv.a(promotionPolicyInfo));
                odd.this.j().startActivity(intent);
                return true;
            }
        });
    }
}
