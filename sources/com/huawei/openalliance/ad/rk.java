package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.vast.VastContent;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes5.dex */
public class rk extends rc {
    @Override // com.huawei.openalliance.ad.rc
    protected void a(XmlPullParser xmlPullParser, VastContent vastContent) {
    }

    @Override // com.huawei.openalliance.ad.rc
    public List<VastContent> a(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        try {
            return b(xmlPullParser).a();
        } catch (Throwable th) {
            ho.c("Vast30Parser", "read vast content error: %s", th.getClass().getSimpleName());
            return arrayList;
        }
    }
}
