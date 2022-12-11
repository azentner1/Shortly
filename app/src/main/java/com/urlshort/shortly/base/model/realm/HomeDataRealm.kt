package com.urlshort.shortly.base.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HomeDataRealm: RealmObject() {
    @PrimaryKey
    var shortLink: String = ""
    var fullShortLink: String = ""
    var originalLink: String = ""
}