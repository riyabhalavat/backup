package com.example.lifespaerktech
//
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import com.github.mikephil.charting.charts.BarChart
//import com.github.mikephil.charting.data.BarData
//import com.github.mikephil.charting.data.BarDataSet
//import com.github.mikephil.charting.data.BarEntry
//import com.github.mikephil.charting.utils.ColorTemplate
//import com.example.lifespaerktech.ui.theme.LifespaerktechTheme
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            LifespaerktechTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    BarChartComposable(Modifier.padding(innerPadding))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BarChartComposable(modifier: Modifier = Modifier) {
//
//    val barChart = BarChart(LocalContext.current)
//    val entries = listOf(
//        BarEntry(0f, 10f),
//        BarEntry(1f, 20f),
//        BarEntry(2f, 30f),
//        BarEntry(3f, 40f),
//        BarEntry(4f, 50f)
//    )
//
//    // Create a BarDataSet from the data
//    val dataSet = BarDataSet(entries, "Example Data")
//    dataSet.color = ColorTemplate.COLORFUL_COLORS[0]
//
//    // Set up the data for the BarChart
//    val barData = BarData(dataSet)
//    barChart.data = barData
//
//
//    barChart.description.isEnabled = false
//    barChart.setFitBars(true)
//
//    AndroidView(
//        modifier = modifier.fillMaxSize(),
//        factory = { barChart }
//    )
//}



//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.compose.*
//import androidx.navigation.navArgument
//import java.time.DayOfWeek
//import java.time.LocalDate
//import java.time.YearMonth
//import java.time.format.TextStyle
//import java.util.*
//
//class MainActivity : ComponentActivity() {
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Scaffold(modifier = Modifier.fillMaxSize()) {
//                MyApp()
//            }
//        }
//    }
//}
//
//// Patient Data Class
//data class Patient(
//    val srNo: Int,
//    val name: String,
//    val status: String,
//    val email: String,
//    val phone: String,
//    val datanow: String
//)
//
//// Database Composable to Display Patients
//@Composable
//fun Database(selectedDate: String) {
//    val patients = listOf(
//        Patient(1, "John Doe", "Healthy", "john.doe@email.com", "(123) 456-7890", "2 December 2024"),
//        Patient(2, "Jane Smith", "In Treatment", "jane.smith@email.com", "(098) 765-4321", "3 December 2024"),
//        Patient(3, "Emily Johnson", "Recovering", "emily.johnson@email.com", "(987) 654-3210", "2 December 2024")
//    )
//
//    val filteredPatients = patients.filter { it.datanow == selectedDate }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = "Patients for $selectedDate",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        if (filteredPatients.isEmpty()) {
//            Text("No patients found for this date.")
//        } else {
//            filteredPatients.forEach { patient ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(patient.srNo.toString(), modifier = Modifier.weight(1f))
//                    Text(patient.name, modifier = Modifier.weight(2f))
//                    Text(patient.status, modifier = Modifier.weight(1f))
//                    Text(patient.email, modifier = Modifier.weight(3f))
//                    Text(patient.phone, modifier = Modifier.weight(2f))
//                }
//                Divider(color = Color.LightGray, thickness = 0.5.dp)
//            }
//        }
//    }
//}
//
//// Navigation App Composable
//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "calendar_screen") {
//        composable("calendar_screen") {
//            MainCalendarScreen(navController)
//        }
//        composable(
//            route = "database_screen/{selectedDate}",
//            arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
//            Database(selectedDate)
//        }
//    }
//}
//
//class CalendarDataSource {
//    @SuppressLint("NewApi")
//    fun YearMonth.getDatesForCalendar(): List<LocalDate> {
//        val firstDayOfMonth = LocalDate.of(year, month, 1)
//        val lastDayOfMonth = firstDayOfMonth.withDayOfMonth(lengthOfMonth())
//
//        // Calculate the offset for the first day of the month
//        val firstDayOffset = firstDayOfMonth.dayOfWeek.value % 7
//
//        // Get dates from the previous month to fill the offset
//        val previousMonth = this.minusMonths(1)
//        val lastDayOfPreviousMonth = previousMonth.atEndOfMonth()
//        val previousMonthDates = (lastDayOfPreviousMonth.dayOfMonth - firstDayOffset + 1..lastDayOfPreviousMonth.dayOfMonth)
//            .map { lastDayOfPreviousMonth.minusDays((lastDayOfPreviousMonth.dayOfMonth - it).toLong()) }
//
//        // Get dates for the current month
//        val currentMonthDates = generateSequence(firstDayOfMonth) { it.plusDays(1).takeIf { day -> day <= lastDayOfMonth } }
//            .toList()
//
//        // Get dates for the next month to fill remaining cells
//        val totalDaysShown = previousMonthDates.size + currentMonthDates.size
//        val nextMonth = this.plusMonths(1)
//        val nextMonthDates = generateSequence(nextMonth.atDay(1)) { it.plusDays(1) }
//            .takeWhile { (totalDaysShown + it.dayOfMonth) % 7 != 0 }
//            .toList()
//
//        return previousMonthDates + currentMonthDates + nextMonthDates
//    }
//
//    @SuppressLint("NewApi")
//    fun getDates(yearMonth: YearMonth): List<CalendarUiState.Date> {
//        return yearMonth.getDatesForCalendar().map { date ->
//            CalendarUiState.Date(
//                dayOfMonth = "${date.dayOfMonth}",
//                isSelected = date.isEqual(LocalDate.now()),
//                isCurrentMonth = date.monthValue == yearMonth.monthValue
//            )
//        }
//    }
//}
//
//
//
//// Calendar UI State
//data class CalendarUiState(val date: Date) {
//    data class Date(val dayOfMonth: String, val isSelected: Boolean,val isCurrentMonth: Boolean)
//}
//
//// Main Calendar Screen Composable
//@SuppressLint("NewApi")
//@Composable
//fun MainCalendarScreen(navController: NavHostController) {
//    val calendarDataSource = CalendarDataSource()
//    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
//    val dates = calendarDataSource.getDates(currentMonth)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Month Navigation Row
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
//                Icon(
//                    imageVector = Icons.Filled.KeyboardArrowLeft,
//                    contentDescription = "Previous month"
//                )
//            }
//            Text(
//                text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.weight(1f),
//            )
//            IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
//                Icon(
//                    imageVector = Icons.Filled.KeyboardArrowRight,
//                    contentDescription = "Next month"
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Calendar Grid
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(7),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")) { day ->
//                Text(
//                    text = day,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(4.dp),
//                    color = Color.Black
//                )
//            }
//
//            // Calendar Days
//            items(dates) { calendarDate ->
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .size(40.dp)
//                        .padding(4.dp)
//                        .background(
//                            shape = RoundedCornerShape(4.dp),
//                            color = if (calendarDate.isSelected) Color.Cyan else Color.Transparent
//                        )
//                        .clickable {
//                            if (calendarDate.isCurrentMonth) {
//                                navController.navigate("database_screen/${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${calendarDate.dayOfMonth}")
//                            }
//                        }
//                ) {
//                    Text(
//                        text = calendarDate.dayOfMonth,
//                        fontSize = 14.sp,
//                        color = if (calendarDate.isCurrentMonth) Color.Black else Color.Gray
//                    )
//                }
//            }
//        }
//    }
//}
