package com.huawei.health.sportservice.download.business;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import defpackage.drd;
import defpackage.mxa;
import defpackage.mxb;
import java.io.File;

/* loaded from: classes.dex */
public class AudioResProviderImpl implements AudioResProviderInterface {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface
    public mxa getAudioResPath(String str, String str2, String str3) {
        LogUtil.a("AudioResProviderImpl", "language= ", str, " speaker= ", str2, " moduleName= ", str3);
        mxa mxaVar = new mxa();
        String b = mxb.a().b(mxb.c());
        String d = drd.d(str3, str + "_" + b, (String) null);
        String d2 = drd.d("Common", str + "_" + b, (String) null);
        StringBuilder sb = new StringBuilder();
        sb.append(d2);
        sb.append(File.separator);
        mxaVar.e(sb.toString());
        mxaVar.c(d + File.separator);
        mxaVar.d(d2 + ".json");
        mxaVar.a(d + ".json");
        return mxaVar;
    }
}
