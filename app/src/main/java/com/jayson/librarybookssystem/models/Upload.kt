package com.jayson.librarybookssystem.models

class Upload{
    var subject:String=""
    var form:String=""
    var bookno:String=""
    constructor(subject:String,form:String,bookno:String,id:String)
    {
        this.subject=subject
        this.form=form
        this.bookno=bookno
    }
    constructor()
}