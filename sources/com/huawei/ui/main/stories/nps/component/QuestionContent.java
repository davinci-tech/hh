package com.huawei.ui.main.stories.nps.component;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyCommitParam;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes9.dex */
public class QuestionContent {

    @SerializedName("firstTime")
    private String mFirstTime;

    @SerializedName("queryTimes")
    private String mQueryTimes;

    @SerializedName("reason")
    private String mReason;

    @SerializedName("resCode")
    private String mResCode;

    @SerializedName("surveyContent")
    private SurveyContentBean mSurveyContent;

    @SerializedName(QuestionSurveyCommitParam.SURVEY_ID)
    private String mSurveyId;

    public static QuestionContent objectFromData(String str) {
        return (QuestionContent) new Gson().fromJson(CommonUtil.z(str), QuestionContent.class);
    }

    public String getFirstTime() {
        return this.mFirstTime;
    }

    public void setFirstTime(String str) {
        this.mFirstTime = str;
    }

    public String getReason() {
        return this.mReason;
    }

    public void setReason(String str) {
        this.mReason = str;
    }

    public String getSurveyId() {
        return this.mSurveyId;
    }

    public void setSurveyId(String str) {
        this.mSurveyId = str;
    }

    public SurveyContentBean getSurveyContent() {
        return this.mSurveyContent;
    }

    public void setSurveyContent(SurveyContentBean surveyContentBean) {
        this.mSurveyContent = surveyContentBean;
    }

    public String getQueryTimes() {
        return this.mQueryTimes;
    }

    public void setQueryTimes(String str) {
        this.mQueryTimes = str;
    }

    public String getResCode() {
        return this.mResCode;
    }

    public void setResCode(String str) {
        this.mResCode = str;
    }

    public static class SurveyContentBean {

        @SerializedName("endDesc")
        private String mEndDesc;

        @SerializedName("questions")
        private List<QuestionsBean> mQuestions;

        @SerializedName("startDesc")
        private String mStartDesc;

        @SerializedName("title")
        private String mTitle;

        public String getEndDesc() {
            return this.mEndDesc;
        }

        public void setEndDesc(String str) {
            this.mEndDesc = str;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public String getStartDesc() {
            return this.mStartDesc;
        }

        public void setStartDesc(String str) {
            this.mStartDesc = str;
        }

        public List<QuestionsBean> getQuestions() {
            return this.mQuestions;
        }

        public void setQuestions(List<QuestionsBean> list) {
            this.mQuestions = list;
        }

        public static class QuestionsBean {

            @SerializedName("id")
            private String mId;

            @SerializedName("options")
            private List<OptionsBean> mOptions;

            @SerializedName("pictureUrl")
            private String mPictureUrl;

            @SerializedName("question")
            private String mQuestion;

            @SerializedName("required")
            private String mRequired;

            @SerializedName("subTitle")
            private String mSubTitle;

            @SerializedName("type")
            private String mType;

            public String getSubTitle() {
                return this.mSubTitle;
            }

            public void setSubTitle(String str) {
                this.mSubTitle = str;
            }

            public String getQuestion() {
                return this.mQuestion;
            }

            public void setQuestion(String str) {
                this.mQuestion = str;
            }

            public String getPictureUrl() {
                return this.mPictureUrl;
            }

            public void setPictureUrl(String str) {
                this.mPictureUrl = str;
            }

            public String getId() {
                return this.mId;
            }

            public void setId(String str) {
                this.mId = str;
            }

            public String getType() {
                return this.mType;
            }

            public void setType(String str) {
                this.mType = str;
            }

            public String getRequired() {
                return this.mRequired;
            }

            public void setRequired(String str) {
                this.mRequired = str;
            }

            public List<OptionsBean> getOptions() {
                return this.mOptions;
            }

            public void setOptions(List<OptionsBean> list) {
                this.mOptions = list;
            }

            public static class OptionsBean {

                @SerializedName("name")
                private String mName;

                @SerializedName("remark")
                private String mRemark;

                public String getName() {
                    return this.mName;
                }

                public void setName(String str) {
                    this.mName = str;
                }

                public String getRemark() {
                    return this.mRemark;
                }

                public void setRemark(String str) {
                    this.mRemark = str;
                }
            }
        }
    }
}
