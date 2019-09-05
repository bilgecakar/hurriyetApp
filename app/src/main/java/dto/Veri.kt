package dto

data class Veri(
                 val Id:Int,
                 val ContentType:String,
                 val CreatedDate:String,
                 val Description:String,
                 val ModifiedDate:String,
                 val Path:String,
                 val StartDate: String,
                 val Tags:List<String>,
                 val Files: List<File>,
                 val Title:String,
                 val Url:String)
