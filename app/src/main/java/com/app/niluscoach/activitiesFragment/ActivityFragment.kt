package com.app.niluscoach.activitiesFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.niluscoach.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.fragment_activities.*


class ActivityFragment : Fragment() {

    private lateinit var mTopExerciseAdapter: TopExerciseAdapter
    private lateinit var mWorkoutHistoryAdapter: WorkoutHistoryAdapter
    private lateinit var mVmodel: ActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_activities, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVmodel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        setAdapter()//set adapter
        initData()
        setObserVer()
        setBarData()

//        barChart.setDrawMarkerViews(true)
//        val customMarkerView = CustomMarkerView(context,
//            R.layout.custom_marker_view_layout)
//        barChart.setMarkerView(customMarkerView!!)
    }

    private fun setObserVer() {
        mVmodel.mSucessfulFullWOrkOutData().observe(viewLifecycleOwner, Observer {
            mWorkoutHistoryAdapter.update(it)

        })

        mVmodel.mSucessfulTopWOrkOutData().observe(viewLifecycleOwner, Observer {
            mTopExerciseAdapter.update(it)
        })
    }

    private fun setAdapter() {

        //*********workOUt History Adapter
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerWOrkoutHistory.layoutManager = manager
        mWorkoutHistoryAdapter = WorkoutHistoryAdapter(activity!!)
        recyclerWOrkoutHistory.adapter = mWorkoutHistoryAdapter


//********** top exercise adapter
        val manager1 = LinearLayoutManager(context)
        recyclerTopExercise.layoutManager = manager1
        mTopExerciseAdapter = TopExerciseAdapter(activity!!)
        recyclerTopExercise.adapter = mTopExerciseAdapter

    }

    private fun initData() {
        //set tabs
        tabLayout.addTab(tabLayout.newTab().setText("This Week"))
        tabLayout.addTab(tabLayout.newTab().setText("This Month"))
        tabLayout.addTab(tabLayout.newTab().setText("All Time"))


    }
    fun setBarData(){


        setbarListner()
        val labels = arrayListOf(
            "25 August", "26 August", "27 August",
            "28 August", "29 August", "30 August",
            "31 August"
        )

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        //barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.setDrawGridBackground(false)
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.description.isEnabled = false

        val entries = arrayListOf(

            BarEntry(0f, 70f),
            BarEntry(1f, 60f),
            BarEntry(2f, 50f),
            BarEntry(3f, 40f),
            BarEntry(4f, 80f),
            BarEntry(5f, 70f),
            BarEntry(6f, 60f)
        )
        val barDataSet = BarDataSet(entries, "BarDataSet")
        barDataSet.valueTextSize = 12f
        barDataSet.setDrawValues(false)//hide the values  graph
      //  barDataSet.setHighlightEnabled(false) // allow highlighting for DataSet
        // set this to false to disable the drawing of highlight indicator (lines)
       // barChart.setHighlightPerTapEnabled(false)

//        barChart.getAxisLeft().setTextColor(R.color.light_grey); // left y-axis
//        barChart.getXAxis().setTextColor(R.color.orange);
//        barChart.getLegend().setTextColor(R.color.light_grey);
        barDataSet.highLightColor=R.color.orange
        barChart.data = BarData(barDataSet)
        barChart.invalidate()
//******************set color
        barDataSet.setColors(setGraphColor())
        setAxisBottom()
        hideDefaultProperty()
        setRoundedCorners()
        barChart.animateY(2000)
    }

    private fun setbarListner() {
        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight?) {
                val x = e.x.toString()
                val y = e.y.toString()
               // barChart.highlightValue(h)
                //  val selectedXAxisCount =
                //     x.substringBefore(".") //this value is float so use substringbefore method
                // another method shown below
                //  val nonFloat = barChart.getXAxis().getValueFormatter().getFormattedValue(e.x)
                //if you are display any string in x axis you will get this
                // Toast.makeText(context!!, nonFloat, Toast.LENGTH_SHORT).show()

//                barChart.getAxisLeft().setTextColor(R.color.orange); // left y-axis
//        barChart.getXAxis().setTextColor(R.color.orange);
//        barChart.getLegend().setTextColor(R.color.orange);
            }

            override fun onNothingSelected() {

//                barChart.getAxisLeft().setTextColor(R.color.light_grey); // left y-axis
//                barChart.getXAxis().setTextColor(R.color.light_grey);
//                barChart.getLegend().setTextColor(R.color.light_grey);
            }
        })



    }


//    private fun setBarChart() {
//        val entries = ArrayList<BarEntry>()
//        entries.add(BarEntry(8f, 0))
//        entries.add(BarEntry(7f, 1))
//        entries.add(BarEntry(6f, 2))
//        entries.add(BarEntry(5f, 3))
//        entries.add(BarEntry(9f, 4))
//        entries.add(BarEntry(6f, 5))
//        entries.add(BarEntry(7f, 6))
//
//        val barDataSet = BarDataSet(entries, "")
//        val labels = ArrayList<String>()
//        labels.add("25 Aug")
//        labels.add("26-Aug")
//        labels.add("27-Aug")
//        labels.add("28-Aug")
//        labels.add("29-Aug")
//        labels.add("30-Aug")
//        labels.add("31-Aug")
//        val data = BarData(labels, barDataSet)
//        barChart.data = data // set the data and list of lables into chart
////******************set color
//        barDataSet.setColors(setGraphColor())
//        setAxisBottom()
//        hideDefaultProperty()
//        setRoundedCorners()
//        barChart.animateY(2000)
//    }

    private fun setRoundedCorners() {
        val roundedBarChartRenderer =
            RoundedBarChartRenderer(barChart, barChart.animator, barChart.viewPortHandler)
        roundedBarChartRenderer.setmRadius(12f)
        barChart.renderer = roundedBarChartRenderer
        
        //barChart.renderer = RoundedBarChart(barChart, barChart.animator, barChart.viewPortHandler)
    }

    private fun setGraphColor(): ArrayList<Int> {
        val colors = ArrayList<Int>()
        colors.add(ContextCompat.getColor(context!!, R.color.light_grey))
        colors.add(ContextCompat.getColor(context!!, R.color.medium_grey))
        colors.add(ContextCompat.getColor(context!!, R.color.light_grey))
        colors.add(ContextCompat.getColor(context!!, R.color.orange))
        colors.add(ContextCompat.getColor(context!!, R.color.medium_grey))
        colors.add(ContextCompat.getColor(context!!, R.color.light_grey))
        colors.add(ContextCompat.getColor(context!!, R.color.medium_grey))
       return colors
    }

    private fun hideDefaultProperty() {
        barChart.getAxisLeft().setDrawGridLines(false)
        barChart.getXAxis().setDrawGridLines(false)
//        barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.getLegend().setEnabled(false)//color cube
        barChart.setDescription(null)//descripton text empty
        //barChart.setTouchEnabled(false)
        //  barChart.setClickable(false)
//        barChart.setDoubleTapToZoomEnabled(false)
//        barChart.setDoubleTapToZoomEnabled(false)

//        barChart.setDrawBorders(false)
//        barChart.setDrawGridBackground(false)


//        barChart.getAxisLeft().setDrawGridLines(false)
//        barChart.getAxisLeft().setDrawLabels(false)
//        barChart.getAxisLeft().setDrawAxisLine(false)
//        barChart.getXAxis().setDrawGridLines(false)
//        barChart.getXAxis().setDrawLabels(false)
//        barChart.getXAxis().setDrawAxisLine(false)
//        barChart.getAxisRight().setDrawGridLines(false)
//        barChart.getAxisRight().setDrawLabels(false)
//        barChart.getAxisRight().setDrawAxisLine(false)
    }

    private fun setAxisBottom() {
        val xAxis: XAxis = barChart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
//        barChart.setXAxisRenderer(CustomXAxisRenderer(barChart.getViewPortHandler(),
//            barChart.getXAxis(),
//            barChart.getTransformer(YAxis.AxisDependency.LEFT)))

        // xAxis.axisMinimum = -0.5f
        //val xAxis: XAxis = mChart.getXAxis()
        barChart.setViewPortOffsets(0f, 0f, 0f, 70f)
        val xMax: Float = barChart.getData().getDataSetByIndex(0).getXMax()
        val xMin = 0f
        xAxis.axisMaximum = xMax
        xAxis.axisMinimum = xMin

    }

//    class CustomMarkerView(context: Context?, layoutResource: Int) :
//        MarkerView(context, layoutResource) {
//        private val tvContent: TextView
//
//        // callbacks everytime the MarkerView is redrawn, can be used to update the
//        // content (user-interface)
//        override fun refreshContent(e: Entry, highlight: Highlight) {
//            tvContent.text = "" + e.y // set the entry-value as the display text
//        }
//
//        fun getXOffset(xpos: Float): Int {
//            // this will center the marker-view horizontally
//            return -(width / 2)
//        }
//
//        fun getYOffset(ypos: Float): Int {
//            // this will cause the marker-view to be above the selected value
//            return -height
//        }
//
//        init {
//            // this markerview only displays a textview
//            tvContent = findViewById<View>(R.id.tvContent) as TextView
//        }
//    }
}


