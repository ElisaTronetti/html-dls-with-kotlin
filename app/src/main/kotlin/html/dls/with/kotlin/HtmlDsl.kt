package html.dls.with.kotlin

object HtmlDsl {
    interface Element {
        fun render(indent: String = "  "): String
    }

    interface TextElement : Element {
        val text: String
    }

    interface Tag : Element {
        val children: List<Element> // a tag might have children that have to be rendered
        fun addChild(child: Element): Unit
        val name: String
    }

    abstract class AbstractTag(override val name: String) : Tag {
        override var children: List<Element> = emptyList()

        override fun render(indent: String): String =
            """
            |$indent<$name>
            |${renderChild(indent)}
            |$indent</$name>
            """.trimMargin()

        private fun renderChild(indent: String): String {
            // render all the children and join the list of string returned
            return children.joinToString() { it.render(indent + indent) }
        }

        override fun addChild(child: Element) {
            children = children + child
        }
    }

    abstract class AbstractTagWithText(override val name: String) : AbstractTag(name) {
        operator fun String.unaryMinus() = addChild(Text(this))
    }

    class Text(override val text: String) : TextElement {
        override fun render(indent: String): String =
            """
                |$indent$text
            """.trimMargin()
    }

    class HTML() : AbstractTag("html") {
        fun head(conf: Head.() -> Unit) = addChild(Head().apply(conf))
    }

    class Head() : AbstractTag("head") {
        fun title(conf: Title.() -> Unit) = addChild(Title().apply(conf))
    }

    class Title() : AbstractTagWithText("title")

    fun html(init: HTML.() -> Unit): HTML = HTML().apply(init)

}
