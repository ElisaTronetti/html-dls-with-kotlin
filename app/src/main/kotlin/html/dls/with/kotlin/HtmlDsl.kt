package html.dls.with.kotlin

object HtmlDsl {

    class HTML() {
        val name: String = "html"
    }

    class Head() {
        val name: String = "head"
    }

    class Title(){
        val name: String = "title"
    }

    fun html(init: HTML.() -> Unit): HTML = HTML().apply(init)

}
