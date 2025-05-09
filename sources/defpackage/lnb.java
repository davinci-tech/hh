package defpackage;

import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class lnb {

    /* renamed from: a, reason: collision with root package name */
    private MultiSimDeviceInfo f14776a;
    private List<SimInfo> b;
    private int c;
    private int d;

    public lnb(int i, int i2) {
        this.f14776a = null;
        this.b = null;
        this.d = i;
        this.c = i2;
    }

    public lnb(int i, MultiSimDeviceInfo multiSimDeviceInfo) {
        this.c = 2;
        this.b = null;
        this.d = i;
        this.f14776a = multiSimDeviceInfo;
        if (multiSimDeviceInfo == null) {
            loq.b("AttachedDeviceMgrCommonResult", "multiSimDeviceInfo is null");
            this.c = -6;
        } else {
            this.c = multiSimDeviceInfo.getResultCode();
        }
    }

    public lnb(int i, int i2, List<SimInfo> list) {
        this.f14776a = null;
        this.d = i;
        this.c = i2;
        this.b = list;
    }
}
