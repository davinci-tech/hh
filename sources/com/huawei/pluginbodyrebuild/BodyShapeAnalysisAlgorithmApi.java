package com.huawei.pluginbodyrebuild;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import com.huawei.aifitness.bodyrebuild.bean.MeasureBean;
import com.huawei.aifitness.bodyrebuild.bean.PreTreatBean;
import com.huawei.aifitness.bodyrebuild.listener.OnCodeListener;
import com.huawei.aifitness.bodyrebuild.listener.OnSensorListener;
import com.huawei.aifitness.bodyrebuild.listener.OnViewTransformListener;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;

/* loaded from: classes9.dex */
public interface BodyShapeAnalysisAlgorithmApi {
    Class<? extends JsModuleBase> getBodyShapeAnalysisJsApi(String str);

    MeasureBean getMeasureByWeight(MeasureBean measureBean, float f, float f2, int i, float f3);

    int initModel(Context context, int i, int i2);

    PreTreatBean preTreatImg(Bitmap bitmap, float[] fArr, int i, int i2);

    void registerSensor(Context context, OnSensorListener onSensorListener);

    void setCodeListener(OnCodeListener onCodeListener);

    void setCurStep(int i);

    void setImage(Image image);

    void setModelInfo(int i, int i2, int i3);

    void setTransformView(Bitmap bitmap);

    void setViewTransformListener(OnViewTransformListener onViewTransformListener);

    void unloadModel();

    void unregisterSensor(Context context);
}
