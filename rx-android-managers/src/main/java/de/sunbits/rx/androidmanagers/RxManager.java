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

package de.sunbits.rx.androidmanagers;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import de.sunbits.rx.androidmanagers.location.LocationConfig;
import de.sunbits.rx.androidmanagers.location.LocationObservable;
import de.sunbits.rx.androidmanagers.location.LocationProvider;
import rx.Observable;

/**
 * Created by Matthias Koch on 01/07/16.
 */

public class RxManager {

    private final Context context;

    public RxManager(final Context context) {

        this.context = context;
    }

    /**
     * creates an Observable that returns current GPS location and max update rate.
     * <p>
     * Listening to GPS locations updates will be started when subscribing and will be
     * stopped on unSubscribe or in case of an error.
     *
     * @return an Observable providing latest location updates
     */
    public Observable<Location> createLocationObservable() {

        LocationConfig config = LocationConfig.newBuilder().withProvider(LocationProvider.GPS).withMinDistance(0).withMinTime(0).build();
        return createLocationObservable(config);
    }

    /**
     * creates an Observable that returns current location based on provided config.
     * <p>
     * Listening to locations updates will be started when subscribing and will be
     * stopped on unSubscribe or in case of an error.
     *
     * @param config configuration for provider minTime and minDistance
     *
     * @return an Observable providing latest location updates
     */
    public Observable<Location> createLocationObservable(LocationConfig config) {

        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return LocationObservable.create(mLocationManager, config);
    }
}
