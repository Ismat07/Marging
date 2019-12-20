package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity {

    GLSurfaceView our_surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        our_surface = new GLSurfaceView(this);
        our_surface.setRenderer(new GLRendererEx());
        setContentView(our_surface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        our_surface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        our_surface.onResume();
    }
}
