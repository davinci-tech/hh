package com.huawei.hmf.orb.tbis.type;

import android.content.Intent;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes9.dex */
public class IntentRef extends ObjectRef {
    private Intent mIntent;

    public IntentRef() {
        this.mIntent = new Intent();
    }

    public IntentRef(Intent intent) {
        this.mIntent = intent;
    }

    @Override // com.huawei.hmf.orb.tbis.type.ObjectRef, com.huawei.hmf.orb.tbis.TBNativeType.Unboxable
    /* renamed from: unboxing */
    public Object unboxing2() {
        Intent intent = (Intent) super.unboxing2();
        if (intent != null) {
            this.mIntent = intent;
        }
        return getIntent();
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public void setAction(String str) {
        this.mIntent.setAction(str);
    }

    public String getAction() {
        return this.mIntent.getAction();
    }

    public void setPackage(String str) {
        this.mIntent.setPackage(str);
    }

    public String getPackage() {
        return this.mIntent.getPackage();
    }

    public void setFlags(int i) {
        this.mIntent.setFlags(i);
    }

    public int getFlags() {
        return this.mIntent.getFlags();
    }

    public Object getExtra(String str) {
        if (this.mIntent.getExtras() == null) {
            return null;
        }
        return this.mIntent.getExtras().get(str);
    }

    public void setExtra(String str, Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            this.mIntent.putExtra(str, (String) obj);
            return;
        }
        if (obj instanceof Integer) {
            this.mIntent.putExtra(str, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Short) {
            this.mIntent.putExtra(str, (Short) obj);
            return;
        }
        if (obj instanceof Long) {
            this.mIntent.putExtra(str, (Long) obj);
            return;
        }
        if (obj instanceof Float) {
            this.mIntent.putExtra(str, (Float) obj);
            return;
        }
        if (obj instanceof Double) {
            this.mIntent.putExtra(str, (Double) obj);
            return;
        }
        if (obj instanceof Boolean) {
            this.mIntent.putExtra(str, (Boolean) obj);
            return;
        }
        if (obj instanceof CharSequence) {
            this.mIntent.putExtra(str, (CharSequence) obj);
            return;
        }
        if (obj instanceof Parcelable) {
            this.mIntent.putExtra(str, (Parcelable) obj);
        } else if (obj instanceof byte[]) {
            this.mIntent.putExtra(str, (byte[]) obj);
        } else if (obj instanceof Serializable) {
            this.mIntent.putExtra(str, (Serializable) obj);
        }
    }

    public void setExtra(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setExtra(entry.getKey(), entry.getValue());
        }
    }
}
