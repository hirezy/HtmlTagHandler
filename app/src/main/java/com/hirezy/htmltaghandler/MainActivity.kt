package com.hirezy.htmltaghandler

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.hirezy.htmltag.htmltext.HtmlTagHandler
import com.hirezy.htmltaghandler.ui.theme.HtmlTagHandlerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HtmlTagHandlerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoadAndroidViewByHtmlTextTag(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HtmlTagHandlerTheme {
        Greeting("Android")
    }
}
@Composable
fun LoadAndroidViewByHtmlTextTag(modifier: Modifier = Modifier) {
    val htmlTagHandler = HtmlTagHandler()
    AndroidView(
        factory = {
            htmlTagHandler.registerTag("section", SectionTag(it))
            htmlTagHandler.registerTag("custom", SectionTag(it))
            htmlTagHandler.registerTag("span", SectionTag(it))
            TextView(it).apply {

            }
        },
        modifier = modifier.wrapContentSize()
    ) {

        val source =
            "<html>今天<span style='color:#FFE31335;font-size:16sp;background-color:white;'>星期二</span>，<custom style='color:#fff;font-size:14sp;background-color:red;'>但是我还要加班</custom><html>"

        val spanned = Html.fromHtml(source, htmlTagHandler, htmlTagHandler)
        it.text = spanned

    }
}