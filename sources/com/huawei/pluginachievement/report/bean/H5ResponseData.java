package com.huawei.pluginachievement.report.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.request.SleepAudio;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes6.dex */
public class H5ResponseData {

    @SerializedName("audioCompleteCount")
    private int audioCompleteCount;

    @SerializedName(ParsedFieldTag.GOAL)
    private List<Goal> goal;

    @SerializedName("memberInfo")
    private UserMemberInfo memberInfo;

    @SerializedName("resultCode")
    private int resultCode;

    @SerializedName("resultDesc")
    private String resultDesc;

    @SerializedName("sleepAudios")
    private List<SleepAudio> sleepAudios;

    @SerializedName("workoutPlayList")
    private List<WorkoutPlayInfo> workoutPlayList;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public List<Goal> getGoal() {
        return this.goal;
    }

    public void setGoal(List<Goal> list) {
        this.goal = list;
    }

    public List<WorkoutPlayInfo> getWorkoutPlayList() {
        return this.workoutPlayList;
    }

    public void setWorkoutPlayList(List<WorkoutPlayInfo> list) {
        this.workoutPlayList = list;
    }

    public int getAudioCompleteCount() {
        return this.audioCompleteCount;
    }

    public void setAudioCompleteCount(int i) {
        this.audioCompleteCount = i;
    }

    public List<SleepAudio> getSleepAudios() {
        return this.sleepAudios;
    }

    public void setSleepAudios(List<SleepAudio> list) {
        this.sleepAudios = list;
    }

    public UserMemberInfo getMemberInfo() {
        return this.memberInfo;
    }

    public void setMemberInfo(UserMemberInfo userMemberInfo) {
        this.memberInfo = userMemberInfo;
    }
}
