package com.huawei.haf.bundle;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class InstallRequest {
    private final List<String> c;

    private InstallRequest(Builder builder) {
        this.c = builder.d;
    }

    public static Builder d() {
        return new Builder();
    }

    public List<String> e() {
        return this.c;
    }

    public static class Builder {
        private final List<String> d;

        private Builder() {
            this.d = new ArrayList();
        }

        public Builder a(String str) {
            if (!TextUtils.isEmpty(str) && !this.d.contains(str)) {
                this.d.add(str);
            }
            return this;
        }

        public InstallRequest d() {
            return new InstallRequest(this);
        }
    }
}
