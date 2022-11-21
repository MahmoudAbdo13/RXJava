package com.mahmoud.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
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
        cold.subscribe(i -> Log.e("MainActivity", "cold onCreate: Student 1: " + i));
        sleep(3000);
        cold.subscribe(i -> Log.e("MainActivity", "cold onCreate: Student 2: " + i));

        //convert cold observable to hot observable
        ConnectableObservable<Long> hot = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
        hot.connect();
        hot.subscribe(i-> Log.e("MainActivity","hot onCreate: Student 1: "+i));
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        hot.subscribe(i-> Log.e("MainActivity","hot onCreate: Student 2: "+i));

        //publishSubject new observer start observe when he come
        publishSubject();

        //behaviorSubject new observer start observe when he come
        behaviorSubject();

    }

    public void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(i-> Log.e("MainActivity","PublishSubject onCreate: Student 1: "+i));
        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        sleep(1000);
        subject.subscribe(i-> Log.e("MainActivity","PublishSubject onCreate: Student 2: "+i));
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
    }

    public void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(i-> Log.e("MainActivity","BehaviorSubject onCreate: Student 1: "+i));
        behaviorSubject.onNext("A");
        sleep(1000);
        behaviorSubject.onNext("B");
        sleep(1000);
        behaviorSubject.onNext("C");
        sleep(1000);
        behaviorSubject.onNext("D");
        sleep(1000);
        behaviorSubject.subscribe(i-> Log.e("MainActivity","BehaviorSubject onCreate: Student 2: "+i));
        behaviorSubject.onNext("E");
        sleep(1000);
        behaviorSubject.onNext("F");
        sleep(1000);
        behaviorSubject.onNext("G");
        sleep(1000);

    }



    public void sleep(int i){
        try {
            Thread.sleep(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}