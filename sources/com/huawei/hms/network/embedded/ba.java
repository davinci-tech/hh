package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.util.List;

/* loaded from: classes9.dex */
public interface ba {

    /* renamed from: a, reason: collision with root package name */
    public static final ba f5190a = new a();

    void a(int i, r9 r9Var);

    boolean a(int i, db dbVar, int i2, boolean z) throws IOException;

    boolean a(int i, List<s9> list);

    boolean a(int i, List<s9> list, boolean z);

    public class a implements ba {
        @Override // com.huawei.hms.network.embedded.ba
        public void a(int i, r9 r9Var) {
        }

        @Override // com.huawei.hms.network.embedded.ba
        public boolean a(int i, List<s9> list) {
            return true;
        }

        @Override // com.huawei.hms.network.embedded.ba
        public boolean a(int i, List<s9> list, boolean z) {
            return true;
        }

        @Override // com.huawei.hms.network.embedded.ba
        public boolean a(int i, db dbVar, int i2, boolean z) throws IOException {
            dbVar.skip(i2);
            return true;
        }
    }
}
