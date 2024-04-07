package com.beyondeye.kbloc.utils

import kotlin.js.Date

public actual fun epochMillis():Long = Date.now().toLong()
