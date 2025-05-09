package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import java.net.URLEncoder;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ib {

    /* renamed from: a, reason: collision with root package name */
    private final ia f6934a;
    private final ic b;
    private final Context c;

    public String a(VideoInfo videoInfo) {
        String a2 = this.f6934a.a(videoInfo.g());
        return com.huawei.openalliance.ad.utils.cz.b(a2) ^ true ? a2 : b(videoInfo);
    }

    private String c(VideoInfo videoInfo) {
        String encode = URLEncoder.encode(videoInfo.g(), "UTF-8");
        boolean isCheckSha256 = videoInfo.isCheckSha256();
        return String.format(Locale.ENGLISH, "http://%s:%s/%s?nonsense=%s&sha256=%s&checkFlag=%s", this.c.getString(R.string._2130851457_res_0x7f023681), Integer.valueOf(this.b.a()), encode, URLEncoder.encode(com.huawei.openalliance.ad.utils.ci.a(8), "UTF-8"), URLEncoder.encode(videoInfo.getSha256(), "UTF-8"), URLEncoder.encode(com.huawei.openalliance.ad.utils.cz.a(Integer.valueOf(!isCheckSha256 ? 1 : 0)), "UTF-8"));
    }

    private String b(VideoInfo videoInfo) {
        ic icVar = this.b;
        if (icVar == null || !icVar.b()) {
            return videoInfo.g();
        }
        try {
            return c(videoInfo);
        } catch (Throwable unused) {
            ho.d("CreativeHttpProxy", "Url encode failed,use origin url");
            return videoInfo.g();
        }
    }

    public ib(Context context, ia iaVar, ic icVar) {
        this.f6934a = iaVar;
        this.b = icVar;
        this.c = context;
    }
}
