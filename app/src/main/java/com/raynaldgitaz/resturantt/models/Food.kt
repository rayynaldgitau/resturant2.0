package com.raynaldgitaz.resturantt.models

class Food {
    var name:String = ""
    var description:String = ""
    var price:String = ""
    var id:String = ""

    constructor(name: String, description: String, price: String, id: String) {
        this.name = name
        this.description = description
        this.price = price
        this.id = id
    }
    constructor()
}