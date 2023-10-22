package com.example.pro_todo.view.dailyTaskScreen

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.R
import com.example.pro_todo.Utility.displayText
import com.example.pro_todo.Utility.setTextColorRes
import com.example.pro_todo.adapter.CalendarAdapter
import com.example.pro_todo.adapter.TaskAdapter
import com.example.pro_todo.database.TaskDatabase
import com.example.pro_todo.databinding.CalendarDayLayoutBinding
import com.example.pro_todo.databinding.FragmentDailyTaskBinding
import com.example.pro_todo.model.Task
import com.example.pro_todo.repository.TodoRepository
import com.example.pro_todo.view.addTaskScreen.AddTaskFragment
import com.example.pro_todo.viewModel.TaskViewModel
import com.example.pro_todo.viewModel.TaskViewModelFactory
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekCalendarView
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.Date


class DailyTaskFragment : Fragment(), CalendarAdapter.OnItemListener {
    private lateinit var binding: FragmentDailyTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvDailyTask: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val weekCalendarView: WeekCalendarView get() = binding.calendarView
    private val monthCalendarView: com.kizitonwose.calendar.view.CalendarView get() = binding.exOneCalendar
    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyTaskBinding.inflate(inflater, container, false)
        initComponents()
        onClickListener()
        return binding.root
    }


    private fun onClickListener() {
        binding.fabAddTask.setOnClickListener{
            val fragmentManager = childFragmentManager

            val addTaskFragment = AddTaskFragment()
            addTaskFragment.arguments = Bundle().apply {
                putSerializable("task", null)
            }

            addTaskFragment.show(fragmentManager, "my_dialog")
        }


    }


    private fun initComponents() {
        rvDailyTask = binding.rvDailyTask
        adapter = TaskAdapter(requireContext(), onItemCLick, onItemDelete)
        val repository = TodoRepository(TaskDatabase.getInstance(requireContext()).cateDao(), TaskDatabase.getInstance(requireContext()).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[TaskViewModel::class.java]
        rvDailyTask.adapter = adapter
        rvDailyTask.layoutManager = LinearLayoutManager(requireContext())
        updateAdapterForDate(LocalDate.now())
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvDailyTask)

        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        setupMonthCalendar(startMonth, endMonth, currentMonth, daysOfWeek)
        setupWeekCalendar(startMonth, endMonth, currentMonth, daysOfWeek)

        monthCalendarView.isInvisible = !binding.weekModeCheckBox.isChecked
        weekCalendarView.isInvisible = binding.weekModeCheckBox.isChecked

        calendarToWeek(false)
        weekCalendarView.scrollToDate(LocalDate.now())
        binding.weekModeCheckBox.setOnCheckedChangeListener(weekModeToggled)
        binding.legendLayout.root.children
            .map { it as TextView }
            .forEachIndexed { index, textView ->
                textView.text = daysOfWeek[index].displayText()
                textView.setTextColorRes(R.color.black)
            }


    }

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            if (direction == ItemTouchHelper.LEFT) {
                adapter.deleteTask(position)
               // adapter.notifyItemChanged(position)
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
            } else if (direction == ItemTouchHelper.RIGHT) {

            }
        }
    }


    private val onItemCLick: (Task) -> Unit={
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.arguments = Bundle().apply {
            putSerializable("task", it)
        }
        addTaskFragment.show(childFragmentManager, "my_dialog")
        taskViewModel.updateTask(it)
    }

    private val onItemDelete: (Task) -> Unit={
        taskViewModel.deleteTask(it)
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        TODO("Not yet implemented")
    }

    private fun updateAdapterForDate(date: LocalDate) {

        val day = date.dayOfMonth
        val month = date.monthValue
        val year = date.year
        val dateToDate = Date(year, month-1, day)

        taskViewModel.getAllTask().observe(viewLifecycleOwner, Observer { tasks ->
            tasks.let {
                val filterdTask = tasks.filter { task ->
                    task.date.toString().substring(0,10) + task.date.toString().substring(30, 34) == dateToDate.toString().substring(0,10) + dateToDate.toString().substring(30, 34)
                }
//                Toast.makeText(requireContext(), dateToDate.toString().substring(0,10) + dateToDate.toString().substring(30, 34), Toast.LENGTH_SHORT).show()
                adapter.setTasks(filterdTask)
            }
        })
    }

    private fun setupMonthCalendar(
        startMonth: YearMonth,
        endMonth: YearMonth,
        currentMonth: YearMonth,
        daysOfWeek: List<DayOfWeek>,
    ) {
        class DayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: CalendarDay
            val textView = CalendarDayLayoutBinding.bind(view).exOneDayText

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {
                        dateClicked(date = day.date)
                        updateAdapterForDate(day.date)
                    }
                }
            }
        }
        monthCalendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                bindDate(data.date, container.textView, data.position == DayPosition.MonthDate)
            }
        }
        monthCalendarView.monthScrollListener = { updateTitle() }
        monthCalendarView.setup(startMonth, endMonth, daysOfWeek.first())
        monthCalendarView.scrollToMonth(currentMonth)
    }
    private fun setupWeekCalendar(
        startMonth: YearMonth,
        endMonth: YearMonth,
        currentMonth: YearMonth,
        daysOfWeek: List<DayOfWeek>,
    ) {
        class WeekDayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: WeekDay
            val textView = CalendarDayLayoutBinding.bind(view).exOneDayText

            init {
                view.setOnClickListener {
                    if (day.position == WeekDayPosition.RangeDate) {
                        dateClicked(date = day.date)
                        updateAdapterForDate(day.date)
                    }
                }
            }
        }
        weekCalendarView.dayBinder = object : WeekDayBinder<WeekDayViewContainer> {
            override fun create(view: View): WeekDayViewContainer = WeekDayViewContainer(view)
            override fun bind(container: WeekDayViewContainer, data: WeekDay) {
                container.day = data
                bindDate(data.date, container.textView, data.position == WeekDayPosition.RangeDate)
            }
        }
        weekCalendarView.weekScrollListener = { updateTitle() }
        weekCalendarView.setup(
            startMonth.atDay(15),
            endMonth.atEndOfMonth(),
            daysOfWeek.component1(),
        )
        weekCalendarView.scrollToWeek(currentMonth.atStartOfMonth())
    }

    private fun bindDate(date: LocalDate, textView: TextView, isSelectable: Boolean) {
        textView.text = date.dayOfMonth.toString()
        if (isSelectable) {
            when {
                selectedDates.contains(date) -> {
                    textView.setTextColorRes(R.color.white)
                    textView.setBackgroundResource(R.drawable.selected_bg)
                }
                today == date -> {
                    textView.setTextColorRes(R.color.white)
                    textView.setBackgroundResource(R.drawable.today_bg)
                }
                else -> {
                    textView.setTextColorRes(R.color.color_5)
                    textView.background = null
                }
            }
        } else {
            textView.setTextColorRes(R.color.task_back)
            textView.background = null
        }
    }

    private fun dateClicked(date: LocalDate) {
        if (selectedDates.contains(date)) {
            selectedDates.remove(date)
        } else {
            selectedDates.add(date)
        }
        // Refresh both calendar views..
        monthCalendarView.notifyDateChanged(date)
        weekCalendarView.notifyDateChanged(date)
    }

    @SuppressLint("SetTextI18n")
    private fun updateTitle() {
        val isMonthMode = binding.weekModeCheckBox.isChecked
        if (isMonthMode) {
            val month = monthCalendarView.findFirstVisibleMonth()?.yearMonth ?: return
            binding.exOneYearText.text = month.year.toString()
            binding.exOneMonthText.text = month.month.displayText(short = false)
        } else {
            val week = weekCalendarView.findFirstVisibleWeek() ?: return
            // In week mode, we show the header a bit differently because
            // an index can contain dates from different months/years.
            val firstDate = week.days.first().date
            val lastDate = week.days.last().date
            if (firstDate.yearMonth == lastDate.yearMonth) {
                binding.exOneYearText.text = firstDate.year.toString()
                binding.exOneMonthText.text = firstDate.month.displayText(short = false)
            } else {
                binding.exOneMonthText.text =
                    firstDate.month.displayText(short = false) + " - " +
                            lastDate.month.displayText(short = false)
                if (firstDate.year == lastDate.year) {
                    binding.exOneYearText.text = firstDate.year.toString()
                } else {
                    binding.exOneYearText.text = "${firstDate.year} - ${lastDate.year}"
                }
            }
        }
    }

    private val weekModeToggled = object : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton, monthToWeek: Boolean) {
            // We want the first visible day to remain visible after the
            // change so we scroll to the position on the target calendar.
            if (!monthToWeek) {
                val targetDate = monthCalendarView.findFirstVisibleDay()?.date ?: return
                weekCalendarView.scrollToWeek(targetDate)
            } else {
                // It is possible to have two months in the visible week (30 | 31 | 1 | 2 | 3 | 4 | 5)
                // We always choose the second one. Please use what works best for your use case.
                val targetMonth = weekCalendarView.findLastVisibleDay()?.date?.yearMonth ?: return
                monthCalendarView.scrollToMonth(targetMonth)
            }

            val weekHeight = weekCalendarView.height
            // If OutDateStyle is EndOfGrid, you could simply multiply weekHeight by 6.
            val visibleMonthHeight = weekHeight *
                    monthCalendarView.findFirstVisibleMonth()?.weekDays.orEmpty().count()

            val oldHeight = if (!monthToWeek) visibleMonthHeight else weekHeight
            val newHeight = if (!monthToWeek) weekHeight else visibleMonthHeight

            // Animate calendar height changes.
            val animator = ValueAnimator.ofInt(oldHeight, newHeight)
            animator.addUpdateListener { anim ->
                monthCalendarView.updateLayoutParams {
                    height = anim.animatedValue as Int
                }
                // A bug is causing the month calendar to not redraw its children
                // with the updated height during animation, this is a workaround.
                monthCalendarView.children.forEach { child ->
                    child.requestLayout()
                }
            }

            animator.doOnStart {
                if (monthToWeek) {
                    weekCalendarView.isInvisible = true
                    monthCalendarView.isVisible = true
                }
            }
            animator.doOnEnd {
                if (!monthToWeek) {
                    weekCalendarView.isVisible = true
                    monthCalendarView.isInvisible = true
                } else {
                    // Allow the month calendar to be able to expand to 6-week months
                    // in case we animated using the height of a visible 5-week month.
                    // Not needed if OutDateStyle is EndOfGrid.
                    monthCalendarView.updateLayoutParams { height =
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    }
                }
                updateTitle()
            }
            animator.duration = 250
            animator.start()
        }
    }

    fun calendarToWeek(monthToWeek: Boolean){
        val weekHeight = weekCalendarView.height
        // If OutDateStyle is EndOfGrid, you could simply multiply weekHeight by 6.
        val visibleMonthHeight = weekHeight *
                monthCalendarView.findFirstVisibleMonth()?.weekDays.orEmpty().count()

        val oldHeight = if (monthToWeek) visibleMonthHeight else weekHeight
        val newHeight = if (monthToWeek) weekHeight else visibleMonthHeight

        // Animate calendar height changes.
        val animator = ValueAnimator.ofInt(oldHeight, newHeight)
        animator.addUpdateListener { anim ->
            monthCalendarView.updateLayoutParams {
                height = anim.animatedValue as Int
            }
            // A bug is causing the month calendar to not redraw its children
            // with the updated height during animation, this is a workaround.
            monthCalendarView.children.forEach { child ->
                child.requestLayout()
            }
        }
    }
}