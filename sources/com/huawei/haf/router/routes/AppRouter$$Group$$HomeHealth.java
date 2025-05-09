package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity;
import com.huawei.ui.homehealth.qrcode.QrCodePretreatmentService;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$HomeHealth implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/HomeHealth/DeviceMoreListActivity", RouteMeta.build(RouteType.ACTIVITY, DeviceMoreListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HomeHealth/QrCodeSchemeActivity", RouteMeta.build(RouteType.ACTIVITY, QrCodeSchemeActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HomeHealth/service/qrcode/pretreatment", RouteMeta.build(RouteType.HANDLER, QrCodePretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
