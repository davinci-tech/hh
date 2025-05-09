package com.huawei.ui.main.stories.nps.interactors.mode;

/* loaded from: classes7.dex */
public class CreateCommitAnswer {
    private String answer;
    private int questionId;

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int i) {
        this.questionId = i;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String toString() {
        return "CreateCommitAnswer{questionId=" + this.questionId + ", answer='" + this.answer + "'}";
    }
}
