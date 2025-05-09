package com.huawei.ui.main.stories.nps.interactors.mode;

import java.util.List;

/* loaded from: classes7.dex */
public class QuestionnaireInfo {
    private String endDesc;
    private Integer id;
    private String pictureUrl;
    private List<QuestionSureyResponse> questions;
    private String startDesc;
    private String title;

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }

    public String getStartDes() {
        return this.startDesc;
    }

    public void setStartDes(String str) {
        this.startDesc = str;
    }

    public String getEndDes() {
        return this.endDesc;
    }

    public void setEndDes(String str) {
        this.endDesc = str;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<QuestionSureyResponse> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<QuestionSureyResponse> list) {
        this.questions = list;
    }
}
