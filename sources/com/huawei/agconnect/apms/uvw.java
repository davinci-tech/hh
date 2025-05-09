package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.CollectData;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class uvw {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public boolean cde;
    public cde def;
    public CollectData fgh;
    public jkl hij;
    public int bcd = 1;
    public pqr efg = pqr.abc();
    public final Collection<qrs> ghi = new ArrayList();

    public final void abc() {
        if (Agent.isDisabled()) {
            abc.info("Collector: APMS has been disabled, skipping data collection.");
        } else {
            AgentLog agentLog = abc;
            agentLog.debug("Collector: sending [" + this.fgh.getAppStartEvents().count() + "] AppStart events.");
            agentLog.debug("Collector: sending [" + this.fgh.getActivityLoadEvents().count() + "] ActivityLoad events.");
            agentLog.debug("Collector: sending [" + this.fgh.getActivityRenderEvents().count() + "] ActivityRender events.");
            agentLog.debug("Collector: sending [" + this.fgh.getHttpEvents().count() + "] Http events.");
            agentLog.debug("Collector: sending [" + this.fgh.getWebViewLoadEvents().count() + "] WebViewLoad events.");
            agentLog.debug("Collector: sending [" + this.fgh.getWebViewErrorEvents().count() + "] WebViewError events.");
            agentLog.debug("Collector: sending [" + this.fgh.getFpsDropEvents().count() + "] FpsDrop events.");
            if (this.fgh.getEventCount() > 0) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put(HeaderType.AGENT_VERSION, Agent.getVersion());
                linkedHashMap.put("type", Agent.NAME);
                linkedHashMap.put(HeaderType.USER_IDENTIFIER, Agent.getUserIdentifier());
                if (this.fgh.getDeviceInformation() != null) {
                    linkedHashMap.put("device", this.fgh.getDeviceInformation().toJsonString());
                }
                if (this.fgh.getPlatformInformation() != null) {
                    linkedHashMap.put("platform", this.fgh.getPlatformInformation().toJsonString());
                }
                if (this.fgh.getUserSettingsInformation() != null) {
                    linkedHashMap.put(EventType.USER_SETTINGS, this.fgh.getUserSettingsInformation().toJsonString());
                }
                if (this.fgh.getApplicationInformation() != null) {
                    linkedHashMap.put("app", this.fgh.getApplicationInformation().toJsonString());
                }
                linkedHashMap.put(EventType.APP_START, this.fgh.getAppStartEvents().toJsonString());
                linkedHashMap.put(EventType.ACTIVITY_LOAD, this.fgh.getActivityLoadEvents().toJsonString());
                linkedHashMap.put(EventType.ACTIVITY_RENDER, this.fgh.getActivityRenderEvents().toJsonString());
                linkedHashMap.put(EventType.NATIVE_HTTP, this.fgh.getHttpEvents().toJsonString());
                linkedHashMap.put(EventType.WEB_VIEW_LOAD, this.fgh.getWebViewLoadEvents().toJsonString());
                linkedHashMap.put(EventType.WEB_VIEW_ERROR, this.fgh.getWebViewErrorEvents().toJsonString());
                linkedHashMap.put(EventType.FPS_DROP, this.fgh.getFpsDropEvents().toJsonString());
                HiAnalyticsManager.getInstance().onEvent(HiAnalyticsManager.APM_EVENT_ID, linkedHashMap);
            }
        }
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).hij();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollectComplete: " + th.getMessage());
        }
        this.fgh.reset();
    }

    public void bcd() {
        this.cde = false;
        try {
            int i = this.bcd;
            if (i == 1) {
                if (this.def == null) {
                    abc.error("APMS configuration is unavailable.");
                    return;
                } else {
                    abc(2);
                    bcd();
                    return;
                }
            }
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException();
                    }
                    nop.def();
                    ghi();
                    return;
                }
                rst.jkl();
                efg();
                def();
                hij();
                abc();
                return;
            }
            efg();
            if (this.efg == null) {
                pqr abc2 = pqr.abc();
                this.efg = abc2;
                nop.abc(abc2);
            }
            if (this.fgh.isValid()) {
                fgh();
                abc(3);
                bcd();
            } else {
                this.fgh.setValid(true);
                fgh();
                abc(3);
            }
        } catch (Throwable th) {
            abc.error("exception occurred while collecting: " + th.getMessage());
        }
    }

    public final Collection<qrs> cde() {
        return new ArrayList(this.ghi);
    }

    public final void def() {
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).def();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollect: " + th.getMessage());
        }
    }

    public final void efg() {
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).efg();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollectBefore: " + th.getMessage());
        }
    }

    public final void fgh() {
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).bcd();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollectConnected: " + th.getMessage());
        }
    }

    public final void ghi() {
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).cde();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollectDisabled: " + th.getMessage());
        }
    }

    public final void hij() {
        try {
            Iterator it = ((ArrayList) cde()).iterator();
            while (it.hasNext()) {
                ((qrs) it.next()).abc();
            }
        } catch (Throwable th) {
            abc.error("exception occurred while notifying onCollectFinalize: " + th.getMessage());
        }
    }

    public final void abc(int i) {
        int i2;
        if (this.cde || (i2 = this.bcd) == i) {
            return;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    if (!abc(i, 2, 4)) {
                        throw new IllegalStateException();
                    }
                } else {
                    throw new IllegalStateException();
                }
            } else if (!abc(i, 1, 3, 4)) {
                throw new IllegalStateException();
            }
        } else if (!abc(i, 2, i, 3, 4)) {
            throw new IllegalStateException();
        }
        if (this.bcd == 3) {
            if (i == 2) {
                try {
                    Iterator it = ((ArrayList) cde()).iterator();
                    while (it.hasNext()) {
                        ((qrs) it.next()).ghi();
                    }
                } catch (Throwable th) {
                    abc.error("exception occurred while notifying onCollectDisconnected: " + th.getMessage());
                }
            } else if (i == 4) {
                ghi();
            }
        }
        this.bcd = i;
        this.cde = true;
    }

    public final boolean abc(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public void abc(qrs qrsVar) {
        if (qrsVar == null) {
            return;
        }
        synchronized (this.ghi) {
            if (this.ghi.contains(qrsVar)) {
                return;
            }
            this.ghi.add(qrsVar);
        }
    }
}
