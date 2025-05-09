package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleGridContent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class GridTemplate extends BaseTemplate {
    private boolean advertiseCloseButtonSwitch;
    private boolean advertiseInfoButtonSwitch;
    private String advertiseInfoLink;
    private List<SingleGridContent> gridContents;
    private boolean hasBlank;
    private int imgShowSize;
    private boolean intelligentFlag;
    private int itemLimit;
    private String linkValue;
    private String moreMenuTitle;
    private String name;
    private boolean nameVisibility;
    private String resourceSceneId;
    private String resourceSceneName;
    private int sleepAudioType;
    private String sortingRules;

    public void setName(String str) {
        this.name = str;
    }

    public void setNameVisibility(boolean z) {
        this.nameVisibility = z;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public void setGridContents(List<SingleGridContent> list) {
        synchronized (this) {
            this.gridContents = list;
        }
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

    public int getSleepAudioType() {
        return this.sleepAudioType;
    }

    public void setSleepAudioType(int i) {
        this.sleepAudioType = i;
    }

    public List<SingleGridContent> getGridContents() {
        List<SingleGridContent> list;
        synchronized (this) {
            list = this.gridContents;
        }
        return list;
    }

    public boolean isIntelligentFlag() {
        return this.intelligentFlag;
    }

    public void setIntelligentFlag(boolean z) {
        this.intelligentFlag = z;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public void setSortingRules(String str) {
        this.sortingRules = str;
    }

    public String getResourceSceneId() {
        return this.resourceSceneId;
    }

    public void setResourceSceneId(String str) {
        this.resourceSceneId = str;
    }

    public String getResourceSceneName() {
        return this.resourceSceneName;
    }

    public void setResourceSceneName(String str) {
        this.resourceSceneName = str;
    }

    public int getItemLimit() {
        return this.itemLimit;
    }

    public void setItemLimit(int i) {
        this.itemLimit = i;
    }

    public int getImgShowSize() {
        return this.imgShowSize;
    }

    public void setImgShowSize(int i) {
        this.imgShowSize = i;
    }

    public boolean hasBlank() {
        return this.hasBlank;
    }

    public void setHasBlank(boolean z) {
        this.hasBlank = z;
    }

    public String getMoreMenuTitle() {
        return this.moreMenuTitle;
    }

    public void setMoreMenuTitle(String str) {
        this.moreMenuTitle = str;
    }

    public boolean isAdvertiseCloseButtonSwitch() {
        return this.advertiseCloseButtonSwitch;
    }

    public void setAdvertiseCloseButtonSwitch(boolean z) {
        this.advertiseCloseButtonSwitch = z;
    }

    public boolean isAdvertiseInfoButtonSwitch() {
        return this.advertiseInfoButtonSwitch;
    }

    public void setAdvertiseInfoButtonSwitch(boolean z) {
        this.advertiseInfoButtonSwitch = z;
    }

    public String getAdvertiseInfoLink() {
        return this.advertiseInfoLink;
    }

    public void setAdvertiseInfoLink(String str) {
        this.advertiseInfoLink = str;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public GridTemplate m426clone() {
        GridTemplate gridTemplate = new GridTemplate();
        gridTemplate.setName(getName());
        gridTemplate.setNameVisibility(isNameVisibility());
        gridTemplate.setLinkValue(getLinkValue());
        gridTemplate.setSleepAudioType(getSleepAudioType());
        if (getGridContents() == null) {
            return gridTemplate;
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            for (SingleGridContent singleGridContent : getGridContents()) {
                if (singleGridContent != null) {
                    arrayList.add(singleGridContent.m425clone());
                }
            }
        }
        gridTemplate.setGridContents(arrayList);
        gridTemplate.setIntelligentFlag(isIntelligentFlag());
        gridTemplate.setSortingRules(getSortingRules());
        gridTemplate.setResourceSceneId(getResourceSceneId());
        gridTemplate.setResourceSceneName(getResourceSceneName());
        gridTemplate.setItemLimit(getItemLimit());
        gridTemplate.setImgShowSize(getImgShowSize());
        gridTemplate.setHasBlank(hasBlank());
        gridTemplate.setMoreMenuTitle(getMoreMenuTitle());
        gridTemplate.setAdvertiseCloseButtonSwitch(isAdvertiseCloseButtonSwitch());
        gridTemplate.setAdvertiseInfoButtonSwitch(isAdvertiseInfoButtonSwitch());
        gridTemplate.setAdvertiseInfoLink(getAdvertiseInfoLink());
        return gridTemplate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GridTemplate gridTemplate = (GridTemplate) obj;
        return this.nameVisibility == gridTemplate.nameVisibility && this.sleepAudioType == gridTemplate.sleepAudioType && this.advertiseCloseButtonSwitch == gridTemplate.advertiseCloseButtonSwitch && this.advertiseInfoButtonSwitch == gridTemplate.advertiseInfoButtonSwitch && Objects.equals(this.name, gridTemplate.name) && Objects.equals(this.linkValue, gridTemplate.linkValue) && Objects.equals(this.gridContents, gridTemplate.gridContents) && this.intelligentFlag == gridTemplate.intelligentFlag && Objects.equals(this.sortingRules, gridTemplate.sortingRules) && Objects.equals(this.resourceSceneId, gridTemplate.resourceSceneId) && Objects.equals(this.resourceSceneName, gridTemplate.resourceSceneName) && Objects.equals(Integer.valueOf(this.itemLimit), Integer.valueOf(gridTemplate.itemLimit)) && Objects.equals(Integer.valueOf(this.imgShowSize), Integer.valueOf(gridTemplate.imgShowSize)) && Objects.equals(Boolean.valueOf(this.hasBlank), Boolean.valueOf(gridTemplate.hasBlank)) && Objects.equals(this.moreMenuTitle, gridTemplate.moreMenuTitle) && Objects.equals(this.advertiseInfoLink, gridTemplate.advertiseInfoLink);
    }

    public int hashCode() {
        return Objects.hash(this.name, Boolean.valueOf(this.nameVisibility), this.linkValue, Integer.valueOf(this.sleepAudioType), this.gridContents, Boolean.valueOf(this.intelligentFlag), this.sortingRules, this.resourceSceneId, this.resourceSceneName, Integer.valueOf(this.itemLimit), Integer.valueOf(this.imgShowSize), Boolean.valueOf(this.hasBlank), this.moreMenuTitle, Boolean.valueOf(this.advertiseCloseButtonSwitch), Boolean.valueOf(this.advertiseInfoButtonSwitch), this.advertiseInfoLink);
    }
}
