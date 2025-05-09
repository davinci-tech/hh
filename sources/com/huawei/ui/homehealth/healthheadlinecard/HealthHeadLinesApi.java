package com.huawei.ui.homehealth.healthheadlinecard;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import defpackage.olz;
import defpackage.omc;
import defpackage.omg;
import defpackage.omh;
import defpackage.omj;
import defpackage.omk;
import defpackage.omm;
import java.util.Map;

/* loaded from: classes6.dex */
public interface HealthHeadLinesApi {
    @POST
    Submit<Object> getAudioWorkoutInfo(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omc> getLecturerInfo(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omg> getWorkoutListHistory(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<olz> getWorkoutsByTopicId(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omh> setWorkoutPlay(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omk> userAccumulatedPlayDuration(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omj> userBehaviorList(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @POST
    Submit<omm> userPlayRecord(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
