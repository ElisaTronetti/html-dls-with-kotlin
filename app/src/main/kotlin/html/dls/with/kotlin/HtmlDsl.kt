package html.dls.with.kotlin

object HtmlDsl {

    interface Tag {
        val name: String
        val children: List<Tag> // a tag might have children that have to be rendered
        fun render(): String
        fun addChild(child: Tag): Unit
    }

    abstract class AbstractTag(override val name: String) : Tag {
        private val indent: String = " "
        override var children: List<Tag> = emptyList()

        override fun render(): String =
            """
            $indent<$name>
            $indent</$name>
            """.trimIndent()

        override fun addChild(child: Tag) {
            children = children + child
        }
    }

    class HTML() : AbstractTag("html") {
        fun head(conf: Head.() -> Unit) = addChild(Head().apply(conf))
    }

    class Head() : AbstractTag("head") {
        fun title(conf: Title.() -> Unit) = Title().apply(conf)
    }

    class Title() : AbstractTag("title")

    fun html(init: HTML.() -> Unit): HTML = HTML().apply(init)

}
