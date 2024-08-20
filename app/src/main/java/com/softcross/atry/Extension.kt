package com.softcross.atry

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

@SuppressLint("RestrictedApi")
fun Fragment.getRoutes(): List<NavHelperItem> {
    val navController = findNavController()
    val backStackEntries = navController.currentBackStack.value
    val routes = mutableListOf<Int>()
    for (entry in backStackEntries) {
        routes.add(entry.destination.id)
    }
    return routes.formatRoutes()
}

fun List<Int>.formatRoutes(): List<NavHelperItem> {
    val list = mutableListOf<NavHelperItem>()
    this.forEach { route ->
        println(route)
        println(R.id.firstFragment.toString())
        when (route) {
            R.id.firstFragment -> list.add(
                NavHelperItem(
                    route,
                    "First",
                    R.drawable.baseline_looks_one_24,
                    0
                )
            )

            R.id.secondFragment -> list.add(
                NavHelperItem(
                    route,
                    "Second",
                    R.drawable.baseline_looks_two_24,
                    0
                )
            )

            R.id.thirdFragment -> list.add(
                NavHelperItem(
                    route,
                    "Third",
                    R.drawable.baseline_looks_3_24,
                    0
                )
            )

            R.id.fourthFragment -> list.add(
                NavHelperItem(
                    route,
                    "Fourth",
                    R.drawable.baseline_looks_4_24,
                    1
                )
            )
            R.id.fifthFragment ->{
                list.add(
                    NavHelperItem(
                        route,
                        "Fifth",
                        R.drawable.baseline_looks_5_24,
                        1
                    )
                )
                list.add(
                    NavHelperItem(
                        route,
                        "6",
                        R.drawable.baseline_looks_5_24,
                        1
                    )
                )
                list.add(
                    NavHelperItem(
                        route,
                        "7",
                        R.drawable.baseline_looks_5_24,
                        1
                    )
                )
                list.add(
                    NavHelperItem(
                        route,
                        "8",
                        R.drawable.baseline_looks_5_24,
                        1
                    )
                )
            }

        }
    }
    return list
}

fun showDialogWithButtons(context: Context, buttonLabels: List<NavHelperItem>) {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.dialog_custom)
    val buttonContainer = dialog.findViewById<LinearLayout>(R.id.buttonContainer)

    buttonLabels.chunked(3).forEachIndexed { index, rowItems ->
        val innerLinearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // Set to MATCH_PARENT for width
                LinearLayout.LayoutParams.WRAP_CONTENT // Set to WRAP_CONTENT for height
            ).apply {
                gravity = if (index % 2 == 1) Gravity.END else Gravity.START
            }
            // Alternate layout direction based on the index
            layoutDirection = if (index % 2 == 1) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
        }

        rowItems.forEachIndexed { itemIndex, item ->
            val relativeLayout = RelativeLayout(context).apply{
                setPadding(16)
                layoutParams = LinearLayout.LayoutParams(
                    0, // Set width to 0 to allow weight distribution
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f, // Distribute space evenly among buttons
                ).apply {
                    leftMargin = if (index == 0 && itemIndex == 0 && buttonLabels.size > 1) 40 else 0
                }
            }

            val button = FloatingActionButton(context).apply {
                setImageDrawable(context.resources.getDrawable(item.icon, null))
                setPadding(8)
                setOnClickListener {
                    // Handle button click
                }
                id = View.generateViewId()
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.CENTER_HORIZONTAL)
                }
            }

            val textView = TextView(context).apply {
                text = item.title
                setPadding(8)
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.BELOW, button.id)
                    addRule(RelativeLayout.CENTER_HORIZONTAL)
                }
            }

            relativeLayout.addView(button)
            relativeLayout.addView(textView)

            val arrowView = ImageView(context).apply {
                setImageDrawable(resources.getDrawable(R.drawable.baseline_arrow_right_alt_24, null))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    gravity = Gravity.CENTER_VERTICAL
                    bottomMargin = 32
                }
            }

            val bottomArrowView = ImageView(context).apply {
                setImageDrawable(
                    if (index % 2 == 0){
                        resources.getDrawable(R.drawable.icon, null)
                    }else{
                        resources.getDrawable(R.drawable.icon2, null)
                    }
                )
                layoutParams = LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    gravity = Gravity.CENTER_VERTICAL
                    bottomMargin = 32
                }
            }

            val bottomArrowView2 = ImageView(context).apply {
                setImageDrawable(
                    if (index % 2 == 0){
                        resources.getDrawable(R.drawable.icon4, null)
                    }else{
                        resources.getDrawable(R.drawable.icon3, null)
                    }
                )
                layoutParams = LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    gravity = Gravity.CENTER_VERTICAL
                    bottomMargin = 32
                }
            }
            if (itemIndex == 0 && index != 0){
                innerLinearLayout.addView(bottomArrowView2)
            }
            innerLinearLayout.addView(relativeLayout)
            if ((itemIndex != buttonLabels.size-1-(3*index)) && (itemIndex % 2 != 0 || itemIndex == 0)){
                innerLinearLayout.addView(arrowView)
            }
            println("Index: $index, ItemIndex: $itemIndex, item: $item, item name: ${item.title}, rowItems.size: ${rowItems.size}, rowItems: $rowItems, buttonLabels.size: ${buttonLabels.size}")
            if (itemIndex % 2 == 0 && itemIndex != 0 && index != ((buttonLabels.size-1)/3) ){
                innerLinearLayout.addView(bottomArrowView)
            }
        }

        buttonContainer.addView(innerLinearLayout)
    }

    val window = dialog.window
    window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    dialog.show()
}
