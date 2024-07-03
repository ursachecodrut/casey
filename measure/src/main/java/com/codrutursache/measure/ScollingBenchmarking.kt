package com.codrutursache.measure

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.Metric
import androidx.benchmark.macro.StartupMode
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ScrollingBenchmarking : AbstractBenchmark(StartupMode.COLD) {

    @Test
    fun accelerateHeavyScreenCompilationFull() = benchmark(CompilationMode.Full())

    @RequiresApi(Build.VERSION_CODES.Q)
    override val metrics: List<Metric> =
        listOf(
            FrameTimingMetric(),
        )

    override fun MacrobenchmarkScope.measureBlock() {
        startActivityAndWait()
        device.waitAndFind(By.text("Sign in with Google")).click()
        device.waitForIdle()

        val scrollable = device.waitAndFind(By.scrollable(true))
        scrollable.setGestureMargin(device.displayWidth / 5)
        scrollable.apply {
            repeat(2) {
                fling(Direction.DOWN)
            }
        }
    }
}
