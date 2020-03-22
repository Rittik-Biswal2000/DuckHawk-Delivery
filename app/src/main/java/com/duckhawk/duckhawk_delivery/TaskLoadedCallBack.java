package com.duckhawk.duckhawk_delivery;

import com.google.android.gms.maps.model.PolylineOptions;

interface TaskLoadedCallback {
    void onTaskDone(PolylineOptions values, String distance);
}