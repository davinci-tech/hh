package com.huawei.phoneservice.feedbackcommon.entity;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class o {

    @SerializedName("data")
    private List<d> b = new ArrayList();

    /* loaded from: classes9.dex */
    public class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("read")
        private boolean f8327a;

        @SerializedName("srcno")
        private String b;

        public boolean d() {
            return !TextUtils.isEmpty(this.b);
        }

        public boolean e() {
            return this.f8327a;
        }
    }

    public List<d> d() {
        return this.b;
    }
}
