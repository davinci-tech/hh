package com.study.apnea.model.bean.algorithm;

/* loaded from: classes9.dex */
public class OsaAppInputS {
    private DsStateT[] dsState;
    private int dsStateLen;
    private OsaRriResultS[] rriBuffer;
    private int rriBufferLen;
    private OsaSpo2DataBufferS[] spo2Buffer;
    private int spo2BufferLen;

    public OsaAppInputS() {
    }

    public OsaAppInputS(int i, int i2, int i3, OsaSpo2DataBufferS[] osaSpo2DataBufferSArr, OsaRriResultS[] osaRriResultSArr, DsStateT[] dsStateTArr) {
        this.dsStateLen = i;
        this.spo2BufferLen = i2;
        this.rriBufferLen = i3;
        this.spo2Buffer = osaSpo2DataBufferSArr;
        this.rriBuffer = osaRriResultSArr;
        this.dsState = dsStateTArr;
    }

    public int getDsStateLen() {
        return this.dsStateLen;
    }

    public int getSpo2BufferLen() {
        return this.spo2BufferLen;
    }

    public int getRriBufferLen() {
        return this.rriBufferLen;
    }

    public OsaSpo2DataBufferS[] getSpo2Buffer() {
        return this.spo2Buffer;
    }

    public OsaRriResultS[] getRriBuffer() {
        return this.rriBuffer;
    }

    public DsStateT[] getDsState() {
        return this.dsState;
    }

    public void setDsStateLen(int i) {
        this.dsStateLen = i;
    }

    public void setSpo2BufferLen(int i) {
        this.spo2BufferLen = i;
    }

    public void setRriBufferLen(int i) {
        this.rriBufferLen = i;
    }

    public void setSpo2Buffer(OsaSpo2DataBufferS[] osaSpo2DataBufferSArr) {
        this.spo2Buffer = osaSpo2DataBufferSArr;
    }

    public void setRriBuffer(OsaRriResultS[] osaRriResultSArr) {
        this.rriBuffer = osaRriResultSArr;
    }

    public void setDsState(DsStateT[] dsStateTArr) {
        this.dsState = dsStateTArr;
    }
}
