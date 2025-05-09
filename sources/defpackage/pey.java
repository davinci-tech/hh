package defpackage;

import android.content.Context;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.ui.main.stories.about.AboutApi;

@ApiDefine(uri = AboutApi.class)
@Singleton
/* loaded from: classes6.dex */
public class pey implements AboutApi {
    @Override // com.huawei.ui.main.stories.about.AboutApi
    public String getAw70HelpUrl(int i, Context context) {
        return pex.a().b(i, context);
    }

    @Override // com.huawei.ui.main.stories.about.AboutApi
    public String adaptUrlInMessageCenter(Context context, int i, String str) {
        return pex.a().c(context, i, str);
    }
}
