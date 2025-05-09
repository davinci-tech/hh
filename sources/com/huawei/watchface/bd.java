package com.huawei.watchface;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;
import com.huawei.hms.support.api.pay.HuaweiPay;
import com.huawei.hms.support.api.pay.PayResult;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.hms.support.common.ActivityMgr;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.bb;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.ui.activity.HMSPayAgentActivity;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import defpackage.mcf;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class bd {

    /* renamed from: a, reason: collision with root package name */
    public static final SparseArray<String> f10920a;
    private static Executor b = Executors.newSingleThreadExecutor();
    private static bd c = new bd();
    private bc d;
    private BaseReq e;
    private int f = 1;
    private Status g;
    private a h;

    /* loaded from: classes9.dex */
    public interface a {
        void a();
    }

    static /* synthetic */ int b(bd bdVar) {
        int i = bdVar.f;
        bdVar.f = i - 1;
        return i;
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f10920a = sparseArray;
        sparseArray.put(0, "支付成功");
        sparseArray.put(30000, "用户取消支付");
        sparseArray.put(30005, "网络连接异常");
        sparseArray.put(30002, "支付结果查询超时");
        sparseArray.put(30001, "参数错误（包含无参）");
        sparseArray.put(30099, "返回给客户端时放入json数据错误，支付结果未知");
        sparseArray.put(30006, "其他异常错误");
        sparseArray.put(-1, "通用支付失败错误码");
        sparseArray.put(30011, "支付服务不可用，请切换华为帐号服务地");
        sparseArray.put(30012, "查询订单结果状态：订单处理中");
        sparseArray.put(30013, "查询订单结果状态：订单还未处理");
        sparseArray.put(30101, "游戏帐号不一致");
        sparseArray.put(30102, "游戏实名认证失败");
        sparseArray.put(40001, "商品ID在对应商户查询的应用中不存在");
        sparseArray.put(40002, "商品授权失败");
        sparseArray.put(40003, "商品服务器内部异常");
        sparseArray.put(40004, "商品ID在对应商户查询的应用中部分不存在");
    }

    public static bd a() {
        return c;
    }

    private bd() {
    }

    public static String b() {
        JSONObject jSONObject;
        String accountToken;
        HwLog.i("PayHelper", "getReservedInfor enter");
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = new JSONObject();
            accountToken = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getAccountToken();
        } catch (JSONException e) {
            HwLog.e("PayHelper", "getReservedInfor error === " + HwLog.printException((Exception) e));
        }
        if (TextUtils.isEmpty(accountToken)) {
            HwLog.e("PayHelper", "getInfos accountToken is null");
            return "";
        }
        jSONObject.put("accessToken", accountToken);
        jSONObject.put("countryCode", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        jSONObject2.put("accountInfo", jSONObject);
        return jSONObject2.toString();
    }

    public void a(final BaseReq baseReq, bc bcVar) {
        if (baseReq == null) {
            HwLog.i("PayHelper", "null == baseReq");
            return;
        }
        this.d = bcVar;
        this.e = baseReq;
        bb.a().a(new bb.a() { // from class: com.huawei.watchface.bd.1
            @Override // com.huawei.watchface.bb.a
            public void a() {
                HwLog.i("PayHelper", "onSuccess");
                bd.this.a(baseReq, (ResultCallback<PayResult>) null);
            }

            @Override // com.huawei.watchface.bb.a
            public void b() {
                bd.this.a(Constants.COORDINATE_FAIL_DEF, (PayResultInfo) null);
            }
        });
    }

    public void a(Activity activity, final BaseReq baseReq, bc bcVar, final bf bfVar, final ResultCallback<PayResult> resultCallback) {
        if (baseReq == null) {
            HwLog.i("PayHelper", "null == baseReq");
            return;
        }
        this.d = bcVar;
        this.e = baseReq;
        bb.a().a(activity, new bb.a() { // from class: com.huawei.watchface.bd.2
            @Override // com.huawei.watchface.bb.a
            public void a() {
                HwLog.i("PayHelper", "onSuccess");
                bd.this.a(baseReq, (ResultCallback<PayResult>) resultCallback);
            }

            @Override // com.huawei.watchface.bb.a
            public void b() {
                bd.this.a(Constants.COORDINATE_FAIL_DEF, (PayResultInfo) null);
                bfVar.onResult(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final BaseReq baseReq, final ResultCallback<PayResult> resultCallback) {
        final HuaweiApiClient b2 = bb.a().b();
        if (CommonUtils.g() >= 50000300) {
            b.execute(new Runnable() { // from class: com.huawei.watchface.bd.3
                @Override // java.lang.Runnable
                public void run() {
                    PendingResult<PayResult> addWithholdingPlan;
                    HwLog.i("PayHelper", "run");
                    BaseReq baseReq2 = baseReq;
                    if (baseReq2 instanceof PayReq) {
                        addWithholdingPlan = HuaweiPay.HuaweiPayApi.pay(b2, (PayReq) baseReq);
                    } else {
                        addWithholdingPlan = baseReq2 instanceof WithholdRequest ? HuaweiPay.HuaweiPayApi.addWithholdingPlan(b2, (WithholdRequest) baseReq) : null;
                    }
                    if (addWithholdingPlan != null) {
                        ResultCallback<PayResult> resultCallback2 = resultCallback;
                        if (resultCallback2 != null) {
                            addWithholdingPlan.setResultCallback(resultCallback2);
                        } else {
                            addWithholdingPlan.setResultCallback(new b());
                        }
                    }
                }
            });
        } else {
            HwLog.i("PayHelper", "HMS version is not supported");
        }
    }

    /* loaded from: classes9.dex */
    class b implements ResultCallback<PayResult> {
        private b() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(PayResult payResult) {
            if (payResult == null) {
                HwLog.e("PayHelper", "result is null");
                bd.this.a(-1, (PayResultInfo) null);
                return;
            }
            Status status = payResult.getStatus();
            HwLog.i("PayHelper", "status is :" + status);
            if (status == null) {
                HwLog.e("PayHelper", "status is null");
                bd.this.a(-1, (PayResultInfo) null);
                return;
            }
            int statusCode = status.getStatusCode();
            HwLog.i("PayHelper", "rstCode=" + statusCode);
            if ((statusCode == 907135006 || statusCode == 907135003) && bd.this.f > 0) {
                bd.b(bd.this);
                bd bdVar = bd.this;
                bdVar.a(bdVar.e, bd.this.d);
                return;
            }
            if (statusCode == 0) {
                Activity currentActivity = ActivityMgr.INST.getCurrentActivity();
                if (currentActivity != null) {
                    if (bd.this.g == null) {
                        try {
                            bd.this.g = status;
                            mcf.cfJ_(currentActivity, new Intent(currentActivity, (Class<?>) HMSPayAgentActivity.class));
                            return;
                        } catch (Exception e) {
                            HwLog.e("PayHelper", "start HMSPayAgentActivity error:" + HwLog.printException(e));
                            bd.this.a(-1, (PayResultInfo) null);
                            return;
                        }
                    }
                    HwLog.e("PayHelper", "has already a pay to dispose");
                    bd.this.a(-1, (PayResultInfo) null);
                    return;
                }
                HwLog.e("PayHelper", "activity is null");
                bd.this.a(-1, (PayResultInfo) null);
                return;
            }
            bd.this.a(statusCode, (PayResultInfo) null);
        }
    }

    public void a(int i, PayResultInfo payResultInfo) {
        HwLog.i("PayHelper", "pay-handler=" + this.d + ", retCode=" + i);
        if (this.d != null) {
            if (payResultInfo != null && this.e != null && TextUtils.isEmpty(payResultInfo.getRequestId())) {
                payResultInfo.setRequestId(this.e.requestId);
                i = 30000;
            }
            this.d.a(i, payResultInfo);
            this.d = null;
        }
        this.g = null;
        this.e = null;
        this.f = 1;
    }

    public Status c() {
        return this.g;
    }

    public a d() {
        return this.h;
    }

    public void setOnEnterPaymentPageListener(a aVar) {
        this.h = aVar;
    }
}
