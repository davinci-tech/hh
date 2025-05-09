package com.huawei.ui.main.stories.nps.interactors.mode;

/* loaded from: classes7.dex */
public class QuestionSurveyDetailResponse {
    private String firstTime;
    private int queryTimes;
    private String reason;
    private int resCode;
    private QuestionnaireInfo surveyContent = new QuestionnaireInfo();
    private String surveyID;

    public int getResCode() {
        return this.resCode;
    }

    public void setResCode(int i) {
        this.resCode = i;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public int getQueryTimes() {
        return this.queryTimes;
    }

    public void setQueryTimes(int i) {
        this.queryTimes = i;
    }

    public String getFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(String str) {
        this.firstTime = str;
    }

    public QuestionnaireInfo getSurveyContent() {
        return this.surveyContent;
    }

    public void setSurveyContent(QuestionnaireInfo questionnaireInfo) {
        this.surveyContent = questionnaireInfo;
    }

    public String getSurveyID() {
        return this.surveyID;
    }

    public void setSurveyID(String str) {
        this.surveyID = str;
    }

    public String toString() {
        return "QuestionSurveyDetailResponse{resCode=" + this.resCode + ", reason='" + this.reason + "', firstTime='" + this.firstTime + "', queryTimes=" + this.queryTimes + ", surveyID='" + this.surveyID + "', surveyContent=" + this.surveyContent + '}';
    }
}
