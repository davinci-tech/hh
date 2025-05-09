package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;

@ApiDefine(uri = HmsAuthApi.class)
@Singleton
/* loaded from: classes4.dex */
public class ipm implements HmsAuthApi {
    @Override // com.huawei.hihealthservice.hmsauth.HmsAuthApi
    public int hmsAuth(String str, int i, boolean z) {
        int b = ipl.b(BaseApplication.e()).b(str, i, z);
        if (b == 8) {
            return new AppStatusHelper(BaseApplication.e()).c(HiScopeUtil.c(BaseApplication.e(), str), z ? 1 : 2, i);
        }
        return b;
    }

    @Override // com.huawei.hihealthservice.hmsauth.HmsAuthApi
    public boolean isValidRedirectUrl(String str, String str2, String str3) {
        return new iru().b(str, str2, str3);
    }

    @Override // com.huawei.hihealthservice.hmsauth.HmsAuthApi
    public boolean isValidSignature(String str, String str2, String str3) {
        return new iru().e(str, str2, str3);
    }
}
