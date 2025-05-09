package defpackage;

import com.huawei.health.device.model.RecordAction;

/* loaded from: classes5.dex */
class lov {
    private String b;
    private String e;

    lov(String str, String str2) {
        this.b = str;
        this.e = str2;
    }

    public String e() {
        return this.b;
    }

    public String b() {
        return this.e;
    }

    public String toString() {
        return RecordAction.ACT_NAME_TAG + this.b + ", value=" + this.e;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof lov)) {
            return false;
        }
        lov lovVar = (lov) obj;
        return d(this.b, lovVar.b) && d(this.e, lovVar.e);
    }

    private boolean d(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
