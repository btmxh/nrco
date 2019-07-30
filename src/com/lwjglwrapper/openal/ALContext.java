/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.openal;

import com.lwjglwrapper.utils.Utils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.libc.LibCStdlib;

/**
 *
 * @author Welcome
 */
public class ALContext {
    private long device, context;
    private Listener listener;
    
    public ALContext() {
        String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
        device = ALC10.alcOpenDevice(defaultDeviceName);
        
        int[] attribs = {0};
        context = ALC10.alcCreateContext(device, attribs);
        
        ALC10.alcMakeContextCurrent(context);
        
        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);
        
        listener = new Listener();
    }
    
    public void dispose() {
        ALC10.alcDestroyContext(context);
        ALC10.alcCloseDevice(device);
    }
    
    public SoundBuffer loadSound(Class cl, String path) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.mallocInt(1);
            ByteBuffer content = Utils.ioResourceToByteBuffer(cl.getResourceAsStream(path), 8 * 1024);
            ShortBuffer rawAudioBuffer = STBVorbis.stb_vorbis_decode_memory(content, channelsBuffer, sampleRateBuffer);
            
            int channels = channelsBuffer.get();
            int sampleRate = sampleRateBuffer.get();
            
            int format = channels == 1 ? AL10.AL_FORMAT_MONO16:
                         channels == 2? AL10.AL_FORMAT_STEREO16 : -1;
            
            int buffer = AL10.alGenBuffers();
            AL10.alBufferData(buffer, format, rawAudioBuffer, sampleRate);
            
            LibCStdlib.free(rawAudioBuffer);
            
            
            
            return new SoundBuffer(buffer);
        } catch (IOException ex) {
            Logger.getLogger(ALContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateListener() {
        listener.update();
    }
    
    public Listener getListener() {
        return listener;
    }

    public void setDistanceModel(int model) {
        AL10.alDistanceModel(model);
    }
}
