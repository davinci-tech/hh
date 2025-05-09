package com.huawei.pluginachievement.report.bean;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class ReportFitnessData extends ReportDataBean {
    private Map<String, Integer> mCourseMap;
    private Map<String, Integer> mFirstLevelMap;
    private String mMaxCourse;
    private String mMaxFirstLevel;
    private ArrayList<String> mMaxTrainingPoints;
    private ArrayList<String> mRecordList;
    private Map<String, Integer> mTrainingPointsMap;

    public ReportFitnessData(int i) {
        super(10001, i, 2003);
        this.mFirstLevelMap = new ConcurrentHashMap(0);
        this.mCourseMap = new ConcurrentHashMap(0);
        this.mTrainingPointsMap = new ConcurrentHashMap(0);
        this.mMaxTrainingPoints = new ArrayList<>(0);
        this.mRecordList = new ArrayList<>(0);
    }

    public void setFirstLevelMap(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        this.mFirstLevelMap = concurrentHashMap;
    }

    public Map<String, Integer> getFirstLevelMap() {
        return this.mFirstLevelMap;
    }

    public void setCourseMap(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        this.mCourseMap = concurrentHashMap;
    }

    public Map<String, Integer> getCourseMap() {
        return this.mCourseMap;
    }

    public void setTrainingPointsMap(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        this.mTrainingPointsMap = concurrentHashMap;
    }

    public Map<String, Integer> getTrainingPointsMap() {
        return this.mTrainingPointsMap;
    }

    public void setMaxCourse(String str) {
        this.mMaxCourse = str;
    }

    public String getMaxCourse() {
        return this.mMaxCourse;
    }

    public void setMaxFirstLevel(String str) {
        this.mMaxFirstLevel = str;
    }

    public String getMaxFirstLevel() {
        return this.mMaxFirstLevel;
    }

    public void setMaxTrainingPoints(ArrayList<String> arrayList) {
        this.mMaxTrainingPoints = arrayList;
    }

    public ArrayList<String> getMaxTrainingPoints() {
        return this.mMaxTrainingPoints;
    }

    public void setRecordList(ArrayList<String> arrayList) {
        this.mRecordList = arrayList;
    }

    public ArrayList<String> getRecordList() {
        return this.mRecordList;
    }
}
