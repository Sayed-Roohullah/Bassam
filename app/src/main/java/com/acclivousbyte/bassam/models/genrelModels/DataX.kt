package com.acclivousbyte.bassam.models.genrelModels

import java.io.Serializable

data class DataX(
    val alive: String,
    val branch: String,
    val brief_description: String,
    val children: List<Children>,
    val city: String,
    val commitee_designation: String,
    val commitee_name: String,
    val contact: String,
    val contact_extension: String,
    val country: String,
    val credentials_issued: String,
    val credentials_revoked: String,
    val dob: String,
    val dob_calendar_type: String,
    val education: List<Education>,
    val email: String,
    val father_name: String,
    val full_name: String,
    val g_grand_father_name: String,
    val gender: String,
    val generation: String,
    val governer: String,
    val grand_father_name: String,
    val id: Int,
    val in_family_committee: String,
    val instagram: String,
    val is_celebrity: String,
    val is_disabled: String,
    val is_locked: String,
    val is_worthy: String,
    val judge: String,
    val m_father_name: String,
    val m_grand_father_name: String,
    val mobile: String,
    val mother_full_name: String,
    val mother_name: String,
    val name: String,
    val next_g_grand_father_one: String,
    val next_g_grand_father_two: String,
    val nodeID: String,
    val p_dob: String,
    val p_education: String,
    val p_email: String,
    val p_landline: String,
    val p_location: String,
    val p_mobile: String,
    val p_mother_info: String,
    val p_profile_picture: String,
    val p_socialnetworks: String,
    val p_wife_info: String,
    val p_workplace: String,
    val parent_id: String,
    val poet: String,
    val profile_picture_circle: String,
    val profile_picture_square: String,
    val ref_id: String,
    val scholar: String,
    val scientist: String,
    val sequence: String,
    val siblings: List<Sibling>,
    val snapchat: String,
    val status: String,
    val twitter: String,
    val username: String,
    val w_father_name: String,
    val w_grand_father_name: String,
    val wife_name: String,
    val workplaces: List<Workplace>
) : Serializable