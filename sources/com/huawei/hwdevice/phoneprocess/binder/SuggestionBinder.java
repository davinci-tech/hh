package com.huawei.hwdevice.phoneprocess.binder;

import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.SuggestionAidl;
import health.compact.a.Services;

/* loaded from: classes9.dex */
public class SuggestionBinder extends SuggestionAidl.Stub {
    @Override // com.huawei.health.suggestion.SuggestionAidl
    public boolean postFitnessRecord(WorkoutRecord workoutRecord) {
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi != null) {
            return fitnessAdviceApi.uploadFitnessRecordData(workoutRecord);
        }
        return false;
    }
}
