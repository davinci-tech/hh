package com.huawei.watchface.mvp.model.latona;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceThemeLayer {

    @SerializedName("@background_color")
    private String mBackgroundColor;

    @SerializedName("@background_drawable")
    private String mBackgroundDrawable;

    @SerializedName("@border_color")
    private String mBorderColor;

    @SerializedName("@bounds_rect")
    private String mBoundsRect;

    @SerializedName("@draw_type")
    private String mDrawType;

    @SerializedName("@end_angle")
    private String mEndAngle;

    @SerializedName("@end_point")
    private String mEndPoint;

    @SerializedName("@end_point_relative")
    private String mEndPointRelative;

    @SerializedName("@highlight_color")
    private String mHighlightColor;

    @SerializedName("@icon_color")
    private String mIconColor;

    @SerializedName("@index")
    private String mIndex;

    @SerializedName("@is_support_text_shadow")
    private String mIsSupportTextShadow;

    @SerializedName("@line_cap")
    private String mLineCap;

    @SerializedName("@line_width")
    private String mLineWidth;

    @SerializedName("@primary_color")
    private String mPrimaryColor;

    @SerializedName("@range_primary_color")
    private String mRangePrimaryColor;

    @SerializedName("@range_secondary_color")
    private String mRangeSecondaryColor;

    @SerializedName("@rect")
    private String mRect;

    @SerializedName("@rect_relative")
    private String mRectRelative;

    @SerializedName("@res_active")
    private String mResActive;

    @SerializedName("@res_active_dot")
    private String mResActiveDot;

    @SerializedName("@res_active_left")
    private String mResActiveLeft;

    @SerializedName("@res_active_right")
    private String mResActiveRight;

    @SerializedName("@res_active_scale")
    private String mResActiveScale;

    @SerializedName("@res_align")
    private String mResAlign;

    @SerializedName("@res_ambient")
    private String mResAmbient;

    @SerializedName("@res_ambient_dot")
    private String mResAmbientDot;

    @SerializedName("@res_ambient_left")
    private String mResAmbientLeft;

    @SerializedName("@res_ambient_right")
    private String mResAmbientRight;

    @SerializedName("@res_ambient_scale")
    private String mResAmbientScale;

    @SerializedName("@res_dot_shadow")
    private String mResDotShadow;

    @SerializedName("@res_interval")
    private String mResInterval;

    @SerializedName("@res_ordered_is_play_once")
    private String mResOrderedIsPlayOnce;

    @SerializedName("@res_position")
    private String mResPosition;

    @SerializedName("@res_position_relative")
    private String mResPositionRelative;

    @SerializedName("@res_shadow")
    private String mResShadow;

    @SerializedName("@rotate_center_point")
    private String mRotateCenterPoint;

    @SerializedName("@rotate_center_point_relative")
    private String mRotateCenterPointRelative;

    @SerializedName("@rotate_degree")
    private String mRotateDegree;

    @SerializedName("@rotate_end_angel")
    private String mRotateEndAngel;

    @SerializedName("@rotate_fixed_degree")
    private String mRotateFixedDegree;

    @SerializedName("@rotate_motion_type")
    private String mRotateMotionType;

    @SerializedName("@rotate_point_face")
    private String mRotatePointFace;

    @SerializedName("@rotate_point_face_relative")
    private String mRotatePointFaceRelative;

    @SerializedName("@rotate_point_hand")
    private String mRotatePointHand;

    @SerializedName("@rotate_start_angel")
    private String mRotateStartAngel;

    @SerializedName("@rotate_time")
    private String mRotateTime;

    @SerializedName("@rotate_type")
    private String mRotateType;

    @SerializedName("@scale_amount")
    private String mScaleAmount;

    @SerializedName("@scale_center_point")
    private String mScaleCenterPoint;

    @SerializedName("@scale_center_point_relative")
    private String mScaleCenterPointRelative;

    @SerializedName("@scale_motion_type")
    private String mScaleMotionType;

    @SerializedName("@scale_time")
    private String mScaleTime;

    @SerializedName("@scale_type")
    private String mScaleType;

    @SerializedName("@secondary_color")
    private String mSecondaryColor;

    @SerializedName("@start_angle")
    private String mStartAngle;

    @SerializedName("@start_point")
    private String mStartPoint;

    @SerializedName("@start_point_relative")
    private String mStartPointRelative;

    @SerializedName("@text_active_color")
    private String mTextActiveColor;

    @SerializedName("@text_align")
    private String mTextAlign;

    @SerializedName("@text_ambient_color")
    private String mTextAmbientColor;

    @SerializedName("@text_color")
    private String mTextColor;

    @SerializedName("@text_content")
    private String mTextContent;

    @SerializedName("@text_font")
    private String mTextFont;

    @SerializedName("@text_font_options")
    private String mTextFontOptions;

    @SerializedName("@text_is_bold")
    private String mTextIsBold;

    @SerializedName("@text_position")
    private String mTextPosition;

    @SerializedName("@text_position_label")
    private String mTextPositionLabel;

    @SerializedName("@text_position_labels")
    private String mTextPositionLabels;

    @SerializedName("@text_position_options")
    private String mTextPositionOptions;

    @SerializedName("@text_position_relative")
    private String mTextPositionRelative;

    @SerializedName("@text_shadow_color")
    private String mTextShadowColor;

    @SerializedName("@text_shadow_position")
    private String mTextShadowPosition;

    @SerializedName("@text_shadow_radius")
    private String mTextShadowRadius;

    @SerializedName("@text_size")
    private String mTextSize;

    @SerializedName("@title_color")
    private String mTitleColor;

    @SerializedName("@title_font")
    private String mTitleFont;

    @SerializedName("@title_size")
    private String mTitleSize;

    @SerializedName("@translate_end_position")
    private String mTranslateEndPosition;

    @SerializedName("@translate_end_position_relative")
    private String mTranslateEndPositionRelative;

    @SerializedName("@translate_type")
    private String mTranslateType;

    @SerializedName("@value_type")
    private String mValueType;

    @SerializedName("layer")
    private List<WatchFaceThemeLayer> mWatchFaceThemeLayerList;

    @SerializedName("@word_capital_type")
    private String mWordCapitalType;

    @SerializedName("@word_is_abbr")
    private String mWordIsAbbr;

    @SerializedName("@word_support_cn")
    private String mWordSupportCn;

    public String getTextShadowPosition() {
        return this.mTextShadowPosition;
    }

    public void setTextShadowPosition(String str) {
        this.mTextShadowPosition = str;
    }

    public String getTextShadowColor() {
        return this.mTextShadowColor;
    }

    public void setTextShadowColor(String str) {
        this.mTextShadowColor = str;
    }

    public String getTextShadowRadius() {
        return this.mTextShadowRadius;
    }

    public void setTextShadowRadius(String str) {
        this.mTextShadowRadius = str;
    }

    public String getIsSupportTextShadow() {
        return this.mIsSupportTextShadow;
    }

    public void setIsSupportTextShadow(String str) {
        this.mIsSupportTextShadow = str;
    }

    public String getIndex() {
        return this.mIndex;
    }

    public String getDrawType() {
        return this.mDrawType;
    }

    public List<WatchFaceThemeLayer> getWatchFaceThemeLayerList() {
        return this.mWatchFaceThemeLayerList;
    }

    public String getTextContent() {
        return this.mTextContent;
    }

    public String getTextActiveColor() {
        return this.mTextActiveColor;
    }

    public String getTextAmbientColor() {
        return this.mTextAmbientColor;
    }

    public String getTextFont() {
        return this.mTextFont;
    }

    public String getTextSize() {
        return this.mTextSize;
    }

    public String getTextAlign() {
        return this.mTextAlign;
    }

    public String getTextPosition() {
        return this.mTextPosition;
    }

    public String getTextPositionRelative() {
        return this.mTextPositionRelative;
    }

    public String getTextIsBold() {
        return this.mTextIsBold;
    }

    public String getWordSupportCn() {
        return this.mWordSupportCn;
    }

    public String getWordIsAbbr() {
        return this.mWordIsAbbr;
    }

    public String getWordCapitalType() {
        return this.mWordCapitalType;
    }

    public String getResActive() {
        return this.mResActive;
    }

    public String getResActiveLeft() {
        return this.mResActiveLeft;
    }

    public String getResActiveRight() {
        return this.mResActiveRight;
    }

    public String getResActiveDot() {
        return this.mResActiveDot;
    }

    public String getResActiveScale() {
        return this.mResActiveScale;
    }

    public String getResAmbient() {
        return this.mResAmbient;
    }

    public String getResAmbientLeft() {
        return this.mResAmbientLeft;
    }

    public String getResAmbientRight() {
        return this.mResAmbientRight;
    }

    public String getResAmbientDot() {
        return this.mResAmbientDot;
    }

    public String getResAmbientScale() {
        return this.mResAmbientScale;
    }

    public String getResShadow() {
        return this.mResShadow;
    }

    public String getResDotShadow() {
        return this.mResDotShadow;
    }

    public String getResInterval() {
        return this.mResInterval;
    }

    public String getResAlign() {
        return this.mResAlign;
    }

    public String getResPosition() {
        return this.mResPosition;
    }

    public String getResPositionRelative() {
        return this.mResPositionRelative;
    }

    public String getResOrderedIsPlayOnce() {
        return this.mResOrderedIsPlayOnce;
    }

    public String getRotatePointHand() {
        return this.mRotatePointHand;
    }

    public String getRotatePointFace() {
        return this.mRotatePointFace;
    }

    public String getRotatePointFaceRelative() {
        return this.mRotatePointFaceRelative;
    }

    public String getRotateStartAngel() {
        return this.mRotateStartAngel;
    }

    public String getRotateEndAngel() {
        return this.mRotateEndAngel;
    }

    public String getPrimaryColor() {
        return this.mPrimaryColor;
    }

    public String getSecondaryColor() {
        return this.mSecondaryColor;
    }

    public String getLineWidth() {
        return this.mLineWidth;
    }

    public String getLineCap() {
        return this.mLineCap;
    }

    public String getStartPoint() {
        return this.mStartPoint;
    }

    public String getEndPoint() {
        return this.mEndPoint;
    }

    public String getStartPointRelative() {
        return this.mStartPointRelative;
    }

    public String getEndPointRelative() {
        return this.mEndPointRelative;
    }

    public String getStartAngle() {
        return this.mStartAngle;
    }

    public String getEndAngle() {
        return this.mEndAngle;
    }

    public String getRect() {
        return this.mRect;
    }

    public String getRectRelative() {
        return this.mRectRelative;
    }

    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public String getBackgroundDrawable() {
        return this.mBackgroundDrawable;
    }

    public String getBorderColor() {
        return this.mBorderColor;
    }

    public String getHighlightColor() {
        return this.mHighlightColor;
    }

    public String getIconColor() {
        return this.mIconColor;
    }

    public String getTextColor() {
        return this.mTextColor;
    }

    public String getTitleColor() {
        return this.mTitleColor;
    }

    public String getTitleFont() {
        return this.mTitleFont;
    }

    public String getTitleSize() {
        return this.mTitleSize;
    }

    public String getRangePrimaryColor() {
        return this.mRangePrimaryColor;
    }

    public String getRangeSecondaryColor() {
        return this.mRangeSecondaryColor;
    }

    public String getBoundsRect() {
        return this.mBoundsRect;
    }

    public String getRotateType() {
        return this.mRotateType;
    }

    public String getRotateFixedDegree() {
        return this.mRotateFixedDegree;
    }

    public String getRotateCenterPoint() {
        return this.mRotateCenterPoint;
    }

    public String getRotateCenterPointRelative() {
        return this.mRotateCenterPointRelative;
    }

    public String getRotateDegree() {
        return this.mRotateDegree;
    }

    public String getRotateTime() {
        return this.mRotateTime;
    }

    public String getRotateMotionType() {
        return this.mRotateMotionType;
    }

    public String getScaleType() {
        return this.mScaleType;
    }

    public String getScaleCenterPoint() {
        return this.mScaleCenterPoint;
    }

    public String getScaleCenterPointRelative() {
        return this.mScaleCenterPointRelative;
    }

    public String getScaleAmount() {
        return this.mScaleAmount;
    }

    public String getScaleTime() {
        return this.mScaleTime;
    }

    public String getScaleMotionType() {
        return this.mScaleMotionType;
    }

    public String getTranslateType() {
        return this.mTranslateType;
    }

    public String getTranslateEndPosition() {
        return this.mTranslateEndPosition;
    }

    public void setIndex(String str) {
        this.mIndex = str;
    }

    public void setDrawType(String str) {
        this.mDrawType = str;
    }

    public void setWatchFaceThemeLayerList(List<WatchFaceThemeLayer> list) {
        this.mWatchFaceThemeLayerList = list;
    }

    public void setTextContent(String str) {
        this.mTextContent = str;
    }

    public void setTextActiveColor(String str) {
        this.mTextActiveColor = str;
    }

    public void setTextAmbientColor(String str) {
        this.mTextAmbientColor = str;
    }

    public void setTextFont(String str) {
        this.mTextFont = str;
    }

    public void setTextSize(String str) {
        this.mTextSize = str;
    }

    public void setTextAlign(String str) {
        this.mTextAlign = str;
    }

    public void setTextPosition(String str) {
        this.mTextPosition = str;
    }

    public void setTextPositionRelative(String str) {
        this.mTextPositionRelative = str;
    }

    public void setWordSupportCn(String str) {
        this.mWordSupportCn = str;
    }

    public void setWordIsAbbr(String str) {
        this.mWordIsAbbr = str;
    }

    public void setWordCapitalType(String str) {
        this.mWordCapitalType = str;
    }

    public String getTextFontOptions() {
        return this.mTextFontOptions;
    }

    public void setTextFontOptions(String str) {
        this.mTextFontOptions = str;
    }

    public String getTextPositionLabel() {
        return this.mTextPositionLabel;
    }

    public void setTextPositionLabel(String str) {
        this.mTextPositionLabel = str;
    }

    public String getTextPositionOptions() {
        return this.mTextPositionOptions;
    }

    public void setTextPositionOptions(String str) {
        this.mTextPositionOptions = str;
    }

    public String getTextPositionLabels() {
        return this.mTextPositionLabels;
    }

    public void setTextPositionLabels(String str) {
        this.mTextPositionLabels = str;
    }

    public void setTextIsBold(String str) {
        this.mTextIsBold = str;
    }

    public void setResActive(String str) {
        this.mResActive = str;
    }

    public void setResActiveLeft(String str) {
        this.mResActiveLeft = str;
    }

    public void setResActiveRight(String str) {
        this.mResActiveRight = str;
    }

    public void setResActiveDot(String str) {
        this.mResActiveDot = str;
    }

    public void setResActiveScale(String str) {
        this.mResActiveScale = str;
    }

    public void setResAmbient(String str) {
        this.mResAmbient = str;
    }

    public void setResAmbientLeft(String str) {
        this.mResAmbientLeft = str;
    }

    public void setResAmbientRight(String str) {
        this.mResAmbientRight = str;
    }

    public void setResAmbientDot(String str) {
        this.mResAmbientDot = str;
    }

    public void setResAmbientScale(String str) {
        this.mResAmbientScale = str;
    }

    public void setResShadow(String str) {
        this.mResShadow = str;
    }

    public void setResDotShadow(String str) {
        this.mResDotShadow = str;
    }

    public void setResInterval(String str) {
        this.mResInterval = str;
    }

    public void setResAlign(String str) {
        this.mResAlign = str;
    }

    public void setResPosition(String str) {
        this.mResPosition = str;
    }

    public void setResPositionRelative(String str) {
        this.mResPositionRelative = str;
    }

    public void setResOrderedIsPlayOnce(String str) {
        this.mResOrderedIsPlayOnce = str;
    }

    public void setRotatePointHand(String str) {
        this.mRotatePointHand = str;
    }

    public void setRotatePointFace(String str) {
        this.mRotatePointFace = str;
    }

    public void setRotatePointFaceRelative(String str) {
        this.mRotatePointFaceRelative = str;
    }

    public void setRotateStartAngel(String str) {
        this.mRotateStartAngel = str;
    }

    public void setRotateEndAngel(String str) {
        this.mRotateEndAngel = str;
    }

    public void setPrimaryColor(String str) {
        this.mPrimaryColor = str;
    }

    public void setSecondaryColor(String str) {
        this.mSecondaryColor = str;
    }

    public void setLineWidth(String str) {
        this.mLineWidth = str;
    }

    public void setLineCap(String str) {
        this.mLineCap = str;
    }

    public void setStartPoint(String str) {
        this.mStartPoint = str;
    }

    public void setEndPoint(String str) {
        this.mEndPoint = str;
    }

    public void setStartPointRelative(String str) {
        this.mStartPointRelative = str;
    }

    public void setEndPointRelative(String str) {
        this.mEndPointRelative = str;
    }

    public void setStartAngle(String str) {
        this.mStartAngle = str;
    }

    public void setEndAngle(String str) {
        this.mEndAngle = str;
    }

    public void setRect(String str) {
        this.mRect = str;
    }

    public void setRectRelative(String str) {
        this.mRectRelative = str;
    }

    public void setBackgroundColor(String str) {
        this.mBackgroundColor = str;
    }

    public void setBackgroundDrawable(String str) {
        this.mBackgroundDrawable = str;
    }

    public void setBorderColor(String str) {
        this.mBorderColor = str;
    }

    public void setHighlightColor(String str) {
        this.mHighlightColor = str;
    }

    public void setIconColor(String str) {
        this.mIconColor = str;
    }

    public void setTextColor(String str) {
        this.mTextColor = str;
    }

    public void setTitleColor(String str) {
        this.mTitleColor = str;
    }

    public void setTitleFont(String str) {
        this.mTitleFont = str;
    }

    public void setTitleSize(String str) {
        this.mTitleSize = str;
    }

    public void setRangePrimaryColor(String str) {
        this.mRangePrimaryColor = str;
    }

    public void setRangeSecondaryColor(String str) {
        this.mRangeSecondaryColor = str;
    }

    public void setBoundsRect(String str) {
        this.mBoundsRect = str;
    }

    public void setRotateType(String str) {
        this.mRotateType = str;
    }

    public void setRotateFixedDegree(String str) {
        this.mRotateFixedDegree = str;
    }

    public void setRotateCenterPoint(String str) {
        this.mRotateCenterPoint = str;
    }

    public void setRotateCenterPointRelative(String str) {
        this.mRotateCenterPointRelative = str;
    }

    public void setRotateDegree(String str) {
        this.mRotateDegree = str;
    }

    public void setRotateTime(String str) {
        this.mRotateTime = str;
    }

    public void setRotateMotionType(String str) {
        this.mRotateMotionType = str;
    }

    public void setScaleType(String str) {
        this.mScaleType = str;
    }

    public void setScaleCenterPoint(String str) {
        this.mScaleCenterPoint = str;
    }

    public void setScaleCenterPointRelative(String str) {
        this.mScaleCenterPointRelative = str;
    }

    public void setScaleAmount(String str) {
        this.mScaleAmount = str;
    }

    public void setScaleTime(String str) {
        this.mScaleTime = str;
    }

    public void setScaleMotionType(String str) {
        this.mScaleMotionType = str;
    }

    public void setTranslateType(String str) {
        this.mTranslateType = str;
    }

    public void setTranslateEndPosition(String str) {
        this.mTranslateEndPosition = str;
    }

    public void setTranslateEndPositionRelative(String str) {
        this.mTranslateEndPositionRelative = str;
    }

    public void setValueType(String str) {
        this.mValueType = str;
    }

    public String getTranslateEndPositionRelative() {
        return this.mTranslateEndPositionRelative;
    }

    public String getValueType() {
        return this.mValueType;
    }

    public boolean isHasFontOptionsProperty() {
        return !TextUtils.isEmpty(getTextFontOptions());
    }

    public boolean isHasPositionOptionsProperty() {
        return !TextUtils.isEmpty(getTextPositionOptions());
    }

    public String toString() {
        return "WatchFaceThemeLayer{mIndex='" + this.mIndex + "', mDrawType='" + this.mDrawType + "', mWatchFaceThemeLayerList=" + this.mWatchFaceThemeLayerList + ", textContent='" + this.mTextContent + "', textActiveColor='" + this.mTextActiveColor + "', textAmbientColor='" + this.mTextAmbientColor + "', textFont='" + this.mTextFont + "', textFontOptions='" + this.mTextFontOptions + "', textSize='" + this.mTextSize + "', textAlign='" + this.mTextAlign + "', textPositionLabel='" + this.mTextPositionLabel + "', textPositionOptions='" + this.mTextPositionOptions + "', textPositionLabels='" + this.mTextPositionLabels + "', textPosition='" + this.mTextPosition + "', textPositionRelative='" + this.mTextPositionRelative + "', wordSupportCn='" + this.mWordSupportCn + "', wordIsAbbr='" + this.mWordIsAbbr + "', wordCapitalType='" + this.mWordCapitalType + "', textIsBold='" + this.mTextIsBold + "', mTextShadowPosition='" + this.mTextShadowPosition + "', textShadowColor='" + this.mTextShadowColor + "', textShadowRadius='" + this.mTextShadowRadius + "', isSupportTextShadow='" + this.mIsSupportTextShadow + "', resActive='" + this.mResActive + "', resActiveLeft='" + this.mResActiveLeft + "', resActiveRight='" + this.mResActiveRight + "', resActiveDot='" + this.mResActiveDot + "', resActiveScale='" + this.mResActiveScale + "', resAmbient='" + this.mResAmbient + "', resAmbientLeft='" + this.mResAmbientLeft + "', resAmbientRight='" + this.mResAmbientRight + "', resAmbientDot='" + this.mResAmbientDot + "', resAmbientScale='" + this.mResAmbientScale + "', resShadow='" + this.mResShadow + "', resDotShadow='" + this.mResDotShadow + "', resInterval='" + this.mResInterval + "', resAlign='" + this.mResAlign + "', resPosition='" + this.mResPosition + "', resPositionRelative='" + this.mResPositionRelative + "', mResOrderedIsPlayOnce='" + this.mResOrderedIsPlayOnce + "', mRotatePointHand='" + this.mRotatePointHand + "', mRotatePointFace='" + this.mRotatePointFace + "', mRotatePointFaceRelative='" + this.mRotatePointFaceRelative + "', mRotateStartAngel='" + this.mRotateStartAngel + "', mRotateEndAngel='" + this.mRotateEndAngel + "', mPrimaryColor='" + this.mPrimaryColor + "', mSecondaryColor='" + this.mSecondaryColor + "', mLineWidth='" + this.mLineWidth + "', mLineCap='" + this.mLineCap + "', mStartPoint='" + this.mStartPoint + "', mEndPoint='" + this.mEndPoint + "', mStartPointRelative='" + this.mStartPointRelative + "', mEndPointRelative='" + this.mEndPointRelative + "', mStartAngle='" + this.mStartAngle + "', mEndAngle='" + this.mEndAngle + "', mRect='" + this.mRect + "', mRectRelative='" + this.mRectRelative + "', mBackgroundColor='" + this.mBackgroundColor + "', mBackgroundDrawable='" + this.mBackgroundDrawable + "', mBorderColor='" + this.mBorderColor + "', mHighlightColor='" + this.mHighlightColor + "', mIconColor='" + this.mIconColor + "', mTextColor='" + this.mTextColor + "', mTitleColor='" + this.mTitleColor + "', mTitleFont='" + this.mTitleFont + "', mTitleSize='" + this.mTitleSize + "', mRangePrimaryColor='" + this.mRangePrimaryColor + "', mRangeSecondaryColor='" + this.mRangeSecondaryColor + "', mBoundsRect='" + this.mBoundsRect + "', mRotateType='" + this.mRotateType + "', mRotateFixedDegree='" + this.mRotateFixedDegree + "', mRotateCenterPoint='" + this.mRotateCenterPoint + "', mRotateCenterPointRelative='" + this.mRotateCenterPointRelative + "', mRotateDegree='" + this.mRotateDegree + "', mRotateTime='" + this.mRotateTime + "', mRotateMotionType='" + this.mRotateMotionType + "', mScaleType='" + this.mScaleType + "', mScaleCenterPoint='" + this.mScaleCenterPoint + "', mScaleCenterPointRelative='" + this.mScaleCenterPointRelative + "', mScaleAmount='" + this.mScaleAmount + "', mScaleTime='" + this.mScaleTime + "', mScaleMotionType='" + this.mScaleMotionType + "', mTranslateType='" + this.mTranslateType + "', mTranslateEndPosition='" + this.mTranslateEndPosition + "', mTranslateEndPositionRelative='" + this.mTranslateEndPositionRelative + "', mValueType='" + this.mValueType + "'}";
    }
}
