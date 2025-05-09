package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.datatype.DataDeviceIntelligentInfo;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.model.intelligent.DevInfo;
import com.huawei.hwcloudmodel.model.intelligent.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataRequest;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataResponse;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class jgy {
    private static final Object d = new Object();
    private static jgy e;

    /* renamed from: a, reason: collision with root package name */
    private ExecutorService f13836a = Executors.newCachedThreadPool();
    private final jbs b;
    private jqi c;

    private jgy(Context context) {
        LogUtil.a("UIDV_FitnessCloud", "RecommendCloud");
        jfq.c();
        this.b = jbs.a(context);
        this.c = jqi.a();
    }

    public static jgy e(Context context) {
        jgy jgyVar;
        LogUtil.a("UIDV_FitnessCloud", "getInstance");
        synchronized (d) {
            if (e == null) {
                e = new jgy(context);
            }
            jgyVar = e;
        }
        return jgyVar;
    }

    public void d(final FitnessCloudCallBack fitnessCloudCallBack) {
        if (fitnessCloudCallBack == null) {
            return;
        }
        final DeviceInfo a2 = jpt.a("UIDV_FitnessCloud");
        if (a2 == null) {
            fitnessCloudCallBack.onResponce(null);
        } else {
            this.f13836a.execute(new Runnable() { // from class: jgy.2
                @Override // java.lang.Runnable
                public void run() {
                    int o = jfu.c(a2.getProductType()).o();
                    if (!TextUtils.isEmpty(a2.getDeviceModel()) && a2.getDeviceModel().toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
                        o = 112;
                    }
                    int a3 = jpp.a(a2.getHiLinkDeviceId());
                    if (a3 != -1) {
                        LogUtil.a("UIDV_FitnessCloud", "startDeviceLinkage getDeviceProductIdByHiLinId is ", Integer.valueOf(a3));
                        o = a3;
                    }
                    BindDeviceReq bindDeviceReq = new BindDeviceReq();
                    bindDeviceReq.setProductId(Integer.valueOf(o));
                    bindDeviceReq.setUniqueId(jgy.this.c(a2));
                    bindDeviceReq.setName(a2.getDeviceName());
                    ReleaseLogUtil.e("R_UIDV_FitnessCloud", "startDeviceLinkage BindDeviceReq is:", bindDeviceReq);
                    BindDeviceRsp b = jgy.this.b.b(bindDeviceReq);
                    if (b != null) {
                        jgy jgyVar = jgy.this;
                        jgyVar.d(b, jgyVar.c(a2), fitnessCloudCallBack);
                    } else {
                        fitnessCloudCallBack.onResponce(null);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(DeviceInfo deviceInfo) {
        if (deviceInfo.getProductType() >= 34 && !Utils.o()) {
            return deviceInfo.getSecurityUuid() + "#ANDROID21";
        }
        return deviceInfo.getUuid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final BindDeviceRsp bindDeviceRsp, final String str, final FitnessCloudCallBack fitnessCloudCallBack) {
        jot.a().a(new IBaseResponseCallback() { // from class: jgy.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    DataDeviceInfo dataDeviceInfo = obj instanceof DataDeviceInfo ? (DataDeviceInfo) obj : null;
                    if (dataDeviceInfo == null) {
                        LogUtil.h("UIDV_FitnessCloud", "dataInfo is null");
                        return;
                    }
                    final StartDeviceLinkageRequest startDeviceLinkageRequest = new StartDeviceLinkageRequest();
                    startDeviceLinkageRequest.setDeviceCode(String.valueOf(bindDeviceRsp.getDeviceCode()));
                    final DevInfo devInfo = new DevInfo();
                    devInfo.setSn(str);
                    if (jfu.h(dataDeviceInfo.getDeviceType())) {
                        devInfo.setDevType("06E");
                    } else {
                        devInfo.setDevType("06D");
                    }
                    devInfo.setModel(dataDeviceInfo.getDeviceModel());
                    jog.c().c(new IBaseResponseCallback() { // from class: jgy.5.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            if (i2 == 0 && obj2 != null) {
                                jgy.this.e(obj2, startDeviceLinkageRequest, devInfo, fitnessCloudCallBack);
                            } else {
                                fitnessCloudCallBack.onResponce(null);
                            }
                        }
                    });
                    return;
                }
                fitnessCloudCallBack.onResponce(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, StartDeviceLinkageRequest startDeviceLinkageRequest, DevInfo devInfo, final FitnessCloudCallBack fitnessCloudCallBack) {
        if (obj instanceof DataDeviceIntelligentInfo) {
            final DataDeviceIntelligentInfo dataDeviceIntelligentInfo = (DataDeviceIntelligentInfo) obj;
            devInfo.setManu(String.valueOf(dataDeviceIntelligentInfo.getDeviceManu()));
            devInfo.setProdId(String.valueOf(dataDeviceIntelligentInfo.getDeviceProductId()));
            devInfo.setHiv(String.valueOf(dataDeviceIntelligentInfo.getDeviceHiv()));
            startDeviceLinkageRequest.setDevInfo(devInfo);
            LogUtil.a("UIDV_FitnessCloud", "startDeviceLinkage StartDeviceLinkageRequest is:", startDeviceLinkageRequest);
            this.b.e(startDeviceLinkageRequest, new ICloudOperationResult<StartDeviceLinkageResponse>() { // from class: jgy.1
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void operationResult(StartDeviceLinkageResponse startDeviceLinkageResponse, String str, boolean z) {
                    if (startDeviceLinkageResponse != null) {
                        startDeviceLinkageResponse.setDevice_prodId(dataDeviceIntelligentInfo.getDeviceProductId());
                        fitnessCloudCallBack.onResponce(startDeviceLinkageResponse);
                    } else {
                        fitnessCloudCallBack.onResponce(null);
                    }
                }
            });
        }
    }

    public void c(String str, String str2, String str3, final FitnessCloudCallBack fitnessCloudCallBack) {
        ReleaseLogUtil.e("R_UIDV_FitnessCloud", "transDeviceData enter.");
        if (fitnessCloudCallBack == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        if (!ScreenUtil.a()) {
            PowerKitManager.e().a("UIDV_FitnessCloud", 512, "user-transSleepData");
        }
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setSid(str);
        HashMap hashMap = new HashMap(16);
        hashMap.put("state", str2);
        deviceServiceInfo.setData(hashMap);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        TransferDeviceDataRequest transferDeviceDataRequest = new TransferDeviceDataRequest();
        transferDeviceDataRequest.setDevId(str3);
        transferDeviceDataRequest.setServices(arrayList);
        this.b.a(transferDeviceDataRequest, new ICloudOperationResult<TransferDeviceDataResponse>() { // from class: jgy.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void operationResult(TransferDeviceDataResponse transferDeviceDataResponse, String str4, boolean z) {
                if (transferDeviceDataResponse != null) {
                    fitnessCloudCallBack.onResponce(transferDeviceDataResponse);
                    PowerKitManager.e().e("UIDV_FitnessCloud", 512, "user-transSleepData");
                }
            }
        });
    }

    public void a(final String str, final FitnessCloudCallBack fitnessCloudCallBack) {
        ReleaseLogUtil.e("R_UIDV_FitnessCloud", "releaseDeviceLinkage enter.");
        if (fitnessCloudCallBack == null) {
            return;
        }
        this.f13836a.execute(new Runnable() { // from class: jgy.4
            @Override // java.lang.Runnable
            public void run() {
                ReleaseDeviceLinkageRequest releaseDeviceLinkageRequest = new ReleaseDeviceLinkageRequest();
                releaseDeviceLinkageRequest.setDevId(str);
                jgy.this.b.d(releaseDeviceLinkageRequest, new ICloudOperationResult<ReleaseDeviceLinkageResponse>() { // from class: jgy.4.2
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void operationResult(ReleaseDeviceLinkageResponse releaseDeviceLinkageResponse, String str2, boolean z) {
                        fitnessCloudCallBack.onResponce(releaseDeviceLinkageResponse);
                    }
                });
            }
        });
    }

    public void a(final FitnessCloudCallBack fitnessCloudCallBack) {
        ReleaseLogUtil.e("R_UIDV_FitnessCloud", "stopDeviceLinkage enter.");
        if (fitnessCloudCallBack == null) {
            return;
        }
        DeviceInfo a2 = jpt.a("UIDV_FitnessCloud");
        if (a2 == null) {
            fitnessCloudCallBack.onResponce(null);
        } else {
            this.c.getSwitchSetting("intelligent_home_linkage", a2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: jgy.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        String str = obj instanceof String ? (String) obj : "";
                        if (str.contains("&&")) {
                            String[] split = str.split("&&");
                            LogUtil.a("UIDV_FitnessCloud", "INTELLIGENT_HOME_LINKAGE split is:", Integer.valueOf(split.length));
                            if (split.length == 5) {
                                jgy.this.d(split[0], fitnessCloudCallBack);
                                return;
                            } else {
                                fitnessCloudCallBack.onResponce(null);
                                return;
                            }
                        }
                        fitnessCloudCallBack.onResponce(null);
                        return;
                    }
                    fitnessCloudCallBack.onResponce(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, final FitnessCloudCallBack fitnessCloudCallBack) {
        if (fitnessCloudCallBack == null) {
            return;
        }
        StopDeviceLinkageRequest stopDeviceLinkageRequest = new StopDeviceLinkageRequest();
        stopDeviceLinkageRequest.setDevId(str);
        this.b.a(stopDeviceLinkageRequest, new ICloudOperationResult<StopDeviceLinkageResponse>() { // from class: jgy.8
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(StopDeviceLinkageResponse stopDeviceLinkageResponse, String str2, boolean z) {
                fitnessCloudCallBack.onResponce(stopDeviceLinkageResponse);
            }
        });
    }
}
