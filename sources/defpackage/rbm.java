package defpackage;

import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonRequest;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.stories.healthzone.model.FeedsPostReq;
import com.huawei.ui.main.stories.healthzone.model.GetHealthZoneVerifyCodeUserReq;
import com.huawei.ui.main.stories.healthzone.model.GetSpecifiedAuthUserReq;

/* loaded from: classes7.dex */
public final class rbm {
    public static void a(GetSpecifiedAuthUserReq getSpecifiedAuthUserReq, ICloudOperationResult<ray> iCloudOperationResult) {
        if (getSpecifiedAuthUserReq != null) {
            jbs.a(BaseApplication.getContext()).e("/healthCare/user/getUserListByFollowUser", getSpecifiedAuthUserReq, iCloudOperationResult, ray.class);
        } else {
            jbs.a(BaseApplication.getContext()).e("/healthCare/user/getUserListByFollowUser", new CloudCommonRequest(), iCloudOperationResult, ray.class);
        }
    }

    public static void a(ICloudOperationResult<raz> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).e("/healthCare/user/getUserListByAuthUser", new CloudCommonRequest(), iCloudOperationResult, raz.class);
    }

    public static void a(GetHealthZoneVerifyCodeUserReq getHealthZoneVerifyCodeUserReq, ICloudOperationResult<rbb> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).e("/healthCare/user/getVerifyResult", getHealthZoneVerifyCodeUserReq, iCloudOperationResult, rbb.class);
    }

    public static void b(ICloudOperationResult<rbh> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).e("/healthCare/user/getMemberList", new CloudCommonRequest(), iCloudOperationResult, rbh.class);
    }

    public static void d(ICloudOperationResult<rbg> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).e("/healthCare/abnormalPost/latestPostId", new CloudCommonRequest(), iCloudOperationResult, rbg.class);
    }

    public static void b(FeedsPostReq feedsPostReq, ICloudOperationResult<rau> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).e("/healthCare/data/getFeedsPosts", feedsPostReq, iCloudOperationResult, rau.class);
    }
}
