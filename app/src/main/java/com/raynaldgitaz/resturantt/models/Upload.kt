package com.raynaldgitaz.resturantt.models

class Upload {
    var image:String = ""
    var name:String = ""
    var description:String = ""
    var price:String = ""
    var id:String = ""

    constructor(image: String, name: String, description: String, price: String, id: String) {
        this.image = image
        this.name = name
        this.description = description
        this.price = price
        this.id = id
    }
    constructor()
}