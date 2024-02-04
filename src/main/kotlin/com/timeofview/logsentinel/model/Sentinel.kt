package com.timeofview.logsentinel.model

data class Sentinel(
    var triggerString: String,
    var messageString: String,
    var isEnabledPopup: Boolean,
    var isEnabledSound: Boolean,
    var audioPath: String
)