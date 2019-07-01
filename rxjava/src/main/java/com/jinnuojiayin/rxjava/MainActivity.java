package com.jinnuojiayin.rxjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCompositeDisposable();
    }

    private void initCompositeDisposable() {
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            //ObservableEmitter发射器
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Integer integer) {
// 判断mDisposable.isDisposed() 如果解除了则不需要处理
            }

            @Override
            public void onError(Throwable e) {
                //onError()和onComplete()不能同时使用
                //onError()不能多次使用
            }

            @Override
            public void onComplete() {
                //onComplete()可多次使用

            }
        });
        //解除所有订阅者
        compositeDisposable.clear();
    }
}
