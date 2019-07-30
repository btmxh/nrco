/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import nrco.states.handlers.AssetHandler;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Welcome
 */
public class Utils {

    public static String loadFileAsString(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append("\n").append(line);
        }

        return content.substring(1);
    }
    
    public static String loadResourceAsString(Class cl, String path) throws IOException {
        return loadResourceAsString(cl, path, StandardCharsets.UTF_8);
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resource the resource to read
     * @param bufferSize the initial buffer size
     *
     * @return the resource data
     *
     * @throws IOException if an IO error occurs
     */
    public static ByteBuffer ioResourceToByteBuffer(InputStream source, int bufferSize) throws IOException {
        Objects.requireNonNull(source);
        
        ByteBuffer buffer = BufferUtils.createByteBuffer(bufferSize);
        
        try {
            byte[] buf = new byte[8192];
            while (true) {
                int bytes = source.read(buf, 0, buf.length);
                if (bytes == -1) {
                    break;
                }
                if (buffer.remaining() < bytes) {
                    buffer = resizeBuffer(buffer, Math.max(buffer.capacity() * 2, buffer.capacity() - buffer.remaining() + bytes));
                }
                buffer.put(buf, 0, bytes);
            }
            buffer.flip();
        } finally {
            source.close();
        }

        return buffer;
    }

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public static String loadResourceAsString(String resPath) throws IOException {
        return loadResourceAsString(Utils.class, resPath);
    }

    public static String loadResourceAsString(Class cl, String path, Charset charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(cl.getResourceAsStream(path), charset));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append("\n").append(line);
        }

        return content.substring(1);
    }
    
}
