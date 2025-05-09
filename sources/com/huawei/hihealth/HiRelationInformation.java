package com.huawei.hihealth;

import android.content.ContentValues;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class HiRelationInformation {

    @SerializedName("mValueHolder")
    private ContentValues b;

    @SerializedName("mRelationType")
    private int c;

    @SerializedName("mRelationId")
    private int d;

    public int b() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }
}
