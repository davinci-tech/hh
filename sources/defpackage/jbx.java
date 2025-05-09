package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.callback.IHttpOperationResult;
import com.huawei.hwcloudmodel.callback.WifiRequestCallBack;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.WifiDataCloudFactory;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyRequest;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyResponse;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataRequest;
import com.huawei.hwcloudmodel.model.unite.AddEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.AddSleepStatReq;
import com.huawei.hwcloudmodel.model.unite.DelEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.DeleteAllHealthDataReq;
import com.huawei.hwcloudmodel.model.unite.DeleteAllMotionPathReq;
import com.huawei.hwcloudmodel.model.unite.DeleteAllSportDataReq;
import com.huawei.hwcloudmodel.model.unite.DeleteSportDataReq;
import com.huawei.hwcloudmodel.model.unite.GetEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.GetMotionPathByTimeReq;
import com.huawei.hwcloudmodel.model.unite.GetSleepStatReq;
import com.huawei.hwcloudmodel.model.unite.GetThirdSignReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAddAuthMsgBySubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAddAuthorizeForSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAuthorizeByMainUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceDeleteAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceExitAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAllDeviceRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceRegistration;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetMainUserAuth;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetVerifyCodeForMainUserRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceShareByMainUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceShareMemberInfoBySubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubuserAuthorize;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUnbindReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUpdateAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.userprofile.DeleteAuthorizeReq;
import com.huawei.hwcloudmodel.model.userprofile.GetAuthorizeReq;
import com.huawei.hwcloudmodel.model.userprofile.GetCommentReq;
import com.huawei.hwcloudmodel.model.userprofile.GetCommentRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetThirdIdentifyReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoRsp;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataReq;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataRsp;
import com.huawei.hwcloudmodel.model.userprofile.SetAuthorizeReq;
import com.huawei.hwcloudmodel.model.userprofile.UpdateCommentReq;
import com.huawei.hwcloudmodel.utils.GetEvaluationResultRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jbx {
    private jcd b;
    private WifiDataCloudFactory f;
    private jcb g;
    public static final HostnameVerifier c = new HostnameVerifier() { // from class: jbx.4
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Context f13719a = null;
    private static volatile jbx e = null;

    private jbx() {
        Context context = BaseApplication.getContext();
        f13719a = context;
        this.g = new jcb(context);
        jcd jcdVar = new jcd();
        this.b = jcdVar;
        jcdVar.d();
        this.f = new WifiDataCloudFactory(f13719a);
    }

    public static jbx d() {
        if (e == null) {
            synchronized (d) {
                if (e == null) {
                    e = new jbx();
                }
            }
        }
        return e;
    }

    private String e(Object obj) {
        if (obj instanceof MergeUserAllDataReq) {
            return "/profile/merge/mergeUserAllData";
        }
        if (obj instanceof GetUserMergeInfoReq) {
            return "/profile/merge/getUserMergeInfo";
        }
        if (obj instanceof UpdateCommentReq) {
            return "/dataRecommend/common/updateCommentRemainderTimes";
        }
        if (obj instanceof GetCommentReq) {
            return "/dataRecommend/common/getCommentRemainderTimes";
        }
        if (obj instanceof DeleteSportDataReq) {
            return "/dataSync/sport/deleteSportsData";
        }
        if (obj instanceof DeleteAllSportDataReq) {
            return "/dataSync/sport/deleteAllSportsData";
        }
        if (obj instanceof AddSleepStatReq) {
            return "/dataSync/sport/addSleepStat";
        }
        if (obj instanceof GetSleepStatReq) {
            return "/dataQuery/sport/getSleepStat";
        }
        if (obj instanceof DeleteAllHealthDataReq) {
            return "/dataSync/health/deleteAllHealthData";
        }
        if (obj instanceof GetMotionPathByTimeReq) {
            return "/dataQuery/path/getMotionPathData";
        }
        if (obj instanceof DeleteAllMotionPathReq) {
            return "/dataSync/path/deleteAllMotionPathData";
        }
        String d2 = d(obj);
        if (d2 != null) {
            return d2;
        }
        String b = b(obj);
        if (b != null) {
            return b;
        }
        return null;
    }

    private String d(Object obj) {
        if (obj instanceof SetAuthorizeReq) {
            return "/profile/third/authorize";
        }
        if (obj instanceof GetAuthorizeReq) {
            return "/profile/third/getAuthorizeToken";
        }
        if (obj instanceof DeleteAuthorizeReq) {
            return "/profile/third/cancelAuthorize";
        }
        if (obj instanceof GetThirdSignReq) {
            return "/dataOpen/third/getThirdSign";
        }
        if (obj instanceof AddEvaluationResultReq) {
            return "/profile/evaluation/addEvaluationResult";
        }
        if (obj instanceof GetEvaluationResultReq) {
            return "/profile/evaluation/getEvaluationResult";
        }
        if (obj instanceof DelEvaluationResultReq) {
            return "/profile/evaluation/deleteEvaluationResult";
        }
        return null;
    }

    private String b(Object obj) {
        if (obj instanceof StartDeviceLinkageRequest) {
            return "/deviceAgent/startDeviceLinkage";
        }
        if (obj instanceof TransferDeviceDataRequest) {
            return "/deviceAgent/transDeviceData";
        }
        if (obj instanceof StopDeviceLinkageRequest) {
            return "/deviceAgent/stopDeviceLinkage";
        }
        if (obj instanceof ReleaseDeviceLinkageRequest) {
            return "/deviceAgent/releaseDeviceLinkage";
        }
        if (obj instanceof GetThirdIdentifyReq) {
            return "hiHealth/getThirdIdentify";
        }
        return null;
    }

    public void c(GetCommentReq getCommentReq, final ICloudOperationResult<GetCommentRsp> iCloudOperationResult) {
        if (getCommentReq == null || iCloudOperationResult == null) {
            LogUtil.h("HWCloudUtils", "getCommentRemainderTimes req or cb is null");
            return;
        }
        LogUtil.a("HWCloudUtils", " Enter getCommentRemainderTimes ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(getCommentReq, hashMap);
        } catch (Exception unused) {
            LogUtil.b("HWCloudUtils", "getCommentRemainderTimes Exception");
        }
        String e2 = e(getCommentReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "getCommentRemainderTimes unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.10
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "getCommentRemainderTimes in operationResult");
                    try {
                        GetCommentRsp getCommentRsp = (GetCommentRsp) new Gson().fromJson(str, GetCommentRsp.class);
                        if (getCommentRsp != null) {
                            if (getCommentRsp.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "getCommentRemainderTimes in operationResult fail:", getCommentRsp.getResultCode());
                                iCloudOperationResult.operationResult(null, "code:" + getCommentRsp.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "getCommentRemainderTimes in operationResult successful");
                                iCloudOperationResult.operationResult(getCommentRsp, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.b("HWCloudUtils", "getCommentRemainderTimes json exception :", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "addPrivacyRecord Exception code:", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void d(UpdateCommentReq updateCommentReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", " Enter updateCommentRemainderTimes ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(updateCommentReq, hashMap);
        } catch (Exception unused) {
            LogUtil.b("HWCloudUtils", "updateCommentRemainderTimes Exception");
        }
        String e2 = e(updateCommentReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "updateCommentRemainderTimes unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.16
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "updateCommentRemainderTimes in operationResult");
                    try {
                        CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                        if (cloudCommonReponse != null) {
                            if (cloudCommonReponse.getResultCode().intValue() == 0) {
                                LogUtil.a("HWCloudUtils", "updateCommentRemainderTimes in operationResult successful");
                                iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                            } else {
                                LogUtil.a("HWCloudUtils", "updateCommentRemainderTimes in operationResult fail: ", cloudCommonReponse.getResultCode());
                                iCloudOperationResult.operationResult(null, "code:" + cloudCommonReponse.getResultCode(), false);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.b("HWCloudUtils", "updateCommentRemainderTimes json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "addPrivacyRecord Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void c(MergeUserAllDataReq mergeUserAllDataReq, final ICloudOperationResult<MergeUserAllDataRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter mergeUserAllData ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(mergeUserAllDataReq, hashMap);
        } catch (Exception unused) {
            LogUtil.b("HWCloudUtils", "mergeUserAllData Exception");
        }
        String e2 = e(mergeUserAllDataReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "mergeUserAllData unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.24
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "mergeUserAllData in operationResult");
                    try {
                        MergeUserAllDataRsp mergeUserAllDataRsp = (MergeUserAllDataRsp) new Gson().fromJson(str, MergeUserAllDataRsp.class);
                        if (mergeUserAllDataRsp != null) {
                            if (mergeUserAllDataRsp.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "mergeUserAllData in operationResult fail: ", mergeUserAllDataRsp.getResultCode());
                                iCloudOperationResult.operationResult(null, "" + mergeUserAllDataRsp.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "mergeUserAllData in operationResult successful");
                                iCloudOperationResult.operationResult(mergeUserAllDataRsp, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.b("HWCloudUtils", "mergeUserAllData json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "mergeUserAllData Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void a(GetUserMergeInfoReq getUserMergeInfoReq, final ICloudOperationResult<GetUserMergeInfoRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getUserMergeInfo");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(getUserMergeInfoReq, hashMap);
        } catch (Exception unused) {
            LogUtil.b("HWCloudUtils", "getUserMergeInfo Exception");
        }
        String e2 = e(getUserMergeInfoReq);
        if (e2 == null) {
            LogUtil.a("HWCloudUtils", "getUserMergeInfo unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.21
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "getUserMergeInfo in operationResult");
                    try {
                        GetUserMergeInfoRsp getUserMergeInfoRsp = (GetUserMergeInfoRsp) new Gson().fromJson(str, GetUserMergeInfoRsp.class);
                        if (getUserMergeInfoRsp != null) {
                            if (getUserMergeInfoRsp.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "getUserMergeInfo in operationResult fail: ", getUserMergeInfoRsp.getResultCode());
                                iCloudOperationResult.operationResult(null, "" + getUserMergeInfoRsp.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "getUserMergeInfo in operationResult successful");
                                iCloudOperationResult.operationResult(getUserMergeInfoRsp, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.b("HWCloudUtils", "getUserMergeInfo json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "getUserMergeInfo Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public void d(final double d2, final double d3, final ICloudOperationResult<jca> iCloudOperationResult) {
        ThreadPoolManager.d().c("HWCloudUtils", new Runnable() { // from class: jbx.25
            @Override // java.lang.Runnable
            public void run() {
                jca b = jbr.e().b(d2, d3);
                if (b.b() != -1.0d && b.d() != 0) {
                    iCloudOperationResult.operationResult(b, null, true);
                } else {
                    iCloudOperationResult.operationResult(b, null, false);
                }
            }
        });
    }

    public void a(AddEvaluationResultReq addEvaluationResultReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter addEvaluationResult ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(addEvaluationResultReq, hashMap);
        } catch (Exception unused) {
            LogUtil.a("HWCloudUtils", "addEvaluationResult Exception");
        }
        String e2 = e(addEvaluationResultReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "addEvaluationResult unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.3
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "addEvaluationResult in operationResult");
                    try {
                        CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                        if (cloudCommonReponse != null) {
                            if (cloudCommonReponse.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "addEvaluationResult in operationResult fail: ", cloudCommonReponse.getResultCode());
                                iCloudOperationResult.operationResult(null, "code:" + cloudCommonReponse.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "addEvaluationResult in operationResult successful");
                                iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.a("HWCloudUtils", "addEvaluationResult json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "addEvaluationResult Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void a(GetEvaluationResultReq getEvaluationResultReq, final ICloudOperationResult<GetEvaluationResultRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getEvaluationResult  ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(getEvaluationResultReq, hashMap);
        } catch (Exception unused) {
            LogUtil.a("HWCloudUtils", "getEvaluationResult eception");
        }
        String e2 = e(getEvaluationResultReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "getEvaluationResult unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.5
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "getEvaluationResult in operationResult");
                    try {
                        GetEvaluationResultRsp getEvaluationResultRsp = (GetEvaluationResultRsp) new Gson().fromJson(str, GetEvaluationResultRsp.class);
                        if (getEvaluationResultRsp != null) {
                            if (getEvaluationResultRsp.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "getEvaluationResult in operationResult fail: ", getEvaluationResultRsp.getResultCode());
                                iCloudOperationResult.operationResult(null, "code:" + getEvaluationResultRsp.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "getEvaluationResult in operationResult successful");
                                iCloudOperationResult.operationResult(getEvaluationResultRsp, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.a("HWCloudUtils", "getEvaluationResult json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "getEvaluationResult Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void e(DelEvaluationResultReq delEvaluationResultReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter delEvaluationResult ");
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            b(delEvaluationResultReq, hashMap);
        } catch (Exception unused) {
            LogUtil.a("HWCloudUtils", "delEvaluationResult Exception");
        }
        String e2 = e(delEvaluationResultReq);
        if (e2 == null) {
            LogUtil.h("HWCloudUtils", "delEvaluationResult unknown url");
            iCloudOperationResult.operationResult(null, "unknown url", false);
        } else {
            this.g.a(e2, hashMap, new IHttpOperationResult() { // from class: jbx.2
                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void operationResult(String str) {
                    LogUtil.a("HWCloudUtils", "delEvaluationResult in operationResult");
                    try {
                        CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                        if (cloudCommonReponse != null) {
                            if (cloudCommonReponse.getResultCode().intValue() != 0) {
                                LogUtil.a("HWCloudUtils", "delEvaluationResult  in operationResult fail: ", cloudCommonReponse.getResultCode());
                                iCloudOperationResult.operationResult(null, "code:" + cloudCommonReponse.getResultCode(), false);
                            } else {
                                LogUtil.a("HWCloudUtils", "delEvaluationResult in operationResult successful");
                                iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                            }
                        }
                    } catch (JsonSyntaxException e3) {
                        LogUtil.a("HWCloudUtils", "delEvaluationResult json exception : ", e3.getMessage());
                        iCloudOperationResult.operationResult(null, e3.getMessage(), false);
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
                public void exception(int i, Exception exc) {
                    LogUtil.a("HWCloudUtils", "delEvaluationResult Exception code: ", Integer.valueOf(i));
                    iCloudOperationResult.operationResult(null, exc.getMessage(), false);
                }
            });
        }
    }

    public void b(Object obj, HashMap<String, Object> hashMap) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String name = field.getName();
                boolean isAccessible = field.isAccessible();
                if (!isAccessible) {
                    field.setAccessible(true);
                }
                Object obj2 = field.get(obj);
                if (!isAccessible) {
                    field.setAccessible(false);
                }
                if (obj2 != null) {
                    hashMap.put(name, obj2);
                }
            }
        } catch (IllegalAccessException e2) {
            LogUtil.b("HWCloudUtils", "HWCloudUtils IllegalAccessException E: ", e2.getMessage());
        } catch (IllegalArgumentException e3) {
            LogUtil.b("HWCloudUtils", "HWCloudUtils IllegalArgumentException E: ", e3.getMessage());
        }
    }

    public void e(WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter syncWifiDeviceControl");
        String b = lql.b(this.f.getBody(wifiDeviceControlDataModelReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceControlDataModelReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.1
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "syncWifiDeviceControl in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "syncWifiDeviceControl, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "syncWifiDeviceControl ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " syncWifiDeviceControl JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void e(WifiDeviceUnbindReq wifiDeviceUnbindReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter unBindWifiDevice");
        String b = lql.b(this.f.getBody(wifiDeviceUnbindReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceUnbindReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.6
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "unBindWifiDevice in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "unBindWifiDevice, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "unBindWifiDevice ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " unBindWifiDevice JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void d(WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq, final ICloudOperationResult<WifiDeviceGetWifiDeviceInfoRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getWifiDeviceInfo");
        String b = lql.b(this.f.getBody(wifiDeviceGetWifiDeviceInfoReq));
        Map<String, String> headers = this.f.getHeaders();
        String requestUrl = this.f.getRequestUrl(wifiDeviceGetWifiDeviceInfoReq.getUrl());
        LogUtil.c("HWCloudUtils", "getWifiDeviceInfo, httpUrl: ", requestUrl, " params: ", b);
        this.b.b(requestUrl, b, headers, new WifiRequestCallBack() { // from class: jbx.9
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getWifiDeviceInfo in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getWifiDeviceInfo, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    LogUtil.c("HWCloudUtils", "getWifiDeviceInfo json: ", str);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        LogUtil.a("HWCloudUtils", "getWifiDeviceInfo primal json resultCode: ", Integer.valueOf(jSONObject.has("resultCode") ? jSONObject.getInt("resultCode") : -1));
                    } catch (JSONException unused) {
                        LogUtil.b("HWCloudUtils", "getWifiDeviceInfo JSONException");
                    }
                    WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp = (WifiDeviceGetWifiDeviceInfoRsp) new Gson().fromJson(str, WifiDeviceGetWifiDeviceInfoRsp.class);
                    if (wifiDeviceGetWifiDeviceInfoRsp != null) {
                        LogUtil.a("HWCloudUtils", "getWifiDeviceInfo ResultCode = ", wifiDeviceGetWifiDeviceInfoRsp.getResultCode());
                        if (wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(wifiDeviceGetWifiDeviceInfoRsp, null, true);
                        } else {
                            iCloudOperationResult.operationResult(wifiDeviceGetWifiDeviceInfoRsp, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " getWifiDeviceInfo JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void a(WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq, final ICloudOperationResult<WifiDeviceServiceInfoRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getWifiDeviceServiceInfo");
        String b = lql.b(this.f.getBody(wifiDeviceServiceInfoReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceServiceInfoReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.7
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getWifiDeviceServiceInfo in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getWifiDeviceServiceInfo, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp = new WifiDeviceServiceInfoRsp();
                    wifiDeviceServiceInfoRsp.toObject(str);
                    LogUtil.a("HWCloudUtils", "getWifiDeviceServiceInfo ResultCode = ", wifiDeviceServiceInfoRsp.getResultCode());
                    if (wifiDeviceServiceInfoRsp.getResultCode().intValue() == 0) {
                        iCloudOperationResult.operationResult(wifiDeviceServiceInfoRsp, null, true);
                    } else {
                        iCloudOperationResult.operationResult(wifiDeviceServiceInfoRsp, null, false);
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " getWifiDeviceServiceInfo JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void b(final ICloudOperationResult<WifiDeviceGetAllDeviceRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getUserAllWifiDevice");
        String b = lql.b(this.f.getBody(null));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl("/deviceAgent/getUserDevice"), b, headers, new WifiRequestCallBack() { // from class: jbx.8
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getUserAllWifiDevice in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getUserAllWifiDevice, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp = (WifiDeviceGetAllDeviceRsp) new Gson().fromJson(str, WifiDeviceGetAllDeviceRsp.class);
                    if (wifiDeviceGetAllDeviceRsp != null) {
                        LogUtil.a("HWCloudUtils", "getUserAllWifiDevice ResultCode = ", wifiDeviceGetAllDeviceRsp.getResultCode());
                        if (wifiDeviceGetAllDeviceRsp.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(wifiDeviceGetAllDeviceRsp, null, true);
                        } else {
                            iCloudOperationResult.operationResult(wifiDeviceGetAllDeviceRsp, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " getUserAllWifiDevice JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void c(WifiDeviceAddAuthorizeForSubUserReq wifiDeviceAddAuthorizeForSubUserReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter addWifiDeviceAuthorizeForSubUser");
        String b = lql.b(this.f.getBody(wifiDeviceAddAuthorizeForSubUserReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceAddAuthorizeForSubUserReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.14
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "addWifiDeviceAuthorizeForSubUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "addWifiDeviceAuthorizeForSubUser, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "addWifiDeviceAuthorizeForSubUser ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() != 0 && cloudCommonReponse.getResultCode().intValue() != 112000020) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                            return;
                        }
                        iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "addWifiDeviceAuthorizeForSubUser JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void c(WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq, final ICloudOperationResult<WifiDeviceGetAuthorizeSubUserRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getWifiDeviceAuthorizeSubUser");
        String b = lql.b(this.f.getBody(wifiDeviceGetAuthorizeSubUserReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceGetAuthorizeSubUserReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.12
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getWifiDeviceAuthorizeSubUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getWifiDeviceAuthorizeSubUser, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp = (WifiDeviceGetAuthorizeSubUserRsp) new Gson().fromJson(str, WifiDeviceGetAuthorizeSubUserRsp.class);
                    if (wifiDeviceGetAuthorizeSubUserRsp != null) {
                        LogUtil.a("HWCloudUtils", "getWifiDeviceAuthorizeSubUser ResultCode = ", wifiDeviceGetAuthorizeSubUserRsp.getResultCode());
                        if (wifiDeviceGetAuthorizeSubUserRsp.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(wifiDeviceGetAuthorizeSubUserRsp, null, true);
                        } else {
                            iCloudOperationResult.operationResult(wifiDeviceGetAuthorizeSubUserRsp, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "getWifiDeviceAuthorizeSubUser, JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void d(WifiDeviceUpdateAuthorizeSubUserReq wifiDeviceUpdateAuthorizeSubUserReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter upateWifiDeviceAuthorizeSubUser");
        String b = lql.b(this.f.getBody(wifiDeviceUpdateAuthorizeSubUserReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceUpdateAuthorizeSubUserReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.13
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "upateWifiDeviceAuthorizeSubUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "updateWifiDeviceAuthorizeSubUser, json is null");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "upateWifiDeviceAuthorizeSubUser ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "upateWifiDeviceAuthorizeSubUser, JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void a(WifiDeviceDeleteAuthorizeSubUserReq wifiDeviceDeleteAuthorizeSubUserReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter deleteWifiDeviceAuthorizeSubUser");
        String b = lql.b(this.f.getBody(wifiDeviceDeleteAuthorizeSubUserReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceDeleteAuthorizeSubUserReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.15
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "deleteWifiDeviceAuthorizeSubUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "deleteWifiDeviceAuthorizeSubUser, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "deleteWifiDeviceAuthorizeSubUser ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "deleteWifiDeviceAuthorizeSubUser JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void c(WifiDeviceExitAuthorizeSubUserReq wifiDeviceExitAuthorizeSubUserReq, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter exitWifiDeviceAuthorizeSubUser");
        String b = lql.b(this.f.getBody(wifiDeviceExitAuthorizeSubUserReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceExitAuthorizeSubUserReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.11
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "exitWifiDeviceAuthorizeSubUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "deleteWifiDeviceAuthorizeSubUser, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "exitWifiDeviceAuthorizeSubUser ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "exitWifiDeviceAuthorizeSubUser JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void e(final ICloudOperationResult<WifiDeviceGetVerifyCodeForMainUserRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getWifiDeviceVerifyCodeForMainUser");
        String b = lql.b(this.f.getBody(null));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl("/deviceAgent/getVerifyCode"), b, headers, new WifiRequestCallBack() { // from class: jbx.17
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getWifiDeviceVerifyCodeForMainUser in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getWifiDeviceVerifyCodeForMainUser, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    WifiDeviceGetVerifyCodeForMainUserRsp wifiDeviceGetVerifyCodeForMainUserRsp = (WifiDeviceGetVerifyCodeForMainUserRsp) new Gson().fromJson(str, WifiDeviceGetVerifyCodeForMainUserRsp.class);
                    if (wifiDeviceGetVerifyCodeForMainUserRsp != null) {
                        LogUtil.a("HWCloudUtils", "getWifiDeviceVerifyCodeForMainUser ResultCode = ", wifiDeviceGetVerifyCodeForMainUserRsp.getResultCode());
                        if (wifiDeviceGetVerifyCodeForMainUserRsp.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(wifiDeviceGetVerifyCodeForMainUserRsp, null, true);
                        } else {
                            iCloudOperationResult.operationResult(wifiDeviceGetVerifyCodeForMainUserRsp, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " getWifiDeviceVerifyCodeForMainUser JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void e(WifiDeviceAddAuthMsgBySubUserReq wifiDeviceAddAuthMsgBySubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter wifiDeviceAddAuthMsgBySubUser");
        d(lql.b(this.f.getBody(wifiDeviceAddAuthMsgBySubUserReq)), wifiDeviceAddAuthMsgBySubUserReq.getUrl(), iCloudOperationResult);
    }

    public void e(WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter wifiDeviceGetSubUserAuthMsg");
        d(lql.b(this.f.getBody(wifiDeviceGetSubUserAuthMsgReq)), wifiDeviceGetSubUserAuthMsgReq.getUrl(), iCloudOperationResult);
    }

    public void e(WifiDeviceAuthorizeByMainUserReq wifiDeviceAuthorizeByMainUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter wifiDeviceAuthorizeByMainUser");
        d(lql.b(this.f.getBody(wifiDeviceAuthorizeByMainUserReq)), wifiDeviceAuthorizeByMainUserReq.getUrl(), iCloudOperationResult);
    }

    private void d(String str, String str2, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter wifiDeviceRequest");
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(str2), str, headers, new WifiRequestCallBack() { // from class: jbx.19
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str3) {
                LogUtil.a("HWCloudUtils", "wifiDeviceRequest in operationResult result is ", str3);
                try {
                    if (TextUtils.isEmpty(str3)) {
                        LogUtil.h("HWCloudUtils", "wifiDeviceRequest, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    CloudCommonReponse cloudCommonReponse = (CloudCommonReponse) new Gson().fromJson(str3, CloudCommonReponse.class);
                    if (cloudCommonReponse != null) {
                        LogUtil.a("HWCloudUtils", "wifiDeviceRequest ResultCode = ", cloudCommonReponse.getResultCode());
                        if (cloudCommonReponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(cloudCommonReponse, str3, true);
                        } else {
                            iCloudOperationResult.operationResult(cloudCommonReponse, str3, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "wifiDeviceRequest JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }

    public void d(WifiDeviceShareByMainUserReq wifiDeviceShareByMainUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter addAuthMsgByMainUser");
        d(lql.b(this.f.getBody(wifiDeviceShareByMainUserReq)), wifiDeviceShareByMainUserReq.getUrl(), iCloudOperationResult);
    }

    public void b(WifiDeviceSubuserAuthorize wifiDeviceSubuserAuthorize, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter authorizeBySubUser");
        d(lql.b(this.f.getBody(wifiDeviceSubuserAuthorize)), wifiDeviceSubuserAuthorize.getUrl(), iCloudOperationResult);
    }

    public void e(WifiDeviceGetMainUserAuth wifiDeviceGetMainUserAuth, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter authorizeBySubUser");
        d(lql.b(this.f.getBody(wifiDeviceGetMainUserAuth)), wifiDeviceGetMainUserAuth.getUrl(), iCloudOperationResult);
    }

    public void a(WifiDeviceGetDeviceRegistration wifiDeviceGetDeviceRegistration, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter getDeviceRegistration");
        d(lql.b(this.f.getBody(wifiDeviceGetDeviceRegistration)), wifiDeviceGetDeviceRegistration.getUrl(), iCloudOperationResult);
    }

    public void b(WifiDeviceShareMemberInfoBySubUserReq wifiDeviceShareMemberInfoBySubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "enter getMemberInfoBySubUser");
        d(lql.b(this.f.getBody(wifiDeviceShareMemberInfoBySubUserReq)), wifiDeviceShareMemberInfoBySubUserReq.getUrl(), iCloudOperationResult);
    }

    public void c(DeviceLinkResultNotifyRequest deviceLinkResultNotifyRequest, final ICloudOperationResult<DeviceLinkResultNotifyResponse> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "deviceLinkResultNotify");
        if (deviceLinkResultNotifyRequest == null || iCloudOperationResult == null) {
            LogUtil.h("HWCloudUtils", "deviceLinkResultNotify params is null.please check.");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        b(deviceLinkResultNotifyRequest, hashMap);
        this.g.a("/deviceAgent/deviceLinkResultNotify", hashMap, new IHttpOperationResult() { // from class: jbx.18
            @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
            public void operationResult(String str) {
                try {
                    DeviceLinkResultNotifyResponse deviceLinkResultNotifyResponse = (DeviceLinkResultNotifyResponse) new Gson().fromJson(str, DeviceLinkResultNotifyResponse.class);
                    if (deviceLinkResultNotifyResponse != null) {
                        LogUtil.a("HWCloudUtils", "responseEntity ResultCode : ", deviceLinkResultNotifyResponse.getResultCode());
                        if (deviceLinkResultNotifyResponse.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(deviceLinkResultNotifyResponse, null, true);
                            return;
                        } else {
                            iCloudOperationResult.operationResult(deviceLinkResultNotifyResponse, null, false);
                            return;
                        }
                    }
                    LogUtil.h("HWCloudUtils", "responseEntity is null.");
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", "deviceLinkResultNotify exception : ", ExceptionUtils.d(e2));
                    iCloudOperationResult.operationResult(null, ExceptionUtils.d(e2), false);
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.IHttpOperationResult
            public void exception(int i, Exception exc) {
                LogUtil.a("HWCloudUtils", "deviceLinkResultNotify Exception code: ", Integer.valueOf(i));
                iCloudOperationResult.operationResult(null, ExceptionUtils.d(exc), false);
            }
        });
    }

    public void a(WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq, final ICloudOperationResult<WifiDeviceGetDeviceStatusRsp> iCloudOperationResult) {
        LogUtil.a("HWCloudUtils", "Enter getWifiDeviceStatus");
        String b = lql.b(this.f.getBody(wifiDeviceGetDeviceStatusReq));
        Map<String, String> headers = this.f.getHeaders();
        this.b.b(this.f.getRequestUrl(wifiDeviceGetDeviceStatusReq.getUrl()), b, headers, new WifiRequestCallBack() { // from class: jbx.20
            @Override // com.huawei.hwcloudmodel.callback.WifiRequestCallBack
            public void operationResult(String str) {
                LogUtil.a("HWCloudUtils", "getWifiDeviceStatus in operationResult text=***");
                try {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.h("HWCloudUtils", "getWifiDeviceStatus, json is empty");
                        iCloudOperationResult.operationResult(null, null, false);
                        return;
                    }
                    WifiDeviceGetDeviceStatusRsp wifiDeviceGetDeviceStatusRsp = (WifiDeviceGetDeviceStatusRsp) new Gson().fromJson(str, WifiDeviceGetDeviceStatusRsp.class);
                    if (wifiDeviceGetDeviceStatusRsp != null) {
                        LogUtil.a("HWCloudUtils", "getWifiDeviceStatus ResultCode = ", wifiDeviceGetDeviceStatusRsp.getResultCode());
                        if (wifiDeviceGetDeviceStatusRsp.getResultCode().intValue() == 0) {
                            iCloudOperationResult.operationResult(wifiDeviceGetDeviceStatusRsp, null, true);
                        } else {
                            iCloudOperationResult.operationResult(wifiDeviceGetDeviceStatusRsp, null, false);
                        }
                    }
                } catch (JsonSyntaxException e2) {
                    LogUtil.b("HWCloudUtils", " getWifiDeviceStatus JsonSyntaxException");
                    iCloudOperationResult.operationResult(null, e2.getMessage(), false);
                }
            }
        });
    }
}
