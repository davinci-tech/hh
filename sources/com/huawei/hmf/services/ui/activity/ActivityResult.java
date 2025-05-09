package com.huawei.hmf.services.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.huawei.hmf.annotation.ActivityDefine;
import com.huawei.hmf.services.codec.MessageCodec;
import com.huawei.hmf.services.ui.internal.ActivityData;
import com.huawei.hmf.services.ui.internal.PojoGenerator;
import com.huawei.hmf.services.ui.internal.SecurityIntent;

/* loaded from: classes9.dex */
public class ActivityResult<T> {
    private static final String TAG = "ActivityResult";
    private PojoGenerator<T> mPojoGenerator;
    private T mResult;
    private String mResultType;

    private ActivityResult(Activity activity) {
        this.mResultType = new ActivityData(activity.getIntent()).getResultType();
        Class<?> result = ((ActivityDefine) activity.getClass().getAnnotation(ActivityDefine.class)).result();
        if (result.isInterface()) {
            PojoGenerator<T> pojoGenerator = new PojoGenerator<>(result);
            this.mPojoGenerator = pojoGenerator;
            this.mResult = pojoGenerator.get();
        } else {
            try {
                this.mResult = (T) result.newInstance();
            } catch (Exception unused) {
            }
        }
    }

    private ActivityResult(Intent intent) {
        T t;
        if (intent == null) {
            return;
        }
        SecurityIntent from = SecurityIntent.from(intent);
        String stringExtra = from.getStringExtra("__ResultClassname__");
        this.mResultType = stringExtra;
        if (stringExtra == null) {
            return;
        }
        try {
            Class<?> cls = Class.forName(stringExtra);
            if (cls.isInterface()) {
                t = (T) new PojoGenerator(cls);
            } else {
                t = (T) cls.newInstance();
            }
            new MessageCodec().decode(from.getBundleExtra("__Result__"), (Bundle) t);
            if (t instanceof PojoGenerator) {
                this.mResult = (T) ((PojoGenerator) t).get();
            } else {
                this.mResult = t;
            }
        } catch (Exception e) {
            Log.e(TAG, "Instancing ActivityResult exception.", e);
        }
    }

    public T get() {
        return this.mResult;
    }

    public Intent toIntent() {
        Intent intent = new Intent();
        T t = this.mResult;
        if (t == null) {
            return intent;
        }
        PojoGenerator<T> pojoGenerator = this.mPojoGenerator;
        if (pojoGenerator != null) {
            t = pojoGenerator;
        }
        intent.putExtra("__ResultClassname__", this.mResultType);
        intent.putExtra("__Result__", new MessageCodec().encode(t, new Bundle()));
        return intent;
    }

    public static <R> ActivityResult<R> create(Activity activity) {
        return new ActivityResult<>(activity);
    }

    public static <R> ActivityResult<R> createFromResultIntent(Intent intent) {
        return new ActivityResult<>(intent);
    }
}
