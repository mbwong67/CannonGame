package ua.edu.lnu.cannongame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log


class GameData(private val gameSurface: GameSurface) {
    // 10 seconds
    private var timeLeft = 100000L

    private var totalTime = 0L

    private var timeFromLastShot = 3000L

    var shotsCount = 0L
        get() = field
        private set

    private val mTextPaint: Paint = Paint()

    init {
        mTextPaint.color = Color.BLACK;
        mTextPaint.textSize = 50f
    }

    fun canMakeNewShot() : Boolean {
        return timeFromLastShot >= 3000
    }

    fun trackNewShot(){
        timeFromLastShot = 0
        shotsCount++

        Log.i("GameData", "New shot made, shoutCount=${shotsCount}, timeLeft=${timeLeft}")
    }

    fun updateTime(waitTime: Long){
        timeLeft -= waitTime
        timeFromLastShot += waitTime
        totalTime += waitTime
    }

    fun isGameRunning() : Boolean {
        return timeLeft > 0
    }

    fun draw(canvas: Canvas) {
        mTextPaint.textSize = 45f;
        canvas.drawText("Time remaining: ${timeLeft / 1000f} seconds", 15f, 50f, mTextPaint)
        canvas.drawText("Shots made: $shotsCount", 15f, 100f, mTextPaint)
    }

    fun hitShot() {
        timeLeft += 3000L
        timeFromLastShot = 3000L
    }

    fun missShot() {
        timeLeft -= 2000L
        timeFromLastShot = 3000L
    }

//    fun updateShot(isHit: Boolean){
//        if (isHit){
//
//        }
//    }
}