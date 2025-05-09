package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import javax.annotation.Nullable;

/* loaded from: classes8.dex */
public class StringResourceValueReader {
    private final Resources zzeu;
    private final String zzev;

    public StringResourceValueReader(Context context) {
        Preconditions.checkNotNull(context);
        Resources resources = context.getResources();
        this.zzeu = resources;
        this.zzev = resources.getResourcePackageName(R.string._2130850799_res_0x7f0233ef);
    }

    @Nullable
    public String getString(String str) {
        int identifier = this.zzeu.getIdentifier(str, "string", this.zzev);
        if (identifier == 0) {
            return null;
        }
        return this.zzeu.getString(identifier);
    }
}
