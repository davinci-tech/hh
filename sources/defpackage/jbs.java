package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudGzipFactory;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddMotionPathReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddSportDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddSportTotalReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteHealthStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteMotionPathReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteSampleSequenceRep;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteSampleSequenceRsq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByReversedOrderReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByReversedOrderRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetMotionPathByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSleepSportsDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSleepSportsDataLatestRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDimenStatisticsReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSyncVersionsReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.UnbindDeviceRequest;
import com.huawei.hwcloudmodel.healthdatacloud.model.UpdateBindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.WechatDeviceRegistReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.WechatDeviceSignReq;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.CloudCommonRequest;
import com.huawei.hwcloudmodel.model.ThirdOauthTokenI;
import com.huawei.hwcloudmodel.model.ThirdOauthTokenO;
import com.huawei.hwcloudmodel.model.ecgservice.EcgGiftCardRequest;
import com.huawei.hwcloudmodel.model.ecgservice.EcgGiftCardResponse;
import com.huawei.hwcloudmodel.model.ecgservice.EcgIvActivationRequest;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyRequest;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyResponse;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.ReleaseDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StartDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageRequest;
import com.huawei.hwcloudmodel.model.intelligent.StopDeviceLinkageResponse;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataRequest;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataResponse;
import com.huawei.hwcloudmodel.model.unite.AddEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.AddHealthStatRsp;
import com.huawei.hwcloudmodel.model.unite.AddMotionPathRsp;
import com.huawei.hwcloudmodel.model.unite.AddSportDataRsp;
import com.huawei.hwcloudmodel.model.unite.AddSportTotalRsp;
import com.huawei.hwcloudmodel.model.unite.DelEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.DelHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.DeleteHealthStatRsp;
import com.huawei.hwcloudmodel.model.unite.DeleteMotionPathRsp;
import com.huawei.hwcloudmodel.model.unite.GetEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthStatRsp;
import com.huawei.hwcloudmodel.model.unite.GetMotionPathByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelReq;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportDimenStatRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportStatRsp;
import com.huawei.hwcloudmodel.model.unite.GetSyncVersionsRsp;
import com.huawei.hwcloudmodel.model.unite.PushMessageToDeviceReq;
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
import com.huawei.hwcloudmodel.model.userprofile.AddPrivacyRecordReq;
import com.huawei.hwcloudmodel.model.userprofile.AddPrivacyRecordRsp;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwcloudmodel.model.userprofile.DeleteAllUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.DeleteAllUserProfileRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetPrivacyRecordReq;
import com.huawei.hwcloudmodel.model.userprofile.GetPrivacyRecordRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoRsp;
import com.huawei.hwcloudmodel.model.userprofile.GetUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserProfileRsp;
import com.huawei.hwcloudmodel.model.userprofile.LastClearCloudDataTimeReq;
import com.huawei.hwcloudmodel.model.userprofile.LastClearCloudDataTimeRsp;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataReq;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataRsp;
import com.huawei.hwcloudmodel.model.userprofile.SetUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.SetUserProfileRsp;
import com.huawei.hwcloudmodel.model.userprofile.UpdateBindDeviceRsp;
import com.huawei.hwcloudmodel.model.wjx.SurveyIdRequest;
import com.huawei.hwcloudmodel.model.wjx.SurveyIdResponse;
import com.huawei.hwcloudmodel.utils.GetEvaluationResultRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class jbs extends HwBaseManager {
    private static jbs b;

    /* renamed from: a, reason: collision with root package name */
    private HealthDataCloudFactory f13715a;
    private jbx c;

    private jbs(Context context) {
        super(context);
        this.c = null;
        this.c = jbx.d();
        this.f13715a = new HealthDataCloudGzipFactory(BaseApplication.e());
    }

    public HealthDataCloudFactory d() {
        return this.f13715a;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 1002;
    }

    public static jbs a(Context context) {
        if (b == null) {
            b = new jbs(BaseApplication.e());
        }
        return b;
    }

    public CloudCommonReponse d(UnbindDeviceRequest unbindDeviceRequest) {
        return (CloudCommonReponse) lqi.d().d(unbindDeviceRequest.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(unbindDeviceRequest)), CloudCommonReponse.class);
    }

    public void a(MergeUserAllDataReq mergeUserAllDataReq, ICloudOperationResult<MergeUserAllDataRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.c(mergeUserAllDataReq, iCloudOperationResult);
        }
    }

    public void c(GetUserMergeInfoReq getUserMergeInfoReq, ICloudOperationResult<GetUserMergeInfoRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(getUserMergeInfoReq, iCloudOperationResult);
        }
    }

    public AddHealthStatRsp d(AddHealthStatReq addHealthStatReq) {
        return (AddHealthStatRsp) lqi.d().d(addHealthStatReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addHealthStatReq)), AddHealthStatRsp.class);
    }

    public GetHealthStatRsp a(GetHealthStatReq getHealthStatReq) {
        return (GetHealthStatRsp) lqi.d().d(getHealthStatReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getHealthStatReq)), GetHealthStatRsp.class);
    }

    public BindDeviceRsp b(BindDeviceReq bindDeviceReq) {
        String str = this.f13715a.getHeaders().get("x-huid");
        String valueOf = String.valueOf(this.f13715a.getBody(bindDeviceReq).get("token"));
        if (TextUtils.isEmpty(str) || "0".equals(str) || TextUtils.isEmpty(valueOf)) {
            LogUtil.b("HWCloudMgr", "wrong request params, dont request bindDevice");
            BindDeviceRsp bindDeviceRsp = new BindDeviceRsp();
            bindDeviceRsp.setResultCode(1);
            bindDeviceRsp.setResultDesc("wrong request params, dont request bindDevice");
            return bindDeviceRsp;
        }
        return (BindDeviceRsp) lqi.d().d(bindDeviceReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(bindDeviceReq)), BindDeviceRsp.class);
    }

    public GetBindDeviceRsp c(GetBindDeviceReq getBindDeviceReq) {
        return (GetBindDeviceRsp) lqi.d().d(getBindDeviceReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getBindDeviceReq)), GetBindDeviceRsp.class);
    }

    public GetBindDeviceRsp d(GetBindDeviceReq getBindDeviceReq) {
        return (GetBindDeviceRsp) lqi.d().d(getBindDeviceReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getBindDeviceReq)), GetBindDeviceRsp.class);
    }

    public UpdateBindDeviceRsp b(UpdateBindDeviceReq updateBindDeviceReq) {
        return (UpdateBindDeviceRsp) lqi.d().d(updateBindDeviceReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(updateBindDeviceReq)), UpdateBindDeviceRsp.class);
    }

    public SetUserProfileRsp d(SetUserProfileReq setUserProfileReq) {
        return (SetUserProfileRsp) lqi.d().d(setUserProfileReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(setUserProfileReq)), SetUserProfileRsp.class);
    }

    public CloudCommonReponse a(PushMessageToDeviceReq pushMessageToDeviceReq) {
        return (CloudCommonReponse) lqi.d().d(pushMessageToDeviceReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(pushMessageToDeviceReq)), CloudCommonReponse.class);
    }

    public void d(GetRunLevelReq getRunLevelReq, ResultCallback<GetRunLevelRsp> resultCallback) {
        LogUtil.a("HWCloudMgr", "Enter getRunLevel ");
        if (getRunLevelReq == null || resultCallback == null) {
            LogUtil.h("HWCloudMgr", "Enter getRunLevel params error");
            return;
        }
        try {
            lqi.d().b(getRunLevelReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getRunLevelReq)), GetRunLevelRsp.class, resultCallback);
        } catch (NullPointerException e) {
            LogUtil.b("HWCloudMgr", "getRunLevel Exception is ", ExceptionUtils.d(e));
        }
    }

    public GetUserProfileRsp c(GetUserProfileReq getUserProfileReq) {
        return (GetUserProfileRsp) lqi.d().d(getUserProfileReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getUserProfileReq)), GetUserProfileRsp.class);
    }

    public GetSyncVersionsRsp d(GetSyncVersionsReq getSyncVersionsReq) {
        return (GetSyncVersionsRsp) lqi.d().d(getSyncVersionsReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSyncVersionsReq)), GetSyncVersionsRsp.class);
    }

    public AddSportDataRsp d(AddSportDataReq addSportDataReq) {
        return (AddSportDataRsp) lqi.d().d(addSportDataReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addSportDataReq)), AddSportDataRsp.class);
    }

    public GetSportDataByTimeRsp b(GetSportDataByTimeReq getSportDataByTimeReq) {
        return (GetSportDataByTimeRsp) lqi.d().d(getSportDataByTimeReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSportDataByTimeReq)), GetSportDataByTimeRsp.class);
    }

    public GetSportDataByVersionRsp c(GetSportDataByVersionReq getSportDataByVersionReq) {
        return (GetSportDataByVersionRsp) lqi.d().d(getSportDataByVersionReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSportDataByVersionReq)), GetSportDataByVersionRsp.class);
    }

    public AddSportTotalRsp e(AddSportTotalReq addSportTotalReq) {
        return (AddSportTotalRsp) lqi.d().d(addSportTotalReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addSportTotalReq)), AddSportTotalRsp.class);
    }

    public GetSportStatRsp b(GetSportStatReq getSportStatReq) {
        return (GetSportStatRsp) lqi.d().d(getSportStatReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSportStatReq)), GetSportStatRsp.class);
    }

    public GetSportDimenStatRsp b(GetSportDimenStatisticsReq getSportDimenStatisticsReq) {
        return (GetSportDimenStatRsp) lqi.d().d(getSportDimenStatisticsReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSportDimenStatisticsReq)), GetSportDimenStatRsp.class);
    }

    public AddHealthDataRsp d(AddHealthDataReq addHealthDataReq) {
        return (AddHealthDataRsp) lqi.d().d(addHealthDataReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addHealthDataReq)), AddHealthDataRsp.class);
    }

    public GetHealthDataByTimeRsp e(GetHealthDataByTimeReq getHealthDataByTimeReq) {
        return (GetHealthDataByTimeRsp) lqi.d().d(getHealthDataByTimeReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getHealthDataByTimeReq)), GetHealthDataByTimeRsp.class);
    }

    public GetHealthDataByVersionRsp a(GetHealthDataByVersionReq getHealthDataByVersionReq) {
        return (GetHealthDataByVersionRsp) lqi.d().d(getHealthDataByVersionReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getHealthDataByVersionReq)), GetHealthDataByVersionRsp.class);
    }

    public GetHealthDataByReversedOrderRsp e(GetHealthDataByReversedOrderReq getHealthDataByReversedOrderReq) {
        return (GetHealthDataByReversedOrderRsp) lqi.d().d(getHealthDataByReversedOrderReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getHealthDataByReversedOrderReq)), GetHealthDataByReversedOrderRsp.class);
    }

    public DelHealthDataRsp a(DeleteHealthDataReq deleteHealthDataReq) {
        return (DelHealthDataRsp) lqi.d().d(deleteHealthDataReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteHealthDataReq)), DelHealthDataRsp.class);
    }

    public DelHealthDataRsp e(DeleteDataByTimeReq deleteDataByTimeReq) {
        return (DelHealthDataRsp) lqi.d().d(deleteDataByTimeReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteDataByTimeReq)), DelHealthDataRsp.class);
    }

    public DeleteHealthStatRsp c(DeleteHealthStatReq deleteHealthStatReq) {
        return (DeleteHealthStatRsp) lqi.d().d(deleteHealthStatReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteHealthStatReq)), DeleteHealthStatRsp.class);
    }

    public AddMotionPathRsp a(AddMotionPathReq addMotionPathReq) {
        return (AddMotionPathRsp) lqi.d().d(addMotionPathReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addMotionPathReq)), AddMotionPathRsp.class);
    }

    public GetMotionPathByVersionRsp a(GetMotionPathByVersionReq getMotionPathByVersionReq) {
        return (GetMotionPathByVersionRsp) lqi.d().d(getMotionPathByVersionReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getMotionPathByVersionReq)), GetMotionPathByVersionRsp.class);
    }

    public DeleteMotionPathRsp d(DeleteMotionPathReq deleteMotionPathReq) {
        return (DeleteMotionPathRsp) lqi.d().d(deleteMotionPathReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteMotionPathReq)), DeleteMotionPathRsp.class);
    }

    public void a(double d, double d2, ICloudOperationResult<jca> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.d(d, d2, iCloudOperationResult);
        }
    }

    public void e(ThirdOauthTokenO thirdOauthTokenO, final ICloudOperationResult<ThirdOauthTokenI> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter getOauthAtWithCode = ", Integer.valueOf(thirdOauthTokenO.getThirdAccountType()));
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAuthToken", thirdOauthTokenO);
        lqi.d().b(GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataOpen/third/getAtWithCode", b(), lql.b(this.f13715a.getBody(hashMap)), ThirdOauthTokenI.class, new ResultCallback<ThirdOauthTokenI>() { // from class: jbs.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ThirdOauthTokenI thirdOauthTokenI) {
                LogUtil.a("HWCloudMgr", "getOauthAtWithCode resultCode ", Integer.valueOf(thirdOauthTokenI.getResultCode()));
                iCloudOperationResult.operationResult(thirdOauthTokenI, thirdOauthTokenI.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " getOauthAtWithRt exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    private Map<String, String> b() {
        Map<String, String> headers = this.f13715a.getHeaders();
        headers.put("Content-Encoding", HealthEngineRequestManager.CONTENT_TYPE);
        return headers;
    }

    public void a(ThirdOauthTokenO thirdOauthTokenO, final ICloudOperationResult<ThirdOauthTokenI> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter getOauthAtWithRt = ", Integer.valueOf(thirdOauthTokenO.getThirdAccountType()));
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAuthToken", thirdOauthTokenO);
        lqi.d().b(GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataOpen/third/getAtWithRt", b(), lql.b(this.f13715a.getBody(hashMap)), ThirdOauthTokenI.class, new ResultCallback<ThirdOauthTokenI>() { // from class: jbs.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ThirdOauthTokenI thirdOauthTokenI) {
                LogUtil.a("HWCloudMgr", "getOauthAtWithRt resultCode ", Integer.valueOf(thirdOauthTokenI.getResultCode()));
                iCloudOperationResult.operationResult(thirdOauthTokenI, thirdOauthTokenI.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " getOauthAtWithRt exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void d(int i, final ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter cancelOauthAuthorization = ", Integer.valueOf(i));
        HashMap hashMap = new HashMap();
        hashMap.put("thirdAccountType", Integer.valueOf(i));
        lqi.d().b(GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataOpen/third/cancelThirdAuthorization", b(), lql.b(this.f13715a.getBody(hashMap)), CloudCommonReponse.class, new ResultCallback<CloudCommonReponse>() { // from class: jbs.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                LogUtil.a("HWCloudMgr", "cancelOauthAuthorization resultCode ", cloudCommonReponse.getResultCode());
                iCloudOperationResult.operationResult(cloudCommonReponse, cloudCommonReponse.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " cancelOauthAuthorization exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void e(AddEvaluationResultReq addEvaluationResultReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(addEvaluationResultReq, iCloudOperationResult);
        }
    }

    public void d(GetEvaluationResultReq getEvaluationResultReq, ICloudOperationResult<GetEvaluationResultRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(getEvaluationResultReq, iCloudOperationResult);
        }
    }

    public void d(DelEvaluationResultReq delEvaluationResultReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(delEvaluationResultReq, iCloudOperationResult);
        }
    }

    public SetUserProfileRsp e() {
        SetUserProfileReq setUserProfileReq = new SetUserProfileReq();
        return (SetUserProfileRsp) lqi.d().d(setUserProfileReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(setUserProfileReq)), SetUserProfileRsp.class);
    }

    public void d(WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceControlDataModelReq, iCloudOperationResult);
        }
    }

    public void c(WifiDeviceUnbindReq wifiDeviceUnbindReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceUnbindReq, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq, ICloudOperationResult<WifiDeviceGetWifiDeviceInfoRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.d(wifiDeviceGetWifiDeviceInfoReq, iCloudOperationResult);
        }
    }

    public void b(WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq, ICloudOperationResult<WifiDeviceServiceInfoRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(wifiDeviceServiceInfoReq, iCloudOperationResult);
        }
    }

    public void d(ICloudOperationResult<WifiDeviceGetAllDeviceRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.b(iCloudOperationResult);
        }
    }

    public void a(WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq, ICloudOperationResult<WifiDeviceGetDeviceStatusRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(wifiDeviceGetDeviceStatusReq, iCloudOperationResult);
        }
    }

    public void d(WifiDeviceAddAuthorizeForSubUserReq wifiDeviceAddAuthorizeForSubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.c(wifiDeviceAddAuthorizeForSubUserReq, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq, ICloudOperationResult<WifiDeviceGetAuthorizeSubUserRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.c(wifiDeviceGetAuthorizeSubUserReq, iCloudOperationResult);
        }
    }

    public void e(WifiDeviceUpdateAuthorizeSubUserReq wifiDeviceUpdateAuthorizeSubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.d(wifiDeviceUpdateAuthorizeSubUserReq, iCloudOperationResult);
        }
    }

    public void b(WifiDeviceDeleteAuthorizeSubUserReq wifiDeviceDeleteAuthorizeSubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(wifiDeviceDeleteAuthorizeSubUserReq, iCloudOperationResult);
        }
    }

    public void e(WifiDeviceExitAuthorizeSubUserReq wifiDeviceExitAuthorizeSubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.c(wifiDeviceExitAuthorizeSubUserReq, iCloudOperationResult);
        }
    }

    public void c(ICloudOperationResult<WifiDeviceGetVerifyCodeForMainUserRsp> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(iCloudOperationResult);
        }
    }

    public void a(WifiDeviceAddAuthMsgBySubUserReq wifiDeviceAddAuthMsgBySubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceAddAuthMsgBySubUserReq, iCloudOperationResult);
        }
    }

    public void c(WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceGetSubUserAuthMsgReq, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceAuthorizeByMainUserReq wifiDeviceAuthorizeByMainUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceAuthorizeByMainUserReq, iCloudOperationResult);
        }
    }

    public void e(StartDeviceLinkageRequest startDeviceLinkageRequest, final ICloudOperationResult<StartDeviceLinkageResponse> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter startDeviceLinkage");
        Map<String, Object> d = lql.d(startDeviceLinkageRequest);
        String c = c();
        if (c == null) {
            iCloudOperationResult.operationResult(null, "unknown url", false);
            return;
        }
        String str = c + "/deviceAgent/startDeviceLinkage";
        LogUtil.a("HWCloudMgr", "startDeviceLinkage url:", str);
        lqi.d().b(str, this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(d)), StartDeviceLinkageResponse.class, new ResultCallback<StartDeviceLinkageResponse>() { // from class: jbs.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(StartDeviceLinkageResponse startDeviceLinkageResponse) {
                LogUtil.a("HWCloudMgr", "StartDeviceLinkageResponse resultCode ", startDeviceLinkageResponse.getResultCode());
                iCloudOperationResult.operationResult(startDeviceLinkageResponse, startDeviceLinkageResponse.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " StartDeviceLinkageResponse exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    private String c() {
        String b2;
        String url = GRSManager.a(BaseApplication.e()).getUrl("healthDeviceUrl");
        return (CommonUtil.as() && (b2 = SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(10008), "key_download_config")) != null && b2.contains("lfhealthtest2")) ? b2 : url;
    }

    public void a(StopDeviceLinkageRequest stopDeviceLinkageRequest, final ICloudOperationResult<StopDeviceLinkageResponse> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter stopDeviceLinkage");
        Map<String, Object> d = lql.d(stopDeviceLinkageRequest);
        String c = c();
        if (c == null) {
            iCloudOperationResult.operationResult(null, "unknown url", false);
            return;
        }
        String str = c + "/deviceAgent/stopDeviceLinkage";
        LogUtil.a("HWCloudMgr", "stopDeviceLinkage url:", str);
        lqi.d().b(str, this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(d)), StopDeviceLinkageResponse.class, new ResultCallback<StopDeviceLinkageResponse>() { // from class: jbs.10
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(StopDeviceLinkageResponse stopDeviceLinkageResponse) {
                LogUtil.a("HWCloudMgr", "StopDeviceLinkageRequest resultCode ", stopDeviceLinkageResponse.getResultCode());
                iCloudOperationResult.operationResult(stopDeviceLinkageResponse, stopDeviceLinkageResponse.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " StopDeviceLinkageRequest exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void a(TransferDeviceDataRequest transferDeviceDataRequest, final ICloudOperationResult<TransferDeviceDataResponse> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter transDeviceData");
        Map<String, Object> d = lql.d(transferDeviceDataRequest);
        String c = c();
        if (c == null) {
            iCloudOperationResult.operationResult(null, "unknown url", false);
            return;
        }
        String str = c + "/deviceAgent/transDeviceData";
        LogUtil.a("HWCloudMgr", "transDeviceData url:", str);
        lqi.d().b(str, this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(d)), TransferDeviceDataResponse.class, new ResultCallback<TransferDeviceDataResponse>() { // from class: jbs.7
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(TransferDeviceDataResponse transferDeviceDataResponse) {
                LogUtil.a("HWCloudMgr", "transDeviceData resultCode ", transferDeviceDataResponse.getResultCode());
                iCloudOperationResult.operationResult(transferDeviceDataResponse, transferDeviceDataResponse.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " transDeviceData exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void d(ReleaseDeviceLinkageRequest releaseDeviceLinkageRequest, final ICloudOperationResult<ReleaseDeviceLinkageResponse> iCloudOperationResult) {
        LogUtil.a("HWCloudMgr", "Enter releaseDeviceLinkage");
        Map<String, Object> d = lql.d(releaseDeviceLinkageRequest);
        String c = c();
        if (c == null) {
            iCloudOperationResult.operationResult(null, "unknown url", false);
            return;
        }
        String str = c + "/deviceAgent/releaseDeviceLinkage";
        LogUtil.a("HWCloudMgr", "releaseDeviceLinkage url:", str);
        lqi.d().b(str, this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(d)), ReleaseDeviceLinkageResponse.class, new ResultCallback<ReleaseDeviceLinkageResponse>() { // from class: jbs.8
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ReleaseDeviceLinkageResponse releaseDeviceLinkageResponse) {
                LogUtil.a("HWCloudMgr", "releaseDeviceLinkage resultCode ", releaseDeviceLinkageResponse.getResultCode());
                iCloudOperationResult.operationResult(releaseDeviceLinkageResponse, releaseDeviceLinkageResponse.getResultDesc(), true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HWCloudMgr", " releaseDeviceLinkage exception :", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void e(DeviceLinkResultNotifyRequest deviceLinkResultNotifyRequest, ICloudOperationResult<DeviceLinkResultNotifyResponse> iCloudOperationResult) {
        if (deviceLinkResultNotifyRequest == null || iCloudOperationResult == null) {
            LogUtil.h("HWCloudMgr", "deviceLinkResultNotify param is null.please check.");
            return;
        }
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.c(deviceLinkResultNotifyRequest, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceShareByMainUserReq wifiDeviceShareByMainUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.d(wifiDeviceShareByMainUserReq, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceSubuserAuthorize wifiDeviceSubuserAuthorize, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.b(wifiDeviceSubuserAuthorize, iCloudOperationResult);
        }
    }

    public void e(WifiDeviceGetMainUserAuth wifiDeviceGetMainUserAuth, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.e(wifiDeviceGetMainUserAuth, iCloudOperationResult);
        }
    }

    public void a(WifiDeviceGetDeviceRegistration wifiDeviceGetDeviceRegistration, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.a(wifiDeviceGetDeviceRegistration, iCloudOperationResult);
        }
    }

    public void c(WifiDeviceShareMemberInfoBySubUserReq wifiDeviceShareMemberInfoBySubUserReq, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbx jbxVar = this.c;
        if (jbxVar != null) {
            jbxVar.b(wifiDeviceShareMemberInfoBySubUserReq, iCloudOperationResult);
        }
    }

    public void c(jal jalVar, ResultCallback<jaq> resultCallback) {
        lqi.d().b(jalVar.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(jalVar)), jaq.class, resultCallback);
    }

    public jas e(jar jarVar) {
        return (jas) lqi.d().d(jarVar.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(jarVar)), jas.class);
    }

    public void e(jaw jawVar, ResultCallback<CloudCommonReponse> resultCallback) {
        lqi.d().b(jawVar.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(jawVar)), CloudCommonReponse.class, resultCallback);
    }

    public void d(jaf jafVar, ResultCallback<jae> resultCallback) {
        String e = KeyValDbManager.b(BaseApplication.e()).e("user_id");
        if (TextUtils.isEmpty(e)) {
            LogUtil.a("HWCloudMgr", "huid isEmpty");
            resultCallback.onFailure(new Throwable("huid is empty"));
        } else {
            if ("0".equals(e)) {
                LogUtil.b("HWCloudMgr", "huid is 0");
                resultCallback.onFailure(new Throwable("huid is 0"));
                return;
            }
            Map<String, Object> body = this.f13715a.getBody(jafVar);
            body.put("deviceId", e);
            Map<String, String> headers = this.f13715a.getHeaders();
            headers.put("x-version", c(CommonUtil.e(BaseApplication.e())));
            lqi.d().b(jafVar.getUrl(), headers, lql.b(body), jae.class, resultCallback);
        }
    }

    public void a(String str, jaf jafVar, ResultCallback<jae> resultCallback) {
        String e = KeyValDbManager.b(BaseApplication.e()).e("user_id");
        if (TextUtils.isEmpty(e)) {
            LogUtil.a("HWCloudMgr", "huid isEmpty");
            resultCallback.onFailure(new Throwable("huid is empty"));
            return;
        }
        if ("0".equals(e)) {
            LogUtil.b("HWCloudMgr", "huid is 0");
            resultCallback.onFailure(new Throwable("huid is 0"));
            return;
        }
        Map<String, Object> body = this.f13715a.getBody(jafVar);
        if (!TextUtils.isEmpty(str)) {
            body.put("abTestFlowID", str);
        }
        body.put("deviceId", e);
        Map<String, String> headers = this.f13715a.getHeaders();
        headers.put("x-version", c(CommonUtil.e(BaseApplication.e())));
        LogUtil.a("HWCloudMgr", "url: ", jafVar.getUrl(), ", header: ", headers, ", body: ", lql.b(body));
        lqi.d().b(jafVar.getUrl(), headers, lql.b(body), jae.class, resultCallback);
    }

    public String c(String str) {
        int indexOf;
        return (TextUtils.isEmpty(str) || (indexOf = str.indexOf(Constants.LINK)) <= 0) ? str : str.substring(0, indexOf);
    }

    public <T extends CloudCommonReponse, V extends CloudCommonRequest> void e(String str, V v, final ICloudOperationResult<T> iCloudOperationResult, Class<T> cls) {
        lqi.d().b(GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + str, this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(v)), cls, new ResultCallback<T>() { // from class: jbs.6
            /* JADX WARN: Incorrect types in method signature: (TT;)V */
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                if (cloudCommonReponse.getResultCode().intValue() == 0) {
                    LogUtil.a("HWCloudMgr", "callHttpPost in operationResult successful");
                    iCloudOperationResult.operationResult(cloudCommonReponse, null, true);
                    return;
                }
                LogUtil.h("HWCloudMgr", "callHttpPost in operationResult fail:", cloudCommonReponse.getResultCode());
                iCloudOperationResult.operationResult(cloudCommonReponse, "code:" + cloudCommonReponse.getResultCode(), false);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public jan e(jak jakVar) {
        return (jan) lqi.d().d(jakVar.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(jakVar)), jan.class);
    }

    public EcgServiceActivationData d(EcgIvActivationRequest ecgIvActivationRequest) {
        return (EcgServiceActivationData) lqi.d().d(ecgIvActivationRequest.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(ecgIvActivationRequest)), EcgServiceActivationData.class);
    }

    public EcgGiftCardResponse d(EcgGiftCardRequest ecgGiftCardRequest) {
        return (EcgGiftCardResponse) lqi.d().d(ecgGiftCardRequest.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(ecgGiftCardRequest)), EcgGiftCardResponse.class);
    }

    public DeleteSampleSequenceRsq d(DeleteSampleSequenceRep deleteSampleSequenceRep) {
        return (DeleteSampleSequenceRsq) lqi.d().d(deleteSampleSequenceRep.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteSampleSequenceRep)), DeleteSampleSequenceRsq.class);
    }

    public void b(DeleteAllUserProfileReq deleteAllUserProfileReq, ResultCallback<DeleteAllUserProfileRsp> resultCallback) {
        lqi.d().b(deleteAllUserProfileReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(deleteAllUserProfileReq)), DeleteAllUserProfileRsp.class, resultCallback);
    }

    public void e(ResultCallback<LastClearCloudDataTimeRsp> resultCallback) {
        LastClearCloudDataTimeReq lastClearCloudDataTimeReq = new LastClearCloudDataTimeReq();
        lqi.d().b(lastClearCloudDataTimeReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(lastClearCloudDataTimeReq)), LastClearCloudDataTimeRsp.class, resultCallback);
    }

    public SurveyIdResponse e(SurveyIdRequest surveyIdRequest) {
        return (SurveyIdResponse) lqi.d().d(surveyIdRequest.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(surveyIdRequest)), SurveyIdResponse.class);
    }

    public void c(AddPrivacyRecordReq addPrivacyRecordReq, final ICloudOperationResult<AddPrivacyRecordRsp> iCloudOperationResult) {
        lqi.d().b(addPrivacyRecordReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(addPrivacyRecordReq)), AddPrivacyRecordRsp.class, new ResultCallback<AddPrivacyRecordRsp>() { // from class: jbs.9
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(AddPrivacyRecordRsp addPrivacyRecordRsp) {
                int intValue = addPrivacyRecordRsp.getResultCode().intValue();
                if (intValue == 0) {
                    iCloudOperationResult.operationResult(addPrivacyRecordRsp, null, true);
                    return;
                }
                iCloudOperationResult.operationResult(null, "code:" + intValue, false);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public void a(GetPrivacyRecordReq getPrivacyRecordReq, final ICloudOperationResult<GetPrivacyRecordRsp> iCloudOperationResult) {
        lqi.d().b(getPrivacyRecordReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getPrivacyRecordReq)), GetPrivacyRecordRsp.class, new ResultCallback<GetPrivacyRecordRsp>() { // from class: jbs.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetPrivacyRecordRsp getPrivacyRecordRsp) {
                iCloudOperationResult.operationResult(getPrivacyRecordRsp, null, true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    public String e(WechatDeviceSignReq wechatDeviceSignReq) {
        return (String) lqi.d().d(wechatDeviceSignReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(wechatDeviceSignReq)), String.class);
    }

    public String a(WechatDeviceRegistReq wechatDeviceRegistReq) {
        return (String) lqi.d().d(wechatDeviceRegistReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(wechatDeviceRegistReq)), String.class);
    }

    public GetSleepSportsDataLatestRsp a(GetSleepSportsDataLatestReq getSleepSportsDataLatestReq) {
        return (GetSleepSportsDataLatestRsp) lqi.d().d(getSleepSportsDataLatestReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getSleepSportsDataLatestReq)), GetSleepSportsDataLatestRsp.class);
    }

    public GetHealthDataLatestRsp c(GetHealthDataLatestReq getHealthDataLatestReq) {
        return (GetHealthDataLatestRsp) lqi.d().d(getHealthDataLatestReq.getUrl(), this.f13715a.getHeaders(), lql.b(this.f13715a.getBody(getHealthDataLatestReq)), GetHealthDataLatestRsp.class);
    }
}
