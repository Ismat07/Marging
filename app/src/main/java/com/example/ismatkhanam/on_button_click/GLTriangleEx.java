package com.example.ismatkhanam.on_button_click;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {

    private float vertices[] = {
            0f, 1f,     //p0
            1f, -1f,    //p1
            -1f, -1f    //p2
    };

    private float rgba_vals[] = {
            1, 1, 0, .5f,
            .25f, 0, .85f, 1,
            0, 1, 1, 1
    };

    private FloatBuffer vert_buff,color_buff;
    private short[] p_index = { 0, 1, 2 };
    private ShortBuffer p_buff;

    public GLTriangleEx(){
        ByteBuffer b_buff = ByteBuffer.allocateDirect(vertices.length * 4);
        b_buff.order(ByteOrder.nativeOrder());
        vert_buff = b_buff.asFloatBuffer();
        vert_buff.put(vertices);
        vert_buff.position(0);

        ByteBuffer pb_buff = ByteBuffer.allocateDirect(p_index.length * 2);
        pb_buff.order(ByteOrder.nativeOrder());
        p_buff = pb_buff.asShortBuffer();
        p_buff.put(p_index);
        p_buff.position(0);

        //this is used for coloring the triangle
        ByteBuffer c_buff = ByteBuffer.allocateDirect(rgba_vals.length * 4);
        c_buff.order(ByteOrder.nativeOrder());
        color_buff = c_buff.asFloatBuffer();
        color_buff.put(rgba_vals);
        color_buff.position(0);
    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);    //for color
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vert_buff);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, color_buff);    //for color
        gl.glDrawElements(GL10.GL_TRIANGLES, p_index.length, GL10.GL_UNSIGNED_SHORT, p_buff);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);    //for color
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
