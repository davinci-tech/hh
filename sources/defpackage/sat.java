package defpackage;

import android.os.Handler;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.trade.datatype.Coupon;
import com.huawei.trade.datatype.GiftCard;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import defpackage.sat;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes7.dex */
public class sat {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f16991a;
    private String b;
    private String c;
    private String d;
    private final PersonalCenterUiApi h;
    private boolean g = false;
    private boolean e = false;
    private boolean j = false;

    public sat(PersonalCenterFragment personalCenterFragment, Handler handler) {
        this.h = personalCenterFragment;
        this.f16991a = handler;
    }

    public String e() {
        return this.c;
    }

    public String d() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public boolean a() {
        return this.j;
    }

    static class a implements IBaseResponseCallback {
        private final WeakReference<sat> b;
        private final int e;

        a(sat satVar, int i) {
            this.b = new WeakReference<>(satVar);
            this.e = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            sat satVar = this.b.get();
            if (satVar == null) {
                LogUtil.h("MyAssetItemManager", "personalCenterFragment is null");
            }
            LogUtil.a("MyAssetItemManager", "GetAssetInfoResponseCallback errorCode = ", Integer.valueOf(i), " mResultType = ", Integer.valueOf(this.e));
            switch (this.e) {
                case 11:
                    if (i == 0 && (obj instanceof List)) {
                        satVar.h.dealAssetMessage((List) obj);
                        break;
                    } else {
                        LogUtil.h("MyAssetItemManager", "assetMsgListQuery fail:", Integer.valueOf(i));
                        break;
                    }
                    break;
                case 12:
                    if (i == 0 && (obj instanceof List)) {
                        satVar.d((List<Coupon>) obj);
                    } else {
                        LogUtil.h("MyAssetItemManager", "couponListQuery fail:", Integer.valueOf(i));
                    }
                    satVar.g = true;
                    satVar.j();
                    break;
                case 13:
                    if (i == 0 && (obj instanceof List)) {
                        satVar.c((List<GiftCard>) obj);
                    } else {
                        LogUtil.h("MyAssetItemManager", "giftCardListQuery fail:", Integer.valueOf(i));
                    }
                    satVar.e = true;
                    satVar.j();
                    break;
                case 14:
                    sat.a(i, obj, satVar);
                    break;
                default:
                    LogUtil.h("MyAssetItemManager", "GetAssetInfoResponseCallback error type ");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i, Object obj, sat satVar) {
        if (i == 0 && (obj instanceof List)) {
            satVar.a((List) obj);
        } else {
            LogUtil.h("MyAssetItemManager", "awardQuery fail:", Integer.valueOf(i));
        }
        satVar.e = true;
        satVar.j();
    }

    public void c() {
        LogUtil.a("MyAssetItemManager", "getAssetsInfo start");
        i();
        final a aVar = new a(this, 11);
        final a aVar2 = new a(this, 13);
        final a aVar3 = new a(this, 12);
        final a aVar4 = new a(this, 14);
        ThreadPoolManager.d().execute(new Runnable() { // from class: sax
            @Override // java.lang.Runnable
            public final void run() {
                sat.a(sat.a.this, aVar2, aVar3, aVar4);
            }
        });
    }

    static /* synthetic */ void a(a aVar, a aVar2, a aVar3, a aVar4) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.a("MyAssetItemManager", "getAssetsInfo payApi is null");
            return;
        }
        payApi.assetMsgListQuery(50, System.currentTimeMillis(), aVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        payApi.giftCardListQuery(arrayList, aVar2);
        payApi.couponListQuery(arrayList, null, aVar3);
        payApi.awardListQuery(aVar4);
    }

    public void h() {
        if (ActiveTipStringUtils.b()) {
            phn.c(new ResultCallback<phl>() { // from class: sat.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(phl phlVar) {
                    ReleaseLogUtil.e("MyAssetItemManager", "queryPointsNewAdd with ", Integer.valueOf(phlVar.c()));
                    sat.this.j = phlVar.c() == 1;
                    if (sat.this.f16991a != null) {
                        if (sat.this.j) {
                            sat.this.f16991a.sendMessage(sat.this.f16991a.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, true));
                            return;
                        } else {
                            sat.this.j();
                            return;
                        }
                    }
                    LogUtil.b("MyAssetItemManager", "mHandler is null in queryPointsNewAdd");
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "queryPointsNewAdd failure ";
                    objArr[1] = th == null ? null : th.getMessage();
                    ReleaseLogUtil.c("MyAssetItemManager", objArr);
                }
            });
        }
    }

    private void i() {
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = false;
        this.g = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Coupon> list) {
        if (koq.b(list)) {
            LogUtil.a("MyAssetItemManager", "coupons is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "coupons size:", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        for (Coupon coupon : list) {
            if (coupon == null || coupon.isOutOfStockFlag() || !coupon.isShowToReceiveFlag()) {
                LogUtil.h("MyAssetItemManager", "coupon = null or coupon isOutOfStockFlag");
            } else if (TextUtils.isEmpty(coupon.getUserLabels())) {
                arrayList.add(coupon);
            } else if (marketingApi == null) {
                LogUtil.h("MyAssetItemManager", "marketingApi = null");
            } else if (marketingApi.isValidUserLabels(coupon.getUserLabels())) {
                arrayList.add(coupon);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("MyAssetItemManager", "myCoupon is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "myCoupon size:", Integer.valueOf(arrayList.size()));
        List<Coupon> b = b(arrayList);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        long g = CommonUtil.g(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_coupon_time"));
        long createTime = b.get(0).getCreateTime();
        LogUtil.a("MyAssetItemManager", "couponBeforeTime beforeTime:", Long.valueOf(g), " createTime:", Long.valueOf(createTime));
        if (g < createTime) {
            this.c = Long.toString(createTime);
            Handler handler = this.f16991a;
            handler.sendMessage(handler.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, true));
        } else {
            this.c = "";
            LogUtil.a("MyAssetItemManager", "couponListQuery time equal");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<GiftCard> list) {
        if (koq.b(list)) {
            LogUtil.a("MyAssetItemManager", "giftCards is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "giftCards size:", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        for (GiftCard giftCard : list) {
            if (giftCard == null || !giftCard.isShowToReceiveFlag()) {
                LogUtil.h("MyAssetItemManager", "giftCard not need show");
            } else if (TextUtils.isEmpty(giftCard.getUserLabels())) {
                arrayList.add(giftCard);
            } else if (marketingApi == null) {
                LogUtil.h("MyAssetItemManager", "dealCardCaseResult marketingApi = null");
            } else if (marketingApi.isValidUserLabels(giftCard.getUserLabels())) {
                arrayList.add(giftCard);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("MyAssetItemManager", "myGiftCards is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "myGiftCards size:", Integer.valueOf(arrayList.size()));
        List<GiftCard> i = i(arrayList);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        long g = CommonUtil.g(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_card_time"));
        long createTime = i.get(0).getCreateTime();
        LogUtil.a("MyAssetItemManager", "giftCardListQuery beforeTime:", Long.valueOf(g), " createTime:", Long.valueOf(createTime));
        if (g < createTime) {
            this.b = Long.toString(createTime);
            Handler handler = this.f16991a;
            handler.sendMessage(handler.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, true));
        } else {
            this.b = "";
            LogUtil.a("MyAssetItemManager", "giftCardListQuery time equal");
        }
    }

    private void a(List<AwardRecordsBean> list) {
        if (koq.b(list)) {
            LogUtil.a("MyAssetItemManager", "giftCards is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "awardRecordsBeans size:", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        for (AwardRecordsBean awardRecordsBean : list) {
            if (awardRecordsBean.getIsExpire() == 0) {
                arrayList.add(awardRecordsBean);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("MyAssetItemManager", "myAwardRecords is empty");
            return;
        }
        LogUtil.a("MyAssetItemManager", "myAwardRecords size:", Integer.valueOf(arrayList.size()));
        List<AwardRecordsBean> e = e(arrayList);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        long b = SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_award_time", 0L);
        long wonTime = e.get(0).getWonTime();
        LogUtil.a("MyAssetItemManager", "myAwardRecords beforeTime:", Long.valueOf(b), " createTime:", Long.valueOf(wonTime));
        if (b < wonTime) {
            this.d = Long.toString(wonTime);
            Handler handler = this.f16991a;
            handler.sendMessage(handler.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, true));
        } else {
            this.d = "";
            LogUtil.a("MyAssetItemManager", "giftCardListQuery time equal");
        }
    }

    private static List<AwardRecordsBean> e(List<AwardRecordsBean> list) {
        Collections.sort(list, new Comparator() { // from class: sar
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((AwardRecordsBean) obj2).getWonTime(), ((AwardRecordsBean) obj).getWonTime());
                return compare;
            }
        });
        return list;
    }

    private static List<GiftCard> i(List<GiftCard> list) {
        Collections.sort(list, new Comparator() { // from class: saw
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((GiftCard) obj2).getCreateTime(), ((GiftCard) obj).getCreateTime());
                return compare;
            }
        });
        return list;
    }

    private static List<Coupon> b(List<Coupon> list) {
        Collections.sort(list, new Comparator() { // from class: sba
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((Coupon) obj2).getCreateTime(), ((Coupon) obj).getCreateTime());
                return compare;
            }
        });
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("MyAssetItemManager", "checkMyAssetRedPointGone:", "mIsHaveGetCardCartData:", Boolean.valueOf(this.e), " mIsHaveGetCouponData:", Boolean.valueOf(this.g), " mCouponTime:", this.c, " mCardCaseTime:", this.b, " mAwardTime:", this.d, " mIsNewPoint:", Boolean.valueOf(this.j));
        if (this.e && this.g && TextUtils.isEmpty(this.c) && TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.d) && !this.j) {
            LogUtil.a("MyAssetItemManager", "set MyAsset red point gone");
            Handler handler = this.f16991a;
            handler.sendMessage(handler.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, false));
        }
    }
}
