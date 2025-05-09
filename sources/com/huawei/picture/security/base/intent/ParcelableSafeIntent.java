package com.huawei.picture.security.base.intent;

import android.content.Intent;
import android.os.Parcelable;
import com.huawei.secure.android.common.intent.SafeIntent;
import defpackage.mce;
import defpackage.mcj;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class ParcelableSafeIntent extends SafeIntent {
    private static final String TAG = "ParcelableSafeIntent";

    public ParcelableSafeIntent(Intent intent) {
        super(intent);
    }

    @Override // com.huawei.secure.android.common.intent.SafeIntent, android.content.Intent
    @Deprecated
    public <T extends Parcelable> T getParcelableExtra(String str) {
        return (T) super.getParcelableExtra(str);
    }

    @Override // com.huawei.secure.android.common.intent.SafeIntent, android.content.Intent
    @Deprecated
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String str) {
        return super.getParcelableArrayListExtra(str);
    }

    @Override // android.content.Intent
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String str, Class<T> cls) {
        try {
            ArrayList<T> arrayList = new ArrayList<>();
            ArrayList<T> parcelableArrayListExtra = super.getParcelableArrayListExtra(str);
            if (mce.d(parcelableArrayListExtra)) {
                return null;
            }
            Iterator<T> it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                T next = it.next();
                if (cls.isInstance(next)) {
                    arrayList.add(cls.cast(next));
                }
            }
            return arrayList;
        } catch (Exception e) {
            mcj.d(TAG, "getParcelableArrayListExtra Exception: ", e);
            return null;
        }
    }
}
