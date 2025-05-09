package defpackage;

import java.util.List;

/* loaded from: classes3.dex */
public class dbn {

    public static class a {
        private e mFilterConfiguration;
        private String mProductId;
        private List<d> mRecommendedCourseList;

        public e getFilterConfiguration() {
            return this.mFilterConfiguration;
        }

        public void setFilterConfiguration(e eVar) {
            this.mFilterConfiguration = eVar;
        }

        public void setProductId(String str) {
            this.mProductId = str;
        }

        public String getProductId() {
            return this.mProductId;
        }

        public void setRecommendedCourseList(List<d> list) {
            this.mRecommendedCourseList = list;
        }

        public List<d> getRecommendedCourseList() {
            return this.mRecommendedCourseList;
        }

        public static class e {
            private b mAllDataSortCondition;
            private List<c> mFilterConditions;

            public void setAllDataSortCondition(b bVar) {
                this.mAllDataSortCondition = bVar;
            }

            public b getAllDataSortCondition() {
                return this.mAllDataSortCondition;
            }

            public void setFilterConditions(List<c> list) {
                this.mFilterConditions = list;
            }

            public List<c> getFilterConditions() {
                return this.mFilterConditions;
            }
        }

        public static class b {
            private String mKey;
            private List<C0294a> mParamsLists;
            private String mSortRules;

            public String getKey() {
                return this.mKey;
            }

            public void setKey(String str) {
                this.mKey = str;
            }

            public void setParamsLists(List<C0294a> list) {
                this.mParamsLists = list;
            }

            public List<C0294a> getParamsLists() {
                return this.mParamsLists;
            }

            public void setSortRules(String str) {
                this.mSortRules = str;
            }

            public String getSortRules() {
                return this.mSortRules;
            }
        }

        public static class c {
            private String mConditionRules;
            private List<String> mMatchingCondition;
            private i mSortCondition;

            public List<String> getMatchingCondition() {
                return this.mMatchingCondition;
            }

            public void setMatchingCondition(List<String> list) {
                this.mMatchingCondition = list;
            }

            public void setSortCondition(i iVar) {
                this.mSortCondition = iVar;
            }

            public i getSortCondition() {
                return this.mSortCondition;
            }

            public void setConditionRules(String str) {
                this.mConditionRules = str;
            }

            public String getConditionRules() {
                return this.mConditionRules;
            }
        }

        public static class i {
            private String mKey;
            private List<C0294a> mParamsLists;
            private String mSortRules;

            public void setSortRules(String str) {
                this.mSortRules = str;
            }

            public String getSortRules() {
                return this.mSortRules;
            }

            public String getKey() {
                return this.mKey;
            }

            public void setKey(String str) {
                this.mKey = str;
            }

            public void setParamsLists(List<C0294a> list) {
                this.mParamsLists = list;
            }

            public List<C0294a> getParamsLists() {
                return this.mParamsLists;
            }
        }

        /* renamed from: dbn$a$a, reason: collision with other inner class name */
        public static class C0294a {
            private String mKey;

            public void setKey(String str) {
                this.mKey = str;
            }

            public String getKey() {
                return this.mKey;
            }
        }

        public static class d {
            private String mCourseId;
            private List<Object> mCourseInfo;
            private String mCourseName;
            private String mCourseType;
            private List<String> mMatchingCondition;
            private String mMatchingRules;
            private String mMoreDataUrl;
            private List<String> mMuscleGroup;
            private String mMuscleGroupImgName;
            private String mMuscleGroupImgUrl;
            private int mRecommendedContent;

            public String getMoreDataUrl() {
                return this.mMoreDataUrl;
            }

            public void setMoreDataUrl(String str) {
                this.mMoreDataUrl = str;
            }

            public int getRecommendedContent() {
                return this.mRecommendedContent;
            }

            public void setRecommendedContent(int i) {
                this.mRecommendedContent = i;
            }

            public void setCourseId(String str) {
                this.mCourseId = str;
            }

            public String getCourseId() {
                return this.mCourseId;
            }

            public void setCourseName(String str) {
                this.mCourseName = str;
            }

            public String getCourseName() {
                return this.mCourseName;
            }

            public void setMuscleGroupImgName(String str) {
                this.mMuscleGroupImgName = str;
            }

            public String getMuscleGroupImgName() {
                return this.mMuscleGroupImgName;
            }

            public String getMuscleGroupImgUrl() {
                return this.mMuscleGroupImgUrl;
            }

            public void setMuscleGroupImgUrl(String str) {
                this.mMuscleGroupImgUrl = str;
            }

            public void setCourseType(String str) {
                this.mCourseType = str;
            }

            public String getCourseType() {
                return this.mCourseType;
            }

            public void setCourseInfo(List<Object> list) {
                this.mCourseInfo = list;
            }

            public List<Object> getCourseInfo() {
                return this.mCourseInfo;
            }

            public void setMuscleGroup(List<String> list) {
                this.mMuscleGroup = list;
            }

            public List<String> getMuscleGroup() {
                return this.mMuscleGroup;
            }

            public void setMatchingRules(String str) {
                this.mMatchingRules = str;
            }

            public String getMatchingRules() {
                return this.mMatchingRules;
            }

            public void setMatchingCondition(List<String> list) {
                this.mMatchingCondition = list;
            }

            public List<String> getMatchingCondition() {
                return this.mMatchingCondition;
            }
        }
    }
}
