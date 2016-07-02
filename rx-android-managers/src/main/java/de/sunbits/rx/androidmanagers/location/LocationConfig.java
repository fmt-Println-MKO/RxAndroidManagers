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

/**
 * Created by Matthias Koch on 01/07/16.
 */

public class LocationConfig {

    private final LocationProvider provider;
    private final float minDistance;
    private final long minTime;

    private LocationConfig(final Builder builder) {

        provider = builder.provider;
        minDistance = builder.minDistance;
        minTime = builder.minTime;
    }

    /**
     * @return Provider name according to Android provider names
     */
    public String getProvider() {

        return provider.getProviderName();
    }

    /**
     * @return min distance between since last location
     */
    public float getMinDistance() {

        return minDistance;
    }

    /**
     * @return min time since last location
     */
    public long getMinTime() {

        return minTime;
    }

    public static Builder newBuilder() {

        return new Builder();
    }

    public static Builder newBuilder(final LocationConfig copy) {

        Builder builder = new Builder();
        builder.provider = copy.provider;
        builder.minDistance = copy.minDistance;
        builder.minTime = copy.minTime;
        return builder;
    }

    /**
     * {@code LocationConfig} builder static inner class.
     */
    public static final class Builder {

        private LocationProvider provider;
        private float minDistance;
        private long minTime;

        private Builder() {

        }

        /**
         * Sets the {@code provider} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code provider} to set
         *
         * @return a reference to this Builder
         */
        public Builder withProvider(final LocationProvider val) {

            provider = val;
            return this;
        }

        /**
         * Sets the {@code minDistance} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code minDistance} to set
         *
         * @return a reference to this Builder
         */
        public Builder withMinDistance(final float val) {

            minDistance = val;
            return this;
        }

        /**
         * Sets the {@code minTime} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code minTime} to set
         *
         * @return a reference to this Builder
         */
        public Builder withMinTime(final long val) {

            minTime = val;
            return this;
        }

        /**
         * Returns a {@code LocationConfig} built from the parameters previously set.
         *
         * @return a {@code LocationConfig} built with parameters of this {@code LocationConfig.Builder}
         */
        public LocationConfig build() {

            return new LocationConfig(this);
        }
    }
}
