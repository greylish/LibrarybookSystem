package com.jayson.librarybookssystem.models

class book{
    var subject:String=""
    var form:String=""
    var bookNo:String=""
    constructor(subject:String,form:String,bookNo:String)
    {
        this.subject=subject
        this.form=form
        this.bookNo=bookNo
    }
    constructor()
}