package com.huawei.health.motiontrack.api;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import defpackage.emb;
import defpackage.emh;
import defpackage.emi;
import defpackage.emk;
import defpackage.emm;
import defpackage.emn;
import defpackage.emo;
import defpackage.emp;
import defpackage.emq;
import defpackage.emr;
import defpackage.ems;
import defpackage.emt;
import defpackage.emu;
import defpackage.emv;
import defpackage.emw;
import defpackage.emx;
import defpackage.emy;
import defpackage.emz;
import defpackage.enb;
import defpackage.enc;
import defpackage.ene;

/* loaded from: classes3.dex */
public interface RunningRouteApi {
    void addHotPathParticipateInfo(emb embVar, UiCallback<CloudCommonReponse> uiCallback);

    @Deprecated
    void deleteFeedbackByPathId(emh emhVar, UiCallback<CloudCommonReponse> uiCallback);

    @Deprecated
    void deleteFeedbackInfo(emi emiVar, UiCallback<CloudCommonReponse> uiCallback);

    void feedbackHotPathInfo(emn emnVar, UiCallback<CloudCommonReponse> uiCallback);

    void getAllCityInfoList(emm emmVar, UiCallback<emo> uiCallback);

    void getCityInfoList(emk emkVar, UiCallback<emt> uiCallback);

    void getCityInfoWithGps(emq emqVar, UiCallback<emp> uiCallback);

    @Deprecated
    void getFeedbackInfos(emr emrVar, UiCallback<ems> uiCallback);

    void getHotPathDetail(emu emuVar, UiCallback<emv> uiCallback);

    void getHotPathDetail(String str, UiCallback<enc> uiCallback);

    void getHotPathParticipateInfo(emy emyVar, UiCallback<emw> uiCallback);

    void getHotPathParticipateInfos(emx emxVar, UiCallback<enb> uiCallback);

    void getHotPaths(ene eneVar, UiCallback<emz> uiCallback);

    void removeLocationUpdates();

    void requestLocationUpdates(ILocationChangeListener iLocationChangeListener);
}
