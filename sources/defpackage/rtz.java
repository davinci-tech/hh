package defpackage;

import android.content.Context;
import com.huawei.health.main.api.CloudAuthApi;
import com.huawei.health.main.model.AppLangItem;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.Utils;
import java.util.Collections;
import java.util.List;

@ApiDefine(uri = CloudAuthApi.class)
/* loaded from: classes7.dex */
public class rtz implements CloudAuthApi {
    private rua e;

    @Override // com.huawei.health.main.api.CloudAuthApi
    public void constructInstance(Context context, String str) {
        this.e = new rua(context, str);
    }

    @Override // com.huawei.health.main.api.CloudAuthApi
    public String getLang() {
        rua ruaVar = this.e;
        return ruaVar != null ? ruaVar.a() : "";
    }

    @Override // com.huawei.health.main.api.CloudAuthApi
    public String getAppLangItemUrl(String str, String str2) {
        rua ruaVar = this.e;
        return ruaVar != null ? ruaVar.c(str, str2) : "";
    }

    @Override // com.huawei.health.main.api.CloudAuthApi
    public List<AppLangItem> getAppLangItem(String str, String str2, String str3) {
        if (!Utils.i()) {
            return Collections.emptyList();
        }
        rua ruaVar = this.e;
        if (ruaVar != null) {
            return ruaVar.c(str, str2, str3);
        }
        return Collections.emptyList();
    }

    @Override // com.huawei.health.main.api.CloudAuthApi
    public String getScopeLangItemUrl(String str, String str2) {
        rua ruaVar = this.e;
        return ruaVar != null ? ruaVar.b(str, str2) : "";
    }

    @Override // com.huawei.health.main.api.CloudAuthApi
    public ehp getScopeLangItem(String str, String str2, String str3) {
        rua ruaVar = this.e;
        if (ruaVar != null) {
            return ruaVar.e(str, str2, str3);
        }
        return null;
    }
}
