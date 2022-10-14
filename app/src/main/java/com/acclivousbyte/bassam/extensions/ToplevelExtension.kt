package com.acclivousbyte.bassam.utils

fun <T> T.wrapWithEvent() = Event(this)

//val internetWorkerConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
//    .build()