package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.interaction.AppStartEvent;

/* loaded from: classes2.dex */
public class z {
    public static volatile z abc;
    public long def;
    public long efg;
    public long fgh;
    public long ghi;
    public long hij;
    public long cde = Agent.getCreateTime();
    public boolean bcd = true;

    public static class abc implements Runnable {
        public long abc;
        public String bcd;
        public String cde;
        public long def;
        public long efg;
        public long fgh;

        public abc(long j, String str, String str2, long j2, long j3, long j4) {
            this.abc = j;
            this.bcd = str;
            this.cde = str2;
            this.def = j2;
            this.efg = j3;
            this.fgh = j4;
        }

        @Override // java.lang.Runnable
        public void run() {
            rst.cde.add(new AppStartEvent(this.abc, this.bcd, this.cde, this.def, this.efg, this.fgh));
        }
    }

    public static z abc() {
        if (abc == null) {
            synchronized (z.class) {
                if (abc == null) {
                    abc = new z();
                }
            }
        }
        return abc;
    }

    public final void bcd() {
        this.cde = 0L;
        this.def = 0L;
        this.efg = 0L;
        this.fgh = 0L;
        this.ghi = 0L;
        this.hij = 0L;
    }

    public final void abc(long j, String str, String str2, long j2, long j3, long j4) {
        if (j2 <= 0 || j2 >= nop.abc().ghi) {
            return;
        }
        Agent.getExecutor().execute(new abc(j, str, str2, j2, j3, j4));
    }
}
