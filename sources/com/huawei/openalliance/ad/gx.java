package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.views.VideoView;

/* loaded from: classes9.dex */
public class gx implements gy {

    /* renamed from: a, reason: collision with root package name */
    private int f6892a;
    private int b;
    private int c;
    private boolean d;
    private VideoView e;
    private VideoInfo f;
    private gt g;
    private int h = 0;
    private Context i;
    private String j;

    @Override // com.huawei.openalliance.ad.gy
    public void b() {
        this.h = 0;
    }

    @Override // com.huawei.openalliance.ad.gy
    public int a(boolean z, boolean z2) {
        ho.b("LinkedAlertAndPlayStrategy", "switchToNetworkConnected, isWifi: %s, needDataAlert: %s", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (this.e == null) {
            return -1;
        }
        return z ? d() : a(z2);
    }

    @Override // com.huawei.openalliance.ad.gy
    public int a(int i, boolean z) {
        this.h = i;
        ho.a("LinkedAlertAndPlayStrategy", "startPlayVideo， mManualOrAuto %d", Integer.valueOf(i));
        if (!TextUtils.isEmpty(this.j) && !com.huawei.openalliance.ad.utils.cz.j(this.j)) {
            return i + 100;
        }
        if (!com.huawei.openalliance.ad.utils.bv.e(this.i)) {
            return 1;
        }
        if (c() && !os.G(this.g.g())) {
            int autoPlayNetwork = this.g.m().getAutoPlayNetwork();
            ho.b("LinkedAlertAndPlayStrategy", "use videoConfig, autoPlay netWork is : %s, needDataAlert: %s", Integer.valueOf(autoPlayNetwork), Boolean.valueOf(z));
            if ((autoPlayNetwork == 2 || autoPlayNetwork == 0) && !com.huawei.openalliance.ad.utils.bv.c(this.i) && z) {
                return i + 200;
            }
            if (autoPlayNetwork == 2) {
                return i + 100;
            }
            if (autoPlayNetwork == 1 || autoPlayNetwork == 0) {
                return i + 100;
            }
        }
        return (com.huawei.openalliance.ad.utils.bv.c(this.i) || this.c == 1) ? i + 100 : !z ? i + 100 : this.h + 200;
    }

    @Override // com.huawei.openalliance.ad.gy
    public int a() {
        ho.a("LinkedAlertAndPlayStrategy", "switchToNoNetwork");
        if (this.e == null) {
            return -1;
        }
        if (TextUtils.isEmpty(this.j) || com.huawei.openalliance.ad.utils.cz.j(this.j)) {
            return 1;
        }
        return this.h == 0 ? 102 : 0;
    }

    private int d() {
        ho.a("LinkedAlertAndPlayStrategy", "switchWifi, mManualOrAuto is %d", Integer.valueOf(this.h));
        if (this.h == 0) {
            this.h = 2;
        }
        return this.h + 100;
    }

    private boolean c() {
        gt gtVar = this.g;
        return (gtVar == null || gtVar.m() == null) ? false : true;
    }

    private int a(boolean z) {
        ho.b("LinkedAlertAndPlayStrategy", "switchData, needDataAlert is %s， autoPlayNet is %s", Boolean.valueOf(z), Integer.valueOf(this.c));
        if (!z || this.c == 1) {
            return this.h + 100;
        }
        if (!TextUtils.isEmpty(this.j) && !com.huawei.openalliance.ad.utils.cz.j(this.j)) {
            return this.h + 100;
        }
        if (this.h == 0) {
            this.h = 1;
        }
        return this.h + 200;
    }

    private int a(gt gtVar, VideoInfo videoInfo) {
        if (gtVar == null) {
            return 0;
        }
        return (gtVar.m() == null || (os.G(gtVar.g()) && videoInfo != null)) ? videoInfo.getAutoPlayNetwork() : gtVar.m().getAutoPlayNetwork();
    }

    public gx(Context context, VideoView videoView, VideoInfo videoInfo, gt gtVar) {
        this.i = context;
        this.e = videoView;
        this.f = videoInfo;
        this.c = a(gtVar, videoInfo);
        this.f6892a = this.f.getDownloadNetwork();
        this.b = this.f.getVideoPlayMode();
        this.d = this.f.f();
        this.g = gtVar;
        String a2 = com.huawei.openalliance.ad.utils.ao.a(this.i, videoInfo);
        this.j = a2;
        ho.b("LinkedAlertAndPlayStrategy", "mediaPath %s", com.huawei.openalliance.ad.utils.cz.f(a2));
        ho.a("LinkedAlertAndPlayStrategy", "isDirectReturn %s", Boolean.valueOf(this.d));
    }
}
