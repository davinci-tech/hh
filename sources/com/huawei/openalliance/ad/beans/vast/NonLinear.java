package com.huawei.openalliance.ad.beans.vast;

import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.utils.ci;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class NonLinear {
    private int height;

    @a
    private String htmlResource;

    @a
    private String iframeResource;

    @a
    private String nonLinearClickThrough;
    private String nonLinearId;
    private StaticResource staticResource;
    private List<Tracking> trackings = new ArrayList();
    private int width;

    public void d(String str) {
        this.nonLinearClickThrough = str;
    }

    public void c(String str) {
        this.htmlResource = str;
    }

    public void b(String str) {
        this.iframeResource = str;
    }

    public void b(int i) {
        this.width = i;
    }

    public String b() {
        return this.nonLinearClickThrough;
    }

    public void a(List<Tracking> list) {
        this.trackings = list;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = ci.a(8);
        }
        this.nonLinearId = str;
    }

    public void a(StaticResource staticResource) {
        this.staticResource = staticResource;
    }

    public void a(int i) {
        this.height = i;
    }

    public StaticResource a() {
        return this.staticResource;
    }
}
