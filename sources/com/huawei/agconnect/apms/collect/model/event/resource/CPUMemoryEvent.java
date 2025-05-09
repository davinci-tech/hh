package com.huawei.agconnect.apms.collect.model.event.resource;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.x;
import com.huawei.agconnect.apms.y;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class CPUMemoryEvent extends Event {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    private List<y> cpuResourceList = new ArrayList();
    private List<x> appMemoryResourceList = new ArrayList();

    private JsonArray changeCpuAsJsonArray(List<y> list) {
        JsonArray jsonArray = new JsonArray();
        for (y yVar : list) {
            if (yVar != null) {
                jsonArray.add(yVar.asJsonArray());
            }
        }
        return jsonArray;
    }

    private JsonArray changeMemoryAsJsonArray(List<x> list) {
        JsonArray jsonArray = new JsonArray();
        for (x xVar : list) {
            if (xVar != null) {
                jsonArray.add(xVar.asJsonArray());
            }
        }
        return jsonArray;
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(changeCpuAsJsonArray(this.cpuResourceList));
        jsonArray.add(changeMemoryAsJsonArray(this.appMemoryResourceList));
        return jsonArray;
    }

    public JSONObject changeCpuAndMemoryAsJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cpu", changeCpuAsJsonArray(this.cpuResourceList));
            jSONObject.put("memory", changeMemoryAsJsonArray(this.appMemoryResourceList));
        } catch (JSONException unused) {
            LOG.warn("JSONException while occurrence changeCpuAndMemoryAsJsonObject.");
        }
        return jSONObject;
    }

    public List<x> getAppMemoryResourceList() {
        return this.appMemoryResourceList;
    }

    public List<y> getCpuResourceList() {
        return this.cpuResourceList;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
