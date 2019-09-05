package dto

data class Yazarlar (  val Id:String,
                       val Fullname:String,
                       val ContentType:String,
                       val CreatedDate:String,
                       val Files:List<Files>,
                       val Path:String,
                       val Url:String)
