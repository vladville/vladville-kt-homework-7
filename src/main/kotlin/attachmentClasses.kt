package ru.netology;

sealed class Attachment(val type: String) {

    class FileAttachment(type: String, val attachment: File) : Attachment(type)

    class File(type: String, val ownerId: Int, val src: String, val size: Int, val name: String) :
        Attachment(type)

    class PhotoAttachment(type: String, val attachment: Photo) : Attachment(type)

    class Photo(
        type: String, val ownerId: Int, val src: String, val width: Int, val height: Int, val text: String
    ) : Attachment(
        type
    )

    class VideoAttachment(type: String, val attachment: Video) : Attachment(type)

    class Video(
        type: String,
        val ownerId: Int,
        val src: String,
        val duration: Int,
        val title: String,
        val views: Int,
    ) : Attachment(type)

    class UrlAttachment(type: String, val attachment: Url) : Attachment(type)

    class Url(type: String, val ownerId: Int, val url: String, val name: String, val description: String) :
        Attachment(
            type
        )

    class GeoAttachment(type: String, val attachment: Geo) : Attachment(type)

    class Geo(
        type: String,
        val ownerId: Int,
        val latitude: Double,
        val longitude: Double,
        val name: String,
        val description: String
    ) :
        Attachment(
            type
        )
}

fun main() {
    val geo = Attachment.Geo("geo", 1, 132.075857, 43.142933, "Тестовая точка", "Точка для теста")
    val geoAttachment = Attachment.GeoAttachment(geo.type, geo)
    println(geoAttachment)
}