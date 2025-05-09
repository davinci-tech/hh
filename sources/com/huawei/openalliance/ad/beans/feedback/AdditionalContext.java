package com.huawei.openalliance.ad.beans.feedback;

import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import java.util.List;

/* loaded from: classes5.dex */
public class AdditionalContext {
    private String advertiseTitle = null;
    private String creativeId = "";
    private String slotId = "";
    private String landingPageUrl = null;
    private List<String> imageUrl = null;
    private List<String> videoUrl = null;
    private String advertiseId = null;

    public String toString() {
        return this.advertiseId + Constants.LINK + this.advertiseTitle + Constants.LINK + this.creativeId + Constants.LINK + this.slotId + Constants.LINK + a() + b() + c();
    }

    public void e(String str) {
        this.advertiseId = str;
    }

    public void d(String str) {
        this.landingPageUrl = str;
    }

    public void c(String str) {
        this.slotId = str;
    }

    public void b(List<String> list) {
        this.videoUrl = list;
    }

    public void b(String str) {
        this.creativeId = str;
    }

    public void a(List<String> list) {
        this.imageUrl = list;
    }

    public void a(String str) {
        this.advertiseTitle = str;
    }

    private String c() {
        if (cz.b(this.landingPageUrl)) {
            return "";
        }
        return "clickUrl:-" + cz.f(this.landingPageUrl);
    }

    private String b() {
        if (bg.a(this.videoUrl)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("videoUrl:");
        for (String str : this.videoUrl) {
            sb.append(Constants.LINK);
            sb.append(cz.f(str));
        }
        return sb.toString();
    }

    private String a() {
        if (bg.a(this.imageUrl)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("imageUrl");
        for (String str : this.imageUrl) {
            sb.append(Constants.LINK);
            sb.append(cz.f(str));
        }
        return sb.toString();
    }
}
