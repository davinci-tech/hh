package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.vast.Impression;
import com.huawei.openalliance.ad.beans.vast.VastContent;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.rh;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rm implements rh.a {

    /* renamed from: a, reason: collision with root package name */
    private String f7510a;
    private VastContent b;
    private XmlPullParser c;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.openalliance.ad.rh.a
    public void a() {
        char c;
        if (this.c == null || this.b == null || TextUtils.isEmpty(this.f7510a)) {
            return;
        }
        if (ho.a()) {
            ho.a("InlineHandle", "handle: %s", this.f7510a);
        }
        String str = this.f7510a;
        str.hashCode();
        switch (str.hashCode()) {
            case -1692490108:
                if (str.equals(VastTag.CREATIVES)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1633884078:
                if (str.equals(VastTag.AD_SYSTEM)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -56677412:
                if (str.equals(VastTag.DESCRIPTION)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 501930965:
                if (str.equals(VastTag.AD_TITLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2065545547:
                if (str.equals(VastTag.ADVERTISER)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2114088489:
                if (str.equals(VastTag.IMPRESSION)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            VastContent vastContent = this.b;
            vastContent.a(rh.c(this.c, vastContent));
            return;
        }
        if (c == 1) {
            String attributeValue = this.c.getAttributeValue(VastTag.NAMESPACE, "version");
            this.b.b(rh.a(this.c));
            this.b.c(attributeValue);
        } else {
            if (c == 2) {
                this.b.e(rh.a(this.c));
                return;
            }
            if (c == 3) {
                this.b.d(rh.a(this.c));
                return;
            }
            if (c == 4) {
                this.b.f(rh.a(this.c));
            } else if (c != 5) {
                ho.b("InlineHandle", "unsupported tag: %s", this.f7510a);
            } else {
                this.b.a(new Impression(this.c.getAttributeValue(VastTag.NAMESPACE, "id"), rh.a(this.c)));
            }
        }
    }

    public rm(String str, VastContent vastContent, XmlPullParser xmlPullParser) {
        this.f7510a = str;
        this.b = vastContent;
        this.c = xmlPullParser;
    }
}
