package com.huawei.ui.main.stories.soical.member;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Coupon;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.bzs;
import defpackage.dqj;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class MemberCouponProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f10480a;
    private List<String> b;
    private List<String> c;
    private List<String> d;
    private List<OnClickSectionListener> e;
    private Context i;
    private SectionBean j;
    private Map<String, Object> m = new HashMap();
    private Handler f = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                ReleaseLogUtil.c("MemberCouponProvider", "msg is null!");
                return;
            }
            super.handleMessage(message);
            if (message.what == 1) {
                ReleaseLogUtil.e("MemberCouponProvider", "UPDATE DATAS!");
                MemberCouponProvider.this.j.e(MemberCouponProvider.this.m);
            }
        }
    };
    private OnClickSectionListener h = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.3
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
            if (str.equals("BOTTOM_TEXT_CLICK_EVENT")) {
                nrh.d(MemberCouponProvider.this.i, MemberCouponProvider.this.i.getResources().getString(R.string._2130845701_res_0x7f022005));
                if (koq.d(MemberCouponProvider.this.b, i) && koq.d(MemberCouponProvider.this.e, i)) {
                    MemberCouponProvider.this.b.set(i, MemberCouponProvider.this.i.getResources().getString(R.string._2130845698_res_0x7f022002));
                    MemberCouponProvider.this.e.set(i, MemberCouponProvider.this.g);
                    MemberCouponProvider.this.m.put("ITEM_JOIN_NUMBER", MemberCouponProvider.this.b);
                    MemberCouponProvider.this.m.put("CLICK_EVENT_LISTENER", MemberCouponProvider.this.e);
                    MemberCouponProvider.this.f10480a.set(i, 1);
                    MemberCouponProvider.this.f.sendEmptyMessage(1);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add((String) MemberCouponProvider.this.c.get(i));
                MemberCouponProvider.this.e(arrayList);
                MemberCouponProvider.this.b(i);
                return;
            }
            String str2 = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.assets?isImmerse&h5pro=true&pName=com.huawei.health&path=CouponDetail&urlType=4&couponId=" + ((String) MemberCouponProvider.this.c.get(i)) + "&status=" + MemberCouponProvider.this.f10480a.get(i);
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                MemberCouponProvider.this.b(i);
                marketRouterApi.router(str2);
            }
        }
    };
    private OnClickSectionListener g = new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.1
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
            String str2 = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.assets?isImmerse&h5pro=true&pName=com.huawei.health&path=CouponDetail&urlType=4&couponId=" + ((String) MemberCouponProvider.this.c.get(i)) + "&status=" + MemberCouponProvider.this.f10480a.get(i);
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                MemberCouponProvider.this.b(i);
                marketRouterApi.router(str2);
            }
        }
    };

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return super.isActive(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (koq.b(this.c, i) || koq.b(this.d, i)) {
            return;
        }
        dqj.d(this.c.get(i), this.d.get(i));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        this.i = context;
        this.j = sectionBean;
        d();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0096, code lost:
    
        if (r1.equals("ITEM_STATUS_BACKGROUND") == false) goto L46;
     */
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseParams(android.content.Context r12, java.util.HashMap r13, java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.parseParams(android.content.Context, java.util.HashMap, java.lang.Object):void");
    }

    private void d() {
        ((PayApi) Services.a("TradeService", PayApi.class)).couponListQuery(null, null, new b(this, 1));
    }

    static class b implements IBaseResponseCallback {
        private WeakReference<MemberCouponProvider> c;
        private int d;

        b(MemberCouponProvider memberCouponProvider, int i) {
            this.c = new WeakReference<>(memberCouponProvider);
            this.d = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MemberCouponProvider memberCouponProvider = this.c.get();
            if (memberCouponProvider != null) {
                if (memberCouponProvider.f == null) {
                    LogUtil.h("MemberCouponProvider", "handler is null");
                    return;
                }
                LogUtil.a("MemberCouponProvider", "GetAssetInfoResponseCallback errorCode = ", Integer.valueOf(i), " mResultType = ", Integer.valueOf(this.d));
                if (this.d == 1) {
                    memberCouponProvider.c(i, obj);
                    return;
                } else {
                    LogUtil.h("MemberCouponProvider", "GetAssetInfoResponseCallback error type ");
                    return;
                }
            }
            LogUtil.h("MemberCouponProvider", "personalCenterFragment is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        LogUtil.a("MemberCouponProvider", "enter dealCouponInfo");
        List<Coupon> arrayList = new ArrayList();
        if (i == 0 && (obj instanceof List)) {
            LogUtil.a("MemberCouponProvider", "dealReceiveInfo errorCode:", Integer.valueOf(i), "objData:", obj);
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            setIsActive(false);
            this.f.sendEmptyMessage(1);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.f10480a = new ArrayList();
        this.e = new ArrayList();
        Collections.sort(arrayList, new Comparator<Coupon>() { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(Coupon coupon, Coupon coupon2) {
                if (coupon.getStatus() != coupon2.getStatus()) {
                    return coupon.getStatus() - coupon2.getStatus();
                }
                return coupon2.getWeight() - coupon.getWeight();
            }
        });
        for (Coupon coupon : arrayList) {
            if (coupon != null && coupon.getStatus() != 2 && coupon.getStatus() != 3 && !coupon.isOutOfStockFlag() && coupon.isVipFlag()) {
                this.c.add(coupon.getCouponId());
                this.f10480a.add(Integer.valueOf(coupon.getStatus()));
                arrayList2.add(101);
                arrayList6.add("¥");
                int feeMode = coupon.getFeeMode();
                if (feeMode == 0) {
                    arrayList3.add(String.valueOf(Integer.valueOf(coupon.getFaceValue()).intValue() / 100));
                } else if (feeMode == 1) {
                    arrayList3.add(String.valueOf(Integer.valueOf(coupon.getSubFee()).intValue() / 100));
                } else if (feeMode == 2) {
                    arrayList3.add(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845020_res_0x7f021d5c), String.valueOf(Integer.valueOf(coupon.getDiscount()).intValue() * 10)));
                }
                arrayList4.add(coupon.getCouponName());
                arrayList5.add(ContextCompat.getDrawable(this.i, R.drawable.pic_coupon_2_xxxhdpi));
                int status = coupon.getStatus();
                if (status == 0) {
                    this.b.add(this.i.getResources().getString(R.string._2130845697_res_0x7f022001));
                    this.e.add(this.h);
                } else if (status == 1) {
                    this.b.add(this.i.getResources().getString(R.string._2130845698_res_0x7f022002));
                    this.e.add(this.g);
                }
            }
        }
        if (koq.b(this.c)) {
            setIsActive(false);
            this.f.sendEmptyMessage(1);
            return;
        }
        this.d = arrayList4;
        this.m.put("COMMON_CLICK_EVENT", new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath("#/Coupons");
                bzs.e().loadH5ProApp(com.huawei.haf.application.BaseApplication.e(), "com.huawei.health.h5.assets", builder);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.m.put("ITEM_VIEW_TYPE", arrayList2);
        this.m.put("ITEM_TITLE", arrayList3);
        this.m.put("ITEM_DESCRIPTION", arrayList4);
        this.m.put("ITEM_STATUS_BACKGROUND", arrayList5);
        this.m.put("ITEM_PAGE_ATTRIBUTE", arrayList6);
        this.m.put("ITEM_JOIN_NUMBER", this.b);
        this.m.put("CLICK_EVENT_LISTENER", this.e);
        this.f.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final List<String> list) {
        final b bVar = new b(this, 2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.soical.member.MemberCouponProvider.7
            @Override // java.lang.Runnable
            public void run() {
                ((PayApi) Services.a("TradeService", PayApi.class)).receiveCouponList(list, bVar);
            }
        });
    }
}
