package com.huawei.hwsportmodel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TrackGolfCourseSegment extends CommonSegment {
    private static final int FIELD_NUM = 10;
    private static final int SECTION_GOLF_SHOT_TRACKS_INDEX = 10;
    private static final int SECTION_HANDICAP_INDEX = 8;
    private static final int SECTION_HOLE_FAIR_WAY_HITS_INDEX = 7;
    private static final int SECTION_HOLE_ID_INDEX = 2;
    private static final int SECTION_HOLE_PENALTY_INDEX = 6;
    private static final int SECTION_HOLE_PUTTS_INDEX = 5;
    private static final int SECTION_HOLE_SCORE_INDEX = 4;
    private static final int SECTION_HOLE_SEQ_INDEX = 1;
    private static final int SECTION_PAR_INDEX = 3;
    private static final int SECTION_VALID_TRACKS_INDEX = 9;
    private static final int STRING_BUILDER_SIZE = 20;
    private static final long serialVersionUID = 4852646696711185708L;
    private List<GolfShotTrack> mSectionGolfShotTracks;
    private int mSectionHandicap;
    private int mSectionHoleFairwayHits;
    private long mSectionHoleId;
    private int mSectionHolePenalty;
    private int mSectionHolePutts;
    private int mSectionHoleScore;
    private int mSectionHoleSeq;
    private int mSectionPar;
    private int mSectionValidTracks;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 10;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.mSectionHoleSeq = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.mSectionHoleId = CommonUtil.b(strArr[2].split("=")[1], -1L);
        this.mSectionPar = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.mSectionHoleScore = CommonUtil.e(strArr[4].split("=")[1], -1);
        this.mSectionHolePutts = CommonUtil.e(strArr[5].split("=")[1], -1);
        this.mSectionHolePenalty = CommonUtil.e(strArr[6].split("=")[1], -1);
        this.mSectionHoleFairwayHits = CommonUtil.e(strArr[7].split("=")[1], -1);
        this.mSectionHandicap = CommonUtil.e(strArr[8].split("=")[1], -1);
        this.mSectionValidTracks = CommonUtil.e(strArr[9].split("=")[1], -1);
        this.mSectionGolfShotTracks = (List) new Gson().fromJson(strArr[10].split("=")[1], new TypeToken<ArrayList<GolfShotTrack>>() { // from class: com.huawei.hwsportmodel.TrackGolfCourseSegment.1
        }.getType());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(20);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("shs=").append(getSectionHoleSeq()).append(";").append("shi=").append(getSectionHoleId()).append(";").append("sp=").append(getSectionPar()).append(";").append("shsc=").append(getSectionHoleScore()).append(";").append("shp=").append(getSectionHolePutts()).append(";").append("shpe=").append(getSectionHolePenalty()).append(";").append("shfh=").append(getSectionHoleFairwayHits()).append(";").append("shd=").append(getSectionHandicap()).append(";").append("svt=").append(getSectionValidTracks()).append(";").append("sgst=").append(new Gson().toJson(getSectionGolfShotTracks())).append(";").append(System.lineSeparator());
    }

    public int getSectionHoleSeq() {
        return this.mSectionHoleSeq;
    }

    public void setSectionHoleSeq(int i) {
        this.mSectionHoleSeq = i;
    }

    public long getSectionHoleId() {
        return this.mSectionHoleId;
    }

    public void setSectionHoleId(long j) {
        this.mSectionHoleId = j;
    }

    public int getSectionPar() {
        return this.mSectionPar;
    }

    public void setSectionPar(int i) {
        this.mSectionPar = i;
    }

    public int getSectionHoleScore() {
        return this.mSectionHoleScore;
    }

    public void setSectionHoleScore(int i) {
        this.mSectionHoleScore = i;
    }

    public int getSectionHolePutts() {
        return this.mSectionHolePutts;
    }

    public void setSectionHolePutts(int i) {
        this.mSectionHolePutts = i;
    }

    public int getSectionHolePenalty() {
        return this.mSectionHolePenalty;
    }

    public void setSectionHolePenalty(int i) {
        this.mSectionHolePenalty = i;
    }

    public int getSectionHoleFairwayHits() {
        return this.mSectionHoleFairwayHits;
    }

    public void setSectionHoleFairwayHits(int i) {
        this.mSectionHoleFairwayHits = i;
    }

    public int getSectionHandicap() {
        return this.mSectionHandicap;
    }

    public void setSectionHandicap(int i) {
        this.mSectionHandicap = i;
    }

    public int getSectionValidTracks() {
        return this.mSectionValidTracks;
    }

    public void setSectionValidTracks(int i) {
        this.mSectionValidTracks = i;
    }

    public List<GolfShotTrack> getSectionGolfShotTracks() {
        return this.mSectionGolfShotTracks;
    }

    public void setSectionGolfShotTracks(List<GolfShotTrack> list) {
        this.mSectionGolfShotTracks = list;
    }

    public String toString() {
        return "TrackGolfCourseSegment{mSectionHoleSeq=" + this.mSectionHoleSeq + ", mSectionHoleId=" + this.mSectionHoleId + ", mSectionPar=" + this.mSectionPar + ", mSectionHoleScore=" + this.mSectionHoleScore + ", mSectionHolePutts=" + this.mSectionHolePutts + ", mSectionHolePenalty=" + this.mSectionHolePenalty + ", mSectionHoleFairwayHits=" + this.mSectionHoleFairwayHits + ", mSectionHandicap=" + this.mSectionHandicap + ", mSectionValidTracks=" + this.mSectionValidTracks + ", mSectionGolfShotTracks=" + this.mSectionGolfShotTracks + '}';
    }
}
