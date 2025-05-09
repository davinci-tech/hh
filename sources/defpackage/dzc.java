package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.PluginDeviceAdapter;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginbase.PluginBaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApiDefine(uri = PluginDeviceApi.class)
/* loaded from: classes3.dex */
public class dzc implements PluginDeviceApi {
    @Override // com.huawei.health.device.api.PluginDeviceApi
    public void init(Context context) {
        cej.e().init(context);
    }

    @Override // com.huawei.health.device.api.PluginDeviceApi
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        cej.e().setAdapter(pluginBaseAdapter);
    }

    @Override // com.huawei.health.device.api.PluginDeviceApi
    public PluginBaseAdapter getAdapter() {
        return cej.e().getAdapter();
    }

    @Override // com.huawei.health.device.api.PluginDeviceApi
    public void checkAutoTrackStatus() {
        PluginDeviceAdapter c = cej.e().c();
        if (c != null) {
            c.checkAutoTrackStatus();
        }
    }

    @Override // com.huawei.health.device.api.PluginDeviceApi
    public List<cjv> getThirdDeviceList() {
        ArrayList<ContentValues> f = ceo.d().f();
        ArrayList arrayList = new ArrayList(16);
        Iterator<ContentValues> it = f.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (next == null) {
                LogUtil.h("PluginDeviceImpl", "getThirdDeviceList contentValue is null ");
            } else {
                String asString = next.getAsString("productId");
                String asString2 = next.getAsString("uniqueId");
                if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                    LogUtil.h("PluginDeviceImpl", "getThirdDeviceList : productId or deviceIdentify is empty");
                } else {
                    dcz d = ResourceManager.e().d(asString);
                    if (d == null || d.n() == null || d.n().d() == null) {
                        LogUtil.h("PluginDeviceImpl", "getThirdDeviceList productInfo is null or manifest is null");
                    } else if (!d.n().d().trim().isEmpty()) {
                        cjv b = dbp.b(d);
                        b.FU_(next);
                        b.a(new cke("deviceUsedTime").b(cpp.a(), next.getAsString("uniqueId")));
                        arrayList.add(b);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.health.device.api.PluginDeviceApi
    public ArrayList<ContentValues> getBondedDevice() {
        return ceo.d().f();
    }
}
