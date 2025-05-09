package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class qvm implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int f16613a;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int b;

    @SerializedName("energy")
    private int c;

    @SerializedName("age")
    private int d;

    @SerializedName("birthday")
    private String e;

    @SerializedName("management")
    private String f;

    public qvm(String str, int i) {
        UserInfomation userInfoFromDbSync = LoginInit.getInstance(BaseApplication.e()).getUserInfoFromDbSync();
        if (userInfoFromDbSync == null) {
            ReleaseLogUtil.a("R_DietAnalysisRequest", "DietAnalysisRequest userInfomation is null");
        } else {
            if (userInfoFromDbSync.getGender() == 1) {
                this.b = 2;
            } else {
                this.b = 1;
            }
            this.d = userInfoFromDbSync.getAge();
            this.e = DateFormatUtil.a(DateFormatUtil.c(CommonUtils.h(userInfoFromDbSync.getBirthday()), TimeZone.getDefault()), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        }
        this.f = str;
        this.c = i;
        this.f16613a = CommonUtils.h(eil.a());
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public int e() {
        return this.c;
    }

    public int c() {
        return this.f16613a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthExpansion/diet/analysis";
    }
}
