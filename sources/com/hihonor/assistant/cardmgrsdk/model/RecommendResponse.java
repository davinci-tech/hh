package com.hihonor.assistant.cardmgrsdk.model;

import com.hihonor.assistant.cardmgrsdk.model.recommend.Response;

/* loaded from: classes8.dex */
public class RecommendResponse {
    public Response result;
    public int resultCode = 104;

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setResult(Response response) {
        this.result = response;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Response getResult() {
        return this.result;
    }
}
