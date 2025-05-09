package com.huawei.appgallery.coreservice.internal.support.parcelable.b;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public interface c<T> {
    void a(Parcel parcel, Field field, int i, T t, int i2, boolean z);

    void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map);
}
