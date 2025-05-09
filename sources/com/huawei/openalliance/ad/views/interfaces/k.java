package com.huawei.openalliance.ad.views.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.webkit.WebSettings;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.data.RewardEvent;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.utils.av;
import com.huawei.openalliance.ad.views.AppDownloadButton;

/* loaded from: classes5.dex */
public interface k extends jk {
    void a(IRewardAd iRewardAd, boolean z);

    void a(Integer num);

    void a(String str);

    void a(boolean z);

    void a(boolean z, String str);

    void addNonwifiActionListener(INonwifiActionListener iNonwifiActionListener);

    void addRewardAdStatusListener(IRewardAdStatusListener iRewardAdStatusListener);

    void b(boolean z);

    void c();

    boolean c(boolean z);

    void destroyView();

    void e();

    void f();

    void g();

    AppDownloadButton getAppDownloadButton();

    av getAppointJs();

    Context getContext();

    Dialog getNonwifiDialog();

    com.huawei.openalliance.ad.views.t getPopUpView();

    Resources getResources();

    WebSettings getWebViewSettings();

    boolean h();

    void l();

    void m();

    void onEvent(RewardEvent rewardEvent);

    void pauseView();

    void removeRewardAdStatusListener();

    void resumeView();

    void setContentRecord(ContentRecord contentRecord);

    void setNonwifiDialog(Dialog dialog);

    void setOrientation(int i);

    void setTemplateErrorListener(ab abVar);

    void setVisibility(int i);
}
