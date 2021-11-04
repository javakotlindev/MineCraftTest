package com.tellit.minecrafttest.model.mods

import com.google.gson.annotations.SerializedName

data class ModsData(
    @SerializedName("5mkbgj_0o")
    val mkbgj_0o: String,
    @SerializedName("5mkbgj_rk")
    val mkbgj_rk: String,
    @SerializedName("5mkbgj_list")
    val mkbgj_list5: HashMap<String, ModsInfoData>
)

data class ModsInfoData(
    @SerializedName("5mkbgj_pw")
    val mkbgj_pw5: String?,
    @SerializedName("5mkbgjt3")
    val mkbgjt3: String?,
    @SerializedName("5mkbgj_ieq")
    val mkbgj_ieq: String?,
    @SerializedName("5mkbgji1")
    val mkbgji1: String?,
    @SerializedName("5mkbgjd4")
    val mkbgjd4: String?,
    @SerializedName("5mkbgjf2")
    val mkbgjf2: String?,
)