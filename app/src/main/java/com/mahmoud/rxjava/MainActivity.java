package com.mahmoud.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Long> cold = Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS);
        cold.subscribe(i -> Log.e("MainActivity", "cold onCreate: Student 1: " + i), Throwable::printStackTrace);
        sleep(3000);
        cold.subscribe(i -> Log.e("MainActivity", "cold onCreate: Student 2: " + i), Throwable::printStackTrace);

        //convert cold observable to hot observable
        ConnectableObservable<Long> hot = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
        hot.connect();
        hot.subscribe(i-> Log.e("MainActivity","hot onCreate: Student 1: "+i), Throwable::printStackTrace);
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        hot.subscribe(i-> Log.e("MainActivity","hot onCreate: Student 2: "+i), Throwable::printStackTrace);

        //publishSubject new observer start observe when he come
        publishSubject();

        //behaviorSubject new observer start observe when he come and latest
        behaviorSubject();

        //replaySubject new observer replay all data before he come and continue
        replaySubject();

        //replaySubject new observer observale only last data
        asyncSubject();

    }

    public void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(i-> Log.e("MainActivity","PublishSubject onCreate: Student 1: "+i), Throwable::printStackTrace);
        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        sleep(1000);
        subject.subscribe(i-> Log.e("MainActivity","PublishSubject onCreate: Student 2: "+i), Throwable::printStackTrace);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
    }

    public void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(i-> Log.e("MainActivity","BehaviorSubject onCreate: Student 1: "+i), Throwable::printStackTrace);
        behaviorSubject.onNext("A");
        sleep(1000);
        behaviorSubject.onNext("B");
        sleep(1000);
        behaviorSubject.onNext("C");
        sleep(1000);
        behaviorSubject.onNext("D");
        sleep(1000);
        behaviorSubject.subscribe(i-> Log.e("MainActivity","BehaviorSubject onCreate: Student 2: "+i), Throwable::printStackTrace);
        behaviorSubject.onNext("E");
        sleep(1000);
        behaviorSubject.onNext("F");
        sleep(1000);
        behaviorSubject.onNext("G");
        sleep(1000);

    }

    public void replaySubject() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(i-> Log.e("MainActivity","replaySubject onCreate: Student 1: "+i), Throwable::printStackTrace);
        replaySubject.onNext("A");
        sleep(1000);
        replaySubject.onNext("B");
        sleep(1000);
        replaySubject.onNext("C");
        sleep(1000);
        replaySubject.onNext("D");
        sleep(1000);
        replaySubject.subscribe(i-> Log.e("MainActivity","replaySubject onCreate: Student 2: "+i), Throwable::printStackTrace);
        replaySubject.onNext("E");
        sleep(1000);
        replaySubject.onNext("F");
        sleep(1000);
        replaySubject.onNext("G");
        sleep(1000);
    }

    public void asyncSubject() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(i -> Log.e("MainActivity", "AsyncSubject onCreate: Student 1: " + i), Throwable::printStackTrace);
        asyncSubject.onNext("A");
        sleep(1000);
        asyncSubject.onNext("B");
        sleep(1000);
        asyncSubject.onNext("C");
        sleep(1000);
        asyncSubject.onNext("D");
        sleep(1000);
        asyncSubject.subscribe(i-> Log.e("MainActivity","AsyncSubject onCreate: Student 2: "+i), Throwable::printStackTrace);
        asyncSubject.onNext("E");
        sleep(1000);
        asyncSubject.onNext("F");
        sleep(1000);
        asyncSubject.onNext("G");
        sleep(1000);
        asyncSubject.onComplete();
    }

    public void sleep(int i){
        try {
            Thread.sleep(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}