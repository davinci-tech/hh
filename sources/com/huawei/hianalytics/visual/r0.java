package com.huawei.hianalytics.visual;

import android.text.TextUtils;
import com.huawei.hianalytics.framework.utils.JsonUtils;
import com.huawei.hianalytics.visual.view.model.config.AbConfigResponse;
import com.huawei.hianalytics.visual.view.model.config.AutoCollectPackages;
import com.huawei.hianalytics.visual.view.model.config.VisualConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class r0 {

    /* renamed from: a, reason: collision with root package name */
    public final e0 f3947a = (e0) d0.a("visual_config");

    public List<String> a() {
        String a2 = this.f3947a.a();
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        List<AbConfigResponse.AbConfigResult.FeatureConfigValue> c = a.c(a2);
        if (c.isEmpty()) {
            return new ArrayList();
        }
        for (AbConfigResponse.AbConfigResult.FeatureConfigValue featureConfigValue : c) {
            if (TextUtils.equals(featureConfigValue.getParamKey(), "hianalytics_auto_collect_packages")) {
                AutoCollectPackages autoCollectPackages = (AutoCollectPackages) JsonUtils.toObjectNoException(featureConfigValue.getParamValue(), AutoCollectPackages.class, new Class[0]);
                return autoCollectPackages == null ? new ArrayList() : autoCollectPackages.packagesList;
            }
        }
        return new ArrayList();
    }

    public List<VisualConfig> b() {
        String a2 = this.f3947a.a();
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        List<AbConfigResponse.AbConfigResult.FeatureConfigValue> c = a.c(a2);
        if (c.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AbConfigResponse.AbConfigResult.FeatureConfigValue> it = c.iterator();
        while (it.hasNext()) {
            VisualConfig visualConfig = (VisualConfig) JsonUtils.toObjectNoException(it.next().getParamValue(), VisualConfig.class, VisualConfig.VisualEvent.class, VisualConfig.VisualProperty.class);
            if (visualConfig != null && visualConfig.event != null) {
                arrayList.add(visualConfig);
            }
        }
        return arrayList;
    }
}
