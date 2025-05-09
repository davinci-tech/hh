package com.huawei.openalliance.ad.views.interfaces;

import android.app.Dialog;
import android.os.Bundle;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;

/* loaded from: classes5.dex */
public interface j extends k {
    void a(int i);

    void a(int i, Bundle bundle);

    void a(String str, RewardItem rewardItem);

    boolean a(int i, MaterialClickInfo materialClickInfo);

    void b(String str);

    void d();

    void i();

    void j();

    void k();

    void setDownloadDialog(Dialog dialog);
}
