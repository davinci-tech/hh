package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.SingleGridContent;
import java.util.List;

/* loaded from: classes3.dex */
public class HomePageRecommendTemplate extends BaseTemplate {
    private static final String TAG = "HomePageRecommendTemplate";

    @SerializedName("description")
    private String description;

    @SerializedName("descriptionVisibility")
    private boolean descriptionVisibility;

    @SerializedName("gridContents")
    private List<SingleGridContent> gridContents;

    @SerializedName("recommendCard")
    private String recommendCard;

    @SerializedName("resourceLabel")
    private String resourceLabel;

    @SerializedName("resourceSceneId")
    private String resourceSceneId;

    @SerializedName("resourceSceneName")
    private String resourceSceneName;

    @SerializedName("sortingRules")
    private String sortingRules;

    @SerializedName("theme")
    private String theme;

    @SerializedName("themeVisibility")
    private boolean themeVisibility;
    private boolean walkingLanternSwitch;

    public String getTheme() {
        return this.theme;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public boolean isWalkingLanternSwitch() {
        return this.walkingLanternSwitch;
    }

    public String getResourceSceneId() {
        return this.resourceSceneId;
    }

    public String getResourceSceneName() {
        return this.resourceSceneName;
    }

    public String getResourceLabel() {
        return this.resourceLabel;
    }

    public String getRecommendCard() {
        return this.recommendCard;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public List<SingleGridContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleGridContent> list) {
        this.gridContents = list;
    }
}
