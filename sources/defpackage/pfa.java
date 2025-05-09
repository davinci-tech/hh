package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.main.api.MainCommonApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;

@ApiDefine(uri = MainCommonApi.class)
@Singleton
/* loaded from: classes6.dex */
public class pfa implements MainCommonApi {
    @Override // com.huawei.health.main.api.MainCommonApi
    public String getPersonalPrivacySettingValue(int i) {
        return rvo.e(BaseApplication.e()).a(i);
    }

    @Override // com.huawei.health.main.api.MainCommonApi
    public Bitmap getQuickAppIcon(Context context, String str) {
        return rhx.dOB_(context, str);
    }

    @Override // com.huawei.health.main.api.MainCommonApi
    public Drawable getSportIconDrawable(Context context, int i) {
        return rdu.dMy_(context, i);
    }

    @Override // com.huawei.health.main.api.MainCommonApi
    public int getSportIconBackgroundColor(Context context, int i) {
        return rdu.b(context, i);
    }

    @Override // com.huawei.health.main.api.MainCommonApi
    public String getSportName(Context context, int i) {
        return rdu.d(i, context);
    }
}
