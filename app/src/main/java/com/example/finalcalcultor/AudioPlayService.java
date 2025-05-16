package com.example.finalcalcultor;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AudioPlayService extends Service {

    private MediaPlayer highPlayer;
    private MediaPlayer lowPlayer;

    public void playHighSound() {
        try {
            if (highPlayer == null) {
                highPlayer = MediaPlayer.create(this, R.raw.mhigh);
            } else {
                highPlayer.seekTo(0); // 回到开头
            }
            if (!highPlayer.isPlaying()) {
                highPlayer.start();
            }
        } catch (Exception e) {
            Log.e("AudioPlayService", "播放 high sound 出错", e);
        }
    }

    public void playLowSound() {
        try {
            if (lowPlayer == null) {
                lowPlayer = MediaPlayer.create(this, R.raw.mlow);
            } else {
                lowPlayer.seekTo(0); // 回到开头
            }
            if (!lowPlayer.isPlaying()) {
                lowPlayer.start();
            }
        } catch (Exception e) {
            Log.e("AudioPlayService", "播放 low sound 出错", e);
        }
    }

    public void stopAllSounds() {
        if (highPlayer != null) {
            if (highPlayer.isPlaying()) {
                highPlayer.pause();
            }
            highPlayer.seekTo(0); // 暂停并回到开头
        }
        if (lowPlayer != null) {
            if (lowPlayer.isPlaying()) {
                lowPlayer.pause();
            }
            lowPlayer.seekTo(0); // 暂停并回到开头
        }
    }

    @Override
    public void onDestroy() {
        if (highPlayer != null) {
            highPlayer.release();
            highPlayer = null;
        }
        if (lowPlayer != null) {
            lowPlayer.release();
            lowPlayer = null;
        }
        super.onDestroy();
    }

    // Binder 实现
    public class LocalBinder extends Binder {
        public AudioPlayService getService() {
            return AudioPlayService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }
}


