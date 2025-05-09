package com.huawei.health.marketing.datatype.templates;

import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QuestionnaireTemplate {
    private String correctOption;
    private String description;
    private boolean descriptionVisibility;
    private List<SingleArticleContent> gridContents;
    private String linkValue;
    private String name;
    private boolean nameVisibility;
    private String optionContent;
    private String optionOne;
    private String optionTwo;
    private String picture;
    private Map<String, Long> questionnaireResults;
    private String theme;
    private int themeId;
    private boolean themeVisibility;

    public int getThemeId() {
        return this.themeId;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public String getCorrectOption() {
        return this.correctOption;
    }

    public String getOptionContent() {
        return this.optionContent;
    }

    public Map<String, Long> getQuestionnaireResults() {
        return this.questionnaireResults;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getTheme() {
        return this.theme;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getOptionOne() {
        return this.optionOne;
    }

    public String getOptionTwo() {
        return this.optionTwo;
    }

    public List<SingleArticleContent> getGridContents() {
        return this.gridContents;
    }

    public void setOptionContent(String str) {
        this.optionContent = str;
    }

    public void setQuestionnaireResults(Map<String, Long> map) {
        this.questionnaireResults = map;
    }

    public String toString() {
        return "QuestionnaireTemplate{name='" + this.name + "', nameVisibility=" + this.nameVisibility + ", linkValue='" + this.linkValue + "', theme='" + this.theme + "', themeId=" + this.themeId + ", themeVisibility=" + this.themeVisibility + ", description='" + this.description + "', descriptionVisibility=" + this.descriptionVisibility + ", optionOne='" + this.optionOne + "', optionTwo='" + this.optionTwo + "', correctOption='" + this.correctOption + "'', gridContents=" + this.gridContents + ", optionContent='" + this.optionContent + "', questionnaireResults=" + this.questionnaireResults + '}';
    }
}
