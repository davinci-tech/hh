package com.huawei.ui.main.stories.recommendcloud.data;

import com.huawei.hihealth.ResultUtils;

/* loaded from: classes7.dex */
public class SleepRecommendData {
    private String category;
    private String description;
    private String imageUrl;
    private String suggestion;
    private String title;
    private String url;

    public String getSuggestion() {
        return (String) ResultUtils.a(this.suggestion);
    }

    public String getCategory() {
        return (String) ResultUtils.a(this.category);
    }

    public String getDescription() {
        return (String) ResultUtils.a(this.description);
    }

    public String getUrl() {
        return (String) ResultUtils.a(this.url);
    }

    public String getTitle() {
        return (String) ResultUtils.a(this.title);
    }

    public String toString() {
        return "SleepRecommendData{imageUrl='" + this.imageUrl + "', suggestion='" + this.suggestion + "', category='" + this.category + "', description='" + this.description + "', url='" + this.url + "', title='" + this.title + "'}";
    }
}
