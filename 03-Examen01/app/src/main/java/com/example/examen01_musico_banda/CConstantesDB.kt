package com.example.examen01_musico_banda

object CConstantesDB {

    const val DB_NAME = "BANDA_MUSICO_DB"

    const val DB_VERSION = 1

    const val TABLE_NAME_BAND = "BANDA_INFO_TABLE"

    const val B_ID = "ID"
    const val B_NAME = "NAME"
    const val B_GENRE = "GENRE"
    const val B_AWARDS = "AWARDS"
    const val B_YEARCREATION = "YEAR_CREATION"
    const val B_FUNDSPERCONCERT = "FUNDS_PER_CONCERT"

    const val CREATE_TABLE_BAND = ("CREATE TABLE " + TABLE_NAME_BAND + " ("
            + B_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + B_NAME + " VARCHAR(50),"
            + B_GENRE + " VARCHAR(50),"
            + B_AWARDS + " VARCHAR(50),"
            + B_YEARCREATION + " VARCHAR(50),"
            + B_FUNDSPERCONCERT + " VARCHAR(50)"
            + ");")

    const val TABLE_NAME_MUSICIAN = "MUSICO_INFO_TABLE"

    const val M_ID = "ID"
    const val FKB_ID = "BANDA_ID"
    const val M_NAME = "NAME"
    const val M_BIRTHDAY = "BIRTHDAY"
    const val M_PERSONAL_AWARDS = "PERSONAL_AWARDS"
    const val M_POSITION = "POSITION"
    const val M_ACTIVITY = "ACTIVITY"

    const val CREATE_TABLE_MUSICIAN = ("CREATE TABLE " + TABLE_NAME_MUSICIAN + " ("
            + M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            //+ FKB_ID + " INTEGER,"
            + M_NAME + " VARCHAR(50),"
            + M_BIRTHDAY + " VARCHAR(50),"
            + M_PERSONAL_AWARDS + " VARCHAR(50),"
            + M_POSITION + " VARCHAR(50),"
            + M_ACTIVITY + " VARCHAR(50)"
            + ");")

}