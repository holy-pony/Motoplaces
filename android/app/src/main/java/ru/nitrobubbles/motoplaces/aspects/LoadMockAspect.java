package ru.nitrobubbles.motoplaces.aspects;

import android.util.Log;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;

import ru.nitrobubbles.motoplaces.model.MockMotoplaces;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import rx.Observable;

/**
 * Created by konstantinaksenov on 16.10.15.
 */
@Aspect
public class LoadMockAspect {
    @Pointcut("execution(* ru.nitrobubbles.motoplaces.MainActivity.loadMotoplaces(..))")
    public void loadMotoplaces(){}

    @Around("loadMotoplaces()")
    public Observable<ArrayList<Motoplace>> loadMockMotoplaces(){
        Log.e("Aspect", "LoadMockPlaces");
        return Observable.just(MockMotoplaces.getMockMotoplace());
    }
}
