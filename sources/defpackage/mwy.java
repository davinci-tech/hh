package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.huaweimapex.HuaweiMapExApi;

/* loaded from: classes6.dex */
public class mwy extends AppBundlePluginProxy<HuaweiMapExApi> implements HuaweiMapExApi {
    private static String c = "PluginHmsMap";
    private static String d = "com.huawei.health.pluginhmsmap.HuaweiMapExImpl";
    private static volatile mwy e;

    /* renamed from: a, reason: collision with root package name */
    private HuaweiMapExApi f15226a;

    private mwy() {
        super("Track_plugin_HuaweiMapExProxy", c, d);
        HuaweiMapExApi createPluginApi = createPluginApi();
        this.f15226a = createPluginApi;
        Object[] objArr = new Object[2];
        objArr[0] = "the mHuaweiMapExApi is null :";
        objArr[1] = Boolean.valueOf(createPluginApi == null);
        LogUtil.a("Track_plugin_HuaweiMapExProxy", objArr);
    }

    public static mwy a() {
        mwy mwyVar;
        LogUtil.a("Track_plugin_HuaweiMapExProxy", "the mHuaweiMapExApi getInstance :");
        if (e == null) {
            synchronized (mwy.class) {
                if (e == null) {
                    e = new mwy();
                }
                mwyVar = e;
            }
            return mwyVar;
        }
        return e;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f15226a != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(HuaweiMapExApi huaweiMapExApi) {
        this.f15226a = huaweiMapExApi;
    }

    @Override // com.huawei.pluginsport.huaweimapex.HuaweiMapExApi
    public boolean set3dMapType(HuaweiMap huaweiMap, boolean z) {
        LogUtil.a("Track_plugin_HuaweiMapExProxy", "enter set3dMapType");
        if (this.f15226a != null) {
            LogUtil.a("Track_plugin_HuaweiMapExProxy", "enter mHuaweiMapExApi != null");
            return this.f15226a.set3dMapType(huaweiMap, z);
        }
        loadPlugin();
        return false;
    }

    @Override // com.huawei.pluginsport.huaweimapex.HuaweiMapExApi
    public boolean set3dScale(HuaweiMap huaweiMap, float f) {
        HuaweiMapExApi huaweiMapExApi = this.f15226a;
        if (huaweiMapExApi != null) {
            return huaweiMapExApi.set3dScale(huaweiMap, f);
        }
        loadPlugin();
        return false;
    }

    @Override // com.huawei.pluginsport.huaweimapex.HuaweiMapExApi
    public Marker set3dMarker(HuaweiMap huaweiMap, hlg hlgVar) {
        LogUtil.a("Track_plugin_HuaweiMapExProxy", "enter set3dMarker");
        if (this.f15226a != null) {
            LogUtil.a("Track_plugin_HuaweiMapExProxy", "enter mHuaweiMapExApi != null,3d marker");
            return this.f15226a.set3dMarker(huaweiMap, hlgVar);
        }
        LogUtil.a("Track_plugin_HuaweiMapExProxy", "enter set3dMarker");
        loadPlugin();
        return null;
    }

    @Override // com.huawei.pluginsport.huaweimapex.HuaweiMapExApi
    public boolean setRotate(Marker marker, float f, float f2, float f3) {
        HuaweiMapExApi huaweiMapExApi = this.f15226a;
        if (huaweiMapExApi != null) {
            return huaweiMapExApi.setRotate(marker, f, f2, f3);
        }
        loadPlugin();
        return false;
    }

    @Override // com.huawei.pluginsport.huaweimapex.HuaweiMapExApi
    public boolean set3dMarkerScale(Marker marker, double d2) {
        HuaweiMapExApi huaweiMapExApi = this.f15226a;
        if (huaweiMapExApi != null) {
            return huaweiMapExApi.set3dMarkerScale(marker, d2);
        }
        loadPlugin();
        return false;
    }
}
