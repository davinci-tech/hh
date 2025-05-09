package defpackage;

import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import defpackage.oru;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class osp {
    public static void c(List<String> list) {
        ReleaseLogUtil.b("Track_SportTabOrderUtils", "saveSportTabOrderToLocal begin. sportTypeList = ", list);
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), "sport_type_list", lql.b(new oru(new oru.c(list))), (StorageParams) null);
    }

    public static List<String> e() {
        oru oruVar = (oru) ixu.e(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), "sport_type_list"), new TypeToken<oru>() { // from class: osp.1
        });
        ReleaseLogUtil.b("Track_SportTabOrderUtils", "getSportTabOrderFromLocal = ", oruVar);
        if (oruVar != null && oruVar.e() != null) {
            return oruVar.e().e();
        }
        return new ArrayList();
    }

    public static List<String> c() {
        ReleaseLogUtil.b("Track_SportTabOrderUtils", "getSportTabOrderFromCloud begin.");
        if (!Utils.l() && !LoginInit.getInstance(BaseApplication.e()).isBrowseMode() && !CommonUtil.bu() && !CommonUtil.cc()) {
            HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.e());
            d dVar = new d();
            oru oruVar = (oru) lqi.d().d(dVar.getUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(dVar)), oru.class);
            if (!b(oruVar)) {
                ReleaseLogUtil.c("Track_SportTabOrderUtils", "getSportTabOrderFromCloud failed:", oruVar);
                return null;
            }
            ReleaseLogUtil.b("Track_SportTabOrderUtils", "getSportTabOrderFromCloud success:", oruVar);
            if (oruVar.e() != null) {
                return oruVar.e().e();
            }
        }
        return null;
    }

    public static boolean b(List<String> list) {
        ReleaseLogUtil.b("Track_SportTabOrderUtils", "saveSportTabOrderToCloud begin. sportTypeList = ", list);
        if (Utils.l() || LoginInit.getInstance(BaseApplication.e()).isBrowseMode() || CommonUtil.bu()) {
            return false;
        }
        e eVar = new e();
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.e());
        ReleaseLogUtil.b("Track_SportTabOrderUtils", " saveSportTabOrderToCloud response is=", (CloudCommonReponse) lqi.d().d(eVar.getUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(new oru(new oru.c(list)))), CloudCommonReponse.class));
        return !b(r7);
    }

    private static boolean b(CloudCommonReponse cloudCommonReponse) {
        return (cloudCommonReponse == null || cloudCommonReponse.getResultCode() == null || cloudCommonReponse.getResultCode().intValue() != 0) ? false : true;
    }

    static class d implements IRequest {
        private d() {
        }

        @Override // com.huawei.networkclient.IRequest
        public String getUrl() {
            return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/getSportType";
        }
    }

    static class e implements IRequest {
        private e() {
        }

        @Override // com.huawei.networkclient.IRequest
        public String getUrl() {
            return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/saveSportType";
        }
    }
}
