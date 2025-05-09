package com.huawei.openalliance.ad.views.interfaces;

import android.view.View;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.lz;

/* loaded from: classes9.dex */
public interface n extends jk, lm, IViewLifeCycle {
    void a(int i);

    void a(int i, int i2);

    void a(View view, Integer num);

    void a(lz lzVar);

    void b();

    void c();

    void c(int i);

    void d();

    boolean e();

    void g();

    jb getAdMediator();

    void h();

    boolean i();

    void setAdContent(ContentRecord contentRecord);

    void setAdMediator(jb jbVar);

    void setAudioFocusType(int i);

    void setDisplayDuration(int i);
}
