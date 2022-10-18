package com.acclivousbyte.bassam.models.genrelModels

import java.io.Serializable


data class Data(
    val alive: String,
    val city: String,
    val contact: String,
    val country: String,
    val credentials_issued: String,
    val credentials_revoked: String,
     val education: List<Any>,
    val email: String,
    val father_name: String,
    val full_name: String,
    val g_grand_father_name: String,
    val gender: String,
    val generation: String,
    val grand_father_name: String,
    val id: Int,
    val instagram: String,
    val is_disabled: String,
    val is_locked: String,
    val is_worthy: String,
    val m_grand_father_name: String,
    val mobile: String,
    val name: String,
    val next_g_grand_father_one: String,
    val next_g_grand_father_two: String,
    val nodeID: String,
    val p_email: String,
    val p_landline: String,
    val p_mobile: String,
    val p_socialnetworks: String,
    val parent_id: String,
    val profile_picture_square: String,
    val sequence: String,
    val snapchat: String,
    val twitter: String,
    val w_grand_father_name: String,
    val workplaces: List<Any>
) : Serializable
