package com.seeseesea.core.id;

import org.springframework.context.SmartLifecycle;

/**
 * AbstractSmartLifecycle
 */
public abstract class AbstractSmartLifecycle implements SmartLifecycle {
    private boolean running = false;

    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        try {
            this.destroy();
        } finally {
            running = false;
        }
    }

    protected abstract void destroy();

    @Override
    public boolean isRunning() {
        return running;
    }

}
