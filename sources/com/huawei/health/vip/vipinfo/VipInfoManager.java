package com.huawei.health.vip.vipinfo;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.tradeservice.cloud.TradeServiceCloudFactory;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.bed;
import defpackage.eaa;
import defpackage.eil;
import defpackage.gpn;
import defpackage.gps;
import defpackage.gpt;
import defpackage.ixx;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class VipInfoManager {
    private static final String CLIENT_TYPE = "clientType";
    private static final String COUNTRY = "country";
    private static final String LANGUAGE = "language";
    private static final String LIMIT = "limit";
    private static final String OFF_TIME = "cutOffTime";
    private static final String OP_KEY_RESULT_CODE = "resultCode";
    private static final String TAG = "VipInfoManager";
    private static final String TAG_RELEASE = "R_VipInfoManager";
    private static volatile VipInfoManager sInstance;
    private VipInfoApi mVipInfoApi = (VipInfoApi) lqi.d().b(VipInfoApi.class);
    private ParamsFactory mParamsFactory = new TradeServiceCloudFactory(BaseApplication.getContext());
    private String mCountryCode = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);

    private VipInfoManager() {
    }

    public static VipInfoManager getInstance() {
        if (sInstance == null) {
            synchronized (VipInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new VipInfoManager();
                }
            }
        }
        return sInstance;
    }

    public void getVipInfo(final IBaseResponseCallback iBaseResponseCallback) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            getVipInfoFromSp(iBaseResponseCallback);
        } else {
            if (isVipNotEnabled(iBaseResponseCallback)) {
                return;
            }
            ThreadPoolManager.d().submit(new Callable() { // from class: com.huawei.health.vip.vipinfo.VipInfoManager$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return VipInfoManager.this.m527lambda$getVipInfo$0$comhuaweihealthvipvipinfoVipInfoManager(iBaseResponseCallback);
                }
            });
        }
    }

    /* renamed from: lambda$getVipInfo$0$com-huawei-health-vip-vipinfo-VipInfoManager, reason: not valid java name */
    /* synthetic */ Void m527lambda$getVipInfo$0$comhuaweihealthvipvipinfoVipInfoManager(IBaseResponseCallback iBaseResponseCallback) throws Exception {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        VipInfoRsp vipMemberInfo = getVipMemberInfo();
        if (vipMemberInfo == null) {
            LogUtil.h(TAG, "vipInfoRsp is null ");
            linkedHashMap.put("resultCode", String.valueOf(-1));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_VIP_INFO_80070002.value(), linkedHashMap);
            getVipInfoFromSp(iBaseResponseCallback);
            return null;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "vipInfoRsp.getResultCode(): ", vipMemberInfo.getResultCode());
        if (vipMemberInfo.getResultCode().intValue() != 0) {
            iBaseResponseCallback.d(-1, "error resultCode=" + vipMemberInfo.getResultCode());
            return null;
        }
        linkedHashMap.put("resultCode", String.valueOf(vipMemberInfo.getResultCode()));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_VIP_INFO_80070002.value(), linkedHashMap);
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "USER_VIP_INFO_KEY", moj.e(vipMemberInfo.getUserMemberInfo()), new StorageParams());
        iBaseResponseCallback.d(vipMemberInfo.getResultCode().intValue(), vipMemberInfo.getUserMemberInfo());
        setVipBiEvent(vipMemberInfo);
        LogUtil.a(TAG, "getVipInfo end");
        return null;
    }

    private void setVipBiEvent(VipInfoRsp vipInfoRsp) {
        if (vipInfoRsp.getResultCode().intValue() == 0) {
            setVipBiInfo(vipInfoRsp.getUserMemberInfo());
        }
    }

    private void setVipBiInfo(UserMemberInfo userMemberInfo) {
        String str;
        if (userMemberInfo.getMemberFlag() == 1) {
            str = gpn.d(userMemberInfo) ? "-1" : "1";
        } else {
            str = "0";
        }
        setBiInfo(str, "0");
    }

    private void setBiInfo(String str, String str2) {
        Context e = com.huawei.haf.application.BaseApplication.e();
        ixx.d().a(str, str2);
        SharedPreferenceManager.e(e, Integer.toString(10000), "MAIN_VIP_KEY", str, new StorageParams());
        SharedPreferenceManager.e(e, Integer.toString(10000), "SHARED_VIP_KEY", str2, new StorageParams());
    }

    public VipInfoRsp getVipMemberInfo() {
        Response<VipInfoRsp> execute;
        VipInfoReq vipInfoReq = new VipInfoReq();
        try {
            HashMap hashMap = new HashMap(10);
            hashMap.put("clientType", eil.a());
            hashMap.put("country", this.mCountryCode);
            execute = this.mVipInfoApi.getVipMemberInfo(vipInfoReq.getVipUserInfoUrl(), this.mParamsFactory.getHeaders(), hashMap).execute();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipInfo fail, ", e.getMessage());
        }
        if (execute == null) {
            LogUtil.h(TAG, "response is null");
            return null;
        }
        if (!execute.isOK()) {
            LogUtil.h(TAG, "response result == ", Integer.valueOf(execute.getCode()));
            return null;
        }
        LogUtil.a(TAG, "response is OK.");
        return execute.getBody();
    }

    public void getVipMessage(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (isVipNotEnabled(iBaseResponseCallback)) {
            return;
        }
        ThreadPoolManager.d().submit(new Callable() { // from class: com.huawei.health.vip.vipinfo.VipInfoManager$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VipInfoManager.this.m528x8463fe1f(str, iBaseResponseCallback);
            }
        });
    }

    /* renamed from: lambda$getVipMessage$1$com-huawei-health-vip-vipinfo-VipInfoManager, reason: not valid java name */
    /* synthetic */ Void m528x8463fe1f(String str, IBaseResponseCallback iBaseResponseCallback) throws Exception {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "msgId is empty");
            iBaseResponseCallback.d(-1, VastAttribute.ERROR);
            return null;
        }
        VipMessageRsp vipMessageInfo = getVipMessageInfo(str);
        if (vipMessageInfo == null) {
            LogUtil.h(TAG, "vipMessageRsp is null ");
            iBaseResponseCallback.d(-1, VastAttribute.ERROR);
            return null;
        }
        LogUtil.a(TAG, "vipMessageRsp.getResultCode(): ", vipMessageInfo.getResultCode());
        iBaseResponseCallback.d(vipMessageInfo.getResultCode().intValue(), vipMessageInfo.getMessage());
        LogUtil.a(TAG, "vipMessageRsp end");
        return null;
    }

    private VipMessageRsp getVipMessageInfo(String str) {
        Response<VipMessageRsp> execute;
        VipMessageReq vipMessageReq = new VipMessageReq();
        try {
            execute = this.mVipInfoApi.getVipMessage(vipMessageReq.getUrl() + str, this.mParamsFactory.getHeaders()).execute();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipMessageInfo fail, ", e.getMessage());
        }
        if (execute == null) {
            LogUtil.h(TAG, "response = null");
            return null;
        }
        if (!execute.isOK()) {
            LogUtil.h(TAG, "response code == ", Integer.valueOf(execute.getCode()));
            return null;
        }
        LogUtil.a(TAG, "response is OK.");
        return execute.getBody();
    }

    public void getVipMessageList(final long j, final long j2, final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        if (isVipNotEnabled(iBaseResponseCallback)) {
            return;
        }
        ThreadPoolManager.d().submit(new Callable() { // from class: com.huawei.health.vip.vipinfo.VipInfoManager$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VipInfoManager.this.m529x5b4274c0(j, j2, z, iBaseResponseCallback);
            }
        });
    }

    /* renamed from: lambda$getVipMessageList$2$com-huawei-health-vip-vipinfo-VipInfoManager, reason: not valid java name */
    /* synthetic */ Void m529x5b4274c0(long j, long j2, boolean z, IBaseResponseCallback iBaseResponseCallback) throws Exception {
        VipMessageListRsp vipMessageList = getVipMessageList(j, j2, z);
        if (vipMessageList == null) {
            LogUtil.h(TAG, "vipMessageListRsp is null ");
            iBaseResponseCallback.d(-1, VastAttribute.ERROR);
            return null;
        }
        LogUtil.a(TAG, "vipMessageListRsp.getResultCode(): ", vipMessageList.getResultCode());
        iBaseResponseCallback.d(vipMessageList.getResultCode().intValue(), vipMessageList.getMessages());
        LogUtil.a(TAG, "vipMessageListRsp end");
        return null;
    }

    private VipMessageListRsp getVipMessageList(long j, long j2, boolean z) {
        Response<VipMessageListRsp> execute;
        VipMessageListReq vipMessageListReq = new VipMessageListReq();
        try {
            HashMap hashMap = new HashMap(10);
            hashMap.put(OFF_TIME, String.valueOf(j));
            hashMap.put("limit", String.valueOf(j2));
            execute = this.mVipInfoApi.getVipMessageList(z ? VipMessageListReq.getVipBenefitPath() : vipMessageListReq.getUrl(), this.mParamsFactory.getHeaders(), hashMap).execute();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipMessageList fail, ", e.getMessage());
        }
        if (execute == null) {
            LogUtil.h(TAG, "response = null");
            return null;
        }
        if (execute.isOK()) {
            LogUtil.a(TAG, "getVipMessageList response is OK.", execute.toString());
            return execute.getBody();
        }
        LogUtil.h(TAG, "getVipMessageList response code == ", Integer.valueOf(execute.getCode()));
        return null;
    }

    public void setVipMessageRead(final List<String> list) {
        ThreadPoolManager.d().submit(new Callable() { // from class: com.huawei.health.vip.vipinfo.VipInfoManager$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VipInfoManager.this.m530x3b8b58db(list);
            }
        });
    }

    /* renamed from: lambda$setVipMessageRead$3$com-huawei-health-vip-vipinfo-VipInfoManager, reason: not valid java name */
    /* synthetic */ Void m530x3b8b58db(List list) throws Exception {
        if (koq.b(list)) {
            LogUtil.h(TAG, "messageIds is empty");
            return null;
        }
        VipMessageReadRsp vipMessageReadRsp = getVipMessageReadRsp(list);
        if (vipMessageReadRsp == null) {
            LogUtil.h(TAG, "vipMessageListRsp is null ");
            return null;
        }
        LogUtil.a(TAG, "vipMessageListRsp.getResultCode(): ", vipMessageReadRsp.getResultCode());
        return null;
    }

    private VipMessageReadRsp getVipMessageReadRsp(List<String> list) {
        Response<VipMessageReadRsp> execute;
        VipMessageReadReq vipMessageReadReq = new VipMessageReadReq();
        try {
            LogUtil.a(TAG, "url:", vipMessageReadReq.getUrl());
            vipMessageReadReq.setMessageIds(list);
            execute = this.mVipInfoApi.setVipMessageRead(vipMessageReadReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(vipMessageReadReq)).execute();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipMessageReadRsp fail, ", e.getMessage());
        }
        if (execute == null) {
            LogUtil.h(TAG, "response = null");
            return null;
        }
        if (!execute.isOK()) {
            LogUtil.h(TAG, "response result == ", Integer.valueOf(execute.getCode()));
            return null;
        }
        LogUtil.a(TAG, "response is OK.");
        return execute.getBody();
    }

    private void getVipInfoFromSp(IBaseResponseCallback iBaseResponseCallback) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "USER_VIP_INFO_KEY");
        if (TextUtils.isEmpty(b)) {
            iBaseResponseCallback.d(-1, VastAttribute.ERROR);
        } else {
            iBaseResponseCallback.d(0, (UserMemberInfo) moj.e(b, UserMemberInfo.class));
        }
    }

    public void getVipTransferBenefits(IBaseResponseCallback iBaseResponseCallback) {
        HashMap hashMap;
        Map<String, String> headers;
        if (isVipNotEnabled(iBaseResponseCallback)) {
            return;
        }
        VipInfoReq vipInfoReq = new VipInfoReq();
        try {
            hashMap = new HashMap(10);
            hashMap.put("clientType", eil.a());
            hashMap.put("country", this.mCountryCode);
            hashMap.put("language", bed.b());
            headers = this.mParamsFactory.getHeaders();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipInfo fail, ", e.getMessage());
        }
        if (headers != null && !TextUtils.isEmpty(headers.get(CloudParamKeys.X_TOKEN))) {
            Response<gps> execute = this.mVipInfoApi.getTransferBenefitsList(vipInfoReq.getVipTransferBenefitsUrl(), headers, hashMap).execute();
            if (execute == null) {
                LogUtil.h(TAG, "response is null");
                iBaseResponseCallback.d(-1, null);
                return;
            } else if (!execute.isOK()) {
                LogUtil.h(TAG, "response result == ", Integer.valueOf(execute.getCode()));
                iBaseResponseCallback.d(-1, null);
                return;
            } else {
                LogUtil.a(TAG, "response is OK.");
                iBaseResponseCallback.d(0, execute.getBody());
                return;
            }
        }
        LogUtil.h(TAG, "x-token is null");
        iBaseResponseCallback.d(-1, null);
    }

    public void getVipType(IBaseResponseCallback iBaseResponseCallback) {
        HashMap hashMap;
        Map<String, String> headers;
        if (isVipNotEnabled(iBaseResponseCallback)) {
            return;
        }
        VipInfoReq vipInfoReq = new VipInfoReq();
        try {
            hashMap = new HashMap(10);
            hashMap.put("clientType", eil.a());
            hashMap.put("country", this.mCountryCode);
            hashMap.put("language", bed.b());
            hashMap.put("clientVersion", eaa.c(BaseApplication.getContext()));
            headers = this.mParamsFactory.getHeaders();
        } catch (IOException e) {
            LogUtil.b(TAG, "getVipInfo fail, ", e.getMessage());
        }
        if (headers != null && !TextUtils.isEmpty(headers.get(CloudParamKeys.X_TOKEN))) {
            Response<gpt> execute = this.mVipInfoApi.getVipMemberType(vipInfoReq.getVipTypeUrl(), headers, hashMap).execute();
            if (execute == null) {
                LogUtil.h(TAG, "response is null");
                iBaseResponseCallback.d(-1, null);
                return;
            } else if (!execute.isOK()) {
                LogUtil.h(TAG, "response result == ", Integer.valueOf(execute.getCode()));
                iBaseResponseCallback.d(-1, null);
                return;
            } else {
                LogUtil.a(TAG, "response is OK.");
                iBaseResponseCallback.d(0, execute.getBody());
                return;
            }
        }
        LogUtil.h(TAG, "x-token is null");
        iBaseResponseCallback.d(-1, null);
    }

    private boolean isVipNotEnabled(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "isVipNotEnabled callback is null.");
            return true;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.h(TAG, "isBrowseMode.");
            iBaseResponseCallback.d(-1, "BrowseMode.");
            return true;
        }
        if (CommonUtil.bu()) {
            iBaseResponseCallback.d(-1, "GP or StoreDemo version.");
            return true;
        }
        if (gpn.d()) {
            return false;
        }
        LogUtil.h(TAG, "VipAbilityEnabled false.");
        iBaseResponseCallback.d(-1, "vip ability not enabled.");
        return true;
    }
}
