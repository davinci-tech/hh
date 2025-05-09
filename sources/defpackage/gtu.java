package defpackage;

import com.huawei.health.sportservice.SportComponent;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class gtu implements SportComponent {
    private Map<String, SportComponent> d = Collections.synchronizedMap(new LinkedHashMap());

    public void e(String str, SportComponent sportComponent) {
        if (StringUtils.i(str) && sportComponent != null) {
            this.d.put(str, sportComponent);
            ReleaseLogUtil.b("Track_SportComponentCenter", "addComponent tag:", str, " component:", sportComponent, " component size:", Integer.valueOf(this.d.size()));
        } else {
            ReleaseLogUtil.a("Track_SportComponentCenter", "addComponent failed with error params. tag:", str, " component:", sportComponent);
        }
    }

    public void d() {
        ReleaseLogUtil.b("Track_SportComponentCenter", "clearComponent.");
        this.d.clear();
    }

    @Override // com.huawei.health.sportservice.SportComponent
    public void init() {
        LogUtil.a("Track_SportComponentCenter", "init() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).init();
        }
        LogUtil.a("Track_SportComponentCenter", "init() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a("Track_SportComponentCenter", "onPreSport() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onPreSport();
        }
        LogUtil.a("Track_SportComponentCenter", "onPreSport() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        LogUtil.a("Track_SportComponentCenter", "onCountDown() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onCountDown();
        }
        LogUtil.a("Track_SportComponentCenter", "onCountDown() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a("Track_SportComponentCenter", "onStartSport() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onStartSport();
        }
        LogUtil.a("Track_SportComponentCenter", "onStartSport() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a("Track_SportComponentCenter", "onResumeSport() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onResumeSport();
        }
        LogUtil.a("Track_SportComponentCenter", "onResumeSport() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a("Track_SportComponentCenter", "onPauseSport() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onPauseSport();
        }
        LogUtil.a("Track_SportComponentCenter", "onPauseSport() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        LogUtil.a("Track_SportComponentCenter", "onStopSport() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).onStopSport();
        }
        LogUtil.a("Track_SportComponentCenter", "onStopSport() finished. components:", linkedHashMap.keySet());
    }

    @Override // com.huawei.health.sportservice.SportComponent
    public void destroy() {
        LogUtil.a("Track_SportComponentCenter", "destroy() begin.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.d);
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((SportComponent) it.next()).destroy();
        }
        LogUtil.a("Track_SportComponentCenter", "destroy() finished. components:", linkedHashMap.keySet());
        d();
    }
}
