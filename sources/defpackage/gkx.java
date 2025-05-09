package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.tradeservice.cloud.OrderManager;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.Iap;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.InAppPurchaseData;
import com.huawei.hms.iap.entity.OwnedPurchasesReq;
import com.huawei.hms.iap.entity.OwnedPurchasesResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.OrderBriefInfo;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class gkx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile gkx f12843a;
    private Context e = BaseApplication.e();
    private String c = "";

    private gkx() {
    }

    public static gkx e() {
        if (f12843a == null) {
            synchronized (gkx.class) {
                if (f12843a == null) {
                    f12843a = new gkx();
                }
            }
        }
        return f12843a;
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(this.c) && this.c.equals(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011))) {
            LogUtil.h("TradeService_PayService", "startRetrievingOrders same huid");
        } else {
            ThreadPoolManager.d().b("TradeService_PayService", null);
            ThreadPoolManager.d().c("TradeService_PayService", new Runnable() { // from class: gkx.5
                @Override // java.lang.Runnable
                public void run() {
                    gkx.this.d();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (CommonUtil.bu()) {
            return;
        }
        if (Utils.l()) {
            LogUtil.a("TradeService_PayService", "retrievingOrders isOverseaNoCloudVersion !!");
            return;
        }
        if (!LoginInit.getInstance(this.e).getIsLogined()) {
            LogUtil.a("TradeService_PayService", "retrievingOrders login in first !!");
            return;
        }
        LogUtil.a("TradeService_PayService", "retrievingOrders enter");
        this.c = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        long c = c();
        final long e = e(c);
        OrderManager.orderQueryHistory(e, c, 0L, new IBaseResponseCallback() { // from class: gkx.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof List)) {
                    StringBuilder sb = new StringBuilder("retrievingOrders last month order size = ");
                    List list = (List) obj;
                    sb.append(list.size());
                    LogUtil.a("TradeService_PayService", sb.toString());
                    gkx.this.a(e, 1, list);
                    gkx.this.a(e, 2, list);
                }
            }
        });
        a(0L, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final long j, int i, final List<OrderBriefInfo> list) {
        LogUtil.a("TradeService_PayService", "getIapOrder：getIapOrder enter [" + i + "]");
        OwnedPurchasesReq ownedPurchasesReq = new OwnedPurchasesReq();
        ownedPurchasesReq.setPriceType(i);
        ownedPurchasesReq.setSignatureAlgorithm("SHA256WithRSA/PSS");
        Task<OwnedPurchasesResult> obtainOwnedPurchases = Iap.getIapClient(this.e, gla.c()).obtainOwnedPurchases(ownedPurchasesReq);
        if (obtainOwnedPurchases == null) {
            LogUtil.b("TradeService_PayService", "getIapOrder：create task failed !!");
        } else {
            obtainOwnedPurchases.addOnSuccessListener(new OnSuccessListener<OwnedPurchasesResult>() { // from class: gkx.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(OwnedPurchasesResult ownedPurchasesResult) {
                    if (ownedPurchasesResult == null || ownedPurchasesResult.getInAppPurchaseDataList() == null) {
                        return;
                    }
                    LogUtil.a("TradeService_PayService", "getIapOrder from iap size = " + ownedPurchasesResult.getInAppPurchaseDataList().size());
                    for (int i2 = 0; i2 < ownedPurchasesResult.getInAppPurchaseDataList().size(); i2++) {
                        try {
                            gkx.this.d(ownedPurchasesResult.getInAppPurchaseDataList().get(i2), ownedPurchasesResult.getInAppSignature().get(i2), j, list);
                        } catch (JSONException unused) {
                            LogUtil.b("TradeService_PayService", "getIapOrder：JSONException");
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: gkx.1
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc instanceof IapApiException) {
                        IapApiException iapApiException = (IapApiException) exc;
                        Status status = iapApiException.getStatus();
                        LogUtil.b("TradeService_PayService", "getIapOrder：createPurchaseIntent returnCode = " + iapApiException.getStatusCode() + ", status = " + status);
                        return;
                    }
                    LogUtil.b("TradeService_PayService", "getIapOrder：Exception：" + exc.getClass().getSimpleName());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, long j, List<OrderBriefInfo> list) throws JSONException {
        InAppPurchaseData inAppPurchaseData = new InAppPurchaseData(str);
        if (inAppPurchaseData.getPurchaseState() != 0) {
            LogUtil.h("TradeService_PayService", "checkOrder iap order is not purchased " + inAppPurchaseData.getOrderID());
        } else {
            if (inAppPurchaseData.getPurchaseTime() < j || a(inAppPurchaseData, list)) {
                return;
            }
            LogUtil.a("TradeService_PayService", "checkOrder health order: " + inAppPurchaseData.getDeveloperPayload() + " iap order: " + inAppPurchaseData.getOrderID());
            OrderManager.orderRedelivery(inAppPurchaseData.getDeveloperPayload(), str, str2, new IBaseResponseCallback() { // from class: gkx.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.h("TradeService_PayService", "checkOrder：orderRedelivery errorCode = " + i);
                }
            });
        }
    }

    private boolean a(InAppPurchaseData inAppPurchaseData, List<OrderBriefInfo> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<OrderBriefInfo> it = list.iterator();
        while (it.hasNext()) {
            if (inAppPurchaseData.getOrderID().equals(it.next().getOrderId())) {
                return true;
            }
        }
        return false;
    }

    private long c() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        return calendar.getTimeInMillis();
    }

    private long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTimeInMillis(j);
        calendar.add(2, -1);
        return calendar.getTimeInMillis();
    }

    public void a(String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TradeService_PayService", "productIdList is empty.");
            iBaseResponseCallback.d(-1, "");
            return;
        }
        final List list = (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: gkx.9
        }.getType());
        if (koq.b(list)) {
            iBaseResponseCallback.d(-1, "");
            return;
        }
        OwnedPurchasesReq ownedPurchasesReq = new OwnedPurchasesReq();
        ownedPurchasesReq.setPriceType(2);
        ownedPurchasesReq.setSignatureAlgorithm("SHA256WithRSA/PSS");
        Task<OwnedPurchasesResult> obtainOwnedPurchases = Iap.getIapClient(BaseApplication.e(), gla.c()).obtainOwnedPurchases(ownedPurchasesReq);
        if (obtainOwnedPurchases == null) {
            LogUtil.b("TradeService_PayService", "getIapOrder：create task failed !!");
            iBaseResponseCallback.d(-1, "");
        } else {
            ReleaseLogUtil.e("R_TradeService_PayService", "start obtainOwnedPurchases.");
            obtainOwnedPurchases.addOnSuccessListener(ThreadPoolManager.d(), new OnSuccessListener<OwnedPurchasesResult>() { // from class: gkx.10
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(OwnedPurchasesResult ownedPurchasesResult) {
                    ReleaseLogUtil.e("R_TradeService_PayService", "obtainOwnedPurchases onSuccess.");
                    Map<String, String> a2 = gkx.this.a(ownedPurchasesResult, list);
                    iBaseResponseCallback.d(0, new Gson().toJson(a2));
                }
            }).addOnFailureListener(ThreadPoolManager.d(), new OnFailureListener() { // from class: gkx.6
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc instanceof IapApiException) {
                        IapApiException iapApiException = (IapApiException) exc;
                        LogUtil.b("TradeService_PayService", "getProductState：returnCode = " + iapApiException.getStatusCode() + ", status = " + iapApiException.getStatus());
                    } else {
                        LogUtil.b("TradeService_PayService", "getProductState：Exception：" + exc.getClass().getSimpleName());
                    }
                    iBaseResponseCallback.d(-1, "");
                }
            });
        }
    }

    public Map<String, String> a(OwnedPurchasesResult ownedPurchasesResult, List<String> list) {
        HashMap hashMap = new HashMap();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), "0");
        }
        if (ownedPurchasesResult == null || ownedPurchasesResult.getInAppPurchaseDataList() == null) {
            LogUtil.a("TradeService_PayService", "no product info result.");
            return hashMap;
        }
        LogUtil.a("TradeService_PayService", "getProductState from iap size = " + ownedPurchasesResult.getInAppPurchaseDataList().size());
        for (int i = 0; i < ownedPurchasesResult.getInAppPurchaseDataList().size(); i++) {
            try {
                InAppPurchaseData inAppPurchaseData = new InAppPurchaseData(ownedPurchasesResult.getInAppPurchaseDataList().get(i));
                Iterator<String> it2 = list.iterator();
                while (it2.hasNext()) {
                    e(it2.next(), inAppPurchaseData, hashMap);
                }
            } catch (JSONException unused) {
                LogUtil.b("TradeService_PayService", "getProductState：Exception.");
            }
        }
        LogUtil.c("TradeService_PayService", "getProductState finish. onRespone: ", hashMap.toString());
        return hashMap;
    }

    private void e(String str, InAppPurchaseData inAppPurchaseData, Map<String, String> map) {
        if (TextUtils.equals(str, inAppPurchaseData.getProductId())) {
            if (inAppPurchaseData.isAutoRenewing()) {
                if (inAppPurchaseData.isSubValid()) {
                    map.put(str, "3");
                    return;
                } else {
                    map.put(str, "2");
                    return;
                }
            }
            map.put(str, "1");
        }
    }
}
