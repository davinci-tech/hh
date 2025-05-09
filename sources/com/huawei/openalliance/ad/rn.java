package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.vast.NonLinear;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rn implements rh.a {

    /* renamed from: a, reason: collision with root package name */
    private String f7511a;
    private XmlPullParser b;
    private NonLinear c;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.openalliance.ad.rh.a
    public void a() {
        char c;
        if (this.c == null || this.b == null || TextUtils.isEmpty(this.f7511a)) {
            return;
        }
        ho.b("NonLinearTagHandle", "handle: %s", this.f7511a);
        String str = this.f7511a;
        str.hashCode();
        switch (str.hashCode()) {
            case -375340334:
                if (str.equals(VastTag.IFRAME_RESOURCE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 482633071:
                if (str.equals(VastTag.NON_LINEAR_CLICK_THROUGH)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 676623548:
                if (str.equals(VastTag.STATIC_RESOURCE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1928285401:
                if (str.equals(VastTag.HTML_RESOURCE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.c.b(rh.a(this.b));
            return;
        }
        if (c == 1) {
            this.c.d(rh.a(this.b));
            return;
        }
        if (c == 2) {
            this.c.a(rh.b(this.b));
        } else if (c != 3) {
            ho.b("NonLinearTagHandle", "unsupported tag: %s", this.f7511a);
        } else {
            this.c.c(rh.a(this.b));
        }
    }

    public rn(String str, NonLinear nonLinear, XmlPullParser xmlPullParser) {
        this.f7511a = str;
        this.c = nonLinear;
        this.b = xmlPullParser;
    }
}
