package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

/* loaded from: classes5.dex */
public class jta {

    /* renamed from: a, reason: collision with root package name */
    private static jta f14061a;
    private static final Map<String, String> c = new ConcurrentHashMap(24);
    private static final ConcurrentHashMap<Integer, Boolean> b = new ConcurrentHashMap<>(24);
    private static final Object e = new Object();

    private jta() {
        synchronized (e) {
            Map<String, String> map = c;
            map.clear();
            map.putAll(e());
        }
    }

    public static jta d() {
        jta jtaVar;
        synchronized (jta.class) {
            if (f14061a == null) {
                f14061a = new jta();
            }
            jtaVar = f14061a;
        }
        return jtaVar;
    }

    public List<DeviceInfo> d(List<DeviceInfo> list) {
        if (list == null) {
            return list;
        }
        ArrayList<DeviceInfo> arrayList = new ArrayList(list);
        if (arrayList.size() > 0) {
            Map<String, String> map = c;
            if (map.size() == 0) {
                LogUtil.h("HwDeviceRelationManager", "fillDeviceListRelation DEVICES_RELATION size is zero");
                map.putAll(e());
            }
        }
        for (DeviceInfo deviceInfo : arrayList) {
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            Map<String, String> map2 = c;
            if (map2.containsKey(deviceIdentify)) {
                deviceInfo.setRelationship(map2.get(deviceIdentify));
            }
        }
        return arrayList;
    }

    public boolean c(List<DeviceInfo> list, String str) {
        synchronized (e) {
            ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "updateDeviceListRelation tag is ", str);
            if (list == null) {
                LogUtil.h("HwDeviceRelationManager", "updateDeviceListRelation is null.");
                return false;
            }
            ArrayList arrayList = new ArrayList(list);
            if (!c(arrayList)) {
                LogUtil.h("HwDeviceRelationManager", "updateDeviceListRelation relationship is incorrect.");
                return false;
            }
            Iterator<String> it = c.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    Iterator<DeviceInfo> it2 = arrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (next.equals(it2.next().getDeviceIdentify())) {
                                break;
                            }
                        } else {
                            it.remove();
                            break;
                        }
                    }
                }
            }
            kcq.b(c);
            for (DeviceInfo deviceInfo : arrayList) {
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                if (!TextUtils.isEmpty(deviceIdentify)) {
                    c.put(deviceIdentify, deviceInfo.getRelationship());
                    ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "updateDeviceListRelation fuzzData: ", CommonUtil.l(deviceIdentify), " ship: ", deviceInfo.getRelationship());
                }
            }
            c();
            ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "updateDeviceListRelation relationship update success.");
            kcq.e(list);
            return true;
        }
    }

    public String e(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HwDeviceRelationManager", "identify is empty");
            return null;
        }
        Iterator<Map.Entry<String, String>> it = c.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it.next();
            if (str.equals(next.getKey())) {
                str2 = next.getValue();
                break;
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            LogUtil.a("HwDeviceRelationManager", "DEVICES_RELATION has relation is ", str2);
            return str2;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwDeviceRelationManager");
        if (deviceList.size() > 0) {
            str2 = deviceList.get(0).getRelationship();
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "relationDeviceInfoList has relation is ", str2);
        return str2;
    }

    public boolean c(int i) {
        LogUtil.a("HwDeviceRelationManager", "isFollowDevice productType: ", Integer.valueOf(i));
        ConcurrentHashMap<Integer, Boolean> concurrentHashMap = b;
        if (concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return concurrentHashMap.get(Integer.valueOf(i)).booleanValue();
        }
        String d = juu.d(i);
        boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(d);
        LogUtil.a("HwDeviceRelationManager", "is plugin download uuid:", d, ", isPluginAvailable", Boolean.valueOf(isResourcesAvailable));
        if (!isResourcesAvailable) {
            return false;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d);
        LogUtil.a("HwDeviceRelationManager", "isFollowDevice success getPluginInfo.");
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.h("HwDeviceRelationManager", "isFollowDevice info or WearDeviceInfo is null");
            return false;
        }
        if ("followed_relationship".equals(pluginInfoByUuid.f().bi())) {
            concurrentHashMap.put(Integer.valueOf(i), true);
            return true;
        }
        concurrentHashMap.put(Integer.valueOf(i), false);
        return false;
    }

    private boolean c(List<DeviceInfo> list) {
        Iterator<DeviceInfo> it = list.iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            DeviceInfo next = it.next();
            String relationship = next.getRelationship();
            if ("main_relationship".equals(relationship)) {
                i++;
                if (i > 1) {
                    break;
                }
            } else if ("followed_relationship".equals(relationship)) {
                if (!c(next.getProductType()) && !cvt.c(next.getProductType())) {
                    break;
                }
            } else {
                LogUtil.c("HwDeviceRelationManager", "isCorrectRelation else relation.");
            }
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "isCorrectRelation is ", Boolean.valueOf(z));
        return z;
    }

    private void c() {
        Map<String, String> map = c;
        if (map.size() != 0) {
            JSONStringer jSONStringer = new JSONStringer();
            try {
                jSONStringer.array();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONStringer.object();
                    jSONStringer.key("type");
                    jSONStringer.value(entry.getKey());
                    jSONStringer.key("value");
                    jSONStringer.value(entry.getValue());
                    jSONStringer.endObject();
                }
                jSONStringer.endArray();
            } catch (JSONException unused) {
                ReleaseLogUtil.c("DEVMGR_HwDeviceRelationManager", "saveRelationMap JSONException.");
            }
            boolean commit = BaseApplication.getContext().getSharedPreferences("relation", 0).edit().putString("relation", jSONStringer.toString()).commit();
            if (commit) {
                return;
            }
            ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "saveRelationMap isSuccess: ", Boolean.valueOf(commit));
            iyv.c("MultiDeviceMessage", "saveRelationMap isSuccess: " + commit);
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "DEVICES_RELATION is empty");
        boolean commit2 = BaseApplication.getContext().getSharedPreferences("relation", 0).edit().putString("relation", "").commit();
        if (commit2) {
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceRelationManager", "DEVICES_RELATION is empty, saveRelationMap isSuccess: ", Boolean.valueOf(commit2));
        iyv.c("MultiDeviceMessage", "DEVICES_RELATION is empty, saveRelationMap isSuccess: " + commit2);
    }

    private Map<String, String> e() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(24);
        String string = BaseApplication.getContext().getSharedPreferences("relation", 0).getString("relation", "");
        if (string.length() > 0) {
            try {
                Object nextValue = new JSONTokener(string).nextValue();
                if (nextValue instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) nextValue;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        concurrentHashMap.put(jSONObject.getString("type"), jSONObject.getString("value"));
                        LogUtil.a("HwDeviceRelationManager", "getRelationMap fuzzDataType: ", CommonUtil.l(jSONObject.getString("type")), " shipValue: ", jSONObject.getString("value"));
                    }
                }
            } catch (JSONException unused) {
                LogUtil.b("HwDeviceRelationManager", "getRelationMap JSONException.");
                iyv.c("MultiDeviceMessage", "getRelationMap JSONException.");
            }
        }
        return concurrentHashMap;
    }
}
