package com.example.sample_mobiletest.ui.model

data class MapsUiModel(val showRefresh: Boolean,
                       val infoUser: InfoUser?,
                       val exception: Exception?)

data class InfoUser(val name: String,
                    val lastname:String,
                    val age: String,
                    val street: String,
                    val number: String,
                    val country: String,
                    val state: String,
                    val zipcode: String,
                    val gender: String,
                    val email: String,
                    val phone: String,
                    val image: String,
                    val latitude: Double,
                    val longitude: Double,
                    val city: String)
