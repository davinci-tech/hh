package com.huawei.health.tradeservice.cloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.trade.datatype.ChallengeInfo;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePolicyInfos;
import defpackage.bzs;
import defpackage.cvx;
import defpackage.cwb;
import defpackage.gkz;
import defpackage.gla;
import defpackage.gpn;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.mtj;
import defpackage.njn;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class DeviceBindRightsManager {
    private static final int DEFAULT_RESULT_CODE_FAIL = -1;
    private static final int OPEN_H5_VIEW = 1;
    private static final int RESULT_CODE_SUCCESS = 0;
    private static final String TAG = "DeviceBindRightsManager";
    private static final long TIME_TWENTY_SECOND = 20000;
    private static DeviceHandler mHandler = new DeviceHandler(Looper.getMainLooper());

    public static void queryBenefitInfo(final int i, final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.c(TAG, "queryBenefitInfo browseMode.");
            iBaseResponseCallback.d(-1, "queryBenefitInfo browseMode");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceBindRightsManager.queryBenefitInfo(i, str, iBaseResponseCallback);
                }
            });
        } else {
            requestBenefitInfo(i, str, iBaseResponseCallback);
        }
    }

    private static void requestBenefitInfo(int i, String str, final IBaseResponseCallback iBaseResponseCallback) {
        TradeServiceCloudFactory tradeServiceCloudFactory = new TradeServiceCloudFactory(BaseApplication.getContext());
        String str2 = GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/repeatresource/benefit-info";
        HashMap hashMap = new HashMap();
        hashMap.put("resourceType", String.valueOf(i));
        hashMap.put("resourceId", str);
        lqi.d().c(str2, tradeServiceCloudFactory.getHeaders(), hashMap, BenefitInfoRsp.class, new ResultCallback<BenefitInfoRsp>() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(BenefitInfoRsp benefitInfoRsp) {
                if (benefitInfoRsp == null) {
                    IBaseResponseCallback.this.d(-1, "queryBenefitInfo is null.");
                } else if (benefitInfoRsp.getResultCode() == 0) {
                    IBaseResponseCallback.this.d(0, benefitInfoRsp.getBenefitResult());
                } else {
                    LogUtil.c(DeviceBindRightsManager.TAG, "queryBenefitInfo data getResultCode: ", Integer.valueOf(benefitInfoRsp.getResultCode()));
                    IBaseResponseCallback.this.d(benefitInfoRsp.getResultCode(), Integer.valueOf(benefitInfoRsp.getResultCode()));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(DeviceBindRightsManager.TAG, "queryBenefitInfo onFailure. throwable: ", th.toString());
                IBaseResponseCallback.this.d(-1, th.toString());
            }
        });
    }

    private static boolean isParametersValid(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.a(TAG, "isParametersValid callback is null.");
            return false;
        }
        if (!gpn.d() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            LogUtil.a(TAG, "isParametersValid GP or oversea or kit account.");
            iBaseResponseCallback.d(-1, "GP or oversea or kit account.");
            return false;
        }
        if (deviceBenefitQueryParam == null) {
            iBaseResponseCallback.d(-1, "no device.");
            LogUtil.a(TAG, "isParametersValid queryParam is null.");
            return false;
        }
        if (deviceBenefitQueryParam.getBenefitType() == null) {
            LogUtil.a(TAG, "getDeviceBenefits benefitType error");
            iBaseResponseCallback.d(-1, "benefitType error");
            return false;
        }
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return true;
        }
        LogUtil.c(TAG, "browseMode.");
        iBaseResponseCallback.d(-1, "browseMode");
        return false;
    }

    public static void getDeviceBenefits(DeviceBenefitQueryParam deviceBenefitQueryParam, IBaseResponseCallback iBaseResponseCallback) {
        if (!isParametersValid(deviceBenefitQueryParam, iBaseResponseCallback)) {
            LogUtil.a(TAG, "getDeviceBenefits Parameters invalid.");
        } else {
            getInboxAndPerfPurchaseBenefit(deviceBenefitQueryParam, iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getInboxAndPerfPurchaseBenefit(final DeviceBenefitQueryParam deviceBenefitQueryParam, final IBaseResponseCallback iBaseResponseCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceBindRightsManager.getInboxAndPerfPurchaseBenefit(DeviceBenefitQueryParam.this, iBaseResponseCallback);
                }
            });
        } else if (!isParametersValid(deviceBenefitQueryParam, iBaseResponseCallback)) {
            LogUtil.a(TAG, "getDeviceInboxInfo Parameters invalid.");
        } else {
            requestBenefit(deviceBenefitQueryParam, iBaseResponseCallback);
        }
    }

    private static void requestBenefit(final DeviceBenefitQueryParam deviceBenefitQueryParam, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c(TAG, "getDeviceInboxInfo enter");
        TradeServiceCloudFactory tradeServiceCloudFactory = new TradeServiceCloudFactory(BaseApplication.getContext());
        Map<String, String> commonParams = getCommonParams(deviceBenefitQueryParam);
        if (commonParams.isEmpty()) {
            LogUtil.a(TAG, "params is empty.");
            iBaseResponseCallback.d(-1, "no device.");
            return;
        }
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v2/device-policies", tradeServiceCloudFactory.getHeaders(), lql.b(commonParams), DeviceInboxAndPerfRsp.class, new ResultCallback<DeviceInboxAndPerfRsp>() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.2
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(DeviceInboxAndPerfRsp deviceInboxAndPerfRsp) {
                if (deviceInboxAndPerfRsp == null) {
                    IBaseResponseCallback.this.d(-1, "getDeviceInboxInfo is null.");
                    return;
                }
                if (deviceInboxAndPerfRsp.getResultCode() == 0) {
                    DevicePolicyInfos devicePolicyInfos = deviceInboxAndPerfRsp.getDevicePolicyInfos();
                    if (devicePolicyInfos == null) {
                        LogUtil.a(DeviceBindRightsManager.TAG, "DevicePolicyInfos is null.");
                        IBaseResponseCallback.this.d(-1, "devicePolicyInfos is null.");
                        return;
                    }
                    DeviceBenefits deviceBenefits = new DeviceBenefits();
                    deviceBenefits.setInboxInfos(devicePolicyInfos.getDeviceInboxInfoList());
                    deviceBenefits.setPerfPurchaseInfos(devicePolicyInfos.getDevicePerfPurchaseInfoList());
                    deviceBenefits.setDeviceSn(deviceBenefitQueryParam.getDeviceSn());
                    deviceBenefits.setDeviceType(deviceBenefitQueryParam.getDeviceType());
                    DeviceBindRightsManager.printBenefitInfo(devicePolicyInfos);
                    IBaseResponseCallback.this.d(0, deviceBenefits);
                    return;
                }
                LogUtil.c(DeviceBindRightsManager.TAG, "getDeviceInboxInfo data getResultDesc: ", deviceInboxAndPerfRsp.getResultDesc());
                IBaseResponseCallback.this.d(deviceInboxAndPerfRsp.getResultCode(), deviceInboxAndPerfRsp.getResultDesc());
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(DeviceBindRightsManager.TAG, "getDeviceInboxInfo onFailure. throwable: ", th.toString());
                IBaseResponseCallback.this.d(-1, th.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printBenefitInfo(DevicePolicyInfos devicePolicyInfos) {
        if (devicePolicyInfos == null) {
            return;
        }
        if (koq.c(devicePolicyInfos.getDeviceInboxInfoList())) {
            LogUtil.c(TAG, "getDeviceInboxInfoList size = ", Integer.valueOf(devicePolicyInfos.getDeviceInboxInfoList().size()));
        }
        if (koq.c(devicePolicyInfos.getDevicePerfPurchaseInfoList())) {
            LogUtil.c(TAG, "getDevicePerfPurchaseInfoList size = ", Integer.valueOf(devicePolicyInfos.getDevicePerfPurchaseInfoList().size()));
        }
    }

    public static void bindDeviceBenefit(DeviceBenefitQueryParam deviceBenefitQueryParam, final IBaseResponseCallback iBaseResponseCallback) {
        if (!isParametersValid(deviceBenefitQueryParam, iBaseResponseCallback)) {
            LogUtil.a(TAG, "bindDeviceBenefit Parameters invalid.");
            return;
        }
        LogUtil.c(TAG, "bindDeviceBenefit enter");
        Map<String, String> commonParams = getCommonParams(deviceBenefitQueryParam);
        if (commonParams.isEmpty()) {
            iBaseResponseCallback.d(-1, "no device.");
            return;
        }
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/device-benefit-bind", new TradeServiceCloudFactory(BaseApplication.getContext()).getHeaders(), lql.b(commonParams), BaseRsp.class, new ResultCallback<BaseRsp>() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.3
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(BaseRsp baseRsp) {
                if (baseRsp == null) {
                    IBaseResponseCallback.this.d(-1, "DevicePerfPurchaseRsp is null.");
                } else {
                    LogUtil.c(DeviceBindRightsManager.TAG, "bindDeviceBenefit success. data: ", Integer.valueOf(baseRsp.getResultCode()));
                    IBaseResponseCallback.this.d(baseRsp.getResultCode(), baseRsp.getResultDesc());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(DeviceBindRightsManager.TAG, "bindDeviceBenefit onFailure. throwable: ", th.toString());
                IBaseResponseCallback.this.d(-1, th.toString());
            }
        });
    }

    private static Map<String, String> getCommonParams(DeviceBenefitQueryParam deviceBenefitQueryParam) {
        if (deviceBenefitQueryParam == null) {
            LogUtil.a(TAG, "queryParam == null");
            return Collections.emptyMap();
        }
        String deviceType = deviceBenefitQueryParam.getDeviceType();
        String deviceSn = deviceBenefitQueryParam.getDeviceSn();
        String salt = deviceBenefitQueryParam.getSalt();
        String deviceNumber = deviceBenefitQueryParam.getDeviceNumber();
        if (TextUtils.isEmpty(deviceType)) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("territory", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(gla.d()));
        hashMap.put("locale", mtj.e(null));
        hashMap.put("deviceType", deviceType);
        hashMap.put("clientVersion", CardManager.getClientVersion(BaseApplication.getContext()));
        if (!TextUtils.isEmpty(deviceSn)) {
            hashMap.put("deviceSN", deviceSn);
        }
        if (!TextUtils.isEmpty(deviceNumber)) {
            hashMap.put("deviceNumber", cvx.e(deviceNumber));
        }
        if (!TextUtils.isEmpty(salt)) {
            hashMap.put("salt", cvx.e(salt));
        }
        int deviceCategory = deviceBenefitQueryParam.getDeviceCategory();
        if (deviceCategory != 0) {
            hashMap.put("deviceCategory", String.valueOf(deviceCategory));
        }
        LogUtil.c(TAG, "queryParam = ", hashMap.toString());
        return hashMap;
    }

    public static void getDeviceBenefitsBatch(final List<DeviceBenefitQueryParam> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (!koq.b(list)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceBindRightsManager.lambda$getDeviceBenefitsBatch$2(list, iBaseResponseCallback);
                }
            });
        } else if (iBaseResponseCallback != null) {
            LogUtil.a(TAG, "getDeviceBenefitsBatch queryParam is null.");
            iBaseResponseCallback.d(-1, "no device.");
        }
    }

    static /* synthetic */ void lambda$getDeviceBenefitsBatch$2(List list, IBaseResponseCallback iBaseResponseCallback) {
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            getDeviceBenefits((DeviceBenefitQueryParam) it.next(), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj instanceof DeviceBenefits) {
                        copyOnWriteArrayList.add((DeviceBenefits) obj);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(TIME_TWENTY_SECOND, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.e(TAG, "getDeviceBenefitsBatch InterruptedException");
        }
        if (iBaseResponseCallback != null) {
            if (koq.b(copyOnWriteArrayList)) {
                LogUtil.c(TAG, "deviceBenefitsList is empty.");
                iBaseResponseCallback.d(-1, "No device has benefit.");
            } else {
                iBaseResponseCallback.d(0, copyOnWriteArrayList);
            }
        }
    }

    public static void enableBenefitAutoActivation(final DeviceBenefitQueryParam deviceBenefitQueryParam) {
        getDeviceBenefits(deviceBenefitQueryParam, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof DeviceBenefits) {
                    List<DeviceInboxInfo> inboxInfos = ((DeviceBenefits) obj).getInboxInfos();
                    if (koq.b(inboxInfos)) {
                        LogUtil.c(DeviceBindRightsManager.TAG, "deviceInboxInfoList is empty");
                        return;
                    }
                    for (DeviceInboxInfo deviceInboxInfo : inboxInfos) {
                        if (deviceInboxInfo.isBenefitInfoValid() && deviceInboxInfo.isAutoActivate()) {
                            LogUtil.c(DeviceBindRightsManager.TAG, "has auto activated inbox.");
                            DeviceBindRightsManager.useWhichValidType(DeviceBenefitQueryParam.this, deviceInboxInfo);
                            return;
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void useWhichValidType(final DeviceBenefitQueryParam deviceBenefitQueryParam, final DeviceInboxInfo deviceInboxInfo) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceBindRightsManager.useWhichValidType(DeviceBenefitQueryParam.this, deviceInboxInfo);
                }
            });
            return;
        }
        Map<String, String> commonParams = getCommonParams(deviceBenefitQueryParam);
        if (commonParams.isEmpty()) {
            LogUtil.a(TAG, "bindAutoActivateDevice params is empty.");
        } else {
            dealWithValidType(deviceBenefitQueryParam, deviceInboxInfo, commonParams);
        }
    }

    private static void dealWithValidType(final DeviceBenefitQueryParam deviceBenefitQueryParam, DeviceInboxInfo deviceInboxInfo, final Map<String, String> map) {
        if (deviceInboxInfo.getValidateType() == 2) {
            getChallenge(new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.c(DeviceBindRightsManager.TAG, "getChallenge onResponse errorCode: ", Integer.valueOf(i));
                    if (i == 0) {
                        LogUtil.c(DeviceBindRightsManager.TAG, "getChallenge onResponse objData: ", obj.toString());
                        cwb.d().b(DeviceBenefitQueryParam.this.getDeviceInfo(), new int[]{1}, ((ChallengeInfo) obj).getChallenge(), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.6.1
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i2, Object obj2) {
                                LogUtil.c(DeviceBindRightsManager.TAG, "sendGetDeviceCertCommand onResponse errorCode: ", Integer.valueOf(i2));
                                if (obj2 instanceof String) {
                                    String str = (String) obj2;
                                    if (TextUtils.isEmpty(str)) {
                                        return;
                                    }
                                    LogUtil.c(DeviceBindRightsManager.TAG, "certChain = ", str);
                                    map.put("certChain", str);
                                    DeviceBindRightsManager.enableAutoActivation(map);
                                }
                            }
                        });
                    }
                }
            });
        } else {
            enableAutoActivation(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void enableAutoActivation(Map<String, String> map) {
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/inbox-device-bind", new TradeServiceCloudFactory(BaseApplication.getContext()).getHeaders(), lql.b(map), BaseRsp.class, new ResultCallback<BaseRsp>() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.7
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(BaseRsp baseRsp) {
                LogUtil.c(DeviceBindRightsManager.TAG, "Bind inbox info data getResultDesc: ", baseRsp.getResultDesc(), "; getResultCode: ", Integer.valueOf(baseRsp.getResultCode()));
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(DeviceBindRightsManager.TAG, "Bind inbox info onFailure. throwable: ", th.toString());
            }
        });
    }

    public static void activateDeviceBenefitPage(final DeviceBenefitQueryParam deviceBenefitQueryParam) {
        if (deviceBenefitQueryParam == null) {
            ReleaseLogUtil.a(TAG, "activateDeviceBenefitPage queryParam is null.");
            return;
        }
        ReleaseLogUtil.b(TAG, "start activateDeviceBenefitPage.");
        getDeviceBenefits(deviceBenefitQueryParam, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str;
                if (obj instanceof DeviceBenefits) {
                    DeviceBenefits deviceBenefits = (DeviceBenefits) obj;
                    boolean a2 = njn.a(deviceBenefits.getInboxInfos());
                    boolean d = njn.d(deviceBenefits.getPerfPurchaseInfos());
                    LogUtil.c(DeviceBindRightsManager.TAG, "isValidInboxInfo: ", Boolean.valueOf(a2), "; isValidPerfPurchaseInfo: ", Boolean.valueOf(d));
                    if (gkz.e(deviceBenefits.getInboxInfos()) != null) {
                        str = d ? "#/ExclusiveGuard?isMultiType=true&from=1" : "#/ExclusiveGuard?from=1";
                    } else if (a2 && d) {
                        gpn.a(true, deviceBenefits.getDeviceSn());
                        gpn.a(false, deviceBenefits.getDeviceSn());
                        str = "#/VipActivate?isMultiType=true&from=1";
                    } else if (a2) {
                        gpn.a(true, deviceBenefits.getDeviceSn());
                        str = "#/VipActivate?from=1";
                    } else if (d) {
                        gpn.a(false, deviceBenefits.getDeviceSn());
                        str = "#/PrivilegeRedeem?from=1";
                    } else {
                        LogUtil.a(DeviceBindRightsManager.TAG, "NO INFO VALID.");
                        str = "";
                    }
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    String str2 = str + "&deviceSN=" + DeviceBenefitQueryParam.this.getDeviceSn();
                    gkz.e(deviceBenefits);
                    DeviceBindRightsManager.sendMsg(1, str2);
                }
            }
        });
        bindDeviceBenefit(deviceBenefitQueryParam, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.c(DeviceBindRightsManager.TAG, "bindDeviceBenefit errorCode: ", Integer.valueOf(i));
            }
        });
    }

    public static void checkDeviceBenefitMessage(DeviceBenefitQueryParam deviceBenefitQueryParam) {
        if (deviceBenefitQueryParam == null) {
            LogUtil.a(TAG, "checkDeviceMessage queryParam is null.");
        } else {
            LogUtil.c(TAG, "start checkDeviceMessage.");
            getDeviceBenefits(deviceBenefitQueryParam, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj instanceof DeviceBenefits) {
                        LogUtil.c(DeviceBindRightsManager.TAG, "checkDeviceMessage start message build.");
                        gkz.e((DeviceBenefits) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void openH5(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "DEVICE_BIND_INBOX_KEY", String.valueOf(1), new StorageParams());
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(str);
        Context activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.a(TAG, "getActivity() is null.");
            activity = BaseApplication.getContext();
        }
        LogUtil.c(TAG, "open H5, path = ", str);
        bzs.e().loadH5ProApp(activity, "com.huawei.health.h5.vip", builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void sendMsg(int i, T t) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        mHandler.sendMessage(obtain);
    }

    static class DeviceHandler extends Handler {
        DeviceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                if (message.obj instanceof String) {
                    DeviceBindRightsManager.openH5((String) message.obj);
                    return;
                }
                return;
            }
            LogUtil.c(DeviceBindRightsManager.TAG, "NO MESSAGE.");
        }
    }

    public static void getChallenge(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.c(TAG, "getChallenge callBack is null.");
            return;
        }
        lqi.d().c(GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/device-cert-challenge", new TradeServiceCloudFactory(BaseApplication.getContext()).getHeaders(), new HashMap(), DeviceCertChallengeRsp.class, new ResultCallback<DeviceCertChallengeRsp>() { // from class: com.huawei.health.tradeservice.cloud.DeviceBindRightsManager.11
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(DeviceCertChallengeRsp deviceCertChallengeRsp) {
                if (deviceCertChallengeRsp == null) {
                    IBaseResponseCallback.this.d(-1, "getDeviceInboxInfo is null.");
                } else if (deviceCertChallengeRsp.getResultCode() == 0) {
                    IBaseResponseCallback.this.d(deviceCertChallengeRsp.getResultCode(), deviceCertChallengeRsp.getChallengeInfo());
                } else {
                    LogUtil.c(DeviceBindRightsManager.TAG, "getChallenge data getResultDesc: ", deviceCertChallengeRsp.getResultDesc());
                    IBaseResponseCallback.this.d(deviceCertChallengeRsp.getResultCode(), deviceCertChallengeRsp.getResultDesc());
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(DeviceBindRightsManager.TAG, "getChallenge onFailure. throwable: ", th.toString());
                IBaseResponseCallback.this.d(-1, th.toString());
            }
        });
    }
}
