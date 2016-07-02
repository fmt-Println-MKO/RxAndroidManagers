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

import android.location.LocationManager;

/**
 * Created by Matthias Koch on 01/07/16.
 */

public enum LocationProvider {
    GPS(LocationManager.GPS_PROVIDER),
    NETWORK(LocationManager.NETWORK_PROVIDER),
    PASSIVE(LocationManager.PASSIVE_PROVIDER);

    private final String providerName;

    public String getProviderName() {

        return providerName;
    }

    LocationProvider(final String providerName) {

        this.providerName = providerName;
    }
}
