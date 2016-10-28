package TestGame;

import Engine.Window.Window;

public class Main {

    Renderer renderer;

    public void run() {
        renderer = new Renderer();
        renderer.run();
    }

    public static void main(String[] args) {
        new Main().run();
    }

}