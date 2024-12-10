package com.example.lifespaerktech
// moving to the graph.
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.example.lifespaerktech.ui.theme.LifespaerktechTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifespaerktechTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BarChartComposable(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BarChartComposable(modifier: Modifier = Modifier) {

    val barChart = BarChart(LocalContext.current)
    val entries = listOf(
        BarEntry(0f, 10f),
        BarEntry(1f, 20f),
        BarEntry(2f, 30f),
        BarEntry(3f, 40f),
        BarEntry(4f, 50f)
    )

    // Create a BarDataSet from the data
    val dataSet = BarDataSet(entries, "Example Data")
    dataSet.color = ColorTemplate.COLORFUL_COLORS[0]

    // Set up the data for the BarChart
    val barData = BarData(dataSet)
    barChart.data = barData


    barChart.description.isEnabled = false
    barChart.setFitBars(true)

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { barChart }
    )
}
