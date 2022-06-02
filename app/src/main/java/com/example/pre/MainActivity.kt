package com.example.pre

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import com.example.pre.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : Activity() {

    private var mBinding: ActivityMainBinding?= null
    private val binding get() = mBinding!!

    //진동관련
    lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 초기화
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        // 생성된 뷰 액티비티에 표시시
        setContentView(binding.root)

        //화면이 꺼지지 않게
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        // elapsedRealtime: 부팅 이후의 밀리초를 리턴 (절전 모드에서 보낸 시간 포함)
        // 사용자가 현재시간을 수정해도 영향 받지 않음
        binding.startBtn.setOnClickListener {
            vibe(3) //시작
//            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
//            binding.chronometer.start()
//            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
//            binding.chronometer.stop()
//            Log.d("로그","${ pauseTime}")
            var uptime = SystemClock.uptimeMillis()

            var mu = 0

            while(true){
                if(SystemClock.uptimeMillis()-uptime==5000L&&mu==0){
                    Log.d("로그","5초 지나감")
                    vibe(1)
                    mu++
                }
                if(SystemClock.uptimeMillis()-uptime==10000L&&mu==1){
                    Log.d("로그","10초 지나감")
                    vibe(1)
                    mu++
                }
                if(SystemClock.uptimeMillis()-uptime==14000L&&mu==2){
                    Log.d("로그","14초 지나감")
                    var timercount = 0
                    timer(period = 700,initialDelay = 0){
                        if(timercount!=3){
                            vibe(2)
                            timercount++
                        }
                        else{
                            cancel()
                        }
                    }
                    mu++
                    break
                }
            }
        }
    }

    private fun vibe(option : Int){
        when(option){
            1->{  //왼쪽 진동
                var timercount = 0
                timer(period = 500,initialDelay = 0){
                    if(timercount!=4){
                        var effect = VibrationEffect.createOneShot(80, 255)
                        vibrator.vibrate(effect)
                        timercount++
                    }
                    else{
                        cancel()
                    }
                }
            }
            2->{  //오른쪽 진동
                var timercount = 0
                timer(period = 240,initialDelay = 0){
                    if(timercount!=2){
                        var effect = VibrationEffect.createOneShot(70, 255)
                        vibrator.vibrate(effect)
                        timercount++
                    }
                    else{
                        cancel()
                    }
                }
            }
            3->{ //시작
                val effect = VibrationEffect.createOneShot(300, 255)
                vibrator.vibrate(effect)
            }
            4->{ //도착
                val effect = VibrationEffect.createOneShot(1000, 255)
                vibrator.vibrate(effect)
            }
        }
    }
}