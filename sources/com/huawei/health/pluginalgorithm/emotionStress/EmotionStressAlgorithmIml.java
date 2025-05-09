package com.huawei.health.pluginalgorithm.emotionStress;

import com.huawei.emotion.alg.EmotionAlg;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.pluginalgorithms.EmotionStressAdviceApi;

@ApiDefine(uri = EmotionStressAdviceApi.class)
@Singleton
/* loaded from: classes3.dex */
public class EmotionStressAlgorithmIml implements EmotionStressAdviceApi {
    @Override // com.huawei.pluginalgorithms.EmotionStressAdviceApi
    public String getVersion() {
        return EmotionAlg.GetEmotionHealthVersion();
    }

    @Override // com.huawei.pluginalgorithms.EmotionStressAdviceApi
    public String getEmotionStressAdvice(String str) {
        EmotionAlg.GetEmotionHealthVersion();
        return EmotionAlg.EmotionAdviceInterface(str);
    }
}
