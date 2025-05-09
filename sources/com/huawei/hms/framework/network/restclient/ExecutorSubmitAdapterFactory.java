package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.framework.network.restclient.SubmitAdapter;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.network.base.util.TypeUtils;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class ExecutorSubmitAdapterFactory extends SubmitAdapter.Factory {
    private Executor executor;

    public ExecutorSubmitAdapterFactory(Executor executor) {
        this.executor = executor;
    }

    @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter.Factory
    public SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient) {
        final Type submitResponseType = TypeUtils.getSubmitResponseType(type);
        return new SubmitAdapter<Object, Object>() { // from class: com.huawei.hms.framework.network.restclient.ExecutorSubmitAdapterFactory.1
            @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter
            public Type responseType() {
                return submitResponseType;
            }

            @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter
            /* renamed from: adapt */
            public Object adapt2(Submit<Object> submit) {
                ExecutorSubmitAdapterFactory executorSubmitAdapterFactory = ExecutorSubmitAdapterFactory.this;
                return executorSubmitAdapterFactory.new ExecutorSubmit(submit, executorSubmitAdapterFactory.executor);
            }
        };
    }

    /* loaded from: classes9.dex */
    class ExecutorSubmit<T> implements Submit<T> {
        Executor executor;
        Submit<T> submit;

        ExecutorSubmit(Submit<T> submit, Executor executor) {
            this.submit = submit;
            this.executor = executor;
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public Request request() throws IOException {
            return this.submit.request();
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public Response<T> execute() throws IOException {
            return this.submit.execute();
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public void cancel() {
            this.submit.cancel();
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public boolean isExecuted() {
            return this.submit.isExecuted();
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public boolean isCanceled() {
            return this.submit.isCanceled();
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Submit m601clone() {
            return ExecutorSubmitAdapterFactory.this.new ExecutorSubmit(this.submit, this.executor);
        }

        @Override // com.huawei.hms.framework.network.restclient.Submit
        public void enqueue(final ResultCallback<T> resultCallback) {
            this.submit.enqueue(new ResultCallback<T>() { // from class: com.huawei.hms.framework.network.restclient.ExecutorSubmitAdapterFactory.ExecutorSubmit.1
                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onResponse(final Response<T> response) {
                    ExecutorSubmit.this.executor.execute(new Runnable() { // from class: com.huawei.hms.framework.network.restclient.ExecutorSubmitAdapterFactory.ExecutorSubmit.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (ExecutorSubmit.this.isCanceled()) {
                                resultCallback.onFailure(new IOException("Canceled"));
                            } else {
                                resultCallback.onResponse(response);
                            }
                        }
                    });
                }

                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onFailure(final Throwable th) {
                    ExecutorSubmit.this.executor.execute(new Runnable() { // from class: com.huawei.hms.framework.network.restclient.ExecutorSubmitAdapterFactory.ExecutorSubmit.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            resultCallback.onFailure(th);
                        }
                    });
                }
            });
        }
    }
}
