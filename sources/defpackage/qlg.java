package defpackage;

import com.huawei.ui.commonui.base.Consumable;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class qlg implements Consumable<qlh>, Serializable {
    private static final long serialVersionUID = 1;
    private qlh b;
    private Consumable.ConsumableType e;

    public qlg(Consumable.ConsumableType consumableType, qlh qlhVar) {
        this.e = consumableType;
        this.b = qlhVar;
    }

    @Override // com.huawei.ui.commonui.base.Consumable
    public Consumable.ConsumableType getType() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.base.Consumable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public qlh getData() {
        return this.b;
    }
}
