package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.ap;

/* loaded from: classes2.dex */
public final class aq extends ap {

    public static abstract class a implements ap.a {
        public abstract boolean a(aq aqVar);

        public abstract boolean b(aq aqVar);

        public abstract void c(aq aqVar);

        @Override // com.amap.api.col.3sl.ap.a
        public final boolean a(ap apVar) {
            return a((aq) apVar);
        }

        @Override // com.amap.api.col.3sl.ap.a
        public final boolean b(ap apVar) {
            return b((aq) apVar);
        }

        @Override // com.amap.api.col.3sl.ap.a
        public final void c(ap apVar) {
            c((aq) apVar);
        }
    }

    public aq(Context context, a aVar) {
        super(context, aVar);
    }

    public final float j() {
        return (float) (((Math.atan2(g(), f()) - Math.atan2(e(), d())) * 180.0d) / 3.141592653589793d);
    }
}
