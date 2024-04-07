package com.beyondeye.kbloc.utils

import kotlin.random.Random


//based on https://github.com/pd4d10/nanoid-dart
// see also https://github.com/ai/nanoid
public const val urlAlphabet:String = "ModuleSymbhasOwnPr-0123456789ABCDEFGHNRVfgctiUvz_KqYTJkLxpZXIjQW"
public const val extAlphabet:String = "$urlAlphabet!@#$%^&*()_+-=[]{}\\|;':,./<>?"
public fun nanoid(size:Int=  21, alphabet:String= extAlphabet, rndgen: Random):String {
    val  len = alphabet.length;
    var size=size
    val  id=StringBuilder(size)
    while (0 < size--) {
        id.append(alphabet[rndgen.nextInt(len)])
    }
    return id.toString()
}
