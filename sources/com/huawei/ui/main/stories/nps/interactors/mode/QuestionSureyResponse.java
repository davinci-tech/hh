package com.huawei.ui.main.stories.nps.interactors.mode;

import java.util.List;

/* loaded from: classes7.dex */
public class QuestionSureyResponse {
    private Integer id;
    private List<QuestionSurveyChooseResponse> options;
    private String pictureUrl;
    private String question;
    private boolean required;
    private String subTitle;
    private String type;

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getTitle() {
        return this.subTitle;
    }

    public void setTitle(String str) {
        this.subTitle = str;
    }

    public List<QuestionSurveyChooseResponse> getChoose() {
        return this.options;
    }

    public void setChoose(List<QuestionSurveyChooseResponse> list) {
        this.options = list;
    }

    public String getQuestionType() {
        return this.type;
    }

    public void setQuestionType(String str) {
        this.type = str;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }
}
