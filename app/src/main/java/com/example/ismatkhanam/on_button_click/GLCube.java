package com.example.ismatkhanam.on_button_click;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {
    private float vertices[] = {
            1, 1, -1,   //p0 - Top Front Right
            1, -1, -1,  //p1 - Bottom Front Right
            -1, -1, -1, //p2 - Bottom Front Left
            -1, 1, -1,  //p3 - Top Front Left
            1, 1, 1,    //p4 - Top Back Right
            1, -1, 1,   //p5 - Bottom Back Right
            -1, -1, 1,  //p6 - Bottom Back Left
            -1, 1, 1,   //p7 - Top Back Left
    };

    private FloatBuffer vert_buff;
    private short[] p_index = {
            3,4,0,  0,4,1,  3,0,1,
            3,7,4,  7,6,4,  7,3,6,
            3,1,2,  1,6,2,  6,3,2,
            1,4,5,  5,6,1,  6,5,4
    };
    private ShortBuffer p_buff;

    public GLCube(){
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
    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vert_buff);
        gl.glDrawElements(GL10.GL_TRIANGLES, p_index.length, GL10.GL_UNSIGNED_SHORT, p_buff);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
