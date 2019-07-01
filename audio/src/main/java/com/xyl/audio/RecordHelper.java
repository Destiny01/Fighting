package com.xyl.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xyl on 2019/7/1.
 */
public class RecordHelper {
    private int sampleRateInHz = 44100;//HZ
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    //0.此状态用于控制线程中的循环操作，应用volatile修饰，保持数据的一致性
    private volatile RecordState state = RecordState.IDLE;
    private AudioRecordThread audioRecordThread;
    private File tmpFile = null;

    public void start(String filePath, RecordConfig config) {
        if (state != RecordState.IDLE) {
            //异常
            return;
        }

//        recordFile = new File(filePath);
//        String tempFilePath = getTempFilePath();
        //tmpFile = new File(tempFilePath);

        //1.开启录音线程并准备录音
        audioRecordThread = new AudioRecordThread();
        audioRecordThread.start();
    }

    public void stop() {
        if (state == RecordState.IDLE) {
            return;
        }
        state = RecordState.STOP;
    }

    class AudioRecordThread extends Thread {
        private AudioRecord audioRecord;
        private int bufferSize;

        public AudioRecordThread() {
            //2.根据录音参数构造AudioRecord实体对象
            bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
            //int audioSource, 录制声源
            //int sampleRateInHz, 采样率
            //int channelConfig, 声道数
            //int audioFormat, 采样位数
            //int bufferSizeInBytes 缓冲区大小
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, audioFormat, bufferSize);
        }

        @Override
        public void run() {
            super.run();
            state = RecordState.RECORDING;
            //开始录制
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(tmpFile);
                //开始录制
                audioRecord.startRecording();
                byte[] byteBuffer = new byte[bufferSize];
                while (state == RecordState.RECORDING) {
                    //3.不断读取录音数据到文件中
                    int end = audioRecord.read(byteBuffer, 0, byteBuffer.length);
                    fos.write(byteBuffer, 0, end);
                    fos.flush();
                }
                //4.当执行stop()方法后state != RecordState.RECORDING，终止循环，停止录音
                audioRecord.stop();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Util.closeQuietly(fos);
            }
            state = RecordState.IDLE;
        }
    }

    /**
     * 当前录制状态
     */
    public enum RecordState {
        /**
         * 空闲状态
         */
        IDLE,
        /**
         * 录音中
         */
        RECORDING,
        /**
         * 暂停中
         */
        PAUSE,
        /**
         * 正在停止
         */
        STOP,
        /**
         * 录音流程结束（转换结束）
         */
        FINISH
    }
}
