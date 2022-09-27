package html.dls.with.kotlin

fun main() {
    with(HtmlDsl) { // this: HtmlDsl
        val htmlCode: String = html {
            // it is like be in HTML class now
            head {
                title {

                }
            }
        }.render()
        print(htmlCode)
    }
}
