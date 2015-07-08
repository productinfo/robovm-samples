/*
 * Copyright (C) 2013-2015 RoboVM AB
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 * Portions of this code is based on Google Inc's Google Analytics sample
 * which is copyright (C) 2015 Google Inc.
 */
package org.robovm.samples.robopods.googleanalytics.ios;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSErrorException;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIColor;
import org.robovm.pods.google.GGLContext;
import org.robovm.pods.google.analytics.GAI;
import org.robovm.pods.google.analytics.GAILogLevel;

public class GoogleAnalyticsApp extends UIApplicationDelegateAdapter {

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        // Configure tracker from GoogleService-Info.plist.
        try {
            GGLContext.getSharedInstance().configure();
        } catch (NSErrorException e) {
            System.err.println("Error configuring the Google context: " + e.getError());
        }

        // Optional: configure GAI options.
        GAI gai = GAI.getSharedInstance();
        gai.enableCrashReporting();
        gai.getLogger().setLogLevel(GAILogLevel.Verbose);

        // Set a white background so that patterns are showcased.
        getWindow().setBackgroundColor(UIColor.white());

        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, GoogleAnalyticsApp.class);
        }
    }
}