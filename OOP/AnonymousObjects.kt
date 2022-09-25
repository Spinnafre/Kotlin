val normalWeight = listOf<Float>(18.6F,24.9F)
val surpassWeight = listOf<Float>(25.0F,29.9F)
val obesityTypeOne = listOf<Float>(30.0F,34.9F)
val obesityTypeTwo = listOf<Float>(35.0F,39.9F)
val obesityTypeThree = listOf<Float>(40.0F)

class Person(firstName:String,lastName:String,var age:Int?=null,category:Int=0,val weight:Float?,val height:Float?){
    var firstName:String=firstName
        get() = "$field"
        set(value:String){
            field = value
        }
        
    var lastName:String=lastName
        get() = "$field"
        set(value:String){
            field = value
        }

    private var category:String = "Normal"
    
    fun setCategory(value:Int){
        this.category = when(value){
            0 -> "Abaixo do Peso"
            1 -> "Sobrepeso"
            2 -> "Obesidade tipo 1"
            3 -> "Obesidade tipo 2"
            4 -> "Obesidade tipo 3"
            else -> "Normal"
        }
    }
    
    fun getCategory():String{
        return this.category
    }
    
    val fullName:String? 
        get() = "Nome completo: ${firstName} ${lastName}"
    
}
interface IMC{
    val imc:Float
    val category:Int
}

fun calcIMC(people:Person):IMC{
    val value:Float = if(people.weight != null && people.height != null) people.weight / (people.height * people.height) else 0.00F
    
    return object:IMC {
        override val imc:Float = value
        override val category = when(value){
            in surpassWeight -> 1
            in obesityTypeOne -> 2
            in obesityTypeTwo -> 3
            in obesityTypeThree -> 4
            else -> 5
        }
        override fun toString() = "IMC: $imc \t CATEGORY: $category"
    }
    
}

fun main() {
    val p = Person("Davi","Silva",21,0,75.00f,1.72f)
    val imc = calcIMC(p)
    println(imc)
    p.setCategory(imc.category)
    println(p.getCategory())
}
