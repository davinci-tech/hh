package com.huawei.basefitnessadvice.model;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class WorkoutListBean {
    private Integer[] mClassList;
    private int mCourseType;
    private Integer[] mDifficulty;
    private Integer[] mEquipments;
    private Integer mIsAi;
    private int mMy;
    private int mPageSize;
    private int mPageStart;
    private Integer mPrimaryClassifyId;
    private boolean mPriority;
    private String mReplacedWorkoutId;
    private SearchCondition mSearchCondition;
    private Integer mSecondClassifyId;
    private Integer[] mSecondClassifyList;
    private int mSupportWear;
    private Integer[] mTrainingPoints;
    private Integer mUserDefinedType;
    private Integer mWorkoutRank;
    private Integer[] mWorkoutType;
    private int mCommodityFlag = -1;
    private boolean mUseCache = true;

    public WorkoutListBean() {
    }

    public WorkoutListBean(int i, int i2, int i3, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, Integer[] numArr4, int i4) {
        this.mPageStart = i;
        this.mPageSize = i2;
        this.mSupportWear = i3;
        this.mClassList = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
        this.mTrainingPoints = numArr2 == null ? null : (Integer[]) Arrays.copyOf(numArr2, numArr2.length);
        this.mDifficulty = numArr3 == null ? null : (Integer[]) Arrays.copyOf(numArr3, numArr3.length);
        this.mEquipments = numArr4 != null ? (Integer[]) Arrays.copyOf(numArr4, numArr4.length) : null;
        this.mMy = i4;
    }

    public WorkoutListBean(int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, boolean z) {
        this.mPageStart = i;
        this.mPageSize = i2;
        this.mWorkoutType = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
        this.mDifficulty = numArr2 == null ? null : (Integer[]) Arrays.copyOf(numArr2, numArr2.length);
        this.mTrainingPoints = numArr3 == null ? null : (Integer[]) Arrays.copyOf(numArr3, numArr3.length);
        this.mSupportWear = i3;
        this.mEquipments = numArr4 != null ? (Integer[]) Arrays.copyOf(numArr4, numArr4.length) : null;
        this.mPriority = z;
    }

    public WorkoutListBean(int i, int i2, Integer num, int i3, SearchCondition searchCondition) {
        this.mPageStart = i;
        this.mPageSize = i2;
        this.mWorkoutRank = num;
        this.mCourseType = i3;
        this.mSearchCondition = searchCondition;
    }

    public static boolean isFitWorkoutType(WorkoutListBean workoutListBean) {
        return workoutListBean.getCourseType() == 2;
    }

    public int getPageStart() {
        return this.mPageStart;
    }

    public void setPageStart(int i) {
        this.mPageStart = i;
    }

    public int getPageSize() {
        return this.mPageSize;
    }

    public void setPageSize(int i) {
        this.mPageSize = i;
    }

    public int getSupportWear() {
        return this.mSupportWear;
    }

    public void setSupportWear(int i) {
        this.mSupportWear = i;
    }

    public Integer[] getClassList() {
        Integer[] numArr = this.mClassList;
        if (numArr == null) {
            return null;
        }
        return (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setClassList(Integer[] numArr) {
        this.mClassList = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public Integer[] getTrainingPoints() {
        Integer[] numArr = this.mTrainingPoints;
        if (numArr == null) {
            return null;
        }
        return (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setTrainingPoints(Integer[] numArr) {
        this.mTrainingPoints = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public Integer[] getDifficulty() {
        Integer[] numArr = this.mDifficulty;
        if (numArr == null) {
            return null;
        }
        return (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setDifficulty(Integer[] numArr) {
        this.mDifficulty = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public Integer[] getEquipments() {
        Integer[] numArr = this.mEquipments;
        if (numArr == null) {
            return null;
        }
        return (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setEquipments(Integer[] numArr) {
        this.mEquipments = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public int getMy() {
        return this.mMy;
    }

    public void setMy(int i) {
        this.mMy = i;
    }

    public Integer getIsAi() {
        return this.mIsAi;
    }

    public void setIsAi(Integer num) {
        this.mIsAi = num;
    }

    public Integer getPrimaryClassifyId() {
        return this.mPrimaryClassifyId;
    }

    public void setPrimaryClassifyId(Integer num) {
        this.mPrimaryClassifyId = num;
    }

    public Integer getSecondClassifyId() {
        return this.mSecondClassifyId;
    }

    public void setSecondClassifyId(Integer num) {
        this.mSecondClassifyId = num;
    }

    public Integer[] getSecondClassifyList() {
        Integer[] numArr = this.mSecondClassifyList;
        return numArr == null ? new Integer[0] : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setSecondClassifyList(Integer[] numArr) {
        this.mSecondClassifyList = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public Integer getWorkoutRank() {
        return this.mWorkoutRank;
    }

    public void setWorkoutRank(Integer num) {
        this.mWorkoutRank = num;
    }

    public Integer[] getWorkoutType() {
        Integer[] numArr = this.mWorkoutType;
        if (numArr == null) {
            return null;
        }
        return (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public void setWorkoutType(Integer[] numArr) {
        this.mWorkoutType = numArr == null ? null : (Integer[]) Arrays.copyOf(numArr, numArr.length);
    }

    public boolean getPriority() {
        return this.mPriority;
    }

    public void setPriority(boolean z) {
        this.mPriority = z;
    }

    public int getCommodityFlag() {
        return this.mCommodityFlag;
    }

    public void setCommodityFlag(int i) {
        this.mCommodityFlag = i;
    }

    public Integer getUserDefinedType() {
        return this.mUserDefinedType;
    }

    public void setUserDefinedType(Integer num) {
        this.mUserDefinedType = num;
    }

    public boolean getUseCache() {
        return this.mUseCache;
    }

    public void setUseCache(boolean z) {
        this.mUseCache = z;
    }

    public SearchCondition getSearchCondition() {
        return this.mSearchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.mSearchCondition = searchCondition;
    }

    public int getCourseType() {
        return this.mCourseType;
    }

    public void setCourseType(int i) {
        this.mCourseType = i;
    }

    public String getReplacedWorkoutId() {
        return this.mReplacedWorkoutId;
    }

    public void setReplacedWorkoutId(String str) {
        this.mReplacedWorkoutId = str;
    }
}
