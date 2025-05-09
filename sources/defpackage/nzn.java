package defpackage;

import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nzn extends nzc {
    private static final long serialVersionUID = 6236452322340363024L;

    /* renamed from: a, reason: collision with root package name */
    private List<nzr> f15568a = new ArrayList(8);
    protected DeclarationConstants.PartType e;

    public void d(DeclarationConstants.PartType partType) {
        this.e = partType;
    }

    public List<nzr> e() {
        return this.f15568a;
    }

    public void a(nzr nzrVar) {
        this.f15568a.add(nzrVar);
    }
}
