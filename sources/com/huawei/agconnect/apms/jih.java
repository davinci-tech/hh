package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.squareup.okhttp.Dns;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes8.dex */
public class jih implements Dns {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public Dns bcd;
    public wxy cde;

    public jih(Dns dns, wxy wxyVar) {
        this.bcd = dns;
        this.cde = wxyVar;
    }

    public List<InetAddress> lookup(String str) throws UnknownHostException {
        wxy wxyVar = this.cde;
        nml nmlVar = new nml(str, 0L);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            List<InetAddress> lookup = this.bcd.lookup(str);
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            String str2 = wxyVar.bcd;
            if (str2 != null && str2.contains(str)) {
                AgentLog agentLog = xyz.abc;
                if (currentTimeMillis2 >= 8) {
                    nmlVar.cde = currentTimeMillis2;
                    nmlVar.efg = true;
                    if (lookup != null) {
                        nmlVar.def = lookup.toString();
                    }
                    wxyVar.abc(nmlVar);
                    wxyVar.fed();
                }
            }
            return lookup;
        } catch (UnknownHostException e) {
            if (!wxyVar.edc()) {
                wxyVar.yxw++;
            }
            nmlVar.efg = false;
            throw e;
        }
    }
}
