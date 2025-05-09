package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import com.huawei.aifitness.bodyrebuild.bean.MeasureBean;
import com.huawei.aifitness.bodyrebuild.bean.PreTreatBean;
import com.huawei.aifitness.bodyrebuild.listener.OnCodeListener;
import com.huawei.aifitness.bodyrebuild.listener.OnSensorListener;
import com.huawei.aifitness.bodyrebuild.listener.OnViewTransformListener;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi;
import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class mmn extends AppBundlePluginProxy<BodyShapeAnalysisAlgorithmApi> implements BodyShapeAnalysisAlgorithmApi {
    private static volatile mmn d;
    private BodyShapeAnalysisAlgorithmApi e;

    private mmn() {
        super("BodyShapeAnalysisAlgorithmProxy", "PluginBodyRebuild", "com.huawei.health.pluginbodyrebuild.impl.BodyShapeAnalysisAlgorithmIml");
        LogUtil.d("BodyShapeAnalysisAlgorithmProxy", "createPluginApi");
        this.e = createPluginApi();
    }

    public static mmn b() {
        mmn mmnVar;
        if (d == null) {
            synchronized (mmn.class) {
                if (d == null) {
                    d = new mmn();
                }
                mmnVar = d;
            }
            return mmnVar;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.e != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(BodyShapeAnalysisAlgorithmApi bodyShapeAnalysisAlgorithmApi) {
        this.e = bodyShapeAnalysisAlgorithmApi;
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setCodeListener(OnCodeListener onCodeListener) {
        if (isPluginAvaiable()) {
            this.e.setCodeListener(onCodeListener);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setCurStep(int i) {
        if (isPluginAvaiable()) {
            this.e.setCurStep(i);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public MeasureBean getMeasureByWeight(MeasureBean measureBean, float f, float f2, int i, float f3) {
        if (isPluginAvaiable()) {
            return this.e.getMeasureByWeight(measureBean, f, f2, i, f3);
        }
        loadPlugin();
        return null;
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void registerSensor(Context context, OnSensorListener onSensorListener) {
        if (isPluginAvaiable()) {
            this.e.registerSensor(context, onSensorListener);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void unregisterSensor(Context context) {
        if (isPluginAvaiable()) {
            this.e.unregisterSensor(context);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public PreTreatBean preTreatImg(Bitmap bitmap, float[] fArr, int i, int i2) {
        if (isPluginAvaiable()) {
            return this.e.preTreatImg(bitmap, fArr, i, i2);
        }
        loadPlugin();
        return null;
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public int initModel(Context context, int i, int i2) {
        if (isPluginAvaiable()) {
            return this.e.initModel(context, i, i2);
        }
        LogUtil.d("BodyShapeAnalysisAlgorithmProxy", "initModel pluginApi not available");
        loadPlugin();
        return -10;
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setImage(Image image) {
        if (isPluginAvaiable()) {
            this.e.setImage(image);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void unloadModel() {
        if (isPluginAvaiable()) {
            this.e.unloadModel();
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setModelInfo(int i, int i2, int i3) {
        if (isPluginAvaiable()) {
            this.e.setModelInfo(i, i2, i3);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public Class<? extends JsModuleBase> getBodyShapeAnalysisJsApi(String str) {
        if (isPluginAvaiable()) {
            return this.e.getBodyShapeAnalysisJsApi(str);
        }
        LogUtil.d("BodyShapeAnalysisAlgorithmProxy", "getCommonJsModule loadPlugin");
        loadPlugin();
        return JsModuleBase.class;
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setViewTransformListener(OnViewTransformListener onViewTransformListener) {
        if (isPluginAvaiable()) {
            this.e.setViewTransformListener(onViewTransformListener);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginbodyrebuild.BodyShapeAnalysisAlgorithmApi
    public void setTransformView(Bitmap bitmap) {
        if (isPluginAvaiable()) {
            this.e.setTransformView(bitmap);
        } else {
            loadPlugin();
        }
    }
}
