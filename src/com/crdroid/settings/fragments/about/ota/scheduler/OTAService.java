/*
 * Copyright (C) 2016-2017 crDroid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crdroid.settings.fragments.about.ota.scheduler;

import android.content.Intent;
import android.os.AsyncTask;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.crdroid.settings.fragments.about.ota.tasks.CheckUpdateTask;
import com.crdroid.settings.fragments.about.ota.utils.OTAUtils;

public class OTAService extends WakefulIntentService {

    public OTAService() {
        super("crDroidOTA");
    }

    @Override
    protected void doWakefulWork(Intent intent) {
        CheckUpdateTask otaChecker = CheckUpdateTask.getInstance(true);
        if (!otaChecker.getStatus().equals(AsyncTask.Status.RUNNING)) {
            OTAUtils.logInfo("crDroidOTA scheduled check is running.");
            otaChecker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getApplicationContext());
        }
    }
}
