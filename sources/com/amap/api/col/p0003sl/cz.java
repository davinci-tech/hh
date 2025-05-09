package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.maps.MapsInitializer;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class cz extends ka {
    protected boolean isPostFlag = true;

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        return null;
    }

    protected kb makeHttpRequestNeedHeader() throws hm {
        if (z.f1381a != null && hw.a(z.f1381a, dv.a()).f1161a != hw.c.SuccessCode) {
            return null;
        }
        setHttpProtocol(MapsInitializer.getProtocol() == 1 ? ka.c.HTTP : ka.c.HTTPS);
        jz.c();
        if (this.isPostFlag) {
            return jz.a(this);
        }
        return jz.e(this);
    }

    protected byte[] makeHttpRequest() throws hm {
        kb makeHttpRequestNeedHeader = makeHttpRequestNeedHeader();
        if (makeHttpRequestNeedHeader != null) {
            return makeHttpRequestNeedHeader.f1250a;
        }
        return null;
    }

    public byte[] makeHttpRequestWithInterrupted() throws hm {
        setDegradeAbility(ka.a.INTERRUPT_IO);
        return makeHttpRequest();
    }
}
