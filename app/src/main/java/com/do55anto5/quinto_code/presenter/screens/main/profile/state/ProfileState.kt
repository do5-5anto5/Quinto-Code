package com.do55anto5.quinto_code.presenter.screens.main.profile.state

import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType


data class ProfileState(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val city: String = "",
    val hasFeedBack: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val isLoading: Boolean = true,
)