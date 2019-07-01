package com.xyl.audio;

import android.media.AudioFormat;

/**
 * @author xyl on 2019/7/1.
 */
public class RecordConfig {
    //int audioSource, 录制声源
    //int sampleRateInHz, 采样率
    //int channelConfig, 声道数
    //int audioFormat, 采样位数
    private int sampleRateInHz = 44100;//HZ
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

}
