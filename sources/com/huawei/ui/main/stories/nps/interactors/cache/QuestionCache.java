package com.huawei.ui.main.stories.nps.interactors.cache;

import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class QuestionCache {
    private static List<QuestionSureyResponse> sQuestions = new ArrayList(16);

    private QuestionCache() {
    }

    public static List<QuestionSureyResponse> getQuestions() {
        return sQuestions;
    }

    public static void setQuestions(List<QuestionSureyResponse> list) {
        sQuestions = list;
    }
}
