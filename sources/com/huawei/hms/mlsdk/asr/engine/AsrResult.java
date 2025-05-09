package com.huawei.hms.mlsdk.asr.engine;

import com.huawei.hms.mlsdk.asr.engine.cloud.vo.RttSegment;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class AsrResult {
    private boolean isFinal;
    private int retCode;
    private String retMessage;
    private ArrayList<RttSegment> sentenceOffsetList;
    private String text;
    private ArrayList<RttSegment> wordOffsetList;

    public AsrResult(int i) {
        this(i, null);
    }

    public int getRetCode() {
        return this.retCode;
    }

    public String getRetMessage() {
        return this.retMessage;
    }

    public ArrayList<RttSegment> getSentenceOffsetList() {
        return this.sentenceOffsetList;
    }

    public String getText() {
        return this.text;
    }

    public ArrayList<RttSegment> getWordOffsetList() {
        return this.wordOffsetList;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void setFinal(boolean z) {
        this.isFinal = z;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    public void setRetMessage(String str) {
        this.retMessage = str;
    }

    public void setSentenceOffsetList(ArrayList<RttSegment> arrayList) {
        this.sentenceOffsetList = arrayList;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setWordOffsetList(ArrayList<RttSegment> arrayList) {
        this.wordOffsetList = arrayList;
    }

    public AsrResult(int i, String str) {
        this(i, null, str);
    }

    public AsrResult(int i, String str, String str2) {
        this(i, str, str2, false);
    }

    public AsrResult(int i, String str, String str2, boolean z) {
        this.text = str2;
        this.retCode = i;
        this.retMessage = str;
        this.isFinal = z;
    }
}
