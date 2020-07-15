package com.aprp.saltstudioandroid.Admin

class GameData(val game_name: String, val game_platform: String,
               val game_price: String, val game_image: String) {
    constructor(): this("","","","")
}