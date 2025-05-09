package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import defpackage.ayb;
import defpackage.aza;
import defpackage.azi;
import defpackage.koq;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class HealthModelCheckBox extends HealthCheckBox {
    private boolean b;
    private List<ayb> d;
    private ayb e;

    public HealthModelCheckBox(Context context) {
        this(context, null);
    }

    public HealthModelCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthModelCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setData(List<ayb> list, ayb aybVar, boolean z) {
        boolean z2;
        HealthLifeBean b;
        if (koq.b(list) || aybVar == null) {
            LogUtil.h("HealthLife_HealthModelCheckBox", "setData dataList is empty or bean is null");
            return;
        }
        Iterator<ayb> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            ayb next = it.next();
            if (next != null && (b = next.b()) != null && b.getTaskType() == 1) {
                LogUtil.a("HealthLife_HealthModelCheckBox", "setData isBasicType true dbBean ", b.toString());
                z2 = true;
                break;
            }
        }
        this.d = list;
        this.e = aybVar;
        this.b = true;
        HealthLifeBean b2 = aybVar.b();
        if (b2 == null) {
            LogUtil.h("HealthLife_HealthModelCheckBox", "setData healthTaskSubscriptionDbBean is null");
            return;
        }
        super.setChecked(b2.getAddStatus() == 1);
        if (z2) {
            if (b2.getTaskType() == 1) {
                b2.setAddStatus(1);
                super.setChecked(true);
            }
        } else if (azi.d().contains(Integer.valueOf(b2.getId()))) {
            b2.setAddStatus(1);
            super.setChecked(true);
        }
        this.b = z;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        ayb aybVar;
        if (this.d == null || (aybVar = this.e) == null || !this.b) {
            return;
        }
        HealthLifeBean b = aybVar.b();
        if (b == null) {
            LogUtil.h("HealthLife_HealthModelCheckBox", "setChecked tomorrowBean is null");
            return;
        }
        int id = b.getId();
        if (!azi.c(id)) {
            LogUtil.h("HealthLife_HealthModelCheckBox", "setChecked taskId is not support");
            return;
        }
        super.setChecked(z);
        aza.e(id, z);
        if (z) {
            if (b.getAddStatus() != 1 && this.e.e() != 3001) {
                LogUtil.a("HealthLife_HealthModelCheckBox", "setChecked tomorrowBean newType is HEALTH_MODEL_NEW_TASK_TYPE");
                this.e.a(3000);
            } else {
                this.e.a(0);
            }
        } else if (this.e.e() == 3000) {
            this.e.a(0);
        } else {
            this.e.a(3001);
        }
        b.setAddStatus(z ? 1 : 0);
    }
}
