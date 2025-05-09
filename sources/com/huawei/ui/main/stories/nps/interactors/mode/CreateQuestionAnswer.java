package com.huawei.ui.main.stories.nps.interactors.mode;

import java.util.List;

/* loaded from: classes7.dex */
public class CreateQuestionAnswer {
    private List<CreateCommitAnswer> answers;
    private Integer questionId;
    private String questionType;

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer num) {
        this.questionId = num;
    }

    public String getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(String str) {
        this.questionType = str;
    }

    public List<CreateCommitAnswer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<CreateCommitAnswer> list) {
        this.answers = list;
    }
}
