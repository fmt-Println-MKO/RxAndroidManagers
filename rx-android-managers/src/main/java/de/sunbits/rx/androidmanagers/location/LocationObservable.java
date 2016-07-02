/*
 * Copyright 2016 Matthias Koch
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package de.sunbits.rx.androidmanagers.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;

import de.sunbits.rx.androidmanagers.BuildConfig;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Matthias Koch on 01/07/16.
 */
@SuppressWarnings("MissingPermission")
public class LocationObservable {

    private static final String TAG = "LocationObservable";

    public static Observable<Location> create(final LocationManager mLocationManager) {

        LocationConfig config = LocationConfig.newBuilder().withProvider(LocationProvider.GPS).withMinDistance(0).withMinTime(0).build();
        return create(mLocationManager, config);
    }

    public static Observable<Location> create(final LocationManager mLocationManager, final LocationConfig config) {

        final LocationListener[] listener = new LocationListener[1];

        return Observable.create(new Observable.OnSubscribe<Location>() {
            @Override
            public void call(final Subscriber<? super Location> subscriber) {

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "call, register subscriber: " + subscriber);
                }
                listener[0] = new LocationListener() {

                    @Override
                    public void onLocationChanged(final Location location) {

                        subscriber.onNext(location);
                    }

                    @Override
                    public void onStatusChanged(final String s, final int i, final Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(final String s) {

                    }

                    @Override
                    public void onProviderDisabled(final String s) {

                    }
                };

                HandlerThread mHandlerThread = new HandlerThread("RxLocationThread");
                mHandlerThread.start();
                mLocationManager.requestLocationUpdates(config.getProvider(), config.getMinTime(), config.getMinDistance(), listener[0], mHandlerThread.getLooper());
            }
        })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {

                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "call unsubscribe " + listener[0]);
                        }
                        if (listener[0] != null) {
                            mLocationManager.removeUpdates(listener[0]);
                            listener[0] = null;
                        }
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {

                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "call onError " + listener[0]);
                        }
                        if (listener[0] != null) {
                            mLocationManager.removeUpdates(listener[0]);
                            listener[0] = null;
                        }
                    }
                });
    }

}
