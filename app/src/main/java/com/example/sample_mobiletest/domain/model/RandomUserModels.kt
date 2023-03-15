package com.example.sample_mobiletest.domain.model

import com.example.sample_mobiletest.ui.model.InfoUser
import com.google.gson.annotations.SerializedName

data class ResponseApi(@SerializedName("results") val userInfo: List<UserInfo>)

data class UserInfo(
        @SerializedName("gender") val gender: String,
        @SerializedName("name") val name: Name,
        @SerializedName("location") val location: Location,
        @SerializedName("email") val email: String,
        @SerializedName("dob") val dob: Dob,
        @SerializedName("cell") val phone: String,
        @SerializedName("picture") val picture: Picture)

data class Name(@SerializedName("title") val title: String,
                @SerializedName("first") val name: String,
                @SerializedName("last") val lastname: String)


data class Location(
        @SerializedName("street") val street: Street,
        @SerializedName("city") val city: String,
        @SerializedName("state") val state: String,
        @SerializedName("country") val country: String,
        @SerializedName("postcode") val zipcode: String,
        @SerializedName("coordinates") val coordinates: Coordinates,
)

data class Coordinates(@SerializedName("latitude") val latitude: String,
                       @SerializedName("longitude") val longitude: String)

data class Street(@SerializedName("number") val houseNumber: Int,
                  @SerializedName("name") val street: String)

data class Dob(@SerializedName("age") val age: Int)

data class Picture(@SerializedName("medium") val image: String)

fun UserInfo.toInfoUser() = InfoUser(name = "${name.title} ${name.name}",
        lastname = name.lastname,
        age = "${dob.age} años",
        street = location.street.street,
        number = "${location.street.houseNumber}",
        country = location.country,
        state = location.state,
        zipcode = location.zipcode,
        gender = gender,
        email = email,
        phone = phone,
        image = picture.image,
        latitude = location.coordinates.latitude.toDouble(),
        longitude = location.coordinates.longitude.toDouble(),
        city = location.city)