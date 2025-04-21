package com.yuyan.inputmethod.util

object DoublePinYinUtils {
    val double_pinyin_abc = mapOf(
        'a' to "zh",
        'e' to "ch",
        'v' to "sh",
    )
    val double_pinyin_ziguang = mapOf(
        'u' to "zh",
        'a' to "ch",
        'i' to "sh",
    )
    val double_pinyin_ls17 = mapOf(
        'z' to "zh",
        'c' to "ch",
        'h' to "sh",
    )
    val double_pinyin = mapOf(
        'v' to "zh",
        'i' to "ch",
        'u' to "sh",
    )

    val doublePinyinMap = mapOf(
        "double_pinyin_ziguang" to double_pinyin_abc,
        "double_pinyin_ziguang" to double_pinyin_ziguang,
        "double_pinyin_ls17" to double_pinyin_ls17,
    )

    fun getDoublePinYinComposition(rimeSchema: String, composition: String, comment: String): String {
        val compositionList = composition.filter { it.code <= 0xFF }.split("'".toRegex())
        return buildSpannedString {
            append(composition.filter { it.code > 0xFF })
            comment.split("'").zip(compositionList).forEach { (pinyin, compo) ->
                append(if (compo.length >= 2) pinyin else {
                    doublePinyinMap.getOrElse(rimeSchema){double_pinyin}.getOrElse(compo[0]) { pinyin.first().toString() }
                })
                append("'")
            }
        }
    }
}