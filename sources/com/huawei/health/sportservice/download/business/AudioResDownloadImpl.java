package com.huawei.health.sportservice.download.business;

import android.app.Activity;
import android.content.Context;
import com.huawei.health.sportservice.download.CustomDownloadManager;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import defpackage.fhc;
import defpackage.fhd;
import defpackage.mxh;
import health.compact.a.LogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AudioResDownloadImpl implements AudioResProviderInterface {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface
    public void queryOrDownAudioResource(Activity activity, mxh mxhVar, String str, AudioResDownloadListener audioResDownloadListener) {
        if (Utils.l()) {
            LogUtil.a("SportService_AudioResDownloadImpl", "isOverseaNoCloudVersion");
            audioResDownloadListener.onResult(0);
            return;
        }
        CustomDownloadManager customDownloadManager = new CustomDownloadManager(activity);
        ArrayList arrayList = new ArrayList();
        fhc fhcVar = new fhc(activity, mxhVar.e(), mxhVar.b(), mxhVar.c(), str);
        fhd fhdVar = new fhd(activity, "MultilingualAudio");
        fhcVar.e(fhdVar);
        arrayList.add(fhdVar);
        arrayList.add(fhcVar);
        customDownloadManager.e(arrayList, audioResDownloadListener);
        fhcVar.startQueryState();
    }
}
