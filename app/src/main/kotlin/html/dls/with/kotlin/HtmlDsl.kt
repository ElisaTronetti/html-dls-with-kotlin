package html.dls.with.kotlin

object HtmlDsl {

    interface Tag {
        val name: String
        fun render(): String
    }

    abstract class AbstractTag(override val name: String) : Tag {
        private val indent: String = " "

        override fun render(): String =
            """
            $indent<$name>
            $indent</$name>
            """.trimIndent()
    }

    class HTML() : AbstractTag("html")

    class Head() : AbstractTag("head")

    class Title() : AbstractTag("title")

    fun html(init: HTML.() -> Unit): HTML = HTML().apply(init)

}
