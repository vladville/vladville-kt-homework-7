package ru.netology
class ReportComment(val commentId: Int, val authorId: Int, val reason: Int)
enum class Reason { Spam, Porno, Violence }